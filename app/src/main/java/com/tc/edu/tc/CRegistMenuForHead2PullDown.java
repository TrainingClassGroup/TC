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

/**
 * Created by ZhaoJietong on 15-2-25.
 */
public class CRegistMenuForHead2PullDown {
    private Activity active=null;

    private OnTouchListener mOnTouchListener=null;


    public interface OnTouchListener{
        void onTouch(View v, MotionEvent event);
    };

    public CRegistMenuForHead2PullDown(Activity active){
        this.active=active;
    }

    public CRegistMenuForHead2PullDown setOnActionListener(OnTouchListener mOnActionListener){
        this.mOnTouchListener = mOnActionListener;

        return this;
    }

    public void regist(int pulldown_id, String name){
        TextView textView = new TextView(active);

        textView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 80));
        textView.setTextColor(Color.parseColor(active.getResources().getString(R.color.head2_text_color)));
        textView.setGravity(Gravity.CENTER);
        textView.setText(name);
        textView.setTextSize(18);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    v.setBackgroundResource(R.color.head2_textbtn_pressed);
                }
                else  if(event.getAction()==MotionEvent.ACTION_UP){
                    v.setBackgroundResource(R.color.head2_textbtn_normal);
                    ((TextView)active.findViewById(R.id.head2_menu1_text)).setText(((TextView)v).getText());

                }

                if(mOnTouchListener!=null){
                    mOnTouchListener.onTouch(v, event);
                }

                return true;
            }
        });

        LinearLayout head2_pulldown_menu= (LinearLayout)active.findViewById(pulldown_id);
        head2_pulldown_menu.addView(textView);
    }

    public void regist(int pulldown_id, String[] names){
        for(int i=0;i<names.length;i++){
            regist(pulldown_id,names[i]);
        }

    }
}
