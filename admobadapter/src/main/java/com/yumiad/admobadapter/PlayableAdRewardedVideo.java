package com.yumiad.admobadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.playableads.PlayLoadingListener;
import com.playableads.PlayPreloadingListener;
import com.playableads.PlayableAds;
import com.playableads.PlayableAdsSettings;

/**
 * Description: PlayableAdRewardedVideo
 * Created by lgd on 2017/12/4.
 */

@SuppressWarnings("unused")
public class PlayableAdRewardedVideo implements MediationRewardedVideoAdAdapter {
    private static final String TAG = "PlayableAdRewardedVideo";
    private PlayableAds playableAds;
    private MediationRewardedVideoAdListener mRewardedVideoEventForwarder;
    private PlayableUtil.PlayableParams params;
    @Override
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String s, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle serverParameters, Bundle bundle1) {
        Log.d(TAG, "initialize");
        if (!(context instanceof Activity)) {
            Log.e(TAG, "requestInterstitialAd: PlayableAd needs Activity object to initialize sdk.");
            mediationRewardedVideoAdListener.onAdFailedToLoad(this, AdRequest.ERROR_CODE_INVALID_REQUEST);
            return;
        }

        params = new PlayableUtil.PlayableParams(serverParameters.getString(CUSTOM_EVENT_SERVER_PARAMETER_FIELD));

        Log.d(TAG, "requestReadPhoneState: " + params.requestReadPhoneState);
        PlayableAdsSettings.enableAutoRequestPermissions(params.requestReadPhoneState);
        Log.d(TAG, "gdprState: " + params.gdprState);
        PlayableUtil.setGDPRConsent(params.gdprState);

        playableAds = PlayableAds.init(context, params.appId);
        playableAds.setAutoLoadAd(params.autoLoad);
        playableAds.setChannelId(params.channelId);
        playableAds.enableAutoRequestPermissions(params.requestReadPhoneState);
        mRewardedVideoEventForwarder = mediationRewardedVideoAdListener;
        mRewardedVideoEventForwarder.onInitializationSucceeded(PlayableAdRewardedVideo.this);
    }

    @Override
    public void loadAd(MediationAdRequest mediationAdRequest, Bundle serverParameters, Bundle bundle1) {
        if (playableAds == null) {
            Log.e(TAG, "PlayableAd not initialized.");
            return;
        }
        loadAd();
    }

    private void loadAd() {
        Log.d(TAG, "requestRewardedVideoAd");
        playableAds.requestPlayableAds(params.unitId, new PlayPreloadingListener() {
            @Override
            public void onLoadFinished() {
                Log.d(TAG, "onLoadFinished");
                mRewardedVideoEventForwarder.onAdLoaded(PlayableAdRewardedVideo.this);
            }

            @Override
            public void onLoadFailed(int code, String errorMsg) {
                Log.e(TAG, "onLoadFailed code: " + code + ", errorMsg: " + errorMsg);
                mRewardedVideoEventForwarder.onAdFailedToLoad(PlayableAdRewardedVideo.this, 0);
            }
        });
    }

    @Override
    public void showVideo() {
        if (playableAds.canPresentAd(params.unitId)) {
            mRewardedVideoEventForwarder.onAdOpened(PlayableAdRewardedVideo.this);
            playableAds.presentPlayableAD(params.unitId, new PlayLoadingListener() {

                @Override
                public void onVideoStart() {
                    Log.d(TAG, "onVideoStart");
                    mRewardedVideoEventForwarder.onVideoStarted(PlayableAdRewardedVideo.this);
                }

                @Override
                public void onVideoFinished() {
                    Log.d(TAG, "onVideoFinished");
                    mRewardedVideoEventForwarder.onVideoCompleted(PlayableAdRewardedVideo.this);
                }

                @Override
                public void onLandingPageInstallBtnClicked() {
                    Log.d(TAG, "onLandingPageInstallBtnClicked");
                    mRewardedVideoEventForwarder.onAdClicked(PlayableAdRewardedVideo.this);
                }

                public void playableAdsIncentive() {
                    Log.d(TAG, "playableAdsIncentive");
                    mRewardedVideoEventForwarder.onRewarded(PlayableAdRewardedVideo.this, null);
                }

                @Override
                public void onAdClosed() {
                    Log.d(TAG, "onAdClosed");
                    mRewardedVideoEventForwarder.onAdClosed(PlayableAdRewardedVideo.this);
                }

                public void onAdsError(int var1, String var2) {
                    Log.e(TAG, "present onAdsError code: " + var1 + ", errorMsg: " + var2);
                    mRewardedVideoEventForwarder.onAdFailedToLoad(PlayableAdRewardedVideo.this, 0);
                }

            });
        }
    }

    @Override
    public boolean isInitialized() {
        return playableAds != null;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }
}
