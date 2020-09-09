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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.app.ActivityCompat;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.VillageSearch.SelectTehsilActivity;
import com.rcons.fcallbacks.VillageSearch.SelectVillageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Q_sectionD extends AppCompatActivity {

    DatabaseAdapter databaseAccess;
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    String userName = "";
    String region = "";
    String emp_id = "";
    String order_id = "";
    Button btnSelectVillage;
    Button btnSelectTehsil;
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String ao_name = "";
    String fa_name = "";
    String ai_name = "";


    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;


    RadioGroup RG_qd1, RG_qd3, RG_qd6, RG_qd7;
    LinearLayout Q_d1_layout, Q_d2_layout, Q_d3_layout, Q_d4_layout, Q_d5_layout, Q_d6_layout, Q_d7_layout;
    EditText D2_ucName, D2_ucCode, D2_VillageName, D2_VillageCode, D3_TehsilName, D3_TehsilCode, Q_D4, Q_D5;
    Button NextButton, BackButton, btn_RedialCall, btn_AddReportQuestionnaire;
    ;
    TextView txtFarmerID, txtEmpID, txtOrderID, farmer_tehsil, txtQuestionD1Text, txtQuestionD3Text, Qd2VillageName, tv_d2VillageName;


    String d2_uc_name = "";
    String d2_uc_code = "";
    String d2_village_name = "";
    String d2_village_code = "";
    String d3_tehsil_name = "";
    String d3_tehsil_code = "";
    String d4 = "";
    String d5 = "";
    String d1 = "";
    String d3 = "";
    String d6 = "";
    String d7 = "";
    String comments = "";
    String enum_name = "";
    String enum_code = "";
    ImageView btniBack;

    @BindView(R.id.btnNoUCFound)
    AppCompatCheckBox btnNoUCFound;
    @BindView(R.id.btnNoVillageFound)
    AppCompatCheckBox btnNoVillageFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_d);
        ButterKnife.bind(this);
        btniBack = findViewById(R.id.btnBack);
        databaseAccess = new DatabaseAdapter(Q_sectionD.this);
        databaseAccess.Open();
        userName = getIntent().getStringExtra("username");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");
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
        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);

        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);

        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        farmer_tehsil = findViewById(R.id.farmer_tehsil);
        farmer_tehsil.setText("Farmer Tehsil: " + farmerTehsil);

        txtQuestionD1Text = findViewById(R.id.txtQuestionD1Text);
        txtQuestionD3Text = findViewById(R.id.txtQuestionD3Text);
        Qd2VillageName = findViewById(R.id.Qd2VillageName);
        tv_d2VillageName = findViewById(R.id.tv_d2VillageName);

        txtQuestionD1Text.setText("  کیا وہ زمین جس پر آپ کاشت کرتے ہیں وہ  ضلع( " + farmerDistrict + " ) میں گاؤں (" + farmerVillage + ") میں واقع  ہے؟ ");

        txtQuestionD3Text.setText(" کیا تحصیل ( " + farmerTehsil + ") ہے؟؟");

        Qd2VillageName.setText("Farmer Village : " + farmerVillage);
        tv_d2VillageName.setText(" آپ کی زمین موضع/ گاؤں(" + farmerVillage + ")  کی کس /بستی /ڈھوک /ڈیرہ  میں  ہے؟ ");
        btnSelectVillage = findViewById(R.id.btnSelectVillage);
        btnSelectTehsil = findViewById(R.id.btnSelectTehsil);


        NextButton = findViewById(R.id.btn_next);
        BackButton = findViewById(R.id.btn_back);
        btn_RedialCall = findViewById(R.id.btn_RedialCall);
        btn_AddReportQuestionnaire = findViewById(R.id.btn_AddReportQuestionnaire);

        Q_d1_layout = findViewById(R.id.qd1_layout);
        Q_d2_layout = findViewById(R.id.qd2_layout);
        Q_d3_layout = findViewById(R.id.qd3_layout);
        Q_d4_layout = findViewById(R.id.qd4_layout);
        Q_d5_layout = findViewById(R.id.qd5_layout);
        Q_d6_layout = findViewById(R.id.qd6_layout);
        Q_d7_layout = findViewById(R.id.qd7_layout);


        RG_qd1 = findViewById(R.id.rg_qd1);
        RG_qd3 = findViewById(R.id.rg_qd3);
        RG_qd6 = findViewById(R.id.rg_qd6);
        RG_qd7 = findViewById(R.id.rg_qd7);

        D2_ucName = findViewById(R.id.txt_d2_ucName);
        D2_ucCode = findViewById(R.id.txt_d2_ucCode);
        D2_VillageName = findViewById(R.id.txt_d2_villageName);
        D2_VillageCode = findViewById(R.id.txt_d2_villageCode);
        D3_TehsilName = findViewById(R.id.txt_d3_tehsilName);
        D3_TehsilCode = findViewById(R.id.txt_d3_tehsilCode);
        Q_D4 = findViewById(R.id.txt_qd4);
        Q_D5 = findViewById(R.id.txt_qd5);

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
        btnSelectVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Q_sectionD.this, SelectVillageActivity.class);
                intent.putExtra("districtID", farmerDistrict);
                intent.putExtra("districtName", farmerDistrict);
                startActivityForResult(intent, 99);
            }
        });

        btnSelectTehsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Q_sectionD.this, SelectTehsilActivity.class);
                intent.putExtra("districtID", farmerDistrict);
                intent.putExtra("districtName", farmerDistrict);
                startActivityForResult(intent, 9999);

            }
        });

        btnNoVillageFound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    D2_VillageCode.setText("-99");
                } else {
                    D2_VillageCode.setText("");
                }
            }
        });
        btnNoUCFound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    D2_ucCode.setText("-99");
                } else {
                    D2_ucCode.setText("");
                }
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int RG_qd1_ID = RG_qd1.getCheckedRadioButtonId();
                int RG_qd3_ID = RG_qd3.getCheckedRadioButtonId();
                int RG_qd6_ID = RG_qd6.getCheckedRadioButtonId();
                int RG_qd7_ID = RG_qd7.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(Q_sectionD.this);
                if (Q_d1_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RG_qd1_ID);

                    if (RG_qd1_ID == R.id.rbtn_d1_1) {
                        Q_d1_layout.setVisibility(View.GONE);
                        Q_d4_layout.setVisibility(View.VISIBLE);
                        d1 = radioButton.getTag().toString();
                    } else if (RG_qd1_ID == R.id.rbtn_d1_2) {
                        Q_d1_layout.setVisibility(View.GONE);
                        Q_d2_layout.setVisibility(View.VISIBLE);
                        d1 = radioButton.getTag().toString();

                    } else {
                        Toast.makeText(Q_sectionD.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (Q_d2_layout.getVisibility() == View.VISIBLE) {


                    d2_uc_name = D2_ucName.getText().toString();
                    d2_uc_code = D2_ucCode.getText().toString();
                    d2_village_name = D2_VillageName.getText().toString();
                    d2_village_code = D2_VillageCode.getText().toString();

                    if (StringUtils.isEmpty(d2_uc_name)) {
                        Toast.makeText(Q_sectionD.this, "Add Union Concil Name", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d2_uc_code)) {
                        Toast.makeText(Q_sectionD.this, "Add Union Concil Code", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d2_village_name)) {
                        Toast.makeText(Q_sectionD.this, "Add Village Name", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d2_village_code)) {
                        Toast.makeText(Q_sectionD.this, "Add Village Code", Toast.LENGTH_SHORT).show();
                    } else {
                        if (d2_uc_code.equalsIgnoreCase("-99") && d2_village_code.equalsIgnoreCase("-99")) {
                            Q_d2_layout.setVisibility(View.GONE);
                            Q_d3_layout.setVisibility(View.VISIBLE);
                            Qd2VillageName.setText("Farmer Village : " + d2_village_name);
                            tv_d2VillageName.setText(" آپ کی زمین موضع/ گاؤں(" + d2_village_name + ") کی کس /بستی /ڈھوک /ڈیرہ  میں  ہے؟ ");
                        } else {
                            d3 = "";
                            d3_tehsil_code = "";
                            d3_tehsil_name = "";
                            Q_d2_layout.setVisibility(View.GONE);
                            Q_d4_layout.setVisibility(View.VISIBLE);
                            Qd2VillageName.setText("Farmer Village : " + d2_village_name);
                            tv_d2VillageName.setText(" آپ کی زمین موضع/ گاؤں(" + d2_village_name + ") کی کس /بستی /ڈھوک /ڈیرہ  میں  ہے؟ ");
                        }
                    }

                } else if (Q_d3_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(RG_qd3_ID);

                    if (RG_qd3_ID == R.id.rbtn_d3_1) {
                        Q_d3_layout.setVisibility(View.GONE);
                        Q_d4_layout.setVisibility(View.VISIBLE);
                        d3 = radioButton.getTag().toString();
                    } else if (RG_qd3_ID == R.id.rbtn_d3_2) {

                        D3_TehsilName.setVisibility(View.VISIBLE);
                        D3_TehsilCode.setVisibility(View.VISIBLE);
                        btnSelectTehsil.setVisibility(View.VISIBLE);
                        d3 = radioButton.getTag().toString();
                        d3_tehsil_name = D3_TehsilName.getText().toString();
                        d3_tehsil_code = D3_TehsilCode.getText().toString();
                        if (StringUtils.isEmpty(d3_tehsil_name)) {
                            Toast.makeText(Q_sectionD.this, "Add Tehsil Name", Toast.LENGTH_SHORT).show();
                        } else if (StringUtils.isEmpty(d3_tehsil_code)) {
                            Toast.makeText(Q_sectionD.this, "Add Tehsil Code", Toast.LENGTH_SHORT).show();
                        } else {

                            Q_d3_layout.setVisibility(View.GONE);
                            Q_d4_layout.setVisibility(View.VISIBLE);

                        }
                    }

                } else if (Q_d4_layout.getVisibility() == View.VISIBLE) {

                    d4 = Q_D4.getText().toString();
                    if (StringUtils.isEmpty(d4)) {
                        Toast.makeText(Q_sectionD.this, "Add D4 Answer", Toast.LENGTH_SHORT).show();
                    } else {
                        Q_d4_layout.setVisibility(View.GONE);
                        Q_d5_layout.setVisibility(View.VISIBLE);

                    }

                } else if (Q_d5_layout.getVisibility() == View.VISIBLE) {


                    d5 = Q_D5.getText().toString();
                    if (!StringUtils.isEmpty(d5)) {
                        Integer age = Integer.parseInt(d5);
                        if ((age < 18 || age > 100) && !d5.equalsIgnoreCase("-99")) {
                            Toast.makeText(Q_sectionD.this, "Age must be in 18-100.", Toast.LENGTH_SHORT).show();
                        } else {
                            Q_d5_layout.setVisibility(View.GONE);

                            Q_d6_layout.setVisibility(View.VISIBLE);
                        }

                    } else {
                        Toast.makeText(Q_sectionD.this, "Add D5 Answer", Toast.LENGTH_SHORT).show();
                    }

                } else if (Q_d6_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(RG_qd6_ID);
                    if (RG_qd6_ID == R.id.rbtn_d6_1 || RG_qd6_ID == R.id.rbtn_d6_2) {
                        d6 = radioButton.getTag().toString();
                        Q_d6_layout.setVisibility(View.GONE);
                        Q_d7_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionD.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (Q_d7_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(RG_qd7_ID);

                    if (RG_qd7_ID == R.id.rbtn_d7_0
                            || RG_qd7_ID == R.id.rbtn_d7_1
                            || RG_qd7_ID == R.id.rbtn_d7_2
                            || RG_qd7_ID == R.id.rbtn_d7_3
                            || RG_qd7_ID == R.id.rbtn_d7_4
                            || RG_qd7_ID == R.id.rbtn_d7_5
                            || RG_qd7_ID == R.id.rbtn_d7_6
                            || RG_qd7_ID == R.id.rbtn_d7_7
                            || RG_qd7_ID == R.id.rbtn_d7_8
                            || RG_qd7_ID == R.id.rbtn_d7_9
                            || RG_qd7_ID == R.id.rbtn_d7_10
                            || RG_qd7_ID == R.id.rbtn_d7_16
                            || RG_qd7_ID == R.id.rbtn_d7_88) {

                        d7 = radioButton.getTag().toString();
                        Intent intent = new Intent(Q_sectionD.this, Q_sectionE.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmerID", FarmerID);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("ao_name", ao_name);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("fa_name", fa_name);
                        intent.putExtra("ai_name", ai_name);
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("id", id);
                        startActivityForResult(intent, 88);
                    } else {
                        Toast.makeText(Q_sectionD.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                }

                SaveData();
            }
        });


        RG_qd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RG_qd3_ID = RG_qd3.getCheckedRadioButtonId();
                if (RG_qd3_ID == R.id.rbtn_d3_2) {

                    D3_TehsilName.setVisibility(View.VISIBLE);
                    D3_TehsilCode.setVisibility(View.VISIBLE);
                    btnSelectTehsil.setVisibility(View.VISIBLE);
                } else {
                    D3_TehsilName.setText("");
                    D3_TehsilCode.setText("");
                    D3_TehsilName.setVisibility(View.GONE);
                    D3_TehsilCode.setVisibility(View.GONE);
                    btnSelectTehsil.setVisibility(View.GONE);


                }


            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_sectionD.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("section", "sectionD");
                startActivityForResult(intent, 88);

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
                        Toast.makeText(Q_sectionD.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

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
        int RG_qd1_ID = RG_qd1.getCheckedRadioButtonId();


        if (Q_d7_layout.getVisibility() == View.VISIBLE) {

            Q_d6_layout.setVisibility(View.VISIBLE);
            Q_d7_layout.setVisibility(View.GONE);
        } else if (Q_d6_layout.getVisibility() == View.VISIBLE) {

            Q_d5_layout.setVisibility(View.VISIBLE);
            Q_d6_layout.setVisibility(View.GONE);
        } else if (Q_d5_layout.getVisibility() == View.VISIBLE) {

            Q_d4_layout.setVisibility(View.VISIBLE);
            Q_d5_layout.setVisibility(View.GONE);
        } else if (Q_d4_layout.getVisibility() == View.VISIBLE) {

            if (RG_qd1_ID == R.id.rbtn_d1_1) {

                Q_d1_layout.setVisibility(View.VISIBLE);
                Q_d3_layout.setVisibility(View.GONE);
                Q_d4_layout.setVisibility(View.GONE);
            } else if (!d3.isEmpty()) {
                Q_d3_layout.setVisibility(View.VISIBLE);
                Q_d1_layout.setVisibility(View.GONE);
                Q_d4_layout.setVisibility(View.GONE);
            } else {
                Q_d2_layout.setVisibility(View.VISIBLE);
                Q_d3_layout.setVisibility(View.GONE);
                Q_d4_layout.setVisibility(View.GONE);
            }
        } else if (Q_d3_layout.getVisibility() == View.VISIBLE) {
            Q_d2_layout.setVisibility(View.VISIBLE);
            Q_d3_layout.setVisibility(View.GONE);
        } else if (Q_d2_layout.getVisibility() == View.VISIBLE) {

            Q_d1_layout.setVisibility(View.VISIBLE);
            Q_d2_layout.setVisibility(View.GONE);
        } else if (Q_d1_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();


        }


    }


    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Q_sectionD.this,
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
                    Toast.makeText(Q_sectionD.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void SaveData() {
        try {


            createNewColumnsBeforeInsertion(DatabaseAdapter.SectionDTable);


            databaseAccess.saveSection_D_Data(enum_name, enum_code, emp_id, order_id, FarmerID, d1, d2_uc_name, d2_uc_code, d2_village_name, d2_village_code, d3, d3_tehsil_name, d3_tehsil_code, d4, d5, d6, d7, comments);


            MubLog.cpnsoleLog("inside SaveData FarmerID " + FarmerID + " SectionBTable  =  " + DatabaseAdapter.SectionDTable);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID, DatabaseAdapter.SectionDTable);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    private void createNewColumnsBeforeInsertion(String tableName) {


        try {

            String insert_or_updated_in_phone_at = "ALTER TABLE `" + tableName + "` ADD `insert_or_updated_in_phone_at` TEXT DEFAULT '' ";
            databaseAccess.createMissingColumn(insert_or_updated_in_phone_at);

            String enum_code = "ALTER TABLE `" + tableName + "` ADD `enum_code` TEXT DEFAULT '' ";
            databaseAccess.createMissingColumn(enum_code);

            String enum_name = "ALTER TABLE `" + tableName + "` ADD `enum_name` TEXT DEFAULT '' ";
            databaseAccess.createMissingColumn(enum_name);


        } catch (Exception ex) {
            MubLog.cpnsoleLog("inside Section 1" + ex.toString());

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                d2_village_code = data.getStringExtra("villageID");
                d2_village_name = data.getStringExtra("villageName");
                d2_uc_code = data.getStringExtra("unionConcilID");
                d2_uc_name = data.getStringExtra("unionConsilName");
                D2_ucName.setText(d2_uc_name);
                D2_ucCode.setText(d2_uc_code);
                D2_VillageName.setText(d2_village_name);
                D2_VillageCode.setText(d2_village_code);


            } else if (requestCode == 9999) {
                d3_tehsil_code = data.getStringExtra("tehsilID");
                d3_tehsil_name = data.getStringExtra("tehsilName");
                D3_TehsilCode.setText(d3_tehsil_code);
                D3_TehsilName.setText(d3_tehsil_name);
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

            Cursor cursor = databaseAccess.getSectionDData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                d1 = cursor.getString(cursor.getColumnIndex("d1"));
                d2_uc_name = cursor.getString(cursor.getColumnIndex("d2_uc_name"));
                d2_uc_code = cursor.getString(cursor.getColumnIndex("d2_uc_code"));
                d2_village_name = cursor.getString(cursor.getColumnIndex("d2_village_name"));
                d2_village_code = cursor.getString(cursor.getColumnIndex("d2_village_code"));
                d3 = cursor.getString(cursor.getColumnIndex("d3"));
                d3_tehsil_name = cursor.getString(cursor.getColumnIndex("d3_tehsil_name"));
                d3_tehsil_code = cursor.getString(cursor.getColumnIndex("d3_tehsil_code"));
                d4 = cursor.getString(cursor.getColumnIndex("d4"));
                d5 = cursor.getString(cursor.getColumnIndex("d5"));
                d6 = cursor.getString(cursor.getColumnIndex("d6"));
                d7 = cursor.getString(cursor.getColumnIndex("d7"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {

        readFromDataBase();
        if (!StringUtils.isEmpty(d1)) {
            for (int i = 0; i < RG_qd1.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qd1.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d1)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(d2_uc_name)) {
            D2_ucName.setText(d2_uc_name);

        }

        if (!StringUtils.isEmpty(d2_uc_code)) {
            D2_ucCode.setText(d2_uc_code);

        }


        if (!StringUtils.isEmpty(d2_village_code)) {
            D2_VillageCode.setText(d2_village_code);

        }

        if (!StringUtils.isEmpty(d2_village_name)) {
            D2_VillageName.setText(d2_village_name);

        }

        if (!StringUtils.isEmpty(d3)) {
            for (int i = 0; i < RG_qd3.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qd3.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d3)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(d3_tehsil_code)) {
            D3_TehsilCode.setText(d3_tehsil_code);
            D3_TehsilCode.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isEmpty(d3_tehsil_name)) {
            D3_TehsilName.setText(d3_tehsil_name);
            D3_TehsilName.setVisibility(View.VISIBLE);

        }

        if (!StringUtils.isEmpty(d4)) {
            Q_D4.setText(d4);

        }

        if (!StringUtils.isEmpty(d5)) {
            Q_D5.setText(d5);

        }

        if (!StringUtils.isEmpty(d6)) {
            for (int i = 0; i < RG_qd6.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qd6.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d6)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(d7)) {
            for (int i = 0; i < RG_qd7.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qd7.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d7)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        //////////////////////////////////
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
