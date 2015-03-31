package com.tc.edu.tc.MyProject._Test;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyProject.Base.CPrjMenuTree;
import com.tc.edu.tc.MyProject._CDataDemo;
import com.tc.edu.tc.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataHead2PullDown extends AsyncTask<CPrjDataHead2PullDown.CParams, Integer, String> {
    static public class CParams {
    }

    ;

    private Activity activity;

    public CPrjDataHead2PullDown(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(CPrjDataHead2PullDown.CParams... params) {

        return _CDataDemo.getHead2PullDown();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ((TextView) activity.findViewById(R.id.head2_menu1_text1)).setText("加载中...");
        ((TextView) activity.findViewById(R.id.head2_menu2_text2)).setText("加载中...");
        ((TextView) activity.findViewById(R.id.head2_menu3_text3)).setText("加载中...");
    }

    @Override
    protected void onPostExecute(String jsonData) {
        ((TextView) activity.findViewById(R.id.head2_menu1_text1)).setText("年级");
        ((TextView) activity.findViewById(R.id.head2_menu2_text2)).setText("课程");
        ((TextView) activity.findViewById(R.id.head2_menu3_text3)).setText("授时");

        try {
            JSONObject jsonObj = new JSONObject(jsonData);
            new CPrjMenuTree(activity) {

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

                    return getActivity().findViewById(viewId);
                }

                @Override
                protected void onTouchMenu(View v, MotionEvent event, int level, String key, Object paras) {
                    clearMenubar(v, event, level, key, paras);

                    if (level == 0) {
                        ((TextView) getActivity().findViewById(R.id.head2_menu1_text1)).setText(((TextView) v).getText());
                        ((TextView) getActivity().findViewById(R.id.head2_menu2_text2)).setText(activity.getResources().getString(R.string.head2_menu2_text));
                        ((LinearLayout) getActivity().findViewById(R.id.head2_pulldown_menu_2)).removeAllViews();
                    } else if (level == 1) {
                        ((TextView) getActivity().findViewById(R.id.head2_menu2_text2)).setText(((TextView) v).getText());
                    }
                }
            }.load(jsonObj.getJSONObject("menus"));
            //
            new CPrjMenuTree(activity) {

                @Override
                protected View getMenubar(int level, String key) {
                    int viewId = -1;
                    switch (level) {
                        case 0:
                            viewId = R.id.head2_pulldown_menu_3;
                            break;
                    }

                    return getActivity().findViewById(viewId);
                }

                @Override
                protected void onTouchMenu(View v, MotionEvent event, int level, String key, Object paras) {
                    clearMenubar(v, event, level, key, paras);

                    ((TextView) getActivity().findViewById(R.id.head2_menu3_text3)).setText(((TextView) v).getText());
                }
            }.load(jsonObj.getJSONObject("time"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
