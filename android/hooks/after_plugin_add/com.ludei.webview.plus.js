#!/usr/bin/env node
var task 					= require('child_process').exec,
	fs 						= require('fs'),
	pathLib 				= require('path'),
	os						= require('os'),
	inWindows 				= (os.platform() === 'win32');

String.prototype.replaceAll = function (find, replace) {
var str = this;
return str.replace(new RegExp(find.replace(/[-\/\\^$*+?.()|[\]{}]/g, '\\$&'), 'g'), replace);
};

var App = function(plugin_name){

	if(!process.env.CORDOVA_PATH_BINARY || !process.env.CORDOVA_CUSTOM_VERSION || !process.env.CORDOVA_PLUGIN_PATH){
		process.exit(0);
	}

	this.CORDOVA_PATH_BINARY = new Buffer(process.env.CORDOVA_PATH_BINARY, 'base64').toString("UTF-8");
	this.CORDOVA_CUSTOM_VERSION = new Buffer(process.env.CORDOVA_CUSTOM_VERSION, 'base64').toString("UTF-8");	
	this.plugin_name 	= plugin_name;
	this.plugin_path 	= this.getPath(new Buffer(process.env.CORDOVA_PLUGIN_PATH, 'base64').toString("UTF-8"));
	this.plugin_path_android =  pathLib.join(this.plugin_path , "../" , "android");
	console.log("Path to Webview+ Project ", this.plugin_path_android, "Plugin path", this.plugin_path);
};

App.prototype = {

	getPath : function(path){
		if(inWindows){
			return path.replaceAll('/', pathLib.sep);
		}else{
			return path;
		}
	},
	getPlugins: function(callback){
		task(this.CORDOVA_PATH_BINARY + " plugins", function (error, stdout, stderr) {
			try{
				if(error) throw new Error(stderr);
				if(this.ctx.CORDOVA_CUSTOM_VERSION >= "3.5.0-0.1.0"){
					var tmp_list = [];
					stdout.split("\\n").forEach(function(plugin_info){
						tmp_list.push(plugin_info.split(" ")[0]);
					});
					stdout = JSON.stringify(tmp_list);
				}
				var plugins = JSON.parse(stdout.toString().replaceAll("'", '"'));
				console.log("Plugin list: ", plugins);
				if(Boolean(plugins) && Array.isArray(plugins))
				{
					this.cllbck.call(this.ctx, plugins);
				}else{
					this.cllbck.call(this.ctx, false);
				}
			}catch(e){
				console.log(e);
			}finally{
				process.exit(1);
			}
		}.bind({ cllbck : callback, ctx : this }));
	},
	processPlugin: function(plugins){
		for (var x = 0; x < plugins.length; x++) {
			if(plugins[x] === this.plugin_name){
				console.log("Installing Webview+");
				this.installChromium();
			}
		};
	},
	installChromium: function(){

		var CORDOVA_LIBRARY_PATH 	= this.getPath("./platforms/android/libs/cordova-3.2.0.jar");
        var PROJECT_PROPERTIES_PATH = (this.CORDOVA_CUSTOM_VERSION === "3.2.0-0.1.0") ? pathLib.join(process.cwd(), "platforms", "android", "project.properties") : pathLib.join(process.cwd(), "platforms" , "android", "CordovaLib" , "project.properties");
		var	CORDOVA_FRAMEWORK_WEBVIEW_PATH 		= this.getPath("./platforms/android/CordovaLib/src/org/apache/cordova/CordovaWebView.java");
		var PROJECT_PROPERTIES_RELATIVE_PATH	= (this.CORDOVA_CUSTOM_VERSION === "3.2.0-0.1.0") ? process.cwd() + "/platforms/android/" : process.cwd() + "/platforms/android/CordovaLib/";
		var RELATIVE_CHROMIUM_PATH 				= pathLib.relative( this.getPath(PROJECT_PROPERTIES_RELATIVE_PATH), this.getPath(this.plugin_path_android) );
		var project_properties_data 			= fs.readFileSync(PROJECT_PROPERTIES_PATH);

		if( !(project_properties_data.toString('utf-8').indexOf("com.ludei.webview.plus") != -1) ){

		console.log("Adding Chromiun as a project reference into " + PROJECT_PROPERTIES_PATH, "Cordova version", this.CORDOVA_CUSTOM_VERSION);
		if(inWindows){
			RELATIVE_CHROMIUM_PATH = RELATIVE_CHROMIUM_PATH.replaceAll(pathLib.sep, '\\\\');
		}
		if(this.CORDOVA_CUSTOM_VERSION === "3.2.0-0.1.0"){
			fs.renameSync( pathLib.join( CORDOVA_LIBRARY_PATH ), pathLib.join( this.plugin_path, "resources", "cordova-3.2.0.jar") );
			fs.renameSync( pathLib.join(this.plugin_path, "resources", "cordova-ludei-framework.jar"), pathLib.join(process.cwd() , "platforms", "android", "libs", "cordova-ludei-framework.jar"));
			if(fs.existsSync(CORDOVA_LIBRARY_PATH)){
				fs.unlinkSync(CORDOVA_LIBRARY_PATH);	
			} 
			fs.appendFileSync(PROJECT_PROPERTIES_PATH, "\nandroid.library.reference.1=" + RELATIVE_CHROMIUM_PATH.toString());
			console.log("Webview+ installed correctly.");
		}

		if(this.CORDOVA_CUSTOM_VERSION >= "3.3.0-0.1.0"){
			var cordova_webview_contents = fs.readFileSync(CORDOVA_FRAMEWORK_WEBVIEW_PATH).toString('utf-8');
			var extend_original = "public class CordovaWebView extends WebView";
			var extend_end = "import com.ludei.chromium.LudeiWebView;\n public class CordovaWebView extends LudeiWebView";

			fs.unlinkSync(CORDOVA_FRAMEWORK_WEBVIEW_PATH);
			fs.writeFileSync(CORDOVA_FRAMEWORK_WEBVIEW_PATH, cordova_webview_contents.replace(extend_original,extend_end));
			fs.appendFileSync(PROJECT_PROPERTIES_PATH, "\nandroid.library.reference.1=" + RELATIVE_CHROMIUM_PATH.toString());
			console.log("Webview+ installed correctly.");
		}

		// Change the api level to 14 if needed
		var android_manifest_path = pathLib.join( process.cwd() , "platforms", "android", "AndroidManifest.xml");
		if(fs.existsSync(android_manifest_path)){
			var manifest_data = fs.readFileSync(android_manifest_path).toString("utf8");
			var manigest_reg_exp = /android:minSdkVersion="([^"]*)"/i;
			var result = manifest_data.match(manigest_reg_exp);
			if(result[1]){
				var android_api_level = parseInt(result[1]);
				if( android_api_level < 14 ){
					manifest_data = manifest_data.replace(manigest_reg_exp, 'android:targetSdkVersion="14"');
					fs.writeFileSync(android_manifest_path, manifest_data, 'utf8');
				}
			}
		}else{
			console.error("Cannot locate Android Manifest");
		}

		}else{
			console.log("Webview+ is already installed in the project.");
		}

		console.log("Hook execution finished.");
		process.exit(0);
	}
}

var cocoon_app = new App('com.ludei.webview.plus');

cocoon_app.getPlugins(function(plugins){
	if(plugins){
		this.processPlugin(plugins);
	}else{
		console.log("No plugins found on the Webview+ hook");
		process.exit(0);
	}
});
