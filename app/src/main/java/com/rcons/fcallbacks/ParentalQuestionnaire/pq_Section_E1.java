package com.rcons.fcallbacks.ParentalQuestionnaire;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.BuildConfig;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.Main.LoginActivity;
import com.rcons.fcallbacks.Main.MainMenuActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideView;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class pq_Section_E1 extends AppCompatActivity {


    @BindView(R.id.qe1_1_layout)
    LinearLayout qe1_1_layout;
    @BindView(R.id.qe1_2_layout)
    LinearLayout qe1_2_layout;
    @BindView(R.id.qe1_3_layout)
    LinearLayout qe1_3_layout;
    @BindView(R.id.qe1_4_layout)
    LinearLayout qe1_4_layout;
    @BindView(R.id.qe1_5_layout)
    LinearLayout qe1_5_layout;
    @BindView(R.id.qe1_6_layout)
    LinearLayout qe1_6_layout;
    @BindView(R.id.qe1_7_layout)
    LinearLayout qe1_7_layout;
    @BindView(R.id.qe1_8_layout)
    LinearLayout qe1_8_layout;
    @BindView(R.id.qe1_9_layout)
    LinearLayout qe1_9_layout;
    @BindView(R.id.qe1_10_layout)
    LinearLayout qe1_10_layout;
    @BindView(R.id.qe1_11_layout)
    LinearLayout qe1_11_layout;
    @BindView(R.id.qe1_12_layout)
    LinearLayout qe1_12_layout;
    @BindView(R.id.qe1_13_layout)
    LinearLayout qe1_13_layout;
    @BindView(R.id.qe1_14_layout)
    LinearLayout qe1_14_layout;
    @BindView(R.id.qe1_15_layout)
    LinearLayout qe1_15_layout;
    @BindView(R.id.qe1_16_layout)
    LinearLayout qe1_16_layout;

    @BindView(R.id.rg_e1_1)
    RadioGroup rg_e1_1;
    @BindView(R.id.rg_e1_2)
    RadioGroup rg_e1_2;
    @BindView(R.id.rg_e1_3)
    RadioGroup rg_e1_3;
    @BindView(R.id.rg_e1_4)
    RadioGroup rg_e1_4;
    @BindView(R.id.rg_e1_5)
    RadioGroup rg_e1_5;
    @BindView(R.id.rg_e1_6)
    RadioGroup rg_e1_6;
    @BindView(R.id.rg_e1_7)
    RadioGroup rg_e1_7;
    @BindView(R.id.rg_e1_8)
    RadioGroup rg_e1_8;
    @BindView(R.id.rg_e1_9)
    RadioGroup rg_e1_9;
    @BindView(R.id.rg_e1_10)
    RadioGroup rg_e1_10;
    @BindView(R.id.rg_e1_11)
    RadioGroup rg_e1_11;
    @BindView(R.id.rg_e1_12)
    RadioGroup rg_e1_12;
    @BindView(R.id.rg_e1_13)
    RadioGroup rg_e1_13;
    @BindView(R.id.rg_e1_14)
    RadioGroup rg_e1_14;
    @BindView(R.id.rg_e1_15)
    RadioGroup rg_e1_15;
    @BindView(R.id.rg_e1_16)
    RadioGroup rg_e1_16;

    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

    @BindView(R.id.btn_Refused)
    Button btn_Refused;

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
    String refused = "";
    String e1_1 = "";
    String e1_2 = "";
    String e1_3 = "";
    String e1_4 = "";
    String e1_5 = "";
    String e1_6 = "";
    String e1_7 = "";
    String e1_8 = "";
    String e1_9 = "";
    String e1_10 = "";
    String e1_11 = "";
    String e1_12 = "";
    String e1_13 = "";
    String e1_14 = "";
    String e1_15 = "";
    String e1_16 = "";

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_e1);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_E1.this);
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

        txt_School_Code.setText("School Code : " + school_code);
        txt_Student_id.setText("Student Id : " + student_id);

        RConsUtils.hideKeyboard(pq_Section_E1.this);
        LoadPreviousData();
        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;


        btn_Refused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowRefusedMessage(pq_Section_E1.this, "Alert", "Are you sure want to skip this section ?");

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_e1_1_ID = rg_e1_1.getCheckedRadioButtonId();
                int rg_e1_2_ID = rg_e1_2.getCheckedRadioButtonId();
                int rg_e1_3_ID = rg_e1_3.getCheckedRadioButtonId();
                int rg_e1_4_ID = rg_e1_4.getCheckedRadioButtonId();
                int rg_e1_5_ID = rg_e1_5.getCheckedRadioButtonId();
                int rg_e1_6_ID = rg_e1_6.getCheckedRadioButtonId();
                int rg_e1_7_ID = rg_e1_7.getCheckedRadioButtonId();
                int rg_e1_8_ID = rg_e1_8.getCheckedRadioButtonId();
                int rg_e1_9_ID = rg_e1_9.getCheckedRadioButtonId();
                int rg_e1_10_ID = rg_e1_10.getCheckedRadioButtonId();
                int rg_e1_11_ID = rg_e1_11.getCheckedRadioButtonId();
                int rg_e1_12_ID = rg_e1_12.getCheckedRadioButtonId();
                int rg_e1_13_ID = rg_e1_13.getCheckedRadioButtonId();
                int rg_e1_14_ID = rg_e1_14.getCheckedRadioButtonId();
                int rg_e1_15_ID = rg_e1_15.getCheckedRadioButtonId();
                int rg_e1_16_ID = rg_e1_16.getCheckedRadioButtonId();

                RConsUtils.hideKeyboard(pq_Section_E1.this);
                if (qe1_1_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e1_1_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e1_1_ID);
                        e1_1 = radioButton.getTag().toString();
                        if (rg_e1_2_ID > 0) {
                            RadioButton radioButton2 = findViewById(rg_e1_2_ID);
                            e1_2 = radioButton2.getTag().toString();
                            if (rg_e1_3_ID > 0) {
                                RadioButton radioButton3 = findViewById(rg_e1_3_ID);
                                e1_3 = radioButton3.getTag().toString();
                                if (rg_e1_4_ID > 0) {
                                    RadioButton radioButton4 = findViewById(rg_e1_4_ID);
                                    e1_4 = radioButton4.getTag().toString();
                                    if (rg_e1_5_ID > 0) {
                                        RadioButton radioButton5 = findViewById(rg_e1_5_ID);
                                        e1_5 = radioButton5.getTag().toString();
                                        if (rg_e1_6_ID > 0) {
                                            RadioButton radioButton6 = findViewById(rg_e1_6_ID);
                                            e1_6 = radioButton6.getTag().toString();
                                            if (rg_e1_7_ID > 0) {
                                                RadioButton radioButton7 = findViewById(rg_e1_7_ID);
                                                e1_7 = radioButton7.getTag().toString();
                                                if (rg_e1_8_ID > 0) {
                                                    RadioButton radioButton8 = findViewById(rg_e1_8_ID);
                                                    e1_8 = radioButton8.getTag().toString();
                                                    if (rg_e1_9_ID > 0) {
                                                        RadioButton radioButton9 = findViewById(rg_e1_9_ID);
                                                        e1_9 = radioButton9.getTag().toString();
                                                        if (rg_e1_10_ID > 0) {
                                                            RadioButton radioButton10 = findViewById(rg_e1_10_ID);
                                                            e1_10 = radioButton10.getTag().toString();
                                                            if (rg_e1_11_ID > 0) {
                                                                RadioButton radioButton11 = findViewById(rg_e1_11_ID);
                                                                e1_11 = radioButton11.getTag().toString();
                                                                if (rg_e1_12_ID > 0) {
                                                                    RadioButton radioButton12 = findViewById(rg_e1_12_ID);
                                                                    e1_12 = radioButton12.getTag().toString();
                                                                    if (rg_e1_13_ID > 0) {
                                                                        RadioButton radioButton13 = findViewById(rg_e1_13_ID);
                                                                        e1_13 = radioButton13.getTag().toString();
                                                                        if (rg_e1_14_ID > 0) {
                                                                            RadioButton radioButton14 = findViewById(rg_e1_14_ID);
                                                                            e1_14 = radioButton14.getTag().toString();
                                                                            if (rg_e1_15_ID > 0) {
                                                                                RadioButton radioButton15 = findViewById(rg_e1_15_ID);
                                                                                e1_15 = radioButton15.getTag().toString();
                                                                                if (rg_e1_16_ID > 0) {
                                                                                    RadioButton radioButton16 = findViewById(rg_e1_16_ID);
                                                                                    e1_16 = radioButton16.getTag().toString();
                                                                                    refused = "No";
                                                                                    SaveData();
                                                                                    Intent intent = new Intent(pq_Section_E1.this, pq_Section_E2.class);
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
                                                                                    toastMessage("Please Select Question 16");
                                                                                }
                                                                            } else {
                                                                                toastMessage("Please Select Question 15");
                                                                            }
                                                                        } else {
                                                                            toastMessage("Please Select Question 14");
                                                                        }
                                                                    } else {
                                                                        toastMessage("Please Select Question 13");
                                                                    }
                                                                } else {
                                                                    toastMessage("Please Select Question 12");
                                                                }
                                                            } else {
                                                                toastMessage("Please Select Question 11");
                                                            }
                                                        } else {
                                                            toastMessage("Please Select Question 10");
                                                        }
                                                    } else {
                                                        toastMessage("Please Select Question 9");
                                                    }
                                                } else {
                                                    toastMessage("Please Select Question 8");
                                                }
                                            } else {
                                                toastMessage("Please Select Question 7");
                                            }
                                        } else {
                                            toastMessage("Please Select Question 6");
                                        }
                                    } else {
                                        toastMessage("Please Select Question 5");
                                    }
                                } else {
                                    toastMessage("Please Select Question 4");
                                }
                            } else {
                                toastMessage("Please Select Question 3");
                            }
                        } else {
                            toastMessage("Please Select Question 2");
                        }
                    } else {
                        toastMessage("Please Select Question 1");
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

                Intent intent = new Intent(pq_Section_E1.this, AddReportActivity.class);
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

        Intent returnIntent = new Intent();
        returnIntent.putExtra("isDataUpdated", false);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        super.onBackPressed();
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
        if (ActivityCompat.checkSelfPermission(pq_Section_E1.this,
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
            ShowDialMessage(pq_Section_E1.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
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
            databaseAccess.savepq_Section_E1_Data(emp_id,
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
                    refused,
                    e1_1,
                    e1_2,
                    e1_3,
                    e1_4,
                    e1_5,
                    e1_6,
                    e1_7,
                    e1_8,
                    e1_9,
                    e1_10,
                    e1_11,
                    e1_12,
                    e1_13,
                    e1_14,
                    e1_15,
                    e1_16);
//
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getpq_section_e_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                refused = cursor.getString(cursor.getColumnIndex("refused"));
                e1_1 = cursor.getString(cursor.getColumnIndex("e1_1"));
                e1_2 = cursor.getString(cursor.getColumnIndex("e1_2"));
                e1_3 = cursor.getString(cursor.getColumnIndex("e1_3"));
                e1_4 = cursor.getString(cursor.getColumnIndex("e1_4"));
                e1_5 = cursor.getString(cursor.getColumnIndex("e1_5"));
                e1_6 = cursor.getString(cursor.getColumnIndex("e1_6"));
                e1_7 = cursor.getString(cursor.getColumnIndex("e1_7"));
                e1_8 = cursor.getString(cursor.getColumnIndex("e1_8"));
                e1_9 = cursor.getString(cursor.getColumnIndex("e1_9"));
                e1_10 = cursor.getString(cursor.getColumnIndex("e1_10"));
                e1_11 = cursor.getString(cursor.getColumnIndex("e1_11"));
                e1_12 = cursor.getString(cursor.getColumnIndex("e1_12"));
                e1_13 = cursor.getString(cursor.getColumnIndex("e1_13"));
                e1_14 = cursor.getString(cursor.getColumnIndex("e1_14"));
                e1_15 = cursor.getString(cursor.getColumnIndex("e1_15"));
                e1_16 = cursor.getString(cursor.getColumnIndex("e1_16"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            ////////////////////////////////////// RadioGroup //////////////////////
            setradiogroup(e1_1, rg_e1_1);
            setradiogroup(e1_2, rg_e1_2);
            setradiogroup(e1_3, rg_e1_3);
            setradiogroup(e1_4, rg_e1_4);
            setradiogroup(e1_5, rg_e1_5);
            setradiogroup(e1_6, rg_e1_6);
            setradiogroup(e1_7, rg_e1_7);
            setradiogroup(e1_8, rg_e1_8);
            setradiogroup(e1_9, rg_e1_9);
            setradiogroup(e1_10, rg_e1_10);
            setradiogroup(e1_11, rg_e1_11);
            setradiogroup(e1_12, rg_e1_12);
            setradiogroup(e1_13, rg_e1_13);
            setradiogroup(e1_14, rg_e1_14);
            setradiogroup(e1_15, rg_e1_15);
            setradiogroup(e1_16, rg_e1_16);

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

    void ShowRefusedMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.delete_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnok = dialogView.findViewById(R.id.btnRconsUser);
        Button btnenum = dialogView.findViewById(R.id.btnenum);
        btnenum.setVisibility(View.GONE);
        btnok.setText("Yes");
        btnCancel.setText("No");
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                refused = "Yes";
                SaveData();
                Intent intent = new Intent(pq_Section_E1.this, pq_Section_E2.class);
                intent.putExtra("emp_id", emp_id);
                intent.putExtra("order_id", order_id);
                intent.putExtra("id", id);
                intent.putExtra("farmer_cellphone", phone_number);
                intent.putExtra("school_code", school_code);
                intent.putExtra("student_id", student_id);
                intent.putExtra("student_name", student_name);
                intent.putExtra("school_name", school_name);
                startActivityForResult(intent, 88);

            }
        });
        btnenum.setOnClickListener(new View.OnClickListener() {
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