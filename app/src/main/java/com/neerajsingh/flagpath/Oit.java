package com.neerajsingh.flagpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neeraj.singh on 11/04/17.
 */

public class Oit extends View {

    List<TextData> textList;
    private Paint mTextPaint;
    private Paint solidCircle;
    private Paint circle;
    private Paint rectBorder;
    private RectF bubble;
    private int width;
    private int height;
    private List<LineData> lineDatas;
    private int cirleRadius = 15;
    private int circleYPosition = 340;
    private int textYPosition = 400;
    private int padding = 50;

    private float rectWidth = 434;
    private float rectHeight = 248;
    private Path path = new Path();
    private Paint grdientLint = new Paint();
    private int listSize = 9;

    public Oit(Context context) {
        super(context);
        init(context);
    }

    public Oit(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Oit(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public Oit(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#000000"));
        mTextPaint.setTextSize(40);
        solidCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        solidCircle.setColor(Color.parseColor("#16be48"));

        circle = new Paint(Paint.ANTI_ALIAS_FLAG);
        circle.setColor(Color.parseColor("#e0e0e0"));

        rectBorder = new Paint(Paint.ANTI_ALIAS_FLAG);
//        rectBorder.setColor(Color.parseColor("#d8d8d8"));
        rectBorder.setColor(Color.parseColor("#FF0000"));

        grdientLint.setColor(Color.parseColor("#FFFFff"));

        bubble = new RectF();

        textList = new ArrayList<>(listSize);
        textList.add(new TextData("Approved"));
        textList.add(new TextData("Processed"));
        textList.add(new TextData("Shipped"));
        textList.add(new TextData("Delivered"));
        textList.add(new TextData("Return"));
//        textList.add(new TextData("DISPATCH"));

        lineDatas = new ArrayList<>(listSize);
        for (int i = 0; i < textList.size(); i++) {
            TextData textData = textList.get(i);
            float[] car = new float[textData.text.length()];
            mTextPaint.getTextWidths(textData.text, car);
            for (int j = 0; j < car.length; j++) {
                textData.length += car[j];
            }
            if (i < textList.size()) {
                lineDatas.add(new LineData());
            }
        }

        List<TextView> textViewList = new ArrayList<>(textList.size());
        for(int i=0;i<textList.size();i++){
            TextView textView = new TextView(context);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = 500;
        width = widthMeasureSpec;
        setMeasuredDimension(widthMeasureSpec, 500);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int size = textList.size();
        int parentWidth = getWidth();
        int width = parentWidth / size;
        for (int i = 0; i < size; i++) {
            int x = padding + (i * width);
            TextData textData = textList.get(i);
            canvas.drawText(textData.text, x, textYPosition, mTextPaint);
            canvas.drawCircle(x + (textData.length / 2), circleYPosition, cirleRadius, solidCircle);
            lineDatas.get(i).start = x + (textData.length / 2);
        }

        for (int i = 0; i < lineDatas.size() - 1; i++) {
            canvas.drawLine(lineDatas.get(i).start + 2 * cirleRadius, circleYPosition, lineDatas.get(i + 1).start - 2 * cirleRadius, circleYPosition, solidCircle);
        }

        float x = lineDatas.get(2).start;
        float y = circleYPosition - cirleRadius - 25;

        float halfRect = rectWidth / 2;
        float halfXTraingle = 10;
        float halfYTraingle = 21;

        float leftToTraingle = halfRect;
        float rightToTraingle = halfRect;
        if (parentWidth < x + halfRect) {
            rightToTraingle = parentWidth - x - padding;
            leftToTraingle = rectWidth - rightToTraingle;
        } else if (x - halfRect < 0) {
            leftToTraingle = x - padding;
            rightToTraingle = rectWidth - leftToTraingle;
        }

        path.moveTo(x, y + halfYTraingle);
        path.lineTo(x + halfXTraingle, y);
        path.lineTo(x + rightToTraingle, y);
        path.lineTo(x + rightToTraingle, y - rectHeight);
        path.lineTo(x - leftToTraingle, y - rectHeight);
        path.lineTo(x - leftToTraingle, y);
        path.lineTo(x - halfXTraingle, y);
        path.lineTo(x, y);
        path.close();
        grdientLint.setAntiAlias(false);
        canvas.drawPath(path, grdientLint);

        canvas.drawLine(x, y + halfYTraingle, x + halfXTraingle, y, rectBorder);
        canvas.drawLine(x, y + halfYTraingle, x - halfXTraingle, y, rectBorder);
        canvas.drawLine(x - halfXTraingle, y, x - leftToTraingle, y, rectBorder);
        canvas.drawLine(x + halfXTraingle, y, x + rightToTraingle, y, rectBorder);
        canvas.drawLine(x - leftToTraingle, y, x - leftToTraingle, y - rectHeight, rectBorder);
        canvas.drawLine(x + rightToTraingle, y, x + rightToTraingle, y - rectHeight, rectBorder);
        canvas.drawLine(x - leftToTraingle, y - rectHeight, x + rightToTraingle, y - rectHeight, rectBorder);
    }

    private static class TextData {
        String text;
        float length;

        public TextData(String text) {
            this.text = text;
        }
    }

    private static class LineData {
        float start;
    }
}
