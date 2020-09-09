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

import com.rcons.fcallbacks.Adapter.DistrictAdapter;
import com.rcons.fcallbacks.Interfaces.onDistrictClick;
import com.rcons.fcallbacks.Model.District;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onDistrictClick {
    DatabaseAdapterSearch databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<District> districts;
    DistrictAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvDistrict);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(manager);
        districts = new ArrayList<>();
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                manager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        databaseAccess = new DatabaseAdapterSearch(MainActivity.this);
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
        cursor = databaseAccess.GetDistrictData();
        if(cursor!=null&&cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do{

                    String districtID = cursor.getString(cursor.getColumnIndex("district_id"));
                    String districtName = cursor.getString(cursor.getColumnIndex("district_name"));
                    District district = new District();
                    district.setDistrictID(districtID);
                    district.setDistrictName(districtName);
                    districts.add(district);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        if(districts!=null&& districts.size()>0){
            adapter = new DistrictAdapter( districts, MainActivity.this);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onEachDistrictClick(District district) {
        Intent intent = new Intent(MainActivity.this , TehsilActivity.class);
        intent.putExtra("districtID" , district.getDistrictID());
        intent.putExtra("districtName" , district.getDistrictName());
        startActivity(intent);
        Toast.makeText(this, ""+district.getDistrictName(), Toast.LENGTH_SHORT).show();
    }
}
