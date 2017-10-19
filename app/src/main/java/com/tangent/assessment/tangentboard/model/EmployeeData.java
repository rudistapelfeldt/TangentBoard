package com.tangent.assessment.tangentboard.model;

import java.util.ArrayList;

/**
 * Created by Rudi Stapelfeldt on 2017/10/19.
 */

public class EmployeeData {

    private ArrayList<StatisticsData> mData;

    public EmployeeData(ArrayList<StatisticsData> mData) {
        this.mData = mData;
    }

    public EmployeeData(){

    }

    public ArrayList<StatisticsData> getmData() {
        return mData;
    }

    public void setmData(ArrayList<StatisticsData> mData) {
        this.mData = mData;
    }
}
