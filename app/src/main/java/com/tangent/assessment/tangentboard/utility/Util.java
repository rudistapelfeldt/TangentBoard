package com.tangent.assessment.tangentboard.utility;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Rudi Stapelfeldt on 2017/10/18.
 */

public class Util {

    //METHOD TO SHOW TOOAST MESSAGE WHEN NEEDED
    public static void showToast(Activity activity, String message){
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
    }
}
