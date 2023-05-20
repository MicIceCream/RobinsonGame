package com.example.robinson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    Paint background = new Paint();
    Paint waitLine = new Paint();
    private Bitmap bitmap;
    int x = 0;
    int y = 0;
    int timeLine = 0;


    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        background.setStyle(Paint.Style.FILL);
        background.setColor(Color.rgb(108, 205, 212));
        waitLine.setColor(Color.RED);
        waitLine.setStyle(Paint.Style.FILL);
    }
    public void requestStop() {
        running = false;
    }
    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), background );
                    canvas.drawRect(100 + timeLine, canvas.getHeight()-400, canvas.getWidth()-100 - timeLine, canvas.getHeight()-200, waitLine );
                    canvas.drawCircle(x, y, 50, waitLine);
                    if (timeLine < (canvas.getWidth()-200)/2) {
                        timeLine ++;
                    } else
                        this.stop();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
    public void setTowardPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
