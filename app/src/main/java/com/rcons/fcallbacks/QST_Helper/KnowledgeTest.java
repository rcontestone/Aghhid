package com.rcons.fcallbacks.QST_Helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rcons.fcallbacks.R;

public class KnowledgeTest extends AppCompatActivity {
    Button btnSugarcane,btnMaze,btnWheat,btnCotton,btnRice;
    Button btnCitrus,btnPotatoes,btnTomato,btnCucumber,btnChili,btnMango;

    Cursor cursor;
    String region = "";
    TextView txtRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_test);

        region = getIntent().getStringExtra("region");

        txtRegion = findViewById(R.id.txt_region);
        txtRegion.setText(region);

////////Major Crops///////////
        btnSugarcane = findViewById(R.id.btn_sugarcane);
        btnMaze = findViewById(R.id.btn_maze);
        btnWheat = findViewById(R.id.btn_wheat);
        btnCotton = findViewById(R.id.btn_cotton);
        btnRice = findViewById(R.id.btn_rice);

////////Minorr Crops///////////
        btnCitrus = findViewById(R.id.btn_citrus);
        btnPotatoes = findViewById(R.id.btn_potatoes);
        btnTomato = findViewById(R.id.btn_tomato);
        btnCucumber = findViewById(R.id.btn_cucumber);
        btnChili = findViewById(R.id.btn_chili);
        btnMango = findViewById(R.id.btn_mango);


        ////////Major Crops///////////

        btnSugarcane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , region);
                intent.putExtra("Crop" , btnSugarcane.getTag().toString());
                startActivity(intent);
            }
        });

        btnMaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , region);
                intent.putExtra("Crop" , btnMaze.getTag().toString());
                startActivity(intent);
            }
        });

        btnWheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , region);
                intent.putExtra("Crop" , btnWheat.getTag().toString());
                startActivity(intent);
            }
        });

        btnCotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , region);
                intent.putExtra("Crop" , btnCotton.getTag().toString());
                startActivity(intent);
            }
        });

        btnRice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , region);
                intent.putExtra("Crop" , btnRice.getTag().toString());
                startActivity(intent);
            }
        });


        ////////////////Minor Crops///////////
        btnCitrus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , "");
                intent.putExtra("Crop" , btnCitrus.getTag().toString());
                startActivity(intent);
            }
        });


        btnPotatoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , "");
                intent.putExtra("Crop" , btnPotatoes.getTag().toString());
                startActivity(intent);
            }
        });


        btnTomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , "");
                intent.putExtra("Crop" , btnTomato.getTag().toString());
                startActivity(intent);
            }
        });

        btnCucumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , "");
                intent.putExtra("Crop" , btnCucumber.getTag().toString());
                startActivity(intent);
            }
        });

        btnChili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , "");
                intent.putExtra("Crop" , btnChili.getTag().toString());
                startActivity(intent);
            }
        });

        btnMango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(KnowledgeTest.this, Questions.class);
                intent.putExtra("region" , region);
                intent.putExtra("Crop" , btnMango.getTag().toString());
                startActivity(intent);
            }
        });

    }
}
