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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Adapter.MajorQuestionsSelectionAdapter;
import com.rcons.fcallbacks.Adapter.MinorQuestionsSelectionAdapter;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.Model.SectionF2;
import com.rcons.fcallbacks.QST_Helper.QuestionModel;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Section_F.Section_f2_adapter;
import com.rcons.fcallbacks.Section_F.Section_f2_items;
import com.rcons.fcallbacks.Section_H.Crops_items;
import com.rcons.fcallbacks.Section_H.Section_H_adapter;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Q_sectionH extends AppCompatActivity {
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    DatabaseAdapter databaseAccess;
    String maxCrops = "";
    Integer maxAcre = 0;
    Integer maxKanal = 0;
    Integer maxKanal2 = 0;
    Integer total_kanals = 0;
    Integer Minor_total_kanals = 0;
    String userName = "";
    boolean isPendingCall = false;
    Cursor cursor;
    String region = "";
    String emp_id = "";
    String id = "";

    String order_id = "";
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String farmer_cellphone;
    TextView txtFarmerID, txtEmpID, txtOrderID;
    @BindView(R.id.qh1_2_layout)
    LinearLayout qh1_2_layout;

    @BindView(R.id.qh6_7_layout)
    LinearLayout qh6_7_layout;

    @BindView(R.id.tv_h2_region)
    TextView tv_h2_region;
    @BindView(R.id.tv_h1_cropName)
    TextView tv_h1_cropName;
    @BindView(R.id.tv_h1_cropAcre)
    TextView tv_h1_cropAcre;
    @BindView(R.id.tv_h7_region)
    TextView tv_h7_region;
    @BindView(R.id.tv_h6_cropName)
    TextView tv_h6_cropName;
    @BindView(R.id.tv_h6_cropAcre)
    TextView tv_h6_cropAcre;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;


    @BindView(R.id.qh3_layout)
    LinearLayout qh3_layout;


    @BindView(R.id.qh4_layout)
    LinearLayout qh4_layout;


    @BindView(R.id.qh5_layout)
    LinearLayout qh5_layout;


    @BindView(R.id.qh8_layout)
    LinearLayout qh8_layout;


    @BindView(R.id.qh9_layout)
    LinearLayout qh9_layout;


    @BindView(R.id.qh10_layout)
    LinearLayout qh10_layout;

    @BindView(R.id.h3_crop_name)
    TextView h3_crop_name;

    @BindView(R.id.h4_crop_name)
    TextView h4_crop_name;

    @BindView(R.id.h5_crop_name)
    TextView h5_crop_name;

    @BindView(R.id.h8_crop_name)
    TextView h8_crop_name;

    @BindView(R.id.h9_crop_name)
    TextView h9_crop_name;

    @BindView(R.id.h10_crop_name)
    TextView h10_crop_name;


    @BindView(R.id.h3_qestion_text)
    TextView h3_qestion_text;

    @BindView(R.id.h4_qestion_text)
    TextView h4_qestion_text;

    @BindView(R.id.h5_qestion_text)
    TextView h5_qestion_text;

    @BindView(R.id.h8_qestion_text)
    TextView h8_qestion_text;

    @BindView(R.id.h9_qestion_text)
    TextView h9_qestion_text;

    @BindView(R.id.h10_qestion_text)
    TextView h10_qestion_text;

    @BindView(R.id.rg_h3_answer)
    RadioGroup rg_h3_answer;

    @BindView(R.id.rg_h4_answer)
    RadioGroup rg_h4_answer;

    @BindView(R.id.rg_h5_answer)
    RadioGroup rg_h5_answer;

    @BindView(R.id.rg_h8_answer)
    RadioGroup rg_h8_answer;

    @BindView(R.id.rg_h9_answer)
    RadioGroup rg_h9_answer;

    @BindView(R.id.rg_h10_answer)
    RadioGroup rg_h10_answer;

    @BindView(R.id.rg_h3)
    RadioGroup rg_h3;

    @BindView(R.id.rg_h4)
    RadioGroup rg_h4;

    @BindView(R.id.rg_h5)
    RadioGroup rg_h5;

    @BindView(R.id.rg_h8)
    RadioGroup rg_h8;

    @BindView(R.id.rg_h9)
    RadioGroup rg_h9;

    @BindView(R.id.rg_h10)
    RadioGroup rg_h10;

    String h1 = "";
    String h2 = "";
    String h3 = "";
    String h4 = "";
    String h5 = "";
    String h6 = "";
    String h7 = "";
    String h8 = "";
    String h9 = "";
    String h10 = "";
    String comments = "";
    String enum_name = "";
    String enum_code = "";
    int counter = 0;
    ImageView btniBack;

    @BindView(R.id.txtNoDataFound)
    TextView txtNoDataFound;
    @BindView(R.id.qst_layout)
    LinearLayout qst_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_h);
        ButterKnife.bind(this);
        btniBack = findViewById(R.id.btnBack);
        databaseAccess = new DatabaseAdapter(Q_sectionH.this);
        databaseAccess.Open();

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
        id = getIntent().getStringExtra("id");
        h2 = region;
        h7 = region;

        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);

        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);

        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        tv_h2_region.setText("Region: " + region);
        tv_h7_region.setText("Region: " + region);


        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }

        GetAllMethods();
        GetEnum();
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


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RG_h3_ID = rg_h3.getCheckedRadioButtonId();
                int RG_h4_ID = rg_h4.getCheckedRadioButtonId();
                int RG_h5_ID = rg_h5.getCheckedRadioButtonId();
                int RG_h8_ID = rg_h8.getCheckedRadioButtonId();
                int RG_h9_ID = rg_h9.getCheckedRadioButtonId();
                int RG_h10_ID = rg_h10.getCheckedRadioButtonId();

                h1 = tv_h1_cropName.getText().toString();
                h2 = tv_h2_region.getText().toString();
                SaveData();
                if (qh1_2_layout.getVisibility() == View.VISIBLE) {
                    if (StringUtils.isEmpty(h1)) {
                        Toast.makeText(Q_sectionH.this, "Please add crop", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(h2)) {
                        Toast.makeText(Q_sectionH.this, "Please add Region ", Toast.LENGTH_SHORT).show();

                    } else {
                        Seth4();
                        Seth5();
                        Seth8();
                        Seth9();
                        Seth10();
                        RadioGroupFalse_h3_4_5();
                        RadioGroupFalse_h8_9_10();
                        qh1_2_layout.setVisibility(View.GONE);
                        qh3_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qh3_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RG_h3_ID);
                    if (RG_h3_ID > 0) {
                        h3 = radioButton.getTag().toString();
                        qh3_layout.setVisibility(View.GONE);
                        qh4_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionH.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }


                } else if (qh4_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RG_h4_ID);
                    if (RG_h4_ID > 0) {
                        h4 = radioButton.getTag().toString();
                        qh4_layout.setVisibility(View.GONE);
                        qh5_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionH.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                } else if (qh5_layout.getVisibility() == View.VISIBLE) {

                    h6 = tv_h6_cropName.getText().toString();
                    h7 = tv_h2_region.getText().toString();
                    String h6_cropAcre = tv_h6_cropAcre.getText().toString();
                    RadioButton radioButton = findViewById(RG_h5_ID);
                    if (RG_h5_ID > 0) {
                        if ((!StringUtils.isEmpty(h6)) && (!StringUtils.isEmpty(h7)) && (!StringUtils.isEmpty(h6_cropAcre))) {
                            h5 = radioButton.getTag().toString();
                            qh5_layout.setVisibility(View.GONE);
                            qh6_7_layout.setVisibility(View.VISIBLE);
                        } else {
                            h5 = radioButton.getTag().toString();
                            SaveData();
                            Intent intent = new Intent(Q_sectionH.this, Q_section1.class);
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
                        }
                    } else {
                        Toast.makeText(Q_sectionH.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }

                } else if (qh6_7_layout.getVisibility() == View.VISIBLE) {
                    qh6_7_layout.setVisibility(View.GONE);
                    qh8_layout.setVisibility(View.VISIBLE);

                } else if (qh8_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RG_h8_ID);
                    if (RG_h8_ID > 0) {
                        h8 = radioButton.getTag().toString();
                        qh8_layout.setVisibility(View.GONE);
                        qh9_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionH.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }

                } else if (qh9_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RG_h9_ID);
                    if (RG_h9_ID > 0) {
                        h9 = radioButton.getTag().toString();
                        qh9_layout.setVisibility(View.GONE);
                        qh10_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionH.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                } else if (qh10_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RG_h10_ID);
                    if (RG_h10_ID > 0) {
                        h10 = radioButton.getTag().toString();
                        SaveData();
                        Intent intent = new Intent(Q_sectionH.this, Q_section1.class);
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
                        Toast.makeText(Q_sectionH.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }

                }
                SaveData();
            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_sectionH.this, AddReportActivity.class);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "sectionH");
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
                        Toast.makeText(Q_sectionH.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


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
        if (ActivityCompat.checkSelfPermission(Q_sectionH.this,
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
        startActivityForResult(callIntent, 99);

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
                    Toast.makeText(Q_sectionH.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        if (qh10_layout.getVisibility() == View.VISIBLE) {

            qh10_layout.setVisibility(View.GONE);
            qh9_layout.setVisibility(View.VISIBLE);

        } else if (qh9_layout.getVisibility() == View.VISIBLE) {

            qh8_layout.setVisibility(View.VISIBLE);
            qh9_layout.setVisibility(View.GONE);

        } else if (qh8_layout.getVisibility() == View.VISIBLE) {

            qh8_layout.setVisibility(View.GONE);
            qh6_7_layout.setVisibility(View.VISIBLE);

        } else if (qh6_7_layout.getVisibility() == View.VISIBLE) {
            qh5_layout.setVisibility(View.VISIBLE);
            qh6_7_layout.setVisibility(View.GONE);

        } else if (qh5_layout.getVisibility() == View.VISIBLE) {
            qh4_layout.setVisibility(View.VISIBLE);
            qh5_layout.setVisibility(View.GONE);

        } else if (qh4_layout.getVisibility() == View.VISIBLE) {
            qh3_layout.setVisibility(View.VISIBLE);
            qh4_layout.setVisibility(View.GONE);

        } else if (qh3_layout.getVisibility() == View.VISIBLE) {
            qh1_2_layout.setVisibility(View.VISIBLE);
            qh3_layout.setVisibility(View.GONE);

        } else if (qh1_2_layout.getVisibility() == View.VISIBLE) {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        }


    }

    void SaveData() {

        try {


            createNewColumnsBeforeInsertion(DatabaseAdapter.SectionHTable);


            databaseAccess.saveSectionH_Data(enum_name, enum_code, emp_id, order_id, FarmerID, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10, comments);


            MubLog.cpnsoleLog("inside SaveData FarmerID " + FarmerID + " SectionBTable  =  " + DatabaseAdapter.SectionHTable);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID, DatabaseAdapter.SectionHTable);


            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionHTable);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {
            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY " + tableName);
            String patch_querry = "UPDATE `" + tableName + "` SET `h3`= '0' WHERE `h3`= '2';";

            databaseAccess.execute_patch_query(patch_querry);

            patch_querry = "UPDATE `" + tableName + "` SET `h4`= '0' WHERE `h4`= '2';";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `h5`= '0' WHERE `h5`= '2';";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `h8`= '0' WHERE `h8`= '2';";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `h9`= '0' WHERE `h9`= '2';";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `h10`= '0' WHERE `h10`= '2';";
            databaseAccess.execute_patch_query(patch_querry);


        } catch (Exception ex) {
            MubLog.cpnsoleLog("inside Section 1" + ex.toString());

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


    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionHData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                h1 = cursor.getString(cursor.getColumnIndex("h1"));
                h2 = cursor.getString(cursor.getColumnIndex("h2"));
                h3 = cursor.getString(cursor.getColumnIndex("h3"));
                h4 = cursor.getString(cursor.getColumnIndex("h4"));
                h5 = cursor.getString(cursor.getColumnIndex("h5"));
                h6 = cursor.getString(cursor.getColumnIndex("h6"));
                h7 = cursor.getString(cursor.getColumnIndex("h7"));
                h8 = cursor.getString(cursor.getColumnIndex("h8"));
                h9 = cursor.getString(cursor.getColumnIndex("h9"));
                h10 = cursor.getString(cursor.getColumnIndex("h10"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        if (!StringUtils.isEmpty(h1)) {
            tv_h1_cropName.setText(h1);
        }


        if (!StringUtils.isEmpty(h2)) {
            tv_h2_region.setText(h2);
        }

        if (!StringUtils.isEmpty(h3)) {
            for (int i = 0; i < rg_h3.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_h3.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(h3)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(h4)) {
            for (int i = 0; i < rg_h4.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_h4.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(h4)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(h5)) {
            for (int i = 0; i < rg_h5.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_h5.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(h5)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(h8)) {
            for (int i = 0; i < rg_h8.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_h8.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(h8)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(h9)) {
            for (int i = 0; i < rg_h9.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_h9.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(h9)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(h10)) {
            for (int i = 0; i < rg_h10.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_h10.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(h10)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        ///////////////////////
    }

    void GetMaximumCrops() {
        /////////        Choti aur bri fslo ko cehck krnay k liye wo section f may question no f1 crops may mjod hai ya nehi

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


                    ///////   bri fslo ko cehck krnay k liye wo section f may question no f1 crops may mjod hai ya nehi
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
                    ///////   choti fslo ko cehck krnay k liye wo section f may question no f1 crops may mjod hai ya nehi
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
            ///////   agr choti ya bri fslay section f may question no f1 crops may mjod hai aur us ka questionnaire b knolwdge table may mjood ho
            String check_h1_minor = "";
            txtNoDataFound.setVisibility(View.GONE);
            qst_layout.setVisibility(View.VISIBLE);

            ///////   total_kanals_list ka variable bri fslo ko zahir krta hai agr
            if (total_kanals_list != null && total_kanals_list.size() > 0) {
                /////////   agr bri fslay mjod hain tu    jo k sub say zyada rkbay pr kasht hai
                Integer max = total_kanals_list.get(0);
                tv_h1_cropName.setText(max_crops.get(0));
                tv_h1_cropAcre.setText(max_acres.get(0));
                for (int i = 0; i < total_kanals_list.size(); i++) {
                    if (total_kanals_list.get(i) > max) {
                        max = total_kanals_list.get(i);
                        tv_h1_cropName.setText(max_crops.get(i));
                        tv_h1_cropAcre.setText(max_acres.get(i));
                        MubLog.cpnsoleLog("MaxCrop " + "Crop ID = " + max_crops.get(i));
                        MubLog.cpnsoleLog("max_cr " + "max max = " + max);
                    }
                }
            } else
            /////////  agr bri fslay mjod nehi hain tu phir h1 k liye choti fsl check kray jo sub say szyada rkbay pr kasht ho
            {
                if (Minor_total_kanals_list != null && Minor_total_kanals_list.size() > 0) {
                    if (crop_type.equalsIgnoreCase("Minor")) {
                        //////////////   agr choti fsl mjod hai tu phir h1 may choti fsl check kray
                        MubLog.cpnsoleLog("Minor_ Crop");
                        Integer Minor_max = Minor_total_kanals_list.get(0);
                        Minor_max = Minor_total_kanals_list.get(0);
                        tv_h1_cropName.setText(Minor_max_crops.get(0));
                        tv_h1_cropAcre.setText(Minor_max_acres.get(0));
                        for (int i = 0; i < Minor_total_kanals_list.size(); i++) {
                            if (Minor_total_kanals_list.get(i) > Minor_max) {
                                Minor_max = Minor_total_kanals_list.get(i);
                                tv_h1_cropName.setText(Minor_max_crops.get(i));
                                tv_h1_cropAcre.setText(Minor_max_acres.get(i));
                                MubLog.cpnsoleLog("check_h1_minor " + check_h1_minor);
                                MubLog.cpnsoleLog("Minor_MaxCrop " + "Minor_Crop ID = " + Minor_max_crops.get(i));
                                MubLog.cpnsoleLog("Minor_max_cr " + "Minor_max = " + Minor_max);
                            }
                            /////////////   check_h1_mninor may h1_minor aa jaye jo k zahr kray ga k h1 may minor crop hai aur bri fsl mjod nehi thi
                            MubLog.cpnsoleLog("check_h1_minor " + check_h1_minor);
                            check_h1_minor = "h1_minor";
                        }
                    }
                }
            }
            if (!check_h1_minor.equalsIgnoreCase("h1_minor")) {
                if (crop_type.equalsIgnoreCase("Minor")) {
                    ////////////////   agr choti fsl mjod thi tu phir h6 may choti fsl jo sub say zyada rkbay pr kasht ki gi hai wo aa jaye
                    MubLog.cpnsoleLog("Minor_ Crop");
                    Integer Minor_max = Minor_total_kanals_list.get(0);
                    Minor_max = Minor_total_kanals_list.get(0);
                    tv_h6_cropName.setText(Minor_max_crops.get(0));
                    tv_h6_cropAcre.setText(Minor_max_acres.get(0));
                    for (int i = 0; i < Minor_total_kanals_list.size(); i++) {
                        if (Minor_total_kanals_list.get(i) > Minor_max) {
                            Minor_max = Minor_total_kanals_list.get(i);
                            tv_h6_cropName.setText(Minor_max_crops.get(i));
                            tv_h6_cropAcre.setText(Minor_max_acres.get(i));
                            MubLog.cpnsoleLog("Minor_MaxCrop " + "Minor_Crop ID = " + Minor_max_crops.get(i));
                            MubLog.cpnsoleLog("Minor_max_cr " + "Minor_max = " + Minor_max);
                        }

                    }
                } else {

                    ////////////////   agr choti fsl mjod behi  thi tu phir h6 bri fsl jo second largest fsl hai wo aa jaye
                    int index = 0;
                    int i, first, second;
                    if (total_kanals_list.size() < 2) {
                        MubLog.cpnsoleLog("Only One Crop");
                    } else {
                        first = second = Integer.MIN_VALUE;
                        for (i = 0; i < total_kanals_list.size(); i++) {
                            if (total_kanals_list.get(i) > first) {
                                second = first;
                                first = total_kanals_list.get(i);
                            } else if (total_kanals_list.get(i) > second && total_kanals_list.get(i) != first)
                                second = total_kanals_list.get(i);
                            index = i;
                        }

                        if (second == Integer.MIN_VALUE) {
                            MubLog.cpnsoleLog("First and Second Is Equal");
                            for (i = 1; i < total_kanals_list.size(); i++) {
                                if (StringUtils.isEmpty(tv_h6_cropName.getText().toString())) {
                                    tv_h6_cropName.setText(max_crops.get(i));
                                    tv_h6_cropAcre.setText(max_acres.get(i));
                                    MubLog.cpnsoleLog("First and Second Is Equal Crop = " + max_crops.get(i));
                                }
                            }
                        } else {
                            //MubLog.cpnsoleLog("The second largest element" + max_crops.get(index).toString());
                            MubLog.cpnsoleLog("The second largest Value = " + second);
                            for (i = 0; i < total_kanals_list.size(); i++) {
                                if (total_kanals_list.get(i) == second) {
                                    MubLog.cpnsoleLog("The second largest Crop = " + max_crops.get(i));
                                    tv_h6_cropName.setText(max_crops.get(i));
                                    tv_h6_cropAcre.setText(max_acres.get(i));
                                }
                            }
                        }
                    }
                }
            } else {

                ////////////////   agr h1 may pehlay say he choti fsl mjod hai tu phir second largest minor crop h6 may aa jaye
                int index = 0;
                int i, first, second;
                if (Minor_total_kanals_list.size() < 2) {
                    MubLog.cpnsoleLog("Only One Crop");
                } else {
                    first = second = Integer.MIN_VALUE;
                    for (i = 0; i < Minor_total_kanals_list.size(); i++) {
                        if (Minor_total_kanals_list.get(i) > first) {
                            second = first;
                            first = Minor_total_kanals_list.get(i);
                        } else if (Minor_total_kanals_list.get(i) > second && Minor_total_kanals_list.get(i) != first)
                            second = Minor_total_kanals_list.get(i);
                        index = i;
                    }

                    if (second == Integer.MIN_VALUE) {
                        MubLog.cpnsoleLog("First and Second Is Equal");
                        for (i = 1; i < Minor_total_kanals_list.size(); i++) {
                            if (StringUtils.isEmpty(tv_h6_cropName.getText().toString())) {
                                MubLog.cpnsoleLog("First and Second Is Equal = " + Minor_max_crops.get(i));
                                tv_h6_cropName.setText(Minor_max_crops.get(i));
                                tv_h6_cropAcre.setText(Minor_max_crops.get(i));
                            }
                        }
                    } else {
                        //MubLog.cpnsoleLog("The second largest element" + max_crops.get(index).toString());
                        MubLog.cpnsoleLog("The second largest Value = " + second);
                        for (i = 0; i < Minor_total_kanals_list.size(); i++) {
                            if (Minor_total_kanals_list.get(i) == second) {
                                MubLog.cpnsoleLog("The second largest Crop = " + Minor_max_crops.get(i));
                                tv_h6_cropName.setText(Minor_max_crops.get(i));
                                tv_h6_cropAcre.setText(Minor_max_crops.get(i));
                            }
                        }
                    }
                }

            }

        } else {
            txtNoDataFound.setVisibility(View.VISIBLE);
            qst_layout.setVisibility(View.GONE);
        }

    }

    void Seth3() {
        String question_type = "Basic Questions";
        String crop_id_ = tv_h1_cropName.getText().toString();
        Cursor cursor;
        if (crop_id_.equalsIgnoreCase("6") || crop_id_.equalsIgnoreCase("7")
                || crop_id_.equalsIgnoreCase("8") || crop_id_.equalsIgnoreCase("9")
                || crop_id_.equalsIgnoreCase("10")) {
            String region_ = "Punjab";
            cursor = databaseAccess.getSection_H_Crops(region_, crop_id_, question_type);
        } else {
            cursor = databaseAccess.getSection_H_Crops(region, crop_id_, question_type);
        }

        MubLog.cpnsoleLog("H3 Cursor " + "Region = " + region + " CropID = " + crop_id_ + " question_type = " + question_type);
        ArrayList<String> h3_options_name = new ArrayList<>();
        ArrayList<String> h3_options_no = new ArrayList<>();
        ArrayList<String> h3_answers = new ArrayList<>();
        ArrayList<String> correct_list = new ArrayList<>();
        String correct_option = "";
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                String question_name = cursor.getString(cursor.getColumnIndex("question_name"));
                correct_option = cursor.getString(cursor.getColumnIndex("correct_option"));
                h3_qestion_text.setText(question_name);

                String option_1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option_2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option_3 = cursor.getString(cursor.getColumnIndex("option_3"));
                String option_4 = cursor.getString(cursor.getColumnIndex("option_4"));
                String option_5 = cursor.getString(cursor.getColumnIndex("option_5"));
                String option_6 = cursor.getString(cursor.getColumnIndex("option_6"));
                String option_7 = cursor.getString(cursor.getColumnIndex("option_7"));

                h3_options_name.add(option_1);
                h3_options_name.add(option_2);
                h3_options_name.add(option_3);
                h3_options_name.add(option_4);
                h3_options_name.add(option_5);
                h3_options_name.add(option_6);
                h3_options_name.add(option_7);
                correct_list.add("option_1");
                correct_list.add("option_2");
                correct_list.add("option_3");
                correct_list.add("option_4");
                correct_list.add("option_5");
                correct_list.add("option_6");
                correct_list.add("option_7");

            }
            cursor.close();
        }
        if (h3_options_name != null && h3_options_name.size() > 0) {
            for (int i = 0; i < h3_options_name.size(); i++) {
                RadioButton radioButton = (RadioButton) rg_h3_answer.getChildAt(i);
                String check = h3_options_name.get(i);
                if (!StringUtils.isEmpty(check)) {
                    radioButton.setVisibility(View.VISIBLE);
                    radioButton.setText(h3_options_name.get(i));
                    if (radioButton != null) {
                        if (correct_option.equalsIgnoreCase(correct_list.get(i))) {
                            radioButton.setChecked(true);
                            radioButton.setTextColor(getResources().getColor(R.color.navy_blue));
                        }
                    }
                }
            }
        }

    }

    void Seth4() {
        String question_type = "Technical Questions";
        String crop_id_ = tv_h1_cropName.getText().toString();
        if (crop_id_.equalsIgnoreCase("6") || crop_id_.equalsIgnoreCase("7")
                || crop_id_.equalsIgnoreCase("8") || crop_id_.equalsIgnoreCase("9")
                || crop_id_.equalsIgnoreCase("10")) {
            String region_ = "Punjab";
            cursor = databaseAccess.getSection_H_Crops(region_, crop_id_, question_type);
        } else {
            cursor = databaseAccess.getSection_H_Crops(region, crop_id_, question_type);
        }
        MubLog.cpnsoleLog("h4 Cursor " + "Region = " + region + " CropID = " + crop_id_ + " question_type = " + question_type);
        ArrayList<String> h4_options_name = new ArrayList<>();
        ArrayList<String> h4_options_no = new ArrayList<>();
        ArrayList<String> h4_answers = new ArrayList<>();
        ArrayList<String> correct_list = new ArrayList<>();
        String correct_option = "";
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                String question_name = cursor.getString(cursor.getColumnIndex("question_name"));
                correct_option = cursor.getString(cursor.getColumnIndex("correct_option"));
                h4_qestion_text.setText(question_name);

                String option_1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option_2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option_3 = cursor.getString(cursor.getColumnIndex("option_3"));
                String option_4 = cursor.getString(cursor.getColumnIndex("option_4"));
                String option_5 = cursor.getString(cursor.getColumnIndex("option_5"));
                String option_6 = cursor.getString(cursor.getColumnIndex("option_6"));
                String option_7 = cursor.getString(cursor.getColumnIndex("option_7"));

                h4_options_name.add(option_1);
                h4_options_name.add(option_2);
                h4_options_name.add(option_3);
                h4_options_name.add(option_4);
                h4_options_name.add(option_5);
                h4_options_name.add(option_6);
                h4_options_name.add(option_7);
                correct_list.add("option_1");
                correct_list.add("option_2");
                correct_list.add("option_3");
                correct_list.add("option_4");
                correct_list.add("option_5");
                correct_list.add("option_6");
                correct_list.add("option_7");

            }
            cursor.close();
        }
        if (h4_options_name != null && h4_options_name.size() > 0) {
            for (int i = 0; i < h4_options_name.size(); i++) {
                RadioButton radioButton = (RadioButton) rg_h4_answer.getChildAt(i);
                String check = h4_options_name.get(i);
                if (!StringUtils.isEmpty(check)) {
                    radioButton.setVisibility(View.VISIBLE);
                    radioButton.setText(h4_options_name.get(i));
                    if (radioButton != null) {
                        if (correct_option.equalsIgnoreCase(correct_list.get(i))) {
                            radioButton.setChecked(true);
                            radioButton.setTextColor(getResources().getColor(R.color.navy_blue));
                        }
                    }
                }
            }
        }

    }


    void Seth5() {
        String question_type = "Highly Technical Questions";
        String crop_id_ = tv_h1_cropName.getText().toString();
        if (crop_id_.equalsIgnoreCase("6") || crop_id_.equalsIgnoreCase("7")
                || crop_id_.equalsIgnoreCase("8") || crop_id_.equalsIgnoreCase("9")
                || crop_id_.equalsIgnoreCase("10")) {
            String region_ = "Punjab";
            cursor = databaseAccess.getSection_H_Crops(region_, crop_id_, question_type);
        } else {
            cursor = databaseAccess.getSection_H_Crops(region, crop_id_, question_type);
        }
        MubLog.cpnsoleLog("h5 Cursor " + "Region = " + region + " CropID = " + crop_id_ + " question_type = " + question_type);
        ArrayList<String> h5_options_name = new ArrayList<>();
        ArrayList<String> h5_options_no = new ArrayList<>();
        ArrayList<String> h5_answers = new ArrayList<>();
        ArrayList<String> correct_list = new ArrayList<>();
        String correct_option = "";
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                String question_name = cursor.getString(cursor.getColumnIndex("question_name"));
                correct_option = cursor.getString(cursor.getColumnIndex("correct_option"));
                h5_qestion_text.setText(question_name);

                String option_1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option_2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option_3 = cursor.getString(cursor.getColumnIndex("option_3"));
                String option_4 = cursor.getString(cursor.getColumnIndex("option_4"));
                String option_5 = cursor.getString(cursor.getColumnIndex("option_5"));
                String option_6 = cursor.getString(cursor.getColumnIndex("option_6"));
                String option_7 = cursor.getString(cursor.getColumnIndex("option_7"));

                h5_options_name.add(option_1);
                h5_options_name.add(option_2);
                h5_options_name.add(option_3);
                h5_options_name.add(option_4);
                h5_options_name.add(option_5);
                h5_options_name.add(option_6);
                h5_options_name.add(option_7);
                correct_list.add("option_1");
                correct_list.add("option_2");
                correct_list.add("option_3");
                correct_list.add("option_4");
                correct_list.add("option_5");
                correct_list.add("option_6");
                correct_list.add("option_7");

            }
            cursor.close();
        }
        if (h5_options_name != null && h5_options_name.size() > 0) {
            for (int i = 0; i < h5_options_name.size(); i++) {
                RadioButton radioButton = (RadioButton) rg_h5_answer.getChildAt(i);
                String check = h5_options_name.get(i);
                if (!StringUtils.isEmpty(check)) {
                    radioButton.setVisibility(View.VISIBLE);
                    radioButton.setText(h5_options_name.get(i));
                    if (radioButton != null) {
                        if (correct_option.equalsIgnoreCase(correct_list.get(i))) {
                            radioButton.setChecked(true);
                            radioButton.setTextColor(getResources().getColor(R.color.navy_blue));
                        }
                    }
                }
            }
        }

    }

    void Seth8() {
        String question_type = "Basic Questions";
        String crop_id_ = tv_h6_cropName.getText().toString();
        if (crop_id_.equalsIgnoreCase("6") || crop_id_.equalsIgnoreCase("7")
                || crop_id_.equalsIgnoreCase("8") || crop_id_.equalsIgnoreCase("9")
                || crop_id_.equalsIgnoreCase("10")) {
            String region_ = "Punjab";
            cursor = databaseAccess.getSection_H_Crops(region_, crop_id_, question_type);
        } else {
            cursor = databaseAccess.getSection_H_Crops(region, crop_id_, question_type);
        }
        MubLog.cpnsoleLog("h8 Cursor " + "Region = " + region + " CropID = " + crop_id_ + " question_type = " + question_type);
        ArrayList<String> h8_options_name = new ArrayList<>();
        ArrayList<String> h8_options_no = new ArrayList<>();
        ArrayList<String> h8_answers = new ArrayList<>();
        ArrayList<String> correct_list = new ArrayList<>();
        String correct_option = "";
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                String question_name = cursor.getString(cursor.getColumnIndex("question_name"));
                correct_option = cursor.getString(cursor.getColumnIndex("correct_option"));
                h8_qestion_text.setText(question_name);

                String option_1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option_2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option_3 = cursor.getString(cursor.getColumnIndex("option_3"));
                String option_4 = cursor.getString(cursor.getColumnIndex("option_4"));
                String option_5 = cursor.getString(cursor.getColumnIndex("option_5"));
                String option_6 = cursor.getString(cursor.getColumnIndex("option_6"));
                String option_7 = cursor.getString(cursor.getColumnIndex("option_7"));

                h8_options_name.add(option_1);
                h8_options_name.add(option_2);
                h8_options_name.add(option_3);
                h8_options_name.add(option_4);
                h8_options_name.add(option_5);
                h8_options_name.add(option_6);
                h8_options_name.add(option_7);
                correct_list.add("option_1");
                correct_list.add("option_2");
                correct_list.add("option_3");
                correct_list.add("option_4");
                correct_list.add("option_5");
                correct_list.add("option_6");
                correct_list.add("option_7");

            }
            cursor.close();
        }
        if (h8_options_name != null && h8_options_name.size() > 0) {
            for (int i = 0; i < h8_options_name.size(); i++) {
                RadioButton radioButton = (RadioButton) rg_h8_answer.getChildAt(i);
                String check = h8_options_name.get(i);
                if (!StringUtils.isEmpty(check)) {
                    radioButton.setVisibility(View.VISIBLE);
                    radioButton.setText(h8_options_name.get(i));
                    if (radioButton != null) {
                        if (correct_option.equalsIgnoreCase(correct_list.get(i))) {
                            radioButton.setChecked(true);
                            radioButton.setTextColor(getResources().getColor(R.color.navy_blue));
                        }
                    }
                }
            }
        }

    }

    void Seth9() {
        String question_type = "Technical Questions";
        String crop_id_ = tv_h6_cropName.getText().toString();
        if (crop_id_.equalsIgnoreCase("6") || crop_id_.equalsIgnoreCase("7")
                || crop_id_.equalsIgnoreCase("8") || crop_id_.equalsIgnoreCase("9")
                || crop_id_.equalsIgnoreCase("10")) {
            String region_ = "Punjab";
            cursor = databaseAccess.getSection_H_Crops(region_, crop_id_, question_type);
        } else {
            cursor = databaseAccess.getSection_H_Crops(region, crop_id_, question_type);
        }
        MubLog.cpnsoleLog("h9 Cursor " + "Region = " + region + " CropID = " + crop_id_ + " question_type = " + question_type);
        ArrayList<String> h9_options_name = new ArrayList<>();
        ArrayList<String> h9_options_no = new ArrayList<>();
        ArrayList<String> h9_answers = new ArrayList<>();
        ArrayList<String> correct_list = new ArrayList<>();
        String correct_option = "";
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                String question_name = cursor.getString(cursor.getColumnIndex("question_name"));
                correct_option = cursor.getString(cursor.getColumnIndex("correct_option"));
                h9_qestion_text.setText(question_name);

                String option_1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option_2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option_3 = cursor.getString(cursor.getColumnIndex("option_3"));
                String option_4 = cursor.getString(cursor.getColumnIndex("option_4"));
                String option_5 = cursor.getString(cursor.getColumnIndex("option_5"));
                String option_6 = cursor.getString(cursor.getColumnIndex("option_6"));
                String option_7 = cursor.getString(cursor.getColumnIndex("option_7"));

                h9_options_name.add(option_1);
                h9_options_name.add(option_2);
                h9_options_name.add(option_3);
                h9_options_name.add(option_4);
                h9_options_name.add(option_5);
                h9_options_name.add(option_6);
                h9_options_name.add(option_7);
                correct_list.add("option_1");
                correct_list.add("option_2");
                correct_list.add("option_3");
                correct_list.add("option_4");
                correct_list.add("option_5");
                correct_list.add("option_6");
                correct_list.add("option_7");

            }
            cursor.close();
        }
        if (h9_options_name != null && h9_options_name.size() > 0) {
            for (int i = 0; i < h9_options_name.size(); i++) {
                RadioButton radioButton = (RadioButton) rg_h9_answer.getChildAt(i);
                String check = h9_options_name.get(i);
                if (!StringUtils.isEmpty(check)) {
                    radioButton.setVisibility(View.VISIBLE);
                    radioButton.setText(h9_options_name.get(i));
                    if (radioButton != null) {
                        if (correct_option.equalsIgnoreCase(correct_list.get(i))) {
                            radioButton.setChecked(true);
                            radioButton.setTextColor(getResources().getColor(R.color.navy_blue));
                        }
                    }
                }
            }
        }

    }


    void Seth10() {
        String question_type = "Highly Technical Questions";
        String crop_id_ = tv_h6_cropName.getText().toString();
        if (crop_id_.equalsIgnoreCase("6") || crop_id_.equalsIgnoreCase("7")
                || crop_id_.equalsIgnoreCase("8") || crop_id_.equalsIgnoreCase("9")
                || crop_id_.equalsIgnoreCase("10")) {
            String region_ = "Punjab";
            cursor = databaseAccess.getSection_H_Crops(region_, crop_id_, question_type);
        } else {
            cursor = databaseAccess.getSection_H_Crops(region, crop_id_, question_type);
        }
        MubLog.cpnsoleLog("h10 Cursor " + "Region = " + region + " CropID = " + crop_id_ + " question_type = " + question_type);
        ArrayList<String> h10_options_name = new ArrayList<>();
        ArrayList<String> h10_options_no = new ArrayList<>();
        ArrayList<String> h10_answers = new ArrayList<>();
        ArrayList<String> correct_list = new ArrayList<>();
        String correct_option = "";
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                String question_name = cursor.getString(cursor.getColumnIndex("question_name"));
                correct_option = cursor.getString(cursor.getColumnIndex("correct_option"));
                h10_qestion_text.setText(question_name);

                String option_1 = cursor.getString(cursor.getColumnIndex("option_1"));
                String option_2 = cursor.getString(cursor.getColumnIndex("option_2"));
                String option_3 = cursor.getString(cursor.getColumnIndex("option_3"));
                String option_4 = cursor.getString(cursor.getColumnIndex("option_4"));
                String option_5 = cursor.getString(cursor.getColumnIndex("option_5"));
                String option_6 = cursor.getString(cursor.getColumnIndex("option_6"));
                String option_7 = cursor.getString(cursor.getColumnIndex("option_7"));

                h10_options_name.add(option_1);
                h10_options_name.add(option_2);
                h10_options_name.add(option_3);
                h10_options_name.add(option_4);
                h10_options_name.add(option_5);
                h10_options_name.add(option_6);
                h10_options_name.add(option_7);
                correct_list.add("option_1");
                correct_list.add("option_2");
                correct_list.add("option_3");
                correct_list.add("option_4");
                correct_list.add("option_5");
                correct_list.add("option_6");
                correct_list.add("option_7");

            }
            cursor.close();
        }
        if (h10_options_name != null && h10_options_name.size() > 0) {
            for (int i = 0; i < h10_options_name.size(); i++) {
                RadioButton radioButton = (RadioButton) rg_h10_answer.getChildAt(i);
                String check = h10_options_name.get(i);
                if (!StringUtils.isEmpty(check)) {
                    radioButton.setVisibility(View.VISIBLE);
                    radioButton.setText(h10_options_name.get(i));
                    if (radioButton != null) {
                        if (correct_option.equalsIgnoreCase(correct_list.get(i))) {
                            radioButton.setChecked(true);
                            radioButton.setTextColor(getResources().getColor(R.color.navy_blue));
                        }
                    }
                }
            }
        }

    }

    void GetAllMethods() {
        readFromDataBase();
        LoadPreviousData();
        GetMaximumCrops();
        Seth3();
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

    void RadioGroupFalse_h3_4_5() {
        for (int i = 0; i < rg_h3_answer.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg_h3_answer.getChildAt(i);
            if (radioButton != null) {
                radioButton.setEnabled(false);
            }
        }
        for (int i = 0; i < rg_h4_answer.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg_h4_answer.getChildAt(i);
            if (radioButton != null) {
                radioButton.setEnabled(false);
            }
        }
        for (int i = 0; i < rg_h5_answer.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg_h5_answer.getChildAt(i);
            if (radioButton != null) {
                radioButton.setEnabled(false);
            }
        }
    }

    void RadioGroupFalse_h8_9_10() {
        for (int i = 0; i < rg_h8_answer.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg_h8_answer.getChildAt(i);
            if (radioButton != null) {
                radioButton.setEnabled(false);
            }
        }
        for (int i = 0; i < rg_h9_answer.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg_h9_answer.getChildAt(i);
            if (radioButton != null) {
                radioButton.setEnabled(false);
            }
        }
        for (int i = 0; i < rg_h10_answer.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) rg_h10_answer.getChildAt(i);
            if (radioButton != null) {
                radioButton.setEnabled(false);
            }
        }
    }

}
