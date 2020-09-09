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


import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class Q_section1 extends AppCompatActivity {

    boolean isFromEdit = false;
    LinearLayout caller_layout;
    DatabaseAdapter databaseAccess;

    String userName = "";
    boolean isPendingCall = false;
    String region = "";
    String emp_id = "";
    String order_id = "";
    TextView txtFarmerID, txtEmpID, txtOrderID;
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String farmer_cellphone;
    String id;
    @BindView(R.id.q1_1_layout)
    LinearLayout q1_1_layout;

    @BindView(R.id.q1_2_layout)
    LinearLayout q1_2_layout;

    @BindView(R.id.q1_3_layout)
    LinearLayout q1_3_layout;

    @BindView(R.id.q1_4_layout)
    LinearLayout q1_4_layout;

    @BindView(R.id.j1_j2_j3_layout)
    LinearLayout j1_j2_j3_layout;

    @BindView(R.id.rg_q1_1)
    RadioGroup rg_q1_1;
    @BindView(R.id.rg_q1_3)
    RadioGroup rg_q1_3;
    @BindView(R.id.rg_q1_4)
    RadioGroup rg_q1_4;

    @BindView(R.id.txt_q1_2_time)
    TextView txt_q1_2_time;

    @BindView(R.id.txt_j1)
    EditText txt_j1;

    @BindView(R.id.txt_j2)
    EditText txt_j2;

    @BindView(R.id.txt_j3)
    EditText txt_j3;

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;
    String q1_2_hh = "";
    String q1_2_mm = "";
    String q1 = "";
    String q3 = "";
    String q4 = "";
    String j1 = "";
    String j2 = "";
    String j3 = "";

    String end_day = "";
    String end_month = "";
    String end_year = "";
    String enum_name = "";
    String enum_code = "";

    ImageView btniBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section1);
        ButterKnife.bind(this);
        btniBack = findViewById(R.id.btnBack);
        databaseAccess = new DatabaseAdapter(Q_section1.this);
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
        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);
        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);
        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        id = getIntent().getStringExtra("id");
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
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RConsUtils.hideKeyboard(Q_section1.this);
                int rg_q1_1_id = rg_q1_1.getCheckedRadioButtonId();
                int rg_q1_3_id = rg_q1_3.getCheckedRadioButtonId();
                int rg_q1_4_id = rg_q1_4.getCheckedRadioButtonId();

                if (q1_1_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_q1_1_id);

                    if (rg_q1_1_id == R.id.rbtn_q1_1_1 || rg_q1_1_id == R.id.rbtn_q1_1_2) {
                        q1_1_layout.setVisibility(View.GONE);
                        q1_2_layout.setVisibility(View.VISIBLE);
                        q1 = radioButton.getTag().toString();
                        SetDate();
                        MubLog.cpnsoleLog("End _Time_  " + q1_2_hh + " : " + q1_2_mm + " Date = " + end_day + "/" + end_month + "/" + end_year);
                    } else {
                        Toast.makeText(Q_section1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (q1_2_layout.getVisibility() == View.VISIBLE) {
                    q1_2_layout.setVisibility(View.GONE);
                    q1_3_layout.setVisibility(View.VISIBLE);


                } else if (q1_3_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_q1_3_id);

                    if (rg_q1_3_id == R.id.rbtn_q1_3_1
                            || rg_q1_3_id == R.id.rbtn_q1_3_2
                            || rg_q1_3_id == R.id.rbtn_q1_3_3) {

                        q1_3_layout.setVisibility(View.GONE);
                        q1_4_layout.setVisibility(View.VISIBLE);
                        q3 = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Q_section1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (q1_4_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_q1_4_id);
                    if (rg_q1_4_id == R.id.rbtn_q1_4_1
                            || rg_q1_4_id == R.id.rbtn_q1_4_2
                            || rg_q1_4_id == R.id.rbtn_q1_4_3) {

                        q1_4_layout.setVisibility(View.GONE);
                        j1_j2_j3_layout.setVisibility(View.VISIBLE);
                        q4 = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Q_section1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (j1_j2_j3_layout.getVisibility() == View.VISIBLE) {

                    ///////// Section J  Q,No J1,J2, J3 ///////////////
                    Intent intent = new Intent(Q_section1.this, AddReportActivity.class);
                    intent.putExtra("isPendingCall", isPendingCall);
                    intent.putExtra("username", userName);
                    intent.putExtra("region", region);
                    intent.putExtra("emp_id", emp_id);
                    intent.putExtra("order_id", order_id);
                    intent.putExtra("farmer_id", FarmerID);
                    intent.putExtra("farmerDistrict", farmerDistrict);
                    intent.putExtra("farmerVillage", farmerVillage);
                    intent.putExtra("farmerTehsil", farmerTehsil);
                    startActivityForResult(intent, 88);
                }
                SaveData();
            }

        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_section1.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("section", "sectionI");
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
                        Toast.makeText(Q_section1.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
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
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (j1_j2_j3_layout.getVisibility() == View.VISIBLE) {

            j1_j2_j3_layout.setVisibility(View.GONE);
            q1_4_layout.setVisibility(View.VISIBLE);

        } else if (q1_4_layout.getVisibility() == View.VISIBLE) {
            q1_3_layout.setVisibility(View.VISIBLE);
            q1_4_layout.setVisibility(View.GONE);

        } else if (q1_3_layout.getVisibility() == View.VISIBLE) {

            q1_2_layout.setVisibility(View.VISIBLE);
            q1_3_layout.setVisibility(View.GONE);

        } else if (q1_2_layout.getVisibility() == View.VISIBLE) {

            q1_1_layout.setVisibility(View.VISIBLE);
            q1_2_layout.setVisibility(View.GONE);

        } else if (q1_1_layout.getVisibility() == View.VISIBLE) {
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
        if (ActivityCompat.checkSelfPermission(Q_section1.this,
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
                    Toast.makeText(Q_section1.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void SaveData() {

        try {

            createNewColumnsBeforeInsertion(DatabaseAdapter.Section1Table);


            databaseAccess.saveSectionI_Data(enum_name, enum_code, emp_id, order_id, FarmerID, q1, q1_2_hh, q1_2_mm, q3, q4, txt_j1.getText().toString(), txt_j2.getText().toString(), txt_j3.getText().toString(), end_day, end_month, end_year);


            MubLog.cpnsoleLog("inside SaveData FarmerID " + FarmerID + " SectionBTable  =  " + DatabaseAdapter.Section1Table);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID, DatabaseAdapter.Section1Table);


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


            String end_year = "ALTER TABLE `" + tableName + "` ADD `end_year` TEXT DEFAULT '' ";
            databaseAccess.createMissingColumn(end_year);

            String end_month = "ALTER TABLE `" + tableName + "` ADD `end_month` TEXT DEFAULT '' ";
            databaseAccess.createMissingColumn(end_month);

            String end_day = "ALTER TABLE `" + tableName + "` ADD `end_day` TEXT DEFAULT '' ";
            databaseAccess.createMissingColumn(end_day);

        } catch (Exception ex) {
            MubLog.cpnsoleLog("inside Section 1" + ex.toString());

        }
    }


    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSection1Data(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                q1 = cursor.getString(cursor.getColumnIndex("q1_1"));
                q1_2_hh = cursor.getString(cursor.getColumnIndex("q1_2_hh"));
                q1_2_mm = cursor.getString(cursor.getColumnIndex("q1_2_mm"));
                q3 = cursor.getString(cursor.getColumnIndex("q1_3"));
                q4 = cursor.getString(cursor.getColumnIndex("q1_4"));
                j1 = cursor.getString(cursor.getColumnIndex("j1"));
                j2 = cursor.getString(cursor.getColumnIndex("j2"));
                j3 = cursor.getString(cursor.getColumnIndex("j3"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        try {
            readFromDataBase();

            if (!StringUtils.isEmpty(q1)) {
                for (int i = 0; i < rg_q1_1.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q1_1.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q1)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            if (!StringUtils.isEmpty(q1_2_hh)) {
                if (!StringUtils.isEmpty(q1_2_mm)) {
                    txt_q1_2_time.setText(q1_2_hh + ":" + q1_2_mm);
                }
            }
            if (!StringUtils.isEmpty(q3)) {
                for (int i = 0; i < rg_q1_3.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q1_3.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q3)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            if (!StringUtils.isEmpty(q4)) {
                for (int i = 0; i < rg_q1_4.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q1_4.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q4)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            if (!StringUtils.isEmpty(j1)) {
                txt_j1.setText(j1);
            }
            if (!StringUtils.isEmpty(j2)) {
                txt_j2.setText(j2);
            }
            if (!StringUtils.isEmpty(j3)) {
                txt_j3.setText(j3);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void SetDate() {
        try {
            Cursor cursor = databaseAccess.getSection1Data(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                q1_2_hh = cursor.getString(cursor.getColumnIndex("q1_2_hh"));
                q1_2_mm = cursor.getString(cursor.getColumnIndex("q1_2_mm"));

                end_day = cursor.getString(cursor.getColumnIndex("end_day"));
                end_month = cursor.getString(cursor.getColumnIndex("end_month"));
                end_year = cursor.getString(cursor.getColumnIndex("end_year"));


            }
            if (StringUtils.isEmpty(q1_2_hh)) {
                q1_2_hh = MubDateAndTime.INSTANCE.getcurrentTime(3);// Integer.toString(month + 1);
                q1_2_mm = MubDateAndTime.INSTANCE.getcurrentTime(4);// Integer.toString(month + 1);
            }
            txt_q1_2_time.setText(q1_2_hh + ":" + q1_2_mm);

            if (StringUtils.isEmpty(end_day)) {
                end_day = MubDateAndTime.INSTANCE.getcurrentTime(2);
                end_month = MubDateAndTime.INSTANCE.getcurrentTime(1);
                end_year = MubDateAndTime.INSTANCE.getcurrentTime(0);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("Date Error " + e.getMessage());
            //  Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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
