package com.ecng_evdash07.evdash100;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Non_PremAds extends AppCompatActivity {

    AdView nAdview1,pAdview,qAdview,rAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non__prem_ads);
        MobileAds.initialize(this,"ca-app-pub-3940256099942544/6300978111");
        nAdview1=(AdView)findViewById(R.id.adView1);
        qAdview=(AdView)findViewById(R.id.adView2);
        rAdview=(AdView)findViewById(R.id.adView3);
        pAdview=(AdView)findViewById(R.id.adView4);

        AdRequest adRequest=new AdRequest.Builder().build();
        nAdview1.loadAd(adRequest);
        pAdview.loadAd(adRequest);
        qAdview.loadAd(adRequest);
        rAdview.loadAd(adRequest);


    }
}
