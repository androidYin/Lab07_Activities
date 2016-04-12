package com.example.android.lab07_activities;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    private static Context context;

    public static Context getContext() { // 產生一個 Context (有內容的東西)
        return context;
    }
    // 建構子
    public MyApp() {
        context = this; // context 就是 MyApp 物件自己
    }
}
