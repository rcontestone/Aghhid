package com.rcons.fcallbacks.VillageSearch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Adapter.TehsilAdapter;
import com.rcons.fcallbacks.Interfaces.onTehsilClick;
import com.rcons.fcallbacks.Model.Tehsil;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class TehsilActivity extends AppCompatActivity implements onTehsilClick {
    DatabaseAdapterSearch databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<Tehsil> tehsils;
    TehsilAdapter adapter ;
    String districtID = "";
    String districtName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tehsil);
        districtID = getIntent().getStringExtra("districtID");
        districtName = getIntent().getStringExtra("districtName");
        recyclerView = findViewById(R.id.rvDistrict);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(TehsilActivity.this, RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(manager);
        tehsils = new ArrayList<>();
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                manager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        databaseAccess = new DatabaseAdapterSearch(TehsilActivity.this);
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
                    if(adapter !=null){
                        adapter.getFilter().filter(text);
                    }

                }catch (Exception e){

                }

            }
        });


    }
    void readDataBase(){
        cursor = databaseAccess.GetTehsilData(districtID);
        if(cursor!=null&&cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do{

                    String tehsil_id = cursor.getString(cursor.getColumnIndex("tehsil_id"));
                    String tehsil_name = cursor.getString(cursor.getColumnIndex("tehsil_name"));
                    Tehsil tehsil = new Tehsil();
                    tehsil.setTehsilID(tehsil_id);
                    tehsil.setTehsilName(tehsil_name);
                    tehsils.add(tehsil);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        if(tehsils!=null&& tehsils.size()>0){
            adapter = new TehsilAdapter( tehsils, TehsilActivity.this);
            recyclerView.setAdapter(adapter);

        }

    }


    @Override
    public void onEachTehsilClick(Tehsil tehsil) {
        Intent intent = new Intent(TehsilActivity.this , VillageActivity.class);
        intent.putExtra("tehsilID" , tehsil.getTehsilID());
        intent.putExtra("tehsilName" , tehsil.getTehsilName());
        startActivity(intent);
        Toast.makeText(this, ""+tehsil.getTehsilName(), Toast.LENGTH_SHORT).show();

    }
}
