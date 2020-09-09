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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Section_F.Section_f12_f15_adapter;
import com.rcons.fcallbacks.Section_F.Section_f12_f15_items;
import com.rcons.fcallbacks.Section_F.Section_f2_adapter;
import com.rcons.fcallbacks.Section_F.Section_f2_items;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Section_F_table5 extends AppCompatActivity implements com.rcons.fcallbacks.Section_F.onf12_f15Click {
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
    String enum_name = "";
    String enum_code = "";

    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;
    Spinner qf12_crop_spinner;
    TextView tv_f12Crop_id;
    RecyclerView rvf12_f15;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;

    @BindView(R.id.txtf12Error)
    TextView txtf12Error;

    @BindView(R.id.edt_qf12_other)
    EditText edt_qf12_other;

    @BindView(R.id.edt_qf13_other)
    EditText edt_qf13_other;
    @BindView(R.id.edt_qf14_other)
    EditText edt_qf14_other;
    @BindView(R.id.edt_qf15_other)
    EditText edt_qf15_other;

    @BindView(R.id.rg_qf13)
    RadioGroup rg_qf13;
    @BindView(R.id.rg_qf14)
    RadioGroup rg_qf14;
    @BindView(R.id.rg_qf15)
    RadioGroup rg_qf15;

    @BindView(R.id.btnAddMoreF12)
    Button btnAddMoreF12;

    String f12_crop = "";
    String f12_other = "";
    String f13 = "";
    String f13_other = "";
    String f14 = "";
    String f14_other = "";
    String f15 = "";
    String f15_other = "";

    LinearLayoutManager manager;
    ArrayList<Section_f12_f15_items> section_f12_f15_itemsArrayList;
    Section_f12_f15_adapter section_f12_f15_adapter;

    ImageView btniBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section__f_table5);
        ButterKnife.bind(this);

        btniBack = findViewById(R.id.btnBack);

        qf12_crop_spinner = findViewById(R.id.qf12_crop_spinner);
        tv_f12Crop_id = findViewById(R.id.tv_f12Crop_id);
        rvf12_f15 = findViewById(R.id.rvf12_f15);

        databaseAccess = new DatabaseAdapter(Section_F_table5.this);
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
        caller_layout = findViewById(R.id.caller_layout);
        isFromEdit = getIntent().getBooleanExtra("isFromEdit", false);

        if (isFromEdit == true) {
            caller_layout.setVisibility(View.GONE);
        } else {
            caller_layout.setVisibility(View.VISIBLE);
        }
        SetRecylerData();
        SpinnerData();
        GetEnum();
        qf12_crop_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                try {
                    if (position != 0) {
                        if (position == 29) {
                            position = 99;
                        }
                        Cursor cursor = databaseAccess.getSectionF_table_5Data_(FarmerID, Integer.toString(position));
                        if (cursor != null && cursor.getCount() > 0) {
                            txtf12Error.setVisibility(View.VISIBLE);
                            tv_f12Crop_id.setText("");
                        } else {
                            txtf12Error.setVisibility(View.GONE);
                            if (position != 28) {
                                edt_qf12_other.setVisibility(View.GONE);
                            } else {
                                edt_qf12_other.setVisibility(View.VISIBLE);
                            }
                            if (position == 29) {
                                tv_f12Crop_id.setText("99");
                            } else {

                                tv_f12Crop_id.setText(Integer.toString(position));
                                Toast.makeText(parent.getContext(), item, Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        tv_f12Crop_id.setText("");
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
        btnAddMoreF12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RG_qf13_ID = rg_qf13.getCheckedRadioButtonId();
                int RG_qf14_ID = rg_qf14.getCheckedRadioButtonId();
                int RG_qf15_ID = rg_qf15.getCheckedRadioButtonId();

                f12_crop = tv_f12Crop_id.getText().toString();
                f12_other = edt_qf12_other.getText().toString();
                f13_other = edt_qf13_other.getText().toString();
                f14_other = edt_qf14_other.getText().toString();
                f15_other = edt_qf15_other.getText().toString();

                if (StringUtils.isEmpty(f12_crop)) {
                    Toast.makeText(Section_F_table5.this, "Please select F1 crop", Toast.LENGTH_SHORT).show();
                } else if (edt_qf12_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f12_other)) {
                    Toast.makeText(Section_F_table5.this, "Please add Other crop", Toast.LENGTH_SHORT).show();
                } else if (RG_qf13_ID < 1) {
                    Toast.makeText(Section_F_table5.this, "Please Select F13", Toast.LENGTH_SHORT).show();
                } else if (edt_qf13_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f13_other)) {
                    Toast.makeText(Section_F_table5.this, "Please fill edt_f13_other", Toast.LENGTH_SHORT).show();
                } else if (RG_qf14_ID < 1) {
                    Toast.makeText(Section_F_table5.this, "Please Select F14", Toast.LENGTH_SHORT).show();
                } else if (edt_qf14_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f14_other) && StringUtils.isEmpty(f14_other)) {
                    Toast.makeText(Section_F_table5.this, "Please fill edt_f14_other", Toast.LENGTH_SHORT).show();
                } else if (RG_qf15_ID < 1) {
                    Toast.makeText(Section_F_table5.this, "Please Select F15", Toast.LENGTH_SHORT).show();
                } else if (edt_qf15_other.getVisibility() == View.VISIBLE && StringUtils.isEmpty(f15_other)) {
                    Toast.makeText(Section_F_table5.this, "Please fill edt_f15_other", Toast.LENGTH_SHORT).show();
                } else {
                    SaveData();
                    SetRecylerData();
                    CleanData();
                    SpinnerData();
                   // addmore();
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
                    edt_qf13_other.setText("");

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
                    edt_qf14_other.setText("");

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
                    edt_qf15_other.setText("");
                }
            }
        });


        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Section_F_table5.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                startActivityForResult(intent, 88);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RConsUtils.hideKeyboard(Section_F_table5.this);
                if (rvf12_f15.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(Section_F_table5.this, Section_F_table6.class);
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
                    intent.putExtra("section", "section_f_table5");
                    intent.putExtra("farmer_cellphone", farmer_cellphone);
                    startActivityForResult(intent, 88);
                } else {
                    Toast.makeText(Section_F_table5.this, "Where are you going,Please Fill Section First", Toast.LENGTH_LONG).show();
                }
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
                        Toast.makeText(Section_F_table5.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MubLog.cpnsoleLog("onActivityResult resultCode"+resultCode);
        MubLog.cpnsoleLog("onActivityResult requestCode"+requestCode);
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
        if (ActivityCompat.checkSelfPermission(Section_F_table5.this,
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
                    Toast.makeText(Section_F_table5.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
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

    void SaveData() {
        try {
            String comments = "";

            int RG_qf13_ID = rg_qf13.getCheckedRadioButtonId();
            int RG_qf14_ID = rg_qf14.getCheckedRadioButtonId();
            int RG_qf15_ID = rg_qf15.getCheckedRadioButtonId();

            RadioButton f13radioButton = findViewById(RG_qf13_ID);
            RadioButton f14radioButton = findViewById(RG_qf14_ID);
            RadioButton f15radioButton = findViewById(RG_qf15_ID);

            f13 = f13radioButton.getTag().toString();
            f14 = f14radioButton.getTag().toString();
            f15 = f15radioButton.getTag().toString();
            f12_crop = tv_f12Crop_id.getText().toString();
            f12_other = edt_qf12_other.getText().toString();
            f13_other = edt_qf13_other.getText().toString();
            f14_other = edt_qf14_other.getText().toString();
            f15_other = edt_qf15_other.getText().toString();


            createNewColumnsBeforeInsertion (DatabaseAdapter.SectionFTable5);



            databaseAccess.saveSectionF_table5_Data(enum_name,enum_code,emp_id, order_id, FarmerID, f12_crop, f12_other, f13, f13_other, f14, f14_other, f15, f15_other, comments);
            Toast.makeText(getApplicationContext(), "Data Saved Successfully :" + f12_crop, Toast.LENGTH_LONG).show();



            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionFTable5);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionFTable5);




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



    @Override
    public void onf12_f15Click(Section_f12_f15_items section_f12_f15_items) {
        if (section_f12_f15_items != null) {
            String TxtF12Crop = section_f12_f15_items.getTxtF12();
            //Toast.makeText(getApplicationContext(), "Selected : " + TxtF12Crop + " Crop", Toast.LENGTH_LONG).show();

            LayoutInflater li = LayoutInflater.from(Section_F_table5.this);
            View dialogView = li.inflate(R.layout.delete_dialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Section_F_table5.this);
            alertDialogBuilder.setView(dialogView);
            TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
            txtDialogTitle.setText("Alert");
            TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
            txtErrorMessage.setText("What Do you want with this crop_id = " + section_f12_f15_items.getTxtF12() + " ?");
            Button btnCancel = dialogView.findViewById(R.id.btnCancel);
            Button btndelete = dialogView.findViewById(R.id.btnenum);
            Button btnEdit = dialogView.findViewById(R.id.btnEdit);
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.show();


            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    String f12_crop = section_f12_f15_items.getTxtF12();
                    String f12_other = section_f12_f15_items.getTxtF12_other();
                    String f13 = section_f12_f15_items.getTxtF13();
                    String f13_other = section_f12_f15_items.getTxtF13_other();
                    String f14 = section_f12_f15_items.getTxtF14();
                    String f14_other = section_f12_f15_items.getTxtF14_other();
                    String f15 = section_f12_f15_items.getTxtF15();
                    String f15_other = section_f12_f15_items.getTxtF15_other();

                    tv_f12Crop_id.setText(f12_crop);

                    if (!StringUtils.isEmpty(f13)) {
                        for (int i = 0; i < rg_qf13.getChildCount(); i++) {
                            RadioButton radioButton = (RadioButton) rg_qf13.getChildAt(i);
                            if (radioButton != null) {
                                if (radioButton.getTag().toString().equalsIgnoreCase(f13)) {
                                    radioButton.setChecked(true);
                                }
                            }

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Nothing Found", Toast.LENGTH_LONG).show();
                    }

                    if (!StringUtils.isEmpty(f14)) {
                        for (int i = 0; i < rg_qf14.getChildCount(); i++) {
                            RadioButton radioButton = (RadioButton) rg_qf14.getChildAt(i);
                            if (radioButton != null) {
                                if (radioButton.getTag().toString().equalsIgnoreCase(f14)) {
                                    radioButton.setChecked(true);
                                }
                            }

                        }
                    }
                    if (!StringUtils.isEmpty(f15)) {
                        for (int i = 0; i < rg_qf15.getChildCount(); i++) {
                            RadioButton radioButton = (RadioButton) rg_qf15.getChildAt(i);
                            if (radioButton != null) {
                                if (radioButton.getTag().toString().equalsIgnoreCase(f15)) {
                                    radioButton.setChecked(true);
                                }
                            }

                        }
                    }

                    if (!StringUtils.isEmpty(f12_other)) {
                        edt_qf12_other.setText(f12_other);
                        edt_qf12_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf12_other.setVisibility(View.GONE);
                    }
                    if (!StringUtils.isEmpty(f13_other)) {
                        edt_qf13_other.setText(f13_other);
                        edt_qf13_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf13_other.setVisibility(View.GONE);
                    }

                    if (!StringUtils.isEmpty(f14_other)) {
                        edt_qf14_other.setText(f14_other);
                        edt_qf14_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf14_other.setVisibility(View.GONE);
                    }
                    if (!StringUtils.isEmpty(f15_other)) {
                        edt_qf15_other.setText(f15_other);
                        edt_qf15_other.setVisibility(View.VISIBLE);
                    } else {
                        edt_qf15_other.setVisibility(View.GONE);
                    }


                }
            });
            btndelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    try {
                        String crop_id = section_f12_f15_items.getTxtF12();

                        databaseAccess.DeleteSection_F5Data(FarmerID, crop_id);
                        SetRecylerData();

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

    void addmore() {
        LayoutInflater li = LayoutInflater.from(Section_F_table5.this);
        View dialogView = li.inflate(R.layout.need_add_crop_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Section_F_table5.this);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText("Alert");
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText("Do you want to add more crop ?");
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
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
            }
        });

    }

    ;

    void CleanData() {
        rg_qf13.clearCheck();
        rg_qf14.clearCheck();
        rg_qf15.clearCheck();

        edt_qf12_other.setVisibility(View.GONE);
        edt_qf13_other.setVisibility(View.GONE);
        edt_qf14_other.setVisibility(View.GONE);
        edt_qf15_other.setVisibility(View.GONE);

        edt_qf12_other.setText("");
        edt_qf13_other.setText("");
        edt_qf14_other.setText("");
        edt_qf15_other.setText("");
        tv_f12Crop_id.setText("");
        f13 = "";
        f14 = "";
        f15 = "";

    }

    void SetRecylerData() {
        manager = new LinearLayoutManager(Section_F_table5.this, RecyclerView.VERTICAL, false);
        rvf12_f15.setLayoutManager(manager);
        section_f12_f15_itemsArrayList = new ArrayList<>();

        Cursor cursor = databaseAccess.getSectionF_table_5Data(FarmerID);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Section_f12_f15_items section_f12_f15_items = new Section_f12_f15_items();

                    String qf12_crop = cursor.getString(cursor.getColumnIndex("f12_crop"));
                    String qf12_other = cursor.getString(cursor.getColumnIndex("f12_other"));
                    String qf13 = cursor.getString(cursor.getColumnIndex("f13"));
                    String qf13_other = cursor.getString(cursor.getColumnIndex("f13_other"));
                    String qf14 = cursor.getString(cursor.getColumnIndex("f14"));
                    String qf14_other = cursor.getString(cursor.getColumnIndex("f14_other"));
                    String qf15 = cursor.getString(cursor.getColumnIndex("f15"));
                    String qf15_other = cursor.getString(cursor.getColumnIndex("f15_other"));


                    section_f12_f15_items.setTxtF12(qf12_crop);
                    section_f12_f15_items.setTxtF12_other(qf12_other);
                    section_f12_f15_items.setTxtF13(qf13);
                    section_f12_f15_items.setTxtF13_other(qf13_other);
                    section_f12_f15_items.setTxtF14(qf14);
                    section_f12_f15_items.setTxtF14_other(qf14_other);
                    section_f12_f15_items.setTxtF15(qf15);
                    section_f12_f15_items.setTxtF15_other(qf15_other);
                    section_f12_f15_itemsArrayList.add(section_f12_f15_items);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if (section_f12_f15_itemsArrayList != null && section_f12_f15_itemsArrayList.size() > 0) {

            section_f12_f15_adapter = new Section_f12_f15_adapter(section_f12_f15_itemsArrayList, Section_F_table5.this);
            rvf12_f15.setAdapter(section_f12_f15_adapter);
            rvf12_f15.setVisibility(View.VISIBLE);
        } else {
            rvf12_f15.setVisibility(View.GONE);
        }


    }

    void SpinnerData() {

        List<String> crops_f12 = new ArrayList<String>();

        crops_f12.add("");
        crops_f12.add("1~ گنا");
        crops_f12.add("2~ مکئی");
        crops_f12.add("3~ گندم");
        crops_f12.add("4~ کپاس");
        crops_f12.add("5~ چاول");
        crops_f12.add("6~ کینو");
        crops_f12.add("7~ آلو");
        crops_f12.add("8~ ٹماٹر");
        crops_f12.add("9~ کھیرا");
        crops_f12.add("10~ مرچ");
        crops_f12.add("11~ آم");
        crops_f12.add("12~ جوار");
        crops_f12.add("13~ باجرہ");
        crops_f12.add("14~ مونگ");
        crops_f12.add("15~ ماش");
        crops_f12.add("16~ تل");
        crops_f12.add("17~ مونگ پھلی");
        crops_f12.add("18~ تربوز");
        crops_f12.add("19~ بھنڈی توری");
        crops_f12.add("20~ چنے");
        crops_f12.add("21~ جو");
        crops_f12.add("22~ مسور");
        crops_f12.add("23~ مٹر");
        crops_f12.add("24~ سرسوں");
        crops_f12.add("25~ چارہ/بھوسہ");
        crops_f12.add("26~ سورج مکھی");
        crops_f12.add("27~ پیاز");
        crops_f12.add("28~ دیگر وضاحت کریں");
        crops_f12.add("99~ کوئی نئی فصل نہیں متعارف کروائی گئی");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapterf12 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, crops_f12);

        // Drop down layout style - list view with radio button
        dataAdapterf12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        qf12_crop_spinner.setAdapter(dataAdapterf12);

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
