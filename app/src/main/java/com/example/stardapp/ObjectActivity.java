package com.example.stardapp;

import android.content.Context;
import   android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stardapp.dao.DAO;
import com.example.stardapp.model.Object;

import java.util.Set;

public class ObjectActivity extends AppCompatActivity {

    private TextView txt;
    private TableLayout tl;
    private Set<Object> objects;
    private DAO dao;
    private Button exitBut, contBut;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_activity);

        tl = (TableLayout) findViewById(R.id.tableLayout);
        tl.removeAllViews();

        txt = findViewById(R.id.textViewType);
        exitBut = findViewById(R.id.oExit);
        contBut = findViewById(R.id.oSave);



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
                tb1.setText(obj.getQuantity().toString());
                tb2.setText(obj.getQuantity().toString());

                tl.addView(inf);
            }
        }

        exitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        contBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0, j = tl.getChildCount(); i < j; i++) {
                    View view = tl.getChildAt(i);
                    if (view instanceof TableRow) {

                        TableRow row = (TableRow) view;

                        View cellView1 = row.getChildAt(0);
                        View cellView2 = row.getChildAt(1);
                        View cellView3 = row.getChildAt(2);

                        String textoCelda1 = ((TextView) cellView1).getText().toString();
                        String textoCelda2 = ((TextView) cellView2).getText().toString();
                        String textoCelda3 = ((TextView) cellView3).getText().toString();


                        if( textoCelda2 != textoCelda3 && textoCelda3.matches("\\d+")) {
                            dao.updateObj(name,textoCelda1,type.toString(),textoCelda3);
                            setResult(RESULT_OK,intent);
                            finish();
                        }else{
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Data input error", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_CANCELED,intent);
                            finish();
                        }
                    }
                }
            }
        });
    }
}
