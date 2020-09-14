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
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.amitshekhar.BuildConfig;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class pq_Section_C3 extends AppCompatActivity {


    @BindView(R.id.qc3a_layout)
    LinearLayout qc3a_layout;
    @BindView(R.id.qc3b_layout)
    LinearLayout qc3b_layout;
    @BindView(R.id.qc3c_layout)
    LinearLayout qc3c_layout;
    @BindView(R.id.qc3d_layout)
    LinearLayout qc3d_layout;
    @BindView(R.id.qc3e_layout)
    LinearLayout qc3e_layout;
    @BindView(R.id.qc3f_layout)
    LinearLayout qc3f_layout;
    @BindView(R.id.qc3g_layout)
    LinearLayout qc3g_layout;
    @BindView(R.id.qc3h_layout)
    LinearLayout qc3h_layout;
    @BindView(R.id.qc3i_layout)
    LinearLayout qc3i_layout;
    @BindView(R.id.qc3j_layout)
    LinearLayout qc3j_layout;
    @BindView(R.id.qc3k_layout)
    LinearLayout qc3k_layout;
    @BindView(R.id.qc3l_layout)
    LinearLayout qc3l_layout;
    @BindView(R.id.qc3m_layout)
    LinearLayout qc3m_layout;
    @BindView(R.id.qc3n_layout)
    LinearLayout qc3n_layout;
    @BindView(R.id.qc3o_layout)
    LinearLayout qc3o_layout;
    @BindView(R.id.qc4_layout)
    LinearLayout qc4_layout;


    @BindView(R.id.edt_c3a_mm)
    EditText edt_c3a_mm;
    @BindView(R.id.edt_c3a_hh)
    EditText edt_c3a_hh;
    @BindView(R.id.edt_c3b_mm)
    EditText edt_c3b_mm;
    @BindView(R.id.edt_c3b_hh)
    EditText edt_c3b_hh;
    @BindView(R.id.edt_c3c_mm)
    EditText edt_c3c_mm;
    @BindView(R.id.edt_c3c_hh)
    EditText edt_c3c_hh;
    @BindView(R.id.edt_c3d_mm)
    EditText edt_c3d_mm;
    @BindView(R.id.edt_c3d_hh)
    EditText edt_c3d_hh;
    @BindView(R.id.edt_c3e_mm)
    EditText edt_c3e_mm;
    @BindView(R.id.edt_c3e_hh)
    EditText edt_c3e_hh;
    @BindView(R.id.edt_c3f_mm)
    EditText edt_c3f_mm;
    @BindView(R.id.edt_c3f_hh)
    EditText edt_c3f_hh;
    @BindView(R.id.edt_c3g_mm)
    EditText edt_c3g_mm;
    @BindView(R.id.edt_c3g_hh)
    EditText edt_c3g_hh;
    @BindView(R.id.edt_c3h_mm)
    EditText edt_c3h_mm;
    @BindView(R.id.edt_c3h_hh)
    EditText edt_c3h_hh;
    @BindView(R.id.edt_c3i_mm)
    EditText edt_c3i_mm;
    @BindView(R.id.edt_c3i_hh)
    EditText edt_c3i_hh;
    @BindView(R.id.edt_c3j_mm)
    EditText edt_c3j_mm;
    @BindView(R.id.edt_c3j_hh)
    EditText edt_c3j_hh;
    @BindView(R.id.edt_c3k_mm)
    EditText edt_c3k_mm;
    @BindView(R.id.edt_c3k_hh)
    EditText edt_c3k_hh;
    @BindView(R.id.edt_c3l_mm)
    EditText edt_c3l_mm;
    @BindView(R.id.edt_c3l_hh)
    EditText edt_c3l_hh;
    @BindView(R.id.edt_c3m_mm)
    EditText edt_c3m_mm;
    @BindView(R.id.edt_c3m_hh)
    EditText edt_c3m_hh;
    @BindView(R.id.edt_c3n_mm)
    EditText edt_c3n_mm;
    @BindView(R.id.edt_c3n_hh)
    EditText edt_c3n_hh;
    @BindView(R.id.edt_c3o_mm)
    EditText edt_c3o_mm;
    @BindView(R.id.edt_c3o_hh)
    EditText edt_c3o_hh;
    @BindView(R.id.edt_c4_other)
    EditText edt_c4_other;


    @BindView(R.id.rg_c4)
    RadioGroup rg_c4;


    @BindView(R.id.txt_remaining_time)
    TextView txt_remaining_time;

    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;

    @BindView(R.id.txt_section_c3_script_2)
    TextView txt_section_c3_script_2;

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
    String c3a_hh = "";
    String c3a_mm = "";
    String c3b_hh = "";
    String c3b_mm = "";
    String c3c_hh = "";
    String c3c_mm = "";
    String c3d_hh = "";
    String c3d_mm = "";
    String c3e_hh = "";
    String c3e_mm = "";
    String c3f_hh = "";
    String c3f_mm = "";
    String c3g_hh = "";
    String c3g_mm = "";
    String c3h_hh = "";
    String c3h_mm = "";
    String c3i_hh = "";
    String c3i_mm = "";
    String c3j_hh = "";
    String c3j_mm = "";
    String c3k_hh = "";
    String c3k_mm = "";
    String c3l_hh = "";
    String c3l_mm = "";
    String c3m_hh = "";
    String c3m_mm = "";
    String c3n_hh = "";
    String c3n_mm = "";
    String c3o_hh = "";
    String c3o_mm = "";
    String c4 = "";
    String c4_other = "";

    String refused = "";


    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_c3);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_C3.this);
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

        txt_section_c3_script_2.setText("پاکستان میں سکول کچھ مہینوں سے بند ہیں  ہم جاننا چاہتے ہیں کہ " + student_name + " ایک عام دن میں  اپنا وقت کیسے گزارتی / گزارتا ہے ");

        txt_School_Code.setText("School Code : " + school_code);
        txt_Student_id.setText("Student Id : " + student_id);

        LoadPreviousData();
        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;

        RemainingTime();
        ChangeText(edt_c3a_hh);
        ChangeText(edt_c3a_mm);

        ChangeText(edt_c3b_hh);
        ChangeText(edt_c3b_mm);

        ChangeText(edt_c3c_hh);
        ChangeText(edt_c3c_mm);

        ChangeText(edt_c3d_hh);
        ChangeText(edt_c3d_mm);

        ChangeText(edt_c3e_hh);
        ChangeText(edt_c3e_mm);

        ChangeText(edt_c3f_hh);
        ChangeText(edt_c3f_mm);

        ChangeText(edt_c3g_hh);
        ChangeText(edt_c3g_mm);

        ChangeText(edt_c3h_hh);
        ChangeText(edt_c3h_mm);

        ChangeText(edt_c3i_hh);
        ChangeText(edt_c3i_mm);

        ChangeText(edt_c3j_hh);
        ChangeText(edt_c3j_mm);

        ChangeText(edt_c3k_hh);
        ChangeText(edt_c3k_mm);

        ChangeText(edt_c3l_hh);
        ChangeText(edt_c3l_mm);

        ChangeText(edt_c3m_hh);
        ChangeText(edt_c3m_mm);

        ChangeText(edt_c3n_hh);
        ChangeText(edt_c3n_mm);

        ChangeText(edt_c3o_hh);
        ChangeText(edt_c3o_mm);

        edt_c3a_hh.requestFocus();
        rg_c4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int RGroup_ID = rg_c4.getCheckedRadioButtonId();
                if (RGroup_ID == R.id.rbtn_c4_99) {
                    edt_c4_other.setVisibility(View.VISIBLE);
                    edt_c4_other.requestFocus();
                } else {
                    edt_c4_other.setVisibility(View.GONE);
                    hideKeyboard(pq_Section_C3.this);
                    edt_c4_other.setText("");
                    c4_other = "";
                }
            }
        });
        btn_Refused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowRefusedMessage(pq_Section_C3.this, "Alert", "Are you sure want to skip this section ?");

            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_c4_ID = rg_c4.getCheckedRadioButtonId();
                refused = "No";
                SaveData();
//                RemainingTime();
                if (qc3a_layout.getVisibility() == View.VISIBLE) {
                    c3a_hh = edt_c3a_hh.getText().toString();
                    c3a_mm = edt_c3a_mm.getText().toString();
                    if (!StringUtils.isEmpty(c3a_hh) && !StringUtils.isEmpty(c3a_mm)) {
                        Integer value_c3a_hh = Integer.parseInt(c3a_hh);
                        Integer value_c3a_mm = Integer.parseInt(c3a_mm);
                        if (value_c3a_hh > 24) {
                            toastMessage("Hours Must be in 4-24.");
                        } else if (value_c3a_hh < 4) {
                            toastMessage("Hours Must be in 4-24.");
                        } else if ((value_c3a_mm > 59)) {
                            toastMessage("Minute Must be in 0-59.");
                        } else {
                            hideView(qc3a_layout, qc3b_layout);
                            edt_c3b_hh.requestFocus();
                        }
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (qc3b_layout.getVisibility() == View.VISIBLE) {
                    c3b_hh = edt_c3b_hh.getText().toString();
                    c3b_mm = edt_c3b_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3b_hh);
                    Integer value_mm = Integer.parseInt("0" + c3b_mm);
                    if (!StringUtils.isEmpty(c3b_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3b_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3c_hh.requestFocus();
                        hideView(qc3b_layout, qc3c_layout);
                    }
                } else if (qc3c_layout.getVisibility() == View.VISIBLE) {
                    c3c_hh = edt_c3c_hh.getText().toString();
                    c3c_mm = edt_c3c_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3c_hh);
                    Integer value_mm = Integer.parseInt("0" + c3c_mm);
                    if (!StringUtils.isEmpty(c3c_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3c_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3d_hh.requestFocus();
                        hideView(qc3c_layout, qc3d_layout);
                    }
                } else if (qc3d_layout.getVisibility() == View.VISIBLE) {
                    c3d_hh = edt_c3d_hh.getText().toString();
                    c3d_mm = edt_c3d_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3d_hh);
                    Integer value_mm = Integer.parseInt("0" + c3d_mm);
                    if (!StringUtils.isEmpty(c3d_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3d_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3e_hh.requestFocus();
                        hideView(qc3d_layout, qc3e_layout);
                    }
                } else if (qc3e_layout.getVisibility() == View.VISIBLE) {
                    c3e_hh = edt_c3e_hh.getText().toString();
                    c3e_mm = edt_c3e_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3e_hh);
                    Integer value_mm = Integer.parseInt("0" + c3e_mm);
                    if (!StringUtils.isEmpty(c3e_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3e_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3f_hh.requestFocus();
                        hideView(qc3e_layout, qc3f_layout);
                    }
                } else if (qc3f_layout.getVisibility() == View.VISIBLE) {
                    c3f_hh = edt_c3f_hh.getText().toString();
                    c3f_mm = edt_c3f_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3f_hh);
                    Integer value_mm = Integer.parseInt("0" + c3f_mm);
                    if (!StringUtils.isEmpty(c3f_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3f_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3g_hh.requestFocus();
                        hideView(qc3f_layout, qc3g_layout);
                    }
                } else if (qc3g_layout.getVisibility() == View.VISIBLE) {
                    c3g_hh = edt_c3g_hh.getText().toString();
                    c3g_mm = edt_c3g_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3g_hh);
                    Integer value_mm = Integer.parseInt("0" + c3g_mm);
                    if (!StringUtils.isEmpty(c3g_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3g_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3h_hh.requestFocus();
                        hideView(qc3g_layout, qc3h_layout);
                    }
                } else if (qc3h_layout.getVisibility() == View.VISIBLE) {
                    c3h_hh = edt_c3h_hh.getText().toString();
                    c3h_mm = edt_c3h_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3h_hh);
                    Integer value_mm = Integer.parseInt("0" + c3h_mm);
                    if (!StringUtils.isEmpty(c3h_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3h_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        hideView(qc3h_layout, qc3i_layout);
                    }
                } else if (qc3i_layout.getVisibility() == View.VISIBLE) {
                    c3i_hh = edt_c3i_hh.getText().toString();
                    c3i_mm = edt_c3i_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3i_hh);
                    Integer value_mm = Integer.parseInt("0" + c3i_mm);
                    if (!StringUtils.isEmpty(c3i_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3i_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        qc3j_layout.requestFocus();
                        hideView(qc3i_layout, qc3j_layout);
                    }
                } else if (qc3j_layout.getVisibility() == View.VISIBLE) {
                    c3j_hh = edt_c3j_hh.getText().toString();
                    c3j_mm = edt_c3j_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3j_hh);
                    Integer value_mm = Integer.parseInt("0" + c3j_mm);
                    if (!StringUtils.isEmpty(c3j_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3j_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3k_hh.requestFocus();
                        hideView(qc3j_layout, qc3k_layout);
                    }
                } else if (qc3k_layout.getVisibility() == View.VISIBLE) {
                    c3k_hh = edt_c3k_hh.getText().toString();
                    c3k_mm = edt_c3k_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3k_hh);
                    Integer value_mm = Integer.parseInt("0" + c3k_mm);
                    if (!StringUtils.isEmpty(c3k_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3k_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3l_hh.requestFocus();
                        hideView(qc3k_layout, qc3l_layout);
                    }
                } else if (qc3l_layout.getVisibility() == View.VISIBLE) {
                    c3l_hh = edt_c3l_hh.getText().toString();
                    c3l_mm = edt_c3l_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3l_hh);
                    Integer value_mm = Integer.parseInt("0" + c3l_mm);
                    if (!StringUtils.isEmpty(c3l_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3l_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3m_hh.requestFocus();
                        hideView(qc3l_layout, qc3m_layout);
                    }
                } else if (qc3m_layout.getVisibility() == View.VISIBLE) {
                    c3m_hh = edt_c3m_hh.getText().toString();
                    c3m_mm = edt_c3m_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3m_hh);
                    Integer value_mm = Integer.parseInt("0" + c3m_mm);
                    if (!StringUtils.isEmpty(c3m_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3m_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3n_hh.requestFocus();
                        hideView(qc3m_layout, qc3n_layout);
                    }
                } else if (qc3n_layout.getVisibility() == View.VISIBLE) {
                    c3n_hh = edt_c3n_hh.getText().toString();
                    c3n_mm = edt_c3n_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3n_hh);
                    Integer value_mm = Integer.parseInt("0" + c3n_mm);
                    if (!StringUtils.isEmpty(c3n_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3n_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        edt_c3o_hh.requestFocus();
                        hideView(qc3n_layout, qc3o_layout);
                    }
                } else if (qc3o_layout.getVisibility() == View.VISIBLE) {
                    c3o_hh = edt_c3o_hh.getText().toString();
                    c3o_mm = edt_c3o_mm.getText().toString();
                    Integer value_hh = Integer.parseInt("0" + c3o_hh);
                    Integer value_mm = Integer.parseInt("0" + c3o_mm);
                    if (!StringUtils.isEmpty(c3o_hh) && value_hh > 24) {
                        toastMessage("Hours Must be in 0-24.");
                    } else if (!StringUtils.isEmpty(c3o_mm) && value_mm > 59) {
                        toastMessage("Hours Must be in 0-59.");
                    } else {
                        TimeCheck();
                    }
                } else if (qc4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_c4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_c4_ID);
                        c4 = radioButton.getTag().toString();
                        if (c4.equalsIgnoreCase("99")) {
                            c4_other = edt_c4_other.getText().toString();
                            if (!StringUtils.isEmpty(c4_other)) {
                                SaveData();
                                Intent intent = new Intent(pq_Section_C3.this, pq_Section_D.class);
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
                                toastMessage("Please Specify other");
                            }
                        } else {
                            SaveData();
                            Intent intent = new Intent(pq_Section_C3.this, pq_Section_D.class);
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
                    } else {
                        toastMessage("Please Select Option");
                    }
                }
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

                Intent intent = new Intent(pq_Section_C3.this, AddReportActivity.class);
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
        if (qc4_layout.getVisibility() == View.VISIBLE) {
            edt_c3o_hh.requestFocus();
            RConsUtils.hideView(qc4_layout, qc3o_layout);
        } else if (qc3o_layout.getVisibility() == View.VISIBLE) {
            edt_c3n_hh.requestFocus();
            RConsUtils.hideView(qc3o_layout, qc3n_layout);
        } else if (qc3n_layout.getVisibility() == View.VISIBLE) {
            edt_c3m_hh.requestFocus();
            RConsUtils.hideView(qc3n_layout, qc3m_layout);
        } else if (qc3m_layout.getVisibility() == View.VISIBLE) {
            edt_c3l_hh.requestFocus();
            RConsUtils.hideView(qc3m_layout, qc3l_layout);
        } else if (qc3l_layout.getVisibility() == View.VISIBLE) {
            edt_c3k_hh.requestFocus();
            RConsUtils.hideView(qc3l_layout, qc3k_layout);
        } else if (qc3k_layout.getVisibility() == View.VISIBLE) {
            edt_c3j_hh.requestFocus();
            RConsUtils.hideView(qc3k_layout, qc3j_layout);
        } else if (qc3j_layout.getVisibility() == View.VISIBLE) {
            edt_c3i_hh.requestFocus();
            RConsUtils.hideView(qc3j_layout, qc3i_layout);
        } else if (qc3i_layout.getVisibility() == View.VISIBLE) {
            edt_c3h_hh.requestFocus();
            RConsUtils.hideView(qc3i_layout, qc3h_layout);
        } else if (qc3h_layout.getVisibility() == View.VISIBLE) {
            edt_c3g_hh.requestFocus();
            RConsUtils.hideView(qc3h_layout, qc3g_layout);
        } else if (qc3g_layout.getVisibility() == View.VISIBLE) {
            edt_c3f_hh.requestFocus();
            RConsUtils.hideView(qc3g_layout, qc3f_layout);
        } else if (qc3f_layout.getVisibility() == View.VISIBLE) {
            edt_c3e_hh.requestFocus();
            RConsUtils.hideView(qc3f_layout, qc3e_layout);
        } else if (qc3e_layout.getVisibility() == View.VISIBLE) {
            edt_c3d_hh.requestFocus();
            RConsUtils.hideView(qc3e_layout, qc3d_layout);
        } else if (qc3d_layout.getVisibility() == View.VISIBLE) {
            edt_c3c_hh.requestFocus();
            RConsUtils.hideView(qc3d_layout, qc3c_layout);
        } else if (qc3c_layout.getVisibility() == View.VISIBLE) {
            edt_c3b_hh.requestFocus();
            RConsUtils.hideView(qc3c_layout, qc3b_layout);
        } else if (qc3b_layout.getVisibility() == View.VISIBLE) {
            edt_c3a_hh.requestFocus();
            edt_c3a_hh.animate();
            RConsUtils.hideView(qc3b_layout, qc3a_layout);
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
        if (ActivityCompat.checkSelfPermission(pq_Section_C3.this,
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
            ShowDialMessage(pq_Section_C3.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
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


    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            databaseAccess.savepq_Section_C3_Data(emp_id,
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
                    c3a_hh,
                    c3a_mm,
                    c3b_hh,
                    c3b_mm,
                    c3c_hh,
                    c3c_mm,
                    c3d_hh,
                    c3d_mm,
                    c3e_hh,
                    c3e_mm,
                    c3f_hh,
                    c3f_mm,
                    c3g_hh,
                    c3g_mm,
                    c3h_hh,
                    c3h_mm,
                    c3i_hh,
                    c3i_mm,
                    c3j_hh,
                    c3j_mm,
                    c3k_hh,
                    c3k_mm,
                    c3l_hh,
                    c3l_mm,
                    c3m_hh,
                    c3m_mm,
                    c3n_hh,
                    c3n_mm,
                    c3o_hh,
                    c3o_mm,
                    c4,
                    c4_other
            );

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getpq_section_c3_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                c3a_hh = cursor.getString(cursor.getColumnIndex("c3a_hh"));
                c3a_mm = cursor.getString(cursor.getColumnIndex("c3a_mm"));
                c3b_hh = cursor.getString(cursor.getColumnIndex("c3b_hh"));
                c3b_mm = cursor.getString(cursor.getColumnIndex("c3b_mm"));
                c3c_hh = cursor.getString(cursor.getColumnIndex("c3c_hh"));
                c3c_mm = cursor.getString(cursor.getColumnIndex("c3c_mm"));
                c3d_hh = cursor.getString(cursor.getColumnIndex("c3d_hh"));
                c3d_mm = cursor.getString(cursor.getColumnIndex("c3d_mm"));
                c3e_hh = cursor.getString(cursor.getColumnIndex("c3e_hh"));
                c3e_mm = cursor.getString(cursor.getColumnIndex("c3e_mm"));
                c3f_hh = cursor.getString(cursor.getColumnIndex("c3f_hh"));
                c3f_mm = cursor.getString(cursor.getColumnIndex("c3f_mm"));
                c3g_hh = cursor.getString(cursor.getColumnIndex("c3g_hh"));
                c3g_mm = cursor.getString(cursor.getColumnIndex("c3g_mm"));
                c3h_hh = cursor.getString(cursor.getColumnIndex("c3h_hh"));
                c3h_mm = cursor.getString(cursor.getColumnIndex("c3h_mm"));
                c3i_hh = cursor.getString(cursor.getColumnIndex("c3i_hh"));
                c3i_mm = cursor.getString(cursor.getColumnIndex("c3i_mm"));
                c3j_hh = cursor.getString(cursor.getColumnIndex("c3j_hh"));
                c3j_mm = cursor.getString(cursor.getColumnIndex("c3j_mm"));
                c3k_hh = cursor.getString(cursor.getColumnIndex("c3k_hh"));
                c3k_mm = cursor.getString(cursor.getColumnIndex("c3k_mm"));
                c3l_hh = cursor.getString(cursor.getColumnIndex("c3l_hh"));
                c3l_mm = cursor.getString(cursor.getColumnIndex("c3l_mm"));
                c3m_hh = cursor.getString(cursor.getColumnIndex("c3m_hh"));
                c3m_mm = cursor.getString(cursor.getColumnIndex("c3m_mm"));
                c3n_hh = cursor.getString(cursor.getColumnIndex("c3n_hh"));
                c3n_mm = cursor.getString(cursor.getColumnIndex("c3n_mm"));
                c3o_hh = cursor.getString(cursor.getColumnIndex("c3o_hh"));
                c3o_mm = cursor.getString(cursor.getColumnIndex("c3o_mm"));
                c4 = cursor.getString(cursor.getColumnIndex("c4"));
                c4_other = cursor.getString(cursor.getColumnIndex("c4_other"));
                refused = cursor.getString(cursor.getColumnIndex("refused"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            RConsUtils.setEditText(edt_c3a_hh, c3a_hh);
            RConsUtils.setEditText(edt_c3a_mm, c3a_mm);

            RConsUtils.setEditText(edt_c3b_hh, c3b_hh);
            RConsUtils.setEditText(edt_c3b_mm, c3b_mm);

            RConsUtils.setEditText(edt_c3c_hh, c3c_hh);
            RConsUtils.setEditText(edt_c3c_mm, c3c_mm);

            RConsUtils.setEditText(edt_c3d_hh, c3d_hh);
            RConsUtils.setEditText(edt_c3d_mm, c3d_mm);


            RConsUtils.setEditText(edt_c3e_hh, c3e_hh);
            RConsUtils.setEditText(edt_c3e_mm, c3e_mm);


            RConsUtils.setEditText(edt_c3f_hh, c3f_hh);
            RConsUtils.setEditText(edt_c3f_mm, c3f_mm);

            RConsUtils.setEditText(edt_c3g_hh, c3g_hh);
            RConsUtils.setEditText(edt_c3g_mm, c3g_mm);

            RConsUtils.setEditText(edt_c3h_hh, c3h_hh);
            RConsUtils.setEditText(edt_c3h_mm, c3h_mm);

            RConsUtils.setEditText(edt_c3i_hh, c3i_hh);
            RConsUtils.setEditText(edt_c3i_mm, c3i_mm);

            RConsUtils.setEditText(edt_c3j_hh, c3j_hh);
            RConsUtils.setEditText(edt_c3j_mm, c3j_mm);

            RConsUtils.setEditText(edt_c3k_hh, c3k_hh);
            RConsUtils.setEditText(edt_c3k_mm, c3k_mm);

            RConsUtils.setEditText(edt_c3l_hh, c3l_hh);
            RConsUtils.setEditText(edt_c3l_mm, c3l_mm);

            RConsUtils.setEditText(edt_c3m_hh, c3m_hh);
            RConsUtils.setEditText(edt_c3m_mm, c3m_mm);

            RConsUtils.setEditText(edt_c3n_hh, c3n_hh);
            RConsUtils.setEditText(edt_c3n_mm, c3n_mm);

            RConsUtils.setEditText(edt_c3o_hh, c3o_hh);
            RConsUtils.setEditText(edt_c3o_mm, c3o_mm);


            RConsUtils.setotherEditText(edt_c4_other, c4_other);

            RConsUtils.setradiogroup(c4, rg_c4);


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

    void TimeCheck() {
        Integer value_a_hh = Integer.parseInt("0" + c3a_hh);
        Integer value_b_hh = Integer.parseInt("0" + c3b_hh);
        Integer value_c_hh = Integer.parseInt("0" + c3c_hh);
        Integer value_d_hh = Integer.parseInt("0" + c3d_hh);
        Integer value_e_hh = Integer.parseInt("0" + c3e_hh);
        Integer value_f_hh = Integer.parseInt("0" + c3f_hh);
        Integer value_g_hh = Integer.parseInt("0" + c3g_hh);
        Integer value_h_hh = Integer.parseInt("0" + c3h_hh);
        Integer value_i_hh = Integer.parseInt("0" + c3i_hh);
        Integer value_j_hh = Integer.parseInt("0" + c3j_hh);
        Integer value_k_hh = Integer.parseInt("0" + c3k_hh);
        Integer value_l_hh = Integer.parseInt("0" + c3l_hh);
        Integer value_m_hh = Integer.parseInt("0" + c3m_hh);
        Integer value_n_hh = Integer.parseInt("0" + c3n_hh);
        Integer value_o_hh = Integer.parseInt("0" + c3o_hh);

        Integer value_a_mm = Integer.parseInt("0" + c3a_mm);
        Integer value_b_mm = Integer.parseInt("0" + c3b_mm);
        Integer value_c_mm = Integer.parseInt("0" + c3c_mm);
        Integer value_d_mm = Integer.parseInt("0" + c3d_mm);
        Integer value_e_mm = Integer.parseInt("0" + c3e_mm);
        Integer value_f_mm = Integer.parseInt("0" + c3f_mm);
        Integer value_g_mm = Integer.parseInt("0" + c3g_mm);
        Integer value_h_mm = Integer.parseInt("0" + c3h_mm);
        Integer value_i_mm = Integer.parseInt("0" + c3i_mm);
        Integer value_j_mm = Integer.parseInt("0" + c3j_mm);
        Integer value_k_mm = Integer.parseInt("0" + c3k_mm);
        Integer value_l_mm = Integer.parseInt("0" + c3l_mm);
        Integer value_m_mm = Integer.parseInt("0" + c3m_mm);
        Integer value_n_mm = Integer.parseInt("0" + c3n_mm);
        Integer value_o_mm = Integer.parseInt("0" + c3o_mm);


        Integer Total_hh = value_a_hh + value_b_hh + value_c_hh + value_d_hh + value_e_hh + value_f_hh + value_g_hh + value_h_hh + value_i_hh + value_j_hh + value_k_hh + value_l_hh + value_m_hh + value_n_hh + value_o_hh;

        Integer Total_mm = value_a_mm + value_b_mm + value_c_mm + value_d_mm + value_e_mm + value_f_mm + value_g_mm + value_h_mm + value_i_mm + value_j_mm + value_k_mm + value_l_mm + value_m_mm + value_n_mm + value_o_mm;

        Integer hoursinminute = Total_hh * 60;

        Integer Total_mminute = Total_mm + hoursinminute;

        if (Total_mminute > 1440) {
            toastMessage("You have crossed 24 hours");
        } else {
            RConsUtils.hideKeyboard(pq_Section_C3.this);
            hideView(qc3o_layout, qc4_layout);
        }

    }

    void RemainingTime() {
        c3a_hh = edt_c3a_hh.getText().toString();
        c3b_hh = edt_c3b_hh.getText().toString();
        c3c_hh = edt_c3c_hh.getText().toString();
        c3d_hh = edt_c3d_hh.getText().toString();
        c3e_hh = edt_c3e_hh.getText().toString();
        c3f_hh = edt_c3f_hh.getText().toString();
        c3g_hh = edt_c3g_hh.getText().toString();
        c3h_hh = edt_c3h_hh.getText().toString();
        c3i_hh = edt_c3i_hh.getText().toString();
        c3j_hh = edt_c3j_hh.getText().toString();
        c3k_hh = edt_c3k_hh.getText().toString();
        c3l_hh = edt_c3l_hh.getText().toString();
        c3m_hh = edt_c3m_hh.getText().toString();
        c3n_hh = edt_c3n_hh.getText().toString();
        c3o_hh = edt_c3o_hh.getText().toString();

        c3a_mm = edt_c3a_mm.getText().toString();
        c3b_mm = edt_c3b_mm.getText().toString();
        c3c_mm = edt_c3c_mm.getText().toString();
        c3d_mm = edt_c3d_mm.getText().toString();
        c3e_mm = edt_c3e_mm.getText().toString();
        c3f_mm = edt_c3f_mm.getText().toString();
        c3g_mm = edt_c3g_mm.getText().toString();
        c3h_mm = edt_c3h_mm.getText().toString();
        c3i_mm = edt_c3i_mm.getText().toString();
        c3j_mm = edt_c3j_mm.getText().toString();
        c3k_mm = edt_c3k_mm.getText().toString();
        c3l_mm = edt_c3l_mm.getText().toString();
        c3m_mm = edt_c3m_mm.getText().toString();
        c3n_mm = edt_c3n_mm.getText().toString();
        c3o_mm = edt_c3o_mm.getText().toString();


        Integer value_a_hh = Integer.parseInt("0" + c3a_hh);
        Integer value_b_hh = Integer.parseInt("0" + c3b_hh);
        Integer value_c_hh = Integer.parseInt("0" + c3c_hh);
        Integer value_d_hh = Integer.parseInt("0" + c3d_hh);
        Integer value_e_hh = Integer.parseInt("0" + c3e_hh);
        Integer value_f_hh = Integer.parseInt("0" + c3f_hh);
        Integer value_g_hh = Integer.parseInt("0" + c3g_hh);
        Integer value_h_hh = Integer.parseInt("0" + c3h_hh);
        Integer value_i_hh = Integer.parseInt("0" + c3i_hh);
        Integer value_j_hh = Integer.parseInt("0" + c3j_hh);
        Integer value_k_hh = Integer.parseInt("0" + c3k_hh);
        Integer value_l_hh = Integer.parseInt("0" + c3l_hh);
        Integer value_m_hh = Integer.parseInt("0" + c3m_hh);
        Integer value_n_hh = Integer.parseInt("0" + c3n_hh);
        Integer value_o_hh = Integer.parseInt("0" + c3o_hh);

        Integer value_a_mm = Integer.parseInt("0" + c3a_mm);
        Integer value_b_mm = Integer.parseInt("0" + c3b_mm);
        Integer value_c_mm = Integer.parseInt("0" + c3c_mm);
        Integer value_d_mm = Integer.parseInt("0" + c3d_mm);
        Integer value_e_mm = Integer.parseInt("0" + c3e_mm);
        Integer value_f_mm = Integer.parseInt("0" + c3f_mm);
        Integer value_g_mm = Integer.parseInt("0" + c3g_mm);
        Integer value_h_mm = Integer.parseInt("0" + c3h_mm);
        Integer value_i_mm = Integer.parseInt("0" + c3i_mm);
        Integer value_j_mm = Integer.parseInt("0" + c3j_mm);
        Integer value_k_mm = Integer.parseInt("0" + c3k_mm);
        Integer value_l_mm = Integer.parseInt("0" + c3l_mm);
        Integer value_m_mm = Integer.parseInt("0" + c3m_mm);
        Integer value_n_mm = Integer.parseInt("0" + c3n_mm);
        Integer value_o_mm = Integer.parseInt("0" + c3o_mm);


        Integer Total_hh = value_a_hh + value_b_hh + value_c_hh + value_d_hh + value_e_hh + value_f_hh + value_g_hh + value_h_hh + value_i_hh + value_j_hh + value_k_hh + value_l_hh + value_m_hh + value_n_hh + value_o_hh;

        Integer Total_mm = value_a_mm + value_b_mm + value_c_mm + value_d_mm + value_e_mm + value_f_mm + value_g_mm + value_h_mm + value_i_mm + value_j_mm + value_k_mm + value_l_mm + value_m_mm + value_n_mm + value_o_mm;

        Integer hoursinminute = Total_hh * 60;

        Integer Total_mminute = Total_mm + hoursinminute;

        Integer diffrence = 1440 - Total_mminute;


        int hours = diffrence / 60; //since both are ints, you get an int
        int minutes = diffrence % 60;

        txt_remaining_time.setText("Remaining Time : " + hours + " Hours and " + minutes + " Minutes");
    }

    void ChangeText(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                RemainingTime();
            }
        });
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
                Intent intent = new Intent(pq_Section_C3.this, pq_Section_D.class);
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