package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.tc.edu.tc.MyBase.CMyScrollView;
import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-3-14.
 */
public class CPrjScollView extends CMyScrollView {

    private final float reserveRightWidth = 100;

    private float downX = 0;
    private DisplayMetrics dm;

    private LinearLayout myset;
    private LinearLayout mainBackground_main;


    public CPrjScollView(Context context) {
        super(context);
    }

    public CPrjScollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CPrjScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        downX = 0.0f;
        return super.onDown(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float x = mainBackground_main.getX();
        float w = dm.widthPixels;

        downX += distanceX;

        update(mainBackground_main, x - downX, x - downX, 0.0f, 0.0f, 0);
        update(myset, x - downX + w, x - downX + w, 0.0f, 0.0f, 0);

        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onTouch(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_UP) {
            float x = mainBackground_main.getX();
            float w = dm.widthPixels;

            float desx = 0.0f;

            if ((x >= 0.0f && x < w / 4.0f) || (x < 0.0f && x > -w / 2.0f)) {
                desx = 0.0f;
            } else if (x >= w / 4.0f) {
                desx = w / 2.0f;
            } else if (x <= -w / 2.0f) {
                desx = reserveRightWidth - w;
            }
            update(mainBackground_main, x, desx, 0.0f, 0.0f, 500);
            update(myset, x + w, desx + w, 0.0f, 0.0f, 500);
        }
        return super.onTouch(e);
    }

    @Override
    protected void init() {
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        myset = (LinearLayout) activity.findViewById(R.id.myset_fragment_layout);
        mainBackground_main = (LinearLayout) activity.findViewById(R.id.MainBackground_main);
    }
}
