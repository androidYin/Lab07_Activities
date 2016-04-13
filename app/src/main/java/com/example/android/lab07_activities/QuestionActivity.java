package com.example.android.lab07_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

// abstract activity 不需要宣告在 manifest
public abstract class QuestionActivity extends AppCompatActivity {

    private TextView m_tv_no;
    private TextView m_tv_question;
    private RadioButton m_radio_a;
    private RadioButton m_radio_b;
    private RadioButton m_radio_c;

    private Button m_btn_back;
    private Button m_btn_next;

    private static int sQuestionIndex = 0;   // 只需要一個 index，所以宣告為靜態
    private static QuestionAdapter sAdapter; // 只需要一個 adapter，所以宣告為靜態

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initQuestions();
        initBackNextButtons();
    }

     // 抑制 setVisibility() 錯誤訊息
    @SuppressWarnings("ResourceType")
    private void initBackNextButtons() {
        m_btn_back = (Button)findViewById(R.id.btn_back);
        m_btn_next = (Button)findViewById(R.id.btn_next);

        // 設定 Back Next 按鈕是否能看見
        // Visibility 只能設定為 View.VISIBLE / View.INVISIBLE / View.GONE
        m_btn_back.setVisibility(getBackButtonVisibility());
        m_btn_next.setVisibility(getNextButtonVisibility());
    }

    private void initQuestions() {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (RadioButton)findViewById(R.id.radio_a);
        m_radio_b = (RadioButton)findViewById(R.id.radio_b);
        m_radio_c = (RadioButton)findViewById(R.id.radio_c);

        // 題號
        String no = String.valueOf(sQuestionIndex +1);
        m_tv_no.setText(no);

        // 題目 與 選項
        if(sAdapter == null) {
            sAdapter = QuestionAdapterFactory.getQuestionAdapter();
        }

        m_tv_question.setText(sAdapter.getQuestion(sQuestionIndex));
        m_radio_a.setText(sAdapter.getQuestionOptionsA(sQuestionIndex));
        m_radio_b.setText(sAdapter.getQuestionOptionsB(sQuestionIndex));
        m_radio_c.setText(sAdapter.getQuestionOptionsC(sQuestionIndex));
    }

    // 按下 BACK
    public void back(View view) {
        sQuestionIndex--;
        // 建立新 Intent: new Intent( 來源 , 目的)
        Intent intent = new Intent(this, getBackActivityClass());
        // 將先前的 Acvivity 移到最上層，而非產生新的 Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        // overridePendingTransition( 進場效果 , 出場效果 )
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }

    // 按下 NEXT
    public void next(View view) {
        sQuestionIndex++;
        // 建立新 Intent: new Intent( 來源 , 目的)
        Intent intent = new Intent(this, getNextActivityClass());
        // 將先前的 Acvivity 移到最上層，而非產生新的 Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

        // overridePendingTransition( 進場效果 , 出場效果 )
        overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    // 設定 Back 按鈕文字
    public void setBackButtonText(CharSequence text) {
        m_btn_back.setText(text);
    }

    // 設定 Next 按鈕文字
    public void setNextButtonText(CharSequence text) {
        m_btn_next.setText(text);
    }

    // 按下 RadioButton 按鈕
    public void click(View view) {

    }

    // 子類別實現以下功能
    protected abstract Class getBackActivityClass(); // 切換下個畫面的 Activity.class
    protected abstract Class getNextActivityClass(); // 切換上個畫面的 Activity.class
    protected abstract int getBackButtonVisibility(); // Back 按鈕是否能被看見
    protected abstract int getNextButtonVisibility(); // Next 按鈕是否能被看見

}
