package com.zplay.playable.playableadmobdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private static final String TAG = "MainActivity";
    private static final String AD_MOB_APP_ID = "ca-app-pub-5451364651863658~6691926353";
    private static final String AD_MOB_AD_UNIT_ID = "ca-app-pub-5451364651863658/6193232902";

    private RewardedVideoAd mRewardedVideoAd;

    View mProgressBar;
    TextView mLogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, AD_MOB_APP_ID);

        mProgressBar = findViewById(R.id.loading_bar);
        mLogView = findViewById(R.id.log_text);

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
    }

    public void loadAd(View view) {
        mLogView.setText("");
        mProgressBar.setVisibility(View.VISIBLE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, 0);
        }
        AdRequest request = new AdRequest.Builder().build();
        mRewardedVideoAd.loadAd(AD_MOB_AD_UNIT_ID, request);
        addLog("start loading ad");
    }

    public void displayAd(View v) {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewarded(RewardItem reward) {
        addLog("onRewarded! currency: " + reward.getType() + "  amount: " +
                reward.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        addLog("onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        addLog("onRewardedVideoAdClosed");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        mProgressBar.setVisibility(View.GONE);
        addLog("onRewardedVideoAdFailedToLoad error code: " + errorCode);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        mProgressBar.setVisibility(View.GONE);
        addLog("onRewardedVideoAdLoaded");
    }

    @Override
    public void onRewardedVideoAdOpened() {
        addLog("onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoStarted() {
        addLog("onRewardedVideoStarted");
    }

    @Override
    public void onResume() {
        super.onResume();
        mRewardedVideoAd.resume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mRewardedVideoAd.pause(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRewardedVideoAd.destroy(this);
    }

    void addLog(String msg) {
        Log.d(TAG, "AdMobDemo=> " + msg);
        mLogView.append("\n" + msg);
    }
}