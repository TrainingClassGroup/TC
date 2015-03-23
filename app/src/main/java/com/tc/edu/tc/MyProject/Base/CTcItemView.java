package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.R;

/**
 * Created by Administrator on 15-3-22.
 */
public class CTcItemView extends LinearLayout {

    private View view = null;
    private LinearLayout layout = null;

    public CTcItemView(Context context) {
        super(context);
        _init(context);
    }

    private void _init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.tcitem_view, this, true);
        layout = (LinearLayout) view.findViewById(R.id.tcitem);
    }

    public CTcItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        _init(context);

        TypedArray typedarray = context.obtainStyledAttributes(attrs, R.styleable.tcitem);

        setImageResource(typedarray.getResourceId(R.styleable.tcitem_image, 0));

        setText(typedarray.getString(R.styleable.tcitem_text));
        setReservation(typedarray.getString(R.styleable.tcitem_text_reservation));
        setRegisted(typedarray.getString(R.styleable.tcitem_text_registed));
        setMemo(typedarray.getString(R.styleable.tcitem_text_memo));
        setDistance(typedarray.getString(R.styleable.tcitem_text_distance));
    }

    public void setImageResource(int resId) {
        ImageView imageView = (ImageView) view.findViewById(R.id.tcitem_image);
        imageView.setImageResource(resId);
    }

    public void setText(String string) {
        TextView tcitem_text = (TextView) view.findViewById(R.id.tcitem_text);
        tcitem_text.setText(string);
    }

    public void setReservation(String string) {
        TextView tcitem_text_reservation = (TextView) view.findViewById(R.id.tcitem_text_reservation);
        tcitem_text_reservation.setText(string);
    }

    public void setRegisted(String string) {
        TextView tcitem_text_registed = (TextView) view.findViewById(R.id.tcitem_text_registed);
        tcitem_text_registed.setText(string);
    }

    public void setMemo(String string) {
        TextView tcitem_text_memo = (TextView) view.findViewById(R.id.tcitem_text_memo);
        tcitem_text_memo.setText(string);
    }

    public void setDistance(String string) {
        TextView tcitem_text_distance = (TextView) view.findViewById(R.id.tcitem_text_distance);
        tcitem_text_distance.setText(string);
    }

    public void setOnClickListener(OnClickListener l) {
        layout.setOnClickListener(l);
    }
}

