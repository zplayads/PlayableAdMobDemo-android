package com.zplay.playable.playableadmobdemo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialActivity extends Activity {

    private static final String TAG = "InterstitialActivity";
    private static final String AD_MOB_APP_ID = "ca-app-pub-9124866175748792~7390209240";
    private static final String AD_MOB_AD_UNIT_ID = "ca-app-pub-9124866175748792/1806710077";

    private InterstitialAd mInterstitialAd;
    private AdListener adListener;
    View mProgressBar;
    TextView mLogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mProgressBar = findViewById(R.id.loading_bar);
        mLogView = findViewById(R.id.log_text);
        createAdListener();
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(AD_MOB_AD_UNIT_ID);
            mInterstitialAd.setAdListener(adListener);
        }
    }


    public void loadAd(View view) {
        mLogView.setText("");
        mProgressBar.setVisibility(View.VISIBLE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 0);
        }
        AdRequest req = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(req);
        addLog("start loading ad");
    }

    public void displayAd(View v) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            addLog("ad no loaded");
        }
    }

    private void createAdListener() {
        adListener = new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                addLog("onAdClosed");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                addLog("onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                addLog("onAdLeftApplication");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mProgressBar.setVisibility(View.GONE);
                addLog("onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                mProgressBar.setVisibility(View.GONE);
                addLog("onAdFailedToLoad errorCode:"+errorCode);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void addLog(String msg) {
        Log.d(TAG, "AdMobDemo=> " + msg);
        mLogView.append("\n" + msg);
    }

}
