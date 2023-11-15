package com.example.stardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView usernameTextView, genderTextView, birthdayTextView, animalTextView, cropTextView, fishTextView;
    private ImageView profileIcon, animalImageView, cropImageView, fishImageView;
    private Button logOutButton, purchaseAnimalButton, purchaseCropButton, purchaseFishButton;
    private Spinner genderSpinner, birthdayYearSpinner, birthdayMonthSpinner, birthdayDaySpinner;
    private ListView animalListView, cropListView, fishListView;
    private EditText usernameEditText;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        birthdayTextView = (TextView) findViewById(R.id.birthdayTextView);
        birthdayDaySpinner = (Spinner) findViewById(R.id.birthdayDaySpinner);
        birthdayMonthSpinner = (Spinner) findViewById(R.id.birthdayMonthSpinner);
        birthdayYearSpinner = (Spinner) findViewById(R.id.birthdayYearSpinner);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        animalTextView = (TextView) findViewById(R.id.animalTextView);
        animalListView = (ListView) findViewById(R.id.animalListView);
        animalImageView = (ImageView) findViewById(R.id.animalImageView);
        purchaseAnimalButton = (Button) findViewById(R.id.purchaseAnimalButton);
        purchaseAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cropTextView = (TextView) findViewById(R.id.cropTextView);
        cropListView = (ListView) findViewById(R.id.cropListView);
        cropImageView = (ImageView) findViewById(R.id.cropImageView);
        purchaseCropButton = (Button) findViewById(R.id.purchaseCropButton);
        purchaseCropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fishTextView = (TextView) findViewById(R.id.fishTextView);
        fishListView = (ListView) findViewById(R.id.fishListView);
        fishImageView = (ImageView) findViewById(R.id.fishImageView);
        purchaseFishButton = (Button) findViewById(R.id.purchaseFishButton);
        purchaseFishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fillLists();
    }

    private void fillLists() {
    }
}