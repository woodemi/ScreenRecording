package com.screenrecording.rn;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.guaju.screenrecorderlibrary.ScreenRecorderHelper;
import com.screenrecording.MainActivity;

import java.io.File;

public class RNInterractModule extends ReactContextBaseJavaModule {

    private ReactContext mReactContext;

    public RNInterractModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactContext = reactContext;
    }

    //导出的模块名字
    @Override
    public String getName() {
        return "RNInterractModule";
    }

    //============== START ==============
    @ReactMethod
    public void start(String info) {
//        Toast.makeText(getReactApplicationContext(),info,Toast.LENGTH_SHORT).show();

        MainActivity.screenRecorderHelper.startRecord(getCurrentActivity());
    }

    //============== STOP ==============
    @ReactMethod
    public void stop(String info, Callback successCallback) {
//        Toast.makeText(getReactApplicationContext(),info,Toast.LENGTH_SHORT).show();

        MainActivity.screenRecorderHelper.stopRecord(new ScreenRecorderHelper.OnRecordStatusChangeListener() {
            @Override
            public void onChangeSuccess() {
                //当停止成功，做界面变化
                Toast.makeText(getCurrentActivity(), "录屏成功"+ MainActivity.screenRecorderHelper.getRecordFilePath(), Toast.LENGTH_SHORT).show();
                Log.e("MainActivity result:", "录屏成功："+ MainActivity.screenRecorderHelper.getRecordFilePath());

                successCallback.invoke(MainActivity.screenRecorderHelper.getRecordFilePath());

                File file = new File(MainActivity.screenRecorderHelper.getRecordFilePath());

                Log.e("MainActivity result:", "dir："+ Environment.getExternalStorageDirectory().getAbsolutePath() + ", --> " + file.exists());
            }

            @Override
            public void onChangeFailed() {

            }
        });
    }
}