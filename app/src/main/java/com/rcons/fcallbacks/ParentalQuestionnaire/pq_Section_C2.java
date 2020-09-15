package com.rcons.fcallbacks.ParentalQuestionnaire;

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
import android.provider.Settings;
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

import com.rcons.fcallbacks.BuildConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Section_B_Recycler.Section_C2Adapter;
import com.rcons.fcallbacks.Section_B_Recycler.Section_C2Model;
import com.rcons.fcallbacks.Section_B_Recycler.onEachIDClick;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideKeyboard;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setEditText;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class pq_Section_C2 extends AppCompatActivity implements onEachIDClick {


    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

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

    @BindView(R.id.c4d_layout)
    LinearLayout c4d_layout;


    @BindView(R.id.tv_c2_id)
    TextView tv_c2_id;

    @BindView(R.id.section_c2_question_c2)
    TextView section_c2_question_c2;

    @BindView(R.id.edt_c2_a)
    EditText edt_c2_a;

    @BindView(R.id.edt_c2_d)
    EditText edt_c2_d;

    @BindView(R.id.rg_qc2b)
    RadioGroup rg_qc2b;

    @BindView(R.id.rg_qc2c)
    RadioGroup rg_qc2c;

    @BindView(R.id.rg_qc2e)
    RadioGroup rg_qc2e;

    @BindView(R.id.btn_Reset)
    Button btn_Reset;

    @BindView(R.id.btn_save)
    Button btn_save;

    @BindView(R.id.recyler_view)
    RecyclerView recyler_view;


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
    String c2_id = "";
    String c2_a = "";
    String c2_b = "";
    String c2_c = "";
    String c2_d = "";
    String c2_e = "";

    String phone_number = "";
    String id = "";

    String school_name = "";
    String student_name = "";

    LinearLayoutManager manager;
    ArrayList<Section_C2Model> section_c2ModelArrayList;
    Section_C2Adapter section_c2Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_c2);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_C2.this);
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

        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;

        section_c2_question_c2.setText("برائے مہربانی اس گھر میں رہنے والے تمام بچوں جن کی عمر 0سے  17  سال  ہے " + student_name + "کے علاوہ کی لسٹ بنوائیں اور تعلیمی معلومات بتائیں۔");

        SetRecylerData();
        readIDscount();

        rg_qc2c.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_qc2c.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_qc2c_2) {
                    c4d_layout.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_C2.this);
                    edt_c2_d.setText("");
                    c2_d = "";
                } else {
                    c4d_layout.setVisibility(View.VISIBLE);
                    edt_c2_d.requestFocus();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2_a = edt_c2_a.getText().toString();
                if (StringUtils.isEmpty(c2_a)) {
                    Intent intent = new Intent(pq_Section_C2.this, pq_Section_C3.class);
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
                    toastMessage("Please Enter Required Data");
                }

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2_a = edt_c2_a.getText().toString();
                Integer age = Integer.parseInt("0" + c2_a);
                int rg_qc2b_ID = rg_qc2b.getCheckedRadioButtonId();
                int rg_qc2c_ID = rg_qc2c.getCheckedRadioButtonId();
                int rg_qc2e_ID = rg_qc2e.getCheckedRadioButtonId();

                if (age < 18) {
                    if (rg_qc2b_ID == R.id.rbtn_qc2b_1 || rg_qc2b_ID == R.id.rbtn_qc2b_2) {
                        if (rg_qc2c_ID == R.id.rbtn_qc2c_0 || rg_qc2c_ID == R.id.rbtn_qc2c_1 || rg_qc2c_ID == R.id.rbtn_qc2c_2) {
                            if (rg_qc2c_ID == R.id.rbtn_qc2c_1 || rg_qc2c_ID == R.id.rbtn_qc2c_0) {
                                c2_d = edt_c2_d.getText().toString();
                                if (!StringUtils.isEmpty(c2_d)) {
                                    if (c2_d.equalsIgnoreCase("98")) {
                                        if (rg_qc2e_ID == R.id.rbtn_qc2e_0 || rg_qc2e_ID == R.id.rbtn_qc2e_1) {
                                            RadioButton radioButton_c3b = findViewById(rg_qc2b_ID);
                                            c2_b = radioButton_c3b.getTag().toString();
                                            RadioButton radioButton_c3c = findViewById(rg_qc2c_ID);
                                            c2_c = radioButton_c3c.getTag().toString();
                                            RadioButton radioButton_c3e = findViewById(rg_qc2e_ID);
                                            c2_e = radioButton_c3e.getTag().toString();
                                            SaveData();
                                            Reset();
                                            SetRecylerData();
                                            readIDscount();
                                        } else {
                                            toastMessage("Please Select Option");
                                        }
                                    } else {
                                        Integer value_c2_d = Integer.parseInt(c2_d);
                                        if (value_c2_d < 17) {
                                            if (rg_qc2e_ID == R.id.rbtn_qc2e_0 || rg_qc2e_ID == R.id.rbtn_qc2e_1) {
                                                RadioButton radioButton_c3b = findViewById(rg_qc2b_ID);
                                                c2_b = radioButton_c3b.getTag().toString();
                                                RadioButton radioButton_c3c = findViewById(rg_qc2c_ID);
                                                c2_c = radioButton_c3c.getTag().toString();
                                                RadioButton radioButton_c3e = findViewById(rg_qc2e_ID);
                                                c2_e = radioButton_c3e.getTag().toString();
                                                SaveData();
                                                Reset();
                                                SetRecylerData();
                                                readIDscount();
                                            } else {
                                                toastMessage("Please Select Option");
                                            }
                                        } else {
                                            toastMessage("Grade Must be less than 16");
                                        }
                                    }
                                } else {
                                    toastMessage("Please Enter Grade");
                                }
                            } else {
                                if (rg_qc2e_ID == R.id.rbtn_qc2e_0 || rg_qc2e_ID == R.id.rbtn_qc2e_1) {
                                    RadioButton radioButton_c3b = findViewById(rg_qc2b_ID);
                                    c2_b = radioButton_c3b.getTag().toString();
                                    RadioButton radioButton_c3c = findViewById(rg_qc2c_ID);
                                    c2_c = radioButton_c3c.getTag().toString();
                                    RadioButton radioButton_c3e = findViewById(rg_qc2e_ID);
                                    c2_e = radioButton_c3e.getTag().toString();
                                    SaveData();
                                    Reset();
                                    SetRecylerData();
                                    readIDscount();
                                } else {
                                    toastMessage("Please Select Option");
                                }
                            }
                        } else {
                            toastMessage("Please Select School Registration option");
                        }
                    } else {
                        toastMessage("Please Select Gender");
                    }
                } else {
                    toastMessage("Age Must be less than 17");
                }
            }
        });
        btn_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
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

                Intent intent = new Intent(pq_Section_C2.this, AddReportActivity.class);
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
        if (ActivityCompat.checkSelfPermission(pq_Section_C2.this,
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
            ShowDialMessage(pq_Section_C2.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
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

    @Override
    public void onEachIDClick(Section_C2Model section_c2Model) {
        if (section_c2Model != null) {
            String ID = section_c2Model.getId();
            try {
                readFromDataBase(ID);
                LoadPreviousData();
            } catch (Exception e) {
                toastMessage(e.getMessage());
            }
        }
    }


    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            databaseAccess.savepq_Section_C2_Data(emp_id,
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
                    c2_id,
                    c2_a,
                    c2_b,
                    c2_c,
                    c2_d,
                    c2_e);
            MubLog.cpnsoleLog("c2_id " + c2_id);
//
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase(String child_id) {
        try {

            Cursor cursor = databaseAccess.getpq_section_c2_Data(school_code, student_id, child_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                c2_id = cursor.getString(cursor.getColumnIndex("c2_id"));
                c2_a = cursor.getString(cursor.getColumnIndex("c2_a"));
                c2_b = cursor.getString(cursor.getColumnIndex("c2_b"));
                c2_c = cursor.getString(cursor.getColumnIndex("c2_c"));
                c2_d = cursor.getString(cursor.getColumnIndex("c2_d"));
                c2_e = cursor.getString(cursor.getColumnIndex("c2_e"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        try {

            tv_c2_id.setText("Child ID : " + c2_id);
            setradiogroup(c2_b, rg_qc2b);
            setradiogroup(c2_c, rg_qc2c);
            setradiogroup(c2_e, rg_qc2e);

            setEditText(edt_c2_a, c2_a);


            if (!StringUtils.isEmpty(c2_d)) {
                setEditText(edt_c2_d, c2_d);
                c4d_layout.setVisibility(View.VISIBLE);
            } else {
                c4d_layout.setVisibility(View.GONE);
            }


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

    void readIDscount() {
        try {

            Cursor cursor = databaseAccess.getpq_section_c2_recyler(school_code, student_id);
            Integer count = 0;
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                count = cursor.getCount() + 1;

            } else {
                count = 1;
            }
            c2_id = count.toString();
            tv_c2_id.setText("Child ID : " + c2_id);
            MubLog.cpnsoleLog("count = " + count.toString());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void SetRecylerData() {
        manager = new LinearLayoutManager(pq_Section_C2.this, RecyclerView.VERTICAL, false);
        recyler_view.setLayoutManager(manager);
        section_c2ModelArrayList = new ArrayList<>();

        Cursor cursor = databaseAccess.getpq_section_c2_recyler(school_code, student_id);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    Section_C2Model section_C2Model = new Section_C2Model();

                    c2_id = cursor.getString(cursor.getColumnIndex("c2_id"));
                    c2_a = cursor.getString(cursor.getColumnIndex("c2_a"));
                    c2_b = cursor.getString(cursor.getColumnIndex("c2_b"));
                    c2_c = cursor.getString(cursor.getColumnIndex("c2_c"));
                    c2_d = cursor.getString(cursor.getColumnIndex("c2_d"));
                    c2_e = cursor.getString(cursor.getColumnIndex("c2_e"));

                    section_C2Model.setId(c2_id);
                    section_C2Model.setAge(c2_a);
                    section_C2Model.setGender(c2_b);
                    section_c2ModelArrayList.add(section_C2Model);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        if (section_c2ModelArrayList != null && section_c2ModelArrayList.size() > 0) {
            section_c2Adapter = new Section_C2Adapter(section_c2ModelArrayList, pq_Section_C2.this);
            recyler_view.setAdapter(section_c2Adapter);
            recyler_view.setVisibility(View.VISIBLE);
            btn_Reset.setVisibility(View.VISIBLE);
        } else {
            btn_Reset.setVisibility(View.GONE);
            recyler_view.setVisibility(View.GONE);
        }


    }

    void Reset() {
        c2_id = "";
        c2_a = "";
        c2_b = "";
        c2_c = "";
        c2_d = "";
        c2_e = "";
        edt_c2_a.setText("");
        edt_c2_d.setText("");
        rg_qc2b.clearCheck();
        rg_qc2c.clearCheck();
        rg_qc2e.clearCheck();
        c4d_layout.setVisibility(View.GONE);
        readIDscount();
        SetRecylerData();

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