package com.tc.edu.tc.MyBase;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by Administrator on 15-3-7.
 */

public abstract class CMyScrollView extends ScrollView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    protected Activity activity = null;
    protected GestureDetector gestureDetector = null;

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
            float fromXDelta = x - view.getX();
            float toXDelta = desx - view.getX();
            float fromYDelta = y - view.getY();
            float toYDelta = desy - view.getY();

            TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
            translateAnimation.setDuration(duration);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.clearAnimation();
                    view.setX(desx);
                    view.setY(desy);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(translateAnimation);
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

    /* extends from ScrollView */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouch(ev);
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(ev);
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    public boolean onTouch(MotionEvent e) {

        return false;
    }

    public void bindActivity(final Activity activity) {
        this.activity = activity;
        gestureDetector = new GestureDetector(activity, this);

        init();

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    abstract protected void init();
}
