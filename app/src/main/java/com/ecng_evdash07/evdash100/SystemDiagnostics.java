package com.ecng_evdash07.evdash100;

import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SystemDiagnostics extends AppCompatActivity {

    private List<SensorSample> sensorSamples = new ArrayList<>();
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

        //iv_valueTextView.setText(getvValue());
        //dt_valueTextView.setText(getValue());
        //m_valueTextView.setText(getValue());

        //This defines what the button click of log does
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_valueTextView.setText((CharSequence) sensorSamples.get(1));
                dt_valueTextView.setText((CharSequence) sensorSamples.get(2));
                m_valueTextView.setText((CharSequence) sensorSamples.get(3));
            }
        });
        
        readSensorData();

    }

    private void readSensorData() {
        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            //Steps over the headers
            reader.readLine();

            while ((line = reader.readLine()) != null){
                //Splits data by commas
                String[] token = line.split(",");

                //Reads the data in
                SensorSample sample = new SensorSample();

                if(token[0].length() > 0){
                    sample.setVoltage(Float.parseFloat(token[0]));
                }else{
                    sample.setVoltage(0);
                }
                if(token[1].length() >  0) {
                    sample.setDistance(Float.parseFloat(token[1]));
                }else{
                    sample.setDistance(0);
                }
                if(token.length >= 3 && token[2].length() > 0) {
                    sample.setMilage(Float.parseFloat(token[2]));
                }else{
                    sample.setMilage(0);
                }
                sensorSamples.add(sample);

                Log.d("SystemDiagonics","Jusy Created "+ sample);
            }
        } catch (IOException e) {
            Log.wtf("SystemDiagnostic","Error reading Data File on line " + line, e );
            e.printStackTrace();
        }
    }
}