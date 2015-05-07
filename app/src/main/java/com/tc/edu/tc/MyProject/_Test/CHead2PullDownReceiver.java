package com.tc.edu.tc.MyProject._Test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyBase.CMyDataReceiver;
import com.tc.edu.tc.MyProject.Base.CPrjMenuTree;
import com.tc.edu.tc.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 Intent intent = new Intent(activity, CHead2PullDownService.class);
 activity.startService(intent);


 IntentFilter filter = new IntentFilter();
 filter.addAction(new CHead2PullDownService().getAction());
 activity.registerReceiver(new CHead2PullDownReceiver(activity), filter);
 */
public class CHead2PullDownReceiver extends CMyDataReceiver {

    public CHead2PullDownReceiver(final Activity active) {
        super(active);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String jsonData = bundle.getString("jsonData");
        try {
            JSONObject jsonObj = new JSONObject(jsonData);
            new CPrjMenuTree(active) {

                @Override
                protected View getMenubar(int level, String key) {
                    int viewId = -1;
                    switch (level) {
                        case 0:
                            viewId = R.id.head2_pulldown_menu_1;
                            break;
                        case 1:
                            viewId = R.id.head2_pulldown_menu_2;
                            break;
                    }

                    return active.findViewById(viewId);
                }

                @Override
                protected void onTouchMenu(View v, MotionEvent event, int level, String key, Object paras) {
                    clearMenubar(v, event, level, key, paras);

                    if (level == 0) {
                        ((TextView) active.findViewById(R.id.head2_menu1_text1)).setText(((TextView) v).getText());
                        ((TextView) active.findViewById(R.id.head2_menu2_text2)).setText(active.getResources().getString(R.string.head2_menu2_text));
                        ((LinearLayout) active.findViewById(R.id.head2_pulldown_menu_2)).removeAllViews();
                    } else if (level == 1) {
                        ((TextView) active.findViewById(R.id.head2_menu2_text2)).setText(((TextView) v).getText());
                    }
                }
            }.load(jsonObj.getJSONObject("menus"));
            //
            new CPrjMenuTree(active) {

                @Override
                protected View getMenubar(int level, String key) {
                    int viewId = -1;
                    switch (level) {
                        case 0:
                            viewId = R.id.head2_pulldown_menu_3;
                            break;
                    }

                    return active.findViewById(viewId);
                }

                @Override
                protected void onTouchMenu(View v, MotionEvent event, int level, String key, Object paras) {
                    clearMenubar(v, event, level, key, paras);

                    ((TextView) active.findViewById(R.id.head2_menu3_text3)).setText(((TextView) v).getText());
                }
            }.load(jsonObj.getJSONObject("time"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

