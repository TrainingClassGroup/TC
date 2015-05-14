package com.tc.edu.tc.MyProject.Base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.R;

import org.json.JSONObject;

/**
 * Created by Administrator on 15-3-22.
 */
public class CTcCommentView extends LinearLayout {

    private View view = null;
    private LinearLayout layout = null;

    private JSONObject value = null;

    public CTcCommentView(Context context) {
        super(context);
        _init(context);
    }

    public CTcCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _init(context);
    }

    private void _init(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.tcinfo_comment_view, this, true);
        layout = (LinearLayout) view.findViewById(R.id.tcinfo_comment);
    }

    public void regist(final LinearLayout layout) {
        int index = 0;
        for (int i = 1; i < layout.getChildCount(); i++) {
            if (layout.getChildAt(i).getId() > layout.getId())
                break;
            index++;
        }
        layout.addView(CTcCommentView.this, index);
    }

    public JSONObject getValue() {
        return value;
    }

    public void setValue(JSONObject value) {
        this.value = value;
    }

    public void setComment(String comment){
        TextView tcinfo_comment_text = (TextView) view.findViewById(R.id.tcinfo_comment_text);
        tcinfo_comment_text.setText(comment);
    }

    public void setCommentUser(String user){
        TextView tcinfo_comment_user = (TextView) view.findViewById(R.id.tcinfo_comment_user);

        tcinfo_comment_user.setText(user.substring(0,2)+"*****"+user.substring(user.length()-3,user.length()-1));
    }
}
