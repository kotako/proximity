package jp.ac.dendai.im.cps.proximitysample;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private Button button;
    private Sensor proximity;
    private SensorManager sensorManager;
    private long startTimeMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init view
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        // init proximity sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (list.size() > 0) {
            proximity = list.get(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        button.setVisibility(View.INVISIBLE);
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor != proximity) return;

        // かざし始めて2.5秒経過していたらボタンを表示
        if ((startTimeMillis != 0) && (System.currentTimeMillis() - startTimeMillis > 2500)) {
            button.setVisibility(View.VISIBLE);
        }

        // かざし始めた時間を計測
        if (sensorEvent.values[0] < 5) {
            if (startTimeMillis == 0) startTimeMillis = System.currentTimeMillis();
        } else {
            startTimeMillis = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
