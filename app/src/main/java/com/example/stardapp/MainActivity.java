package com.example.stardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stardapp.dao.DAO;
import com.example.stardapp.model.User;

import java.text.ParseException;

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
        userTextField = findViewById(R.id.si_userTextField);
        userTextField.setText("");
        passwordTextFile = findViewById(R.id.si_passwordTextField);
        passwordTextFile.setText("");
        signInButton = findViewById(R.id.si_signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!userTextField.getText().toString().isEmpty()&&!passwordTextFile.getText().toString().isEmpty()){
                        User user = dao.signIn(userTextField.getText().toString(),passwordTextFile.getText().toString());
                        if(user!=null){
                            Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                            profile.putExtra( "USER", user);
                            startActivityForResult(profile,PROFILE_ACTIVITY);
                        }else{
                            Toast.makeText(MainActivity.this, R.string.sign_in_wrong_auth, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, R.string.missing_fields, Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, R.string.exception, Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUpHyperlink = findViewById(R.id.si_signUpHyperlink);
        signUpHyperlink.setTextColor(Color.BLUE);
        signUpHyperlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up = new Intent(MainActivity.this, SignUpActivity.class);
                startActivityForResult(sign_up,SIGN_UP_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (data.getIntExtra("ID",0)){
                case 1:
                    userTextField.setText(data.getStringExtra("USER_NAME"));
                    Toast.makeText(MainActivity.this, R.string.sign_up_success, Toast.LENGTH_SHORT).show();
                    break;
                case 2:

                    break;
            }
        }else{
            switch (data.getIntExtra("ID",0)){
                case 1:
                    Toast.makeText(MainActivity.this, R.string.sign_up_canceled, Toast.LENGTH_SHORT).show();
                    break;
                case 2:

                    break;
            }
        }
    }
}