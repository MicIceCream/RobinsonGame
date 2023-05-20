package com.example.robinson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    Paint background = new Paint();
    Paint waitLine = new Paint();
    private Bitmap bitmap;
    int x = 0;
    int y = 0;
    int timeLine = 0;
    double workAmplifier;
    ArrayList<PopCircle> deal;
    int j = 1;



    public DrawThread(Context context, SurfaceHolder surfaceHolder, int a) {
        this.surfaceHolder = surfaceHolder;
        Random random = new Random();
        deal = new ArrayList<>();
        workAmplifier = a / 100;
        background.setStyle(Paint.Style.FILL);
        background.setColor(Color.rgb(108, 205, 212));
//        background.setTextScaleX(20f);
        background.setTextSize(100f);
        waitLine.setColor(Color.RED);
        waitLine.setStyle(Paint.Style.FILL);
        for (int i = 0; i < a / 10; i++) {
            deal.add(new PopCircle(100 + random.nextInt(500), 200 + random.nextInt(1000), i));
        }
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
                    int rectX = canvas.getWidth()/2;
                    int rectY = canvas.getHeight()/2;
                    canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), background );
                    canvas.drawRect(rectX - 500 + timeLine, canvas.getHeight()-400, rectX + 500 - timeLine, canvas.getHeight()-200, waitLine );
                    canvas.drawCircle(x, y, 50, waitLine);
                    for (int i = 1; i < deal.size(); i++) {
                        PopCircle circle = deal.get(i);

                        if (10000 > (x - circle.x) * (x - circle.x) + (y - circle.y ) * (y - circle.y ) && circle.number == j) {
                            deal.remove(circle);
                            j ++;
                        } else {
                            canvas.drawCircle(circle.x, circle.y, 100, waitLine);
                            canvas.drawText(circle.number + "",circle.x - background.getTextSize() / 4, circle.y + background.getTextSize() / 4, background);
                        }
                    }

                    if (j == workAmplifier * 10)
                        this.stop();

                    if (timeLine < (canvas.getWidth()-100)/2) {
                        timeLine += 1/workAmplifier;
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
