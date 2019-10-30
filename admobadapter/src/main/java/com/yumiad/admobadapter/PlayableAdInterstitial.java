package com.yumiad.admobadapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.playableads.PlayPreloadingListener;
import com.playableads.PlayableInterstitial;
import com.playableads.SimplePlayLoadingListener;

public class PlayableAdInterstitial implements CustomEventInterstitial {
    private static final String TAG = "PlayableAdRewardedVideo";
    private String paAppId;
    private String paAdUnitId;
    private PlayableInterstitial interstitial;
    private CustomEventInterstitialListener mMediationInterstitialListener;

    @Override
    public void requestInterstitialAd(Context context, CustomEventInterstitialListener listener, String serverParameter, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        try {
            Log.e(TAG, "requestInterstitialAd");
            resetIds(serverParameter);
            interstitial = PlayableInterstitial.init(context, paAppId);
            interstitial.setAutoload(false);
            mMediationInterstitialListener = listener;
            interstitial.requestPlayableAds(paAdUnitId, new PlayPreloadingListener() {
                @Override
                public void onLoadFinished() {
                    mMediationInterstitialListener.onAdLoaded();
                    Log.e(TAG, "onLoadFinished");
                }

                @Override
                public void onLoadFailed(int i, String s) {
                    mMediationInterstitialListener.onAdFailedToLoad(i);
                    Log.e(TAG, "onLoadFailed");
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
        if (interstitial.canPresentAd(paAdUnitId)) {
            mMediationInterstitialListener.onAdOpened();
            interstitial.presentPlayableAd(paAdUnitId, new SimplePlayLoadingListener() {

                @Override
                public void onAdsError(int var1, String var2) {
                    mMediationInterstitialListener.onAdFailedToLoad(var1);
                    Log.e(TAG, "onAdsError " + var1 + "  " + var2);
                }

                @Override
                public void onVideoStart() {
                    Log.e(TAG, "onVideoStart");
                }

                @Override
                public void onAdClosed() {
                    mMediationInterstitialListener.onAdClosed();
                    Log.e(TAG, "onAdClosed");
                }

                @Override
                public void onLandingPageInstallBtnClicked() {
                    mMediationInterstitialListener.onAdClicked();
                    Log.e(TAG, "onInstallBtnClicked");
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

    private void resetIds(String parameters) {
        if (parameters == null) {
            Log.e(TAG, "check parameter from AdMob web");
            return;
        }
        String[] ids = parameters.trim().split("\\s");
        if (ids.length != 2) {
            Log.e(TAG, "check parameter from AdMob web");
            return;
        }
        paAppId = ids[0];
        paAdUnitId = ids[1];
    }
}
