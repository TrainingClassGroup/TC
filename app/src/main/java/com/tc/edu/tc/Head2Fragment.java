package com.tc.edu.tc;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class Head2Fragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.head2_fragment, container, false);

        final LinearLayout head2_menu1 = (LinearLayout)view.findViewById(R.id.head2_menu1);
        final LinearLayout head2_menu2 = (LinearLayout)view.findViewById(R.id.head2_menu2);
        final LinearLayout head2_menu3 = (LinearLayout)view.findViewById(R.id.head2_menu3);

/*
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm); //device screen info

        int width =  (dm.widthPixels-2*14)/3; // 2*(18width - 2padding * 2), head2_menu1_image and head2_menu2_image

        head2_menu1.getLayoutParams().width=width;
        head2_menu2.getLayoutParams().width=width;
        head2_menu3.getLayoutParams().width=width;
*/
        final AtomicInteger menuLock = new AtomicInteger(0);
        head2_menu1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(menuLock.get()>0 && menuLock.get()!=1) return true;
                ImageView head2_menu1_image=(ImageView)view.findViewById(R.id.head2_menu1_image);
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    menuLock.set(1);
                    head2_menu1.setBackgroundColor(Color.parseColor("#FF8099FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow270);
                }
                else  if(event.getAction()==MotionEvent.ACTION_UP){
                    head2_menu1.setBackgroundColor(Color.parseColor("#FF3760FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    menuLock.set(0);

                    LinearLayout head2_pulldown_menu=(LinearLayout) getActivity().findViewById(R.id.head2_pulldown_menu);
                    ResizeAnimation a = new ResizeAnimation(head2_pulldown_menu);
                    a.setDuration(500);
                    if(head2_pulldown_menu.getLayoutParams().height==0){
                       a.setParamsHeight(0, 500);
                     }
                     else{
                        a.setParamsHeight(head2_pulldown_menu.getLayoutParams().height, 0);
                     }
                    head2_pulldown_menu.startAnimation(a);
                }

                return true;
            }

        });


        head2_menu2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(menuLock.get()>0 && menuLock.get()!=2) return true;
                ImageView head2_menu1_image=(ImageView)view.findViewById(R.id.head2_menu1_image);
                ImageView head2_menu2_image=(ImageView)view.findViewById(R.id.head2_menu2_image);
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    menuLock.set(2);
                    head2_menu2.setBackgroundColor(Color.parseColor("#FF8099FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow470);
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow270);
                }
                else  if(event.getAction()==MotionEvent.ACTION_UP){
                    head2_menu2.setBackgroundColor(Color.parseColor("#FF3760FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    menuLock.set(0);
                }
                return true;
            }

        });

        head2_menu3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(menuLock.get()>0 && menuLock.get()!=3) return true;
                ImageView head2_menu2_image=(ImageView)view.findViewById(R.id.head2_menu2_image);
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    menuLock.set(3);
                    head2_menu3.setBackgroundColor(Color.parseColor("#FF8099FF"));
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow470);
                }
                else  if(event.getAction()==MotionEvent.ACTION_UP){
                    head2_menu3.setBackgroundColor(Color.parseColor("#FF3760FF"));
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    menuLock.set(0);
                }
                return true;
            }

        });

		return view;
	}


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("ExampleFragment--onPause");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("ExampleFragment--onResume");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("ExampleFragment--onStop");
	}
}
