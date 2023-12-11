package com.example.animeflix.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.animeflix.R;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt , passEdt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        intiView();
    }

    private void intiView() {
        userEdt = findViewById(R.id.editTextText);
        passEdt = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.LogInbutton);
        loginBtn.setOnClickListener(view -> {
            if(userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()){
                Toast.makeText(LoginActivity.this,"Please fill your userName and password",Toast.LENGTH_SHORT).show();


            } else if(userEdt.getText().toString().equals("admin") && passEdt.getText().toString().equals("admin")) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

    }
}