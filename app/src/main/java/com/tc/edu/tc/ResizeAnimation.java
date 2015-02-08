package com.tc.edu.tc;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 15-2-8.
 */
public class ResizeAnimation extends Animation {

    private int startHeight;
    private int deltaHeight=0; // distance between start and end height

    private int startWidth;
    private int deltaWidth=0; // distance between start and end width
    private View view;

    /**
     * constructor, do not forget to use the setParams(int, int) method before
     * starting the animation
     * @param v
     */
    public ResizeAnimation (View v) {
        this.view = v;
        ViewGroup.LayoutParams lp=v.getLayoutParams();
        this.startHeight=lp.height;
        this.startWidth=lp.width;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        view.getLayoutParams().height = (int) (startHeight + deltaHeight * interpolatedTime);
        view.getLayoutParams().width = (int) (startWidth + deltaWidth * interpolatedTime);
        view.requestLayout();
    }

    /**
     * set the starting and ending height for the resize animation
     * starting height is usually the views current height, the end height is the height
     * we want to reach after the animation is completed
     * @param start height in pixels
     * @param end height in pixels
     */
    public void setParamsHeight(int start, int end) {

        this.startHeight = start;
        deltaHeight = end - startHeight;
    }

    public void setParamsWidth(int start, int end) {

        this.startWidth = start;
        deltaWidth = end - startWidth;
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
