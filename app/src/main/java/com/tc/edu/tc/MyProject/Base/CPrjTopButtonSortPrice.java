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
public class CPrjTopButtonSortPrice extends CMyTopButton {
    public CPrjTopButtonSortPrice(Context context, AttributeSet attrs) {
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
        if(!isVisable()) return;

        AnimationSet anim=new AnimationSet(true);
        anim.addAnimation(new CMyAlphaAnimation(this, 1, 0).setCallback(animationListener));
        anim.addAnimation(new CMyTranslateAnimation(this, getInitX(), getInitX()+getLayout().getLayoutParams().width, getInitY(), getInitY()));
        anim.setDuration(500);

        startAnimation(anim);
    }

    public void show(Animation.AnimationListener animationListener){
        if(isVisable()) return;

        AnimationSet anim=new AnimationSet(true);
        anim.addAnimation(new CMyAlphaAnimation(this, 0, 1).setCallback(animationListener));
        anim.addAnimation(new CMyTranslateAnimation(this, getInitX()+getLayout().getLayoutParams().width, getInitX(), getInitY(), getInitY()));
        anim.setDuration(500);

        startAnimation(anim);
    }
}
