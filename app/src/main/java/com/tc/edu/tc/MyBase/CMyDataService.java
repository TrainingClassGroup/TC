package com.tc.edu.tc.MyBase;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Administrator on 15-3-26.
 */
abstract public class CMyDataService extends Service {

    public CMyDataService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intentData = new Intent();
        intentData.putExtra("jsonData", getJsonData());

        intentData.setAction(getAction());
        sendBroadcast(intentData);
        return super.onStartCommand(intent, flags, startId);
    }

    abstract protected String getJsonData();
    abstract public String getAction();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
