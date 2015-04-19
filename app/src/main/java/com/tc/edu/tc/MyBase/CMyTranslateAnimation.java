package com.tc.edu.tc.MyBase;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 15-4-19.
 */
public class CMyTranslateAnimation extends TranslateAnimation {

    private View view;
    public CMyTranslateAnimation(final View view, final float x, final float desx, final float y, final float desy) {
        super(x - view.getX(), desx - view.getX(), y - view.getY(), desy - view.getY());

        _setAnimationListener(desx, desy);

        this.view = view;
    }

    public CMyTranslateAnimation(final View view, final float x, final float desx, final float y, final float desy, final int duration) {
        super(x - view.getX(), desx - view.getX(), y - view.getY(), desy - view.getY());
        setDuration(duration);

        _setAnimationListener(desx, desy);

        this.view = view;
    }

    private void _setAnimationListener(final float desx, final float desy){
        setAnimationListener(new Animation.AnimationListener() {
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
    }
}
