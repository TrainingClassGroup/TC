package com.tc.edu.tc.MyProject.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tc.edu.tc.MyProject.Base.CPrjDataRequest;
import com.tc.edu.tc.MyProject.Base.CTcItemView;
import com.tc.edu.tc.R;
import com.tc.edu.tc.TcInfoActivity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Iterator;

/**
 * Created by Administrator on 15-3-28.
 */
public class CPrjDataTcItems4ScrollView {

    private Activity activity=null;
    private static int page=0;
    private static boolean isloading = false;

    public CPrjDataTcItems4ScrollView(Activity activity) {
       this.activity = activity;
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

                    final LinearLayout tclister = (LinearLayout) activity.findViewById(R.id.tclister);

                    Iterator it = jsonObj.keys();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        final JSONObject value = jsonObj.getJSONObject(key);

                        final CTcItemView tcItem = new CTcItemView(activity);

                        tcItem.setValue(value);
                        tcItem.setId(value.getInt("index"));
                       // tcItem.setImageResource(Base64.decode(value.getString("imagedata").getBytes(), Base64.DEFAULT));
                        tcItem.setImageResourceByImageId(value.getInt("logo_image"));
                        tcItem.setText(value.getString("text"));
                        tcItem.setReservation("预约：" + value.getString("reservation_cnt") + "人");
                        tcItem.setRegisted("报名：0人");
                        tcItem.setMemo("评论：("+value.getString("comment_cnt")+")");
                        tcItem.setDistance("距离："+value.getString("distance"));

                        tcItem.regist(tclister);

                        tcItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tcItem.findViewById(R.id.tcitem_layout).setBackgroundColor(R.color.material_deep_teal_200);
                                 new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            tcItem.findViewById(R.id.tcitem_layout).setBackgroundColor(0);
                                            Intent intent = new Intent(activity, TcInfoActivity.class);
                                            intent.putExtra("company_id", value.getString("id"));
                                            intent.putExtra("logo_image", value.getInt("logo_image"));


                                            DecimalFormat df = new DecimalFormat("0.00");
                                            String distance = df.format(Double.parseDouble(value.getString("distance")));
                                            String comment_cnt = value.getString("comment_cnt");
                                            if(Integer.parseInt(comment_cnt)>99) comment_cnt="99+";
                                            String reservation_cnt =  value.getString("reservation_cnt");
                                            if(Integer.parseInt(reservation_cnt)>99) reservation_cnt="99+";

                                            intent.putExtra("comment_cnt", comment_cnt);
                                            intent.putExtra("reservation_cnt",reservation_cnt);
                                            intent.putExtra("distance", distance);
                                            activity.startActivity(intent);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, 250);
                            }
                        });
                    }
                } catch (JSONException e) {
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

        String catalog=((TextView) activity.findViewById(R.id.head2_menu1_text1)).getText().toString();
        String course=((TextView) activity.findViewById(R.id.head2_menu2_text2)).getText().toString();
        String schedule=((TextView) activity.findViewById(R.id.head2_menu3_text3)).getText().toString();

        execute("{paras:{lng:123.417095,lat:41.836929,catalog:\""+catalog+"\",curriculum:\""+course+"\",schedule:\""+schedule+"\",rownum:5,page:"+page+",type:\"json\"}}");
    }
}
