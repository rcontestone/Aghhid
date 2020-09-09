package com.rcons.fcallbacks.TRT;

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
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.BuildConfig;
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
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class trt_Section_4 extends AppCompatActivity {

    DatabaseAdapter databaseAccess;


    @BindView(R.id.txt_phone_number)
    TextView txt_phone_number;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_RedialCall)
    FloatingActionButton btn_RedialCall;
    @BindView(R.id.btn_AddReportQuestionnaire)
    Button btn_AddReportQuestionnaire;


    @BindView(R.id.q21_layout)
    LinearLayout q21_layout;

    @BindView(R.id.q22_layout)
    LinearLayout q22_layout;

    @BindView(R.id.q23_layout)
    LinearLayout q23_layout;

    @BindView(R.id.q24_layout)
    LinearLayout q24_layout;

    @BindView(R.id.q25_layout)
    LinearLayout q25_layout;

    @BindView(R.id.rg_q22)
    RadioGroup rg_q22;

    @BindView(R.id.rg_q25)
    RadioGroup rg_q25;

    @BindView(R.id.edt_q21)
    EditText edt_q21;

    @BindView(R.id.edt_q23)
    EditText edt_q23;

    @BindView(R.id.edt_q24)
    EditText edt_q24;

    String id = "";
    String emp_id = "";
    String order_id = "";
    String farmer_id = "";
    String sr_no = "";
    String rcons_user = "";
    String enum_code = "";
    String enum_name = "";
    String isComplete = "";
    String isSynced = "";
    String insert_or_updated_in_phone_at = "";
    String deviceid = "";
    String build_no = "";
    String phone_number = "";
    String q21 = "";
    String q22 = "";
    String q23 = "";
    String q24 = "";
    String q25 = "";
    String username = "";
    String city_name = "";

    @BindView(R.id.btnBack)
    ImageView btniBack;

    @BindView(R.id.btn_end)
    Button btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trt_section_4);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(trt_Section_4.this);
        databaseAccess.Open();


        id = getIntent().getStringExtra("id");
        emp_id = getIntent().getStringExtra("emp_id");
        order_id = getIntent().getStringExtra("order_id");
        farmer_id = getIntent().getStringExtra("farmer_id");
        sr_no = getIntent().getStringExtra("sr_no");
        city_name = getIntent().getStringExtra("city_name");
        phone_number = getIntent().getStringExtra("phone_number");
        txt_phone_number.setText("Phone Number " + phone_number);

        SetEnumState();
        LoadPreviousData();

        deviceid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        build_no = BuildConfig.VERSION_NAME;

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_q22_ID = rg_q22.getCheckedRadioButtonId();
                int rg_q25_ID = rg_q25.getCheckedRadioButtonId();

                hideKeyboard(trt_Section_4.this);
                SaveData();
                if (q21_layout.getVisibility() == View.VISIBLE) {
                    q21 = edt_q21.getText().toString();
                    if (!StringUtils.isEmpty(q21)) {
                        hideView(q21_layout, q22_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q22_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q22_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q22_ID);
                        q22 = radioButton.getTag().toString();
                        hideView(q22_layout, q23_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q23_layout.getVisibility() == View.VISIBLE) {
                    q23 = edt_q23.getText().toString();
                    if (!StringUtils.isEmpty(q23)) {
                        Integer value = Integer.parseInt(q23);
                        if ((value >= 10 && value <= 100)) {
                            hideView(q23_layout, q24_layout);
                        } else {
                            toastMessage("Year must be in 10-100.");
                        }
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q24_layout.getVisibility() == View.VISIBLE) {
                    q24 = edt_q24.getText().toString();
                    if (!StringUtils.isEmpty(q24)) {
                        hideView(q24_layout, q25_layout);
                    } else {
                        toastMessage("Please Enter Required Data");
                    }
                } else if (q25_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q25_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q25_ID);
                        q25 = radioButton.getTag().toString();
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
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
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogMessage(trt_Section_4.this, "Alert", "Are You Sure Want to End Survey ?");
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(trt_Section_4.this, AddReportActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("farmer_id", farmer_id);
                intent.putExtra("empID", emp_id);
                intent.putExtra("farmer_cellphone", phone_number);
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
        if (q25_layout.getVisibility() == View.VISIBLE) {
            hideView(q25_layout, q24_layout);
        } else if (q24_layout.getVisibility() == View.VISIBLE) {
            hideView(q24_layout, q23_layout);
        } else if (q23_layout.getVisibility() == View.VISIBLE) {
            hideView(q23_layout, q22_layout);
        } else if (q22_layout.getVisibility() == View.VISIBLE) {
            hideView(q22_layout, q21_layout);
        } else {
            finish();
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
        if (ActivityCompat.checkSelfPermission(trt_Section_4.this,
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 77) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }
    }


    void SaveData() {
        try {

            insert_or_updated_in_phone_at = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("build_no " + build_no);
            databaseAccess.savetrt_Section_4_Data(emp_id, order_id, farmer_id, sr_no, rcons_user,
                    enum_code, enum_name, isComplete, isSynced,
                    insert_or_updated_in_phone_at, deviceid, build_no,
                    phone_number, q21, q22, q23, q24, q25);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.gettrt_section_4_Data(phone_number);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                q21 = cursor.getString(cursor.getColumnIndex("q21"));
                q22 = cursor.getString(cursor.getColumnIndex("q22"));
                q23 = cursor.getString(cursor.getColumnIndex("q23"));
                q24 = cursor.getString(cursor.getColumnIndex("q24"));
                q25 = cursor.getString(cursor.getColumnIndex("q25"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            setEditText(edt_q21, q21);
            setEditText(edt_q23, q23);
            setEditText(edt_q24, q24);
            setradiogroup(q22, rg_q22);
            setradiogroup(q25, rg_q25);

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
            enum_name = RConsUtils.getUserName();
            enum_code = RConsUtils.getUserPassword();
        }
    }

    void ShowDialogMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.my_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnYes = dialogView.findViewById(R.id.btnYes);
        btnYes.setText("Yes");
        btnCancel.setText("No");
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
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