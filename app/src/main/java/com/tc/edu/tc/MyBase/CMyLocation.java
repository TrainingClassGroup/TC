package com.tc.edu.tc.MyBase;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Administrator on 15-5-20.
 */
public class CMyLocation {

    private LocationManager lm = null;
    private String providerName = null;

    public CMyLocation(Activity activity) {
        lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        /*
        // 返回所有已知的位置提供者的名称列表，包括未获准访问或调用活动目前已停用的。
        List<String> lp = lm.getAllProviders();
        for (String item:lp){
            Log.i("8023", "可用位置服务：" + item);
        }
        */

        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false); //设置位置服务免费
        criteria.setAccuracy(Criteria.ACCURACY_COARSE); //设置水平位置精度
        providerName = lm.getBestProvider(criteria, true);//getBestProvider 只有允许访问调用活动的位置供应商将被返回
        //Log.i("8023", "------位置服务：" + providerName);
    }

    public Location getLocation(){
        if (providerName != null) {
            Location location = lm.getLastKnownLocation(providerName);
            //Log.i("8023", "-------" + location);
            return location;
        }
        else {
            return null;
            //Toast.makeText(this, "1.请检查网络连接 \n2.请打开我的位置", Toast.LENGTH_SHORT).show();
        }
    }
}
