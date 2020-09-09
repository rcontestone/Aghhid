package com.rcons.fcallbacks.EditForm;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Adapter.EditFarmerAdapter;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Interfaces.onFarmerClick;
import com.rcons.fcallbacks.Model.Farmer;
import com.rcons.fcallbacks.Questionnaire.Section_A;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditFormActivity extends AppCompatActivity implements onFarmerClick {


    DatabaseAdapter adapter;
    Cursor cursor;


    @BindView(R.id.edtSearchBar)
    EditText edtSearchBar;
    @BindView(R.id.rvFarmerList)
    RecyclerView rvFarmerList;
    @BindView(R.id.txtNoDataFound)
    TextView txtNoDataFound;

    @BindView(R.id.btnBack)
    ImageView btnBack;


    LinearLayoutManager manager;
    ArrayList<Farmer> farmerArrayList;
    EditFarmerAdapter editFarmerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_form);
        ButterKnife.bind(this);

        manager = new LinearLayoutManager(EditFormActivity.this, RecyclerView.VERTICAL, false);
        rvFarmerList.setLayoutManager(manager);
        farmerArrayList = new ArrayList<>();


        adapter = new DatabaseAdapter(EditFormActivity.this);
        adapter.Open();

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
                        editFarmerAdapter.getFilter().filter(text);
                    }

                } catch (Exception e) {

                }

            }
        });

    }

    void readDataBase() {
        cursor = adapter.getEditFormFarmerData();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Farmer farmer = new Farmer();
                    String farmer_id = cursor.getString(cursor.getColumnIndex("farmer_id"));
                    String farmerTehsil = cursor.getString(cursor.getColumnIndex("tehsil_basedata"));
                    farmer.setFarmerID(farmer_id);
                    farmer.setFarmerName(farmerTehsil);
                    farmerArrayList.add(farmer);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if (farmerArrayList != null && farmerArrayList.size() > 0) {
            txtNoDataFound.setVisibility(View.GONE);
            rvFarmerList.setVisibility(View.VISIBLE);
            editFarmerAdapter = new EditFarmerAdapter(farmerArrayList, EditFormActivity.this);
            rvFarmerList.setAdapter(editFarmerAdapter);
        } else {
            txtNoDataFound.setVisibility(View.VISIBLE);
            rvFarmerList.setVisibility(View.GONE);
        }


    }

    @Override
    public void onEachFarmerClick(Farmer farmer) {
        try {


        if (farmer != null) {
            boolean isAlternate = false;
            String farmerID = farmer.getFarmerID();
             Cursor cursor = adapter.getSpecificFromCallBackTable(farmerID);
            //Cursor cursor = null;
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                String altSc1 = cursor.getString(cursor.getColumnIndex("sc1_alt"));
                String altSc2 = cursor.getString(cursor.getColumnIndex("sc2_alt"));
                String altSc3 = cursor.getString(cursor.getColumnIndex("sc3_alt"));
                String sc1 = cursor.getString(cursor.getColumnIndex("sc1"));
                String sc2 = cursor.getString(cursor.getColumnIndex("sc2"));
                String sc3 = cursor.getString(cursor.getColumnIndex("sc3"));
                String altFarmerCellphone = cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone"));
                String farmerCellphone = cursor.getString(cursor.getColumnIndex("farmer_cellphone"));
                String emp_id = cursor.getString(cursor.getColumnIndex("emp_id"));
                String surveyStatus = cursor.getString(cursor.getColumnIndex("survey_status"));
                if (StringUtils.isEmpty(surveyStatus) || StringUtils.isEmpty(altFarmerCellphone)) {
                    isAlternate = false;
                } else if (!StringUtils.isEmpty(farmerCellphone) && !StringUtils.isEmpty(altFarmerCellphone) && farmerCellphone.equalsIgnoreCase(altFarmerCellphone)) {
                    isAlternate = false;
                } else if (StringUtils.isEmpty(sc1) || StringUtils.isEmpty(sc2) || StringUtils.isEmpty(sc3)) {
                    isAlternate = false;
                } else if (StringUtils.isEmpty(altSc1) || StringUtils.isEmpty(altSc2) || StringUtils.isEmpty(altSc3)) {
                    isAlternate = true;

                }
            }


            Intent intent = new Intent(EditFormActivity.this, Section_A.class);
            intent.putExtra("id", cursor.getString(cursor.getColumnIndex("id")));
            intent.putExtra("farmerID", cursor.getString(cursor.getColumnIndex("farmer_id")));
            intent.putExtra("region", cursor.getString(cursor.getColumnIndex("region")));
            intent.putExtra("emp_id", cursor.getString(cursor.getColumnIndex("emp_id")));
            intent.putExtra("order_id", cursor.getString(cursor.getColumnIndex("order_id")));

            intent.putExtra("isFromEditActivity", true);


            if (isAlternate) {
                intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone")));
            } else {
                intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("farmer_cellphone")));
            }
            intent.putExtra("farmerDistrict", cursor.getString(cursor.getColumnIndex("district_basedata")));
            intent.putExtra("farmerVillage", cursor.getString(cursor.getColumnIndex("village_muza_basedata")));
            intent.putExtra("farmerTehsil", cursor.getString(cursor.getColumnIndex("tehsil_basedata")));
            intent.putExtra("ao_name", cursor.getString(cursor.getColumnIndex("ao_assigned")));
            intent.putExtra("fa_name", cursor.getString(cursor.getColumnIndex("fa_assigned")));
            intent.putExtra("ai_name", cursor.getString(cursor.getColumnIndex("ai_assigned")));
            intent.putExtra("isAlternateFarmer", isAlternate);
            startActivity(intent);
        }
        } catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


    }


}
