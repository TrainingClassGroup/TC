package com.tc.edu.tc.MyBase;

import android.app.Application;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 15-4-26.
 */
public class CMyApplication extends Application {
    public static class Cache extends HashMap<String, Object>{
        private HashMap<String, Integer> expMap = new HashMap<>();

        public Cache(){
            Executors.newScheduledThreadPool(1).schedule(new Runnable() {
                @Override
                public void run() {
                    updateAllExpiration();
                }
            }, 10, TimeUnit.SECONDS);
        }

        private int getSecond(){
            return Calendar.getInstance().get(Calendar.SECOND);
        }

        private int getExpiration(String key){
            Integer exp = expMap.get(key);
            return (exp == null) ? Integer.MAX_VALUE : exp;
        }

        private void updateAllExpiration(){
            Iterator iter = expMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String)entry.getKey();
                Integer val = (Integer)entry.getValue();
                if(getSecond() > val){
                    super.remove(key);
                    expMap.remove(key);
                }
            }
        }

        public Object put(String key, Object value, int expiration) {
            expMap.put(key, getSecond() + expiration);
            return super.put(key, value);
        }

        public Object get(String key) {
            return (getSecond() <= getExpiration(key)) ? super.get(key) : null;
        }
    }


    private Cache mCache = new Cache();

    public Cache getCache() {
        return mCache;
    }
}
