package com.example.stardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stardapp.dao.DAO;
import com.example.stardapp.model.Object;

import java.util.Set;

public class ObjectActivity extends AppCompatActivity {

    private TextView txt;
    private TableLayout tl;
    private Set<Object> objects;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_activity);

        tl = (TableLayout) findViewById(R.id.tableLayout);
        tl.removeAllViews();

        txt = findViewById(R.id.textViewType);

        Intent origin = getIntent();

        String name = origin.getStringExtra("USER_NAME");
        Integer type = origin.getIntExtra("TYPE",0);
        if(type==1){
            txt.setText(getString(R.string.type_animal));
        }else if(type==2){
            txt.setText(getString(R.string.type_crop));
        }else{
            txt.setText(getString(R.string.type_fish));
        }
        dao = new DAO(ObjectActivity.this);
        objects = dao.readObjects(name, type);
        if(objects!=null){
            for(Object obj : objects){
                View inf = LayoutInflater.from(this).inflate(R.layout.row_table,null,false);
                TextView tb0 = inf.findViewById(R.id.tb0);
                TextView tb1 = inf.findViewById(R.id.tb1);
                TextView tb2 = inf.findViewById(R.id.tb2);

                tb0.setText(obj.getName());
                tb1.setText(obj.getType().toString());
                tb2.setText(obj.getQuantity().toString());

                tl.addView(inf);
            }
        }
    }
}
