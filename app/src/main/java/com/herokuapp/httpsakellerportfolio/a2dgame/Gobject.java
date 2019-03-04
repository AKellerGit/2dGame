package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.graphics.Canvas;

//due to the game object being an interface, remember these functions
//cannot be defined here, by definition. Similar to an Abstract base class.
public interface Gobject {
    public void draw(Canvas canvas);
    public void update();
}
