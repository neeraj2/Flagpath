package com.neerajsingh.flagpath;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by neeraj.singh on 10/04/17.
 */

public class BubbleDrawable extends Drawable {
    private Paint paint = new Paint();
    private Paint grdientLint = new Paint();
    Path path = new Path();

    int width;
    int height;

    int coneWidth = 40;
    int coneHeight = 40;
    int halfCone = coneWidth / 2;
    private int coneStartXPosition;
    private int rightMargin = 0;
    private int leftMargin = 0;


    @Override
    public void draw(@NonNull Canvas canvas) {
        paint.setColor(Color.parseColor("#ff0000"));
        grdientLint.setColor(Color.parseColor("#bcbcbc"));
        path.moveTo(0, 0);
        path.lineTo(width, 0);
        path.lineTo(width, height - coneHeight);
        path.lineTo(width - (coneStartXPosition), height - coneHeight);
        path.lineTo(width - (coneStartXPosition + halfCone), height);
        path.lineTo(width - (coneStartXPosition + coneWidth), height - coneHeight);
        path.lineTo(0, height - coneHeight);
        path.lineTo(0, 0);
        path.close();
        grdientLint.setAntiAlias(false);
//        grdientLint.setPathEffect(effect);

        canvas.drawPath(path, grdientLint);

        canvas.drawLine(0, 0, width, 0, paint);
        canvas.drawLine(width - 1, 0, width - 1, height - coneHeight, paint);
        canvas.drawLine(width, height - coneHeight, width - coneStartXPosition, height - coneHeight, paint);
        canvas.drawLine(width - coneStartXPosition, height - coneHeight, width - coneStartXPosition - halfCone, height, paint);
        canvas.drawLine(width - coneStartXPosition - halfCone, height, width - coneStartXPosition - coneWidth, height - coneHeight, paint);
        canvas.drawLine(width - coneStartXPosition - coneWidth, height - coneHeight, 0, height - coneHeight, paint);
        canvas.drawLine(0, 0, 0, height - coneHeight, paint);

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        width = bounds.width();// - (leftMargin );
        height = bounds.height();
//        coneStartXPosition = (width - coneWidth) / 4;
    }

    void setConcCenterPoint(int xPosition) {
        coneStartXPosition = xPosition - halfCone;
    }
}
