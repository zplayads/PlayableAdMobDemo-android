package com.yumiad.admobadapter;

import android.text.TextUtils;
import android.util.Log;

import com.playableads.PlayableAdsSettings;
import com.playableads.entity.GDPRStatus;

import org.json.JSONException;
import org.json.JSONObject;

import static android.text.TextUtils.isEmpty;

public class ZPLAYAdsUtil {
    private static final String TAG = "ZPLAYAdsUtil";

    static void setGDPRConsent(String gdprConsentState) {
        if (TextUtils.equals("NON_PERSONALIZED", gdprConsentState)) {
            PlayableAdsSettings.setGDPRConsent(GDPRStatus.NON_PERSONALIZED);
        } else if (TextUtils.equals("PERSONALIZED", gdprConsentState)) {
            PlayableAdsSettings.setGDPRConsent(GDPRStatus.PERSONALIZED);
        }else{
            PlayableAdsSettings.setGDPRConsent(GDPRStatus.UNKNOWN);
        }

    }

    static class PlayableParams {
        String appId = "";
        String unitId = "";
        boolean autoLoad;
        String channelId = "";
        boolean requestReadPhoneState = false;
        String gdprState = "";

        PlayableParams(String json) {
            if (isEmpty(json)) {
                return;
            }

            try {
                JSONObject jo = new JSONObject(json);
                appId = getString(jo, "appId");
                unitId = getString(jo, "unitId");
                channelId = getString(jo, "channelId");
                autoLoad = getBoolean(jo, "autoLoad");
                requestReadPhoneState = getBoolean(jo, "requestReadPhoneState");
                gdprState = getString(jo, "gdprState");
            } catch (JSONException e) {
                Log.d(TAG, "YumiParams: parse error, ", e);
            }
        }

        private String getString(JSONObject jo, String key) {
            try {
                return jo.getString(key);
            } catch (Exception e) {
                Log.d(TAG, "YumiParams: parse error, ", e);
                return "";
            }
        }

        private boolean getBoolean(JSONObject jo, String key) {
            try {
                return jo.getBoolean(key);
            } catch (Exception e) {
                Log.d(TAG, "YumiParams: parse error, ", e);
                return false;
            }
        }

    }
}
