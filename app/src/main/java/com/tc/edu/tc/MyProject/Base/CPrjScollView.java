package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.tc.edu.tc.MyBase.CMyView;
import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-3-14.
 */
public class CPrjScollView extends CMyView {

    private int downX = 0;
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
        downX = 0;
        return super.onDown(e);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        downX += distanceX;

        update(mainBackground_main, mainBackground_main.getX() - downX, mainBackground_main.getX() - downX, 0, 0, 0);
        update(myset, mainBackground_main.getX() - downX + dm.widthPixels, mainBackground_main.getX() - downX + dm.widthPixels, 0, 0, 0);

        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        update(mainBackground_main, 0, 100 - dm.widthPixels, 0, 0, 1000);
        update(myset, dm.widthPixels, 100, 0, 0, 1000);

        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    protected void init() {
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        myset = (LinearLayout) activity.findViewById(R.id.myset_fragment_layout);
        mainBackground_main = (LinearLayout) activity.findViewById(R.id.MainBackground_main);
    }
}
