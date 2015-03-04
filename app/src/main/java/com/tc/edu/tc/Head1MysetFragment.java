package com.tc.edu.tc;

import android.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class Head1MysetFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.head1_myset_fragment, container, false);


        view.findViewById(R.id.myset).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

                final LinearLayout myset = (LinearLayout) getActivity().findViewById(R.id.myset_fragment_layout);
               // myset.setLayoutParams(new LinearLayout.LayoutParams(dm.widthPixels-80, dm.heightPixels));
               // myset.refreshDrawableState();
               // myset.setX(80);


                final LinearLayout mainBackground_main = (LinearLayout) getActivity().findViewById(R.id.MainBackground_main);
                final float desX=80-dm.widthPixels;

                if(mainBackground_main.getX()==0){
                    TranslateAnimation translateAnimation = new TranslateAnimation( 0, desX, 0, 0);
                    translateAnimation.setDuration(1000);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainBackground_main.clearAnimation();
                            mainBackground_main.setX(desX);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });

                    TranslateAnimation translateAnimation2 = new TranslateAnimation( dm.widthPixels, 80, 0, 0);
                    translateAnimation2.setDuration(1000);
                    translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            myset.clearAnimation();
                            myset.setX(80);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });


                    myset.startAnimation(translateAnimation2);
                    mainBackground_main.startAnimation(translateAnimation);
                }
                else if(mainBackground_main.getX()==desX){
                    TranslateAnimation translateAnimation = new TranslateAnimation( 0, -desX, 0, 0);
                    translateAnimation.setDuration(1000);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainBackground_main.clearAnimation();
                            mainBackground_main.setX(0);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });



                    mainBackground_main.startAnimation(translateAnimation);
                }
                return false;
            }
        });


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
