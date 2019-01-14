<p align="right"><a href="https://github.com/PicoSupport/PicoSupport" target="_blank"> <img src="https://github.com/PicoSupport/PicoSupport/blob/master/Assets/home.png" width="20"/> </a></p>

# SystemKeyConfig Instructions

Note: Regarding java package creation and usege, please refer to [the Guideline](https://github.com/PicoSupport/PicoSupport/blob/master/How_to_use_JAR_file_in_Unity_project_on_Pico_device.docx)

1. Equipment Goblin system B76 or above version is required

  Create a new Unity project and copy the PicoKeyConfig_xxx. Jar package from the plugins-> Android in the Demo to the directory corresponding to the Unity project.Place the files that need to be configured in any directory of the system and pass the file name and path as parameters to the interface

2. Modify the androidminifests. XML file.Add android:sharedUserId="android.uid.system" in the Manifest tag.

Add permissions (if not) :

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

Modifica mainactivity: android:name=" com.example.picokeyconfig.PicoKeyConfigManager"



3. Copy the script of PicoUnityActivity. Cs in the Demo to any directory of Unity project.

4. Call the Android interface,For example: PicoUnityActivity CallObjectMethod (" androidSetUserKey ", "file contains path");

5. Profile description

   Action_home_single_tap   :home click function

Action_home_double_tap   :home double tap function

Action_home_long_press   :home long press function

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



