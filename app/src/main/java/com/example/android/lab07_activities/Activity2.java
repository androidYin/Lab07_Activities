package com.example.android.lab07_activities;

import android.view.View;

public class Activity2 extends QuestionActivity {

    @Override
    protected Class getBackActivityClass() {
        return Activity1.class;
    }

    @Override
    protected Class getNextActivityClass() {
        return Activity3.class;
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
