package com.ecng_evdash07.evdash100;

import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class SystemDiagnostics extends AppCompatActivity {

    public TextView iv_labelTextView, dt_labelTextView, m_labelTextView, iv_valueTextView, dt_valueTextView, m_valueTextView;
    public Button logBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_diagnostics);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        iv_labelTextView = (TextView) findViewById(R.id.iv_labelTextView);
        dt_labelTextView = (TextView) findViewById(R.id.iv_labelTextView);
        m_labelTextView = (TextView) findViewById(R.id.iv_labelTextView);
        iv_valueTextView = (TextView) findViewById(R.id.iv_valueTextView);
        dt_valueTextView = (TextView) findViewById(R.id.dt_valueTextView);
        m_valueTextView = (TextView) findViewById(R.id.m_valueTextView);
        logBtn = (Button) findViewById(R.id.logBtn);

        iv_valueTextView.setText(getvValue());
        dt_valueTextView.setText(getValue());
        m_valueTextView.setText(getValue());

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_valueTextView.setText("Logged");
                dt_valueTextView.setText("Logged");
                m_valueTextView.setText("Logged");
            }
        });

    }

    public String getvValue() {
        //This is where you enter the file and extract the data
        //This finds the directory of the sensor data
        File sensorData = Environment.getExternalStorageDirectory();
        //This stores that text file in a local variable called sensedData?
        File sensedData = new File(sensorData, "sensor_data.txt");

        try {
            FileOutputStream log = openFileOutput()

        }   catch (Exception e){
            //The exception to handle not finding the file is placed here
        }
    }
}
