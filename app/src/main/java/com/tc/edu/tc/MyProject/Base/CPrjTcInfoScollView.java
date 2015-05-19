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
public class CPrjTcInfoScollView extends CMyScrollView {

    private final float reserveScroll = 120.0f;
    private final float reserveRightWidth = 100.0f;
    private final int animationDuation = 500;

    private int curStatus = 0;//-1  0  1
    private float velocityX = 0.0f;
    private float downX = 0.0f;
    private DisplayMetrics dm;

    private boolean buttonStatus = true;

    private LinearLayout myset;
    private LinearLayout mainBackground_main;

    public CPrjTcInfoScollView(Context context) {
        super(context);
    }

    public CPrjTcInfoScollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CPrjTcInfoScollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        _autoTopButton(buttonStatus);
        buttonStatus=!buttonStatus;

        float x = mainBackground_main.getX();
        float w = dm.widthPixels;

        if (x > -w / 2.0f && x < w / 4.0f) curStatus = 0;
        else if (x >= w / 4.0f) curStatus = 1;
        else curStatus = -1;

        downX = 0.0f;

        return super.onDown(e);
    }

    private void _autoTopButton(boolean show){
        CPrjTopButtonComment topbtn_comment = (CPrjTopButtonComment)activity.findViewById(R.id.topbtn_comment);
        CPrjTopButtonShare topbtn_share = (CPrjTopButtonShare)activity.findViewById(R.id.topbtn_share);
        CPrjTopButtonLike topbtn_like = (CPrjTopButtonLike)activity.findViewById(R.id.topbtn_like);

        if(show){
            topbtn_comment.show();
            topbtn_share.show();
            topbtn_like.show();
        }
        else{
            topbtn_comment.hide();
            topbtn_share.hide();
            topbtn_like.hide();
        }
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        this.velocityX = velocityX;
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onTouch(MotionEvent e) {
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

    }

}
