package com.tc.edu.tc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 15-4-23.
 */
public class TcMapActivity extends Activity implements GestureDetector.OnGestureListener{


    MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        //SDKInitializer.initialize(getApplication());
//
        Intent i = getIntent();
        String company = i.getStringExtra("company");
        String tel = i.getStringExtra("tel");
        String address = i.getStringExtra("address");
        double lng = i.getDoubleExtra("lng",0);
        double lat = i.getDoubleExtra("lat",0);
//
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_tcmap);
//获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = mMapView.getMap();
        //定义Maker坐标点
        LatLng point = new LatLng(lat, lng);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(point)
                .zoom(13)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
//构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
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


                break;
        }
    }

    public void onEventMainThread(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:


                break;
        }
    }

    public void onEventBackgroundThread(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:


                break;
        }
    }

    public void onEventAsync(MyEvent myEvent) {
        switch (myEvent.getEvent()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
    }

    public void onEvent(Integer integer) {

    }


}
