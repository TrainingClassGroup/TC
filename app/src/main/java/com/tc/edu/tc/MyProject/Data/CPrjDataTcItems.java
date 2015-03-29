package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.tc.edu.tc.MyProject.Base.CTcItemView;
import com.tc.edu.tc.MyProject._CDataDemo;
import com.tc.edu.tc.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcItems extends AsyncTask<CPrjDataTcItems.CParams, Integer, String> {
    static public class CParams {
        protected double lng;
        protected double lat;
        protected String catalog;
        protected String curricum;
        protected int rownum = 10;
        protected int page = 0;
        protected String type = "json";

        public CParams(double lng, double lat, String catalog, String curricum, int rownum, int page, String type) {
            this.lng = lng;
            this.lat = lat;
            this.catalog = catalog;
            this.curricum = curricum;
            this.rownum = rownum;
            this.page = page;
            this.type = type;
        }
    };
    private Activity activity;

    public CPrjDataTcItems(Activity activity) {
       this.activity = activity;
    }

    @Override
    protected String doInBackground(CPrjDataTcItems.CParams... params) {
        return _CDataDemo.getTcItems();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String jsonData) {
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
                String text = value.getString("text");
                if(text!=null) {
                    text = text.replace("\n","");
                    if (text.length() > 32) text = text.substring(0, 32) + "...";
                    tcItem.setText(text);
                }
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
