package com.example.selling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.selling.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    // ImageButton yHomeBtn;
    EditText yFullName, yName, yEmail, yPassword, yConfirmPassword;
    Button ySignupBtn, yLoginBtn;
    FirebaseAuth yAuth;
    FirebaseDatabase yDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //yHomeBtn = findViewById(R.id.homeBtn);
        yFullName = findViewById(R.id.fullName);
        yName = findViewById(R.id.name);
        yEmail = findViewById(R.id.email);
        yPassword = findViewById(R.id.password);
        yConfirmPassword = findViewById(R.id.pwConfirmed);
        ySignupBtn = findViewById(R.id.signupButton);
        yAuth = FirebaseAuth.getInstance();
        yDB = FirebaseDatabase.getInstance();


        yLoginBtn = findViewById(R.id.loginButton);



        //yHomeBtn.setOnClickListener(v5 -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));


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
                yName.setError("Votre pr??nom est requis pour l'inscription.");
            }

            if (TextUtils.isEmpty(eMail)) {
                yEmail.setError("Votre email est requis pour l'inscription.");

            } else if (!isValidEmail(eMail)) {
                yEmail.setError("L'adresse email n'est pas valide.");
            }

            if (TextUtils.isEmpty(pw)) {
                yPassword.setError("La cr??ation d'un mot de passe est requise pour l'inscription.");

            } else if (pw.length() < 7) {
                yPassword.setError("Votre mot de passe doit contenir au moins 7 caract??res.");

            } else if (!confirmPw.equals(pw)) {
                yConfirmPassword.setError("Les deux mots de passe ne sont pas identiques.");

            } else {

                yAuth.createUserWithEmailAndPassword(eMail, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(SignUp.this, Login.class));
                            Toast.makeText(SignUp.this, "Votre compte a bien ??t?? cr????", Toast.LENGTH_LONG).show();
                            finish();

                            UserModel user = new UserModel(eMail, pw);
                            String id = task.getResult().getUser().getUid();
                            yDB.getReference().child("Clients").child(id).setValue(user);
                        } else {
                            Toast.makeText(SignUp.this, "Votre compte n'a pas ??t?? cr????", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }




        });

        yLoginBtn.setOnClickListener(v6 -> startActivity(new Intent(getApplicationContext(), Login.class)));
    }

    private boolean isValidEmail(String eMail) {

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(eMail);
        return matcher.matches();

    }
}