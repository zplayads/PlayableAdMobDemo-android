package com.zplay.playable.playableadmobdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;
import com.playableads.PlayPreloadingListener;
import com.playableads.PlayableAds;
import com.playableads.SimplePlayLoadingListener;


@SuppressWarnings("unused")
public class ZPLAYAdsAdMobInterstitialAdapter implements CustomEventInterstitial {
    private static final String TAG = "ZPLAYAdsAdMobAdapter";
    private String paAppId;
    private String paAdUnitId;
    private PlayableAds pAd;
    private CustomEventInterstitialListener mMediationInterstitialListener;

    @Override
    public void requestInterstitialAd(Context context, CustomEventInterstitialListener listener, String serverParameter, MediationAdRequest mediationAdRequest, Bundle customEventExtras) {
        try {
            Log.e(TAG, "requestInterstitialAd");
            resetIds(serverParameter);
            pAd = PlayableAds.init(context, paAppId);
            pAd.setAutoLoadAd(false);
            pAd.enableAutoRequestPermissions(true);
            mMediationInterstitialListener = listener;
            pAd.requestPlayableAds(paAdUnitId, new PlayPreloadingListener() {
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
        if (pAd.canPresentAd(paAdUnitId)) {
            mMediationInterstitialListener.onAdOpened();
            pAd.presentPlayableAD(paAdUnitId, new SimplePlayLoadingListener() {

                public void onAdsError(int var1, String var2) {
                    mMediationInterstitialListener.onAdFailedToLoad( 0);
                    Log.e(TAG, "onAdsError");
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
                    Log.e(TAG, "onLandingPageInstallBtnClicked");
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


    private void resetIds(@NonNull String parameters) {
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
