package com.rcons.fcallbacks.Questionnaire;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.util.Calendar;

public class Q_sectionC extends AppCompatActivity {
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    private static final String TAG = "Q_sectionC";
    DatabaseAdapter databaseAccess;
    String userName = "";
    String region = "";
    String emp_id = "";
    String order_id = "";
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String ao_name = "";
    String fa_name = "";
    String ai_name = "";
    TextView txtFarmerID, txtEmpID, txtOrderID;
    RadioGroup RG_qc1;
    LinearLayout Q_c1_layout, Q_c2_layout;
    TextView txt_c2_Date, txt_c2_time;
    Button NextButton, BackButton, btn_RedialCall, btn_AddReportQuestionnaire;
    String farmer_cellphone;
    String id;
    String c1 = "";
    String c2_day = "";
    String c2_month = "";
    String c2_year = "";
    String c2_hh = "";
    String c2_mm = "";
    String c3_day = "";
    String c3_month = "";
    String c3_year = "";
    String c4_hh = "";
    String c4_mm = "";
    String commetns = "";
    String enum_name = "";
    String enum_code = "";

    ImageView btniBack;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_c);
        btniBack = findViewById(R.id.btnBack);
        databaseAccess = new DatabaseAdapter(Q_sectionC.this);
        databaseAccess.Open();

        userName = getIntent().getStringExtra("username");
        region = getIntent().getStringExtra("region");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        FarmerID = getIntent().getStringExtra("farmerID");
        MubLog.cpnsoleLog("farmerID" + FarmerID);
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        ao_name = getIntent().getStringExtra("ao_name");
        fa_name = getIntent().getStringExtra("fa_name");
        ai_name = getIntent().getStringExtra("ai_name");

        id = getIntent().getStringExtra("id");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");

        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);

        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);

        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);


        NextButton = findViewById(R.id.btn_next);
        BackButton = findViewById(R.id.btn_back);
        btn_RedialCall = findViewById(R.id.btn_RedialCall);
        btn_AddReportQuestionnaire = findViewById(R.id.btn_AddReportQuestionnaire);


        Q_c1_layout = findViewById(R.id.qc1_layout);
        Q_c2_layout = findViewById(R.id.qc2_layout);

        RG_qc1 = findViewById(R.id.rg_qc1);

        txt_c2_Date = findViewById(R.id.txt_c2_date);
        txt_c2_time = findViewById(R.id.txt_c2_time);

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
        txt_c2_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Q_sectionC.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                txt_c2_Date.setText(date);
                c2_day = Integer.toString(day);
                c2_month = Integer.toString(month);
                c2_year = Integer.toString(year);

            }
        };
        RG_qc1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupc1_ID = RG_qc1.getCheckedRadioButtonId();
                if (RGroupc1_ID == R.id.rbtn_c1_3) {
                    Q_c2_layout.setVisibility(View.VISIBLE);
                } else {
                    Q_c2_layout.setVisibility(View.GONE);

                }
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MubLog.cpnsoleLog("btn_AddReportQuestionnaire " + FarmerID);
                Intent intent = new Intent(Q_sectionC.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "sectionC");
                startActivityForResult(intent, 88);
            }
        });

        txt_c2_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Q_sectionC.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_c2_time.setText(selectedHour + ":" + selectedMinute);
                        c2_hh = Integer.toString(selectedHour);
                        c2_mm = Integer.toString(selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FarmerID = getIntent().getStringExtra("farmerID");
                int RGroupQC_ID = RG_qc1.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(Q_sectionC.this);
                if (Q_c1_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQC_ID);

                    if (RGroupQC_ID == R.id.rbtn_c1_1) {
                        readdateandtime();
                        c1 = radioButton.getTag().toString();
                        SaveData();
                        MubLog.cpnsoleLog("Start _Time_  " + c4_hh + " : " + c4_mm + " Date = " + c3_day + "/" + c3_month + "/" + c3_year);
                        Intent intent = new Intent(Q_sectionC.this, Q_sectionD.class);
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
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        startActivityForResult(intent, 88);

                    } else if (RGroupQC_ID == R.id.rbtn_c1_2) {

                        Intent intent = new Intent(Q_sectionC.this, AddReportActivity.class);
                        intent.putExtra("username", userName);

                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmer_id", FarmerID);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("ao_name", ao_name);
                        intent.putExtra("fa_name", fa_name);
                        intent.putExtra("ai_name", ai_name);
                        intent.putExtra("empID", emp_id);
                        intent.putExtra("isFromEdit", isFromEdit);
                        c1 = radioButton.getTag().toString();
                        SaveData();
                        startActivityForResult(intent, 88);


                    } else if (RGroupQC_ID == R.id.rbtn_c1_3) {
                        c1 = radioButton.getTag().toString();
                        SaveData();
                        Q_c1_layout.setVisibility(View.GONE);
                        Q_c2_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionC.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (Q_c2_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q C 2 ///////////////
                    String c2_date = txt_c2_Date.getText().toString();
                    String c2_time = txt_c2_time.getText().toString();
                    if (StringUtils.isEmpty(c2_date) || (StringUtils.isEmpty(c2_time))) {
                        Toast.makeText(Q_sectionC.this, "Please Enter Date and Time", Toast.LENGTH_SHORT).show();
                    } else {

                        SaveData();
                        Intent intent = new Intent(Q_sectionC.this, AddReportActivity.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmer_id", FarmerID);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("ao_name", ao_name);
                        intent.putExtra("fa_name", fa_name);
                        intent.putExtra("ai_name", ai_name);
                        intent.putExtra("empID", emp_id);
                        intent.putExtra("isFromEdit", isFromEdit);
                        startActivityForResult(intent, 88);
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
        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(farmer_cellphone)) {
                        DialUserNumber(farmer_cellphone);
                    } else {
                        Toast.makeText(Q_sectionC.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        int RG_qc1_ID = RG_qc1.getCheckedRadioButtonId();


        if (Q_c2_layout.getVisibility() == View.VISIBLE) {
            txt_c2_Date.setText("");
            txt_c2_time.setText("");
            Q_c1_layout.setVisibility(View.VISIBLE);
            Q_c2_layout.setVisibility(View.GONE);
        } else if (Q_c1_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        }

    }

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Q_sectionC.this,
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
                    Toast.makeText(Q_sectionC.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void SaveData() {

        try {


            createNewColumnsBeforeInsertion (DatabaseAdapter.SectionCTable);



            databaseAccess.saveSection_C_Data(enum_name,enum_code,emp_id, order_id, FarmerID, c1, c2_day, c2_month, c2_year, c2_hh, c2_mm, c3_day, c3_month, c3_year, c4_hh, c4_mm, commetns);





            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionCTable);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionCTable);

            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionCTable);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {
            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY "+tableName);

            String  patch_querry =  "UPDATE  "+tableName+" SET  c1 = '3' WHERE c2_year !='' AND c1 IN (1,2)";

            databaseAccess.execute_patch_query(patch_querry);



        }catch(Exception ex) {
            MubLog.cpnsoleLog("inside Section 1"+ex.toString());

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





    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionCData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                c1 = cursor.getString(cursor.getColumnIndex("c1"));
                c2_day = cursor.getString(cursor.getColumnIndex("c2_day"));
                c2_month = cursor.getString(cursor.getColumnIndex("c2_month"));
                c2_year = cursor.getString(cursor.getColumnIndex("c2_year"));
                c2_hh = cursor.getString(cursor.getColumnIndex("c2_hh"));
                c2_mm = cursor.getString(cursor.getColumnIndex("c2_mm"));
                c3_day = cursor.getString(cursor.getColumnIndex("c3_day"));
                c3_month = cursor.getString(cursor.getColumnIndex("c3_month"));
                c3_year = cursor.getString(cursor.getColumnIndex("c3_year"));
                c4_hh = cursor.getString(cursor.getColumnIndex("c4_hh"));
                c4_mm = cursor.getString(cursor.getColumnIndex("c4_mm"));
                commetns = cursor.getString(cursor.getColumnIndex("comments"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {

        readFromDataBase();
        if (!StringUtils.isEmpty(c1)) {
            for (int i = 0; i < RG_qc1.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qc1.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(c1)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(c2_day)) {
            if (!StringUtils.isEmpty(c2_month)) {
                if (!StringUtils.isEmpty(c2_year)) {
                    txt_c2_Date.setText(c2_day + "/" + c2_month + "/" + c2_year);

                }

            }

        }
        if (!StringUtils.isEmpty(c2_hh)) {
            if (!StringUtils.isEmpty(c2_mm)) {
                txt_c2_time.setText(c2_hh + ":" + c2_mm);

            }

        }

    }

    void readdateandtime() {

        Cursor cursor = databaseAccess.getSectionCData(FarmerID);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            c3_day = cursor.getString(cursor.getColumnIndex("c3_day"));
            c3_month = cursor.getString(cursor.getColumnIndex("c3_month"));
            c3_year = cursor.getString(cursor.getColumnIndex("c3_year"));
            c4_hh = cursor.getString(cursor.getColumnIndex("c4_hh"));
            c4_mm = cursor.getString(cursor.getColumnIndex("c4_mm"));
        }

        if (StringUtils.isEmpty(c3_day)) {
            c3_day = MubDateAndTime.INSTANCE.getcurrentTime(2);
        }
        if (StringUtils.isEmpty(c3_month)) {
            c3_month = MubDateAndTime.INSTANCE.getcurrentTime(1);
            MubLog.cpnsoleLog("Month " + MubDateAndTime.INSTANCE.getcurrentTime(1));
        }
        if (StringUtils.isEmpty(c3_year)) {
            c3_year = MubDateAndTime.INSTANCE.getcurrentTime(0);
        }
        if (StringUtils.isEmpty(c4_hh)) {
            c4_hh = MubDateAndTime.INSTANCE.getcurrentTime(3);
            c4_mm = MubDateAndTime.INSTANCE.getcurrentTime(4);
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
