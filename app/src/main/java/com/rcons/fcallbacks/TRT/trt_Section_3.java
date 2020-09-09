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
import static com.rcons.fcallbacks.Utilties.RConsUtils.setradiogroup;

public class trt_Section_3 extends AppCompatActivity {

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

    @BindView(R.id.q14_layout)
    LinearLayout q14_layout;

    @BindView(R.id.q15_layout)
    LinearLayout q15_layout;

    @BindView(R.id.q16_layout)
    LinearLayout q16_layout;

    @BindView(R.id.q17_layout)
    LinearLayout q17_layout;

    @BindView(R.id.q18_layout)
    LinearLayout q18_layout;

    @BindView(R.id.q19_layout)
    LinearLayout q19_layout;

    @BindView(R.id.q20_layout)
    LinearLayout q20_layout;

    @BindView(R.id.rg_q14)
    RadioGroup rg_q14;

    @BindView(R.id.rg_q15)
    RadioGroup rg_q15;

    @BindView(R.id.rg_q16)
    RadioGroup rg_q16;

    @BindView(R.id.rg_q17)
    RadioGroup rg_q17;

    @BindView(R.id.rg_q18)
    RadioGroup rg_q18;

    @BindView(R.id.rg_q19)
    RadioGroup rg_q19;

    @BindView(R.id.rg_q20)
    RadioGroup rg_q20;

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
    String q14 = "";
    String q15 = "";
    String q16 = "";
    String q17 = "";
    String q18 = "";
    String q19 = "";
    String q20 = "";
    String username = "";
    String city_name = "";

    @BindView(R.id.btnBack)
    ImageView btniBack;

    @BindView(R.id.btn_end)
    Button btn_end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trt_section_3);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(trt_Section_3.this);
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

        hideKeyboard(trt_Section_3.this);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rg_q14_ID = rg_q14.getCheckedRadioButtonId();
                int rg_q15_ID = rg_q15.getCheckedRadioButtonId();
                int rg_q16_ID = rg_q16.getCheckedRadioButtonId();
                int rg_q17_ID = rg_q17.getCheckedRadioButtonId();
                int rg_q18_ID = rg_q18.getCheckedRadioButtonId();
                int rg_q19_ID = rg_q19.getCheckedRadioButtonId();
                int rg_q20_ID = rg_q20.getCheckedRadioButtonId();

                SaveData();
                if (q14_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q14_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q14_ID);
                        q14 = radioButton.getTag().toString();
                        hideView(q14_layout, q15_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q15_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q15_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q15_ID);
                        q15 = radioButton.getTag().toString();
                        hideView(q15_layout, q16_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q16_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q16_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q16_ID);
                        q16 = radioButton.getTag().toString();
                        hideView(q16_layout, q17_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q17_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q17_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q17_ID);
                        q17 = radioButton.getTag().toString();
                        hideView(q17_layout, q18_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q18_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q18_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q18_ID);
                        q18 = radioButton.getTag().toString();
                        hideView(q18_layout, q19_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q19_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q19_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q19_ID);
                        q19 = radioButton.getTag().toString();
                        hideView(q19_layout, q20_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q20_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q20_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q20_ID);
                        q20 = radioButton.getTag().toString();
                        Intent intent = new Intent(trt_Section_3.this, trt_Section_4.class);
                        intent.putExtra("city_name", city_name);
                        intent.putExtra("phone_number", phone_number);
                        intent.putExtra("id", id);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("farmer_id", farmer_id);
                        intent.putExtra("sr_no", sr_no);
                        startActivityForResult(intent, 77);
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
                ShowDialogMessage(trt_Section_3.this, "Alert", "Are You Sure Want to End Survey ?");
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(trt_Section_3.this, AddReportActivity.class);
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
        if (q20_layout.getVisibility() == View.VISIBLE) {
            hideView(q20_layout, q19_layout);
        } else if (q19_layout.getVisibility() == View.VISIBLE) {
            hideView(q19_layout, q18_layout);
        } else if (q18_layout.getVisibility() == View.VISIBLE) {
            hideView(q18_layout, q17_layout);
        } else if (q17_layout.getVisibility() == View.VISIBLE) {
            hideView(q17_layout, q16_layout);
        } else if (q16_layout.getVisibility() == View.VISIBLE) {
            hideView(q16_layout, q15_layout);
        } else if (q15_layout.getVisibility() == View.VISIBLE) {
            hideView(q15_layout, q14_layout);
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
        if (ActivityCompat.checkSelfPermission(trt_Section_3.this,
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
            databaseAccess.savetrt_Section_3_Data(emp_id, order_id, farmer_id,
                    sr_no, rcons_user, enum_code,
                    enum_name, isComplete, isSynced,
                    insert_or_updated_in_phone_at, deviceid, build_no,
                    phone_number, q14, q15,
                    q16, q17, q18,
                    q19, q20);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.gettrt_section_3_Data(phone_number);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                q14 = cursor.getString(cursor.getColumnIndex("q14"));
                q15 = cursor.getString(cursor.getColumnIndex("q15"));
                q16 = cursor.getString(cursor.getColumnIndex("q16"));
                q17 = cursor.getString(cursor.getColumnIndex("q17"));
                q18 = cursor.getString(cursor.getColumnIndex("q18"));
                q19 = cursor.getString(cursor.getColumnIndex("q19"));
                q20 = cursor.getString(cursor.getColumnIndex("q20"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {
            setradiogroup(q14, rg_q14);
            setradiogroup(q15, rg_q15);
            setradiogroup(q16, rg_q16);
            setradiogroup(q17, rg_q17);
            setradiogroup(q18, rg_q18);
            setradiogroup(q19, rg_q19);
            setradiogroup(q20, rg_q20);

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