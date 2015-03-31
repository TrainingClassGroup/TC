package com.tc.edu.tc.MyBase;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Administrator on 15-3-30.
 */
public class CDataRequest {

    static public class Params extends RequestParams{
        /*
         * Example: "{paras: {type: json}}"
         */
        public void put(String jsonString){
            try {
                put(new JSONObject(jsonString));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        public void put(JSONObject paraValue){
            _bindPara(null,paraValue);
        }

        private void _bindPara(String name, Object jsonValue) {
            try {
                if (jsonValue instanceof JSONArray) {
                    if(name==null) name="_";
                    JSONArray jsonArray = (JSONArray) jsonValue;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        _bindPara(name + "[" + i + "]", jsonArray.get(i));
                    }
                } else if (jsonValue instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) jsonValue;
                    Iterator it = jsonObject.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        if(name==null){
                            _bindPara(key, jsonObject.get(key));
                        }
                        else {
                            _bindPara(name + "[" + key + "]", jsonObject.get(key));
                        }
                    }
                }
                else {
                    put(name, jsonValue);
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }

    private String url;
    private AsyncHttpClient client;
    private CDataRequest.Params params;

    public CDataRequest(String url) {
        this.url = url;
        client = new AsyncHttpClient();
        params = new CDataRequest.Params();
    }

    public String getUrl() {
        return url;
    }

    public AsyncHttpClient getClient() {
        return client;
    }

    public CDataRequest.Params getParams() {
        return params;
    }

    public void post(AsyncHttpResponseHandler asyncHttpResponseHandler) {
        client.post(url, params, asyncHttpResponseHandler);
    }

    public void get(AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(url, params, asyncHttpResponseHandler);
    }
}
