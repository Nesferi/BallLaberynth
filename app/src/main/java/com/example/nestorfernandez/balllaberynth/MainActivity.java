package com.example.nestorfernandez.balllaberynth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

//Implementamos SensorEventListener para poder utilizar el acelerometro
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView texto;
    SensorManager sensorManager;
    private Sensor acelerometro;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creamos un SensorManager y cogemos el acelerometro del movil
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Asignamos GameView como la vista de la aplicacion
        gameView = new GameView(this);
        setContentView(gameView);
    }

    //Metodos sobreescritos por obligacion al implementar SensorEventListener
    @Override
    public void onSensorChanged(SensorEvent event) {
        gameView.comunicateData(event.values.clone());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onPostResume();
        sensorManager.registerListener(this,acelerometro,sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
