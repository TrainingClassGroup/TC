package com.tc.edu.tc.MyProject.Data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Head2PullDownService extends Service {
    public Head2PullDownService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String jsonData = "{\"menus\":{\"1\":{\"menu\":\"学前\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]}}},\"2\":{\"menu\":\"小学\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]}}},\"3\":{\"menu\":\"小升初\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]}}},\"4\":{\"menu\":\"初中\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]}}},\"5\":{\"menu\":\"中考\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]}}},\"6\":{\"menu\":\"高中\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]},\"5\":{\"menu\":\"化学\",\"paras\":\"\",\"sub\":[]}}},\"7\":{\"menu\":\"高考\",\"paras\":\"\",\"sub\":{\"1\":{\"menu\":\"数学\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"语文\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"英语\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"物理\",\"paras\":\"\",\"sub\":[]},\"5\":{\"menu\":\"化学\",\"paras\":\"\",\"sub\":[]}}},\"8\":{\"menu\":\"艺术\",\"paras\":\"\",\"sub\":{\"6\":{\"menu\":\"舞蹈\",\"paras\":\"\",\"sub\":[]},\"7\":{\"menu\":\"乐器\",\"paras\":\"\",\"sub\":[]},\"8\":{\"menu\":\"歌唱\",\"paras\":\"\",\"sub\":[]}}},\"9\":{\"menu\":\"其它\",\"paras\":\"\",\"sub\":{\"\":{\"menu\":null,\"paras\":\"\",\"sub\":[]}}}},\"time\":{\"1\":{\"menu\":\"全天\",\"paras\":\"\",\"sub\":[]},\"2\":{\"menu\":\"上午\",\"paras\":\"\",\"sub\":[]},\"3\":{\"menu\":\"下午\",\"paras\":\"\",\"sub\":[]},\"4\":{\"menu\":\"晚上\",\"paras\":\"\",\"sub\":[]}}}";

        Intent intentData = new Intent();
        intentData.putExtra("jsonData", jsonData);

        intentData.setAction("Head2PullDownService");
        sendBroadcast(intentData);

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
