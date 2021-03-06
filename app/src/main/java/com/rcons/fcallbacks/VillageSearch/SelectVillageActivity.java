package com.rcons.fcallbacks.VillageSearch;

import android.app.Activity;
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

import com.rcons.fcallbacks.Adapter.SelectVillageAdapter;
import com.rcons.fcallbacks.Interfaces.onVillageClick;
import com.rcons.fcallbacks.Model.Village;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class SelectVillageActivity extends AppCompatActivity implements onVillageClick {
    DatabaseAdapterSearch databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<Village> villages;
    SelectVillageAdapter adapter ;
    String districtID = "";
    String districtName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village);
        districtID = getIntent().getStringExtra("districtID");
        districtName = getIntent().getStringExtra("districtName");
        if(districtName.contains("District") || districtName.contains("DISTRICT")){
            districtName =   districtName.replace("District" , "");
            districtName =    districtName.replace("DISTRICT" , "");
        }
        recyclerView = findViewById(R.id.rvDistrict);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(SelectVillageActivity.this, RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(manager);
        villages = new ArrayList<>();
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                manager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        databaseAccess = new DatabaseAdapterSearch(SelectVillageActivity.this);
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
        cursor = databaseAccess.GetVillageDataDistrictBased(districtName.trim());
        if(cursor!=null&&cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do{

                    String mauza_village_id = cursor.getString(cursor.getColumnIndex("mauza_village_id"));
                    String mauza_village_name = cursor.getString(cursor.getColumnIndex("mauza_village_name"));
                    String union_council_id = cursor.getString(cursor.getColumnIndex("union_council_id"));
                    String union_council_name = cursor.getString(cursor.getColumnIndex("union_council_name"));
                    String tehsil_id = cursor.getString(cursor.getColumnIndex("tehsil_id"));
                    String tehsil_name = cursor.getString(cursor.getColumnIndex("tehsil_name"));
                    Village village = new Village();
                    village.setVillageID(mauza_village_id);
                    village.setVillageName(mauza_village_name);
                    village.setUnionConcilID(union_council_id);
                    village.setUnionConcilName(union_council_name);

                    village.setTehsilID(tehsil_id);
                    village.setTehsilName(tehsil_name);

                    villages.add(village);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        if(villages!=null&& villages.size()>0){
            adapter = new SelectVillageAdapter(villages, SelectVillageActivity.this);
            recyclerView.setAdapter(adapter);

        }

    }



    @Override
    public void onEachVillageClick(Village village) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("villageID" , village.getVillageID()+"");
        returnIntent.putExtra("villageName" , village.getVillageName());
        returnIntent.putExtra("unionConcilID" , village.getUnionConcilID());
        returnIntent.putExtra("unionConsilName" , village.getUnionConcilName());
        returnIntent.putExtra("tehsilID" , village.getTehsilID());
        returnIntent.putExtra("tehsilName" , village.getTehsilName());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        Toast.makeText(this, ""+village.getVillageName(), Toast.LENGTH_SHORT).show();

    }
}
