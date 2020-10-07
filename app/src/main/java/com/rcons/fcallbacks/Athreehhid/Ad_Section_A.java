package com.rcons.fcallbacks.Athreehhid;

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
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity_AD;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideKeyboard;
import static com.rcons.fcallbacks.Utilties.RConsUtils.hideView;

public class Ad_Section_A extends AppCompatActivity {

    @BindView(R.id.qa1_layout)
    LinearLayout qa1_layout;
    @BindView(R.id.qa2_layout)
    LinearLayout qa2_layout;
    @BindView(R.id.qa3_layout)
    LinearLayout qa3_layout;
    @BindView(R.id.qa4_layout)
    LinearLayout qa4_layout;
    @BindView(R.id.qa5_layout)
    LinearLayout qa5_layout;
    @BindView(R.id.qa6_layout)
    LinearLayout qa6_layout;
    @BindView(R.id.qa7_layout)
    LinearLayout qa7_layout;

    @BindView(R.id.qa4a_layout)
    LinearLayout qa4a_layout;

    @BindView(R.id.qa4b_layout)
    LinearLayout qa4b_layout;

    @BindView(R.id.qa4c_layout)
    LinearLayout qa4c_layout;

    @BindView(R.id.qa4d_layout)
    LinearLayout qa4d_layout;

    @BindView(R.id.edt_a4a)
    EditText edt_a4a;

    @BindView(R.id.rg_a4b)
    RadioGroup rg_a4b;

    @BindView(R.id.rg_a4c)
    RadioGroup rg_a4c;

    @BindView(R.id.rg_a4d)
    RadioGroup rg_a4d;

    @BindView(R.id.rg_a1)
    RadioGroup rg_a1;
    @BindView(R.id.rg_a2)
    RadioGroup rg_a2;
    @BindView(R.id.rg_a3)
    RadioGroup rg_a3;
    @BindView(R.id.rg_a4)
    RadioGroup rg_a4;
    @BindView(R.id.rg_a5)
    RadioGroup rg_a5;
    @BindView(R.id.rg_a6)
    RadioGroup rg_a6;

    @BindView(R.id.rg_a5a)
    RadioGroup rg_a5a;

    @BindView(R.id.edt_a6_other)
    EditText edt_a6_other;

    @BindView(R.id.edt_a7)
    EditText edt_a7;

    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

    @BindView(R.id.txt_section_a_script_2)
    TextView txt_section_a_script_2;

    @BindView(R.id.txt_a6_time)
    TextView txt_a6_time;

    @BindView(R.id.txt_a6_date)
    TextView txt_a6_date;

    @BindView(R.id.section_a_question_3)
    TextView section_a_question_3;

    @BindView(R.id.section_a_question_4)
    TextView section_a_question_4;

    @BindView(R.id.txt_a4_time)
    TextView txt_a4_time;
    @BindView(R.id.txt_a4_date)
    TextView txt_a4_date;

    @BindView(R.id.edt_a4_alt_number)
    EditText edt_a4_alt_number;

    @BindView(R.id.edt_a4d_other)
    EditText edt_a4d_other;

    @BindView(R.id.edt_a4c_other)
    EditText edt_a4c_other;

    @BindView(R.id.qb2_layout)
    LinearLayout qb2_layout;

    @BindView(R.id.qb1_layout)
    LinearLayout qb1_layout;

    @BindView(R.id.rg_b2)
    RadioGroup rg_b2;

    @BindView(R.id.rg_b1)
    RadioGroup rg_b1;

    @BindView(R.id.section_b_question_q1)
    TextView section_b_question_q1;

    @BindView(R.id.section_b_question_q2)
    TextView section_b_question_q2;

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

    String emp_id = "";
    String order_id = "";
    String farmer_id = "";
    String rcons_user = "";
    String enum_code = "";
    String enum_name = "";
    String isComplete = "";
    String isSynced = "";
    String insert_or_updated_in_phone_at = "";
    String deviceid = "";
    String build_no = "";
    String school_code = "";
    String student_id = "";
    String a1 = "";
    String a2 = "";
    String a3 = "";
    String a4 = "";

    String b1 = "";
    String b2 = "";

    String a4_a = "";
    String a4_b = "";
    String a4_c = "";
    String a4_c_other = "";
    String a4_d = "";
    String a4_d_other = "";

    String a4_day = "";
    String a4_month = "";
    String a4_year = "";
    String a4_hh = "";
    String a4_mm = "";
    String a4_number = "";
    String a5 = "";
    String a5_a = "";
    String a6 = "";
    String a6_other = "";
    String a6_day = "";
    String a6_month = "";
    String a6_year = "";
    String a6_hh = "";
    String a6_mm = "";
    String a7 = "";

    String try_no = "";

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";
    String adolescent_name = "";

    private DatePickerDialog.OnDateSetListener mDateSetListener_a4;
    private DatePickerDialog.OnDateSetListener mDateSetListener_a6;


    String s1 = "";
    String s2 = "";
    String s3 = "";
    String s4 = "";
    String s5 = "";
    String s6 = "";

    @BindView(R.id.adol_name)
    TextView adol_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_section_a);

        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(Ad_Section_A.this);
        databaseAccess.Open();


        SetEnumState();


        phone_number = getIntent().getStringExtra("m1b_parent_mobile");
        emp_id = "1";//getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("phone_order");
        farmer_id = "1";//getIntent().getStringExtra("farmerID");
        school_code = getIntent().getStringExtra("scode");
        student_id = getIntent().getStringExtra("studentid");
        student_name = getIntent().getStringExtra("m1b_student_name");
        school_name = "";//getIntent().getStringExtra("m2_school_name");


        id = getIntent().getStringExtra("id");
        s1 = getIntent().getStringExtra("sc1");
        s2 = getIntent().getStringExtra("sc2");
        s3 = getIntent().getStringExtra("sc3");
        s4 = getIntent().getStringExtra("sc4");
        s5 = getIntent().getStringExtra("sc5");
        s6 = getIntent().getStringExtra("sc6");

        readFromDataBasic();

        txt_section_a_script_2.setText("السلام علیکم/میرا نام (" + enum_name + ")  ہے۔ میں آپ کو آرکونز اور ادارہ تعلیم و آگہی کی جانب سے  جو کہ پاکستان میں  تعلیم پر کام  کرنے والا ایک ادارہ ہے اور پنجاب حکومت کے ساتھ مل کر مختلف پروگرام مہیا کرتا ہے   کی جانب سے کال کررہی ہوں۔کچھ دن پہلے ہم آپ کے گاوں میں آئے اور ایسے گھرانوں سے فون نمبرز اکٹھے کئے جہاں سکول جانے کی عمر والی (9سے19سال)کی لڑکیاں  یا لڑکے موجود ہیں۔ہم آپ سے آپ کے علاقے میں تعلیم کے بارے میں مزید جاننا چاہتے ہیں۔");

        section_a_question_3.setText("کیا آپ   " + student_name + " کے گھرانے سے ہیں، آپ کی عمر 18سال سے اوپر ہے اور اپنے گھرانے کی فیصلہ سازی کرتے ہیں۔ ");
        section_a_question_4.setText("کیا  آپ    " + student_name + " کے    والد / والدہ  یا  سرپرست سے میر ی   بات  کروا سکتے ہیں ؟");

        section_b_question_q1.setText("کیا میں    " + student_name + " سے بات کر رہی ہوں؟ ");
        section_b_question_q2.setText("کیا میں    " + student_name + "سے بات کر سکتی ہوں؟ ");

        txt_School_Code.setText("Village Code : " + school_code);
        txt_Student_id.setText("HH Id : " + student_id);


        LoadPreviousData();
        displayHeaderName();

        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;

        if (StringUtils.isEmpty(s1)) {
            tvTitle.setText("Section B1");
            MubLog.cpnsoleLog("tvTitle Try 1");
        } else if (StringUtils.isEmpty(s2)) {
            tvTitle.setText("Section B2");
            MubLog.cpnsoleLog("tvTitle Try 2");
        } else if (StringUtils.isEmpty(s3)) {
            tvTitle.setText("Section B3");
            MubLog.cpnsoleLog("tvTitle Try 3");
        } else if (StringUtils.isEmpty(s4)) {
            tvTitle.setText("Section B4");
            MubLog.cpnsoleLog("tvTitle Try 4");
        } else if (StringUtils.isEmpty(s5)) {
            tvTitle.setText("Section B5");
            MubLog.cpnsoleLog("tvTitle Try 5");
        } else if (StringUtils.isEmpty(s6)) {
            tvTitle.setText("Section B6");
            MubLog.cpnsoleLog("tvTitle Try 6");
        }

        txt_a6_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Ad_Section_A.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener_a6,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener_a6 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                year = 2020;
                MubLog.cpnsoleLog("onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                txt_a6_date.setText(date);
                a6_day = Integer.toString(day);
                a6_month = Integer.toString(month);
                a6_year = Integer.toString(year);

            }
        };
        txt_a6_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Ad_Section_A.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_a6_time.setText(selectedHour + ":" + selectedMinute);
                        a6_mm = Integer.toString(selectedMinute);
                        a6_hh = Integer.toString(selectedHour);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        txt_a4_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Ad_Section_A.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener_a4,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener_a4 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                year = 2020;
                MubLog.cpnsoleLog("onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                txt_a4_date.setText(date);
                a4_day = Integer.toString(day);
                a4_month = Integer.toString(month);
                a4_year = Integer.toString(year);

            }
        };

        txt_a4_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Ad_Section_A.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_a4_time.setText(selectedHour + ":" + selectedMinute);
                        a4_mm = Integer.toString(selectedMinute);
                        a4_hh = Integer.toString(selectedHour);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });


        rg_a6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_a6.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_a6_99) {
                    edt_a6_other.setVisibility(View.VISIBLE);
                    edt_a6_other.requestFocus();
                    txt_a6_date.setVisibility(View.GONE);
                    txt_a6_time.setVisibility(View.GONE);

                    a6_day = "";
                    a6_month = "";
                    a6_year = "";
                    a6_hh = "";
                    a6_mm = "";
                    txt_a6_date.setText("");
                    txt_a6_time.setText("");
                } else if (RGroup_ID == R.id.rbtn_a6_4) {
                    txt_a6_date.setVisibility(View.VISIBLE);
                    txt_a6_time.setVisibility(View.VISIBLE);
                    edt_a6_other.setVisibility(View.GONE);
                    hideKeyboard(Ad_Section_A.this);
                    edt_a6_other.setText("");
                    a6_other = "";
                } else {
                    edt_a6_other.setVisibility(View.GONE);
                    hideKeyboard(Ad_Section_A.this);
                    edt_a6_other.setText("");
                    txt_a6_date.setText("");
                    txt_a6_time.setText("");
                    a6_other = "";
                    txt_a6_date.setVisibility(View.GONE);
                    txt_a6_time.setVisibility(View.GONE);

                    a6_other = "";
                    a6_day = "";
                    a6_month = "";
                    a6_year = "";
                    a6_hh = "";
                    a6_mm = "";
                }
            }
        });


        rg_a4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_a4.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_a4_3) {
                    edt_a4_alt_number.setVisibility(View.VISIBLE);
                    edt_a4_alt_number.requestFocus();

                    txt_a4_date.setVisibility(View.GONE);
                    txt_a4_time.setVisibility(View.GONE);

                    a4_day = "";
                    a4_month = "";
                    a4_year = "";
                    a4_hh = "";
                    a4_mm = "";

                    txt_a4_date.setText("");
                    txt_a4_time.setText("");

                } else if (RGroup_ID == R.id.rbtn_a4_2) {
                    txt_a4_date.setVisibility(View.VISIBLE);
                    txt_a4_time.setVisibility(View.VISIBLE);

                    edt_a4_alt_number.setVisibility(View.GONE);

                    a4_number = "";
                    edt_a4_alt_number.setText("");

                } else {
                    txt_a4_date.setVisibility(View.GONE);
                    txt_a4_time.setVisibility(View.GONE);
                    edt_a4_alt_number.setVisibility(View.GONE);

                    txt_a4_date.setText("");
                    txt_a4_time.setText("");
                    edt_a4_alt_number.setText("");


                    a4_number = "";
                    a4_day = "";
                    a4_month = "";
                    a4_year = "";
                    a4_hh = "";
                    a4_mm = "";
                }
            }
        });

        rg_a4c.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_a4c.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_a4c_5) {
                    edt_a4c_other.setVisibility(View.VISIBLE);
                    edt_a4c_other.requestFocus();
                } else {
                    edt_a4c_other.setVisibility(View.GONE);
                    hideKeyboard(Ad_Section_A.this);
                    edt_a4c_other.setText("");
                    a4_c_other = "";
                }
            }
        });
        rg_a4d.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_a4d.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_a4d_1) {
                    edt_a4d_other.setVisibility(View.VISIBLE);
                    edt_a4d_other.requestFocus();
                } else {
                    edt_a4d_other.setVisibility(View.GONE);
                    hideKeyboard(Ad_Section_A.this);
                    edt_a4d_other.setText("");
                    a4_d_other = "";
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_a1_ID = rg_a1.getCheckedRadioButtonId();
                int rg_a2_ID = rg_a2.getCheckedRadioButtonId();
                int rg_a3_ID = rg_a3.getCheckedRadioButtonId();
                int rg_a4_ID = rg_a4.getCheckedRadioButtonId();
                int rg_a5_ID = rg_a5.getCheckedRadioButtonId();
                int rg_a6_ID = rg_a6.getCheckedRadioButtonId();

                int rg_a4b_ID = rg_a4b.getCheckedRadioButtonId();
                int rg_a4c_ID = rg_a4c.getCheckedRadioButtonId();
                int rg_a4d_ID = rg_a4d.getCheckedRadioButtonId();
                int rg_a5a_ID = rg_a5a.getCheckedRadioButtonId();

                int rg_b1_ID = rg_b1.getCheckedRadioButtonId();
                int rg_b2_ID = rg_b2.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(Ad_Section_A.this);
                SaveData();
                // A14
                if (qa1_layout.getVisibility() == View.VISIBLE) {
                    if (rg_a1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a1_ID);
                        a1 = radioButton.getTag().toString();
                        if (a1.equalsIgnoreCase("1")) {
                            a2 = "";
                            rg_a2.clearCheck();
                            RConsUtils.hideView(qa1_layout, qb1_layout);
                        } else {
                            RConsUtils.hideView(qa1_layout, qa2_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                }// A15
                else if (qa2_layout.getVisibility() == View.VISIBLE) {
                    if (rg_a2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a2_ID);
                        a2 = radioButton.getTag().toString();
                        SaveData();
                        Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", phone_number);
                        intent.putExtra("school_code", school_code);
                        intent.putExtra("student_id", student_id);
                        startActivityForResult(intent, 88);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qb1_layout.getVisibility() == View.VISIBLE) {
                    // B1
                    if (rg_b1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b1_ID);
                        b1 = radioButton.getTag().toString();
                        if (b1.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qb1_layout, qa4a_layout);
                        } else if (b1.equalsIgnoreCase("3") || (b1.equalsIgnoreCase("-777"))) {
                            SaveData();
                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            // RConsUtils.hideView(qb1_layout, qb2_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qb2_layout.getVisibility() == View.VISIBLE) {
                    // B2
                    if (rg_b2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b2_ID);
                        b2 = radioButton.getTag().toString();
                        if (b2.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qb2_layout, qa4a_layout);
                        } else if (b2.equalsIgnoreCase("3") || b2.equalsIgnoreCase("-99") || (b2.equalsIgnoreCase("-777"))) {
                            SaveData();
                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            RConsUtils.hideView(qb2_layout, qa3_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa3_layout.getVisibility() == View.VISIBLE) {
                    // B3
                    if (rg_a3_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a3_ID);
                        a3 = radioButton.getTag().toString();
                        if (a3.equalsIgnoreCase("1") || a3.equalsIgnoreCase("-88") || a3.equalsIgnoreCase("-98")) {
                            RConsUtils.hideView(qa3_layout, qa4a_layout);
                        } else {
                            SaveData();
                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_a4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a4_ID);
                        a4 = radioButton.getTag().toString();
                        if (a4.equalsIgnoreCase("1") || (a4.equalsIgnoreCase("5"))) {


                            txt_a4_date.setText("");
                            txt_a4_time.setText("");
                            edt_a4_alt_number.setText("");
                            a4_number = "";
                            a4_day = "";
                            a4_month = "";
                            a4_year = "";
                            a4_hh = "";
                            a4_mm = "";

                            RConsUtils.hideView(qa4_layout, qa4a_layout);
                        } else if (a4.equalsIgnoreCase("2")) {

                            edt_a4_alt_number.setText("");
                            a4_number = "";
                            a5 = "";
                            rg_a5.clearCheck();
                            a6 = "";
                            rg_a6.clearCheck();
                            a6_other = "";
                            a6_day = "";
                            a6_month = "";
                            a6_year = "";
                            a6_hh = "";
                            a6_mm = "";
                            a7 = "";
                            edt_a6_other.setText("");
                            txt_a6_date.setText("");
                            txt_a6_time.setText("");
                            edt_a7.setText("");

                            if (!StringUtils.isEmpty(txt_a4_time.getText().toString())
                                    && !StringUtils.isEmpty(txt_a4_date.getText().toString())) {
                                SaveData();
                                Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_cellphone", phone_number);
                                intent.putExtra("school_code", school_code);
                                intent.putExtra("student_id", student_id);
                                startActivityForResult(intent, 88);
                            } else {
                                toastMessage("Please Enter Required Data");
                            }
                        } else if (a4.equalsIgnoreCase("3")) {

                            txt_a4_date.setText("");
                            txt_a4_time.setText("");
                            a4_day = "";
                            a4_month = "";
                            a4_year = "";
                            a4_hh = "";
                            a4_mm = "";
                            a5 = "";
                            rg_a5.clearCheck();
                            a6 = "";
                            rg_a6.clearCheck();
                            a6_other = "";
                            a6_day = "";
                            a6_month = "";
                            a6_year = "";
                            a6_hh = "";
                            a6_mm = "";
                            a7 = "";
                            edt_a6_other.setText("");
                            txt_a6_date.setText("");
                            txt_a6_time.setText("");
                            edt_a7.setText("");

                            a4_number = edt_a4_alt_number.getText().toString();
                            if (StringUtils.isEmpty(a4_number)) {
                                toastMessage("Please Enter Number");
                            } else if (a4_number.length() < 10 || a4_number.length() > 10) {
                                toastMessage("Invalid Phone Number");
                            } else {


                                databaseAccess.UpdateGivenNumber(a4_number, school_code, student_id);
                                toastMessage("Number updated ~ " + a4_number);
                                phone_number = a4_number;
                                //  RConsUtils.hideView(qa4_layout, qa5_layout);
                                Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_cellphone", phone_number);
                                intent.putExtra("school_code", school_code);
                                intent.putExtra("student_id", student_id);
                                startActivityForResult(intent, 88);
                            }
                        } else {
                            txt_a4_date.setText("");
                            txt_a4_time.setText("");
                            edt_a4_alt_number.setText("");
                            a4_number = "";
                            a4_day = "";
                            a4_month = "";
                            a4_year = "";
                            a4_hh = "";
                            a4_mm = "";
                            a5 = "";
                            rg_a5.clearCheck();
                            a6 = "";
                            rg_a6.clearCheck();
                            a6_other = "";
                            a6_day = "";
                            a6_month = "";
                            a6_year = "";
                            a6_hh = "";
                            a6_mm = "";
                            a7 = "";
                            edt_a6_other.setText("");
                            txt_a6_date.setText("");
                            txt_a6_time.setText("");
                            edt_a7.setText("");

                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa4a_layout.getVisibility() == View.VISIBLE) {
                    // B4
                    a4_a = edt_a4a.getText().toString();
                    if (!StringUtils.isEmpty(a4_a)) {
                        Integer age = Integer.parseInt("0" + a4_a);
                        if (age > 5 && age < 50) {
                            RConsUtils.hideView(qa4a_layout, qa4d_layout);
                        } else {
                            toastMessage("Age must be in 5-50");
                        }
                    } else {
                        toastMessage("Please Enter Name");
                    }
                } else if (qa4b_layout.getVisibility() == View.VISIBLE) {
                    // B5
                    if (rg_a4b_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a4b_ID);
                        a4_b = radioButton.getTag().toString();
                        if (a4_b.equalsIgnoreCase("-777") || a4_b.equalsIgnoreCase("-99")) {
                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else if (a4_b.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qa4b_layout, qa4d_layout);
                        } else {
                            RConsUtils.hideView(qa4b_layout, qa4c_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa4c_layout.getVisibility() == View.VISIBLE) {
                    // B6
                    if (rg_a4c_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a4c_ID);
                        a4_c = radioButton.getTag().toString();
                        if (a4_c.equalsIgnoreCase("5")) {
                            a4_c_other = edt_a4c_other.getText().toString();
                            if (!StringUtils.isEmpty(a4_c_other)) {
                                RConsUtils.hideView(qa4c_layout, qa4d_layout);
                            } else {
                                toastMessage("Please specify other");
                            }
                        } else if (a4_c.equalsIgnoreCase("0") || a4_c.equalsIgnoreCase("-99") || a4_c.equalsIgnoreCase("-777")) {
                            a4_d_other = "";
                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            a4_d_other = "";
                            RConsUtils.hideView(qa4c_layout, qa4d_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa4d_layout.getVisibility() == View.VISIBLE) {
                    // B7
                    if (rg_a4d_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a4d_ID);
                        a4_d = radioButton.getTag().toString();
                        if (a4_d.equalsIgnoreCase("1")) {
                            a4_d_other = edt_a4d_other.getText().toString();
                            if (!StringUtils.isEmpty(a4_d_other)) {
                                if (a4_d_other.length() == 10) {
                                    RConsUtils.hideView(qa4d_layout, qa5_layout);
                                } else {
                                    toastMessage("Length must be 10-10");
                                }
                            } else {
                                toastMessage("Please Enter Number");
                            }
                        } else {
                            a4_d_other = "";
                            RConsUtils.hideView(qa4d_layout, qa5_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa5_layout.getVisibility() == View.VISIBLE) {
                    // B9
                    if (rg_a5_ID > 0) {
                        // B10
                        if (rg_a5a_ID > 0) {
                            RadioButton radioButton = findViewById(rg_a5_ID);
                            a5 = radioButton.getTag().toString();
                            RadioButton radioButton_a5a = findViewById(rg_a5a_ID);
                            a5_a = radioButton_a5a.getTag().toString();
                            if (a5_a.equalsIgnoreCase("2") || a5_a.equalsIgnoreCase("-777")) {
                                SaveData();
                                Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_cellphone", phone_number);
                                intent.putExtra("school_code", school_code);
                                intent.putExtra("student_id", student_id);
                                startActivityForResult(intent, 88);
                            } else {
                                SaveData();

//                                Intent returnIntent = new Intent();
//                                returnIntent.putExtra("isDataUpdated", false);
//                                setResult(Activity.RESULT_OK, returnIntent);


                                Intent intent = new Intent(Ad_Section_A.this, Ad_Section_F.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);


//                                intent.putExtra("farmer_cellphone", phone_number);
//                                intent.putExtra("school_code", school_code);
//                                intent.putExtra("student_id", student_id);
//                                intent.putExtra("student_name", student_name);
//                                intent.putExtra("school_name", school_name);

                                intent.putExtra("m1b_parent_mobile", phone_number);
                                intent.putExtra("scode", school_code);
                                intent.putExtra("studentid", student_id);
                                intent.putExtra("m1b_student_name", student_name);
                                intent.putExtra("rcons_user", RConsUtils.getUserName());

//                                startActivity(intent);
                                startActivityForResult(intent, 88);
//                                finish();
                            }
                        } else {
                            toastMessage("Please Select B9 Option");
                        }
                    } else {
                        toastMessage("Please Select B8 Option");
                    }
                } else if (qa6_layout.getVisibility() == View.VISIBLE) {
                    if (rg_a6_ID > 0) {
                        RadioButton radioButton = findViewById(rg_a6_ID);
                        a6 = radioButton.getTag().toString();
                        if (a6.equalsIgnoreCase("4")) {
                            if (!StringUtils.isEmpty(txt_a6_time.getText().toString())
                                    && !StringUtils.isEmpty(txt_a6_date.getText().toString())) {
                                Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_cellphone", phone_number);
                                intent.putExtra("school_code", school_code);
                                intent.putExtra("student_id", student_id);
                                startActivityForResult(intent, 88);
                            } else {
                                toastMessage("Please fill date and time");
                            }
                        } else if (a6.equalsIgnoreCase("99")) {
                            a6_other = edt_a6_other.getText().toString();
                            if (!StringUtils.isEmpty(a6_other)) {
                                Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_cellphone", phone_number);
                                intent.putExtra("school_code", school_code);
                                intent.putExtra("student_id", student_id);
                                startActivityForResult(intent, 88);
                            } else {
                                toastMessage("Please specify other");
                            }

                        } else {
                            Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qa7_layout.getVisibility() == View.VISIBLE) {
                    a7 = edt_a7.getText().toString();
                    if (!StringUtils.isEmpty(a7)) {
                        if (!a7.equalsIgnoreCase("-98")) {
                            if (a7.length() < 10 || a7.length() > 10) {
                                toastMessage("Invalid Number Length must be equal to 10");

                            } else {
                                SaveData();
//                                Intent returnIntent = new Intent();
//                                returnIntent.putExtra("isDataUpdated", false);
//                                setResult(Activity.RESULT_OK, returnIntent);

                                Intent intent = new Intent(Ad_Section_A.this, HH_Screen_two.class);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("id", id);


//                                intent.putExtra("farmer_cellphone", phone_number);
//                                intent.putExtra("school_code", school_code);
//                                intent.putExtra("student_id", student_id);
//                                intent.putExtra("student_name", student_name);
//                                intent.putExtra("school_name", school_name);

                                intent.putExtra("m1b_parent_mobile", phone_number);
                                intent.putExtra("scode", school_code);
                                intent.putExtra("studentid", student_id);
                                intent.putExtra("m1b_student_name", student_name);
                                intent.putExtra("rcons_user", RConsUtils.getUserName());

//                                startActivity(intent);
                                startActivityForResult(intent, 88);
//                                finish();
                            }
                        } else {
                            SaveData();
//                            Intent returnIntent = new Intent();
//                            returnIntent.putExtra("isDataUpdated", false);
//                            setResult(Activity.RESULT_OK, returnIntent);

                            Intent intent = new Intent(Ad_Section_A.this, HH_Screen_two.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);


//                            intent.putExtra("farmer_cellphone", phone_number);
//                            intent.putExtra("school_code", school_code);
//                            intent.putExtra("student_id", student_id);
//                            intent.putExtra("student_name", student_name);
//                            intent.putExtra("school_name", school_name);

                            intent.putExtra("m1b_parent_mobile", phone_number);
                            intent.putExtra("scode", school_code);
                            intent.putExtra("studentid", student_id);
                            intent.putExtra("m1b_student_name", student_name);
                            intent.putExtra("rcons_user", RConsUtils.getUserName());

//                            startActivity(intent);
                            startActivityForResult(intent, 88);
//                            finish();
                        }
                    } else {
                        toastMessage("Please Enter Required Data");
                    }

                }
                SaveData();
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

                Intent intent = new Intent(Ad_Section_A.this, AddReportActivity_AD.class);
                intent.putExtra("emp_id", emp_id);
                intent.putExtra("order_id", order_id);
                intent.putExtra("id", id);
                intent.putExtra("farmer_cellphone", phone_number);
                intent.putExtra("school_code", school_code);
                intent.putExtra("student_id", student_id);
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

    @Override
    public void onBackPressed() {
        if (qa7_layout.getVisibility() == View.VISIBLE) {
            if (a5.equalsIgnoreCase("1")) {
                hideView(qa7_layout, qa5_layout);
            } else {
                hideView(qa7_layout, qa6_layout);
            }
        } else if (qa6_layout.getVisibility() == View.VISIBLE) {
            hideView(qa6_layout, qa5_layout);
        } else if (qa5_layout.getVisibility() == View.VISIBLE) {
            hideView(qa5_layout, qa4d_layout);
        } else if (qa4d_layout.getVisibility() == View.VISIBLE) {
            hideView(qa4d_layout, qa4a_layout);
        } else if (qa4c_layout.getVisibility() == View.VISIBLE) {
            hideView(qa4c_layout, qa4b_layout);
        } else if (qa4b_layout.getVisibility() == View.VISIBLE) {
            hideView(qa4b_layout, qa4a_layout);
        } else if (qa4a_layout.getVisibility() == View.VISIBLE) {
            if (b1.equalsIgnoreCase("1")) {
                hideView(qa4a_layout, qb1_layout);
            } else if (b2.equalsIgnoreCase("1")) {
                hideView(qa4a_layout, qb2_layout);
            } else {
                hideView(qa4a_layout, qa3_layout);
            }
        } else if (qa4_layout.getVisibility() == View.VISIBLE) {
            hideView(qa4_layout, qa3_layout);
        } else if (qa3_layout.getVisibility() == View.VISIBLE) {
            hideView(qa3_layout, qb2_layout);
        } else if (qb2_layout.getVisibility() == View.VISIBLE) {
            hideView(qb2_layout, qb1_layout);
        } else if (qb1_layout.getVisibility() == View.VISIBLE) {
            hideView(qb1_layout, qa1_layout);
        } else if (qa2_layout.getVisibility() == View.VISIBLE) {
            hideView(qa2_layout, qa1_layout);
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (!StringUtils.isEmpty(phone_number)) {
                    DialUserNumber(phone_number);
                } else {
                    toastMessage("Phone number is empty");
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
                // ShowPermissionDialog("Allow this permission to copy images to your phone memory");
            }
        }
    }

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Ad_Section_A.this,
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
        SaveInterviewStart_time();
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);

    }

    void SaveInterviewStart_time() {

        String year = RConsUtils.getcurrentTime(0);
        String month = RConsUtils.getcurrentTime(1);
        String day = RConsUtils.getcurrentTime(2);
        String hh = RConsUtils.getcurrentTime(3);
        String mm = RConsUtils.getcurrentTime(4);


        if (StringUtils.isEmpty(s1)) {
            databaseAccess.savepq_interview_start_sc1(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s2)) {
            databaseAccess.savepq_interview_start_sc2(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s3)) {
            databaseAccess.savepq_interview_start_sc3(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s4)) {
            databaseAccess.savepq_interview_start_sc4(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s5)) {
            databaseAccess.savepq_interview_start_sc5(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s6)) {
            databaseAccess.savepq_interview_start_sc6(school_code, student_id, year, month, day, hh, mm);
        }

    }

    private String getSimNetwork() {
        TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return phoneMgr.getSimOperatorName();
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
            readFromDataBasic();
        }
    }


    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);

            a4_year = MubDateAndTime.INSTANCE.getcurrentTime(0);
            a4_month = MubDateAndTime.INSTANCE.getcurrentTime(1);
            a4_day = MubDateAndTime.INSTANCE.getcurrentTime(2);
            a4_hh = MubDateAndTime.INSTANCE.getcurrentTime(3);
            a4_mm = MubDateAndTime.INSTANCE.getcurrentTime(4);

            isSynced = "99";
            checkAndUpdateVariables();
            a4 = adolescent_name;
            if (StringUtils.isEmpty(s1)) {
                try_no = "1";
                MubLog.cpnsoleLog("SaveData Try 1");
            } else if (StringUtils.isEmpty(s2)) {
                try_no = "2";
                MubLog.cpnsoleLog("SaveData Try 2");
            } else if (StringUtils.isEmpty(s3)) {
                try_no = "3";
                MubLog.cpnsoleLog("SaveData Try 3");
            } else if (StringUtils.isEmpty(s4)) {
                try_no = "4";
                MubLog.cpnsoleLog("SaveData Try 4");
            } else if (StringUtils.isEmpty(s5)) {
                try_no = "5";
                MubLog.cpnsoleLog("SaveData Try 5");
            } else if (StringUtils.isEmpty(s6)) {
                try_no = "6";
                MubLog.cpnsoleLog("SaveData Try 6");
            }
            databaseAccess.savead_Section_A_Data(emp_id,
                    order_id,
                    farmer_id,
                    rcons_user,
                    enum_code,
                    enum_name,
                    isComplete,
                    isSynced,
                    insert_or_updated_in_phone_at,
                    deviceid,
                    build_no,
                    school_code,
                    student_id,
                    phone_number,
                    try_no,
                    a1,
                    b1,
                    b2,
                    a2,
                    a3,
                    a4,
                    a4_a,
                    a4_b,
                    a4_c,
                    a4_c_other,
                    a4_d,
                    a4_d_other,
                    a4_day,
                    a4_month,
                    a4_year,
                    a4_hh,
                    a4_mm,
                    a4_number,
                    a5,
                    a5_a,
                    a6,
                    a6_other,
                    a6_day,
                    a6_month,
                    a6_year,
                    a6_hh,
                    a6_mm,
                    a7);

        } catch (Exception e) {
            MubLog.cpnsoleLog("Data Save Error :" + e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void checkAndUpdateVariables() {

        try {


            if (a1.equalsIgnoreCase("1")) {
                a2 = "";
                rg_a2.clearCheck();
            }


            if (a1.equalsIgnoreCase("2")) {
                b1 = "";
                rg_b1.clearCheck();
                b2 = "";
                rg_b2.clearCheck();
                a3 = "";
                rg_a3.clearCheck();
                a4_a = "";
                edt_a4a.setText("");
                a4_b = "";
                rg_a4b.clearCheck();
                a4_c = "";
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }


            if (!a2.equalsIgnoreCase("")) {
                b1 = "";
                rg_b1.clearCheck();
                b2 = "";
                rg_b2.clearCheck();
                a3 = "";
                rg_a3.clearCheck();
                a4_a = "";
                edt_a4a.setText("");
                a4_b = "";
                rg_a4b.clearCheck();
                a4_c = "";
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }

            if (b1.equalsIgnoreCase("4")) {
                b2 = "";
                rg_b2.clearCheck();
                a3 = "";
                rg_a3.clearCheck();

            }

            if (b1.equalsIgnoreCase("3") || b1.equalsIgnoreCase("-777")) {
                b2 = "";
                rg_b2.clearCheck();
                a3 = "";
                rg_a3.clearCheck();
                a4_a = "";
                edt_a4a.setText("");
                a4_b = "";
                rg_a4b.clearCheck();
                a4_c = "";
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }

            if (b2.equalsIgnoreCase("4")) {
                a3 = "";
                rg_a3.clearCheck();
            }

            if (b2.equalsIgnoreCase("3") || b2.equalsIgnoreCase("-99") || b2.equalsIgnoreCase("-777")) {
                rg_a3.clearCheck();
                a4_a = "";
                edt_a4a.setText("");
                a4_b = "";
                rg_a4b.clearCheck();
                a4_c = "";
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }

            if (a3.equalsIgnoreCase("2") || a3.equalsIgnoreCase("-99") || a3.equalsIgnoreCase("-777")) {
                a4_a = "";
                edt_a4a.setText("");
                a4_b = "";
                rg_a4b.clearCheck();
                a4_c = "";
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }

            if (a4_b.equalsIgnoreCase("1")) {
                a4_c = "";
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
            }

            if (a4_b.equalsIgnoreCase("-99") || a4_b.equalsIgnoreCase("-777")) {
                rg_a4c.clearCheck();
                edt_a4c_other.setText("");
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }
            if (a4_c.equalsIgnoreCase("0") || a4_c.equalsIgnoreCase("-99") || a4_c.equalsIgnoreCase("-777")) {
                a4_d = "";
                rg_a4d.clearCheck();
                edt_a4d_other.setText("");
                a5 = "";
                rg_a5.clearCheck();
                a5_a = "";
                rg_a5a.clearCheck();
            }

        } catch (Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog("[pq_Section_A] inside checkAndUpdateVariables() Exception is :"+e.toString());
        }

    }

    void readFromDataBase() {
        try {
            Cursor cursor = null;
            if (StringUtils.isEmpty(s1)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, phone_number, "1");
                MubLog.cpnsoleLog("readFromDataBase Try 1");
            } else if (StringUtils.isEmpty(s2)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, phone_number, "2");
                MubLog.cpnsoleLog("readFromDataBase Try 2");
            } else if (StringUtils.isEmpty(s3)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, phone_number, "3");
                MubLog.cpnsoleLog("readFromDataBase Try 3");
            } else if (StringUtils.isEmpty(s4)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, phone_number, "4");
                MubLog.cpnsoleLog("readFromDataBase Try 4");
            } else if (StringUtils.isEmpty(s5)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, phone_number, "5");
                MubLog.cpnsoleLog("readFromDataBase Try 5");
            } else if (StringUtils.isEmpty(s6)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, phone_number, "6");
                MubLog.cpnsoleLog("readFromDataBase Try 6");
            }
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                a1 = cursor.getString(cursor.getColumnIndex("a1"));
                b1 = cursor.getString(cursor.getColumnIndex("b1"));
                b2 = cursor.getString(cursor.getColumnIndex("b2"));
                a2 = cursor.getString(cursor.getColumnIndex("a2"));
                a3 = cursor.getString(cursor.getColumnIndex("a3"));
                a4 = cursor.getString(cursor.getColumnIndex("a4"));
                a4_a = cursor.getString(cursor.getColumnIndex("a4_a"));
                a4_b = cursor.getString(cursor.getColumnIndex("a4_b"));
                a4_c = cursor.getString(cursor.getColumnIndex("a4_c"));
                a4_c_other = cursor.getString(cursor.getColumnIndex("a4_c_other"));
                a4_d = cursor.getString(cursor.getColumnIndex("a4_d"));
                a4_d_other = cursor.getString(cursor.getColumnIndex("a4_d_other"));
                a4_day = cursor.getString(cursor.getColumnIndex("a4_day"));
                a4_month = cursor.getString(cursor.getColumnIndex("a4_month"));
                a4_year = cursor.getString(cursor.getColumnIndex("a4_year"));
                a4_hh = cursor.getString(cursor.getColumnIndex("a4_hh"));
                a4_mm = cursor.getString(cursor.getColumnIndex("a4_mm"));
                a4_number = cursor.getString(cursor.getColumnIndex("a4_number"));
                a5 = cursor.getString(cursor.getColumnIndex("a5"));
                a5_a = cursor.getString(cursor.getColumnIndex("a5_a"));
                a6 = cursor.getString(cursor.getColumnIndex("a6"));
                a6_other = cursor.getString(cursor.getColumnIndex("a6_other"));
                a6_day = cursor.getString(cursor.getColumnIndex("a6_day"));
                a6_month = cursor.getString(cursor.getColumnIndex("a6_month"));
                a6_year = cursor.getString(cursor.getColumnIndex("a6_year"));
                a6_hh = cursor.getString(cursor.getColumnIndex("a6_hh"));
                a6_mm = cursor.getString(cursor.getColumnIndex("a6_mm"));
                a7 = cursor.getString(cursor.getColumnIndex("a7"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            MubLog.cpnsoleLog("Data Read Error :" + e.getMessage());
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {

            RConsUtils.setradiogroup(b1, rg_b1);
            RConsUtils.setradiogroup(b2, rg_b2);
            RConsUtils.setradiogroup(a1, rg_a1);
            RConsUtils.setradiogroup(a1, rg_a1);
            RConsUtils.setradiogroup(a2, rg_a2);
            RConsUtils.setradiogroup(a3, rg_a3);
            RConsUtils.setradiogroup(a4, rg_a4);
            RConsUtils.setradiogroup(a4_b, rg_a4b);
            RConsUtils.setradiogroup(a4_c, rg_a4c);
            RConsUtils.setradiogroup(a4_d, rg_a4d);
            RConsUtils.setradiogroup(a5, rg_a5);
            RConsUtils.setradiogroup(a5_a, rg_a5a);
            RConsUtils.setradiogroup(a6, rg_a6);
            RConsUtils.setotherEditText(edt_a6_other, a6_other);
            RConsUtils.setotherEditText(edt_a4_alt_number, a4_number);
            RConsUtils.setEditText(edt_a7, a7);
            RConsUtils.setEditText(edt_a4a, a4_a);

            RConsUtils.setotherEditText(edt_a4c_other, a4_c_other);
            RConsUtils.setotherEditText(edt_a4d_other, a4_d_other);

            if (!StringUtils.isEmpty(a6_hh) && !StringUtils.isEmpty(a6_mm)) {
                txt_a6_time.setText(a6_hh + ":" + a6_mm);
                txt_a6_date.setText(a6_month + "/" + a6_day + "/" + a6_year);
                txt_a6_time.setVisibility(View.VISIBLE);
                txt_a6_date.setVisibility(View.VISIBLE);
            } else {
                txt_a6_time.setVisibility(View.GONE);
                txt_a6_date.setVisibility(View.GONE);
            }


            if (!StringUtils.isEmpty(a4_hh) && !StringUtils.isEmpty(a4_mm)) {
                txt_a4_time.setText(a4_hh + ":" + a4_mm);
                txt_a4_date.setText(a4_month + "/" + a4_day + "/" + a4_year);
                txt_a4_time.setVisibility(View.VISIBLE);
                txt_a4_date.setVisibility(View.VISIBLE);
            } else {
                txt_a4_time.setVisibility(View.GONE);
                txt_a4_date.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            toastMessage(e.getMessage());
        }
    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    void SetEnumState() {

        if (RConsUtils.GetRegistrationState() == 1) {
            enum_name = RConsUtils.getEnumName();
            enum_code = RConsUtils.getEnumCode();
            rcons_user = RConsUtils.getUserName();
        }
    }

    void ShowDialMessage(final Context context, String title, String message, String str_btonok, String str_btnenum) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.delete_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        ImageView imageView1 = dialogView.findViewById(R.id.imageView1);
        imageView1.setVisibility(View.GONE);
        txtDialogTitle.setText(title);
        txtDialogTitle.setVisibility(View.GONE);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        txtErrorMessage.setVisibility(View.GONE);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnok = dialogView.findViewById(R.id.btnRconsUser);
        Button btnenum = dialogView.findViewById(R.id.btnenum);
        btnenum.setVisibility(View.VISIBLE);
        btnok.setText("Dial " + str_btonok);
        btnok.setTextSize(24);
        btnenum.setText("Dial " + str_btnenum);
        btnenum.setTextSize(24);
        btnCancel.setText("Cancel");
        btnCancel.setTextSize(24);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (qa1_layout.getVisibility() == View.VISIBLE) {
                    SaveInterviewStart_time();
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + str_btonok));
                startActivity(callIntent);

            }
        });
        btnenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (qa1_layout.getVisibility() == View.VISIBLE) {
                    SaveInterviewStart_time();
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + str_btnenum));
                startActivity(callIntent);

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }

    void displayHeaderName() {
        String headName = HouseHoldDataBaseHelper.getDataBaseProcessor(Ad_Section_A.this).aghhid_get_member_selected_in_e13(Ad_Section_A.this, school_code, student_id);

        if (headName != null) {

            if (headName.length() > 0) {

            } else {
                headName = HouseHoldDataBaseHelper.getDataBaseProcessor(Ad_Section_A.this).aghhid_get_member_selected_in_e1(Ad_Section_A.this, school_code, student_id);
            }

        }
        adol_name.setText("Adolescent Name : " + headName);

        section_a_question_3.setText("کیا آپ   " + headName + " کے گھرانے سے ہیں، آپ کی عمر 18سال سے اوپر ہے اور اپنے گھرانے کی فیصلہ سازی کرتے ہیں۔ ");
        section_a_question_4.setText("کیا  آپ    " + headName + " کے    والد / والدہ  یا  سرپرست سے میر ی   بات  کروا سکتے ہیں ؟");

        section_b_question_q1.setText("کیا  آپ     " + headName + " بات کر رہی ہیں ؟ ");
        section_b_question_q2.setText("کیا میں    " + headName + "سے بات کر سکتی ہوں؟ ");
        adolescent_name = headName;
    }

    void readFromDataBasic() {
        try {
            Cursor cursor = databaseAccess.getBasic_Data(school_code, student_id);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                s1 = cursor.getString(cursor.getColumnIndex("sc1"));
                s2 = cursor.getString(cursor.getColumnIndex("sc2"));
                s3 = cursor.getString(cursor.getColumnIndex("sc3"));
                s4 = cursor.getString(cursor.getColumnIndex("sc4"));
                s5 = cursor.getString(cursor.getColumnIndex("sc5"));
                s6 = cursor.getString(cursor.getColumnIndex("sc6"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            MubLog.cpnsoleLog("Data Read Error :" + e.getMessage());
        }
    }

}