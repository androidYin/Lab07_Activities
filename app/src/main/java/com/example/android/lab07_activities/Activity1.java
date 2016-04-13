package com.example.android.lab07_activities;

import android.view.View;

public class Activity1 extends QuestionActivity {

    @Override
    protected Class getBackActivityClass() {
        return null;
    }

    @Override
    protected Class getNextActivityClass() {
        return Activity2.class;
    }

    @Override
    protected int getBackButtonVisibility() {
        return View.GONE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return View.VISIBLE;
    }
}
