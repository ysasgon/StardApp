package com.example.stardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final Integer SIGN_UP_ACTIVITY = 1;
    public static final Integer PROFILE_ACTIVITY = 2;

    private DAO dao;

    private TextView signUpHyperlink;
    private EditText userTextField, passwordTextFile;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new DAO(MainActivity.this);

        signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(intent1,SIGN_UP_ACTIVITY);
            }
        });

        signUpHyperlink = findViewById(R.id.signUpHyperlink);
        signUpHyperlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}