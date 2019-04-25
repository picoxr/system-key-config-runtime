# SystemKeyConfig 

Note: Regarding JAR file creation and usage, please refer to [the Guideline](https://github.com/picoxr/support/blob/master/How%20to%20Use%20JAR%20file%20in%20Unity%20project%20on%20Pico%20device.docx)

## Introduction
This JAR file is used to config system home key

## Modify AndroidManifest

Add Manifest tag
```
android:sharedUserId="android.uid.system"
``` 

Add permissions

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

## ClassName

```
android:name=" com.example.picokeyconfig.PicoKeyConfigManager"
``` 
## Configration file

```
Action_home_single_tap   :home click function

Action_home_double_tap   :home double tap function

Action_home_long_press   :home long press function
```

The value of the above functions can be set as, and the above values can be set as the corresponding functions defined below to take effect

1: start the application with the specified package name and class name. 

2: return the launcher

3: launch Settings 

4: return

5: no function, just send button notifications

Action_power_press power  function

Not set: system default function 0: invalid 1: valid


Time_home_double_tap  :double click to determine the interval

Time_home_long_press long press  : the judgment interval

Just set it to milliseconds



Action_home_XXX _package = XXX

Action_home_XXX _class = XXX

Parameter set to function value 1

6. In addition, note: manual modification of system key mode (equipment restart is required)

Open adb and copy the file to data/local/ TMP:

adb push SystemKeyConfig.prop /data/local/tmp/
