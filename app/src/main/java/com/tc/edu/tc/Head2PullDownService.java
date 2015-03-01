package com.tc.edu.tc;

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

        String jsonData="{\n" +
                "\"menu12\":[\n" +
                "{\"menu1\":\"学前\",\"menu2\":[\"数学\",\"语文\",\"英语\"]},\n" +
                "{\"menu1\":\"小学\",\"menu2\":[\"数学\",\"语文\",\"英语\"]},\n" +
                "{\"menu1\":\"小升初\",\"menu2\":[\"数学\",\"语文\",\"英语\"]},\n" +
                "{\"menu1\":\"初中\",\"menu2\":[\"数学\",\"语文\",\"英语\",\"物理\",\"化学\"]},\n" +
                "{\"menu1\":\"中考\",\"menu2\":[\"数学\",\"语文\",\"英语\",\"物理\",\"化学\"]},\n" +
                "{\"menu1\":\"高中\",\"menu2\":[\"数学\",\"语文\",\"英语\",\"物理\",\"化学\"]},\n" +
                "{\"menu1\":\"高考\",\"menu2\":[\"数学\",\"语文\",\"英语\",\"物理\",\"化学\"]},\n" +
                "{\"menu1\":\"艺术\",\"menu2\":[\"舞蹈\",\"乐器\",\"歌唱\"]},\n" +
                "{\"menu1\":\"其他\",\"menu2\":[]}\n" +
                "],\n" +
                "\"menu3\":[\"全天\",\"上午\",\"下午\",\"晚上\"]\n" +
                "}";

        Intent intentData=new Intent();
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
