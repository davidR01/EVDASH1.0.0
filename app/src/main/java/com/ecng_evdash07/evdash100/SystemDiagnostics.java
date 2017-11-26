package com.ecng_evdash07.evdash100;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SystemDiagnostics extends AppCompatActivity {

    //Declaring Variables
    Button btnExport, btnLog;
    TextView tvBatteryVoltage, tvSumDistance, tvAvgEnergy, tvCoolTemp, tvAvgDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_diagnostics);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Instantiating the buttons and textviews
        tvBatteryVoltage = (TextView) findViewById(R.id.tvBatteryVoltage);
        tvSumDistance = (TextView) findViewById(R.id.tvSumDistance);
        tvAvgEnergy = (TextView) findViewById(R.id.tvAvgEnergy);
        tvCoolTemp = (TextView) findViewById(R.id.tvCoolTemp);
        tvAvgDistance = (TextView) findViewById(R.id.tvAvgDistance);
        btnExport = (Button) findViewById(R.id.btnExport);
        btnLog = (Button) findViewById(R.id.btnLog);
    }

    btnExport.SetOnClickListener
    //whatever the button does
    public void btnlog (View view){
        //whatever the button does
    }
}