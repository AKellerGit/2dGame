package com.herokuapp.httpsakellerportfolio.a2dgame;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


/* public class GyroControl implements SensorEventListener{
   private SensorManager manager;
    private Sensor accelerate;
    private Sensor mag;
    private float[] accelerateOut;
    private float[] magOut;
    private float[] gyro;
    private float[] startGyro = null;

    public float[] getGyro() {
        return gyro;
    }

    public float[] getStartGyro() {
        return startGyro;
    }

    public void newGame() {
        startGyro = null;
    }

    public GyroControl() {
        manager = (SensorManager) Dimensions.CURRENT.getSystemService(Context.SENSOR_SERVICE);
        accelerate = manager.getDefaultSensor (Sensor.TYPE_ACCELEROMETER);
        mag = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void register() {
        manager.registerListener(this,accelerate, SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this,mag, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause() {
        manager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            accelerateOut = event.values;
        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            magOut = event.values;
        }
        if(accelerateOut != null && magOut != null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, accelerateOut, magOut);
            if(success) {
                SensorManager.getOrientation(R, gyro);
                if(startGyro == null) {
                    startGyro = new float[gyro.length];
                    System.arraycopy(gyro,0,startGyro,0,gyro.length);
                }
            }
        }
    }



}
*/