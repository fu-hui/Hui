package com.github.hui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.hui.model.ServiceResponse;
import com.github.hui.model.User;
import com.github.hui.service.UserService;

public class LoginActivity extends AppCompatActivity {

    private static final UserService userService = new UserService();
    private static final String TAG = "LoginActivity";
    private EditText usernameEt;
    private EditText passwordEt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // init view bing
        usernameEt = findViewById(R.id.et_username);
        passwordEt = findViewById(R.id.et_password);

        // get button and add events
        // login
        Button loginButton = findViewById(R.id.bt_login);
        loginButton.setOnClickListener(view -> {
            new Thread(() -> {
                User user = getLoginUser();
                ServiceResponse response = userService.login(user);
                if (response != ServiceResponse.OK) {
                    Log.e(TAG, "user login fail, err:" + response.getMsg());
                    runOnUiThread(() -> {
                        AlertDialog dialog = new AlertDialog.Builder(this).create();
                        dialog.setMessage(response.getMsg());
                        dialog.show();
                    });
                    return;
                }

                Log.i(TAG, "user login success");
                startActivity(new Intent(this, MainActivity.class));
            }).start();
        });

        // register
        Button registerButton = findViewById(R.id.bt_register);
        registerButton.setOnClickListener(view -> {
            new Thread(() -> {
                User user = getLoginUser();
                ServiceResponse response = userService.register(user);
                if (response != ServiceResponse.OK) {
                    Log.e(TAG, "user register fail");
                    runOnUiThread(() -> {
                        AlertDialog dialog = new AlertDialog.Builder(this).create();
                        dialog.setMessage(response.getMsg());
                        dialog.show();
                    });
                    return;
                }

                Log.i(TAG, "user register success");
                runOnUiThread(() -> {
                    AlertDialog dialog = new AlertDialog.Builder(this).create();
                    dialog.setMessage("register success");
                    dialog.show();
                });
            }).start();
        });
    }

    private User getLoginUser() {
        User user = new User();
        user.setUsername(usernameEt.getText().toString());
        user.setPassword(passwordEt.getText().toString());
        return user;
    }
}
