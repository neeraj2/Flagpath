package com.neerajsingh.flagpath;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        View linearLayout = findViewById(R.id.parent);
//        if(linearLayout!=null)
//        linearLayout.setBackground(new BubbleDrawable());
//        TextPath textPath = (TextPath) findViewById(R.id.textPath);
        List<String> textList = new ArrayList<>();
        textList.add("Approved");
        textList.add("Processed");
        textList.add("Shipped");
//        textList.add("Delivered");
        textList.add("Return");
//        textPath.setData(textList);

        OitViewGroup oitViewGroup = new OitViewGroup(this);
        oitViewGroup.setSteps(textList, 0, 3);
        FrameLayout parent = (FrameLayout) findViewById(R.id.parent);
        parent.addView(oitViewGroup);
    }
}
