package com.zplay.playable.playableadmobdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.playableads.PlayPreloadingListener;
import com.playableads.PlayableAds;
import com.playableads.SimplePlayLoadingListener;


@SuppressWarnings("unused")
public class ZPLAYAdsAdMobInterstitialAdapter implements MediationInterstitialAdapter {
    private static final String TAG = "ZPLAYAdsAdMobAdapter";
    private String paAppId;
    private String paAdUnitId;
    private PlayableAds pAd;
    private MediationInterstitialListener mMediationInterstitialListener;

    @Override
    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle serverParameters, MediationAdRequest mediationAdRequest, Bundle bundle1) {
        try {
            resetIds(serverParameters);
            pAd = PlayableAds.init(context, paAppId);
            pAd.setAutoLoadAd(false);
            pAd.enableAutoRequestPermissions(true);
            mMediationInterstitialListener = mediationInterstitialListener;
            pAd.requestPlayableAds(paAdUnitId, new PlayPreloadingListener() {
                @Override
                public void onLoadFinished() {
                    mMediationInterstitialListener.onAdLoaded(ZPLAYAdsAdMobInterstitialAdapter.this);
                }

                @Override
                public void onLoadFailed(int i, String s) {
                    mMediationInterstitialListener.onAdFailedToLoad(ZPLAYAdsAdMobInterstitialAdapter.this, 0);
                }
            });
        } catch (IllegalArgumentException e) {
            if (mediationInterstitialListener != null) {
                mediationInterstitialListener
                        .onAdFailedToLoad(ZPLAYAdsAdMobInterstitialAdapter.this,
                                AdRequest.ERROR_CODE_INVALID_REQUEST);
            }
        }
    }

    @Override
    public void showInterstitial() {
        if (pAd.canPresentAd(paAdUnitId)) {
            mMediationInterstitialListener.onAdOpened(ZPLAYAdsAdMobInterstitialAdapter.this);
            pAd.presentPlayableAD(paAdUnitId, new SimplePlayLoadingListener() {
                public void playableAdsIncentive() {
                }

                public void onAdsError(int var1, String var2) {
                    mMediationInterstitialListener.onAdFailedToLoad(ZPLAYAdsAdMobInterstitialAdapter.this, 0);
                }

                @Override
                public void onVideoStart() {
                    mMediationInterstitialListener.onAdOpened(ZPLAYAdsAdMobInterstitialAdapter.this);
                }

                @Override
                public void onAdClosed() {
                    mMediationInterstitialListener.onAdClosed(ZPLAYAdsAdMobInterstitialAdapter.this);
                }

                @Override
                public void onLandingPageInstallBtnClicked() {
                    mMediationInterstitialListener.onAdClicked(ZPLAYAdsAdMobInterstitialAdapter.this);
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


    private void resetIds(@NonNull Bundle param) {
        String parameters = param.getString("parameter");
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
