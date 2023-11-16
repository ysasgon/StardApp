package com.example.stardapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stardapp.dao.DAO;
import com.example.stardapp.model.Object;
import com.example.stardapp.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ProfileActivity extends AppCompatActivity {
    private static final Integer PURCHASE_ANIMAL=1;
    private static final Integer PURCHASE_CROP=2;
    private static final Integer PURCHASE_FISH=3;

    public static final Integer OBJECT_ACTIVITY = 3;

    private TextView usernameTextView, genderTextView, birthdayTextView, animalTextView, cropTextView, fishTextView, usernameText, genderText, birthdayText;
    private ImageView profileIcon, animalImageView, cropImageView, fishImageView;
    private Button logOutButton, purchaseAnimalButton, purchaseCropButton, purchaseFishButton;
    private ListView animalListView, cropListView, fishListView;
    private DAO dao;
    private EditText usernameEditText;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle bundleProfile=getIntent().getExtras();
        Intent origin = getIntent();

        dao = new DAO(ProfileActivity.this);

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
                intentSignIn.putExtra("ID", 2);
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
                intentAnimalPurchase.putExtra("USER_NAME",usernameText.getText().toString());
                intentAnimalPurchase.putExtra( "TYPE",1);
                startActivityForResult(intentAnimalPurchase,OBJECT_ACTIVITY);
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
                intentCropPurchase.putExtra("USER_NAME", usernameText.getText().toString());
                intentCropPurchase.putExtra( "TYPE",2);
                startActivityForResult(intentCropPurchase,OBJECT_ACTIVITY);
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
                intentFishPurchase.putExtra("USER_NAME",usernameText.getText().toString());
                intentFishPurchase.putExtra( "TYPE",3);
                startActivityForResult(intentFishPurchase,OBJECT_ACTIVITY);
            }
        });

        getFieldValues((User) origin.getSerializableExtra("USER"));
    }

    private void getFieldValues(User user) {
        if(!user.equals(null)){
            usernameText.setText(user.getName());
            genderText.setText(user.getGender());
            birthdayText.setText(user.getBirth_date().toString());
            //metodo que llama al DAO y recoge todos los objetos que posee el usuario, si no posee
            // el tipo de objeto de la lista, se introduce en la lista una fila con el mensaje
            // correspondiente(si no tiene animals, se mete un String con
            //
            ArrayList<String> listAnimals = new ArrayList();
            ArrayAdapter<String> adapterA = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, listAnimals);
            animalListView.setAdapter(adapterA);

            Set<Object> animals = dao.readObjects(usernameText.getText().toString(), 1);
            if (animals!=null){
                for (Object obj : animals) {
                    listAnimals.add(obj.getName()+"-"+obj.getQuantity());
                    adapterA.notifyDataSetChanged();
                }
            }else{
                listAnimals.add(getString(R.string.animalListEmpty));
                adapterA.notifyDataSetChanged();
            }

            ArrayList<String> listCrops = new ArrayList();
            ArrayAdapter<String> adapterC = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, listCrops);
            cropListView.setAdapter(adapterC);

            Set<Object> crops = dao.readObjects(usernameText.getText().toString(), 2);
            if (animals!=null) {
                for (Object obj : crops) {
                    listCrops.add(obj.getName() + "-" + obj.getQuantity());
                    adapterC.notifyDataSetChanged();
                }
            }else{
                listCrops.add(getString(R.string.cropListEmpty));
                adapterC.notifyDataSetChanged();
            }


            ArrayList<String> listFishes = new ArrayList();
            ArrayAdapter<String> adapterF = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, listFishes);
            fishListView.setAdapter(adapterF);

            Set<Object> fishes = dao.readObjects(usernameText.getText().toString(), 3);
            if (animals!=null) {
            for (Object obj : fishes) {
                listFishes.add(obj.getName()+"-"+obj.getQuantity());
                adapterF.notifyDataSetChanged();
            }
            }else{
                listFishes.add(getString(R.string.fishLisEmpty));
                adapterF.notifyDataSetChanged();
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){

        }else{

        }
    }
}