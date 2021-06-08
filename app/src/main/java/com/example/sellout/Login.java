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
import android.widget.TextView;

public class Login extends MainActivity {
    EditText yEmail, yPassword;
    Button yLoginBtn, ySignUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        yEmail = findViewById(R.id.email);
        yPassword = findViewById(R.id.password);
        yLoginBtn = findViewById(R.id.loginButton);
        ySignUpBtn = findViewById(R.id.signupButton);


        yLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMail = yEmail.getText().toString().trim();
                String pw = yPassword.getText().toString().trim();

                if (TextUtils.isEmpty(eMail)) {
                    yEmail.setError("Veuillez indiquer votre email");
                }

                if (TextUtils.isEmpty(pw)) {
                    yPassword.setError("Veuillez indiquer votre mot de passe");
                }

                if (pw.length() < 8) {
                    yPassword.setError("Le mot de passe doit contenir au moins 8 caractères.");
                }


                ySignUpBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), Signup.class));
                    }
                });
            }
        });
    }
}