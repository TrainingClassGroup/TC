package com.tc.edu.tc;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class Head2Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.head2_fragment, container, false);



        final LinearLayout head2_menu1 = (LinearLayout) view.findViewById(R.id.head2_menu1);
        final LinearLayout head2_menu2 = (LinearLayout) view.findViewById(R.id.head2_menu2);
        final LinearLayout head2_menu3 = (LinearLayout) view.findViewById(R.id.head2_menu3);

        final AtomicInteger menuLock = new AtomicInteger(0);
        head2_menu1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (menuLock.get() > 0 && menuLock.get() != 1) return true;
                ImageView head2_menu1_image = (ImageView) view.findViewById(R.id.head2_menu1_image);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    menuLock.set(1);
                    head2_menu1.setBackgroundColor(Color.parseColor("#FF8099FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow270);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    head2_menu1.setBackgroundColor(Color.parseColor("#FF3760FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    menuLock.set(0);

                    pullUpDownMenu(getActivity(), false);
                }

                return true;
            }

        });

        head2_menu2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (menuLock.get() > 0 && menuLock.get() != 2) return true;
                ImageView head2_menu1_image = (ImageView) view.findViewById(R.id.head2_menu1_image);
                ImageView head2_menu2_image = (ImageView) view.findViewById(R.id.head2_menu2_image);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    menuLock.set(2);
                    head2_menu2.setBackgroundColor(Color.parseColor("#FF8099FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow470);
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow270);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    head2_menu2.setBackgroundColor(Color.parseColor("#FF3760FF"));
                    head2_menu1_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    menuLock.set(0);

                    pullUpDownMenu(getActivity(), false);
                }
                return true;
            }

        });

        head2_menu3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (menuLock.get() > 0 && menuLock.get() != 3) return true;
                ImageView head2_menu2_image = (ImageView) view.findViewById(R.id.head2_menu2_image);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    menuLock.set(3);
                    head2_menu3.setBackgroundColor(Color.parseColor("#FF8099FF"));
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow470);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    head2_menu3.setBackgroundColor(Color.parseColor("#FF3760FF"));
                    head2_menu2_image.setImageResource(R.drawable.hdpi_right_arrow070);
                    menuLock.set(0);

                    pullUpDownMenu(getActivity(), false);
                }
                return true;
            }

        });

        return view;
    }

    public static void pullUpDownMenu(final Activity activity, boolean enableClose) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int maxHeight = dm.heightPixels * 2 / 3;

        final LinearLayout head2_pulldown_menu = (LinearLayout) activity.findViewById(R.id.head2_pulldown_menu);
        if (head2_pulldown_menu.getY() < 0) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, maxHeight);
            translateAnimation.setDuration(500);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    head2_pulldown_menu.clearAnimation();
                    head2_pulldown_menu.setY(0);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            head2_pulldown_menu.startAnimation(translateAnimation);
        }
        else if (enableClose) {
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -maxHeight);
            translateAnimation.setDuration(500);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    head2_pulldown_menu.clearAnimation();
                    head2_pulldown_menu.setY(-maxHeight);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            head2_pulldown_menu.startAnimation(translateAnimation);
        }


        /*
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        LinearLayout head2_pulldown_menu = (LinearLayout) activity.findViewById(R.id.head2_pulldown_menu);

        ResizeAnimation a = new ResizeAnimation(head2_pulldown_menu);
        a.setDuration(1000);
        if (head2_pulldown_menu.getLayoutParams().height <= 0) {
            a.setParamsHeight(0, dm.heightPixels * 2 / 3);
            head2_pulldown_menu.startAnimation(a);
        } else if (enableClose) {
            a.setParamsHeight(head2_pulldown_menu.getLayoutParams().height, 0);
            head2_pulldown_menu.startAnimation(a);
        }
        */
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();


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


}
/*
class MyAsyncTask extends AsyncTask<String, Integer, String> {

    private View view;

    public MyAsyncTask(View view) {
        super();
        this.view = view;
    }

    @Override
    protected String doInBackground(String... params) {
        while(){


            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

        }

        publishProgress();
        return mName;
    }

    protected void onProgressUpdate(Integer... progress) {
        setProgressPercent(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Log.e(TAG, result + "execute finish at " + df.format(new Date()));
    }
}
*/
