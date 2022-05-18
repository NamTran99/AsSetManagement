package com.son.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUsername,edtPasswords,edtCfPasswords;
    MaterialButton btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initUI();
        initClickListener();

    }

    private void initUI(){
        edtUsername = findViewById(R.id.edt_username_signup);
        edtPasswords = findViewById(R.id.edt_password_signup);
        edtCfPasswords = findViewById(R.id.edt_cf_password_signup);
        btnSignUp = findViewById(R.id.btn_signup_signup);
    }

    private void initClickListener(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MenuActivity.class);
                startActivity(intent);
            }

        });
    }
}