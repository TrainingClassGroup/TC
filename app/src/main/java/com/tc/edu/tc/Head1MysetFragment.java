package com.tc.edu.tc;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tc.edu.tc.MyBase.CMyScrollView;

public class Head1MysetFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.head1_myset_fragment, container, false);


        view.findViewById(R.id.myset).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

                LinearLayout myset = (LinearLayout) getActivity().findViewById(R.id.myset_fragment_layout);
                LinearLayout mainBackground_main = (LinearLayout) getActivity().findViewById(R.id.MainBackground_main);
                float desX = 100 - dm.widthPixels;

                if (mainBackground_main.getX() == 0) {
                    CMyScrollView.update(mainBackground_main, 0, desX, 0, 0, 1000);
                    CMyScrollView.update(myset, dm.widthPixels, 100, 0, 0, 1000);
                } else if (mainBackground_main.getX() == desX) {
                    CMyScrollView.update(mainBackground_main, 0, -desX, 0, 0, 1000);
                    CMyScrollView.update(myset, 0, dm.widthPixels - 100, 0, 0, 1000);
                }
                return false;
            }
        });

        return view;
    }


}


