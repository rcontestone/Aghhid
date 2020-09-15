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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rcons.fcallbacks.BuildConfig;
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

import static com.rcons.fcallbacks.Utilties.RConsUtils.hideView;
import static com.rcons.fcallbacks.Utilties.RConsUtils.setEditText;

public class pq_Section_C1 extends AppCompatActivity {


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

    @BindView(R.id.edt_c1_a)
    EditText edt_c1_a;

    @BindView(R.id.edt_c1_b)
    EditText edt_c1_b;

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
    String c1_a = "";
    String c1_b = "";

    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pq_section_c1);

        ButterKnife.bind(this);
        databaseAccess = new DatabaseAdapter(pq_Section_C1.this);
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


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1_a = edt_c1_a.getText().toString();
                c1_b = edt_c1_b.getText().toString();

                if (StringUtils.isEmpty(c1_a)) {
                    toastMessage("Please Enter Required Data");
                    edt_c1_a.requestFocus();
                } else if (StringUtils.isEmpty(c1_b)) {
                    toastMessage("Please Enter Required Data");
                    edt_c1_b.requestFocus();
                } else {

                    Integer c1_a_value = Integer.parseInt(c1_a);
                    Integer c1_b_value = Integer.parseInt(c1_b);

                    if ((c1_a_value > 20)) {
                        toastMessage("Must be in 0-20");
                        edt_c1_a.requestFocus();
                    } else if ((c1_b_value > 20)) {
                        toastMessage("Must be in 0-20");
                        edt_c1_b.requestFocus();
                    } else {
                        SaveData();
                        Intent intent = new Intent(pq_Section_C1.this, pq_Section_C2.class);
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

                Intent intent = new Intent(pq_Section_C1.this, AddReportActivity.class);
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
        if (ActivityCompat.checkSelfPermission(pq_Section_C1.this,
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
          //      phoneNumber = "880" + phoneNumber;
            } else {
                phoneNumber = "770" + phoneNumber;
            }
        } else {

        }
        if (network.equalsIgnoreCase("Telenor")) {
            ShowDialMessage(pq_Section_C1.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
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
            databaseAccess.savepq_Section_C1_Data(emp_id,
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
                    c1_a, c1_b);
//
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    void readFromDataBase() {
        try {

            Cursor cursor = databaseAccess.getpq_section_c1_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                c1_a = cursor.getString(cursor.getColumnIndex("c1_a"));
                c1_b = cursor.getString(cursor.getColumnIndex("c1_b"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void LoadPreviousData() {
        readFromDataBase();
        try {

            setEditText(edt_c1_a, c1_a);
            setEditText(edt_c1_b, c1_b);

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