package com.rcons.fcallbacks.VillageSearch;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Adapter.ZaatAdapter;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Interfaces.onZaatClick;
import com.rcons.fcallbacks.Model.ZaatModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class SelectZaatActivity extends AppCompatActivity implements onZaatClick {
    DatabaseAdapter databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<ZaatModel> zaatModelArrayList;
    ZaatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaat);

        recyclerView = findViewById(R.id.rvZaat);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(SelectZaatActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        zaatModelArrayList = new ArrayList<>();

        databaseAccess = new DatabaseAdapter(SelectZaatActivity.this);
        databaseAccess.Open();
        readDataBase();

        edtSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String text = edtSearchBar.getText().toString().toLowerCase();
                    if (adapter != null) {
                        adapter.getFilter().filter(text);
                    }

                } catch (Exception e) {

                }

            }
        });


    }

    void readDataBase() {
        try {
            cursor = databaseAccess.getZaatCodeInformation();
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {

                        String zaatCode = cursor.getString(cursor.getColumnIndex("zaat_code"));
                        String zaatName = cursor.getString(cursor.getColumnIndex("zaat_name"));

                        ZaatModel zaat = new ZaatModel();
                        zaat.setZaatCode(zaatCode);
                        zaat.setZaatName(zaatName);

                        zaatModelArrayList.add(zaat);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
            if (zaatModelArrayList != null && zaatModelArrayList.size() > 0) {
                adapter = new ZaatAdapter(zaatModelArrayList, SelectZaatActivity.this);
                recyclerView.setAdapter(adapter);

            }
        } catch (Exception e) {
            Log.e("TAG", "" + e.toString());
        }


    }


    @Override
    public void onEachZaatClick(ZaatModel zaatModel) {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("zaatCode", zaatModel.getZaatCode() + "");
        returnIntent.putExtra("zaatName", zaatModel.getZaatName());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("zaatCode", "");
        returnIntent.putExtra("zaatName", "");

        setResult(Activity.RESULT_OK, returnIntent);

        super.onBackPressed();
    }
}
