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

                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

                final LinearLayout mainBackground = (LinearLayout) getActivity().findViewById(R.id.MainBackground);
                final float desX=80-dm.widthPixels;

                if(mainBackground.getX()==0){
                    TranslateAnimation translateAnimation = new TranslateAnimation( 0, desX, 0, 0);
                    translateAnimation.setDuration(1000);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainBackground.clearAnimation();
                            mainBackground.setX(desX);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });

                    mainBackground.startAnimation(translateAnimation);
                }
                else if(mainBackground.getX()==desX){
                    TranslateAnimation translateAnimation = new TranslateAnimation( 0, -desX, 0, 0);
                    translateAnimation.setDuration(1000);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) { }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mainBackground.clearAnimation();
                            mainBackground.setX(0);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) { }
                    });

                    mainBackground.startAnimation(translateAnimation);
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
