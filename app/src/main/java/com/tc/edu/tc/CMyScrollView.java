package com.tc.edu.tc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Administrator on 15-3-7.
 */

public class CMyScrollView extends ScrollView {

    private GestureDetector gestureDetector=null;

    public CMyScrollView(Context context) {
        super(context);
    }

    public CMyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CMyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setGestureDetector(final GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        if(gestureDetector!=null){
            return gestureDetector.onTouchEvent(ev);
        }
        return true;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        if(gestureDetector!=null) {
            gestureDetector.onTouchEvent(ev);
        }
        super.dispatchTouchEvent(ev);
        return true;
    }


}
