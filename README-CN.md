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


注：您在测试中可使用如下ID进行测试，测试ID不会产生收益，应用上线时请使用您申请的正式id。

|操作系统|广告形式|  App_ID  |  Ad_Unit_id|
|--------|---|----------|------------|
|Android |激励视频|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|3FBEFA05-3A8B-2122-24C7-A87D0BC9FEEC|
|Android|插屏|5C5419C7-A2DE-88BC-A311-C3E7A646F6AF|19393189-C4EB-3886-60B9-13B39407064E|


