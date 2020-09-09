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

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Section_F_table6 extends AppCompatActivity {
    DatabaseAdapter databaseAccess;
    boolean isFromEdit = false;
    LinearLayout caller_layout;
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
    String ao_name = "";
    String fa_name = "";
    String ai_name = "";

    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;


    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;

    @BindView(R.id.rg_qf16)
    RadioGroup rg_qf16;
    @BindView(R.id.rg_qf17)
    RadioGroup rg_qf17;
    @BindView(R.id.rg_qf19)
    RadioGroup rg_qf19;
    @BindView(R.id.rg_qf20)
    RadioGroup rg_qf20;


    @BindView(R.id.qf16_layout)
    LinearLayout qf16_layout;
    @BindView(R.id.qf17_layout)
    LinearLayout qf17_layout;
    @BindView(R.id.qf18_layout)
    LinearLayout qf18_layout;
    @BindView(R.id.qf19_layout)
    LinearLayout qf19_layout;
    @BindView(R.id.qf20_layout)
    LinearLayout qf20_layout;

    @BindView(R.id.edt_qf18)
    EditText edt_qf18;

    String f16 = "";
    String f17 = "";
    String f18 = "";
    String f19 = "";
    String f20 = "";
    String comments = "";
    String enum_name = "";
    String enum_code = "";

    ImageView btniBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section__f_table6);
        ButterKnife.bind(this);

        btniBack = findViewById(R.id.btnBack);

        databaseAccess = new DatabaseAdapter(Section_F_table6.this);
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
        ao_name = getIntent().getStringExtra("ao_name");
        fa_name = getIntent().getStringExtra("fa_name");
        ai_name = getIntent().getStringExtra("ai_name");


        id = getIntent().getStringExtra("id");
        isAlternateFarmer = getIntent().getBooleanExtra("isAlternateFarmer", false);
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");
        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);
        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);
        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        readFromDataBase();
        LoadPreviousData();
        GetEnum();
        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RConsUtils.hideKeyboard(Section_F_table6.this);
                int rg_qf16_id = rg_qf16.getCheckedRadioButtonId();
                int rg_qf17_id = rg_qf17.getCheckedRadioButtonId();
                int rg_qf19_id = rg_qf19.getCheckedRadioButtonId();
                int rg_qf20_id = rg_qf20.getCheckedRadioButtonId();
                SaveData();
                if (qf16_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 16 ///////////////

                    RadioButton radioButton = findViewById(rg_qf16_id);

                    if (rg_qf16_id == R.id.rbtn_f16_1
                            || rg_qf16_id == R.id.rbtn_f16_2
                            || rg_qf16_id == R.id.rbtn_f16_3) {
                        qf16_layout.setVisibility(View.GONE);

                        qf17_layout.setVisibility(View.VISIBLE);
                        f16 = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Section_F_table6.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf17_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 17 ///////////////

                    RadioButton radioButton = findViewById(rg_qf17_id);

                    if (rg_qf17_id == R.id.rbtn_f17_1 || rg_qf17_id == R.id.rbtn_f17_3) {
                        qf17_layout.setVisibility(View.GONE);
                        edt_qf18.setVisibility(View.VISIBLE);
                        qf18_layout.setVisibility(View.VISIBLE);
                        f17 = radioButton.getTag().toString();
                    } else if (rg_qf17_id == R.id.rbtn_f17_2) {
                        qf17_layout.setVisibility(View.GONE);
                        qf20_layout.setVisibility(View.VISIBLE);
                        f17 = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Section_F_table6.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf18_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 18 /////////////// Logic
                    f18 = edt_qf18.getText().toString();

                    if (StringUtils.isEmpty(f18)) {
                        Toast.makeText(Section_F_table6.this, "Please Enter feet", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(f18) < 10 || Integer.parseInt(f18) > 1000) && !f18.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table6.this, "Please Enter in 10-1000", Toast.LENGTH_SHORT).show();
                    } else {
                        qf18_layout.setVisibility(View.GONE);
                        qf19_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qf19_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 19 ///////////////
                    RadioButton radioButton = findViewById(rg_qf19_id);

                    if (rg_qf19_id == R.id.rbtn_f19_1
                            || rg_qf19_id == R.id.rbtn_f19_2
                            || rg_qf19_id == R.id.rbtn_f19_3) {
                        qf19_layout.setVisibility(View.GONE);
                        qf20_layout.setVisibility(View.VISIBLE);
                        f19 = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Section_F_table6.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf20_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 20 ///////////////
                    RadioButton radioButton = findViewById(rg_qf20_id);

                    if (rg_qf20_id == R.id.rbtn_f20_1
                            || rg_qf20_id == R.id.rbtn_f20_2
                            || rg_qf20_id == R.id.rbtn_f20_3) {
                        f20 = radioButton.getTag().toString();
                        Intent intent = new Intent(Section_F_table6.this, Q_sectionG.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("isPendingCall", isPendingCall);
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
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("isFromEdit", isFromEdit);
                        startActivityForResult(intent, 88);

                    } else {
                        Toast.makeText(Section_F_table6.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                }

                SaveData();
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Section_F_table6.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "section_f_table6");
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

        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(farmer_cellphone)) {
                        DialUserNumber(farmer_cellphone);
                    } else {
                        Toast.makeText(Section_F_table6.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
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
        if (ActivityCompat.checkSelfPermission(Section_F_table6.this,
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
                    Toast.makeText(Section_F_table6.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {

        int rg_qf17_id = rg_qf17.getCheckedRadioButtonId();
        if (qf20_layout.getVisibility() == View.VISIBLE) {

            if (rg_qf17_id == R.id.rbtn_f17_2) {

                f17 = "";
                qf17_layout.setVisibility(View.VISIBLE);
                qf20_layout.setVisibility(View.GONE);
            } else {
                f19 = "";
                qf19_layout.setVisibility(View.VISIBLE);
                qf20_layout.setVisibility(View.GONE);
            }

        } else if (qf19_layout.getVisibility() == View.VISIBLE) {
            ///////// F 19 /////////
            f18 = "";
            qf18_layout.setVisibility(View.VISIBLE);
            qf19_layout.setVisibility(View.GONE);
        } else if (qf18_layout.getVisibility() == View.VISIBLE) {
            ///////// F 18 /////////
            f17 = "";
            qf17_layout.setVisibility(View.VISIBLE);
            qf18_layout.setVisibility(View.GONE);

        } else if (qf17_layout.getVisibility() == View.VISIBLE) {
            ///////// F 17 /////////
            f16 = "";
            qf16_layout.setVisibility(View.VISIBLE);
            qf17_layout.setVisibility(View.GONE);

        } else if (qf16_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();
        }


    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionF_table_6Data(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                f16 = cursor.getString(cursor.getColumnIndex("f16"));
                f17 = cursor.getString(cursor.getColumnIndex("f17"));
                f18 = cursor.getString(cursor.getColumnIndex("f18"));
                f19 = cursor.getString(cursor.getColumnIndex("f19"));
                f20 = cursor.getString(cursor.getColumnIndex("f20"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        if (!StringUtils.isEmpty(f16)) {
            for (int i = 0; i < rg_qf16.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf16.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f16)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(f17)) {
            for (int i = 0; i < rg_qf17.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf17.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f17)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(f18)) {
            edt_qf18.setText(f18);
        }

        if (!StringUtils.isEmpty(f19)) {
            for (int i = 0; i < rg_qf19.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf19.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f19)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(f20)) {
            for (int i = 0; i < rg_qf20.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf20.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f20)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

    }

    void SaveData() {

        try {
            createNewColumnsBeforeInsertion (DatabaseAdapter.SectionFTable6);



            databaseAccess.saveSectionF_table6_Data(enum_name,enum_code,emp_id, order_id, FarmerID, f16, f17, f18, f19, f20, comments);


            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionFTable6);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionFTable6);



        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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
