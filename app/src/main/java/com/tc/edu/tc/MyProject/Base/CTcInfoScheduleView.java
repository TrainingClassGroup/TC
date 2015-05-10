package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyProject.Data.CPrjDataTcImage;
import com.tc.edu.tc.R;

import org.json.JSONObject;

/**
 * Created by Administrator on 15-5-3.
 */
public class CTcInfoScheduleView extends LinearLayout {
    private View view = null;
    private LinearLayout layout = null;

    private JSONObject value = null;

    public CTcInfoScheduleView(Context context) {
        super(context);
        _init(context);
    }

    public CTcInfoScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    private void _init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.tcinfo_schedule_view, this, true);
        layout = (LinearLayout) view.findViewById(R.id.tcinfo);
    }

    public void regist(final LinearLayout layout) {
        layout.addView(CTcInfoScheduleView.this);
    }

    public JSONObject getValue() {
        return value;
    }

    public void setValue(JSONObject value) {
        this.value = value;
    }

    public ImageView getImage() {
        return (ImageView) view.findViewById(R.id.tcinfo_image);
    }

    public void setImageResource(String url) {
        CPrjDownloadBitmap downloadBitmap = new CPrjDownloadBitmap(getImage());
        downloadBitmap.execute(url);
    }

    public void setImageResource(byte[] b) {
        getImage().setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length, null));
    }

    public void setImageResource(Bitmap m) {
        getImage().setImageBitmap(m);
    }

    public void setImageResourceByImageId(int id) {
        CPrjDataTcImage prjDataTcImage = new CPrjDataTcImage();
        prjDataTcImage.setOnLoadListener(new CPrjDataTcImage.OnLoadListener() {
            @Override
            public void onload(byte[] b) {
                setImageResource(b);
            }
        });
        prjDataTcImage.execute(""+id);
    }

    //课程
    public void setCourse(String string){
        ((TextView) view.findViewById(R.id.tcinfo_course)).setText(string);
    }

    //资历
    public void setSeniority(String string){
        ((TextView) view.findViewById(R.id.tcinfo_seniority)).setText(string);
    }

    //课程
    public void setSchedule(int week,int time, String string){
        int wt[][]={ {R.id.r1c1,R.id.r1c2,R.id.r1c3,R.id.r1c4,R.id.r1c5,R.id.r1c6,R.id.r1c7},
                    {R.id.r2c1,R.id.r2c2,R.id.r2c3,R.id.r2c4,R.id.r2c5,R.id.r2c6,R.id.r2c7},
                    {R.id.r3c1,R.id.r3c2,R.id.r3c3,R.id.r3c4,R.id.r3c5,R.id.r3c6,R.id.r3c7}};
        TextView textView = (TextView) view.findViewById(wt[time][week]);
        textView.setText(string);
        textView.setBackgroundColor(0xFFF);
    }
}
