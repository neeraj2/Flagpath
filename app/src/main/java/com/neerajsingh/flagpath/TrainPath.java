package com.neerajsingh.flagpath;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neeraj.singh on 02/05/17.
 */

public class TrainPath extends View {
    private int cirleRadius = 15;
    private Paint solidCircle;
    private Paint circle;
    private int size = 1;
    private int activeIndex = 0;
    private int padding = 0;
    private int height;
    private int width;
    private List<Float> lineDatas;

    public TrainPath(Context context) {
        super(context);
        init(context);
    }

    public TrainPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TrainPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TrainPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int gap = ((width - 2 * padding) / (size));
        int y = padding + gap / 2;
        for (int i = 0; i < size; i++) {
            int x = y + ((i * gap));
            canvas.drawCircle((x), cirleRadius, cirleRadius, i <= activeIndex ? solidCircle : circle);
            lineDatas.add(i, Float.valueOf((x)));
        }
        for (int i = 0; i < lineDatas.size() - 1; i++) {
            canvas.drawLine(lineDatas.get(i) + 2 * cirleRadius, cirleRadius, lineDatas.get(i + 1) - 2 * cirleRadius, cirleRadius, i >= activeIndex ? circle : solidCircle);
        }
        lineDatas.clear();
    }

    private void init(Context context) {
        solidCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        solidCircle.setColor(Color.parseColor("#16be48"));

        circle = new Paint(Paint.ANTI_ALIAS_FLAG);
        circle.setColor(Color.parseColor("#ff0000"));
        lineDatas = new ArrayList<>(size);
//        Resources r = context.getResources();
//        padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, r.getDisplayMetrics());
    }

    void setLength(int size) {
        if (size > 1) {
            this.size = size;
        }
    }

    void setActive(int index) {
        if (index < size && index > 0) {
            this.activeIndex = index;
        }
    }

    void setPadding(int padding) {
        this.padding = padding;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
