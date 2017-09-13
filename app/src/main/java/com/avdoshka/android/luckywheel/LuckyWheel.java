package com.avdoshka.android.luckywheel;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Irina on 13.09.2017.
 */

public class LuckyWheel extends View {

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

    private void initLuckyWheel() {
        setFocusable(true);
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
}
