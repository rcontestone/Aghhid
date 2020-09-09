package com.rcons.fcallbacks.TRT;

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
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideKeyboard;
import static com.rcons.fcallbacks.Utilties.RConsUtils.hideView;
import static com.rcons.fcallbacks.Utilties.RConsUtils.iif;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setCheckbox_load;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setotherEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class trt_Section_2 extends AppCompatActivity {

    DatabaseAdapter databaseAccess;


    @BindView(R.id.txt_phone_number)
    TextView txt_phone_number;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    FloatingActionButton btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;

    @BindView(R.id.q5_layout)
    LinearLayout q5_layout;

    @BindView(R.id.q6_layout)
    LinearLayout q6_layout;

    @BindView(R.id.q7_layout)
    LinearLayout q7_layout;

    @BindView(R.id.q8_layout)
    LinearLayout q8_layout;

    @BindView(R.id.q9_layout)
    LinearLayout q9_layout;

    @BindView(R.id.q10_layout)
    LinearLayout q10_layout;

    @BindView(R.id.q11_layout)
    LinearLayout q11_layout;

    @BindView(R.id.q12_layout)
    LinearLayout q12_layout;

    @BindView(R.id.q13_layout)
    LinearLayout q13_layout;

    @BindView(R.id.rg_q5)
    RadioGroup rg_q5;

    @BindView(R.id.rg_q11)
    RadioGroup rg_q11;

    @BindView(R.id.rg_q12)
    RadioGroup rg_q12;

    @BindView(R.id.edt_q5_other)
    EditText edt_q5_other;
    @BindView(R.id.edt_q6_other)
    EditText edt_q6_other;
    @BindView(R.id.edt_q7_other)
    EditText edt_q7_other;
    @BindView(R.id.edt_q8)
    EditText edt_q8;
    @BindView(R.id.edt_q9)
    EditText edt_q9;
    @BindView(R.id.edt_q10_other)
    EditText edt_q10_other;
    @BindView(R.id.edt_q11_other)
    EditText edt_q11_other;
    @BindView(R.id.edt_q13_other)
    EditText edt_q13_other;

    @BindView(R.id.checkbox_q6_1)
    CheckBox checkbox_q6_1;
    @BindView(R.id.checkbox_q6_2)
    CheckBox checkbox_q6_2;
    @BindView(R.id.checkbox_q6_3)
    CheckBox checkbox_q6_3;
    @BindView(R.id.checkbox_q6_4)
    CheckBox checkbox_q6_4;
    @BindView(R.id.checkbox_q6_5)
    CheckBox checkbox_q6_5;
    @BindView(R.id.checkbox_q6_6)
    CheckBox checkbox_q6_6;
    @BindView(R.id.checkbox_q6_7)
    CheckBox checkbox_q6_7;
    @BindView(R.id.checkbox_q7_1)
    CheckBox checkbox_q7_1;
    @BindView(R.id.checkbox_q7_2)
    CheckBox checkbox_q7_2;
    @BindView(R.id.checkbox_q7_3)
    CheckBox checkbox_q7_3;
    @BindView(R.id.checkbox_q7_4)
    CheckBox checkbox_q7_4;
    @BindView(R.id.checkbox_q7_5)
    CheckBox checkbox_q7_5;
    @BindView(R.id.checkbox_q7_6)
    CheckBox checkbox_q7_6;
    @BindView(R.id.checkbox_q7_7)
    CheckBox checkbox_q7_7;
    @BindView(R.id.checkbox_q10_1)
    CheckBox checkbox_q10_1;
    @BindView(R.id.checkbox_q10_2)
    CheckBox checkbox_q10_2;
    @BindView(R.id.checkbox_q10_3)
    CheckBox checkbox_q10_3;
    @BindView(R.id.checkbox_q10_4)
    CheckBox checkbox_q10_4;
    @BindView(R.id.checkbox_q10_5)
    CheckBox checkbox_q10_5;
    @BindView(R.id.checkbox_q10_6)
    CheckBox checkbox_q10_6;
    @BindView(R.id.checkbox_q10_7)
    CheckBox checkbox_q10_7;
    @BindView(R.id.checkbox_q10_8)
    CheckBox checkbox_q10_8;
    @BindView(R.id.checkbox_q13_1)
    CheckBox checkbox_q13_1;
    @BindView(R.id.checkbox_q13_2)
    CheckBox checkbox_q13_2;
    @BindView(R.id.checkbox_q13_3)
    CheckBox checkbox_q13_3;
    @BindView(R.id.checkbox_q13_4)
    CheckBox checkbox_q13_4;
    @BindView(R.id.checkbox_q13_5)
    CheckBox checkbox_q13_5;
    @BindView(R.id.checkbox_q13_6)
    CheckBox checkbox_q13_6;
    @BindView(R.id.checkbox_q13_7)
    CheckBox checkbox_q13_7;

    String username = "";
    String id = "";
    String emp_id = "";
    String order_id = "";
    String farmer_id = "";
    String sr_no = "";
    String rcons_user = "";
    String enum_code = "";
    String enum_name = "";
    String isComplete = "";
    String isSynced = "";
    String insert_or_updated_in_phone_at = "";
    String deviceid = "";
    String build_no = "";
    String phone_number = "";
    String q5 = "";
    String q5_other = "";
    String q6_1 = "";
    String q6_2 = "";
    String q6_3 = "";
    String q6_4 = "";
    String q6_5 = "";
    String q6_6 = "";
    String q6_7 = "";
    String q6_other = "";
    String q7_1 = "";
    String q7_2 = "";
    String q7_3 = "";
    String q7_4 = "";
    String q7_5 = "";
    String q7_6 = "";
    String q7_7 = "";
    String q7_other = "";
    String q8 = "";
    String q9 = "";
    String q10_1 = "";
    String q10_2 = "";
    String q10_3 = "";
    String q10_4 = "";
    String q10_5 = "";
    String q10_6 = "";
    String q10_7 = "";
    String q10_8 = "";
    String q10_other = "";
    String q11 = "";
    String q11_other = "";
    String q12 = "";
    String q13_1 = "";
    String q13_2 = "";
    String q13_3 = "";
    String q13_4 = "";
    String q13_5 = "";
    String q13_6 = "";
    String q13_7 = "";
    String q13_other = "";
    String city_name = "";
    @BindView(R.id.btnBack)
    ImageView btniBack;

    @BindView(R.id.btn_end)
    Button btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trt_section_2);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(trt_Section_2.this);
        databaseAccess.Open();


        id = getIntent().getStringExtra("id");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        farmer_id = getIntent().getStringExtra("farmer_id");
        sr_no = getIntent().getStringExtra("sr_no");
        city_name = getIntent().getStringExtra("city_name");
        phone_number = getIntent().getStringExtra("phone_number");
        txt_phone_number.setText("Phone Number " + phone_number);

        SetEnumState();
        LoadPreviousData();

        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;


        rg_q5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_q5.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_q5_5) {
                    edt_q5_other.setVisibility(View.VISIBLE);
                    edt_q5_other.requestFocus();
                } else {
                    edt_q5_other.setVisibility(View.GONE);
                    hideKeyboard(trt_Section_2.this);
                    edt_q5_other.setText("");
                    q5_other = "";
                }
            }
        });
        rg_q11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_q11.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_q11_8) {
                    edt_q11_other.setVisibility(View.VISIBLE);
                    edt_q11_other.requestFocus();
                } else {
                    edt_q11_other.setVisibility(View.GONE);
                    hideKeyboard(trt_Section_2.this);
                    edt_q11_other.setText("");
                    q11_other = "";
                }
            }
        });

        checkbox_q6_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_q6_7.isChecked()) {
                    edt_q6_other.setVisibility(View.VISIBLE);
                    edt_q6_other.requestFocus();
                } else {
                    edt_q6_other.setVisibility(View.GONE);
                    hideKeyboard(trt_Section_2.this);
                    q6_other = "";
                }
            }
        });

        checkbox_q7_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_q7_7.isChecked()) {
                    edt_q7_other.setVisibility(View.VISIBLE);
                    edt_q7_other.requestFocus();
                } else {
                    edt_q7_other.setVisibility(View.GONE);
                    edt_q7_other.setText("");
                    hideKeyboard(trt_Section_2.this);
                    q7_other = "";
                }
            }
        });

        checkbox_q10_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_q10_8.isChecked()) {
                    edt_q10_other.setVisibility(View.VISIBLE);
                    edt_q10_other.requestFocus();
                } else {
                    edt_q10_other.setVisibility(View.GONE);
                    edt_q10_other.setText("");
                    hideKeyboard(trt_Section_2.this);
                    q10_other = "";
                }
            }
        });
        checkbox_q13_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_q13_7.isChecked()) {
                    edt_q13_other.setVisibility(View.VISIBLE);
                    edt_q13_other.requestFocus();
                } else {
                    edt_q13_other.setVisibility(View.GONE);
                    edt_q13_other.setText("");
                    hideKeyboard(trt_Section_2.this);
                    q13_other = "";
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_q5_ID = rg_q5.getCheckedRadioButtonId();
                int rg_q11_ID = rg_q11.getCheckedRadioButtonId();
                int rg_q12_ID = rg_q12.getCheckedRadioButtonId();
                hideKeyboard(trt_Section_2.this);
                SaveData();
                if (q5_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q5_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q5_ID);
                        q5 = radioButton.getTag().toString();
                        if (rg_q5_ID == R.id.rbtn_q5_5) {
                            q5_other = edt_q5_other.getText().toString();
                            if (!StringUtils.isEmpty(q5_other)) {
                                RConsUtils.hideView(q5_layout, q6_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            RConsUtils.hideView(q5_layout, q6_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q6_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_q6_1.isChecked()
                            || checkbox_q6_2.isChecked()
                            || checkbox_q6_3.isChecked()
                            || checkbox_q6_4.isChecked()
                            || checkbox_q6_5.isChecked()
                            || checkbox_q6_6.isChecked()
                            || checkbox_q6_7.isChecked()) {
                        if (checkbox_q6_7.isChecked()) {
                            q6_other = edt_q6_other.getText().toString();
                            if (!StringUtils.isEmpty(q6_other)) {
                                RConsUtils.hideView(q6_layout, q7_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            q6_other = "";
                            edt_q6_other.setText("");
                            RConsUtils.hideView(q6_layout, q7_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple...");
                    }
                } else if (q7_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_q7_1.isChecked()
                            || checkbox_q7_2.isChecked()
                            || checkbox_q7_3.isChecked()
                            || checkbox_q7_4.isChecked()
                            || checkbox_q7_5.isChecked()
                            || checkbox_q7_6.isChecked()
                            || checkbox_q7_7.isChecked()) {
                        if (checkbox_q7_7.isChecked()) {
                            q7_other = edt_q7_other.getText().toString();
                            if (!StringUtils.isEmpty(q7_other)) {
                                RConsUtils.hideView(q7_layout, q8_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            q7_other = "";
                            edt_q7_other.setText("");
                            RConsUtils.hideView(q7_layout, q8_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple...");
                    }
                } else if (q8_layout.getVisibility() == View.VISIBLE) {
                    q8 = edt_q8.getText().toString();
                    if (!StringUtils.isEmpty(q8)) {
                        RConsUtils.hideView(q8_layout, q9_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q9_layout.getVisibility() == View.VISIBLE) {
                    q9 = edt_q9.getText().toString();
                    if (!StringUtils.isEmpty(q9)) {
                        RConsUtils.hideView(q9_layout, q10_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q10_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_q10_1.isChecked()
                            || checkbox_q10_2.isChecked()
                            || checkbox_q10_3.isChecked()
                            || checkbox_q10_4.isChecked()
                            || checkbox_q10_5.isChecked()
                            || checkbox_q10_6.isChecked()
                            || checkbox_q10_7.isChecked()
                            || checkbox_q10_8.isChecked()) {
                        if (checkbox_q10_8.isChecked()) {
                            q10_other = edt_q10_other.getText().toString();
                            if (!StringUtils.isEmpty(q10_other)) {
                                RConsUtils.hideView(q10_layout, q11_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            q10_other = "";
                            edt_q10_other.setText("");
                            RConsUtils.hideView(q10_layout, q11_layout);
                        }
                    } else {
                        toastMessage("You Can Select Multiple...");
                    }
                } else if (q11_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q11_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q11_ID);
                        q11 = radioButton.getTag().toString();
                        if (rg_q11_ID == R.id.rbtn_q11_8) {
                            q11_other = edt_q11_other.getText().toString();
                            if (!StringUtils.isEmpty(q11_other)) {
                                RConsUtils.hideView(q11_layout, q12_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            RConsUtils.hideView(q11_layout, q12_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q12_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q12_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q12_ID);
                        q12 = radioButton.getTag().toString();
                        RConsUtils.hideView(q12_layout, q13_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q13_layout.getVisibility() == View.VISIBLE) {
                    if (checkbox_q13_1.isChecked()
                            || checkbox_q13_2.isChecked()
                            || checkbox_q13_3.isChecked()
                            || checkbox_q13_4.isChecked()
                            || checkbox_q13_5.isChecked()
                            || checkbox_q13_6.isChecked()
                            || checkbox_q13_7.isChecked()) {
                        if (checkbox_q13_7.isChecked()) {
                            q13_other = edt_q13_other.getText().toString();
                            if (!StringUtils.isEmpty(q13_other)) {
                                Intent intent = new Intent(trt_Section_2.this, trt_Section_3.class);
                                intent.putExtra("city_name", city_name);
                                intent.putExtra("phone_number", phone_number);
                                intent.putExtra("id", id);
                                intent.putExtra("emp_id", emp_id);
                                intent.putExtra("order_id", order_id);
                                intent.putExtra("farmer_id", farmer_id);
                                intent.putExtra("sr_no", sr_no);
                                startActivityForResult(intent, 77);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            q13_other = "";
                            edt_q13_other.setText("");
                            Intent intent = new Intent(trt_Section_2.this, trt_Section_3.class);
                            intent.putExtra("city_name", city_name);
                            intent.putExtra("phone_number", phone_number);
                            intent.putExtra("id", id);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("farmer_id", farmer_id);
                            intent.putExtra("sr_no", sr_no);
                            startActivityForResult(intent, 77);
                        }
                    } else {
                        toastMessage("You Can Select Multiple...");
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
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogMessage(trt_Section_2.this, "Alert", "Are You Sure Want to End Survey ?");
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(trt_Section_2.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", farmer_id);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", phone_number);
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
        if (q13_layout.getVisibility() == View.VISIBLE) {
            hideView(q13_layout, q12_layout);
        } else if (q12_layout.getVisibility() == View.VISIBLE) {
            hideView(q12_layout, q11_layout);
        } else if (q11_layout.getVisibility() == View.VISIBLE) {
            hideView(q11_layout, q10_layout);
        } else if (q10_layout.getVisibility() == View.VISIBLE) {
            hideView(q10_layout, q9_layout);
        } else if (q9_layout.getVisibility() == View.VISIBLE) {
            hideView(q9_layout, q8_layout);
        } else if (q8_layout.getVisibility() == View.VISIBLE) {
            hideView(q8_layout, q7_layout);
        } else if (q7_layout.getVisibility() == View.VISIBLE) {
            hideView(q7_layout, q6_layout);
        } else if (q6_layout.getVisibility() == View.VISIBLE) {
            hideView(q6_layout, q5_layout);
        } else {
            finish();
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
        if (ActivityCompat.checkSelfPermission(trt_Section_2.this,
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 77) {
                Intent returnIntent = new Intent();
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

            databaseAccess.savetrt_Section_2_Data(emp_id, order_id,
                    farmer_id, sr_no,
                    rcons_user, enum_code,
                    enum_name, isComplete, isSynced,
                    insert_or_updated_in_phone_at, deviceid, build_no,
                    phone_number, q5, q5_other,
                    q6_1, q6_2, q6_3, q6_4,
                    q6_5, q6_6, q6_7, q6_other,
                    q7_1, q7_2, q7_3, q7_4, q7_5,
                    q7_6, q7_7, q7_other, q8, q9,
                    q10_1, q10_2, q10_3, q10_4,
                    q10_5, q10_6, q10_7, q10_8,
                    q10_other, q11, q11_other, q12,
                    q13_1, q13_2, q13_3, q13_4,
                    q13_5, q13_6, q13_7, q13_other);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.gettrt_section_2_Data(phone_number);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                q5 = cursor.getString(cursor.getColumnIndex("q5"));
                q5_other = cursor.getString(cursor.getColumnIndex("q5_other"));
                q6_1 = cursor.getString(cursor.getColumnIndex("q6_1"));
                q6_2 = cursor.getString(cursor.getColumnIndex("q6_2"));
                q6_3 = cursor.getString(cursor.getColumnIndex("q6_3"));
                q6_4 = cursor.getString(cursor.getColumnIndex("q6_4"));
                q6_5 = cursor.getString(cursor.getColumnIndex("q6_5"));
                q6_6 = cursor.getString(cursor.getColumnIndex("q6_6"));
                q6_7 = cursor.getString(cursor.getColumnIndex("q6_7"));
                q6_other = cursor.getString(cursor.getColumnIndex("q6_other"));
                q7_1 = cursor.getString(cursor.getColumnIndex("q7_1"));
                q7_2 = cursor.getString(cursor.getColumnIndex("q7_2"));
                q7_3 = cursor.getString(cursor.getColumnIndex("q7_3"));
                q7_4 = cursor.getString(cursor.getColumnIndex("q7_4"));
                q7_5 = cursor.getString(cursor.getColumnIndex("q7_5"));
                q7_6 = cursor.getString(cursor.getColumnIndex("q7_6"));
                q7_7 = cursor.getString(cursor.getColumnIndex("q7_7"));
                q7_other = cursor.getString(cursor.getColumnIndex("q7_other"));
                q8 = cursor.getString(cursor.getColumnIndex("q8"));
                q9 = cursor.getString(cursor.getColumnIndex("q9"));
                q10_1 = cursor.getString(cursor.getColumnIndex("q10_1"));
                q10_2 = cursor.getString(cursor.getColumnIndex("q10_2"));
                q10_3 = cursor.getString(cursor.getColumnIndex("q10_3"));
                q10_4 = cursor.getString(cursor.getColumnIndex("q10_4"));
                q10_5 = cursor.getString(cursor.getColumnIndex("q10_5"));
                q10_6 = cursor.getString(cursor.getColumnIndex("q10_6"));
                q10_7 = cursor.getString(cursor.getColumnIndex("q10_7"));
                q10_8 = cursor.getString(cursor.getColumnIndex("q10_8"));
                q10_other = cursor.getString(cursor.getColumnIndex("q10_other"));
                q11 = cursor.getString(cursor.getColumnIndex("q11"));
                q11_other = cursor.getString(cursor.getColumnIndex("q11_other"));
                q12 = cursor.getString(cursor.getColumnIndex("q12"));
                q13_1 = cursor.getString(cursor.getColumnIndex("q13_1"));
                q13_2 = cursor.getString(cursor.getColumnIndex("q13_2"));
                q13_3 = cursor.getString(cursor.getColumnIndex("q13_3"));
                q13_4 = cursor.getString(cursor.getColumnIndex("q13_4"));
                q13_5 = cursor.getString(cursor.getColumnIndex("q13_5"));
                q13_6 = cursor.getString(cursor.getColumnIndex("q13_6"));
                q13_7 = cursor.getString(cursor.getColumnIndex("q13_7"));
                q13_other = cursor.getString(cursor.getColumnIndex("q13_other"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {

            ////////////////////////////////////// RadioGroup //////////////////////
            setradiogroup(q5, rg_q5);
            setradiogroup(q11, rg_q11);
            setradiogroup(q12, rg_q12);
            ////////////////////////////////////// EditText //////////////////////
            setEditText(edt_q8, q8);
            setEditText(edt_q9, q9);
            ////////////////////////////////////// OtherEditText //////////////////////
            setotherEditText(edt_q5_other, q5_other);
            setotherEditText(edt_q6_other, q6_other);
            setotherEditText(edt_q7_other, q7_other);
            setotherEditText(edt_q10_other, q10_other);
            setotherEditText(edt_q11_other, q11_other);
            setotherEditText(edt_q13_other, q13_other);
            ///////////////////////////////////////////////////////////////////////
            setCheckbox_load(checkbox_q6_1, q6_1);
            setCheckbox_load(checkbox_q6_2, q6_2);
            setCheckbox_load(checkbox_q6_3, q6_3);
            setCheckbox_load(checkbox_q6_4, q6_4);
            setCheckbox_load(checkbox_q6_5, q6_5);
            setCheckbox_load(checkbox_q6_6, q6_6);
            setCheckbox_load(checkbox_q6_7, q6_7);

            setCheckbox_load(checkbox_q7_1, q7_1);
            setCheckbox_load(checkbox_q7_2, q7_2);
            setCheckbox_load(checkbox_q7_3, q7_3);
            setCheckbox_load(checkbox_q7_4, q7_4);
            setCheckbox_load(checkbox_q7_5, q7_5);
            setCheckbox_load(checkbox_q7_6, q7_6);
            setCheckbox_load(checkbox_q7_7, q7_7);

            setCheckbox_load(checkbox_q10_1, q10_1);
            setCheckbox_load(checkbox_q10_2, q10_2);
            setCheckbox_load(checkbox_q10_3, q10_3);
            setCheckbox_load(checkbox_q10_4, q10_4);
            setCheckbox_load(checkbox_q10_5, q10_5);
            setCheckbox_load(checkbox_q10_6, q10_6);
            setCheckbox_load(checkbox_q10_7, q10_7);
            setCheckbox_load(checkbox_q10_8, q10_8);

            setCheckbox_load(checkbox_q13_1, q13_1);
            setCheckbox_load(checkbox_q13_2, q13_2);
            setCheckbox_load(checkbox_q13_3, q13_3);
            setCheckbox_load(checkbox_q13_4, q13_4);
            setCheckbox_load(checkbox_q13_5, q13_5);
            setCheckbox_load(checkbox_q13_6, q13_6);
            setCheckbox_load(checkbox_q13_7, q13_7);
            ///////////////////////////////////////////////////////////////////////


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
            enum_name = RConsUtils.getUserName();
            enum_code = RConsUtils.getUserPassword();
        }
    }

    void SetCheckboxesTags() {

        q6_1 = iif(checkbox_q6_1.isChecked(), "1", "");
        q6_2 = iif(checkbox_q6_2.isChecked(), "1", "");
        q6_3 = iif(checkbox_q6_3.isChecked(), "1", "");
        q6_4 = iif(checkbox_q6_4.isChecked(), "1", "");
        q6_5 = iif(checkbox_q6_5.isChecked(), "1", "");
        q6_6 = iif(checkbox_q6_6.isChecked(), "1", "");
        q6_7 = iif(checkbox_q6_7.isChecked(), "1", "");

        q7_1 = iif(checkbox_q7_1.isChecked(), "1", "");
        q7_2 = iif(checkbox_q7_2.isChecked(), "1", "");
        q7_3 = iif(checkbox_q7_3.isChecked(), "1", "");
        q7_4 = iif(checkbox_q7_4.isChecked(), "1", "");
        q7_5 = iif(checkbox_q7_5.isChecked(), "1", "");
        q7_6 = iif(checkbox_q7_6.isChecked(), "1", "");
        q7_7 = iif(checkbox_q7_7.isChecked(), "1", "");

        q10_1 = iif(checkbox_q10_1.isChecked(), "1", "");
        q10_2 = iif(checkbox_q10_2.isChecked(), "1", "");
        q10_3 = iif(checkbox_q10_3.isChecked(), "1", "");
        q10_4 = iif(checkbox_q10_4.isChecked(), "1", "");
        q10_5 = iif(checkbox_q10_5.isChecked(), "1", "");
        q10_6 = iif(checkbox_q10_6.isChecked(), "1", "");
        q10_7 = iif(checkbox_q10_7.isChecked(), "1", "");
        q10_8 = iif(checkbox_q10_8.isChecked(), "1", "");

        q13_1 = iif(checkbox_q13_1.isChecked(), "1", "");
        q13_2 = iif(checkbox_q13_2.isChecked(), "1", "");
        q13_3 = iif(checkbox_q13_3.isChecked(), "1", "");
        q13_4 = iif(checkbox_q13_4.isChecked(), "1", "");
        q13_5 = iif(checkbox_q13_5.isChecked(), "1", "");
        q13_6 = iif(checkbox_q13_6.isChecked(), "1", "");
        q13_7 = iif(checkbox_q13_7.isChecked(), "1", "");
    }

    void ShowDialogMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.my_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnYes = dialogView.findViewById(R.id.btnYes);
        btnYes.setText("Yes");
        btnCancel.setText("No");
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
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