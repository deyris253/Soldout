package com.example.sellout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Signup extends AppCompatActivity {
    ImageButton yHomeBtn;
    EditText yFullName, yName, yEmail, yPassword, yConfirmPassword;
    Button ySignupBtn, yLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        yHomeBtn = findViewById(R.id.homeBtn);
        yFullName = findViewById(R.id.fullName);
        yName = findViewById(R.id.name);
        yEmail = findViewById(R.id.email);
        yPassword = findViewById(R.id.password);
        yConfirmPassword = findViewById(R.id.pwConfirmed);
        yLoginBtn = findViewById(R.id.loginButton);
        ySignupBtn = findViewById(R.id.signupButton);


        yHomeBtn.setOnClickListener(v5 -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));


        ySignupBtn.setOnClickListener(v -> {
            String nom = yFullName.getText().toString().trim();
            String prenom = yName.getText().toString().trim();
            String eMail = yEmail.getText().toString().trim();
            String pw = yPassword.getText().toString().trim();
            String confirmPw = yConfirmPassword.getText().toString().trim();


            if (TextUtils.isEmpty((nom))) {
                yFullName.setError("Votre nom est requis pour l'inscription.");
            }

            if (TextUtils.isEmpty((prenom))) {
                yName.setError("Votre prénom est requis pour l'inscription.");
            }

            if (TextUtils.isEmpty(eMail)) {
                yEmail.setError("Votre email est requis pour l'inscription.");
            }

            if (TextUtils.isEmpty(pw)) {
                yPassword.setError("La création d'un mot de passe est requise pour l'inscription.");
            }

            if (pw.length() < 8) {
                yPassword.setError("Votre mot de passe doit contenir au moins 8 caractères.");
            }

            if (!confirmPw.equals(pw)) {
                yConfirmPassword.setError("Les deux mots de passe ne sont pas identiques.");
            }


        });

        yLoginBtn.setOnClickListener(v6 -> startActivity(new Intent(getApplicationContext(), Login.class)));


    }
}