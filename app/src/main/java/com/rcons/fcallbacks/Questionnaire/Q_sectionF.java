package com.rcons.fcallbacks.Questionnaire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rcons.fcallbacks.Adapter.F10CropSelectionAdapter;
import com.rcons.fcallbacks.Adapter.F4CropSelectionAdapter;
import com.rcons.fcallbacks.Adapter.F6CropSelectionAdapter;
import com.rcons.fcallbacks.Adapter.F8CropSelectionAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Interfaces.onF10CheckBoxClick;
import com.rcons.fcallbacks.Interfaces.onF4CheckBoxClick;
import com.rcons.fcallbacks.Interfaces.onF6CheckBoxClick;
import com.rcons.fcallbacks.Interfaces.onF8CheckBoxClick;
import com.rcons.fcallbacks.Model.Crop;
import com.rcons.fcallbacks.Model.CropModel;
import com.rcons.fcallbacks.Model.SectionF12;
import com.rcons.fcallbacks.Model.SectionF2;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Q_sectionF extends AppCompatActivity implements onF4CheckBoxClick, onF6CheckBoxClick, onF8CheckBoxClick, onF10CheckBoxClick {

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
    @BindView(R.id.qf1_f2a_f2b_layout)
    LinearLayout qf1_f2a_f2b_layout;
    @BindView(R.id.qf1_layout)
    LinearLayout qf1_layout;
    @BindView(R.id.qf2a_layout)
    LinearLayout qf2a_layout;
    @BindView(R.id.qf2b_layout)
    LinearLayout qf2b_layout;
    @BindView(R.id.qf2c_layout)
    LinearLayout qf2c_layout;
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
    @BindView(R.id.qf12_f13_f14_f15_layout)
    LinearLayout qf12_f13_f14_f15_layout;
    @BindView(R.id.qf12_layout)
    LinearLayout qf12_layout;
    @BindView(R.id.qf13_layout)
    LinearLayout qf13_layout;
    @BindView(R.id.qf14_layout)
    LinearLayout qf14_layout;
    @BindView(R.id.qf15_layout)
    LinearLayout qf15_layout;
    @BindView(R.id.qf16_layout)
    LinearLayout qf16_layout;
    @BindView(R.id.qf17_layout)
    LinearLayout qf17_layout;
    @BindView(R.id.qf18_layout)
    LinearLayout qf18_layout;
    @BindView(R.id.qf19_layout)
    LinearLayout qf19_layout;
    @BindView(R.id.qf20_layout)
    LinearLayout qf20_layout;

    @BindView(R.id.rg_qd8a)
    RadioGroup rg_qd8a;
    @BindView(R.id.rg_qd8b)
    RadioGroup rg_qd8b;
    @BindView(R.id.rg_qd10)
    RadioGroup rg_qd10;
    @BindView(R.id.rg_qd12)
    RadioGroup rg_qd12;
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
    @BindView(R.id.rg_qf13)
    RadioGroup rg_qf13;
    @BindView(R.id.rg_qf14)
    RadioGroup rg_qf14;
    @BindView(R.id.rg_qf15)
    RadioGroup rg_qf15;
    @BindView(R.id.rg_qf16)
    RadioGroup rg_qf16;
    @BindView(R.id.rg_qf17)
    RadioGroup rg_qf17;
    @BindView(R.id.rg_qf19)
    RadioGroup rg_qf19;
    @BindView(R.id.rg_qf20)
    RadioGroup rg_qf20;
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

    @BindView(R.id.edt_qf1_crop)
    EditText edt_qf1_crop;
    @BindView(R.id.edt_qf1_other)
    EditText edt_qf1_other;

    @BindView(R.id.edt_qf2a_kanal)
    EditText edt_qf2a_kanal;
    @BindView(R.id.edt_qf2a_acre)
    EditText edt_qf2a_acre;

    @BindView(R.id.edt_qf2b_crop)
    EditText edt_qf2b_crop;
    @BindView(R.id.edt_qf2b_other)
    EditText edt_qf2b_other;
    @BindView(R.id.edt_qf2b_kanal)
    EditText edt_qf2b_kanal;
    @BindView(R.id.edt_qf2b_acre)
    EditText edt_qf2b_acre;

    @BindView(R.id.edt_qf2c_crop)
    EditText edt_qf2c_crop;
    @BindView(R.id.edt_qf2c_other)
    EditText edt_qf2c_other;
    @BindView(R.id.edt_qf2c_kanal)
    EditText edt_qf2c_kanal;
    @BindView(R.id.edt_qf2c_acre)
    EditText edt_qf2c_acre;


    @BindView(R.id.edt_qf4_a)
    EditText edt_qf4_a;
    @BindView(R.id.edt_qf4_b)
    EditText edt_qf4_b;
    @BindView(R.id.edt_qf4_c)
    EditText edt_qf4_c;
    @BindView(R.id.edt_qf4_other)
    EditText edt_qf4_other;


    @BindView(R.id.edt_qf6_a)
    EditText edt_qf6_a;
    @BindView(R.id.edt_qf6_b)
    EditText edt_qf6_b;
    @BindView(R.id.edt_qf6_c)
    EditText edt_qf6_c;
    @BindView(R.id.edt_qf6_other)
    EditText edt_qf6_other;

    @BindView(R.id.edt_qf8_a)
    EditText edt_qf8_a;
    @BindView(R.id.edt_qf8_b)
    EditText edt_qf8_b;
    @BindView(R.id.edt_qf8_c)
    EditText edt_qf8_c;
    @BindView(R.id.edt_qf8_other)
    EditText edt_qf8_other;

    @BindView(R.id.edt_qf10_a)
    EditText edt_qf10_a;
    @BindView(R.id.edt_qf10_b)
    EditText edt_qf10_b;
    @BindView(R.id.edt_qf10_c)
    EditText edt_qf10_c;
    @BindView(R.id.edt_qf10_other)
    EditText edt_qf10_other;

    @BindView(R.id.edt_qf12_crop)
    EditText edt_qf12_crop;
    @BindView(R.id.edt_qf12_other)
    EditText edt_qf12_other;

    @BindView(R.id.edt_qf13_other)
    EditText edt_qf13_other;
    @BindView(R.id.edt_qf14_other)
    EditText edt_qf14_other;
    @BindView(R.id.edt_qf15_other)
    EditText edt_qf15_other;
    @BindView(R.id.edt_qf18)
    EditText edt_qf18;

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;
    @BindView(R.id.edt_qf1_crop_spinner)
    SearchableSpinner edt_qf1_crop_spinner;

    @BindView(R.id.edt_qf2b_crop_spinner)
    SearchableSpinner edt_qf2b_crop_spinner;

    @BindView(R.id.qf2c_crop_spinner)
    SearchableSpinner qf2c_crop_spinner;

    @BindView(R.id.qf12_crop_spinner)
    SearchableSpinner qf12_crop_spinner;

    @BindView(R.id.btnAddMore)
    Button btnAddMore;

    @BindView(R.id.btn2fcAddMore)
    Button btn2fcAddMore;

    @BindView(R.id.btnAddMoreF12)
    Button btnAddMoreF12;

    @BindView(R.id.txtf2aError)
    TextView txtf2aError;

    @BindView(R.id.txtf12Error)
    TextView txtf12Error;

    LinearLayoutManager f4Manager;
    @BindView(R.id.rvF4Crops)
    RecyclerView rvF4Crops;
    F4CropSelectionAdapter f4CropSelectionAdapter;
    ArrayList<Crop> f4CropList;

    LinearLayoutManager f6Manager;
    @BindView(R.id.rvF6Crops)
    RecyclerView rvF6Crops;
    F6CropSelectionAdapter f6CropSelectionAdapter;
    ArrayList<Crop> f6CropList;


    LinearLayoutManager f8Manager;
    @BindView(R.id.rvF8Crops)
    RecyclerView rvF8Crops;
    F8CropSelectionAdapter f8CropSelectionAdapter;
    ArrayList<Crop> f8CropList;

    LinearLayoutManager f10Manager;
    @BindView(R.id.rvF10Crops)
    RecyclerView rvF10Crops;
    F10CropSelectionAdapter f10CropSelectionAdapter;
    ArrayList<Crop> f10CropList;

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
    String f1_crop = "";
    String f1_other = "";
    String f2a_kanal = "";
    String f2a_acre = "";
    String f2b_crop = "";
    String f2b_other = "";
    String f2b_kanal = "";
    String f2b_acre = "";
    String f2c_crop = "";
    String f2c_other = "";
    String f2c_kanal = "";
    String f2c_acre = "";
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
    String f12_crop = "";
    String f12_other = "";
    String f13 = "";
    String f13_other = "";
    String f14 = "";
    String f14_other = "";
    String f15 = "";
    String f15_other = "";
    String f16 = "";
    String f17 = "";
    String f18 = "";
    String f19 = "";
    String f20 = "";
    CropModel f2aCrops;
    CropModel f2bCrops = null;
    CropModel f2cCrops = null;
    CropModel f12Crops = null;

    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;
    TextView txtQuestionD9Text;
    ArrayList<SectionF2> sectionF2ArrayList;
    ArrayList<SectionF12> sectionF12ArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.q_section_f);
        ButterKnife.bind(this);

        databaseAccess = new DatabaseAdapter(Q_sectionF.this);
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

        f4Manager = new LinearLayoutManager(Q_sectionF.this, RecyclerView.VERTICAL, false);
        rvF4Crops.setLayoutManager(f4Manager);

        f6Manager = new LinearLayoutManager(Q_sectionF.this, RecyclerView.VERTICAL, false);
        rvF6Crops.setLayoutManager(f6Manager);

        f8Manager = new LinearLayoutManager(Q_sectionF.this, RecyclerView.VERTICAL, false);
        rvF8Crops.setLayoutManager(f8Manager);

        f10Manager = new LinearLayoutManager(Q_sectionF.this, RecyclerView.VERTICAL, false);
        rvF10Crops.setLayoutManager(f10Manager);

        btn_RedialCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    if (!StringUtils.isEmpty(farmer_cellphone)) {
                        DialUserNumber(farmer_cellphone);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        edt_qf1_crop_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if (f2aCrops != null) {
                    f1_crop = f2aCrops.getCropID().get(position);
                    if (!StringUtils.isEmpty(f1_crop) && f1_crop.equalsIgnoreCase("28")) {
                        edt_qf1_other.setVisibility(View.VISIBLE);
                        edt_qf1_other.requestFocus();
                    } else {
                        edt_qf1_other.setVisibility(View.GONE);
                        edt_qf1_other.setText("");
                    }


                    if (f2aCrops != null && !StringUtils.isEmpty(f1_crop)) {
                        f2bCrops = new CropModel();

                        f2bCrops.getCropID().add("-99");
                        f2bCrops.getCropCategory().add("Other Crops");
                        f2bCrops.getCropEnglishName().add("Please select some crop.");
                        f2bCrops.getCropUrduName().add("کسی فصل کا انتخاب کریں۔");

                        f2bCrops.getCropID().add("-88");
                        f2bCrops.getCropCategory().add("Other Crops");
                        f2bCrops.getCropEnglishName().add("No New Crop has been cultivated.");
                        f2bCrops.getCropUrduName().add("کوئی فصل کاشت نہیں کی گئی۔");
                        f2bCrops = databaseAccess.Read2FBBYParameter(f1_crop, f2bCrops);
                    }
                    if (f2bCrops != null && f2bCrops.getCropID().size() > 0) {

                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<>(Q_sectionF.this,
                                R.layout.each_spinner_item, f2bCrops.getCropUrduName());
                        dataAdapter1.setDropDownViewResource(R.layout.each_spinner_item);
                        edt_qf2b_crop_spinner.setAdapter(dataAdapter1);

                    }

                    RConsUtils.HideKeyBoard(Q_sectionF.this);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edt_qf2b_crop_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (f2bCrops != null) {
                    f2b_crop = f2bCrops.getCropID().get(position);
                    if (!StringUtils.isEmpty(f2b_crop) && f2b_crop.equalsIgnoreCase("28")) {
                        edt_qf2b_other.setVisibility(View.VISIBLE);
                        edt_qf2b_kanal.setVisibility(View.VISIBLE);
                        edt_qf2b_acre.setVisibility(View.VISIBLE);
                        edt_qf2b_other.requestFocus();
                    } else if (!StringUtils.isEmpty(f2b_crop) && f2b_crop.equalsIgnoreCase("-88")) {
                        edt_qf2b_kanal.setVisibility(View.GONE);
                        edt_qf2b_acre.setVisibility(View.GONE);
                        edt_qf2b_other.setVisibility(View.GONE);
                    } else if (!StringUtils.isEmpty(f2b_crop) && f2b_crop.equalsIgnoreCase("99")) {
                        edt_qf2b_kanal.setVisibility(View.GONE);
                        edt_qf2b_acre.setVisibility(View.GONE);
                        edt_qf2b_other.setVisibility(View.GONE);
                    } else {
                        edt_qf2b_other.setVisibility(View.GONE);
                        edt_qf2b_kanal.setVisibility(View.VISIBLE);
                        edt_qf2b_acre.setVisibility(View.VISIBLE);
                        edt_qf2b_other.setText("");
                    }
                    RConsUtils.HideKeyBoard(Q_sectionF.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        qf2c_crop_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (f2cCrops != null) {
                    f2c_crop = f2cCrops.getCropID().get(position);
                    if (!StringUtils.isEmpty(f2c_crop) && f2c_crop.equalsIgnoreCase("28")) {
                        edt_qf2c_other.setVisibility(View.VISIBLE);
                        edt_qf2c_other.requestFocus();
                    } else {
                        edt_qf2c_other.setVisibility(View.GONE);
                        edt_qf2c_other.setText("");
                    }
                    RConsUtils.HideKeyBoard(Q_sectionF.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        qf12_crop_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (f12Crops != null) {
                    f12_crop = f12Crops.getCropID().get(position);
                    txtf12Error.setVisibility(View.GONE);
                    if (!StringUtils.isEmpty(f12_crop) && f12_crop.equalsIgnoreCase("28")) {
                        edt_qf12_other.setVisibility(View.VISIBLE);
                        edt_qf12_other.requestFocus();
                    } else {
                        edt_qf12_other.setVisibility(View.GONE);
                        edt_qf12_other.setText("");
                    }
                    RConsUtils.HideKeyBoard(Q_sectionF.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                f1_other = edt_qf1_other.getText().toString();
                f2a_kanal = edt_qf2a_kanal.getText().toString();
                f2a_acre = edt_qf2a_acre.getText().toString();
                //  f2b_crop = edt_qf2b_crop.getText().toString();
                f2b_other = edt_qf2b_other.getText().toString();
                f2b_kanal = edt_qf2b_kanal.getText().toString();
                f2b_acre = edt_qf2b_acre.getText().toString();
                if (StringUtils.isEmpty(f1_crop) || f1_crop.equalsIgnoreCase("-99")) {
                    Toast.makeText(Q_sectionF.this, "Please select F1 crop", Toast.LENGTH_SHORT).show();
                } else if (edt_qf1_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f1_other)) {
                    Toast.makeText(Q_sectionF.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f2a_kanal)) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2a Kanal", Toast.LENGTH_SHORT).show();

                } else if (StringUtils.isEmpty(f2a_acre)) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2b Acre", Toast.LENGTH_SHORT).show();

                } else if (StringUtils.isEmpty(f2b_crop) || f2b_crop.equalsIgnoreCase("-99")) {
                    Toast.makeText(Q_sectionF.this, "Please select F2b crop", Toast.LENGTH_SHORT).show();

                } else if (edt_qf2b_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f2b_other)) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2b Other crop", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f2b_kanal) && edt_qf2b_kanal.getVisibility() == View.VISIBLE) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2b Kanal", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f2b_acre) && edt_qf2b_acre.getVisibility() == View.VISIBLE) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2b Acre", Toast.LENGTH_SHORT).show();
                } else {
                    sectionF2ArrayList = databaseAccess.readSectionF2(FarmerID);
                    if (sectionF2ArrayList != null && sectionF2ArrayList.size() > 0) {

                        for (SectionF2 f2 : sectionF2ArrayList) {
                            if (f1_crop.equalsIgnoreCase(f2.getF1_crop())) {
                                txtf2aError.setVisibility(View.VISIBLE);
                                txtf2aError.setText("Crop Already Exist");
                                Toast.makeText(Q_sectionF.this, "Crop already Exist", Toast.LENGTH_SHORT).show();
                                RConsUtils.HideKeyBoard(Q_sectionF.this);
                                // parentScrollView.fullScroll(ScrollView.FOCUS_UP);

                                return;
                            }
                        }
                    }


                    LayoutInflater li = LayoutInflater.from(Q_sectionF.this);
                    View dialogView = li.inflate(R.layout.need_add_crop_dialog, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Q_sectionF.this);
                    alertDialogBuilder.setView(dialogView);
                    TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
                    txtDialogTitle.setText("Alert");
                    TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
                    txtErrorMessage.setText("Do you want to add more crop ?");
                    Button btnCancel = dialogView.findViewById(R.id.btnCancel);
                    Button btnOk = dialogView.findViewById(R.id.btnOk);
                    // txtErrorMessage.setVisibility(View.GONE);

                    final AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.setCancelable(false);
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    alertDialog.show();
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();


                        }
                    });
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            qf1_f2a_f2b_layout.setVisibility(View.GONE);
                            qf3_layout.setVisibility(View.VISIBLE);

                        }
                    });
                    edt_qf1_other.setText("");
                    edt_qf2a_kanal.setText("");
                    edt_qf2a_acre.setText("");
                    edt_qf2b_crop.setText("");
                    edt_qf2b_other.setText("");
                    edt_qf2b_kanal.setText("");
                    edt_qf2b_acre.setText("");
                }


            }
        });

        btn2fcAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                f2c_other = edt_qf2c_other.getText().toString();
                f2c_kanal = edt_qf2c_kanal.getText().toString();
                f2c_acre = edt_qf2c_acre.getText().toString();

                if (StringUtils.isEmpty(f2c_crop) || f2c_crop.equalsIgnoreCase("-99")) {
                    Toast.makeText(Q_sectionF.this, "Please select F2C crop", Toast.LENGTH_SHORT).show();
                } else if (edt_qf2c_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f2c_other)) {
                    Toast.makeText(Q_sectionF.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f2c_kanal)) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2c Kanal", Toast.LENGTH_SHORT).show();

                } else if (StringUtils.isEmpty(f2c_acre)) {
                    Toast.makeText(Q_sectionF.this, "Please enter F2c Acre", Toast.LENGTH_SHORT).show();

                } else {
                    qf2c_crop_spinner.setNoItemSelected(0);
                    edt_qf2c_other.setText("");
                    edt_qf2c_kanal.setText("");
                    edt_qf2c_acre.setText("");
                }


            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int rg_qd8a_id = rg_qd8a.getCheckedRadioButtonId();
                int rg_qd8b_id = rg_qd8b.getCheckedRadioButtonId();
                int rg_qd10_id = rg_qd10.getCheckedRadioButtonId();
                int rg_qd12_id = rg_qd12.getCheckedRadioButtonId();
                int rg_qf3_id = rg_qf3.getCheckedRadioButtonId();
                int rg_qf5_id = rg_qf5.getCheckedRadioButtonId();
                int rg_qf7_id = rg_qf7.getCheckedRadioButtonId();
                int rg_qf9_id = rg_qf9.getCheckedRadioButtonId();
                int rg_qf11_id = rg_qf11.getCheckedRadioButtonId();
                int rg_qf13_id = rg_qf13.getCheckedRadioButtonId();
                int rg_qf14_id = rg_qf14.getCheckedRadioButtonId();
                int rg_qf15_id = rg_qf15.getCheckedRadioButtonId();
                int rg_qf16_id = rg_qf16.getCheckedRadioButtonId();
                int rg_qf17_id = rg_qf17.getCheckedRadioButtonId();
                int rg_qf19_id = rg_qf19.getCheckedRadioButtonId();
                int rg_qf20_id = rg_qf20.getCheckedRadioButtonId();

                if (qd8a_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qd8a_id);

                    if (rg_qd8a_id == R.id.rbtn_d8a_1) {
                        qd8a_layout.setVisibility(View.GONE);
                        qd8d_layout.setVisibility(View.VISIBLE);


                        f4CropList = databaseAccess.readF4Crops();
                        if (f4CropList != null && f4CropList.size() > 0) {
                            f4CropSelectionAdapter = new F4CropSelectionAdapter(f4CropList, Q_sectionF.this);
                            rvF4Crops.setAdapter(f4CropSelectionAdapter);

                        }

                    } else if (rg_qd8a_id == R.id.rbtn_d8a_2 || rg_qd8a_id == R.id.rbtn_d8a_3) {
                        qd8a_layout.setVisibility(View.GONE);
                        qd8b_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qd8b_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qd8b_id);

                    if (rg_qd8b_id == R.id.rbtn_d8b_1) {
                        qd8b_layout.setVisibility(View.GONE);

                        qd8c_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qd8b_id == R.id.rbtn_d8b_2 || rg_qd8b_id == R.id.rbtn_d8b_3) {
                        edt_d8b_other.setVisibility(View.VISIBLE);

                        if (StringUtils.isEmpty(d8b_other)) {
                            Toast.makeText(Q_sectionF.this, "Please Enter other", Toast.LENGTH_SHORT).show();
                        } else {
                            qd8b_layout.setVisibility(View.GONE);

                            if (!StringUtils.isEmpty(d8a)) {
                                if (d8a.equalsIgnoreCase("2") || d8a.equalsIgnoreCase("3")) {
                                    qd8c_layout.setVisibility(View.VISIBLE);
                                }
                            } else {
                                qd8d_layout.setVisibility(View.VISIBLE);
                            }
                        }
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qd8c_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q d8c ///////////////Logic
                    d8c_acre = edt_d8c_acre.getText().toString();
                    d8c_kanal = edt_d8c_kanal.getText().toString();

                    if (StringUtils.isEmpty(d8c_acre)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d8c_kanal)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8c_acre) < 0 || Integer.parseInt(d8c_acre) > 600) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre in 0-600", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8c_kanal) > 7) {
                        Toast.makeText(Q_sectionF.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else {
                        qd8c_layout.setVisibility(View.GONE);
                        qd8d_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qd8d_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q d8d /////////////// Logic
                    d8d_kanal = edt_d8d_kanal.getText().toString();
                    d8d_acre = edt_d8d_acre.getText().toString();

                    if (StringUtils.isEmpty(d8d_acre)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d8d_kanal)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8d_acre) < 0 || Integer.parseInt(d8d_acre) > 600) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre in 0-600", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8d_kanal) > 7) {
                        Toast.makeText(Q_sectionF.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8d_kanal) == 0 && Integer.parseInt(d8d_acre) == 0) {
                        qd8d_layout.setVisibility(View.GONE);
                        qd11_layout.setVisibility(View.VISIBLE);
                    } else {

                        qd8d_layout.setVisibility(View.GONE);
                        qd9_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qd9_layout.getVisibility() == View.VISIBLE) {
                    d9_kanal = edt_d9_kanal.getText().toString();
                    d9_acre = edt_d9_acre.getText().toString();
                    if (StringUtils.isEmpty(d9_acre)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(d9_kanal)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d9_acre) > Integer.parseInt(d8d_acre)) {
                        Toast.makeText(Q_sectionF.this, " جواب سوالنمبر D8d کے برابر ایکڑ لکھیں یا کم", Toast.LENGTH_SHORT).show();

                    } else if (Integer.parseInt(d9_kanal) > Integer.parseInt(d8d_kanal)) {
                        Toast.makeText(Q_sectionF.this, " جواب سوالنمبر D8d کے برابر کنال لکھیں یا کم", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d9_kanal) == 0 && Integer.parseInt(d9_acre) == 0) {
                        qd9_layout.setVisibility(View.GONE);
                        qd11_layout.setVisibility(View.VISIBLE);

                    } else {
                        qd9_layout.setVisibility(View.GONE);
                        qd10_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qd10_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q d10 ////////
                    RadioButton radioButton = findViewById(rg_qd10_id);
                    if (rg_qd10_id == R.id.rbtn_d10_1
                            || rg_qd10_id == R.id.rbtn_d10_2
                            || rg_qd10_id == R.id.rbtn_d10_3) {
                        qd10_layout.setVisibility(View.GONE);
                        qd11_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qd11_layout.getVisibility() == View.VISIBLE) {

                    d11_kanal = edt_d11_kanal.getText().toString();
                    d11_acre = edt_d11_acre.getText().toString();
                    if (StringUtils.isEmpty(d11_acre)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    }
                    if (StringUtils.isEmpty(d11_kanal)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d8d_acre) < 0 || Integer.parseInt(d8d_acre) > 600) {
                        Toast.makeText(Q_sectionF.this, "Please Enter Acre in 0-600", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d11_kanal) > 7) {
                        Toast.makeText(Q_sectionF.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(d11_kanal) == 0 && Integer.parseInt(d11_acre) == 0) {
                        qd11_layout.setVisibility(View.GONE);
                        qf1_f2a_f2b_layout.setVisibility(View.VISIBLE);

                    } else {
                        qd11_layout.setVisibility(View.GONE);
                        qd12_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qd12_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qd12_id);
                    if (rg_qd12_id == R.id.rbtn_d12_1
                            || rg_qd12_id == R.id.rbtn_d12_2
                            || rg_qd12_id == R.id.rbtn_d12_3) {
                        qd12_layout.setVisibility(View.GONE);
                        qf1_f2a_f2b_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf1_f2a_f2b_layout.getVisibility() == View.VISIBLE) {

                    f1_other = edt_qf1_other.getText().toString();
                    f2a_kanal = edt_qf2a_kanal.getText().toString();
                    f2a_acre = edt_qf2a_acre.getText().toString();

                    f2b_other = edt_qf2b_other.getText().toString();
                    f2b_kanal = edt_qf2b_kanal.getText().toString();
                    f2b_acre = edt_qf2b_acre.getText().toString();
                    if (StringUtils.isEmpty(f1_crop) || f1_crop.equalsIgnoreCase("-99")) {
                        Toast.makeText(Q_sectionF.this, "Please select F1 crop", Toast.LENGTH_SHORT).show();
                    } else if (edt_qf1_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f1_other)) {
                        Toast.makeText(Q_sectionF.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f2a_acre)) {
                        Toast.makeText(Q_sectionF.this, "Please enter F2a Acre", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(f2a_kanal)) {
                        Toast.makeText(Q_sectionF.this, "Please enter F2b Kanal", Toast.LENGTH_SHORT).show();

                    } else if (Integer.parseInt(f2a_kanal) > 7) {
                        Toast.makeText(Q_sectionF.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f2b_crop) || f2b_crop.equalsIgnoreCase("-99")) {
                        Toast.makeText(Q_sectionF.this, "Please select F2b crop", Toast.LENGTH_SHORT).show();

                    } else if (edt_qf2b_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f2b_other)) {
                        Toast.makeText(Q_sectionF.this, "Please enter F2b Other crop", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f2b_acre) && edt_qf2b_acre.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionF.this, "Please enter F2b Acre", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f2b_kanal) && edt_qf2b_kanal.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionF.this, "Please enter F2b Kanal", Toast.LENGTH_SHORT).show();
                    } else if (edt_qf2b_kanal.getVisibility() == View.VISIBLE && Integer.parseInt(f2b_kanal) > 7) {
                        Toast.makeText(Q_sectionF.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else {

                        sectionF2ArrayList = databaseAccess.readSectionF2(FarmerID);
                        if (sectionF2ArrayList != null && sectionF2ArrayList.size() > 0) {

                            for (SectionF2 f2 : sectionF2ArrayList) {
                                if (f1_crop.equalsIgnoreCase(f2.getF1_crop())) {
                                    txtf2aError.setVisibility(View.VISIBLE);
                                    txtf2aError.setText("Crop Already Exist");
                                    Toast.makeText(Q_sectionF.this, "Crop already Exist", Toast.LENGTH_SHORT).show();
                                    RConsUtils.HideKeyBoard(Q_sectionF.this);

                                    return;
                                }
                            }
                        }

                        txtf2aError.setVisibility(View.GONE);

                        qf1_f2a_f2b_layout.setVisibility(View.GONE);
                                            qf3_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qf3_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qf3_id);
                    if (rg_qf3_id == R.id.rbtn_f3_1) {
                        qf3_layout.setVisibility(View.GONE);
                        f4CropList = databaseAccess.readF4Crops();
                        if (f4CropList != null && f4CropList.size() > 0) {
                            f4CropSelectionAdapter = new F4CropSelectionAdapter(f4CropList, Q_sectionF.this);
                            rvF4Crops.setAdapter(f4CropSelectionAdapter);

                        }

                        qf4_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qf3_id == R.id.rbtn_f3_2) {
                        qf3_layout.setVisibility(View.GONE);

                        qf5_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf4_layout.getVisibility() == View.VISIBLE) {
                    f4_a = "";
                    f4_b = "";
                    f4_c = "";
                    f4_other = edt_qf4_other.getText().toString();

                    int counter = 0;
                    if (f4CropList != null && f4CropList.size() > 0) {
                        for (Crop crop : f4CropList) {
                            if (crop.getIsSelected()) {
                                if (StringUtils.isEmpty(f4_a)) {
                                    f4_a = crop.getCropID();
                                } else if (StringUtils.isEmpty(f4_b)) {
                                    f4_b = crop.getCropID();
                                } else if (StringUtils.isEmpty(f4_c)) {
                                    f4_c = crop.getCropID();
                                }
                                counter++;
                            }
                        }
                    }
                    if (counter < 1) {
                        Toast.makeText(Q_sectionF.this, "Please select 3 crops", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f4_other) && edt_qf4_other.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionF.this, "Please enter f4 other option", Toast.LENGTH_SHORT).show();
                    } else {
                        qf4_layout.setVisibility(View.GONE);
                        qf5_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qf5_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qf5_id);
                    if (rg_qf5_id == R.id.rbtn_f5_1) {
                        qf5_layout.setVisibility(View.GONE);

                        f6CropList = databaseAccess.readF4Crops();
                        if (f6CropList != null && f6CropList.size() > 0) {
                            f6CropSelectionAdapter = new F6CropSelectionAdapter(f6CropList, Q_sectionF.this);
                            rvF6Crops.setAdapter(f6CropSelectionAdapter);

                        }


                        qf6_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qf5_id == R.id.rbtn_f5_2) {
                        qf5_layout.setVisibility(View.GONE);

                        qf7_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf6_layout.getVisibility() == View.VISIBLE) {
                    f6_a = "";
                    f6_b = "";
                    f6_c = "";
                    f6_other = edt_qf6_other.getText().toString();

                    int counter = 0;
                    if (f6CropList != null && f6CropList.size() > 0) {
                        for (Crop crop : f6CropList) {
                            if (crop.getIsSelected()) {
                                if (StringUtils.isEmpty(f6_a)) {
                                    f6_a = crop.getCropID();
                                } else if (StringUtils.isEmpty(f6_b)) {
                                    f6_b = crop.getCropID();
                                } else if (StringUtils.isEmpty(f6_c)) {
                                    f6_c = crop.getCropID();
                                }
                                counter++;
                            }
                        }
                    }
                    if (counter < 1) {
                        Toast.makeText(Q_sectionF.this, "Please select 3 crops", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f6_other) && edt_qf6_other.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionF.this, "Please enter f6 other option", Toast.LENGTH_SHORT).show();
                    } else {
                        qf6_layout.setVisibility(View.GONE);

                        qf7_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qf7_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qf7_id);
                    if (rg_qf7_id == R.id.rbtn_f7_1) {
                        qf7_layout.setVisibility(View.GONE);

                        f8CropList = databaseAccess.readF4Crops();
                        if (f8CropList != null && f8CropList.size() > 0) {
                            f8CropSelectionAdapter = new F8CropSelectionAdapter(f8CropList, Q_sectionF.this);
                            rvF8Crops.setAdapter(f8CropSelectionAdapter);

                        }


                        qf8_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qf7_id == R.id.rbtn_f7_2) {
                        qf7_layout.setVisibility(View.GONE);

                        qf9_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf8_layout.getVisibility() == View.VISIBLE) {

                    f8_a = "";
                    f8_b = "";
                    f8_c = "";
                    f8_other = edt_qf8_other.getText().toString();

                    int counter = 0;
                    if (f8CropList != null && f8CropList.size() > 0) {
                        for (Crop crop : f8CropList) {
                            if (crop.getIsSelected()) {
                                if (StringUtils.isEmpty(f8_a)) {
                                    f8_a = crop.getCropID();
                                } else if (StringUtils.isEmpty(f8_b)) {
                                    f8_b = crop.getCropID();
                                } else if (StringUtils.isEmpty(f8_c)) {
                                    f8_c = crop.getCropID();
                                }
                                counter++;
                            }
                        }
                    }
                    if (counter < 1) {
                        Toast.makeText(Q_sectionF.this, "Please select 3 crops", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f8_other) && edt_qf8_other.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionF.this, "Please enter f8 other option", Toast.LENGTH_SHORT).show();
                    } else {
                        qf8_layout.setVisibility(View.GONE);
                        qf9_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qf9_layout.getVisibility() == View.VISIBLE) {

                    RadioButton radioButton = findViewById(rg_qf9_id);
                    if (rg_qf9_id == R.id.rbtn_f9_1) {
                        qf9_layout.setVisibility(View.GONE);

                        f10CropList = databaseAccess.readF4Crops();
                        if (f10CropList != null && f10CropList.size() > 0) {
                            f10CropSelectionAdapter = new F10CropSelectionAdapter(f10CropList, Q_sectionF.this);
                            rvF10Crops.setAdapter(f10CropSelectionAdapter);

                        }

                        qf10_layout.setVisibility(View.VISIBLE);
                    } else if (rg_qf9_id == R.id.rbtn_f9_2) {
                        qf9_layout.setVisibility(View.GONE);
                        qf11_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf10_layout.getVisibility() == View.VISIBLE) {

                    f10_a = "";
                    f10_b = "";
                    f10_c = "";
                    f10_other = edt_qf10_other.getText().toString();

                    int counter = 0;
                    if (f10CropList != null && f10CropList.size() > 0) {
                        for (Crop crop : f10CropList) {
                            if (crop.getIsSelected()) {
                                if (StringUtils.isEmpty(f10_a)) {
                                    f10_a = crop.getCropID();
                                } else if (StringUtils.isEmpty(f10_b)) {
                                    f10_b = crop.getCropID();
                                } else if (StringUtils.isEmpty(f10_c)) {
                                    f10_c = crop.getCropID();
                                }
                                counter++;
                            }
                        }
                    }
                    if (counter < 1) {
                        Toast.makeText(Q_sectionF.this, "Please select 3 crops", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(f10_other) && edt_qf10_other.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_sectionF.this, "Please enter f10 other option", Toast.LENGTH_SHORT).show();
                    } else {
                        qf10_layout.setVisibility(View.GONE);
                        qf11_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qf11_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qf11_id);
                    if (rg_qf11_id == R.id.rbtn_f11_1) {
                        qf11_layout.setVisibility(View.GONE);
                        qf12_f13_f14_f15_layout.setVisibility(View.VISIBLE);

                    } else if (rg_qf11_id == R.id.rbtn_f11_2) {
                        qf11_layout.setVisibility(View.GONE);
                        qf16_layout.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf12_f13_f14_f15_layout.getVisibility() == View.VISIBLE) {
                    f12_other = edt_qf12_other.getText().toString();

                    f13_other = edt_qf13_other.getText().toString();
                    f14_other = edt_qf14_other.getText().toString();
                    f15_other = edt_qf15_other.getText().toString();

                    RadioButton radioButton13 = findViewById(rg_qf13_id);
                    RadioButton radioButton14 = findViewById(rg_qf14_id);
                    RadioButton radioButton15 = findViewById(rg_qf15_id);

                    if (StringUtils.isEmpty(f12_crop) || f12_crop == "-99") {
                        Toast.makeText(Q_sectionF.this, "Please select F12 crop", Toast.LENGTH_SHORT).show();
                    } else if (edt_qf12_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f12_other)) {
                        Toast.makeText(Q_sectionF.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
                    } else if (rg_qf13_id == -1 && StringUtils.isEmpty(f13_other)) {
                        Toast.makeText(Q_sectionF.this, "Please add Q 13 Answer", Toast.LENGTH_SHORT).show();
                    } else if (rg_qf14_id == -1 && StringUtils.isEmpty(f14_other)) {
                        Toast.makeText(Q_sectionF.this, "Please add Q 14 Answer", Toast.LENGTH_SHORT).show();
                    } else if (rg_qf15_id == -1 && StringUtils.isEmpty(f15_other)) {
                        Toast.makeText(Q_sectionF.this, "Please add Q 15 Answer", Toast.LENGTH_SHORT).show();
                    } else {
                        if (radioButton13 != null) {
                            f13 = radioButton13.getTag().toString();
                        }
                        if (radioButton14 != null) {
                            f14 = radioButton14.getTag().toString();
                        }
                        if (radioButton15 != null) {
                            f15 = radioButton15.getTag().toString();
                        }

                        sectionF12ArrayList = databaseAccess.readSectionF12(FarmerID);
                        if (sectionF12ArrayList != null && sectionF12ArrayList.size() > 0) {

                            for (SectionF12 f2 : sectionF12ArrayList) {
                                if (f12_crop.equalsIgnoreCase(f2.getF12_crop())) {
                                    txtf12Error.setVisibility(View.VISIBLE);
                                    txtf12Error.setText("Crop Already Exist");
                                    Toast.makeText(Q_sectionF.this, "Crop already Exist", Toast.LENGTH_SHORT).show();
                                    RConsUtils.HideKeyBoard(Q_sectionF.this);
                                    return;
                                }
                            }
                        }

                        qf12_f13_f14_f15_layout.setVisibility(View.GONE);
                        qf16_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qf16_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 16 ///////////////

                    RadioButton radioButton = findViewById(rg_qf16_id);

                    if (rg_qf16_id == R.id.rbtn_f16_1
                            || rg_qf16_id == R.id.rbtn_f16_2
                            || rg_qf16_id == R.id.rbtn_f16_3) {
                        qf16_layout.setVisibility(View.GONE);

                        qf17_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf17_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 17 ///////////////

                    RadioButton radioButton = findViewById(rg_qf17_id);

                    if (rg_qf17_id == R.id.rbtn_f17_1 || rg_qf17_id == R.id.rbtn_f17_3) {
                        qf17_layout.setVisibility(View.GONE);
                        edt_qf18.setVisibility(View.VISIBLE);
                        qf18_layout.setVisibility(View.VISIBLE);

                    } else if (rg_qf17_id == R.id.rbtn_f17_2) {
                        qf17_layout.setVisibility(View.GONE);
                        qf20_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf18_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 18 /////////////// Logic
                    f18 = edt_qf18.getText().toString();

                    if (StringUtils.isEmpty(f18)) {
                        Toast.makeText(Q_sectionF.this, "Please Enter feet", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(f18) < 10 || Integer.parseInt(f18) > 1000) {
                        Toast.makeText(Q_sectionF.this, "Please Enter in 10-1000", Toast.LENGTH_SHORT).show();
                    } else {
                        qf18_layout.setVisibility(View.GONE);
                        qf19_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qf19_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 19 ///////////////
                    RadioButton radioButton = findViewById(rg_qf19_id);

                    if (rg_qf19_id == R.id.rbtn_f19_1
                            || rg_qf19_id == R.id.rbtn_f19_2
                            || rg_qf19_id == R.id.rbtn_f19_3) {
                        qf19_layout.setVisibility(View.GONE);
                        qf20_layout.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                } else if (qf20_layout.getVisibility() == View.VISIBLE) {

                    ///////// Q f 20 ///////////////
                    RadioButton radioButton = findViewById(rg_qf20_id);

                    if (rg_qf20_id == R.id.rbtn_f20_1
                            || rg_qf20_id == R.id.rbtn_f20_2
                            || rg_qf20_id == R.id.rbtn_f20_3) {
                        Intent intent = new Intent(Q_sectionF.this, Q_sectionG.class);
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
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        startActivityForResult(intent, 88);

                    } else {
                        Toast.makeText(Q_sectionF.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }

                }


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

        rg_qf13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rbtn_f13_9) {
                    edt_qf13_other.setVisibility(View.VISIBLE);
                } else {
                    edt_qf13_other.setVisibility(View.GONE);

                }
            }
        });
        rg_qf14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rbtn_f14_6) {
                    edt_qf14_other.setVisibility(View.VISIBLE);
                } else {
                    edt_qf14_other.setVisibility(View.GONE);

                }
            }
        });
        rg_qf15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.rbtn_f15_11) {
                    edt_qf15_other.setVisibility(View.VISIBLE);
                } else {
                    edt_qf15_other.setVisibility(View.GONE);

                }
            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_sectionF.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("section", "sectionF");
                startActivityForResult(intent, 88);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {

        int rg_qd8a_id = rg_qd8a.getCheckedRadioButtonId();
        int rg_qf3_id = rg_qf3.getCheckedRadioButtonId();
        int rg_qf5_id = rg_qf5.getCheckedRadioButtonId();
        int rg_qf7_id = rg_qf7.getCheckedRadioButtonId();
        int rg_qf9_id = rg_qf9.getCheckedRadioButtonId();
        int rg_qf11_id = rg_qf11.getCheckedRadioButtonId();
        int rg_qf17_id = rg_qf17.getCheckedRadioButtonId();
        if (qf20_layout.getVisibility() == View.VISIBLE) {

            if (rg_qf17_id == R.id.rbtn_f17_2) {

                f17 = "";
                qf17_layout.setVisibility(View.VISIBLE);
                qf20_layout.setVisibility(View.GONE);
            } else {
                f19 = "";
                qf19_layout.setVisibility(View.VISIBLE);
                qf20_layout.setVisibility(View.GONE);
            }

        } else if (qf19_layout.getVisibility() == View.VISIBLE) {
            ///////// F 19 /////////
            f18 = "";
            qf18_layout.setVisibility(View.VISIBLE);
            qf19_layout.setVisibility(View.GONE);
        } else if (qf18_layout.getVisibility() == View.VISIBLE) {
            ///////// F 18 /////////
            f17 = "";
            qf17_layout.setVisibility(View.VISIBLE);
            qf18_layout.setVisibility(View.GONE);

        } else if (qf17_layout.getVisibility() == View.VISIBLE) {
            ///////// F 17 /////////
            f16 = "";
            qf16_layout.setVisibility(View.VISIBLE);
            qf17_layout.setVisibility(View.GONE);

        } else if (qf16_layout.getVisibility() == View.VISIBLE) {

            ///////// F 16/////////
            if (rg_qf11_id == R.id.rbtn_f11_2) {

//                f12_crop = "";
//                f12_other = "";
//                f13 = "";
//                f13_other = "";
//                f14 = "";
//                f14_other = "";
//                f15 = "";
//                f15_other = "";

                qf11_layout.setVisibility(View.VISIBLE);
                qf16_layout.setVisibility(View.GONE);
            } else {

                qf12_f13_f14_f15_layout.setVisibility(View.VISIBLE);
                qf16_layout.setVisibility(View.GONE);
            }
        } else if (qf12_f13_f14_f15_layout.getVisibility() == View.VISIBLE) {
            ///////// F 12, 13, 14, 15 /////////**************************

            qf11_layout.setVisibility(View.VISIBLE);
            qf12_f13_f14_f15_layout.setVisibility(View.GONE);

        } else if (qf11_layout.getVisibility() == View.VISIBLE) {

            ///////// F 11 /////////**************************
            if (rg_qf9_id == R.id.rbtn_f9_2) {
                f9 = "";
                qf9_layout.setVisibility(View.VISIBLE);
                qf11_layout.setVisibility(View.GONE);

            } else {
                f10_a = "";
                f10_b = "";
                f10_c = "";
                f10_other = "";
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

                qf7_layout.setVisibility(View.VISIBLE);
                qf9_layout.setVisibility(View.GONE);

            } else {
                f8_a = "";
                f8_b = "";
                f8_c = "";
                f8_other = "";
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

            } else {

                f6_a = "";
                f6_b = "";
                f6_c = "";
                f6_other = "";

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

            } else {
                f4_a = "";
                f4_b = "";
                f4_c = "";
                f4_other = "";
                qf4_layout.setVisibility(View.VISIBLE);
                qf5_layout.setVisibility(View.GONE);
            }
        } else if (qf4_layout.getVisibility() == View.VISIBLE) {

            ///////// F 4 /////////

            qf3_layout.setVisibility(View.VISIBLE);
            qf4_layout.setVisibility(View.GONE);
        } else if (qf3_layout.getVisibility() == View.VISIBLE) {

            ///////// F 3 /////////
            qf1_f2a_f2b_layout.setVisibility(View.VISIBLE);
            qf3_layout.setVisibility(View.GONE);

//        } else if (qf2c_layout.getVisibility() == View.VISIBLE) {
//            ///////// F 2 c /////////
//            qf1_f2a_f2b_layout.setVisibility(View.VISIBLE);
//            qf2c_layout.setVisibility(View.GONE);
//
        } else if (qf1_f2a_f2b_layout.getVisibility() == View.VISIBLE) {

            ///////// F 1 2a 2b  /////////
            //d11_kanal = edt_d11_kanal.getText().toString();
            //d11_acre = edt_d11_acre.getText().toString();
            if (Integer.parseInt(d11_kanal) == 0 && Integer.parseInt(d11_acre) == 0) {
                qd11_layout.setVisibility(View.VISIBLE);
                qf1_f2a_f2b_layout.setVisibility(View.GONE);

            } else {
                d12 = "";
                qd12_layout.setVisibility(View.VISIBLE);
                qf1_f2a_f2b_layout.setVisibility(View.GONE);
            }

        } else if (qd12_layout.getVisibility() == View.VISIBLE) {

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

    @OnClick(R.id.btnAddMoreF12)
    public void onAddMoreF12Click(View view) {
        int rg_qf13_id = rg_qf13.getCheckedRadioButtonId();
        int rg_qf14_id = rg_qf14.getCheckedRadioButtonId();
        int rg_qf15_id = rg_qf15.getCheckedRadioButtonId();
        f12_other = edt_qf12_other.getText().toString();


        f13_other = edt_qf13_other.getText().toString();
        f14_other = edt_qf14_other.getText().toString();
        f15_other = edt_qf15_other.getText().toString();


        RadioButton radioButton13 = findViewById(rg_qf13_id);
        RadioButton radioButton14 = findViewById(rg_qf14_id);
        RadioButton radioButton15 = findViewById(rg_qf15_id);

        if (StringUtils.isEmpty(f12_crop)) {
            Toast.makeText(Q_sectionF.this, "Please select F12 crop", Toast.LENGTH_SHORT).show();
        } else if (edt_qf12_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f12_other)) {
            Toast.makeText(Q_sectionF.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
        } else if (rg_qf13_id == -1 && StringUtils.isEmpty(f13_other)) {
            Toast.makeText(Q_sectionF.this, "Please add Q 13 Answer", Toast.LENGTH_SHORT).show();
        } else if (rg_qf14_id == -1 && StringUtils.isEmpty(f14_other)) {
            Toast.makeText(Q_sectionF.this, "Please add Q 14 Answer", Toast.LENGTH_SHORT).show();
        } else if (rg_qf15_id == -1 && StringUtils.isEmpty(f15_other)) {
            Toast.makeText(Q_sectionF.this, "Please add Q 15 Answer", Toast.LENGTH_SHORT).show();
        } else {
            if (radioButton13 != null) {
                f13 = radioButton13.getTag().toString();
            }
            if (radioButton14 != null) {
                f14 = radioButton14.getTag().toString();
            }
            if (radioButton15 != null) {
                f15 = radioButton15.getTag().toString();
            }

            sectionF12ArrayList = databaseAccess.readSectionF12(FarmerID);
            if (sectionF12ArrayList != null && sectionF12ArrayList.size() > 0) {

                for (SectionF12 f2 : sectionF12ArrayList) {
                    if (f12_crop.equalsIgnoreCase(f2.getF12_crop())) {
                        txtf12Error.setVisibility(View.VISIBLE);
                        txtf12Error.setText("Crop Already Exist");
                        Toast.makeText(Q_sectionF.this, "Crop already Exist", Toast.LENGTH_SHORT).show();
                        RConsUtils.HideKeyBoard(Q_sectionF.this);
                        // parentScrollView.fullScroll(ScrollView.FOCUS_UP);

                        return;
                    }
                }
            }

            txtf12Error.setVisibility(View.GONE);
            edt_qf13_other.setText("");
            edt_qf14_other.setText("");
            edt_qf15_other.setText("");
            edt_qf12_other.setText("");
            edt_qf12_other.setVisibility(View.GONE);
            qf12_crop_spinner.setNoItemSelected(0);
            rg_qf13.clearCheck();
            rg_qf14.clearCheck();
            rg_qf15.clearCheck();
        }


    }

    CropModel readCropFromDatabase() {
        Cursor cursor = databaseAccess.getCropIDInformation();
        if (cursor != null && cursor.getCount() > 0) {
            CropModel cropArrayList = new CropModel();
            if (cursor.moveToFirst()) {
                do {

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));

                    cropArrayList.getCropID().add(crop_id);
                    cropArrayList.getCropUrduName().add(crop_name_urdu);
                    cropArrayList.getCropEnglishName().add(crop_name_english);
                    cropArrayList.getCropCategory().add(crop_catagory);
                    cropArrayList.getIsSelected().add(false);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return cropArrayList;

        } else {
            return null;
        }


    }

    CropModel ReadCrops(CropModel cropArrayList) {
        Cursor cursor = databaseAccess.getCropIDInformation();
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));

                    cropArrayList.getCropID().add(crop_id);
                    cropArrayList.getCropUrduName().add(crop_name_urdu);
                    cropArrayList.getCropEnglishName().add(crop_name_english);
                    cropArrayList.getCropCategory().add(crop_catagory);
                    cropArrayList.getIsSelected().add(false);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return cropArrayList;

        } else {
            return null;
        }


    }


    @Override
    public void onSectionF4CheckBoxClick(int position) {
        int Counter = 0;
        if (f4CropList != null && f4CropList.size() > 0) {
            for (Crop crop : f4CropList) {
                if (crop.getIsSelected()) {
                    Counter++;
                }
            }

            Crop crop = f4CropList.get(position);


            if (crop.getIsSelected()) {
                crop.setIsSelected(false);
                f4CropSelectionAdapter.notifyDataSetChanged();

                if (crop.getCropID().equalsIgnoreCase("28")) {
                    edt_qf4_other.setVisibility(View.GONE);
                }

            } else {
                if (Counter < 3) {
                    crop.setIsSelected(true);
                    f4CropSelectionAdapter.notifyDataSetChanged();

                    if (crop.getCropID().equalsIgnoreCase("28")) {
                        edt_qf4_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf4_other.setVisibility(View.GONE);
                    }

                } else {
                    crop.setIsSelected(false);
                    f4CropSelectionAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "You cannot select more then 3 crops.", Toast.LENGTH_SHORT).show();

                }


            }


        }

    }


    @Override
    public void onSectionF6CheckBoxClick(int position) {
        int Counter = 0;
        if (f6CropList != null && f6CropList.size() > 0) {
            for (Crop crop : f6CropList) {
                if (crop.getIsSelected()) {
                    Counter++;
                }
            }

            Crop crop = f6CropList.get(position);


            if (crop.getIsSelected()) {
                crop.setIsSelected(false);
                f6CropSelectionAdapter.notifyDataSetChanged();

                if (crop.getCropID().equalsIgnoreCase("28")) {
                    edt_qf6_other.setVisibility(View.GONE);
                }

            } else {
                if (Counter < 3) {
                    crop.setIsSelected(true);
                    f6CropSelectionAdapter.notifyDataSetChanged();

                    if (crop.getCropID().equalsIgnoreCase("28")) {
                        edt_qf6_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf6_other.setVisibility(View.GONE);
                    }

                } else {
                    crop.setIsSelected(false);
                    f6CropSelectionAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "You cannot select more then 3 crops.", Toast.LENGTH_SHORT).show();

                }


            }


        }
    }

    @Override
    public void onSectionF10CheckBoxClick(int position) {
        int Counter = 0;
        if (f10CropList != null && f10CropList.size() > 0) {
            for (Crop crop : f10CropList) {
                if (crop.getIsSelected()) {
                    Counter++;
                }
            }

            Crop crop = f10CropList.get(position);


            if (crop.getIsSelected()) {
                crop.setIsSelected(false);
                f10CropSelectionAdapter.notifyDataSetChanged();

                if (crop.getCropID().equalsIgnoreCase("28")) {
                    edt_qf10_other.setVisibility(View.GONE);
                }

            } else {
                if (Counter < 3) {
                    crop.setIsSelected(true);
                    f10CropSelectionAdapter.notifyDataSetChanged();

                    if (crop.getCropID().equalsIgnoreCase("28")) {
                        edt_qf10_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf10_other.setVisibility(View.GONE);
                    }

                } else {
                    crop.setIsSelected(false);
                    f10CropSelectionAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "You cannot select more then 3 crops.", Toast.LENGTH_SHORT).show();

                }


            }


        }
    }

    @Override
    public void onSectionF8CheckBoxClick(int position) {
        int Counter = 0;
        if (f8CropList != null && f8CropList.size() > 0) {
            for (Crop crop : f8CropList) {
                if (crop.getIsSelected()) {
                    Counter++;
                }
            }

            Crop crop = f8CropList.get(position);


            if (crop.getIsSelected()) {
                crop.setIsSelected(false);
                f8CropSelectionAdapter.notifyDataSetChanged();

                if (crop.getCropID().equalsIgnoreCase("28")) {
                    edt_qf8_other.setVisibility(View.GONE);
                }

            } else {
                if (Counter < 3) {
                    crop.setIsSelected(true);
                    f8CropSelectionAdapter.notifyDataSetChanged();

                    if (crop.getCropID().equalsIgnoreCase("28")) {
                        edt_qf8_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf8_other.setVisibility(View.GONE);
                    }

                } else {
                    crop.setIsSelected(false);
                    f8CropSelectionAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "You cannot select more then 3 crops.", Toast.LENGTH_SHORT).show();

                }

            }

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
        if (ActivityCompat.checkSelfPermission(Q_sectionF.this,
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
                    Toast.makeText(Q_sectionF.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
