package media_sci.com.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Bassem on 12/15/2015.
 */
public class UserData {

    SharedPreferences userData;
    SharedPreferences user_calories;

    public UserData(Context context) {
        userData = context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
        user_calories = context.getSharedPreferences("user_calories"
                , Context.MODE_APPEND);
    }

    public void ClearUserData() {
        SharedPreferences.Editor editor = userData.edit();
        editor.clear();
        editor.commit();
    }

    public void setUserData(int user_id, String first_name, String last_name, String mobileNo,
                            String email, String password, String gender,
                            String age, String height, String weight, int exercise
            , double calories) {

        SharedPreferences.Editor editor = userData.edit();
        editor.putInt("user_id", user_id);
        editor.putString("first_name", first_name);
        editor.putString("last_name", last_name);
        editor.putString("mobile_no", mobileNo);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("gender", gender);
        editor.putString("age", age);
        editor.putString("height", height);
        editor.putString("weight", weight);
        editor.putInt("exercise", exercise);
        //  editor.putInt("verify", verify);
        // editor.putString("verification_code", verification_code);
        editor.commit();
        Log.e("User_Date", "Saved");

        editor = user_calories.edit();
        editor.putFloat("calories", Float.valueOf(calories + ""));
        editor.commit();

    }

    public int GetUserID() {

        return userData.getInt("user_id", -1);
    }

    public double GetCalories() {

        return user_calories.getFloat("calories", -1);
    }

    public void UpdateCalories(double calories) {

        SharedPreferences.Editor editor = user_calories.edit();
        editor.putFloat("calories", Float.valueOf(calories + ""));
        editor.commit();
    }

    public int GetExercise() {

        return userData.getInt("exercise", -1);
    }

    public int GetAge() {
        String age_str = userData.getString("age", "");
        if (!age_str.equals(""))
            return Integer.parseInt(age_str);
        else return -1;
    }

    public double GetWeight() {
        String weight_str = userData.getString("weight", "");
        if (!weight_str.equals(""))
            return Double.parseDouble(weight_str);
        else return -1;
    }

    public double GetHeight() {
        String height_str = userData.getString("height", "");
        if (!height_str.equals(""))
            return Double.parseDouble(height_str);
        else return -1;
    }

    public int GetGender() {
        String gender_str = userData.getString("gender", "");
        if (!gender_str.equals(""))
            return Integer.parseInt(gender_str);
        else return -1;
    }


}
