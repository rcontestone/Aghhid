package com.rcons.fcallbacks.Main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.MubDateAndTime;
import com.mubashar.mubchatheadservice.ChatHeadService;
import com.rcons.fcallbacks.Athreehhid.HH_Screen_eight_Section_e;
import com.rcons.fcallbacks.Athreehhid.HH_Screen_one_Section_h;
import com.rcons.fcallbacks.Athreehhid.HH_Screen_one_section_e;
import com.rcons.fcallbacks.Athreehhid.HH_Screen_two;
import com.rcons.fcallbacks.Athreehhid.HouseHoldDataBaseHelper;
import com.rcons.fcallbacks.EditForm.SectionsSelections;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_A;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_B;
import com.rcons.fcallbacks.ParentalQuestionnaire.pq_Section_C1;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CallMenuActivity extends AppCompatActivity {

    DatabaseAdapter databaseAccess;
    Cursor cursor;
    TextView idtextview;
    TextView txtAlternateMobileLabel;
    TextView txtOrderID;
    TextView txtEmployeeID;
    ImageView btnBack;
    Button btnAddReport;
    Button btnAssignStaff;
    //Button btnKnowledgeTest;
    Button btnEditForm;
    Button btnQuestionnaire;

    Button NextCall, PreviousCall, Main_Menu, btnCallNow;
    TextView txtOriginalFarmerName, txtAlternateFarmerName, FarmerMobile, Region, DistrictName, VillageName, UcName, TehsilName, MarkazName;

    String userName = "";
    String region = "";
    String emp_id = "";
    String order_id = "";
    Spinner numbers_sp_q_2 = null;
    boolean isFromEdit = false;
    boolean isPendingCall = false;
    boolean isAlternateFarmer = false;
    TextView txtRelationShip, txtRelationShipName;
    TextView txtReason;
    LinearLayout reasonLayout;
    LinearLayout alternateNameLayout;
    LinearLayout triesLayout;
    EditText edtSearchBar;
    private Button btnAddNumber;

    public static String LAST_SAVED_FARMER_ID = "";
    public static String LAST_SAVED_STATUS_ID = "";

    TextView onorignal;
    TextView onalternative;

    TextView txt_village_name, txt_school_code, txt_school_emis_code, txt_private_school_code, txt_hh_head_name, txt_Student_id, txt_district, txt_Tehsil, txt_mobile_number;

    String school_code, student_id;

    String s1 = "";
    String s2 = "";
    String s3 = "";
    String s4 = "";
    String s5 = "";
    String s6 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_menu);

        userName = getIntent().getStringExtra("username");
        isPendingCall = getIntent().getBooleanExtra("isPendingCall", true);


        txtAlternateMobileLabel = findViewById(R.id.txtAlternateMobileLabel);
        txtOrderID = findViewById(R.id.txtOrderID);
        txtEmployeeID = findViewById(R.id.txtEmployeeID);
        edtSearchBar = findViewById(R.id.edtSearchBar);
        btnAddNumber = findViewById(R.id.btnAddNumber);
        txtReason = findViewById(R.id.txtReason);
        reasonLayout = findViewById(R.id.reasonLayout);
        btnAssignStaff = findViewById(R.id.btnAssignStaff);
        // btnKnowledgeTest =  findViewById(R.id.btn_knowledge_test);
        btnEditForm = findViewById(R.id.btnEditForm);
        btnQuestionnaire = findViewById(R.id.btn_questionnaire);

        txtRelationShip = findViewById(R.id.txtRelationShip);
        btnBack = findViewById(R.id.btnBack);
        btnAddReport = findViewById(R.id.btnAddReport);
        NextCall = findViewById(R.id.next_call);
        PreviousCall = findViewById(R.id.prevs_call);
        btnCallNow = findViewById(R.id.btnCallNow);


        txtAlternateFarmerName = findViewById(R.id.txtAlternateFarmerName);
        txtOriginalFarmerName = findViewById(R.id.txtOriginalFarmerName);
        alternateNameLayout = findViewById(R.id.alternateNameLayout);
        FarmerMobile = findViewById(R.id.farmer_cellphone);
        txtRelationShipName = findViewById(R.id.txtRelationShipName);
        Region = findViewById(R.id.region);
        DistrictName = findViewById(R.id.district_basedata);
        VillageName = findViewById(R.id.village_muza_basedata);
        UcName = findViewById(R.id.uc_name);
        TehsilName = findViewById(R.id.tehsil_basedata);
        MarkazName = findViewById(R.id.markaz_name);


        idtextview = findViewById(R.id.idtextview);

        txt_village_name = findViewById(R.id.txt_village_name);
        txt_school_code = findViewById(R.id.txt_school_code);
        txt_school_emis_code = findViewById(R.id.txt_school_emis_code);
        txt_private_school_code = findViewById(R.id.txt_private_school_code);
        txt_hh_head_name = findViewById(R.id.txt_hh_head_name);
        txt_Student_id = findViewById(R.id.txt_Student_id);
        txt_district = findViewById(R.id.txt_district);
        txt_Tehsil = findViewById(R.id.txt_Tehsil);
        txt_mobile_number = findViewById(R.id.txt_mobile_number);


        triesLayout = findViewById(R.id.triesLayout);
        onorignal = findViewById(R.id.onorignal);
        onalternative = findViewById(R.id.onalternative);

//        CallStateListener callStateListener = new CallStateListener();
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
//        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        databaseAccess = new DatabaseAdapter(CallMenuActivity.this);

        databaseAccess.Open();

        RConsUtils.hideKeyboard(CallMenuActivity.this);

        if (isPendingCall) {
            edtSearchBar.setVisibility(View.VISIBLE);
            reasonLayout.setVisibility(View.GONE);
            triesLayout.setVisibility(View.VISIBLE);
            cursor = databaseAccess.aghhid_getPendingCallCursor(userName);
        } else {
            triesLayout.setVisibility(View.GONE);
            edtSearchBar.setVisibility(View.GONE);
            reasonLayout.setVisibility(View.GONE);
            cursor = databaseAccess.aghhid_getNewCallsCursor(userName);
        }


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            GetData();
            //  Toast.makeText(this, "Fetch and show data enable code and line . 178", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();

            }
        });
        NextCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopChatHeadService();
                if (!cursor.isLast()) {
                    cursor.moveToNext();
                    GetData();
                } else {
                    Toast.makeText(CallMenuActivity.this, "No Further record found.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        PreviousCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopChatHeadService();

                if (!cursor.isFirst()) {
                    cursor.moveToPrevious();
                    GetData();
                } else {
                    Toast.makeText(CallMenuActivity.this, "No Previous record found.", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnCallNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChatHeadService();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {

                    if(!numberSelected()){
                        return;
                    }else {
                        DialUserNumber();
                    }




                }

            }
        });

        btnAssignStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChatHeadService();
                Intent intent = new Intent(CallMenuActivity.this, AssignedStaffActivity.class);
                intent.putExtra("farmerID", cursor.getString(cursor.getColumnIndex("farmer_id")));
                startActivity(intent);
            }
        });
        btnEditForm.setOnClickListener(view -> {
            stopChatHeadService();
            String farmerID = cursor.getString(cursor.getColumnIndex("farmer_id"));
            if (!StringUtils.isEmpty(farmerID)) {
                Cursor cursor1 = databaseAccess.getSectionBData(farmerID);
                if (cursor1 != null && cursor1.getCount() > 0) {

                    Intent intent = new Intent(CallMenuActivity.this, SectionsSelections.class);
                    intent.putExtra("isFromEditActivity", false);

                    intent.putExtra("isPendingCall", isPendingCall);
                    intent.putExtra("username", userName);
                    intent.putExtra("isFromEdit", isFromEdit);
                    intent.putExtra("region", cursor.getString(cursor.getColumnIndex("region")));
                    intent.putExtra("emp_id", cursor.getString(cursor.getColumnIndex("emp_id")));
                    intent.putExtra("order_id", cursor.getString(cursor.getColumnIndex("order_id")));

                    intent.putExtra("farmerID", cursor.getString(cursor.getColumnIndex("farmer_id")));
                    intent.putExtra("farmerDistrict", cursor.getString(cursor.getColumnIndex("district_basedata")));
                    intent.putExtra("farmerVillage", cursor.getString(cursor.getColumnIndex("village_muza_basedata")));
                    intent.putExtra("farmerTehsil", cursor.getString(cursor.getColumnIndex("tehsil_basedata")));
                    intent.putExtra("ao_name", cursor.getString(cursor.getColumnIndex("ao_assigned")));
                    intent.putExtra("fa_name", cursor.getString(cursor.getColumnIndex("fa_assigned")));
                    intent.putExtra("ai_name", cursor.getString(cursor.getColumnIndex("ai_assigned")));
                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("id")));

                    if (isAlternateFarmer) {
                        intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone")));
                    } else {
                        intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("farmer_cellphone")));
                    }

                    intent.putExtra("isAlternateFarmer", isAlternateFarmer);


                    if (cursor.isLast()) {
                        startActivity(intent);
                        finish();

                    } else {
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(CallMenuActivity.this, "Form has not been started yet.", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(CallMenuActivity.this, " ID is empty.", Toast.LENGTH_SHORT).show();
            }


        });
//        btnKnowledgeTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CallMenuActivity.this, KnowledgeTest.class);
//                intent.putExtra("farmerID", cursor.getString(cursor.getColumnIndex("farmer_id")));
//                intent.putExtra("region", cursor.getString(cursor.getColumnIndex("region")));
//                startActivity(intent);
//            }
//        });

        btnQuestionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChatHeadService();


                if (!numberSelected()){


                    return;
                }



//                Intent intent = new Intent(CallMenuActivity.this, pq_Section_A.class);
                Intent intent = new Intent(CallMenuActivity.this, HH_Screen_two.class);
//                Intent intent = new Intent(CallMenuActivity.this, HH_Screen_eight_Section_e.class);

                intent.putExtra("isPendingCall", isPendingCall);
                intent.putExtra("username", userName);
                intent.putExtra("isFromEdit", isFromEdit);

                //intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("farmer_cellphone")));
                //intent.putExtra("strata", cursor.getString(cursor.getColumnIndex("strata")));
                intent.putExtra("scode", cursor.getString(cursor.getColumnIndex("village_id")));
                //  intent.putExtra("scode_original", cursor.getString(cursor.getColumnIndex("scode_original")));
                //  intent.putExtra("m2_district", cursor.getString(cursor.getColumnIndex("m2_district")));
                // intent.putExtra("m2_tehsil", cursor.getString(cursor.getColumnIndex("m2_tehsil")));
                // intent.putExtra("m2_mauza", cursor.getString(cursor.getColumnIndex("m2_mauza")));
                // intent.putExtra("m2_school_name", cursor.getString(cursor.getColumnIndex("m2_school_name")));
                //intent.putExtra("studentnumber", cursor.getString(cursor.getColumnIndex("studentnumber")));
                intent.putExtra("studentid", cursor.getString(cursor.getColumnIndex("hhid")));
                //  intent.putExtra("m1b_student_sex", cursor.getString(cursor.getColumnIndex("m1b_student_sex")));
                intent.putExtra("m1b_student_name", cursor.getString(cursor.getColumnIndex("hhid_father_name")));



                //intent.putExtra("m1b_parent_mobile", cursor.getString(cursor.getColumnIndex("hhid_phone_number")));
                intent.putExtra("m1b_parent_mobile", numbers_sp_q_2.getSelectedItem().toString());




                intent.putExtra("phone_order", cursor.getString(cursor.getColumnIndex("phone_order")));
                // intent.putExtra("m5_studentassessment", cursor.getString(cursor.getColumnIndex("m5_studentassessment")));
                // intent.putExtra("order_to_contact", cursor.getString(cursor.getColumnIndex("order_to_contact")));


                intent.putExtra("isFromEditActivity", false);
                if (isAlternateFarmer) {
                    intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("alt_farmer_cellphone")));
                } else {
                    intent.putExtra("farmer_cellphone", cursor.getString(cursor.getColumnIndex("hhid_phone_number")));
                }

                intent.putExtra("isAlternateFarmer", isAlternateFarmer);
                intent.putExtra("sc1", s1);
                intent.putExtra("sc2", s2);
                intent.putExtra("sc3", s3);
                intent.putExtra("sc4", s4);
                intent.putExtra("sc5", s5);
                intent.putExtra("sc6", s6);


                btnAddReport.setEnabled(false);
                btnAddReport.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_disable_button_bg));


                NextCall.setEnabled(true);
                NextCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));


                PreviousCall.setEnabled(true);
                PreviousCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));

                if (cursor.isLast()) {
                    startActivity(intent);
                    finish();

                } else {
                    startActivityForResult(intent, 88);
                }


            }
        });


        btnAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChatHeadService();
                if (cursor != null && cursor.getCount() > 0) {
                    Intent intent = new Intent(CallMenuActivity.this, AddReportActivity.class);
                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("id")));
                    intent.putExtra("school_code", cursor.getString(cursor.getColumnIndex("scode")));
                    intent.putExtra("student_id", cursor.getString(cursor.getColumnIndex("studentid")));

                    if (cursor.isLast()) {
                        startActivity(intent);
                        finish();

                    } else {
                        startActivityForResult(intent, 88);
                    }

                }

            }
        });


        btnAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopChatHeadService();
                if (cursor != null && cursor.getCount() > 0) {
                    String farmerID = cursor.getString(cursor.getColumnIndex("farmer_id"));
                    if (StringUtils.isEmpty(farmerID)) {
                        return;
                    } else {
                        ShowAddNumberDialog("Add Number", "", farmerID);
                    }
                }


            }
        });


        edtSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String text = edtSearchBar.getText().toString().toLowerCase();
                    if (text.length() > 0) {
                        SearchDataFromCursor(text);
                    } else {
                        if (isPendingCall) {
                            cursor = databaseAccess.baseline_getPendingCallCursor(userName);
                            if (cursor != null && cursor.getCount() > 0) {
                                cursor.moveToFirst();
                                GetData();
                            }
                        } else {
                            cursor = databaseAccess.baseline_getNewCallsCursor(userName);
                            if (cursor != null && cursor.getCount() > 0) {
                                cursor.moveToFirst();
                                GetData();
                            }
                        }
                    }
                } catch (Exception e) {

                }

            }
        });


        numbers_sp_q_2 = (Spinner) findViewById(R.id.numbers_sp_q_2);
        numbers_sp_q_2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        ArrayList<String> spinnerArray = HouseHoldDataBaseHelper.getDataBaseProcessor(CallMenuActivity.this).aghhid_getNumbersDataagainstvillageAndhhid(CallMenuActivity.this,school_code,student_id);


        JSONObject data = HouseHoldDataBaseHelper.getDataBaseProcessor(CallMenuActivity.this).aghhid_getDataFromtable(CallMenuActivity.this, DatabaseAdapter.aghhid_section_c_table, school_code, student_id);
        DebugLog.console("[HH_Screen_two] inside onStart() " + data.toString());

        if (data.length() > 0) {

            try {
                if (data.getString("c1_given_number").equalsIgnoreCase("null")) {
                    data.put("c1_given_number", "");
                }
                spinnerArray.add(spinnerArray.size(),"c1_given_number");
                spinnerArray.add(data.getString("c1_given_number"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        data = HouseHoldDataBaseHelper.getDataBaseProcessor(CallMenuActivity.this).aghhid_getDataFromtable(CallMenuActivity.this, DatabaseAdapter.AGHHID_SampleTable, school_code, student_id);
        DebugLog.console("[HH_Screen_two] inside onStart() " + data.toString());

        if (data.length() > 0) {

            try {
                if (data.getString("given_number").equalsIgnoreCase("null")) {
                    data.put("given_number", "");
                }


                if(data.getString("given_number").length()>0) {
                    spinnerArray.add(spinnerArray.size(), "section_a_given_number");
                    spinnerArray.add(data.getString("given_number"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }




        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                R.layout.spinner_item,
                spinnerArray);
        numbers_sp_q_2.setAdapter(spinnerArrayAdapter);

        numbers_sp_q_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                DebugLog.console("[CallMenuActivity] inside numbers onItemSelected() "+i);


                try {
                    if (i==0){
                        //  hh_edtfield_q_2.setText(  "");

                    }else{

                        //   hh_edtfield_q_2.setText(  parent.getSelectedItem().toString().trim());

                    }


                    if (parent.getSelectedItem().toString().trim().equalsIgnoreCase("c1_given_number")){
                        parent.setSelection(0);
                    }

                    if (parent.getSelectedItem().toString().trim().equalsIgnoreCase("section_a_given_number")){
                        parent.setSelection(0);
                    }




                } catch (Exception e) {
                    EmailDebugLog.getInstance(CallMenuActivity.this).writeLog("[CallMenuActivity] inside on numbers() Exception is :"+e.toString());
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private boolean numberSelected() {
        try {
            if(numbers_sp_q_2.getSelectedItem().toString().equalsIgnoreCase(numbers_sp_q_2.getItemAtPosition(0).toString())){

                Toast.makeText(CallMenuActivity.this, "Please select number first.", Toast.LENGTH_SHORT).show();


                return false;
            }

            return true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(CallMenuActivity.this).writeLog("[CallMenuActivity] inside numberSelected() Exception is :"+e.toString());
            return false;
        }
    }

    void SearchDataFromCursor(String scode) {

        if (StringUtils.isEmpty(scode)) {
            return;
        }
        Cursor cursor1 = databaseAccess.baseline_getPendingCallCursor_search(userName, scode);

        if (cursor1 != null && cursor1.getCount() > 0) {
            cursor = cursor1;
            cursor.moveToFirst();
            GetData();
        }

    }

    void GetData() {

        if (cursor != null && cursor.getCount() > 0) {

            MubLog.cpnsoleLog("inside GetData count is greater ");
            String id = cursor.getString(cursor.getColumnIndex("id"));
            MubLog.cpnsoleLog("id" + id);


            String given_number = cursor.getString(cursor.getColumnIndex("given_number"));
            String hhid_phone_number = cursor.getString(cursor.getColumnIndex("hhid_phone_number"));
            MubLog.cpnsoleLog("hhid_phone_number" + hhid_phone_number);


            String survey_status = cursor.getString(cursor.getColumnIndex("survey_status"));
            MubLog.cpnsoleLog("survey_status" + survey_status);

            String sc1 = cursor.getString(cursor.getColumnIndex("sc1"));
            String sc2 = cursor.getString(cursor.getColumnIndex("sc2"));
            String sc3 = cursor.getString(cursor.getColumnIndex("sc3"));
            String sc4 = cursor.getString(cursor.getColumnIndex("sc4"));
            String sc5 = cursor.getString(cursor.getColumnIndex("sc5"));
            String sc6 = cursor.getString(cursor.getColumnIndex("sc6"));
            String reason = cursor.getString(cursor.getColumnIndex("reason"));

            String farmer_cellphone = cursor.getString(cursor.getColumnIndex("farmer_cellphone"));

            String strata = "";//cursor.getString(cursor.getColumnIndex("strata"));

            String scode = cursor.getString(cursor.getColumnIndex("village_id"));

            String scode_original = "";// cursor.getString(cursor.getColumnIndex("scode_original"));
            String m2_district = "";//cursor.getString(cursor.getColumnIndex("m2_district"));
            String m2_tehsil = "";//cursor.getString(cursor.getColumnIndex("m2_tehsil"));
            String m2_mauza = "";//cursor.getString(cursor.getColumnIndex("m2_mauza"));
            String m2_school_name = cursor.getString(cursor.getColumnIndex("village_name"));
            String studentnumber = "";//cursor.getString(cursor.getColumnIndex("studentnumber"));
            String studentid = cursor.getString(cursor.getColumnIndex("hhid"));
            String m1b_student_sex = "";//cursor.getString(cursor.getColumnIndex("m1b_student_sex"));
            String m1b_student_name = cursor.getString(cursor.getColumnIndex("hhid_father_name"));
            String m5_studentassessment = "";//cursor.getString(cursor.getColumnIndex("m5_studentassessment"));
            String order_to_contact = "";//cursor.getString(cursor.getColumnIndex("order_to_contact"));
            String duration = cursor.getString(cursor.getColumnIndex("duration"));

            // String school_emis_code = cursor.getString(cursor.getColumnIndex("school_emis_code"));


            //String private_school_code = cursor.getString(cursor.getColumnIndex("private_school_code"));

            s1 = sc1;
            s2 = sc2;
            s3 = sc3;
            s4 = sc4;
            s5 = sc5;
            s6 = sc6;
            school_code = scode;
            student_id = studentid;

            int orignal_tries_count = 0;
            int alternate_tries_count = 0;

            if (!StringUtils.isEmpty(sc1)) {
                onorignal.setText("Tried 1");
                orignal_tries_count++;
            }
            if (!StringUtils.isEmpty(sc2)) {
                onorignal.setText("Tried 2");
                orignal_tries_count++;
            }
            if (!StringUtils.isEmpty(sc3)) {
                onorignal.setText("Tried  3");
                orignal_tries_count++;
            }
            if (!StringUtils.isEmpty(sc4)) {
                onorignal.setText("Tried  4");
                orignal_tries_count++;
            }
            if (!StringUtils.isEmpty(sc5)) {
                onorignal.setText("Tried  5");
                orignal_tries_count++;
            }
            if (!StringUtils.isEmpty(sc6)) {
                onorignal.setText("Tried  6");
                orignal_tries_count++;
            }


            if (!StringUtils.isEmpty(given_number)) {
                hhid_phone_number = given_number;
            }
//            Cursor cursor_number = databaseAccess.getpq_section_a_Data(school_code, student_id);
//            if (cursor_number != null && cursor_number.getCount() > 0) {
//                cursor_number.moveToFirst();
//
//                String a4_number = cursor_number.getString(cursor_number.getColumnIndex("a4_number"));
//
//                if (!StringUtils.isEmpty(a4_number)) {
//                    hhid_phone_number = a4_number;
////                    given_number
//                }
//
//            }

            /////update this function according to requiremen
            SetData(farmer_cellphone,
                    strata,
                    scode,
                    scode_original,
                    m2_district,
                    m2_tehsil,
                    m2_mauza,
                    m2_school_name,
                    studentnumber,
                    studentid,
                    m1b_student_sex,
                    m1b_student_name,
                    hhid_phone_number,
                    m5_studentassessment,
                    order_to_contact,
                    reason,
                    sc1,
                    sc2,
                    sc3
            );


            return;


        }


    }

    private void checkOptionTenCombinations() {


        try {

        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[CallMenuActivity] inside checkOptionTenCombinations() Exception is :"+e.toString());
        }
    }

    void SetData(String farmer_cellphone,
                 String strata,
                 String scode,
                 String scode_original,
                 String m2_district,
                 String m2_tehsil,
                 String m2_mauza,
                 String m2_school_name,
                 String studentnumber,
                 String studentid,
                 String m1b_student_sex,
                 String m1b_student_name,
                 String m1b_parent_mobile,
                 String m5_studentassessment,
                 String order_to_contact,
                 String reason,
                 String sc1,
                 String sc2,
                 String sc3) {
        try{
            txt_village_name.setText(m2_school_name);
//        txt_school_emis_code.setText(scode);
            txt_school_code.setText("Village Code : " + scode);
            txt_private_school_code.setText(scode);
            txt_hh_head_name.setText(m1b_student_name);
            txt_Student_id.setText("HH Id : " + studentid);
            txt_district.setText(m2_district);
            txt_Tehsil.setText(m2_tehsil);


            // txt_mobile_number.setText(m1b_parent_mobile);

            ArrayList<String> spinnerArray = HouseHoldDataBaseHelper.getDataBaseProcessor(CallMenuActivity.this).aghhid_getNumbersDataagainstvillageAndhhid(CallMenuActivity.this, school_code, student_id);


            spinnerArray.remove(0);

            JSONObject data = HouseHoldDataBaseHelper.getDataBaseProcessor(CallMenuActivity.this).aghhid_getDataFromtable(CallMenuActivity.this, DatabaseAdapter.aghhid_section_c_table, school_code, student_id);
            DebugLog.console("[HH_Screen_two] inside onStart() " + data.toString());

            if (data.length() > 0) {

                try {
                    if (data.getString("c1_given_number").equalsIgnoreCase("null")) {
                        data.put("c1_given_number", "");
                    }



                    spinnerArray.add(spinnerArray.size(),"---(c1_given_number: ");
                    spinnerArray.add(data.getString("c1_given_number")+")");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            data = HouseHoldDataBaseHelper.getDataBaseProcessor(CallMenuActivity.this).aghhid_getDataFromtable(CallMenuActivity.this, DatabaseAdapter.AGHHID_SampleTable, school_code, student_id);
            DebugLog.console("[HH_Screen_two] inside onStart() " + data.toString());

            if (data.length() > 0) {

                try {
                    if (data.getString("given_number").equalsIgnoreCase("null")) {
                        data.put("given_number", "");
                    }


                    if(data.getString("given_number").length()>0) {
                        spinnerArray.add(spinnerArray.size(),"section_a_given_number");
                        spinnerArray.add(data.getString("given_number"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }






            String hhidlisthaving_numbers = android.text.TextUtils.join("----  ", spinnerArray);
            txt_mobile_number.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            txt_mobile_number.setSingleLine(false);
            txt_mobile_number.setText(Html.fromHtml(hhidlisthaving_numbers));


            school_code = scode;
            student_id = studentid;
            if (!StringUtils.isEmpty(reason)) {
                txtReason.setText(reason);
                reasonLayout.setVisibility(View.VISIBLE);
            } else {
                reasonLayout.setVisibility(View.GONE);
            }


        }catch (Exception ex) {
            EmailDebugLog.getInstance(CallMenuActivity.this).writeLog("[CallMenuActivity] inside SetData() ");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                DialUserNumber();


            } else {
                Toast.makeText(this, "Allow this permission to make phone call to the user.", Toast.LENGTH_SHORT).show();
                // ShowPermissionDialog("Allow this permission to copy images to your phone memory");
            }
        }
    }

    void DialUserNumber() {
        if (ActivityCompat.checkSelfPermission(CallMenuActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        String phoneNumber =     txt_mobile_number.getText().toString().trim();

        if(numbers_sp_q_2.getSelectedItemPosition()==0){
            Toast.makeText(this, "Please select number before pressing DIAL.", Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneNumber =    numbers_sp_q_2.getSelectedItem().toString().trim();

        String network = getSimNetwork();
        MubLog.cpnsoleLog("getSimNetwork  " + network);
        if (!StringUtils.isEmpty(network)) {
            if (network.equalsIgnoreCase("Jazz")) {
                phoneNumber = "660" + phoneNumber;
            } else if (network.equalsIgnoreCase("Telenor")) {
                //     phoneNumber = "880" + phoneNumber;
            } else {
                phoneNumber = "770" + phoneNumber;
            }
        } else {

        }
        //callIntent.setData(Uri.parse("tel:" + "03006982661"));

//        SaveInterviewStart_time();
        if (network.equalsIgnoreCase("Telenor")) {
            ShowDialMessage(CallMenuActivity.this, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
        } else {
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }

        btnAddReport.setEnabled(true);
        btnAddReport.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));


        NextCall.setEnabled(false);
        NextCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_disable_button_bg));


        PreviousCall.setEnabled(false);
        PreviousCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_disable_button_bg));

        //  startActivityForResult(callIntent, 99);
    }

    private String getSimNetwork() {
        TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return phoneMgr.getSimOperatorName();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CallMenuActivity", "onPause: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        MubLog.cpnsoleLog("CallMenuActivity onActivityResult resultCode" + resultCode);
        MubLog.cpnsoleLog("CallMenuActivity onActivityResult requestCode " + requestCode);
        if (requestCode == 99) {
//            Intent intent = new Intent(CallMenuActivity.this, AddReportActivity.class);
//            intent.putExtra("id", cursor.getString(cursor.getColumnIndex("id")));
//            intent.putExtra("pitb_emp_id", cursor.getString(cursor.getColumnIndex("pitb_emp_id")));
//            intent.putExtra("farmer_number_", cursor.getString(cursor.getColumnIndex("farmer_number_")));
//            startActivityForResult(intent , 88);


            // startService(new Intent(CallMenuActivity.this.getApplicationContext(), com.mubashar.mubchatheadservice.ChatHeadService.class));
            MubLog.cpnsoleLog("inside call dialed ");

        } else if (requestCode == 880) {
            Toast.makeText(this, "Umer Waqas", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 999) {
            Toast.makeText(this, "Umer Waqas", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 88) {


            boolean isDataUpdated = data.getBooleanExtra("isDataUpdated", false);
            if (isDataUpdated) {


                btnAddReport.setEnabled(false);
                btnAddReport.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_disable_button_bg));


                NextCall.setEnabled(true);
                NextCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));


                PreviousCall.setEnabled(true);
                PreviousCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));

                if (isPendingCall) {
                    cursor = databaseAccess.getFarmerPendingCalls(userName);
                } else {
                    cursor = databaseAccess.getFarmerDistinctCallRecord(userName);
                }


                if (cursor != null && cursor.getCount() > 0) {
                    MubLog.cpnsoleLog("Count - > " + cursor.getCount());
                    cursor.moveToFirst();
                    GetData();

                } else {
                    Toast.makeText(this, "No data found.", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

        }
    }

    void ShowAddNumberDialog(String title, String message, final String farmerID) {

        LayoutInflater li = LayoutInflater.from(CallMenuActivity.this);
        View dialogView = li.inflate(R.layout.add_number_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CallMenuActivity.this);
        alertDialogBuilder.setView(dialogView);
        TextView txtDialogTitle = dialogView.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setText(title);
        final EditText edtNumber = dialogView.findViewById(R.id.edtNumber);
        final TextView txtErrorMessage = dialogView.findViewById(R.id.txtErrorMessage);
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

                String phoneNumber = edtNumber.getText().toString();
                if (StringUtils.isEmpty(phoneNumber)) {
                    txtErrorMessage.setVisibility(View.VISIBLE);
                    txtErrorMessage.setText("Phone number cannot be empty.");
                    edtNumber.requestFocus();
                } else if (phoneNumber.length() < 10 || phoneNumber.length() > 10) {
                    txtErrorMessage.setVisibility(View.VISIBLE);
                    txtErrorMessage.setText("Invalid Phone Number");
                    edtNumber.requestFocus();
                } else {
                    txtErrorMessage.setVisibility(View.GONE);
                    //   databaseAccess.UpdateNumber(phoneNumber, farmerID, isAlternateFarmer);

                    if (isPendingCall) {
                        cursor = databaseAccess.getFarmerPendingCalls(userName);
                    } else {
                        cursor = databaseAccess.getFarmerDistinctCallRecord(userName);
                    }

                    btnAddReport.setEnabled(false);
                    btnAddReport.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_disable_button_bg));


                    NextCall.setEnabled(true);
                    NextCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));


                    PreviousCall.setEnabled(true);
                    PreviousCall.setBackground(ContextCompat.getDrawable(CallMenuActivity.this, R.drawable.rounder_button_bg));

                    if (cursor != null && cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        GetData();
                    } else {
                        Toast.makeText(CallMenuActivity.this, "No data found.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    alertDialog.dismiss();

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

    private class CallStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING: {
                    break;
                }
                case TelephonyManager.CALL_STATE_OFFHOOK: {
                    try {

                        //THIS WILL SIMULATE A HOME BUTTON PRESS
                        //Effectively Minimizing the In Call Screen
                        // Intent startMain = new Intent(CallMenuActivity.this , SplashActivity.class);
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        //  startActivityForResult(startMain ,880);

                        Intent mIntent = new Intent(CallMenuActivity.this, CallMenuActivity.class);
                        mIntent.putExtra("username", userName);
                        mIntent.putExtra("isPendingCall", isPendingCall);
                        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivities(new Intent[]{startMain, mIntent});
                        //Now that you are home, and your In Call Screen is minimized
                        //move back to your application
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MubLog.cpnsoleLog("inside on onAttachedToWindow ");

        stopChatHeadService();
    }

    private void stopChatHeadService() {
        MubLog.cpnsoleLog("inside on stopChatHeadService ");
        Intent v = new Intent(CallMenuActivity.this.getApplicationContext(), ChatHeadService.class);
        v.putExtra("stop", true);
        startService(v);

    }

    void SaveInterviewStart_time() {

        String year = RConsUtils.getcurrentTime(0);
        String month = RConsUtils.getcurrentTime(1);
        String day = RConsUtils.getcurrentTime(2);
        String hh = RConsUtils.getcurrentTime(3);
        String mm = RConsUtils.getcurrentTime(4);


        if (StringUtils.isEmpty(s1)) {
            databaseAccess.savepq_interview_start_sc1(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s2)) {
            databaseAccess.savepq_interview_start_sc2(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s3)) {
            databaseAccess.savepq_interview_start_sc3(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s4)) {
            databaseAccess.savepq_interview_start_sc4(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s5)) {
            databaseAccess.savepq_interview_start_sc5(school_code, student_id, year, month, day, hh, mm);
        } else if (StringUtils.isEmpty(s6)) {
            databaseAccess.savepq_interview_start_sc6(school_code, student_id, year, month, day, hh, mm);
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
                SaveInterviewStart_time();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + str_btonok));
                startActivity(callIntent);

            }
        });
        btnenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                SaveInterviewStart_time();
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


