package com.example.android.lab07_activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.android.lab07_activities.adapter.QuestionAdapter;
import com.example.android.lab07_activities.adapter.QuestionAdapterFactory;
import com.example.android.lab07_activities.model.UserAnswers;
import com.example.android.lab07_activities.service.SubmitAnswersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        return QuestionActivity.VISIBLE;
    }

    @Override
    protected int getNextButtonVisibility() {
        return QuestionActivity.VISIBLE;
    }

    @Override
    public void next(View view) {

        QuestionAdapter adapter = QuestionAdapterFactory.getQuestionAdapter();
        final UserAnswers userAnswers = QuestionActivity.getsUserAnswers();
        StringBuilder message = new StringBuilder();

        message.append("您的作答如下\n\n");
        for(int i = 0 ; i < adapter.getQuestionCount() ; i++) {
            message.append(String.valueOf(i+1))
                    .append(": ")
                    .append(userAnswers.getAnswer(i))
                    .append("\n")
                    .append(userAnswers.getDescription(i))
                    .append("\n\n");

        }
        message.append("\n確定要結束?");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // 將答案提交至 Google 表單
                        SubmitAnswersService service = SubmitAnswersService.retrofit.create(SubmitAnswersService.class);
                        char q1Ans = userAnswers.getAnswer(0); // 使用著第一題答案
                        char q2Ans = userAnswers.getAnswer(1); // 使用著第一題答案
                        char q3Ans = userAnswers.getAnswer(2); // 使用著第一題答案
                        String url = "/forms/d/1rkj8X7a9MU_ro4B3pGQYnL0WdDjxlNO_wWGf3FvC8Aw/formResponse?ifq&entry.510729086=" + q1Ans +
                                "&entry.1132910563=" + q2Ans +
                                "&entry.1762837083=" + q3Ans +
                                "&submit=Submit\"";
                        Call<String> call =  service.send(url);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.isSuccessful()) {
                                    Log.d("Retrofit", "onResponse() success");
                                    Log.d("Retrofit Response", response.body());
                                } else {
                                    Log.d("Retrofit", "onResponse() : error response, no access to resource ?");
                                    Log.d("Retorift", "response code = " + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("Retrofit", "onFailure() : " + t.toString());
                            }
                        });



                        Intent intent = new Intent(Activity3.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
                        startActivity(intent); // 回主畫面
                        QuestionActivity.resetQuestionIndex(); // 索引編號歸零
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
