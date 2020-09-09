package com.rcons.fcallbacks.Questionnaire;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.Model.SectionF2;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Section_F.Section_f2_adapter;
import com.rcons.fcallbacks.Section_F.Section_f2_items;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Section_F_table2 extends AppCompatActivity implements com.rcons.fcallbacks.Section_F.onf2Click {

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
    TextView tv_f1Crop_id;
    TextView tv_f2bCrop_id;
    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;
    Spinner spinner_qf1_crop;
    Spinner spinner_qf2b_crop;

    @BindView(R.id.btnAddMore)
    Button btnAddMore;

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;

    String f1_crop = "";
    String f1_other = "";
    String f2a_kanal = "";
    String f2a_acre = "";
    String f2b_crop = "";
    String f2b_other = "";
    String f2b_kanal = "";
    String f2b_acre = "";
    String enum_name = "";
    String enum_code = "";


    @BindView(R.id.edt_qf1_other)
    EditText edt_qf1_other;
    @BindView(R.id.txtf2aError)
    TextView txtf2aError;


    @BindView(R.id.totalarea_textview)
    TextView totalarea_textview;
    @BindView(R.id.remainingarea_textview)
    TextView remainingarea_textview;

    @BindView(R.id.edt_qf2a_kanal)
    EditText edt_qf2a_kanal;
    @BindView(R.id.edt_qf2a_acre)
    EditText edt_qf2a_acre;

    @BindView(R.id.edt_qf2b_other)
    EditText edt_qf2b_other;
    @BindView(R.id.edt_qf2b_kanal)
    EditText edt_qf2b_kanal;
    @BindView(R.id.edt_qf2b_acre)
    EditText edt_qf2b_acre;

    LinearLayoutManager manager;
    ArrayList<Section_f2_items> section_f2_itemsArrayList;
    Section_f2_adapter section_f2_adapter;

    RecyclerView rvf2a_f2b;

    ImageView btniBack;
    Integer total_kanals_f1 = 0;
    Integer total_kanals_f2 = 0;
    Integer total_area = 0;
    Integer f1_total_area = 0;

    String d8a = "";

    String checkarea = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section__f_table_2);
        ButterKnife.bind(this);

        btniBack = findViewById(R.id.btnBack);

        spinner_qf1_crop = findViewById(R.id.spinner_qf1_crop);
        spinner_qf2b_crop = findViewById(R.id.spinner_qf2b_crop);
        databaseAccess = new DatabaseAdapter(Section_F_table2.this);
        databaseAccess.Open();

        tv_f1Crop_id = findViewById(R.id.tv_f1Crop_id);
        tv_f2bCrop_id = findViewById(R.id.tv_f2bCrop_id);
        rvf2a_f2b = findViewById(R.id.rvf2a_f2b);


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
        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }

        SpinnerData();
        SetRecylerData();
        CheckSectionf1();
        areadisplay();
        GetEnum();
        spinner_qf1_crop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                try {
                    if (position != 0) {
                        if (position == 29) {
                            position = 99;
                        }
                        Cursor cursor = databaseAccess.getSectionF_table_2Data(FarmerID, Integer.toString(position));
                        if (cursor != null && cursor.getCount() > 0) {
                            txtf2aError.setVisibility(View.VISIBLE);
                            tv_f1Crop_id.setText("");
                        } else {
                            txtf2aError.setVisibility(View.GONE);
                            if (position != 28) {
                                edt_qf1_other.setVisibility(View.GONE);
                            } else {
                                edt_qf1_other.setVisibility(View.VISIBLE);
                            }
                            if (position == 29) {
                                tv_f1Crop_id.setText("99");
                                f1_crop = tv_f1Crop_id.getText().toString();
                            } else {
                                tv_f1Crop_id.setText(Integer.toString(position));
                                Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        tv_f1Crop_id.setText("");
                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        spinner_qf2b_crop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                String f1crop_id = tv_f1Crop_id.getText().toString();
                String f2bCrop_id = Integer.toString(position);
                if (!f1crop_id.contains(f2bCrop_id)) {
                    if (position != 28) {
                        edt_qf2b_other.setVisibility(View.GONE);
                        edt_qf2b_acre.setVisibility(View.VISIBLE);
                        edt_qf2b_kanal.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf2b_acre.setVisibility(View.VISIBLE);
                        edt_qf2b_kanal.setVisibility(View.VISIBLE);
                        edt_qf2b_other.setVisibility(View.VISIBLE);
                    }
                    if (position == 29) {
                        tv_f2bCrop_id.setText("-88");
                        edt_qf2b_acre.setVisibility(View.GONE);
                        edt_qf2b_kanal.setVisibility(View.GONE);
                        edt_qf2b_acre.setText("");
                        edt_qf2b_kanal.setText("");
                        Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
                    } else {
                        if (position != 0) {
                            edt_qf2b_acre.setVisibility(View.VISIBLE);
                            edt_qf2b_kanal.setVisibility(View.VISIBLE);
                            tv_f2bCrop_id.setText(Integer.toString(position));
                            Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
                        } else {
                            tv_f2bCrop_id.setText("");
                        }
                    }
                } else {
                    Toast.makeText(parent.getContext(), "F1 and f2b crops could not be same :", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Section_F_table2.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "section_f_table2");
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
        btnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1_crop = tv_f1Crop_id.getText().toString();
                f2b_crop = tv_f2bCrop_id.getText().toString();
                f1_other = edt_qf1_other.getText().toString();
                f2a_kanal = edt_qf2a_kanal.getText().toString();
                f2a_acre = edt_qf2a_acre.getText().toString();
                //  f2b_crop = edt_qf2b_crop.getText().toString();
                f2b_other = edt_qf2b_other.getText().toString();
                f2b_kanal = edt_qf2b_kanal.getText().toString();
                f2b_acre = edt_qf2b_acre.getText().toString();
                if (txtf2aError.getVisibility() == View.VISIBLE) {
                    Toast.makeText(Section_F_table2.this, "Crops Cannot Be Duplicate", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f1_crop) || f1_crop.equalsIgnoreCase("-99")) {
                    Toast.makeText(Section_F_table2.this, "Please select F1 crop", Toast.LENGTH_SHORT).show();
                } else if (edt_qf1_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f1_other)) {
                    Toast.makeText(Section_F_table2.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f2a_kanal)) {
                    Toast.makeText(Section_F_table2.this, "Please enter F2a Kanal", Toast.LENGTH_SHORT).show();

                } else if (Integer.parseInt(f2a_kanal) > 7) {
                    Toast.makeText(Section_F_table2.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();

                } else if (StringUtils.isEmpty(f2a_acre)) {
                    Toast.makeText(Section_F_table2.this, "Please enter F2b Acre", Toast.LENGTH_SHORT).show();

                } else if (f2a_acre.equalsIgnoreCase("0") && (f2a_kanal.equalsIgnoreCase("0"))) {
                    Toast.makeText(Section_F_table2.this, "f2a Kanal & f2a Acre Cannot be equal = 0", Toast.LENGTH_SHORT).show();

                } else if (f2a_acre.equalsIgnoreCase("00") && (f2a_kanal.equalsIgnoreCase("00"))) {
                    Toast.makeText(Section_F_table2.this, "f2a Kanal & f2a Acre Cannot be equal =  0", Toast.LENGTH_SHORT).show();

                } else if (f2a_acre.equalsIgnoreCase("0") && (f2a_kanal.equalsIgnoreCase("00"))) {
                    Toast.makeText(Section_F_table2.this, "f2a Kanal & f2a Acre Cannot be equal =  0", Toast.LENGTH_SHORT).show();

                } else if (f2a_acre.equalsIgnoreCase("00") && (f2a_kanal.equalsIgnoreCase("0"))) {
                    Toast.makeText(Section_F_table2.this, "f2a Kanal & f2a Acre Cannot be equal =  0", Toast.LENGTH_SHORT).show();

                } else if (StringUtils.isEmpty(f2b_crop) || f2b_crop.equalsIgnoreCase("-99")) {
                    Toast.makeText(Section_F_table2.this, "Please select F2b crop", Toast.LENGTH_SHORT).show();

                } else if (edt_qf2b_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f2b_other)) {
                    Toast.makeText(Section_F_table2.this, "Please enter F2b Other crop", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(f2b_kanal) && edt_qf2b_kanal.getVisibility() == View.VISIBLE) {
                    Toast.makeText(Section_F_table2.this, "Please enter F2b Kanal", Toast.LENGTH_SHORT).show();
                } else if (!StringUtils.isEmpty(f2b_kanal) && Integer.parseInt(f2b_kanal) > 7 && edt_qf2b_kanal.getVisibility() == View.VISIBLE) {
                    Toast.makeText(Section_F_table2.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();

                } else if (StringUtils.isEmpty(f2b_acre) && edt_qf2b_acre.getVisibility() == View.VISIBLE) {
                    Toast.makeText(Section_F_table2.this, "Please enter F2b Acre", Toast.LENGTH_SHORT).show();
                } else {

                    SaveData();
                    SpinnerData();
                    SetRecylerData();
                    edt_qf1_other.setText("");
                    edt_qf2a_kanal.setText("");
                    edt_qf2a_acre.setText("");
                    edt_qf2b_other.setText("");
                    edt_qf2b_kanal.setText("");
                    edt_qf2b_acre.setText("");
                    tv_f1Crop_id.setText("");
                    tv_f2bCrop_id.setText("");
                    areadisplay();

                }
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RConsUtils.hideKeyboard(Section_F_table2.this);
                if (rvf2a_f2b.getVisibility() == View.VISIBLE) {
                    GetTotalArea();
                    if ((total_area > f1_total_area) && !checkarea.equalsIgnoreCase("-99")) {
                        if (d8a.equalsIgnoreCase("1")) {
                            Toast.makeText(Section_F_table2.this, "d.8d and d.11 area could not be less than from f2a area", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Section_F_table2.this, "q8.c,d.8d and d.11 area could not be less than from f2a", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Intent intent = new Intent(Section_F_table2.this, Section_F_table4.class);
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
                        intent.putExtra("isFromEdit", isFromEdit);
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", farmer_cellphone);
                        startActivityForResult(intent, 88);
                    }

                } else {
                    Toast.makeText(Section_F_table2.this, "Where are you going,Please Fill Section First", Toast.LENGTH_LONG).show();
                }
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
                        Toast.makeText(Section_F_table2.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
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
        if (ActivityCompat.checkSelfPermission(Section_F_table2.this,
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
                    Toast.makeText(Section_F_table2.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("isDataUpdated", false);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        super.onBackPressed();
    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionF_table_2Data(FarmerID, f1_crop);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    void SaveData() {
        try {

            createNewColumnsBeforeInsertion(DatabaseAdapter.SectionFTable2);


            String comments = "";
            databaseAccess.saveSectionF_table2_Data(enum_name, enum_code, emp_id, order_id, FarmerID, f1_crop, f1_other, f2a_kanal, f2a_acre, f2b_crop, f2b_other, f2b_kanal, f2b_acre, comments);


            MubLog.cpnsoleLog("inside SaveData FarmerID " + FarmerID + " SectionBTable  =  " + DatabaseAdapter.SectionFTable2);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID, DatabaseAdapter.SectionFTable2);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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


    @Override
    public void onf2Click(Section_f2_items section_f2_items) {
        if (section_f2_items != null) {
            String TxtF1Crop = section_f2_items.getTxtF1Crop();
            //  Toast.makeText(getApplicationContext(), "Selected : " + TxtF1Crop, Toast.LENGTH_LONG).show();

            ////////////
            LayoutInflater li = LayoutInflater.from(Section_F_table2.this);
            View dialogView = li.inflate(R.layout.delete_dialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Section_F_table2.this);
            alertDialogBuilder.setView(dialogView);
            TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
            txtDialogTitle.setText("Alert");
            TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
            txtErrorMessage.setText("What Do you want with this crop_id = " + section_f2_items.getTxtF1Crop() + " ?");
            Button btnCancel = dialogView.findViewById(R.id.btnCancel);
            Button btnenum = dialogView.findViewById(R.id.btnenum);
            Button btnEdit = dialogView.findViewById(R.id.btnEdit);
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.show();


            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    tv_f1Crop_id.setText(section_f2_items.getTxtF1Crop());
                    edt_qf1_other.setText(section_f2_items.getTxtF1Crop_others());
                    edt_qf2a_kanal.setText(section_f2_items.getTxtF2aCanal());
                    edt_qf2a_acre.setText(section_f2_items.getTxtF2aAcre());
                    tv_f2bCrop_id.setText(section_f2_items.getTxtF2bCrop());
                    edt_qf2b_other.setText(section_f2_items.getTxtF2bCrop_others());
                    edt_qf2b_kanal.setText(section_f2_items.getTxtF2bCanal());
                    edt_qf2b_acre.setText(section_f2_items.getTxtF2bAcre());

                    f1_other = edt_qf1_other.getText().toString();
                    f2b_crop = tv_f2bCrop_id.getText().toString();
                    f2b_other = edt_qf2b_other.getText().toString();
                    f2b_kanal = edt_qf2b_kanal.getText().toString();
                    f2b_acre = edt_qf2b_acre.getText().toString();


                    if (f2b_crop.equalsIgnoreCase("-88")) {
                        edt_qf2b_acre.setVisibility(View.GONE);
                        edt_qf2b_kanal.setVisibility(View.GONE);
                        edt_qf2b_other.setVisibility(View.GONE);
                    } else {
                        edt_qf2b_acre.setVisibility(View.VISIBLE);
                        edt_qf2b_kanal.setVisibility(View.VISIBLE);
                        if (!f2b_other.isEmpty()) {
                            edt_qf2b_other.setVisibility(View.VISIBLE);
                        } else {
                            edt_qf2b_other.setVisibility(View.GONE);
                        }
                    }

                    if (!f1_other.isEmpty()) {
                        edt_qf1_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf1_other.setVisibility(View.GONE);
                    }


                }
            });
            btnenum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    try {
                        String crop_id = section_f2_items.getTxtF1Crop();

                        databaseAccess.DeleteSection_F2Data(FarmerID, crop_id);
                        SetRecylerData();
                        areadisplay();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }


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

    void SetRecylerData() {
        manager = new LinearLayoutManager(Section_F_table2.this, RecyclerView.VERTICAL, false);
        rvf2a_f2b.setLayoutManager(manager);
        section_f2_itemsArrayList = new ArrayList<>();

        Cursor cursor = databaseAccess.getSectionF_table_2Recycler(FarmerID);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Section_f2_items section_f2_items = new Section_f2_items();

                    String f1_crop = cursor.getString(cursor.getColumnIndex("f1_crop"));
                    String f1_other = cursor.getString(cursor.getColumnIndex("f1_other"));
                    String f2a_kanal = cursor.getString(cursor.getColumnIndex("f2a_kanal"));
                    String f2a_acre = cursor.getString(cursor.getColumnIndex("f2a_acre"));
                    String f2b_crop = cursor.getString(cursor.getColumnIndex("f2b_crop"));
                    String f2b_other = cursor.getString(cursor.getColumnIndex("f2b_other"));
                    String f2b_kanal = cursor.getString(cursor.getColumnIndex("f2b_kanal"));
                    String f2b_acre = cursor.getString(cursor.getColumnIndex("f2b_acre"));

                    section_f2_items.setTxtF1Crop(f1_crop);
                    section_f2_items.setTxtF1Crop_others(f1_other);
                    section_f2_items.setTxtF2aAcre(f2a_acre);
                    section_f2_items.setTxtF2aCanal(f2a_kanal);
                    section_f2_items.setTxtF2bCrop(f2b_crop);
                    section_f2_items.setTxtF2bCrop_others(f2b_other);
                    section_f2_items.setTxtF2bAcre(f2b_acre);
                    section_f2_items.setTxtF2bCanal(f2b_kanal);
                    section_f2_itemsArrayList.add(section_f2_items);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if (section_f2_itemsArrayList != null && section_f2_itemsArrayList.size() > 0) {

            section_f2_adapter = new Section_f2_adapter(section_f2_itemsArrayList, Section_F_table2.this);
            rvf2a_f2b.setAdapter(section_f2_adapter);
            rvf2a_f2b.setVisibility(View.VISIBLE);
        } else {
            rvf2a_f2b.setVisibility(View.GONE);
        }


    }

    void SpinnerData() {
        List<String> crops_f1 = new ArrayList<String>();

        crops_f1.add("");
        crops_f1.add("1~ گنا");
        crops_f1.add("2~ مکئی");
        crops_f1.add("3~ گندم");
        crops_f1.add("4~ کپاس");
        crops_f1.add("5~ چاول");
        crops_f1.add("6~ کینو");
        crops_f1.add("7~ آلو");
        crops_f1.add("8~ ٹماٹر");
        crops_f1.add("9~ کھیرا");
        crops_f1.add("10~ مرچ");
        crops_f1.add("11~ آم");
        crops_f1.add("12~ جوار");
        crops_f1.add("13~ باجرہ");
        crops_f1.add("14~ مونگ");
        crops_f1.add("15~ ماش");
        crops_f1.add("16~ تل");
        crops_f1.add("17~ مونگ پھلی");
        crops_f1.add("18~ تربوز");
        crops_f1.add("19~ بھنڈی توری");
        crops_f1.add("20~ چنے");
        crops_f1.add("21~ جو");
        crops_f1.add("22~ مسور");
        crops_f1.add("23~ مٹر");
        crops_f1.add("24~ سرسوں");
        crops_f1.add("25~ چارہ/بھوسہ");
        crops_f1.add("26~ سورج مکھی");
        crops_f1.add("27~ پیاز");
        crops_f1.add("28~ دیگر وضاحت کریں");
        crops_f1.add("99~ کوئی نئی فصل نہیں متعارف کروائی گئی");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterf1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crops_f1);

        // Drop down layout style - list view with radio button
        dataAdapterf1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_qf1_crop.setAdapter(dataAdapterf1);


        List<String> crops_f2b = new ArrayList<String>();

        crops_f2b.add("");
        crops_f2b.add("1~ گنا");
        crops_f2b.add("2~ مکئی");
        crops_f2b.add("3~ گندم");
        crops_f2b.add("4~ کپاس");
        crops_f2b.add("5~ چاول");
        crops_f2b.add("6~ کینو");
        crops_f2b.add("7~ آلو");
        crops_f2b.add("8~ ٹماٹر");
        crops_f2b.add("9~ کھیرا");
        crops_f2b.add("10~ مرچ");
        crops_f2b.add("11~ آم");
        crops_f2b.add("12~ جوار");
        crops_f2b.add("13~ باجرہ");
        crops_f2b.add("14~ مونگ");
        crops_f2b.add("15~ ماش");
        crops_f2b.add("16~ تل");
        crops_f2b.add("17~ مونگ پھلی");
        crops_f2b.add("18~ تربوز");
        crops_f2b.add("19~ بھنڈی توری");
        crops_f2b.add("20~ چنے");
        crops_f2b.add("21~ جو");
        crops_f2b.add("22~ مسور");
        crops_f2b.add("23~ مٹر");
        crops_f2b.add("24~ سرسوں");
        crops_f2b.add("25~ چارہ/بھوسہ");
        crops_f2b.add("26~ سورج مکھی");
        crops_f2b.add("27~ پیاز");
        crops_f2b.add("28~ دیگر وضاحت کریں");
        crops_f2b.add("88- ~ اگر کوئی اور فصل کاشت نہیں کی گئی تو فصل میں -88ڈال دیں");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterf2b = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crops_f2b);

        // Drop down layout style - list view with radio button
        dataAdapterf2b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_qf2b_crop.setAdapter(dataAdapterf2b);


    }

    void TotalCropArea() {

        if (section_f2_itemsArrayList != null && section_f2_itemsArrayList.size() > 0) {

        } else {
            rvf2a_f2b.setVisibility(View.GONE);
        }

    }

    void GetTotalArea() {
        checkarea = "";
        total_kanals_f1 = 0;
        total_kanals_f2 = 0;
        Cursor cursor = databaseAccess.getSectionF_table_2Recycler(FarmerID);
        ArrayList<Integer> total_kanals_list = new ArrayList<>();
        ArrayList<String> max_acres = new ArrayList<>();
        ArrayList<String> max_crops = new ArrayList<>();


        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String f1_crop = cursor.getString(cursor.getColumnIndex("f1_crop"));
                    String f1_other = cursor.getString(cursor.getColumnIndex("f1_other"));
                    Integer f2a_kanal = cursor.getInt(cursor.getColumnIndex("f2a_kanal"));
                    Integer f2a_acre = cursor.getInt(cursor.getColumnIndex("f2a_acre"));
                    String f2b_crop = cursor.getString(cursor.getColumnIndex("f2b_crop"));
                    String f2b_other = cursor.getString(cursor.getColumnIndex("f2b_other"));
                    Integer f2b_kanal = cursor.getInt(cursor.getColumnIndex("f2b_kanal"));
                    Integer f2b_acre = cursor.getInt(cursor.getColumnIndex("f2b_acre"));

                    if ((f2a_kanal == -99 || f2a_acre == -99) || (((f2b_kanal == -99 || f2b_acre == -99) && !f2b_crop.equalsIgnoreCase("-88")))) {
                        checkarea = "-99";
                        MubLog.cpnsoleLog("checkarea = " + checkarea);
                    }
                    Integer acres_kanals = f2a_acre * 8;
                    total_kanals_f1 = acres_kanals + f2a_kanal;
                    if (!f2b_crop.equalsIgnoreCase("-88")) {
                        Integer acres_kanals_f2b = f2b_acre * 8;
                        total_kanals_f2 = acres_kanals_f2b + f2b_kanal;
                    }
                    if (!StringUtils.isEmpty(Integer.toString(total_kanals_f2))) {
                        total_kanals_list.add(total_kanals_f1 + total_kanals_f2);
                    } else {
                        total_kanals_list.add(total_kanals_f1);
                    }
                    max_acres.add(Integer.toString(f2a_acre));
                    max_crops.add(f1_crop);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if ((total_kanals_list != null && total_kanals_list.size() > 0)) {
            total_area = 0;
            for (int i = 0; i < total_kanals_list.size(); i++) {
                total_area += total_kanals_list.get(i);
            }
            MubLog.cpnsoleLog("totalarea = " + total_area);
        }
    }

    void CheckSectionf1() {
        try {

            Cursor cursor = databaseAccess.getSectionF_table_1Data(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                d8a = cursor.getString(cursor.getColumnIndex("d8a"));
                Integer d8c_kanal = cursor.getInt(cursor.getColumnIndex("d8c_kanal"));
                Integer d8c_acre = cursor.getInt(cursor.getColumnIndex("d8c_acre"));
                Integer d8d_kanal = cursor.getInt(cursor.getColumnIndex("d8d_kanal"));
                Integer d8d_acre = cursor.getInt(cursor.getColumnIndex("d8d_acre"));
                Integer d11_kanal = cursor.getInt(cursor.getColumnIndex("d11_kanal"));
                Integer d11_acre = cursor.getInt(cursor.getColumnIndex("d11_acre"));


                if (d8d_acre == -99 || d8d_kanal == -99
                        || d8c_acre == -99 || d8c_kanal == -99
                        || d11_acre == -99 || d11_kanal == -99) {
                    checkarea = "-99";
                    MubLog.cpnsoleLog("checkarea = " + checkarea);

                }

                if (d8a.equalsIgnoreCase("1")) {
                    Integer d8dacres_kanals = d8d_acre * 8;
                    Integer d8dtotal_kanals = d8dacres_kanals + d8d_kanal;

                    Integer d11acres_kanals = d11_acre * 8;
                    Integer d11total_kanals = d11acres_kanals + d11_kanal;

                    f1_total_area = d11total_kanals + d8dtotal_kanals;

                } else {
                    Integer d8cacres_kanals = d8c_acre * 8;
                    Integer d8ctotal_kanals = d8cacres_kanals + d8c_kanal;

                    Integer d8dacres_kanals = d8d_acre * 8;
                    Integer d8dtotal_kanals = d8dacres_kanals + d8d_kanal;

                    Integer d11acres_kanals = d11_acre * 8;
                    Integer d11total_kanals = d11acres_kanals + d11_kanal;

                    f1_total_area = d11total_kanals + d8dtotal_kanals + d8ctotal_kanals;

                }


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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

    void areadisplay() {


        if (f1_total_area < 8) {
            totalarea_textview.setText("Total Area in Kanal = " + f1_total_area);
        } else {
            totalarea_textview.setText("Total Area in Kanal = " + f1_total_area);
        }
        GetTotalArea();
        if (checkarea.equalsIgnoreCase("-99")) {
            remainingarea_textview.setText("Remaining Area in Kanal = " + "-99");
        } else {
            Integer remainingarea = f1_total_area - total_area;
            remainingarea_textview.setText("Remaining Area in Kanal = " + remainingarea);
        }
    }
}
