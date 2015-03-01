package com.tc.edu.tc;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by ZhaoJietong on 15-2-25.
 */
public class CRegistMenuForHead2PullDown {
    private Activity active=null;

    private OnTouchListener mOnTouchListener=null;


    public interface OnTouchListener{
        void onTouch(View v, MotionEvent event, Object paras);
    };

    public CRegistMenuForHead2PullDown(Activity active){
        this.active=active;
    }

    public CRegistMenuForHead2PullDown setOnActionListener(OnTouchListener mOnActionListener){
        this.mOnTouchListener = mOnActionListener;

        return this;
    }

    public void regist(final int pulldown_id, String name, final Object paras){
        TextView textView = new TextView(active);

        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 80));
        textView.setTextColor(Color.parseColor(active.getResources().getString(R.color.head2_text_color)));
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setTextSize(18);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LinearLayout head2_pulldown_menu= (LinearLayout)active.findViewById(pulldown_id);

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    for (int i = 0; i < head2_pulldown_menu.getChildCount(); i++) {
                        head2_pulldown_menu.getChildAt(i).setBackgroundResource(R.color.head2_textbtn_normal);
                    }
                    v.setBackgroundResource(R.color.head2_textbtn_pressed);
                }

                if(mOnTouchListener!=null){
                    mOnTouchListener.onTouch(v, event, paras);
                }

                return true;
            }
        });


        LinearLayout head2_pulldown_menu= (LinearLayout)active.findViewById(pulldown_id);
        head2_pulldown_menu.addView(textView);
    }

    public void regist(int pulldown_id, String[] names, final Object paras){
        for(int i=0;i<names.length;i++){
            regist(pulldown_id, names[i], paras);
        }

    }

    public void regist(int pulldown_id, JSONArray names, final Object paras){
        try{
            for(int i=0;i<names.length();i++){
                regist(pulldown_id, names.getString(i), paras);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
