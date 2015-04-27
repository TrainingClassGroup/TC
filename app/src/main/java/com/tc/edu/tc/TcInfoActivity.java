package com.tc.edu.tc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ViewFlipper;

import com.tc.edu.tc.MyBase.CMyApplication;
import com.tc.edu.tc.MyProject.Base.CPrjScollView;

/**
 * Created by Administrator on 15-4-23.
 */
public class TcInfoActivity extends Activity implements GestureDetector.OnGestureListener{
    private ViewFlipper viewFlipper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tcinfo);

        Intent i = getIntent();
        String company_id = i.getStringExtra("id");

        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        CPrjScollView scrollView1 = (CPrjScollView) findViewById(R.id.scrollView1);
        scrollView1.bindActivity(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        String aaa = (String)((CMyApplication)getApplication()).getCache().get("xxx");

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
