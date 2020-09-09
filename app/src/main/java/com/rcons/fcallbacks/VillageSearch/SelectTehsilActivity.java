package com.rcons.fcallbacks.VillageSearch;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Adapter.SelectTehsilAdapter;
import com.rcons.fcallbacks.Interfaces.onTehsilClick;
import com.rcons.fcallbacks.Model.Tehsil;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class SelectTehsilActivity extends AppCompatActivity implements onTehsilClick {
    DatabaseAdapterSearch databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<Tehsil> tehsils;
    SelectTehsilAdapter adapter;

    ImageView btniBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tehsil);

        btniBack = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.rvDistrict);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(SelectTehsilActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        tehsils = new ArrayList<>();


        databaseAccess = new DatabaseAdapterSearch(SelectTehsilActivity.this);
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
        btniBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("tehsilName", edtSearchBar.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        Toast.makeText(this, "" + edtSearchBar.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    void readDataBase() {
        cursor = databaseAccess.GetSelectTehsilDataDistrictBased();
        int count = 1;
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    String tehsil_name = cursor.getString(cursor.getColumnIndex("tehsil_name"));
                    Tehsil tehsil = new Tehsil();
                    Integer count_ =  count ++;
                    tehsil.setTehsilID(Integer.toString(count_));
                    tehsil.setTehsilName(tehsil_name);
                    tehsils.add(tehsil);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if (tehsils != null && tehsils.size() > 0) {
            adapter = new SelectTehsilAdapter(tehsils, SelectTehsilActivity.this);
            recyclerView.setAdapter(adapter);

        }

    }


    @Override
    public void onEachTehsilClick(Tehsil tehsil) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("tehsilName", tehsil.getTehsilName());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        Toast.makeText(this, "" + tehsil.getTehsilName(), Toast.LENGTH_SHORT).show();

    }
}
