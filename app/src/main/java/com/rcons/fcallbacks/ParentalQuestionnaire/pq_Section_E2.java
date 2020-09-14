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
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.BuildConfig;
import com.mubashar.dateandtime.MubDateAndTime;
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
import static com.rcons.fcallbacks.Utilties.RConsUtils.setEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setotherEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class pq_Section_E2 extends AppCompatActivity {


    @BindView(R.id.qe2_layout)
    LinearLayout qe2_layout;
    @BindView(R.id.qe3_layout)
    LinearLayout qe3_layout;
    @BindView(R.id.qe4_layout)
    LinearLayout qe4_layout;
    @BindView(R.id.qe5_layout)
    LinearLayout qe5_layout;
    @BindView(R.id.qe6_layout)
    LinearLayout qe6_layout;
    @BindView(R.id.qe9_layout)
    LinearLayout qe9_layout;
    @BindView(R.id.qe10_layout)
    LinearLayout qe10_layout;


    @BindView(R.id.qe7_layout)
    LinearLayout qe7_layout;
    @BindView(R.id.qe8_layout)
    LinearLayout qe8_layout;


    @BindView(R.id.rg_e2)
    RadioGroup rg_e2;
    @BindView(R.id.rg_e4)
    RadioGroup rg_e4;
    @BindView(R.id.rg_e5)
    RadioGroup rg_e5;
    @BindView(R.id.rg_e6)
    RadioGroup rg_e6;
    @BindView(R.id.rg_e9)
    RadioGroup rg_e9;
    @BindView(R.id.rg_e10)
    RadioGroup rg_e10;

    @BindView(R.id.rg_e7)
    RadioGroup rg_e7;

    @BindView(R.id.rg_e8)
    RadioGroup rg_e8;


    @BindView(R.id.rbtn_e6_97)
    RadioButton rbtn_e6_97;

    @BindView(R.id.edt_e3)
    EditText edt_e3;

    @BindView(R.id.edt_e4_other)
    EditText edt_e4_other;

    @BindView(R.id.edt_e6_other)
    EditText edt_e6_other;

    @BindView(R.id.edt_e8_other)
    EditText edt_e8_other;

    @BindView(R.id.edt_e9_other)
    EditText edt_e9_other;

    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

    @BindView(R.id.btn_message)
    Button btn_message;

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
    String e2 = "";
    String e3 = "";
    String e4 = "";
    String e4_other = "";
    String e5 = "";
    String e6 = "";
    String e6_other = "";
    String e6_message = "";
    String e7 = "";
    String e8 = "";
    String e8_other = "";
    String e9 = "";
    String e9_other = "";
    String e10 = "";

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_e2);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_E2.this);
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

        LoadPreviousData();
        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;


        rg_e6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_e6.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_e6_4) {
                    edt_e6_other.setVisibility(View.VISIBLE);
                    edt_e6_other.requestFocus();
                } else {
                    edt_e6_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_E2.this);
                    edt_e6_other.setText("");
                    e6_other = "";
                }
            }
        });
        rg_e4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_e4.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_e4_99) {
                    edt_e4_other.setVisibility(View.VISIBLE);
                    edt_e4_other.requestFocus();
                } else {
                    edt_e4_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_E2.this);
                    edt_e4_other.setText("");
                    e4_other = "";
                }
            }
        });

        rg_e8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_e8.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_e8_99) {
                    edt_e8_other.setVisibility(View.VISIBLE);
                    edt_e8_other.requestFocus();
                } else {
                    edt_e8_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_E2.this);
                    edt_e8_other.setText("");
                    e8_other = "";
                }
            }
        });
        rg_e9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_e9.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_e9_99) {
                    edt_e9_other.setVisibility(View.VISIBLE);
                    edt_e9_other.requestFocus();
                } else {
                    edt_e9_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_E2.this);
                    edt_e9_other.setText("");
                    e9_other = "";
                }
            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String send_msg = "یہ  ایس ایم ایس  سروس کا ٹیسٹ میسج ہے۔  جو سکول کی پڑھائی کی معلومات مہیا  کرتا ہے۔";
                try {
                    RConsUtils.sendSMS(pq_Section_E2.this, 0 + phone_number, send_msg);
                } catch (Exception e) {
                    Toast.makeText(pq_Section_E2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_e2_ID = rg_e2.getCheckedRadioButtonId();
                int rg_e4_ID = rg_e4.getCheckedRadioButtonId();
                int rg_e5_ID = rg_e5.getCheckedRadioButtonId();
                int rg_e6_ID = rg_e6.getCheckedRadioButtonId();
                int rg_e7_ID = rg_e7.getCheckedRadioButtonId();
                int rg_e8_ID = rg_e8.getCheckedRadioButtonId();
                int rg_e9_ID = rg_e9.getCheckedRadioButtonId();
                int rg_e10_ID = rg_e10.getCheckedRadioButtonId();


                RConsUtils.hideKeyboard(pq_Section_E2.this);
                SaveData();
                if (qe2_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e2_ID);
                        e2 = radioButton.getTag().toString();
                        hideView(qe2_layout, qe3_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe3_layout.getVisibility() == View.VISIBLE) {
                    e3 = edt_e3.getText().toString();
                    if (!StringUtils.isEmpty(e3)) {
                        Integer value_e3 = Integer.parseInt(e3);
                        if (value_e3 < 16) {
                            hideView(qe3_layout, qe4_layout);
                        } else {
                            toastMessage("Must be less than 16");
                        }
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (qe4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e4_ID);
                        e4 = radioButton.getTag().toString();
                        if (e4.equalsIgnoreCase("99")) {
                            e4_other = edt_e4_other.getText().toString();
                            if (!StringUtils.isEmpty(e4_other)) {
                                hideView(qe4_layout, qe5_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            e4_other = "";
                            edt_e4_other.setText("");
                            hideView(qe4_layout, qe5_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe5_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e5_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e5_ID);
                        e5 = radioButton.getTag().toString();
                        hideView(qe5_layout, qe6_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe6_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e6_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e6_ID);
                        e6 = radioButton.getTag().toString();
                        if (e6.equalsIgnoreCase("4")) {
                            e6_other = edt_e6_other.getText().toString();
                            if (!StringUtils.isEmpty(e6_other)) {
                                hideView(qe6_layout, qe7_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            e6_other = "";
                            edt_e6_other.setText("");
                            hideView(qe6_layout, qe7_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe7_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e7_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e7_ID);
                        e7 = radioButton.getTag().toString();
                        hideView(qe7_layout, qe8_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe8_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e8_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e8_ID);
                        e8 = radioButton.getTag().toString();
                        if (e8.equalsIgnoreCase("99")) {
                            e8_other = edt_e8_other.getText().toString();
                            if (!StringUtils.isEmpty(e8_other)) {
                                hideView(qe8_layout, qe9_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            e8_other = "";
                            edt_e8_other.setText("");
                            hideView(qe8_layout, qe9_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe9_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e9_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e9_ID);
                        e9 = radioButton.getTag().toString();
                        if (e9.equalsIgnoreCase("99")) {
                            e9_other = edt_e9_other.getText().toString();
                            if (!StringUtils.isEmpty(e9_other)) {
                                hideView(qe9_layout, qe10_layout);
                            } else {
                                toastMessage("Please Specify other");
                            }
                        } else {
                            e9_other = "";
                            edt_e9_other.setText("");
                            hideView(qe9_layout, qe10_layout);
                        }
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (qe10_layout.getVisibility() == View.VISIBLE) {
                    if (rg_e10_ID > 0) {
                        RadioButton radioButton = findViewById(rg_e10_ID);
                        e10 = radioButton.getTag().toString();
                        Intent intent = new Intent(pq_Section_E2.this, AddReportActivity.class);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", phone_number);
                        intent.putExtra("school_code", school_code);
                        intent.putExtra("student_id", student_id);
                        startActivityForResult(intent, 88);
                    } else {
                        toastMessage("Please Select Option");
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

                Intent intent = new Intent(pq_Section_E2.this, AddReportActivity.class);
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
        if (qe10_layout.getVisibility() == View.VISIBLE) {
            hideView(qe10_layout, qe9_layout);
        } else if (qe9_layout.getVisibility() == View.VISIBLE) {
            hideView(qe9_layout, qe8_layout);
        } else if (qe8_layout.getVisibility() == View.VISIBLE) {
            hideView(qe8_layout, qe7_layout);
        } else if (qe7_layout.getVisibility() == View.VISIBLE) {
            hideView(qe7_layout, qe6_layout);
        } else if (qe6_layout.getVisibility() == View.VISIBLE) {
            hideView(qe6_layout, qe5_layout);
        } else if (qe5_layout.getVisibility() == View.VISIBLE) {
            hideView(qe5_layout, qe4_layout);
        } else if (qe4_layout.getVisibility() == View.VISIBLE) {
            hideView(qe4_layout, qe3_layout);
        } else if (qe3_layout.getVisibility() == View.VISIBLE) {
            hideView(qe3_layout, qe2_layout);
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
        if (ActivityCompat.checkSelfPermission(pq_Section_E2.this,
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
             //   phoneNumber = "880" + phoneNumber;
            } else {
                phoneNumber = "770" + phoneNumber;
            }
        } else {

        }
        if (network.equalsIgnoreCase("Telenor")) {
            ShowDialMessage(pq_Section_E2.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
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

            String end_year = RConsUtils.getcurrentTime(0);
            String end_month = RConsUtils.getcurrentTime(1);
            String end_day = RConsUtils.getcurrentTime(2);

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            databaseAccess.savepq_Section_E2_Data(emp_id,
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
                    e2,
                    e3,
                    e4,
                    e4_other,
                    e5,
                    e6,
                    e6_other,
                    e7,
                    e8,
                    e8_other,
                    e9,
                    e9_other,
                    e10,
                    end_day,
                    end_month,
                    end_year
            );

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getpq_section_e_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                e2 = cursor.getString(cursor.getColumnIndex("e2"));
                e3 = cursor.getString(cursor.getColumnIndex("e3"));
                e4 = cursor.getString(cursor.getColumnIndex("e4"));
                e4_other = cursor.getString(cursor.getColumnIndex("e4_other"));
                e5 = cursor.getString(cursor.getColumnIndex("e5"));
                e6 = cursor.getString(cursor.getColumnIndex("e6"));
                e6_other = cursor.getString(cursor.getColumnIndex("e6_other"));
                e7 = cursor.getString(cursor.getColumnIndex("e7"));
                e8 = cursor.getString(cursor.getColumnIndex("e8"));
                e8_other = cursor.getString(cursor.getColumnIndex("e8_other"));
                e9 = cursor.getString(cursor.getColumnIndex("e9"));
                e9_other = cursor.getString(cursor.getColumnIndex("e9_other"));
                e10 = cursor.getString(cursor.getColumnIndex("e10"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            ////////////////////////////////////// RadioGroup //////////////////////
            setradiogroup(e2, rg_e2);
            setradiogroup(e2, rg_e2);
            setradiogroup(e4, rg_e4);
            setradiogroup(e5, rg_e5);
            setradiogroup(e6, rg_e6);
            setradiogroup(e7, rg_e7);
            setradiogroup(e8, rg_e8);
            setradiogroup(e9, rg_e9);
            setradiogroup(e10, rg_e10);
            ////////////////////////////////////// Edit Text //////////////////////
            setEditText(edt_e3, e3);
            ////////////////////////////////////// OtherEditText //////////////////////
            setotherEditText(edt_e4_other, e4_other);
            setotherEditText(edt_e8_other, e8_other);
            setotherEditText(edt_e9_other, e9_other);
            setotherEditText(edt_e6_other, e6_other);


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