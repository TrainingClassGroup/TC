package com.tc.edu.tc.MyBase;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Administrator on 15-3-15.
 */
public abstract class CMenuTree {
    protected Activity active = null;

    public CMenuTree(Activity active) {
        this.active = active;
    }

    abstract protected View getMenubar(int level, String key);

    abstract protected void onTouchMenu(View v, MotionEvent event, int level, String key, Object paras);

    abstract protected View createMenu(String name, final OnTouchListener mOnTouchListener, final Object paras);

    abstract protected void registMenu(final View menubarView, final View menu);

    public void load(final JSONObject jsonObj) throws JSONException {
        Iterator it = jsonObj.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            JSONObject value = jsonObj.getJSONObject(key);
            load(key, value);
        }
    }

    public void load(final String key, final JSONObject jsonObj) throws JSONException {
        load(0, key, jsonObj);
    }

    public void load(final int level, final String key, final JSONObject jsonObj) throws JSONException {
        if (key == null || key.length() == 0 || jsonObj.getString("menu") == null) return;

        OnTouchListener touchListener = new OnTouchListener() {
            @Override
            public void onTouch(View v, MotionEvent event, final Object paras) {
                if (paras == null) return;

                try {
                    onTouchMenu(v, event, level, key, jsonObj.get("paras"));

                    JSONObject subs = (JSONObject) paras;
                    Iterator it = subs.keys();
                    while (it.hasNext()) {
                        try {
                            String subkey = (String) it.next();
                            JSONObject subvalue = subs.getJSONObject(subkey);
                            load(level + 1, subkey, subvalue);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        regist(getMenubar(level, key), jsonObj.getString("menu"), touchListener, jsonObj.get("sub"));
    }

    protected void regist(final View menubarView, final String name, final OnTouchListener mOnTouchListener, final Object paras) {
        registMenu(menubarView, createMenu(name, mOnTouchListener, paras));
    }

    protected interface OnTouchListener {
        void onTouch(View v, MotionEvent event, Object paras);
    }

}
