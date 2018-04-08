前提：中国大陆用户请注意，满足手机装有Google Play并且可访问Google官网（翻墙）后才能接入Admob广告，不然可能会提示网络错误。

## 一 接入ZPLAY Ads SDK和AdMob SDK
以Android Studio为例，其它平台请查看ZPLAY Ads接入文档及AdMob SDK接入文档，以下简要步骤
### 1. 添加ZPLAY Ads SDK依赖：
在app Module下添加
```
dependencies {
    compile 'com.playableads:playableads:2.0.3'
}
```
### 2. 添加AdMob广告SDK依赖
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
b. 在app Module下添加
```
dependencies {
compile 'com.google.android.gms:play-services-ads:11.6.2'
}
```
## 二 在AdMob平台添加ZPLAY Ads广告源
### 1: 添加新应用且添加广告位，使其运行ZPLAY Ads广告
a. 添加应用，如图：
![img](imgs/image01.png)
b. 选择应该是否已经上架到Google Play或App Store，根据实际情况选择即可。这里选择“NO”, 后期上线后可再回来绑定
![img](imgs/image02.png)
c. 设置应用名以及平台，此处以PlayableAdMobDemo应用及Android平台为例，填好后点击“ADD”继续
![img](imgs/image03.png)
d. 创建完成，此时已经拥有应用ID，点击“NEXT: CREATE AD UNIT”创建广告位
![img](imgs/image04.png)
e. 创建广告位时，选择广告形式，此处以“激励视频”为例，选择SELECT
![img](imgs/image05.png)
f. 根据需求填写广告位信息，如这里只是示例，只填写了广告位名称，其它为默认。点击“CREATE AD UNIT”创建完成
![img](imgs/image06.png)
g. 创建完成，记录下新创建的Admob应用ID和广告位ID以备后用。
![img](imgs/image07.png)
### 2 添加ZPLAY Ads广告源
a. 在Mediation目录下点击“CREATE MEDIATION GROUP”
![img](imgs/image08.png)
b. 设置广告源信息，此处以Rewarded video为例，Ad format为Rewarded video，Platform设置为Android，点击“CONTINUE”
![img](imgs/image09.png)
c. 设置广告源方便管理，这里设置为Playable-Advertising-android（广告源名称可按需求设定，如ZPLAY Ads），点击"ADD AD UNITS"向该源添加广告位
![img](imgs/image10.png)
d. 选择步骤2中创建的广告位，点击“DONE”完成添加
![img](imgs/image11.png)
c. 添加适配类信息，以便传递ZPLAY Ads应用ID和广告位ID
![img](imgs/image12.png)
d. 添加广告源名称，此处以Playable Network为例，可根据需求自定义
![img](imgs/image13.png)
e. 添加适配类以及ZPLAY Ads应用ID（如5C5419C7-A2DE-88BC-A311-C3E7A646F6AF）和广告位ID（如3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC），适配类在目录三中介绍。
![img](imgs/image14.png)

注：您在测试中可使用如下ID进行测试，测试ID不会产生收益，应用上线时请使用您申请的正式id。

|操作系统|广告形式|  App_ID  |  Ad_Unit_id|
|--------|---|----------|------------|
|Android |激励视频|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC|
|Android|插屏|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|19393189-C4EB-3886-60B9-13B39407064E|

f. 配置完成，可看到图示1位置的广告位id已经支持图示2中的两个广告源，点击“SAVE”保存，完成广告源添加
![img](imgs/image15.png)
### 3 已有应用，修改现有广告位，使其支持ZPLAY Ads
a. 注意此处，OldRewardedUnit广告位默认有0个三方广告源
![img](imgs/image16.png)
b. 进入ZPLAY Ads广告源
![img](imgs/image17.png)
c. 点击“ADD AD UNITS”将广告位添加ZPLAY Ads广告源
![img](imgs/image18.png)
d. 选择已有的激励视频广告位，点击“CONTINUE”，进行适配类设置
![img](imgs/image19.png)
e. 设置适配类，根据实际情况进行填写，与步骤2-e类似

f. 配制完成如图所示
![img](imgs/image21.png)
g. 此时，OldRewardedUnit的三方广告源为1，广告源添加完成
![img](imgs/image22.png)
## 三 [适配类](./app/src/main/java/com/zplay/playable/playableadmobdemo/MainActivity.java)与请求请参考[DEMO](https://github.com/zplayads/PlayableAdMobDemo-android)
