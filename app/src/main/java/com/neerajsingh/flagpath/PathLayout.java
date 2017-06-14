package com.neerajsingh.flagpath;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by neeraj.singh on 11/04/17.
 */

public class PathLayout extends LinearLayout {
    public PathLayout(Context context) {
        super(context);
        init();
    }


    public PathLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PathLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ArrayList<String> textList = new ArrayList<>(4);
        textList.add("Approved");
        textList.add("Processed");
        textList.add("Shipped");
        textList.add("Delivered");
        setOrientation(HORIZONTAL);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (int i = 0; i < textList.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            TextView textView = (TextView) inflater.inflate(R.layout.text_layout, null);
            textView.setText(textList.get(i));
            textView.setLayoutParams(params);
            addView(textView, i);
        }
    }

}
