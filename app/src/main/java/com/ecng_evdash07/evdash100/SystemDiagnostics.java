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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SystemDiagnostics extends AppCompatActivity {

    //Declaring Variables
    Button btnExport, btnLog;
    TextView tvBatteryVoltage, tvSumDistance, tvAvgEnergy, tvCoolTemp, tvAvgDistance;
    //Using an array list to save all my data
    ArrayList<VehicleMetrics> vehicleMetrics;

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

        vehicleMetrics = new ArrayList<VehicleMetrics>();


        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    loadMetrics();
                    FileOutputStream file = openFileOutput("ExportedData.csv", MODE_PRIVATE);
                    OutputStreamWriter outputFile = new OutputStreamWriter(file);
                    outputFile.write("Battery Voltage,Distance Travelled,Energy Used,Coolant Temperature\n");

                    for(int i = 0; i < vehicleMetrics.size();i++){
                        outputFile.write(vehicleMetrics.get(i).getBatteryVoltage()+","+vehicleMetrics.get(i).getDistance()+","+vehicleMetrics.get(i).getEnergy()+","+vehicleMetrics.get(i).getCoolantTemp()+"\n");

                    }
                    outputFile.flush();
                    outputFile.close();

                    Toast.makeText(SystemDiagnostics.this, "Exported Successfully",Toast.LENGTH_SHORT).show();

                }
                catch (IOException e){
                    Toast.makeText(SystemDiagnostics.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //whatever the button does
            }
        });
    }

    private void loadMetrics() {

        vehicleMetrics.clear();

        InputStream input = getResources().openRawResource(R.raw.data);
        String lineFF="";

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                while((lineFF = reader.readLine()) != null){
                    StringTokenizer tokens = new StringTokenizer(lineFF, ",");
                    VehicleMetrics vehicleMetric = new VehicleMetrics(tokens.nextToken(), tokens.nextToken(), tokens.nextToken(), tokens.nextToken());
                    vehicleMetrics.add(vehicleMetric);
                    Toast.makeText(SystemDiagnostics.this, "Loaded the data",Toast.LENGTH_SHORT).show();
                }

                reader.close();

            }
            catch (IOException e){
                Toast.makeText(SystemDiagnostics.this, e.getMessage(), Toast.LENGTH_SHORT ).show();
            }
    }
}
