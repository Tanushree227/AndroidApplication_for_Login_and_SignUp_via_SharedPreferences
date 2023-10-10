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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    public static final String SHARED_PREFS = "login_prefs";
    public static final String USERNAME_KEY = "username_key";

    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.email);
        EditText phone = (EditText) findViewById(R.id.phone);
        EditText course = (EditText) findViewById(R.id.course);
        EditText gender = (EditText) findViewById(R.id.gender);
        EditText age = (EditText) findViewById(R.id.age);
        Button signup = (Button) findViewById(R.id.Signup);

        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtnSignUp);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                String email1 = email.getText().toString();
                String phone1 = phone.getText().toString();
                String course1 = course.getText().toString();
                String gender1 = gender.getText().toString();
                String age1 = age.getText().toString();

                if(username1.isEmpty() && password1.isEmpty() && email1.isEmpty() && phone1.isEmpty() && course1.isEmpty() && gender1.isEmpty() && age1.isEmpty())
                {
                    Toast.makeText(SignUp.this, "Please Enter all the fields.", Toast.LENGTH_SHORT).show();
                } else if (!isValidPassword(password1)) {
                    password.setError("Password too weak");
                    password.setText("");

                } else if (!validatePhone(phone1))
                {
                    phone.setError("Invalid Phone Number");
                    phone.setText("");
                } else if (!isValidEmail(email1)) {
                    email.setError("Invalid Email");
                    email.setText("");
                }
                else
                {

                    saveMessage(username1, password1);
                    username.setText("");
                    password.setText("");
                    Intent intent = new Intent(SignUp.this, WelcomeActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }
    private boolean validatePhone(String p1)
    {
        String phoneNumberPattern = "^[6-9]\\d{9}$";
        return Pattern.matches(phoneNumberPattern, p1);
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(xyz\\.(com|edu|in|org))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void saveMessage(String user, String pass)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_KEY, user);
        editor.putString(PASSWORD_KEY, pass);
        editor.apply();
        Toast.makeText(this, "Username and Password saved to shared Preferences.", Toast.LENGTH_SHORT).show();
    }
}