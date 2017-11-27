package com.ecng_evdash07.evdash100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TSOInterface extends AppCompatActivity {

    public Button registerBtn, ctrlAdBtn, sysDiagnosticBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tsointerface);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        sysDiagnosticBtn = (Button) findViewById(R.id.sysDiagnosticBtn);

        //Details Register button functionality
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerScrn = new Intent(TSOInterface.this, Registration.class);
                startActivity(registerScrn);
            }
        });

        //Details System Diagnostic button functionality
        sysDiagnosticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sysDiagScrn = new Intent(TSOInterface.this, SystemDiagnostics.class);
                startActivity(sysDiagScrn);
            }
        });

    }
}
