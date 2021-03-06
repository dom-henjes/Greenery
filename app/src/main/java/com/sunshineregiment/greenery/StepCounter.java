package com.sunshineregiment.greenery;

/**
 * Tab that shows the current number of steps for day/week/month
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StepCounter extends AppCompatActivity implements SensorEventListener {

    private TextView textView;

    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    private Sensor mStepDetectorSensor;

    /**
     * initialise sensors and prepare tab for action
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.steps_textView);

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    /**
     * On sensor change, update total steps taken to new value
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        final Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }
        final int value2 = value;
        try {
            if (textView != null) {
                textView.getHandler().post(new Runnable() {

                    @Override
                    public void run() {
                        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER || sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
                            textView.setText("" + value2);
                        }
                    }
                });
            } else {
                textView = (TextView) findViewById(R.id.steps_textView);
            }
        } catch (Exception e) {
            //Do nothing
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }
}

