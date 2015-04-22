package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import com.tc.edu.tc.MyBase.CMyAlphaAnimation;
import com.tc.edu.tc.MyBase.CMyTopButton;
import com.tc.edu.tc.MyBase.CMyTranslateAnimation;

/**
 * Created by Administrator on 15-4-20.
 */
public class CPrjTopButtonSortHot extends CMyTopButton {
    public CPrjTopButtonSortHot(Context context, AttributeSet attrs) {
        super(context, attrs);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setInitX(getView().getX());
                setInitY(getView().getY());

                hide();

            }
        }, 2000);
    }

    public void hide(Animation.AnimationListener animationListener){

        if(!isState(State.VISABLE)) return;

        AnimationSet anim=new AnimationSet(true);
        CMyAlphaAnimation alphaAnimation = new CMyAlphaAnimation(this, 1.0f, 0.0f);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                setState(State.VISABLE, false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        CMyTranslateAnimation translateAnimation = new CMyTranslateAnimation(this, getInitX(), getInitX(), getInitY(), getInitY()-getLayout().getLayoutParams().height);
        anim.addAnimation(alphaAnimation);
        anim.addAnimation(translateAnimation);
        anim.setDuration(250);
        anim.setFillAfter(true);
        anim.setAnimationListener(animationListener);

        clearAnimation();
        startAnimation(anim);
    }

    public void show(Animation.AnimationListener animationListener){

        if(isState(State.VISABLE)) return;

        AnimationSet anim=new AnimationSet(true);
        CMyAlphaAnimation alphaAnimation = new CMyAlphaAnimation(this, 0.0f, 1.0f);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                setState(State.VISABLE, true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hide();
                    }
                }, 10000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        CMyTranslateAnimation translateAnimation = new CMyTranslateAnimation(this, getInitX(), getInitX(), getInitY()-getLayout().getLayoutParams().height, getInitY());
        anim.addAnimation(alphaAnimation);
        anim.addAnimation(translateAnimation);
        anim.setDuration(250);
        anim.setFillAfter(true);
        anim.setAnimationListener(animationListener);

        clearAnimation();
        startAnimation(anim);
    }
}
