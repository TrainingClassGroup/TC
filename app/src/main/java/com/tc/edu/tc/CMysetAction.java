package com.tc.edu.tc;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 15-3-5.
 */
public class CMysetAction {

    public static void slideLeft(Activity activity){
        final DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        final LinearLayout myset = (LinearLayout) activity.findViewById(R.id.myset_fragment_layout);
        final LinearLayout mainBackground_main = (LinearLayout) activity.findViewById(R.id.MainBackground_main);
        final float desX=100-dm.widthPixels;

        TranslateAnimation translateAnimation = new TranslateAnimation( 0, desX, 0, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                mainBackground_main.clearAnimation();
                mainBackground_main.setX(desX);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        TranslateAnimation translateAnimation2 = new TranslateAnimation( dm.widthPixels, 100, 0, 0);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                myset.clearAnimation();
                myset.setX(100);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });


        myset.startAnimation(translateAnimation2);
        mainBackground_main.startAnimation(translateAnimation);
    }

    public static void slideRight(Activity activity){
        final DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        final LinearLayout myset = (LinearLayout) activity.findViewById(R.id.myset_fragment_layout);
        final LinearLayout mainBackground_main = (LinearLayout) activity.findViewById(R.id.MainBackground_main);
        final float desX=100-dm.widthPixels;

        TranslateAnimation translateAnimation = new TranslateAnimation( 0, -desX, 0, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                mainBackground_main.clearAnimation();
                mainBackground_main.setX(0);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

        TranslateAnimation translateAnimation2 = new TranslateAnimation( 0, dm.widthPixels-100, 0, 0);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                myset.clearAnimation();
                myset.setX(0);
            }
            @Override
            public void onAnimationRepeat(Animation animation) { }
        });


        myset.startAnimation(translateAnimation2);

        mainBackground_main.startAnimation(translateAnimation);
    }
}
