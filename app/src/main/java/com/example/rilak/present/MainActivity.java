package com.example.rilak.present;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Semaphore;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private GameView gameView;

    private SensorManager sensorManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView =new GameView(this);
        setContentView(gameView);

        sensorManager =(SensorManager)getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            Log.d("SensorValues","\nX軸"+ sensorEvent.values[0]+"\nY軸"+sensorEvent.values[1]+"\nz軸"+sensorEvent.values[2]);

            if(gameView.player !=null){
                gameView.player.move(-sensorEvent.values[0]);
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    protected void  onResume(){
        super.onResume();

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(!sensors.isEmpty()){
            sensorManager.registerListener(this,sensors.get(0), SensorManager.SENSOR_DELAY_FASTEST);
        }

    }
    @Override
    protected void onPause(){
        sensorManager.unregisterListener(this);
        super.onPause();
    }
}
