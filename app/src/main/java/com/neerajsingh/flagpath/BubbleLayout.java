package com.neerajsingh.flagpath;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by neeraj.singh on 07/05/17.
 */

public class BubbleLayout extends LinearLayout {
    private int bubbleChildSize = 1;
    private ImageView imageView;
    private ImageView imageViewRight;
    private TextView plusView;
    private TextView textView;

    public BubbleLayout(Context context) {
        super(context);
    }

    public BubbleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setUp(int noOfProducts, int columnSize, int activeIndex, int totalWidth) {
        this.bubbleChildSize = noOfProducts;
        setOrientation(HORIZONTAL);
        int mySize = noOfProducts == 1 ? 168 : noOfProducts == 2 ? 374 : 378;
        int leftMargin = (activeIndex * columnSize) + (mySize < columnSize ? (columnSize - mySize) / 2 : 0);
        leftMargin = leftMargin > 0 ? (totalWidth - leftMargin < mySize ? totalWidth - mySize - 20 : leftMargin) : 20;
        int rightMargin = totalWidth - leftMargin - mySize;
        rightMargin = rightMargin > 0 ? rightMargin : 20;

        LayoutParams layoutParams = new LinearLayout.LayoutParams(mySize, 300);

        layoutParams.setMargins(leftMargin, 20, rightMargin, 0);

        setLayoutParams(layoutParams);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        if (noOfProducts > 0) {
            imageView = (ImageView) layoutInflater.inflate(R.layout.child_image, this, false);
            addView(imageView);
            if (noOfProducts == 2) {
                plusView = (TextView) layoutInflater.inflate(R.layout.plus_layout, this, false);
                addView(plusView);
                imageViewRight = (ImageView) layoutInflater.inflate(R.layout.child_image, this, false);
                addView(imageViewRight);
            } else if (noOfProducts > 2) {
                textView = (TextView) layoutInflater.inflate(R.layout.more_text_layout, this, false);
                textView.setText(" + " + (noOfProducts - 1) + " \n more");
                addView(textView);
            }
        }
        BubbleDrawable bubbleDrawable = new BubbleDrawable();
        int x = activeIndex * columnSize + columnSize / 2;
        int y = leftMargin + mySize;
        bubbleDrawable.setConcCenterPoint(y - x);
        setBackground(bubbleDrawable);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ImageView getImageViewRight() {
        return imageViewRight;
    }
}
