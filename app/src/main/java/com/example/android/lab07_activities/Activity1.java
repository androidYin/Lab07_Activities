package com.example.android.lab07_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity {

    public static final String Q1_ANSWER_KEY = "Q1";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;

    private CharSequence m_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        init();
    }

    private void init() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        int index = 0; // 第1題
        String no = String.valueOf(index+1);
        m_tv_no.setText(no);

        // 透過工廠取得 adapter
        QuestionAdapter adapter = QuestionAdapterFactory.getQuestionAdapter();
        m_tv_question.setText(adapter.getQuestion(index));
        m_radio_a.setText(adapter.getQuestionOptionsA(index));
        m_radio_b.setText(adapter.getQuestionOptionsB(index));
        m_radio_c.setText(adapter.getQuestionOptionsC(index));
    }

    // 按下 NEXT
    public void next(View view) {

        // 建立新 Intent: new Intent( 來源 , 目的)
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(Q1_ANSWER_KEY, m_answer);
        startActivity(intent);

        // overridePendingTransition( 進場效果 , 出場效果 )
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);

    }

    // 按下 RadioButton
    public void click(View view) {
        m_answer = view.getTag().toString();
    }

}
