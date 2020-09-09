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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Section_F_table1 extends AppCompatActivity {
    boolean isFromEdit = false;
    LinearLayout caller_layout;
    DatabaseAdapter databaseAccess;
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

    @BindView(R.id.qd8a_layout)
    LinearLayout qd8a_layout;
    @BindView(R.id.qd8b_layout)
    LinearLayout qd8b_layout;
    @BindView(R.id.qd8c_layout)
    LinearLayout qd8c_layout;
    @BindView(R.id.qd8d_layout)
    LinearLayout qd8d_layout;
    @BindView(R.id.qd9_layout)
    LinearLayout qd9_layout;
    @BindView(R.id.qd10_layout)
    LinearLayout qd10_layout;
    @BindView(R.id.qd11_layout)
    LinearLayout qd11_layout;
    @BindView(R.id.qd12_layout)
    LinearLayout qd12_layout;


    //////////  Radio Group /////////////////
    @BindView(R.id.rg_qd8a)
    RadioGroup rg_qd8a;
    @BindView(R.id.rg_qd8b)
    RadioGroup rg_qd8b;
    @BindView(R.id.rg_qd10)
    RadioGroup rg_qd10;
    @BindView(R.id.rg_qd12)
    RadioGroup rg_qd12;

    //////////// Edit Text //////////////////
    @BindView(R.id.edt_d8b_other)
    EditText edt_d8b_other;
    @BindView(R.id.edt_d8c_kanal)
    EditText edt_d8c_kanal;
    @BindView(R.id.edt_d8c_acre)
    EditText edt_d8c_acre;
    @BindView(R.id.edt_d8d_kanal)
    EditText edt_d8d_kanal;
    @BindView(R.id.edt_d8d_acre)
    EditText edt_d8d_acre;
    @BindView(R.id.edt_d9_kanal)
    EditText edt_d9_kanal;
    @BindView(R.id.edt_d9_acre)
    EditText edt_d9_acre;
    @BindView(R.id.edt_d11_kanal)
    EditText edt_d11_kanal;
    @BindView(R.id.edt_d11_acre)
    EditText edt_d11_acre;

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;


    String d8a = "";
    String d8b = "";
    String d8b_other = "";
    String d8c_kanal = "";
    String d8c_acre = "";
    String d8d_kanal = "";
    String d8d_acre = "";
    String d9_kanal = "";
    String d9_acre = "";
    String d10 = "";
    String d11_kanal = "";
    String d11_acre = "";
    String d12 = "";
    String comments = "";
    String enum_name = "";
    String enum_code = "";

    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;

    ImageView btniBack;

    TextView txtQuestionD9Text;

    Integer d8_total_kanals = 0;
    Integer d9_total_kanals = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section__f_table1);
        ButterKnife.bind(this);

        btniBack = findViewById(R.id.btnBack);

        databaseAccess = new DatabaseAdapter(Section_F_table1.this);
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

        txtQuestionD9Text = findViewById(R.id.txtQuestionD9Text);

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
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
                int rg_qd8a_id = rg_qd8a.getCheckedRadioButtonId();
                int rg_qd8b_id = rg_qd8b.getCheckedRadioButtonId();
                int rg_qd10_id = rg_qd10.getCheckedRadioButtonId();
                int rg_qd12_id = rg_qd12.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(Section_F_table1.this);

                if (qd8a_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qd8a_id);

                    if (rg_qd8a_id == R.id.rbtn_d8a_1) {
                        qd8a_layout.setVisibility(View.GONE);
                        qd8d_layout.setVisibility(View.VISIBLE);
                        d8a = radioButton.getTag().toString();
                    } else if (rg_qd8a_id == R.id.rbtn_d8a_2 || rg_qd8a_id == R.id.rbtn_d8a_3) {
                        qd8a_layout.setVisibility(View.GONE);
                        qd8b_layout.setVisibility(View.VISIBLE);
                        d8a = radioButton.getTag().toString();
                    } else {
                        Toast.makeText(Section_F_table1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qd8b_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qd8b_id);

                    if (rg_qd8b_id == R.id.rbtn_d8b_1) {
                        qd8b_layout.setVisibility(View.GONE);
                        d8b = radioButton.getTag().toString();
                        qd8c_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qd8b_id == R.id.rbtn_d8b_2 || rg_qd8b_id == R.id.rbtn_d8b_3) {
                        edt_d8b_other.setVisibility(View.VISIBLE);
                        d8b_other = edt_d8b_other.getText().toString();
                        d8b = radioButton.getTag().toString();
                        if (StringUtils.isEmpty(d8b_other)) {
                            Toast.makeText(Section_F_table1.this, "Please Enter other", Toast.LENGTH_SHORT).show();
                        } else {
                            qd8b_layout.setVisibility(View.GONE);
                            d8b_other = edt_d8b_other.getText().toString();
                            d8b = radioButton.getTag().toString();
                            if (!StringUtils.isEmpty(d8a)) {
                                if (d8a.equalsIgnoreCase("2") || d8a.equalsIgnoreCase("3")) {
                                    qd8c_layout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                qd8d_layout.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        Toast.makeText(Section_F_table1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qd8c_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q d8c ///////////////Logic
                    d8c_acre = edt_d8c_acre.getText().toString();
                    d8c_kanal = edt_d8c_kanal.getText().toString();

                    if (StringUtils.isEmpty(d8c_acre)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d8c_kanal)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d8c_acre) < 0 || Integer.parseInt(d8c_acre) > 2000) && !d8c_acre.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre in 0-2000", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d8c_kanal) > 7) && !d8c_kanal.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Kanal Must be from 0-7", Toast.LENGTH_SHORT).show();
                    } else {
                        qd8c_layout.setVisibility(View.GONE);
                        qd8d_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qd8d_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q d8d /////////////// Logic
                    d8d_kanal = edt_d8d_kanal.getText().toString();
                    d8d_acre = edt_d8d_acre.getText().toString();

                    if (StringUtils.isEmpty(d8d_acre)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d8d_kanal)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d8d_acre) < 0 || Integer.parseInt(d8d_acre) > 2000) && !d8d_acre.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre in 0-2000", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d8d_kanal) > 7) && !d8d_kanal.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8d_kanal) == 0 && Integer.parseInt(d8d_acre) == 0) {
                        qd8d_layout.setVisibility(View.GONE);
                        qd11_layout.setVisibility(View.VISIBLE);
                    } else {

                        qd8d_layout.setVisibility(View.GONE);
                        qd9_layout.setVisibility(View.VISIBLE);
                        txtQuestionD9Text.setText(" اس موسم میں آپ یا آپ کے گھرانے نے( " + d8d_acre + ") ایکڑ اور (" + d8d_kanal + ")کنال  میں سے کتنی زمین ٹھیکے (حصے) پہ دی ہوئی ہے؟ ");
                    }

                } else if (qd9_layout.getVisibility() == View.VISIBLE) {
                    d9_kanal = edt_d9_kanal.getText().toString();
                    d9_acre = edt_d9_acre.getText().toString();
                    if (StringUtils.isEmpty(d9_acre)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d9_kanal)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d9_acre) < 0 || Integer.parseInt(d9_acre) > 2000) && !d9_acre.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre in 0-2000", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d9_kanal) > 7) && !d9_kanal.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else if (!StringUtils.isEmpty(d9_acre)) {
                        d8_total_kanals = (Integer.parseInt(d8d_acre) * 7) + Integer.parseInt(d8d_kanal);
                        d9_total_kanals = (Integer.parseInt(d9_acre) * 7) + Integer.parseInt(d9_kanal);
                        MubLog.cpnsoleLog("d8_d9 " + "d8_total_kanals ID = " + d8_total_kanals + " d9_total_kanals = " + Integer.toString(d9_total_kanals));
                        if ((d9_total_kanals > d8_total_kanals) && !d8d_acre.equalsIgnoreCase("-99") && !d8d_kanal.equalsIgnoreCase("-99")
                                && !d9_acre.equalsIgnoreCase("-99") && !d9_kanal.equalsIgnoreCase("-99")) {
                            Toast.makeText(Section_F_table1.this, " جواب سوالنمبر D8d کے برابر رقبہ  لکھیں یا کم", Toast.LENGTH_SHORT).show();
                        } else if (Integer.parseInt(d9_kanal) == 0 && Integer.parseInt(d9_acre) == 0) {
                            qd9_layout.setVisibility(View.GONE);
                            qd11_layout.setVisibility(View.VISIBLE);

                        } else {
                            qd9_layout.setVisibility(View.GONE);
                            qd10_layout.setVisibility(View.VISIBLE);
                        }
                    }

                } else if (qd10_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q d10 ////////
                    RadioButton radioButton = findViewById(rg_qd10_id);
                    if (rg_qd10_id == R.id.rbtn_d10_1
                            || rg_qd10_id == R.id.rbtn_d10_2
                            || rg_qd10_id == R.id.rbtn_d10_3) {
                        qd10_layout.setVisibility(View.GONE);
                        qd11_layout.setVisibility(View.VISIBLE);
                        d10 = radioButton.getTag().toString();

                    } else {
                        Toast.makeText(Section_F_table1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qd11_layout.getVisibility() == View.VISIBLE) {

                    d11_kanal = edt_d11_kanal.getText().toString();
                    d11_acre = edt_d11_acre.getText().toString();
                    if (StringUtils.isEmpty(d11_acre)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    }
                    if (StringUtils.isEmpty(d11_kanal)) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d11_acre) < 0 || Integer.parseInt(d11_acre) > 2000) && !d11_acre.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Please Enter Acre in 0-2000", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(d11_kanal) > 7) && !d11_kanal.equalsIgnoreCase("-99")) {
                        Toast.makeText(Section_F_table1.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d11_kanal) == 0 && Integer.parseInt(d11_acre) == 0) {
                        Intent intent = new Intent(Section_F_table1.this, Section_F_table2.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("isPendingCall", isPendingCall);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("id", id);
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
                        startActivityForResult(intent, 88);

                    } else {
                        qd11_layout.setVisibility(View.GONE);
                        qd12_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qd12_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qd12_id);
                    if (rg_qd12_id == R.id.rbtn_d12_1
                            || rg_qd12_id == R.id.rbtn_d12_2
                            || rg_qd12_id == R.id.rbtn_d12_3) {
                        d12 = radioButton.getTag().toString();
                        Intent intent = new Intent(Section_F_table1.this, Section_F_table2.class);
                        intent.putExtra("username", userName);
                        intent.putExtra("isPendingCall", isPendingCall);
                        intent.putExtra("region", region);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmerID", FarmerID);
                        intent.putExtra("id", id);
                        intent.putExtra("farmerDistrict", farmerDistrict);
                        intent.putExtra("farmerVillage", farmerVillage);
                        intent.putExtra("farmerTehsil", farmerTehsil);
                        intent.putExtra("ao_name", ao_name);
                        intent.putExtra("fa_name", fa_name);
                        intent.putExtra("ai_name", ai_name);
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        startActivityForResult(intent, 88);

                    } else {
                        Toast.makeText(Section_F_table1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(Section_F_table1.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                }
                SaveData();
            }
        });
        rg_qd8b.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int rg_qd8b_id = rg_qd8b.getCheckedRadioButtonId();
                if (rg_qd8b_id == R.id.rbtn_d8b_2 || rg_qd8b_id == R.id.rbtn_d8b_3) {
                    //do work when radioButton1 is active
                    edt_d8b_other.setVisibility(View.VISIBLE);
                } else {
                    edt_d8b_other.setText("");
                    edt_d8b_other.setVisibility(View.GONE);
                }


            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Section_F_table1.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "section_f_table1");
                startActivityForResult(intent, 88);
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
                        Toast.makeText(Section_F_table1.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

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
        if (ActivityCompat.checkSelfPermission(Section_F_table1.this,
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
                    Toast.makeText(Section_F_table1.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {

        int rg_qd8a_id = rg_qd8a.getCheckedRadioButtonId();

        if (qd12_layout.getVisibility() == View.VISIBLE) {

            ///////// D 12 /////////

            qd11_layout.setVisibility(View.VISIBLE);
            qd12_layout.setVisibility(View.GONE);
        } else if (qd11_layout.getVisibility() == View.VISIBLE) {

            ///////// D 11  /////////
            //d8d_kanal = edt_d8d_kanal.getText().toString();
            //d8d_acre = edt_d8d_acre.getText().toString();
            if (Integer.parseInt(d8d_kanal) == 0 && Integer.parseInt(d8d_acre) == 0) {
                qd8d_layout.setVisibility(View.VISIBLE);
                qd11_layout.setVisibility(View.GONE);
            } else {
                if (Integer.parseInt(d9_kanal) == 0 && Integer.parseInt(d9_acre) == 0) {
                    d9_kanal = "";
                    d9_acre = "";
                    qd9_layout.setVisibility(View.VISIBLE);
                    qd11_layout.setVisibility(View.GONE);
                } else {
                    d10 = "";
                    qd10_layout.setVisibility(View.VISIBLE);
                    qd11_layout.setVisibility(View.GONE);
                }

            }

        } else if (qd10_layout.getVisibility() == View.VISIBLE) {

            ///////// D 10 /////////
            d9_kanal = "";
            d9_acre = "";
            qd9_layout.setVisibility(View.VISIBLE);
            qd10_layout.setVisibility(View.GONE);

        } else if (qd9_layout.getVisibility() == View.VISIBLE) {

            ///////// D 9 /////////

            d8d_kanal = "";
            d8d_acre = "";

            qd8d_layout.setVisibility(View.VISIBLE);
            qd9_layout.setVisibility(View.GONE);


        } else if (qd8d_layout.getVisibility() == View.VISIBLE) {

            ///////// D 8 d /////////
            if (rg_qd8a_id == R.id.rbtn_d8a_1) {

                d8c_acre = "";
                d8c_kanal = "";
                d8b = "";
                d8b_other = "";

                qd8a_layout.setVisibility(View.VISIBLE);
                qd8d_layout.setVisibility(View.GONE);

            } else {

                d8c_kanal = "";
                d8c_acre = "";

                qd8c_layout.setVisibility(View.VISIBLE);
                qd8d_layout.setVisibility(View.GONE);
            }

        } else if (qd8c_layout.getVisibility() == View.VISIBLE) {

            ///////// D 8 c /////////

            d8b = "";
            d8b_other = "";
            qd8b_layout.setVisibility(View.VISIBLE);
            qd8c_layout.setVisibility(View.GONE);


        } else if (qd8b_layout.getVisibility() == View.VISIBLE) {

            ///////// D 8 b /////////

            qd8a_layout.setVisibility(View.VISIBLE);
            qd8b_layout.setVisibility(View.GONE);


        } else if (qd8a_layout.getVisibility() == View.VISIBLE) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();
        }


    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionF_table_1Data(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                d8a = cursor.getString(cursor.getColumnIndex("d8a"));
                d8b = cursor.getString(cursor.getColumnIndex("d8b"));
                d8b_other = cursor.getString(cursor.getColumnIndex("d8b_other"));
                d8c_kanal = cursor.getString(cursor.getColumnIndex("d8c_kanal"));
                d8c_acre = cursor.getString(cursor.getColumnIndex("d8c_acre"));
                d8d_kanal = cursor.getString(cursor.getColumnIndex("d8d_kanal"));
                d8d_acre = cursor.getString(cursor.getColumnIndex("d8d_acre"));
                d9_kanal = cursor.getString(cursor.getColumnIndex("d9_kanal"));
                d9_acre = cursor.getString(cursor.getColumnIndex("d9_acre"));
                d10 = cursor.getString(cursor.getColumnIndex("d10"));
                d11_kanal = cursor.getString(cursor.getColumnIndex("d11_kanal"));
                d11_acre = cursor.getString(cursor.getColumnIndex("d11_acre"));
                d12 = cursor.getString(cursor.getColumnIndex("d12"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        if (!StringUtils.isEmpty(d8a)) {
            for (int i = 0; i < rg_qd8a.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qd8a.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d8a)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
        if (!StringUtils.isEmpty(d8b)) {
            for (int i = 0; i < rg_qd8b.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qd8b.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d8b)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(d8b_other)) {
            edt_d8b_other.setVisibility(View.VISIBLE);
            edt_d8b_other.setText(d8b_other);
        } else {
            edt_d8b_other.setVisibility(View.GONE);
        }


        if (!StringUtils.isEmpty(d8c_kanal)) {
            edt_d8c_kanal.setText(d8c_kanal);
        }

        if (!StringUtils.isEmpty(d8c_acre)) {
            edt_d8c_acre.setText(d8c_acre);
        }
        if (!StringUtils.isEmpty(d8d_kanal)) {
            edt_d8d_kanal.setText(d8d_kanal);
        }

        if (!StringUtils.isEmpty(d8d_acre)) {
            edt_d8d_acre.setText(d8d_acre);
        }

        if (!StringUtils.isEmpty(d9_acre)) {
            edt_d9_acre.setText(d9_acre);
        }

        if (!StringUtils.isEmpty(d9_kanal)) {
            edt_d9_kanal.setText(d9_kanal);
        }

        if (!StringUtils.isEmpty(d10)) {
            for (int i = 0; i < rg_qd10.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qd10.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d10)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(d11_acre)) {
            edt_d11_acre.setText(d11_acre);
        }

        if (!StringUtils.isEmpty(d11_kanal)) {
            edt_d11_kanal.setText(d11_kanal);
        }

        if (!StringUtils.isEmpty(d12)) {
            for (int i = 0; i < rg_qd12.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qd12.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(d12)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }

    }

    void SaveData() {

        try {

            createNewColumnsBeforeInsertion (DatabaseAdapter.SectionFTable1);



            databaseAccess.saveSectionF_table1_Data(enum_name,enum_code,emp_id, order_id, FarmerID, d8a, d8b, d8b_other, d8c_kanal, d8c_acre, d8d_kanal, d8d_acre, d9_kanal, d9_acre, d10, d11_kanal, d11_acre, d12, comments);




            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionFTable1);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionFTable1);

            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionFTable1);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {
            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY "+tableName);

            String patch_querry =   "UPDATE  "+tableName+" SET  d12 ='' WHERE d11_kanal IN (0,00) AND d11_acre  IN (0,00) AND d12 !=''";
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
