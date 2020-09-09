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
import com.rcons.fcallbacks.BuildConfig;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideView;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setCheckbox_load;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setotherEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class pq_Section_D extends AppCompatActivity {

    @BindView(R.id.qd1_layout)
    LinearLayout qd1_layout;
    @BindView(R.id.d2_layout)
    LinearLayout d2_layout;
    @BindView(R.id.d3_layout)
    LinearLayout d3_layout;
    @BindView(R.id.qd4_layout)
    LinearLayout qd4_layout;
    @BindView(R.id.qd5_layout)
    LinearLayout qd5_layout;
    @BindView(R.id.qd6_layout)
    LinearLayout qd6_layout;
    @BindView(R.id.qd7_layout)
    LinearLayout qd7_layout;
    @BindView(R.id.qd8_layout)
    LinearLayout qd8_layout;
    @BindView(R.id.qd9_layout)
    LinearLayout qd9_layout;


    @BindView(R.id.rg_d4)
    RadioGroup rg_d4;
    @BindView(R.id.rg_d7)
    RadioGroup rg_d7;

    @BindView(R.id.edt_d2)
    EditText edt_d2;
    @BindView(R.id.edt_d3)
    EditText edt_d3;
    @BindView(R.id.edt_d6)
    EditText edt_d6;
    @BindView(R.id.edt_d8_other)
    EditText edt_d8_other;

    @BindView(R.id.edt_d5_other)
    EditText edt_d5_other;


    @BindView(R.id.checkbox_qd1_1)
    CheckBox checkbox_qd1_1;
    @BindView(R.id.checkbox_qd1_2)
    CheckBox checkbox_qd1_2;
    @BindView(R.id.checkbox_qd1_3)
    CheckBox checkbox_qd1_3;
    @BindView(R.id.checkbox_qd1_0)
    CheckBox checkbox_qd1_0;
    @BindView(R.id.checkbox_qd8_1)
    CheckBox checkbox_qd8_1;
    @BindView(R.id.checkbox_qd8_2)
    CheckBox checkbox_qd8_2;
    @BindView(R.id.checkbox_qd8_3)
    CheckBox checkbox_qd8_3;
    @BindView(R.id.checkbox_qd8_4)
    CheckBox checkbox_qd8_4;
    @BindView(R.id.checkbox_qd8_5)
    CheckBox checkbox_qd8_5;
    @BindView(R.id.checkbox_qd8_99)
    CheckBox checkbox_qd8_99;
    @BindView(R.id.checkbox_qd9_0)
    CheckBox checkbox_qd9_0;
    @BindView(R.id.checkbox_qd9_1)
    CheckBox checkbox_qd9_1;
    @BindView(R.id.checkbox_qd9_2)
    CheckBox checkbox_qd9_2;
    @BindView(R.id.checkbox_qd9_3)
    CheckBox checkbox_qd9_3;
    @BindView(R.id.checkbox_qd9_98)
    CheckBox checkbox_qd9_98;
    @BindView(R.id.checkbox_qd9_97)
    CheckBox checkbox_qd9_97;


    @BindView(R.id.checkbox_qd5_0)
    CheckBox checkbox_qd5_0;

    @BindView(R.id.checkbox_qd5_1)
    CheckBox checkbox_qd5_1;

    @BindView(R.id.checkbox_qd5_2)
    CheckBox checkbox_qd5_2;

    @BindView(R.id.checkbox_qd5_3)
    CheckBox checkbox_qd5_3;

    @BindView(R.id.checkbox_qd5_4)
    CheckBox checkbox_qd5_4;

    @BindView(R.id.checkbox_qd5_99)
    CheckBox checkbox_qd5_99;

    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

    @BindView(R.id.txt_section_D_d1)
    TextView txt_section_D_d1;

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
    String d1_0 = "";
    String d1_1 = "";
    String d1_2 = "";
    String d1_3 = "";
    String d2 = "";
    String d3 = "";
    String d4 = "";
    String d5_0 = "";
    String d5_1 = "";
    String d5_2 = "";
    String d5_3 = "";
    String d5_4 = "";
    String d5_99 = "";
    String d5_other = "";
    String d6 = "";
    String d7 = "";
    String d8_1 = "";
    String d8_2 = "";
    String d8_3 = "";
    String d8_4 = "";
    String d8_5 = "";
    String d8_99 = "";
    String d8_other = "";
    String d9_0 = "";
    String d9_1 = "";
    String d9_2 = "";
    String d9_3 = "";
    String d9_98 = "";
    String d9_97 = "";

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_d);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_D.this);
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

        txt_section_D_d1.setText("سکول  بند ہونے سے پہلے  اور   سکو ل بند ہونے کے بعد کیا " + student_name + "سکول کے  اوقات  کے  بعد   ٹیوشن  پڑھتی تھی / تھا ؟ ");

        txt_School_Code.setText("School Code : " + school_code);
        txt_Student_id.setText("Student Id : " + student_id);

        LoadPreviousData();
        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;


        checkbox_qd1_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd1_0.isChecked()) {
                    d1_1 = "";
                    d1_2 = "";
                    d1_3 = "";
                    checkbox_qd1_1.setChecked(false);
                    checkbox_qd1_2.setChecked(false);
                    checkbox_qd1_3.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd1_1.isChecked()) {
                    d1_0 = "";
                    checkbox_qd1_0.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd1_2.isChecked()) {
                    d1_0 = "";
                    checkbox_qd1_0.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd1_3.isChecked()) {
                    d1_0 = "";
                    checkbox_qd1_0.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd5_99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd5_99.isChecked()) {
                    edt_d5_other.setVisibility(View.VISIBLE);
                    edt_d5_other.requestFocus();
                    checkbox_qd5_0.setChecked(false);
                } else {
                    edt_d5_other.setVisibility(View.GONE);
                    edt_d5_other.setText("");
                    d5_other = "";
                }
            }
        });
        checkbox_qd5_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd5_0.isChecked()) {
                    checkbox_qd5_1.setChecked(false);
                    checkbox_qd5_2.setChecked(false);
                    checkbox_qd5_3.setChecked(false);
                    checkbox_qd5_4.setChecked(false);
                    checkbox_qd5_99.setChecked(false);
                    edt_d5_other.setText("");
                    edt_d5_other.setVisibility(View.GONE);
                } else {

                }
            }
        });

        checkbox_qd5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd5_1.isChecked()) {
                    checkbox_qd5_0.setChecked(false);
                } else {

                }
            }
        });


        checkbox_qd5_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd5_2.isChecked()) {
                    checkbox_qd5_0.setChecked(false);
                } else {

                }
            }
        });

        checkbox_qd5_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd5_3.isChecked()) {
                    checkbox_qd5_0.setChecked(false);
                } else {

                }
            }
        });

        checkbox_qd5_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd5_4.isChecked()) {
                    checkbox_qd5_0.setChecked(false);
                } else {

                }
            }
        });


        checkbox_qd8_99.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd8_99.isChecked()) {
                    edt_d8_other.setVisibility(View.VISIBLE);
                    edt_d8_other.requestFocus();
                } else {
                    edt_d8_other.setVisibility(View.GONE);
                    edt_d8_other.setText("");
                    d8_other = "";
                }
            }
        });

        checkbox_qd9_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd9_0.isChecked()) {
                    checkbox_qd9_1.setChecked(false);
                    checkbox_qd9_2.setChecked(false);
                    checkbox_qd9_3.setChecked(false);
                    checkbox_qd9_98.setChecked(false);
                    checkbox_qd9_97.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd9_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd9_1.isChecked()) {
                    checkbox_qd9_0.setChecked(false);
                    checkbox_qd9_98.setChecked(false);
                    checkbox_qd9_97.setChecked(false);
                } else {

                }
            }
        });

        checkbox_qd9_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd9_2.isChecked()) {
                    checkbox_qd9_0.setChecked(false);
                    checkbox_qd9_98.setChecked(false);
                    checkbox_qd9_97.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd9_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd9_3.isChecked()) {
                    checkbox_qd9_0.setChecked(false);
                    checkbox_qd9_98.setChecked(false);
                    checkbox_qd9_97.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd9_98.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd9_98.isChecked()) {
                    checkbox_qd9_0.setChecked(false);
                    checkbox_qd9_1.setChecked(false);
                    checkbox_qd9_2.setChecked(false);
                    checkbox_qd9_3.setChecked(false);
                    checkbox_qd9_97.setChecked(false);
                } else {

                }
            }
        });
        checkbox_qd9_97.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_qd9_97.isChecked()) {
                    checkbox_qd9_0.setChecked(false);
                    checkbox_qd9_1.setChecked(false);
                    checkbox_qd9_2.setChecked(false);
                    checkbox_qd9_3.setChecked(false);
                    checkbox_qd9_98.setChecked(false);
                } else {

                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_d4_ID = rg_d4.getCheckedRadioButtonId();
                int rg_d7_ID = rg_d7.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(pq_Section_D.this);
                SaveData();
                if (qd1_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_qd1_1.isChecked() || checkbox_qd1_2.isChecked()
                            || checkbox_qd1_3.isChecked() || checkbox_qd1_0.isChecked()) {
                        if (checkbox_qd1_1.isChecked() && checkbox_qd1_3.isChecked()) {
                            hideView(qd1_layout, d2_layout);
                        } else if (checkbox_qd1_2.isChecked() && checkbox_qd1_3.isChecked()) {
                            hideView(qd1_layout, d2_layout);
                        } else if (checkbox_qd1_1.isChecked() || checkbox_qd1_2.isChecked()) {
                            hideView(qd1_layout, d2_layout);
                        } else {
                            d2 = "";
                            edt_d2.setText("");
                            hideView(qd1_layout, d3_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple.....");
                    }
                } else if (d2_layout.getVisibility() == View.VISIBLE) {
                    d2 = edt_d2.getText().toString();
                    if (!StringUtils.isEmpty(d2)) {
                        hideView(d2_layout, d3_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (d3_layout.getVisibility() == View.VISIBLE) {
                    d3 = edt_d3.getText().toString();
                    if (!StringUtils.isEmpty(d3)) {
                        hideView(d3_layout, qd4_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (qd4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_d4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_d4_ID);
                        d4 = radioButton.getTag().toString();
                        hideView(qd4_layout, qd5_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qd5_layout.getVisibility() == View.VISIBLE) {
                    d5_other = edt_d5_other.getText().toString();
                    if (checkbox_qd5_0.isChecked() || checkbox_qd5_1.isChecked() || checkbox_qd5_2.isChecked()
                            || checkbox_qd5_3.isChecked() || checkbox_qd5_4.isChecked()
                            || checkbox_qd5_99.isChecked()) {
                        if (checkbox_qd5_99.isChecked()) {
                            d5_other = edt_d5_other.getText().toString();
                            if (!StringUtils.isEmpty(d5_other)) {
                                hideView(qd5_layout, qd6_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            edt_d5_other.setText("");
                            d5_other = "";
                            hideView(qd5_layout, qd6_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple.....");
                    }
                } else if (qd6_layout.getVisibility() == View.VISIBLE) {
                    d6 = edt_d6.getText().toString();
                    if (!StringUtils.isEmpty(d6)) {
                        Integer d6_value = Integer.parseInt(d6);
                        if (d6_value > 16) {
                            toastMessage("Must be in 0-16");
                        } else {
                            hideView(qd6_layout, qd7_layout);
                        }
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (qd7_layout.getVisibility() == View.VISIBLE) {
                    if (rg_d7_ID > 0) {
                        RadioButton radioButton = findViewById(rg_d7_ID);
                        d7 = radioButton.getTag().toString();
                        if (d7.equalsIgnoreCase("3")) {
                            d8_1 = "";
                            d8_2 = "";
                            d8_3 = "";
                            d8_4 = "";
                            d8_5 = "";
                            d8_99 = "";
                            checkbox_qd8_1.setChecked(false);
                            checkbox_qd8_2.setChecked(false);
                            checkbox_qd8_3.setChecked(false);
                            checkbox_qd8_4.setChecked(false);
                            checkbox_qd8_5.setChecked(false);
                            checkbox_qd8_99.setChecked(false);
                            d8_other = "";
                            edt_d8_other.setText("");
                            edt_d8_other.setVisibility(View.GONE);
                            hideView(qd7_layout, qd9_layout);
                        } else {
                            hideView(qd7_layout, qd8_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (qd8_layout.getVisibility() == View.VISIBLE) {
                    d8_other = edt_d8_other.getText().toString();
                    if (checkbox_qd8_1.isChecked() || checkbox_qd8_2.isChecked()
                            || checkbox_qd8_3.isChecked() || checkbox_qd8_4.isChecked()
                            || checkbox_qd8_5.isChecked() || checkbox_qd8_99.isChecked()) {
                        if (checkbox_qd8_99.isChecked()) {
                            d8_other = edt_d8_other.getText().toString();
                            if (!StringUtils.isEmpty(d8_other)) {
                                hideView(qd8_layout, qd9_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            edt_d8_other.setText("");
                            d8_other = "";
                            hideView(qd8_layout, qd9_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple.....");
                    }
                } else if (qd9_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_qd9_0.isChecked() || checkbox_qd9_1.isChecked() || checkbox_qd9_2.isChecked()
                            || checkbox_qd9_3.isChecked() || checkbox_qd9_97.isChecked() || checkbox_qd9_98.isChecked()) {
                        SaveData();
                        Intent intent = new Intent(pq_Section_D.this, pq_Section_E1.class);
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
                        toastMessage("You Can Select Multiple.....");
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

                Intent intent = new Intent(pq_Section_D.this, AddReportActivity.class);
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
        if (qd9_layout.getVisibility() == View.VISIBLE) {
            if (d7.equalsIgnoreCase("3")) {
                hideView(qd9_layout, qd7_layout);
            } else {
                hideView(qd9_layout, qd8_layout);
            }
        } else if (qd8_layout.getVisibility() == View.VISIBLE) {
            hideView(qd8_layout, qd7_layout);
        } else if (qd7_layout.getVisibility() == View.VISIBLE) {
            hideView(qd7_layout, qd6_layout);
        } else if (qd6_layout.getVisibility() == View.VISIBLE) {
            hideView(qd6_layout, qd5_layout);
        } else if (qd5_layout.getVisibility() == View.VISIBLE) {
            hideView(qd5_layout, qd4_layout);
        } else if (qd4_layout.getVisibility() == View.VISIBLE) {
            hideView(qd4_layout, d3_layout);
        } else if (d3_layout.getVisibility() == View.VISIBLE) {
            if (d1_1.equalsIgnoreCase("1") || d1_1.equalsIgnoreCase("2")) {
                hideView(d3_layout, d2_layout);
            } else {
                hideView(d3_layout, qd1_layout);
            }
        } else if (d2_layout.getVisibility() == View.VISIBLE) {
            hideView(d2_layout, qd1_layout);
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
        if (ActivityCompat.checkSelfPermission(pq_Section_D.this,
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
       //         phoneNumber = "880" + phoneNumber;
            } else {
                phoneNumber = "770" + phoneNumber;
            }
        } else {

        }
        if (network.equalsIgnoreCase("Telenor")) {
            ShowDialMessage(pq_Section_D.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
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

            SetCheckboxesTags();

            databaseAccess.savepq_Section_D_Data(emp_id,
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
                    d1_0,
                    d1_1,
                    d1_2,
                    d1_3,
                    d2,
                    d3,
                    d4,
                    d5_0,
                    d5_1,
                    d5_2,
                    d5_3,
                    d5_4,
                    d5_99,
                    d5_other,
                    d6,
                    d7,
                    d8_1,
                    d8_2,
                    d8_3,
                    d8_4,
                    d8_5,
                    d8_99,
                    d8_other,
                    d9_0,
                    d9_1,
                    d9_2,
                    d9_3,
                    d9_98,
                    d9_97);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getpq_section_d_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                d1_0 = cursor.getString(cursor.getColumnIndex("d1_0"));
                d1_1 = cursor.getString(cursor.getColumnIndex("d1_1"));
                d1_2 = cursor.getString(cursor.getColumnIndex("d1_2"));
                d1_3 = cursor.getString(cursor.getColumnIndex("d1_3"));
                d2 = cursor.getString(cursor.getColumnIndex("d2"));
                d3 = cursor.getString(cursor.getColumnIndex("d3"));
                d4 = cursor.getString(cursor.getColumnIndex("d4"));
                d5_0 = cursor.getString(cursor.getColumnIndex("d5_0"));
                d5_1 = cursor.getString(cursor.getColumnIndex("d5_1"));
                d5_2 = cursor.getString(cursor.getColumnIndex("d5_2"));
                d5_3 = cursor.getString(cursor.getColumnIndex("d5_3"));
                d5_4 = cursor.getString(cursor.getColumnIndex("d5_4"));
                d5_99 = cursor.getString(cursor.getColumnIndex("d5_99"));
                d5_other = cursor.getString(cursor.getColumnIndex("d5_other"));
                d6 = cursor.getString(cursor.getColumnIndex("d6"));
                d7 = cursor.getString(cursor.getColumnIndex("d7"));
                d8_1 = cursor.getString(cursor.getColumnIndex("d8_1"));
                d8_2 = cursor.getString(cursor.getColumnIndex("d8_2"));
                d8_3 = cursor.getString(cursor.getColumnIndex("d8_3"));
                d8_4 = cursor.getString(cursor.getColumnIndex("d8_4"));
                d8_5 = cursor.getString(cursor.getColumnIndex("d8_5"));
                d8_99 = cursor.getString(cursor.getColumnIndex("d8_99"));
                d8_other = cursor.getString(cursor.getColumnIndex("d8_other"));
                d9_0 = cursor.getString(cursor.getColumnIndex("d9_0"));
                d9_1 = cursor.getString(cursor.getColumnIndex("d9_1"));
                d9_2 = cursor.getString(cursor.getColumnIndex("d9_2"));
                d9_3 = cursor.getString(cursor.getColumnIndex("d9_3"));
                d9_98 = cursor.getString(cursor.getColumnIndex("d9_98"));
                d9_97 = cursor.getString(cursor.getColumnIndex("d9_97"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            ////////////////////////////////////// RadioGroup //////////////////////
            setradiogroup(d4, rg_d4);
            setradiogroup(d7, rg_d7);
            ////////////////////////////////////// Edit Text //////////////////////
            setEditText(edt_d2, d2);
            setEditText(edt_d3, d3);
            setEditText(edt_d6, d6);
            ////////////////////////////////////// OtherEditText //////////////////////
            setotherEditText(edt_d5_other, d5_other);
            setotherEditText(edt_d8_other, d8_other);
            ////////////////////////////////////// CheckBox //////////////////////
            setCheckbox_load(checkbox_qd1_0, d1_0);
            setCheckbox_load(checkbox_qd1_1, d1_1);
            setCheckbox_load(checkbox_qd1_2, d1_2);
            setCheckbox_load(checkbox_qd1_3, d1_3);

            setCheckbox_load(checkbox_qd5_0, d5_0);
            setCheckbox_load(checkbox_qd5_1, d5_1);
            setCheckbox_load(checkbox_qd5_2, d5_2);
            setCheckbox_load(checkbox_qd5_3, d5_3);
            setCheckbox_load(checkbox_qd5_4, d5_4);
            setCheckbox_load(checkbox_qd5_99, d5_99);

            setCheckbox_load(checkbox_qd8_1, d8_1);
            setCheckbox_load(checkbox_qd8_2, d8_2);
            setCheckbox_load(checkbox_qd8_3, d8_3);
            setCheckbox_load(checkbox_qd8_4, d8_4);
            setCheckbox_load(checkbox_qd8_5, d8_5);

            setCheckbox_load(checkbox_qd9_0, d9_0);
            setCheckbox_load(checkbox_qd9_1, d9_1);
            setCheckbox_load(checkbox_qd9_2, d9_2);
            setCheckbox_load(checkbox_qd9_3, d9_3);
            setCheckbox_load(checkbox_qd9_98, d9_98);
            setCheckbox_load(checkbox_qd9_97, d9_97);

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

    void SetCheckboxesTags() {
        d1_1 = iif(checkbox_qd1_1.isChecked(), "1", "");
        d1_2 = iif(checkbox_qd1_2.isChecked(), "1", "");
        d1_3 = iif(checkbox_qd1_3.isChecked(), "1", "");
        d1_0 = iif(checkbox_qd1_0.isChecked(), "1", "");

        d5_0 = iif(checkbox_qd5_0.isChecked(), "1", "");
        d5_1 = iif(checkbox_qd5_1.isChecked(), "1", "");
        d5_2 = iif(checkbox_qd5_2.isChecked(), "1", "");
        d5_3 = iif(checkbox_qd5_3.isChecked(), "1", "");
        d5_4 = iif(checkbox_qd5_4.isChecked(), "1", "");
        d5_99 = iif(checkbox_qd5_99.isChecked(), "1", "");

        d8_1 = iif(checkbox_qd8_1.isChecked(), "1", "");
        d8_2 = iif(checkbox_qd8_2.isChecked(), "1", "");
        d8_3 = iif(checkbox_qd8_3.isChecked(), "1", "");
        d8_4 = iif(checkbox_qd8_4.isChecked(), "1", "");
        d8_5 = iif(checkbox_qd8_5.isChecked(), "1", "");
        d8_99 = iif(checkbox_qd8_5.isChecked(), "1", "");

        d9_0 = iif(checkbox_qd9_0.isChecked(), "1", "");
        d9_1 = iif(checkbox_qd9_1.isChecked(), "1", "");
        d9_2 = iif(checkbox_qd9_2.isChecked(), "1", "");
        d9_3 = iif(checkbox_qd9_3.isChecked(), "1", "");
        d9_98 = iif(checkbox_qd9_98.isChecked(), "1", "");
        d9_97 = iif(checkbox_qd9_97.isChecked(), "1", "");

    }

    public static <T> T iif(boolean test, T ifTrue, T ifFalse) {
        return test ? ifTrue : ifFalse;
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