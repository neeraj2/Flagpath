package com.neerajsingh.flagpath;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by neeraj.singh on 03/05/17.
 */

public class TextPath extends LinearLayout {

    public TextPath(Context context) {
        super(context);
    }

    public TextPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextPath(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setData(List<String> list) {
        setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, list.size());
        setLayoutParams(layoutParams);
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        for (int i = 0; i < list.size(); i++) {
            TextView textView = (TextView) layoutInflater.inflate(R.layout.text, this, false);
            textView.setText(list.get(i));
            addView(textView);
        }
    }
}
