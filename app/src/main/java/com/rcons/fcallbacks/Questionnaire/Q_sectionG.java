package com.rcons.fcallbacks.Questionnaire;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Q_sectionG extends AppCompatActivity {
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    DatabaseAdapter databaseAccess;
    String userName = "";
    boolean isPendingCall = false;
    String region = "";
    String emp_id = "";
    String id;
    String order_id = "";
    TextView txtFarmerID, txtEmpID, txtOrderID;
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String farmer_cellphone;
    @BindView(R.id.qg1_layout)
    LinearLayout qg1_layout;
    @BindView(R.id.qg2_layout)
    LinearLayout qg2_layout;
    @BindView(R.id.qg3_layout)
    LinearLayout qg3_layout;
    @BindView(R.id.qg4_layout)
    LinearLayout qg4_layout;
    @BindView(R.id.qg5_layout)
    LinearLayout qg5_layout;
    @BindView(R.id.qg6_g7_layout)
    LinearLayout qg6_g7_layout;
    @BindView(R.id.rg_qg1)
    RadioGroup rg_qg1;
    @BindView(R.id.rg_qg2)
    RadioGroup rg_qg2;
    @BindView(R.id.edt_g3_kanal)
    EditText edt_g3_kanal;
    @BindView(R.id.edt_g3_acre)
    EditText edt_g3_acre;
    @BindView(R.id.edt_g4)
    EditText edt_g4;
    @BindView(R.id.edt_g5)
    EditText edt_g5;
    @BindView(R.id.edt_g6_1)
    EditText edt_g6_1;
    @BindView(R.id.edt_g7_1)
    EditText edt_g7_1;
    @BindView(R.id.edt_g6_2)
    EditText edt_g6_2;
    @BindView(R.id.edt_g7_2)
    EditText edt_g7_2;
    @BindView(R.id.edt_g6_3)
    EditText edt_g6_3;
    @BindView(R.id.edt_g7_3)
    EditText edt_g7_3;
    @BindView(R.id.edt_g6_4)
    EditText edt_g6_4;
    @BindView(R.id.edt_g7_4)
    EditText edt_g7_4;
    @BindView(R.id.edt_g6_5)
    EditText edt_g6_5;
    @BindView(R.id.edt_g6_6)
    EditText edt_g6_6;
    @BindView(R.id.edt_g6_7_other)
    EditText edt_g6_7_other;
    @BindView(R.id.edt_g6_7)
    EditText edt_g6_7;
    @BindView(R.id.edt_g7_7)
    EditText edt_g7_7;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;
    @BindView(R.id.btnAddMore)
    AppCompatCheckBox btnAddMore;
    @BindView(R.id.other_layout)
    LinearLayout other_layout;

    @BindView(R.id.qg3b_layout)
    LinearLayout qg3b_layout;
    @BindView(R.id.qg4b_layout)
    LinearLayout qg4b_layout;
    @BindView(R.id.qg4c_layout)
    LinearLayout qg4c_layout;

    @BindView(R.id.rg_qg3b)
    RadioGroup rg_qg3b;

    @BindView(R.id.rg_qg4b)
    RadioGroup rg_qg4b;

    @BindView(R.id.rg_qg4c)
    RadioGroup rg_qg4c;

    @BindView(R.id.g5_textview)
    TextView g5_textview;

    @BindView(R.id.G4_Total_textview)
    TextView G4_Total_textview;

    @BindView(R.id.G5_sellout_textview)
    TextView G5_sellout_textview;
    @BindView(R.id.g7_price_textview)
    TextView g7_price_textview;
    @BindView(R.id.q7_prices_txt)
    TextView q7_prices_txt;


   @BindView(R.id.rbtn_g1_2)
    RadioButton rbtn_g1_2;

   @BindView(R.id.rbtn_g1_1)
    RadioButton rbtn_g1_1;

    String g1 = "";
    String g2 = "";
    String g3_kanal = "";
    String g3_acre = "";
    String g4 = "";
    String g5 = "";
    String g6_1 = "";
    String g7_1 = "";
    String g6_2 = "";
    String g7_2 = "";
    String g6_3 = "";
    String g7_3 = "";
    String g6_4 = "";
    String g7_4 = "";
    String g6_5 = "";
    String g6_6 = "";
    String g6_7_other = "";
    String g6_7 = "";
    String g7_7 = "";
    String comments = "";

    String g3b = "";
    String g4b = "";
    String g4c = "";
    String enum_name = "";
    String enum_code = "";

    ImageView btniBack;

    int total = 0;
    int total_all = 0;

    Integer total_kanals = 0;
    Integer Minor_total_kanals = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_g);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(Q_sectionG.this);
        databaseAccess.Open();
        btniBack = findViewById(R.id.btnBack);
        userName = getIntent().getStringExtra("username");
        isPendingCall = getIntent().getBooleanExtra("isPendingCall", false);
        region = getIntent().getStringExtra("region");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        FarmerID = getIntent().getStringExtra("farmerID");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");
        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);
        id = getIntent().getStringExtra("id");
        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);

        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }
        readFromDataBase();
        LoadPreviousData();
        GetEnum();
        EditTextValidations(edt_g6_1, edt_g7_1);
        EditTextValidations(edt_g6_2, edt_g7_2);
        EditTextValidations(edt_g6_3, edt_g7_3);
        EditTextValidations(edt_g6_4, edt_g7_4);
        EditTextValidations(edt_g6_7, edt_g7_7);

        rbtn_g1_2.setChecked(true);
        rbtn_g1_1.setEnabled(false);


        btnAddMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    other_layout.setVisibility(View.VISIBLE);
                } else {
                    other_layout.setVisibility(View.GONE);
                }
            }
        });

        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(farmer_cellphone)) {
                        DialUserNumber(farmer_cellphone);
                    } else {
                        Toast.makeText(Q_sectionG.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rg_qg1_id = rg_qg1.getCheckedRadioButtonId();
                int rg_qg2_id = rg_qg2.getCheckedRadioButtonId();

                int rg_qg3b_id = rg_qg3b.getCheckedRadioButtonId();
                int rg_qg4b_id = rg_qg4b.getCheckedRadioButtonId();
                int rg_qg4c_id = rg_qg4c.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(Q_sectionG.this);
                SaveData();
                if (qg1_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qg1_id);

                    if (rg_qg1_id == R.id.rbtn_g1_1) {
                        qg1_layout.setVisibility(View.GONE);
                        qg2_layout.setVisibility(View.VISIBLE);
                        g1 = radioButton.getTag().toString();
                    } else if (rg_qg1_id == R.id.rbtn_g1_2) {
                        g1 = radioButton.getTag().toString();
                        qg1_layout.setVisibility(View.GONE);
                        qg2_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionG.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qg2_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qg2_id);
                    g2 = radioButton.getTag().toString();
                    if (rg_qg2_id == R.id.rbtn_g2_1) {
                        g2 = radioButton.getTag().toString();
                        qg2_layout.setVisibility(View.GONE);
                        qg3_layout.setVisibility(View.VISIBLE);
                        SaveData();
                    } else if (rg_qg2_id == R.id.rbtn_g2_2) {
                        SaveData();
                        Intent intent = new Intent(Q_sectionG.this, Q_SectionG_b.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("isPendingCall", isPendingCall);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmerID", FarmerID);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("id", id);
                        startActivityForResult(intent, 88);
                    } else {
                        Toast.makeText(Q_sectionG.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qg3_layout.getVisibility() == View.VISIBLE) {
                    g3_acre = edt_g3_acre.getText().toString();
                    g3_kanal = edt_g3_kanal.getText().toString();
                    if (StringUtils.isEmpty(g3_acre)) {
                        Toast.makeText(Q_sectionG.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    }
                    if (StringUtils.isEmpty(g3_kanal)) {
                        Toast.makeText(Q_sectionG.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(g3_kanal) > 7) {
                        Toast.makeText(Q_sectionG.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else {
                        SaveData();
                        qg3_layout.setVisibility(View.GONE);
                        qg3b_layout.setVisibility(View.VISIBLE);
                    }
                } else if (qg3b_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qg3b_id);
                    g3b = radioButton.getTag().toString();
                    if (rg_qg3b_id == R.id.rbtn_g3b_1) {
                        g3b = radioButton.getTag().toString();
                        qg3b_layout.setVisibility(View.GONE);
                        qg4_layout.setVisibility(View.VISIBLE);
                        SaveData();
                    } else if (rg_qg3b_id == R.id.rbtn_g3b_2) {
                        SaveData();
                        Intent intent = new Intent(Q_sectionG.this, Q_SectionG_b.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("isPendingCall", isPendingCall);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmerID", FarmerID);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("id", id);
                        startActivityForResult(intent, 88);
                    } else {
                        Toast.makeText(Q_sectionG.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qg4_layout.getVisibility() == View.VISIBLE) {

                    g4 = edt_g4.getText().toString();
                    if (StringUtils.isEmpty(g4)) {
                        Toast.makeText(Q_sectionG.this, "پیداوار من میں میں نوٹ کریں ", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(g4) < 0 || Integer.parseInt(g4) > 40000) && !g4.equalsIgnoreCase("-99")) {
                        Toast.makeText(Q_sectionG.this, "Please Enter in 0-40000", Toast.LENGTH_SHORT).show();
                    } else {
                        G4_Total_textview.setText("G4 Total : " + g4);
                        g5_textview.setText("آپ نے( " + g4 + ")من میں سے کتنی  گندم  فروخت کی؟");
                        SaveData();
                        qg4_layout.setVisibility(View.GONE);
                        qg4b_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qg4b_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qg4b_id);

                    if (rg_qg4b_id == R.id.rbtn_g4b_1) {
                        g4c = "";
                        qg4b_layout.setVisibility(View.GONE);
                        qg5_layout.setVisibility(View.VISIBLE);
                        g4b = radioButton.getTag().toString();
                        edt_g7_1.setVisibility(View.VISIBLE);
                        edt_g7_2.setVisibility(View.VISIBLE);
                        edt_g7_3.setVisibility(View.VISIBLE);
                        edt_g7_4.setVisibility(View.VISIBLE);
                        edt_g7_7.setVisibility(View.VISIBLE);
                        q7_prices_txt.setVisibility(View.VISIBLE);
                        g7_price_textview.setVisibility(View.VISIBLE);
                    } else if (rg_qg4b_id == R.id.rbtn_g4b_2) {
                        g4b = radioButton.getTag().toString();
                        qg4b_layout.setVisibility(View.GONE);
                        qg4c_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionG.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qg4c_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qg4c_id);
                    g4c = radioButton.getTag().toString();
                    if (rg_qg4c_id == R.id.rbtn_g4c_2) {
                        g4c = radioButton.getTag().toString();
                        g5 = "";
                        edt_g6_1.setText("0");
                        edt_g6_2.setText("0");
                        edt_g6_3.setText("0");
                        edt_g6_4.setText("0");

                        qg4c_layout.setVisibility(View.GONE);
                        qg6_g7_layout.setVisibility(View.VISIBLE);
                        edt_g7_1.setVisibility(View.INVISIBLE);
                        edt_g7_2.setVisibility(View.INVISIBLE);
                        edt_g7_3.setVisibility(View.INVISIBLE);
                        edt_g7_4.setVisibility(View.INVISIBLE);
                        edt_g7_7.setVisibility(View.INVISIBLE);
                        q7_prices_txt.setVisibility(View.GONE);
                        g7_price_textview.setVisibility(View.INVISIBLE);
                        SaveData();
                    } else if (rg_qg4c_id == R.id.rbtn_g4c_1) {
                        SaveData();
                        Intent intent = new Intent(Q_sectionG.this, Q_SectionG_b.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("isPendingCall", isPendingCall);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmerID", FarmerID);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("id", id);
                        startActivityForResult(intent, 88);
                    } else {
                        Toast.makeText(Q_sectionG.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qg5_layout.getVisibility() == View.VISIBLE) {
                    g5 = edt_g5.getText().toString();
                    if (StringUtils.isEmpty(g5)) {
                        Toast.makeText(Q_sectionG.this, " وزن من میں میں نوٹ کریں ", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(g5) > Integer.parseInt(g4)) && !g5.equalsIgnoreCase("-99") && !g4.equalsIgnoreCase("-99")) {
                        Toast.makeText(Q_sectionG.this, "G5 Must be less than G4", Toast.LENGTH_SHORT).show();
                    } else {
                        G5_sellout_textview.setText("G5 sell out : " + g5);
                        SaveData();
                        qg5_layout.setVisibility(View.GONE);
                        qg6_g7_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qg6_g7_layout.getVisibility() == View.VISIBLE) {
                    g6_1 = edt_g6_1.getText().toString();
                    g6_2 = edt_g6_2.getText().toString();
                    g6_3 = edt_g6_3.getText().toString();
                    g6_4 = edt_g6_4.getText().toString();
                    g6_5 = edt_g6_5.getText().toString();
                    g6_6 = edt_g6_6.getText().toString();
                    g6_7 = edt_g6_7.getText().toString();

                    g7_1 = edt_g7_1.getText().toString();
                    g7_2 = edt_g7_2.getText().toString();
                    g7_3 = edt_g7_3.getText().toString();
                    g7_4 = edt_g7_4.getText().toString();
                    g7_7 = edt_g7_7.getText().toString();
                    g6_7_other = edt_g6_7_other.getText().toString();

                    if (StringUtils.isEmpty(g6_1)) {
                        Toast.makeText(Q_sectionG.this, "Please enter G1 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g7_1.isEnabled() && StringUtils.isEmpty(g7_1) && rg_qg4b_id == R.id.rbtn_g4b_1) {
                        Toast.makeText(Q_sectionG.this, "Please enter G1 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g6_2)) {
                        Toast.makeText(Q_sectionG.this, "Please enter G2 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g7_2.isEnabled() && StringUtils.isEmpty(g7_2) && rg_qg4b_id == R.id.rbtn_g4b_1) {
                        Toast.makeText(Q_sectionG.this, "Please enter G2 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g6_3)) {
                        Toast.makeText(Q_sectionG.this, "Please enter G3 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g7_3.isEnabled() && StringUtils.isEmpty(g7_3) && rg_qg4b_id == R.id.rbtn_g4b_1) {
                        Toast.makeText(Q_sectionG.this, "Please enter G3 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g6_4)) {
                        Toast.makeText(Q_sectionG.this, "Please enter G4 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g7_4.isEnabled() && StringUtils.isEmpty(g7_4) && rg_qg4b_id == R.id.rbtn_g4b_1) {
                        Toast.makeText(Q_sectionG.this, "Please enter G4 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g6_5)) {
                        Toast.makeText(Q_sectionG.this, "Please enter G5 Weight", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g6_6)) {
                        Toast.makeText(Q_sectionG.this, "Please enter G6 Weight", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g6_7) && other_layout.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionG.this, "Please enter G7 Weight", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g7_7) && other_layout.getVisibility() == View.VISIBLE && rg_qg4b_id == R.id.rbtn_g4b_1 && g6_7.length() > 0 && !g6_7.equalsIgnoreCase("0")) {
                        Toast.makeText(Q_sectionG.this, "Please enter G7 Price", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g6_7_other) && other_layout.getVisibility() == View.VISIBLE && g6_7.length() > 0 && !g6_7.equalsIgnoreCase("0")) {
                        Toast.makeText(Q_sectionG.this, "Please enter G7 Other", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            total = Integer.parseInt(g6_1) + Integer.parseInt(g6_2) + Integer.parseInt(g6_3) + Integer.parseInt(g6_4);
                            total_all = Integer.parseInt(g6_1) + Integer.parseInt(g6_2) + Integer.parseInt(g6_3) + Integer.parseInt(g6_4) + Integer.parseInt(g6_5) + Integer.parseInt(g6_6) + Integer.parseInt(g6_7);
                            ////////////////////////// Check G4 With All total and if -99 then ignore
                            if (!StringUtils.isEmpty(g5)) {
                                if (total == Integer.parseInt(g5) || Integer.parseInt(g5) == -99 || Integer.parseInt(g6_1) == -99 || Integer.parseInt(g6_2) == -99
                                        || Integer.parseInt(g6_3) == -99 || Integer.parseInt(g6_4) == -99 || Integer.parseInt(g5) == -99) {
                                    ////////////////////////// Check G4 With All total and if -99 then ignore
                                    if (total_all == Integer.parseInt(g4) || Integer.parseInt(g4) == -99 || Integer.parseInt(g6_1) == -99 || Integer.parseInt(g6_2) == -99
                                            || Integer.parseInt(g6_3) == -99 || Integer.parseInt(g6_4) == -99 || Integer.parseInt(g6_5) == -99
                                            || Integer.parseInt(g6_6) == -99 || Integer.parseInt(g6_7) == -99 || Integer.parseInt(g4) == -99 && rg_qg4b_id == R.id.rbtn_g4b_1) {
                                        SaveData();
                                        NextSection();
                                    } else {
                                        ShowLogoutMessage(Q_sectionG.this, "Error", "برائے مہربانی g6_1 to g6_g7 کا ٹوٹل g4 کے برابر ہونا چاہئے۔ g4 اس کی کل پیداوار ہے جبکہ g4 = " + g4 + " ہے اور total =" + total_all + "ہے");
                                    }


                                } else {

                                    ShowLogoutMessage(Q_sectionG.this, "Error", "برائے مہربانی  g6_g1 to g6_g4 کا ٹوٹل g5 کے برابر ہونا چاہئے۔ g5 اس نے کل میں سے کتنی گندم فروخت کی ہے. جبکہ g5 =  " + g5 + " ہے اور total =" + total + "ہے");
                                }
                            } else {
                                if (total_all == Integer.parseInt(g4) || Integer.parseInt(g6_1) == -99 || Integer.parseInt(g6_2) == -99
                                        || Integer.parseInt(g6_3) == -99 || Integer.parseInt(g6_4) == -99 || Integer.parseInt(g6_5) == -99
                                        || Integer.parseInt(g6_6) == -99 || Integer.parseInt(g6_7) == -99 || Integer.parseInt(g4) == -99 && rg_qg4b_id == R.id.rbtn_g4b_1) {
                                    SaveData();
                                    NextSection();
                                } else {
                                    ShowLogoutMessage(Q_sectionG.this, "Error", "برائے مہربانی g6_1 to g6_g7 کا ٹوٹل g4 کے برابر ہونا چاہئے۔ g4 اس کی کل پیداوار ہے جبکہ g4 = " + g4 + " ہے اور total =" + total_all + "ہے");
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_sectionG.this, AddReportActivity.class);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "sectionG");
                startActivityForResult(intent, 88);
            }
        });
        btniBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        int rg_qg4b_id = rg_qg4b.getCheckedRadioButtonId();
        int rg_qg4c_id = rg_qg4c.getCheckedRadioButtonId();

        if (qg6_g7_layout.getVisibility() == View.VISIBLE) {
            if (rg_qg4c_id == R.id.rbtn_g4c_2) {
                qg4c_layout.setVisibility(View.VISIBLE);
                qg6_g7_layout.setVisibility(View.GONE);

            } else {
                qg5_layout.setVisibility(View.VISIBLE);
                qg6_g7_layout.setVisibility(View.GONE);

            }
        } else if (qg5_layout.getVisibility() == View.VISIBLE) {
            if (rg_qg4b_id == R.id.rbtn_g4b_1) {
                qg4b_layout.setVisibility(View.VISIBLE);
                qg5_layout.setVisibility(View.GONE);

            } else {
                qg4c_layout.setVisibility(View.VISIBLE);
                qg5_layout.setVisibility(View.GONE);
            }
        } else if (qg4c_layout.getVisibility() == View.VISIBLE) {
            qg4b_layout.setVisibility(View.VISIBLE);
            qg4c_layout.setVisibility(View.GONE);

        } else if (qg4b_layout.getVisibility() == View.VISIBLE) {
            qg4_layout.setVisibility(View.VISIBLE);
            qg4b_layout.setVisibility(View.GONE);

        } else if (qg4_layout.getVisibility() == View.VISIBLE) {
            qg3b_layout.setVisibility(View.VISIBLE);
            qg4_layout.setVisibility(View.GONE);

        } else if (qg3b_layout.getVisibility() == View.VISIBLE) {
            qg3_layout.setVisibility(View.VISIBLE);
            qg3b_layout.setVisibility(View.GONE);

        } else if (qg3_layout.getVisibility() == View.VISIBLE) {
            qg2_layout.setVisibility(View.VISIBLE);
            qg3_layout.setVisibility(View.GONE);

        } else if (qg2_layout.getVisibility() == View.VISIBLE) {

            qg1_layout.setVisibility(View.VISIBLE);
            qg2_layout.setVisibility(View.GONE);

        } else if (qg1_layout.getVisibility() == View.VISIBLE) {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 88) {
            boolean isDataUpdated = data.getBooleanExtra("isDataUpdated", false);
            if (isDataUpdated) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("isDataUpdated", isDataUpdated);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

        }
    }

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Q_sectionG.this,
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
                    Toast.makeText(Q_sectionG.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void SaveData() {


        boolean error = false;
        try {


//           error =  section_Validations(enum_name,enum_code,emp_id, order_id, FarmerID, g1, g2, g3_kanal, g3_acre, g3b, g4, g4b, g4c, g5, g6_1, g7_1, g6_2, g7_2, g6_3, g7_3, g6_4, g7_4, g6_5, g6_6, g6_7_other, g6_7, g7_7, comments);
//
//
//           if(error){
//
//
//           }else{


           createNewColumnsBeforeInsertion (DatabaseAdapter.SectionGTable);
            databaseAccess.saveSectionG_Data(enum_name,enum_code,emp_id, order_id, FarmerID, g1, g2, g3_kanal, g3_acre, g3b, g4, g4b, g4c, g5, g6_1, g7_1, g6_2, g7_2, g6_3, g7_3, g6_4, g7_4, g6_5, g6_6, g6_7_other, g6_7, g7_7, comments);

            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionGTable);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionGTable);




            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionGTable);
//           }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {
            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY "+tableName);



            String patch_querry =  "UPDATE  "+tableName+" SET  g4c ='' where g4b = '1' AND g4c !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET `g3_kanal`='',`g3_acre`='',`g3b`='',`g4`='',`g4b`='',`g4c`='',`g5`='',`g6_1`='',`g7_1`='',`g6_2`='',`g7_2`='',`g6_3`='',`g7_3`='',`g6_4`='',`g7_4`='',`g6_5`='',`g6_6`='',`g6_7_other`='',`g6_7`='',`g7_7`='' WHERE g2 = '2' AND ( `g3_kanal`!='' OR `g3_acre`!='' OR `g3b`!='' OR `g4`!='' OR `g4b`!='' OR `g4c`!='' OR `g5`!='' OR `g6_1`!='' OR `g7_1`!='' OR `g6_2`!='' OR `g7_2`!='' OR `g6_3`!='' OR `g7_3`!='' OR `g6_4`!='' OR `g7_4`!='' OR `g6_5`!='' OR `g6_6`!='' OR `g6_7_other`!='' OR `g6_7`!='' OR `g7_7`!='' )";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE  "+tableName+" SET  g7_1 ='' WHERE `g6_1` IN (0,00) and g7_1 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  g7_2 ='' WHERE `g6_2` IN (0,00) and g7_2 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  g7_3 ='' WHERE `g6_3` IN (0,00) and g7_3 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  g7_4 ='' WHERE `g6_4` IN (0,00) and g7_4 !=''";
            databaseAccess.execute_patch_query(patch_querry);

            patch_querry ="UPDATE  "+tableName+" SET  g7_7 ='' , g6_7_other='' WHERE `g6_7` IN (0,00) and (g7_7 !='' OR g6_7_other!='')";
            databaseAccess.execute_patch_query(patch_querry);


        }catch(Exception ex) {
            MubLog.cpnsoleLog("inside Section 1"+ex.toString());

        }
    }



    boolean  section_Validations(String enum_name, String enum_code, String emp_id, String order_id, String farmerID, String g1, String g2, String g3_kanal, String g3_acre, String g3b, String g4, String g4b, String g4c, String g5, String g6_1, String g7_1, String g6_2, String g7_2, String g6_3, String g7_3, String g6_4, String g7_4, String g6_5, String g6_6, String g6_7_other, String g6_7, String g7_7, String comments){

        boolean error = false;
        try {


            //  If G6_7 is not equal to 0 then G7_7 is must fill - But G7_7 is blank in many cases
            error = check_G6_7_is_not_equal_to_0(g6_7,g7_7);
            if(error){

                return  error;
            }




        } catch (Exception e) {
            MubLog.cpnsoleLog("inside check_G6_7_is_not_equal_to_0"+e.toString());
            return false;
        }
        return  error;
    }

    private boolean check_G6_7_is_not_equal_to_0(String g6_7, String g7_7) {

        try {


            MubLog.cpnsoleLog("g6_7"+g6_7);
            MubLog.cpnsoleLog("g7_7"+g7_7);

            if (g6_7.equalsIgnoreCase("")){
                return false;
            }

            if(g6_7.equalsIgnoreCase("0")|| g6_7.equalsIgnoreCase("00")||  g6_7.equalsIgnoreCase("000")&& !g6_7.equalsIgnoreCase("0000")) {

                                return  false;
            }else  if (g7_7.equalsIgnoreCase("")) {

                 Toast.makeText(Q_sectionG.this, "g7_7 should be filled.", Toast.LENGTH_SHORT).show();

                 return true;


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


    void message(String message) {
        View contextView = findViewById(R.id.edt_d8b_other);
        Snackbar snackbar = Snackbar.make(contextView, message, Snackbar.LENGTH_LONG);
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

    void ShowLogoutMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.g4_error_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionGData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                g1 = cursor.getString(cursor.getColumnIndex("g1"));
                g2 = cursor.getString(cursor.getColumnIndex("g2"));
                g3_kanal = cursor.getString(cursor.getColumnIndex("g3_kanal"));
                g3_acre = cursor.getString(cursor.getColumnIndex("g3_acre"));
                g3b = cursor.getString(cursor.getColumnIndex("g3b"));
                g4 = cursor.getString(cursor.getColumnIndex("g4"));
                g4b = cursor.getString(cursor.getColumnIndex("g4b"));
                g4c = cursor.getString(cursor.getColumnIndex("g4c"));
                g5 = cursor.getString(cursor.getColumnIndex("g5"));
                g6_1 = cursor.getString(cursor.getColumnIndex("g6_1"));
                g7_1 = cursor.getString(cursor.getColumnIndex("g7_1"));
                g6_2 = cursor.getString(cursor.getColumnIndex("g6_2"));
                g7_2 = cursor.getString(cursor.getColumnIndex("g7_2"));
                g6_3 = cursor.getString(cursor.getColumnIndex("g6_3"));
                g7_3 = cursor.getString(cursor.getColumnIndex("g7_3"));
                g6_4 = cursor.getString(cursor.getColumnIndex("g6_4"));
                g7_4 = cursor.getString(cursor.getColumnIndex("g7_4"));
                g6_5 = cursor.getString(cursor.getColumnIndex("g6_5"));
                g6_6 = cursor.getString(cursor.getColumnIndex("g6_6"));
                g6_7_other = cursor.getString(cursor.getColumnIndex("g6_7_other"));
                g6_7 = cursor.getString(cursor.getColumnIndex("g6_7"));
                g7_7 = cursor.getString(cursor.getColumnIndex("g7_7"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();

        if (!StringUtils.isEmpty(g1)) {
            for (int i = 0; i < rg_qg1.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qg1.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(g1)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(g2)) {
            for (int i = 0; i < rg_qg2.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qg2.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(g2)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(g3b)) {
            for (int i = 0; i < rg_qg3b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qg3b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(g3b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(g4b)) {
            for (int i = 0; i < rg_qg4b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qg4b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(g4b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(g4c)) {
            for (int i = 0; i < rg_qg4c.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qg4c.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(g4c)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(g3_kanal)) {
            edt_g3_kanal.setText(g3_kanal);
        }
        if (!StringUtils.isEmpty(g3_acre)) {
            edt_g3_acre.setText(g3_acre);
        }
        if (!StringUtils.isEmpty(g4)) {
            edt_g4.setText(g4);
        }

        if (!StringUtils.isEmpty(g5)) {
            edt_g5.setText(g5);
        }

        if (!StringUtils.isEmpty(g6_1)) {
            edt_g6_1.setText(g6_1);
        }

        if (!StringUtils.isEmpty(g6_2)) {
            edt_g6_2.setText(g6_2);
        }

        if (!StringUtils.isEmpty(g6_3)) {
            edt_g6_3.setText(g6_3);
        }

        if (!StringUtils.isEmpty(g6_4)) {
            edt_g6_4.setText(g6_4);
        }

        if (!StringUtils.isEmpty(g6_5)) {
            edt_g6_5.setText(g6_5);
        }

        if (!StringUtils.isEmpty(g6_6)) {
            edt_g6_6.setText(g6_6);
        }
        if (!StringUtils.isEmpty(g6_7)) {
            edt_g6_7.setText(g6_7);
        }

        if (!StringUtils.isEmpty(g6_7_other)) {
            edt_g6_7_other.setText(g6_7_other);
        }
        if (!StringUtils.isEmpty(g7_1)) {
            edt_g7_1.setText(g7_1);
        }

        if (!StringUtils.isEmpty(g7_2)) {
            edt_g7_2.setText(g7_2);
        }

        if (!StringUtils.isEmpty(g7_3)) {
            edt_g7_3.setText(g7_3);
        }

        if (!StringUtils.isEmpty(g7_4)) {
            edt_g7_4.setText(g7_4);
        }

        if (!StringUtils.isEmpty(g7_7)) {
            edt_g7_7.setText(g7_7);
        }

        ///////////////////////
    }


    void NextSection() {
        String crop_type = "";
        Cursor cursor = databaseAccess.getSectionF_table_2Recycler(FarmerID);
        ArrayList<Integer> total_kanals_list = new ArrayList<>();
        ArrayList<String> max_acres = new ArrayList<>();
        ArrayList<String> max_crops = new ArrayList<>();
        ArrayList<Integer> Minor_total_kanals_list = new ArrayList<>();
        ArrayList<String> Minor_max_acres = new ArrayList<>();
        ArrayList<String> Minor_max_crops = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String f1_crop = cursor.getString(cursor.getColumnIndex("f1_crop"));
                    String f1_other = cursor.getString(cursor.getColumnIndex("f1_other"));
                    Integer f2a_kanal = cursor.getInt(cursor.getColumnIndex("f2a_kanal"));
                    Integer f2a_acre = cursor.getInt(cursor.getColumnIndex("f2a_acre"));
                    String f2b_crop = cursor.getString(cursor.getColumnIndex("f2b_crop"));
                    String f2b_other = cursor.getString(cursor.getColumnIndex("f2b_other"));
                    String f2b_kanal = cursor.getString(cursor.getColumnIndex("f2b_kanal"));
                    String f2b_acre = cursor.getString(cursor.getColumnIndex("f2b_acre"));

                    Cursor cursor_;
                    if (f1_crop.equalsIgnoreCase("6") || f1_crop.equalsIgnoreCase("7")
                            || f1_crop.equalsIgnoreCase("8") || f1_crop.equalsIgnoreCase("9")
                            || f1_crop.equalsIgnoreCase("10")) {
                        String region_ = "Punjab";
                        cursor_ = databaseAccess.getSection_H_Crops(region_, f1_crop, "Basic Questions");
                    } else {
                        cursor_ = databaseAccess.getSection_H_Crops(region, f1_crop, "Basic Questions");
                    }


                    if ((f1_crop.equalsIgnoreCase("1") || (f1_crop.equalsIgnoreCase("2"))
                            || (f1_crop.equalsIgnoreCase("3")) || (f1_crop.equalsIgnoreCase("4"))
                            || (f1_crop.equalsIgnoreCase("5"))) && (cursor_ != null && cursor_.getCount() > 0)) {
                        Integer acres_kanals = f2a_acre * 8;
                        total_kanals = acres_kanals + f2a_kanal;
                        total_kanals_list.add(total_kanals);
                        max_acres.add(Integer.toString(f2a_acre));
                        max_crops.add(f1_crop);

                        MubLog.cpnsoleLog("GetMaximumCrops " + "Crop ID = " + f1_crop + " Total Kanals = " + Integer.toString(total_kanals) + " f2a_acre = " + Integer.toString(f2a_acre));
                    }

                    if ((f1_crop.equalsIgnoreCase("6") || f1_crop.equalsIgnoreCase("7")
                            || f1_crop.equalsIgnoreCase("8") || f1_crop.equalsIgnoreCase("9")
                            || f1_crop.equalsIgnoreCase("10") || f1_crop.equalsIgnoreCase("11"))
                            && (cursor_ != null && cursor_.getCount() > 0)) {
                        crop_type = "Minor";
                        Integer Minor_acres_kanals = f2a_acre * 8;
                        Minor_total_kanals = Minor_acres_kanals + f2a_kanal;
                        Minor_total_kanals_list.add(Minor_total_kanals);
                        Minor_max_acres.add(Integer.toString(f2a_acre));
                        Minor_max_crops.add(f1_crop);

                        MubLog.cpnsoleLog("Get Minor_ Crop" + "Crop ID = " + f1_crop + " Total Kanals = " + Integer.toString(Minor_total_kanals) + " f2a_acre = " + Integer.toString(f2a_acre));
                    }

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if ((total_kanals_list != null && total_kanals_list.size() > 0) || (Minor_total_kanals_list != null && Minor_total_kanals_list.size() > 0)) {
            Intent intent = new Intent(Q_sectionG.this, Q_sectionH.class);
            intent.putExtra("username", userName);
            intent.putExtra("isPendingCall", isPendingCall);
            intent.putExtra("region", region);
            intent.putExtra("emp_id", emp_id);
            intent.putExtra("order_id", order_id);
            intent.putExtra("farmerID", FarmerID);
            intent.putExtra("farmerDistrict", farmerDistrict);
            intent.putExtra("farmerVillage", farmerVillage);
            intent.putExtra("farmerTehsil", farmerTehsil);
            intent.putExtra("farmer_cellphone", farmer_cellphone);
            intent.putExtra("isFromEdit", isFromEdit);
            intent.putExtra("id", id);

            startActivityForResult(intent, 88);
        } else {
            Intent intent = new Intent(Q_sectionG.this, Q_section1.class);
            intent.putExtra("username", userName);
            intent.putExtra("isPendingCall", isPendingCall);
            intent.putExtra("region", region);
            intent.putExtra("emp_id", emp_id);
            intent.putExtra("order_id", order_id);
            intent.putExtra("farmerID", FarmerID);
            intent.putExtra("farmerDistrict", farmerDistrict);
            intent.putExtra("farmerVillage", farmerVillage);
            intent.putExtra("farmerTehsil", farmerTehsil);
            intent.putExtra("isFromEdit", isFromEdit);
            intent.putExtra("id", id);
            intent.putExtra("farmer_cellphone", farmer_cellphone);
            startActivityForResult(intent, 88);
        }
    }

    void EditTextValidations(EditText check, EditText enable) {
        check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str_check = check.getText().toString();
                if (!str_check.isEmpty()) {
                    if ((str_check.length() > 0) && (!str_check.equalsIgnoreCase("0") && (!str_check.equalsIgnoreCase("00") && (!str_check.equalsIgnoreCase("000"))))) {

                        enable.setEnabled(true);
                        enable.setHint("Price");

                    } else {
                        enable.setEnabled(false);
                        enable.setHint("Not Required");
                        enable.setText("");
                    }

                } else {
                    enable.setEnabled(false);
                    enable.setHint("Price");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
