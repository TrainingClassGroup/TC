package com.tc.edu.tc;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Head2PullDownFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.head2_pulldown_fragment, container, false);

        LinearLayout head2_pulldown_menu=(LinearLayout)view.findViewById(R.id.head2_pulldown_menu);
        head2_pulldown_menu.getLayoutParams().height=0;
        head2_pulldown_menu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        view.findViewById(R.id.btn_pulldown1_1).setOnClickListener(null);

		return view;
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


}
