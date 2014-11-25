# Webview+ for Android #

This project is a plugin for cordova/phonegap apps, and provides a **uniform webview on any Android 4.x device.**.

Looking for [Webview+ for iOs](https://github.com/ludei/webview-plus-ios)?

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
// Install Ludei's CLI
$ sudo npm install -g cocoonjs

$ cocoonjs create MyProject
$ cd MyProject
$ cocoonjs platform add ios
$ cocoonjs plugin add com.ludei.webview.plus -d
$ cocoonjs run/emulate
```

The `-d` flag is used to activate the verbose mode.

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
* Current version may not reproduce sound files.
* In some devices 3D context may not be created, this is due to a memory limitation of the device.
* As android SDK API level 19 is required, if it is not installed properly in the machine, it will raise an "Unable to resolve project target 'Google Inc.:Google APIs:19'" compilation error. Here it is a [step-by-step guide](https://github.com/ludei/cocoonjs-cli/wiki/How-to-solve-the-compilation-error:-%E2%80%9CUnable-to-resolve-project-target-'Google-Inc.:Google-APIs:19'%E2%80%9D) to solve this issue. 

### About the author ###

[Ludei](http://www.ludei.com) is a San Francisco based company, creators of CocoonJS. Ludei aims to empower HTML5 industry with a set of tools that eases the adoption of HTML5 as the target platform for every mobile development.
