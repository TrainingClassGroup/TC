package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CPrjMenuTree;
import com.tc.edu.tc.R;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-3-30.
 */
public class CPrjDataHead2PullDown {
    private Activity activity;

    public CPrjDataHead2PullDown(Activity activity){
        this.activity = activity;
    }

    public void execute(String parasJson){
        ((LinearLayout) activity.findViewById(R.id.head2_pulldown_menu_1)).removeAllViews();
        ((LinearLayout) activity.findViewById(R.id.head2_pulldown_menu_2)).removeAllViews();
        ((LinearLayout) activity.findViewById(R.id.head2_pulldown_menu_3)).removeAllViews();

        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_ClassNameAndCourseAndTime");

        dataRequest.getParams().put(parasJson);

        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                String jsonData = new String(bytes);
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
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable error) {
                error.printStackTrace();
            }
        });
    }
}

