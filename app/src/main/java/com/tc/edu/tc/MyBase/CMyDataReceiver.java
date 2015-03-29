package com.tc.edu.tc.MyBase;

import android.app.Activity;
import android.content.BroadcastReceiver;

/**
 * Created by Administrator on 15-3-26.
 */
abstract public class CMyDataReceiver extends BroadcastReceiver {

    protected Activity active;

    public CMyDataReceiver(){}
    public CMyDataReceiver(final Activity active) {
        this.active = active;
    }
}
