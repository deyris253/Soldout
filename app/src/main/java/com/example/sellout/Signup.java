package com.example.sellout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText yFullName, yName, yEmail, yPassword;
    Button ySignupButton, yLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //yFullName = findViewById(R.id.fullName);
        //yName = findViewById(R.id.name);
        yEmail = findViewById(R.id.email);
        yPassword = findViewById(R.id.password);
        ySignupButton = findViewById(R.id.signupButton);
        yLoginButton = findViewById(R.id.loginButton);

        ySignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = yName.getText().toString().trim();
                String eMail = yEmail.getText().toString().trim();
                String pw = yPassword.getText().toString().trim();

                /*if (TextUtils.isEmpty(nom)){
                    yName.setError("Votre prénom est obligatoire.");
                }*/

                if (TextUtils.isEmpty(eMail)){
                    yEmail.setError("Votre email est obligatoire.");
                }

                if (TextUtils.isEmpty(pw)){
                    yPassword.setError("La création d'un mot de passe est obligatoire.");
                }

                if (pw.length() < 8) {
                    yPassword.setError("Le mot de passe doit contenir au moins 8 caractères.");
                }


                yLoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                });


            }
        });

    }
}