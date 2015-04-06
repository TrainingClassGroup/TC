package com.tc.edu.tc.MyProject.Data;

import android.util.Base64;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CTcItemView;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcLogoImage {

    private CTcItemView mCTcItemView;

    public CPrjDataTcLogoImage(CTcItemView mCTcItemView) {
       this.mCTcItemView = mCTcItemView;
    }

    public void execute(final String id){
        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_Image");

        dataRequest.getParams().put("{paras:{id:\""+id+"\",type:json}}");

        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);
                    JSONObject imgJsonObj = jsonObj.getJSONObject(id);
                    mCTcItemView.setImageResource(Base64.decode(imgJsonObj.getString("imagedata").getBytes(), Base64.DEFAULT));
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
