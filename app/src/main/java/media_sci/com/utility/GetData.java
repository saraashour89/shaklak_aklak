package media_sci.com.utility;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import media_sci.com.models.LastUpdate;
import media_sci.com.shaklak_aklak.MainActivity;

/**
 * Created by Bassem on 12/13/2015.
 */
public class GetData {

    static String CategoryURL =
            "http://192.168.1.227/shaklk/public/api/categories/all";
    static String RestaurantURL =
            "http://192.168.1.227/shaklk/public/api/categories/restaurants";
    Context context;

    public GetData(Context context) {
        this.context = context;
        new DataAsyncTask().execute();
    }

    private void GetCategory() {
        HttpClient httpClient = Utility.SetTimeOut();
        HttpPost httpPost = Utility.SetHttpPost(CategoryURL);
        String last_update;
        int lastUpdate_id;
        LastUpdate lastUpdate = LastUpdate.GetLastUpdate(context, "category");
        if (lastUpdate != null) {
            last_update = lastUpdate.last_update;
            lastUpdate_id = lastUpdate.lastUpdate_id;
        } else {
            last_update = "0";
            lastUpdate_id = 1;
        }
        try {

            List<NameValuePair> data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("lastupdate", last_update));
            httpPost.setEntity(new UrlEncodedFormEntity(data));
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            Log.e("json_status", "" + status);
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String category = EntityUtils.toString(entity);

                JSONObject CategoryJson = new JSONObject(category);
                last_update = CategoryJson.getString("lastupdate");
                lastUpdate = new LastUpdate(lastUpdate_id, "category", last_update);
                LastUpdate.InsertLastUpdate(lastUpdate, context);

                // parse update and delete category
                ParseData.ParseCategory(CategoryJson, context);
            }
        } catch (Exception e) {
            Log.e("category error", "" + e);
        }

    }

    private void GetRestaurant() {
        HttpClient httpClient = Utility.SetTimeOut();
        HttpPost httpPost = Utility.SetHttpPost(RestaurantURL);

        String last_update;
        int lastUpdate_id;
        LastUpdate lastUpdate = LastUpdate.GetLastUpdate(context, "restaurant");
        if (lastUpdate != null) {
            last_update = lastUpdate.last_update;
            lastUpdate_id = lastUpdate.lastUpdate_id;
        } else {
            last_update = "0";
            lastUpdate_id = 2;
        }
        try {

            List<NameValuePair> data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("lastupdate", last_update));
            httpPost.setEntity(new UrlEncodedFormEntity(data));
            HttpResponse response = httpClient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            Log.e("json_status", "" + status);
            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String restaurant = EntityUtils.toString(entity);

                JSONObject RestaurantJson = new JSONObject(restaurant);
                last_update = RestaurantJson.getString("lastupdate");
                lastUpdate = new LastUpdate(lastUpdate_id, "restaurant", last_update);
                LastUpdate.InsertLastUpdate(lastUpdate, context);

                // parse update and delete category
                ParseData.ParseRestaurant(RestaurantJson, context);
            }
        } catch (Exception e) {
            Log.e("restaurant error", "" + e);
        }

    }

    public class DataAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            GetCategory();
            GetRestaurant();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }
}