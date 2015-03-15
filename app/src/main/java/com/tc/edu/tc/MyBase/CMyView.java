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

public abstract class CMyView extends ScrollView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    protected Activity activity = null;
    protected GestureDetector gestureDetector = null;

    /* extends from ScrollView */
    public CMyView(Context context) {
        super(context);
    }

    /* extends from ScrollView */
    public CMyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* extends from ScrollView */
    public CMyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void update(final View view, final float fromXDelta, final float toXDelta, final float fromYDelta, final float toYDelta, final int duration) {
        if (toXDelta == fromXDelta && toYDelta == fromYDelta) {
            view.setX(toXDelta);
            view.setY(toYDelta);
        } else {
            TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
            translateAnimation.setDuration(duration);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.clearAnimation();
                    view.setX(toXDelta);
                    view.setY(toYDelta);
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
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        if (gestureDetector != null) {
            return gestureDetector.onTouchEvent(ev);
        }
        return true;
    }

    /* extends from ScrollView */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(ev);
        }
        super.dispatchTouchEvent(ev);
        return true;
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
