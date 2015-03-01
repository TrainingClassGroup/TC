package com.tc.edu.tc;

import android.app.Activity;
import android.app.AlertDialog;

/**
 * Created by Administrator on 15-3-1.
 */
public class CAlert {
    public static void alert(Activity activity, String title, String message){
        new AlertDialog.Builder(activity).setTitle(title).setMessage(message).setPositiveButton("OK",null).show();
    }
}
