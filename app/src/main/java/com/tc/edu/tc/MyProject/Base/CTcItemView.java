package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyProject.Data.CPrjDataTcImage;
import com.tc.edu.tc.R;

import org.json.JSONObject;

/**
 * Created by Administrator on 15-3-22.
 */
public class CTcItemView extends LinearLayout {

    private View view = null;
    private LinearLayout layout = null;

    private JSONObject value = null;

    public CTcItemView(Context context) {
        super(context);
        _init(context);
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

    private void _init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.tcitem_view, this, true);
        layout = (LinearLayout) view.findViewById(R.id.tcitem);


    }

    public void regist(final LinearLayout layout) {
        int index = 1;
        for (int i = 1; i < layout.getChildCount(); i++) {
            if (layout.getChildAt(i).getId() > layout.getId() || layout.getChildAt(i).getId() == R.id.tc_loading)
                break;
            index++;
        }
        layout.addView(CTcItemView.this, index);
    }

    public JSONObject getValue() {
        return value;
    }

    public void setValue(JSONObject value) {
        this.value = value;
    }

    public ImageView getImage() {
        return (ImageView) view.findViewById(R.id.tcitem_image);
    }

    public void setImageResource(int resId) {
        getImage().setImageResource(resId);
    }

    public void setImageResource(String url) {
        CPrjDownloadBitmap downloadBitmap = new CPrjDownloadBitmap(getImage());
        downloadBitmap.execute(url);
    }

    public void setImageResource(byte[] b) {
        getImage().setImageBitmap(BitmapFactory.decodeByteArray(b, 0, b.length, null));
    }

    public void setImageResource(Bitmap m) {
        getImage().setImageBitmap(m);
    }

    public void setImageResourceByImageId(int id) {
        new CPrjDataTcImage().setOnLoadListener(new CPrjDataTcImage.OnLoadListener() {
            @Override
            public void onload(byte[] b) {
                setImageResource(b);
            }
        }).execute(""+id, true);
    }

    public void setText(String string) {
        TextView tcitem_text = (TextView) view.findViewById(R.id.tcitem_text);
        if (string != null) {
            string = string.replace("\n", "");
            if (string.length() > 32) string = string.substring(0, 32) + "...";
            tcitem_text.setText(string);
        }
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
