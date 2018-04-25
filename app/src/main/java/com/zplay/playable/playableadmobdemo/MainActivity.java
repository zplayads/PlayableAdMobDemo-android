package com.zplay.playable.playableadmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    Button btn_RewardedVideoAD, btn_InterstitialAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_RewardedVideoAD = findViewById(R.id.btn_RewardedVideoAD);
        btn_RewardedVideoAD.setOnClickListener(this);
        btn_InterstitialAD = findViewById(R.id.btn_InterstitialAD);
        btn_InterstitialAD.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btn_RewardedVideoAD) {
            intent.setClass(MainActivity.this, RewardedVideoActivity.class);
        }else{
            intent.setClass(MainActivity.this, InterstitialActivity.class);
        }
        startActivity(intent);
    }
}