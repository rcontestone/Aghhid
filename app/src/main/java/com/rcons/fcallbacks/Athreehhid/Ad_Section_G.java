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
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_A;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_B;
import com.rcons.fcallbacks.Questionnaire.Q_sectionC;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Ad_Section_G extends AppCompatActivity {

    @BindView(R.id.rg_g1)
    RadioGroup rg_g1;
    @BindView(R.id.rg_g2)
    RadioGroup rg_g2;
    @BindView(R.id.rg_g3)
    RadioGroup rg_g3;
    @BindView(R.id.rg_g4)
    RadioGroup rg_g4;
    @BindView(R.id.rg_g5)
    RadioGroup rg_g5;
    @BindView(R.id.rg_g6)
    RadioGroup rg_g6;
    @BindView(R.id.rg_g7)
    RadioGroup rg_g7;
    @BindView(R.id.rg_g8)
    RadioGroup rg_g8;
    @BindView(R.id.rg_g_urdu_1)
    RadioGroup rg_g_urdu_1;
    @BindView(R.id.rg_g_urdu_2_a)
    RadioGroup rg_g_urdu_2_a;
    @BindView(R.id.rg_g_urdu_2_b)
    RadioGroup rg_g_urdu_2_b;
    @BindView(R.id.rg_g_urdu_3)
    RadioGroup rg_g_urdu_3;
    @BindView(R.id.rg_g_urdu_4)
    RadioGroup rg_g_urdu_4;


    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;


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
    @BindView(R.id.qg6_layout)
    LinearLayout qg6_layout;
    @BindView(R.id.qg7_layout)
    LinearLayout qg7_layout;
    @BindView(R.id.qg8_layout)
    LinearLayout qg8_layout;
    @BindView(R.id.qg_urdu_1_layout)
    LinearLayout qg_urdu_1_layout;
    @BindView(R.id.qg_urdu_2_a_layout)
    LinearLayout qg_urdu_2_a_layout;
    @BindView(R.id.qg_urdu_2_b_layout)
    LinearLayout qg_urdu_2_b_layout;
    @BindView(R.id.qg_urdu_3_layout)
    LinearLayout qg_urdu_3_layout;
    @BindView(R.id.qg_urdu_4_layout)
    LinearLayout qg_urdu_4_layout;

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
    String school_code = "";
    String student_id = "";

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
    String g_1 = "";
    String g_2 = "";
    String g_3 = "";
    String g_4 = "";
    String g_5 = "";
    String g_6 = "";
    String g_7 = "";
    String g_8 = "";
    String g_urdu_1 = "";
    String g_urdu_2_a = "";
    String g_urdu_2_b = "";
    String g_urdu_3 = "";
    String g_urdu_4 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_section_g);

        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(Ad_Section_G.this);
        databaseAccess.Open();


        phone_number = getIntent().getStringExtra("farmer_cellphone");
        emp_id = getIntent().getStringExtra("emp_id");
        id = getIntent().getStringExtra("id");
        village_id = getIntent().getStringExtra("school_code");
        hhid = getIntent().getStringExtra("student_id");
        student_name = getIntent().getStringExtra("student_name");
        school_name = getIntent().getStringExtra("school_name");

        txt_School_Code.setText("Village Code : " + village_id);
        txt_Student_id.setText("HH Id : " + hhid);


        SetEnumState();


        LoadPreviousData();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_g1_ID = rg_g1.getCheckedRadioButtonId();
                int rg_g2_ID = rg_g2.getCheckedRadioButtonId();
                int rg_g3_ID = rg_g3.getCheckedRadioButtonId();
                int rg_g4_ID = rg_g4.getCheckedRadioButtonId();
                int rg_g5_ID = rg_g5.getCheckedRadioButtonId();
                int rg_g6_ID = rg_g6.getCheckedRadioButtonId();
                int rg_g7_ID = rg_g7.getCheckedRadioButtonId();
                int rg_g8_ID = rg_g8.getCheckedRadioButtonId();
                int rg_g_urdu_1_ID = rg_g_urdu_1.getCheckedRadioButtonId();
                int rg_g_urdu_2_a_ID = rg_g_urdu_2_a.getCheckedRadioButtonId();
                int rg_g_urdu_2_b_ID = rg_g_urdu_2_b.getCheckedRadioButtonId();
                int rg_g_urdu_3_ID = rg_g_urdu_3.getCheckedRadioButtonId();
                int rg_g_urdu_4_ID = rg_g_urdu_4.getCheckedRadioButtonId();

                SaveData();

                if (qg1_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g1_ID);
                        g_1 = radioButton.getTag().toString();
                        if (g_1.equalsIgnoreCase("2") || g_1.equalsIgnoreCase("-88") || g_1.equalsIgnoreCase("-98")) {
                            g_2 = "";
                            g_3 = "";
                            g_4 = "";
                            g_5 = "";
                            rg_g2.clearCheck();
                            rg_g3.clearCheck();
                            rg_g4.clearCheck();
                            rg_g5.clearCheck();
                            RConsUtils.hideView(qg1_layout, qg6_layout);
                        } else if (g_1.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qg1_layout, qg2_layout);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg2_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g2_ID);
                        g_2 = radioButton.getTag().toString();
                        if (g_2.equalsIgnoreCase("2") || g_2.equalsIgnoreCase("-88") || g_2.equalsIgnoreCase("-98")) {
                            g_3 = "";
                            g_4 = "";
                            g_5 = "";
                            g_6 = "";
                            g_7 = "";
                            g_8 = "";
                            rg_g3.clearCheck();
                            rg_g4.clearCheck();
                            rg_g5.clearCheck();
                            rg_g6.clearCheck();
                            rg_g7.clearCheck();
                            rg_g8.clearCheck();
                            RConsUtils.hideView(qg2_layout, qg_urdu_1_layout);
                        } else if (g_2.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qg2_layout, qg3_layout);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }

                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qg3_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g3_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g3_ID);
                        g_3 = radioButton.getTag().toString();
                        if (g_3.equalsIgnoreCase("2") || g_3.equalsIgnoreCase("-88") || g_3.equalsIgnoreCase("-98")) {
                            g_4 = "";
                            g_5 = "";
                            g_6 = "";
                            g_7 = "";
                            g_8 = "";
                            rg_g4.clearCheck();
                            rg_g5.clearCheck();
                            rg_g6.clearCheck();
                            rg_g7.clearCheck();
                            rg_g8.clearCheck();
                            RConsUtils.hideView(qg3_layout, qg_urdu_1_layout);
                        } else if (g_3.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qg3_layout, qg4_layout);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g4_ID);
                        g_4 = radioButton.getTag().toString();
                        if (g_4.equalsIgnoreCase("2") || g_4.equalsIgnoreCase("-88") || g_4.equalsIgnoreCase("-98")) {
                            g_5 = "";
                            g_6 = "";
                            g_7 = "";
                            g_8 = "";
                            rg_g5.clearCheck();
                            rg_g6.clearCheck();
                            rg_g7.clearCheck();
                            rg_g8.clearCheck();
                            RConsUtils.hideView(qg4_layout, qg_urdu_1_layout);
                        } else if (g_4.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qg4_layout, qg5_layout);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg5_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g5_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g5_ID);
                        g_5 = radioButton.getTag().toString();
                        if (g_5.equalsIgnoreCase("-99") || g_5.equalsIgnoreCase("-777")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            RConsUtils.hideView(qg5_layout, qg6_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qg6_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g6_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g6_ID);
                        g_6 = radioButton.getTag().toString();
                        if (g_6.equalsIgnoreCase("-99") || g_6.equalsIgnoreCase("-777")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            RConsUtils.hideView(qg6_layout, qg7_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg7_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g7_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g7_ID);
                        g_7 = radioButton.getTag().toString();
                        if (g_7.equalsIgnoreCase("-99") || g_7.equalsIgnoreCase("-777")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            RConsUtils.hideView(qg7_layout, qg8_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg8_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g8_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g8_ID);
                        g_8 = radioButton.getTag().toString();
                        if (g_8.equalsIgnoreCase("-99") || g_8.equalsIgnoreCase("-777")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            RConsUtils.hideView(qg8_layout, qg_urdu_1_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg_urdu_1_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g_urdu_1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g_urdu_1_ID);
                        g_urdu_1 = radioButton.getTag().toString();
                        if (g_urdu_1.equalsIgnoreCase("-99") || g_urdu_1.equalsIgnoreCase("-777")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else if (g_urdu_1.equalsIgnoreCase("-98")) {
                            rg_g_urdu_2_a.clearCheck();
                            rg_g_urdu_2_b.clearCheck();
                            g_urdu_2_a = "";
                            g_urdu_2_b = "";
                            RConsUtils.hideView(qg_urdu_1_layout, qg_urdu_3_layout);
                        } else {
                            RConsUtils.hideView(qg_urdu_1_layout, qg_urdu_2_a_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qg_urdu_2_a_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g_urdu_2_a_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g_urdu_2_a_ID);
                        g_urdu_2_a = radioButton.getTag().toString();
                        if (g_urdu_2_a.equalsIgnoreCase("-777")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else if (g_urdu_2_a.equalsIgnoreCase("1")) {
                            RConsUtils.hideView(qg_urdu_2_a_layout, qg_urdu_2_b_layout);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, pq_Section_A.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            intent.putExtra("student_name", student_name);
                            intent.putExtra("school_name", school_name);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg_urdu_2_b_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g_urdu_2_b_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g_urdu_2_b_ID);
                        g_urdu_2_b = radioButton.getTag().toString();
                        if (g_urdu_2_b.equalsIgnoreCase("-777") || g_urdu_2_b.equalsIgnoreCase("-99")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, pq_Section_A.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            intent.putExtra("student_name", student_name);
                            intent.putExtra("school_name", school_name);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg_urdu_3_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g_urdu_3_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g_urdu_3_ID);
                        g_urdu_3 = radioButton.getTag().toString();
                        if (g_urdu_3.equalsIgnoreCase("-777") || g_urdu_3.equalsIgnoreCase("-99")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else if (g_urdu_3.equalsIgnoreCase("1")) {
                            Intent intent = new Intent(Ad_Section_G.this, pq_Section_A.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            intent.putExtra("student_name", student_name);
                            intent.putExtra("school_name", school_name);
                            startActivityForResult(intent, 88);
                        } else {
                            RConsUtils.hideView(qg_urdu_3_layout, qg_urdu_4_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qg_urdu_4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_g_urdu_4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_g_urdu_4_ID);
                        g_urdu_4 = radioButton.getTag().toString();
                        if (g_urdu_4.equalsIgnoreCase("-777") || g_urdu_4.equalsIgnoreCase("-99")) {
                            Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            startActivityForResult(intent, 88);
                        } else {
                            Intent intent = new Intent(Ad_Section_G.this, pq_Section_A.class);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_cellphone", phone_number);
                            intent.putExtra("school_code", school_code);
                            intent.putExtra("student_id", student_id);
                            intent.putExtra("student_name", student_name);
                            intent.putExtra("school_name", school_name);
                            startActivityForResult(intent, 88);
                        }
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

                Intent intent = new Intent(Ad_Section_G.this, AddReportActivity.class);
                intent.putExtra("emp_id", emp_id);
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

    void SetEnumState() {

        if (RConsUtils.GetRegistrationState() == 1) {
            enum_name = RConsUtils.getEnumName();
            enum_code = RConsUtils.getEnumCode();
            rcons_user = RConsUtils.getUserName();
        }
    }

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Ad_Section_G.this,
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
                    Toast.makeText(Ad_Section_G.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
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

    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            databaseAccess.savehh_Section_G_Data(isSynced,
                    rcons_user,
                    enum_code,
                    enum_name,
                    deviceid,
                    insert_or_updated_in_phone_at,
                    uploaded_time,
                    build_no,
                    village_id,
                    hhid,
                    g_1,
                    g_2,
                    g_3,
                    g_4,
                    g_5,
                    g_6,
                    g_7,
                    g_8,
                    g_urdu_1,
                    g_urdu_2_a,
                    g_urdu_2_b,
                    g_urdu_3,
                    g_urdu_4
            );
//
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.gethh_section_g_Data(village_id, hhid);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                g_1 = cursor.getString(cursor.getColumnIndex("g_1"));
                g_2 = cursor.getString(cursor.getColumnIndex("g_2"));
                g_3 = cursor.getString(cursor.getColumnIndex("g_3"));
                g_4 = cursor.getString(cursor.getColumnIndex("g_4"));
                g_5 = cursor.getString(cursor.getColumnIndex("g_5"));
                g_6 = cursor.getString(cursor.getColumnIndex("g_6"));
                g_7 = cursor.getString(cursor.getColumnIndex("g_7"));
                g_8 = cursor.getString(cursor.getColumnIndex("g_8"));
                g_urdu_1 = cursor.getString(cursor.getColumnIndex("g_urdu_1"));
                g_urdu_2_a = cursor.getString(cursor.getColumnIndex("g_urdu_2_a"));
                g_urdu_2_b = cursor.getString(cursor.getColumnIndex("g_urdu_2_b"));
                g_urdu_3 = cursor.getString(cursor.getColumnIndex("g_urdu_3"));
                g_urdu_4 = cursor.getString(cursor.getColumnIndex("g_urdu_4"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (qg_urdu_4_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg_urdu_4_layout, qg_urdu_3_layout);
        } else if (qg_urdu_3_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg_urdu_3_layout, qg_urdu_1_layout);
        } else if (qg_urdu_2_b_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg_urdu_2_b_layout, qg_urdu_2_a_layout);
        } else if (qg_urdu_2_a_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg_urdu_2_a_layout, qg_urdu_1_layout);
        } else if (qg_urdu_1_layout.getVisibility() == View.VISIBLE) {
            if (g_2.equalsIgnoreCase("2") || g_2.equalsIgnoreCase("-88") || g_2.equalsIgnoreCase("-98")) {
                RConsUtils.hideView(qg_urdu_1_layout, qg2_layout);
            } else if (g_3.equalsIgnoreCase("2") || g_3.equalsIgnoreCase("-88") || g_3.equalsIgnoreCase("-98") || g_3.equalsIgnoreCase("-99")) {
                RConsUtils.hideView(qg_urdu_1_layout, qg3_layout);
            } else if (g_4.equalsIgnoreCase("2") || g_4.equalsIgnoreCase("-88") || g_4.equalsIgnoreCase("-98") || g_3.equalsIgnoreCase("-99")) {
                RConsUtils.hideView(qg_urdu_1_layout, qg4_layout);
            } else {
                RConsUtils.hideView(qg_urdu_1_layout, qg8_layout);
            }
        } else if (qg8_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg8_layout, qg7_layout);
        } else if (qg7_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg7_layout, qg6_layout);
        } else if (qg6_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg6_layout, qg5_layout);
        } else if (qg5_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg5_layout, qg4_layout);
        } else if (qg4_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg4_layout, qg3_layout);
        } else if (qg3_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg3_layout, qg2_layout);
        } else if (qg2_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(qg2_layout, qg1_layout);
        } else if (qg1_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();
        }
    }

    void LoadPreviousData() {
        try {
            readFromDataBase();
            RConsUtils.setradiogroup(g_1, rg_g1);
            RConsUtils.setradiogroup(g_2, rg_g2);
            RConsUtils.setradiogroup(g_3, rg_g3);
            RConsUtils.setradiogroup(g_4, rg_g4);
            RConsUtils.setradiogroup(g_5, rg_g5);
            RConsUtils.setradiogroup(g_6, rg_g6);
            RConsUtils.setradiogroup(g_7, rg_g7);
            RConsUtils.setradiogroup(g_8, rg_g8);

            RConsUtils.setradiogroup(g_urdu_1, rg_g_urdu_1);
            RConsUtils.setradiogroup(g_urdu_2_a, rg_g_urdu_2_a);
            RConsUtils.setradiogroup(g_urdu_2_b, rg_g_urdu_2_b);
            RConsUtils.setradiogroup(g_urdu_3, rg_g_urdu_3);
            RConsUtils.setradiogroup(g_urdu_4, rg_g_urdu_4);

        } catch (Exception e) {
            toastMessage(e.getMessage());
            MubLog.cpnsoleLog(e.getMessage());
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
}