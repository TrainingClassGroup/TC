package com.tc.edu.tc.MyBase;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-4-15.
 */
public class CMyTopButton extends LinearLayout {

    private TypedArray typedarray = null;
    private View view = null;
    private LinearLayout layout = null;

    public CMyTopButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        view= LayoutInflater.from(context).inflate(R.layout.top_button, this,true);
        layout=(LinearLayout)view.findViewById(R.id.top_btn);

        typedarray=context.obtainStyledAttributes(attrs, R.styleable.topbtn);
        setImageResource(typedarray.getResourceId(R.styleable.topbtn_btn_image, 0));
        setText(typedarray.getString(R.styleable.topbtn_btn_text));

        _init(context);
    }
    private void _init(Context context){
        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    setImageResource(typedarray.getResourceId(R.styleable.topbtn_btn_image_active, 0));
                } else {
                    setImageResource(typedarray.getResourceId(R.styleable.topbtn_btn_image, 0));
                }

                return true;
            }
        });
    }

    public void setOnClickListener(OnClickListener l){
        layout.setOnClickListener(l);
    }

    public void setImageResource(int resId){
        ImageView imageView = (ImageView)view.findViewById(R.id.top_btn_image);
        imageView.setImageResource(resId);
    }

    public void setText(String string){
        TextView tcitem_text = (TextView)view.findViewById(R.id.top_btn_text);
        tcitem_text.setText(string);
    }
}
