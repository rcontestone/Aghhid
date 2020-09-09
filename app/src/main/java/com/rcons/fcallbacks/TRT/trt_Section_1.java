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

public class trt_Section_1 extends AppCompatActivity {

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

    @BindView(R.id.q1_layout)
    LinearLayout q1_layout;

    @BindView(R.id.q2_layout)
    LinearLayout q2_layout;

    @BindView(R.id.q3_layout)
    LinearLayout q3_layout;

    @BindView(R.id.q4_layout)
    LinearLayout q4_layout;

    @BindView(R.id.edt_q1_a)
    EditText edt_q1_a;

    @BindView(R.id.edt_q1_b)
    EditText edt_q1_b;

    @BindView(R.id.edt_q1_c)
    EditText edt_q1_c;

    @BindView(R.id.rg_q2)
    RadioGroup rg_q2;

    @BindView(R.id.rg_q3)
    RadioGroup rg_q3;

    @BindView(R.id.rg_q4)
    RadioGroup rg_q4;
    String username = "";
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
    String q1_a = "";
    String q1_b = "";
    String q1_c = "";
    String q2 = "";
    String q3 = "";
    String q4 = "";
    String city_name = "";
    @BindView(R.id.btnBack)
    ImageView btniBack;

    @BindView(R.id.btn_end)
    Button btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trt_section_1);
        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(trt_Section_1.this);
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
                int rg_q2_ID = rg_q2.getCheckedRadioButtonId();
                int rg_q3_ID = rg_q3.getCheckedRadioButtonId();
                int rg_q4_ID = rg_q4.getCheckedRadioButtonId();
                hideKeyboard(trt_Section_1.this);
                SaveData();
                if (q1_layout.getVisibility() == View.VISIBLE) {
                    q1_a = edt_q1_a.getText().toString();
                    q1_b = edt_q1_b.getText().toString();
                    q1_c = edt_q1_c.getText().toString();
                    if (!StringUtils.isEmpty(q1_a)) {
                        RConsUtils.hideView(q1_layout, q2_layout);
                    } else {
                        toastMessage("Please Enter Required data");
                    }
                } else if (q2_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q2_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q2_ID);
                        q2 = radioButton.getTag().toString();
                        RConsUtils.hideView(q2_layout, q3_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q3_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q3_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q3_ID);
                        q3 = radioButton.getTag().toString();
                        RConsUtils.hideView(q3_layout, q4_layout);
                    } else {
                        toastMessage("Please Select Option");
                    }
                } else if (q4_layout.getVisibility() == View.VISIBLE) {
                    if (rg_q4_ID > 0) {
                        RadioButton radioButton = findViewById(rg_q4_ID);
                        q4 = radioButton.getTag().toString();
                        if (rg_q2_ID == R.id.rbtn_q4_1) {
                            Intent intent = new Intent(trt_Section_1.this, trt_Section_3.class);
                            intent.putExtra("city_name", city_name);
                            intent.putExtra("phone_number", phone_number);
                            intent.putExtra("id", id);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("farmer_id", farmer_id);
                            intent.putExtra("sr_no", sr_no);
                            startActivityForResult(intent, 77);
                        }else {
                            Intent intent = new Intent(trt_Section_1.this, trt_Section_2.class);
                            intent.putExtra("city_name", city_name);
                            intent.putExtra("phone_number", phone_number);
                            intent.putExtra("id", id);
                            intent.putExtra("emp_id", emp_id);
                            intent.putExtra("order_id", order_id);
                            intent.putExtra("farmer_id", farmer_id);
                            intent.putExtra("sr_no", sr_no);
                            startActivityForResult(intent, 77);
                        }
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
                ShowDialogMessage(trt_Section_1.this, "Alert", "Are You Sure Want to End Survey ?");
            }
        });

        btn_AddReportQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(trt_Section_1.this, AddReportActivity.class);
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
        if (q4_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(q4_layout, q3_layout);
        } else if (q3_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(q3_layout, q2_layout);
        } else if (q2_layout.getVisibility() == View.VISIBLE) {
            RConsUtils.hideView(q2_layout, q1_layout);
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
        if (ActivityCompat.checkSelfPermission(trt_Section_1.this,
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
            databaseAccess.savetrt_Section_1_Data(emp_id, order_id, farmer_id,
                    sr_no, rcons_user,
                    enum_code, enum_name, isComplete,
                    isSynced, insert_or_updated_in_phone_at, deviceid,
                    build_no, phone_number, q1_a, q1_b,
                    q1_c, q2, q3, q4);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.gettrt_section_1_Data(phone_number);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                emp_id = cursor.getString(cursor.getColumnIndex("emp_id"));
                order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                farmer_id = cursor.getString(cursor.getColumnIndex("farmer_id"));
                sr_no = cursor.getString(cursor.getColumnIndex("sr_no"));
                rcons_user = cursor.getString(cursor.getColumnIndex("rcons_user"));
                enum_code = cursor.getString(cursor.getColumnIndex("enum_code"));
                enum_name = cursor.getString(cursor.getColumnIndex("enum_name"));
                isComplete = cursor.getString(cursor.getColumnIndex("isComplete"));
                isSynced = cursor.getString(cursor.getColumnIndex("isSynced"));
                insert_or_updated_in_phone_at = cursor.getString(cursor.getColumnIndex("insert_or_updated_in_phone_at"));
                deviceid = cursor.getString(cursor.getColumnIndex("deviceid"));
                build_no = cursor.getString(cursor.getColumnIndex("build_no"));
                phone_number = cursor.getString(cursor.getColumnIndex("phone_number"));
                q1_a = cursor.getString(cursor.getColumnIndex("q1_a"));
                q1_b = cursor.getString(cursor.getColumnIndex("q1_b"));
                q1_c = cursor.getString(cursor.getColumnIndex("q1_c"));
                q2 = cursor.getString(cursor.getColumnIndex("q2"));
                q3 = cursor.getString(cursor.getColumnIndex("q3"));
                q4 = cursor.getString(cursor.getColumnIndex("q4"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {

            RConsUtils.setEditText(edt_q1_a, q1_a);
            RConsUtils.setEditText(edt_q1_b, q1_b);
            RConsUtils.setEditText(edt_q1_c, q1_c);

            RConsUtils.setradiogroup(q2, rg_q2);
            RConsUtils.setradiogroup(q3, rg_q3);
            RConsUtils.setradiogroup(q4, rg_q4);

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