package com.yumiad.playable.admobsample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.zplay.playable.playableadmobdemo.R;

import static com.yumiad.playable.admobsample.MainActivity.VIDEO_ID;

public class RewardedVideoActivity extends Activity implements RewardedVideoAdListener {
    private static final String TAG = "MainActivity";

    View mProgressBar;
    TextView mLogView;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded_video);

        mProgressBar = findViewById(R.id.loading_bar);
        mLogView = findViewById(R.id.log_text);

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
    }

    public void loadAd(View view) {
        mLogView.setText("");
        mProgressBar.setVisibility(View.VISIBLE);
        AdRequest request = new AdRequest.Builder().build();
        mRewardedVideoAd.loadAd(VIDEO_ID, request);
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
    public void onRewardedVideoCompleted() {
        addLog("onRewardedVideoCompleted");
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
