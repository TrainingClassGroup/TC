package com.tc.edu.tc;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tc.edu.tc.MyProject.Data.Head2PullDownReceiver;
import com.tc.edu.tc.MyProject.Data.Head2PullDownService;

public class Head2PullDownFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.head2_pulldown_fragment, container, false);

        LinearLayout head2_pulldown_menu = (LinearLayout) view.findViewById(R.id.head2_pulldown_menu);
        head2_pulldown_menu.getLayoutParams().height = 0;
        head2_pulldown_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                    Head2Fragment.pullUpDownMenu(getActivity(), true);
                    updateClassLister();
                }

                return true;
            }
        });


        return view;
    }

    /*
    ** 查询补习班列表
     */
    private void updateClassLister() {

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Activity activity = getActivity();

        Intent intent = new Intent(activity, Head2PullDownService.class);
        activity.startService(intent);


        IntentFilter filter = new IntentFilter();
        filter.addAction("Head2PullDownService");
        activity.registerReceiver(new Head2PullDownReceiver(activity), filter);


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
        getActivity().stopService(new Intent(getActivity(), Head2PullDownService.class));
        super.onDestroy();
    }


}
