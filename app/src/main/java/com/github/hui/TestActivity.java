package com.github.hui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {
    private TextView msgTextView;
    private static final String AM_URL = "http://152.136.192.15:8080/ping";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        msgTextView = findViewById(R.id.tv_msg);
        Button msgButton = findViewById(R.id.bt_msg);

        msgButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(AM_URL)
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        msgTextView.setText(response.body().string());
                    } catch (Exception e) {
                        msgTextView.setText("get msg from " + AM_URL + " error, ex is:" + e.getMessage());
                    }
                }).start();
            }
        });
    }
}