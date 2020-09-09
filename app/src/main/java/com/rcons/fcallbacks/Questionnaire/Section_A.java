package com.rcons.fcallbacks.Questionnaire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Gravity;
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

import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.VillageSearch.SelectTehsilActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Section_A extends AppCompatActivity {
    @BindView(R.id.rg_q0)
    RadioGroup rg_q0;
    @BindView(R.id.rg_q1a)
    RadioGroup rg_q1a;
    @BindView(R.id.rg_q2)
    RadioGroup rg_q2;
    @BindView(R.id.rg_q3a)
    RadioGroup rg_q3a;
    @BindView(R.id.rg_q4a)
    RadioGroup rg_q4a;
    @BindView(R.id.rg_q5)
    RadioGroup rg_q5;
    @BindView(R.id.rg_q7)
    RadioGroup rg_q7;
    @BindView(R.id.rg_q8)
    RadioGroup rg_q8;
    @BindView(R.id.rg_q9)
    RadioGroup rg_q9;


    @BindView(R.id.q0_layout)
    LinearLayout q0_layout;
    @BindView(R.id.q1a_layout)
    LinearLayout q1a_layout;
    @BindView(R.id.q1b_layout)
    LinearLayout q1b_layout;
    @BindView(R.id.q2_layout)
    LinearLayout q2_layout;
    @BindView(R.id.q3a_layout)
    LinearLayout q3a_layout;
    @BindView(R.id.q3b_layout)
    LinearLayout q3b_layout;
    @BindView(R.id.q4a_layout)
    LinearLayout q4a_layout;
    @BindView(R.id.q4b_layout)
    LinearLayout q4b_layout;
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

    @BindView(R.id.other_tehsil_layout)
    LinearLayout other_tehsil_layout;


    @BindView(R.id.edt_1b_tehsilName)
    EditText edt_1b_tehsilName;
    @BindView(R.id.edt_3b)
    EditText edt_3b;
    @BindView(R.id.edt_4b)
    EditText edt_4b;
    @BindView(R.id.edt_6)
    EditText edt_6;

    @BindView(R.id.edt_q10_other)
    EditText edt_q10_other;
    @BindView(R.id.edt_q0_other)
    EditText edt_q0_other;


    @BindView(R.id.btnBack)
    ImageView btniBack;

    @BindView(R.id.CheckBox_q10_1)
    CheckBox CheckBox_q10_1;
    @BindView(R.id.CheckBox_q10_2)
    CheckBox CheckBox_q10_2;
    @BindView(R.id.CheckBox_q10_3)
    CheckBox CheckBox_q10_3;

    @BindView(R.id.CheckBox_q10_4)
    CheckBox CheckBox_q10_4;


    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;

    @BindView(R.id.btnSelectTehsil)
    Button btnSelectTehsil;

    @BindView(R.id.btnSelectTehsil_q0)
    Button btnSelectTehsil_q0;

    @BindView(R.id.tvq0_tehsil_name)
    TextView tvq0_tehsil_name;
    @BindView(R.id.txt_farmer_id)
    TextView txt_farmer_id;

    String id = "";
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

    String username = "";
    String person_id = "";
    String enum_code = "";
    String enum_name = "";
    String q0 = "";
    String q0_other = "";
    String q1a = "";
    String q1b = "";
    String q2 = "";
    String q3a = "";
    String q3b = "";
    String q4a = "";
    String q4b = "";
    String q5 = "";
    String q6 = "";
    String q7 = "";
    String q8 = "";
    String q9 = "";
    String q10a = "";
    String q10b = "";
    String q10c = "";
    String q10d = "";
    String q10_other = "";
    String deviceid = "";
    String person_cellphone;
    String build_number = "";

    int question10CheckBoxCounter = 0;
    ArrayList<String> question10CheckBoxTags = new ArrayList<>();

    DatabaseAdapter databaseAccess;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_a);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(Section_A.this);
        databaseAccess.Open();


        username = getIntent().getStringExtra("username");
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
        tvq0_tehsil_name.setText("      کیا آپ ( " + farmerTehsil + ") تحصیل میں رہتے ہیں؟");
        id = getIntent().getStringExtra("id");
        person_cellphone = getIntent().getStringExtra("farmer_cellphone");


        person_id = getIntent().getStringExtra("farmerID");
        txt_farmer_id.setText("Person ID " + person_id);
        SetEnumState();
        SetQuestion10CheckBoxListener();


        LoadPreviousData();

        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_number = BuildConfig.VERSION_NAME;

        rg_q0.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroupq0_ID = rg_q0.getCheckedRadioButtonId();
                if (RGroupq0_ID == R.id.rbtn_q0_2) {
                    other_tehsil_layout.setVisibility(View.VISIBLE);
                } else {
                    other_tehsil_layout.setVisibility(View.GONE);
                    edt_q0_other.setText("");
                    q0_other = "";
                }
            }
        });

        btnSelectTehsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Section_A.this, SelectTehsilActivity.class);
                startActivityForResult(intent, 9999);

            }
        });
        btnSelectTehsil_q0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Section_A.this, SelectTehsilActivity.class);
                startActivityForResult(intent, 9990);

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_q0_ID = rg_q0.getCheckedRadioButtonId();
                int rg_q1a_ID = rg_q1a.getCheckedRadioButtonId();
                int rg_q2_ID = rg_q2.getCheckedRadioButtonId();
                int rg_q3a_ID = rg_q3a.getCheckedRadioButtonId();
                int rg_q4a_ID = rg_q4a.getCheckedRadioButtonId();
                int rg_q5_ID = rg_q5.getCheckedRadioButtonId();
                int rg_q7_ID = rg_q7.getCheckedRadioButtonId();
                int rg_q8_ID = rg_q8.getCheckedRadioButtonId();
                int rg_q9_ID = rg_q9.getCheckedRadioButtonId();

                SaveData();
                RConsUtils.hideKeyboard(Section_A.this);

                if (q0_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q0_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q0_ID);
                        q0 = radioButton.getTag().toString();
                        if (q0.equalsIgnoreCase("2")) {
                            q0_other = edt_q0_other.getText().toString();
                            if (!StringUtils.isEmpty(q0_other)) {
                                q1a = "";
                                rg_q1a.clearCheck();
                                q1b = "";
                                edt_1b_tehsilName.setText("");
                                q2 = "";
                                rg_q2.clearCheck();
                                q3a = "";
                                rg_q3a.clearCheck();
                                q3b = "";
                                edt_3b.setText("");
                                q4a = "";
                                rg_q4a.clearCheck();
                                q4b = "";
                                edt_4b.setText("");
                                q5 = "";
                                rg_q5.clearCheck();
                                q6 = "";
                                edt_6.setText("");
                                q7 = "";
                                rg_q7.clearCheck();
                                q8 = "";
                                rg_q8.clearCheck();
                                q9 = "";
                                rg_q9.clearCheck();
                                q10a = "";
                                CheckBox_q10_1.setChecked(false);
                                q10b = "";
                                CheckBox_q10_2.setChecked(false);
                                q10c = "";
                                CheckBox_q10_3.setChecked(false);
                                Intent intent = new Intent(Section_A.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", person_cellphone);
                                startActivityForResult(intent, 88);
                            } else {
                                toastMessage("Please Note Tehsil Name");
                            }
                        } else {
                            q0_other = "";
                            edt_q0_other.setText("");
                            q0_layout.setVisibility(View.GONE);
                            q1a_layout.setVisibility(View.VISIBLE);
                        }

                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q1a_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q1a_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q1a_ID);
                        q1a = radioButton.getTag().toString();
                        if (q1a.equalsIgnoreCase("1") || q1a.equalsIgnoreCase("2")) {
                            q1a_layout.setVisibility(View.GONE);
                            q1b_layout.setVisibility(View.VISIBLE);
                        } else if (q1a.equalsIgnoreCase("3")) {

                            q1b = "";
                            edt_1b_tehsilName.setText("");
                            q2 = "";
                            rg_q2.clearCheck();
                            q3a = "";
                            rg_q3a.clearCheck();
                            q3b = "";
                            edt_3b.setText("");
                            q4a = "";
                            rg_q4a.clearCheck();
                            q4b = "";
                            edt_4b.setText("");
                            q5 = "";
                            rg_q5.clearCheck();
                            q6 = "";
                            edt_6.setText("");

                            q1a_layout.setVisibility(View.GONE);
                            q7_layout.setVisibility(View.VISIBLE);
                        } else if (q1a.equalsIgnoreCase("4")) {

                            q1b = "";
                            edt_1b_tehsilName.setText("");
                            q2 = "";
                            rg_q2.clearCheck();
                            q3a = "";
                            rg_q3a.clearCheck();
                            q3b = "";
                            edt_3b.setText("");
                            q4a = "";
                            rg_q4a.clearCheck();
                            q4b = "";
                            edt_4b.setText("");
                            q5 = "";
                            rg_q5.clearCheck();
                            q6 = "";
                            edt_6.setText("");
                            q7 = "";
                            rg_q7.clearCheck();
                            q8 = "";
                            rg_q8.clearCheck();
                            q9 = "";
                            rg_q9.clearCheck();
                            q10a = "";
                            CheckBox_q10_1.setChecked(false);
                            q10b = "";
                            CheckBox_q10_2.setChecked(false);
                            q10c = "";
                            CheckBox_q10_3.setChecked(false);

                            Intent intent = new Intent(Section_A.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", person_cellphone);
                            startActivityForResult(intent, 88);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q1b_layout.getVisibility() == View.VISIBLE) {
                    q1b = edt_1b_tehsilName.getText().toString();
                    if (!StringUtils.isEmpty(q1b)) {
                        q1b_layout.setVisibility(View.GONE);
                        q2_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Enter Tehsil Name");
                    }
                } else if (q2_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q2_ID);
                        q2 = radioButton.getTag().toString();
                        if (q2.equalsIgnoreCase("1")) {
                            q2_layout.setVisibility(View.GONE);
                            q3a_layout.setVisibility(View.VISIBLE);
                        } else {

                            q3a = "";
                            rg_q3a.clearCheck();
                            q3b = "";
                            edt_3b.setText("");
                            q4a = "";
                            edt_4b.setText("");
                            q4b = "";

                            q2_layout.setVisibility(View.GONE);
                            q5_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q3a_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q3a_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q3a_ID);
                        q3a = radioButton.getTag().toString();
                        if (q3a.equalsIgnoreCase("1")) {
                            q3a_layout.setVisibility(View.GONE);
                            q3b_layout.setVisibility(View.VISIBLE);
                        } else {
                            q3b = "";
                            edt_3b.setText("");
                            q3a_layout.setVisibility(View.GONE);
                            q4a_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q3b_layout.getVisibility() == View.VISIBLE) {
                    q3b = edt_3b.getText().toString();
                    if (!StringUtils.isEmpty(q3b)) {
                        if (q3b.length() == 4 || q3b.equalsIgnoreCase("999")) {
                            Integer value = Integer.parseInt(q3b);
                            if ((value > 2014 && value < 2021) || q3b.equalsIgnoreCase("999")) {
                                q3b_layout.setVisibility(View.GONE);
                                q4a_layout.setVisibility(View.VISIBLE);
                            } else {
                                toastMessage("Year must be in 2015-2020.");
                            }
                        } else {
                            toastMessage("Please Note Right Answer");
                        }

                    } else {
                        toastMessage("Please Enter Required Data");
                    }

                } else if (q4a_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q4a_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q4a_ID);
                        q4a = radioButton.getTag().toString();
                        if (q4a.equalsIgnoreCase("1")) {
                            q4a_layout.setVisibility(View.GONE);
                            q4b_layout.setVisibility(View.VISIBLE);
                        } else {

                            edt_4b.setText("");
                            q4b = "";

                            q4a_layout.setVisibility(View.GONE);
                            q5_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q4b_layout.getVisibility() == View.VISIBLE) {
                    q4b = edt_4b.getText().toString();
                    if (!StringUtils.isEmpty(q4b)) {
                        if (q4b.length() == 4 || q4b.equalsIgnoreCase("999")) {
                            if (!StringUtils.isEmpty(q3b) && !q3b.equalsIgnoreCase("999") && !q4b.equalsIgnoreCase("999")) {
                                Integer value_q4b = Integer.parseInt(q4b);
                                Integer value_q3b = Integer.parseInt(q3b);
                                if (((value_q4b <= value_q3b)) || q4b.equalsIgnoreCase("999")) {
                                    q4b_layout.setVisibility(View.GONE);
                                    q5_layout.setVisibility(View.VISIBLE);
                                } else {
                                    toastMessage("Value_q4b = " + value_q4b + " must be greater than from value_q3b = " + value_q3b);
                                }
                            } else {
                                Integer value = Integer.parseInt(q4b);
                                if ((value > 1899 && value < 2021) || q4b.equalsIgnoreCase("999")) {
                                    q4b_layout.setVisibility(View.GONE);
                                    q5_layout.setVisibility(View.VISIBLE);
                                } else {
                                    toastMessage("Year must be in 1900-2020.");
                                }
                            }
                        } else {
                            toastMessage("Please Note Right Answer");
                        }

                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q5_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q5_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q5_ID);
                        q5 = radioButton.getTag().toString();
                        if (q5.equalsIgnoreCase("1")) {
                            q5_layout.setVisibility(View.GONE);
                            q6_layout.setVisibility(View.VISIBLE);
                        } else {

                            q6 = "";
                            edt_6.setText("");

                            q5_layout.setVisibility(View.GONE);
                            q7_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q6_layout.getVisibility() == View.VISIBLE) {
                    q6 = edt_6.getText().toString();
                    if (!StringUtils.isEmpty(q6)) {
                        if (q6.length() == 4 || q6.equalsIgnoreCase("999")) {
                            Integer value = Integer.parseInt(q6);
                            if ((value > 1899 && value < 2021) || q6.equalsIgnoreCase("999")) {
                                q6_layout.setVisibility(View.GONE);
                                q7_layout.setVisibility(View.VISIBLE);
                            } else {
                                toastMessage("Year must be in 1900-2020.");
                            }
                        } else {
                            toastMessage("Please Note Right Answer");
                        }

                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q7_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q7_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q7_ID);
                        q7 = radioButton.getTag().toString();
                        if (q7.equalsIgnoreCase("2")) {

                            q8 = "";
                            rg_q8.clearCheck();

                            q7_layout.setVisibility(View.GONE);
                            q9_layout.setVisibility(View.VISIBLE);
                        } else {
                            q7_layout.setVisibility(View.GONE);
                            q8_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q8_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q8_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q8_ID);
                        q8 = radioButton.getTag().toString();
                        q8_layout.setVisibility(View.GONE);
                        q9_layout.setVisibility(View.VISIBLE);
                    } else {
                        toastMessage("Please Select Option");
                    }

                } else if (q9_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q9_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q9_ID);
                        q9 = radioButton.getTag().toString();
                        if (q9.equalsIgnoreCase("2")) {

                            q10a = "";
                            CheckBox_q10_1.setChecked(false);
                            q10b = "";
                            CheckBox_q10_2.setChecked(false);
                            q10c = "";
                            CheckBox_q10_3.setChecked(false);

                            Intent intent = new Intent(Section_A.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", person_cellphone);
                            startActivityForResult(intent, 88);
                        } else {
                            q9_layout.setVisibility(View.GONE);
                            q10_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q10_layout.getVisibility() == View.VISIBLE) {
                    if (question10CheckBoxCounter > 0) {
                        if (edt_q10_other.getVisibility() == View.VISIBLE) {
                            String otherText = edt_q10_other.getText().toString();
                            if (StringUtils.isEmpty(otherText)) {
                                toastMessage("Please Enter other specify");
                                return;
                            } else {
                                q10_other = otherText;
                                if (question10CheckBoxTags != null && question10CheckBoxTags.size() > 0) {
                                    q10a = question10CheckBoxTags.get(0);
                                    if (question10CheckBoxTags.size() > 1) {
                                        q10b = question10CheckBoxTags.get(1);
                                        MubLog.cpnsoleLog("Inside - q10b = " + q10b);
                                    } else {
                                        q10b = "";
                                    }
                                    if (question10CheckBoxTags.size() > 2) {
                                        q10c = question10CheckBoxTags.get(2);
                                        MubLog.cpnsoleLog("Inside - q10 = c" + q10c);
                                    } else {
                                        q10c = "";
                                    }
                                    if (question10CheckBoxTags.size() > 3) {
                                        q10d = question10CheckBoxTags.get(3);
                                    } else {
                                        q10d = "";
                                    }
                                }
                                Intent intent = new Intent(Section_A.this, AddReportActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("farmer_id", FarmerID);
                                intent.putExtra("empID", emp_id);
                                intent.putExtra("farmer_cellphone", person_cellphone);
                                startActivityForResult(intent, 88);
                            }
                        } else {
                            q10_other = "";
                            if (question10CheckBoxTags != null && question10CheckBoxTags.size() > 0) {
                                q10a = question10CheckBoxTags.get(0);
                                if (question10CheckBoxTags.size() > 1) {
                                    q10b = question10CheckBoxTags.get(1);
                                } else {
                                    q10b = "";
                                }
                                if (question10CheckBoxTags.size() > 2) {
                                    q10c = question10CheckBoxTags.get(2);
                                } else {
                                    q10c = "";
                                }
                                if (question10CheckBoxTags.size() > 3) {
                                    q10d = question10CheckBoxTags.get(3);
                                } else {
                                    q10d = "";
                                }
                            }
                            Intent intent = new Intent(Section_A.this, AddReportActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("farmer_id", FarmerID);
                            intent.putExtra("empID", emp_id);
                            intent.putExtra("farmer_cellphone", person_cellphone);
                            startActivityForResult(intent, 88);
                        }
                        MubLog.cpnsoleLog("q10a = " + q10a + " q10b = " + q10b + " q10c = " + q10c + " q10d = " + q10d + " q10_other = " + q10_other);
                    } else {
                        toastMessage(" ایک سے زائد جوابات منتخب کر سکتے ہیں");
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

                Intent intent = new Intent(Section_A.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", person_cellphone);
                startActivityForResult(intent, 88);
            }
        });
        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(person_cellphone)) {
                        DialUserNumber(person_cellphone);
                    } else {
                        Toast.makeText(Section_A.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (q10_layout.getVisibility() == View.VISIBLE) {
            q10_layout.setVisibility(View.GONE);
            q9_layout.setVisibility(View.VISIBLE);
        } else if (q9_layout.getVisibility() == View.VISIBLE) {
            if (q7.equalsIgnoreCase("2")) {
                q9_layout.setVisibility(View.GONE);
                q7_layout.setVisibility(View.VISIBLE);
            } else {
                q9_layout.setVisibility(View.GONE);
                q8_layout.setVisibility(View.VISIBLE);
            }
        } else if (q8_layout.getVisibility() == View.VISIBLE) {
            q8_layout.setVisibility(View.GONE);
            q7_layout.setVisibility(View.VISIBLE);
        } else if (q7_layout.getVisibility() == View.VISIBLE) {
            if (q1a.equalsIgnoreCase("3")) {
                q7_layout.setVisibility(View.GONE);
                q1a_layout.setVisibility(View.VISIBLE);
            } else if (q5.equalsIgnoreCase("2")) {
                q7_layout.setVisibility(View.GONE);
                q5_layout.setVisibility(View.VISIBLE);
            } else {
                q7_layout.setVisibility(View.GONE);
                q6_layout.setVisibility(View.VISIBLE);
            }
        } else if (q6_layout.getVisibility() == View.VISIBLE) {
            q6_layout.setVisibility(View.GONE);
            q5_layout.setVisibility(View.VISIBLE);
        } else if (q5_layout.getVisibility() == View.VISIBLE) {
            if (q2.equalsIgnoreCase("999") || q2.equalsIgnoreCase("2")) {
                q5_layout.setVisibility(View.GONE);
                q2_layout.setVisibility(View.VISIBLE);
            } else if (q4a.equalsIgnoreCase("2") || q4a.equalsIgnoreCase("999")) {
                q5_layout.setVisibility(View.GONE);
                q4a_layout.setVisibility(View.VISIBLE);
            } else {
                q5_layout.setVisibility(View.GONE);
                q4b_layout.setVisibility(View.VISIBLE);
            }
        } else if (q4b_layout.getVisibility() == View.VISIBLE) {
            q4b_layout.setVisibility(View.GONE);
            q4a_layout.setVisibility(View.VISIBLE);
        } else if (q4a_layout.getVisibility() == View.VISIBLE) {
            if (q3a.equalsIgnoreCase("1")) {
                q4a_layout.setVisibility(View.GONE);
                q3b_layout.setVisibility(View.VISIBLE);
            } else {
                q4a_layout.setVisibility(View.GONE);
                q3a_layout.setVisibility(View.VISIBLE);
            }
        } else if (q3b_layout.getVisibility() == View.VISIBLE) {
            q3b_layout.setVisibility(View.GONE);
            q3a_layout.setVisibility(View.VISIBLE);

        } else if (q3a_layout.getVisibility() == View.VISIBLE) {
            q3a_layout.setVisibility(View.GONE);
            q2_layout.setVisibility(View.VISIBLE);
        } else if (q2_layout.getVisibility() == View.VISIBLE) {
            q2_layout.setVisibility(View.GONE);
            q1b_layout.setVisibility(View.VISIBLE);
        } else if (q1b_layout.getVisibility() == View.VISIBLE) {
            q1b_layout.setVisibility(View.GONE);
            q1a_layout.setVisibility(View.VISIBLE);
        } else if (q1a_layout.getVisibility() == View.VISIBLE) {
            q1a_layout.setVisibility(View.GONE);
            q0_layout.setVisibility(View.VISIBLE);
        } else if (q0_layout.getVisibility() == View.VISIBLE) {
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
                if (!StringUtils.isEmpty(person_cellphone)) {
                    DialUserNumber(person_cellphone);
                } else {
                    Toast.makeText(Section_A.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
                // ShowPermissionDialog("Allow this permission to copy images to your phone memory");
            }
        }
    }

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Section_A.this,
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

        MubLog.cpnsoleLog("onActivityResult resultCode" + resultCode);
        MubLog.cpnsoleLog("onActivityResult requestCode " + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                enum_code = data.getStringExtra("enumCode");
                enum_name = data.getStringExtra("enumName");


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

            } else if (requestCode == 9999) {

                String Tehsil_Name = data.getStringExtra("tehsilName");
                edt_1b_tehsilName.setText(Tehsil_Name);
            } else if (requestCode == 9990) {

                String Tehsil_Name = data.getStringExtra("tehsilName");
                edt_q0_other.setText(Tehsil_Name);
            }
        }


    }


    void SaveData() {
        try {

            String time = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_number);
            databaseAccess.saveSectionA_Data(emp_id, order_id, FarmerID, person_id, username, enum_name, enum_code, person_cellphone, q0, q0_other, q1a,
                    q1b, q2, q3a, q3b, q4a, q4b, q5,
                    q6, q7, q8, q9, q10a, q10b, q10c, q10d, q10_other, time, deviceid, build_number);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionAData(person_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                username = cursor.getString(cursor.getColumnIndex("rcons_user"));
                enum_code = cursor.getString(cursor.getColumnIndex("enum_code"));
                enum_name = cursor.getString(cursor.getColumnIndex("enum_name"));

                q0 = cursor.getString(cursor.getColumnIndex("q0"));
                q0_other = cursor.getString(cursor.getColumnIndex("q0_other"));
                q1a = cursor.getString(cursor.getColumnIndex("q1a"));
                q1b = cursor.getString(cursor.getColumnIndex("q1b"));
                q2 = cursor.getString(cursor.getColumnIndex("q2"));
                q3a = cursor.getString(cursor.getColumnIndex("q3a"));
                q3b = cursor.getString(cursor.getColumnIndex("q3b"));
                q4a = cursor.getString(cursor.getColumnIndex("q4a"));
                q4b = cursor.getString(cursor.getColumnIndex("q4b"));
                q5 = cursor.getString(cursor.getColumnIndex("q5"));
                q6 = cursor.getString(cursor.getColumnIndex("q6"));
                q7 = cursor.getString(cursor.getColumnIndex("q7"));
                q8 = cursor.getString(cursor.getColumnIndex("q8"));
                q9 = cursor.getString(cursor.getColumnIndex("q9"));
                q10a = cursor.getString(cursor.getColumnIndex("q10a"));
                q10b = cursor.getString(cursor.getColumnIndex("q10b"));
                q10c = cursor.getString(cursor.getColumnIndex("q10c"));
                q10d = cursor.getString(cursor.getColumnIndex("q10d"));
                q10_other = cursor.getString(cursor.getColumnIndex("q10_other"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q0)) {
                for (int i = 0; i < rg_q0.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q0.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q0)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            /////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q0)) {
                if (q0.equalsIgnoreCase("2")) {
                    other_tehsil_layout.setVisibility(View.VISIBLE);
                    edt_q0_other.setText(q0_other);
                } else {
                    other_tehsil_layout.setVisibility(View.GONE);
                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q1a)) {
                for (int i = 0; i < rg_q1a.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q1a.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q1a)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q1b)) {
                edt_1b_tehsilName.setText(q1b);
            }
            if (!StringUtils.isEmpty(q10_other)) {
                edt_q10_other.setText(q10_other);
                edt_q10_other.setVisibility(View.VISIBLE);
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q2)) {
                for (int i = 0; i < rg_q2.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q2.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q2)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q3a)) {
                for (int i = 0; i < rg_q3a.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q3a.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q3a)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q3b)) {
                edt_3b.setText(q3b);
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q4a)) {
                for (int i = 0; i < rg_q4a.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q4a.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q4a)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q4b)) {
                edt_4b.setText(q4b);
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q5)) {
                for (int i = 0; i < rg_q5.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q5.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q5)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q6)) {
                edt_6.setText(q6);
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q7)) {
                for (int i = 0; i < rg_q7.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q7.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q7)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q8)) {
                for (int i = 0; i < rg_q8.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q8.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q8)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            if (!StringUtils.isEmpty(q9)) {
                for (int i = 0; i < rg_q9.getChildCount(); i++) {
                    RadioButton radioButton = (RadioButton) rg_q9.getChildAt(i);
                    if (radioButton != null) {
                        if (radioButton.getTag().toString().equalsIgnoreCase(q9)) {
                            radioButton.setChecked(true);
                        }
                    }

                }
            }
            //////////////////////////////////////////////////////////////////////////////////////
            for (int i = 0; i < q10_layout.getChildCount(); i++) {

                View childBView = q10_layout.getChildAt(i);
                if (childBView instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) childBView;
                    if (checkBox != null) {
                        if (!StringUtils.isEmpty(q10a) && checkBox.getTag().toString().equalsIgnoreCase(q10a)) {
                            question10CheckBoxCounter++;
                            checkBox.setChecked(true);
                        }
                        if (!StringUtils.isEmpty(q10b) && checkBox.getTag().toString().equalsIgnoreCase(q10b)) {
                            question10CheckBoxCounter++;
                            checkBox.setChecked(true);
                        }
                        if (!StringUtils.isEmpty(q10c) && checkBox.getTag().toString().equalsIgnoreCase(q10c)) {
                            question10CheckBoxCounter++;
                            checkBox.setChecked(true);
                        }
                        if (!StringUtils.isEmpty(q10d) && checkBox.getTag().toString().equalsIgnoreCase(q10d)) {
                            question10CheckBoxCounter++;
                            checkBox.setChecked(true);
                        }

                    }
                }


            }


            //////////////////////////////////////////////////////////////////////////////////////

        } catch (Exception e) {
            toastMessage(e.getMessage());
        }


    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    void SetQuestion10CheckBoxListener() {
        CheckBox_q10_1.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(CheckBox_q10_1));
        CheckBox_q10_2.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(CheckBox_q10_2));
        CheckBox_q10_3.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(CheckBox_q10_3));
        CheckBox_q10_4.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(CheckBox_q10_4));

    }


    void SetQuestion10CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question10CheckBoxCounter < 4) {
                checkBox.setChecked(true);
                question10CheckBoxCounter++;

                if (checkBox.getId() == R.id.CheckBox_q10_4) {
                    edt_q10_other.setVisibility(View.VISIBLE);
                }
                question10CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.CheckBox_q10_4) {
                    edt_q10_other.setVisibility(View.GONE);
                    edt_q10_other.setText("");
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.CheckBox_q10_4) {
                edt_q10_other.setVisibility(View.GONE);
                edt_q10_other.setText("");
            }
            checkBox.setChecked(false);
            question10CheckBoxCounter--;
            question10CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SetEnumState() {
        if (RConsUtils.GetEnumState() == 1) {
            enum_name = RConsUtils.getEnumName();
            enum_code = RConsUtils.getEnumCode();
        }
    }
}
