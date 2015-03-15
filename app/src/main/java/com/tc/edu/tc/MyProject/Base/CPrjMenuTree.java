package com.tc.edu.tc.MyProject.Base;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyBase.CMenuTree;
import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-3-15.
 */
public abstract class CPrjMenuTree extends CMenuTree {

    public CPrjMenuTree(Activity active) {
        super(active);
    }

    @Override
    protected View createMenu(String name, final OnTouchListener mOnTouchListener, final Object paras) {
        TextView textView = new TextView(active);

        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 80));
        textView.setTextColor(Color.parseColor(active.getResources().getString(R.color.head2_text_color)));
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setTextSize(18);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mOnTouchListener != null) {
                    mOnTouchListener.onTouch(v, event, paras);
                }

                return true;
            }
        });

        return textView;
    }

    @Override
    protected void registMenu(final View menubarView, final View menu) {
        LinearLayout view = (LinearLayout) menubarView;
        view.addView(menu);
    }

    protected void clearMenubar(View v, MotionEvent event, int level, String key, Object paras) {
        LinearLayout layout = (LinearLayout) getMenubar(level, key);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < layout.getChildCount(); i++) {
                layout.getChildAt(i).setBackgroundResource(R.color.head2_textbtn_normal);
            }
            v.setBackgroundResource(R.color.head2_textbtn_pressed);
        }
    }
}
