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
 * Description: ZPLAYAdsRewardedVideo
 * Created by lgd on 2017/12/4.
 */

@SuppressWarnings("unused")
public class ZPLAYAdsRewardedVideo implements MediationRewardedVideoAdAdapter {
    private static final String TAG = "ZPLAYAdsRewardedVideo";
    private PlayableAds playableAds;
    private MediationRewardedVideoAdListener mRewardedVideoEventForwarder;
    private ZPLAYAdsUtil.PlayableParams params;
    @Override
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String s, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle serverParameters, Bundle bundle1) {
        Log.d(TAG, "initialize");
        if (!(context instanceof Activity)) {
            Log.e(TAG, "requestInterstitialAd: PlayableAd needs Activity object to initialize sdk.");
            mediationRewardedVideoAdListener.onAdFailedToLoad(this, AdRequest.ERROR_CODE_INVALID_REQUEST);
            return;
        }

        params = new ZPLAYAdsUtil.PlayableParams(serverParameters.getString(CUSTOM_EVENT_SERVER_PARAMETER_FIELD));

        Log.d(TAG, "requestReadPhoneState: " + params.requestReadPhoneState);
        PlayableAdsSettings.enableAutoRequestPermissions(params.requestReadPhoneState);
        Log.d(TAG, "gdprState: " + params.gdprState);
        ZPLAYAdsUtil.setGDPRConsent(params.gdprState);

        playableAds = PlayableAds.init(context, params.appId);
        playableAds.setAutoLoadAd(params.autoLoad);
        playableAds.setChannelId(params.channelId);
        playableAds.enableAutoRequestPermissions(params.requestReadPhoneState);
        mRewardedVideoEventForwarder = mediationRewardedVideoAdListener;
        mRewardedVideoEventForwarder.onInitializationSucceeded(ZPLAYAdsRewardedVideo.this);
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
                mRewardedVideoEventForwarder.onAdLoaded(ZPLAYAdsRewardedVideo.this);
            }

            @Override
            public void onLoadFailed(int code, String errorMsg) {
                Log.e(TAG, "onLoadFailed code: " + code + ", errorMsg: " + errorMsg);
                mRewardedVideoEventForwarder.onAdFailedToLoad(ZPLAYAdsRewardedVideo.this, 0);
            }
        });
    }

    @Override
    public void showVideo() {
        if (playableAds.canPresentAd(params.unitId)) {
            mRewardedVideoEventForwarder.onAdOpened(ZPLAYAdsRewardedVideo.this);
            playableAds.presentPlayableAD(params.unitId, new PlayLoadingListener() {

                @Override
                public void onVideoStart() {
                    Log.d(TAG, "onVideoStart");
                    mRewardedVideoEventForwarder.onVideoStarted(ZPLAYAdsRewardedVideo.this);
                }

                @Override
                public void onVideoFinished() {
                    Log.d(TAG, "onVideoFinished");
                    mRewardedVideoEventForwarder.onVideoCompleted(ZPLAYAdsRewardedVideo.this);
                }

                @Override
                public void onLandingPageInstallBtnClicked() {
                    Log.d(TAG, "onLandingPageInstallBtnClicked");
                    mRewardedVideoEventForwarder.onAdClicked(ZPLAYAdsRewardedVideo.this);
                }

                public void playableAdsIncentive() {
                    Log.d(TAG, "playableAdsIncentive");
                    mRewardedVideoEventForwarder.onRewarded(ZPLAYAdsRewardedVideo.this, null);
                }

                @Override
                public void onAdClosed() {
                    Log.d(TAG, "onAdClosed");
                    mRewardedVideoEventForwarder.onAdClosed(ZPLAYAdsRewardedVideo.this);
                }

                public void onAdsError(int var1, String var2) {
                    Log.e(TAG, "present onAdsError code: " + var1 + ", errorMsg: " + var2);
                    mRewardedVideoEventForwarder.onAdFailedToLoad(ZPLAYAdsRewardedVideo.this, 0);
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
