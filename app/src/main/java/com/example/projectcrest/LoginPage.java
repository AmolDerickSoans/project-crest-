package com.example.projectcrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {



    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button loginButton = findViewById(R.id.loginPageButton);
        EditText passTxt = findViewById(R.id.loginPasswordText);
        EditText emailTxt = findViewById(R.id.loginEmailidText);

        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passText = passTxt.getText().toString();
                String emailText = emailTxt.getText().toString();
                loginUser(emailText, passText);

            }
        });

    }


    private void loginUser(String emailidText, String passText) {
        auth.signInWithEmailAndPassword(emailidText, passText).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(LoginPage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginPage.this, LandingPage.class));
                finish();
            }
        });
    }
}