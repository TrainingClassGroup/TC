package com.tc.edu.tc.MyBase;

import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * Created by Administrator on 15-4-20.
 */
public class CMyAlphaAnimation extends AlphaAnimation {

    private View view;

    public CMyAlphaAnimation(View view, float fromAlpha, float toAlpha) {
        super(fromAlpha, toAlpha);

        this.view = view;
    }
}
