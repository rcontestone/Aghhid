package com.rcons.fcallbacks.Main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mubashar.dateandtime.DebugLog;
import com.rcons.fcallbacks.Athreehhid.HouseHoldDataBaseHelper;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class AddReportActivity_AD extends AppCompatActivity implements DatabaseAdapter.DurationPopup {

    Button SaveReport, Back;
    RadioGroup group;
    RadioButton radioButton;
    RadioButton code_10_other;

    EditText other, m4_other,newNUmber,anyComments;
    EditText callReasonEditText;
    String section;
    String school_code;
    String student_id;
    String farmer_id;
    String farmer_cellphone;
    String id;
    boolean isAlternateFarmer = false;
    String empID;
    DatabaseAdapter databaseAccess;

    LinearLayout otherReasonLayout;
    LinearLayout callReasonLayout;
    ImageView btnBack;
    boolean isDataUpdated = false;
    boolean isFromCallActivity = false;
    TextView txtErrorMessage;

    String calldurationReason = "";
    String b1 = "";
    String b2 = "";
    String c3a_hh = "";
    String e10 = "";

    String h_1 = "";
    String h_2 = "";

    String a1 = "";
    String a2 = "";
    String a3 = "";
    String a4 = "";
    String a4_day = "";
    String a4_month = "";
    String a4_year = "";
    String a4_hh = "";
    String a4_mm = "";
    String a4_number = "";
    String a5 = "";
    String a5_a = "";
    String a6 = "";
    String a6_other = "";
    String a6_day = "";
    String a6_month = "";
    String a6_year = "";
    String a6_hh = "";
    String a6_mm = "";
    String a7 = "";
    String e8 = "";
    String e9 = "";

    String sc1 = "";
    String sc2 = "";
    String sc3 = "";
    String sc4 = "";
    String sc5 = "";
    String sc6 = "";

    String a4_a = "";
    String a4_b = "";
    String a4_c = "";
    String a4_c_other = "";
    String a4_d = "";
    String a4_d_other = "";

    RadioButton code_1, code_2, code_3, code_4, code_5, code_6, code_7, code_8, code_9;

    LinearLayout qm3_layout;
    RelativeLayout month_spinner_name, day_spinner_name, hour_spinner_name, minutes_spinner_name;
    LinearLayout qm4_layout;
    RadioGroup rg_m3;
    RadioGroup rg_m4;

    String m3_answered = "";
    String m4_answered = "";
    String m4_answered_other = "";

    public Spinner spiiner_hour, spiiner_min, spiiner_day, spiiner_month;
    String e11_day = "";
    String e11_month = "";
    String e11_hh = "";
    String e11_mm = "";


    String call_from = "HHID_ADOLESCENT";
    private String reportComments="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_ad);

        spiiner_day = (Spinner) findViewById(R.id.spiiner_day);
        spiiner_month = (Spinner) findViewById(R.id.spiiner_month);
        spiiner_hour = (Spinner) findViewById(R.id.spiiner_hour);
        spiiner_min = (Spinner) findViewById(R.id.spiiner_min);
        spiiner_min = (Spinner) findViewById(R.id.spiiner_min);

        month_spinner_name = findViewById(R.id.month_spinner_name);
        day_spinner_name = findViewById(R.id.day_spinner_name);
        hour_spinner_name = findViewById(R.id.hour_spinner_name);
        minutes_spinner_name = findViewById(R.id.minutes_spinner_name);


        calldurationReason = "";
        databaseAccess = new DatabaseAdapter(AddReportActivity_AD.this);
        databaseAccess.Open();

        txtErrorMessage = findViewById(R.id.txtErrorMessage);
        qm3_layout = findViewById(R.id.qm3_layout);
        qm4_layout = findViewById(R.id.qm4_layout);
        rg_m3 = findViewById(R.id.rg_m3);
        rg_m4 = findViewById(R.id.rg_m4);
        m4_other = findViewById(R.id.m4_other);
        anyComments = findViewById(R.id.anyComments);
        newNUmber = findViewById(R.id.newNUmber);

        code_1 = findViewById(R.id.code_1);
        code_2 = findViewById(R.id.code_2);
        code_3 = findViewById(R.id.code_3);
        code_4 = findViewById(R.id.code_4);
        code_5 = findViewById(R.id.code_5);
        code_6 = findViewById(R.id.code_6);
        code_7 = findViewById(R.id.code_7);
        code_8 = findViewById(R.id.code_8);
        code_9 = findViewById(R.id.code_9);

        btnBack = findViewById(R.id.btnBack);
        code_10_other = findViewById(R.id.code_10_other);
        code_10_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    otherReasonLayout.setVisibility(View.VISIBLE);
                    callReasonLayout.setVisibility(View.GONE);
                } else {
                    otherReasonLayout.setVisibility(View.GONE);
                }
            }
        });
        code_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //qm3_layout.setVisibility(View.VISIBLE);
                    qm4_layout.setVisibility(View.VISIBLE);
                } else {
                    qm3_layout.setVisibility(View.GONE);
                    qm4_layout.setVisibility(View.GONE);
                    m3_answered = "";
                    m4_answered = "";
                    m4_answered_other = "";
                }
            }
        });


        code_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    spiiner_day.setVisibility(View.VISIBLE);
                    spiiner_month.setVisibility(View.VISIBLE);
                    spiiner_hour.setVisibility(View.VISIBLE);
                    spiiner_min.setVisibility(View.VISIBLE);
                } else {

                }
            }
        });


        code_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    spiiner_day.setVisibility(View.GONE);
                    spiiner_month.setVisibility(View.GONE);
                    spiiner_hour.setVisibility(View.GONE);
                    spiiner_min.setVisibility(View.GONE);

                    spiiner_day.setSelection(0);
                    spiiner_month.setSelection(0);
                    spiiner_hour.setSelection(0);
                    spiiner_min.setSelection(0);
                    e11_day = "";
                    e11_month = "";
                    e11_hh = "";
                    e11_mm = "";
                }
            }
        });


        code_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    spiiner_day.setVisibility(View.GONE);
                    spiiner_month.setVisibility(View.GONE);
                    spiiner_hour.setVisibility(View.GONE);
                    spiiner_min.setVisibility(View.GONE);

                    spiiner_day.setSelection(0);
                    spiiner_month.setSelection(0);
                    spiiner_hour.setSelection(0);
                    spiiner_min.setSelection(0);
                    e11_day = "";
                    e11_month = "";
                    e11_hh = "";
                    e11_mm = "";
                }
            }
        });


        code_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    spiiner_day.setVisibility(View.VISIBLE);
                    spiiner_month.setVisibility(View.VISIBLE);
                    spiiner_hour.setVisibility(View.VISIBLE);
                    spiiner_min.setVisibility(View.VISIBLE);
                } else {

                }
            }
        });


        code_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    spiiner_day.setVisibility(View.GONE);
                    spiiner_month.setVisibility(View.GONE);
                    spiiner_hour.setVisibility(View.GONE);
                    spiiner_min.setVisibility(View.GONE);

                    spiiner_day.setSelection(0);
                    spiiner_month.setSelection(0);
                    spiiner_hour.setSelection(0);
                    spiiner_min.setSelection(0);
                    e11_day = "";
                    e11_month = "";
                    e11_hh = "";
                    e11_mm = "";
                }
            }
        });


        code_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    spiiner_day.setVisibility(View.GONE);
                    spiiner_month.setVisibility(View.GONE);
                    spiiner_hour.setVisibility(View.GONE);
                    spiiner_min.setVisibility(View.GONE);

                    spiiner_day.setSelection(0);
                    spiiner_month.setSelection(0);
                    spiiner_hour.setSelection(0);
                    spiiner_min.setSelection(0);
                    e11_day = "";
                    e11_month = "";
                    e11_hh = "";
                    e11_mm = "";
                }
            }
        });


        code_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    spiiner_day.setVisibility(View.GONE);
                    spiiner_month.setVisibility(View.GONE);
                    spiiner_hour.setVisibility(View.GONE);
                    spiiner_min.setVisibility(View.GONE);

                    spiiner_day.setSelection(0);
                    spiiner_month.setSelection(0);
                    spiiner_hour.setSelection(0);
                    spiiner_min.setSelection(0);
                    e11_day = "";
                    e11_month = "";
                    e11_hh = "";
                    e11_mm = "";
                }
            }
        });

        otherReasonLayout = findViewById(R.id.otherReasonLayout);
        callReasonLayout = findViewById(R.id.callReasonLayout);
        other = findViewById(R.id.other);
        callReasonEditText = findViewById(R.id.callReasonEditText);
        group = findViewById(R.id.report);

        SaveReport = (Button) findViewById(R.id.save_report);
        Back = (Button) findViewById(R.id.back);

        id = getIntent().getStringExtra("id");
        isAlternateFarmer = getIntent().getBooleanExtra("isAlternateFarmer", false);
        isFromCallActivity = getIntent().getBooleanExtra("isFromCallActivity", false);
        farmer_id = getIntent().getStringExtra("farmer_id");
        farmer_cellphone = getIntent().getStringExtra("farmer_cellphone");
        empID = getIntent().getStringExtra("empID");
        section = getIntent().getStringExtra("section");
        school_code = getIntent().getStringExtra("school_code");
        student_id = getIntent().getStringExtra("student_id");

        readFromDataBase_Basic();
        setRadioButton();
        // ///    getSupportActionBar().setTitle("Add AddReportActivity");


        SaveReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, 0);
                } else {
                    //Toast.makeText(AddReportActivity.this, ""+RConsUtils.LastCallDuration(AddReportActivity.this , farmer_number_), Toast.LENGTH_SHORT).show();
                    addReportData();
                }


            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowErrorMessage(AddReportActivity_AD.this, "Alert", "You didn't update any call status. Are you sure you want to exit?", true);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowErrorMessage(AddReportActivity_AD.this, "Alert", "You didn't update any call status. Are you sure you want to exit?", true);

            }
        });


    }

    void addReportData() {
        String reason = "";
        String needCallAgain = "0";

        String year = RConsUtils.getcurrentTime(0);
        String month = RConsUtils.getcurrentTime(1);
        String day = RConsUtils.getcurrentTime(2);
        String hh = RConsUtils.getcurrentTime(3);
        String mm = RConsUtils.getcurrentTime(4);

        int selectedID = group.getCheckedRadioButtonId();
        if (selectedID == -1) {
            Toast.makeText(AddReportActivity_AD.this, "Please select from given choices.", Toast.LENGTH_LONG).show();
            return;
        }
        if (selectedID == R.id.code_10_other) {
            otherReasonLayout.setVisibility(View.VISIBLE);
            reason = other.getText().toString();
            if (StringUtils.isEmpty(reason)) {
                Toast.makeText(this, "Please add some reason.", Toast.LENGTH_SHORT).show();
                return;
            }

        } else {
            otherReasonLayout.setVisibility(View.GONE);
        }
        radioButton = findViewById(selectedID);
        String surveyStatus = radioButton.getText().toString();
        if (surveyStatus.contains(".")) {
            surveyStatus = surveyStatus.split(Pattern.quote("."))[0];
        } else {
            surveyStatus = String.valueOf(surveyStatus.charAt(0));
        }
//


        boolean any_error_foud = checkForMandatoryOptions(surveyStatus);

        if (any_error_foud) {
            DebugLog.console("[AddReportActivity] inside addReportData() any_error_foud after " + any_error_foud);
            return;
        }

        DebugLog.console("[AddReportActivity] inside addReportData() any_error_foud after if ");
        if (selectedID == R.id.code_10_other) {

            NeedCallDialog(AddReportActivity_AD.this, "Call Again", "Do you need to call again on this number ?", surveyStatus, reason, isAlternateFarmer);
        } else {

            DebugLog.console("[AddReportActivity] inside addReportData() surveyStatus " + surveyStatus);
            // databaseAccess.baseline_updateCallStatus(AddReportActivity.this, surveyStatus, school_code, student_id, id, farmer_id, farmer_cellphone, reason, isAlternateFarmer, needCallAgain, empID, calldurationReason, AddReportActivity.this);

            reportComments = anyComments.getText().toString();
            if(newNUmber.getText().toString().length()>0)
                HouseHoldDataBaseHelper.getDataBaseProcessor(AddReportActivity_AD.this).aghhid_update_given_nummber(school_code, student_id,newNUmber.getText().toString());


            databaseAccess.savetitle_interview_end_time(school_code, student_id, year, month, day, hh, mm);


            databaseAccess.aghh_updateCallStatus(AddReportActivity_AD.this, surveyStatus, school_code, student_id, id, farmer_id, farmer_cellphone, reason, isAlternateFarmer, needCallAgain, empID, calldurationReason, AddReportActivity_AD.this, m3_answered, m4_answered, m4_answered_other, e11_day, e11_month, e11_hh, e11_mm, reportComments,call_from);
            isDataUpdated = true;
            Toast.makeText(AddReportActivity_AD.this, "Data updated Successfully.", Toast.LENGTH_SHORT).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", isDataUpdated);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

        }

    }

    private boolean checkForMandatoryOptions(String surveyStatus) {
        boolean error = false;
        try {

            if (surveyStatus.equalsIgnoreCase("1")) {

                m3_answered = m4_answered;

                if (!(m4_answered.length() > 0)) {
                    Toast.makeText(AddReportActivity_AD.this, "Please select M3.", Toast.LENGTH_LONG).show();
                    return error = true;
                }

                if (m4_answered.length() > 0) {
                    if (m4_answered.equalsIgnoreCase("5")) {
                        m4_answered_other = m4_other.getText().toString();

                        if (m4_answered_other.length() > 0) {

                        } else {
                            Toast.makeText(AddReportActivity_AD.this, "Please ADD M3 explanation.", Toast.LENGTH_LONG).show();
                            return error = true;
                        }
                    }
                }

            } else if (surveyStatus.equalsIgnoreCase("2") || surveyStatus.equalsIgnoreCase("5")) {


                if (spiiner_day.getSelectedItemPosition() == 0 || spiiner_month.getSelectedItemPosition() == 0 || spiiner_hour.getSelectedItemPosition() == 0 || spiiner_min.getSelectedItemPosition() == 0) {

                    Toast.makeText(AddReportActivity_AD.this, "Please Select call time .", Toast.LENGTH_LONG).show();
                    return error = true;
                } else {
                    e11_day = spiiner_day.getSelectedItem().toString();
                    e11_month = spiiner_month.getSelectedItem().toString();
                    e11_hh = spiiner_hour.getSelectedItem().toString();
                    e11_mm = spiiner_min.getSelectedItem().toString();
                }

            }

            if(newNUmber.getText().toString().length()>0 && newNUmber.getText().toString().length()<10){
                Toast.makeText(AddReportActivity_AD.this, "Please Enter valid phone number ", Toast.LENGTH_LONG).show();
                newNUmber.requestFocus();

                return error = true;
            }


        } catch (Exception e) {
            EmailDebugLog.getInstance(AddReportActivity_AD.this).writeLog("[AddReportActivity] inside checkForMandatoryOptions() Exception is :" + e.toString());
            return error = true;
        }
        return error;
    }


    void NeedCallDialog(final Context context, String title, String message, final String surveyStatus, final String reason, final boolean isAlternateFarmer) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.need_call_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        // txtErrorMessage.setVisibility(View.GONE);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertDialog.dismiss();
                //  boolean callagain_flag_manual_set = databaseAccess.baseline_updateCallStatus(AddReportActivity.this, surveyStatus, school_code, student_id, id, farmer_id, farmer_cellphone, reason, isAlternateFarmer, "2", empID, calldurationReason, AddReportActivity.this);
                boolean callagain_flag_manual_set = databaseAccess.aghh_updateCallStatus(AddReportActivity_AD.this, surveyStatus, school_code, student_id, id, farmer_id, farmer_cellphone, reason, isAlternateFarmer, "2", empID, calldurationReason, AddReportActivity_AD.this, m3_answered, m4_answered, m4_answered_other, e11_day, e11_month, e11_hh, e11_mm,reportComments ,call_from);

                isDataUpdated = true;
                Toast.makeText(AddReportActivity_AD.this, "Data updated Successfully.", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (callagain_flag_manual_set) {
                    Toast.makeText(AddReportActivity_AD.this, "Three tries completed against this ." + id, Toast.LENGTH_SHORT).show();

                }

                Intent returnIntent = new Intent();
                MubLog.cpnsoleLog("isDataUpdated: " + isDataUpdated);
                returnIntent.putExtra("isDataUpdated", isDataUpdated);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                //  databaseAccess.baseline_updateCallStatus(AddReportActivity.this, surveyStatus, school_code, student_id, id, farmer_id, farmer_cellphone, reason, isAlternateFarmer, "1", empID, calldurationReason, AddReportActivity.this);
                databaseAccess.aghh_updateCallStatus(AddReportActivity_AD.this, surveyStatus, school_code, student_id, id, farmer_id, farmer_cellphone, reason, isAlternateFarmer, "1", empID, calldurationReason, AddReportActivity_AD.this, m3_answered, m4_answered, m4_answered_other, e11_day, e11_month, e11_hh, e11_mm,reportComments ,call_from);


                isDataUpdated = true;
                Toast.makeText(AddReportActivity_AD.this, "Data updated Successfully.", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("isDataUpdated", isDataUpdated);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.checkSelfPermission(AddReportActivity_AD.this,
                        Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    return;

                }
                addReportData();
            } else {
                Toast.makeText(this, "Allow this permission to fetch the last call record.", Toast.LENGTH_SHORT).show();
                // ShowPermissionDialog("Allow this permission to copy images to your phone memory");
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("isDataUpdated", isDataUpdated);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        super.onBackPressed();
    }

    void ShowErrorMessage(final Context context, String title, String message, final boolean isExit) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.custom_error_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnOk = dialogView.findViewById(R.id.btnOk);
        // txtErrorMessage.setVisibility(View.GONE);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (isExit) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("isDataUpdated", isDataUpdated);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {

                }


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }

    public boolean checkQuestionbandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionB")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        Cursor cursor = databaseAccess.getSectionBData(farmer_id);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            String b26 = cursor.getString(cursor.getColumnIndex("b26"));
                            String b15 = cursor.getString(cursor.getColumnIndex("b15"));
                            String b16 = cursor.getString(cursor.getColumnIndex("b16"));
                            String b17 = cursor.getString(cursor.getColumnIndex("b17"));
                            String b5 = cursor.getString(cursor.getColumnIndex("b5"));
                            String b4 = cursor.getString(cursor.getColumnIndex("b4"));
                            String b3 = cursor.getString(cursor.getColumnIndex("b3"));
                            String b2 = cursor.getString(cursor.getColumnIndex("b2"));
                            String b6 = cursor.getString(cursor.getColumnIndex("b6"));
                            String b7 = cursor.getString(cursor.getColumnIndex("b7"));
                            String b14 = cursor.getString(cursor.getColumnIndex("b14"));


                            if (b26.equalsIgnoreCase("1") || b26.equalsIgnoreCase("3")) {

                                if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_5 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                                    Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


//                            If B15=3 then B16 is must fill – But B16 is blank in 104 cases.
                            if (b15.equalsIgnoreCase("3")) {
                                if (b16.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B15=3 then B16 is must fill.", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


//                            If B15 is <3 then B17 is must fill – But B17 is blank in 12 cases.
                            if (b15.equalsIgnoreCase("1") || b15.equalsIgnoreCase("2")) {
                                if (b17.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B15 is 1 or 2 then B17 is must fill .", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


                            //If B5=1 then B6 is must fill – But B6 is blank in 63 cases.
                            if (b5.equalsIgnoreCase("1")) {
                                if (b6.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B5=1 then B6 is must fill  .", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


                            //If B5=1 then B7 is must fill – But B7 is blank in 63 cases.
                            if (b5.equalsIgnoreCase("1")) {
                                if (b7.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B5=1 then B7 is must fill  .", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


                            //If B5=2 then skip to B14 – But B14 is blank in 55 cases.
                            if (b5.equalsIgnoreCase("2")) {
                                if (b14.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B5=2  B14  is must fill.", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


                            //If B4=2 then B5 is must fill – But B5 is blank in 22 cases.
                            if (b4.equalsIgnoreCase("2")) {
                                if (b5.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B4=2 then B5 is must fill.", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


                            // If B2=3 then B3 is must fill – But B3 is blank in 274 cases.
                            if (b2.equalsIgnoreCase("3")) {
                                if (b3.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B2=3 then B3 is must fill .", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


//                            If B4=1 then skip to B26 – But B26 is blank in 93 cases.
                            if (b4.equalsIgnoreCase("1")) {
                                if (b26.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B4=1 then B26 is must fill .", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }

//                            If B2=3 then B3 is must fill & then skip to B14 – But B14 is blank in 97 cases.
                            if (b2.equalsIgnoreCase("3")) {
                                if (b14.equalsIgnoreCase("")) {
                                    Toast.makeText(AddReportActivity_AD.this, "If B2=3 then B14 is must fill .", Toast.LENGTH_LONG).show();
                                    return false;
                                }
                            }


                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionCandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionC")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_5 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionDandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionD")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_5 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionETakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionE")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        Cursor cursor = databaseAccess.getSectionEData(farmer_id);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            String e42 = cursor.getString(cursor.getColumnIndex("e42"));

                            if (!StringUtils.isEmpty(e42)) {
                                if (selectedID == R.id.code_3 || selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                                    Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                                    return false;
                                }

                            } else {
                                if (selectedID == R.id.code_5 || selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                                    Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                                    return false;
                                }

                            }

                        }

                    }
                    return true;
                }
            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }


    public boolean checkSectionftable_1andTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("section_f_table1")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionftable_2andTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("section_f_table2")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionftable_4andTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("section_f_table4")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionftable_5andTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("section_f_table5")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionftable_6andTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("section_f_table6")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionGandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionG")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionG_bandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionG_b")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkSectionHandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (section.equalsIgnoreCase("sectionH")) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        if (selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_3 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                            Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                    return true;
                }

            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }


    public boolean checkSectionITakeaction(int selectedID) {

        try {

            if (section.equalsIgnoreCase("sectionI")) {
                if (selectedID == R.id.code_3 || selectedID == R.id.code_2 || selectedID == R.id.code_4 || selectedID == R.id.code_6 || selectedID == R.id.code_7 || selectedID == R.id.code_8 || selectedID == R.id.code_9) {
                    Toast.makeText(AddReportActivity_AD.this, "Please select valid choices.", Toast.LENGTH_LONG).show();

                    return false;
                } else if (farmer_id != null) {
                    if (!farmer_id.equalsIgnoreCase("")) {
                        Cursor cursor = databaseAccess.getSection1Data(farmer_id);
                        if (cursor != null) {
                            cursor.moveToFirst();
                            String q1_1 = cursor.getString(cursor.getColumnIndex("q1_1"));
                            String q1_3 = cursor.getString(cursor.getColumnIndex("q1_3"));
                            if (!StringUtils.isEmpty(q1_1) && StringUtils.isEmpty(q1_3)) {
                                Toast.makeText(AddReportActivity_AD.this, "Please fill complete section.", Toast.LENGTH_LONG).show();

                                return false;
                            } else {
                                return true;
                            }

                        } else {
                            return true;
                        }
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }

            } else {
                return true;
            }
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    public boolean checkQuestionIandTakeaction(int selectedID) {

        try {

            if (farmer_id != null) {
                if (!farmer_id.equalsIgnoreCase("")) {
                    Cursor cursor = databaseAccess.getSection1Data(farmer_id);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        String q1_4 = cursor.getString(cursor.getColumnIndex("q1_4"));
                        if (StringUtils.isEmpty(q1_4)) {
                            if (selectedID == R.id.code_1) {
                                Toast.makeText(AddReportActivity_AD.this, "Please Fill First Section_I.Q i.4", Toast.LENGTH_LONG).show();

                                return false;
                            }

                        }
                    } else {
                        if (selectedID == R.id.code_1) {
                            Toast.makeText(AddReportActivity_AD.this, "Please Fill First Section_I.Q i.4", Toast.LENGTH_LONG).show();

                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (Exception e) {
            //e EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside checkQuestionbandTakeaction() Exception is :"+e.toString());
            return true;
        }
    }

    @Override
    public boolean showDurationPopUp() {

        try {
            MubLog.cpnsoleLog("inside showDurationPopUp");
            return false;
        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[AddReportActivity] inside showDurationPopUp() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside showDurationPopUp" + e.toString());
            return false;
        }

    }

    void readFromDataBase_Section_b() {
        try {

            Cursor cursor = databaseAccess.getpq_section_b_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                b2 = cursor.getString(cursor.getColumnIndex("b2"));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readFromDataBase_Section_c3() {
        try {

            Cursor cursor = databaseAccess.getpq_section_c3_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                c3a_hh = cursor.getString(cursor.getColumnIndex("c3a_hh"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readFromDataBase_Section_e2() {
        try {

            Cursor cursor = databaseAccess.getpq_section_e_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                e8 = cursor.getString(cursor.getColumnIndex("e8"));
                e9 = cursor.getString(cursor.getColumnIndex("e9"));
                e10 = cursor.getString(cursor.getColumnIndex("e10"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readFromDataBase_Section_a() {
        try {

            Cursor cursor = null;
            if (StringUtils.isEmpty(sc1)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, farmer_cellphone, "1");
                MubLog.cpnsoleLog("readFromDataBase Try 1");
            } else if (StringUtils.isEmpty(sc2)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, farmer_cellphone, "2");
                MubLog.cpnsoleLog("readFromDataBase Try 2");
            } else if (StringUtils.isEmpty(sc3)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, farmer_cellphone, "3");
                MubLog.cpnsoleLog("readFromDataBase Try 3");
            } else if (StringUtils.isEmpty(sc4)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, farmer_cellphone, "4");
                MubLog.cpnsoleLog("readFromDataBase Try 4");
            } else if (StringUtils.isEmpty(sc5)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, farmer_cellphone, "5");
                MubLog.cpnsoleLog("readFromDataBase Try 5");
            } else if (StringUtils.isEmpty(sc6)) {
                cursor = databaseAccess.getad_section_a_Data(school_code, student_id, farmer_cellphone, "6");
                MubLog.cpnsoleLog("readFromDataBase Try 6");
            }
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                a1 = cursor.getString(cursor.getColumnIndex("a1"));
                b1 = cursor.getString(cursor.getColumnIndex("b1"));
                b2 = cursor.getString(cursor.getColumnIndex("b2"));
                a2 = cursor.getString(cursor.getColumnIndex("a2"));
                a3 = cursor.getString(cursor.getColumnIndex("a3"));
                a4 = cursor.getString(cursor.getColumnIndex("a4"));
                a4_a = cursor.getString(cursor.getColumnIndex("a4_a"));
                a4_b = cursor.getString(cursor.getColumnIndex("a4_b"));
                a4_c = cursor.getString(cursor.getColumnIndex("a4_c"));
                a4_c_other = cursor.getString(cursor.getColumnIndex("a4_c_other"));
                a4_d = cursor.getString(cursor.getColumnIndex("a4_d"));
                a4_d_other = cursor.getString(cursor.getColumnIndex("a4_d_other"));
                a4_day = cursor.getString(cursor.getColumnIndex("a4_day"));
                a4_month = cursor.getString(cursor.getColumnIndex("a4_month"));
                a4_year = cursor.getString(cursor.getColumnIndex("a4_year"));
                a4_hh = cursor.getString(cursor.getColumnIndex("a4_hh"));
                a4_mm = cursor.getString(cursor.getColumnIndex("a4_mm"));
                a4_number = cursor.getString(cursor.getColumnIndex("a4_number"));
                a5 = cursor.getString(cursor.getColumnIndex("a5"));
                a5_a = cursor.getString(cursor.getColumnIndex("a5_a"));
                a6 = cursor.getString(cursor.getColumnIndex("a6"));
                a6_other = cursor.getString(cursor.getColumnIndex("a6_other"));
                a6_day = cursor.getString(cursor.getColumnIndex("a6_day"));
                a6_month = cursor.getString(cursor.getColumnIndex("a6_month"));
                a6_year = cursor.getString(cursor.getColumnIndex("a6_year"));
                a6_hh = cursor.getString(cursor.getColumnIndex("a6_hh"));
                a6_mm = cursor.getString(cursor.getColumnIndex("a6_mm"));
                a7 = cursor.getString(cursor.getColumnIndex("a7"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readFromDataBase_Section_h() {
        try {

            Cursor cursor = databaseAccess.getad_section_f_Data(school_code, student_id);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                h_1 = cursor.getString(cursor.getColumnIndex("h_1"));
                h_2 = cursor.getString(cursor.getColumnIndex("h_2"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readFromDataBase_Basic() {
        try {


            Cursor cursor = databaseAccess.getpq_basic_Data(school_code, student_id);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();


                sc1 = cursor.getString(cursor.getColumnIndex("sc1"));
                sc2 = cursor.getString(cursor.getColumnIndex("sc2"));
                sc3 = cursor.getString(cursor.getColumnIndex("sc3"));
                sc4 = cursor.getString(cursor.getColumnIndex("sc4"));
                sc5 = cursor.getString(cursor.getColumnIndex("sc5"));
                sc6 = cursor.getString(cursor.getColumnIndex("sc6"));


            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

//    void setRadioButton() {
//        readFromDataBase_Section_b();
//        readFromDataBase_Section_c3();
//        readFromDataBase_Section_e2();
//        readFromDataBase_Section_a();
//
//        if (!StringUtils.isEmpty(e10)) {
//            code_1.setVisibility(View.VISIBLE);
//            code_2.setVisibility(View.GONE);
//            code_3.setVisibility(View.GONE);
//            code_4.setVisibility(View.GONE);
//            code_5.setVisibility(View.GONE);
//            code_6.setVisibility(View.GONE);
//            code_7.setVisibility(View.GONE);
//            code_8.setVisibility(View.GONE);
//            code_9.setVisibility(View.GONE);
//            code_10_other.setVisibility(View.VISIBLE);
//        }  else if (!StringUtils.isEmpty(b2)) {
//            code_1.setVisibility(View.GONE);
//            code_2.setVisibility(View.GONE);
//            code_3.setVisibility(View.VISIBLE);
//            code_4.setVisibility(View.GONE);
//            code_5.setVisibility(View.GONE);
//            code_6.setVisibility(View.GONE);
//            code_7.setVisibility(View.GONE);
//            code_8.setVisibility(View.GONE);
//            code_9.setVisibility(View.GONE);
//            code_10_other.setEnabled(true);
//        } else {
//            if (StringUtils.isEmpty(a1)) {
//                code_1.setVisibility(View.GONE);
//                code_2.setVisibility(View.GONE);
//                code_3.setVisibility(View.GONE);
//                code_4.setVisibility(View.GONE);
//                code_5.setVisibility(View.GONE);
//                code_6.setVisibility(View.GONE);
//                code_7.setVisibility(View.GONE);
//                code_8.setVisibility(View.GONE);
//                code_9.setVisibility(View.GONE);
//                code_10_other.setVisibility(View.GONE);
//                SaveReport.setVisibility(View.GONE);
//            } else {
//                if (!StringUtils.isEmpty(a7)) {
//                    code_1.setVisibility(View.GONE);
//                    code_2.setVisibility(View.GONE);
//                    code_3.setVisibility(View.GONE);
//                    code_4.setVisibility(View.GONE);
//                    code_5.setVisibility(View.VISIBLE);
//                    code_6.setVisibility(View.GONE);
//                    code_7.setVisibility(View.GONE);
//                    code_8.setVisibility(View.GONE);
//                    code_9.setVisibility(View.GONE);
//                    code_10_other.setVisibility(View.VISIBLE);
//                }else if (!StringUtils.isEmpty(a6_other)) {
//                    code_1.setVisibility(View.GONE);
//                    code_2.setVisibility(View.GONE);
//                    code_3.setVisibility(View.GONE);
//                    code_4.setVisibility(View.GONE);
//                    code_5.setVisibility(View.GONE);
//                    code_6.setVisibility(View.GONE);
//                    code_7.setVisibility(View.GONE);
//                    code_8.setVisibility(View.GONE);
//                    code_9.setVisibility(View.GONE);
//                    code_10_other.setVisibility(View.VISIBLE);
//                } else if (a6.equalsIgnoreCase("1")
//                        || a6.equalsIgnoreCase("2")
//                        || a6.equalsIgnoreCase("3")
//                ) {
//                    code_1.setVisibility(View.GONE);
//                    code_2.setVisibility(View.GONE);
//                    code_3.setVisibility(View.VISIBLE);
//                    code_4.setVisibility(View.GONE);
//                    code_5.setVisibility(View.GONE);
//                    code_6.setVisibility(View.GONE);
//                    code_7.setVisibility(View.GONE);
//                    code_8.setVisibility(View.GONE);
//                    code_9.setVisibility(View.GONE);
//                    code_10_other.setVisibility(View.GONE);
//                } else if (a6.equalsIgnoreCase("4")) {
//                    code_1.setVisibility(View.GONE);
//                    code_2.setVisibility(View.GONE);
//                    code_3.setVisibility(View.GONE);
//                    code_4.setVisibility(View.GONE);
//                    code_5.setVisibility(View.GONE);
//                    code_6.setVisibility(View.GONE);
//                    code_7.setVisibility(View.GONE);
//                    code_8.setVisibility(View.GONE);
//                    code_9.setVisibility(View.GONE);
//                    code_10_other.setVisibility(View.VISIBLE);
//                } else if (a5.equalsIgnoreCase("1")) {
//                    code_1.setVisibility(View.GONE);
//                    code_2.setVisibility(View.GONE);
//                    code_3.setVisibility(View.VISIBLE);
//                    code_4.setVisibility(View.GONE);
//                    code_5.setVisibility(View.GONE);
//                    code_6.setVisibility(View.GONE);
//                    code_7.setVisibility(View.GONE);
//                    code_8.setVisibility(View.GONE);
//                    code_9.setVisibility(View.GONE);
//                    code_10_other.setVisibility(View.VISIBLE);
//                } else {
//                    code_1.setVisibility(View.GONE);
//                    code_2.setVisibility(View.VISIBLE);
//                    code_3.setVisibility(View.VISIBLE);
//                    code_4.setVisibility(View.VISIBLE);
//                    code_5.setVisibility(View.GONE);
//                    code_6.setVisibility(View.VISIBLE);
//                    code_7.setVisibility(View.VISIBLE);
//                    code_8.setVisibility(View.VISIBLE);
//                    code_9.setVisibility(View.VISIBLE);
//                    code_10_other.setVisibility(View.VISIBLE);
//                }
//
//            }
//
//        }
//
//
//    }

    void setRadioButton() {
        readFromDataBase_Section_a();
        readFromDataBase_Section_h();
        if (!StringUtils.isEmpty(a2)) {
            code_1.setVisibility(View.VISIBLE);
            code_2.setVisibility(View.VISIBLE);
            code_3.setVisibility(View.VISIBLE);
            code_4.setVisibility(View.VISIBLE);
            code_5.setVisibility(View.VISIBLE);
            code_6.setVisibility(View.VISIBLE);
            code_7.setVisibility(View.VISIBLE);
            code_8.setVisibility(View.VISIBLE);
            group.setVisibility(View.VISIBLE);
            SaveReport.setVisibility(View.VISIBLE);
            month_spinner_name.setVisibility(View.GONE);
            day_spinner_name.setVisibility(View.GONE);
            hour_spinner_name.setVisibility(View.GONE);
            minutes_spinner_name.setVisibility(View.GONE);
        } else if (!StringUtils.isEmpty(h_1) && StringUtils.isEmpty(h_2)) {
            code_1.setVisibility(View.GONE);
            code_2.setVisibility(View.GONE);
            code_3.setVisibility(View.GONE);
            code_4.setVisibility(View.GONE);
            code_5.setVisibility(View.GONE);
            code_6.setVisibility(View.GONE);
            code_7.setVisibility(View.GONE);
            code_8.setVisibility(View.GONE);
            group.setVisibility(View.GONE);
            SaveReport.setVisibility(View.GONE);
            month_spinner_name.setVisibility(View.GONE);
            day_spinner_name.setVisibility(View.GONE);
            hour_spinner_name.setVisibility(View.GONE);
            minutes_spinner_name.setVisibility(View.GONE);
            txtErrorMessage.setText("Kindly fill section_f question f2");
            txtErrorMessage.setVisibility(View.VISIBLE);
        } else if (!StringUtils.isEmpty(h_2)) {
            code_1.setVisibility(View.VISIBLE);
            code_2.setVisibility(View.GONE);
            code_3.setVisibility(View.GONE);
            code_4.setVisibility(View.GONE);
            code_5.setVisibility(View.GONE);
            code_6.setVisibility(View.GONE);
            code_7.setVisibility(View.GONE);
            code_8.setVisibility(View.GONE);
            group.setVisibility(View.VISIBLE);
            SaveReport.setVisibility(View.VISIBLE);
            month_spinner_name.setVisibility(View.GONE);
            day_spinner_name.setVisibility(View.GONE);
            hour_spinner_name.setVisibility(View.GONE);
            minutes_spinner_name.setVisibility(View.GONE);
        } else {
            code_1.setVisibility(View.GONE);
        }
        code_4.setVisibility(View.GONE);
        code_5.setVisibility(View.GONE);
    }


    public void setMigrationM3(View view) {

        m3_answered = view.getTag().toString();

    }

    public void setMigrationM4(View view) {

        m4_answered = view.getTag().toString();

    }

}
