package com.yumiad.playable.admobsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;
import com.zplay.playable.playableadmobdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    static final String INTERSTITIAL_ID = "ca-app-pub-5451364651863658/6174288359";
    static final String BANNER_ID = "ca-app-pub-5451364651863658/3431927093";
    static final String VIDEO_ID = "ca-app-pub-5451364651863658/6193232902";
    private static final String APP_ID = "ca-app-pub-5451364651863658~6691926353";

    Button btn_RewardedVideoAD, btn_InterstitialAD, btn_BannerAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, APP_ID);

        btn_RewardedVideoAD = findViewById(R.id.btn_RewardedVideoAD);
        btn_RewardedVideoAD.setOnClickListener(this);
        btn_InterstitialAD = findViewById(R.id.btn_InterstitialAD);
        btn_InterstitialAD.setOnClickListener(this);
        btn_BannerAD = findViewById(R.id.btn_BannerAD);
        btn_BannerAD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btn_BannerAD) {
            intent.setClass(MainActivity.this, BannerActivity.class);
        } else if (v.getId() == R.id.btn_InterstitialAD) {
            intent.setClass(MainActivity.this, InterstitialActivity.class);
        } else if (v.getId() == R.id.btn_RewardedVideoAD) {
            intent.setClass(MainActivity.this, RewardedVideoActivity.class);
        }

        startActivity(intent);
    }
}