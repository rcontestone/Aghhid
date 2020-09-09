package com.rcons.fcallbacks.Main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Questionnaire.Q_sectionB_a_b;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.VillageSearch.SelectEnterFormActivity;

public class SelectEnterFarmer extends AppCompatActivity {

    DatabaseAdapter databaseAccess;
   Button btn_selectFarmer;
   Button btn_next;
   TextView txt_EmpID, txt_OrderID, txt_FarmerID;

    String region = "";
    String emp_id = "";
    String id = "";
    String order_id = "";
    String FarmerID = "";
    String FarmerName = "";
    String farmer_cellphone = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String ao_name = "";
    String fa_name = "";
    String ai_name = "";
    String b26 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_select_enter_farmer);

        databaseAccess = new DatabaseAdapter(SelectEnterFarmer.this);
        databaseAccess.Open();


        region = getIntent().getStringExtra("region");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        FarmerID = getIntent().getStringExtra("farmerID");
        ao_name = getIntent().getStringExtra("ao_name");
        fa_name = getIntent().getStringExtra("fa_name");
        ai_name = getIntent().getStringExtra("ai_name");

        b26 = getIntent().getStringExtra("b26");

        btn_selectFarmer =  findViewById(R.id.btn_selectFarmer);
        btn_next =  findViewById(R.id.btn_next);
        txt_EmpID =  findViewById(R.id.txt_EmpID);
        txt_OrderID =  findViewById(R.id.txt_OrderID);
        txt_FarmerID =  findViewById(R.id.txt_FarmerID);






        btn_selectFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(SelectEnterFarmer.this , SelectEnterFormActivity.class);
                    startActivityForResult(intent , 99);
                }catch (Exception e){
                    Log.e("TAG"  , ""+e.toString());

                }

            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (StringUtils.isEmpty(emp_id) || StringUtils.isEmpty(order_id)  || StringUtils.isEmpty(FarmerID)) {
                        Toast.makeText(SelectEnterFarmer.this, "Please Select Farmer ID", Toast.LENGTH_SHORT).show();
                    }

                    else {

                        Intent intent = new Intent(SelectEnterFarmer.this ,Q_sectionB_a_b.class);

                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmerID", FarmerID);
                        intent.putExtra("region" , region);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("ao_name", ao_name);
                        intent.putExtra("fa_name", fa_name);
                        intent.putExtra("ai_name", ai_name);
                        intent.putExtra("b26", b26);

                        startActivityForResult(intent , 99);
                    }

                }catch (Exception e){
                    Log.e("TAG"  , ""+e.toString());

                }

            }
        });


    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 99){

                id= data.getStringExtra("id");
                emp_id= data.getStringExtra("employeeID");
                region= data.getStringExtra("farmer_region");
                FarmerID= data.getStringExtra("farmerID");

                FarmerName= data.getStringExtra("farmerName");
                farmerVillage= data.getStringExtra("farmerVillage");
                farmerDistrict= data.getStringExtra("farmerDistrict");
                farmerTehsil= data.getStringExtra("farmerTehsil");
                order_id= data.getStringExtra("order_id");
                ao_name= data.getStringExtra("ao_name");
                fa_name= data.getStringExtra("fa_name");
                ai_name= data.getStringExtra("ai_name");
                farmer_cellphone= data.getStringExtra("farmer_cellphone");



                txt_EmpID.setText(emp_id);
                txt_OrderID.setText(order_id);
                txt_FarmerID.setText(FarmerID);



            }
        }


    }
}
