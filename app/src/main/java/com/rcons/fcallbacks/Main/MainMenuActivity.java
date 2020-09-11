package com.rcons.fcallbacks.Main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.EmailDebugLog;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Athreehhid.HH_Screen_One;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.EditForm.EditFormActivity;
import com.rcons.fcallbacks.HHIDConfigurations;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_B;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.AppController;
import com.rcons.fcallbacks.Utilties.MpcUtil;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StartUpMainActivity;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.Utilties.SyncMainActivity;
import com.rcons.fcallbacks.VillageSearch.SelectEnumNameActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainMenuActivity extends AppCompatActivity {


    TextView txtEnumName, txtEnumCode;

    String enum_name = "";
    String enum_code = "";

    DatabaseAdapter databaseAccess;
    Cursor cursor;
    DatabaseAdapter adapter;
    TextView txtNewCallCounter;
    TextView txtPendingCallCounter;
    TextView txtCompletedCallCounter;
    TextView txtSuccessfullCallCounter;
    TextView txtSyncCompleteQuestionnaireCounter;
    TextView txtLogout;
    TextView tvTitle;
    TextView txtAppVersion;
    String userName = "";
    int newCallCounter = 0;
    int pendingCallCounter = 0;
    int completedCallCounter = 0;
    int SuccessfullCallCounter = 0;
    int SyncCompleteQuestionnaireCounter = 0;
    int pageNumber = 1;
    int pageSize = 500;
    int totalPages = 1;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog2;
    private CardView btnNewCalls;
    private CardView btnPendingCalls;
    private CardView btnImportData;
    private CardView btnExit;
    private CardView btnselectvillage;

    private CardView btnSyncSectionData;

    private CardView btnEditForm;

    private CardView btnImportDatabase;

    LinearLayout MainContent;
    LinearLayout enumName_layout;
    Button btnSelectEnumName, btn_save_enum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        MainContent = findViewById(R.id.MainContent);
        enumName_layout = findViewById(R.id.enumName_layout);

        txtEnumName = findViewById(R.id.txt_enumName);
        txtEnumCode = findViewById(R.id.txt_enumCode);
        btnSelectEnumName = findViewById(R.id.btnSelectEnumName);
        btn_save_enum = findViewById(R.id.btn_save_enum);

        txtAppVersion = findViewById(R.id.txtAppVersion);
        txtAppVersion.setText("App version : " + BuildConfig.VERSION_NAME);

        txtLogout = findViewById(R.id.txtLogout);
        tvTitle = findViewById(R.id.tvTitle);
        txtSuccessfullCallCounter = findViewById(R.id.txtSuccessfullCallCounter);
        txtCompletedCallCounter = findViewById(R.id.txtCompletedCallCounter);
        txtSyncCompleteQuestionnaireCounter = findViewById(R.id.txtSyncCompleteQuestionnaireCounter);
        btnImportData = findViewById(R.id.btnImportData);
        btnPendingCalls = findViewById(R.id.btnPendingCalls);
        btnNewCalls = findViewById(R.id.btnNewCalls);
        txtPendingCallCounter = findViewById(R.id.txtPendingCallCounter);
        txtNewCallCounter = findViewById(R.id.txtNewCallCounter);
        btnEditForm = findViewById(R.id.btnEditForm);

        btnSyncSectionData = findViewById(R.id.btnSyncSectionData);
        //    btnImportDatabase = findViewById(R.id.btnImportDatabase);
        btnExit = findViewById(R.id.btnExit);
        btnselectvillage = findViewById(R.id.btnselectvillage);

        adapter = new DatabaseAdapter(MainMenuActivity.this);
        adapter.Open();
        userName = getIntent().getStringExtra("username");

        databaseAccess = new DatabaseAdapter(MainMenuActivity.this);
        databaseAccess.Open();

        screensize();
        SetEnumState();
       // tvTitle.setText("RconsUser " + userName + " & Enum " + enum_name);
        tvTitle.setText("RconsUser " + userName);
        btnSelectEnumName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(MainMenuActivity.this, SelectEnumNameActivity.class);
                    startActivityForResult(intent, 99);
                } catch (Exception e) {
                    Log.e("TAG", "" + e.toString());
                }
            }
        });
        btn_save_enum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enum_name = txtEnumName.getText().toString();
                enum_code = txtEnumCode.getText().toString();
                if (StringUtils.isEmpty(enum_name)) {
                    Toast.makeText(MainMenuActivity.this, "Add Enum Name", Toast.LENGTH_SHORT).show();
                } else if (StringUtils.isEmpty(enum_code)) {
                    Toast.makeText(MainMenuActivity.this, "Add Enum Code", Toast.LENGTH_SHORT).show();
                } else {
                    RConsUtils.SaveEnumInfo(enum_name, enum_code);
                    MainContent.setVisibility(View.VISIBLE);
                    enumName_layout.setVisibility(View.GONE);
                    tvTitle.setText("RconsUser " + userName + " & Enum " + enum_name);
                }
            }
        });

        btnNewCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(MainMenuActivity.this, pq_Section_B.class);
////                //  intent.putExtra("isPendingCall", true);
////                MubLog.cpnsoleLog("inside btnNewCalls.setOnClickListener  isPendingCall making false");
////                startActivity(intent);

                MubLog.cpnsoleLog("inside btnNewCalls.setOnClickListener");
                if (newCallCounter > 0) {
                    Intent intent = new Intent(MainMenuActivity.this, CallMenuActivity.class);
                    //  intent.putExtra("isPendingCall", true);
                    MubLog.cpnsoleLog("inside btnNewCalls.setOnClickListener  isPendingCall making false");
                    intent.putExtra("isPendingCall", false);
                    intent.putExtra("username", userName);
                    startActivity(intent);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!Settings.canDrawOverlays(MainMenuActivity.this.getApplicationContext())) {
                            Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            myIntent.setData(Uri.parse("package:" + getPackageName()));
                            startActivityForResult(myIntent, 101);
                        }
                    }


                } else {
                    Toast.makeText(MainMenuActivity.this, "No data found. Please sync data from server.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnPendingCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pendingCallCounter > 0) {
                    Intent intent = new Intent(MainMenuActivity.this, CallMenuActivity.class);
                    intent.putExtra("isPendingCall", true);
                    intent.putExtra("username", userName);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainMenuActivity.this, "No data found. Please sync data from server.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowExitMessage(MainMenuActivity.this, "Alert", "Are you sure you want to exit ?", true);

            }
        });

        btnselectvillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  ShowExitMessage(MainMenuActivity.this, "Alert", "Are you sure you want to exit ?", true);
                Intent intent = MpcUtil.buildNewIntent(MainMenuActivity.this, HH_Screen_One.class);
                intent.putExtra("launchActivity","signup");
                startActivity(intent);
            }
        });



        btnSyncSectionData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (RConsUtils.isNetworkAvailable(MainMenuActivity.this)) {
                        ShowSyncMessage(MainMenuActivity.this, "Alert", "Are you sure Sync Questionnaire?");

                    } else {
                        Toast.makeText(MainMenuActivity.this, "No internet.", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    Log.e("TAG", e.toString());

                }

            }
        });

        btnImportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // adapter.CheckMissingColumnsList();

                if (adapter.aghhid_getSampleDataCount() > 0) {
                    ShowErrorMessage(MainMenuActivity.this, "Alert", "Data  already Imported.All data changes will be lost. Do you want to do it again?", true);
                } else {
                    if (RConsUtils.isNetworkAvailable(MainMenuActivity.this)) {


                       // FetchData("Fetching Data...");
                        Intent intent = new Intent(MainMenuActivity.this, StartUpMainActivity.class);
                        startActivity(intent);



                    } else {
                        Toast.makeText(MainMenuActivity.this, "No internet available.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShowLogoutMessage(MainMenuActivity.this, "Alert", "Are you sure you want to Logout ?");

            }
        });
        btnEditForm.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, EditFormActivity.class);
            startActivity(intent);


        });

//        btnImportDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//                } else {
//                    Toast.makeText(MainMenuActivity.this, "Successfully Saved In External Storage", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    void ShowSyncMessage(final Context context, String title, String message) {
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
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                try {
                    if (RConsUtils.isNetworkAvailable(MainMenuActivity.this)) {
                        //sectiondataManualClick("Syncing");
                        Intent intent = new Intent(MainMenuActivity.this, SyncMainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainMenuActivity.this, "No internet.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("TAG", e.toString());
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainMenuActivity.this, "Successfully Saved In External Storage", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Allow this permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void ShowLogoutMessage(final Context context, String title, String message) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.delete_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
        txtErrorMessage.setText(message);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnRconsUser = dialogView.findViewById(R.id.btnRconsUser);
        Button btnenum = dialogView.findViewById(R.id.btnenum);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        btnRconsUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                RConsUtils.LogoutUser();
                Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();


            }
        });
        btnenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                RConsUtils.LogoutEnum();
                SetEnumState();
                enumName_layout.setVisibility(View.VISIBLE);
                MainContent.setVisibility(View.GONE);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FetchCallsCounters();

    }

    void FetchCallsCounters() {


        newCallCounter = adapter.aghhid_getNewCallsCount(userName);
        pendingCallCounter = adapter.aghhid_getPendingCall(userName);
        MubLog.cpnsoleLog("FetchCallsCounters pendingCallCounter" + pendingCallCounter);
        SuccessfullCallCounter = adapter.aghhid_getSuccessFullCallCounter(userName);

        completedCallCounter = adapter.aghhid_getCompletedCallCounter(userName);
        MubLog.cpnsoleLog("FetchCallsCounters completedCallCounter" + completedCallCounter);



        SyncCompleteQuestionnaireCounter = adapter.baseline_GetSyncCompleteQuestionnaireCounter(userName);


        txtNewCallCounter.setText(String.valueOf(newCallCounter));
        txtPendingCallCounter.setText("" + pendingCallCounter);
        txtCompletedCallCounter.setText("" + completedCallCounter);
        txtSuccessfullCallCounter.setText("" + SuccessfullCallCounter);
        txtSyncCompleteQuestionnaireCounter.setText("" + SyncCompleteQuestionnaireCounter);
    }


    void ShowExitMessage(final Context context, String title, String message, final boolean isExit) {

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

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

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

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (progressDialog != null && progressDialog.isShowing()) {
                    MubLog.cpnsoleLog("progressDialog making null");
                    progressDialog.dismiss();
                    progressDialog = null;
                }
                if (isExit) {
                    MubLog.cpnsoleLog("inside isExit " + isExit);
                    if (RConsUtils.isNetworkAvailable(MainMenuActivity.this)) {
                        adapter.aghhid_deleteAllData();
                    }
//                    FetchData("Fetching Data...");
                    Intent intent = new Intent(MainMenuActivity.this, StartUpMainActivity.class);
                    startActivity(intent);

                } else {
//                    pageNumber = 1;
//                    pageSize = 500;
//                    totalPages = 1;
//                    FetchData("Fetching Data...");
                    Intent intent = new Intent(MainMenuActivity.this, StartUpMainActivity.class);
                    startActivity(intent);

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

    private void FetchData(String message) {
        try {
            if (progressDialog == null) {
                MubLog.cpnsoleLog("inside FetchData init progressDialog" + message);
                progressDialog = new ProgressDialog(MainMenuActivity.this);
                progressDialog.show();
                progressDialog.setCancelable(false);

            } else {
                progressDialog.show();
                progressDialog.setCancelable(false);
            }
            progressDialog.setMessage(message);

            String _Login_Url = "http://webapi.rconsdata.com/api/fcbs/sample?pageNo=" + pageNumber + "&pageSize=" + pageSize; // New Url

            String usernameParam = URLEncoder.encode(userName, "UTF-8");

            _Login_Url = "http://rconsdb.org/devteam/general/services/ffbcsr/phoneq_sample.php?" + "&pageSize=" + pageSize + "&username=" + usernameParam + "&password=" + "fcb123!1"; // New Url
//            _Login_Url = "http://rconsdb.org/devteam/general/services/ffbcsr/phoneq_sample.php_test.php?" +  "&pageSize=" + pageSize+ "&username="+usernameParam+ "&password="+"fcb123!1"; // New Url

            MubLog.cpnsoleLog("Fetching data url " + _Login_Url);

            StringRequest stringRequest = new StringRequest(Method.GET, _Login_Url,
                    new Response.Listener<String>() {

                        @Override
                        public void onResponse(String s) {

                            try {
                                // JSONObject resultObject = new JSONObject(s);
                                totalPages = 1;//resultObject.getInt("TotalPage");
                                MubLog.cpnsoleLog("totalPages line commented by mub ");
                                // MubLog.cpnsoleLog("onResponse"+s);
                                //JSONArray result = resultObject.getJSONArray("FarmerCallbackData");
                                //s =s.replaceAll("NULL","\"\"");
                              //  s =s.replaceAll("null","\"\"");

                                JSONArray result = new JSONArray(s);
                                MubLog.cpnsoleLog("onResponse" + result.length());

                                if (result != null && result.length() > 0) {

                                    for (int x = 0; x < result.length(); x++) {
                                        JSONObject jsonObject = result.getJSONObject(x);

                                        jsonObject = new JSONObject(  jsonObject.toString().replaceAll("null","\"\""));
                                        String id = jsonObject.getString("id");
                                        String farmer_id = jsonObject.getString("farmer_id");
                                        String region = jsonObject.getString("region");

                                        String district_basedata = jsonObject.getString("district_basedata");
                                        String tehsil_basedata = jsonObject.getString("tehsil_basedata");
                                        String markaz_name = jsonObject.getString("markaz_name");
                                        String uc_name = jsonObject.getString("uc_name");
                                        String village_muza_basedata = jsonObject.getString("village_muza_basedata");
                                        String farmer_name = jsonObject.getString("farmer_name");
                                        String farmer_fatherhusband_name = jsonObject.getString("farmer_fatherhusband_name");
                                        String gender = jsonObject.getString("gender");
                                        String farmer_cellphone = jsonObject.getString("farmer_cellphone");
                                        String alt_farmer_name = jsonObject.getString("alt_farmer_name");
                                        String alt_relation_with_farmer = jsonObject.getString("alt_relation_with_farmer");
                                        String alt_farmer_cellphone = jsonObject.getString("alt_farmer_cellphone");
                                        String empost_designation = jsonObject.getString("empost_designation");
                                        String emp_fullname = jsonObject.getString("emp_fullname");
                                        String emp_id = jsonObject.getString("emp_id");
                                        String emp_cnic = jsonObject.getString("emp_cnic");
                                        String empost_imei = jsonObject.getString("empost_imei");
                                        String ao_assigned = jsonObject.getString("ao_assigned");
                                        String fa_assigned = jsonObject.getString("fa_assigned");
                                        String ai_assigned = jsonObject.getString("ai_assigned");
                                        String unique_id = jsonObject.getString("unique_id");
                                        String order_id = jsonObject.getString("order_id");
                                        String survey_status = jsonObject.getString("survey_status");
                                        String reason = jsonObject.getString("reason");
                                        String sc1 = jsonObject.getString("sc1");
                                        String sc1_reason = jsonObject.getString("sc1_reason");
                                        String sc1_date_time = jsonObject.getString("sc1_date_time");
                                        String sc2 = jsonObject.getString("sc2");
                                        String sc2_reason = jsonObject.getString("sc2_reason");
                                        String sc2_date_time = jsonObject.getString("sc2_date_time");
                                        String sc3 = jsonObject.getString("sc3");
                                        String sc3_reason = jsonObject.getString("sc3_reason");
                                        String sc3_date_time = jsonObject.getString("sc3_date_time");
                                        String duration = jsonObject.getString("duration");
                                        String add_farmer_no = jsonObject.getString("add_farmer_no");
                                        String sc1_alt = jsonObject.getString("sc1_alt");
                                        String sc1_alt_reason = jsonObject.getString("sc1_alt_reason");
                                        String sc1_date_time_alt = jsonObject.getString("sc1_date_time_alt");
                                        String sc2_alt = jsonObject.getString("sc2_alt");
                                        String sc2_alt_reason = jsonObject.getString("sc2_alt_reason");
                                        String sc2_date_time_alt = jsonObject.getString("sc2_date_time_alt");
                                        String sc3_alt = jsonObject.getString("sc3_alt");
                                        String sc3_alt_reason = jsonObject.getString("sc3_alt_reason");
                                        String sc3_date_time_alt = jsonObject.getString("sc3_date_time_alt");
                                        String duration_alt = jsonObject.getString("duration_alt");
                                        String add_farmer_no_alt = jsonObject.getString("add_farmer_no");
                                        String sync = jsonObject.getString("sync");
                                        String month = jsonObject.getString("month");
                                        String rcons_user = jsonObject.getString("rcons_user");
                                        String deviceid = jsonObject.getString("deviceid");
                                        String temp1 = jsonObject.getString("temp1");
                                        String temp2 = jsonObject.getString("temp2");
                                        String temp3 = jsonObject.getString("temp3");
                                        String temp4 = jsonObject.getString("temp4");
                                        String temp5 = jsonObject.getString("temp5");


                                        String insert_or_updated_in_phone_at = jsonObject.getString("insert_or_updated_in_phone_at");
                                        String call_duration_reason = jsonObject.getString("call_duration_reason");
                                        //String uploaded_time = jsonObject.getString("uploaded_time");

                                        String survey_status_org_call_on=    jsonObject.getString("survey_status_org_call_on");
                                        String survey_status_org =   jsonObject.getString("survey_status_org");
                                        String survey_status_org_reason =   jsonObject.getString("survey_status_org_reason");

                                        String survey_status_alt_call_on =  jsonObject.getString("survey_status_alt_call_on");
                                        String survey_status_alt =  jsonObject.getString("survey_status_alt");
                                        String survey_status_alt_reason =  jsonObject.getString("survey_status_alt_reason");


                                        String enum_code =jsonObject.getString("enum_code");
                                        String enum_name = jsonObject.getString("enum_name");
                                        String build_no = jsonObject.getString("build_no");

                                        if (StringUtils.isEmpty(temp1)) {
                                            temp1 = "0";
                                        }

                                        ContentValues values = new ContentValues();

                                        values.put("id", id);
                                        values.put("farmer_id", farmer_id);
                                        values.put("emp_no", "");
                                        values.put("empost_designation", empost_designation);
                                        values.put("emp_fullname", emp_fullname);
                                        values.put("emp_id", emp_id);
                                        values.put("order_id", order_id);
                                        values.put("emp_cnic", emp_cnic);
                                        values.put("empost_imei", empost_imei);
                                        values.put("region", region);
                                        values.put("district_basedata", district_basedata);
                                        values.put("tehsil_basedata", tehsil_basedata);
                                        values.put("markaz_name", markaz_name);
                                        values.put("uc_name", uc_name);
                                        values.put("village_muza_basedata", village_muza_basedata);
                                        values.put("farmer_name", farmer_name);
                                        values.put("farmer_fatherhusband_name", farmer_fatherhusband_name);
                                        values.put("gender", gender);
                                        values.put("farmer_cellphone", farmer_cellphone);
                                        values.put("alt_farmer_name", alt_farmer_name);
                                        values.put("alt_relation_with_farmer", alt_relation_with_farmer);
                                        values.put("alt_farmer_cellphone", alt_farmer_cellphone);
                                        values.put("ao_assigned", ao_assigned);
                                        values.put("fa_assigned", fa_assigned);
                                        values.put("ai_assigned", ai_assigned);
                                        values.put("unique_id", unique_id);
                                        values.put("survey_status", survey_status);
                                        values.put("reason", reason);
                                        values.put("sc1", sc1);
                                        values.put("sc1_reason", sc1_reason);
                                        values.put("sc1_date_time", sc1_date_time);
                                        values.put("sc2", sc2);
                                        values.put("sc2_reason", sc2_reason);
                                        values.put("sc2_date_time", sc2_date_time);
                                        values.put("sc3", sc3);
                                        values.put("sc3_reason", sc3_reason);
                                        values.put("sc3_date_time", sc3_date_time);
                                        values.put("duration", duration);
                                        values.put("add_farmer_no", add_farmer_no);
                                        values.put("sc1_alt", sc1_alt);
                                        values.put("sc1_alt_reason", sc1_alt_reason);
                                        values.put("sc1_date_time_alt", sc1_date_time_alt);
                                        values.put("sc2_alt", sc2_alt);
                                        values.put("sc2_alt_reason", sc2_alt_reason);
                                        values.put("sc2_date_time_alt", sc2_date_time_alt);
                                        values.put("sc3_alt", sc3_alt);
                                        values.put("sc3_alt_reason", sc3_alt_reason);
                                        values.put("sc3_date_time_alt", sc3_date_time_alt);
                                        values.put("duration_alt", duration_alt);
                                        values.put("add_farmer_no_alt", add_farmer_no_alt);
                                        values.put("sync", sync);
                                        values.put("month", month);
                                        values.put("rcons_user", rcons_user);
                                        values.put("deviceid", deviceid);
                                        values.put("temp1", temp1);
                                        values.put("temp2", temp2);
                                        values.put("temp3", temp3);
                                        values.put("temp4", temp4);
                                        values.put("temp5", temp5);


                                        values.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                                        values.put("call_duration_reason",call_duration_reason);
                                        // values.put("uploaded_time",uploaded_time);



                                        values.put("survey_status_org_call_on",survey_status_org_call_on);
                                        values.put("survey_status_org",survey_status_org);
                                        values.put("survey_status_org_reason",survey_status_org_reason);
                                        values.put("survey_status_alt_call_on",survey_status_alt_call_on);
                                        values.put("survey_status_alt",survey_status_alt);
                                        values.put("survey_status_alt_reason",survey_status_alt_reason);


                                        values.put("enum_code",enum_code);
                                        values.put("enum_name",enum_name);
                                        values.put("build_no",build_no);


                                        adapter.AddFarmerData(values);
                                        MubLog.cpnsoleLog("AddFarmerData called  " + values.getAsString("id"));
                                        MubLog.cpnsoleLog("Emp Name " + values.getAsString("farmer_name"));
                                    }
                                    FetchCallsCounters();

                                } else {
                                    Toast.makeText(MainMenuActivity.this, "Unable to fetch data. Please try again.", Toast.LENGTH_SHORT).show();
                                }

                                if (pageNumber < totalPages) {
                                    if (RConsUtils.isNetworkAvailable(MainMenuActivity.this)) {
                                        pageNumber = pageNumber + 1;
                                        FetchData("Fetching Data...Part " + pageNumber + " of " + totalPages);
                                    }

                                } else {
                                    if (progressDialog != null && progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                }
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            } catch (Exception e) {
                                Log.e("LoginUser", e.toString());
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(MainMenuActivity.this, "Unable to fetch data. Please try again.", Toast.LENGTH_SHORT).show();

                            }


                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(MainMenuActivity.this, "Unable to fetch data. Please try again.", Toast.LENGTH_SHORT).show();

                }
            }) {

            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(25000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            AppController.getInstance().addToRequestQueue(stringRequest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private void SyncDataToServer() {

        Cursor cursor = adapter.getCompletedCalls(userName);
        int count = cursor.getCount();
        if (cursor == null || count == 0) {
            return;
        } else {
            MubLog.cpnsoleLog("SyncDataToServer count " + count);
        }

        final ArrayList<String> updatedIDs = new ArrayList<>();

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MainMenuActivity.this);
            progressDialog.setMessage("Syncing Report  Data...");
            progressDialog.show();
            progressDialog.setCancelable(false);
        }


        final JSONArray jsonArray = new JSONArray();
        String synDataUrl = "http://webapi.rconsdata.com/api/fcbs/report";  // New Url
        synDataUrl = "http://rconsdb.org/devteam/general/services/ffbcsr/phoneq_req.php";
        if (cursor != null && cursor.getCount() > 0) {

//           MubLog.cpnsoleLog("inside count "+cursor.getColumnCount());
//
//            for (String s:cursor.getColumnNames()
//                 ) {
//                MubLog.cpnsoleLog("inside coulmn nmaes "+s);
//            }

            String uploaded_time = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
            MubLog.cpnsoleLog("inside sync report data and time is " + uploaded_time);
            String build_no = BuildConfig.VERSION_NAME;
            if (cursor.moveToFirst()) {
                do {
                    try {
                        JSONObject values = new JSONObject();
                        String id = cursor.getString(cursor.getColumnIndex("id"));
                        updatedIDs.add(id);
                        values.put("id", id);
                        values.put("farmer_id", cursor.getString(cursor.getColumnIndex("farmer_id")));
                        // values.put("emp_no", cursor.getString(cursor.getColumnIndex("emp_no")));
                        values.put("empost_designation", cursor.getString(cursor.getColumnIndex("empost_designation")));
                        values.put("emp_fullname", cursor.getString(cursor.getColumnIndex("emp_fullname")));
                        values.put("emp_id", cursor.getString(cursor.getColumnIndex("emp_id")));
                        values.put("order_id", cursor.getString(cursor.getColumnIndex("order_id")));
                        values.put("emp_cnic", cursor.getString(cursor.getColumnIndex("emp_cnic")));
                        values.put("empost_imei", cursor.getString(cursor.getColumnIndex("empost_imei")));
                        values.put("region", cursor.getString(cursor.getColumnIndex("region")));
                        values.put("district_basedata", cursor.getString(cursor.getColumnIndex("district_basedata")));
                        values.put("tehsil_basedata", cursor.getString(cursor.getColumnIndex("tehsil_basedata")));
                        values.put("markaz_name", cursor.getString(cursor.getColumnIndex("markaz_name")));
                        values.put("uc_name", cursor.getString(cursor.getColumnIndex("uc_name")));
                        values.put("village_muza_basedata", cursor.getString(cursor.getColumnIndex("village_muza_basedata")));
                        values.put("farmer_name", cursor.getString(cursor.getColumnIndex("farmer_name")));
                        values.put("farmer_fatherhusband_name", cursor.getString(cursor.getColumnIndex("farmer_fatherhusband_name")));
                        values.put("gender", cursor.getString(cursor.getColumnIndex("gender")));
                        values.put("farmer_cellphone", cursor.getString(cursor.getColumnIndex("farmer_cellphone")));
                        values.put("alt_farmer_name", cursor.getString(cursor.getColumnIndex("alt_farmer_name")));
                        values.put("alt_relation_with_farmer", cursor.getString(cursor.getColumnIndex("alt_relation_with_farmer")));
                        values.put("alt_farmer_cellphone", cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone")));
                        values.put("ao_assigned", cursor.getString(cursor.getColumnIndex("ao_assigned")));
                        values.put("fa_assigned", cursor.getString(cursor.getColumnIndex("fa_assigned")));
                        values.put("ai_assigned", cursor.getString(cursor.getColumnIndex("ai_assigned")));
                        values.put("unique_id", cursor.getString(cursor.getColumnIndex("unique_id")));
                        values.put("survey_status", cursor.getString(cursor.getColumnIndex("survey_status")));
                        values.put("reason", cursor.getString(cursor.getColumnIndex("reason")));
                        values.put("survey_status_org_call_on", cursor.getString(cursor.getColumnIndex("survey_status_org_call_on")));
                        values.put("survey_status_org", cursor.getString(cursor.getColumnIndex("survey_status_org")));
                        values.put("survey_status_org_reason", cursor.getString(cursor.getColumnIndex("survey_status_org_reason")));
                        values.put("survey_status_alt_call_on", cursor.getString(cursor.getColumnIndex("survey_status_alt_call_on")));
                        values.put("survey_status_alt", cursor.getString(cursor.getColumnIndex("survey_status_alt")));
                        values.put("survey_status_alt_reason", cursor.getString(cursor.getColumnIndex("survey_status_alt_reason")));


                        //  MubLog.cpnsoleLog("inside getColumnIndex"+cursor.getColumnIndex("survey_status_alt_call_on"));

                        // MubLog.cpnsoleLog("inside getString"+ cursor.getString(cursor.getColumnIndex("survey_status_alt_call_on")));

//                        values.put("1", cursor.getString(cursor.getColumnIndex("survey_status_alt_call_on")));
//                        values.put("2", cursor.getString(cursor.getColumnIndex("sc1")));
//                        values.put("3", cursor.getString(cursor.getColumnIndex("sc1")));


                        values.put("sc1", cursor.getString(cursor.getColumnIndex("sc1")));
                        values.put("sc1_reason", cursor.getString(cursor.getColumnIndex("sc1_reason")));
                        values.put("sc1_date_time", cursor.getString(cursor.getColumnIndex("sc1_date_time")));
                        values.put("sc2_reason", cursor.getString(cursor.getColumnIndex("sc2_reason")));
                        values.put("sc2", cursor.getString(cursor.getColumnIndex("sc2")));
                        values.put("sc2_date_time", cursor.getString(cursor.getColumnIndex("sc2_date_time")));
                        values.put("sc3", cursor.getString(cursor.getColumnIndex("sc3")));
                        values.put("sc3_reason", cursor.getString(cursor.getColumnIndex("sc3_reason")));
                        values.put("sc3_date_time", cursor.getString(cursor.getColumnIndex("sc3_date_time")));
                        values.put("duration", cursor.getString(cursor.getColumnIndex("duration")));
                        values.put("add_farmer_no", cursor.getString(cursor.getColumnIndex("add_farmer_no")));
                        values.put("sc1_alt", cursor.getString(cursor.getColumnIndex("sc1_alt")));
                        values.put("sc1_alt_reason", cursor.getString(cursor.getColumnIndex("sc1_alt_reason")));
                        values.put("sc1_date_time_alt", cursor.getString(cursor.getColumnIndex("sc1_date_time_alt")));
                        values.put("sc2_alt", cursor.getString(cursor.getColumnIndex("sc2_alt")));
                        values.put("sc2_alt_reason", cursor.getString(cursor.getColumnIndex("sc2_alt_reason")));
                        values.put("sc2_date_time_alt", cursor.getString(cursor.getColumnIndex("sc2_date_time_alt")));
                        values.put("sc3_alt", cursor.getString(cursor.getColumnIndex("sc3_alt")));
                        values.put("sc3_alt_reason", cursor.getString(cursor.getColumnIndex("sc3_alt_reason")));
                        values.put("sc3_date_time_alt", cursor.getString(cursor.getColumnIndex("sc3_date_time_alt")));
                        values.put("duration_alt", cursor.getString(cursor.getColumnIndex("duration_alt")));
                        values.put("add_farmer_no_alt", cursor.getString(cursor.getColumnIndex("add_farmer_no")));
                        values.put("sync", cursor.getString(cursor.getColumnIndex("sync")));
                        //values.put("sync", "2");
                        values.put("month", cursor.getString(cursor.getColumnIndex("month")));
                        values.put("rcons_user", cursor.getString(cursor.getColumnIndex("rcons_user")));
                        values.put("deviceid", cursor.getString(cursor.getColumnIndex("deviceid")));
                        values.put("temp1", cursor.getString(cursor.getColumnIndex("temp1")));
                        values.put("temp2", cursor.getString(cursor.getColumnIndex("temp2")));
                        values.put("temp3", "");
                        values.put("temp4", "");
                        values.put("temp5", "");
                        values.put("insert_or_updated_in_phone_at", cursor.getString(cursor.getColumnIndex("insert_or_updated_in_phone_at")));
                        values.put("call_duration_reason", cursor.getString(cursor.getColumnIndex("call_duration_reason")));

                        values.put("build_no",cursor.getString( cursor.getColumnIndex("build_no")));
                        values.put("uploaded_time", uploaded_time);

                        values.put("enum_code", cursor.getString(cursor.getColumnIndex("enum_code")));
                        values.put("enum_name", cursor.getString(cursor.getColumnIndex("enum_name")));


                        jsonArray.put(values);

//
                    } catch (Exception e) {

                    }

                } while (cursor.moveToNext());
            }
            cursor.close();

        }
        if (jsonArray == null || jsonArray.length() == 0) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            return;
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, synDataUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean Success = response.getBoolean("Success");
                    if (Success) {
                        Toast.makeText(MainMenuActivity.this, "Successfully updated data. count " + updatedIDs.size(), Toast.LENGTH_SHORT).show();

                        MubLog.cpnsoleLog("Successfully updated data count " + updatedIDs.size());

//                        for (int k=0; k<updatedIDs.size(); k++) {
//                            String id = updatedIDs.get(k);
//                            MubLog.cpnsoleLog("updating id "+id);
//                            adapter.updateCallSyncStatus(id, "2");
//                        }
                    } else {
                        Toast.makeText(MainMenuActivity.this, "Unable to update data. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    FetchCallsCounters();
                } catch (Exception e) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(MainMenuActivity.this, "Unable to sync. Please try again.", Toast.LENGTH_SHORT).show();
                }
                Log.e("Final Call", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(MainMenuActivity.this, "Unable to sync. Please try again.", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "" + error.getMessage() + "," + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> jsonObject = new HashMap<String, String>();
                return jsonObject;
            }

            @Override
            public String getBodyContentType() {
                return /*(for exmaple)*/ "application/json";
            }


            @Override
            public byte[] getBody() {
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    return jsonArray == null ? null : jsonArray.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", jsonArray.toString(), "utf-8");
                    return null;
                }
            }
        };


        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))//check if sd card is mounted
        {
            exportReportDataonSdcard(jsonArray);

        } else {
            Toast.makeText(MainMenuActivity.this, "Sdcard Export Issue", Toast.LENGTH_LONG).show();

        }

        MubLog.cpnsoleLog("Uploading to server is disabled ");
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        sr.setRetryPolicy(new DefaultRetryPolicy(2500000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(sr);

    }

    private void SyncSectionsDataToServer(String message) {
        String synDataUrl = "http://webapi.rconsdata.com/api/farmercallback/data";
        final ProgressDialog progressDialog = new ProgressDialog(MainMenuActivity.this);
        progressDialog.setMessage(message);
        progressDialog.show();
        progressDialog.setCancelable(false);

        String whereQuery = " WHERE farmer_id IN ";
        ArrayList<String> farmerIDs = new ArrayList<>();

        Cursor farmersCoursor = adapter.SelectCompletedSectionFarmers();
        if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
            Toast.makeText(this, "No completed form exist", Toast.LENGTH_SHORT).show();
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            return;

        } else {
            if (farmersCoursor.moveToFirst()) {
                do {
                    String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
                    if (!StringUtils.isEmpty(formerID)) {
                        farmerIDs.add(formerID);
                    }

                } while (farmersCoursor.moveToNext());

            }
        }
        String subQuery = "";
        for (int i = 0; i < farmerIDs.size(); i++) {
            if (i == 0) {
                subQuery = farmerIDs.get(i);
            } else if (i < farmerIDs.size()) {
                subQuery = subQuery + " , " + farmerIDs.get(i);
            } else {
                subQuery = subQuery + " , " + farmerIDs.get(i);
            }
        }

        subQuery = "( " + subQuery + " )";
        whereQuery = whereQuery + subQuery;
        Log.e("SubQuery", whereQuery + subQuery);

        MubLog.cpnsoleLog("SubQuery:" + whereQuery + subQuery);
        JSONObject finalObject = new JSONObject();
        try {


            Log.e("TAG", finalObject.toString());
        } catch (Exception e) {

        }

        if (finalObject == null || finalObject.length() == 0) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            return;
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, synDataUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    boolean Success = response.getBoolean("Success");
                    if (Success) {
                        Toast.makeText(MainMenuActivity.this, "Successfully updated data.", Toast.LENGTH_SHORT).show();
                        Cursor remainingFormCursor = adapter.SelectCompletedSectionFarmers();
                        if (remainingFormCursor != null && remainingFormCursor.getCount() > 0) {
                            SyncSectionsDataToServer("Syncing Section data....");
                        }
                    } else {
                        Toast.makeText(MainMenuActivity.this, "Unable to update data. Please try again.", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(MainMenuActivity.this, "Unable to sync. Please try again.", Toast.LENGTH_SHORT).show();

                }


                Log.e("Final Call", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(MainMenuActivity.this, "Unable to sync. Please try again.", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "" + error.getMessage() + "," + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> jsonObject = new HashMap<String, String>();
                return jsonObject;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }


            @Override
            public byte[] getBody() {
                try {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    return null;
                } catch (Exception uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", finalObject.toString(), "utf-8");
                    return null;
                }
            }
        };

        sr.setRetryPolicy(new DefaultRetryPolicy(2500, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(sr);







    }

    void blinkng_logo() {
        ImageView btnLogo = findViewById(R.id.btnLogo);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(10);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setStartOffset(1500);
        fadeOut.setDuration(1500);
        fadeOut.setRepeatCount(Animation.INFINITE);
        fadeOut.setRepeatMode(Animation.REVERSE);

        AnimationSet animation = new AnimationSet(false); //change to false
        //animation.addAnimation(fadeIn);
        animation.addAnimation(fadeOut);
        btnLogo.setAnimation(animation);
    }

    void screensize() {
        blinkng_logo();
        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        TextView txtappname = findViewById(R.id.txtappname);
        TextView txtlogout = findViewById(R.id.txtLogout);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView txtAppVersion = findViewById(R.id.txtAppVersion);
        TextView txt_newcalls = findViewById(R.id.txt_newcalls);
        TextView tv_pending = findViewById(R.id.tv_pending);
        TextView tv_complted = findViewById(R.id.tv_complted);
        TextView tv_suucessful = findViewById(R.id.tv_suucessful);
        TextView tv_edit_form = findViewById(R.id.tv_edit_form);

        TextView tv_import_data = findViewById(R.id.tv_import_data);
        TextView tv_sync_qst = findViewById(R.id.tv_sync_qst);
        TextView tv_exit_now = findViewById(R.id.tv_exit_now);
        txtPendingCallCounter = findViewById(R.id.txtPendingCallCounter);
        txtNewCallCounter = findViewById(R.id.txtNewCallCounter);
        txtCompletedCallCounter = findViewById(R.id.txtCompletedCallCounter);
        txtSuccessfullCallCounter = findViewById(R.id.txtSuccessfullCallCounter);
        txtSyncCompleteQuestionnaireCounter = findViewById(R.id.txtSyncCompleteQuestionnaireCounter);
        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:

                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                txtNewCallCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                txtPendingCallCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                txtCompletedCallCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                txtSuccessfullCallCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                txtSyncCompleteQuestionnaireCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                txtappname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                txtlogout.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                txtAppVersion.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                txt_newcalls.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_pending.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_complted.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_suucessful.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_edit_form.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_import_data.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_sync_qst.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tv_exit_now.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                Toast.makeText(getApplicationContext(), "Small Screen Some Font Size May be disturb", Toast.LENGTH_LONG).show();
                break;

        }

    }

    private void exportReportDataonSdcard(JSONArray dataToUpload) {
        try {

            JSONObject allSectiondata = new JSONObject();
            try {
                allSectiondata.put("ReportData", dataToUpload);


                String fileName = com.mubashar.dateandtime.filemanager.FileManager.createFileName(MainMenuActivity.this.getApplicationContext(), "PhoneQuestionnaire_ReportData", "Rcons");


                //  File directory = new File(MainMenuActivity.this.getApplicationContext().getExternalFilesDir(null),MainMenuActivity.this.getApplicationContext().getString(R.string.app_name));
                File directory = new File(Environment.getExternalStorageDirectory() + "/Rcons/" + MubDateAndTime.INSTANCE.getcurrentTime(6));


                if (directory.isDirectory()) {

                } else {
                    DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + directory.getName());
                    DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + directory.getAbsolutePath());

                    DebugLog.console("[MyTask1] inside exportLogFile() created " + directory.mkdir());
                    DebugLog.console("[MyTask1] inside exportLogFile() created " + directory.mkdirs());
                    DebugLog.console("[MyTask1] inside exportLogFile() getPath " + directory.getPath());
                }
                File newFile = new File(directory.getPath() + "/" + fileName);
                MubLog.cpnsoleLog("newFile " + newFile.toString());
                MubLog.cpnsoleLog("fileName " + fileName);
                FileOutputStream fos = new FileOutputStream(newFile, true);
                fos.write(allSectiondata.toString().getBytes("UTF-8"));
                fos.close();
                Toast.makeText(MainMenuActivity.this, "ReportData Exported at " + newFile.getPath(), Toast.LENGTH_SHORT).show();


                // boolean created =  com.mubashar.dateandtime.filemanager.FileManager.createNewFileOnSdCarddownloads(MainMenuActivity.this.getApplicationContext(),backupDB.toString(),allSectiondata.toString());
                //   MubLog.cpnsoleLog("created "+created);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(MainMenuActivity.this).writeLog("[MainMenuActivity] inside exportDataonSdcard() Exception is :" + e.toString());
        }
    }

   // private void exportDataonSdcard(JSONArray dataToUploadB, JSONArray dataToUploadC, JSONArray dataToUploadD, JSONArray dataToUploadE, JSONArray readSectionFTable1ForDataSync, JSONArray readSectionFTable2ForDataSync, JSONArray readSectionFTable3ForDataSync, JSONArray readSectionFTable4ForDataSync, JSONArray readSectionFTable5ForDataSync, JSONArray readSectionFTable6ForDataSync, JSONArray readSectionGForDataSync, JSONArray readSectionGBForDataSync, JSONArray readSectionHForDataSync, JSONArray readSectionOneForDataSync) {
    private void exportDataonSdcard(JSONArray dataToUploadA) {
        try {

            JSONObject allSectiondata = new JSONObject();
            try {


                allSectiondata.put("phn_quest_section_a", dataToUploadA);
//                allSectiondata.put("section_b", dataToUploadB);
//                allSectiondata.put("section_c", dataToUploadC);
//                allSectiondata.put("section_d", dataToUploadD);
//                allSectiondata.put("section_e", dataToUploadE);
//                allSectiondata.put("section_f_table1", readSectionFTable1ForDataSync);
//                allSectiondata.put("section_f_table2", readSectionFTable2ForDataSync);
//                allSectiondata.put("section_f_table3", readSectionFTable3ForDataSync);
//                allSectiondata.put("section_f_table4", readSectionFTable4ForDataSync);
//                allSectiondata.put("section_f_table5", readSectionFTable5ForDataSync);
//                allSectiondata.put("section_f_table6", readSectionFTable6ForDataSync);
//                allSectiondata.put("section_g", readSectionGForDataSync);
//                allSectiondata.put("section_g_b", readSectionGBForDataSync);
//                allSectiondata.put("section_h", readSectionHForDataSync);
//                allSectiondata.put("section_1", readSectionOneForDataSync);


                String fileName = com.mubashar.dateandtime.filemanager.FileManager.createFileName(MainMenuActivity.this.getApplicationContext(), "PhoneQuestionnaire_SectionData", "Rcons");


                //  File directory = new File(MainMenuActivity.this.getApplicationContext().getExternalFilesDir(null),MainMenuActivity.this.getApplicationContext().getString(R.string.app_name));
                File directory = new File(Environment.getExternalStorageDirectory() + "/Rcons/" + MubDateAndTime.INSTANCE.getcurrentTime(6));


                if (directory.isDirectory()) {

                } else {
                    DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + directory.getName());
                    DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + directory.getAbsolutePath());

                    DebugLog.console("[MyTask1] inside exportLogFile() created " + directory.mkdir());
                    DebugLog.console("[MyTask1] inside exportLogFile() created " + directory.mkdirs());
                    DebugLog.console("[MyTask1] inside exportLogFile() getPath " + directory.getPath());
                }
                File newFile = new File(directory.getPath() + "/" + fileName);
                MubLog.cpnsoleLog("newFile " + newFile.toString());
                MubLog.cpnsoleLog("fileName " + fileName);
                FileOutputStream fos = new FileOutputStream(newFile, true);
                fos.write(allSectiondata.toString().getBytes("UTF-8"));
                fos.close();
                Toast.makeText(MainMenuActivity.this, "Data Exported at " + newFile.getPath(), Toast.LENGTH_SHORT).show();


                // boolean created =  com.mubashar.dateandtime.filemanager.FileManager.createNewFileOnSdCarddownloads(MainMenuActivity.this.getApplicationContext(),backupDB.toString(),allSectiondata.toString());
                //   MubLog.cpnsoleLog("created "+created);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(MainMenuActivity.this).writeLog("[MainMenuActivity] inside exportDataonSdcard() Exception is :" + e.toString());
        }
    }


    public void sectiondataManualClick(String message) {
        try {

            SyncDataToServer();
            MubLog.cpnsoleLog("inside sectiondataManualClick");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            } else {


                progressDialog2 = new ProgressDialog(MainMenuActivity.this);
                progressDialog2.setMessage(message);
                progressDialog2.show();
                progressDialog2.setCancelable(false);

                String whereQuery = " WHERE farmer_id IN ";
                String nowhereQuery = " WHERE farmer_id NOT IN ";
                ArrayList<String> farmerIDs = new ArrayList<>();

                ArrayList<String> excude_farmerIDs = new ArrayList<>();

                Cursor farmersCoursor = adapter.SelectCompletedSectionFarmers();
                if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
                    Toast.makeText(this, "No completed form exist", Toast.LENGTH_SHORT).show();
                    if (progressDialog2 != null && progressDialog2.isShowing()) {
                        progressDialog2.dismiss();
                    }
                    return;

                } else {
                    if (farmersCoursor.moveToFirst()) {
                        do {
                            String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
                            if (!StringUtils.isEmpty(formerID)) {


                                String userName_against_farmer_id = adapter.getUserNameagainstFarmerID(formerID);
//adding in farmerIDs.add(formerID); for upload all adding in this excude_farmerIDs.add(formerID); to exclude from deletion
                                if (userName.equalsIgnoreCase(userName_against_farmer_id)) {
                                    farmerIDs.add(formerID);
                                } else {

                                    farmerIDs.add(formerID);
                                    excude_farmerIDs.add(formerID);
                                    MubLog.cpnsoleLog("inside userName" + userName + "is not equal to " + userName_against_farmer_id);
                                }

                            }

                        } while (farmersCoursor.moveToNext());

                    }
                }
                String subQuery = "";
                for (int i = 0; i < farmerIDs.size(); i++) {
                    if (i == 0) {
                        subQuery = farmerIDs.get(i);
                    } else if (i < farmerIDs.size()) {
                        subQuery = subQuery + " , " + farmerIDs.get(i);
                    } else {
                        subQuery = subQuery + " , " + farmerIDs.get(i);
                    }
                }

                subQuery = "( " + subQuery + " )";
                MubLog.cpnsoleLog("subquery " + subQuery);
                MubLog.cpnsoleLog("where  " + whereQuery + subQuery);


                String notinquerry = getpendingcallsFarmerIds(excude_farmerIDs);
                if (notinquerry.equalsIgnoreCase("")) {
                    notinquerry = "";
                } else {
                    notinquerry = nowhereQuery + notinquerry;
                    MubLog.cpnsoleLog("notinquerry" + notinquerry);
                }

                JSONArray dataToUploadA = adapter.readSectionAForDataSync(whereQuery + subQuery);//adapter.getSectionBData_ALL();
//                JSONArray dataToUploadB = adapter.readSectionBForDataSync(whereQuery + subQuery);//adapter.getSectionBData_ALL();
//                JSONArray dataToUploadC = adapter.readSectionCForDataSync(whereQuery + subQuery);//adapter.getSectionCData_ALL();
//                JSONArray dataToUploadD = adapter.readSectionDForDataSync(whereQuery + subQuery);//adapter.getSectionDData_ALL();
//                JSONArray dataToUploadE = adapter.readSectionEForDataSync(whereQuery + subQuery);//adapter.getSectionEData_ALL();
//
//                JSONArray readSectionFTable1ForDataSync = adapter.readSectionFTable1ForDataSync(whereQuery + subQuery);
//                JSONArray readSectionFTable2ForDataSync = adapter.readSectionFTable2ForDataSync(whereQuery + subQuery);
//                JSONArray readSectionFTable3ForDataSync = adapter.readSectionFTable3ForDataSync(whereQuery + subQuery);
//                JSONArray readSectionFTable4ForDataSync = adapter.readSectionFTable4ForDataSync(whereQuery + subQuery);
//                JSONArray readSectionFTable5ForDataSync = adapter.readSectionFTable5ForDataSync(whereQuery + subQuery);
//                JSONArray readSectionFTable6ForDataSync = adapter.readSectionFTable6ForDataSync(whereQuery + subQuery);
//                JSONArray readSectionGForDataSync = adapter.readSectionGForDataSync(whereQuery + subQuery);
//                JSONArray readSectionGBForDataSync = adapter.readSectionGBForDataSync(whereQuery + subQuery);
//                JSONArray readSectionHForDataSync = adapter.readSectionHForDataSync(whereQuery + subQuery);
//                JSONArray readSectionOneForDataSync = adapter.readSectionOneForDataSync(whereQuery + subQuery);


                // if(dataToUploadB.length()>0||dataToUploadC.length()>0||dataToUploadD.length()>0||dataToUploadE.length()>0){
                if (dataToUploadA.length() > 0) {

                } else {
                    Toast.makeText(MainMenuActivity.this, "No data to sync.", Toast.LENGTH_SHORT).show();
                    btnSyncSectionData.setEnabled(true);
                    return;

                }

                if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))//check if sd card is mounted
                {
                  //  exportDataonSdcard(dataToUploadB, dataToUploadC, dataToUploadD, dataToUploadE, readSectionFTable1ForDataSync, readSectionFTable2ForDataSync, readSectionFTable3ForDataSync, readSectionFTable4ForDataSync, readSectionFTable5ForDataSync, readSectionFTable6ForDataSync, readSectionGForDataSync, readSectionGBForDataSync, readSectionHForDataSync, readSectionOneForDataSync);
                    exportDataonSdcard(dataToUploadA);

                    //   adapter.deleteAllSectionData(userName,notinquerry);

                } else {
                    Toast.makeText(MainMenuActivity.this, "Sdcard Export Issue", Toast.LENGTH_LONG).show();

                }
                if (RConsUtils.isNetworkAvailable(MainMenuActivity.this)) {


                    MubLog.cpnsoleLog("inside data to upload ");
                    JSONObject allSectiondata = new JSONObject();
                    allSectiondata.put("phn_quest_section_a", dataToUploadA);
//                    allSectiondata.put("section_b", dataToUploadB);
//                    allSectiondata.put("section_c", dataToUploadC);
//                    allSectiondata.put("section_d", dataToUploadD);
//                    allSectiondata.put("section_e", dataToUploadE);
//                    allSectiondata.put("section_f_table1", readSectionFTable1ForDataSync);
//                    allSectiondata.put("section_f_table2", readSectionFTable2ForDataSync);
//                    allSectiondata.put("section_f_table3", readSectionFTable3ForDataSync);
//                    allSectiondata.put("section_f_table4", readSectionFTable4ForDataSync);
//                    allSectiondata.put("section_f_table5", readSectionFTable5ForDataSync);
//                    allSectiondata.put("section_f_table6", readSectionFTable6ForDataSync);
//                    allSectiondata.put("section_g", readSectionGForDataSync);
//                    allSectiondata.put("section_g_b", readSectionGBForDataSync);
//                    allSectiondata.put("section_h", readSectionHForDataSync);
//                    allSectiondata.put("section_1", readSectionOneForDataSync);

                    SyncSectionDataToServer(allSectiondata, "All", notinquerry);


//                    if (dataToUploadB.length() > 0) {
//                        SyncSectionDataToServer(dataToUploadB, "B");
//                    }

//                    if (dataToUploadB.length() > 0) {
//                        SyncSectionDataToServer(dataToUploadB, "B");
//                    }
//                    MubLog.cpnsoleLog("inside data to upload " + dataToUploadC.length());
//                    if (dataToUploadC.length() > 0) {
//                        SyncSectionDataToServer(dataToUploadC, "C");
//                    }
//                    MubLog.cpnsoleLog("inside data to upload " + dataToUploadD.length());
//                    if (dataToUploadD.length() > 0) {
//                        SyncSectionDataToServer(dataToUploadD, "D");
//                    }
//
//                    MubLog.cpnsoleLog("inside data to upload " + dataToUploadE.length());
//                    if (dataToUploadE.length() > 0) {
//                        SyncSectionDataToServer(dataToUploadE, "E");
//                    }


                } else {
                    Toast.makeText(MainMenuActivity.this, "No internet available.", Toast.LENGTH_SHORT).show();
                    if (progressDialog2 != null && progressDialog2.isShowing()) {
                        progressDialog2.dismiss();
                    }
                }


            }

            btnSyncSectionData.setEnabled(true);
        } catch (Exception e) {
            btnSyncSectionData.setEnabled(true);
            if (progressDialog2 != null && progressDialog2.isShowing()) {
                progressDialog2.dismiss();
            }
            EmailDebugLog.getInstance(MainMenuActivity.this).writeLog("[MainMenuActivity] inside onClick() Exception is :" + e.toString());
        }
    }

    private String getpendingcallsFarmerIds(ArrayList<String> excude_farmerIDs) {

        String querry = "";
        try {

            Cursor farmersCoursor = adapter.getFarmerPendingCalls(userName);
            if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
                //Toast.makeText(this, "No pending call exist", Toast.LENGTH_SHORT).show();

                // return querry;

            } else {
                if (farmersCoursor.moveToFirst()) {
                    do {
                        String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
                        if (!StringUtils.isEmpty(formerID)) {
                            excude_farmerIDs.add(formerID);
                        }

                    } while (farmersCoursor.moveToNext());

                }
            }
            String subQuery = "";
            for (int i = 0; i < excude_farmerIDs.size(); i++) {
                if (i == 0) {
                    subQuery = excude_farmerIDs.get(i);
                } else if (i < excude_farmerIDs.size()) {
                    subQuery = subQuery + " , " + excude_farmerIDs.get(i);
                } else {
                    subQuery = subQuery + " , " + excude_farmerIDs.get(i);
                }
            }

            subQuery = "( " + subQuery + " )";
            MubLog.cpnsoleLog("subquery pending calls" + subQuery);
            MubLog.cpnsoleLog("where  " + querry + subQuery);

            if (excude_farmerIDs.size() > 0) {

            } else {
                subQuery = "";
            }


            return subQuery;
        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[MainMenuActivity] inside getpendingcallsFarmerIds() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside getpendingcallsFarmerIds" + e.toString());
            return querry;
        }
    }


    private void SyncSectionDataToServer(JSONObject allSectiondata, String section, String whereQuerysubQuery) {


        ArrayList<String> updatedIDs = new ArrayList<>();
//        final ProgressDialog progressDialog = new ProgressDialog(MainMenuActivity.this);
//        progressDialog.setMessage("Syncing  Data...");
//        progressDialog.show();
//        progressDialog.setCancelable(false);

        String synDataUrl = "http://webapi.rconsdata.com/api/ffbs/report";

        if (section.equalsIgnoreCase("All"))
            synDataUrl = "http://rconsdb.org/devteam/general/services/ffbcsr/phoneq_secdata.php";

        if (section.equalsIgnoreCase("B"))
            synDataUrl = "http://rconsdb.org/devteam/general/services/ffbcsr/reqB.php";

        if (section.equalsIgnoreCase("C"))
            synDataUrl = "http://rconsdb.org/devteam/general/services/ffbcsr/reqC.php";

        if (section.equalsIgnoreCase("D"))
            synDataUrl = "http://rconsdb.org/devteam/general/services/ffbcsr/reqD.php";

        if (section.equalsIgnoreCase("E"))
            synDataUrl = "http://rconsdb.org/devteam/general/services/ffbcsr/reqE.php";

        MubLog.cpnsoleLog("Section " + section);

        MubLog.cpnsoleLog("synDataUrl" + synDataUrl);

        if (allSectiondata == null || allSectiondata.length() == 0) {
            if (progressDialog2 != null && progressDialog2.isShowing()) {
                progressDialog2.dismiss();
            }
            return;
        }

        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.POST, synDataUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean Success = response.getBoolean("Success");
                    if (Success) {
                        MubLog.cpnsoleLog("Successfully updated data Section");

                        Toast.makeText(MainMenuActivity.this, "Successfully updated data Section." + section, Toast.LENGTH_SHORT).show();
//                        if(section.equalsIgnoreCase("B"))
//                            adapter.deleteAllSectionData(DatabaseAdapter.SectionBTable);
//                        if(section.equalsIgnoreCase("C"))
//                            adapter.deleteAllSectionData(DatabaseAdapter.SectionCTable);
//
//                        if(section.equalsIgnoreCase("D"))
//                            adapter.deleteAllSectionData(DatabaseAdapter.SectionDTable);
//
//                        if(section.equalsIgnoreCase("E"))
//                            adapter.deleteAllSectionData(DatabaseAdapter.SectionETable);

                        if (section.equalsIgnoreCase("All")) {
                            MubLog.cpnsoleLog("inside write code to delete uploaded ");
                            adapter.deleteAllSectionData(userName, whereQuerysubQuery);

                        }
                        FetchCallsCounters();
                        if (progressDialog2 != null && progressDialog2.isShowing()) {
                            progressDialog2.dismiss();
                        }

                    } else {
                        Toast.makeText(MainMenuActivity.this, "Unable to update data. Please try again." + section, Toast.LENGTH_SHORT).show();
                    }
                    if (progressDialog2 != null && progressDialog2.isShowing()) {
                        progressDialog2.dismiss();
                    }
                } catch (Exception e) {
                    if (progressDialog2 != null && progressDialog2.isShowing()) {
                        progressDialog2.dismiss();
                    }
                    Toast.makeText(MainMenuActivity.this, section + "Unable to sync. Please try again.", Toast.LENGTH_SHORT).show();

                }


                Log.e("Final Call", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog2.dismiss();
                }
                Toast.makeText(MainMenuActivity.this, "Unable to sync. Please try again.", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "" + error.getMessage() + "," + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> jsonObject = new HashMap<String, String>();
                return jsonObject;
            }

            @Override
            public String getBodyContentType() {
                return /*(for exmaple)*/ "application/json";
            }


            @Override
            public byte[] getBody() {
                try {

                    return allSectiondata == null ? null : allSectiondata.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", allSectiondata.toString(), "utf-8");
                    return null;
                }
            }
        };


//        MubLog.cpnsoleLog("uploading is disabled for testing");

        sr.setRetryPolicy(new DefaultRetryPolicy(25000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MubLog.cpnsoleLog("added in que");
        AppController.getInstance().addToRequestQueue(sr);


        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();


        try {
            //updating Question table question no 37
            MubLog.cpnsoleLog("inside onStart");
           // databaseAccess.update_Question_table_q_no_37();

            TextView tv_selectvillage_now = findViewById(R.id.tv_selectvillage_now);
            tv_selectvillage_now.setText("Select Village Code: \r\n Current Selected Code : "+ HHIDConfigurations.getPeshawarCurrentPSU(MainMenuActivity.this));

            String  enum_code = "ALTER TABLE `"+DatabaseAdapter.FarmerCallBackTable+"` ADD `enum_code` TEXT DEFAULT '' ";
            //databaseAccess. createMissingColumn(enum_code);

            String  enum_name = "ALTER TABLE `"+DatabaseAdapter.FarmerCallBackTable+"` ADD `enum_name` TEXT DEFAULT '' ";
            //databaseAccess. createMissingColumn(enum_name);



        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[MainMenuActivity] inside onStart() Exception is :"+e.toString());
            Toast.makeText(MainMenuActivity.this, "Error in Question table please contact admin.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 99) {
                enum_code = data.getStringExtra("enumCode");
                enum_name = data.getStringExtra("enumName");

                txtEnumName.setText(enum_name);
                txtEnumCode.setText(enum_code);

            }
        }


    }






    void SetEnumState() {
        if (RConsUtils.GetEnumState() == 1) {
            enum_name = RConsUtils.getEnumName();
            enum_code = RConsUtils.getEnumCode();
            enumName_layout.setVisibility(View.GONE);
            MainContent.setVisibility(View.VISIBLE);
            tvTitle.setText("RconsUser " + userName + " & Enum " + enum_name);
        } else {
            enumName_layout.setVisibility(View.VISIBLE);
            MainContent.setVisibility(View.GONE);
        }
    }
}
