package com.tc.edu.tc;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.tc.edu.tc.MyProject.Base.CPrjScollView;
import com.tc.edu.tc.MyProject.Data.CPrjDataTcItems;

import de.greenrobot.event.EventBus;


public class MainActivity extends Activity implements GestureDetector.OnGestureListener {

    private ViewFlipper viewFlipper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        final DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        CPrjScollView scrollView1 = (CPrjScollView) findViewById(R.id.scrollView1);
        scrollView1.bindActivity(this);

        //EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        LinearLayout tclister = (LinearLayout) findViewById(R.id.tclister);
       // tclister.removeAllViewsInLayout();
        for (int i = 1; i < tclister.getChildCount() - 1; i++) {
            tclister.removeViewAt(i);
        }

        CPrjDataTcItems dataTcItems = new CPrjDataTcItems(this);
        dataTcItems.execute("{paras:{lng:123.417095,lat:41.836929,catalog:高中,curriculum:数学,rownum:10,page:0,type:json}}");
    }

    public boolean onTouchEvent(MotionEvent event) {

        EventBus.getDefault().post(new MyEvent(event.getAction()));
        /*
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                viewFlipper.setInAnimation(this, R.anim.in_leftright);
                viewFlipper.showNext();
                break;
        }
        */

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
        Toast.makeText(this, "OnDown", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // 当用户在触摸屏幕上按下、而且还未移动和松开时触发该方法
        Toast.makeText(this, "onShowPress", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // 在屏幕上的轻击事件将会触发该方法
        Toast.makeText(this, "onSingleTapUp", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        // 当屏幕“滚动”时触发该方法
        Toast.makeText(this, "onScroll", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // 当用户在屏幕上长按时触发该方法
        Toast.makeText(this, "onLongPress", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // 当用户在屏幕上“拖动”时触发该方法
        Toast.makeText(this, "onFling", Toast.LENGTH_LONG).show();
        return false;
    }

    public void onEvent(String data) {
        Log.i("yyy", "onEvent:" + data);

    }

    public void onEventBackgroundThread(String data) {
        Log.i("yyy", "onEventBackgroundThread:" + data);

    }

    public void onEventMainThread(String data) {
        Log.i("yyy", "onEventMainThread:" + data);

    }

    public void onEvent(MyEvent myEvent) {
        Log.i("xxx", "onEvent");
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
        Log.i("xxx", "onEventMainThread");
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
        Log.i("xxx", "onEventBackgroundThread");
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
        Log.i("xxx", "onEventAsync");
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

class MyEvent {
    public int event;

    MyEvent(int event) {
        this.event = event;
    }

    public int getEvent() {
        return event;
    }
}
