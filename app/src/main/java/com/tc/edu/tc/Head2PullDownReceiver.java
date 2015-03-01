package com.tc.edu.tc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Head2PullDownReceiver extends BroadcastReceiver {

    private Activity active;

    public Head2PullDownReceiver(final Activity active) {
        this.active=active;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String jsonData = bundle.getString("jsonData");
        try {
            JSONObject jsonObj=new JSONObject(jsonData);
            JSONArray jsonObjs = jsonObj.getJSONArray("menu12");
            for (int i = 0; i < jsonObjs.length(); i++) {
                loadMenus(((JSONObject) jsonObjs.opt(i)));
            }

            ((LinearLayout)active.findViewById(R.id.head2_pulldown_menu_3)).removeAllViews();
            new CRegistMenuForHead2PullDown(active).setOnActionListener(new CRegistMenuForHead2PullDown.OnTouchListener() {
                @Override
                public void onTouch(View v, MotionEvent event, final Object paras) {
                    ((TextView)active.findViewById(R.id.head2_menu3_text3)).setText(((TextView)v).getText());
                }
            }).regist(R.id.head2_pulldown_menu_3, jsonObj.getJSONArray("menu3"), jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void loadMenus(JSONObject jsonObj) {
        try {
            new CRegistMenuForHead2PullDown(active).setOnActionListener(new CRegistMenuForHead2PullDown.OnTouchListener() {
                @Override
                public void onTouch(View v, MotionEvent event, final Object paras) {
                    ((TextView)active.findViewById(R.id.head2_menu1_text1)).setText(((TextView)v).getText());
                    ((TextView)active.findViewById(R.id.head2_menu2_text2)).setText(active.getResources().getString(R.string.head2_menu2_text));
                    ((LinearLayout) active.findViewById(R.id.head2_pulldown_menu_2)).removeAllViews();
                    try {
                        JSONObject jsonObj = (JSONObject) paras;
                        new CRegistMenuForHead2PullDown(active).setOnActionListener(new CRegistMenuForHead2PullDown.OnTouchListener() {
                            @Override
                            public void onTouch(View v, MotionEvent event, final Object paras) {
                                ((TextView)active.findViewById(R.id.head2_menu2_text2)).setText(((TextView)v).getText());
                            }
                        }).regist(R.id.head2_pulldown_menu_2, jsonObj.getJSONArray("menu2"), jsonObj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).regist(R.id.head2_pulldown_menu_1, jsonObj.getString("menu1"), jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
