package com.tc.edu.tc.MyBase;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by Administrator on 15-4-20.
 */
public class CMyAlphaAnimation extends AlphaAnimation {

    private Animation.AnimationListener animationListener = null;
    private View view;

    public CMyAlphaAnimation(View view, float fromAlpha, float toAlpha) {
        super(fromAlpha, toAlpha);
        _setAnimationListener(toAlpha);
        this.view = view;
    }

    public CMyAlphaAnimation setCallback(Animation.AnimationListener animationListener){
        this.animationListener = animationListener;

        return this;
    }

    private void _setAnimationListener(final float toAlpha){
        setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if(animationListener!=null) animationListener.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.setAlpha(toAlpha);
                if(animationListener!=null) animationListener.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if(animationListener!=null) animationListener.onAnimationRepeat(animation);
            }
        });
    }
}
