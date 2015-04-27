package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.tc.edu.tc.MyBase.CMyTopButton;

/**
 * Created by Administrator on 15-4-20.
 */
public class CPrjTopButton extends CMyTopButton {

    protected final int autoHideTimeout = 10000;

    public CPrjTopButton(Context context, AttributeSet attrs) {
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

}
