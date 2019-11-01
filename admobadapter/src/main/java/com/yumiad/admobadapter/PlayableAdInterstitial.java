package com.yumiad.admobadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.playableads.PlayPreloadingListener;
import com.playableads.PlayableAdsSettings;
import com.playableads.PlayableInterstitial;
import com.playableads.SimplePlayLoadingListener;

public class PlayableAdInterstitial implements CustomEventInterstitial {
    private static final String TAG = "PlayableAdInterstitial";
    private PlayableInterstitial interstitial;
    private CustomEventInterstitialListener mMediationInterstitialListener;
    private PlayableUtil.PlayableParams params;

    @Override
    public void requestInterstitialAd(Context context, CustomEventInterstitialListener listener, String serverParameter, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        try {
            Log.e(TAG, "requestInterstitialAd");
            if (!(context instanceof Activity)) {
                Log.e(TAG, "requestInterstitialAd: PlayableAd needs Activity object to initialize sdk.");
                listener.onAdFailedToLoad(AdRequest.ERROR_CODE_INVALID_REQUEST);
                return;
            }
            params = new PlayableUtil.PlayableParams(serverParameter);

            Log.d(TAG, "requestReadPhoneState: " + params.requestReadPhoneState);
            PlayableAdsSettings.enableAutoRequestPermissions(params.requestReadPhoneState);
            Log.d(TAG, "gdprState: " + params.gdprState);
            PlayableUtil.setGDPRConsent(params.gdprState);

            interstitial = PlayableInterstitial.init(context, params.appId);
            interstitial.setAutoload(params.autoLoad);
            interstitial.setChannelId(params.channelId);
            mMediationInterstitialListener = listener;
            interstitial.requestPlayableAds(params.unitId, new PlayPreloadingListener() {
                @Override
                public void onLoadFinished() {
                    Log.d(TAG, "onLoadFinished");
                    mMediationInterstitialListener.onAdLoaded();
                }

                @Override
                public void onLoadFailed(int code, String errorMsg) {
                    Log.e(TAG, "onLoadFailed code: " + code + ", errorMsg: " + errorMsg);
                    mMediationInterstitialListener.onAdFailedToLoad(code);

                }
            });
        } catch (IllegalArgumentException e) {
            if (mMediationInterstitialListener != null) {
                mMediationInterstitialListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INVALID_REQUEST);
            }
        }
    }

    @Override
    public void showInterstitial() {
        if (interstitial.canPresentAd(params.unitId)) {
            mMediationInterstitialListener.onAdOpened();
            interstitial.presentPlayableAd(params.unitId, new SimplePlayLoadingListener() {

                @Override
                public void onAdsError(int var1, String var2) {
                    Log.e(TAG, "present onAdsError code: " + var1 + ", errorMsg" + var2);
                    mMediationInterstitialListener.onAdFailedToLoad(var1);
                }

                @Override
                public void onVideoStart() {
                    Log.d(TAG, "onVideoStart");
                }

                @Override
                public void onAdClosed() {
                    Log.d(TAG, "onAdClosed");
                    mMediationInterstitialListener.onAdClosed();
                }

                @Override
                public void onLandingPageInstallBtnClicked() {
                    Log.d(TAG, "onInstallBtnClicked");
                    mMediationInterstitialListener.onAdClicked();
                }

            });
        }
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
