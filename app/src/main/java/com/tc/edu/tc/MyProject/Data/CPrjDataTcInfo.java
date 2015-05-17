package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyBase.CMyApplication;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CTcCommentView;
import com.tc.edu.tc.MyProject.Base.CTcInfoScheduleView;
import com.tc.edu.tc.R;
import com.tc.edu.tc.TcMapActivity;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcInfo {

    private Activity activity;
    private CMyApplication myApplication;

    public CPrjDataTcInfo(Activity activity) {
        this.activity = activity;
        this.myApplication = (CMyApplication) activity.getApplication();
    }

    public void execute(final String id) {
        loadTcInfo(id);
        loadComment(id);
    }

    private void loadTcInfo(final String id){
        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_TrainingClassInfo");

        dataRequest.getParams().put("{paras:{company_id:\"" + id + "\",type:json}}");
        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    final JSONObject jsonObj = new JSONObject(jsonData);
                    // myApplication.getCache().put("xxx", "123");
                    ((TextView)activity.findViewById(R.id.tcinfo_name)).setText(jsonObj.getJSONObject("tc").getString("company"));
                    LinearLayout tcinfo_schedules = (LinearLayout) activity.findViewById(R.id.tcinfo_schedules);
                    tcinfo_schedules.removeAllViews();
                    JSONArray schedules=jsonObj.getJSONArray("schedule");
                    loadSchedule(schedules);
                    //
                    ((LinearLayout)activity.findViewById(R.id.tcinfo_map)).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_UP:
                                    try {
                                        Intent intent = new Intent(activity, TcMapActivity.class);
                                        JSONObject tcJson = jsonObj.getJSONObject("tc");
                                        intent.putExtra("company", tcJson.getString("company"));
                                        intent.putExtra("tel", tcJson.getString("tel"));
                                        intent.putExtra("address", tcJson.getString("address"));
                                        intent.putExtra("lng", tcJson.getDouble("lng"));
                                        intent.putExtra("lat", tcJson.getDouble("lat"));
                                        activity.startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                            }
                            return true;
                        }
                    });
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

    private void setSchedule(CTcInfoScheduleView tcInfoScheduleView,int week, String str){
        String[] ss=str.split("/");
        for(int i=0; i<ss.length;i++){
            if(ss[i].length()>0) {
                tcInfoScheduleView.setSchedule(week, i, ss[i]);
            }
        }
    }

    private void loadSchedule(JSONArray schedules){
        try {
            LinearLayout tcinfo_schedules = (LinearLayout) activity.findViewById(R.id.tcinfo_schedules);
            tcinfo_schedules.removeAllViews();

            for(int i = 0;i<schedules.length();i++){
                JSONObject schedule = schedules.getJSONObject(i);
                CTcInfoScheduleView tcInfoScheduleView = new CTcInfoScheduleView(activity);
                tcInfoScheduleView.regist(tcinfo_schedules);
                tcInfoScheduleView.setCourse(schedule.getString("course"));
                tcInfoScheduleView.setSeniority(schedule.getString("teacher"));
                for(int w=0;w<7;w++) {
                    setSchedule(tcInfoScheduleView, w, schedule.getString("week" + w));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadComment(final String id){
        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_Comment");

        dataRequest.getParams().put("{paras:{company_id:\"" + id + "\",rownum:50,page:0,type:json}}");
        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    LinearLayout tcCommentlister = (LinearLayout)activity.findViewById(R.id.tcCommentlister);
                    JSONObject jsonObj = new JSONObject(jsonData);
                    Iterator it = jsonObj.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        final JSONObject value = jsonObj.getJSONObject(key);
                        CTcCommentView commentView = new CTcCommentView(activity);
                        commentView.setId(Integer.parseInt(key));
                        commentView.setComment(value.getString("comment"));
                        commentView.setCommentUser(value.getString("username"));
                        commentView.regist(tcCommentlister);
                    }
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
