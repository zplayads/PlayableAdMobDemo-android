- [1 接入ZPLAY Ads SDK和AdMob SDK](#1-%E6%8E%A5%E5%85%A5zplay-ads-sdk%E5%92%8Cadmob-sdk)
    - [1.1 添加ZPLAY Ads SDK依赖：](#11-%E6%B7%BB%E5%8A%A0zplay-ads-sdk%E4%BE%9D%E8%B5%96)
    - [1.2 添加AdMob广告SDK依赖](#12-%E6%B7%BB%E5%8A%A0admob%E5%B9%BF%E5%91%8Asdk%E4%BE%9D%E8%B5%96)
    - [1.3 将可玩Adapter导入到工程中](#13-%E5%B0%86%E5%8F%AF%E7%8E%A9adapter%E5%AF%BC%E5%85%A5%E5%88%B0%E5%B7%A5%E7%A8%8B%E4%B8%AD)
- [2 在AdMob平台添加ZPLAY Ads广告源](#2-%E5%9C%A8admob%E5%B9%B3%E5%8F%B0%E6%B7%BB%E5%8A%A0zplay-ads%E5%B9%BF%E5%91%8A%E6%BA%90)
        - [2.1 添加新应用](#21-%E6%B7%BB%E5%8A%A0%E6%96%B0%E5%BA%94%E7%94%A8)
        - [2.2 添加新广告位](#22-%E6%B7%BB%E5%8A%A0%E6%96%B0%E5%B9%BF%E5%91%8A%E4%BD%8D)
        - [2.3 添加ZPLAY Ads广告源](#23-%E6%B7%BB%E5%8A%A0zplay-ads%E5%B9%BF%E5%91%8A%E6%BA%90)

## 1 接入ZPLAY Ads SDK和AdMob SDK
以Android Studio为例，接入AdMob请查看[AdMob SDK接入文档](https://developers.google.com/admob/android/quick-start)，以下简要步骤
### 1.1 添加ZPLAY Ads SDK依赖：
在app Module下添加
```
dependencies {
    compile 'com.playableads:playableads:2.2.1'
}
```
### 1.2 添加AdMob广告SDK依赖
a. 在project构建文件(gradle)中的allprojects.repositories结点添加以下代码
```
maven {
    url "https://maven.google.com"
}
```
使其看起来像：
```
allprojects {
    repositories {
        maven {
            url "https://maven.google.com"
        }
    }
}
```
b. 在app Module下添加（可选）
```
dependencies {
    compile 'com.google.android.gms:play-services-ads:15.0.1'
}
```

### 1.3 将可玩Adapter导入到工程中
激励视频Adapter：[ZPLAYAdsAdMobAdapter.java](./app/src/main/java/com/zplay/playable/playableadmobdemo/ZPLAYAdsAdMobAdapter.java)

插屏广告Adapter：[ZPLAYAdsAdMobInterstitialAdapter.java](./app/src/main/java/com/zplay/playable/playableadmobdemo/ZPLAYAdsAdMobInterstitialAdapter.java)

确保导入相关的类之后不出现错误，记录该文件类名，例如激励视频在示例中的类名为：com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobAdapter，在Admob平台配置ZPLAYAds信息时需要使用到该类名。

> 关于适配类和请求的详细内容，请参考DEMO中的代码。

## 2 在[AdMob平台](https://apps.admob.com/v2/home)添加ZPLAY Ads广告源
#### 2.1 添加新应用
a. 选择目录中Apps，点击“ADD APP”按钮
![image](imgs/018addapp1.png)

b. 选择您的应用是否已经上架Googleplay或AppStore，如果已上架，选择“是”，如果未上架，选择“否”，以下以未上架为例
![image](imgs/018addapp2.png)

c. 输入应用名称，选择应用操作系统，点击“ADD”保存添加的应用
![image](imgs/019addapp3.png)

#### 2.2 添加新广告位
a. 添加应用后，点击“NEXT: CREATE AD UNIT”按钮创建广告位。
![image](imgs/addunit.jpg)

b. 选择您所需要的广告形式，ZPLAY Ads目前支持Interstitial及Rewarded，此处以Rewarded为例
![image](imgs/003addadunit2RV1.png)

c. 输入广告位名称并点击“CREATE AD UNIT”保存添加的广告位

![image](imgs/004addadunit2RV2.png)

d. 获取此广告位的app ID及ad unit ID，点击“DONE”完成广告位的创建

![image](imgs/005addadunit2RV3.png)

#### 2.3 添加ZPLAY Ads广告源
a. 目录中选择“[Mediation](https://apps.admob.com/v2/mediation/groups/list)”，选择“[CREATE MEDIATION GROUP](https://apps.admob.com/v2/mediation/groups/create)”

![image](imgs/007mediationgroupcreate.png)

b. 选择您要使用的广告形式及操作系统，ZPLAY Ads目前支持Interstitial及Rewarded video，此处以Rewarded video为例，点击“CONTINUE”进入下一步

![image](imgs/008mediationgroupcreate1.png)

c. 输入Mediation名字，通过Location进行地域设置，状态置位Enable时Mediation才可生效，请保证状态选择了Enable。点击“ADD AD UNIT”选择要添加的广告位

![image](imgs/009mediationgroupcreat2.png)

d. 在广告位选择框中，先后选择所需应用及广告位，点击“DONE”保存所配置的广告位

![image](imgs/011mediationgroupcreate4.png)

e. 点击“ADD CUSTOM EVENT”添加自定义广告源

![image](imgs/012mediationgroupcreate5.png)

f. 输入第三方广告源名称，此处以ZPLAYAds为例，可根据需求进行自定义，根据需要对第三方广告源进行价格设置

![image](imgs/013mediationgroupcreate6.png)

g. 对ZPLAY Ads广告源进行配置。在Class Name处（如下图所示，图中的class name仅为示例，请填写下述适配类名称）ZPLAY Ads插屏适配器为com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobInterstitialAdapter，ZPLAY Ads激励视频适配器为com.zplay.playable.playableadmobdemo.ZPLAYAdsAdMobAdapter。Parameter中第一个值需填写ZPLAY Ads平台申请的[应用ID](https://sellers.zplayads.com/#/app/appList/)，第二个值须填写ZPLAY Ads平台申请的[广告位ID](https://sellers.zplayads.com/#/ad/placeList/)，注意这两个值的顺序不能更改，且两参数间只有一个空格，点击“DONE”完成ZPLAY Ads的配置

![image](imgs/014mediationgroupcreate7.png)

注：您在测试中可使用如下ID进行测试，测试ID不会产生收益，应用上线时请使用您申请的正式id。

| 广告形式 | App_ID                               | Ad_Unit_id                           |
| -------- | ------------------------------------ | ------------------------------------ |
| 激励视频 | 5C5419C7-A2DE-88BC-A311-C3E7A646F6AF | 3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC |
| 插屏广告 | 5C5419C7-A2DE-88BC-A311-C3E7A646F6AF | 19393189-C4EB-3886-60B9-13B39407064E |

h. Ad source列表中可以看到所设置的广告源ZPLAY Ads，点击“SAVE”完成Mediation的配置

![image](imgs/015mediationgroupcreate8.png)

i. 检查第三方广告源是否添加完成，在[Apps列表](https://apps.admob.com/v2/apps/list)中找到步骤d中选择的应用及广告位，广告位Mediation groups中active数量增加表示广告源添加成功

![image](imgs/016mediationgroupcreate9.png)
