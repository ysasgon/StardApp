package com.example.stardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stardapp.dao.DAO;

public class ProfileActivity extends AppCompatActivity {
    private static final Integer PURCHASE_ANIMAL=1;
    private static final Integer PURCHASE_CROP=2;
    private static final Integer PURCHASE_FISH=3;

    private TextView usernameTextView, genderTextView, birthdayTextView, animalTextView, cropTextView, fishTextView, usernameText, genderText, birthdayText;
    private ImageView profileIcon, animalImageView, cropImageView, fishImageView;
    private Button logOutButton, purchaseAnimalButton, purchaseCropButton, purchaseFishButton;
    private ListView animalListView, cropListView, fishListView;
    private DAO db;
    private EditText usernameEditText;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle bundleProfile=getIntent().getExtras();

        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        usernameText = (TextView) findViewById(R.id.usernameText);
        genderTextView = (TextView) findViewById(R.id.genderTextView);
        genderText = (TextView) findViewById(R.id.genderText);
        birthdayTextView = (TextView) findViewById(R.id.birthdayTextView);
        birthdayText = (TextView) findViewById(R.id.birthdayText);
        logOutButton = (Button) findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignIn = new Intent();
                setResult(RESULT_OK,intentSignIn);
                finish();
            }
        });

        animalTextView = (TextView) findViewById(R.id.animalTextView);
        animalListView = (ListView) findViewById(R.id.animalListView);
        animalImageView = (ImageView) findViewById(R.id.animalImageView);
        purchaseAnimalButton = (Button) findViewById(R.id.purchaseAnimalButton);
        purchaseAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnimalPurchase = new Intent(ProfileActivity.this, ObjectActivity.class);
                intentAnimalPurchase.putExtra((String) usernameTextView.getText(),"user");
                intentAnimalPurchase.putExtra( "0","type");
                startActivity(intentAnimalPurchase);
            }
        });

        cropTextView = (TextView) findViewById(R.id.cropTextView);
        cropListView = (ListView) findViewById(R.id.cropListView);
        cropImageView = (ImageView) findViewById(R.id.cropImageView);
        purchaseCropButton = (Button) findViewById(R.id.purchaseCropButton);
        purchaseCropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCropPurchase = new Intent(ProfileActivity.this, ObjectActivity.class);
                intentCropPurchase.putExtra((String) usernameTextView.getText(),"user");
                intentCropPurchase.putExtra( "1","type");
                startActivity(intentCropPurchase);
            }
        });

        fishTextView = (TextView) findViewById(R.id.fishTextView);
        fishListView = (ListView) findViewById(R.id.fishListView);
        fishImageView = (ImageView) findViewById(R.id.fishImageView);
        purchaseFishButton = (Button) findViewById(R.id.purchaseFishButton);
        purchaseFishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFishPurchase = new Intent(ProfileActivity.this, ObjectActivity.class);
                intentFishPurchase.putExtra((String) usernameTextView.getText(),"user");
                intentFishPurchase.putExtra( "2","type");
                startActivity(intentFishPurchase);
            }
        });

        fillLists();
    }

    private void fillLists() {
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){

        }else{

        }
    }
}