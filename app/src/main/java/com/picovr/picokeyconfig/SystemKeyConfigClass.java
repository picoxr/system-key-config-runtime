package com.picovr.picokeyconfig;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SystemKeyConfigClass {
    private static final String TAG = "SystemKeyConfigClass";

    private Context mContext;
    private String mConfigPath;
    private final static String userKeyConfig = "UserKeyConfig.prop";
    private final static String defaultKeyConfig = "DefaultKeyConfig.prop";
    private final static String KEY_CONFIG_PATH = "/data/local/tmp/SystemKeyConfig.prop";

    /**
     * @param context Context
     * @param configPath The path of configuration file. eg: "/storage/emulated/0/Download/".*/
    public void init(Context context, String configPath) {
        mContext = context;
        mConfigPath = configPath;
    }

    /**
    * Set user custom key configuration.*/
    public void setUserKeyConfig() {
        setKeyConfig(mContext, 0);
    }

    /**
     * Set key configuration to default.*/
    public void setDefaultKeyConfig() {
        setKeyConfig(mContext, 1);
    }

    /**
     * @param context Context
     * @param configType 0: Set user custom key configuration.
     *                   1: Set key configuration to default.*/
    private boolean setKeyConfig(Context context, int configType) {
        File file = null;
        FileInputStream in = null;
        FileOutputStream out = null;
        int length = -1;
        byte[] buf = new byte[1024];
        switch (configType) {
            case 0:
                try {
                    String filePath = mConfigPath + userKeyConfig;
                    Log.e(TAG, "setKeyConfig: filePath = " + filePath);
                    file = new File(filePath);
                    in = new FileInputStream(file);
                    out = new FileOutputStream(new File(KEY_CONFIG_PATH));
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    out.flush();
                } catch (Exception e) {
                    Log.e(TAG, "Copy config file failed. Error message: " + e.getMessage());
                }
                break;
            case 1:
                try {
                    String filePath = mConfigPath + defaultKeyConfig;
                    Log.e(TAG, "setKeyConfig: filePath = " + filePath);
                    file = new File(filePath);
                    in = new FileInputStream(file);
                    out = new FileOutputStream(new File(KEY_CONFIG_PATH));
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    out.flush();
                } catch (Exception e) {
                    Log.e(TAG, "Copy config file failed. Error message: " + e.getMessage());
                }
                break;
            default:
                break;
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "setKeyConfig: " + e.getMessage());
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "setKeyConfig: " + e.getMessage() );
            }
        }

        //Send broadcast to notify system to refresh config.
        Intent intent = new Intent();
        intent.setAction("android.intent.user_keyconfig_change");
        context.sendBroadcast(intent);

        return true;
    }
}
