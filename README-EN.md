## 1 Integrate ZPLAY Ads SDK and AdMob SDK
Take Android Studio as an example, following are major steps. If you use other platform, please check ZPLAY Ads SDK Integration Guide and AdMob SDK Integration Guide to see details. 

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

a. Click "Apps" in the index, and then click "ADD APP" button. 
![image](imgs/018addapp1.png)

b. Choose whether your app has launched in Google Play or App Store, following takes "unlaunched" as an example. 
![image](imgs/018addapp2.png)

c. Enter your app information and platform, and click "ADD" to add app.
![image](imgs/019addapp3.png)

#### 2.2 Add New Ad Unit

a. Click "Apps" in the index, and choose the app you are going to add ad unit. 
![image](imgs/001chooseapp.png)

b. Click "ADD AD UNIT" button. 
![image](imgs/002addadunit1.png)

c. Choose an ad format. ZPLAY Ads supports Interstitial and Rewarded now. Here take Rewarded as an example. 
![image](imgs/003addadunit2RV1.png)

d. Enter ad unit name and set your ad unit, then click "CREATE AD UNIT" to add ad unit. 
![image](imgs/004addadunit2RV2.png)

e. Acquire app ID and ad unit ID of this ad unit. Click "DONE" to accomplish ad unit creation.
![image](imgs/005addadunit2RV3.png)

#### 2.3 Add ZPLAY Ads as a New Network on AdMob
 
 a. Choose "Mediation" in the index, and click "CREATE MEDIATION GROUP" button.
![image](imgs/007mediationgroupcreate.png)

 b. Choose ad format and platform. ZPLAY Ads supports Interstitial and Rewarded now. Here take Rewarded as an example. Click "CONTINUE" button to go on. 
![image](imgs/008mediationgroupcrate1.png)

c. Enter name of Mediation and set target locations through Location function. The Mediation will be enabled only when the Status controller has switched to "Enable". Click "ADD AD UNITS" to choose ad unit. 
![image](imgs/009mediationgroupcreat2.png)

d. Choose app and ad unit in selection box, and click "DONE" to save the ad unit you configured.
![image](imgs/011mediationgroupcreate4.png)

e. Click "ADD CUSTOM EVENT" to add custom ad sources.
![image](imgs/012mediationgroupcreate5.png)

f. Enter label of third party ad source, you can customize it according to your requirements. Here takes ZPLAY Ads as an example. Then you can set eCPM according to your requirements. 
![image](imgs/013mediationgroupcreate6.png)

g. Configure ZPLAY Ads ad source. Fill in adapter name in Class Name, which means replace ZPLAYAds (as following image shows) with full name of adapter name(**bundle name+class name**) . As for the example, the Rewarded's adapter name is ```com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobAdapter```, and the Interstitial's adapter name is ```com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobInterstitialAdapter```. Parameter contains two values, the first value is App ID which applied in ZPLAY Ads, and the second value is Ad Unit ID which applied in ZPLAY Ads. Please note that the sequence of two values should not be changed, and there is only one blank between two values. Click "DONE" to accomplish configuration. 

![image](imgs/014mediationgroupcreate7.png)


Note: You are available to use the following ID when testing(not charge). Please switch to the ID you applied in production mode.

|Ad Format|App_ID|Ad_Unit_id|
|---|---|---|
|Rewarded|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC|
|Interstitial|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|19393189-C4EB-3886-60B9-13B39407064E|

h. You can see ZPLAY Ads in Ad sources list. Click "SAVE" to accomplish configuration of Mediation. 
![image](imgs/015mediationgroupcreate8.png)

i. Check whether third party ad source has been added successfully. Find app and ad unit choosed in step d in app list. In the Mediation groups column, if the number of active increased, then the ad source has been added successfully.
![image](imgs/016mediationgroupcreate9.png)

## 3 About Adapter and Request, please refer to the DEMO. 