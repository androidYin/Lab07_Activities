package com.example.android.lab07_activities;

import android.content.res.Resources;

public class QuestionAdapterFactory { // 轉接器工廠
    // 靜態成員
    private static QuestionAdapter adapter;

    public static QuestionAdapter getQuestionAdapter() {
        if(adapter == null) {
            // 透過 MyApp 取得 Context，進一步取得 Resources
            Resources res = MyApp.getContext().getResources();
            adapter = new QuestionFromStringResource(res);
        }
        return adapter;
    }

    // 私有建構子
    private QuestionAdapterFactory() { // 產生轉接器
    }

}

