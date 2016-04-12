package com.example.android.lab07_activities;

/**
 * Created by teacher on 2016/4/12.
 */
public interface QuestionAdapter { // 轉接器
    int getQuestionCount();
    CharSequence getQuestion(int index);
    CharSequence getQuestionOptionsA(int index);
    CharSequence getQuestionOptionsB(int index);
    CharSequence getQuestionOptionsC(int index);

}
