package com.rcons.fcallbacks.ParentalQuestionnaire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.TRT.trt_Section_1;
import com.rcons.fcallbacks.TRT.trt_Section_2;
import com.rcons.fcallbacks.TRT.trt_Section_3;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.rcons.fcallbacks.BuildConfig;

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideKeyboard;
import static com.rcons.fcallbacks.Utilties.RConsUtils.hideView;
import static com.rcons.fcallbacks.Utilties.RConsUtils.iif;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setCheckbox_load;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setotherEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class pq_Section_B extends AppCompatActivity {

    @BindView(R.id.qb0_layout)
    LinearLayout qb0_layout;
    @BindView(R.id.qb1_layout)
    LinearLayout qb1_layout;
    @BindView(R.id.qb2_layout)
    LinearLayout qb2_layout;
    @BindView(R.id.qb3_layout)
    LinearLayout qb3_layout;
    @BindView(R.id.b4_layout)
    LinearLayout b4_layout;
    @BindView(R.id.b5_layout)
    LinearLayout b5_layout;
    @BindView(R.id.qb6_layout)
    LinearLayout qb6_layout;
    @BindView(R.id.qb7_layout)
    LinearLayout qb7_layout;
    @BindView(R.id.qb8_layout)
    LinearLayout qb8_layout;
    @BindView(R.id.qb9_layout)
    LinearLayout qb9_layout;
    @BindView(R.id.qb10_layout)
    LinearLayout qb10_layout;
    @BindView(R.id.qb11_layout)
    LinearLayout qb11_layout;


    @BindView(R.id.rg_b1)
    RadioGroup rg_b1;
    @BindView(R.id.rg_b2)
    RadioGroup rg_b2;
    @BindView(R.id.rg_b3)
    RadioGroup rg_b3;
    @BindView(R.id.rg_b6)
    RadioGroup rg_b6;
    @BindView(R.id.rg_b8)
    RadioGroup rg_b8;
    @BindView(R.id.rg_b11)
    RadioGroup rg_b11;

    @BindView(R.id.rg_b5)
    RadioGroup rg_b5;


    @BindView(R.id.edt_b0)
    EditText edt_b0;
    @BindView(R.id.edt_b1_other)
    EditText edt_b1_other;
    @BindView(R.id.edt_b4_a)
    EditText edt_b4_a;
    @BindView(R.id.edt_b4_b)
    EditText edt_b4_b;

    @BindView(R.id.edt_b10_other)
    EditText edt_b10_other;


    @BindView(R.id.checkbox_qb7_0)
    CheckBox checkbox_qb7_0;
    @BindView(R.id.checkbox_qb7_1)
    CheckBox checkbox_qb7_1;
    @BindView(R.id.checkbox_qb7_2)
    CheckBox checkbox_qb7_2;
    @BindView(R.id.checkbox_qb7_3)
    CheckBox checkbox_qb7_3;
    @BindView(R.id.checkbox_qb7_4)
    CheckBox checkbox_qb7_4;
    @BindView(R.id.checkbox_qb7_5)
    CheckBox checkbox_qb7_5;
    @BindView(R.id.checkbox_qb7_6)
    CheckBox checkbox_qb7_6;
    @BindView(R.id.checkbox_qb7_7)
    CheckBox checkbox_qb7_7;
    @BindView(R.id.checkbox_qb9_1)
    CheckBox checkbox_qb9_1;
    @BindView(R.id.checkbox_qb9_2)
    CheckBox checkbox_qb9_2;
    @BindView(R.id.checkbox_qb9_3)
    CheckBox checkbox_qb9_3;
    @BindView(R.id.checkbox_qb9_4)
    CheckBox checkbox_qb9_4;
    @BindView(R.id.checkbox_qb9_5)
    CheckBox checkbox_qb9_5;


    @BindView(R.id.checkbox_b10_0)
    CheckBox checkbox_b10_0;

    @BindView(R.id.checkbox_b10_1)
    CheckBox checkbox_b10_1;

    @BindView(R.id.checkbox_b10_2)
    CheckBox checkbox_b10_2;

    @BindView(R.id.checkbox_b10_3)
    CheckBox checkbox_b10_3;

    @BindView(R.id.checkbox_b10_4)
    CheckBox checkbox_b10_4;

    @BindView(R.id.checkbox_b10_5)
    CheckBox checkbox_b10_5;

    @BindView(R.id.checkbox_b10_99)
    CheckBox checkbox_b10_99;

    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;


    @BindView(R.id.txt_section_b_question_b3)
    TextView txt_section_b_question_b3;

    @BindView(R.id.txt_section_b_question_b2)
    TextView txt_section_b_question_b2;

    @BindView(R.id.txt_section_b_question_b5)
    TextView txt_section_b_question_b5;

    @BindView(R.id.txt_section_b_question_b6)
    TextView txt_section_b_question_b6;

    @BindView(R.id.txt_section_b_question_b11)
    TextView txt_section_b_question_b11;

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
    String b0 = "";
    String b1 = "";
    String b1_other = "";
    String b2 = "";
    String b3 = "";
    String b4_a = "";
    String b4_b = "";
    String b5 = "";
    String b6 = "";
    String b7_0 = "";
    String b7_1 = "";
    String b7_2 = "";
    String b7_3 = "";
    String b7_4 = "";
    String b7_5 = "";
    String b7_6 = "";
    String b7_7 = "";
    String b8 = "";
    String b9_1 = "";
    String b9_2 = "";
    String b9_3 = "";
    String b9_4 = "";
    String b9_5 = "";
    String b10_0 = "";
    String b10_1 = "";
    String b10_2 = "";
    String b10_3 = "";
    String b10_4 = "";
    String b10_5 = "";
    String b10_99 = "";
    String b10_other = "";
    String b11 = "";

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_b);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_B.this);
        databaseAccess.Open();

        SetEnumState();

        phone_number = getIntent().getStringExtra("farmer_cellphone");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        farmer_id = getIntent().getStringExtra("farmerID");
        id = getIntent().getStringExtra("id");
        school_code = getIntent().getStringExtra("school_code");
        student_id = getIntent().getStringExtra("student_id");
        student_name = getIntent().getStringExtra("student_name");
        school_name = getIntent().getStringExtra("school_name");

        txt_section_b_question_b2.setText("سکول بند ہونے سے پہلے  کیا " + student_name + " سکول میں داخل تھی / تھا ؟");
        txt_section_b_question_b3.setText("کیا " + student_name + " سکول " + school_name + " میں داخل تھی / تھا ؟");
        txt_section_b_question_b5.setText(student_name + "کس جماعت میں  داخل  تھی/ تھا ؟ ");
        txt_section_b_question_b6.setText("جب دوبارہ سکول  کھلیں گے   تو کیا آپ " + student_name + " کو   دوبارہ سکول بھیجنے کا ارادہ  (منصوبہ) رکھتے ہیں ؟ ");
        txt_section_b_question_b11.setText("کیا " + student_name + "  گھر پر سکول کی تعلیم   کے کسی پروگرام  جو کہ ریڈیو  ٹیلی ویژن یا انٹرنیٹ پر  پڑھا رہے ہیں میں  شامل ہے؟  ");

        txt_School_Code.setText("School Code : " + school_code);
        txt_Student_id.setText("Student Id : " + student_id);

        LoadPreviousData();
        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;

        rg_b1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_b1.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_b1_99) {
                    edt_b1_other.setVisibility(View.VISIBLE);
                    edt_b1_other.requestFocus();
                } else {
                    edt_b1_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_B.this);
                    edt_b1_other.setText("");
                    b1_other = "";
                }
            }
        });
        checkbox_b10_99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_99.isChecked()) {
                    checkbox_b10_0.setChecked(false);
                    edt_b10_other.setVisibility(View.VISIBLE);
                    edt_b10_other.requestFocus();
                } else {
                    edt_b10_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_B.this);
                    edt_b10_other.setText("");
                    b10_other = "";
                }
            }
        });


        checkbox_b10_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_0.isChecked()) {
                    checkbox_b10_1.setChecked(false);
                    checkbox_b10_2.setChecked(false);
                    checkbox_b10_3.setChecked(false);
                    checkbox_b10_4.setChecked(false);
                    checkbox_b10_5.setChecked(false);
                    checkbox_b10_99.setChecked(false);
                    edt_b10_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_B.this);
                    edt_b10_other.setText("");
                    b10_other = "";
                } else {

                }
            }
        });

        checkbox_b10_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_1.isChecked()) {
                    checkbox_b10_0.setChecked(false);
                } else {

                }
            }
        });

        checkbox_b10_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_2.isChecked()) {
                    checkbox_b10_0.setChecked(false);
                } else {

                }
            }
        });
        checkbox_b10_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_3.isChecked()) {
                    checkbox_b10_0.setChecked(false);
                } else {

                }
            }
        });
        checkbox_b10_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_4.isChecked()) {
                    checkbox_b10_0.setChecked(false);
                } else {

                }
            }
        });

        checkbox_b10_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_b10_5.isChecked()) {
                    checkbox_b10_0.setChecked(false);
                } else {

                }
            }
        });


        checkbox_qb7_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_0.isChecked()) {
                    checkbox_qb7_1.setChecked(false);
                    checkbox_qb7_2.setChecked(false);
                    checkbox_qb7_3.setChecked(false);
                    checkbox_qb7_4.setChecked(false);
                    checkbox_qb7_5.setChecked(false);
                    checkbox_qb7_6.setChecked(false);
                    checkbox_qb7_7.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qb7_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_1.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });
        checkbox_qb7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_2.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });
        checkbox_qb7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_3.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });

        checkbox_qb7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_4.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });

        checkbox_qb7_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_5.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });

        checkbox_qb7_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_6.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });

        checkbox_qb7_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qb7_7.isChecked()) {
                    checkbox_qb7_0.setChecked(false);

                } else {

                }
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_b1_ID = rg_b1.getCheckedRadioButtonId();
                int rg_b2_ID = rg_b2.getCheckedRadioButtonId();
                int rg_b3_ID = rg_b3.getCheckedRadioButtonId();
                int rg_b5_ID = rg_b5.getCheckedRadioButtonId();
                int rg_b6_ID = rg_b6.getCheckedRadioButtonId();
                int rg_b8_ID = rg_b8.getCheckedRadioButtonId();
                int rg_b11_ID = rg_b11.getCheckedRadioButtonId();
                RConsUtils.hideKeyboard(pq_Section_B.this);
                SaveData();
                if (qb0_layout.getVisibility() == View.VISIBLE) {
                    b0 = edt_b0.getText().toString();
                    if (!StringUtils.isEmpty(b0)) {
                        hideView(qb0_layout, qb1_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }

                } else if (qb1_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b1_ID);
                        b1 = radioButton.getTag().toString();
                        if (rg_b1_ID == R.id.rbtn_b1_99) {
                            b1_other = edt_b1_other.getText().toString();
                            if (!StringUtils.isEmpty(b1_other)) {
                                hideView(qb1_layout, qb2_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            b1_other = "";
                            edt_b1_other.setText("");
                            if (rg_b1_ID == R.id.rbtn_b1_3 || rg_b1_ID == R.id.rbtn_b1_4) {
                                hideView(qb1_layout, qb2_layout);
                            } else {
                                hideView(qb1_layout, qb2_layout);
                            }
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qb2_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b2_ID);
                        b2 = radioButton.getTag().toString();
                        if (b2.equalsIgnoreCase("0")) {
                            b3 = "";
                            rg_b3.clearCheck();
                            b4_a = "";
                            b4_b = "";
                            edt_b4_a.setText("");
                            edt_b4_b.setText("");
                            b5 = "";
                            rg_b5.clearCheck();
                            hideView(qb2_layout, qb6_layout);
                        } else {
                            hideView(qb2_layout, qb3_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qb3_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b3_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b3_ID);
                        b3 = radioButton.getTag().toString();
                        if (b3.equalsIgnoreCase("1")) {
                            b4_a = "";
                            b4_b = "";
                            edt_b4_a.setText("");
                            edt_b4_b.setText("");
                            hideView(qb3_layout, b5_layout);
                        } else {
                            hideView(qb3_layout, b4_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (b4_layout.getVisibility() == View.VISIBLE) {
                    b4_a = edt_b4_a.getText().toString();
                    b4_b = edt_b4_b.getText().toString();
                    if (!StringUtils.isEmpty(b4_a) && !StringUtils.isEmpty(b4_b)) {
                        hideView(b4_layout, b5_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }

                } else if (b5_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b5_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b5_ID);
                        b5 = radioButton.getTag().toString();
                        hideView(b5_layout, qb6_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qb6_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b6_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b6_ID);
                        b6 = radioButton.getTag().toString();
                        hideView(qb6_layout, qb7_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qb7_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_qb7_0.isChecked() || checkbox_qb7_1.isChecked() || checkbox_qb7_2.isChecked() || checkbox_qb7_3.isChecked()
                            || checkbox_qb7_4.isChecked() || checkbox_qb7_5.isChecked() || checkbox_qb7_6.isChecked() || checkbox_qb7_7.isChecked()) {
                        hideView(qb7_layout, qb8_layout);
                    } else {
                        toastMessage("You Can Select Multiple....");
                    }
                } else if (qb8_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b8_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b8_ID);
                        b8 = radioButton.getTag().toString();
                        if (b8.equalsIgnoreCase("0")) {
                            b9_1 = "";
                            checkbox_qb9_1.setChecked(false);
                            b9_2 = "";
                            checkbox_qb9_2.setChecked(false);
                            b9_3 = "";
                            checkbox_qb9_3.setChecked(false);
                            b9_4 = "";
                            checkbox_qb9_4.setChecked(false);
                            b9_5 = "";
                            checkbox_qb9_5.setChecked(false);
                            hideView(qb8_layout, qb10_layout);
                        } else {
                            hideView(qb8_layout, qb9_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qb9_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_qb9_1.isChecked() || checkbox_qb9_2.isChecked() || checkbox_qb9_3.isChecked()
                            || checkbox_qb9_4.isChecked() || checkbox_qb9_5.isChecked()) {
                        hideView(qb9_layout, qb10_layout);
                    } else {
                        toastMessage("You Can Select Multiple....");
                    }
                } else if (qb10_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_b10_0.isChecked()
                            || checkbox_b10_1.isChecked()
                            || checkbox_b10_2.isChecked()
                            || checkbox_b10_3.isChecked()
                            || checkbox_b10_4.isChecked()
                            || checkbox_b10_5.isChecked()
                            || checkbox_b10_99.isChecked()) {
                        if (checkbox_b10_0.isChecked()) {
                            SaveData();
                            Intent intent = new Intent(pq_Section_B.this, pq_Section_C1.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            intent.putExtra("student_name", student_name);
                            intent.putExtra("school_name", school_name);
                            startActivityForResult(intent, 88);
                        } else if (checkbox_b10_99.isChecked()) {
                            b10_other = edt_b10_other.getText().toString();
                            if (!StringUtils.isEmpty(b10_other)) {
                                hideView(qb10_layout, qb11_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            hideView(qb10_layout, qb11_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple....");
                    }
                } else if (qb11_layout.getVisibility() == View.VISIBLE) {
                    if (rg_b11_ID > 0) {
                        RadioButton radioButton = findViewById(rg_b11_ID);
                        b11 = radioButton.getTag().toString();
                        SaveData();
                        Intent intent = new Intent(pq_Section_B.this, pq_Section_C1.class);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", phone_number);
                        intent.putExtra("school_code", school_code);
                        intent.putExtra("student_id", student_id);
                        intent.putExtra("student_name", student_name);
                        intent.putExtra("school_name", school_name);
                        startActivityForResult(intent, 88);
                    } else {
                        toastMessage("Please Select Option");
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

                Intent intent = new Intent(pq_Section_B.this, AddReportActivity.class);
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

        if (qb11_layout.getVisibility() == View.VISIBLE) {
            hideView(qb11_layout, qb10_layout);
        } else if (qb10_layout.getVisibility() == View.VISIBLE) {
            if (b8.equalsIgnoreCase("0")) {
                hideView(qb10_layout, qb8_layout);
            } else {
                hideView(qb10_layout, qb9_layout);
            }
        } else if (qb9_layout.getVisibility() == View.VISIBLE) {
            hideView(qb9_layout, qb8_layout);
        } else if (qb8_layout.getVisibility() == View.VISIBLE) {
            hideView(qb8_layout, qb7_layout);
        } else if (qb7_layout.getVisibility() == View.VISIBLE) {
            hideView(qb7_layout, qb6_layout);
        } else if (qb6_layout.getVisibility() == View.VISIBLE) {
            if (b2.equalsIgnoreCase("0")) {
                hideView(qb6_layout, qb2_layout);
            } else {
                hideView(qb6_layout, b5_layout);
            }
        } else if (b5_layout.getVisibility() == View.VISIBLE) {
            if (b3.equalsIgnoreCase("1")) {
                hideView(b5_layout, qb3_layout);
            } else {
                hideView(b5_layout, b4_layout);
            }
        } else if (b4_layout.getVisibility() == View.VISIBLE) {
            hideView(b4_layout, qb3_layout);
        } else if (qb3_layout.getVisibility() == View.VISIBLE) {
            hideView(qb3_layout, qb2_layout);
        } else if (qb2_layout.getVisibility() == View.VISIBLE) {
            hideView(qb2_layout, qb1_layout);
        } else if (qb1_layout.getVisibility() == View.VISIBLE) {
            hideView(qb1_layout, qb0_layout);
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
        if (ActivityCompat.checkSelfPermission(pq_Section_B.this,
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
//                phoneNumber = "880" + phoneNumber;
            } else {
                phoneNumber = "770" + phoneNumber;
            }
        } else {

        }
        if (network.equalsIgnoreCase("Telenor")) {
            ShowDialMessage(pq_Section_B.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
        } else {
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
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

        }

    }


    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            SetCheboxesTags();
            databaseAccess.savepq_Section_B_Data(emp_id,
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
                    b0,
                    b1,
                    b1_other,
                    b2,
                    b3,
                    b4_a,
                    b4_b,
                    b5,
                    b6,
                    b7_0,
                    b7_1,
                    b7_2,
                    b7_3,
                    b7_4,
                    b7_5,
                    b7_6,
                    b7_7,
                    b8,
                    b9_1,
                    b9_2,
                    b9_3,
                    b9_4,
                    b9_5,
                    b10_0,
                    b10_1,
                    b10_2,
                    b10_3,
                    b10_4,
                    b10_5,
                    b10_99,
                    b10_other,
                    b11);
//
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getpq_section_b_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                b0 = cursor.getString(cursor.getColumnIndex("b0"));
                b1 = cursor.getString(cursor.getColumnIndex("b1"));
                b1_other = cursor.getString(cursor.getColumnIndex("b1_other"));
                b2 = cursor.getString(cursor.getColumnIndex("b2"));
                b3 = cursor.getString(cursor.getColumnIndex("b3"));
                b4_a = cursor.getString(cursor.getColumnIndex("b4_a"));
                b4_b = cursor.getString(cursor.getColumnIndex("b4_b"));
                b5 = cursor.getString(cursor.getColumnIndex("b5"));
                b6 = cursor.getString(cursor.getColumnIndex("b6"));
                b7_0 = cursor.getString(cursor.getColumnIndex("b7_0"));
                b7_1 = cursor.getString(cursor.getColumnIndex("b7_1"));
                b7_2 = cursor.getString(cursor.getColumnIndex("b7_2"));
                b7_3 = cursor.getString(cursor.getColumnIndex("b7_3"));
                b7_4 = cursor.getString(cursor.getColumnIndex("b7_4"));
                b7_5 = cursor.getString(cursor.getColumnIndex("b7_5"));
                b7_6 = cursor.getString(cursor.getColumnIndex("b7_6"));
                b7_7 = cursor.getString(cursor.getColumnIndex("b7_7"));
                b8 = cursor.getString(cursor.getColumnIndex("b8"));
                b9_1 = cursor.getString(cursor.getColumnIndex("b9_1"));
                b9_2 = cursor.getString(cursor.getColumnIndex("b9_2"));
                b9_3 = cursor.getString(cursor.getColumnIndex("b9_3"));
                b9_4 = cursor.getString(cursor.getColumnIndex("b9_4"));
                b9_5 = cursor.getString(cursor.getColumnIndex("b9_5"));
                b10_0 = cursor.getString(cursor.getColumnIndex("b10_0"));
                b10_1 = cursor.getString(cursor.getColumnIndex("b10_1"));
                b10_2 = cursor.getString(cursor.getColumnIndex("b10_2"));
                b10_3 = cursor.getString(cursor.getColumnIndex("b10_3"));
                b10_4 = cursor.getString(cursor.getColumnIndex("b10_4"));
                b10_5 = cursor.getString(cursor.getColumnIndex("b10_5"));
                b10_99 = cursor.getString(cursor.getColumnIndex("b10_99"));
                b10_other = cursor.getString(cursor.getColumnIndex("b10_other"));
                b11 = cursor.getString(cursor.getColumnIndex("b11"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            ////////////////////////////////////// RadioGroup //////////////////////
            setradiogroup(b1, rg_b1);
            setradiogroup(b2, rg_b2);
            setradiogroup(b3, rg_b3);
            setradiogroup(b5, rg_b5);
            setradiogroup(b6, rg_b6);
            setradiogroup(b8, rg_b8);
            setradiogroup(b11, rg_b11);
            ////////////////////////////////////// Edit Text //////////////////////
            setEditText(edt_b0, b0);
            setEditText(edt_b4_a, b4_a);
            setEditText(edt_b4_b, b4_b);

            ////////////////////////////////////// OtherEditText //////////////////////
            setotherEditText(edt_b1_other, b1_other);
            setotherEditText(edt_b10_other, b10_other);
            ////////////////////////////////////// CheckBox //////////////////////
            setCheckbox_load(checkbox_qb7_0, b7_0);
            setCheckbox_load(checkbox_qb7_1, b7_1);
            setCheckbox_load(checkbox_qb7_2, b7_2);
            setCheckbox_load(checkbox_qb7_3, b7_3);
            setCheckbox_load(checkbox_qb7_4, b7_4);
            setCheckbox_load(checkbox_qb7_5, b7_5);
            setCheckbox_load(checkbox_qb7_6, b7_6);
            setCheckbox_load(checkbox_qb7_7, b7_7);

            setCheckbox_load(checkbox_qb9_1, b9_1);
            setCheckbox_load(checkbox_qb9_2, b9_2);
            setCheckbox_load(checkbox_qb9_3, b9_3);
            setCheckbox_load(checkbox_qb9_4, b9_4);
            setCheckbox_load(checkbox_qb9_5, b9_5);

            setCheckbox_load(checkbox_b10_0, b10_0);
            setCheckbox_load(checkbox_b10_1, b10_1);
            setCheckbox_load(checkbox_b10_2, b10_2);
            setCheckbox_load(checkbox_b10_3, b10_3);
            setCheckbox_load(checkbox_b10_4, b10_4);
            setCheckbox_load(checkbox_b10_5, b10_5);
            setCheckbox_load(checkbox_b10_99, b10_99);


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

    public static <T> T iif(boolean test, T ifTrue, T ifFalse) {
        return test ? ifTrue : ifFalse;
    }

    void SetCheboxesTags() {
        b7_0 = iif(checkbox_qb7_0.isChecked(), "1", "");
        b7_1 = iif(checkbox_qb7_1.isChecked(), "1", "");
        b7_2 = iif(checkbox_qb7_2.isChecked(), "1", "");
        b7_3 = iif(checkbox_qb7_3.isChecked(), "1", "");
        b7_4 = iif(checkbox_qb7_4.isChecked(), "1", "");
        b7_5 = iif(checkbox_qb7_5.isChecked(), "1", "");
        b7_6 = iif(checkbox_qb7_6.isChecked(), "1", "");
        b7_7 = iif(checkbox_qb7_7.isChecked(), "1", "");

        b9_1 = iif(checkbox_qb9_1.isChecked(), "1", "");
        b9_2 = iif(checkbox_qb9_2.isChecked(), "1", "");
        b9_3 = iif(checkbox_qb9_3.isChecked(), "1", "");
        b9_4 = iif(checkbox_qb9_4.isChecked(), "1", "");
        b9_5 = iif(checkbox_qb9_5.isChecked(), "1", "");

        b10_0 = iif(checkbox_b10_0.isChecked(), "1", "");
        b10_1 = iif(checkbox_b10_1.isChecked(), "1", "");
        b10_2 = iif(checkbox_b10_2.isChecked(), "1", "");
        b10_3 = iif(checkbox_b10_3.isChecked(), "1", "");
        b10_4 = iif(checkbox_b10_4.isChecked(), "1", "");
        b10_5 = iif(checkbox_b10_5.isChecked(), "1", "");
        b10_99 = iif(checkbox_b10_99.isChecked(), "1", "");
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
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + str_btonok));
                startActivity(callIntent);

            }
        });
        btnenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
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
}