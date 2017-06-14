package com.neerajsingh.flagpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neeraj.singh on 31/03/17.
 */

public class FlagPath extends View {

    private List<String> textList;
    private Paint textPaint;
    private Paint circle;
    private Paint line;
    private float mTextHeight = 0;
    int width = 0;
    int listSize = 0;
    public FlagPath(Context context) {
        super(context);
        init();
    }

    public FlagPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlagPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FlagPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        textList = new ArrayList<>(4);
        textList.add("Approved");
        textList.add("Processed");
        textList.add("Shipped");
        textList.add("Delivered");
        listSize= textList.size();
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#212121"));
        circle = new Paint();
        circle.setColor(Color.parseColor("#16be48"));
        line = new Paint();
        line.setColor(Color.parseColor("#e0e0e0"));

        if (mTextHeight == 0) {
            mTextHeight = textPaint.getTextSize();
        } else {
            textPaint.setTextSize(mTextHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int div = width/listSize;
        for (int i = 0; i < textList.size(); i++) {
            canvas.drawText(textList.get(i),i*div,0,textPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        width = widthMeasureSpec - 2 * 72;
        setMeasuredDimension(width, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
