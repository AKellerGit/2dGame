package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class ObstacleGeneration {
    //higher index => further down obstacle will be.
    private ArrayList<Obstacles> obstacles;
    private int pGap;
    private int obsGap;
    private int obsHeight;
    private int color;

    private long start;
    private long init = System.currentTimeMillis();

    private int score = 0;

    //constructor
    public ObstacleGeneration(int pGap, int obsGap, int obsHeight , int color) {
        this.pGap = pGap;
        this.obsGap = obsGap;
        this.obsHeight = obsHeight;
        this.color = color;
        start = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        populateObs();
    }

    // 4/3 overflow for obstacles generated based on screen height
    private void populateObs(){
        int y = -5*Dimensions.SCREEN_HEIGHT/4;

        //while rectangle has not gotten to screen => keep generating obstacles
        while (y < 0){
            //generate random x value gap for player to pass through
            int x = (int)(Math.random()*(Dimensions.SCREEN_WIDTH - pGap));

            //generate new obstacles
            obstacles.add(new Obstacles(obsHeight, color, x, y, pGap));
            y = y + obsHeight + obsGap;
        }
    }

    //if collision happens return true, otherwise return false
    public boolean Collision(Player player) {
        for(Obstacles obs : obstacles) {
            if(obs.Collision(player))
                return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    //framerate independence, update based on time
    public void update() {
        int elapsedTime = (int)(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();

        //time to take obstacle to reach bottom of screen (10seconds) at first
        //increase as time continues (every 5 seconds)
        float speed = (float)(Math.sqrt(1+(start - init)/5000.0))*Dimensions.SCREEN_HEIGHT/10000.0f;

        for(Obstacles obs : obstacles) {
            obs.incremY(speed *elapsedTime);
        }

        //generate new obstacles and add to the top
        if(obstacles.get(obstacles.size() - 1).getR().top >= Dimensions.SCREEN_HEIGHT) {
            int x = (int)(Math.random()*(Dimensions.SCREEN_WIDTH - pGap));
            obstacles.add(0,new Obstacles(obsHeight, color, x, obstacles.get(0).getR().top - obsHeight - obsGap, pGap));
            obstacles.remove(obstacles.size()-1);
            score++;
        }

    }

    //draw obstacles!
    public void draw (Canvas canvas) {
        for(Obstacles obs : obstacles) {
            obs.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(150);
        paint.setColor(Color.RED);
        canvas.drawText(score + "",50 ,150,paint);
    }
}
