package com.example.robinson;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class DrawThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    Paint background = new Paint();
    Paint waitLine = new Paint();
    Bitmap coconut;
    int x = 0;
    int y = 0;
    int timeLine = 0;
    double workAmplifier;
    ArrayList<PopCircle> deal;
    int j = 1;
    boolean startCircles;
    Activity c;
    ArrayList<Integer> positionsX = new ArrayList<>();
    ArrayList<Integer> positionsY = new ArrayList<>();


    public DrawThread(Context context, SurfaceHolder surfaceHolder, int a, Activity b) {
        this.surfaceHolder = surfaceHolder;
        Random random = new Random();
        coconut = BitmapFactory.decodeResource(context.getResources(),R.drawable.pop_coconut);
//        backgroundHint = BitmapFactory.decodeResource(context.getResources(),R.drawable.sun_theme);
        deal = new ArrayList<>();
        workAmplifier = a / 100;
        background.setStyle(Paint.Style.FILL);
        background.setColor(Color.rgb(108, 205, 212));
//        background.setTextScaleX(20f);
        waitLine.setTextSize(100f);
        waitLine.setColor(Color.RED);
        waitLine.setStyle(Paint.Style.FILL);
        startCircles = true;

        c = b;
    }
    public void requestStop() {
        running = false;
    }
    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                Random random = new Random();
                if (startCircles) {
                    for (int i = 0; i < workAmplifier * 10; i++) {
                        int thisX = random.nextInt((canvas.getWidth() - 1) / 100);
                        int thisY = random.nextInt((canvas.getHeight() - 1) / 100);
                        while (positionsX.contains(thisX)) thisX = random.nextInt((canvas.getWidth() - 1) / 100);
                        positionsX.add(thisX);
                        while (positionsY.contains(thisY)) thisY = random.nextInt((canvas.getHeight() - 1) / 100);
                        positionsY.add(thisY);
                        deal.add(new PopCircle(100 + thisX * 100, 100 + thisY * 100, i));
                    }
                    startCircles = false;
                }
                try {
                    int rectX = canvas.getWidth()/2;
                    int rectY = canvas.getHeight()/2;
                    canvas.drawRect(0,0, canvas.getWidth(), canvas.getHeight(), background );
//                    canvas.drawBitmap(backgroundHint, 0, 0, waitLine);
                    canvas.drawRect(rectX - 500 + timeLine, canvas.getHeight()-400, rectX + 500 - timeLine, canvas.getHeight()-200, waitLine );
//                    canvas.drawCircle(x, y, 50, waitLine);
//                    canvas.drawBitmap(coconut, x - coconut.getWidth() / 2, y - coconut.getHeight() / 2, waitLine);
                    for (int i = 1; i < deal.size(); i++) {
                        PopCircle circle = deal.get(i);

                        if (10000 > (x - circle.x) * (x - circle.x) + (y - circle.y ) * (y - circle.y ) && circle.number == j) {
                            deal.remove(circle);
                            j ++;
                        } else {
//                            canvas.drawCircle(circle.x, circle.y, 100, waitLine);
                            canvas.drawBitmap(coconut, circle.x - coconut.getWidth() / 2, circle.y - coconut.getHeight() / 2, waitLine);
                            canvas.drawText(circle.number + "",circle.x - waitLine.getTextSize() / 4, circle.y + waitLine.getTextSize() / 4, waitLine);
                        }
                    }

                    if (j == workAmplifier * 10) {
                        running = false;
                        c.finish();
                    }


                    if (timeLine < (canvas.getWidth()-100)/2) {
                        timeLine += 1/workAmplifier;
                    } else {
                        running = false;
                        c.finish();
                    }
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
