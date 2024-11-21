package com.example.assignment3;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float lastX, lastY, lastZ;
    private static final float SHAKE_THRESHOLD = 12.0f;
    private boolean isDialogVisible = false;

    private TextView tvInstructions;
    private Button btnShake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvInstructions = findViewById(R.id.tvInstructions);
        btnShake = findViewById(R.id.btnShake);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }


        btnShake.setOnClickListener(v -> showPredictionDialog());


        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float deltaX = Math.abs(lastX - x);
            float deltaY = Math.abs(lastY - y);
            float deltaZ = Math.abs(lastZ - z);


            if ((deltaX > SHAKE_THRESHOLD || deltaY > SHAKE_THRESHOLD || deltaZ > SHAKE_THRESHOLD) && !isDialogVisible) {
                showPredictionDialog();
            }

            lastX = x;
            lastY = y;
            lastZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void showPredictionDialog() {
        isDialogVisible = true;

        new Thread(() -> {

            int randomNumber = new Random().nextInt(30) + 1;
            String prediction = getPredictionText(randomNumber);


            new Handler(Looper.getMainLooper()).post(() -> {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("คำทำนาย")
                        .setMessage("หมายเลข: " + randomNumber + "\n" + prediction)
                        .setPositiveButton("ตกลง", (dialog, which) -> isDialogVisible = false)
                        .setOnDismissListener(dialog -> isDialogVisible = false)
                        .show();
            });
        }).start();
    }

    private String getPredictionText(int number) {

        switch (number) {
            case 1: return getString(R.string.prediction_1);
            case 2: return getString(R.string.prediction_2);
            case 3: return getString(R.string.prediction_3);
            case 4: return getString(R.string.prediction_4);
            case 5: return getString(R.string.prediction_5);
            case 6: return getString(R.string.prediction_6);
            case 7: return getString(R.string.prediction_7);
            case 8: return getString(R.string.prediction_8);
            case 9: return getString(R.string.prediction_9);
            case 10: return getString(R.string.prediction_10);
            case 11: return getString(R.string.prediction_11);
            case 12: return getString(R.string.prediction_12);
            case 13: return getString(R.string.prediction_13);
            case 14: return getString(R.string.prediction_14);
            case 15: return getString(R.string.prediction_15);
            case 16: return getString(R.string.prediction_16);
            case 17: return getString(R.string.prediction_17);
            case 18: return getString(R.string.prediction_18);
            case 19: return getString(R.string.prediction_19);
            case 20: return getString(R.string.prediction_20);
            case 21: return getString(R.string.prediction_21);
            case 22: return getString(R.string.prediction_22);
            case 23: return getString(R.string.prediction_23);
            case 24: return getString(R.string.prediction_24);
            case 25: return getString(R.string.prediction_25);
            case 26: return getString(R.string.prediction_26);
            case 27: return getString(R.string.prediction_27);
            case 28: return getString(R.string.prediction_28);
            case 29: return getString(R.string.prediction_29);
            case 30: return getString(R.string.prediction_30);
            default: return "";
        }
    }
}
