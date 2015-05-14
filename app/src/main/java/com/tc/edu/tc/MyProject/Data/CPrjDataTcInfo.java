package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyBase.CMyApplication;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CTcCommentView;
import com.tc.edu.tc.MyProject.Base.CTcInfoScheduleView;
import com.tc.edu.tc.R;

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
        loadSchedule(id);
        loadComment(id);
    }

    private void loadSchedule(final String id){
        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_TrainingClassInfo");

        dataRequest.getParams().put("{paras:{company_id:\"" + id + "\",type:json}}");
        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);
                    // myApplication.getCache().put("xxx", "123");
                    ((TextView)activity.findViewById(R.id.tcinfo_name)).setText(jsonObj.getJSONObject("tc").getString("company"));
                    LinearLayout tcinfo_schedules = (LinearLayout) activity.findViewById(R.id.tcinfo_schedules);
                    tcinfo_schedules.removeAllViews();
                    JSONArray schedules=jsonObj.getJSONArray("schedule");
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

            private void setSchedule(CTcInfoScheduleView tcInfoScheduleView,int week, String str){
                String[] ss=str.split("/");
                for(int i=0; i<ss.length;i++){
                    if(ss[i].length()>0) {
                        tcInfoScheduleView.setSchedule(week, i, ss[i]);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable error) {
                error.printStackTrace();
            }
        });
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
