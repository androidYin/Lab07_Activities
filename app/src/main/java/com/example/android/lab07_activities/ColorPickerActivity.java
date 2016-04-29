package com.example.android.lab07_activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ColorPickerActivity extends AppCompatActivity {

    private static final String TAG = "ColorPickerActivity";

    // key 值的最佳實踐方式，以 App 的 package 作為前綴詞
    public static final String BUNDLE_KEY_COLOR_INT = "com.teacheryin.android.colorInt";
    public static final String BUNDLE_KEY_COLOR_NAME = "com.teacheryin.android.colorName";

    private int mColorInt;
    private CharSequence mColorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);
        Log.d(TAG, "onCreate");
        initColorData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
        resotreData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPuase");
        saveData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    private void initColorData() {
        // 預設選取紅色
        RadioButton radio = (RadioButton)findViewById(R.id.radio_holo_red_light);
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();
        Log.d(TAG, "clickColor() mColorInt = " + mColorInt + "  mColorName = " + mColorName);
    }

    public void clickColor(View view) {
        RadioButton radio = (RadioButton)view;
        mColorInt = radio.getCurrentTextColor();
        mColorName = radio.getText();
    }

    public void ok(View view) {
        // 建立意圖
        Intent intent = new Intent();
        // 設定包裹
        intent.putExtra(BUNDLE_KEY_COLOR_INT, mColorInt);   // key: String , value: int )
        intent.putExtra(BUNDLE_KEY_COLOR_NAME, mColorName); // key: String , value: String )
        setResult(RESULT_OK, intent); // 設定結果OK了，傳送意圖
        finish(); // 目前 Activity 結束

    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }


    private void saveData() {
        // 取得偏好設定物件
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        // 取得偏好設定編輯物件
        SharedPreferences.Editor editor = prefs.edit();
        // 寄放資料到包裹
        editor.putInt(BUNDLE_KEY_COLOR_INT, mColorInt);
        editor.putString(BUNDLE_KEY_COLOR_NAME, mColorName.toString());
        // 送出資料
        editor.commit();
        Log.d(TAG, "saveData() mColorInt = " + mColorInt + "  mColorName = " + mColorName);
    }

    private void resotreData() {
        // 取得偏好設定物件
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        // 從包裹取回寄放的資料
        mColorInt = prefs.getInt(BUNDLE_KEY_COLOR_INT, 0);
        mColorName = prefs.getString(BUNDLE_KEY_COLOR_NAME, "holo_red_light");
        Log.d(TAG, "restoreData() mColorInt = " + mColorInt + "  mColorName = " + mColorName);
    }

}
