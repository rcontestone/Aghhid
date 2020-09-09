package com.rcons.fcallbacks.VillageSearch;

import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Adapter.CropAdapter;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Interfaces.onVillageClick;
import com.rcons.fcallbacks.Model.CropModel;
import com.rcons.fcallbacks.Model.Village;
import com.rcons.fcallbacks.R;

public class CropActivity extends AppCompatActivity implements onVillageClick {
    DatabaseAdapter databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;

    CropModel cropArrayList ;
    CropAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        recyclerView = findViewById(R.id.rvDistrict);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(CropActivity.this, RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(manager);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                manager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        databaseAccess = new DatabaseAdapter(CropActivity.this);
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
                     //   adapter.getFilter().filter(text);
                    }

                }catch (Exception e){

                }

            }
        });


    }
    void readDataBase(){
        cursor = databaseAccess.getCropIDInformation();
        if(cursor!=null&&cursor.getCount()>0){
            cropArrayList  = new CropModel();
            if (cursor.moveToFirst()){
                do{

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));

                    cropArrayList.getCropID().add(crop_id);
                    cropArrayList.getCropUrduName().add(crop_name);


                //    cropArrayList.add(crop);
                }while(cursor.moveToNext());
            }
            cursor.close();
        }
        if(cropArrayList!=null&& cropArrayList.getCropID().size()>0){
            adapter = new CropAdapter(cropArrayList, CropActivity.this);
            recyclerView.setAdapter(adapter);

        }

    }



    @Override
    public void onEachVillageClick(Village village) {
        Toast.makeText(this, ""+village.getVillageName(), Toast.LENGTH_SHORT).show();

    }
}
