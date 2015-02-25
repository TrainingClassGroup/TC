package com.tc.edu.tc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
      //  EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    EventBus.getDefault().post("aaa");

                    Thread.sleep(1000);
                }
                catch(Exception e){

                }
            }
        }).start();
*/
        return super.onStartCommand(intent, flags, startId);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
