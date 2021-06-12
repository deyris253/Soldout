package com.example.sellout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AccountCreated extends AppCompatActivity {

    Button yLoginBtn, ySignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_created);

        yLoginBtn = findViewById(R.id.loginButton);
        ySignupBtn = findViewById(R.id.signupButton);


        yLoginBtn.setOnClickListener(v6 -> startActivity(new Intent(getApplicationContext(), Login.class)));

        ySignupBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Signup.class)));
    }
}