package com.example.robinson;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class BuildView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;

    public BuildView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    drawThread = new DrawThread(getContext(),getHolder());
    drawThread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    drawThread.requestStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.setTowardPoint((int)event.getX(),(int)event.getY());
        return super.onTouchEvent(event);
    }
}
