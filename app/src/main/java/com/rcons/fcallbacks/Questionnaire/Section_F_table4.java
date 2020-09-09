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
import android.widget.CheckBox;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Section_F_table4 extends AppCompatActivity {

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
    String comments = "";
    String enum_name = "";
    String enum_code = "";

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

    @BindView(R.id.qf3_layout)
    LinearLayout qf3_layout;
    @BindView(R.id.qf4_layout)
    LinearLayout qf4_layout;
    @BindView(R.id.qf5_layout)
    LinearLayout qf5_layout;
    @BindView(R.id.qf6_layout)
    LinearLayout qf6_layout;
    @BindView(R.id.qf7_layout)
    LinearLayout qf7_layout;
    @BindView(R.id.qf8_layout)
    LinearLayout qf8_layout;
    @BindView(R.id.qf9_layout)
    LinearLayout qf9_layout;
    @BindView(R.id.qf10_layout)
    LinearLayout qf10_layout;
    @BindView(R.id.qf11_layout)
    LinearLayout qf11_layout;

    @BindView(R.id.rg_qf3)
    RadioGroup rg_qf3;
    @BindView(R.id.rg_qf5)
    RadioGroup rg_qf5;
    @BindView(R.id.rg_qf7)
    RadioGroup rg_qf7;
    @BindView(R.id.rg_qf9)
    RadioGroup rg_qf9;
    @BindView(R.id.rg_qf11)
    RadioGroup rg_qf11;

    @BindView(R.id.checkbox_f4_1)
    CheckBox checkbox_f4_1;

    @BindView(R.id.checkbox_f4_2)
    CheckBox checkbox_f4_2;

    @BindView(R.id.checkbox_f4_3)
    CheckBox checkbox_f4_3;

    @BindView(R.id.checkbox_f4_4)
    CheckBox checkbox_f4_4;

    @BindView(R.id.checkbox_f4_5)
    CheckBox checkbox_f4_5;

    @BindView(R.id.checkbox_f4_6)
    CheckBox checkbox_f4_6;

    @BindView(R.id.checkbox_f4_7)
    CheckBox checkbox_f4_7;

    @BindView(R.id.checkbox_f4_8)
    CheckBox checkbox_f4_8;


    @BindView(R.id.checkbox_f4_9)
    CheckBox checkbox_f4_9;


    @BindView(R.id.checkbox_f4_10)
    CheckBox checkbox_f4_10;


    @BindView(R.id.checkbox_f4_11)
    CheckBox checkbox_f4_11;


    @BindView(R.id.checkbox_f4_12)
    CheckBox checkbox_f4_12;


    @BindView(R.id.checkbox_f4_13)
    CheckBox checkbox_f4_13;


    @BindView(R.id.checkbox_f4_14)
    CheckBox checkbox_f4_14;


    @BindView(R.id.checkbox_f4_15)
    CheckBox checkbox_f4_15;


    @BindView(R.id.checkbox_f4_16)
    CheckBox checkbox_f4_16;


    @BindView(R.id.checkbox_f4_17)
    CheckBox checkbox_f4_17;


    @BindView(R.id.checkbox_f4_18)
    CheckBox checkbox_f4_18;


    @BindView(R.id.checkbox_f4_19)
    CheckBox checkbox_f4_19;


    @BindView(R.id.checkbox_f4_20)
    CheckBox checkbox_f4_20;


    @BindView(R.id.checkbox_f4_21)
    CheckBox checkbox_f4_21;


    @BindView(R.id.checkbox_f4_22)
    CheckBox checkbox_f4_22;

    @BindView(R.id.checkbox_f4_23)
    CheckBox checkbox_f4_23;

    @BindView(R.id.checkbox_f4_24)
    CheckBox checkbox_f4_24;

    @BindView(R.id.checkbox_f4_25)
    CheckBox checkbox_f4_25;

    @BindView(R.id.checkbox_f4_26)
    CheckBox checkbox_f4_26;

    @BindView(R.id.checkbox_f4_27)
    CheckBox checkbox_f4_27;


    @BindView(R.id.checkbox_f4_28)
    CheckBox checkbox_f4_28;


    @BindView(R.id.checkbox_f4_99)
    CheckBox checkbox_f4_99;

    @BindView(R.id.checkbox_f6_1)
    CheckBox checkbox_f6_1;

    @BindView(R.id.checkbox_f6_2)
    CheckBox checkbox_f6_2;

    @BindView(R.id.checkbox_f6_3)
    CheckBox checkbox_f6_3;

    @BindView(R.id.checkbox_f6_4)
    CheckBox checkbox_f6_4;

    @BindView(R.id.checkbox_f6_5)
    CheckBox checkbox_f6_5;

    @BindView(R.id.checkbox_f6_6)
    CheckBox checkbox_f6_6;

    @BindView(R.id.checkbox_f6_7)
    CheckBox checkbox_f6_7;

    @BindView(R.id.checkbox_f6_8)
    CheckBox checkbox_f6_8;


    @BindView(R.id.checkbox_f6_9)
    CheckBox checkbox_f6_9;


    @BindView(R.id.checkbox_f6_10)
    CheckBox checkbox_f6_10;


    @BindView(R.id.checkbox_f6_11)
    CheckBox checkbox_f6_11;


    @BindView(R.id.checkbox_f6_12)
    CheckBox checkbox_f6_12;


    @BindView(R.id.checkbox_f6_13)
    CheckBox checkbox_f6_13;


    @BindView(R.id.checkbox_f6_14)
    CheckBox checkbox_f6_14;


    @BindView(R.id.checkbox_f6_15)
    CheckBox checkbox_f6_15;


    @BindView(R.id.checkbox_f6_16)
    CheckBox checkbox_f6_16;


    @BindView(R.id.checkbox_f6_17)
    CheckBox checkbox_f6_17;


    @BindView(R.id.checkbox_f6_18)
    CheckBox checkbox_f6_18;


    @BindView(R.id.checkbox_f6_19)
    CheckBox checkbox_f6_19;


    @BindView(R.id.checkbox_f6_20)
    CheckBox checkbox_f6_20;


    @BindView(R.id.checkbox_f6_21)
    CheckBox checkbox_f6_21;


    @BindView(R.id.checkbox_f6_22)
    CheckBox checkbox_f6_22;

    @BindView(R.id.checkbox_f6_23)
    CheckBox checkbox_f6_23;

    @BindView(R.id.checkbox_f6_24)
    CheckBox checkbox_f6_24;

    @BindView(R.id.checkbox_f6_25)
    CheckBox checkbox_f6_25;

    @BindView(R.id.checkbox_f6_26)
    CheckBox checkbox_f6_26;

    @BindView(R.id.checkbox_f6_27)
    CheckBox checkbox_f6_27;


    @BindView(R.id.checkbox_f6_28)
    CheckBox checkbox_f6_28;


    @BindView(R.id.checkbox_f6_99)
    CheckBox checkbox_f6_99;

    @BindView(R.id.checkbox_f8_1)
    CheckBox checkbox_f8_1;

    @BindView(R.id.checkbox_f8_2)
    CheckBox checkbox_f8_2;

    @BindView(R.id.checkbox_f8_3)
    CheckBox checkbox_f8_3;

    @BindView(R.id.checkbox_f8_4)
    CheckBox checkbox_f8_4;

    @BindView(R.id.checkbox_f8_5)
    CheckBox checkbox_f8_5;

    @BindView(R.id.checkbox_f8_6)
    CheckBox checkbox_f8_6;

    @BindView(R.id.checkbox_f8_7)
    CheckBox checkbox_f8_7;

    @BindView(R.id.checkbox_f8_8)
    CheckBox checkbox_f8_8;


    @BindView(R.id.checkbox_f8_9)
    CheckBox checkbox_f8_9;


    @BindView(R.id.checkbox_f8_10)
    CheckBox checkbox_f8_10;


    @BindView(R.id.checkbox_f8_11)
    CheckBox checkbox_f8_11;


    @BindView(R.id.checkbox_f8_12)
    CheckBox checkbox_f8_12;


    @BindView(R.id.checkbox_f8_13)
    CheckBox checkbox_f8_13;


    @BindView(R.id.checkbox_f8_14)
    CheckBox checkbox_f8_14;


    @BindView(R.id.checkbox_f8_15)
    CheckBox checkbox_f8_15;


    @BindView(R.id.checkbox_f8_16)
    CheckBox checkbox_f8_16;


    @BindView(R.id.checkbox_f8_17)
    CheckBox checkbox_f8_17;


    @BindView(R.id.checkbox_f8_18)
    CheckBox checkbox_f8_18;


    @BindView(R.id.checkbox_f8_19)
    CheckBox checkbox_f8_19;


    @BindView(R.id.checkbox_f8_20)
    CheckBox checkbox_f8_20;


    @BindView(R.id.checkbox_f8_21)
    CheckBox checkbox_f8_21;


    @BindView(R.id.checkbox_f8_22)
    CheckBox checkbox_f8_22;

    @BindView(R.id.checkbox_f8_23)
    CheckBox checkbox_f8_23;

    @BindView(R.id.checkbox_f8_24)
    CheckBox checkbox_f8_24;

    @BindView(R.id.checkbox_f8_25)
    CheckBox checkbox_f8_25;

    @BindView(R.id.checkbox_f8_26)
    CheckBox checkbox_f8_26;

    @BindView(R.id.checkbox_f8_27)
    CheckBox checkbox_f8_27;


    @BindView(R.id.checkbox_f8_28)
    CheckBox checkbox_f8_28;


    @BindView(R.id.checkbox_f8_99)
    CheckBox checkbox_f8_99;

    @BindView(R.id.checkbox_f10_1)
    CheckBox checkbox_f10_1;

    @BindView(R.id.checkbox_f10_2)
    CheckBox checkbox_f10_2;

    @BindView(R.id.checkbox_f10_3)
    CheckBox checkbox_f10_3;

    @BindView(R.id.checkbox_f10_4)
    CheckBox checkbox_f10_4;

    @BindView(R.id.checkbox_f10_5)
    CheckBox checkbox_f10_5;

    @BindView(R.id.checkbox_f10_6)
    CheckBox checkbox_f10_6;

    @BindView(R.id.checkbox_f10_7)
    CheckBox checkbox_f10_7;

    @BindView(R.id.checkbox_f10_8)
    CheckBox checkbox_f10_8;


    @BindView(R.id.checkbox_f10_9)
    CheckBox checkbox_f10_9;


    @BindView(R.id.checkbox_f10_10)
    CheckBox checkbox_f10_10;


    @BindView(R.id.checkbox_f10_11)
    CheckBox checkbox_f10_11;


    @BindView(R.id.checkbox_f10_12)
    CheckBox checkbox_f10_12;


    @BindView(R.id.checkbox_f10_13)
    CheckBox checkbox_f10_13;


    @BindView(R.id.checkbox_f10_14)
    CheckBox checkbox_f10_14;


    @BindView(R.id.checkbox_f10_15)
    CheckBox checkbox_f10_15;


    @BindView(R.id.checkbox_f10_16)
    CheckBox checkbox_f10_16;


    @BindView(R.id.checkbox_f10_17)
    CheckBox checkbox_f10_17;


    @BindView(R.id.checkbox_f10_18)
    CheckBox checkbox_f10_18;


    @BindView(R.id.checkbox_f10_19)
    CheckBox checkbox_f10_19;


    @BindView(R.id.checkbox_f10_20)
    CheckBox checkbox_f10_20;


    @BindView(R.id.checkbox_f10_21)
    CheckBox checkbox_f10_21;


    @BindView(R.id.checkbox_f10_22)
    CheckBox checkbox_f10_22;

    @BindView(R.id.checkbox_f10_23)
    CheckBox checkbox_f10_23;

    @BindView(R.id.checkbox_f10_24)
    CheckBox checkbox_f10_24;

    @BindView(R.id.checkbox_f10_25)
    CheckBox checkbox_f10_25;

    @BindView(R.id.checkbox_f10_26)
    CheckBox checkbox_f10_26;

    @BindView(R.id.checkbox_f10_27)
    CheckBox checkbox_f10_27;


    @BindView(R.id.checkbox_f10_28)
    CheckBox checkbox_f10_28;


    @BindView(R.id.checkbox_f10_99)
    CheckBox checkbox_f10_99;

    @BindView(R.id.edt_qf4_other)
    EditText edt_qf4_other;


    @BindView(R.id.edt_qf6_other)
    EditText edt_qf6_other;

    @BindView(R.id.edt_qf8_other)
    EditText edt_qf8_other;


    @BindView(R.id.edt_qf10_other)
    EditText edt_qf10_other;

    int question4CheckBoxCounter = 0;
    ArrayList<String> question4CheckBoxTags = new ArrayList<>();

    int question6CheckBoxCounter = 0;
    ArrayList<String> question6CheckBoxTags = new ArrayList<>();

    int question8CheckBoxCounter = 0;
    ArrayList<String> question8CheckBoxTags = new ArrayList<>();

    int question10CheckBoxCounter = 0;
    ArrayList<String> question10CheckBoxTags = new ArrayList<>();

    String f3 = "";
    String f4_a = "";
    String f4_b = "";
    String f4_c = "";
    String f4_other = "";
    String f5 = "";
    String f6_a = "";
    String f6_b = "";
    String f6_c = "";
    String f6_other = "";
    String f7 = "";
    String f8_a = "";
    String f8_b = "";
    String f8_c = "";
    String f8_other = "";
    String f9 = "";
    String f10_a = "";
    String f10_b = "";
    String f10_c = "";
    String f10_other = "";
    String f11 = "";

    ImageView btniBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section__f_table4);
        ButterKnife.bind(this);

        btniBack = findViewById(R.id.btnBack);

        databaseAccess = new DatabaseAdapter(Section_F_table4.this);
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

        SetQuestion4CheckBoxListener();
        SetQuestion6CheckBoxListener();
        SetQuestion8CheckBoxListener();
        SetQuestion10CheckBoxListener();
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
        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
                Intent intent = new Intent(Section_F_table4.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                startActivityForResult(intent, 88);
                intent.putExtra("section", "section_f_table4");
                intent.putExtra("isFromEdit", isFromEdit);
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
                SaveData();
                try {
                    onBackPressed();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(farmer_cellphone)) {
                        DialUserNumber(farmer_cellphone);
                    } else {
                        Toast.makeText(Section_F_table4.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_qf3_id = rg_qf3.getCheckedRadioButtonId();
                int rg_qf5_id = rg_qf5.getCheckedRadioButtonId();
                int rg_qf7_id = rg_qf7.getCheckedRadioButtonId();
                int rg_qf9_id = rg_qf9.getCheckedRadioButtonId();
                int rg_qf11_id = rg_qf11.getCheckedRadioButtonId();
                RConsUtils.hideKeyboard(Section_F_table4.this);
                SaveData();
                if (qf3_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qf3_id);
                    if (rg_qf3_id == R.id.rbtn_f3_1) {
                        qf3_layout.setVisibility(View.GONE);
                        qf4_layout.setVisibility(View.VISIBLE);
                        f3 = radioButton.getTag().toString();
                    } else if (rg_qf3_id == R.id.rbtn_f3_2) {
                        qf3_layout.setVisibility(View.GONE);
                        f3 = radioButton.getTag().toString();
                        qf5_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Section_F_table4.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qf4_layout.getVisibility() == View.VISIBLE) {
                    if (question4CheckBoxCounter > 0) {
                        if (edt_qf4_other.getVisibility() == View.VISIBLE) {
                            String otherText = edt_qf4_other.getText().toString();
                            if (StringUtils.isEmpty(otherText)) {
                                Toast.makeText(Section_F_table4.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                f4_other = otherText;
                                if (question4CheckBoxTags != null && question4CheckBoxTags.size() > 0) {
                                    f4_a = question4CheckBoxTags.get(0);
                                    if (question4CheckBoxTags.size() > 1) {
                                        f4_b = question4CheckBoxTags.get(1);
                                    }
                                    if (question4CheckBoxTags.size() > 2) {
                                        f4_c = question4CheckBoxTags.get(2);
                                    }
                                }
                                qf4_layout.setVisibility(View.GONE);

                                qf5_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (question4CheckBoxTags != null && question4CheckBoxTags.size() > 0) {
                                f4_a = question4CheckBoxTags.get(0);
                                if (question4CheckBoxTags.size() > 1) {
                                    f4_b = question4CheckBoxTags.get(1);
                                }
                                if (question4CheckBoxTags.size() > 2) {
                                    f4_c = question4CheckBoxTags.get(2);
                                }
                            }
                            qf4_layout.setVisibility(View.GONE);

                            qf5_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(Section_F_table4.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf5_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qf5_id);
                    if (rg_qf5_id == R.id.rbtn_f5_1) {
                        qf5_layout.setVisibility(View.GONE);
                        f5 = radioButton.getTag().toString();

                        qf6_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qf5_id == R.id.rbtn_f5_2) {
                        qf5_layout.setVisibility(View.GONE);
                        f5 = radioButton.getTag().toString();
                        qf7_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Section_F_table4.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qf6_layout.getVisibility() == View.VISIBLE) {

                    if (question6CheckBoxCounter > 0) {
                        if (edt_qf6_other.getVisibility() == View.VISIBLE) {
                            String otherText = edt_qf6_other.getText().toString();
                            if (StringUtils.isEmpty(otherText)) {
                                Toast.makeText(Section_F_table4.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                f6_other = otherText;
                                if (question6CheckBoxTags != null && question6CheckBoxTags.size() > 0) {
                                    f6_a = question6CheckBoxTags.get(0);
                                    if (question6CheckBoxTags.size() > 1) {
                                        f6_b = question6CheckBoxTags.get(1);
                                    }
                                    if (question6CheckBoxTags.size() > 2) {
                                        f6_c = question6CheckBoxTags.get(2);
                                    }
                                }
                                qf6_layout.setVisibility(View.GONE);

                                qf7_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (question6CheckBoxTags != null && question6CheckBoxTags.size() > 0) {
                                f6_a = question6CheckBoxTags.get(0);
                                if (question6CheckBoxTags.size() > 1) {
                                    f6_b = question6CheckBoxTags.get(1);
                                }
                                if (question6CheckBoxTags.size() > 2) {
                                    f6_c = question6CheckBoxTags.get(2);
                                }
                            }
                            qf6_layout.setVisibility(View.GONE);

                            qf7_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(Section_F_table4.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                    }


                } else if (qf7_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qf7_id);
                    if (rg_qf7_id == R.id.rbtn_f7_1) {
                        qf7_layout.setVisibility(View.GONE);
                        f7 = radioButton.getTag().toString();
                        qf8_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qf7_id == R.id.rbtn_f7_2) {
                        qf7_layout.setVisibility(View.GONE);
                        f7 = radioButton.getTag().toString();
                        qf9_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Section_F_table4.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qf8_layout.getVisibility() == View.VISIBLE) {

                    if (question8CheckBoxCounter > 0) {
                        if (edt_qf8_other.getVisibility() == View.VISIBLE) {
                            String otherText = edt_qf8_other.getText().toString();
                            if (StringUtils.isEmpty(otherText)) {
                                Toast.makeText(Section_F_table4.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                f8_other = otherText;
                                if (question8CheckBoxTags != null && question8CheckBoxTags.size() > 0) {
                                    f8_a = question8CheckBoxTags.get(0);
                                    if (question8CheckBoxTags.size() > 1) {
                                        f8_b = question8CheckBoxTags.get(1);
                                    }
                                    if (question8CheckBoxTags.size() > 2) {
                                        f8_c = question8CheckBoxTags.get(2);
                                    }
                                }
                                qf8_layout.setVisibility(View.GONE);

                                qf9_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (question8CheckBoxTags != null && question8CheckBoxTags.size() > 0) {
                                f8_a = question8CheckBoxTags.get(0);
                                if (question8CheckBoxTags.size() > 1) {
                                    f8_b = question8CheckBoxTags.get(1);
                                }
                                if (question8CheckBoxTags.size() > 2) {
                                    f8_c = question8CheckBoxTags.get(2);
                                }
                            }
                            qf8_layout.setVisibility(View.GONE);

                            qf9_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(Section_F_table4.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                    }


                } else if (qf9_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qf9_id);
                    if (rg_qf9_id == R.id.rbtn_f9_1) {
                        qf9_layout.setVisibility(View.GONE);
                        qf10_layout.setVisibility(View.VISIBLE);
                        f9 = radioButton.getTag().toString();
                    } else if (rg_qf9_id == R.id.rbtn_f9_2) {
                        qf9_layout.setVisibility(View.GONE);
                        qf11_layout.setVisibility(View.VISIBLE);
                        f9 = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Section_F_table4.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qf10_layout.getVisibility() == View.VISIBLE) {

                    if (question10CheckBoxCounter > 0) {
                        if (edt_qf10_other.getVisibility() == View.VISIBLE) {
                            String otherText = edt_qf10_other.getText().toString();
                            if (StringUtils.isEmpty(otherText)) {
                                Toast.makeText(Section_F_table4.this, "Please Enter other specify", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                f10_other = otherText;
                                if (question10CheckBoxTags != null && question10CheckBoxTags.size() > 0) {
                                    f10_a = question10CheckBoxTags.get(0);
                                    if (question10CheckBoxTags.size() > 1) {
                                        f10_b = question10CheckBoxTags.get(1);
                                    }
                                    if (question10CheckBoxTags.size() > 2) {
                                        f10_c = question10CheckBoxTags.get(2);
                                    }
                                }
                                qf10_layout.setVisibility(View.GONE);

                                qf11_layout.setVisibility(View.VISIBLE);
                            }
                        } else {
                            if (question10CheckBoxTags != null && question10CheckBoxTags.size() > 0) {
                                f10_a = question10CheckBoxTags.get(0);
                                if (question10CheckBoxTags.size() > 1) {
                                    f10_b = question10CheckBoxTags.get(1);
                                }
                                if (question10CheckBoxTags.size() > 2) {
                                    f10_c = question10CheckBoxTags.get(2);
                                }
                            }
                            qf10_layout.setVisibility(View.GONE);

                            qf11_layout.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(Section_F_table4.this, "زیادہ سے زیادہ تین جوابات منتخب کر سکتے ہیں", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf11_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qf11_id);
                    if (rg_qf11_id == R.id.rbtn_f11_1) {
                        f11 = radioButton.getTag().toString();
                        Intent intent = new Intent(Section_F_table4.this, Section_F_table5.class);
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
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("id", id);
                        SaveData();
                        startActivityForResult(intent, 88);
                        //startActivity(intent);

                    } else if (rg_qf11_id == R.id.rbtn_f11_2) {
                        f11 = radioButton.getTag().toString();
                        Intent intent = new Intent(Section_F_table4.this, Section_F_table6.class);
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
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        intent.putExtra("id", id);
                        SaveData();
                        startActivityForResult(intent, 88);
                        //startActivity(intent);
                    } else {
                        Toast.makeText(Section_F_table4.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                }

                SaveData();
            }
        });


    }

    @Override
    public void onBackPressed() {

        int rg_qf3_id = rg_qf3.getCheckedRadioButtonId();
        int rg_qf5_id = rg_qf5.getCheckedRadioButtonId();
        int rg_qf7_id = rg_qf7.getCheckedRadioButtonId();
        int rg_qf9_id = rg_qf9.getCheckedRadioButtonId();
        if (qf11_layout.getVisibility() == View.VISIBLE) {

            ///////// F 11 /////////**************************
            if (rg_qf9_id == R.id.rbtn_f9_2) {
                f10_a = "";
                f10_b = "";
                f10_c = "";
                f10_other = "";
                qf9_layout.setVisibility(View.VISIBLE);
                qf11_layout.setVisibility(View.GONE);

            } else {
                qf10_layout.setVisibility(View.VISIBLE);
                qf11_layout.setVisibility(View.GONE);
            }

        } else if (qf10_layout.getVisibility() == View.VISIBLE) {

            ///////// F 10 /////////
            qf9_layout.setVisibility(View.VISIBLE);
            qf10_layout.setVisibility(View.GONE);
        } else if (qf9_layout.getVisibility() == View.VISIBLE) {

            ///////// F 9 /////////**************************
            if (rg_qf7_id == R.id.rbtn_f7_2) {
                f8_a = "";
                f8_b = "";
                f8_c = "";
                f8_other = "";
                qf7_layout.setVisibility(View.VISIBLE);
                qf9_layout.setVisibility(View.GONE);

            } else {
                qf8_layout.setVisibility(View.VISIBLE);
                qf9_layout.setVisibility(View.GONE);
            }

        } else if (qf8_layout.getVisibility() == View.VISIBLE) {

            ///////// F 8 /////////
            qf7_layout.setVisibility(View.VISIBLE);
            qf8_layout.setVisibility(View.GONE);
        } else if (qf7_layout.getVisibility() == View.VISIBLE) {

            ///////// F 7/////////
            if (rg_qf5_id == R.id.rbtn_f5_2) {
                qf5_layout.setVisibility(View.VISIBLE);
                qf7_layout.setVisibility(View.GONE);
                f6_a = "";
                f6_b = "";
                f6_c = "";
                f6_other = "";
            } else {

                qf6_layout.setVisibility(View.VISIBLE);
                qf7_layout.setVisibility(View.GONE);
            }
        } else if (qf6_layout.getVisibility() == View.VISIBLE) {

            ///////// F 6 /////////
            qf5_layout.setVisibility(View.VISIBLE);
            qf6_layout.setVisibility(View.GONE);
        } else if (qf5_layout.getVisibility() == View.VISIBLE) {

            ///////// F 5/////////
            if (rg_qf3_id == R.id.rbtn_f3_2) {
                qf3_layout.setVisibility(View.VISIBLE);
                qf5_layout.setVisibility(View.GONE);
                f4_a = "";
                f4_b = "";
                f4_c = "";
                f4_other = "";
            } else {
                qf4_layout.setVisibility(View.VISIBLE);
                qf5_layout.setVisibility(View.GONE);
            }
        } else if (qf4_layout.getVisibility() == View.VISIBLE) {

            ///////// F 4 /////////

            qf3_layout.setVisibility(View.VISIBLE);
            qf4_layout.setVisibility(View.GONE);
        } else if (qf3_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();
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

    void DialUserNumber(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(Section_F_table4.this,
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
                    Toast.makeText(Section_F_table4.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void SetQuestion4CheckBoxListener() {
        checkbox_f4_1.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_1));
        checkbox_f4_2.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_2));
        checkbox_f4_3.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_3));
        checkbox_f4_4.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_4));
        checkbox_f4_5.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_5));
        checkbox_f4_6.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_6));
        checkbox_f4_7.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_7));
        checkbox_f4_8.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_8));
        checkbox_f4_9.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_9));
        checkbox_f4_10.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_10));
        checkbox_f4_11.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_11));
        checkbox_f4_12.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_12));
        checkbox_f4_13.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_13));
        checkbox_f4_14.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_14));
        checkbox_f4_15.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_15));
        checkbox_f4_16.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_16));
        checkbox_f4_17.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_17));
        checkbox_f4_18.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_18));
        checkbox_f4_19.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_19));
        checkbox_f4_20.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_20));
        checkbox_f4_21.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_21));
        checkbox_f4_22.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_22));
        checkbox_f4_23.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_23));
        checkbox_f4_24.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_24));
        checkbox_f4_25.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_25));
        checkbox_f4_26.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_26));
        checkbox_f4_27.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_27));
        checkbox_f4_28.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_28));
        checkbox_f4_99.setOnClickListener(view -> SetQuestion4CheckBoxItemClick(checkbox_f4_99));

    }

    void SetQuestion4CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question4CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question4CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_f4_28) {
                    edt_qf4_other.setVisibility(View.VISIBLE);
                }
                question4CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_f4_28) {
                    edt_qf4_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_f4_28) {
                edt_qf4_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question4CheckBoxCounter--;
            question4CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }


    void SetQuestion6CheckBoxListener() {
        checkbox_f6_1.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_1));
        checkbox_f6_2.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_2));
        checkbox_f6_3.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_3));
        checkbox_f6_4.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_4));
        checkbox_f6_5.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_5));
        checkbox_f6_6.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_6));
        checkbox_f6_7.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_7));
        checkbox_f6_8.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_8));
        checkbox_f6_9.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_9));
        checkbox_f6_10.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_10));
        checkbox_f6_11.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_11));
        checkbox_f6_12.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_12));
        checkbox_f6_13.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_13));
        checkbox_f6_14.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_14));
        checkbox_f6_15.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_15));
        checkbox_f6_16.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_16));
        checkbox_f6_17.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_17));
        checkbox_f6_18.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_18));
        checkbox_f6_19.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_19));
        checkbox_f6_20.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_20));
        checkbox_f6_21.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_21));
        checkbox_f6_22.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_22));
        checkbox_f6_23.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_23));
        checkbox_f6_24.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_24));
        checkbox_f6_25.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_25));
        checkbox_f6_26.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_26));
        checkbox_f6_27.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_27));
        checkbox_f6_28.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_28));
        checkbox_f6_99.setOnClickListener(view -> SetQuestion6CheckBoxItemClick(checkbox_f6_99));

    }

    void SetQuestion6CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question6CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question6CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_f6_28) {
                    edt_qf6_other.setVisibility(View.VISIBLE);
                }
                question6CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_f6_28) {
                    edt_qf6_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_f6_28) {
                edt_qf6_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question6CheckBoxCounter--;
            question6CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }


    void SetQuestion8CheckBoxListener() {
        checkbox_f8_1.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_1));
        checkbox_f8_2.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_2));
        checkbox_f8_3.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_3));
        checkbox_f8_4.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_4));
        checkbox_f8_5.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_5));
        checkbox_f8_6.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_6));
        checkbox_f8_7.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_7));
        checkbox_f8_8.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_8));
        checkbox_f8_9.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_9));
        checkbox_f8_10.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_10));
        checkbox_f8_11.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_11));
        checkbox_f8_12.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_12));
        checkbox_f8_13.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_13));
        checkbox_f8_14.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_14));
        checkbox_f8_15.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_15));
        checkbox_f8_16.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_16));
        checkbox_f8_17.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_17));
        checkbox_f8_18.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_18));
        checkbox_f8_19.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_19));
        checkbox_f8_20.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_20));
        checkbox_f8_21.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_21));
        checkbox_f8_22.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_22));
        checkbox_f8_23.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_23));
        checkbox_f8_24.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_24));
        checkbox_f8_25.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_25));
        checkbox_f8_26.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_26));
        checkbox_f8_27.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_27));
        checkbox_f8_28.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_28));
        checkbox_f8_99.setOnClickListener(view -> SetQuestion8CheckBoxItemClick(checkbox_f8_99));

    }

    void SetQuestion8CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question8CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question8CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_f8_28) {
                    edt_qf8_other.setVisibility(View.VISIBLE);
                }
                question8CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_f8_28) {
                    edt_qf8_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_f8_28) {
                edt_qf8_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question8CheckBoxCounter--;
            question8CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }

    void SetQuestion10CheckBoxListener() {
        checkbox_f10_1.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_1));
        checkbox_f10_2.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_2));
        checkbox_f10_3.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_3));
        checkbox_f10_4.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_4));
        checkbox_f10_5.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_5));
        checkbox_f10_6.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_6));
        checkbox_f10_7.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_7));
        checkbox_f10_8.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_8));
        checkbox_f10_9.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_9));
        checkbox_f10_10.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_10));
        checkbox_f10_11.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_11));
        checkbox_f10_12.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_12));
        checkbox_f10_13.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_13));
        checkbox_f10_14.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_14));
        checkbox_f10_15.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_15));
        checkbox_f10_16.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_16));
        checkbox_f10_17.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_17));
        checkbox_f10_18.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_18));
        checkbox_f10_19.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_19));
        checkbox_f10_20.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_20));
        checkbox_f10_21.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_21));
        checkbox_f10_22.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_22));
        checkbox_f10_23.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_23));
        checkbox_f10_24.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_24));
        checkbox_f10_25.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_25));
        checkbox_f10_26.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_26));
        checkbox_f10_27.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_27));
        checkbox_f10_28.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_28));
        checkbox_f10_99.setOnClickListener(view -> SetQuestion10CheckBoxItemClick(checkbox_f10_99));

    }

    void SetQuestion10CheckBoxItemClick(CheckBox checkBox) {
        if (checkBox.isChecked()) {

            if (question10CheckBoxCounter < 3) {
                checkBox.setChecked(true);
                question10CheckBoxCounter++;

                if (checkBox.getId() == R.id.checkbox_f10_28) {
                    edt_qf10_other.setVisibility(View.VISIBLE);
                }
                question10CheckBoxTags.add(checkBox.getTag().toString());


            } else {
                if (checkBox.getId() == R.id.checkbox_f10_28) {
                    edt_qf10_other.setVisibility(View.GONE);
                }
                checkBox.setChecked(false);
            }

        } else {
            if (checkBox.getId() == R.id.checkbox_f10_28) {
                edt_qf10_other.setVisibility(View.GONE);
            }
            checkBox.setChecked(false);
            question10CheckBoxCounter--;
            question10CheckBoxTags.remove(checkBox.getTag().toString());
        }
    }


    void SaveData() {

        MubLog.cpnsoleLog("F9_" + f9);
        try {

            createNewColumnsBeforeInsertion (DatabaseAdapter.SectionFTable4);



            databaseAccess.saveSectionF_table4_Data(enum_name,enum_code,emp_id, order_id, FarmerID, f3, f4_a, f4_b, f4_c, f4_other, f5, f6_a, f6_b, f6_c, f6_other, f7, f8_a, f8_b, f8_c, f8_other, f9, f10_a, f10_b, f10_c
                    , f10_other, f11, comments);



            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionFTable4);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionFTable4);


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



    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionF_table_4Data(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                f3 = cursor.getString(cursor.getColumnIndex("f3"));
                f4_a = cursor.getString(cursor.getColumnIndex("f4_a"));
                f4_b = cursor.getString(cursor.getColumnIndex("f4_b"));
                f4_c = cursor.getString(cursor.getColumnIndex("f4_c"));
                f4_other = cursor.getString(cursor.getColumnIndex("f4_other"));
                f5 = cursor.getString(cursor.getColumnIndex("f5"));
                f6_a = cursor.getString(cursor.getColumnIndex("f6_a"));
                f6_b = cursor.getString(cursor.getColumnIndex("f6_b"));
                f6_c = cursor.getString(cursor.getColumnIndex("f6_c"));
                f6_other = cursor.getString(cursor.getColumnIndex("f6_other"));
                f7 = cursor.getString(cursor.getColumnIndex("f7"));
                f8_a = cursor.getString(cursor.getColumnIndex("f8_a"));
                f8_b = cursor.getString(cursor.getColumnIndex("f8_b"));
                f8_c = cursor.getString(cursor.getColumnIndex("f8_c"));
                f8_other = cursor.getString(cursor.getColumnIndex("f8_other"));
                f9 = cursor.getString(cursor.getColumnIndex("f9"));
                f10_a = cursor.getString(cursor.getColumnIndex("f10_a"));
                f10_b = cursor.getString(cursor.getColumnIndex("f10_b"));
                f10_c = cursor.getString(cursor.getColumnIndex("f10_c"));
                f10_other = cursor.getString(cursor.getColumnIndex("f10_other"));
                f11 = cursor.getString(cursor.getColumnIndex("f11"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {

        readFromDataBase();
        if (!StringUtils.isEmpty(f3)) {
            for (int i = 0; i < rg_qf3.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf3.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f3)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        for (int i = 0; i < qf4_layout.getChildCount(); i++) {

            View childBView = qf4_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(f4_a) && checkBox.getTag().toString().equalsIgnoreCase(f4_a)) {
                        question4CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f4_b) && checkBox.getTag().toString().equalsIgnoreCase(f4_b)) {
                        question4CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f4_c) && checkBox.getTag().toString().equalsIgnoreCase(f4_c)) {
                        question4CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }

        if (!StringUtils.isEmpty(f4_other)) {
            edt_qf4_other.setText(f4_other);
            edt_qf4_other.setVisibility(View.VISIBLE);
        } else {
            edt_qf4_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(f5)) {
            for (int i = 0; i < rg_qf5.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf5.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f5)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        for (int i = 0; i < qf6_layout.getChildCount(); i++) {

            View childBView = qf6_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(f6_a) && checkBox.getTag().toString().equalsIgnoreCase(f6_a)) {
                        question6CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f6_b) && checkBox.getTag().toString().equalsIgnoreCase(f6_b)) {
                        question6CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f6_c) && checkBox.getTag().toString().equalsIgnoreCase(f6_c)) {
                        question6CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }
        if (!StringUtils.isEmpty(f6_other)) {
            edt_qf6_other.setText(f6_other);
            edt_qf6_other.setVisibility(View.VISIBLE);
        } else {
            edt_qf6_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(f7)) {
            for (int i = 0; i < rg_qf7.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf7.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f7)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        for (int i = 0; i < qf8_layout.getChildCount(); i++) {

            View childBView = qf8_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(f8_a) && checkBox.getTag().toString().equalsIgnoreCase(f8_a)) {
                        question8CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f8_b) && checkBox.getTag().toString().equalsIgnoreCase(f8_b)) {
                        question8CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f8_c) && checkBox.getTag().toString().equalsIgnoreCase(f8_c)) {
                        question8CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }
        if (!StringUtils.isEmpty(f8_other)) {
            edt_qf8_other.setText(f8_other);
            edt_qf8_other.setVisibility(View.VISIBLE);
        } else {
            edt_qf8_other.setVisibility(View.GONE);
        }

        if (!StringUtils.isEmpty(f9)) {
            for (int i = 0; i < rg_qf9.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf9.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f9)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

        for (int i = 0; i < qf10_layout.getChildCount(); i++) {

            View childBView = qf10_layout.getChildAt(i);
            if (childBView instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) childBView;
                if (checkBox != null) {
                    if (!StringUtils.isEmpty(f10_a) && checkBox.getTag().toString().equalsIgnoreCase(f10_a)) {
                        question10CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f10_b) && checkBox.getTag().toString().equalsIgnoreCase(f10_b)) {
                        question10CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }
                    if (!StringUtils.isEmpty(f10_c) && checkBox.getTag().toString().equalsIgnoreCase(f10_c)) {
                        question10CheckBoxCounter++;
                        checkBox.setChecked(true);
                    }

                }
            }


        }
        if (!StringUtils.isEmpty(f10_other)) {
            edt_qf10_other.setText(f10_other);
            edt_qf10_other.setVisibility(View.VISIBLE);
        } else {
            edt_qf10_other.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(f11)) {
            for (int i = 0; i < rg_qf11.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qf11.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(f11)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        ////////////////////////////
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
