# AB Dial

This is an application to control your pc as a radial controler (like Surface Dial) or keyboard using the Bluetooth HID Device profile in Android 9(Pie) & above devices. App is currently in development. 

It is forked from Kontroller(https://github.com/raghavk92/Kontroller) , an application to control your pc,mac,tv,ipad etc as a mouse or keyboard using the Bluetooth HID Device profile in Android 9(Pie) & above devices. App is currently in development. 

Get it from Coolapk:
<a href='https://www.coolapk.com/apk/266123'><img alt='Get it from Coolapk' src='https://static.coolapk.com/static/web/v8/img/icon.png'/></a>

Procedure to use the app:
1) Remove previous pairings with the host device in bluetooth settings(This has to be done once)
2) Open the app
3) Send a pairing request from the host device to the controlling device
4) Accept the pairing on the device running Kontroller

Many device manufacturers have disabled the Bluetooth HID device profile on their devices. You would have to ask your device manufacturers to enable it. You can check with this app {[Bluetooth HID Device Profile Compatibility Checker](https://play.google.com/store/apps/details?id=com.rkaneapplabs.bluetooth_hid.bluetoothproxy)} if the Bluetooth HID device profile is disabled for you or not.

For OnePlus users- you can upvote this [Idea](https://forums.oneplus.com/threads/converting-one-plus-devices-into-a-bluetooth-controller-mouse-keyboard-etc.1192272/) in the community forums after login(This may help in bringing the issue into notice to One Plus)  


# 中文说明
这是一个把Android手机虚拟为蓝牙外设的工具。眼馋surface dial好久了，现在终于手搓出一个虚拟设备！
这是从 Kontroller(https://github.com/raghavk92/Kontroller) fork而来，增加了radial controler的功能并修改了包名。

### 使用前提：
1) 手机系统Android9以上。（硬性要求）
2) 电脑需要运行Windows 10 周年更新或更高版本并且具有蓝牙 4.0 LE。（这是微软官网要求，别问我周年更新是啥，我也不懂。）蓝牙4.0实际上是非必须的，我的笔记本的蓝牙网卡坏掉了，插了一个2.1的适配器，也能正常工作

### 使用方法：

1) 从电脑上删除手机和电脑的蓝牙配对
2) 在手机上打开APP
3) 在选项中为auto pair打勾
4) 用电脑搜索手机并配对。在APP的顶部弹出配对请求时，点击同意（很重要，如果顶部没有弹出请求而是底部直接弹出，操作就失败了）

通过点击图标中央，或者在环状区域滑动，可以实现surface dial的操作及功能。
点击键盘图标，可以用此APP模拟蓝牙键盘（只能用键盘输入英文和数字符号，不可拼音打字）

### 异常处理：
1) 确认操作要点。先删除之前的配对/打开APP后再开始配对/配对过程APP不可以切换到后台/配对请求是从通知栏顶部弹出的，而不是直接从屏幕底部弹出
2) 如果始终不能连上：
你的手机的系统可能被厂商删除了一部分协议支持。可以通过安装Bluetooth HID Device Profile Compatibility Checker进行检查：
谷歌市场 https://play.google.com/store/apps/details?id=com.rkaneapplabs.bluetooth_hid.bluetoothproxy 
百度网盘 https://pan.baidu.com/s/12aiHsOAUT696E7v2P60Iyw 提取码:66k5
如果是系统不支持，那就不要挣扎了。（据说1+是不行的)
3) APP一开始可以用，自动断开不能自动连上：
先检查APP设置的Enable autopair是否打勾。
APP保持在前台，关闭手机蓝牙再重新打开，就能连上了

视频说明：http://www.bilibili.com/video/BV1tV411k73h
项目地址：https://github.com/tumuyan/Kontroller
下载地址：https://www.coolapk.com/apk/266123
