package com.example.android.lab07_activities.adapter;

import android.content.res.Resources;

import com.example.android.lab07_activities.myapp.MyApp;

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

