package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyBase.CMyLocation;
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

        public MyListAdapter() {
        }

        public int getCount() {
            return data.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView =data.get(position);
            }
            else{
                CTcItemView tcItem = (CTcItemView)convertView;
                try {
                    JSONObject value = data.get(position).getValue();
                    tcItem.setValue(value);
                    tcItem.setId(value.getInt("index"));
                    //tcItem.setImageResource(Base64.decode(value.getString("imagedata").getBytes(), Base64.DEFAULT));
                    tcItem.setImageResourceByImageId(value.getInt("logo_image"));
                    tcItem.setText(value.getString("text"));
                    tcItem.setReservation("预约：0人");
                    tcItem.setRegisted("报名：0人");
                    tcItem.setMemo("评论：(0)");
                    tcItem.setDistance("距离："+value.getString("distance"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return convertView;
        }
    };

    private Activity activity=null;
    static List<CTcItemView> data = new ArrayList<>();
    private int listViewId = 0;
    private static int page=0;
    private static boolean isloading = false;

    public CPrjDataTcItems4ListView(Activity activity, int listViewId) {
       this.activity = activity;
        this.listViewId = listViewId;
    }

    public void execute(String parasJson){
        if(isloading) return;
        isloading = true;

        CPrjDataRequest dataRequest = new CPrjDataRequest("CData_TrainingClass");
        dataRequest.getParams().put(parasJson);
        dataRequest.post(new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                String jsonData = new String(bytes);
                try {
                    JSONObject jsonObj = new JSONObject(jsonData);

                    Iterator it = jsonObj.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        final JSONObject value = jsonObj.getJSONObject(key);

                        CTcItemView tcItem = new CTcItemView(activity);

                        tcItem.setValue(value);
                        tcItem.setId(value.getInt("index"));
                        //tcItem.setImageResource(Base64.decode(value.getString("imagedata").getBytes(), Base64.DEFAULT));
                        tcItem.setImageResourceByImageId(value.getInt("logo_image"));
                        tcItem.setText(value.getString("text"));
                        tcItem.setReservation("预约：0人");
                        tcItem.setRegisted("报名：0人");
                        tcItem.setMemo("评论：(0)");
                        tcItem.setDistance("距离："+value.getString("distance"));

                        data.add(tcItem);
                    }

                    ListView listView = (ListView)activity.findViewById(listViewId);
                    MyListAdapter adapter = new MyListAdapter();
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                Log.i("xxx", data.get(position).getValue().getString("company"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
//
                    int totalHeight = 0;
                    for (int i = 0, len = adapter.getCount(); i < len; i++) {
                        View listItem = adapter.getView(i, null, listView);
                        listItem.measure(0, 0);
                        totalHeight += listItem.getMeasuredHeight();
                    }
                    ViewGroup.LayoutParams params = listView.getLayoutParams();
                    params.height = totalHeight+ (listView.getDividerHeight() * (adapter.getCount() - 1));
                    listView.setLayoutParams(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isloading =false;
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void updateClassLister(boolean clearLister) {
        if(isloading) return;

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

        int rownum = 5;
        String catalog=((TextView) activity.findViewById(R.id.head2_menu1_text1)).getText().toString();
        String course=((TextView) activity.findViewById(R.id.head2_menu2_text2)).getText().toString();
        String schedule=((TextView) activity.findViewById(R.id.head2_menu3_text3)).getText().toString();

        CMyLocation myLocation =  new CMyLocation(activity);
        Location location = myLocation.getLocation();
        execute("{paras:{lng:"+location.getLongitude()+",lat:"+location.getLatitude()+",catalog:\""+catalog+"\",curriculum:\""+course+"\",schedule:\""+schedule+"\",rownum:"+rownum+",page:"+page+",type:\"json\"}}");
    }
}
