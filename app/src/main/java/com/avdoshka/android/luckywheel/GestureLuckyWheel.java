package com.avdoshka.android.luckywheel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by User on 15.09.2017.
 */

public class GestureLuckyWheel extends LuckyWheel{

    interface RotationFlingListener {
        void onRotationFling(float rotationMoment);
    }

    private RotationFlingListener rotationFlingListener;
    private GestureDetectorCompat mDetector;


    public GestureLuckyWheel(Context context) {
        super(context);
        initGestureDetector();
    }

    public GestureLuckyWheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initGestureDetector();
    }

    public GestureLuckyWheel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGestureDetector();
    }

    public void subscribeRotationListener(RotationFlingListener rotationFlingListener) {
        this.rotationFlingListener = rotationFlingListener;
        Log.d("AAAAAAAAAAAAAAAAA", " from subscribeRotationListener()");
    }

    public void unSubscribeRotationListener(RotationFlingListener rotationFlingListener) {
        this.rotationFlingListener = null;
    }

    private void initGestureDetector() {
        mDetector = new GestureDetectorCompat(getContext(), new FlingGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return true;
    }

    private class FlingGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            float rX = event1.getX() - mCenterX;
            float rY = event1.getY() - mCenterY;
            float velocity = (float)Math.sqrt(velocityX * velocityX + velocityY * velocityY);
            float F1 = event2.getX() - event1.getX();
            float F2 = event2.getY() - event1.getY();
            float modulF = (float)Math.sqrt(F1 * F1 + F2 * F2);

            float rotationMoment = velocity * (rX * F2 - rY * F1) / modulF;
            // normal rotationMoment
            rotationMoment /= -1;
            Log.d("AAAAAAAAAAAAAAA", "rotationMoment ===== " + rotationMoment);

            rotationFlingListener.onRotationFling(rotationMoment);

            return true;
        }
    }

}
