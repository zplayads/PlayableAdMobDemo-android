package com.zplay.playable.playableadmobdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.playableads.PlayPreloadingListener;
import com.playableads.PlayableAds;
import com.playableads.SimplePlayLoadingListener;
import com.playableads.constants.StatusCode;

/**
 * Description: 不要修改
 * Created by lgd on 2017/12/4.
 */

@SuppressWarnings("unused")
public class PlayableAdMobAdapter implements MediationRewardedVideoAdAdapter {
    private static final String TAG = "PlayableAdMobAdapter";
    private String paAppId;
    private String paAdUnitId;
    private PlayableAds pAd;
    private MediationRewardedVideoAdListener mRewardedVideoEventForwarder;

    @Override
    public void initialize(Context context, MediationAdRequest mediationAdRequest, String s, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle serverParameters, Bundle bundle1) {
        resetIds(serverParameters);
        pAd = PlayableAds.init(context, paAppId);
        pAd.setAutoLoadAd(false);
        mRewardedVideoEventForwarder = mediationRewardedVideoAdListener;
        loadAd();
    }

    @Override
    public void loadAd(MediationAdRequest mediationAdRequest, Bundle serverParameters, Bundle bundle1) {
        resetIds(serverParameters);
        loadAd();
    }

    private void loadAd() {
        pAd.requestPlayableAds(paAdUnitId, new PlayPreloadingListener() {
            @Override
            public void onLoadFinished() {
                mRewardedVideoEventForwarder.onAdLoaded(PlayableAdMobAdapter.this);
            }

            @Override
            public void onLoadFailed(int i, String s) {
                if (i == StatusCode.PRELOAD_FILLED.code) {
                    mRewardedVideoEventForwarder.onAdLoaded(PlayableAdMobAdapter.this);
                } else {
                    mRewardedVideoEventForwarder.onAdFailedToLoad(PlayableAdMobAdapter.this, 0);
                }
            }
        });
    }

    @Override
    public void showVideo() {
        if (pAd.canPresentAd(paAdUnitId)) {
            pAd.presentPlayableAD(paAdUnitId, new SimplePlayLoadingListener() {
                public void playableAdsIncentive() {
                    mRewardedVideoEventForwarder.onRewarded(PlayableAdMobAdapter.this, null);
                }

                public void onAdsError(int var1, String var2) {
                    Log.d(TAG, "presentPlayableAD error code: " + var1 + ", " + var2);
                    mRewardedVideoEventForwarder.onAdFailedToLoad(PlayableAdMobAdapter.this, 0);
                }
            });
        }
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
        Log.d(TAG, paAppId + ":" + paAdUnitId);
    }

    @Override
    public boolean isInitialized() {
        return pAd != null;
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