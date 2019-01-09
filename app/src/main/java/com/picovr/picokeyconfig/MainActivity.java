package com.picovr.picokeyconfig;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private Context mContext;

    private final static String userKeyConfig = "UserKeyConfig.prop";
    private final static String defaultKeyConfig = "DefaultKeyConfig.prop";
    private final static String KEY_CONFIG_PATH = "/data/local/tmp/SystemKeyConfig.prop";
    private final static String localConfigEnable = "picovr.local.config.enable";

    private static final String TAG = "MainActivity";

    BroadcastReceiver testReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("systemconfig", "info is " + intent.getAction() + intent.getExtras().getInt("keycode")
                    + intent.getExtras().getInt("action"));
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        IntentFilter f = new IntentFilter();
        f.addAction("android.intent.keybroadcast");
        mContext.registerReceiver(testReceiver, f);

        SystemPropertiesProxy.set(mContext, localConfigEnable, "1");
    }

    // 设置用户的Home键值响应
    public void setUserKeyClick(View view) {
        copyConfigTodata(0);
        Intent intent = new Intent();
        intent.setAction("android.intent.user_keyconfig_change");
        sendBroadcast(intent);
        Log.i("发送广播", "sucess");
    }

    // 回复系统的Home键值响应
    public void resetDefaultKeyClick(View view) {
        copyConfigTodata(1);
        Intent intent = new Intent();
        intent.setAction("android.intent.user_keyconfig_change");
        sendBroadcast(intent);
        Log.i("发送广播", "sucess");
    }

    private boolean copyConfigTodata(int configType) {
        // InputStream in = null;
        File file = null;
        FileInputStream in = null;
        FileOutputStream out = null;
        int length = -1;
        byte[] buf = new byte[1024];
        switch (configType) {
            case 0:
                try {
                    // in = mContext.getAssets().open(userKeyConfig);
                    String directory = Environment.getExternalStoragePublicDirectory(DOWNLOAD_SERVICE).getPath();
                    Log.e(TAG, "directory = " + directory);
                    file = new File(directory + File.separator + userKeyConfig);
                    in = new FileInputStream(file);
                    out = new FileOutputStream(new File(KEY_CONFIG_PATH));
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    out.flush();
                } catch (Exception e) {
                    Log.e("systemConfig", e.toString());
                }
                break;
            case 1:
                try {
                    // in = mContext.getAssets().open(defaultKeyConfig);
                    String directory = Environment.getExternalStoragePublicDirectory(DOWNLOAD_SERVICE).getPath();
                    Log.e(TAG, "directory = " + directory);
                    file = new File(directory + File.separator + defaultKeyConfig);
                    in = new FileInputStream(file);
                    out = new FileOutputStream(new File(KEY_CONFIG_PATH));
                    while ((length = in.read(buf)) != -1) {
                        out.write(buf, 0, length);
                    }
                    out.flush();
                } catch (Exception e) {
                    Log.e("systemConfig", e.toString());
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
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}

