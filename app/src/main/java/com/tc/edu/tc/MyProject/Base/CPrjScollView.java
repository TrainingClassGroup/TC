package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyBase.CMyScrollView;
import com.tc.edu.tc.MyProject.Data.CPrjDataTcItems4ScrollView;
import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-3-14.
 */
public class CPrjScollView extends CMyScrollView {

    private final float reserveScroll = 120.0f;
    private final float reserveRightWidth = 100.0f;
    private final int animationDuation = 500;

    private int curStatus = 0;//-1  0  1
    private float velocityX = 0.0f;
    private float downX = 0.0f;
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
        _autoTopButton(true);

        float x = mainBackground_main.getX();
        float w = dm.widthPixels;

        if (x > -w / 2.0f && x < w / 4.0f) curStatus = 0;
        else if (x >= w / 4.0f) curStatus = 1;
        else curStatus = -1;

        downX = 0.0f;

        return super.onDown(e);
    }

    private void _autoTopButton(boolean show){
        CPrjTopButtonSortX topbtn_sortx = (CPrjTopButtonSortX)activity.findViewById(R.id.topbtn_xsrot);

        if(show){
            topbtn_sortx.show();
        }
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        /*
        downX += distanceX;
        if (Math.abs(downX) > reserveScroll) {
            float x = mainBackground_main.getX();
            float w = dm.widthPixels;
            float offset;
            if (downX > 0) offset = downX - reserveScroll;
            else offset = downX + reserveScroll;
            update(mainBackground_main, x - offset, x - offset, 0.0f, 0.0f, 0);
            update(myset, x - offset + w, x - offset + w, 0.0f, 0.0f, 0);
        }
*/
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.velocityX = velocityX;
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onTouch(MotionEvent e) {
        /*
        if (e.getAction() == MotionEvent.ACTION_UP) {

            float x = mainBackground_main.getX();
            float w = dm.widthPixels;

            float desx = 0.0f;

            if (Math.abs(velocityX) > 200.0f && curStatus != 0) {
                if (curStatus == -1) {
                    desx = 0.0f;
                } else if (curStatus == 1) {
                    desx = 0.0f;
                }
            } else {
                if ((x >= 0.0f && x < w / 4.0f) || (x < 0.0f && x > -w / 4.0f)) {
                    desx = 0.0f;
                } else if (x >= w / 4.0f) {
                    desx = w / 2.0f;
                } else if (x <= -w / 4.0f) {
                    desx = reserveRightWidth - w;
                }
            }
            update(mainBackground_main, x, desx, 0.0f, 0.0f, animationDuation);
            update(myset, x + w, desx + w, 0.0f, 0.0f, animationDuation);
            velocityX = 0.0f;
        }
        */
        return super.onTouch(e);
    }

    @Override
    protected void init() {
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        myset = (LinearLayout) activity.findViewById(R.id.myset_fragment_layout);
        mainBackground_main = (LinearLayout) activity.findViewById(R.id.MainBackground_main);
    }

    @Override
    public void onScrolling(int scrollX, int scrollY){

    }

    @Override
    public void onScrollend(int scrollX, int scrollY){
        TextView tc_loading = (TextView) activity.findViewById(R.id.tc_loading);
        int[] position = new int[2];
        tc_loading.getLocationInWindow(position);

        if(position[1]<dm.heightPixels){
            CPrjDataTcItems4ScrollView dataTcItems = new CPrjDataTcItems4ScrollView(activity);
            //CPrjDataTcItems4ListView dataTcItems = new CPrjDataTcItems4ListView(activity, R.id.myList);
            dataTcItems.updateClassLister(false);
        }
    }

}
