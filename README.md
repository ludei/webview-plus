# Webview+ #

This project is a plugin for cordova apps, and provides a **uniform webview on any Android 4.x device.**.

## Webview+ Features, Advantages and Benefits  ##

* V8 Javascript VM
* Works on Android 4.x
* Latest HTML5 APIs
* Compatible with Cordova Plugins
* Better performance than system webview

### Prerequisites ###
You'll need [android SDK API](http://developer.android.com/guide/topics/manifest/uses-sdk-element.html) level 19 installed in with your SDK tools.

#### How to install the Webview+ in your current project ####

Even though it's a cordova-compatible plugin, some steps must be done for the installation. If you prefer, you can install it automatically using the [CocoonJS Command Line Interface](https://github.com/ludei/cocoonjs-cli). 

The cocoonjs-cli has the same usage and commands of [cordova-cli](https://github.com/apache/cordova-cli#project-commands).

Once the cocoonjs-cli is installed in your system, just type:

```
$ cd /to/your/cordova/based/project
$ cocoonjs plugin add com.ludei.webview.plus
```

#### Manual installation  ####
The CocoonJS-CLI automates the installation of the Webview+, but if for some reason you want to install the Webview+ without the CocoonJS-CLI you’ll have to do the following steps.

These steps are specific for Cordova 3.4.0 and higher. If you want to install manually Webview+ in previous versions, please, refer to the hooks in the cocoonjs-cli

**Step 1**

Clone the Webview+ repository in your computer:

```
git clone https://github.com/ludei/webview-plus.git
```

**Step 2**

Edit the following file by using your favorite text editor

```
_PATH_TO_YOUR_CORDOVA_PROJECT_/platforms/android/CordovaLib/src/org/apache/cordova/CordovaWebview.java
```

**Step 3**

Find the following string inside CordovaWebview.java:

```
public class CordovaWebView extends WebView
```


**Step 4**

Replace the string you've found with these two strings:


```
import com.ludei.chromium.LudeiWebView;
public class CordovaWebView extends LudeiWebView 
```
Save and close the file

**Step 5**

Edit the following file:

```
_PATH_TO_YOUR_CORDOVA_PROJECT_/platforms/android/CordovaLib/project.properties
```

**Step 6**

Paste the following string and change the path after “android.library.reference.1=” to the relative path that points to your downloaded Webview+ (It must be a relative path and not an absolute path)

```
android.library.reference.1=../../../plugins/com.ludei.webview.plus/android
```


There you go. Now that you have installed the Webview+ in your project you can run the command “$ cordova build” and your app will benefit of the advantages of the Webview+.

If you want to know how the CocoonJS-CLI does these steps programatically, check the sources at android/hooks folder (after_plugin_add / after_plugin_rm).

#### Known bugs ####

* Launching the Webview+ inside android's emulator can lead to an application crash. Use a real device for testing.
* Current version may not reproduce sound files. Will be fixed for next version
* In some devices 3D context may not be created, this is due to a memory limitation of the device.

### About the author ###

Ludei is a San Francisco based company, creators of CocoonJS. Ludei aims to empower HTML5 industry with a set of tools that eases the adoption of HTML5 as the target platform for every mobile development.
