package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CTcItemView;
import com.tc.edu.tc.R;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcItems{

    private Activity activity;

    public CPrjDataTcItems(Activity activity) {
       this.activity = activity;
    }

    public void execute(String parasJson){
        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_TrainingClass");

        dataRequest.getParams().put(parasJson);

        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);

                    LinearLayout tclister = (LinearLayout) activity.findViewById(R.id.tclister);

                    Iterator it = jsonObj.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        final JSONObject value = jsonObj.getJSONObject(key);

                        CTcItemView tcItem = new CTcItemView(activity);

                        tcItem.setId(value.getInt("index"));
                        tcItem.setImageResourceByImageId(value.getInt("logo_image"));
                        tcItem.setText(value.getString("text"));
                        tcItem.setReservation("预约：0人");
                        tcItem.setRegisted("报名：0人");
                        tcItem.setMemo("评论：(0)");
                        tcItem.setDistance("距离："+value.getString("distance"));

                        int index=1;
                        for (int i = 1; i < tclister.getChildCount(); i++) {
                            if(tclister.getChildAt(i).getId() > tcItem.getId()) break;
                            index++;
                        }
                        tclister.addView(tcItem, index);

                        tcItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Log.i("xxx", value.getString("company"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
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
