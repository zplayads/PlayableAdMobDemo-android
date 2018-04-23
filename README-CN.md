前提：中国大陆用户请注意，满足手机装有Google Play并且可访问Google官网（翻墙）后才能接入Admob广告，不然会一直提示网络错误。

## 一 接入ZPLAY Ads SDK和AdMob SDK
以Android Studio为例，其它平台请查看ZPLAY Ads接入文档及AdMob SDK接入文档，以下简要步骤
### 1. 添加ZPLAY Ads SDK依赖：
在app Module下添加
```
dependencies {
compile 'com.playableads:playableads:2.0.2'
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
#### 1. 添加新应用
a. 选择目录中Apps，点击“ADD APP”按钮
![img](imgs/018-add app1.png)

b. 选择您的应用是否已经上架Googleplay或AppStore，以下以未上架为例
![img](imgs/018-add app2.png)

c. 输入应用名称，选择应用操作系统，点击“ADD”保存添加的应用
![img](imgs/018-add app2.png)

#### 2. 添加新广告位
a. 目录中选择“Apps”，选择您要添加广告位的应用

![img](imgs/001-choose app.png)

b. 点击“ADD AD UNIT”按钮

![img](imgs/002-add ad unit 1.png)

c. 选择您所需要的广告形式，ZPLAY Ads目前支持Interstitial及Rewarded，此处以Rewarded为例

![img](imgs/003-add ad unit2 RV 1.png)

d. 输入广告位名称及对广告位进行设置，点击“CREAT AD UNIT”保存添加的广告位

![img](imgs/004-add ad unit2 RV2.png)

e. 获取此广告位的app ID及ad unit ID，点击“DONE”完成广告位的创建

![img](imgs/005-add ad unit2 RV3.png)

#### 3. 添加ZPLAY Ads广告源
a. 目录中选择“Mediation”，选择“CREATE MEDIATION GROUP”

![img](imgs/007-mediation group create.png)

b. 选择您要使用的广告形式及操作系统，ZPLAY Ads目前支持Interstitial及Rewarded，此处以Rewarded为例，点击“CONTINUE”进入下一步

![img](imgs/008-mediation group crate1.png)

c. 输入Mediation名字，通过Location进行地域设置，状态置位Enable时Mediation才可生效，点击“ADD AD UNIT”选择要添加的广告位

![img](imgs/009-mediation group creat2.png)

d. 在广告位选择框中，先后选择所需应用及广告位，点击“DONE”保存所配置的广告位

![img](imgs/011-mediation group creat4.png)

e. 点击“ADD CUSTOM EVENT”添加自定义广告源

![img](imgs/012-mediation group creat5.png)

f. 输入第三方广告源名称，此处以ZPLAY Ads为例，可根据需求进行自定义，根据需要对第三方广告源进行价格设置

![img](imgs/013-mediation group creat6.png)

g. 对ZPLAY Ads广告源进行配置，在Class Name处输入适配类名称（详情参考第三部分），Parameter中填入ZPLAY Ads平台参数（应用ID和广告位ID），参数间以空格隔开，点击“DONE”完成ZPLAY Ads的配置

![img](imgs/014-mediation group creat7.png)

注：您在测试中可使用如下ID进行测试，测试ID不会产生收益，应用上线时请使用您申请的正式id。

|操作系统|广告形式|  App_ID  |  Ad_Unit_id|
|--------|---|----------|------------|
|Android |激励视频|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC|
|Android|插屏|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|19393189-C4EB-3886-60B9-13B39407064E|

h. Ad source列表中可以看到所设置的广告源ZPLAY Ads，点击“SAVE”完成Mediation的配置

![img](imgs/015-mediation group creat8.png)

i. 检查第三方广告源是否添加完成，在Apps列表中找到步骤d中选择的应用及广告位，广告位Mediation groups中active数量增加表示广告源添加成功

![img](imgs/016-mediation group creat9.png)

## 三 适配类与请求请参考DEMO
