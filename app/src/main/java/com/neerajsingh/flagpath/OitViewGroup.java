package com.neerajsingh.flagpath;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by neeraj.singh on 02/05/17.
 */

public class OitViewGroup extends LinearLayout {
    private BubbleLayout bubble;
    private TrainPath trainPath;
    private TextPath textLayout;

    public OitViewGroup(Context context) {
        super(context);
    }

    public OitViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OitViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public OitViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSteps(List<String> textList, int activeIndex, int numberOfProducts) {
//        textList.add("Approved");
//        textList.add("Processed");
//        textList.add("Shipped");
//        textList.add("Delivered");
//        textList.add("Return");

        /** TODO: 06/05/17  Going to extend LinearLayout and in begeining only add 3 views to parent
         * First chile is linearLayout of horizontal first with be a ImageView and 2nd will be TextView to represnt +
         * third will be either an Image view or TextView based on the number of product Items we are getting from response
         * A custom drawable will be set as background of this Linear layout for showing that edge for showing connection
         * between bubble and train path.
         * 2nd child will be trainPath a  custome view which will draw a train with different color scheme for active and
         * pending steps.
         * Third child is LinearLayout with equal waits for TextViews these textviews will be ellipsize in the end.
         */

        setOrientation(VERTICAL);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        Context context = getContext();
        //add bubble to ViewGroup
        bubble = new BubbleLayout(context);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int columnSize = widthPixels / textList.size();
        bubble.setUp(numberOfProducts, columnSize, activeIndex, widthPixels);
        addView(bubble);

        //add trainPath
        trainPath = new TrainPath(context);
        trainPath.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40));
        trainPath.setLength(textList.size());
        trainPath.setActive(activeIndex);
        addView(trainPath);
        //add textPath

        textLayout = new TextPath(context);
        textLayout.setData(textList);
        addView(textLayout);
    }
}
