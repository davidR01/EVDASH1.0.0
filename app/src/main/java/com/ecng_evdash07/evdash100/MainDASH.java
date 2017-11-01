package com.ecng_evdash07.evdash100;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainDASH extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.DiagnosticButton);

        Intent i = new Intent(MainDASH.this, loginTSO.class);
        startActivity(i);
    }






}
