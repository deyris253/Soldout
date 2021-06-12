package com.example.sellout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sellout.navigationBar.FragmentHome;

public class Login extends MainActivity {
    ImageButton yHomeBtn;
    EditText yEmail, yPassword;
    Button yLoginBtn, ySignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        yHomeBtn = findViewById(R.id.homeBtn);
        yEmail = findViewById(R.id.email);
        yPassword = findViewById(R.id.password);
        yLoginBtn = findViewById(R.id.loginButton);
        ySignUpBtn = findViewById(R.id.signupButton);



        yHomeBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));


        yLoginBtn.setOnClickListener(v -> {
            String eMail = yEmail.getText().toString().trim();
            String pw = yPassword.getText().toString().trim();

            if (TextUtils.isEmpty(eMail)) {
                yEmail.setError("Veuillez indiquer votre email.");
            }

            if (TextUtils.isEmpty(pw)) {
                yPassword.setError("Veuillez indiquer votre mot de passe.");
            }

            if (pw.length() < 8) {
                yPassword.setError("Le mot de passe doit contenir au moins 8 caractères.");
            }
        });


        ySignUpBtn.setOnClickListener(v1 -> startActivity(new Intent(getApplicationContext(), Signup.class)));
    }
}