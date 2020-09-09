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

import com.rcons.fcallbacks.Adapter.EnterFormAdapter;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Interfaces.onEnterFormClick;
import com.rcons.fcallbacks.Model.EnterFormModel;
import com.rcons.fcallbacks.R;

import java.util.ArrayList;

public class SelectEnterFormActivity extends AppCompatActivity implements onEnterFormClick {
    DatabaseAdapter databaseAccess;
    Cursor cursor;
    EditText edtSearchBar;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    ArrayList<EnterFormModel> EnterFormModelArrayList;
    EnterFormAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_form);

        recyclerView = findViewById(R.id.rvEnterForm);
        edtSearchBar = findViewById(R.id.edtSearchBar);

        manager = new LinearLayoutManager(SelectEnterFormActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        EnterFormModelArrayList = new ArrayList<>();

        databaseAccess = new DatabaseAdapter(SelectEnterFormActivity.this);
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
            cursor = databaseAccess.getEnterFormInformation();
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {

                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        String emp_id =  cursor.getString(cursor.getColumnIndex("emp_id"));
                        String farmerID =  cursor.getString(cursor.getColumnIndex("farmer_id"));
                        String order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                        String farmer_name =  cursor.getString(cursor.getColumnIndex("farmer_name"));

                        String region =  cursor.getString(cursor.getColumnIndex("region"));
                        String district_basedata =  cursor.getString(cursor.getColumnIndex("district_basedata"));
                        String tehsil_basedata =  cursor.getString(cursor.getColumnIndex("tehsil_basedata"));
                        String village_muza_basedata =  cursor.getString(cursor.getColumnIndex("village_muza_basedata"));
                        String farmer_cellphone =  cursor.getString(cursor.getColumnIndex("farmer_cellphone"));

                        String ao_assigned =  cursor.getString(cursor.getColumnIndex("ao_assigned"));
                        String fa_assigned =  cursor.getString(cursor.getColumnIndex("fa_assigned"));
                        String ai_assigned =  cursor.getString(cursor.getColumnIndex("ai_assigned"));
                        




                        EnterFormModel enterform = new EnterFormModel();
                        enterform.setId(id);
                        enterform.setEmp_id(emp_id);
                        enterform.setFarmerID(farmerID);
                        enterform.setFarmerName(farmer_name);
                        enterform.setOrder_id(order_id);
                        enterform.setRegion(region);
                        enterform.setFarmerDistrict(district_basedata);
                        enterform.setFarmerTehsil(tehsil_basedata);
                        enterform.setFarmerVillage(village_muza_basedata);
                        enterform.setAi_name(ai_assigned);
                        enterform.setFa_name(fa_assigned);
                        enterform.setAo_name(ao_assigned);
                        enterform.setFarmer_cellphone(farmer_cellphone);



                        EnterFormModelArrayList.add(enterform);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
            if (EnterFormModelArrayList != null && EnterFormModelArrayList.size() > 0) {
                adapter = new EnterFormAdapter(EnterFormModelArrayList, SelectEnterFormActivity.this);
                recyclerView.setAdapter(adapter);

            }
        } catch (Exception e) {
            Log.e("TAG = " ,  e.toString());
        }


    }

    @Override
    public void onEachEnterFormClick(EnterFormModel enterFormModel) {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("id", enterFormModel.getId()+"");
        returnIntent.putExtra("employeeID", enterFormModel.getEmp_id()+"");
        returnIntent.putExtra("farmer_region", enterFormModel.getRegion()+"");
        returnIntent.putExtra("farmerID" , enterFormModel.getFarmerID()+"");
        returnIntent.putExtra("farmerName" , enterFormModel.getFarmerName()+"");
        returnIntent.putExtra("farmerVillage" , enterFormModel.getFarmerVillage()+"");
        returnIntent.putExtra("farmerDistrict" , enterFormModel.getFarmerDistrict()+"");
        returnIntent.putExtra("farmerTehsil" , enterFormModel.getFarmerTehsil()+"");
        returnIntent.putExtra("order_id" , enterFormModel.getOrder_id()+"");
        returnIntent.putExtra("ao_name" , enterFormModel.getAo_name()+"");
        returnIntent.putExtra("fa_name" , enterFormModel.getFa_name()+"");
        returnIntent.putExtra("ai_name" , enterFormModel.getAi_name()+"");

        returnIntent.putExtra("farmer_cellphone" , enterFormModel.getFarmer_cellphone());


        setResult(Activity.RESULT_OK, returnIntent);
        finish();


    }




    @Override
    public void onBackPressed() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("emp_id ",  "");
        returnIntent.putExtra("farmerID ", "");
        returnIntent.putExtra("farmerName ", "");
        returnIntent.putExtra("order_id ", "");


        setResult(Activity.RESULT_CANCELED, returnIntent);

        super.onBackPressed();
    }


}
