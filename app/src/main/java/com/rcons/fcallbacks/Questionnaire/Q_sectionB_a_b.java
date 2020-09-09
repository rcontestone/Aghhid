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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.rcons.fcallbacks.Main.CallMenuActivity;
import com.rcons.fcallbacks.Main.MainMenuActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.VillageSearch.SelectEnumNameActivity;

import java.util.Calendar;

public class Q_sectionB_a_b extends AppCompatActivity {

    DatabaseAdapter databaseAccess;

    String userName = "";
    boolean isPendingCall = false;

    String emp_id = "";
    String order_id = "";
    String region = "";
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
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    RadioGroup RG_qb26;
    LinearLayout qb26_layout, enumName_layout;
    LinearLayout qb1_layout, status_layout, qb2_layout, qb3_layout, qb4_layout, qb5_layout, qb6_layout, qb7_layout, qb8_layout, qb9_layout, qb10_layout, qb11a_layout, qb11b_layout, qb12_layout, qb13_layout, qb14_layout, qb15_layout, qb16_layout, qb17_layout, qb18_layout, qb19_layout, qb20_layout, qb21_layout, qb22_layout, qb23_layout, qb24_layout, qb25_layout;
    RadioGroup RG_qb1, RG_status, RG_qb2, RG_qb3, RG_qb4, RG_qb5, RG_qb6, RG_qb7, RG_qb8, RG_qb9, RG_qb10, RG_qb11a, RG_qb11b, RG_qb12, RG_qb13, RG_qb14, RG_qb15, RG_qb16, RG_qb17, RG_qb18, RG_qb19, RG_qb20, RG_qb21, RG_qb22, RG_qb23, RG_qb24, RG_qb25;
    Button NextButton, BackButton, btn_RedialCall, btn_AddReportQuestionnaire, btnSelectEnumName;
    TextView txtFarmerID, txtEmpID, txtOrderID;

    EditText edt_b3_other, edt_b7_alternative_number, edt_b10_other, edt_b16_other, edt_b19_alternative_number, edt_b22_other;
    TextView tv_b7_time, tv_b7_date, tv_b13_time, tv_b13_date, tv_b19_time, tv_b19_date, tv_b25_time, tv_b25_date;

    TextView txtEnumName, txtEnumCode;

    String enum_name = "";
    String enum_code = "";
    String q1 = "";
    String status = "";
    String q2 = "";
    String q3 = "";
    String q3_other = "";
    String q4 = "";
    String q5 = "";
    String q6 = "";
    String q7 = "";
    String q7_hh = "";
    String q7_mm = "";
    String q7_day = "";
    String q7_month = "";
    String q7_date = "";
    String q7_time = "";
    String q7_alternative_number = "";
    String q8 = "";
    String q9 = "";
    String q10 = "";
    String q10_other = "";
    String q11a = "";
    String q11b = "";
    String q12 = "";
    String q13 = "";
    String q13_hh = "";
    String q13_mm = "";
    String q13_day = "";
    String q13_month = "";
    String q13_date = "";
    String q13_time = "";
    String q14 = "";
    String q15 = "";
    String q16 = "";
    String q16_other = "";
    String q17 = "";
    String q18 = "";
    String q19 = "";
    String q19_hh = "";
    String q19_mm = "";
    String q19_day = "";
    String q19_month = "";
    String q19_date = "";
    String q19_time = "";
    String q19_alternative_number = "";
    String q20 = "";
    String q21 = "";
    String q22 = "";
    String q22_other = "";
    String q23 = "";
    String q24 = "";
    String q25 = "";
    String q25_hh = "";
    String q25_mm = "";
    String q25_day = "";
    String q25_month = "";
    String q25_date = "";
    String q25_time = "";
    String b26 = "";
    String comments = "";
    ImageView btniBack;

    String fC_farmer_cellphone = "";
    String fC_alt_farmer_cellphone = "";
    String fC_farmer_no_alt = "";
    String fC_add_farmer_no = "";

    String altFarmerCellphone_ = "";

    TextView b4_txt, b5_txt, b6_txt, b7_txt, b11a_txt, b11b_txt, b12_txt, b13_txt, b14_txt, b17_txt, b18a_txt, b18b_txt, b18_fathername_txt, b19_txt, b19_fathername_txt, b23a_txt, b23b_txt, b24_txt, b25_txt;


    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_b_a_b);

        databaseAccess = new DatabaseAdapter(Q_sectionB_a_b.this);
        databaseAccess.Open();

        userName = getIntent().getStringExtra("username");
        isPendingCall = getIntent().getBooleanExtra("isPendingCall", false);

        region = getIntent().getStringExtra("region");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");

        FarmerID = getIntent().getStringExtra("farmerID");
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        ao_name = getIntent().getStringExtra("ao_name");
        fa_name = getIntent().getStringExtra("fa_name");
        ai_name = getIntent().getStringExtra("ai_name");


        id = getIntent().getStringExtra("id");
        isAlternateFarmer = getIntent().getBooleanExtra("isAlternateFarmer", false);
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");


        txtEnumName = findViewById(R.id.txt_enumName);
        txtEnumCode = findViewById(R.id.txt_enumCode);

        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);

        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);

        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

        ////////////Buttons iDs//////////
        NextButton = findViewById(R.id.btn_next);
        BackButton = findViewById(R.id.btn_back);
        btniBack = findViewById(R.id.btnBack);
        btn_RedialCall = findViewById(R.id.btn_RedialCall);
        btn_AddReportQuestionnaire = findViewById(R.id.btn_AddReportQuestionnaire);
        btnSelectEnumName = findViewById(R.id.btnSelectEnumName);


        ////////////Layout iDs//////////

        enumName_layout = findViewById(R.id.enumName_layout);
        qb1_layout = findViewById(R.id.qb1_layout);
        status_layout = findViewById(R.id.status_layout);
        qb2_layout = findViewById(R.id.b2_layout);
        qb3_layout = findViewById(R.id.b3_layout);
        qb4_layout = findViewById(R.id.b4_layout);
        qb5_layout = findViewById(R.id.b5_layout);
        qb6_layout = findViewById(R.id.b6_layout);
        qb7_layout = findViewById(R.id.b7_layout);
        qb8_layout = findViewById(R.id.b8_layout);
        qb9_layout = findViewById(R.id.b9_layout);
        qb10_layout = findViewById(R.id.b10_layout);
        qb11a_layout = findViewById(R.id.b11a_layout);
        qb11b_layout = findViewById(R.id.b11b_layout);
        qb12_layout = findViewById(R.id.b12_layout);
        qb13_layout = findViewById(R.id.b13_layout);
        qb14_layout = findViewById(R.id.b14_layout);
        qb15_layout = findViewById(R.id.b15_layout);
        qb16_layout = findViewById(R.id.b16_layout);
        qb17_layout = findViewById(R.id.b17_layout);
        qb18_layout = findViewById(R.id.b18_layout);
        qb19_layout = findViewById(R.id.b19_layout);
        qb20_layout = findViewById(R.id.b20_layout);
        qb21_layout = findViewById(R.id.b21_layout);
        qb22_layout = findViewById(R.id.b22_layout);
        qb23_layout = findViewById(R.id.b23_layout);
        qb24_layout = findViewById(R.id.b24_layout);
        qb25_layout = findViewById(R.id.b25_layout);
        qb26_layout = findViewById(R.id.qb26_layout);

        /////Radio Group IDs////////

        RG_qb26 = findViewById(R.id.rg_qb26);
        RG_qb1 = findViewById(R.id.rg_qb1);
        RG_status = findViewById(R.id.rg_status);
        RG_qb2 = findViewById(R.id.rg_qb2);
        RG_qb3 = findViewById(R.id.rg_qb3);
        RG_qb4 = findViewById(R.id.rg_qb4);
        RG_qb5 = findViewById(R.id.rg_qb5);
        RG_qb6 = findViewById(R.id.rg_qb6);
        RG_qb7 = findViewById(R.id.rg_qb7);
        RG_qb8 = findViewById(R.id.rg_qb8);
        RG_qb9 = findViewById(R.id.rg_qb9);
        RG_qb10 = findViewById(R.id.rg_qb10);
        RG_qb11a = findViewById(R.id.rg_qb11a);
        RG_qb11b = findViewById(R.id.rg_qb11b);
        RG_qb12 = findViewById(R.id.rg_qb12);
        RG_qb13 = findViewById(R.id.rg_qb13);
        RG_qb14 = findViewById(R.id.rg_qb14);
        RG_qb15 = findViewById(R.id.rg_qb15);
        RG_qb16 = findViewById(R.id.rg_qb16);
        RG_qb17 = findViewById(R.id.rg_qb17);
        RG_qb18 = findViewById(R.id.rg_qb18);
        RG_qb19 = findViewById(R.id.rg_qb19);
        RG_qb20 = findViewById(R.id.rg_qb20);
        RG_qb21 = findViewById(R.id.rg_qb21);
        RG_qb22 = findViewById(R.id.rg_qb22);
        RG_qb23 = findViewById(R.id.rg_qb23);
        RG_qb24 = findViewById(R.id.rg_qb24);
        RG_qb25 = findViewById(R.id.rg_qb25);

        edt_b3_other = findViewById(R.id.b3_other);
        edt_b16_other = findViewById(R.id.b16_other);
        edt_b22_other = findViewById(R.id.b22_other);
        edt_b10_other = findViewById(R.id.b10_other);

        edt_b7_alternative_number = findViewById(R.id.b7_alt_number);
        edt_b19_alternative_number = findViewById(R.id.b19_alt_number);

        tv_b7_time = findViewById(R.id.b7_time);
        tv_b7_date = findViewById(R.id.b7_date);

        tv_b13_time = findViewById(R.id.b13_time);
        tv_b13_date = findViewById(R.id.b13_date);

        tv_b19_time = findViewById(R.id.b19_time);
        tv_b19_date = findViewById(R.id.b19_date);

        tv_b25_time = findViewById(R.id.b25_time);
        tv_b25_date = findViewById(R.id.b25_date);

        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }

        readFromDataBase();
        LoadPreviousData();
        settextviews();

        readdateandtime();

//        createVisibilityListner()
        createVisibilityListner(RG_qb14);


        RG_qb7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ7_ID = RG_qb7.getCheckedRadioButtonId();
                if (RGroupQ7_ID == R.id.rbtn_b7_3) {
                    tv_b7_time.setVisibility(View.VISIBLE);
                    tv_b7_date.setVisibility(View.VISIBLE);
                } else {
                    tv_b7_time.setVisibility(View.GONE);
                    tv_b7_date.setVisibility(View.GONE);

                }
                if (RGroupQ7_ID == R.id.rbtn_b7_4) {
                    edt_b7_alternative_number.setVisibility(View.VISIBLE);
                } else {
                    edt_b7_alternative_number.setVisibility(View.GONE);
                }
            }
        });
        RG_qb3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ3_ID = RG_qb3.getCheckedRadioButtonId();
                if (RGroupQ3_ID == R.id.rbtn_b3_10) {
                    edt_b3_other.setVisibility(View.VISIBLE);
                } else {
                    edt_b3_other.setVisibility(View.GONE);
                    edt_b3_other.setText("");
                }
            }
        });
        RG_qb10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ10_ID = RG_qb10.getCheckedRadioButtonId();
                if (RGroupQ10_ID == R.id.rbtn_b10_10) {
                    edt_b10_other.setVisibility(View.VISIBLE);
                } else {
                    edt_b10_other.setVisibility(View.GONE);
                    edt_b10_other.setText("");
                }
            }
        });
        RG_qb16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ16_ID = RG_qb16.getCheckedRadioButtonId();
                if (RGroupQ16_ID == R.id.rbtn_b16_10) {
                    edt_b16_other.setVisibility(View.VISIBLE);
                } else {
                    edt_b16_other.setVisibility(View.GONE);
                    edt_b16_other.setText("");
                }
            }
        });
        RG_qb22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ22_ID = RG_qb22.getCheckedRadioButtonId();
                if (RGroupQ22_ID == R.id.rbtn_b22_10) {
                    edt_b22_other.setVisibility(View.VISIBLE);
                } else {
                    edt_b22_other.setVisibility(View.GONE);
                    edt_b22_other.setText("");
                }
            }
        });

        RG_qb13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ13_ID = RG_qb13.getCheckedRadioButtonId();
                if (RGroupQ13_ID == R.id.rbtn_b13_3) {
                    tv_b13_time.setVisibility(View.VISIBLE);
                    tv_b13_date.setVisibility(View.VISIBLE);
                } else {
                    tv_b13_time.setVisibility(View.GONE);
                    tv_b13_date.setVisibility(View.GONE);

                }
            }
        });
        RG_qb19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ19_ID = RG_qb19.getCheckedRadioButtonId();
                if (RGroupQ19_ID == R.id.rbtn_b19_3) {
                    tv_b19_time.setVisibility(View.VISIBLE);
                    tv_b19_date.setVisibility(View.VISIBLE);
                } else {
                    tv_b19_time.setVisibility(View.GONE);
                    tv_b19_date.setVisibility(View.GONE);

                }
                if (RGroupQ19_ID == R.id.rbtn_b19_4) {
                    edt_b19_alternative_number.setVisibility(View.VISIBLE);
                } else {
                    edt_b19_alternative_number.setVisibility(View.GONE);
                }
            }
        });

        RG_qb25.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupQ25_ID = RG_qb25.getCheckedRadioButtonId();
                if (RGroupQ25_ID == R.id.rbtn_b25_3) {
                    tv_b25_time.setVisibility(View.VISIBLE);
                    tv_b25_date.setVisibility(View.VISIBLE);
                } else {
                    tv_b25_time.setVisibility(View.GONE);
                    tv_b25_date.setVisibility(View.GONE);

                }
            }
        });

        tv_b7_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Q_sectionB_a_b.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_b7_time.setText(selectedHour + ":" + selectedMinute);
                        q7_hh = Integer.toString(selectedHour);
                        q7_mm = Integer.toString(selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        tv_b7_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Q_sectionB_a_b.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = month + "/" + dayOfMonth;
                        tv_b7_date.setText(date);
                        q7_month = Integer.toString(month);
                        q7_day = Integer.toString(dayOfMonth);
                    }
                }, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        tv_b13_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Q_sectionB_a_b.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_b13_time.setText(selectedHour + ":" + selectedMinute);
                        q13_hh = Integer.toString(selectedHour);
                        q13_mm = Integer.toString(selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        tv_b13_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Q_sectionB_a_b.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        tv_b13_date.setText(date);
                        q13_month = Integer.toString(month);
                        q13_day = Integer.toString(dayOfMonth);
                    }
                }, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        tv_b19_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Q_sectionB_a_b.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_b19_time.setText(selectedHour + ":" + selectedMinute);
                        q19_hh = Integer.toString(selectedHour);
                        q19_mm = Integer.toString(selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        tv_b19_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Q_sectionB_a_b.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        tv_b19_date.setText(date);
                        q19_month = Integer.toString(month);
                        q19_day = Integer.toString(dayOfMonth);
                    }
                }, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        tv_b25_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Q_sectionB_a_b.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tv_b25_time.setText(selectedHour + ":" + selectedMinute);
                        q25_hh = Integer.toString(selectedHour);
                        q25_mm = Integer.toString(selectedMinute);

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        tv_b25_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Q_sectionB_a_b.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = month + 1 + "/" + dayOfMonth + "/" + year;
                        tv_b25_date.setText(date);
                        q25_month = Integer.toString(month) + 1;
                        q25_day = Integer.toString(dayOfMonth);
                    }
                }, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        btnSelectEnumName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Q_sectionB_a_b.this, SelectEnumNameActivity.class);
                    startActivityForResult(intent, 99);
                } catch (Exception e) {
                    Log.e("TAG", "" + e.toString());
                }
            }
        });


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FarmerID = getIntent().getStringExtra("farmerID");

                //////////Get Radio Button IDs//////////
                int RGroupQ26_ID = RG_qb26.getCheckedRadioButtonId();

                int RGroupQ1_ID = RG_qb1.getCheckedRadioButtonId();
                int RGroupStatus_ID = RG_status.getCheckedRadioButtonId();
                int RGroupQ2_ID = RG_qb2.getCheckedRadioButtonId();
                int RGroupQ3_ID = RG_qb3.getCheckedRadioButtonId();
                int RGroupQ4_ID = RG_qb4.getCheckedRadioButtonId();
                int RGroupQ5_ID = RG_qb5.getCheckedRadioButtonId();
                int RGroupQ6_ID = RG_qb6.getCheckedRadioButtonId();
                int RGroupQ7_ID = RG_qb7.getCheckedRadioButtonId();
                int RGroupQ8_ID = RG_qb8.getCheckedRadioButtonId();
                int RGroupQ9_ID = RG_qb9.getCheckedRadioButtonId();
                int RGroupQ10_ID = RG_qb10.getCheckedRadioButtonId();
                int RGroupQ11a_ID = RG_qb11a.getCheckedRadioButtonId();
                int RGroupQ11b_ID = RG_qb11b.getCheckedRadioButtonId();
                int RGroupQ12_ID = RG_qb12.getCheckedRadioButtonId();
                int RGroupQ13_ID = RG_qb13.getCheckedRadioButtonId();
                int RGroupQ14_ID = RG_qb14.getCheckedRadioButtonId();
                int RGroupQ15_ID = RG_qb15.getCheckedRadioButtonId();
                int RGroupQ16_ID = RG_qb16.getCheckedRadioButtonId();
                int RGroupQ17_ID = RG_qb17.getCheckedRadioButtonId();
                int RGroupQ18_ID = RG_qb18.getCheckedRadioButtonId();
                int RGroupQ19_ID = RG_qb19.getCheckedRadioButtonId();
                int RGroupQ20_ID = RG_qb20.getCheckedRadioButtonId();
                int RGroupQ21_ID = RG_qb21.getCheckedRadioButtonId();
                int RGroupQ22_ID = RG_qb22.getCheckedRadioButtonId();
                int RGroupQ23_ID = RG_qb23.getCheckedRadioButtonId();
                int RGroupQ24_ID = RG_qb24.getCheckedRadioButtonId();
                int RGroupQ25_ID = RG_qb25.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(Q_sectionB_a_b.this);

                /////////Q Enum Name and Code ///////////////
                if (enumName_layout.getVisibility() == View.VISIBLE) {

                    enum_name = txtEnumName.getText().toString();
                    enum_code = txtEnumCode.getText().toString();
                    if (StringUtils.isEmpty(enum_name)) {
                        Toast.makeText(Q_sectionB_a_b.this, "Add Enum Name", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(enum_code)) {
                        Toast.makeText(Q_sectionB_a_b.this, "Add Enum Code", Toast.LENGTH_SHORT).show();
                    } else {

                        if (isAlternateFarmer == true) {
                            if (q25.equalsIgnoreCase("3")) {
                                qb17_layout.setVisibility(View.VISIBLE);
                                enumName_layout.setVisibility(View.GONE);
                            } else if (q19.equalsIgnoreCase("3")) {
                                qb17_layout.setVisibility(View.VISIBLE);
                                enumName_layout.setVisibility(View.GONE);
                            } else {
                                qb14_layout.setVisibility(View.VISIBLE);
                                enumName_layout.setVisibility(View.GONE);
                            }
                        } else {
                            if (q13.equalsIgnoreCase("3")) {
                                qb11a_layout.setVisibility(View.VISIBLE);
                                enumName_layout.setVisibility(View.GONE);
                            } else if (q7.equalsIgnoreCase("3")) {
                                qb4_layout.setVisibility(View.VISIBLE);
                                enumName_layout.setVisibility(View.GONE);
                            } else {
                                qb1_layout.setVisibility(View.VISIBLE);
                                enumName_layout.setVisibility(View.GONE);
                            }
                        }

                    }

                } else if (qb1_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ1_ID);
                    if (RGroupQ1_ID > 0) {
                        if (RGroupQ1_ID == R.id.rbtn_b1_2) {
                            q1 = radioButton.getTag().toString();
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else {
                            q1 = radioButton.getTag().toString();
                            qb1_layout.setVisibility(View.GONE);
                            qb2_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (status_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupStatus_ID);
                    if (RGroupStatus_ID > 0) {
                        try {
                            status = radioButton.getTag().toString();
                            status_layout.setVisibility(View.GONE);
                            qb2_layout.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb2_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ2_ID);
                    if (RGroupQ2_ID > 0) {
                        q2 = radioButton.getTag().toString();
                        if (RGroupQ2_ID == R.id.rbtn_b2_3) {
                            qb2_layout.setVisibility(View.GONE);
                            qb3_layout.setVisibility(View.VISIBLE);
                        } else {
                            qb2_layout.setVisibility(View.GONE);
                            q3 = "";
                            q3_other = "";
                            qb4_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb3_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ3_ID);
                    if (RGroupQ3_ID > 0) {
                        q3 = radioButton.getTag().toString();
                        if (RGroupQ3_ID == R.id.rbtn_b3_10) {
                            q3_other = edt_b3_other.getText().toString();
                            if (StringUtils.isEmpty(q3_other)) {
                                toastMessage("Please Enter Some Data");
                            } else {
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        } else {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb4_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ4_ID);
                    if (RGroupQ4_ID > 0) {
                        q4 = radioButton.getTag().toString();
                        if (RGroupQ4_ID == R.id.rbtn_b4_1) {
                            qb4_layout.setVisibility(View.GONE);
                            qb26_layout.setVisibility(View.VISIBLE);
                        } else {
                            qb4_layout.setVisibility(View.GONE);
                            qb5_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb5_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ5_ID);
                    if (RGroupQ5_ID > 0) {
                        q5 = radioButton.getTag().toString();
                        if (RGroupQ5_ID == R.id.rbtn_b5_2) {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else {
                            qb5_layout.setVisibility(View.GONE);
                            qb6_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb6_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ6_ID);
                    if (RGroupQ6_ID > 0) {
                        q6 = radioButton.getTag().toString();
                        qb6_layout.setVisibility(View.GONE);
                        qb7_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb7_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ7_ID);
                    if (RGroupQ7_ID > 0) {
                        q7 = radioButton.getTag().toString();
                        if (RGroupQ7_ID == R.id.rbtn_b7_1) {
                            qb7_layout.setVisibility(View.GONE);
                            qb26_layout.setVisibility(View.VISIBLE);
                        } else if (RGroupQ7_ID == R.id.rbtn_b7_2) {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else if (RGroupQ7_ID == R.id.rbtn_b7_3) {
                            q7_time = tv_b7_time.getText().toString();
                            q7_date = tv_b7_date.getText().toString();
                            if (StringUtils.isEmpty(q7_time) && StringUtils.isEmpty(q7_date)) {
                                toastMessage("Please Enter Required Data");
                            } else {
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        } else if (RGroupQ7_ID == R.id.rbtn_b7_4) {
                            q7_alternative_number = edt_b7_alternative_number.getText().toString();
                            if (StringUtils.isEmpty(q7_alternative_number)) {
                                toastMessage("Please Enter Phone Number");
                            } else if (q7_alternative_number.length() < 10 || q7_alternative_number.length() > 10) {
                                toastMessage("Invalid Phone Number");
                            } else {
                                if (q7_alternative_number.equalsIgnoreCase(fC_farmer_cellphone)
                                        || q7_alternative_number.equalsIgnoreCase(fC_alt_farmer_cellphone)
                                        || q7_alternative_number.equalsIgnoreCase(fC_farmer_no_alt)
                                        || q7_alternative_number.equalsIgnoreCase(q19_alternative_number)) {
                                    toastMessage("Number Already Exist");
                                } else {
                                    qb7_layout.setVisibility(View.GONE);
                                    toastMessage("Number updated " + q7_alternative_number);
                                    databaseAccess.UpdateNumber(q7_alternative_number, FarmerID, isAlternateFarmer);
                                    farmer_cellphone = q7_alternative_number;
                                    qb8_layout.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb8_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ8_ID);
                    if (RGroupQ8_ID > 0) {
                        q8 = radioButton.getTag().toString();
                        qb8_layout.setVisibility(View.GONE);
                        qb9_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb9_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ9_ID);
                    if (RGroupQ9_ID > 0) {
                        q9 = radioButton.getTag().toString();
                        if (RGroupQ9_ID == R.id.rbtn_b9_3) {
                            qb9_layout.setVisibility(View.GONE);
                            qb10_layout.setVisibility(View.VISIBLE);
                        } else {
                            qb9_layout.setVisibility(View.GONE);
                            qb11a_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb10_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ10_ID);
                    if (RGroupQ10_ID > 0) {
                        q10 = radioButton.getTag().toString();
                        if (RGroupQ10_ID == R.id.rbtn_b10_10) {
                            q10_other = edt_b10_other.getText().toString();
                            if (StringUtils.isEmpty(q10_other)) {
                                toastMessage("Please Enter Some Data");
                            } else {
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        } else {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb11a_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ11a_ID);
                    if (RGroupQ11a_ID > 0) {
                        q11a = radioButton.getTag().toString();
                        if (RGroupQ11a_ID == R.id.rbtn_b11a_1) {
                            qb11a_layout.setVisibility(View.GONE);
                            qb26_layout.setVisibility(View.VISIBLE);
                        } else {
                            qb11a_layout.setVisibility(View.GONE);
                            qb11b_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb11b_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ11b_ID);
                    if (RGroupQ11b_ID > 0) {
                        q11b = radioButton.getTag().toString();
                        if (RGroupQ11b_ID == R.id.rbtn_b11b_2) {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else {
                            qb11b_layout.setVisibility(View.GONE);
                            qb12_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb12_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ12_ID);
                    if (RGroupQ12_ID > 0) {
                        q12 = radioButton.getTag().toString();
                        qb12_layout.setVisibility(View.GONE);
                        qb13_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb13_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ13_ID);
                    if (RGroupQ13_ID > 0) {
                        q13 = radioButton.getTag().toString();
                        if (RGroupQ13_ID == R.id.rbtn_b13_1) {
                            qb13_layout.setVisibility(View.GONE);
                            qb26_layout.setVisibility(View.VISIBLE);
                        } else if (RGroupQ13_ID == R.id.rbtn_b13_2) {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else if (RGroupQ13_ID == R.id.rbtn_b13_3) {
                            q13_time = tv_b13_time.getText().toString();
                            q13_date = tv_b13_date.getText().toString();
                            if (StringUtils.isEmpty(q13_time) && StringUtils.isEmpty(q13_date)) {
                                toastMessage("Please Enter Required Data");
                            } else {
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb14_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ14_ID);
                    if (RGroupQ14_ID > 0) {
                        q14 = radioButton.getTag().toString();
                        if (RGroupQ14_ID == R.id.rbtn_b14_2) {
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else {
                            if (!StringUtils.isEmpty(altFarmerCellphone_)) {
                                qb14_layout.setVisibility(View.GONE);
                                qb15_layout.setVisibility(View.VISIBLE);
                            } else {
                                toastMessage("Number Not Found Please Select Second Option");
                            }
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb15_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ15_ID);
                    if (RGroupQ15_ID > 0) {
                        q15 = radioButton.getTag().toString();
                        if (RGroupQ15_ID == R.id.rbtn_b15_3) {
                            qb15_layout.setVisibility(View.GONE);
                            qb16_layout.setVisibility(View.VISIBLE);
                        } else {
                            q16 = "";
                            qb15_layout.setVisibility(View.GONE);
                            qb17_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb16_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ16_ID);
                    if (RGroupQ16_ID > 0) {
                        q16 = radioButton.getTag().toString();
                        if (RGroupQ16_ID == R.id.rbtn_b16_10) {
                            q16_other = edt_b16_other.getText().toString();
                            if (StringUtils.isEmpty(q16_other)) {
                                toastMessage("Please Enter Some Data");
                            } else {
                                q17 = "";
                                q18 = "";
                                q19 = "";
                                q19_hh = "";
                                q19_mm = "";
                                q19_day = "";
                                q19_month = "";
                                q19_alternative_number = "";
                                q20 = "";
                                q21 = "";
                                q22 = "";
                                q22_other = "";
                                q23 = "";
                                q24 = "";
                                q25 = "";
                                q25_hh = "";
                                q25_mm = "";
                                q25_day = "";
                                q25_month = "";
                                b26 = "";
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        } else {
                            q16_other = "";
                            q17 = "";
                            q18 = "";
                            q19 = "";
                            q19_hh = "";
                            q19_mm = "";
                            q19_day = "";
                            q19_month = "";
                            q19_alternative_number = "";
                            q20 = "";
                            q21 = "";
                            q22 = "";
                            q22_other = "";
                            q23 = "";
                            q24 = "";
                            q25 = "";
                            q25_hh = "";
                            q25_mm = "";
                            q25_day = "";
                            q25_month = "";
                            b26 = "";
                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb17_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ17_ID);
                    if (RGroupQ17_ID > 0) {
                        q17 = radioButton.getTag().toString();
                        qb17_layout.setVisibility(View.GONE);
                        qb18_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb18_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ18_ID);
                    if (RGroupQ18_ID > 0) {
                        q18 = radioButton.getTag().toString();
                        if (RGroupQ18_ID == R.id.rbtn_b18_2) {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else {
                            qb18_layout.setVisibility(View.GONE);
                            qb19_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb19_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ19_ID);
                    if (RGroupQ19_ID > 0) {
                        q19 = radioButton.getTag().toString();
                        if (RGroupQ19_ID == R.id.rbtn_b19_1) {
                            qb19_layout.setVisibility(View.GONE);
                            qb26_layout.setVisibility(View.VISIBLE);
                        } else if (RGroupQ19_ID == R.id.rbtn_b19_2) {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else if (RGroupQ19_ID == R.id.rbtn_b19_3) {
                            q19_time = tv_b19_time.getText().toString();
                            q19_date = tv_b19_date.getText().toString();
                            if (StringUtils.isEmpty(q19_time) && StringUtils.isEmpty(q19_date)) {
                                toastMessage("Please Enter Required Data");
                            } else {
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        } else if (RGroupQ19_ID == R.id.rbtn_b19_4) {
                            q19_alternative_number = edt_b19_alternative_number.getText().toString();
                            if (StringUtils.isEmpty(q19_alternative_number)) {
                                toastMessage("Please Enter Phone Number");
                            } else if (q19_alternative_number.length() < 10 || q19_alternative_number.length() > 10) {
                                toastMessage("Invalid Phone Number");
                            } else {
                                if (q19_alternative_number.equalsIgnoreCase(fC_farmer_cellphone)
                                        || q19_alternative_number.equalsIgnoreCase(fC_alt_farmer_cellphone)
                                        || q19_alternative_number.equalsIgnoreCase(q7_alternative_number)
                                        || q19_alternative_number.equalsIgnoreCase(fC_add_farmer_no)) {
                                    toastMessage("Number Already Exist");
                                } else {
                                    qb19_layout.setVisibility(View.GONE);

                                    toastMessage(" Phone Number Updated" + q19_alternative_number);
                                    databaseAccess.UpdateNumber(q19_alternative_number, FarmerID, isAlternateFarmer);

                                    farmer_cellphone = q19_alternative_number;
                                    qb20_layout.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb20_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ20_ID);
                    if (RGroupQ20_ID > 0) {
                        q20 = radioButton.getTag().toString();
                        if (RGroupQ20_ID == R.id.rbtn_b20_1) {
                            qb20_layout.setVisibility(View.GONE);
                            qb21_layout.setVisibility(View.VISIBLE);
                        } else {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb21_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ21_ID);
                    if (RGroupQ21_ID > 0) {
                        q21 = radioButton.getTag().toString();
                        if (RGroupQ21_ID == R.id.rbtn_b21_3) {
                            qb21_layout.setVisibility(View.GONE);
                            qb22_layout.setVisibility(View.VISIBLE);
                        } else {
                            q22 = "";
                            qb21_layout.setVisibility(View.GONE);
                            qb23_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb22_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ22_ID);
                    if (RGroupQ22_ID > 0) {
                        q22 = radioButton.getTag().toString();
                        if (RGroupQ22_ID == R.id.rbtn_b22_10) {
                            q22_other = edt_b22_other.getText().toString();
                            if (StringUtils.isEmpty(q22_other)) {
                                toastMessage("Please Enter Some Data");
                            } else {

                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        } else {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb23_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ23_ID);
                    if (RGroupQ23_ID > 0) {
                        q23 = radioButton.getTag().toString();
                        if (RGroupQ23_ID == R.id.rbtn_b23_1) {
                            qb23_layout.setVisibility(View.GONE);
                            qb24_layout.setVisibility(View.VISIBLE);
                        } else {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb24_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ24_ID);
                    if (RGroupQ24_ID > 0) {
                        q24 = radioButton.getTag().toString();
                        qb24_layout.setVisibility(View.GONE);
                        qb25_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb25_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(RGroupQ25_ID);
                    if (RGroupQ25_ID > 0) {
                        q25 = radioButton.getTag().toString();
                        if (RGroupQ25_ID == R.id.rbtn_b25_1) {
                            qb25_layout.setVisibility(View.GONE);
                            qb26_layout.setVisibility(View.VISIBLE);
                        } else if (RGroupQ25_ID == R.id.rbtn_b25_2) {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);
                            intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                            intent.putExtra("isFromCallActivity", false);
                            intent.putExtra("isFromEdit", isFromEdit);
                            startActivityForResult(intent, 88);
                        } else if (RGroupQ25_ID == R.id.rbtn_b25_3) {
                            q25_time = tv_b25_time.getText().toString();
                            q25_date = tv_b25_date.getText().toString();
                            if (StringUtils.isEmpty(q25_time) && StringUtils.isEmpty(q25_date)) {
                                toastMessage("Please Enter Required Data");
                            } else {
                                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", farmer_cellphone);
                                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                                intent.putExtra("isFromCallActivity", false);
                                intent.putExtra("isFromEdit", isFromEdit);
                                startActivityForResult(intent, 88);
                            }
                        }
                    } else {
                        toastMessage("Please Select Some Options");
                    }
                } else if (qb26_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(RGroupQ26_ID);
                    if (q16.isEmpty() && q22.isEmpty()) {

                        if (RGroupQ26_ID == R.id.rbtn_b26_1 || RGroupQ26_ID == R.id.rbtn_b26_3) {
                            b26 = radioButton.getTag().toString();
                            Intent intent = new Intent(Q_sectionB_a_b.this, Q_sectionC.class);

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
                            intent.putExtra("b26", b26);
                            intent.putExtra("id", id);
                            intent.putExtra("isFromEdit", isFromEdit);
                            intent.putExtra("farmer_cellphone", farmer_cellphone);

                            startActivityForResult(intent, 88);

                        } else if (RGroupQ26_ID == R.id.rbtn_b26_2) {

                            Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);

                            intent.putExtra("isPendingCall", isPendingCall);
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
                            b26 = radioButton.getTag().toString();
                            startActivityForResult(intent, 88);

                        } else {
                            Toast.makeText(Q_sectionB_a_b.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Q_sectionB_a_b.this, "If 16 or b22 is fill than questionnaire could not be filled", Toast.LENGTH_SHORT).show();
                    }
                }

                SaveData();
            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_sectionB_a_b.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "sectionB");
                startActivityForResult(intent, 88);
                //finishAffinity();
                //  finish();
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
            public void onClick(View v) {
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
                        Toast.makeText(Q_sectionB_a_b.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }


    @Override
    public void onBackPressed() {

        //////////Get Radio Button IDs//////////
        int RGroupQ26_ID = RG_qb26.getCheckedRadioButtonId();

        int RGroupQ1_ID = RG_qb1.getCheckedRadioButtonId();
        int RGroupStatus_ID = RG_status.getCheckedRadioButtonId();
        int RGroupQ2_ID = RG_qb2.getCheckedRadioButtonId();
        int RGroupQ3_ID = RG_qb3.getCheckedRadioButtonId();
        int RGroupQ4_ID = RG_qb4.getCheckedRadioButtonId();
        int RGroupQ5_ID = RG_qb5.getCheckedRadioButtonId();
        int RGroupQ6_ID = RG_qb6.getCheckedRadioButtonId();
        int RGroupQ7_ID = RG_qb7.getCheckedRadioButtonId();
        int RGroupQ8_ID = RG_qb8.getCheckedRadioButtonId();
        int RGroupQ9_ID = RG_qb9.getCheckedRadioButtonId();
        int RGroupQ10_ID = RG_qb10.getCheckedRadioButtonId();
        int RGroupQ11a_ID = RG_qb11a.getCheckedRadioButtonId();
        int RGroupQ11b_ID = RG_qb11b.getCheckedRadioButtonId();
        int RGroupQ12_ID = RG_qb12.getCheckedRadioButtonId();
        int RGroupQ13_ID = RG_qb13.getCheckedRadioButtonId();
        int RGroupQ14_ID = RG_qb14.getCheckedRadioButtonId();
        int RGroupQ15_ID = RG_qb15.getCheckedRadioButtonId();
        int RGroupQ16_ID = RG_qb16.getCheckedRadioButtonId();
        int RGroupQ17_ID = RG_qb17.getCheckedRadioButtonId();
        int RGroupQ18_ID = RG_qb18.getCheckedRadioButtonId();
        int RGroupQ19_ID = RG_qb19.getCheckedRadioButtonId();
        int RGroupQ20_ID = RG_qb20.getCheckedRadioButtonId();
        int RGroupQ21_ID = RG_qb21.getCheckedRadioButtonId();
        int RGroupQ22_ID = RG_qb22.getCheckedRadioButtonId();
        int RGroupQ23_ID = RG_qb23.getCheckedRadioButtonId();
        int RGroupQ24_ID = RG_qb24.getCheckedRadioButtonId();
        int RGroupQ25_ID = RG_qb25.getCheckedRadioButtonId();


        if (qb26_layout.getVisibility() == View.VISIBLE) {
            if (RGroupQ19_ID == R.id.rbtn_b19_1) {
                qb26_layout.setVisibility(View.GONE);
                qb19_layout.setVisibility(View.VISIBLE);
            } else if (RGroupQ13_ID == R.id.rbtn_b13_1) {
                qb26_layout.setVisibility(View.GONE);
                qb13_layout.setVisibility(View.VISIBLE);

            } else if (RGroupQ11a_ID == R.id.rbtn_b11a_1) {
                qb26_layout.setVisibility(View.GONE);
                qb11a_layout.setVisibility(View.VISIBLE);

            } else if (RGroupQ7_ID == R.id.rbtn_b7_1) {
                qb26_layout.setVisibility(View.GONE);
                qb7_layout.setVisibility(View.VISIBLE);
            } else if (RGroupQ4_ID == R.id.rbtn_b4_1) {
                qb26_layout.setVisibility(View.GONE);
                qb4_layout.setVisibility(View.VISIBLE);
            } else {
                qb26_layout.setVisibility(View.GONE);
                qb25_layout.setVisibility(View.VISIBLE);
            }
        } else if (qb25_layout.getVisibility() == View.VISIBLE) {
            qb25_layout.setVisibility(View.GONE);
            qb24_layout.setVisibility(View.VISIBLE);
        } else if (qb24_layout.getVisibility() == View.VISIBLE) {
            qb24_layout.setVisibility(View.GONE);
            qb23_layout.setVisibility(View.VISIBLE);
        } else if (qb23_layout.getVisibility() == View.VISIBLE) {
            if (RGroupQ21_ID == R.id.rbtn_b21_1 || RGroupQ21_ID == R.id.rbtn_b21_2) {
                qb23_layout.setVisibility(View.GONE);
                qb21_layout.setVisibility(View.VISIBLE);
            } else {
                qb23_layout.setVisibility(View.GONE);
                qb22_layout.setVisibility(View.VISIBLE);
            }
        } else if (qb22_layout.getVisibility() == View.VISIBLE) {
            qb22_layout.setVisibility(View.GONE);
            qb21_layout.setVisibility(View.VISIBLE);
        } else if (qb21_layout.getVisibility() == View.VISIBLE) {
            qb21_layout.setVisibility(View.GONE);
            qb20_layout.setVisibility(View.VISIBLE);
        } else if (qb20_layout.getVisibility() == View.VISIBLE) {
            qb20_layout.setVisibility(View.GONE);
            qb19_layout.setVisibility(View.VISIBLE);
        } else if (qb19_layout.getVisibility() == View.VISIBLE) {
            qb19_layout.setVisibility(View.GONE);
            qb18_layout.setVisibility(View.VISIBLE);
        } else if (qb18_layout.getVisibility() == View.VISIBLE) {
            qb18_layout.setVisibility(View.GONE);
            qb17_layout.setVisibility(View.VISIBLE);
        } else if (qb17_layout.getVisibility() == View.VISIBLE) {
            if (RGroupQ15_ID == R.id.rbtn_b15_3) {
                qb17_layout.setVisibility(View.GONE);
                qb16_layout.setVisibility(View.VISIBLE);
            } else {
                qb17_layout.setVisibility(View.GONE);
                qb15_layout.setVisibility(View.VISIBLE);
            }
        } else if (qb16_layout.getVisibility() == View.VISIBLE) {
            qb16_layout.setVisibility(View.GONE);
            qb15_layout.setVisibility(View.VISIBLE);
        } else if (qb15_layout.getVisibility() == View.VISIBLE) {
            qb15_layout.setVisibility(View.GONE);
            qb14_layout.setVisibility(View.VISIBLE);
            MubLog.cpnsoleLog("armer_cellphone = altFarmerCellphone_");
            farmer_cellphone = altFarmerCellphone_;
        } else if (qb14_layout.getVisibility() == View.VISIBLE) {
            if (isAlternateFarmer == true) {
                enumName_layout.setVisibility(View.VISIBLE);
                qb14_layout.setVisibility(View.GONE);
            } else if (RGroupQ3_ID > 0) {
                qb14_layout.setVisibility(View.GONE);
                qb3_layout.setVisibility(View.VISIBLE);
            } else if ((RGroupQ5_ID == R.id.rbtn_b5_2)) {
                qb14_layout.setVisibility(View.GONE);
                qb5_layout.setVisibility(View.VISIBLE);

            } else if (RGroupQ7_ID == R.id.rbtn_b7_2) {
                qb14_layout.setVisibility(View.GONE);
                qb7_layout.setVisibility(View.VISIBLE);

            } else if (RGroupQ10_ID > 0) {
                qb14_layout.setVisibility(View.GONE);
                qb10_layout.setVisibility(View.VISIBLE);
            } else {
                qb14_layout.setVisibility(View.GONE);
                qb11b_layout.setVisibility(View.VISIBLE);

            }
        } else if (qb13_layout.getVisibility() == View.VISIBLE) {
            qb13_layout.setVisibility(View.GONE);
            qb12_layout.setVisibility(View.VISIBLE);
        } else if (qb12_layout.getVisibility() == View.VISIBLE) {
            qb12_layout.setVisibility(View.GONE);
            qb11b_layout.setVisibility(View.VISIBLE);
        } else if (qb11b_layout.getVisibility() == View.VISIBLE) {
            qb11b_layout.setVisibility(View.GONE);
            qb11a_layout.setVisibility(View.VISIBLE);
        } else if (qb11a_layout.getVisibility() == View.VISIBLE) {
            qb11a_layout.setVisibility(View.GONE);
            qb9_layout.setVisibility(View.VISIBLE);
        } else if (qb10_layout.getVisibility() == View.VISIBLE) {
            qb10_layout.setVisibility(View.GONE);
            qb9_layout.setVisibility(View.VISIBLE);
        } else if (qb9_layout.getVisibility() == View.VISIBLE) {
            qb9_layout.setVisibility(View.GONE);
            qb8_layout.setVisibility(View.VISIBLE);
        } else if (qb8_layout.getVisibility() == View.VISIBLE) {
            qb8_layout.setVisibility(View.GONE);
            qb7_layout.setVisibility(View.VISIBLE);
        } else if (qb7_layout.getVisibility() == View.VISIBLE) {
            qb7_layout.setVisibility(View.GONE);
            qb6_layout.setVisibility(View.VISIBLE);
        } else if (qb6_layout.getVisibility() == View.VISIBLE) {
            qb6_layout.setVisibility(View.GONE);
            qb5_layout.setVisibility(View.VISIBLE);
        } else if (qb5_layout.getVisibility() == View.VISIBLE) {
            qb5_layout.setVisibility(View.GONE);
            qb4_layout.setVisibility(View.VISIBLE);
        } else if (qb4_layout.getVisibility() == View.VISIBLE) {
            if (RGroupQ2_ID == R.id.rbtn_b2_3) {
                qb4_layout.setVisibility(View.GONE);
                qb3_layout.setVisibility(View.VISIBLE);
            } else {
                qb4_layout.setVisibility(View.GONE);
                qb2_layout.setVisibility(View.VISIBLE);
            }
        } else if (qb3_layout.getVisibility() == View.VISIBLE) {
            qb3_layout.setVisibility(View.GONE);
            qb2_layout.setVisibility(View.VISIBLE);
        } else if (qb2_layout.getVisibility() == View.VISIBLE) {
            qb2_layout.setVisibility(View.GONE);
            qb1_layout.setVisibility(View.VISIBLE);
        } else if (status_layout.getVisibility() == View.VISIBLE) {
            status_layout.setVisibility(View.GONE);
            qb1_layout.setVisibility(View.VISIBLE);
        } else if (qb1_layout.getVisibility() == View.VISIBLE) {
            qb1_layout.setVisibility(View.GONE);
            enumName_layout.setVisibility(View.VISIBLE);
        } else if (enumName_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!StringUtils.isEmpty(farmer_cellphone)) {
                    DialUserNumber(farmer_cellphone);
                } else {
                    Toast.makeText(Q_sectionB_a_b.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
                // ShowPermissionDialog("Allow this permission to copy images to your phone memory");
            }
        }
    }

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Q_sectionB_a_b.this,
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


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 88) {
//            boolean isDataUpdated = data.getBooleanExtra("isDataUpdated", false);
//            if (isDataUpdated) {
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("isDataUpdated", isDataUpdated);
//                setResult(Activity.RESULT_OK, returnIntent);
//                finish();
//            }
//
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        MubLog.cpnsoleLog("onActivityResult resultCode" + resultCode);
        MubLog.cpnsoleLog("onActivityResult requestCode " + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                enum_code = data.getStringExtra("enumCode");
                enum_name = data.getStringExtra("enumName");

                txtEnumName.setText(enum_name);
                txtEnumCode.setText(enum_code);


            } else if (requestCode == 88) {
                MubLog.cpnsoleLog("inside requestCode");
                boolean isDataUpdated = data.getBooleanExtra("isDataUpdated", false);
                MubLog.cpnsoleLog("inside isDataUpdated " + isDataUpdated);
                if (isDataUpdated) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("isDataUpdated", isDataUpdated);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                    MubLog.cpnsoleLog("finish called ");
                }

            }
        }


    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    void SaveData() {
        try {


            createNewColumnsBeforeInsertion(DatabaseAdapter.SectionBTable);




            patchesBeforUpdate();


//            patch_for_question14();


            databaseAccess.saveSection_B_Data(emp_id, order_id, FarmerID, enum_code, enum_name, q1, status, q2, q3, q3_other, q4, q5, q6, q7, q7_hh, q7_mm, q7_day, q7_month, q7_alternative_number
                    , q8, q9, q10, q10_other, q11a, q11b, q12, q13, q13_hh, q13_mm, q13_day, q13_month, q14, q15, q16, q16_other, q17, q18, q19, q19_hh, q19_mm, q19_day, q19_month, q19_alternative_number
                    , q20, q21, q22, q22_other, q23, q24, q25, q25_hh, q25_mm, q25_day, q25_month, b26, comments);


            MubLog.cpnsoleLog("inside SaveData FarmerID " + FarmerID + " SectionBTable  =  " + DatabaseAdapter.SectionBTable);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID, DatabaseAdapter.SectionBTable);

            CheckLogging();


//            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionBTable);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void patch_for_question14() {

        try {

            boolean  empty_or_same = false;
            Cursor cursor = databaseAccess.getBasicInfo(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                fC_farmer_cellphone = cursor.getString(cursor.getColumnIndex("farmer_cellphone"));
                altFarmerCellphone_ = cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone"));

                MubLog.cpnsoleLog("inside patch_for_question14 altFarmerCellphone_ "+altFarmerCellphone_);
                MubLog.cpnsoleLog("inside patch_for_question14 fC_farmer_cellphone "+fC_farmer_cellphone);

                if (altFarmerCellphone_.equalsIgnoreCase(fC_farmer_cellphone) || altFarmerCellphone_.equalsIgnoreCase("")) {
                    MubLog.cpnsoleLog("inside patch_for_question14 fC_farmer_cellphone is equal to  altFarmerCellphone_");
                    empty_or_same = true;
                 }
              }

            if(empty_or_same) {

                if (!q3.equalsIgnoreCase("")) {
                       q14="3";
                }

                if (q5.equalsIgnoreCase("2")) {
                    q14="3";
                }

                if (q7.equalsIgnoreCase("2")) {
                    q14="3";
                }

                if (!q10.equalsIgnoreCase("")) {
                    q14="3";
                }

                if (q11b.equalsIgnoreCase("2")) {
                    q14="3";
                }


                if (q13.equalsIgnoreCase("2")) {
                    q14="3";
                }


                update_Section_b_14_number();

            }

        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[Q_sectionB_a_b] inside patch_for_question14() Exception is :"+e.toString());
        }


    }

    private void patchesBeforUpdate() {

        MubLog.cpnsoleLog("inside patchesBeforUpdate");
        try {

            //if q1=2 reset remaining all
            if (q1.equalsIgnoreCase("2")) {
                status = "";
                q2 = "";
                q3 = "";
                q3_other = "";
                q4 = "";
                q5 = "";
                q6 = "";
                q7 = "";
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";
                q7_alternative_number
                        = "";
                q8 = "";
                q9 = "";
                q10 = "";
                q10_other = "";
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
                q14 = "";
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";
            }


            //if q2=1,2 reset remaining all
            if (q2.equalsIgnoreCase("1") || q2.equalsIgnoreCase("2")) {
                status = "";
                q3 = "";
                q3_other = "";
            }


            //if q2=3 reset remaining all
            if (q2.equalsIgnoreCase("3")) {
                status = "";
                q4 = "";
                q5 = "";
                q6 = "";
                q7 = "";
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";
                q7_alternative_number = "";
                q8 = "";
                q9 = "";
                q10 = "";
                q10_other = "";
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";

            }


            //if q3=2,8,9 reset q3_other
            if (q3.equalsIgnoreCase("2") || q3.equalsIgnoreCase("8") || q3.equalsIgnoreCase("9")) {
                q3_other = "";
            }

            //if q4=1,reset all before 26  (b26 isn't include )
            if (q4.equalsIgnoreCase("1")) {
                status = "";
                q5 = "";
                q6 = "";
                q7 = "";
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";
                q7_alternative_number = "";
                q8 = "";
                q9 = "";
                q10 = "";
                q10_other = "";
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
                q14 = "";
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }


            //if q5=2,reset all before 14 (b14 isn't include )
            if (q5.equalsIgnoreCase("2")) {
                status = "";
                q6 = "";
                q7 = "";
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";
                q7_alternative_number = "";
                q8 = "";
                q9 = "";
                q10 = "";
                q10_other = "";
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";

            }


            //if q7=1,reset all before 26 (b26 isn't include )
            if (q7.equalsIgnoreCase("1")) {
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";
                q7_alternative_number = "";
                q8 = "";
                q9 = "";
                q10 = "";
                q10_other = "";
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
                q14 = "";
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }

            //if q7=2,reset all before 14 (b14 isn't include )
            if (q7.equalsIgnoreCase("2")) {
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";
                q7_alternative_number = "";
                q8 = "";
                q9 = "";
                q10 = "";
                q10_other = "";
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";

            }

            //if q7=3,reset q7_alternative_number
            if (q7.equalsIgnoreCase("3")) {
                q7_alternative_number = "";

            }

            //if q7=4,reset
            if (q7.equalsIgnoreCase("4")) {
                q7_hh = "";
                q7_mm = "";
                q7_day = "";
                q7_month = "";

            }


            //if q9=1,2,reset
            if (q9.equalsIgnoreCase("1") || q9.equalsIgnoreCase("2")) {
                q10 = "";
                q10_other = "";

            }

            //if q9=3,reset (b14 isn't include )
            if (q9.equalsIgnoreCase("3")) {
                q11a = "";
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
            }


            //if q10=2,8,9 reset q10_other
            if (q10.equalsIgnoreCase("2") || q10.equalsIgnoreCase("8") || q10.equalsIgnoreCase("9")) {
                q10_other = "";

            }


            //if q11a=1 (b26 isn't include )
            if (q11a.equalsIgnoreCase("1")) {
                q11b = "";
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
                q14 = "";
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }

            //if q11b=2 (b14 isn't include )
            if (q11b.equalsIgnoreCase("2")) {
                q12 = "";
                q13 = "";
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
            }


            //if q13=1 (b26 isn't include )
            if (q13.equalsIgnoreCase("1")) {
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
                q14 = "";
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }


            //if q13=2 (b26 isn't include )
            if (q13.equalsIgnoreCase("2")) {
                q13_hh = "";
                q13_mm = "";
                q13_day = "";
                q13_month = "";
                q14 = "";
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }


            //if q14=2  (b26 is  include )
            if (q14.equalsIgnoreCase("2")) {
                q15 = "";
                q16 = "";
                q16_other = "";
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";

            }


            //if q15=2,1
            if (q15.equalsIgnoreCase("1") || q15.equalsIgnoreCase("2")) {
                q16 = "";
                q16_other = "";
            }

            //if q15=3 (b26 is  include )
            if (q15.equalsIgnoreCase("3")) {
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";

            }


            //if q16 !=""
            if (!q16.equalsIgnoreCase("")) {
                q17 = "";
                q18 = "";
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number
                        = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";

            }


            //if q16 =2,8,9
            if (q16.equalsIgnoreCase("2") || q16.equalsIgnoreCase("8") || q16.equalsIgnoreCase("9")) {
                q16_other = "";
            }

            //if q18 =2
            if (q18.equalsIgnoreCase("2")) {
                q19 = "";
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";

            }


            //if q19 =1 (b26 isn't include )
            if (q19.equalsIgnoreCase("1")) {
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number
                        = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }

            //if q19 =2
            if (q19.equalsIgnoreCase("2")) {
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";
                q19_alternative_number = "";
                q20 = "";
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";

            }

            //if q19 =3
            if (q19.equalsIgnoreCase("3")) {
                q19_alternative_number = "";
            }

            //if q19 =4
            if (q19.equalsIgnoreCase("4")) {
                q19_hh = "";
                q19_mm = "";
                q19_day = "";
                q19_month = "";

            }

            //if q20 =2 (b26 is include )
            if (q20.equalsIgnoreCase("2")) {
                q21 = "";
                q22 = "";
                q22_other = "";
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";
            }


            //if q21 =1,2
            if (q21.equalsIgnoreCase("1") || q21.equalsIgnoreCase("2")) {
                q22 = "";
                q22_other = "";

            }
            //if q22 !="" (b26 is include )
            if (!q22.equalsIgnoreCase("")) {
                q23 = "";
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";
            }


            //if q23 = 2 (b26 is include )
            if (q23.equalsIgnoreCase("2")) {
                q24 = "";
                q25 = "";
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";
            }

            //if q25 = 1
            if (q25.equalsIgnoreCase("1")) {
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";

            }

            //if q25 = 2 (b26 is include )
            if (q25.equalsIgnoreCase("2")) {
                q25_hh = "";
                q25_mm = "";
                q25_day = "";
                q25_month = "";
                b26 = "";

            }

//            //if q25 = 3
//            if (q25.equalsIgnoreCase("1")) {
//                q25_hh = "";
//                q25_mm = "";
//                q25_day = "";
//                q25_month = "";
//
//            }


        } catch (Exception ex) {


        }
    }


    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {
            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY " + tableName);


            String patch_querry = "UPDATE `" + tableName + "` SET `b3` ='9'  WHERE `b3` ='3'";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `b16` ='9'  WHERE `b16` ='3' ";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `b16` ='2' WHERE b16 = '1'";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `b4`='',`b5`='',`b6`='',`b7`='',`b7_hh`='',`b7_mm`='',`b7_day`='',`b7_month`='',`b7_alternative_number`='',`b8`='',`b9`='',`b10`='',`b10_other`='',`b11a`='',`b11b`='',`b12`='',`b13`='',`b13_hh`='',`b13_mm`='',`b13_day`='',`b13__month`='' WHERE b3 != ''";

            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE `" + tableName + "` SET `b5`='' ,`b6`='' ,`b7`='' ,`b7_hh`='' ,`b7_mm`='' ,`b7_day`='' ,`b7_month`='' ,`b7_alternative_number`='' ,`b8`='' ,`b9`='' ,`b10`='' ,`b10_other`='' ,`b11a`='' ,`b11b`='' ,`b12`='' ,`b13`='' ,`b13_hh`='' ,`b13_mm`='' ,`b13_day`='' ,`b13__month`='' ,`b14`='' ,`b15`='' ,`b16`='' ,`b16_other`='' ,`b17`='' ,`b18`='' ,`b19`='' ,`b19_hh`='' ,`b19_mm`='' ,`b19_day`='' ,`b19_month`='' ,`b19_alternative_number`='' ,`b20`='' ,`b21`='' ,`b22`='' ,`b22_other`='' ,`b23`='' ,`b24`='' ,`b25`='' ,`b25_hh`='' ,`b25_mm`='' ,`b25_day`='' ,`b25_month`='' WHERE b4 = '1'  AND ( `b5`!='' OR`b6`!='' OR`b7`!='' OR`b7_hh`!='' OR`b7_mm`!='' OR`b7_day`!='' OR`b7_month`!='' OR`b7_alternative_number`!='' OR`b8`!='' OR`b9`!='' OR`b10`!='' OR`b10_other`!='' OR`b11a`!='' OR`b11b`!='' OR`b12`!='' OR`b13`!='' OR`b13_hh`!='' OR`b13_mm`!='' OR`b13_day`!='' OR`b13__month`!='' OR`b14`!='' OR`b15`!='' OR`b16`!='' OR`b16_other`!='' OR`b17`!='' OR`b18`!='' OR`b19`!='' OR`b19_hh`!='' OR`b19_mm`!='' OR`b19_day`!='' OR`b19_month`!='' OR`b19_alternative_number`!='' OR`b20`!='' OR`b21`!='' OR`b22`!='' OR`b22_other`!='' OR`b23`!='' OR`b24`!='' OR`b25`!='' OR`b25_hh`!='' OR`b25_mm`!='' OR`b25_day`!='' OR`b25_month`!='')";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry = "UPDATE  " + tableName + " SET b16='' WHERE b15 IN (1,2) AND b16 !=''";
            databaseAccess.execute_patch_query(patch_querry);

            patch_querry = "UPDATE `" + tableName + "` SET `b17`='',`b18`='',`b19`='',`b19_hh`='',`b19_mm`='',`b19_day`='',`b19_month`='',`b19_alternative_number`='',`b20`='',`b21`='',`b22`='',`b22_other`='',`b23`='',`b24`='',`b25`='',`b25_hh`='',`b25_mm`='',`b25_day`='',`b25_month`='',`b26`='' WHERE b16 != ''";
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

            Cursor cursor = databaseAccess.getSectionBData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                enum_code = cursor.getString(cursor.getColumnIndex("enum_code"));
                enum_name = cursor.getString(cursor.getColumnIndex("enum_name"));
                q1 = cursor.getString(cursor.getColumnIndex("b1"));
                status = cursor.getString(cursor.getColumnIndex("status"));
                q2 = cursor.getString(cursor.getColumnIndex("b2"));
                q3 = cursor.getString(cursor.getColumnIndex("b3"));
                q3_other = cursor.getString(cursor.getColumnIndex("b3_other"));
                q4 = cursor.getString(cursor.getColumnIndex("b4"));
                q5 = cursor.getString(cursor.getColumnIndex("b5"));
                q6 = cursor.getString(cursor.getColumnIndex("b6"));
                q7 = cursor.getString(cursor.getColumnIndex("b7"));
                q7_hh = cursor.getString(cursor.getColumnIndex("b7_hh"));
                q7_mm = cursor.getString(cursor.getColumnIndex("b7_mm"));
                q7_day = cursor.getString(cursor.getColumnIndex("b7_day"));
                q7_month = cursor.getString(cursor.getColumnIndex("b7_month"));
                q7_alternative_number = cursor.getString(cursor.getColumnIndex("b7_alternative_number"));
                q8 = cursor.getString(cursor.getColumnIndex("b8"));
                q9 = cursor.getString(cursor.getColumnIndex("b9"));
                q10 = cursor.getString(cursor.getColumnIndex("b10"));
                q10_other = cursor.getString(cursor.getColumnIndex("b10_other"));
                q11a = cursor.getString(cursor.getColumnIndex("b11a"));
                q11b = cursor.getString(cursor.getColumnIndex("b11b"));
                q12 = cursor.getString(cursor.getColumnIndex("b12"));
                q13 = cursor.getString(cursor.getColumnIndex("b13"));
                q13_hh = cursor.getString(cursor.getColumnIndex("b13_hh"));
                q13_mm = cursor.getString(cursor.getColumnIndex("b13_mm"));
                q13_day = cursor.getString(cursor.getColumnIndex("b13_day"));
                q13_month = cursor.getString(cursor.getColumnIndex("b13__month"));
                q14 = cursor.getString(cursor.getColumnIndex("b14"));
                q15 = cursor.getString(cursor.getColumnIndex("b15"));
                q16 = cursor.getString(cursor.getColumnIndex("b16"));
                q16_other = cursor.getString(cursor.getColumnIndex("b16_other"));
                q17 = cursor.getString(cursor.getColumnIndex("b17"));
                q18 = cursor.getString(cursor.getColumnIndex("b18"));
                q19 = cursor.getString(cursor.getColumnIndex("b19"));
                q19_hh = cursor.getString(cursor.getColumnIndex("b19_hh"));
                q19_mm = cursor.getString(cursor.getColumnIndex("b19_mm"));
                q19_day = cursor.getString(cursor.getColumnIndex("b19_day"));
                q19_month = cursor.getString(cursor.getColumnIndex("b19_month"));
                q19_alternative_number = cursor.getString(cursor.getColumnIndex("b19_alternative_number"));
                q20 = cursor.getString(cursor.getColumnIndex("b20"));
                q21 = cursor.getString(cursor.getColumnIndex("b21"));
                q22 = cursor.getString(cursor.getColumnIndex("b22"));
                q22_other = cursor.getString(cursor.getColumnIndex("b22_other"));
                q23 = cursor.getString(cursor.getColumnIndex("b23"));
                q24 = cursor.getString(cursor.getColumnIndex("b24"));
                q25 = cursor.getString(cursor.getColumnIndex("b25"));
                q25_hh = cursor.getString(cursor.getColumnIndex("b25_hh"));
                q25_mm = cursor.getString(cursor.getColumnIndex("b25_mm"));
                q25_day = cursor.getString(cursor.getColumnIndex("b25_day"));
                q25_month = cursor.getString(cursor.getColumnIndex("b25_month"));
                b26 = cursor.getString(cursor.getColumnIndex("b26"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {

        readFromDataBase();

        if (!StringUtils.isEmpty(enum_code)) {
            txtEnumCode.setText(enum_code);
        }
        if (!StringUtils.isEmpty(enum_name)) {
            txtEnumName.setText(enum_name);
        }

        if (!StringUtils.isEmpty(q1)) {
            for (int i = 0; i < RG_qb1.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb1.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q1)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(status)) {
            for (int i = 0; i < RG_status.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_status.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(status)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q2)) {
            for (int i = 0; i < RG_qb2.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb2.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q2)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q3)) {
            for (int i = 0; i < RG_qb3.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb3.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q3)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q3_other)) {
            edt_b3_other.setText(q3_other);
            edt_b3_other.setVisibility(View.VISIBLE);
        } else {
            edt_b3_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(q4)) {
            for (int i = 0; i < RG_qb4.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb4.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q4)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q5)) {
            for (int i = 0; i < RG_qb5.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb5.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q5)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q6)) {
            for (int i = 0; i < RG_qb6.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb6.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q6)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q7)) {
            for (int i = 0; i < RG_qb7.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb7.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q7)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q7_hh)) {
            if (!StringUtils.isEmpty(q7_mm)) {
                tv_b7_time.setText(q7_hh + ":" + q7_mm);
                tv_b7_time.setVisibility(View.VISIBLE);
            }
        }
        if (!StringUtils.isEmpty(q7_day)) {
            if (!StringUtils.isEmpty(q7_month)) {
                tv_b7_date.setText(q7_month + "/" + q7_day);
                tv_b7_date.setVisibility(View.VISIBLE);
            }
        }

        if (!StringUtils.isEmpty(q7_alternative_number)) {
            edt_b7_alternative_number.setText(q7_alternative_number);
            edt_b7_alternative_number.setVisibility(View.VISIBLE);
        } else {
            edt_b7_alternative_number.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(q8)) {
            for (int i = 0; i < RG_qb8.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb8.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q8)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q9)) {
            for (int i = 0; i < RG_qb9.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb9.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q9)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q10)) {
            for (int i = 0; i < RG_qb10.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb10.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q10)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q10_other)) {
            edt_b10_other.setText(q10_other);
            edt_b10_other.setVisibility(View.VISIBLE);
        } else {
            edt_b10_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(q11a)) {
            for (int i = 0; i < RG_qb11a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb11a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q11a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q11b)) {
            for (int i = 0; i < RG_qb11b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb11b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q11b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q12)) {
            for (int i = 0; i < RG_qb12.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb12.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q12)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q13)) {
            for (int i = 0; i < RG_qb13.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb13.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q13)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q13_hh)) {
            if (!StringUtils.isEmpty(q13_mm)) {
                tv_b13_time.setText(q13_hh + ":" + q13_mm);
                tv_b13_time.setVisibility(View.VISIBLE);
            }
        }
        if (!StringUtils.isEmpty(q13_day)) {
            if (!StringUtils.isEmpty(q13_month)) {
                tv_b13_date.setText(q13_month + "/" + q13_day);
                tv_b13_date.setVisibility(View.VISIBLE);
            }
        }

        if (!StringUtils.isEmpty(q14)) {
            for (int i = 0; i < RG_qb14.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb14.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q14)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q15)) {
            for (int i = 0; i < RG_qb15.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb15.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q15)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q16)) {
            for (int i = 0; i < RG_qb16.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb16.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q16)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q16_other)) {
            edt_b16_other.setText(q16_other);
            edt_b16_other.setVisibility(View.VISIBLE);
        } else {
            edt_b16_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(q17)) {
            for (int i = 0; i < RG_qb17.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb17.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q17)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q18)) {
            for (int i = 0; i < RG_qb18.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb18.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q18)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q18)) {
            for (int i = 0; i < RG_qb18.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb18.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q18)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q19)) {
            for (int i = 0; i < RG_qb19.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb19.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q19)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q19_hh)) {
            if (!StringUtils.isEmpty(q19_mm)) {
                tv_b19_time.setText(q19_hh + ":" + q19_mm);
                tv_b19_time.setVisibility(View.VISIBLE);
            }
        }
        if (!StringUtils.isEmpty(q19_day)) {
            if (!StringUtils.isEmpty(q19_month)) {
                tv_b19_date.setText(q19_month + "/" + q19_day);
                tv_b19_date.setVisibility(View.VISIBLE);
            }
        }

        if (!StringUtils.isEmpty(q19_alternative_number)) {
            edt_b19_alternative_number.setText(q19_alternative_number);
            edt_b19_alternative_number.setVisibility(View.VISIBLE);
        } else {
            edt_b19_alternative_number.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(q20)) {
            for (int i = 0; i < RG_qb20.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb20.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q20)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q21)) {
            for (int i = 0; i < RG_qb21.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb21.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q21)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q22)) {
            for (int i = 0; i < RG_qb22.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb22.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q22)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q22_other)) {
            edt_b22_other.setText(q22_other);
            edt_b22_other.setVisibility(View.VISIBLE);
        } else {
            edt_b22_other.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(q23)) {
            for (int i = 0; i < RG_qb23.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb23.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q23)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        if (!StringUtils.isEmpty(q24)) {
            for (int i = 0; i < RG_qb24.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb24.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q24)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(q25)) {
            for (int i = 0; i < RG_qb25.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb25.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(q25)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(q25_hh)) {
            if (!StringUtils.isEmpty(q25_mm)) {
                tv_b25_time.setText(q25_hh + ":" + q25_mm);
                tv_b25_time.setVisibility(View.VISIBLE);
            }
        }
        if (!StringUtils.isEmpty(q25_day)) {
            if (!StringUtils.isEmpty(q25_month)) {
                tv_b25_date.setText(q25_month + "/" + q25_day);
                tv_b25_date.setVisibility(View.VISIBLE);
            }
        }


        if (!StringUtils.isEmpty(b26)) {
            for (int i = 0; i < RG_qb26.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) RG_qb26.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(b26)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        ///////////////////
    }

    void settextviews() {
        try {
            ////////////////  Textview text changes ////
            b4_txt = findViewById(R.id.b4_txt);
            b5_txt = findViewById(R.id.b5_txt);
            b6_txt = findViewById(R.id.b6_txt);
            b7_txt = findViewById(R.id.b7_txt);
            b11a_txt = findViewById(R.id.b11a_txt);
            b11b_txt = findViewById(R.id.b11b_txt);
            b12_txt = findViewById(R.id.b12_txt);
            b13_txt = findViewById(R.id.b13_txt);
            b14_txt = findViewById(R.id.b14_txt);
            b17_txt = findViewById(R.id.b17_txt);
            b18a_txt = findViewById(R.id.b18a_txt);
            b18b_txt = findViewById(R.id.b18b_txt);
            b18_fathername_txt = findViewById(R.id.b18_fathername_txt);
            b19_txt = findViewById(R.id.b19_txt);
            b19_fathername_txt = findViewById(R.id.b19_fathername_txt);
            b23a_txt = findViewById(R.id.b23a_txt);
            b23b_txt = findViewById(R.id.b23b_txt);
            b24_txt = findViewById(R.id.b24_txt);
            b25_txt = findViewById(R.id.b25_txt);

            String farmerName = "";
            String farmer_fatherhusband_name = "";
            String farmerCellphone = "";
            String altFarmerName = "";
            String empFullname = "";
            String empostDesignation = "";


            MubLog.cpnsoleLog("inside settextviews ");

            Cursor cursor = databaseAccess.getBasicInfo(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                farmerName = cursor.getString(cursor.getColumnIndex("farmer_name"));
                farmer_fatherhusband_name = cursor.getString(cursor.getColumnIndex("farmer_fatherhusband_name"));
                farmerCellphone = cursor.getString(cursor.getColumnIndex("farmer_cellphone"));
                altFarmerName = cursor.getString(cursor.getColumnIndex("alt_farmer_name"));

                fC_farmer_cellphone = cursor.getString(cursor.getColumnIndex("farmer_cellphone"));
                fC_add_farmer_no = cursor.getString(cursor.getColumnIndex("add_farmer_no"));
                fC_alt_farmer_cellphone = cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone"));
                fC_farmer_no_alt = cursor.getString(cursor.getColumnIndex("add_farmer_no_alt"));

                altFarmerCellphone_ = cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone"));

                if (altFarmerCellphone_.equalsIgnoreCase(farmerCellphone)) {
                    altFarmerCellphone_ = "";

                }


                empFullname = cursor.getString(cursor.getColumnIndex("emp_fullname"));
                empostDesignation = cursor.getString(cursor.getColumnIndex("empost_designation"));
            }
            b4_txt.setText("  کیاآپ( " + farmerName + ") بات کر رہے  ہیں؟");
            b5_txt.setText("  کیاآپ( " + farmerName + ") کو جانتے ہیں؟");
            b6_txt.setText("  کیا یہ فون نمبر(  " + farmerName + ")  کا ہے؟");
            b7_txt.setText("  کیا ہم( " + farmerName + ") سے اس وقت یا بعد ازاں کسی مناسب وقت پر بات کر سکتے ہیں؟");

            b11a_txt.setText("  کیاآپ( " + farmerName + ") بات کر رہے  ہیں؟");
            b11b_txt.setText("  کیاآپ( " + farmerName + ") کو جانتے ہیں؟");
            b12_txt.setText("  کیا یہ فون نمبر(  " + farmerName + ")  کا ہے؟");
            b13_txt.setText("  کیا ہم( " + farmerName + ") سے اس وقت یا بعد ازاں کسی مناسب وقت پر بات کر سکتے ہیں؟");

            b14_txt.setText("  کیا متبادل نمبر( " + altFarmerCellphone_ + ")درست ہے؟");

            MubLog.cpnsoleLog("altFarmerCellphone_ " + altFarmerCellphone_);

            b17_txt.setText("  کیاآپ( " + altFarmerName + ") بات کر رہے  ہیں؟");

            b18a_txt.setText("  اسلام و علیکم: مجھے یہ نمبر محکمہ زراعت کے ذریعے سے ملا ۔میں ( " + farmerName + ")  صاحب/صاحبہ سے زراعت کے متعلق کچھ معلومات حاصل کرنا چاہتی ہوں ");
            b18b_txt.setText("  کیاآپ( " + farmerName + ") کو جانتے ہیں؟");
            b19_txt.setText("کیا ہم( " + farmerName + ") سے اس وقت یا بعد ازاں کسی مناسب وقت پر بات کر سکتے ہیں؟");
            b19_fathername_txt.setText("Father / Husband : " + farmer_fatherhusband_name);
            b18_fathername_txt.setText("Father / Husband : " + farmer_fatherhusband_name);
            b23a_txt.setText("  اسلام و علیکم: مجھے یہ نمبر محکمہ زراعت کے ذریعے سے ملا ۔میں ( " + farmerName + ")  صاحب/صاحبہ سے زراعت کے متعلق کچھ معلومات حاصل کرنا چاہتی ہوں ");
            b23b_txt.setText("  کیاآپ( " + farmerName + ") کو جانتے ہیں؟");
            b24_txt.setText("  کیا یہ فون نمبر(  " + farmerName + ")  کا ہے؟");
            b25_txt.setText("  xکیا ہم( " + farmerName + ") سے اس وقت یا بعد ازاں کسی مناسب وقت پر بات کر سکتے ہیں؟");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    void readdateandtime() {

        Cursor cursor = databaseAccess.getSectionCData(FarmerID);
        String c3_day = "";
        String c3_month = "";
        String c3_year = "";
        String c4_hh = "";
        String c4_mm = "";

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

        MubLog.cpnsoleLog("inside section b going to store start_date_and_time " + c3_day + "/" + c3_month + "/" + c3_year + " " + c4_hh + ":" + c4_mm);


        databaseAccess.savestartTime_of_survey(enum_name, enum_code, emp_id, order_id, FarmerID, c3_day, c3_month, c3_year, c4_hh, c4_mm);


    }


    private void createVisibilityListner(RadioGroup RG_qb14) {

        try {

            MubLog.cpnsoleLog("inside createVisibilityListner ");


            qb14_layout.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
                @Override
                public void onChildViewAdded(View parent, View child) {
                    MubLog.cpnsoleLog("inside createVisibilityListner onChildViewAdded");
                }

                @Override
                public void onChildViewRemoved(View parent, View child) {
                    MubLog.cpnsoleLog("inside createVisibilityListner onChildViewRemoved");

                }
            });


            qb14_layout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                                                      /**
                                                       * Called when the layout bounds of a view changes due to layout processing.
                                                       *
                                                       * @param v         The view whose bounds have changed.
                                                       * @param left      The new value of the view's left property.
                                                       * @param top       The new value of the view's top property.
                                                       * @param right     The new value of the view's right property.
                                                       * @param bottom    The new value of the view's bottom property.
                                                       * @param oldLeft   The previous value of the view's left property.
                                                       * @param oldTop    The previous value of the view's top property.
                                                       * @param oldRight  The previous value of the view's right property.
                                                       * @param oldBottom The previous value of the view's bottom property.
                                                       */
                                                      @Override
                                                      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {


                                                          MubLog.cpnsoleLog("Inside createVisibilityListner onLayoutChange altFarmerCellphone_ " + altFarmerCellphone_);

                                                          if (altFarmerCellphone_.equalsIgnoreCase("")) {
                                                              q14 = "2";
                                                          } else {
                                                              q14 = "1";
                                                          }
                                                          update_Section_b_14_number();


                                                      }


                                                  }
            );


        } catch (Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog("[Q_sectionB_a_b] inside createVisibilityListner() Exception is :"+e.toString());
        }
    }


    private void update_Section_b_14_number() {

        try {

            MubLog.cpnsoleLog("inside update_Section_b_14_number");
            databaseAccess.update_Section_b_14_number(emp_id, order_id, FarmerID, enum_code, enum_name, q14);


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside update_Section_b_14_number" + e.toString());
            // EmailDebugLog.getInstance(appContext).writeLog("[Q_sectionB_a_b] inside update_Section_b_14_number() Exception is :"+e.toString());
        }
    }

    void CheckLogging() {
        try {
            MubLog.cpnsoleLog("q1.equalsIgnoreCase = 2)  then " +
                    "status = " + status +
                    " q2 = " + q2 +
                    "  q3 = " + q3 +
                    "  q3_other = " + q3_other +
                    "  q4 = " + q4 +
                    "  q5 = " + q5 +
                    "  q6  = " + q6 +
                    "  q7  = " + q7 +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month +
                    "  q7_alternative_number = " + q7_alternative_number +
                    "  q8  = " + q8 +
                    "  q9  = " + q9 +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26   = " + b26
            );

            MubLog.cpnsoleLog("q2.equalsIgnoreCase = 1,2)  then " +
                    "  status = " + status +
                    "  q3 = " + q3 +
                    "  q3_other = " + q3_other
            );

            MubLog.cpnsoleLog("q2.equalsIgnoreCase = 3)  then " +
                    "  status = " + status +
                    "  q4 = " + q4 +
                    "  q5 = " + q5 +
                    "  q6  = " + q6 +
                    "  q7  = " + q7 +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month +
                    "  q7_alternative_number = " + q7_alternative_number +
                    "  q8  = " + q8 +
                    "  q9  = " + q9 +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26   = " + b26
            );

            MubLog.cpnsoleLog("q3.equalsIgnoreCase = 2,8,9)  then " +
                    "  q3_other = " + q3_other
            );

            MubLog.cpnsoleLog("q4.equalsIgnoreCase = 1)  then " +
                    "  status = " + status +
                    "  q5 = " + q5 +
                    "  q6  = " + q6 +
                    "  q7  = " + q7 +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month +
                    "  q7_alternative_number = " + q7_alternative_number +
                    "  q8  = " + q8 +
                    "  q9  = " + q9 +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );


            MubLog.cpnsoleLog("q5.equalsIgnoreCase = 2)  then " +
                    "  status = " + status +
                    "  q6  = " + q6 +
                    "  q7  = " + q7 +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month +
                    "  q7_alternative_number = " + q7_alternative_number +
                    "  q8  = " + q8 +
                    "  q9  = " + q9 +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month
            );

            MubLog.cpnsoleLog("q7.equalsIgnoreCase = 1)  then " +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month +
                    "  q7_alternative_number = " + q7_alternative_number +
                    "  q8  = " + q8 +
                    "  q9  = " + q9 +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );

            MubLog.cpnsoleLog("  q7.equalsIgnoreCase = 2)  then " +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month +
                    "  q7_alternative_number = " + q7_alternative_number +
                    "  q8  = " + q8 +
                    "  q9  = " + q9 +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month
            );

            MubLog.cpnsoleLog("  q7.equalsIgnoreCase = 3)  then " +
                    "  q7_alternative_number = " + q7_alternative_number
            );

            MubLog.cpnsoleLog("  q7.equalsIgnoreCase = 4)  then " +
                    "  q7_hh  = " + q7_hh +
                    "  q7_mm  = " + q7_mm +
                    "  q7_day  = " + q7_day +
                    "  q7_month  = " + q7_month
            );

            MubLog.cpnsoleLog("  q9.equalsIgnoreCase = 1,2)  then " +
                    "  q10  = " + q10 +
                    "  q10_other  = " + q10_other
            );
            MubLog.cpnsoleLog("  q9.equalsIgnoreCase = 3)  then " +
                    "  q11a  = " + q11a +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month
            );

            MubLog.cpnsoleLog("  q10.equalsIgnoreCase = 2,8,9)  then " +
                    "  q10_other  = " + q10_other
            );


            MubLog.cpnsoleLog("  q11a.equalsIgnoreCase = 1)  then " +
                    "  q11b  = " + q11b +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );


            MubLog.cpnsoleLog("  q11b.equalsIgnoreCase = 2)  then " +
                    "  q12  = " + q12 +
                    "  q13  = " + q13 +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month
            );


            MubLog.cpnsoleLog("  q13.equalsIgnoreCase = 1)  then " +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );

            MubLog.cpnsoleLog("  q13.equalsIgnoreCase = 2)  then " +
                    "  q13_hh  = " + q13_hh +
                    "  q13_mm  = " + q13_mm +
                    "  q13_day  = " + q13_day +
                    "  q13_month  = " + q13_month +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );


            MubLog.cpnsoleLog("  q14.equalsIgnoreCase = 2)  then " +
                    "  q14  = " + q14 +
                    "  q15  = " + q15 +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "b26    = " + b26
            );

            MubLog.cpnsoleLog("  q15.equalsIgnoreCase = 1,2)  then " +
                    "  q16  = " + q16 +
                    "  q16_other  = " + q16_other
            );

            MubLog.cpnsoleLog("  q15.equalsIgnoreCase = 3)  then " +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26    = " + b26
            );

            MubLog.cpnsoleLog("!q16.equalsIgnoreCase = blank)  then " +
                    "  q17  = " + q17 +
                    "  q18   = " + q18 +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26    = " + b26
            );

            MubLog.cpnsoleLog("  q16.equalsIgnoreCase = 2,8,9)" +
                    "  q16_other  = " + q16_other
            );


            MubLog.cpnsoleLog("  q18.equalsIgnoreCase = 2)  then " +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26    = " + b26
            );
            MubLog.cpnsoleLog("  q19.equalsIgnoreCase = 1)  then " +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );

            MubLog.cpnsoleLog("  q19.equalsIgnoreCase = 2)  then " +
                    "  q19   = " + q19 +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month +
                    "  q19_alternative_number   = " + q19_alternative_number +
                    "  q20   = " + q20 +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26    = " + b26
            );

            MubLog.cpnsoleLog("  q19.equalsIgnoreCase = 3)  then " +
                    "  q19_alternative_number   = " + q19_alternative_number
            );

            MubLog.cpnsoleLog("  q19.equalsIgnoreCase = 4)  then " +
                    "  q19_hh   = " + q19_hh +
                    "  q19_mm   = " + q19_mm +
                    "  q19_day   = " + q19_day +
                    "  q19_month   = " + q19_month
            );

            MubLog.cpnsoleLog("  q20.equalsIgnoreCase = 2)  then " +
                    "  q21   = " + q21 +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26    = " + b26
            );

            MubLog.cpnsoleLog("  q21.equalsIgnoreCase = 1,2)  then " +
                    "  q22   = " + q22 +
                    "  q22_other   = " + q22_other
            );

            MubLog.cpnsoleLog("!q22.equalsIgnoreCase = blank)  then " +
                    "  q23   = " + q23 +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "   b26    = " + b26
            );

            MubLog.cpnsoleLog("  q23.equalsIgnoreCase = 2)  then " +
                    "  q24   = " + q24 +
                    "  q25   = " + q25 +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "  b26    = " + b26
            );

            MubLog.cpnsoleLog("  q25.equalsIgnoreCase = 1)  then " +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month
            );

            MubLog.cpnsoleLog("  q25.equalsIgnoreCase = 2)  then " +
                    "  q25_hh   = " + q25_hh +
                    "  q25_mm   = " + q25_mm +
                    "  q25_day   = " + q25_day +
                    "  q25_month   = " + q25_month +
                    "b26    = " + b26
            );


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in CheckLogging = " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
