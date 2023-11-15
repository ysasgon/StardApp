package com.example.stardapp;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Set;

public class ObjectActivity extends AppCompatActivity {

    private TableLayout tl = null;
    private Set<Object> objects = null;
    private DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tl = findViewById(R.id.tableLayout);
        tl.removeAllViews();

        String uwu = getIntent().getExtras().getString("user");
        String uwu2 = getIntent().getExtras().getString("type");

        dao = new DAO(ObjectActivity.this);
        objects = dao.readObjects(uwu);

        for(Object obj : objects){
            View inf = LayoutInflater.from(this).inflate(R.layout.row_table,null,false);
            TextView tb0 = inf.findViewById(R.id.tb0);
            TextView tb1 = inf.findViewById(R.id.tb1);
            TextView tb2 = inf.findViewById(R.id.tb2);

            tb0.setText(obj.getName());
            tb1.setText(obj.getType());
            tb2.setText(obj.getQuantity());

            tl.addView(inf);
        }


    }
}
