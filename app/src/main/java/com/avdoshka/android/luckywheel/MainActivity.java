package com.avdoshka.android.luckywheel;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;


public class MainActivity extends AppCompatActivity implements GestureLuckyWheel.RotationFlingListener{

    private GestureLuckyWheel gestureLuckyWheel;
    private float lastRotation;

    @Override
    public void onRotationFling(float rotationMoment) {
        Log.d("AAAAAAAAAA", "from onRotationFling()");
        if (Math.abs(rotationMoment) > 0)
            startRotateAnimation(rotationMoment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureLuckyWheel = (GestureLuckyWheel) findViewById(R.id.lucky_wheel);
        gestureLuckyWheel.subscribeRotationListener(this);

        //startRotateAnimation(10);

        //new LuckyWheelRotationAsyncTask().execute();

    }

    private void startRotateAnimation(final float rotationMoment) {

        int rotMom = Math.abs((int)rotationMoment);
        if (rotMom != 0) {
            /*float initRotation = gestureLuckyWheel.getRotation();
            Log.d("AAAAAAAAAAAA", "initRotation: " + initRotation);
            an = new RotateAnimation(initRotation, initRotation - rotationMoment/400, gestureLuckyWheel.getWidth() / 2, gestureLuckyWheel.getHeight() / 2);
            // Set the animation's parameters
            an.setDuration(rotMom / 30);               // duration in ms
            an.setRepeatCount(0);                // -1 = infinite repeated
            an.setInterpolator(new DecelerateInterpolator());
            an.setFillAfter(true);

            // Aply animation to image view
            gestureLuckyWheel.startAnimation(an);*/


            ObjectAnimator animation = ObjectAnimator.ofFloat(gestureLuckyWheel, "rotation", lastRotation, lastRotation - rotationMoment/400);
            animation.setDuration(rotMom / 30);
            gestureLuckyWheel.setPivotX(gestureLuckyWheel.getWidth() / 2);
            gestureLuckyWheel.setPivotY(gestureLuckyWheel.getHeight() / 2);
            animation.setRepeatCount(0);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {}

                @Override
                public void onAnimationEnd(Animator animator) {
                    lastRotation = gestureLuckyWheel.getRotation();
                }

                @Override
                public void onAnimationCancel(Animator animator) {}

                @Override
                public void onAnimationRepeat(Animator animator) {}
            });
            animation.start();
        }

    }

/*    private class LuckyWheelRotationAsyncTask extends AsyncTask<Void, Float, Void>{

        LuckyWheel luckyWheel = (LuckyWheel) MainActivity.this.findViewById(R.id.lucky_wheel);

        @Override
        protected Void doInBackground(Void... voids) {

            float bearing = 0f;
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bearing = bearing + 0.1f;
                publishProgress(bearing);
            }

        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
            luckyWheel.setBearing(values[0]);
            Log.d("AAAAAAAAAAAAA", String.valueOf(values[0]));
        }
    }*/

}
