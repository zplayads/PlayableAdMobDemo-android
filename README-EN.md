## 1 Integrate ZPLAY Ads SDK and AdMob SDK
Using Android Studio as an example, here are some major steps. If you use other IDE, please refer to ZPLAY Ads SDK Integration Guide and AdMob SDK Integration Guide.

### 1.1 Add ZPLAY Ads SDK Dependence

Add ZPLAY Ads dependence in app Module:

```
dependencies {
    compile 'com.playableads:playableads:2.2.1'
}
```

### 1.2 Add AdMob Ads SDK Dependence

a. Add maven repository in the build.gradle, which locates in the root directory:

```
maven {
    url "https://maven.google.com"
}
```
And make it look like follows:
```
allprojects {
    repositories {
        maven {
            url "https://maven.google.com"
        }
    }
}
```
b. Add Admob dependence in app Module
```
dependencies {
    compile 'com.google.android.gms:play-services-ads:15.0.1'
}
```

About integrating of AdMob, please refers to [Official Documentation](https://developers.google.com/admob/android/quick-start#import_the_mobile_ads_sdk)

## 2 Add ZPLAY Ads as a New Network on AdMob

#### 2.1 Add New App

a. Click "Apps" in the menu bar, and then click "ADD APP" button. 
![image](imgs/018addapp1.png)

b. Choose whether your app has published in Google Play or App Store, following takes "unpublished" as an example. 
![image](imgs/018addapp2.png)

c. Enter your app information and choose a platform, and click "ADD" to add an app.
![image](imgs/019addapp3.png)

#### 2.2 Add New Ad Unit

a. Click "Apps" in the menu bar, and choose the App which you want to add an Ad unit. 
![image](imgs/001chooseapp.png)

b. Click "ADD AD UNIT" button. 
![image](imgs/002addadunit1.png)

c. Choose an ad format. ZPLAY Ads supports Interstitial and Rewarded now. Using Rewarded as an example. 
![image](imgs/003addadunit2RV1.png)

d. Fill in the name of Ad unit, you can also make some advanced setting for this Ad unit, then click "CREATE AD UNIT" to add ad unit. 
![image](imgs/004addadunit2RV2.png)

e. Get app ID and ad unit ID of this ad unit. Click "DONE" to accomplish ad unit creation.
![image](imgs/005addadunit2RV3.png)

#### 2.3 Add ZPLAY Ads as a New Network on AdMob
 
a. Choose "Mediation" in the menu bar, and click "CREATE MEDIATION GROUP" button.
![image](imgs/007mediationgroupcreate.png)

b. Choose ad format and platform. ZPLAY Ads supports Interstitial and Rewarded video now. Here take Rewarded video as an example. Click "CONTINUE" button to go on. 
![image](imgs/008mediationgroupcrate1.png)

c. Fill in the name of Mediation and select locations to target. Mediation is only available when the status is Enable. Click "ADD AD UNITS" to choose ad unit. 
![image](imgs/009mediationgroupcreat2.png)

d. Choose the app and ad unit you want to configure in the selection box, and click "DONE" to complete configuration.
![image](imgs/011mediationgroupcreate4.png)

e. Click "ADD CUSTOM EVENT" to add custom ad sources.
![image](imgs/012mediationgroupcreate5.png)

f. Fill in the label of custom ad resource, using ZPLAYAds as an example, you can customize it and set floor price according to your needs.
![image](imgs/013mediationgroupcreate6.png)

g. Configure ZPLAY Ads ad source. Fill in adapter name in "Class Name (as the following image shows)", which means replace ZPLAYAds with adapter name (package name.class name). The Interstitial adapter name for ZPLAY Ads is com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobInterstitialAdapter, and Rewarded Video adapter name for ZPLAY Ads is com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobAdapter. The first value in "Parameter" should be the App ID you applied on ZPLAY Ads, and the second value in "Parameter" should be the Ad Unit ID you applied on ZPLAY Ads. Please note that the order of these two values cannot be changed, and there is only one space between these two values. Click "DONE" to complete configuration.

![image](imgs/014mediationgroupcreate7.png)


Note: You are available to use the following ID when testing(no charge). Please switch to the ID you applied in production mode.

|Ad Format|App_ID|Ad_Unit_id|
|---|---|---|
|Rewarded|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC|
|Interstitial|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|19393189-C4EB-3886-60B9-13B39407064E|

h. You can see ZPLAY Ads in Ad sources list. Click "SAVE" to complete ZPLAYAds configuration.
![image](imgs/015mediationgroupcreate8.png)

i. Check whether third-party ad source has been added successfully. In the Apps list, find the application and ad unit selected in step d. The active number in the "mediation groups" increased, indicating that the ad source is successfully configured.
![image](imgs/016mediationgroupcreate9.png)

## 3 About Adapter and Request, please refer to the DEMO. 