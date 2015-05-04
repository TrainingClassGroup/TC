package com.tc.edu.tc.MyProject.Data;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyBase.CMyApplication;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcInfo {

    private CMyApplication myApplication;

    public CPrjDataTcInfo(CMyApplication myApplication) {
       this.myApplication = myApplication;
    }

    public void execute(final String id){
        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_TrainingClassInfo");

        dataRequest.getParams().put("{paras:{company_id:\""+id+"\",type:json}}");
        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);
                    myApplication.getCache().put("xxx", "123");



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
