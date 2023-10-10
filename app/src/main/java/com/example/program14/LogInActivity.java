package com.example.program14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    public static final String SHARED_PREFS = "login_prefs";
    public static final String USERNAME_KEY = "username_key";

    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedPreferences;

    EditText user, pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        user = findViewById(R.id.username1);
        pass = findViewById(R.id.password1);
        login = findViewById(R.id.login);

        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtnLogin);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String user_str = sharedPreferences.getString(USERNAME_KEY, "");
        String pass_str = sharedPreferences.getString(PASSWORD_KEY, "");
        user.setText(user_str);
        pass.setText(pass_str);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = user.getText().toString();
                String password1 = pass.getText().toString();
                if(TextUtils.isEmpty(username1) && TextUtils.isEmpty(password1))
                    Toast.makeText(LogInActivity.this,"Please enter all the fields", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogInActivity.this, WelcomeActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String user_str = sharedPreferences1.getString(USERNAME_KEY, "");
        String pass_str = sharedPreferences1.getString(PASSWORD_KEY, "");
        user.setText(user_str);
        pass.setText(pass_str);
    }
}