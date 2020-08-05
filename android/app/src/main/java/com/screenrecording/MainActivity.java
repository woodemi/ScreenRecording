package com.screenrecording;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.guaju.screenrecorderlibrary.ScreenRecorderHelper;

import java.io.File;

public class MainActivity extends ReactActivity {

  private int value = 0;

  private String path = "";

  public static ScreenRecorderHelper screenRecorderHelper;

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "ScreenRecording";
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    setContentView(R.layout.activity_main);

    screenRecorderHelper = MainApplication.getApp().getSRHelper();
    screenRecorderHelper.initRecordService(this);
  }

  public void click(View view) {
    switch (view.getId()) {
      case R.id.startScan:
        Log.e("MainActivity log: ", "startScan");
        screenRecorderHelper.startRecord(MainActivity.this);
        break;
      case R.id.stopScan:
        Log.e("MainActivity log: ", "stopScan");
        screenRecorderHelper.stopRecord(new ScreenRecorderHelper.OnRecordStatusChangeListener() {
          @Override
          public void onChangeSuccess() {
            //当停止成功，做界面变化
            Toast.makeText(MainActivity.this, "录屏成功"+ screenRecorderHelper.getRecordFilePath(), Toast.LENGTH_SHORT).show();
            Log.e("MainActivity result:", "录屏成功："+ screenRecorderHelper.getRecordFilePath());

            path = screenRecorderHelper.getRecordFilePath();

            File file = new File(screenRecorderHelper.getRecordFilePath());

            Log.e("MainActivity result:", "dir："+ Environment.getExternalStorageDirectory().getAbsolutePath() + ", --> " + file.exists());
          }

          @Override
          public void onChangeFailed() {

          }
        });
        break;
      case R.id.toast:
        Toast.makeText(MainActivity.this, "test " + ++value, Toast.LENGTH_SHORT).show();
        Log.e("MainActivity result:", "toast path："+ path);

        String filesPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ScreenRecord";
        Log.e("MainActivity result:", "filesPath：1: "+ filesPath);

        File[] filsList = (new File(filesPath)).listFiles();

        for (File file : filsList) {
          Log.e("MainActivity result:", "filesPath：2: " + file.getName() + ", path: " + file.getPath());
        }

        break;
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    Log.e("tag", "requestCode****" + requestCode);
    screenRecorderHelper.onActivityResult(this, requestCode, resultCode, data, new ScreenRecorderHelper.OnRecordStatusChangeListener() {
      @Override
      public void onChangeSuccess() {
        //开始录制，处理开始录制后的事件
      }

      @Override
      public void onChangeFailed() {
        //如果录制失败，则不作任何变化
      }
    });
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    screenRecorderHelper.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
  }
}
