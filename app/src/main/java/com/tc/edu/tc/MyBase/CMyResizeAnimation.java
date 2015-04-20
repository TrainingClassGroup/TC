package com.tc.edu.tc.MyBase;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 15-2-8.
 */
public class CMyResizeAnimation extends Animation {

    private Animation.AnimationListener animationListener = null;

    private int startHeight;
    private int deltaHeight = 0; // distance between start and end height

    private int startWidth;
    private int deltaWidth = 0; // distance between start and end width
    private View view;

    private ViewGroup.LayoutParams layoutParams;

    /**
     * constructor, do not forget to use the setParams(int, int) method before
     * starting the animation
     *
     * @param v
     */
    public CMyResizeAnimation(View v) {
        this.view = v;

        layoutParams = v.getLayoutParams();
        this.startHeight = layoutParams.height;
        this.startWidth = layoutParams.width;

        _setAnimationListener();
    }

    /**
     * set the starting and ending height for the resize animation
     * starting height is usually the views current height, the end height is the height
     * we want to reach after the animation is completed
     *
     * @param start height in pixels
     * @param end   height in pixels
     */
    public void setParamsHeight(int start, int end) {

        this.startHeight = start;
        deltaHeight = end - start;
    }

    public void setParamsWidth(int start, int end) {

        this.startWidth = start;
        deltaWidth = end - start;
    }

    @Override
    public boolean willChangeBounds() {
        return false;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        layoutParams.height = (int) (startHeight + deltaHeight * interpolatedTime);
        layoutParams.width = (int) (startWidth + deltaWidth * interpolatedTime);

        view.requestLayout();
    }

    public CMyResizeAnimation setCallback(Animation.AnimationListener animationListener){
        this.animationListener = animationListener;

        return this;
    }

    private void _setAnimationListener(){
        setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(animationListener!=null) animationListener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                if(animationListener!=null) animationListener.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(animationListener!=null) animationListener.onAnimationRepeat(animation);
            }
        });
    }
}
