package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

//defining the Gobject interface
public class Player implements Gobject {
    private Rect r;
    //colors are represented by integers in android studio
    private int color;

    //construct with 2 parameters initializing rectangle and color
    public Player(Rect r, int color) {
        this.r = r;
        this.color = color;
    }
    //accessor function
    public Rect getrect() {
        return r;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(r, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        //top screen is zero y value, increases at point moves down
        r.set(point.x - r.width()/2, point.y - r.height()/2,
                point.x + r.width()/2, point.y + r.height()/2);


    }
}
