package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CTcItemView;
import com.tc.edu.tc.R;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcItems4ListView {
    private class MyListAdapter extends BaseAdapter {
        private List<CTcItemView> data;
        public MyListAdapter( List<CTcItemView> data) {
            this.data = data;
        }

        public int getCount() {
            return data.size();
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public CTcItemView getItem(int position) {
            return data.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView =getItem(position);
            }
            return convertView;
        }
    };

    private Activity activity=null;
    private int listViewId = 0;
    private static int page=0;
    private static boolean isloaded = false;

    public CPrjDataTcItems4ListView(Activity activity, int listViewId) {
       this.activity = activity;
        this.listViewId = listViewId;
    }

    public void execute(String parasJson){
        if(isloaded) return;
        isloaded = true;

        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_TrainingClass");

        dataRequest.getParams().put(parasJson);

        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);

                    List<CTcItemView> data = new ArrayList<>();

                    Iterator it = jsonObj.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        final JSONObject value = jsonObj.getJSONObject(key);

                        CTcItemView tcItem = new CTcItemView(activity);

                        tcItem.setId(value.getInt("index"));
                        tcItem.setImageResource(Base64.decode(value.getString("imagedata").getBytes(), Base64.DEFAULT));
                       // tcItem.setImageResourceByImageId(value.getInt("logo_image"));
                        tcItem.setText(value.getString("text"));
                        tcItem.setReservation("预约：0人");
                        tcItem.setRegisted("报名：0人");
                        tcItem.setMemo("评论：(0)");
                        tcItem.setDistance("距离："+value.getString("distance"));

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

                        data.add(tcItem);
                    }

                    ListView listView = (ListView)activity.findViewById(listViewId);
                    MyListAdapter adapter = new MyListAdapter(data);
                    listView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isloaded=false;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void updateClassLister(boolean clearLister) {
        if(isloaded) return;

        if(clearLister) {
            LinearLayout tclister = (LinearLayout) activity.findViewById(R.id.tclister);
            for (int i = 1; i < tclister.getChildCount() - 1; i++) {
                tclister.removeViewAt(i);
            }
            page=0;
        }
        else{
            page++;
        }

        String catalog=((TextView) activity.findViewById(R.id.head2_menu1_text1)).getText().toString();
        String course=((TextView) activity.findViewById(R.id.head2_menu2_text2)).getText().toString();
        String schedule=((TextView) activity.findViewById(R.id.head2_menu3_text3)).getText().toString();

        execute("{paras:{lng:123.417095,lat:41.836929,catalog:\""+catalog+"\",curriculum:\""+course+"\",schedule:\""+schedule+"\",rownum:5,page:"+page+",type:\"json\"}}");
    }
}
