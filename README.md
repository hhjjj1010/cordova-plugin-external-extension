# cordova-plugin-externalExtension
### 前言：
1. 写这个插件的初衷是因为在项目中需要在我们的app中通过QQ号打开手机QQ并进入临时聊天界面，相当于一个QQ客服的功能。
2. 前前后后试过很多种方法，H5上直接打开QQ的URL为"mqq://im/chat?chat_type=wpa&uin="+qqNum+"&version=1&src_type=web"。直接使用a href=url只能把qq号写死才行，在js中用window.open(url)的方式，android和iOS10以下的设备没问题可以打开。iOS10的设备通过设置URL Schemes的方式
可以打开手机QQ，但此时iOS10以下的设备会报错打不开手机QQ。
3. 所以最后选择写插件的方式来实现这个功能，因为app内需要有跳转到App Store的功能，又扩展了一个使用手机默认浏览器打开URL的方法。
***
### 功能说明
1. openURL：使用手机默认浏览器打开URL，例如iOS上用Safari打开"https://www.baidu.com"；
2. openMobileQQChat：打开手机QQ聊天页面；
3. 打开APP Store（仅限iOS）：openURL("your app download url in APP Store")；
4. 当然也可用来打开android app的下载地址。

***
### 支持平台
1. android 
2. iOS

***
### 安装
###### 在线安装
cordova plugin add cordova-plugin-externalExtension

cordova plugin add https://github.com/hhjjj1010/cordova-plugin-externalExtension.git

###### 本地安装
下载插件到本地

cordova plugin add /your/local/path

***
### 使用 API
1. 打开手机QQ聊天页面
cordova.plugins.externalExtension.openMobileQQChat(qqNum);
2. 使用手机默认浏览器打开URL
cordova.plugins.externalExtension.openURL("https://www.baidu.com");
3. 打开App Store（仅限iOS）
cordova.plugins.externalExtension.openURL("your app downlaod url in App Store");
4. 打开android app的下载地址
cordova.plugins.externalExtension.openURL("your app downlaod url in android market");
