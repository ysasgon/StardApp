package com.example.stardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stardapp.dao.DAO;
import com.example.stardapp.exceptions.UserAlreadyExistsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private Button signUp,cancel,dateButton;
    private DAO dao;
    private EditText userTextField,password,conf_password;
    private RadioGroup rgGender;
    private RadioButton rb;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dao = new DAO(SignUpActivity.this);

        rgGender = findViewById(R.id.su_radioGroupGender);

        date=findViewById(R.id.su_date);
        userTextField = findViewById(R.id.su_userTextField);
        password = findViewById(R.id.su_passwordTextField);
        conf_password = findViewById(R.id.su_confirmPasswordTextField);

        dateButton = findViewById(R.id.su_dateButton);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int cYear = c.get(Calendar.YEAR);
                int cMonth = c.get(Calendar.MONTH);
                int cDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog diag = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(String.valueOf(year)+"-"+String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth));
                    }
                }, cYear, cMonth, cDay);
                diag.show();
            }
        });

        signUp = findViewById(R.id.su_signUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date cDate = new Date();
                try{
                    if(!password.getText().toString().isEmpty()
                            &&!conf_password.getText().toString().isEmpty()
                            &&!userTextField.getText().toString().isEmpty()){
                        if(password.getText().toString().equals(conf_password.getText().toString())){
                            if(rgGender.getCheckedRadioButtonId()!=-1){
                                if(!date.getText().toString().equals(getString(R.string.sign_up_date))){
                                    Date strDate = sdf.parse(date.getText().toString());
                                    if(cDate.after(strDate)){
                                        rb = findViewById(rgGender.getCheckedRadioButtonId());
                                        dao.signUp(userTextField.getText().toString(),password.getText().toString(),rb.getText().toString(),date.getText().toString());
                                        Intent intent = new Intent();
                                        intent.putExtra("ID", 1);
                                        intent.putExtra("USER_NAME", userTextField.getText().toString());
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }else{
                                        Toast.makeText(SignUpActivity.this, R.string.sign_up_birth_date_invalid, Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(SignUpActivity.this, R.string.sign_up_birth_date_missing, Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(SignUpActivity.this, R.string.sign_up_gender_missing, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUpActivity.this, R.string.sign_up_not_matching_passwords, Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, R.string.missing_fields, Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLiteConstraintException e) {
                    Toast.makeText(SignUpActivity.this, R.string.sign_up_user_already_exists, Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(SignUpActivity.this, R.string.exception, Toast.LENGTH_SHORT).show();
                } catch (UserAlreadyExistsException e) {
                    Toast.makeText(SignUpActivity.this, R.string.sign_up_user_already_exists, Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel = findViewById(R.id.su_cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ID", 1);
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });

    }
}