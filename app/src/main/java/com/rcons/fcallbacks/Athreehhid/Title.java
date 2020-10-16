package com.rcons.fcallbacks.Athreehhid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_A;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Title extends AppCompatActivity {
    @BindView(R.id.txt_School_Code)
    TextView txt_School_Code;
    @BindView(R.id.txt_Student_id)
    TextView txt_Student_id;

    @BindView(R.id.edt_hhid)
    EditText edt_hhid;

    @BindView(R.id.edt_phone_number_1)
    EditText edt_phone_number_1;


    @BindView(R.id.edt_phone_number_2)
    EditText edt_phone_number_2;

    @BindView(R.id.edt_headname)
    EditText edt_headname;

    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.btnBack)
    ImageView btniBack;

    @BindView(R.id.rg_phone_number_1)
    RadioGroup rg_phone_number_1;

    @BindView(R.id.rg_phone_number_2)
    RadioGroup rg_phone_number_2;


    String emp_id = "";
    String order_id = "";
    String farmer_id = "";
    String rcons_user = "";
    String enum_code = "";
    String enum_name = "";
    String isComplete = "";
    String isSynced = "";
    String insert_or_updated_in_phone_at = "";
    String uploaded_time = "";
    String deviceid = "";
    String build_no = "";

    String village_id = "";
    String village_name = "";
    String hhid = "";
    String head_name = "";
    String phone_number_1 = "";
    String phone_number_2 = "";
    String phone_1_v = "";
    String phone_2_v = "";
    DatabaseAdapter databaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        ButterKnife.bind(this);

        village_id = getIntent().getStringExtra("villageID");
        village_name = getIntent().getStringExtra("Village_name");

        txt_School_Code.setText("Village Code : " + village_id);
        txt_Student_id.setText("Village Name : " + village_name);

        databaseAccess = new DatabaseAdapter(Title.this);
        databaseAccess.Open();


        SetEnumState();
        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;

        edt_hhid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = edt_hhid.getText().toString().toLowerCase();
                if (text.length() > 0) {
//                    ReadDatabase(village_id, text);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hhid = edt_hhid.getText().toString();
                head_name = edt_headname.getText().toString();
                phone_number_1 = edt_phone_number_1.getText().toString();
                phone_number_2 = edt_phone_number_2.getText().toString();
                int rg_1_ID = rg_phone_number_1.getCheckedRadioButtonId();
                int rg_2_ID = rg_phone_number_2.getCheckedRadioButtonId();
                if (StringUtils.isEmpty(hhid)) {
                    toastMessage("Please Specify hhid");
                } else if (StringUtils.isEmpty(head_name)) {
                    toastMessage("Please Specify head name");
                } else if (StringUtils.isEmpty(phone_number_1)) {
                    toastMessage("Please Enter Phone Number");
                } else if (!StringUtils.isEmpty(phone_number_1) && phone_number_1.length() != 10 && !phone_number_1.equalsIgnoreCase("999")) {
                    toastMessage("Invalid Phone Number 1 Length or Select phone number verification option");
                } else if (!StringUtils.isEmpty(phone_number_1) && !phone_number_1.equalsIgnoreCase("999") && rg_1_ID < 1) {
                    toastMessage("Phone Number 1 Select phone number verification option");
                } else if (!StringUtils.isEmpty(phone_number_2) && phone_number_2.length() != 10 && !phone_number_2.equalsIgnoreCase("999")) {
                    toastMessage("Invalid Phone Number 2 Length or Select phone number verification option");
                } else if (!StringUtils.isEmpty(phone_number_2) && !phone_number_2.equalsIgnoreCase("999") && rg_2_ID < 1) {
                    toastMessage("Phone Number 2 Select phone number verification option");
                } else {
                    if (rg_1_ID > 0) {
                        RadioButton radioButton_1 = findViewById(rg_1_ID);
                        phone_1_v = radioButton_1.getTag().toString();
                    }
                    if (rg_2_ID > 0) {
                        RadioButton radioButton_2 = findViewById(rg_2_ID);
                        phone_2_v = radioButton_2.getTag().toString();
                    }
                    SaveData();
                    Intent intent = new Intent(Title.this, pq_Section_A.class);
                    intent.putExtra("scode", village_id);
                    intent.putExtra("village_name", village_name);
                    intent.putExtra("studentid", hhid);
                    intent.putExtra("m1b_student_name", head_name);
                    startActivityForResult(intent, 88);
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
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("isDataUpdated", false);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void toastMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    void SaveData() {
        try {
            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);


            String start_year = MubDateAndTime.INSTANCE.getcurrentTime(0);
            String start_month = MubDateAndTime.INSTANCE.getcurrentTime(1);
            String start_day = MubDateAndTime.INSTANCE.getcurrentTime(2);
            String start_hh = MubDateAndTime.INSTANCE.getcurrentTime(3);
            String start_mm = MubDateAndTime.INSTANCE.getcurrentTime(4);
            isSynced = "99";

            databaseAccess.saveTitle_Data(village_id,
                    hhid,
                    start_day,
                    start_month,
                    start_year,
                    start_hh,
                    start_mm,
                    head_name,
                    phone_number_1,
                    phone_1_v,
                    phone_number_2,
                    phone_2_v,
                    isSynced,
                    rcons_user,
                    enum_code,
                    enum_name,
                    deviceid,
                    insert_or_updated_in_phone_at,
                    uploaded_time,
                    build_no
            );


        } catch (Exception e) {
            toastMessage(e.getMessage());
        }
    }

    void ReadDatabase(String village_id, String hhid) {
        try {

            Cursor cursor = databaseAccess.getTitleData(village_id, hhid);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                head_name = cursor.getString(cursor.getColumnIndex("head_name"));
                phone_number_1 = cursor.getString(cursor.getColumnIndex("phone_number_1"));
                phone_1_v = cursor.getString(cursor.getColumnIndex("phone_1_v"));
                phone_number_2 = cursor.getString(cursor.getColumnIndex("phone_number_2"));
                phone_2_v = cursor.getString(cursor.getColumnIndex("phone_2_v"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void SetEnumState() {

        if (RConsUtils.GetRegistrationState() == 1) {
            enum_name = RConsUtils.getEnumName();
            enum_code = RConsUtils.getEnumCode();
            rcons_user = RConsUtils.getUserName();
        }
    }

}