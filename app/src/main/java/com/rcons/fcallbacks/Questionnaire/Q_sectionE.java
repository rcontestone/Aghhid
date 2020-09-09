package com.rcons.fcallbacks.Questionnaire;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.VillageSearch.SelectZaatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Q_sectionE extends AppCompatActivity {
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    String userName = "";
    DatabaseAdapter databaseAccess;
    String region = "";
    String emp_id = "";
    String order_id = "";
    Button btnSelectZaat;
    TextView txtFarmerID, txtAoName, txtFaName, txtEmpID, txtOrderID;
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String ao_name = "";
    String fa_name = "";
    String ai_name = "";
    String farmer_cellphone;
    String b26 = "";
    String id;
    @BindView(R.id.checkbox_e15_1)
    CheckBox checkbox_e15_1;
    @BindView(R.id.checkbox_e15_2)
    CheckBox checkbox_e15_2;
    @BindView(R.id.checkbox_e15_3)
    CheckBox checkbox_e15_3;
    @BindView(R.id.checkbox_e15_4)
    CheckBox checkbox_e15_4;
    @BindView(R.id.checkbox_e15_5)
    CheckBox checkbox_e15_5;
    @BindView(R.id.checkbox_e15_6)
    CheckBox checkbox_e15_6;
    @BindView(R.id.checkbox_e15_7)
    CheckBox checkbox_e15_7;
    @BindView(R.id.checkbox_e15_8)
    CheckBox checkbox_e15_8;
    @BindView(R.id.checkbox_e15_9)
    CheckBox checkbox_e15_9;
    @BindView(R.id.checkbox_e15_10)
    CheckBox checkbox_e15_10;
    @BindView(R.id.checkbox_e15_11)
    CheckBox checkbox_e15_11;
    @BindView(R.id.checkbox_e15_12)
    CheckBox checkbox_e15_12;
    @BindView(R.id.checkbox_e15_13)
    CheckBox checkbox_e15_13;
    @BindView(R.id.checkbox_e15_14)
    CheckBox checkbox_e15_14;
    @BindView(R.id.checkbox_e15_15)
    CheckBox checkbox_e15_15;
    @BindView(R.id.checkbox_e15_16)
    CheckBox checkbox_e15_16;
    @BindView(R.id.checkbox_e15_17)
    CheckBox checkbox_e15_17;


    @BindView(R.id.checkbox_e33_1)
    CheckBox checkbox_e33_1;
    @BindView(R.id.checkbox_e33_2)
    CheckBox checkbox_e33_2;
    @BindView(R.id.checkbox_e33_3)
    CheckBox checkbox_e33_3;
    @BindView(R.id.checkbox_e33_4)
    CheckBox checkbox_e33_4;
    @BindView(R.id.checkbox_e33_5)
    CheckBox checkbox_e33_5;
    @BindView(R.id.checkbox_e33_6)
    CheckBox checkbox_e33_6;
    @BindView(R.id.checkbox_e33_7)
    CheckBox checkbox_e33_7;
    @BindView(R.id.checkbox_e33_8)
    CheckBox checkbox_e33_8;
    @BindView(R.id.checkbox_e33_9)
    CheckBox checkbox_e33_9;


    @BindView(R.id.checkbox_e35_1)
    CheckBox checkbox_e35_1;
    @BindView(R.id.checkbox_e35_2)
    CheckBox checkbox_e35_2;
    @BindView(R.id.checkbox_e35_3)
    CheckBox checkbox_e35_3;
    @BindView(R.id.checkbox_e35_4)
    CheckBox checkbox_e35_4;
    @BindView(R.id.checkbox_e35_5)
    CheckBox checkbox_e35_5;
    @BindView(R.id.checkbox_e35_6)
    CheckBox checkbox_e35_6;
    @BindView(R.id.checkbox_e35_7)
    CheckBox checkbox_e35_7;
    @BindView(R.id.checkbox_e35_8)
    CheckBox checkbox_e35_8;


    @BindView(R.id.checkbox_e36_1)
    CheckBox checkbox_e36_1;
    @BindView(R.id.checkbox_e36_2)
    CheckBox checkbox_e36_2;
    @BindView(R.id.checkbox_e36_3)
    CheckBox checkbox_e36_3;
    @BindView(R.id.checkbox_e36_4)
    CheckBox checkbox_e36_4;
    @BindView(R.id.checkbox_e36_5)
    CheckBox checkbox_e36_5;
    @BindView(R.id.checkbox_e36_6)
    CheckBox checkbox_e36_6;
    @BindView(R.id.checkbox_e36_7)
    CheckBox checkbox_e36_7;
    @BindView(R.id.checkbox_e36_8)
    CheckBox checkbox_e36_8;
    @BindView(R.id.checkbox_e36_9)
    CheckBox checkbox_e36_9;
    @BindView(R.id.checkbox_e36_10)
    CheckBox checkbox_e36_10;


    @BindView(R.id.checkbox_e38_1)
    CheckBox checkbox_e38_1;
    @BindView(R.id.checkbox_e38_2)
    CheckBox checkbox_e38_2;
    @BindView(R.id.checkbox_e38_3)
    CheckBox checkbox_e38_3;
    @BindView(R.id.checkbox_e38_4)
    CheckBox checkbox_e38_4;

    @BindView(R.id.qE11a_layout)
    LinearLayout qE11a_layout;
    @BindView(R.id.qE11b_layout)
    LinearLayout qE11b_layout;
    @BindView(R.id.qE11c_layout)
    LinearLayout qE11c_layout;
    @BindView(R.id.qE11d_layout)
    LinearLayout qE11d_layout;


    @BindView(R.id.qE17a_layout)
    LinearLayout qE17a_layout;
    @BindView(R.id.qE17b_layout)
    LinearLayout qE17b_layout;
    @BindView(R.id.qE17c_layout)
    LinearLayout qE17c_layout;
    @BindView(R.id.qE17d_layout)
    LinearLayout qE17d_layout;

    @BindView(R.id.rg_qE11a)
    RadioGroup rg_qE11a;
    @BindView(R.id.rg_qE11b)
    RadioGroup rg_qE11b;
    @BindView(R.id.rg_qE17a)
    RadioGroup rg_qE17a;
    @BindView(R.id.rg_qE17b)
    RadioGroup rg_qE17b;

    @BindView(R.id._edt_e11c)
    EditText edt_e11c;
    @BindView(R.id._edt_e11d)
    EditText edt_e11d;

    @BindView(R.id._edt_e17c)
    EditText edt_e17c;
    @BindView(R.id._edt_e17d)
    EditText edt_e17d;

    @BindView(R.id.txt_e15_2_other)
    EditText txt_e15_2_other;
    @BindView(R.id.txt_e15_3_other)
    EditText txt_e15_3_other;

    ImageView btniBack;

    RadioGroup RG_qe1, RG_qe2, RG_qe3a, RG_qe3b, RG_qe4, RG_qe5, RG_qe6a, RG_qe6b, RG_qe7, RG_qe8, RG_qe9, RG_qe10, RG_qe11, RG_qe14, RG_qe16, RG_qe17, RG_qe18, RG_qe19, RG_qe21, RG_qe22, RG_qe23, RG_qe24, RG_qe25, RG_qe26, RG_qe27, RG_qe28, RG_qe29, RG_qe30, RG_qe31, RG_qe32, RG_qe32a, RG_qe34, RG_qe37, RG_qe39, RG_qe40, RG_qe41;
    EditText txtQ_e1_other, txtQ_e4_other, txtQ_e12, txtQ_e13, txtQ_e15_other, txtQ_e20, txt_e33_other, txtQ_e34_other, txt_e35_other, txt_e36_other, txt_e42_zaat_code, txt_e42_other_zaat;
    LinearLayout Q_e1_layout, Q_e2_layout, Q_e3a_layout, Q_e3b_layout, Q_e4_layout, Q_e5_layout, Q_e6a_layout, Q_e6b_layout, Q_e7_layout, Q_e8_layout, Q_e9_layout, Q_e10_layout, Q_e11_layout, Q_e12_layout, Q_e13_layout, Q_e14_layout, Q_e15_layout, Q_e16_layout, Q_e17_layout, Q_e18_layout, Q_e19_layout, Q_e20_layout,
            Q_e21_layout, Q_e22_layout, Q_e23_layout, Q_e24_layout, Q_e25_layout, Q_e26_layout, Q_e27_layout, Q_e28_layout, Q_e29_layout, Q_e30_layout, Q_e31_layout, Q_e32_layout, Q_e32a_layout, Q_e33_layout, Q_e34_layout, Q_e35_layout, Q_e36_layout, Q_e37_layout, Q_e38_layout, Q_e39_layout, Q_e40_layout, Q_e41_layout, Q_e42_layout;

    Button NextButton, BackButton, btn_RedialCall, btn_AddReportQuestionnaire;
    ;


    String e1 = "";
    String e1_other = "";
    String e2 = "";
    String e3a = "";
    String e3b = "";
    String e4 = "";
    String e4_other = "";
    String e5 = "";
    String e6a = "";
    String e6b = "";
    String e7 = "";
    String e8 = "";
    String e9 = "";
    String e10 = "";
    String e11 = "";
    String e11a = "";
    String e11b = "";
    String e11c = "";
    String e11d = "";
    String e12 = "";
    String e13 = "";
    String e14 = "";
    String e15a = "";
    String e15b = "";
    String e15c = "";
    String e15_other = "";
    String e15_2_other = "";
    String e15_3_other = "";

    String e16 = "";
    String e17 = "";
    String e17a = "";
    String e17b = "";
    String e17c = "";
    String e17d = "";
    String e18 = "";
    String e19 = "";
    String e20 = "";

    String e21 = "";
    String e22 = "";
    String e23 = "";
    String e24 = "";
    String e25 = "";
    String e26 = "";
    String e27 = "";
    String e28 = "";
    String e29 = "";
    String e30 = "";
    String e31 = "";
    String e32 = "";
    String e32a = "";

    String e33a = "";
    String e33b = "";
    String e33c = "";
    String e33_other = "";

    String e34 = "";
    String e34_other = "";

    String e35a = "";
    String e35b = "";
    String e35c = "";
    String e35_other = "";

    String e36a = "";
    String e36b = "";
    String e36c = "";
    String e36_other = "";

    String e37 = "";
    String e38a = "";
    String e38b = "";
    String e38c = "";
    String e38_other = "";
    String e39 = "";
    String e40 = "";
    String e41 = "";
    String e42_zaat_code = "";
    String e42_other = "";
    String comments = "";
    String enum_name = "";
    String enum_code = "";

    int question15CheckBoxCounter = 0;
    ArrayList<String> question15CheckBoxTags = new ArrayList<>();

    int question33CheckBoxCounter = 0;
    ArrayList<String> question33CheckBoxTags = new ArrayList<>();

    int question35CheckBoxCounter = 0;
    ArrayList<String> question35CheckBoxTags = new ArrayList<>();

    int question36CheckBoxCounter = 0;
    ArrayList<String> question36CheckBoxTags = new ArrayList<>();

    int question38CheckBoxCounter = 0;
    ArrayList<String> question38CheckBoxTags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_e);
        ButterKnife.bind(this);
        btniBack = findViewById(R.id.btnBack);
        databaseAccess = new DatabaseAdapter(Q_sectionE.this);
        databaseAccess.Open();

        userName = getIntent().getStringExtra("username");
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
        id = getIntent().getStringExtra("id");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");

        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);
        txtAoName = findViewById(R.id.ao_name);
        txtAoName.setText("AO Name: " + ao_name);

        txtFaName = findViewById(R.id.fa_name);
        txtFaName.setText("FA Name: " + fa_name);

        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);

        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        btnSelectZaat = findViewById(R.id.btnSelectZaat);

        NextButton = findViewById(R.id.btn_next);
        BackButton = findViewById(R.id.btn_back);
        btn_RedialCall = findViewById(R.id.btn_RedialCall);
        btn_AddReportQuestionnaire = findViewById(R.id.btn_AddReportQuestionnaire);


        Q_e1_layout = findViewById(R.id.qe1_layout);
        Q_e2_layout = findViewById(R.id.qe2_layout);
        Q_e3a_layout = findViewById(R.id.qe3a_layout);
        Q_e3b_layout = findViewById(R.id.qe3b_layout);
        Q_e4_layout = findViewById(R.id.qe4_layout);
        Q_e5_layout = findViewById(R.id.qe5_layout);
        Q_e6a_layout = findViewById(R.id.qe6a_layout);
        Q_e6b_layout = findViewById(R.id.qe6b_layout);
        Q_e7_layout = findViewById(R.id.qe7_layout);
        Q_e8_layout = findViewById(R.id.qe8_layout);
        Q_e9_layout = findViewById(R.id.qe9_layout);
        Q_e10_layout = findViewById(R.id.qe10_layout);
        Q_e11_layout = findViewById(R.id.qe11_layout);
        Q_e12_layout = findViewById(R.id.qe12_layout);
        Q_e13_layout = findViewById(R.id.qe13_layout);
        Q_e14_layout = findViewById(R.id.qe14_layout);
        Q_e15_layout = findViewById(R.id.qe15_layout);
        Q_e16_layout = findViewById(R.id.qe16_layout);
        Q_e17_layout = findViewById(R.id.qe17_layout);
        Q_e18_layout = findViewById(R.id.qe18_layout);
        Q_e19_layout = findViewById(R.id.qe19_layout);
        Q_e20_layout = findViewById(R.id.qe20_layout);
        Q_e21_layout = findViewById(R.id.qe21_layout);
        Q_e22_layout = findViewById(R.id.qe22_layout);
        Q_e23_layout = findViewById(R.id.qe23_layout);
        Q_e24_layout = findViewById(R.id.qe24_layout);
        Q_e25_layout = findViewById(R.id.qe25_layout);
        Q_e26_layout = findViewById(R.id.qe26_layout);
        Q_e27_layout = findViewById(R.id.qe27_layout);
        Q_e28_layout = findViewById(R.id.qe28_layout);
        Q_e29_layout = findViewById(R.id.qe29_layout);
        Q_e30_layout = findViewById(R.id.qe30_layout);
        Q_e31_layout = findViewById(R.id.qe31_layout);
        Q_e32_layout = findViewById(R.id.qe32_layout);
        Q_e32a_layout = findViewById(R.id.qe32a_layout);
        Q_e33_layout = findViewById(R.id.qe33_layout);
        Q_e34_layout = findViewById(R.id.qe34_layout);
        Q_e35_layout = findViewById(R.id.qe35_layout);
        Q_e36_layout = findViewById(R.id.qe36_layout);
        Q_e37_layout = findViewById(R.id.qe37_layout);
        Q_e38_layout = findViewById(R.id.qe38_layout);
        Q_e39_layout = findViewById(R.id.qe39_layout);
        Q_e40_layout = findViewById(R.id.qe40_layout);
        Q_e41_layout = findViewById(R.id.qe41_layout);
        Q_e42_layout = findViewById(R.id.qe42_layout);

        RG_qe1 = findViewById(R.id.rg_qe1);
        RG_qe2 = findViewById(R.id.rg_qe2);
        RG_qe3a = findViewById(R.id.rg_qe3a);
        RG_qe3b = findViewById(R.id.rg_qe3b);
        RG_qe4 = findViewById(R.id.rg_qe4);
        RG_qe5 = findViewById(R.id.rg_qe5);
        RG_qe6a = findViewById(R.id.rg_qe6a);
        RG_qe6b = findViewById(R.id.rg_qe6b);
        RG_qe7 = findViewById(R.id.rg_qe7);
        RG_qe8 = findViewById(R.id.rg_qe8);
        RG_qe9 = findViewById(R.id.rg_qe9);
        RG_qe10 = findViewById(R.id.rg_qe10);
        RG_qe11 = findViewById(R.id.rg_qe11);
        RG_qe14 = findViewById(R.id.rg_qe14);
        RG_qe16 = findViewById(R.id.rg_qe16);
        RG_qe17 = findViewById(R.id.rg_qe17);
        RG_qe18 = findViewById(R.id.rg_qe18);
        RG_qe19 = findViewById(R.id.rg_qe19);
        RG_qe21 = findViewById(R.id.rg_qe21);
        RG_qe22 = findViewById(R.id.rg_qe22);
        RG_qe23 = findViewById(R.id.rg_qe23);
        RG_qe24 = findViewById(R.id.rg_qe24);
        RG_qe25 = findViewById(R.id.rg_qe25);
        RG_qe26 = findViewById(R.id.rg_qe26);
        RG_qe27 = findViewById(R.id.rg_qe27);
        RG_qe28 = findViewById(R.id.rg_qe28);
        RG_qe29 = findViewById(R.id.rg_qe29);
        RG_qe30 = findViewById(R.id.rg_qe30);
        RG_qe31 = findViewById(R.id.rg_qe31);
        RG_qe32 = findViewById(R.id.rg_qe32);
        RG_qe32a = findViewById(R.id.rg_qe32a);
        RG_qe34 = findViewById(R.id.rg_qe34);
        RG_qe37 = findViewById(R.id.rg_qe37);
        RG_qe39 = findViewById(R.id.rg_qe39);
        RG_qe40 = findViewById(R.id.rg_qe40);
        RG_qe41 = findViewById(R.id.rg_qe41);

        txtQ_e1_other = findViewById(R.id.txt_qe1_other);
        txtQ_e4_other = findViewById(R.id.txt_qe4_other);
        txtQ_e12 = findViewById(R.id.txt_e12);
        txtQ_e13 = findViewById(R.id.txt_e13);
        txtQ_e15_other = findViewById(R.id.txt_e15_other);
        txtQ_e20 = findViewById(R.id.txt_e20);
        txt_e33_other = findViewById(R.id.txt_e33_other);
        txtQ_e34_other = findViewById(R.id.txt_e34_other);
        txt_e35_other = findViewById(R.id.txt_e35_other);
        txt_e36_other = findViewById(R.id.txt_e36_other);
        txt_e42_zaat_code = findViewById(R.id.txt_e42_zaat_code);
        txt_e42_other_zaat = findViewById(R.id.txt_e42_other_zaat);

        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }
        SetQuestion15CheckBoxListener();
        SetQuestion33CheckBoxListener();
        SetQuestion35CheckBoxListener();
        SetQuestion36CheckBoxListener();
        SetQuestion38CheckBoxListener();

        readFromDataBase();
        LoadPreviousData();
        readSection_b();
        GetEnum();
        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(farmer_cellphone)) {
                        DialUserNumber(farmer_cellphone);
                    } else {
                        Toast.makeText(Q_sectionE.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        btnSelectZaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Q_sectionE.this, SelectZaatActivity.class);
                    startActivityForResult(intent, 99);

                } catch (Exception e) {
                    Log.e("TAG", "" + e.toString());

                }

            }
        });


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    SaveData();
                    FarmerID = getIntent().getStringExtra("farmerID");

                    int RG_qe1_ID = RG_qe1.getCheckedRadioButtonId();
                    int RG_qe2_ID = RG_qe2.getCheckedRadioButtonId();
                    int RG_qe3a_ID = RG_qe3a.getCheckedRadioButtonId();
                    int RG_qe3b_ID = RG_qe3b.getCheckedRadioButtonId();
                    int RG_qe4_ID = RG_qe4.getCheckedRadioButtonId();
                    int RG_qe5_ID = RG_qe5.getCheckedRadioButtonId();
                    int RG_qe6a_ID = RG_qe6a.getCheckedRadioButtonId();
                    int RG_qe6b_ID = RG_qe6b.getCheckedRadioButtonId();
                    int RG_qe7_ID = RG_qe7.getCheckedRadioButtonId();
                    int RG_qe8_ID = RG_qe8.getCheckedRadioButtonId();
                    int RG_qe9_ID = RG_qe9.getCheckedRadioButtonId();
                    int RG_qe10_ID = RG_qe10.getCheckedRadioButtonId();
                    int RG_qe11_ID = RG_qe11.getCheckedRadioButtonId();
                    int RG_qe11a_ID = rg_qE11a.getCheckedRadioButtonId();
                    int RG_qe11b_ID = rg_qE11b.getCheckedRadioButtonId();
                    int RG_qe14_ID = RG_qe14.getCheckedRadioButtonId();
                    int RG_qe16_ID = RG_qe16.getCheckedRadioButtonId();
                    int RG_qe17_ID = RG_qe17.getCheckedRadioButtonId();
                    int RG_qe17a_ID = rg_qE17a.getCheckedRadioButtonId();
                    int RG_qe17b_ID = rg_qE17b.getCheckedRadioButtonId();
                    int RG_qe18_ID = RG_qe18.getCheckedRadioButtonId();
                    int RG_qe19_ID = RG_qe19.getCheckedRadioButtonId();
                    int RG_qe21_ID = RG_qe21.getCheckedRadioButtonId();
                    int RG_qe22_ID = RG_qe22.getCheckedRadioButtonId();
                    int RG_qe23_ID = RG_qe23.getCheckedRadioButtonId();
                    int RG_qe24_ID = RG_qe24.getCheckedRadioButtonId();
                    int RG_qe25_ID = RG_qe25.getCheckedRadioButtonId();
                    int RG_qe26_ID = RG_qe26.getCheckedRadioButtonId();
                    int RG_qe27_ID = RG_qe27.getCheckedRadioButtonId();
                    int RG_qe28_ID = RG_qe28.getCheckedRadioButtonId();
                    int RG_qe29_ID = RG_qe29.getCheckedRadioButtonId();
                    int RG_qe30_ID = RG_qe30.getCheckedRadioButtonId();
                    int RG_qe31_ID = RG_qe31.getCheckedRadioButtonId();
                    int RG_qe32_ID = RG_qe32.getCheckedRadioButtonId();
                    int RG_qe32a_ID = RG_qe32a.getCheckedRadioButtonId();
                    int RG_qe34_ID = RG_qe34.getCheckedRadioButtonId();
                    int RG_qe37_ID = RG_qe37.getCheckedRadioButtonId();
                    int RG_qe39_ID = RG_qe39.getCheckedRadioButtonId();
                    int RG_qe40_ID = RG_qe40.getCheckedRadioButtonId();
                    int RG_qe41_ID = RG_qe41.getCheckedRadioButtonId();

                    RConsUtils.hideKeyboard(Q_sectionE.this);

                    if (Q_e1_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe1_ID);
                        if (RG_qe1_ID == R.id.rbtn_e1_1 || RG_qe1_ID == R.id.rbtn_e1_3) {
                            Q_e1_layout.setVisibility(View.GONE);
                            Q_e2_layout.setVisibility(View.VISIBLE);
                            e1 = radioButton.getTag().toString();
                        } else if (RG_qe1_ID == R.id.rbtn_e1_5) {
                            Q_e1_layout.setVisibility(View.GONE);
                            Q_e4_layout.setVisibility(View.VISIBLE);
                            e1 = radioButton.getTag().toString();
                        } else if (RG_qe1_ID == R.id.rbtn_e1_2 || RG_qe1_ID == R.id.rbtn_e1_4) {
                            e1 = radioButton.getTag().toString();
                            txtQ_e1_other.setVisibility(View.VISIBLE);
                            e1_other = txtQ_e1_other.getText().toString();
                            if (StringUtils.isEmpty(e1_other)) {
                                Toast.makeText(Q_sectionE.this, "Please Enter AO Name", Toast.LENGTH_SHORT).show();
                            } else {
                                e1 = radioButton.getTag().toString();
                                Q_e1_layout.setVisibility(View.GONE);
                                Q_e2_layout.setVisibility(View.VISIBLE);
                            }

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }
                        if (RG_qe1_ID == R.id.rbtn_e1_1 || RG_qe1_ID == R.id.rbtn_e1_2 || RG_qe1_ID == R.id.rbtn_e1_3 || RG_qe1_ID == R.id.rbtn_e1_4) {
                            RadioButton e25_1radiobutton = findViewById(R.id.rbtn_e25_1);
                            e25_1radiobutton.setChecked(true);

                        } else {
                            RadioButton e25_2radiobutton = findViewById(R.id.rbtn_e25_2);
                            e25_2radiobutton.setChecked(true);
                        }
                    } else if (Q_e2_layout.getVisibility() == View.VISIBLE) {


                        RadioButton radioButton = findViewById(RG_qe2_ID);

                        if (RG_qe2_ID == R.id.rbtn_e2_1 || RG_qe2_ID == R.id.rbtn_e2_2) {
                            e2 = radioButton.getTag().toString();
                            Q_e2_layout.setVisibility(View.GONE);
                            Q_e3a_layout.setVisibility(View.VISIBLE);
                        } else if (RG_qe2_ID == R.id.rbtn_e2_3) {
                            Q_e2_layout.setVisibility(View.GONE);
                            Q_e4_layout.setVisibility(View.VISIBLE);
                            e2 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e3a_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe3a_ID);
                        if (RG_qe3a_ID == R.id.rbtn_e3a_1 || RG_qe3a_ID == R.id.rbtn_e3a_2 || RG_qe3a_ID == R.id.rbtn_e3a_3) {
                            if (b26.equalsIgnoreCase("3")) {
                                e3a = radioButton.getTag().toString();
                                Q_e3a_layout.setVisibility(View.GONE);
                                Q_e3b_layout.setVisibility(View.VISIBLE);
                            } else {
                                e3a = radioButton.getTag().toString();
                                e3b = "";
                                Q_e3a_layout.setVisibility(View.GONE);
                                Q_e4_layout.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e3b_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe3b_ID);
                        if (RG_qe3b_ID == R.id.rbtn_e3b_1 || RG_qe3b_ID == R.id.rbtn_e3b_2 || RG_qe3b_ID == R.id.rbtn_e3b_3) {
                            Q_e3b_layout.setVisibility(View.GONE);
                            Q_e4_layout.setVisibility(View.VISIBLE);
                            e3b = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e4_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe4_ID);
                        if (RG_qe4_ID == R.id.rbtn_e4_1 || RG_qe4_ID == R.id.rbtn_e4_3) {
                            Q_e4_layout.setVisibility(View.GONE);
                            Q_e5_layout.setVisibility(View.VISIBLE);
                            e4 = radioButton.getTag().toString();
                        } else if (RG_qe4_ID == R.id.rbtn_e4_5) {
                            Q_e4_layout.setVisibility(View.GONE);
                            Q_e7_layout.setVisibility(View.VISIBLE);
                            e4 = radioButton.getTag().toString();
                        } else if (RG_qe4_ID == R.id.rbtn_e4_2 || RG_qe4_ID == R.id.rbtn_e4_4) {
                            txtQ_e4_other.setVisibility(View.VISIBLE);
                            e4_other = txtQ_e4_other.getText().toString();
                            if (StringUtils.isEmpty(e4_other)) {
                                Toast.makeText(Q_sectionE.this, "Please Enter AO Name", Toast.LENGTH_SHORT).show();
                            } else {
                                Q_e4_layout.setVisibility(View.GONE);
                                e4 = radioButton.getTag().toString();
                                Q_e5_layout.setVisibility(View.VISIBLE);
                            }

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }
                        if (RG_qe4_ID == R.id.rbtn_e4_1 || RG_qe4_ID == R.id.rbtn_e4_2 || RG_qe4_ID == R.id.rbtn_e4_3 || RG_qe4_ID == R.id.rbtn_e4_4) {
                            RadioButton e27_1radiobutton = findViewById(R.id.rbtn_e27_1);
                            e27_1radiobutton.setChecked(true);
                        } else {
                            RadioButton e27_2radiobutton = findViewById(R.id.rbtn_e27_2);
                            e27_2radiobutton.setChecked(true);
                        }

                    } else if (Q_e5_layout.getVisibility() == View.VISIBLE) {


                        RadioButton radioButton = findViewById(RG_qe5_ID);

                        if (RG_qe5_ID == R.id.rbtn_e5_1 || RG_qe5_ID == R.id.rbtn_e5_2) {
                            Q_e5_layout.setVisibility(View.GONE);
                            Q_e6a_layout.setVisibility(View.VISIBLE);
                            e5 = radioButton.getTag().toString();
                        } else if (RG_qe5_ID == R.id.rbtn_e5_3) {
                            Q_e5_layout.setVisibility(View.GONE);
                            Q_e7_layout.setVisibility(View.VISIBLE);
                            e5 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e6a_layout.getVisibility() == View.VISIBLE) {


                        RadioButton radioButton = findViewById(RG_qe6a_ID);

                        if (RG_qe6a_ID == R.id.rbtn_e6a_1 || RG_qe6a_ID == R.id.rbtn_e6a_2 || RG_qe6a_ID == R.id.rbtn_e6a_3) {
                            if (b26.equalsIgnoreCase("3")) {
                                Q_e6a_layout.setVisibility(View.GONE);
                                Q_e6b_layout.setVisibility(View.VISIBLE);
                                e6a = radioButton.getTag().toString();
                            } else {
                                Q_e6a_layout.setVisibility(View.GONE);
                                Q_e7_layout.setVisibility(View.VISIBLE);
                                e6a = radioButton.getTag().toString();
                            }

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e6b_layout.getVisibility() == View.VISIBLE) {


                        RadioButton radioButton = findViewById(RG_qe6b_ID);

                        if (RG_qe6b_ID == R.id.rbtn_e6b_1 || RG_qe6b_ID == R.id.rbtn_e6b_2 || RG_qe6b_ID == R.id.rbtn_e6b_3) {
                            Q_e6b_layout.setVisibility(View.GONE);
                            Q_e7_layout.setVisibility(View.VISIBLE);
                            e6b = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e7_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe7_ID);

                        if (RG_qe7_ID == R.id.rbtn_e7_1) {
                            Q_e7_layout.setVisibility(View.GONE);
                            Q_e8_layout.setVisibility(View.VISIBLE);
                            e7 = radioButton.getTag().toString();
                        } else if (RG_qe7_ID == R.id.rbtn_e7_2 || RG_qe7_ID == R.id.rbtn_e7_3) {
                            Q_e7_layout.setVisibility(View.GONE);
                            Q_e14_layout.setVisibility(View.VISIBLE);
                            e7 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e8_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe8_ID);

                        if (RG_qe8_ID == R.id.rbtn_e8_1
                                || RG_qe8_ID == R.id.rbtn_e8_2
                                || RG_qe8_ID == R.id.rbtn_e8_3
                                || RG_qe8_ID == R.id.rbtn_e8_4
                                || RG_qe8_ID == R.id.rbtn_e8_99) {
                            Q_e8_layout.setVisibility(View.GONE);
                            Q_e9_layout.setVisibility(View.VISIBLE);
                            e8 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e9_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe9_ID);
                        if (RG_qe9_ID == R.id.rbtn_e9_1) {
                            Q_e9_layout.setVisibility(View.GONE);
                            Q_e11_layout.setVisibility(View.VISIBLE);
                            e9 = radioButton.getTag().toString();
                        } else if (RG_qe9_ID == R.id.rbtn_e9_2) {
                            Q_e9_layout.setVisibility(View.GONE);
                            Q_e10_layout.setVisibility(View.VISIBLE);
                            e9 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e10_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe10_ID);
                        if (RG_qe10_ID == R.id.rbtn_e10_1
                                || RG_qe10_ID == R.id.rbtn_e10_2
                                || RG_qe10_ID == R.id.rbtn_e10_3
                                || RG_qe10_ID == R.id.rbtn_e10_4) {
                            e10 = radioButton.getTag().toString();
                            Q_e10_layout.setVisibility(View.GONE);
                            Q_e14_layout.setVisibility(View.VISIBLE);

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e11_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe11_ID);
                        if (RG_qe11_ID == R.id.rbtn_e11_1
                                || RG_qe11_ID == R.id.rbtn_e11_2
                                || RG_qe11_ID == R.id.rbtn_e11_3
                                || RG_qe11_ID == R.id.rbtn_e11_4) {
                            Q_e11_layout.setVisibility(View.GONE);
                            e11 = radioButton.getTag().toString();
                            qE11a_layout.setVisibility(View.VISIBLE);

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (qE11a_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe11a_ID);
                        if (RG_qe11a_ID == R.id.rbtn_E11a_1) {
                            e11a = radioButton.getTag().toString();
                            qE11a_layout.setVisibility(View.GONE);
                            qE11b_layout.setVisibility(View.VISIBLE);
                        } else if (RG_qe11a_ID == R.id.rbtn_E11a_2) {
                            e11a = radioButton.getTag().toString();
                            qE11a_layout.setVisibility(View.GONE);
                            e11b = "";
                            e11c = "";
                            e11d = "";
                            Q_e12_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select One Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (qE11b_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe11b_ID);
                        if (RG_qe11b_ID == R.id.rbtn_E11b_1) {
                            e11b = radioButton.getTag().toString();
                            qE11b_layout.setVisibility(View.GONE);
                            qE11c_layout.setVisibility(View.VISIBLE);
                        } else if (RG_qe11b_ID == R.id.rbtn_E11b_2) {
                            e11b = radioButton.getTag().toString();
                            e11c = "";
                            qE11b_layout.setVisibility(View.GONE);
                            qE11d_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select One Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (qE11c_layout.getVisibility() == View.VISIBLE) {
                        e11c = edt_e11c.getText().toString();
                        if (!StringUtils.isEmpty(e11c)) {
                            qE11c_layout.setVisibility(View.GONE);
                            Q_e12_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please Enter Some Detail", Toast.LENGTH_SHORT).show();
                        }

                    } else if (qE11d_layout.getVisibility() == View.VISIBLE) {
                        e11d = edt_e11d.getText().toString();
                        if (!StringUtils.isEmpty(e11d)) {
                            qE11d_layout.setVisibility(View.GONE);
                            Q_e12_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please Enter Some Detail", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e12_layout.getVisibility() == View.VISIBLE) {

                        e12 = txtQ_e12.getText().toString();
                        if (!StringUtils.isEmpty(e12)) {
                            Integer Minute = Integer.parseInt(e12);
                            if (Minute < 2 || Minute > 480) {
                                Toast.makeText(Q_sectionE.this, "Age must be in 2-480.", Toast.LENGTH_SHORT).show();
                            } else {
                                Q_e12_layout.setVisibility(View.GONE);
                                Q_e13_layout.setVisibility(View.VISIBLE);
                            }

                        } else {
                            Toast.makeText(Q_sectionE.this, "Add E12 Answer", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e13_layout.getVisibility() == View.VISIBLE) {

                        e13 = txtQ_e13.getText().toString();
                        if (!StringUtils.isEmpty(e13)) {
                            Integer Farmer = Integer.parseInt(e13);
                            if (Farmer < 2 || Farmer > 1000) {
                                Toast.makeText(Q_sectionE.this, "Age must be in 2-1000.", Toast.LENGTH_SHORT).show();
                            } else {
                                Q_e13_layout.setVisibility(View.GONE);

                                Q_e14_layout.setVisibility(View.VISIBLE);
                            }

                        } else {
                            Toast.makeText(Q_sectionE.this, "Add E14 Answer", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e14_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe14_ID);

                        if (RG_qe14_ID == R.id.rbtn_e14_1) {
                            Q_e14_layout.setVisibility(View.GONE);
                            e14 = radioButton.getTag().toString();
                            Q_e15_layout.setVisibility(View.VISIBLE);

                        } else if (RG_qe14_ID == R.id.rbtn_e14_2) {
                            Q_e14_layout.setVisibility(View.GONE);
                            e14 = radioButton.getTag().toString();
                            Q_e25_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Q_e15_layout.getVisibility() == View.VISIBLE) {
                        if (question15CheckBoxCounter > 0) {
                            if (txtQ_e15_other.getVisibility() == View.VISIBLE) {
                                String otherText = txtQ_e15_other.getText().toString();
                                if (StringUtils.isEmpty(otherText)) {
                                    Toast.makeText(Q_sectionE.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    e15_other = otherText;
                                    e15_2_other = txt_e15_2_other.getText().toString();
                                    e15_3_other = txt_e15_3_other.getText().toString();
                                    if (question15CheckBoxTags != null && question15CheckBoxTags.size() > 0) {
                                        e15a = question15CheckBoxTags.get(0);
                                        if (question15CheckBoxTags.size() > 1) {
                                            e15b = question15CheckBoxTags.get(1);
                                        }
                                        if (question15CheckBoxTags.size() > 2) {
                                            e15c = question15CheckBoxTags.get(2);
                                        }
                                    }
                                    Q_e15_layout.setVisibility(View.GONE);

                                    Q_e16_layout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (question15CheckBoxTags != null && question15CheckBoxTags.size() > 0) {
                                    e15a = question15CheckBoxTags.get(0);
                                    if (question15CheckBoxTags.size() > 1) {
                                        e15b = question15CheckBoxTags.get(1);
                                    }
                                    if (question15CheckBoxTags.size() > 2) {
                                        e15c = question15CheckBoxTags.get(2);
                                    }
                                }
                                Q_e15_layout.setVisibility(View.GONE);

                                Q_e16_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Q_e16_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe16_ID);

                        if (RG_qe16_ID == R.id.rbtn_e16_1 || RG_qe16_ID == R.id.rbtn_e16_2) {
                            Q_e16_layout.setVisibility(View.GONE);
                            Q_e17_layout.setVisibility(View.VISIBLE);
                            e16 = radioButton.getTag().toString();

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e17_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe17_ID);
                        if (RG_qe17_ID == R.id.rbtn_e17_1
                                || RG_qe17_ID == R.id.rbtn_e17_2
                                || RG_qe17_ID == R.id.rbtn_e17_3
                                || RG_qe17_ID == R.id.rbtn_e17_4) {
                            e17 = radioButton.getTag().toString();
                            Q_e17_layout.setVisibility(View.GONE);
                            qE17a_layout.setVisibility(View.VISIBLE);

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (qE17a_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe17a_ID);
                        if (RG_qe17a_ID == R.id.rbtn_E17a_1) {
                            e17a = radioButton.getTag().toString();
                            qE17a_layout.setVisibility(View.GONE);
                            qE17b_layout.setVisibility(View.VISIBLE);
                        } else if (RG_qe17a_ID == R.id.rbtn_E17a_2) {
                            e17a = radioButton.getTag().toString();
                            qE17a_layout.setVisibility(View.GONE);
                            e17b = "";
                            e17c = "";
                            e17d = "";
                            Q_e18_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select One Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (qE17b_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe17b_ID);
                        if (RG_qe17b_ID == R.id.rbtn_E17b_1) {
                            e17b = radioButton.getTag().toString();
                            qE17b_layout.setVisibility(View.GONE);
                            qE17c_layout.setVisibility(View.VISIBLE);
                        } else if (RG_qe17b_ID == R.id.rbtn_E17b_2) {
                            e17b = radioButton.getTag().toString();
                            qE17b_layout.setVisibility(View.GONE);
                            e17c = "";
                            qE17d_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select One Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (qE17c_layout.getVisibility() == View.VISIBLE) {
                        e17c = edt_e17c.getText().toString();
                        if (!StringUtils.isEmpty(e17c)) {
                            qE17c_layout.setVisibility(View.GONE);
                            Q_e18_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please Enter Some Detail", Toast.LENGTH_SHORT).show();
                        }

                    } else if (qE17d_layout.getVisibility() == View.VISIBLE) {
                        e17d = edt_e17d.getText().toString();
                        if (!StringUtils.isEmpty(e17d)) {
                            qE17d_layout.setVisibility(View.GONE);
                            Q_e18_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please Enter Some Detail", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e18_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe18_ID);
                        if (RG_qe18_ID == R.id.rbtn_e18_1) {
                            Q_e18_layout.setVisibility(View.GONE);
                            Q_e19_layout.setVisibility(View.VISIBLE);
                            e18 = radioButton.getTag().toString();
                        } else if (RG_qe18_ID == R.id.rbtn_e18_2) {
                            Q_e18_layout.setVisibility(View.GONE);
                            Q_e20_layout.setVisibility(View.VISIBLE);
                            e18 = radioButton.getTag().toString();

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e19_layout.getVisibility() == View.VISIBLE) {


                        RadioButton radioButton = findViewById(RG_qe19_ID);
                        if (RG_qe19_ID == R.id.rbtn_e19_1 || RG_qe19_ID == R.id.rbtn_e19_2) {
                            Q_e19_layout.setVisibility(View.GONE);
                            Q_e20_layout.setVisibility(View.VISIBLE);
                            e19 = radioButton.getTag().toString();

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e20_layout.getVisibility() == View.VISIBLE) {

                        ///////// Q E 20 ///////////////
                        e20 = txtQ_e20.getText().toString();
                        if (StringUtils.isEmpty(e20)) {
                            Toast.makeText(Q_sectionE.this, "Please Enter Minutes", Toast.LENGTH_SHORT).show();
                        } else {
                            Q_e20_layout.setVisibility(View.GONE);
                            Q_e21_layout.setVisibility(View.VISIBLE);
                        }


                    } else if (Q_e21_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe21_ID);
                        if (RG_qe21_ID == R.id.rbtn_e21_1) {
                            Q_e21_layout.setVisibility(View.GONE);
                            Q_e22_layout.setVisibility(View.VISIBLE);
                            e21 = radioButton.getTag().toString();
                        } else if (RG_qe21_ID == R.id.rbtn_e21_2) {
                            Q_e21_layout.setVisibility(View.GONE);
                            Q_e25_layout.setVisibility(View.VISIBLE);
                            e21 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e22_layout.getVisibility() == View.VISIBLE) {


                        RadioButton radioButton = findViewById(RG_qe22_ID);
                        if (RG_qe22_ID == R.id.rbtn_e22_1) {
                            Q_e22_layout.setVisibility(View.GONE);
                            e22 = radioButton.getTag().toString();
                            Q_e23_layout.setVisibility(View.VISIBLE);

                        } else if (RG_qe22_ID == R.id.rbtn_e22_2) {
                            Q_e22_layout.setVisibility(View.GONE);
                            Q_e24_layout.setVisibility(View.VISIBLE);
                            e22 = radioButton.getTag().toString();

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e23_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe23_ID);
                        if (RG_qe23_ID == R.id.rbtn_e23_1 || RG_qe23_ID == R.id.rbtn_e23_2) {
                            Q_e23_layout.setVisibility(View.GONE);
                            Q_e25_layout.setVisibility(View.VISIBLE);
                            e23 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e24_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe24_ID);
                        if (RG_qe24_ID == R.id.rbtn_e24_1 || RG_qe24_ID == R.id.rbtn_e24_2) {
                            Q_e24_layout.setVisibility(View.GONE);
                            Q_e25_layout.setVisibility(View.VISIBLE);
                            e24 = radioButton.getTag().toString();

                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e25_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe25_ID);
                        if (RG_qe25_ID == R.id.rbtn_e25_1) {
                            Q_e25_layout.setVisibility(View.GONE);
                            Q_e26_layout.setVisibility(View.VISIBLE);
                            e25 = radioButton.getTag().toString();
                        } else if (RG_qe25_ID == R.id.rbtn_e25_2) {
                            Q_e25_layout.setVisibility(View.GONE);
                            Q_e27_layout.setVisibility(View.VISIBLE);
                            e25 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e26_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe26_ID);
                        if (RG_qe26_ID == R.id.rbtn_e26_1 || RG_qe26_ID == R.id.rbtn_e26_2) {
                            Q_e26_layout.setVisibility(View.GONE);
                            Q_e27_layout.setVisibility(View.VISIBLE);
                            e26 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e27_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe27_ID);
                        if (RG_qe27_ID == R.id.rbtn_e27_1) {
                            Q_e27_layout.setVisibility(View.GONE);
                            Q_e28_layout.setVisibility(View.VISIBLE);
                            e27 = radioButton.getTag().toString();
                            if (RG_qe27_ID == R.id.rbtn_e27_2 && RG_qe25_ID == R.id.rbtn_e25_2) {
                                RadioButton e29_1radiobutton = findViewById(R.id.rbtn_e29_1);
                                e29_1radiobutton.setChecked(true);
                            } else {
                                RadioButton e29_2radiobutton = findViewById(R.id.rbtn_e29_2);
                                e29_2radiobutton.setChecked(true);
                            }
                        } else if (RG_qe27_ID == R.id.rbtn_e27_2) {
                            Q_e27_layout.setVisibility(View.GONE);
                            Q_e29_layout.setVisibility(View.VISIBLE);
                            e27 = radioButton.getTag().toString();
                            if (RG_qe27_ID == R.id.rbtn_e27_2 && RG_qe25_ID == R.id.rbtn_e25_2) {
                                RadioButton e29_1radiobutton = findViewById(R.id.rbtn_e29_1);
                                e29_1radiobutton.setChecked(true);
                            } else {
                                RadioButton e29_2radiobutton = findViewById(R.id.rbtn_e29_2);
                                e29_2radiobutton.setChecked(true);
                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e28_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe28_ID);
                        if (RG_qe28_ID == R.id.rbtn_e28_1 || RG_qe28_ID == R.id.rbtn_e28_2) {
                            Q_e28_layout.setVisibility(View.GONE);
                            Q_e29_layout.setVisibility(View.VISIBLE);
                            e28 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }


                    } else if (Q_e29_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe29_ID);
                        if (RG_qe29_ID == R.id.rbtn_e29_1) {
                            Q_e29_layout.setVisibility(View.GONE);
                            Q_e31_layout.setVisibility(View.VISIBLE);
                            e29 = radioButton.getTag().toString();
                        } else if (RG_qe29_ID == R.id.rbtn_e29_2) {
                            Q_e29_layout.setVisibility(View.GONE);
                            Q_e30_layout.setVisibility(View.VISIBLE);
                            e29 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e30_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe30_ID);
                        if (RG_qe30_ID == R.id.rbtn_e30_1
                                || RG_qe30_ID == R.id.rbtn_e30_2
                                || RG_qe30_ID == R.id.rbtn_e30_3
                                || RG_qe30_ID == R.id.rbtn_e30_4) {
                            Q_e30_layout.setVisibility(View.GONE);
                            Q_e31_layout.setVisibility(View.VISIBLE);
                            e30 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e31_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe31_ID);
                        if (RG_qe31_ID == R.id.rbtn_e31_1) {
                            Q_e31_layout.setVisibility(View.GONE);
                            Q_e32_layout.setVisibility(View.VISIBLE);
                            e31 = radioButton.getTag().toString();
                        } else if (RG_qe31_ID == R.id.rbtn_e31_2) {
                            Q_e31_layout.setVisibility(View.GONE);
                            e31 = radioButton.getTag().toString();
                            Q_e32a_layout.setVisibility(View.VISIBLE);
                            e31 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e32_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe32_ID);
                        if (RG_qe32_ID == R.id.rbtn_e32_1
                                || RG_qe32_ID == R.id.rbtn_e32_2
                                || RG_qe32_ID == R.id.rbtn_e32_3
                                || RG_qe32_ID == R.id.rbtn_e32_4) {
                            Q_e32_layout.setVisibility(View.GONE);
                            Q_e32a_layout.setVisibility(View.VISIBLE);
                            e32 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e32a_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe32a_ID);
                        if (RG_qe32a_ID == R.id.rbtn_e32a_1
                                || RG_qe32a_ID == R.id.rbtn_e32a_2
                                || RG_qe32a_ID == R.id.rbtn_e32a_3
                                || RG_qe32a_ID == R.id.rbtn_e32a_4) {
                            Q_e32a_layout.setVisibility(View.GONE);
                            Q_e33_layout.setVisibility(View.VISIBLE);
                            e32a = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Q_e33_layout.getVisibility() == View.VISIBLE) {
                        if (question33CheckBoxCounter > 0) {
                            if (txt_e33_other.getVisibility() == View.VISIBLE) {
                                String otherText = txt_e33_other.getText().toString();
                                if (StringUtils.isEmpty(otherText)) {
                                    Toast.makeText(Q_sectionE.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    e33_other = otherText;
                                    if (question33CheckBoxTags != null && question33CheckBoxTags.size() > 0) {
                                        e33a = question33CheckBoxTags.get(0);
                                        if (question33CheckBoxTags.size() > 1) {
                                            e33b = question33CheckBoxTags.get(1);
                                        }
                                        if (question33CheckBoxTags.size() > 2) {
                                            e33c = question33CheckBoxTags.get(2);
                                        }
                                    }
                                    Q_e33_layout.setVisibility(View.GONE);

                                    Q_e34_layout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (question33CheckBoxTags != null && question33CheckBoxTags.size() > 0) {
                                    e33a = question33CheckBoxTags.get(0);
                                    if (question33CheckBoxTags.size() > 1) {
                                        e33b = question33CheckBoxTags.get(1);
                                    }
                                    if (question33CheckBoxTags.size() > 2) {
                                        e33c = question33CheckBoxTags.get(2);
                                    }
                                }
                                Q_e33_layout.setVisibility(View.GONE);

                                Q_e34_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e34_layout.getVisibility() == View.VISIBLE) {

                        RadioButton radioButton = findViewById(RG_qe34_ID);
                        if (RG_qe34_ID == R.id.rbtn_e34_1
                                || RG_qe34_ID == R.id.rbtn_e34_2
                                || RG_qe34_ID == R.id.rbtn_e34_3
                                || RG_qe34_ID == R.id.rbtn_e34_4
                                || RG_qe34_ID == R.id.rbtn_e34_5
                                || RG_qe34_ID == R.id.rbtn_e34_6
                                || RG_qe34_ID == R.id.rbtn_e34_7
                                || RG_qe34_ID == R.id.rbtn_e34_8
                                || RG_qe34_ID == R.id.rbtn_e34_9) {
                            Q_e34_layout.setVisibility(View.GONE);
                            Q_e35_layout.setVisibility(View.VISIBLE);
                            e34 = radioButton.getTag().toString();
                        } else if (RG_qe34_ID == R.id.rbtn_e34_99) {
                            e34 = radioButton.getTag().toString();
                            Q_e34_layout.setVisibility(View.GONE);
                            Q_e36_layout.setVisibility(View.VISIBLE);
                        } else if (RG_qe34_ID == R.id.rbtn_e34_10) {
                            txtQ_e34_other.setVisibility(View.VISIBLE);
                            e34_other = txtQ_e34_other.getText().toString();
                            if (StringUtils.isEmpty(e34_other)) {
                                Toast.makeText(Q_sectionE.this, "Please Enter Other", Toast.LENGTH_SHORT).show();
                            } else {
                                Q_e34_layout.setVisibility(View.GONE);
                                Q_e35_layout.setVisibility(View.VISIBLE);
                                e34 = radioButton.getTag().toString();
                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e35_layout.getVisibility() == View.VISIBLE) {
                        if (question35CheckBoxCounter > 0) {
                            if (txt_e35_other.getVisibility() == View.VISIBLE) {
                                String otherText = txt_e35_other.getText().toString();
                                if (StringUtils.isEmpty(otherText)) {
                                    Toast.makeText(Q_sectionE.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    e35_other = otherText;
                                    if (question35CheckBoxTags != null && question35CheckBoxTags.size() > 0) {
                                        e35a = question35CheckBoxTags.get(0);
                                        if (question35CheckBoxTags.size() > 1) {
                                            e35b = question35CheckBoxTags.get(1);
                                        }
                                        if (question35CheckBoxTags.size() > 2) {
                                            e35c = question35CheckBoxTags.get(2);
                                        }
                                    }
                                    Q_e35_layout.setVisibility(View.GONE);

                                    Q_e36_layout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (question35CheckBoxTags != null && question35CheckBoxTags.size() > 0) {
                                    e35a = question35CheckBoxTags.get(0);
                                    if (question35CheckBoxTags.size() > 1) {
                                        e35b = question35CheckBoxTags.get(1);
                                    }
                                    if (question35CheckBoxTags.size() > 2) {
                                        e35c = question35CheckBoxTags.get(2);
                                    }
                                }
                                Q_e35_layout.setVisibility(View.GONE);

                                Q_e36_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Q_e36_layout.getVisibility() == View.VISIBLE) {
                        if (question36CheckBoxCounter > 0) {
                            if (txt_e36_other.getVisibility() == View.VISIBLE) {
                                String otherText = txt_e36_other.getText().toString();
                                if (StringUtils.isEmpty(otherText)) {
                                    Toast.makeText(Q_sectionE.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    e36_other = otherText;
                                    if (question36CheckBoxTags != null && question36CheckBoxTags.size() > 0) {
                                        e36a = question36CheckBoxTags.get(0);
                                        if (question36CheckBoxTags.size() > 1) {
                                            e36b = question36CheckBoxTags.get(1);
                                        }
                                        if (question36CheckBoxTags.size() > 2) {
                                            e36c = question36CheckBoxTags.get(2);
                                        }
                                    }
                                    Q_e36_layout.setVisibility(View.GONE);

                                    Q_e37_layout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                if (question36CheckBoxTags != null && question36CheckBoxTags.size() > 0) {
                                    e36a = question36CheckBoxTags.get(0);
                                    if (question36CheckBoxTags.size() > 1) {
                                        e36b = question36CheckBoxTags.get(1);
                                    }
                                    if (question36CheckBoxTags.size() > 2) {
                                        e36c = question36CheckBoxTags.get(2);
                                    }
                                }
                                Q_e36_layout.setVisibility(View.GONE);

                                Q_e37_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(Q_sectionE.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e37_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe37_ID);
                        if (RG_qe37_ID == R.id.rbtn_e37_1) {
                            Q_e37_layout.setVisibility(View.GONE);
                            Q_e38_layout.setVisibility(View.VISIBLE);
                            e37 = radioButton.getTag().toString();
                        } else if (RG_qe37_ID == R.id.rbtn_e37_2) {
                            e37 = radioButton.getTag().toString();
                            Q_e37_layout.setVisibility(View.GONE);
                            Q_e42_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Q_e38_layout.getVisibility() == View.VISIBLE) {
                        if (question38CheckBoxCounter > 0) {
                            if (question38CheckBoxTags != null && question38CheckBoxTags.size() > 0) {
                                e38a = question38CheckBoxTags.get(0);
                                if (question38CheckBoxTags.size() > 1) {
                                    e38b = question38CheckBoxTags.get(1);
                                }
                                if (question38CheckBoxTags.size() > 2) {
                                    e38c = question38CheckBoxTags.get(2);
                                }
                            }
                            Q_e38_layout.setVisibility(View.GONE);

                            Q_e39_layout.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(Q_sectionE.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                        }
                    } else if (Q_e39_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe39_ID);
                        if (RG_qe39_ID == R.id.rbtn_e39_1) {
                            Q_e39_layout.setVisibility(View.GONE);
                            Q_e40_layout.setVisibility(View.VISIBLE);
                            e39 = radioButton.getTag().toString();
                        } else if (RG_qe39_ID == R.id.rbtn_e39_2) {
                            Q_e39_layout.setVisibility(View.GONE);
                            Q_e41_layout.setVisibility(View.VISIBLE);
                            e39 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e40_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe40_ID);
                        if (RG_qe40_ID == R.id.rbtn_e40_1
                                || RG_qe40_ID == R.id.rbtn_e40_2
                                || RG_qe40_ID == R.id.rbtn_e40_3
                                || RG_qe40_ID == R.id.rbtn_e40_4) {
                            Q_e40_layout.setVisibility(View.GONE);
                            Q_e41_layout.setVisibility(View.VISIBLE);
                            e40 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e41_layout.getVisibility() == View.VISIBLE) {
                        RadioButton radioButton = findViewById(RG_qe41_ID);
                        if (RG_qe41_ID == R.id.rbtn_e41_1 || RG_qe41_ID == R.id.rbtn_e41_2) {
                            Q_e41_layout.setVisibility(View.GONE);
                            Q_e42_layout.setVisibility(View.VISIBLE);
                            e41 = radioButton.getTag().toString();
                        } else {
                            Toast.makeText(Q_sectionE.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }

                    } else if (Q_e42_layout.getVisibility() == View.VISIBLE) {
                        e42_zaat_code = txt_e42_zaat_code.getText().toString();
                        e42_other = txt_e42_other_zaat.getText().toString();
                        if (StringUtils.isEmpty(e42_zaat_code)) {
                            Toast.makeText(Q_sectionE.this, "Add Zaat Code", Toast.LENGTH_SHORT).show();
                        } else if (txt_e42_other_zaat.getVisibility() == View.VISIBLE && StringUtils.isEmpty(e42_other)) {

                            Toast.makeText(Q_sectionE.this, "Add Other Zakat Code", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(Q_sectionE.this, Section_F_table1.class);
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
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("ai_name", ai_name);
                            intent.putExtra("isFromEdit", isFromEdit);
                            intent.putExtra("id", id);
                            startActivityForResult(intent, 88);
                            //startActivity(intent);
                        }

                    }
                } catch (Exception e) {
                    message(e.getMessage(), v);

                }

            }
        });


        RG_qe1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RG_qe1_ID = RG_qe1.getCheckedRadioButtonId();
                if (RG_qe1_ID == R.id.rbtn_e1_2 || RG_qe1_ID == R.id.rbtn_e1_4) {
                    txtQ_e1_other.setVisibility(View.VISIBLE);
                } else {
                    txtQ_e1_other.setText("");
                    txtQ_e1_other.setVisibility(View.GONE);
                }


            }
        });


        RG_qe4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RG_qe4_ID = RG_qe4.getCheckedRadioButtonId();
                if (RG_qe4_ID == R.id.rbtn_e4_2 || RG_qe4_ID == R.id.rbtn_e4_4) {
                    txtQ_e4_other.setVisibility(View.VISIBLE);
                } else {
                    txtQ_e4_other.setText("");
                    txtQ_e4_other.setVisibility(View.GONE);
                }


            }
        });


        RG_qe34.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RG_qe34_ID = RG_qe34.getCheckedRadioButtonId();
                if (RG_qe34_ID == R.id.rbtn_e34_10) {
                    //do work when radioButton1 is active
                    txtQ_e34_other.setVisibility(View.VISIBLE);
                } else {
                    txtQ_e34_other.setText("");
                    txtQ_e34_other.setVisibility(View.GONE);
                }


            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_sectionE.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "sectionE");
                startActivityForResult(intent, 88);
            }
        });

        btniBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {

        int RG_qe1_ID = RG_qe1.getCheckedRadioButtonId();
        int RG_qe2_ID = RG_qe2.getCheckedRadioButtonId();
        int RG_qe4_ID = RG_qe4.getCheckedRadioButtonId();
        int RG_qe5_ID = RG_qe5.getCheckedRadioButtonId();
        int RG_qe7_ID = RG_qe7.getCheckedRadioButtonId();
        int RG_qe9_ID = RG_qe9.getCheckedRadioButtonId();
        int RG_qe10_ID = RG_qe10.getCheckedRadioButtonId();
        int RG_qe11b_ID = rg_qE11b.getCheckedRadioButtonId();
        int RG_qe17b_ID = rg_qE17b.getCheckedRadioButtonId();
        int RG_qe14_ID = RG_qe14.getCheckedRadioButtonId();
        int RG_qe18_ID = RG_qe18.getCheckedRadioButtonId();
        int RG_qe21_ID = RG_qe21.getCheckedRadioButtonId();
        int RG_qe22_ID = RG_qe22.getCheckedRadioButtonId();
        int RG_qe23_ID = RG_qe23.getCheckedRadioButtonId();
        int RG_qe27_ID = RG_qe27.getCheckedRadioButtonId();
        int RG_qe39_ID = RG_qe39.getCheckedRadioButtonId();
        int RG_qe37_ID = RG_qe37.getCheckedRadioButtonId();
        int RG_qe31_ID = RG_qe31.getCheckedRadioButtonId();
        int RG_qe34_ID = RG_qe34.getCheckedRadioButtonId();
        int RG_qe29_ID = RG_qe29.getCheckedRadioButtonId();
        int RG_qe25_ID = RG_qe25.getCheckedRadioButtonId();


        if (Q_e42_layout.getVisibility() == View.VISIBLE) {

            if (RG_qe37_ID == R.id.rbtn_e37_2) {

                Q_e37_layout.setVisibility(View.VISIBLE);
                Q_e42_layout.setVisibility(View.GONE);
            } else {

                Q_e41_layout.setVisibility(View.VISIBLE);
                Q_e42_layout.setVisibility(View.GONE);
            }
        } else if (Q_e41_layout.getVisibility() == View.VISIBLE) {

            if (RG_qe39_ID == R.id.rbtn_e39_2) {


                Q_e39_layout.setVisibility(View.VISIBLE);
                Q_e41_layout.setVisibility(View.GONE);
            } else {


                Q_e40_layout.setVisibility(View.VISIBLE);
                Q_e41_layout.setVisibility(View.GONE);
            }
        } else if (Q_e40_layout.getVisibility() == View.VISIBLE) {


            Q_e39_layout.setVisibility(View.VISIBLE);
            Q_e40_layout.setVisibility(View.GONE);
        } else if (Q_e39_layout.getVisibility() == View.VISIBLE) {

            Q_e38_layout.setVisibility(View.VISIBLE);
            Q_e39_layout.setVisibility(View.GONE);
        } else if (Q_e38_layout.getVisibility() == View.VISIBLE) {

            Q_e37_layout.setVisibility(View.VISIBLE);
            Q_e38_layout.setVisibility(View.GONE);
        } else if (Q_e37_layout.getVisibility() == View.VISIBLE) {

            Q_e36_layout.setVisibility(View.VISIBLE);
            Q_e37_layout.setVisibility(View.GONE);
        } else if (Q_e36_layout.getVisibility() == View.VISIBLE) {

            if (RG_qe34_ID == R.id.rbtn_e34_99) {
                Q_e34_layout.setVisibility(View.VISIBLE);
                Q_e36_layout.setVisibility(View.GONE);
            } else {
                Q_e35_layout.setVisibility(View.VISIBLE);
                Q_e36_layout.setVisibility(View.GONE);
            }
        } else if (Q_e35_layout.getVisibility() == View.VISIBLE) {
            Q_e34_layout.setVisibility(View.VISIBLE);
            Q_e35_layout.setVisibility(View.GONE);
        } else if (Q_e34_layout.getVisibility() == View.VISIBLE) {

            Q_e33_layout.setVisibility(View.VISIBLE);
            Q_e34_layout.setVisibility(View.GONE);
        } else if (Q_e33_layout.getVisibility() == View.VISIBLE) {

            if (RG_qe31_ID == R.id.rbtn_e31_2) {
                Q_e31_layout.setVisibility(View.VISIBLE);
                Q_e33_layout.setVisibility(View.GONE);
            } else {
                Q_e32a_layout.setVisibility(View.VISIBLE);
                Q_e33_layout.setVisibility(View.GONE);
            }
        } else if (Q_e32a_layout.getVisibility() == View.VISIBLE) {


            Q_e32_layout.setVisibility(View.VISIBLE);
            Q_e32a_layout.setVisibility(View.GONE);

        } else if (Q_e32_layout.getVisibility() == View.VISIBLE) {


            Q_e31_layout.setVisibility(View.VISIBLE);
            Q_e32_layout.setVisibility(View.GONE);

        } else if (Q_e31_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe29_ID == R.id.rbtn_e29_1) {

                Q_e29_layout.setVisibility(View.VISIBLE);
                Q_e30_layout.setVisibility(View.GONE);
                Q_e31_layout.setVisibility(View.GONE);
            } else {

                Q_e29_layout.setVisibility(View.GONE);
                Q_e30_layout.setVisibility(View.VISIBLE);
                Q_e31_layout.setVisibility(View.GONE);
            }
        } else if (Q_e30_layout.getVisibility() == View.VISIBLE) {


            Q_e29_layout.setVisibility(View.VISIBLE);
            Q_e30_layout.setVisibility(View.GONE);

        } else if (Q_e29_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe27_ID == R.id.rbtn_e27_2) {
                Q_e27_layout.setVisibility(View.VISIBLE);
                Q_e28_layout.setVisibility(View.GONE);
                Q_e29_layout.setVisibility(View.GONE);
            } else {

                Q_e27_layout.setVisibility(View.GONE);
                Q_e28_layout.setVisibility(View.VISIBLE);
                Q_e29_layout.setVisibility(View.GONE);
            }
        } else if (Q_e28_layout.getVisibility() == View.VISIBLE) {


            Q_e27_layout.setVisibility(View.VISIBLE);
            Q_e28_layout.setVisibility(View.GONE);

        } else if (Q_e27_layout.getVisibility() == View.VISIBLE) {

            if (RG_qe25_ID == R.id.rbtn_e25_2) {

                Q_e25_layout.setVisibility(View.VISIBLE);
                Q_e26_layout.setVisibility(View.GONE);
                Q_e27_layout.setVisibility(View.GONE);
            } else {
                Q_e25_layout.setVisibility(View.GONE);
                Q_e27_layout.setVisibility(View.GONE);

                Q_e26_layout.setVisibility(View.VISIBLE);
            }
        } else if (Q_e26_layout.getVisibility() == View.VISIBLE) {

            Q_e25_layout.setVisibility(View.VISIBLE);
            Q_e26_layout.setVisibility(View.GONE);

        } else if (Q_e25_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe23_ID == R.id.rbtn_e23_1 || RG_qe23_ID == R.id.rbtn_e23_2) {

                Q_e23_layout.setVisibility(View.VISIBLE);
                Q_e21_layout.setVisibility(View.GONE);
                Q_e25_layout.setVisibility(View.GONE);
                Q_e14_layout.setVisibility(View.GONE);
                Q_e24_layout.setVisibility(View.GONE);
            } else if (RG_qe21_ID == R.id.rbtn_e21_2) {

                Q_e21_layout.setVisibility(View.VISIBLE);
                Q_e23_layout.setVisibility(View.GONE);
                Q_e25_layout.setVisibility(View.GONE);
                Q_e14_layout.setVisibility(View.GONE);
                Q_e24_layout.setVisibility(View.GONE);

            } else if (RG_qe14_ID == R.id.rbtn_e14_2) {

                Q_e14_layout.setVisibility(View.VISIBLE);
                Q_e25_layout.setVisibility(View.GONE);
                Q_e23_layout.setVisibility(View.GONE);
                Q_e21_layout.setVisibility(View.GONE);
                Q_e24_layout.setVisibility(View.GONE);
            } else {

                Q_e24_layout.setVisibility(View.VISIBLE);
                Q_e25_layout.setVisibility(View.GONE);
                Q_e14_layout.setVisibility(View.GONE);
                Q_e21_layout.setVisibility(View.GONE);
                Q_e23_layout.setVisibility(View.GONE);
            }

        } else if (Q_e24_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe22_ID == R.id.rbtn_e22_2) {

                Q_e22_layout.setVisibility(View.VISIBLE);
                Q_e24_layout.setVisibility(View.GONE);
            } else {

                Q_e23_layout.setVisibility(View.VISIBLE);
                Q_e22_layout.setVisibility(View.GONE);
                Q_e24_layout.setVisibility(View.GONE);
            }
        } else if (Q_e23_layout.getVisibility() == View.VISIBLE) {

            Q_e22_layout.setVisibility(View.VISIBLE);
            Q_e23_layout.setVisibility(View.GONE);
        } else if (Q_e22_layout.getVisibility() == View.VISIBLE) {

            Q_e21_layout.setVisibility(View.VISIBLE);
            Q_e22_layout.setVisibility(View.GONE);
        } else if (Q_e21_layout.getVisibility() == View.VISIBLE) {

            Q_e20_layout.setVisibility(View.VISIBLE);
            Q_e21_layout.setVisibility(View.GONE);
        } else if (Q_e20_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe18_ID == R.id.rbtn_e18_2) {

                Q_e18_layout.setVisibility(View.VISIBLE);
                Q_e19_layout.setVisibility(View.GONE);
                Q_e20_layout.setVisibility(View.GONE);
            } else {

                Q_e19_layout.setVisibility(View.VISIBLE);
                Q_e18_layout.setVisibility(View.GONE);
                Q_e20_layout.setVisibility(View.GONE);
            }
        } else if (Q_e19_layout.getVisibility() == View.VISIBLE) {

            Q_e18_layout.setVisibility(View.VISIBLE);
            Q_e19_layout.setVisibility(View.GONE);
        } else if (Q_e18_layout.getVisibility() == View.VISIBLE) {
            if (!StringUtils.isEmpty(e17d)) {
                Q_e18_layout.setVisibility(View.GONE);
                qE17d_layout.setVisibility(View.VISIBLE);
            } else if (!StringUtils.isEmpty(e17c)) {
                Q_e18_layout.setVisibility(View.GONE);
                qE17c_layout.setVisibility(View.VISIBLE);
            } else {
                Q_e18_layout.setVisibility(View.GONE);
                qE17a_layout.setVisibility(View.VISIBLE);
            }
        } else if (qE17d_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe17b_ID == R.id.rbtn_E17b_2) {
                qE17d_layout.setVisibility(View.GONE);
                qE17b_layout.setVisibility(View.VISIBLE);
                e17c = "";
            } else {
                qE17d_layout.setVisibility(View.GONE);
                qE17c_layout.setVisibility(View.VISIBLE);
            }
        } else if (qE17c_layout.getVisibility() == View.VISIBLE) {
            qE17c_layout.setVisibility(View.GONE);
            qE17b_layout.setVisibility(View.VISIBLE);
        } else if (qE17b_layout.getVisibility() == View.VISIBLE) {
            qE17b_layout.setVisibility(View.GONE);
            qE17a_layout.setVisibility(View.VISIBLE);
        } else if (qE17a_layout.getVisibility() == View.VISIBLE) {
            qE17a_layout.setVisibility(View.GONE);
            Q_e17_layout.setVisibility(View.VISIBLE);
        } else if (Q_e17_layout.getVisibility() == View.VISIBLE) {

            Q_e16_layout.setVisibility(View.VISIBLE);
            Q_e17_layout.setVisibility(View.GONE);
        } else if (Q_e16_layout.getVisibility() == View.VISIBLE) {

            Q_e15_layout.setVisibility(View.VISIBLE);
            Q_e16_layout.setVisibility(View.GONE);
        } else if (Q_e15_layout.getVisibility() == View.VISIBLE) {
            Q_e14_layout.setVisibility(View.VISIBLE);
            Q_e15_layout.setVisibility(View.GONE);
        } else if (Q_e14_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe7_ID == R.id.rbtn_e7_2 || RG_qe7_ID == R.id.rbtn_e7_3) {

                Q_e7_layout.setVisibility(View.VISIBLE);
                Q_e14_layout.setVisibility(View.GONE);
                Q_e13_layout.setVisibility(View.GONE);
                Q_e10_layout.setVisibility(View.GONE);
            } else if (RG_qe10_ID == R.id.rbtn_e10_1 || RG_qe10_ID == R.id.rbtn_e10_2 || RG_qe10_ID == R.id.rbtn_e10_3 || RG_qe10_ID == R.id.rbtn_e10_4) {

                Q_e10_layout.setVisibility(View.VISIBLE);
                Q_e13_layout.setVisibility(View.GONE);
                Q_e7_layout.setVisibility(View.GONE);
                Q_e14_layout.setVisibility(View.GONE);
            } else {

                Q_e13_layout.setVisibility(View.VISIBLE);
                Q_e14_layout.setVisibility(View.GONE);
                Q_e7_layout.setVisibility(View.GONE);
                Q_e10_layout.setVisibility(View.GONE);
            }
        } else if (Q_e13_layout.getVisibility() == View.VISIBLE) {

            Q_e12_layout.setVisibility(View.VISIBLE);
            Q_e13_layout.setVisibility(View.GONE);
        } else if (Q_e12_layout.getVisibility() == View.VISIBLE) {
            if (!StringUtils.isEmpty(e11d)) {
                Q_e12_layout.setVisibility(View.GONE);
                qE11d_layout.setVisibility(View.VISIBLE);
            } else if (!StringUtils.isEmpty(e11c)) {
                Q_e12_layout.setVisibility(View.GONE);
                qE11c_layout.setVisibility(View.VISIBLE);
            } else {
                qE11a_layout.setVisibility(View.VISIBLE);
                Q_e12_layout.setVisibility(View.GONE);
            }
        } else if (qE11d_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe11b_ID == R.id.rbtn_E11b_2) {
                qE11d_layout.setVisibility(View.GONE);
                qE11b_layout.setVisibility(View.VISIBLE);
                e11c = "";
            } else {
                qE11d_layout.setVisibility(View.GONE);
                qE11c_layout.setVisibility(View.VISIBLE);
            }
        } else if (qE11c_layout.getVisibility() == View.VISIBLE) {
            qE11c_layout.setVisibility(View.GONE);
            qE11b_layout.setVisibility(View.VISIBLE);
        } else if (qE11b_layout.getVisibility() == View.VISIBLE) {
            qE11b_layout.setVisibility(View.GONE);
            qE11a_layout.setVisibility(View.VISIBLE);
        } else if (qE11a_layout.getVisibility() == View.VISIBLE) {
            qE11a_layout.setVisibility(View.GONE);
            Q_e11_layout.setVisibility(View.VISIBLE);
        } else if (Q_e11_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe9_ID == R.id.rbtn_e9_1) {

                Q_e9_layout.setVisibility(View.VISIBLE);
                Q_e10_layout.setVisibility(View.GONE);
                Q_e11_layout.setVisibility(View.GONE);
            } else {
                Q_e10_layout.setVisibility(View.VISIBLE);
                Q_e11_layout.setVisibility(View.GONE);
            }

        } else if (Q_e10_layout.getVisibility() == View.VISIBLE) {
            e9 = "";
            Q_e9_layout.setVisibility(View.VISIBLE);
            Q_e10_layout.setVisibility(View.GONE);
        } else if (Q_e9_layout.getVisibility() == View.VISIBLE) {

            Q_e8_layout.setVisibility(View.VISIBLE);
            Q_e9_layout.setVisibility(View.GONE);
        } else if (Q_e8_layout.getVisibility() == View.VISIBLE) {

            Q_e7_layout.setVisibility(View.VISIBLE);
            Q_e8_layout.setVisibility(View.GONE);
        } else if (Q_e7_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe5_ID == R.id.rbtn_e4_5) {

                Q_e5_layout.setVisibility(View.GONE);
                Q_e4_layout.setVisibility(View.VISIBLE);
                Q_e6b_layout.setVisibility(View.GONE);
                Q_e7_layout.setVisibility(View.GONE);
            } else if (RG_qe4_ID == R.id.rbtn_e5_3) {

                Q_e4_layout.setVisibility(View.GONE);
                Q_e6b_layout.setVisibility(View.GONE);
                Q_e5_layout.setVisibility(View.VISIBLE);
                Q_e7_layout.setVisibility(View.GONE);
            } else {
                if (!StringUtils.isEmpty(e6b)) {
                    Q_e4_layout.setVisibility(View.GONE);
                    Q_e6b_layout.setVisibility(View.VISIBLE);
                    Q_e5_layout.setVisibility(View.GONE);
                    Q_e7_layout.setVisibility(View.GONE);
                } else {
//                    if (!StringUtils.isEmpty(e6a)) {
                    Q_e4_layout.setVisibility(View.GONE);
                    Q_e6a_layout.setVisibility(View.VISIBLE);
                    Q_e5_layout.setVisibility(View.GONE);
                    Q_e7_layout.setVisibility(View.GONE);
                }
            }
        } else if (Q_e6b_layout.getVisibility() == View.VISIBLE) {

            Q_e6a_layout.setVisibility(View.VISIBLE);
            Q_e6b_layout.setVisibility(View.GONE);
        } else if (Q_e6a_layout.getVisibility() == View.VISIBLE) {
            Q_e5_layout.setVisibility(View.VISIBLE);
            Q_e6a_layout.setVisibility(View.GONE);
        } else if (Q_e5_layout.getVisibility() == View.VISIBLE) {

            Q_e4_layout.setVisibility(View.VISIBLE);
            Q_e5_layout.setVisibility(View.GONE);
        } else if (Q_e4_layout.getVisibility() == View.VISIBLE) {
            if (RG_qe1_ID == R.id.rbtn_e1_5) {

                Q_e1_layout.setVisibility(View.VISIBLE);
                Q_e4_layout.setVisibility(View.GONE);
            } else {
                if (!StringUtils.isEmpty(e3b)) {
                    Q_e3b_layout.setVisibility(View.VISIBLE);
                    Q_e4_layout.setVisibility(View.GONE);
                } else if (!StringUtils.isEmpty(e3a)) {
                    Q_e3a_layout.setVisibility(View.VISIBLE);
                    Q_e4_layout.setVisibility(View.GONE);
                } else {
                    Q_e4_layout.setVisibility(View.GONE);
                    Q_e2_layout.setVisibility(View.VISIBLE);
                }
            }
        } else if (Q_e3b_layout.getVisibility() == View.VISIBLE) {


            Q_e3b_layout.setVisibility(View.GONE);
            Q_e3a_layout.setVisibility(View.VISIBLE);
        } else if (Q_e3a_layout.getVisibility() == View.VISIBLE) {

            Q_e2_layout.setVisibility(View.VISIBLE);
            Q_e3a_layout.setVisibility(View.GONE);
        } else if (Q_e2_layout.getVisibility() == View.VISIBLE) {

            Q_e1_layout.setVisibility(View.VISIBLE);
            Q_e2_layout.setVisibility(View.GONE);
        } else if (Q_e1_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        }

    }


    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Q_sectionE.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;

        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);


        String network = getSimNetwork();
        MubLog.cpnsoleLog("getSimNetwork  " + network);
        if (!StringUtils.isEmpty(network)) {
            if (network.equalsIgnoreCase("Jazz")) {
                phoneNumber = "660" + phoneNumber;
            } else if (network.equalsIgnoreCase("Telenor")) {
                phoneNumber = "880" + phoneNumber;
            } else {
                phoneNumber = "770" + phoneNumber;
            }
        } else {

        }
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);

    }

    private String getSimNetwork() {
        TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return phoneMgr.getSimOperatorName();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!StringUtils.isEmpty(farmer_cellphone)) {
                    DialUserNumber(farmer_cellphone);
                } else {
                    Toast.makeText(Q_sectionE.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();

            }
        }
    }

    void message(String message, View v) {

        Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
        snackbar.setDuration(10000);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
        snackbar.setAction("OKAY", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do Something here
            }
        });
        snackbar.show();
    }

    void SetQuestion15CheckBoxListener() {
        checkbox_e15_1.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_1));
        checkbox_e15_2.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_2));
        checkbox_e15_3.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_3));
        checkbox_e15_4.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_4));
        checkbox_e15_5.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_5));
        checkbox_e15_6.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_6));
        checkbox_e15_7.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_7));
        checkbox_e15_8.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_8));
        checkbox_e15_9.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_9));
        checkbox_e15_10.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_10));
        checkbox_e15_11.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_11));
        checkbox_e15_12.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_12));
        checkbox_e15_13.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_13));
        checkbox_e15_14.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_14));
        checkbox_e15_15.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_15));
        checkbox_e15_16.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_16));
        checkbox_e15_17.setOnClickListener(view -> SetQuestion15CheckBoxItemClick(checkbox_e15_17));

    }

    void SetQuestion15CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question15CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question15CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_e15_17) {
                    txtQ_e15_other.setVisibility(View.VISIBLE);
                    txt_e15_2_other.setVisibility(View.VISIBLE);
                    txt_e15_3_other.setVisibility(View.VISIBLE);

                }
                question15CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_e15_17) {
                    txtQ_e15_other.setVisibility(View.GONE);
                    txt_e15_2_other.setVisibility(View.GONE);
                    txt_e15_3_other.setVisibility(View.GONE);

                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_e15_17) {
                txtQ_e15_other.setVisibility(View.GONE);
                txt_e15_2_other.setVisibility(View.GONE);
                txt_e15_3_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question15CheckBoxCounter--;
            question15CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SetQuestion33CheckBoxListener() {
        checkbox_e33_1.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_1));
        checkbox_e33_2.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_2));
        checkbox_e33_3.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_3));
        checkbox_e33_4.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_4));
        checkbox_e33_5.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_5));
        checkbox_e33_6.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_6));
        checkbox_e33_7.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_7));
        checkbox_e33_8.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_8));
        checkbox_e33_9.setOnClickListener(view -> SetQuestion33CheckBoxItemClick(checkbox_e33_9));

    }

    void SetQuestion33CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question33CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question33CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_e33_9) {
                    txt_e33_other.setVisibility(View.VISIBLE);
                }
                question33CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_e33_9) {
                    txt_e33_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_e33_9) {
                txt_e33_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question33CheckBoxCounter--;
            question33CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SetQuestion35CheckBoxListener() {
        checkbox_e35_1.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_1));
        checkbox_e35_2.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_2));
        checkbox_e35_3.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_3));
        checkbox_e35_4.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_4));
        checkbox_e35_5.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_5));
        checkbox_e35_6.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_6));
        checkbox_e35_7.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_7));
        checkbox_e35_8.setOnClickListener(view -> SetQuestion35CheckBoxItemClick(checkbox_e35_8));

    }

    void SetQuestion35CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question35CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question35CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_e35_8) {
                    txt_e35_other.setVisibility(View.VISIBLE);
                }
                question35CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_e35_8) {
                    txt_e35_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_e35_8) {
                txt_e35_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question35CheckBoxCounter--;
            question35CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SetQuestion36CheckBoxListener() {
        checkbox_e36_1.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_1));
        checkbox_e36_2.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_2));
        checkbox_e36_3.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_3));
        checkbox_e36_4.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_4));
        checkbox_e36_5.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_5));
        checkbox_e36_6.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_6));
        checkbox_e36_7.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_7));
        checkbox_e36_8.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_8));
        checkbox_e36_9.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_9));
        checkbox_e36_10.setOnClickListener(view -> SetQuestion36CheckBoxItemClick(checkbox_e36_10));
    }


    void SetQuestion36CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question36CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question36CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_e36_10) {
                    txt_e36_other.setVisibility(View.VISIBLE);
                }
                question36CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_e36_10) {
                    txt_e36_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_e36_10) {
                txt_e36_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question36CheckBoxCounter--;
            question36CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SetQuestion38CheckBoxListener() {
        checkbox_e38_1.setOnClickListener(view -> SetQuestion38CheckBoxItemClick(checkbox_e38_1));
        checkbox_e38_2.setOnClickListener(view -> SetQuestion38CheckBoxItemClick(checkbox_e38_2));
        checkbox_e38_3.setOnClickListener(view -> SetQuestion38CheckBoxItemClick(checkbox_e38_3));
        checkbox_e38_4.setOnClickListener(view -> SetQuestion38CheckBoxItemClick(checkbox_e38_4));

    }


    void SetQuestion38CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            if (question38CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question38CheckBoxCounter++;
                question38CheckBoxTags.add(checkBox.getTag().toString());
            } else {
                checkBox.setChecked(false);
            }

        } else {
            checkBox.setChecked(false);
            question38CheckBoxCounter--;
            question38CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SaveData() {
        boolean error = false;
        try {

//            error =  section_Validations( e3b,e6b);
//            if(error){
//
//
//            }else {

                createNewColumnsBeforeInsertion(DatabaseAdapter.SectionETable);

                databaseAccess.saveSection_E_Data(enum_name, enum_code, emp_id, order_id, FarmerID, e1, e1_other, e2, e3a, e3b, e4, e4_other, e5, e6a, e6b, e7, e8, e9, e10, e11, e11a, e11b, e11c, e11d, e12, e13, e14, e15a
                        , e15b, e15c, e15_other, e15_2_other, e15_3_other, e16, e17, e17a, e17b, e17c, e17d, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e32a, e33a, e33b, e33c, e33_other, e34
                        , e34_other, e35a, e35b, e35c, e35_other, e36a, e36b, e36c, e36_other, e37, e38a, e38b, e38c, e38_other, e39, e40, e41, e42_zaat_code, e42_other, comments);


                MubLog.cpnsoleLog("inside SaveData FarmerID " + FarmerID + " SectionBTable  =  " + DatabaseAdapter.SectionETable);
                databaseAccess.update_insert_or_updated_in_phone_at(FarmerID, DatabaseAdapter.SectionETable);

            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionETable);



//            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {
            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY "+tableName);


            String patch_querry  = "UPDATE  "+tableName+" SET  e1_other = '' , e2='' , e3a ='' , e3b = ''  WHERE e1 = '5' AND (e1_other != '' OR e2 !='' OR e3a !='' OR e3b != '')";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  e4_other = '' , e5 = ''  , e6a ='' , e6b = ''  WHERE `e4` = '5' AND (e4_other != '' OR e5 != ''  OR e6a !='' OR e6b != '' )";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET e8 = '' , e9 = '' , e10 = '' ,  e11 = '' , e11a = '' , e11b = '' , e11c = '' , e11d = '' , e12 = '' , e13 = '' WHERE e7 IN (2,3)  AND (e8 != '' OR e9 != '' OR e10 != '' OR  e11 != '' OR e11a != '' OR e11b != '' OR e11c != '' OR e11d != '' OR e12 != '' OR e13 != '')";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET `e15a`='' , `e15b`='' , `e15c`='' , `e15_other`='' , `e15_2_other`='' , `e15_3_other`='' , `e16`='' , `e17`='' , `e17a`='' , `e17b`='' , `e17c`='' , `e17d`='' , `e18`='' , `e19`='' , `e20`='' , `e21`='' , `e22`='' , `e23`='' , `e24`='' WHERE e14 = 2 AND ( `e15a`!='' OR `e15b`!='' OR `e15c`!='' OR `e15_other`!='' OR `e15_2_other`!='' OR `e15_3_other`!='' OR `e16`!='' OR `e17`!='' OR `e17a`!='' OR `e17b`!='' OR `e17c`!='' OR `e17d`!='' OR `e18`!='' OR `e19`!='' OR `e20`!='' OR `e21`!='' OR `e22`!='' OR `e23`!='' OR `e24`!='' )";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET e26 ='' WHERE e25 = '2' AND e26 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET e27 = '1' WHERE e4 IN (1,2,3,4) AND e27 != '1'";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  `e38a`='',`e38b`='',`e38c`='',`e38_other`='',`e39`='',`e40`='',`e41`='' WHERE e37 = '2' AND (`e38a`!='' OR `e38b`!='' OR `e38c`!='' OR `e38_other`!='' OR `e39`!='' OR `e40`!='' OR `e41`!='' )";

            databaseAccess.execute_patch_query(patch_querry);




        }catch(Exception ex) {
            MubLog.cpnsoleLog("inside Section 1"+ex.toString());

        }
    }


    boolean  section_Validations(String e3b, String e6b){

        boolean error = false;
        try {


            //  E3b & E6b must be fill if B26==3 - But these questions still blanked.
            error = check_E3b_E6b_must_fill (e3b,e6b);
            if(error){

                return  error;
            }




        } catch (Exception e) {
            MubLog.cpnsoleLog("inside check_G6_7_is_not_equal_to_0"+e.toString());
            return false;
        }
        return  error;
    }

    private boolean check_E3b_E6b_must_fill(String e3b, String e6b) {

        try {


            MubLog.cpnsoleLog("check_E3b_E6b_must_fill");
            MubLog.cpnsoleLog("b26"+b26);
            MubLog.cpnsoleLog("e3b"+e3b);
            MubLog.cpnsoleLog("e6b"+e6b);

            if (b26.equalsIgnoreCase("3")){

                if(e3b.equalsIgnoreCase("")||e6b.equalsIgnoreCase("")) {
                    Toast.makeText(Q_sectionE.this, "e3b &  e6b should be filled.", Toast.LENGTH_SHORT).show();
               return  true;

                }
            }else  {


                return false;


            }

            return false;
        } catch (Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog("[Q_sectionG] inside check_G6_7_is_not_equal_to_0() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside check_G6_7_is_not_equal_to_0"+e.toString());
            return false;
        }
    }



    private void createNewColumnsBeforeInsertion(String tableName) {


        try {

            String  insert_or_updated_in_phone_at = "ALTER TABLE `"+tableName+"` ADD `insert_or_updated_in_phone_at` TEXT DEFAULT '' ";
            databaseAccess. createMissingColumn(insert_or_updated_in_phone_at);

            String  enum_code = "ALTER TABLE `"+tableName+"` ADD `enum_code` TEXT DEFAULT '' ";
            databaseAccess. createMissingColumn(enum_code);

            String  enum_name = "ALTER TABLE `"+tableName+"` ADD `enum_name` TEXT DEFAULT '' ";
            databaseAccess. createMissingColumn(enum_name);




        }catch(Exception ex) {
            MubLog.cpnsoleLog("inside Section 1"+ex.toString());

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                e42_zaat_code = data.getStringExtra("zaatCode");
                txt_e42_zaat_code.setText(e42_zaat_code);
                if (e42_zaat_code.equals("999")) {
                    txt_e42_other_zaat.setVisibility(View.VISIBLE);
                } else {
                    txt_e42_other_zaat.setVisibility(View.GONE);
                    txt_e42_other_zaat.setText("");
                }

            } else if (requestCode == 88) {
                boolean isDataUpdated = data.getBooleanExtra("isDataUpdated", false);
                if (isDataUpdated) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("isDataUpdated", isDataUpdated);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }

            }
        }
    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionEData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                e1 = cursor.getString(cursor.getColumnIndex("e1"));
                e1_other = cursor.getString(cursor.getColumnIndex("e1_other"));
                e2 = cursor.getString(cursor.getColumnIndex("e2"));
                e3a = cursor.getString(cursor.getColumnIndex("e3a"));
                e3b = cursor.getString(cursor.getColumnIndex("e3b"));
                e4 = cursor.getString(cursor.getColumnIndex("e4"));
                e4_other = cursor.getString(cursor.getColumnIndex("e4_other"));
                e5 = cursor.getString(cursor.getColumnIndex("e5"));
                e6a = cursor.getString(cursor.getColumnIndex("e6a"));
                e6b = cursor.getString(cursor.getColumnIndex("e6b"));
                e7 = cursor.getString(cursor.getColumnIndex("e7"));
                e8 = cursor.getString(cursor.getColumnIndex("e8"));
                e9 = cursor.getString(cursor.getColumnIndex("e9"));
                e10 = cursor.getString(cursor.getColumnIndex("e10"));
                e11 = cursor.getString(cursor.getColumnIndex("e11"));

                e11a = cursor.getString(cursor.getColumnIndex("e11a"));
                e11b = cursor.getString(cursor.getColumnIndex("e11b"));
                e11c = cursor.getString(cursor.getColumnIndex("e11c"));
                e11d = cursor.getString(cursor.getColumnIndex("e11d"));

                e12 = cursor.getString(cursor.getColumnIndex("e12"));
                e13 = cursor.getString(cursor.getColumnIndex("e13"));
                e14 = cursor.getString(cursor.getColumnIndex("e14"));
                e15a = cursor.getString(cursor.getColumnIndex("e15a"));
                e15b = cursor.getString(cursor.getColumnIndex("e15b"));
                e15c = cursor.getString(cursor.getColumnIndex("e15c"));
                e15_other = cursor.getString(cursor.getColumnIndex("e15_other"));
                e15_2_other = cursor.getString(cursor.getColumnIndex("e15_2_other"));
                e15_3_other = cursor.getString(cursor.getColumnIndex("e15_3_other"));
                e16 = cursor.getString(cursor.getColumnIndex("e16"));
                e17 = cursor.getString(cursor.getColumnIndex("e17"));

                e17a = cursor.getString(cursor.getColumnIndex("e17a"));
                e17b = cursor.getString(cursor.getColumnIndex("e17b"));
                e17c = cursor.getString(cursor.getColumnIndex("e17c"));
                e17d = cursor.getString(cursor.getColumnIndex("e17d"));

                e18 = cursor.getString(cursor.getColumnIndex("e18"));
                e19 = cursor.getString(cursor.getColumnIndex("e19"));
                e20 = cursor.getString(cursor.getColumnIndex("e20"));
                e21 = cursor.getString(cursor.getColumnIndex("e21"));
                e22 = cursor.getString(cursor.getColumnIndex("e22"));
                e23 = cursor.getString(cursor.getColumnIndex("e23"));
                e24 = cursor.getString(cursor.getColumnIndex("e24"));
                e25 = cursor.getString(cursor.getColumnIndex("e25"));
                e26 = cursor.getString(cursor.getColumnIndex("e26"));
                e27 = cursor.getString(cursor.getColumnIndex("e27"));
                e28 = cursor.getString(cursor.getColumnIndex("e28"));
                e29 = cursor.getString(cursor.getColumnIndex("e29"));
                e30 = cursor.getString(cursor.getColumnIndex("e30"));
                e31 = cursor.getString(cursor.getColumnIndex("e31"));
                e32 = cursor.getString(cursor.getColumnIndex("e32"));
                e32a = cursor.getString(cursor.getColumnIndex("e32a"));
                e33a = cursor.getString(cursor.getColumnIndex("e33a"));
                e33b = cursor.getString(cursor.getColumnIndex("e33b"));
                e33c = cursor.getString(cursor.getColumnIndex("e33c"));
                e33_other = cursor.getString(cursor.getColumnIndex("e33_other"));
                e34 = cursor.getString(cursor.getColumnIndex("e34"));
                e34_other = cursor.getString(cursor.getColumnIndex("e34_other"));
                e35a = cursor.getString(cursor.getColumnIndex("e35a"));
                e35b = cursor.getString(cursor.getColumnIndex("e35b"));
                e35c = cursor.getString(cursor.getColumnIndex("e35c"));
                e35_other = cursor.getString(cursor.getColumnIndex("e35_other"));
                e36a = cursor.getString(cursor.getColumnIndex("e36a"));
                e36b = cursor.getString(cursor.getColumnIndex("e36b"));
                e36c = cursor.getString(cursor.getColumnIndex("e36c"));
                e36_other = cursor.getString(cursor.getColumnIndex("e36_other"));
                e37 = cursor.getString(cursor.getColumnIndex("e37"));
                e38a = cursor.getString(cursor.getColumnIndex("e38a"));
                e38b = cursor.getString(cursor.getColumnIndex("e38b"));
                e38c = cursor.getString(cursor.getColumnIndex("e38c"));
                e38_other = cursor.getString(cursor.getColumnIndex("e38_other"));
                e39 = cursor.getString(cursor.getColumnIndex("e39"));
                e40 = cursor.getString(cursor.getColumnIndex("e40"));
                e41 = cursor.getString(cursor.getColumnIndex("e41"));
                e42_zaat_code = cursor.getString(cursor.getColumnIndex("e42"));
                e42_other = cursor.getString(cursor.getColumnIndex("e42_other"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();

        if (!StringUtils.isEmpty(e1)) {
            for (int i = 0; i < RG_qe1.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe1.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e1)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e1_other)) {
            txtQ_e1_other.setText(e1_other);
            txtQ_e1_other.setVisibility(View.VISIBLE);
        } else {
            txtQ_e1_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(e2)) {
            for (int i = 0; i < RG_qe2.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe2.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e2)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e3a)) {
            for (int i = 0; i < RG_qe3a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe3a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e3a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e3b)) {
            for (int i = 0; i < RG_qe3b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe3b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e3b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e4)) {
            for (int i = 0; i < RG_qe4.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe4.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e4)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e4_other)) {
            txtQ_e4_other.setText(e4_other);
            txtQ_e4_other.setVisibility(View.VISIBLE);
        } else {
            txtQ_e4_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(e5)) {
            for (int i = 0; i < RG_qe5.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe5.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e5)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e6a)) {
            for (int i = 0; i < RG_qe6a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe6a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e6a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e6b)) {
            for (int i = 0; i < RG_qe6b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe6b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e6b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e7)) {
            for (int i = 0; i < RG_qe7.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe7.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e7)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e8)) {
            for (int i = 0; i < RG_qe8.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe8.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e8)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e9)) {
            for (int i = 0; i < RG_qe9.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe9.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e9)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e10)) {
            for (int i = 0; i < RG_qe10.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe10.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e10)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e11)) {
            for (int i = 0; i < RG_qe11.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe11.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e11)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(e11a)) {
            for (int i = 0; i < rg_qE11a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qE11a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e11a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(e11b)) {
            for (int i = 0; i < rg_qE11b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qE11b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e11b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e11c)) {
            edt_e11c.setText(e11c);
        }
        if (!StringUtils.isEmpty(e11d)) {
            edt_e11d.setText(e11d);
        }

        if (!StringUtils.isEmpty(e12)) {
            txtQ_e12.setText(e12);
        }
        if (!StringUtils.isEmpty(e13)) {
            txtQ_e13.setText(e13);
        }


        if (!StringUtils.isEmpty(e14)) {
            for (int i = 0; i < RG_qe14.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe14.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e14)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        for (int i = 0; i < Q_e15_layout.getChildCount(); i++) {

            View childBView = Q_e15_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(e15a) && checkBox.getTag().toString().equalsIgnoreCase(e15a)) {
                        question15CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e15b) && checkBox.getTag().toString().equalsIgnoreCase(e15b)) {
                        question15CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e15c) && checkBox.getTag().toString().equalsIgnoreCase(e15c)) {
                        question15CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }

        if (!StringUtils.isEmpty(e15_other)) {
            txtQ_e15_other.setText(e15_other);
            txtQ_e15_other.setVisibility(View.VISIBLE);
        } else {
            txtQ_e15_other.setVisibility(View.GONE);
        }
        if (!StringUtils.isEmpty(e15_2_other)) {
            txt_e15_2_other.setText(e15_2_other);
            txt_e15_2_other.setVisibility(View.VISIBLE);
        } else {
            txt_e15_2_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(e15_3_other)) {
            txt_e15_3_other.setText(e15_3_other);
            txt_e15_3_other.setVisibility(View.VISIBLE);
        } else {
            txt_e15_3_other.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(e16)) {
            for (int i = 0; i < RG_qe16.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe16.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e16)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e17)) {
            for (int i = 0; i < RG_qe17.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe17.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e17)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e17a)) {
            for (int i = 0; i < rg_qE17a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qE17a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e17a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(e17b)) {
            for (int i = 0; i < rg_qE17b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qE17b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e17b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e17c)) {
            edt_e17c.setText(e17c);
        }
        if (!StringUtils.isEmpty(e17d)) {
            edt_e17d.setText(e17d);
        }

        if (!StringUtils.isEmpty(e18)) {
            for (int i = 0; i < RG_qe18.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe18.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e18)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e19)) {
            for (int i = 0; i < RG_qe19.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe19.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e19)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e20)) {
            txtQ_e20.setText(e20);
        }

        if (!StringUtils.isEmpty(e21)) {
            for (int i = 0; i < RG_qe21.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe21.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e21)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e22)) {
            for (int i = 0; i < RG_qe22.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe22.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e22)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e23)) {
            for (int i = 0; i < RG_qe23.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe23.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e23)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e24)) {
            for (int i = 0; i < RG_qe24.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe24.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e24)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e25)) {
            for (int i = 0; i < RG_qe25.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe25.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e25)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e26)) {
            for (int i = 0; i < RG_qe26.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe26.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e26)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e27)) {
            for (int i = 0; i < RG_qe27.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe27.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e27)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e28)) {
            for (int i = 0; i < RG_qe28.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe28.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e28)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e29)) {
            for (int i = 0; i < RG_qe29.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe29.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e29)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e30)) {
            for (int i = 0; i < RG_qe30.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe30.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e30)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e31)) {
            for (int i = 0; i < RG_qe31.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe31.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e31)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e32)) {
            for (int i = 0; i < RG_qe32.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe32.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e32)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e32a)) {
            for (int i = 0; i < RG_qe32a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe32a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e32a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        for (int i = 0; i < Q_e33_layout.getChildCount(); i++) {

            View childBView = Q_e33_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(e33a) && checkBox.getTag().toString().equalsIgnoreCase(e33a)) {
                        question33CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e33b) && checkBox.getTag().toString().equalsIgnoreCase(e33b)) {
                        question33CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e33c) && checkBox.getTag().toString().equalsIgnoreCase(e33c)) {
                        question33CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }

        if (!StringUtils.isEmpty(e33_other)) {
            txt_e33_other.setText(e33_other);
            txt_e33_other.setVisibility(View.VISIBLE);
        } else {
            txt_e33_other.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(e34)) {
            for (int i = 0; i < RG_qe34.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe34.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e34)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        for (int i = 0; i < Q_e35_layout.getChildCount(); i++) {

            View childBView = Q_e35_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(e35a) && checkBox.getTag().toString().equalsIgnoreCase(e35a)) {
                        question35CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e35b) && checkBox.getTag().toString().equalsIgnoreCase(e35b)) {
                        question35CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e35c) && checkBox.getTag().toString().equalsIgnoreCase(e35c)) {
                        question35CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }

        if (!StringUtils.isEmpty(e35_other)) {
            txt_e35_other.setText(e35_other);
            txt_e35_other.setVisibility(View.VISIBLE);
        } else {
            txt_e35_other.setVisibility(View.GONE);
        }


        for (int i = 0; i < Q_e36_layout.getChildCount(); i++) {

            View childBView = Q_e36_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(e36a) && checkBox.getTag().toString().equalsIgnoreCase(e36a)) {
                        question36CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e36b) && checkBox.getTag().toString().equalsIgnoreCase(e36b)) {
                        question36CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e36c) && checkBox.getTag().toString().equalsIgnoreCase(e36c)) {
                        question36CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }

        if (!StringUtils.isEmpty(e36_other)) {
            txt_e36_other.setText(e36_other);
            txt_e36_other.setVisibility(View.VISIBLE);
        } else {
            txt_e36_other.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(e37)) {
            for (int i = 0; i < RG_qe37.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe37.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e37)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        for (int i = 0; i < Q_e38_layout.getChildCount(); i++) {

            View childBView = Q_e38_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(e38a) && checkBox.getTag().toString().equalsIgnoreCase(e38a)) {
                        question38CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e38b) && checkBox.getTag().toString().equalsIgnoreCase(e38b)) {
                        question38CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(e38c) && checkBox.getTag().toString().equalsIgnoreCase(e38c)) {
                        question38CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }


        if (!StringUtils.isEmpty(e39)) {
            for (int i = 0; i < RG_qe39.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe39.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e39)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e40)) {
            for (int i = 0; i < RG_qe40.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe40.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e40)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(e41)) {
            for (int i = 0; i < RG_qe41.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qe41.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(e41)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(e42_zaat_code)) {
            txt_e42_zaat_code.setText(e42_zaat_code);
        }


        if (!StringUtils.isEmpty(e42_other)) {
            txt_e42_other_zaat.setText(e42_other);
            txt_e42_other_zaat.setVisibility(View.VISIBLE);
        } else {
            txt_e42_other_zaat.setVisibility(View.GONE);
        }

        //////////////////////////////
    }

    void readSection_b() {
        try {

            Cursor cursor = databaseAccess.getSectionBData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                b26 = cursor.getString(cursor.getColumnIndex("b26"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    void GetEnum() {
        try {

            Cursor cursor = databaseAccess.getSectionBData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                enum_code = cursor.getString(cursor.getColumnIndex("enum_code"));
                enum_name = cursor.getString(cursor.getColumnIndex("enum_name"));
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }





}

