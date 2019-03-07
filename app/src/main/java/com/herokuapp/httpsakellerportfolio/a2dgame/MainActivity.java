package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;



public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets fullscreen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);
        Dimensions.SCREEN_WIDTH = DM.widthPixels;
        Dimensions.SCREEN_HEIGHT = DM.heightPixels;
        setContentView(new Panel(this));

    }

    public void addData() {
        database db = new database(this);
    }
}
