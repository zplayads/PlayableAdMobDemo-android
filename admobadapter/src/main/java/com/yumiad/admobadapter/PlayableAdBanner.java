package com.yumiad.admobadapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;
import com.playableads.AtmosplayAdsBanner;
import com.playableads.BannerListener;
import com.playableads.PlayableAdsSettings;
import com.playableads.entity.BannerSize;
import com.playableads.presenter.widget.AtmosBannerView;

/**
 * Description:
 * <p>
 * Created by lgd on 2019-07-18.
 */
public class PlayableAdBanner implements CustomEventBanner {
    private static final String TAG = "PlayableAdBanner";

    AtmosplayAdsBanner mBanner;

    @Override
    public void requestBannerAd(Context context, final CustomEventBannerListener customEventBannerListener, String s, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle) {
        Log.d(TAG, "requestBannerAd");
        if (!(context instanceof Activity)) {
            Log.e(TAG, "requestInterstitialAd: PlayableAd needs Activity object to initialize sdk.");
            customEventBannerListener.onAdFailedToLoad(AdRequest.ERROR_CODE_INVALID_REQUEST);
            return;
        }

        PlayableUtil.PlayableParams params = new PlayableUtil.PlayableParams(s);

        Log.d(TAG, "requestReadPhoneState: " + params.requestReadPhoneState);
        PlayableAdsSettings.enableAutoRequestPermissions(params.requestReadPhoneState);
        Log.d(TAG, "gdprState: " + params.gdprState);
        PlayableUtil.setGDPRConsent(params.gdprState);

        mBanner = new AtmosplayAdsBanner(context, params.appId, params.unitId);
        mBanner.setBannerSize(calculateAdSize(adSize));
        mBanner.setBannerContainer(null);
        mBanner.setChannelId(params.channelId);
        mBanner.setBannerListener(new BannerListener() {
            @Override
            public void onBannerPrepared(AtmosBannerView view) {
                Log.d(TAG, "onBannerPrepared");
                customEventBannerListener.onAdLoaded(view);
            }

            @Override
            public void onBannerPreparedFailed(int code, String error) {
                Log.d(TAG, "onBannerPreparedFailed code: " + code + " ,errorMsg: " + error);
                customEventBannerListener.onAdFailedToLoad(code);
            }

            @Override
            public void onBannerClicked() {
                Log.d(TAG, "onBannerClicked");
                customEventBannerListener.onAdClicked();
            }
        });
        mBanner.loadAd();
    }

    private BannerSize calculateAdSize(AdSize adSize) {
        // Use the smallest AdSize that will properly contain the adView
        if (adSize == AdSize.BANNER) {
            return BannerSize.BANNER_320x50;
        } else if (adSize == AdSize.LARGE_BANNER) {
            return BannerSize.BANNER_728x90;
        }
        return BannerSize.BANNER_320x50;
    }

    @Override
    public void onDestroy() {
        mBanner.destroy();
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }
}