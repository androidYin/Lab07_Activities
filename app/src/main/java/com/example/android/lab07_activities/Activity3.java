package com.example.android.lab07_activities;

import android.view.View;

public class Activity3 extends QuestionActivity {

    @Override
    protected void onStart() {
        super.onStart();
        setNextButtonText("Finish"); // 呼叫父類別寫好的功能
    }

    @Override
    protected Class getBackActivityClass() {
        return Activity2.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return null;
    }

    @Override
    protected int getBackButtonVisibility() {
        return View.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return View.VISIBLE;
    }

}
