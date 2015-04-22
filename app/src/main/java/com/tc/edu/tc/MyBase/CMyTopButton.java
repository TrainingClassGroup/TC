package com.tc.edu.tc.MyBase;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-4-15.
 */
public class CMyTopButton extends LinearLayout {

    public static enum State{
        VISABLE(0x01),
        ENABLE(0x02);

        private int stateValue;
        private State(int value){
            stateValue = value;
        }

        public int getStateValue() {
            return stateValue;
        }
    };

    private int state = State.ENABLE.getStateValue() | State.VISABLE.getStateValue();

    private TypedArray typedarray = null;
    private View view = null;
    private LinearLayout layout = null;

    private float initX = 0;
    private float initY = 0;

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

    public TypedArray getTypedarray() {
        return typedarray;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public float getInitX() {
        return initX;
    }

    public void setInitX(float initX) {
        this.initX = initX;
    }

    public float getInitY() {
        return initY;
    }

    public void setInitY(float initY) {
        this.initY = initY;
    }

    public void setState(State state, boolean t){
        if(t) this.state |= state.getStateValue();
        else this.state &= (~state.getStateValue());
    }

    public boolean isState(State state){
        return (state.getStateValue() & this.state) == state.getStateValue();
    }

    public void hide(Animation.AnimationListener animationListener){
    }

    public void show(Animation.AnimationListener animationListener){
    }

    public void hide(){
        hide(null);
    }

    public void show(){
        show(null);
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
