package com.avdoshka.android.luckywheel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;


public class MainActivity extends AppCompatActivity implements GestureLuckyWheel.RotationFlingListener{

    private GestureLuckyWheel gestureLuckyWheel;
    private Animation an;

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
        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Create an animation instance
                Animation an = new RotateAnimation(0.0f,+3600.0f, gestureLuckyWheel.getWidth() / 2, gestureLuckyWheel.getHeight() / 2);

                // Set the animation's parameters
                an.setDuration(100000/speed);               // duration in ms
                an.setRepeatCount(0);                // -1 = infinite repeated

                an.setInterpolator(new DecelerateInterpolator());

                // Aply animation to image view
                gestureLuckyWheel.setAnimation(an);
            }
        };

        gestureLuckyWheel.post(runnable);*/

        Log.d("AAAAAAAAAAAAA", "rotationMoment = " + String.valueOf(rotationMoment));
        int rotMom = Math.abs((int)rotationMoment);
        if (rotMom != 0) {
            an = new RotateAnimation(0.0f, -rotationMoment/400, gestureLuckyWheel.getWidth() / 2, gestureLuckyWheel.getHeight() / 2);
            // Set the animation's parameters
            an.setDuration(rotMom / 30);               // duration in ms
            an.setRepeatCount(0);                // -1 = infinite repeated
            an.setInterpolator(new DecelerateInterpolator());
            an.setFillAfter(true);
            // Aply animation to image view
            gestureLuckyWheel.startAnimation(an);
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
