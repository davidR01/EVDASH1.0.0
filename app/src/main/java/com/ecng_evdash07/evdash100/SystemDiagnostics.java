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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

        //This sets the phones orientation to landscape
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Instantiating the buttons and text Views
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
                try{
                    //Creates a file, or looks for one then appends data to the end of the file
                    FileOutputStream file = openFileOutput("LoggedData.txt", MODE_APPEND);
                    OutputStreamWriter outputFile = new OutputStreamWriter(file);

                    //Creates a time stamp for the logged data
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                    outputFile.write("\n Data Log of: "+timeStamp+ "\n"+ analyzeMetrics());
                    outputFile.flush();
                    outputFile.close();

                    Toast.makeText(SystemDiagnostics.this, "Created a log File extension", Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(SystemDiagnostics.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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
                }
                Toast.makeText(SystemDiagnostics.this, "Loaded the data",Toast.LENGTH_SHORT).show();

                reader.close();

            }
            catch (IOException e){
                Toast.makeText(SystemDiagnostics.this, e.getMessage(), Toast.LENGTH_SHORT ).show();
            }
    }

    private String analyzeMetrics(){

        double metricHandlerVoltage = 0.0, metricHandlerDistance = 0.0, metricHandlerEnergy = 0.0, metricHandlerCoolantTemp = 0.0;
        double count = 0.0;

        for(int i = 0; i < vehicleMetrics.size(); i++){

            metricHandlerVoltage = metricHandlerVoltage + Double.parseDouble(vehicleMetrics.get(i).getBatteryVoltage());
            metricHandlerDistance = metricHandlerDistance + Double.parseDouble(vehicleMetrics.get(i).getDistance());
            metricHandlerEnergy = metricHandlerEnergy + Double.parseDouble(vehicleMetrics.get(i).getEnergy());
            metricHandlerCoolantTemp = metricHandlerCoolantTemp + Double.parseDouble(vehicleMetrics.get(i).getCoolantTemp());
            count++;
        }

        double avgDistance = metricHandlerDistance / count;
        double avgVoltage = metricHandlerVoltage/count;
        double avgEnergy = metricHandlerEnergy/count;
        double avgCoolantTemp = metricHandlerCoolantTemp/count;
        String metric = ("Average Voltage: " + String.valueOf(avgVoltage) + "\n"+ "Total Distance: " + String.valueOf(metricHandlerDistance)
                + "\n" + "Average Distance: " + String.valueOf(avgDistance) + "\n" + "Average Energy: " + String.valueOf(avgEnergy)
                + "\n" + "Average Coolant Temperature: " + String.valueOf(avgCoolantTemp)
        );

        return metric;
    }
}
