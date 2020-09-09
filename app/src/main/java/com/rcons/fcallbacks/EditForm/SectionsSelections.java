package com.rcons.fcallbacks.EditForm;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Questionnaire.Q_SectionG_b;
import com.rcons.fcallbacks.Questionnaire.Q_section1;
import com.rcons.fcallbacks.Questionnaire.Q_sectionB_a_b;
import com.rcons.fcallbacks.Questionnaire.Q_sectionC;
import com.rcons.fcallbacks.Questionnaire.Q_sectionD;
import com.rcons.fcallbacks.Questionnaire.Q_sectionE;
import com.rcons.fcallbacks.Questionnaire.Q_sectionG;
import com.rcons.fcallbacks.Questionnaire.Q_sectionH;
import com.rcons.fcallbacks.Questionnaire.Section_F_table1;
import com.rcons.fcallbacks.Questionnaire.Section_F_table2;
import com.rcons.fcallbacks.Questionnaire.Section_F_table4;
import com.rcons.fcallbacks.Questionnaire.Section_F_table5;
import com.rcons.fcallbacks.Questionnaire.Section_F_table6;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.StringUtils;

public class SectionsSelections extends AppCompatActivity {
    DatabaseAdapter databaseAccess;
    String userName = "";
    String region = "";
    String emp_id = "";
    String id = "";
    String edit_form = "";
    String order_id = "";
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String farmer_cellphone = "";
    String ao_name = "";
    String fa_name = "";
    String ai_name = "";
    TextView txtFarmerID, txtEmpID, txtOrderID;
    TextView txtVFarmerID, txtVEmpID, txtVOrderID, txtforBAndC;
    private CardView txtSection_B, txtSection_C, txtSection_D, txtSection_E, txtSection_ftable_1, txtSection_G, txtSection_H, txtSection_1;
    boolean isFromEdit = true;

    String b26 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.sections_selections);

        FarmerID = getIntent().getStringExtra("farmerID");

        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        region = getIntent().getStringExtra("region");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        id = getIntent().getStringExtra("id");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");
        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        ao_name = getIntent().getStringExtra("ao_name");
        fa_name = getIntent().getStringExtra("fa_name");
        ai_name = getIntent().getStringExtra("ai_name");
        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtEmpID = findViewById(R.id.txt_emp_id);
        txtOrderID = findViewById(R.id.txt_order_id);
        txtVFarmerID = findViewById(R.id.txtV_farmer_id);
        txtVEmpID = findViewById(R.id.txtV_emp_id);
        txtVOrderID = findViewById(R.id.txtV_order_id);

        txtVFarmerID.setText(FarmerID);
        txtVEmpID.setText(emp_id);
        txtVOrderID.setText(order_id);

        txtforBAndC = findViewById(R.id.txtforBAndC);

        txtSection_B = findViewById(R.id.txt_Section_B);
        txtSection_C = findViewById(R.id.txt_Section_C);
        txtSection_D = findViewById(R.id.txt_Section_D);
        txtSection_E = findViewById(R.id.txt_Section_E);
        txtSection_G = findViewById(R.id.txt_Section_G);
        txtSection_H = findViewById(R.id.txt_Section_H);
        txtSection_1 = findViewById(R.id.txt_Section_1);
        txtSection_ftable_1 = findViewById(R.id.txt_Section_Ftable1);

        readSection_b();
        readSection_c();


        txtSection_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_sectionB_a_b.class);
            }
        });
        txtSection_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_sectionC.class);
            }
        });
        txtSection_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_sectionD.class);
            }
        });
        txtSection_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_sectionE.class);
            }
        });
        txtSection_G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_sectionG.class);
            }
        });
        txtSection_H.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_sectionH.class);
            }
        });
        txtSection_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Q_section1.class);
            }
        });
        txtSection_ftable_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(Section_F_table1.class);
            }
        });


    }

    void startIntent(Class activity) {
        Intent intent = new Intent(SectionsSelections.this, activity);

        intent.putExtra("username", userName);

        intent.putExtra("region", region);
        intent.putExtra("emp_id", emp_id);
        intent.putExtra("order_id", order_id);

        intent.putExtra("farmerID", FarmerID);
        intent.putExtra("farmerDistrict", farmerDistrict);
        intent.putExtra("farmerVillage", farmerVillage);
        intent.putExtra("farmerTehsil", farmerTehsil);
        intent.putExtra("ao_name", ao_name);
        intent.putExtra("fa_name", fa_name);
        intent.putExtra("ai_name", ai_name);
        intent.putExtra("id", id);
        intent.putExtra("farmer_cellphone", farmer_cellphone);
        intent.putExtra("isFromEdit", isFromEdit);
        startActivity(intent);
        //   finish();
    }

    void readSection_b() {

        try {
            databaseAccess = new DatabaseAdapter(SectionsSelections.this);
            databaseAccess.Open();

            Cursor cursor = databaseAccess.getSectionBData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                b26 = cursor.getString(cursor.getColumnIndex("b26"));
                MubLog.cpnsoleLog("Farmer ID = " + FarmerID + "  : b26 =  " + b26);
                if (b26.equalsIgnoreCase("1") || b26.equalsIgnoreCase("3")) {
                    txtSection_C.setVisibility(View.VISIBLE);
                    txtSection_D.setVisibility(View.VISIBLE);
                    txtSection_E.setVisibility(View.VISIBLE);
                    txtSection_ftable_1.setVisibility(View.VISIBLE);
                    txtSection_G.setVisibility(View.VISIBLE);
                    txtSection_H.setVisibility(View.VISIBLE);
                    txtSection_1.setVisibility(View.VISIBLE);
                    txtforBAndC.setVisibility(View.GONE);
                } else {
                    txtSection_C.setVisibility(View.GONE);
                    txtSection_D.setVisibility(View.GONE);
                    txtSection_E.setVisibility(View.GONE);
                    txtSection_ftable_1.setVisibility(View.GONE);
                    txtSection_G.setVisibility(View.GONE);
                    txtSection_H.setVisibility(View.GONE);
                    txtSection_1.setVisibility(View.GONE);
                    txtforBAndC.setVisibility(View.VISIBLE);
                    txtforBAndC.setText("Please Fill First Question B 26");
                }
                cursor.close();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readSection_c() {

        try {
            databaseAccess = new DatabaseAdapter(SectionsSelections.this);
            databaseAccess.Open();

            Cursor cursor = databaseAccess.getSectionCData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                String c1 = cursor.getString(cursor.getColumnIndex("c1"));
                MubLog.cpnsoleLog("Farmer ID C1 = " + FarmerID + "  : c1 =  " + c1);
                if (!StringUtils.isEmpty(c1)) {
                    txtSection_D.setVisibility(View.VISIBLE);
                    txtSection_E.setVisibility(View.VISIBLE);
                    txtSection_ftable_1.setVisibility(View.VISIBLE);
                    txtSection_G.setVisibility(View.VISIBLE);
                    txtSection_H.setVisibility(View.VISIBLE);
                    txtSection_1.setVisibility(View.VISIBLE);
                    txtforBAndC.setVisibility(View.GONE);

                } else {
                    txtSection_D.setVisibility(View.GONE);
                    txtSection_E.setVisibility(View.GONE);
                    txtSection_ftable_1.setVisibility(View.GONE);
                    txtSection_G.setVisibility(View.GONE);
                    txtSection_H.setVisibility(View.GONE);
                    txtSection_1.setVisibility(View.GONE);
                    if (txtSection_C.getVisibility() == View.VISIBLE){
                        txtforBAndC.setVisibility(View.VISIBLE);
                        txtforBAndC.setText("Please Fill First Question C 1");
                    }
                }
                cursor.close();
            } else {
                txtSection_D.setVisibility(View.GONE);
                txtSection_E.setVisibility(View.GONE);
                txtSection_ftable_1.setVisibility(View.GONE);
                txtSection_G.setVisibility(View.GONE);
                txtSection_H.setVisibility(View.GONE);
                txtSection_1.setVisibility(View.GONE);
                txtforBAndC.setVisibility(View.VISIBLE);
                if (txtSection_C.getVisibility() == View.VISIBLE){
                    txtforBAndC.setVisibility(View.VISIBLE);
                    txtforBAndC.setText("Please Fill First Question C 1");
                }
            }

        } catch (
                Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
