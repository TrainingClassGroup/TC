package com.tc.edu.tc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tc.edu.tc.MyProject.Base.CPrjTcInfoScollView;
import com.tc.edu.tc.MyProject.Data.CPrjDataTcImage;
import com.tc.edu.tc.MyProject.Data.CPrjDataTcInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 15-4-23.
 */
public class TcInfoActivity extends Activity implements GestureDetector.OnGestureListener{
    private String company_id;
    private String distance;
    private String comment_cnt;
    private String reservation_cnt;
    private int logo_image;

    private ViewFlipper viewFlipper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tcinfo);

        Intent i = getIntent();
        company_id = i.getStringExtra("company_id");
        logo_image = i.getIntExtra("logo_image",-1);
        distance = i.getStringExtra("distance");
        comment_cnt = i.getStringExtra("comment_cnt");
        reservation_cnt = i.getStringExtra("reservation_cnt");

        ((TextView)findViewById(R.id.tcinfo_text_distance)).setText("距离："+distance+"Km");
        ((TextView)findViewById(R.id.tcinfo_text_reservation)).setText("预约："+reservation_cnt+"人");
        ((TextView)findViewById(R.id.tcinfo_text_comment)).setText("评论："+comment_cnt);

        new CPrjDataTcImage().setOnLoadListener(new CPrjDataTcImage.OnLoadListener() {
            @Override
            public void onload(byte[] b) {
                ((ImageView)findViewById(R.id.tcinfo_logo)).setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length, null));
            }
        }).execute("" + logo_image, false);


        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        CPrjTcInfoScollView scrollView1 = (CPrjTcInfoScollView) findViewById(R.id.scrollView1);
        scrollView1.bindActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        CPrjDataTcInfo dataTcItems = new CPrjDataTcInfo(this);
        dataTcItems.execute(company_id);

        //String aaa = (String)((CMyApplication)getApplication()).getCache().get("xxx");

        ((TextView)findViewById(R.id.tcinfo_text_tel)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        String phone=((TextView)v).getText().toString();
                        Pattern pattern = Pattern.compile("([0-9-]+)");
                        Matcher matcher = pattern.matcher(phone);
                        if(matcher.find()){
                            phone = matcher.group(1);
                            Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                            startActivity(intent);
                        }
                        break;
                }
                return true;
            }
        });



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 100);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        Handle action bar item clicks here. The action bar will
		automatically handle clicks on the Home/Up button, so long
		as you specify a parent activity in AndroidManifest.xml.
		*/

        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // 触碰时间按下时触发该方法
        return false;
    }


    @Override
    public void onShowPress(MotionEvent e) {
        // 当用户在触摸屏幕上按下、而且还未移动和松开时触发该方法
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // 在屏幕上的轻击事件将会触发该方法
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // 当屏幕“滚动”时触发该方法
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // 当用户在屏幕上长按时触发该方法
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 当用户在屏幕上“拖动”时触发该方法
        return false;
    }

    public void onEvent(String data) {

    }

    public void onEventBackgroundThread(String data) {

    }

    public void onEventMainThread(String data) {
    }

    public void onEvent(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                viewFlipper.setInAnimation(this, R.anim.slide_left_in);
                viewFlipper.showNext();
                break;
        }
    }

    public void onEventMainThread(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                viewFlipper.setInAnimation(this, R.anim.slide_left_in);
                viewFlipper.showNext();
                break;
        }
    }

    public void onEventBackgroundThread(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                viewFlipper.setInAnimation(this, R.anim.slide_left_in);
                viewFlipper.showNext();
                break;
        }
    }

    public void onEventAsync(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                viewFlipper.setInAnimation(this, R.anim.slide_left_in);
                viewFlipper.showNext();
                break;
        }
    }

    public void onEvent(Integer integer) {

    }


}
