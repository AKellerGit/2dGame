package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacles implements Gobject {
    private Rect rLeft;
    private Rect rRight;
    private int color;


    public Rect getR() {
        return rLeft;
    }

    //construct with 2 parameters initializing rectangle and color
    public Obstacles(int rHeight, int color, int x,int y, int pGap) {
        this.color = color;

        rLeft = new Rect(0, y, x, y + rHeight );
        rRight = new Rect(x + pGap, y, Dimensions.SCREEN_WIDTH, y + rHeight);


    }

    //move obstacles down
    public void incremY (float y) {
        rLeft.top += y;
        rLeft.bottom += y;
        rRight.top += y;
        rRight.bottom += y;
    }

    //detects collision based on the player rectangle that was drawn.
    //any overlap returns true and the game will end.
    public boolean Collision(Player player) {
        return Rect.intersects(rLeft, player.getrect()) || Rect.intersects(rRight,player.getrect());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rLeft, paint);
        canvas.drawRect(rRight,paint);
    }

    @Override
    public void update() {

    }


    }
