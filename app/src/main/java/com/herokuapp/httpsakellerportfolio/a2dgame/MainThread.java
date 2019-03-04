package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    public static final int MAX_FPS = 30;
    private double AveFPS;
    private SurfaceHolder surfacehold;
    private Panel Gpanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfacehold, Panel Gpanel) {
        super();

        this.surfacehold = surfacehold;
        this.Gpanel = Gpanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        long startTime;
        long milliseconds = 1000/MAX_FPS;
        long waittimer;
        int framecount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfacehold.lockCanvas();
                synchronized (surfacehold) {
                    this.Gpanel.update();
                    this.Gpanel.draw(canvas);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
            finally {
                if(canvas != null){
                    try{
                        surfacehold.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            milliseconds = (System.nanoTime() - startTime)/1000000;
            waittimer = targetTime - milliseconds;
            totalTime = totalTime + (System.nanoTime() - startTime);
            framecount++;

            try {
                if(waittimer > 0)
                    this.sleep(waittimer);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            if (framecount == MAX_FPS) {
                AveFPS = 1000/((totalTime/framecount)/1000000);
                framecount = 0;
                totalTime = 0;
                System.out.println(AveFPS);
            }

        }
    }



}
