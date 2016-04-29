package com.example.android.lab07_activities.myapp;

import android.app.Application;
import android.content.Context;

import com.example.android.lab07_activities.adapter.QuestionAdapter;

public class MyApp extends Application {
    private static Context context;
//    private static UserAnswers userAnswers; // QuestionActivity 自己管理 UserAnswers
    private static QuestionAdapter adapter;

    // getter
    public static Context getContext() { // 產生一個 Context (有內容的東西)
        return context;
    }

    // getter
//    public static UserAnswers getUserAnswers() { // QuestionActivity 自己管理 UserAnswers
//        if(userAnswers == null) {
//            userAnswers = new UserAnswers(3); // 可放3題答案
//        }
//        return userAnswers;
//    }

    public static QuestionAdapter getAdapter() {
        return adapter;
    }

    public static void setAdapter(QuestionAdapter adapter) {
        MyApp.adapter = adapter;
    }
    // 建構子
    public MyApp() {
        context = this; // context 就是 MyApp 物件自己
    }
}
