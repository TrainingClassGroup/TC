package com.tc.edu.tc;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyProject.Data.CPrjDataHead2PullDown;
import com.tc.edu.tc.MyProject.Data.CPrjDataTcItems4ScrollView;

public class Head2PullDownFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.head2_pulldown_fragment, container, false);

        LinearLayout head2_pulldown_menu = (LinearLayout) view.findViewById(R.id.head2_pulldown_menu);
        head2_pulldown_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int maxHeight = dm.heightPixels * 2 / 3;

        head2_pulldown_menu.getLayoutParams().height = maxHeight;
        head2_pulldown_menu.setY(-maxHeight);


        TextView head2_cancle = (TextView) view.findViewById(R.id.head2_CANCLE);
        head2_cancle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.color.head2_textbtn_pressed);
                } else {
                    v.setBackgroundResource(R.color.head2_textbtn_normal);
                    Head2Fragment.pullUpDownMenu(getActivity(), true, new Head2Fragment.Callback(){
                        public void before(){}
                        public void end(){}
                    });
                }

                return true;
            }
        });
//下拉选择菜单中的“确定”按钮
        TextView head2_ok = (TextView) view.findViewById(R.id.head2_OK);
        head2_ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundResource(R.color.head2_textbtn_pressed);
                } else {
                    v.setBackgroundResource(R.color.head2_textbtn_normal);
                    Head2Fragment.pullUpDownMenu(getActivity(), true, new Head2Fragment.Callback(){
                        public void before(){}
                        public void end(){
                            CPrjDataTcItems4ScrollView dataTcItems = new CPrjDataTcItems4ScrollView(getActivity());
                            //CPrjDataTcItems4ListView dataTcItems = new CPrjDataTcItems4ListView(getActivity(), R.id.myList);
                            dataTcItems.updateClassLister(true);
                        }
                    });
                }

                return true;
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Activity activity = getActivity();

        CPrjDataHead2PullDown dataHead2PullDown = new CPrjDataHead2PullDown(activity);
        dataHead2PullDown.execute("{paras: {type: json}}");
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(new Intent(getActivity(), com.tc.edu.tc.MyProject._Test.CPrjDataHead2PullDown.class));
        super.onDestroy();
    }


}
