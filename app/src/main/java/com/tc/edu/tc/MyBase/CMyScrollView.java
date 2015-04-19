package com.tc.edu.tc.MyBase;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 15-3-7.
 */

public abstract class CMyScrollView extends ScrollView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    protected Activity activity = null;
    protected GestureDetector gestureDetector = null;
    //
    private int lastScrollY = -1;
    private int lastScrollX = -1;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int scrollY = CMyScrollView.this.getScrollY();
            int scrollX = CMyScrollView.this.getScrollX();
            //此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY || lastScrollX != scrollX) {
                lastScrollY = scrollY;
                lastScrollX = scrollX;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
                onScrolling(scrollX, scrollY);
            } else {
                onScrollend(scrollX, scrollY);
            }
        }

        ;
    };

    /* extends from ScrollView */
    public CMyScrollView(Context context) {
        super(context);
    }

    /* extends from ScrollView */
    public CMyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* extends from ScrollView */
    public CMyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void update(final View view, final float x, final float desx, final float y, final float desy, final int duration) {
        if (x == desx && y == desy) {
            view.setX(desx);
            view.setY(desy);
        } else {
            CMyTranslateAnimation myTranslateAnimation = new CMyTranslateAnimation(view, x, desx, y, desy, duration);
            view.startAnimation(myTranslateAnimation);
        }
    }

    /* implements from GestureDetector.OnGestureListener */
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    /* implements from GestureDetector.OnGestureListener */
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    /* implements from GestureDetector.OnGestureListener */
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    /* implements from GestureDetector.OnGestureListener */
    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    /* implements from GestureDetector.OnGestureListener */
    public void onShowPress(MotionEvent e) {
    }

    /* implements from GestureDetector.OnGestureListener */
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    /* implements from GestureDetector.OnGestureListener */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    /* implements from GestureDetector.OnGestureListener */
    public void onLongPress(MotionEvent e) {
    }

    /* implements from GestureDetector.OnGestureListener */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }

    public void onScrolling(int scrollX, int scrollY) {

    }

    public void onScrollend(int scrollX, int scrollY) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        lastScrollY = getScrollY();
        lastScrollX = getScrollX();
        onScrolling(lastScrollX, lastScrollY);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
                break;
        }

        super.onTouchEvent(ev);
        return gestureDetector.onTouchEvent(ev);
    }

    /* extends from ScrollView */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(ev);
        }
        super.dispatchTouchEvent(ev);
        onTouch(ev);
        return true;
    }

    public boolean onTouch(MotionEvent e) {
        return false;
    }

    public void bindActivity(final Activity activity) {
        this.activity = activity;
        gestureDetector = new GestureDetector(activity, this);

        init();
    }

    abstract protected void init();
}
