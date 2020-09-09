package com.rcons.fcallbacks.Questionnaire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
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
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Q_SectionG_b extends AppCompatActivity {

    boolean isFromEdit = false;
    LinearLayout caller_layout;
    DatabaseAdapter databaseAccess;
    String userName = "";
    boolean isPendingCall = false;
    String region = "";
    String emp_id = "";
    String id;
    String order_id = "";
    TextView txtFarmerID, txtEmpID, txtOrderID;
    String FarmerID = "";
    String farmerVillage = "";
    String farmerDistrict = "";
    String farmerTehsil = "";
    String farmer_cellphone;
    @BindView(R.id.qg8_layout)
    LinearLayout qg8_layout;
    @BindView(R.id.qg9_layout)
    LinearLayout qg9_layout;
    @BindView(R.id.qg10_layout)
    LinearLayout qg10_layout;
    @BindView(R.id.qg11_layout)
    LinearLayout qg11_layout;
    @BindView(R.id.qg12_g13_layout)
    LinearLayout qg12_g13_layout;

    @BindView(R.id.rg_qg8)
    RadioGroup rg_qg8;
    @BindView(R.id.edt_g9_kanal)
    EditText edt_g9_kanal;
    @BindView(R.id.edt_g9_acre)
    EditText edt_g9_acre;
    @BindView(R.id.edt_g10)
    EditText edt_g10;
    @BindView(R.id.edt_g11)
    EditText edt_g11;
    @BindView(R.id.edt_g12_1)
    EditText edt_g12_1;
    @BindView(R.id.edt_g13_1)
    EditText edt_g13_1;
    @BindView(R.id.edt_g12_2)
    EditText edt_g12_2;
    @BindView(R.id.edt_g13_2)
    EditText edt_g13_2;
    @BindView(R.id.edt_g12_3)
    EditText edt_g12_3;
    @BindView(R.id.edt_g13_3)
    EditText edt_g13_3;
    @BindView(R.id.edt_g12_4)
    EditText edt_g12_4;
    @BindView(R.id.edt_g13_4)
    EditText edt_g13_4;
    @BindView(R.id.edt_g12_5)
    EditText edt_g12_5;
    @BindView(R.id.edt_g12_6)
    EditText edt_g12_6;
    @BindView(R.id.edt_g12_7_other)
    EditText edt_g12_7_other;
    @BindView(R.id.edt_g12_7)
    EditText edt_g12_7;
    @BindView(R.id.edt_g13_7)
    EditText edt_g13_7;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    Button btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;
    @BindView(R.id.btnAddMore)
    AppCompatCheckBox btnAddMore;
    @BindView(R.id.other_layout)
    LinearLayout other_layout;

    @BindView(R.id.g11_textview)
    TextView g11_textview;

    @BindView(R.id.G10_Total_textview)
    TextView G10_Total_textview;

    @BindView(R.id.G11_sellout_textview)
    TextView G11_sellout_textview;

    String g8 = "";
    String g9_kanal = "";
    String g9_acre = "";
    String g10 = "";
    String g11 = "";
    String g12_1 = "";
    String g13_1 = "";
    String g12_2 = "";
    String g13_2 = "";
    String g12_3 = "";
    String g13_3 = "";
    String g12_4 = "";
    String g13_4 = "";
    String g12_5 = "";
    String g12_6 = "";
    String g12_7_other = "";
    String g12_7 = "";
    String g13_7 = "";
    String comments = "";
    String enum_name = "";
    String enum_code = "";

    ImageView btniBack;

    int total = 0;
    int total_all = 0;
    Integer total_kanals = 0;
    Integer Minor_total_kanals = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q__section_g_b);

        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(Q_SectionG_b.this);
        databaseAccess.Open();
        btniBack = findViewById(R.id.btnBack);
        userName = getIntent().getStringExtra("username");
        isPendingCall = getIntent().getBooleanExtra("isPendingCall", false);
        region = getIntent().getStringExtra("region");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        farmerVillage = getIntent().getStringExtra("farmerVillage");
        farmerDistrict = getIntent().getStringExtra("farmerDistrict");
        farmerTehsil = getIntent().getStringExtra("farmerTehsil");
        FarmerID = getIntent().getStringExtra("farmerID");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");
        txtFarmerID = findViewById(R.id.txt_farmer_id);
        txtFarmerID.setText("FarmerID: " + FarmerID);

        txtEmpID = findViewById(R.id.txt_emp_id);
        txtEmpID.setText("EmpID: " + emp_id);
        id = getIntent().getStringExtra("id");
        txtOrderID = findViewById(R.id.txt_order_id);
        txtOrderID.setText("OrderID: " + order_id);

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
        EditTextValidations(edt_g12_1, edt_g13_1);
        EditTextValidations(edt_g12_2, edt_g13_2);
        EditTextValidations(edt_g12_3, edt_g13_3);
        EditTextValidations(edt_g12_4, edt_g13_4);
        EditTextValidations(edt_g12_7, edt_g13_7);


        btnAddMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    other_layout.setVisibility(View.VISIBLE);
                } else {
                    other_layout.setVisibility(View.GONE);
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
                        Toast.makeText(Q_SectionG_b.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Q_SectionG_b.this, AddReportActivity.class);
                intent.putExtra("farmer_id", FarmerID);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", farmer_cellphone);
                intent.putExtra("isFromCallActivity", false);
                intent.putExtra("isFromEdit", isFromEdit);
                intent.putExtra("section", "sectionG_b");
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

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RConsUtils.hideKeyboard(Q_SectionG_b.this);
                int rg_qg8_id = rg_qg8.getCheckedRadioButtonId();
                SaveData();
                if (qg8_layout.getVisibility() == View.VISIBLE) {
                    RadioButton radioButton = findViewById(rg_qg8_id);
                    if (rg_qg8_id == R.id.rbtn_g8_1) {
                        g8 = radioButton.getTag().toString();
                        qg8_layout.setVisibility(View.GONE);
                        qg9_layout.setVisibility(View.VISIBLE);
                        SaveData();
                    } else if (rg_qg8_id == R.id.rbtn_g8_2) {
                        g8 = radioButton.getTag().toString();
                        SaveData();
                        NextSection();
                    } else {
                        Toast.makeText(Q_SectionG_b.this, "Please select some Option", Toast.LENGTH_SHORT).show();
                    }
                } else if (qg9_layout.getVisibility() == View.VISIBLE) {
                    g9_acre = edt_g9_acre.getText().toString();
                    g9_kanal = edt_g9_kanal.getText().toString();
                    if (StringUtils.isEmpty(g9_acre)) {
                        Toast.makeText(Q_SectionG_b.this, "Please Enter Acre", Toast.LENGTH_SHORT).show();
                    }
                    if (StringUtils.isEmpty(g9_kanal)) {
                        Toast.makeText(Q_SectionG_b.this, "Please Enter Kanal", Toast.LENGTH_SHORT).show();
                    } else if (Integer.parseInt(g9_kanal) > 7) {
                        Toast.makeText(Q_SectionG_b.this, "Kanal Must be from 0-7.", Toast.LENGTH_SHORT).show();
                    } else {
                        SaveData();
                        qg9_layout.setVisibility(View.GONE);
                        qg10_layout.setVisibility(View.VISIBLE);
                    }
                } else if (qg10_layout.getVisibility() == View.VISIBLE) {

                    g10 = edt_g10.getText().toString();
                    if (StringUtils.isEmpty(g10)) {
                        Toast.makeText(Q_SectionG_b.this, "پیداوار من میں میں نوٹ کریں ", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(g10) < 0 || Integer.parseInt(g10) > 40000) && !g10.equalsIgnoreCase("-99")) {
                        Toast.makeText(Q_SectionG_b.this, "Please Enter in 0-40000", Toast.LENGTH_SHORT).show();
                    } else {
                        G10_Total_textview.setText("G10 Total : " + g10);
                        g11_textview.setText("آپ نے( " + g10 + ")من میں سے کتنی  گندم  فروخت کی؟");
                        SaveData();
                        qg10_layout.setVisibility(View.GONE);
                        qg11_layout.setVisibility(View.VISIBLE);
                    }


                } else if (qg11_layout.getVisibility() == View.VISIBLE) {
                    g11 = edt_g11.getText().toString();
                    if (StringUtils.isEmpty(g11)) {
                        Toast.makeText(Q_SectionG_b.this, " وزن من میں میں نوٹ کریں ", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(g11) > Integer.parseInt(g10)) && !g11.equalsIgnoreCase("-99") && !g10.equalsIgnoreCase("-99")) {
                        Toast.makeText(Q_SectionG_b.this, "g11 Must be less than g10", Toast.LENGTH_SHORT).show();
                    } else {
                        G11_sellout_textview.setText("G11 Sale out : " + g11);
                        SaveData();
                        qg11_layout.setVisibility(View.GONE);
                        qg12_g13_layout.setVisibility(View.VISIBLE);
                    }

                } else if (qg12_g13_layout.getVisibility() == View.VISIBLE) {
                    g12_1 = edt_g12_1.getText().toString();
                    g12_2 = edt_g12_2.getText().toString();
                    g12_3 = edt_g12_3.getText().toString();
                    g12_4 = edt_g12_4.getText().toString();
                    g12_5 = edt_g12_5.getText().toString();
                    g12_6 = edt_g12_6.getText().toString();
                    g12_7 = edt_g12_7.getText().toString();

                    g13_1 = edt_g13_1.getText().toString();
                    g13_2 = edt_g13_2.getText().toString();
                    g13_3 = edt_g13_3.getText().toString();
                    g13_4 = edt_g13_4.getText().toString();

                    g13_7 = edt_g13_7.getText().toString();
                    g12_7_other = edt_g12_7_other.getText().toString();

                    if (StringUtils.isEmpty(g12_1)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter G1 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g13_1.isEnabled() && StringUtils.isEmpty(g13_1)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter G1 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g12_2)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g8 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g13_2.isEnabled() && StringUtils.isEmpty(g13_2)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g8 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g12_3)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g9 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g13_3.isEnabled() && StringUtils.isEmpty(g13_3)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g9 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g12_4)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g10 Weight", Toast.LENGTH_SHORT).show();
                    } else if (edt_g13_4.isEnabled() && StringUtils.isEmpty(g13_4)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g10 Price", Toast.LENGTH_SHORT).show();

                    } else if (StringUtils.isEmpty(g12_5)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g11 Weight", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g12_6)) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g12 Weight", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g12_7) && other_layout.getVisibility() == View.VISIBLE) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g13 Weight", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g13_7) && other_layout.getVisibility() == View.VISIBLE && g12_7.length() > 0 && !g12_7.equalsIgnoreCase("0")) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g13 Price", Toast.LENGTH_SHORT).show();
                    } else if (StringUtils.isEmpty(g12_7_other) && other_layout.getVisibility() == View.VISIBLE && g12_7.length() > 0 && !g12_7.equalsIgnoreCase("0")) {
                        Toast.makeText(Q_SectionG_b.this, "Please enter g13 Other", Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            total = Integer.parseInt(g12_1) + Integer.parseInt(g12_2) + Integer.parseInt(g12_3) + Integer.parseInt(g12_4);
                            total_all = Integer.parseInt(g12_1) + Integer.parseInt(g12_2) + Integer.parseInt(g12_3) + Integer.parseInt(g12_4) + Integer.parseInt(g12_5) + Integer.parseInt(g12_6) + Integer.parseInt(g12_7);
                            ////////////////////////// Check g10 With All total and if -99 then ignore
                            if (total == Integer.parseInt(g11) || Integer.parseInt(g11) == -99 || Integer.parseInt(g12_1) == -99 || Integer.parseInt(g12_2) == -99
                                    || Integer.parseInt(g12_3) == -99 || Integer.parseInt(g12_4) == -99 || Integer.parseInt(g11) == -99) {
                                ////////////////////////// Check g10 With All total and if -99 then ignore
                                if (total_all == Integer.parseInt(g10) || Integer.parseInt(g12_1) == -99 || Integer.parseInt(g12_2) == -99
                                        || Integer.parseInt(g12_3) == -99 || Integer.parseInt(g12_4) == -99 || Integer.parseInt(g12_5) == -99
                                        || Integer.parseInt(g12_6) == -99 || Integer.parseInt(g12_7) == -99 || Integer.parseInt(g10) == -99) {
                                    SaveData();
                                    NextSection();
                                } else {
                                    ShowErrorMessage(Q_SectionG_b.this, "Error", "برائے مہربانی g12_1 to g12_g13 کا ٹوٹل g10 کے برابر ہونا چاہئے۔ g10 اس کی کل پیداوار ہے جبکہ g10 = " + g10 + " ہے اور total =" + total_all + "ہے");
                                }


                            } else {

                                ShowErrorMessage(Q_SectionG_b.this, "Error", "برائے مہربانی  g12_g1 to g12_g10 کا ٹوٹل g11 کے برابر ہونا چاہئے۔ g11 اس نے کل میں سے کتنی گندم فروخت کی ہے. جبکہ g11 =  " + g11 + " ہے اور total =" + total + "ہے");
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

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
        if (ActivityCompat.checkSelfPermission(Q_SectionG_b.this,
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
                    Toast.makeText(Q_SectionG_b.this, "Phone number is empty.", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (qg12_g13_layout.getVisibility() == View.VISIBLE) {

            qg11_layout.setVisibility(View.VISIBLE);
            qg12_g13_layout.setVisibility(View.GONE);

        } else if (qg11_layout.getVisibility() == View.VISIBLE) {

            qg10_layout.setVisibility(View.VISIBLE);
            qg11_layout.setVisibility(View.GONE);

        } else if (qg10_layout.getVisibility() == View.VISIBLE) {
            qg9_layout.setVisibility(View.VISIBLE);
            qg10_layout.setVisibility(View.GONE);

        } else if (qg9_layout.getVisibility() == View.VISIBLE) {
            qg8_layout.setVisibility(View.VISIBLE);
            qg9_layout.setVisibility(View.GONE);

        } else if (qg8_layout.getVisibility() == View.VISIBLE) {

            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
            super.onBackPressed();

        }

    }

    void ShowErrorMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.g4_error_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
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


    void SaveData() {

        try {

            createNewColumnsBeforeInsertion (DatabaseAdapter.SectionG_bTable);



            databaseAccess.saveSectionG_b_Data(enum_name,enum_code,emp_id, order_id, FarmerID, g8, g9_kanal, g9_acre, g10, g11, g12_1, g13_1, g12_2, g13_2, g12_3, g13_3, g12_4, g13_4, g12_5, g12_6, g12_7_other, g12_7, g13_7, comments);


            MubLog.cpnsoleLog("inside SaveData FarmerID "+FarmerID + " SectionBTable  =  "+DatabaseAdapter.SectionG_bTable);
            databaseAccess.update_insert_or_updated_in_phone_at(FarmerID,DatabaseAdapter.SectionG_bTable);


            create_AND_EXECUTE_PATVH_QUERRY(DatabaseAdapter.SectionG_bTable);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    private void create_AND_EXECUTE_PATVH_QUERRY(String tableName) {


        try {

            MubLog.cpnsoleLog("inside create_AND_EXECUTE_PATVH_QUERRY "+tableName);



            String patch_querry = "UPDATE  "+tableName+" SET  g13_7 ='' , g12_7_other ='' WHERE `g12_7` IN (0,00) and (g13_7 !='' OR g12_7_other !='')";


            databaseAccess.execute_patch_query(patch_querry);

            patch_querry = "UPDATE  "+tableName+" SET  g13_4 ='' WHERE `g12_4` IN (0,00) and g13_4 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  g13_3 ='' WHERE `g12_3` IN (0,00) and g13_3 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  g13_1 ='' WHERE `g12_1` IN (0,00) and g13_1 !=''";
            databaseAccess.execute_patch_query(patch_querry);
            patch_querry ="UPDATE  "+tableName+" SET  g13_2 ='' WHERE `g12_2` IN (0,00) and g13_2 !=''";
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


    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getSectionG_bData(FarmerID);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                g8 = cursor.getString(cursor.getColumnIndex("g8"));
                g9_kanal = cursor.getString(cursor.getColumnIndex("g9_kanal"));
                g9_acre = cursor.getString(cursor.getColumnIndex("g9_acre"));
                g10 = cursor.getString(cursor.getColumnIndex("g10"));
                g11 = cursor.getString(cursor.getColumnIndex("g11"));
                g12_1 = cursor.getString(cursor.getColumnIndex("g12_1"));
                g13_1 = cursor.getString(cursor.getColumnIndex("g13_1"));
                g12_2 = cursor.getString(cursor.getColumnIndex("g12_2"));
                g13_2 = cursor.getString(cursor.getColumnIndex("g13_2"));
                g12_3 = cursor.getString(cursor.getColumnIndex("g12_3"));
                g13_3 = cursor.getString(cursor.getColumnIndex("g13_3"));
                g12_4 = cursor.getString(cursor.getColumnIndex("g12_4"));
                g13_4 = cursor.getString(cursor.getColumnIndex("g13_4"));
                g12_5 = cursor.getString(cursor.getColumnIndex("g12_5"));
                g12_6 = cursor.getString(cursor.getColumnIndex("g12_6"));
                g12_7_other = cursor.getString(cursor.getColumnIndex("g12_7_other"));
                g12_7 = cursor.getString(cursor.getColumnIndex("g12_7"));
                g13_7 = cursor.getString(cursor.getColumnIndex("g13_7"));
                comments = cursor.getString(cursor.getColumnIndex("comments"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    void LoadPreviousData() {
        readFromDataBase();

        if (!StringUtils.isEmpty(g8)) {
            for (int i = 0; i < rg_qg8.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) rg_qg8.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(g8)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }


        if (!StringUtils.isEmpty(g9_kanal)) {
            edt_g9_kanal.setText(g9_kanal);
        }
        if (!StringUtils.isEmpty(g9_acre)) {
            edt_g9_acre.setText(g9_acre);
        }
        if (!StringUtils.isEmpty(g10)) {
            edt_g10.setText(g10);
        }

        if (!StringUtils.isEmpty(g11)) {
            edt_g11.setText(g11);
        }

        if (!StringUtils.isEmpty(g12_1)) {
            edt_g12_1.setText(g12_1);
        }

        if (!StringUtils.isEmpty(g12_2)) {
            edt_g12_2.setText(g12_2);
        }

        if (!StringUtils.isEmpty(g12_3)) {
            edt_g12_3.setText(g12_3);
        }

        if (!StringUtils.isEmpty(g12_4)) {
            edt_g12_4.setText(g12_4);
        }

        if (!StringUtils.isEmpty(g12_5)) {
            edt_g12_5.setText(g12_5);
        }

        if (!StringUtils.isEmpty(g12_6)) {
            edt_g12_6.setText(g12_6);
        }
        if (!StringUtils.isEmpty(g12_7)) {
            edt_g12_7.setText(g12_7);
        }

        if (!StringUtils.isEmpty(g12_7_other)) {
            edt_g12_7_other.setText(g12_7_other);
        }
        if (!StringUtils.isEmpty(g13_1)) {
            edt_g13_1.setText(g13_1);
        }

        if (!StringUtils.isEmpty(g13_2)) {
            edt_g13_2.setText(g13_2);
        }

        if (!StringUtils.isEmpty(g13_3)) {
            edt_g13_3.setText(g13_3);
        }

        if (!StringUtils.isEmpty(g13_4)) {
            edt_g13_4.setText(g13_4);
        }

        if (!StringUtils.isEmpty(g13_7)) {
            edt_g13_7.setText(g13_7);
        }

        ///////////////////////
    }

    void NextSection() {
        String crop_type = "";
        Cursor cursor = databaseAccess.getSectionF_table_2Recycler(FarmerID);
        ArrayList<Integer> total_kanals_list = new ArrayList<>();
        ArrayList<String> max_acres = new ArrayList<>();
        ArrayList<String> max_crops = new ArrayList<>();
        ArrayList<Integer> Minor_total_kanals_list = new ArrayList<>();
        ArrayList<String> Minor_max_acres = new ArrayList<>();
        ArrayList<String> Minor_max_crops = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String f1_crop = cursor.getString(cursor.getColumnIndex("f1_crop"));
                    String f1_other = cursor.getString(cursor.getColumnIndex("f1_other"));
                    Integer f2a_kanal = cursor.getInt(cursor.getColumnIndex("f2a_kanal"));
                    Integer f2a_acre = cursor.getInt(cursor.getColumnIndex("f2a_acre"));
                    String f2b_crop = cursor.getString(cursor.getColumnIndex("f2b_crop"));
                    String f2b_other = cursor.getString(cursor.getColumnIndex("f2b_other"));
                    String f2b_kanal = cursor.getString(cursor.getColumnIndex("f2b_kanal"));
                    String f2b_acre = cursor.getString(cursor.getColumnIndex("f2b_acre"));

                    Cursor cursor_;
                    if (f1_crop.equalsIgnoreCase("6") || f1_crop.equalsIgnoreCase("7")
                            || f1_crop.equalsIgnoreCase("8") || f1_crop.equalsIgnoreCase("9")
                            || f1_crop.equalsIgnoreCase("10") ){
                        String region_ = "Punjab";
                        cursor_ = databaseAccess.getSection_H_Crops(region_, f1_crop, "Basic Questions");
                    }else {
                        cursor_ = databaseAccess.getSection_H_Crops(region, f1_crop, "Basic Questions");
                    }

                    if ((f1_crop.equalsIgnoreCase("1") || (f1_crop.equalsIgnoreCase("2"))
                            || (f1_crop.equalsIgnoreCase("3")) || (f1_crop.equalsIgnoreCase("4"))
                            || (f1_crop.equalsIgnoreCase("5"))) && (cursor_ != null && cursor_.getCount() > 0)) {
                        Integer acres_kanals = f2a_acre * 8;
                        total_kanals = acres_kanals + f2a_kanal;
                        total_kanals_list.add(total_kanals);
                        max_acres.add(Integer.toString(f2a_acre));
                        max_crops.add(f1_crop);

                        MubLog.cpnsoleLog("GetMaximumCrops " + "Crop ID = " + f1_crop + " Total Kanals = " + Integer.toString(total_kanals) + " f2a_acre = " + Integer.toString(f2a_acre));
                    }

                    if ((f1_crop.equalsIgnoreCase("6") || f1_crop.equalsIgnoreCase("7")
                            || f1_crop.equalsIgnoreCase("8") || f1_crop.equalsIgnoreCase("9")
                            || f1_crop.equalsIgnoreCase("10") || f1_crop.equalsIgnoreCase("11"))
                            && (cursor_ != null && cursor_.getCount() > 0)) {
                        crop_type = "Minor";
                        Integer Minor_acres_kanals = f2a_acre * 8;
                        Minor_total_kanals = Minor_acres_kanals + f2a_kanal;
                        Minor_total_kanals_list.add(Minor_total_kanals);
                        Minor_max_acres.add(Integer.toString(f2a_acre));
                        Minor_max_crops.add(f1_crop);

                        MubLog.cpnsoleLog("Get Minor_ Crop" + "Crop ID = " + f1_crop + " Total Kanals = " + Integer.toString(Minor_total_kanals) + " f2a_acre = " + Integer.toString(f2a_acre));
                    }

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if ((total_kanals_list != null && total_kanals_list.size() > 0) || (Minor_total_kanals_list != null && Minor_total_kanals_list.size() > 0)) {
            Intent intent = new Intent(Q_SectionG_b.this, Q_sectionH.class);
            intent.putExtra("username", userName);
            intent.putExtra("isPendingCall", isPendingCall);
            intent.putExtra("region", region);
            intent.putExtra("emp_id", emp_id);
            intent.putExtra("order_id", order_id);
            intent.putExtra("farmerID", FarmerID);
            intent.putExtra("farmerDistrict", farmerDistrict);
            intent.putExtra("farmerVillage", farmerVillage);
            intent.putExtra("farmerTehsil", farmerTehsil);
            intent.putExtra("farmer_cellphone", farmer_cellphone);
            intent.putExtra("isFromEdit", isFromEdit);
            intent.putExtra("id", id);
            startActivityForResult(intent, 88);
        } else {
            Intent intent = new Intent(Q_SectionG_b.this, Q_section1.class);
            intent.putExtra("username", userName);
            intent.putExtra("isPendingCall", isPendingCall);
            intent.putExtra("region", region);
            intent.putExtra("emp_id", emp_id);
            intent.putExtra("order_id", order_id);
            intent.putExtra("farmerID", FarmerID);
            intent.putExtra("farmerDistrict", farmerDistrict);
            intent.putExtra("farmerVillage", farmerVillage);
            intent.putExtra("farmerTehsil", farmerTehsil);
            intent.putExtra("isFromEdit", isFromEdit);
            intent.putExtra("farmer_cellphone", farmer_cellphone);
            intent.putExtra("id", id);
            startActivityForResult(intent, 88);
        }
    }

    void EditTextValidations(EditText check, EditText enable) {
        check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str_check = check.getText().toString();
                if (!str_check.isEmpty()) {
                    if ((str_check.length() > 0) && (!str_check.equalsIgnoreCase("0") && (!str_check.equalsIgnoreCase("00")) && (!str_check.equalsIgnoreCase("000")))) {

                        enable.setEnabled(true);
                        enable.setHint("Price");
                    } else {
                        enable.setEnabled(false);
                        enable.setHint("Not Required");
                        enable.setText("");
                    }

                } else {
                    enable.setEnabled(false);
                    enable.setHint("Price");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
