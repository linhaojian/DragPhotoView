package com.wingsofts.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2018/1/15.
 */

@SuppressLint("AppCompatCustomView")
public class DragImageView extends ImageView {
    private static float SCALE_MIN = 0.3f,SCALE_MAX = 1f;
    private float scale = 1;
    private float downX,downY,dragX,dragY;
    private float transX,transY;
    public DragImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(transX,transY);
        canvas.scale(scale,scale,getWidth()/2.0f,getHeight()/2.0f);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            downX = event.getX();
            downY = event.getY();
        }else {
            dragX = event.getX() - downX;
            dragY = event.getY() - downY;
            scale = 1 - (dragY/500.0f);
            if(scale<SCALE_MIN){
                scale = SCALE_MIN;
            }else if(scale>SCALE_MAX){
                scale = SCALE_MAX;
            }
            transX = dragX;
            transY = dragY;
            invalidate();
        }
        return true;
    }

}
