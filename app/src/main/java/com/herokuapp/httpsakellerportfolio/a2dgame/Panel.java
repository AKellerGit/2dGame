package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Dimension;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Player player;
    private Point point;
    private ObstacleGeneration obsGen;
    private boolean validMove;
    private boolean End;
    private long EndTimer;
    private Rect r = new Rect();

    //Constructor
    public Panel(Context context) {
        super(context);
        obsGen = new ObstacleGeneration(200,350,75, Color.DKGRAY);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        player = new Player(new Rect(100,100,200,200), Color.BLACK);
        point = new Point(Dimensions.SCREEN_WIDTH/2, Dimensions.SCREEN_HEIGHT/3);
        player.update(point);
        setFocusable(true);
    }

    public void reset(){
        point = new Point(Dimensions.SCREEN_WIDTH/2, Dimensions.SCREEN_HEIGHT/3);
        player.update(point);
        obsGen = new ObstacleGeneration(200,350,75, Color.DKGRAY);
        validMove = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    //changes on touch event, on every touch.
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()) {
            //on touch
            case MotionEvent.ACTION_DOWN:
                //check if point touched is inside rectangle, if so then move is allowed
                if(!End && player.getrect().contains((int)event.getX(),(int)event.getY())) {
                    validMove = true;
                }
                //reset game after 2 seconds
                if(End && System.currentTimeMillis() - EndTimer >=2000){
                    reset();
                    End = false;
                }
            break;
            //on move
            case MotionEvent.ACTION_MOVE:
                if(!End && validMove) {
                    point.set((int) event.getX(), (int) event.getY());
                }
                break;
            //On release touch
            case MotionEvent.ACTION_UP:
                validMove = false; break;
        }

        //returning super.ontouchEvent(event) gives reason to return false in the future.
        //in our case, this is unnecessary.
        return true;
        //return super.onTouchEvent(event);
    }

    public void update(){
        if(!End) {
            player.update(point);
            obsGen.update();
            if(obsGen.Collision(player)) {
                End = true;
                EndTimer = System.currentTimeMillis();
            }
        }
}

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        canvas.drawColor(Color.GREEN);
        player.draw(canvas);
        obsGen.draw(canvas);

        if(End) {
            Paint paint = new Paint();
            paint.setTextSize(125);
            paint.setColor(Color.RED);
            drawEndGameText(canvas,paint,"You LOSE!");
        }
    }

    private void drawEndGameText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}