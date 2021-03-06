/*
 * DataRange.java
 * Android-Charts
 *
 * Created by limc on 2014.
 *
 * Copyright 2011 limc.cn All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.limc.androidcharts.model;

import cn.limc.androidcharts.series.ChartDataSet;

/**
 * DataRange
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-22 limc create v1.0 <br>
 *
 */
public interface DataRange {
    
    public static final int DEFAULT_DATA_MULTIPLE = 1;
    public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;
    
    int getDataMultiple();
    double getMaxValue();
    double getMinValue();
    void setMaxValue(double max);
    void setMinValue(double min);
    
    double getValueRange();
    double valueForRate(double rate);
    
    boolean isAutoCalcValueRange();
    void calcValueRange(ChartDataSet data);
    void optimizeValueRange();
    
    RangeCalculator getRangeCalculator();
    void setRangeCalculator(RangeCalculator rangeCalculator);
}
