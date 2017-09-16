package com.avdoshka.android.luckywheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Irina on 13.09.2017.
 */

public class LuckyWheel extends View {

    //private float mBearing;
    private Paint mCirclePaint;
    private Paint mSectorPaint;
    private Paint mTextPaint;
    protected int mCenterX, mCenterY;
    private  int mRadius;
    private RectF mRectF;
    private int [] mColorArr;
    private int mViewWidth, mViewHeight;
    private final int TEXT_SIZE = 30;



    public LuckyWheel(Context context) {
        super(context);
        initLuckyWheel();
    }

    public LuckyWheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLuckyWheel();
    }

    public LuckyWheel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLuckyWheel();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // take the smallest size
        int size = Math.min(measure(widthMeasureSpec), measure(heightMeasureSpec));
        setMeasuredDimension(size, size);
    }

    private int measure(int measureSpec) {
        int result;
        // decoding measureSpec
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 200;
        } else {
            result = specSize;
        }

        return result;
    }

    private void initLuckyWheel() {
        setFocusable(true);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
        mCirclePaint.setStrokeWidth(7);
        mCirclePaint.setStyle(Paint.Style.STROKE);

        mSectorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSectorPaint.setStyle(Paint.Style.FILL);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(TEXT_SIZE);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);

        mColorArr  = new int[] {R.color.colorOne, R.color.colorTwo, R.color.colorThree, R.color.colorFour,
                R.color.colorOne, R.color.colorTwo, R.color.colorThree, R.color.colorFour};
    }

    private void calculateCircleParams() {
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        mCenterX = mViewWidth / 2;
        mCenterY = mViewHeight / 2;
        mRadius = (int)(0.8 * Math.min(mCenterX, mCenterY));
        mRectF = new RectF(mCenterX - mRadius, mCenterY - mRadius,
                           mCenterX + mRadius, mCenterY +mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
Log.d("AAAAAAAAAAAAAAAAAA", "from onDraw");
        if (mRadius == 0) {
            calculateCircleParams();
        }
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);

        int angleStep = 360 / mColorArr.length;
        int initialAngle = 180 - angleStep/2;
        for (int i = 0; i < mColorArr.length; i++) {
            mSectorPaint.setColor(ContextCompat.getColor(getContext(),mColorArr[i]));
            canvas.drawArc(mRectF, initialAngle,angleStep, true, mSectorPaint);
            canvas.drawText("OPTION " + i, mViewWidth / 2f - mRadius * 0.9f, mViewHeight / 2f + TEXT_SIZE / 2, mTextPaint);

            canvas.rotate(-angleStep, mCenterX, mCenterY);
        }

    }

/*    public float getBearing() {
        return mBearing;
    }

    public void setBearing(float mBearing) {
        this.mBearing = mBearing;
        invalidate();
        Log.d("AAAAAAAAAAAAAA", "from setBearing " + mBearing);
    }
*/
}
