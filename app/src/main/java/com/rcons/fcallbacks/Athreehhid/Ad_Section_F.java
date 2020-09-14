package com.rcons.fcallbacks.Athreehhid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ad_Section_F extends AppCompatActivity {

    @BindView(R.id.qf1_layout)
    LinearLayout qf1_layout;
    @BindView(R.id.qf2_layout)
    LinearLayout qf2_layout;
    @BindView(R.id.qf3_layout)
    LinearLayout qf3_layout;
    @BindView(R.id.qf4_layout)
    LinearLayout qf4_layout;
    @BindView(R.id.qf5_layout)
    LinearLayout qf5_layout;
    @BindView(R.id.qf6_layout)
    LinearLayout qf6_layout;
    @BindView(R.id.qf6a_layout)
    LinearLayout qf6a_layout;

    @BindView(R.id.rg_f1)
    RadioGroup rg_f1;
    @BindView(R.id.rg_f3)
    RadioGroup rg_f3;
    @BindView(R.id.rg_f4)
    RadioGroup rg_f4;
    @BindView(R.id.rg_f5)
    RadioGroup rg_f5;
    @BindView(R.id.rg_f6)
    RadioGroup rg_f6;
    @BindView(R.id.rg_f6a)
    RadioGroup rg_f6a;

    @BindView(R.id.checkbox_f2_1)
    CheckBox checkbox_f2_1;
    @BindView(R.id.checkbox_f2_2)
    CheckBox checkbox_f2_2;
    @BindView(R.id.checkbox_f2_3)
    CheckBox checkbox_f2_3;
    @BindView(R.id.checkbox_f2_4)
    CheckBox checkbox_f2_4;
    @BindView(R.id.checkbox_f2_5)
    CheckBox checkbox_f2_5;
    @BindView(R.id.checkbox_f2_6)
    CheckBox checkbox_f2_6;
    @BindView(R.id.checkbox_f2_7)
    CheckBox checkbox_f2_7;
    @BindView(R.id.checkbox_f2_8)
    CheckBox checkbox_f2_8;
    @BindView(R.id.checkbox_f2_9)
    CheckBox checkbox_f2_9;
    @BindView(R.id.checkbox_f2_10)
    CheckBox checkbox_f2_10;
    @BindView(R.id.checkbox_f2_11)
    CheckBox checkbox_f2_11;
    @BindView(R.id.checkbox_f2_12)
    CheckBox checkbox_f2_12;
    @BindView(R.id.checkbox_f2_13)
    CheckBox checkbox_f2_13;
    @BindView(R.id.checkbox_f2_14)
    CheckBox checkbox_f2_14;
    @BindView(R.id.checkbox_f2_15)
    CheckBox checkbox_f2_15;
    @BindView(R.id.checkbox_f2_88)
    CheckBox checkbox_f2_88;
    @BindView(R.id.checkbox_f2_98)
    CheckBox checkbox_f2_98;
    @BindView(R.id.checkbox_f2_99)
    CheckBox checkbox_f2_99;
    @BindView(R.id.checkbox_f2_777)
    CheckBox checkbox_f2_777;


    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.edt_f2_other)
    EditText edt_f2_other;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;


    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    FloatingActionButton btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    FloatingActionButton btn_AddReportQuestionnaire;

    @BindView(R.id.btnBack)
    ImageView btniBack;

    DatabaseAdapter databaseAccess;

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";

    String isSynced = "";
    String isComplete = "";
    String rcons_user = "";
    String enum_code = "";
    String enum_name = "";
    String deviceid = "";
    String insert_or_updated_in_phone_at = "";
    String uploaded_time = "";
    String build_no = "";
    String village_id = "";
    String hhid = "";
    String f_1 = "";
    String f_2_1 = "";
    String f_2_2 = "";
    String f_2_3 = "";
    String f_2_4 = "";
    String f_2_5 = "";
    String f_2_6 = "";
    String f_2_7 = "";
    String f_2_8 = "";
    String f_2_9 = "";
    String f_2_10 = "";
    String f_2_11 = "";
    String f_2_12 = "";
    String f_2_13 = "";
    String f_2_14 = "";
    String f_2_15 = "";
    String f_2_15_other = "";
    String f_2_16 = "";
    String f_2_17 = "";
    String f_2_18 = "";
    String f_2_19 = "";
    String f_3_0 = "";
    String f_3_1 = "";
    String f_3_2 = "";
    String f_3_3 = "";
    String f_3_4 = "";
    String f_3_5 = "";
    String f_3_6 = "";
    String f_3_7 = "";
    String f_3_8 = "";
    String f_3_9 = "";
    String f_3_10 = "";
    String f_3_11 = "";
    String f_3_12 = "";
    String f_3_13 = "";
    String f_3_14 = "";
    String f_3_15 = "";
    String f_3_16 = "";
    String f_3_17 = "";
    String f_3_18 = "";
    String f_3_19 = "";
    String f_3_20 = "";
    String f_3_21 = "";
    String f_3_22 = "";
    String f_3_23 = "";
    String f_3_23_other = "";
    String f_3_24 = "";
    String f_3_25 = "";
    String f_3_26 = "";
    String f_4 = "";
    String f_5 = "";
    String f_6 = "";

    int questionf2CheckBoxCounter = 0;
    ArrayList<String> questionf2CheckBoxTags = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_section_f);

        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(Ad_Section_F.this);
        databaseAccess.Open();


        phone_number = getIntent().getStringExtra("farmer_cellphone");
        id = getIntent().getStringExtra("id");
        village_id = getIntent().getStringExtra("school_code");
        hhid = getIntent().getStringExtra("student_id");
        student_name = getIntent().getStringExtra("student_name");
        school_name = getIntent().getStringExtra("school_name");

        txt_School_Code.setText("Village Code : " + village_id);
        txt_Student_id.setText("HH Id : " + hhid);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rg_f1_ID = rg_f1.getCheckedRadioButtonId();
                int rg_f3_ID = rg_f3.getCheckedRadioButtonId();
                int rg_f4_ID = rg_f4.getCheckedRadioButtonId();
                int rg_f5_ID = rg_f5.getCheckedRadioButtonId();
                int rg_f6_ID = rg_f6.getCheckedRadioButtonId();
                int rg_f6a_ID = rg_f6a.getCheckedRadioButtonId();

                if (qf1_layout.getVisibility() == View.VISIBLE) {
                    if (rg_f1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_f1_ID);
                        f_1 = radioButton.getTag().toString();
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qf2_layout.getVisibility() == View.VISIBLE) {

                } else if (qf3_layout.getVisibility() == View.VISIBLE) {
                    if (rg_f3_ID > 0) {
                        RadioButton radioButton = findViewById(rg_f3_ID);
                        f_3_0 = radioButton.getTag().toString();
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qf4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_f4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_f4_ID);
                        f_4 = radioButton.getTag().toString();
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qf5_layout.getVisibility() == View.VISIBLE) {
                    if (rg_f5_ID > 0) {
                        RadioButton radioButton = findViewById(rg_f5_ID);
                        f_5 = radioButton.getTag().toString();
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qf6_layout.getVisibility() == View.VISIBLE) {
                    if (rg_f6_ID > 0) {
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qf6a_layout.getVisibility() == View.VISIBLE) {
                    if (rg_f6a_ID > 0) {
                    } else {
                        toastMessage("Please Select Option");
                    }

                }

            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btniBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Ad_Section_F.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_cellphone", phone_number);
                intent.putExtra("school_code", village_id);
                intent.putExtra("student_id", hhid);
                startActivityForResult(intent, 88);
            }
        });
        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(phone_number)) {
                        DialUserNumber(phone_number);
                    } else {
                        toastMessage("Phone number is empty.");
                    }
                }

            }
        });
    }

    void SetEnumState() {

        if (RConsUtils.GetRegistrationState() == 1) {
            enum_name = RConsUtils.getEnumName();
            enum_code = RConsUtils.getEnumCode();
            rcons_user = RConsUtils.getUserName();
        }
    }


    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Ad_Section_F.this,
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
                if (!StringUtils.isEmpty(phone_number)) {
                    DialUserNumber(phone_number);
                } else {
                    Toast.makeText(Ad_Section_F.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
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

    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            databaseAccess.savehh_Section_F_Data(isSynced,
                    isComplete,
                    rcons_user,
                    enum_code,
                    enum_name,
                    deviceid,
                    insert_or_updated_in_phone_at,
                    uploaded_time,
                    build_no,
                    village_id,
                    hhid,
                    f_1,
                    f_2_1,
                    f_2_2,
                    f_2_3,
                    f_2_4,
                    f_2_5,
                    f_2_6,
                    f_2_7,
                    f_2_8,
                    f_2_9,
                    f_2_10,
                    f_2_11,
                    f_2_12,
                    f_2_13,
                    f_2_14,
                    f_2_15,
                    f_2_15_other,
                    f_2_16,
                    f_2_17,
                    f_2_18,
                    f_2_19,
                    f_3_0,
                    f_3_1,
                    f_3_2,
                    f_3_3,
                    f_3_4,
                    f_3_5,
                    f_3_6,
                    f_3_7,
                    f_3_8,
                    f_3_9,
                    f_3_10,
                    f_3_11,
                    f_3_12,
                    f_3_13,
                    f_3_14,
                    f_3_15,
                    f_3_16,
                    f_3_17,
                    f_3_18,
                    f_3_19,
                    f_3_20,
                    f_3_21,
                    f_3_22,
                    f_3_23,
                    f_3_23_other,
                    f_3_24,
                    f_3_25,
                    f_3_26,
                    f_4,
                    f_5,
                    f_6
            );
//
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.gethh_section_f_Data(village_id, hhid);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                f_1 = cursor.getString(cursor.getColumnIndex("f_1"));
                f_2_1 = cursor.getString(cursor.getColumnIndex("f_2_1"));
                f_2_2 = cursor.getString(cursor.getColumnIndex("f_2_2"));
                f_2_3 = cursor.getString(cursor.getColumnIndex("f_2_3"));
                f_2_4 = cursor.getString(cursor.getColumnIndex("f_2_4"));
                f_2_5 = cursor.getString(cursor.getColumnIndex("f_2_5"));
                f_2_6 = cursor.getString(cursor.getColumnIndex("f_2_6"));
                f_2_7 = cursor.getString(cursor.getColumnIndex("f_2_7"));
                f_2_8 = cursor.getString(cursor.getColumnIndex("f_2_8"));
                f_2_9 = cursor.getString(cursor.getColumnIndex("f_2_9"));
                f_2_10 = cursor.getString(cursor.getColumnIndex("f_2_10"));
                f_2_11 = cursor.getString(cursor.getColumnIndex("f_2_11"));
                f_2_12 = cursor.getString(cursor.getColumnIndex("f_2_12"));
                f_2_13 = cursor.getString(cursor.getColumnIndex("f_2_13"));
                f_2_14 = cursor.getString(cursor.getColumnIndex("f_2_14"));
                f_2_15 = cursor.getString(cursor.getColumnIndex("f_2_15"));
                f_2_15_other = cursor.getString(cursor.getColumnIndex("f_2_15_other"));
                f_2_16 = cursor.getString(cursor.getColumnIndex("f_2_16"));
                f_2_17 = cursor.getString(cursor.getColumnIndex("f_2_17"));
                f_2_18 = cursor.getString(cursor.getColumnIndex("f_2_18"));
                f_2_19 = cursor.getString(cursor.getColumnIndex("f_2_19"));
                f_3_0 = cursor.getString(cursor.getColumnIndex("f_3_0"));
                f_3_1 = cursor.getString(cursor.getColumnIndex("f_3_1"));
                f_3_2 = cursor.getString(cursor.getColumnIndex("f_3_2"));
                f_3_3 = cursor.getString(cursor.getColumnIndex("f_3_3"));
                f_3_4 = cursor.getString(cursor.getColumnIndex("f_3_4"));
                f_3_5 = cursor.getString(cursor.getColumnIndex("f_3_5"));
                f_3_6 = cursor.getString(cursor.getColumnIndex("f_3_6"));
                f_3_7 = cursor.getString(cursor.getColumnIndex("f_3_7"));
                f_3_8 = cursor.getString(cursor.getColumnIndex("f_3_8"));
                f_3_9 = cursor.getString(cursor.getColumnIndex("f_3_9"));
                f_3_10 = cursor.getString(cursor.getColumnIndex("f_3_10"));
                f_3_11 = cursor.getString(cursor.getColumnIndex("f_3_11"));
                f_3_12 = cursor.getString(cursor.getColumnIndex("f_3_12"));
                f_3_13 = cursor.getString(cursor.getColumnIndex("f_3_13"));
                f_3_14 = cursor.getString(cursor.getColumnIndex("f_3_14"));
                f_3_15 = cursor.getString(cursor.getColumnIndex("f_3_15"));
                f_3_16 = cursor.getString(cursor.getColumnIndex("f_3_16"));
                f_3_17 = cursor.getString(cursor.getColumnIndex("f_3_17"));
                f_3_18 = cursor.getString(cursor.getColumnIndex("f_3_18"));
                f_3_19 = cursor.getString(cursor.getColumnIndex("f_3_19"));
                f_3_20 = cursor.getString(cursor.getColumnIndex("f_3_20"));
                f_3_21 = cursor.getString(cursor.getColumnIndex("f_3_21"));
                f_3_22 = cursor.getString(cursor.getColumnIndex("f_3_22"));
                f_3_23 = cursor.getString(cursor.getColumnIndex("f_3_23"));
                f_3_23_other = cursor.getString(cursor.getColumnIndex("f_3_23_other"));
                f_3_24 = cursor.getString(cursor.getColumnIndex("f_3_24"));
                f_3_25 = cursor.getString(cursor.getColumnIndex("f_3_25"));
                f_3_26 = cursor.getString(cursor.getColumnIndex("f_3_26"));
                f_4 = cursor.getString(cursor.getColumnIndex("f_4"));
                f_5 = cursor.getString(cursor.getColumnIndex("f_5"));
                f_6 = cursor.getString(cursor.getColumnIndex("f_6"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("isDataUpdated", false);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        super.onBackPressed();
    }

    void LoadPreviousData() {
        try {
            RConsUtils.setradiogroup(f_1, rg_f1);
            RConsUtils.setradiogroup(f_3_0, rg_f3);
            RConsUtils.setradiogroup(f_4, rg_f4);
            RConsUtils.setradiogroup(f_5, rg_f5);
            RConsUtils.setradiogroup(f_6, rg_f6);

        } catch (Exception e) {
            toastMessage(e.getMessage());
            MubLog.cpnsoleLog("LoadPreviousData Error : " + e.getMessage());
        }
    }

    void SetQuestionf2CheckBoxListener() {
        checkbox_f2_1.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_1));
        checkbox_f2_2.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_2));
        checkbox_f2_3.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_3));
        checkbox_f2_4.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_4));
        checkbox_f2_5.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_5));
        checkbox_f2_6.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_6));
        checkbox_f2_7.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_7));
        checkbox_f2_8.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_8));
        checkbox_f2_9.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_9));
        checkbox_f2_10.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_10));
        checkbox_f2_11.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_11));
        checkbox_f2_12.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_12));
        checkbox_f2_13.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_13));
        checkbox_f2_14.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_14));

        checkbox_f2_15.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_15));

        checkbox_f2_88.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    checkbox_f2_1.setEnabled(false);
                    checkbox_f2_2.setEnabled(false);
                    checkbox_f2_3.setEnabled(false);
                    checkbox_f2_4.setEnabled(false);
                    checkbox_f2_5.setEnabled(false);
                    checkbox_f2_6.setEnabled(false);
                    checkbox_f2_7.setEnabled(false);
                    checkbox_f2_8.setEnabled(false);
                    checkbox_f2_9.setEnabled(false);
                    checkbox_f2_10.setEnabled(false);
                    checkbox_f2_11.setEnabled(false);
                    checkbox_f2_12.setEnabled(false);
                    checkbox_f2_13.setEnabled(false);
                    checkbox_f2_14.setEnabled(false);
                    checkbox_f2_15.setEnabled(false);

                    checkbox_f2_98.setEnabled(false);
                    checkbox_f2_99.setEnabled(false);
                    checkbox_f2_777.setEnabled(false);

                } else {

                    checkbox_f2_1.setEnabled(true);
                    checkbox_f2_2.setEnabled(true);
                    checkbox_f2_3.setEnabled(true);
                    checkbox_f2_4.setEnabled(true);
                    checkbox_f2_5.setEnabled(true);
                    checkbox_f2_6.setEnabled(true);
                    checkbox_f2_7.setEnabled(true);
                    checkbox_f2_8.setEnabled(true);
                    checkbox_f2_9.setEnabled(true);
                    checkbox_f2_10.setEnabled(true);
                    checkbox_f2_11.setEnabled(true);
                    checkbox_f2_12.setEnabled(true);
                    checkbox_f2_13.setEnabled(true);
                    checkbox_f2_14.setEnabled(true);
                    checkbox_f2_15.setEnabled(true);

                    checkbox_f2_98.setEnabled(true);
                    checkbox_f2_99.setEnabled(true);
                    checkbox_f2_777.setEnabled(true);

                }

            }
        });

        checkbox_f2_88.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_88));

        checkbox_f2_98.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    checkbox_f2_1.setEnabled(false);
                    checkbox_f2_2.setEnabled(false);
                    checkbox_f2_3.setEnabled(false);
                    checkbox_f2_4.setEnabled(false);
                    checkbox_f2_5.setEnabled(false);
                    checkbox_f2_6.setEnabled(false);
                    checkbox_f2_7.setEnabled(false);
                    checkbox_f2_8.setEnabled(false);
                    checkbox_f2_9.setEnabled(false);
                    checkbox_f2_10.setEnabled(false);
                    checkbox_f2_11.setEnabled(false);
                    checkbox_f2_12.setEnabled(false);
                    checkbox_f2_13.setEnabled(false);
                    checkbox_f2_14.setEnabled(false);
                    checkbox_f2_15.setEnabled(false);

                    checkbox_f2_88.setEnabled(false);
                    checkbox_f2_99.setEnabled(false);
                    checkbox_f2_777.setEnabled(false);

                } else {

                    checkbox_f2_1.setEnabled(true);
                    checkbox_f2_2.setEnabled(true);
                    checkbox_f2_3.setEnabled(true);
                    checkbox_f2_4.setEnabled(true);
                    checkbox_f2_5.setEnabled(true);
                    checkbox_f2_6.setEnabled(true);
                    checkbox_f2_7.setEnabled(true);
                    checkbox_f2_8.setEnabled(true);
                    checkbox_f2_9.setEnabled(true);
                    checkbox_f2_10.setEnabled(true);
                    checkbox_f2_11.setEnabled(true);
                    checkbox_f2_12.setEnabled(true);
                    checkbox_f2_13.setEnabled(true);
                    checkbox_f2_14.setEnabled(true);
                    checkbox_f2_15.setEnabled(true);

                    checkbox_f2_88.setEnabled(true);
                    checkbox_f2_99.setEnabled(true);
                    checkbox_f2_777.setEnabled(true);

                }

            }
        });


        checkbox_f2_98.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_98));
        checkbox_f2_99.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_99));

        checkbox_f2_99.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    checkbox_f2_1.setEnabled(false);
                    checkbox_f2_2.setEnabled(false);
                    checkbox_f2_3.setEnabled(false);
                    checkbox_f2_4.setEnabled(false);
                    checkbox_f2_5.setEnabled(false);
                    checkbox_f2_6.setEnabled(false);
                    checkbox_f2_7.setEnabled(false);
                    checkbox_f2_8.setEnabled(false);
                    checkbox_f2_9.setEnabled(false);
                    checkbox_f2_10.setEnabled(false);
                    checkbox_f2_11.setEnabled(false);
                    checkbox_f2_12.setEnabled(false);
                    checkbox_f2_13.setEnabled(false);
                    checkbox_f2_14.setEnabled(false);
                    checkbox_f2_15.setEnabled(false);

                    checkbox_f2_88.setEnabled(false);
                    checkbox_f2_98.setEnabled(false);
                    checkbox_f2_777.setEnabled(false);

                } else {

                    checkbox_f2_1.setEnabled(true);
                    checkbox_f2_2.setEnabled(true);
                    checkbox_f2_3.setEnabled(true);
                    checkbox_f2_4.setEnabled(true);
                    checkbox_f2_5.setEnabled(true);
                    checkbox_f2_6.setEnabled(true);
                    checkbox_f2_7.setEnabled(true);
                    checkbox_f2_8.setEnabled(true);
                    checkbox_f2_9.setEnabled(true);
                    checkbox_f2_10.setEnabled(true);
                    checkbox_f2_11.setEnabled(true);
                    checkbox_f2_12.setEnabled(true);
                    checkbox_f2_13.setEnabled(true);
                    checkbox_f2_14.setEnabled(true);
                    checkbox_f2_15.setEnabled(true);

                    checkbox_f2_88.setEnabled(true);
                    checkbox_f2_98.setEnabled(true);
                    checkbox_f2_777.setEnabled(true);

                }

            }
        });

        checkbox_f2_777.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    checkbox_f2_1.setEnabled(false);
                    checkbox_f2_2.setEnabled(false);
                    checkbox_f2_3.setEnabled(false);
                    checkbox_f2_4.setEnabled(false);
                    checkbox_f2_5.setEnabled(false);
                    checkbox_f2_6.setEnabled(false);
                    checkbox_f2_7.setEnabled(false);
                    checkbox_f2_8.setEnabled(false);
                    checkbox_f2_9.setEnabled(false);
                    checkbox_f2_10.setEnabled(false);
                    checkbox_f2_11.setEnabled(false);
                    checkbox_f2_12.setEnabled(false);
                    checkbox_f2_13.setEnabled(false);
                    checkbox_f2_14.setEnabled(false);
                    checkbox_f2_15.setEnabled(false);

                    checkbox_f2_88.setEnabled(false);
                    checkbox_f2_98.setEnabled(false);
                    checkbox_f2_99.setEnabled(false);

                } else {

                    checkbox_f2_1.setEnabled(true);
                    checkbox_f2_2.setEnabled(true);
                    checkbox_f2_3.setEnabled(true);
                    checkbox_f2_4.setEnabled(true);
                    checkbox_f2_5.setEnabled(true);
                    checkbox_f2_6.setEnabled(true);
                    checkbox_f2_7.setEnabled(true);
                    checkbox_f2_8.setEnabled(true);
                    checkbox_f2_9.setEnabled(true);
                    checkbox_f2_10.setEnabled(true);
                    checkbox_f2_11.setEnabled(true);
                    checkbox_f2_12.setEnabled(true);
                    checkbox_f2_13.setEnabled(true);
                    checkbox_f2_14.setEnabled(true);
                    checkbox_f2_15.setEnabled(true);

                    checkbox_f2_88.setEnabled(true);
                    checkbox_f2_98.setEnabled(true);
                    checkbox_f2_99.setEnabled(true);

                }

            }
        });

        checkbox_f2_777.setOnClickListener(view -> SetQuestionf2CheckBoxItemClick(checkbox_f2_777));


    }

    void SetQuestionf2CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            if (questionf2CheckBoxCounter < 16) {
                checkBox.setChecked(true);
                questionf2CheckBoxCounter++;

                questionf2CheckBoxTags.add(checkBox.getTag().toString());
                if (checkBox.getId() == R.id.checkbox_f2_15) {
                    edt_f2_other.setVisibility(View.VISIBLE);
                }


            } else {
                if (checkBox.getId() == R.id.checkbox_f2_15) {
                    edt_f2_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            MubLog.cpnsoleLog("uncheck" + checkBox.getTag().toString());
            if (checkBox.getId() == R.id.checkbox_f2_15) {
                edt_f2_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            questionf2CheckBoxCounter--;
            questionf2CheckBoxTags.remove(checkBox.getTag().toString());
        }
        MubLog.cpnsoleLog("checkbox_f2_tag" + checkBox.getTag().toString());
    }
}