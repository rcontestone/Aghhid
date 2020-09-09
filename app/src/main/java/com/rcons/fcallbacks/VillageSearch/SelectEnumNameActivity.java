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

import com.rcons.fcallbacks.Adapter.EnumNameAdapter;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Interfaces.onEnumNameClick;
import com.rcons.fcallbacks.Model.EnumNameModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class SelectEnumNameActivity extends AppCompatActivity implements onEnumNameClick {
    DatabaseAdapter databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<EnumNameModel> enumNameArrayList;
    EnumNameAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum_name);

        recyclerView = findViewById(R.id.rvEnumName);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(SelectEnumNameActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        enumNameArrayList = new ArrayList<>();

        databaseAccess = new DatabaseAdapter(SelectEnumNameActivity.this);
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
            cursor = databaseAccess.getEnumNameCodeInformation();
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {

                        String enumCode = cursor.getString(cursor.getColumnIndex("enum_code"));
                        String enumName = cursor.getString(cursor.getColumnIndex("enum_name"));

                        EnumNameModel enumNameCode = new EnumNameModel();
                        enumNameCode.setEnumCode(enumCode);
                        enumNameCode.setEnumName(enumName);

                        enumNameArrayList.add(enumNameCode);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
            if (enumNameArrayList != null && enumNameArrayList.size() > 0) {
                adapter = new EnumNameAdapter(enumNameArrayList, SelectEnumNameActivity.this);
                recyclerView.setAdapter(adapter);

            }
        }catch (Exception e){
            Log.e("TAG" , ""+e.toString());
        }


    }


    @Override
    public void onEachEnumNameClick(EnumNameModel enumNameModel) {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("enumCode" , enumNameModel.getEnumCode()+"");
        returnIntent.putExtra("enumName" , enumNameModel.getEnumName());

        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }

    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("enumCode" , "");
        returnIntent.putExtra("enumName" ,"");

        setResult(Activity.RESULT_OK, returnIntent);

        super.onBackPressed();
    }
}
