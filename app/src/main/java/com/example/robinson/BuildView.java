package com.example.robinson;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class BuildView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    int workAmplifier;
    int timeLine;
    Activity c;

    public BuildView(Context context, int a, Activity b) {
        super(context);
        getHolder().addCallback(this);
        workAmplifier = a;
        c = b;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    drawThread = new DrawThread(getContext(),getHolder(), workAmplifier, c);
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
    DrawThread getDrawThread() {
        return drawThread;
    }
}
