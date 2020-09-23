package com.rcons.fcallbacks.Athreehhid;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.filemanager.FileManager;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.HHIDConfigurations;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;

import com.rcons.fcallbacks.Main.AddReportActivity_AD;
import com.rcons.fcallbacks.Main.MainMenuActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.AppController;
import com.rcons.fcallbacks.Utilties.MpcUtil;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;
import com.rcons.fcallbacks.http.ConnectionVerifier;
import com.rcons.fcallbacks.http.ResponceVerifier;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import butterknife.BindView;


public class HH_Screen_one_Section_h extends Activity {
    FloatingActionButton btn_AddReportQuestionnaire;
    //Use For sign-up
    EditText editTextfirstName;
    //	EditText editTextLastName;
    EditText editTextUserName;
    EditText hh_edtfield_q_2;
    EditText hh_edtfield_q_4;
    EditText hh_edtfield_q_4_a;
    EditText editTextReTypeEmailID;
    EditText editTextPhone;
    EditText editTextPassword;
    EditText editTextConfirmPassword;
    ImageButton btnCreateAccount,btnHHNew;
    ImageButton nextbtn;
    String phone = "";
    String userName = "";
    String firstName = "";
    String lastName = "";
    String aghhid_h_1 = "";

    String aghhid_c1_given_number = "";
    String reTypedEmailID = "";
    String password = "";
    String hh_edtfield_q_3_edt_answer = "";
    String hh_edtfield_q_4_edt_answer = "";
    String hh_edtfield_q_4_a_edt_answer = "";
    String launchedActivityName = "";

    TextView hh_textview_q_2 = null;
    TextView hh_textview_q_3 = null;
    TextView head_count = null;
    TextView migration = null;
    RadioButton migration1 = null;
    RadioButton migration2 = null;
    RadioGroup sign_up_confirm_password_textfield_rb = null;
    RadioGroup hh_edtfield_q_2_rdg = null;
    Spinner numbers_sp_q_2 = null;



    TextView house_address = null;
    TextView house_no = null;
    TextView block_no = null;
    TextView area_no =null;
    TextView landmark =null;






    String Question_5 = "";
    String Question_5_1 = "";
    String Question_5_2 = "";
    String Question_5_3 = "";
    String Question_5_4 = "";
    String Question_5_5 = "";

    // Use For sign-in
    EditText editTextUserNameToLogin;
    EditText editTextPasswordToLogin;
    EditText edt_Question_5 = null;
    EditText edt_Question_5_1 = null;
    EditText edt_Question_5_2 = null;
    EditText edt_Question_5_3 = null;
    EditText edt_Question_5_4 = null;
    EditText edt_Question_5_5 = null;
    String userNameToLogin = "";
    String passwordToLogin = "";
    ImageButton btnSignIn;


    RelativeLayout sign_in_back_btn = null;
    RelativeLayout screenthreeBackbtnonerl = null;
    RelativeLayout screenthreenextbtnonerl = null;
    RelativeLayout relative_layout_sign_up_btn = null;
    RelativeLayout relative_layout_new_hh_in_same_stid_btn = null;


    TextView sign_up_eula_textview_label_line_two,sign_up_eula_btn_label_below_btn_line_one,sign_in_eula_btn_label_below_btn_line_one, top_bar = null;

    JSONObject result = null;
    static String[] licenseLabels = null;
    public static JSONArray licenseList = null;
    private ProgressDialog pd = null;
    Context appContext =  null;
    boolean selfClose = false;
    public static String calledFromView = ""; //Use by m_handler to show responce to user in case of request from forgot-password view or from signup-in view

    static boolean  NEW_HH_IN_SAME_STRUCTURE = false;
    static int current_STID = -1;
    static int CURRENT_HHID = -1;
    static  boolean USER_CONFIRMATION = false;

    private static String START_TIME  = "";



    String CALLED_FROM ="";

    //for agapp
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
    String phone_number = "";
    String id = "";
    String school_name = "";
    String student_name = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        appContext = getApplicationContext();
        try {

            //			Cursor cur1 = Browser.getAllVisitedUrls(appContext.getContentResolver());
            //			if (cur1!=null){
            //				cur1.getCount();
            //				DebugLog.console("can"+cur1.getCount());
            //			}

            Bundle bundle = getIntent().getExtras();
            //launchedActivityName = bundle.getString("launchActivity");

            HH_Screen_one_Section_h.START_TIME = MpcUtil.getcurrentTime(14);



            setContentView(R.layout.section_h_screen_one);
            //AnalyticsUtil.updateScreen(this, getResources().getString(R.string.sign_up_screen_top_bar_text_view));


            phone_number = getIntent().getStringExtra("m1b_parent_mobile");
            school_code = getIntent().getStringExtra("scode");
            student_id = getIntent().getStringExtra("studentid");
            student_name = getIntent().getStringExtra("m1b_student_name");
            school_name = "";//getIntent().getStringExtra("m2_school_name");
            rcons_user = RConsUtils.getUserName();

           // CALLED_FROM = getIntent().getStringExtra("onbackclick");


            // Get Refferences of Views
            initializeReferenceOfViews();



        }catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]: Exception occured inside onCreate");
        }
    }public HH_Screen_one_Section_h() {
        // TODO Auto-generated constructor stub
    }



    private void initializeReferenceOfViews() {
        // TODO Auto-generated method stub
        try {




            btn_AddReportQuestionnaire = (FloatingActionButton) findViewById(R.id.btn_AddReportQuestionnaire);
            hh_edtfield_q_2_rdg = (RadioGroup) findViewById(R.id.hh_edtfield_q_2_rdg);





            sign_in_back_btn = (RelativeLayout) findViewById(R.id.sign_in_back_btn);

            screenthreenextbtnonerl = (RelativeLayout) findViewById(R.id.screenthreenextbtnonerl);

            top_bar = (TextView)findViewById(R.id.sign_in_top_bar_text_view);

            String currentPSUCode = HHIDConfigurations.getPeshawarCurrentPSU(appContext);
            DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() currentPSUCode "+currentPSUCode);
            String last_strict_id_against_psu = HHIDConfigurations.getPeshawarLastStructerIDagainstPSU(appContext,currentPSUCode);

            current_STID = Integer.parseInt(last_strict_id_against_psu)+1;

            DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() last_strict_id_against_psu "+last_strict_id_against_psu);

            String lastHHIID = HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(appContext,currentPSUCode+"_hhid");
            CURRENT_HHID = Integer.parseInt(lastHHIID)+1;

            // int count =  HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_getCountOfhhid_having_phoneNumber(HHIDConfigurations.getPeshawarCurrentPSU(appContext),"");


            String headName = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).aghhid_get_member_selected_in_e13(appContext,school_code,student_id);

            if(headName!=null){

                if(headName.length()>0){

                }else{
                    headName = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).aghhid_get_member_selected_in_e1(appContext,school_code,student_id);
                }

            }


            String stringTopBar = getResources().getString(R.string.screen_two_top_bar,headName,school_code, student_id+"");

            top_bar.setText(stringTopBar);

            hh_edtfield_q_2 =  (EditText)findViewById(R.id.hh_edtfield_q_2);

            MpcUtil.restartInput(appContext,hh_edtfield_q_2);
            //hh_edtfield_q_2.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_email_name_textfield_label) + "</font>" + "<small>"));





            TextView hh_textview_q_4 = findViewById(R.id.hh_textview_e_script);
            String qtext = getResources().getString(R.string.ad_section_e_script,headName,headName,headName);
            hh_textview_q_4.setText(qtext);




            /**
             * Enabling TextChangedListener for  Email-ID field signup
             * */
            hh_edtfield_q_2.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    DebugLog.console("inside onTextChanged:" );

                    // aghhid_h_1 = hh_edtfield_q_2.getText().toString().trim();


                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });



            numbers_sp_q_2 = (Spinner) findViewById(R.id.numbers_sp_q_2);

            ArrayList<String> spinnerArray = HouseHoldDataBaseHelper.getDataBaseProcessor(HH_Screen_one_Section_h.this).aghhid_getNumbersDataagainstvillageAndhhid(appContext,school_code,student_id);
//            spinnerArray.add("Show previous numbers");
//            spinnerArray.add("3006854549");

            ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item,
                    spinnerArray);
            numbers_sp_q_2.setAdapter(spinnerArrayAdapter);

            numbers_sp_q_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                    DebugLog.console("[HH_Screen_three] inside relative_code_sp_d_5 onItemSelected() "+i);


                    try {
                        if (i==0){
                            //  hh_edtfield_q_2.setText(  "");

                        }else{

                            hh_edtfield_q_2.setText(  parent.getSelectedItem().toString().trim());

                        }
                    } catch (Exception e) {
                        EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside onItemSelected() Exception is :"+e.toString());
                    }


                }


                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]: Exception occured inside initializeReferenceOfViews");
        }
    }



    private void initializeReferenceOfViews_old() {
        // TODO Auto-generated method stub
        try {

            if (launchedActivityName.equalsIgnoreCase("signup")) {


                hh_textview_q_2 = (TextView)findViewById(R.id.hh_textview_q_2);
                hh_textview_q_3 = (TextView)findViewById(R.id.hh_textview_q_3);

                migration1 = (RadioButton)findViewById(R.id.migration1);

                hh_edtfield_q_2_rdg = (RadioGroup) findViewById(R.id.hh_edtfield_q_2_rdg);





                sign_in_back_btn = (RelativeLayout) findViewById(R.id.sign_in_back_btn);

                screenthreenextbtnonerl = (RelativeLayout) findViewById(R.id.screenthreenextbtnonerl);
                relative_layout_sign_up_btn = (RelativeLayout) findViewById(R.id.relative_layout_sign_up_btn);

                String stringToShow =getResources().getString(R.string.sign_up_eula_textview_label_line_two);

                String stringToShowforSigninnavigation = getResources().getString(R.string.sign_up_eula_btn_label_below_btn_line_two);

                sign_up_eula_btn_label_below_btn_line_one = (TextView)findViewById(R.id.textViewbelowbtn);
                top_bar = (TextView)findViewById(R.id.sign_in_top_bar_text_view);
                sign_up_eula_btn_label_below_btn_line_one.setText(Html.fromHtml(stringToShowforSigninnavigation));

                sign_up_eula_textview_label_line_two = (TextView)findViewById(R.id.sign_up_eula_textview_label_line_two);
                sign_up_eula_textview_label_line_two.setText(Html.fromHtml(stringToShow));

                String currentPSUCode = HHIDConfigurations.getPeshawarCurrentPSU(appContext);
                DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() currentPSUCode "+currentPSUCode);
                String last_strict_id_against_psu = HHIDConfigurations.getPeshawarLastStructerIDagainstPSU(appContext,currentPSUCode);

                current_STID = Integer.parseInt(last_strict_id_against_psu)+1;

                DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() last_strict_id_against_psu "+last_strict_id_against_psu);

                String lastHHIID = HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(appContext,currentPSUCode+"_hhid");
                CURRENT_HHID = Integer.parseInt(lastHHIID)+1;

                //int count =  HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_getCountOfhhid_having_phoneNumber(HHIDConfigurations.getPeshawarCurrentPSU(appContext),"");


                String stringTopBar = getResources().getString(R.string.screen_two_top_bar,student_name,school_code, student_id+"");

                top_bar.setText(stringTopBar);

                //sign_up_eula_btn_label_below_btn_line_one.setVisibility(View.GONE);
                //sign_up_eula_textview_label_line_two.setVisibility(View.GONE);

                editTextfirstName = (EditText)findViewById(R.id.sign_up_first_name_textfield);
                editTextfirstName.setLines(1);
                editTextfirstName.setInputType(InputType.TYPE_CLASS_TEXT);
//				editTextfirstName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon, 0, 0, 0);
//				editTextfirstName.setCompoundDrawablePadding(8);

//				editTextfirstName.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_first_name_textfield_label) + "</font>" + "<small>"));

                hh_edtfield_q_2 =  (EditText)findViewById(R.id.hh_edtfield_q_2);

//				hh_edtfield_q_2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//				hh_edtfield_q_2.setCompoundDrawablePadding(8);
                hh_edtfield_q_2.requestFocus();
                MpcUtil.restartInput(appContext,hh_edtfield_q_2);
                //hh_edtfield_q_2.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_email_name_textfield_label) + "</font>" + "<small>"));

//                editTextReTypeEmailID = (EditText)findViewById(R.id.sign_up_confirm_email_textfield);
//				editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//				editTextReTypeEmailID.setCompoundDrawablePadding(8);
//
//				editTextReTypeEmailID.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_confirm_email_name_textfield_label) + "</font>" + "<small>"));

                //edt_Question_5 = (EditText)findViewById(R.id.sign_up_first_name_textfield);

//				edt_Question_5_5 = (EditText)findViewById(R.id.sign_up_first_name_textfield);

                //Handling back-navigation from signup_part_two screen
//                if(!firstName.equalsIgnoreCase("")){
//                    editTextfirstName.setText(firstName);
//                    hh_edtfield_q_2.setText(aghhid_h_1);
//                    editTextReTypeEmailID.setText(reTypedEmailID);
//                }
                //manually filling firstName
                editTextfirstName.setText("Parent");

                /**
                 * Enabling TextChangedListener for  Email-ID field signup
                 * */
                hh_edtfield_q_2.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                        // When user changed the Text
                        DebugLog.console("inside onTextChanged:" );

                    }

                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                  int arg3) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void afterTextChanged(Editable arg0) {
                        // TODO Auto-generated method stub
                    }
                });



                String number = "";//MpcUtil.getPhoneNumber(appContext);
                if (number.equalsIgnoreCase("")) {
                    //	editTextPhone.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_in_phone_textfield_label) + "</font>" + "<small>"));
                }else{
                    //	editTextPhone.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_in_phone_textfield_label) + "</font>" + "<small>"));

//					editTextPhone.setText(number);
                }





                btnHHNew.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // TODO Auto-generated method stub

//						if(ConnectionVerifier.isInternetOn(appContext)){
                        NEW_HH_IN_SAME_STRUCTURE = true;
                        //AnalyticsUtil.updateEventAction(appContext,getResources().getString(R.string.event_register_create_acc));
                        grabEnteredTextForSignUpPartTwo();
//						}else{
//
//							showAlert( getResources().getString(R.string.signup_activity_alert_box_title),getResources().getString(R.string.connection_error_message));
//						}
                    }
                });





                btnCreateAccount.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        NEW_HH_IN_SAME_STRUCTURE = false;
//						if(ConnectionVerifier.isInternetOn(appContext)){

                        //AnalyticsUtil.updateEventAction(appContext,getResources().getString(R.string.event_register_create_acc));
                        grabEnteredTextForSignUpPartTwo();
//						}else{
//
//							showAlert( getResources().getString(R.string.signup_activity_alert_box_title),getResources().getString(R.string.connection_error_message));
//						}
                    }
                });


            }if (launchedActivityName.equalsIgnoreCase("signin")) {

                editTextUserNameToLogin = (EditText)findViewById(R.id.sign_in_email_textfield);
                editTextPasswordToLogin = (EditText)findViewById(R.id.sign_in_password_textfield);
                editTextUserNameToLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
                editTextUserNameToLogin.setCompoundDrawablePadding(8);
                editTextPasswordToLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
                editTextPasswordToLogin.setCompoundDrawablePadding(8);



                editTextUserNameToLogin.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_in_email_textfield_hint) + "</font>" + "<small>"));
                editTextPasswordToLogin.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_in_password_textfield_hint) + "</font>" + "<small>"));
//				editTextUserNameToLogin.setHintTextColor(colors);TextColor(R.color.LightGrey);
//				editTextPasswordToLogin.setHintTextColor(R.color.LightGrey);


                String stringToShowforSigninnavigation = getResources().getString(R.string.sign_in_eula_btn_label_below_btn_line_two);

                sign_in_eula_btn_label_below_btn_line_one = (TextView)findViewById(R.id.textViewbelowbtn);
                if (HHIDConfigurations.getMappingId(getApplicationContext())!=null){
                    sign_in_eula_btn_label_below_btn_line_one.setText("");
                    sign_in_eula_btn_label_below_btn_line_one.setClickable(false);
                }else{
                    sign_in_eula_btn_label_below_btn_line_one.setClickable(true);
                    sign_in_eula_btn_label_below_btn_line_one.setText(Html.fromHtml(stringToShowforSigninnavigation));
                }


//				editTextUserNameToLogin.setText("m@m.com");
//				editTextPasswordToLogin.setText("test");


                btnSignIn = (ImageButton)findViewById(R.id.sign_in_btn);
                btnSignIn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        //						if(ConnectionVerifier.isInternetOn(appContext)){
                        //AnalyticsUtil.updateEventAction(appContext,getResources().getString(R.string.event_signin_login));
                        grabEnteredTextForSignIN();

                        //						}else{
                        //							showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
                        //						}
                    }
                });

                //If device is already registered, automatically filling the username field and disabling editing
                String registeredEmailAddress  = HHIDConfigurations.getRegisteredEmailAddress(getApplicationContext());




                if(registeredEmailAddress.equalsIgnoreCase("")){

                }else{

                    if (HHIDConfigurations.getMappingId(appContext)!=null) {
//							editTextUserNameToLogin.setEnabled(false);
//							editTextUserNameToLogin.setText(registeredEmailAddress);

                    }


                    EmailDebugLog.getInstance(appContext).writeLog( "\r\n[HH_Screen_One]:starting SecureSecondService  ");

                    //appContext.startService(new Intent(appContext,SecureSecondService.class));

                }


            }






        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]: Exception occured inside initializeReferenceOfViews");
        }
    }

    public  boolean isInternetOn(Context ctx) {

        boolean isConnected =false;

        try{
            ConnectivityManager connec =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED &&  connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ){
                isConnected=false;
            }else{
                isConnected=true;
            }
            return isConnected;
        }catch(Exception e){
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside isInternetOn");
            //FileManager.exportLogFile(appContext);
            return isConnected=true;
        }
    }



    public void grabEnteredTextForSignUp(View v) {

        boolean error = false;

        try {




            //editTextfirstName.setText("Parent");
            //userName = editTextUserName.getText().toString();
            firstName = "Parent";// editTextfirstName.getText().toString();

            reTypedEmailID = editTextReTypeEmailID.getText().toString();
            //AnalyticsUtil.updateEventAction(appContext, "Email Address : "+aghhid_h_1);
            //AnalyticsUtil.updateEventAction(appContext, "reTypedEmail Address : "+reTypedEmailID);
            if(firstName.trim().length() == 0)
            {
                MpcUtil.hideSoftKeyBoard(appContext, editTextfirstName);
                showAlert(getResources().getString(R.string.signup_activity_firstname_field_empty_message_title),getResources().getString(R.string.signup_activity_firstname_field_empty_message_body));
                editTextfirstName.requestFocus();
                MpcUtil.restartInput(appContext, editTextfirstName);
                error = true;
//			}else if (!MpcUtil.isValidFullName(appContext, firstName.trim() )){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextfirstName);
//				showAlert(getResources().getString(R.string.signup_activity_firstname_field_invalid_charc_message_title),getResources().getString(R.string.signup_activity_firstname_field_invalid_charc_message_body));
//				editTextfirstName.requestFocus();
//				MpcUtil.restartInput(appContext, editTextfirstName);
//				error = true;true

            }else if (aghhid_h_1.trim().length() == 0 ){
                MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
                showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
                hh_edtfield_q_2.requestFocus();
                MpcUtil.restartInput(appContext, hh_edtfield_q_2);
                error = true;
//			}else if (!MpcUtil.isEmailValid(aghhid_h_1.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_2.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_2);
//				error = true;
            }else if (reTypedEmailID.trim().length()==0){
                MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
                showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
                editTextReTypeEmailID.requestFocus();
                MpcUtil.restartInput(appContext, editTextReTypeEmailID);
                error = true;
            }else if (!reTypedEmailID.trim().equalsIgnoreCase(aghhid_h_1.trim())){
                MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
                showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
                editTextReTypeEmailID.requestFocus();
                MpcUtil.restartInput(appContext, editTextReTypeEmailID);
                error = true;
            }
            if(!error){
                pd = ProgressDialog.show(this, getResources().getString(R.string.signup_activity__progress_bar_title), getResources().getString(R.string.signup_activity__progress_bar_body_text), true, false);
                new Thread() {
                    public void run() {
                        try{
                            // complete registration from server
                            validateEmailAddress();

                        } catch (Exception e) {  }
                        // Dismiss the Dialog
                        // myProgressDialog.dismiss();
                    }
                }.start();
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside grabEnteredTextForSignUp");
        }
    }




    public void grabEnteredTextForscreenthreeBackbtn(View v) {

        boolean error = false;
        try {




            sign_in_back_btn.setVisibility(View.VISIBLE);

            hh_textview_q_2.setVisibility(View.VISIBLE);
            hh_edtfield_q_2.setVisibility(View.VISIBLE);

            hh_textview_q_3.setVisibility(View.VISIBLE);
            editTextReTypeEmailID.setVisibility(View.VISIBLE);

            head_count.setVisibility(View.VISIBLE);
            editTextPassword.setVisibility(View.VISIBLE);


            house_address.setVisibility(View.GONE);

            house_no.setVisibility(View.GONE);
            block_no.setVisibility(View.GONE);
            area_no.setVisibility(View.GONE);
            landmark.setVisibility(View.GONE);


            migration.setVisibility(View.GONE);
            migration1.setVisibility(View.GONE);
            migration2.setVisibility(View.GONE);

            editTextConfirmPassword.setVisibility(View.GONE);
            //sign_up_confirm_password_textfield_rb.setSelected(false);
            //migration1.setChecked(false);
            //migration2.setChecked(false);

            edt_Question_5_1 .setVisibility(View.GONE);
            edt_Question_5_2 .setVisibility(View.GONE);
            edt_Question_5_3 .setVisibility(View.GONE);
            edt_Question_5_4 .setVisibility(View.GONE);



            screenthreeBackbtnonerl.setVisibility(View.GONE);
            relative_layout_sign_up_btn.setVisibility(View.GONE);
            relative_layout_new_hh_in_same_stid_btn.setVisibility(View.GONE);
            screenthreenextbtnonerl.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside grabEnteredTextForscreenthreeBackbtn");
        }
    }











    public void HandleNextButnClick(View v) {

        boolean error = false;
        try {


            //aghhid_c1_given_number = hh_edtfield_q_2.getText().toString();
            int rdg_checkedID = hh_edtfield_q_2_rdg.getCheckedRadioButtonId();


            if(rdg_checkedID==-1){
                error =  true;
                showAlert(appContext.getResources().getString(R.string.app_name),"Please Select Some value");
            }else


                if (aghhid_h_1.length()==0){
                    error =  true;
                    showAlert(appContext.getResources().getString(R.string.app_name),"Please Select Some value");
                   // MpcUtil.restartInput(appContext, hh_edtfield_q_2);
                }




            if(!error){



                boolean screen_two = true;
                if (screen_two){


                    saveDataNow();
                    return ;
                }



                house_address.setVisibility(View.VISIBLE);
                house_no.setVisibility(View.VISIBLE);
                block_no.setVisibility(View.VISIBLE);
                area_no.setVisibility(View.VISIBLE);
                landmark.setVisibility(View.VISIBLE);

                edt_Question_5_1 .setVisibility(View.VISIBLE);
                edt_Question_5_2 .setVisibility(View.VISIBLE);
                edt_Question_5_3 .setVisibility(View.VISIBLE);
                edt_Question_5_4 .setVisibility(View.VISIBLE);



                screenthreeBackbtnonerl.setVisibility(View.VISIBLE);
                relative_layout_sign_up_btn.setVisibility(View.VISIBLE);
                relative_layout_new_hh_in_same_stid_btn.setVisibility(View.VISIBLE);



                screenthreenextbtnonerl.setVisibility(View.GONE);


//				pd = ProgressDialog.show(this, getResources().getString(R.string.signup_activity__progress_bar_title), getResources().getString(R.string.signup_activity__progress_bar_body_text), true, false);
//				new Thread() {
//					public void run() {
//						try{
//
//
//
//
//
//
//
//							completeProcess("signup",MpcUtil.SIGNUP_URL);
//
//						} catch (Exception e) {  }
//
//					}
//				}.start();


//
//				edt_Question_5_1.requestFocus();
//				MpcUtil.hideSoftKeyBoard(appContext,edt_Question_5_1);

                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside grabEnteredTextForscreenthreenextbtn");
        }
    }


    public void grabEnteredTextForSignUpPartTwo() {

        boolean error = false;
        try {

            Question_5_1 = edt_Question_5_1 .getText().toString();
            Question_5_2 = edt_Question_5_2 .getText().toString();
            Question_5_3 = edt_Question_5_3 .getText().toString();
            Question_5_4 = edt_Question_5_4 .getText().toString();



            reTypedEmailID = editTextReTypeEmailID.getText().toString();
            password = editTextPassword.getText().toString();


            String selectedRadio = ""+ sign_up_confirm_password_textfield_rb.getCheckedRadioButtonId();//editTextConfirmPassword.getText().toString();

            hh_edtfield_q_3_edt_answer = selectedRadio;

            firstName = editTextfirstName.getText().toString();

            if (aghhid_h_1.trim().length() == 0 ){
                //AnalyticsUtil.updateEventAction(appContext, " Email field empty");
                MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
                showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 2");
                hh_edtfield_q_2.requestFocus();
                MpcUtil.restartInput(appContext, hh_edtfield_q_2);
                error = true;
//			}else if (!MpcUtil.isEmailValid(aghhid_h_1.trim())){
//				//AnalyticsUtil.updateEventAction(appContext, "Invalid Email Address : "+aghhid_h_1.trim());
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_2.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_2);
//				error = true;
//			}else if (reTypedEmailID.trim().length()==0){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 2");
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
                //AnalyticsUtil.updateEventAction(appContext, " Re-type Email field empty");
//			}else if (!reTypedEmailID.trim().equalsIgnoreCase(aghhid_h_1.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " Re-type Email and email mismatch :"+reTypedEmailID.trim()+" email :"+aghhid_h_1.trim());
//			}else
//
//			if(phone.trim().length() == 0)
//			{
//				//AnalyticsUtil.updateEventAction(appContext, "Register Button: Phone field empty");
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPhone);
//				showAlert(getResources().getString(R.string.signup_activity_phone_field_empty_message_title),getResources().getString(R.string.signup_activity_Phone_field_empty_message_body));
//				editTextPhone.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPhone);
//				error = true;
//			}
//
//			else if (!MpcUtil.isPhoneValid(phone.trim())){
//				//AnalyticsUtil.updateEventAction(appContext, "Register Button: Invalid phone Number : "+phone.trim());
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPhone);
//				showAlert(getResources().getString(R.string.signup_activity_phone_field_empty_message_title),getResources().getString(R.string.signup_activity_phone_field_invalid_message_body));
//				editTextPhone.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPhone);
//				error = true;
//			}
//			else

//			if(password.trim().length() == 0)
//			{
//				//AnalyticsUtil.updateEventAction(appContext, " Password field empty");
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
//				showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 3");
//				editTextPassword.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPassword);
//				error = true;
////			}
//			else if(password.trim().length() < MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH )
//			{
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
//				showAlert(getResources().getString(R.string.signup_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_minimum_length,MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH));
//				editTextPassword.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPassword);
//				//showAlert("Invalid Password","Password Length should be greater than "+MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH+" characters");
//				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " Password length less than 4 characters");
//			}
//			else if(password.trim().length() > MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH )
//			{
//				//AnalyticsUtil.updateEventAction(appContext, " Password length greater than 50 characters");
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
//				showAlert(getResources().getString(R.string.signup_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_maximum_length,MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH));
//				editTextPassword.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPassword);
//				error = true;
            }
            else if(hh_edtfield_q_3_edt_answer.trim().length()==0)
            {
//				MpcUtil.hideSoftKeyBoard(appContext, editTextConfirmPassword);
                showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 4");
//				editTextConfirmPassword.requestFocus();
//				MpcUtil.restartInput(appContext, editTextConfirmPassword);
                error = true;
                //AnalyticsUtil.updateEventAction(appContext, " ReType Password field empty");
            }  else 	if (  Question_5_1.trim().length() == 0 ){

                MpcUtil.hideSoftKeyBoard(appContext, edt_Question_5_1);
                error = true;
                showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 5.1");
                MpcUtil.restartInput(appContext, edt_Question_5_1);
            }else if (  Question_5_2.trim().length() == 0 ){

                MpcUtil.hideSoftKeyBoard(appContext, edt_Question_5_2);
                error = true;
                showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 5.2");
                MpcUtil.restartInput(appContext, edt_Question_5_2);
            }else if (  Question_5_3.trim().length() == 0 ){

                MpcUtil.hideSoftKeyBoard(appContext, edt_Question_5_3);
                error = true;
                showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 5.3");
                MpcUtil.restartInput(appContext, edt_Question_5_3);
            }else if (  Question_5_4.trim().length() == 0 ){

                MpcUtil.hideSoftKeyBoard(appContext, edt_Question_5_4);
                error = true;
                showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 5.4");
                MpcUtil.restartInput(appContext, edt_Question_5_4);
            }




            if(!error){

                pd = ProgressDialog.show(this, getResources().getString(R.string.signup_activity__progress_bar_title), getResources().getString(R.string.signup_activity__progress_bar_body_text), true, false);
                new Thread() {
                    public void run() {
                        try{






                            // complete registration from server
                            completeProcess("signup", MpcUtil.SIGNUP_URL);

                        } catch (Exception e) {  }
                        // Dismiss the Dialog
                        // myProgressDialog.dismiss();
                    }
                }.start();
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside grabEnteredTextForSignUp");
        }
    }


    public void grabEnteredTextForSignIN() {
        boolean error = false;

        try {
            //startService(new Intent(appContext,ApplicationsListProvider.class));

            userNameToLogin = editTextUserNameToLogin.getText().toString();
            passwordToLogin = editTextPasswordToLogin.getText().toString();
            //AnalyticsUtil.updateEventAction(appContext, "Email Address : "+userNameToLogin);

            if (userNameToLogin.trim().length() == 0 )
            {
                MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
                showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_body));
                editTextUserNameToLogin.requestFocus();
                MpcUtil.restartInput(appContext, editTextUserNameToLogin);
                error = true;
                //AnalyticsUtil.updateEventAction(appContext, "Login Button: Email field empty");
            }
//			else if (!MpcUtil.isEmailValid(userNameToLogin)) {
//				//AnalyticsUtil.updateEventAction(appContext, "Login Button: Invalid Email Address : "+userNameToLogin);
//				MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
//				showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_body));
//				editTextUserNameToLogin.requestFocus();
//				MpcUtil.restartInput(appContext, editTextUserNameToLogin);
//				error = true;
//			}
            else  if (passwordToLogin.trim().length() == 0 )
            {
                //AnalyticsUtil.updateEventAction(appContext, "Login Button: Password field empty");
                MpcUtil.hideSoftKeyBoard(appContext, editTextPasswordToLogin);
                showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.signin_activity_password_field_empty_message_body));
                editTextPasswordToLogin.requestFocus();
                MpcUtil.restartInput(appContext, editTextPasswordToLogin);
                error = true;
            }
            else  if (passwordToLogin.trim().length() < MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH )
            {
                //AnalyticsUtil.updateEventAction(appContext, "Login Button: Password length less than 4 characters");
                MpcUtil.hideSoftKeyBoard(appContext, editTextPasswordToLogin);
                //showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_minimum_length,MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH));
                showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.invalid_password_error_message_via_server_code));
                editTextPasswordToLogin.requestFocus();
                MpcUtil.restartInput(appContext, editTextPasswordToLogin);
                error = true;
            }
            else  if (passwordToLogin.trim().length() > MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH )
            {
                //AnalyticsUtil.updateEventAction(appContext, "Login Button: Password length greater than 50 characters");
                MpcUtil.hideSoftKeyBoard(appContext, editTextPasswordToLogin);
                //showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_maximum_length,MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH));
                showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.invalid_password_error_message_via_server_code));
                editTextPasswordToLogin.requestFocus();
                MpcUtil.restartInput(appContext, editTextPasswordToLogin);
                error = true;
            }

//			if(!error){
//
//				MpcUtil.APP_STATUS =  HHIDConfigurations.getAPPFunctionalityStatus(appContext);
//				DebugLog.console("	HH_Screen_One:  MpcUtil.APP_STATUS"+MpcUtil.APP_STATUS );
//
//				if (MpcUtil.APP_STATUS.equalsIgnoreCase(MpcUtil.APP_PERMANENTLY_STOP_WORKING_STATUS) ) {
//					showPermanentExpiredAlert("SecureTeen deactivated","SecureTeen has been disabled on this device. Please contact technical support.");
//
//					//finish();
//				}else{
//
//					String mappingId = null;//HHIDConfigurations.getMappingId(appContext);
//					if(mappingId!=null){
//						String storedPasswrd = HHIDConfigurations.getDeviceLockPassword(appContext);
//						if((!storedPasswrd.equalsIgnoreCase("")) && (storedPasswrd.equalsIgnoreCase(passwordToLogin))){
//							EmailDebugLog.getInstance(appContext).writeLog("\r\n[HH_Screen_One]: Matched with local password" );
//							MpcUtil.APP_STATUS =  HHIDConfigurations.getAPPFunctionalityStatus(appContext);
//							DebugLog.console("	HH_Screen_One:  MpcUtil.APP_STATUS"+MpcUtil.APP_STATUS );
//							if (MpcUtil.APP_STATUS.equalsIgnoreCase(MpcUtil.APP_STOP_DATA_UPLOADING_STATUS) ) {
//								Intent i = new Intent(appContext,ExpiredLicenceActivity.class);
//								i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NO_HISTORY);
//								appContext.startActivity(i);
//								selfClose =  true;
//								finish();
//							}else {
//								Intent intent = MpcUtil.buildNewIntent(appContext,  SettingsActivity.class);
//								startActivity(intent);
//								selfClose =  true;
//								finish();
//							}
//						}else{
//							DebugLog.console("	HH_Screen_One: Going to get from server");
//							if(ConnectionVerifier.isInternetOn(appContext)){
//
//								pd = ProgressDialog.show(this, getResources().getString(R.string.signin_activity__progress_bar_title), getResources().getString(R.string.signin_activity__progress_bar_body_text), true, false);
//
//								new Thread() {
//									public void run() {
//										try{
//											// complete registration from server
//											completeProcess("signin",MpcUtil.SIGNIN_URL);
//
//										} catch (Exception e) {  }
//
//									}
//								}.start();
//							}else{
//								showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
//							}
//						}
//					}else{
//
//
//
//						if(ConnectionVerifier.isInternetOn(appContext)){
//
//
//
//
//							pd = ProgressDialog.show(this, getResources().getString(R.string.signin_activity__progress_bar_title), getResources().getString(R.string.signin_activity__progress_bar_body_text), true, false);
//
//							new Thread() {
//								public void run() {
//									try{
//										// complete registration from server
//										completeProcess("signin",MpcUtil.SIGNIN_URL);
//
//									} catch (Exception e) {  }
//
//								}
//							}.start();
//						}else{
//							showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
//						}
//					}
//				}
//			}
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside grabEnteredTextForSignIN");
        }
    }

    private void completeProcess(String processType, String url){

        JSONObject json = null;
        try{

            // to show in device-register screen (default-name )
//			HHIDConfigurations.setConfiguration(appContext, HHIDConfigurations.APP_PHONE_MODEL, HHIDConfigurations.getPhoneModelForPref(appContext).trim());
//
//			MpcUtil.DEVICE_UNIQUE_ID = MpcUtil.getDeviceUniqueId(appContext);
//			if (processType.equalsIgnoreCase("signup")){
//				DebugLog.console("userName: "+userName+"\r\nfirstName"+ firstName+ "\r\nlastName"+lastName+"\r\naghhid_h_1"+ aghhid_h_1+"\r\npassword"+ password);
//				json = JsonHandler.createSIGNUPJsonObject(appContext, firstName, aghhid_h_1, phone,password, "careteen", "android", MpcUtil.DEVICE_UNIQUE_ID,MpcUtil.BUILD_INFO);
//				//EmailDebugLog.getInstance(appContext).writeLog(json.toString());
//				MainUserInfo.devicesTempLockPassword = password;
//				MainUserInfo.REGISTERED_EMAIL_ADDRESS = aghhid_h_1;
//			}else{
//				DebugLog.console("\r\nuserNameToLogin"+userNameToLogin+ "\r\npasswordToLogin"+passwordToLogin);
//				json = JsonHandler.createSIGNINJsonObjectFromChildAppWithoutCode(appContext,userNameToLogin, passwordToLogin, "careteen", "android", MpcUtil.DEVICE_UNIQUE_ID,MpcUtil.BUILD_INFO);
//				//EmailDebugLog.getInstance(appContext).writeLog(json.toString());
//				MainUserInfo.devicesTempLockPassword = passwordToLogin;
//				MainUserInfo.REGISTERED_EMAIL_ADDRESS = userNameToLogin;
//			}
//			if (json.names() != null){
//				JSONObject result = registerOnServer(appContext,json, url);
//				if(result != null){
//					parseResultReceivedFromServer(result);
//				}else{
//					EmailDebugLog.getInstance(getApplicationContext()).writeLog("returned json is null");
            m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//				}
//			}
        }catch(Exception e){

            if (pd!=null)
                pd.dismiss();
            EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside completeProcess");
            m_Handler.sendEmptyMessage(
                    ResponceVerifier.GENERAL_ERROR);
        }
    }



    private void validateEmailAddress(){

//		try{
//
//			DebugLog.console("userName: "+userName+"\r\nfirstName"+ firstName+ "\r\nlastName"+lastName+"\r\naghhid_h_1"+ aghhid_h_1+"\r\npassword"+ password);
//			MainUserInfo.REGISTERED_EMAIL_ADDRESS = aghhid_h_1;
//			String url = MpcUtil.getBaseURL(appContext)+ MpcUtil.EMAIL_ID_VERIFICATION_URL + Uri.encode(aghhid_h_1);
//
//			JSONObject result = HttpsClient.sendHttpsGetRequest(appContext, url);
//			if(result != null){
//				if(result.has("code")){
//					if (!result.isNull("code")){
//						if (result.get("code").toString().equalsIgnoreCase("100")){
//
//							if(result.has("status")){
//								if (!result.isNull("status")){
//
//									if(result.getString("status").equalsIgnoreCase("true")){
//										sign_up_screen_navigation_Handler.sendEmptyMessage(1);
//
//									}else{
//										m_Handler.sendEmptyMessage(ResponceVerifier.STATUS_USER_ALREADY_EXSIST);
//									}
//
//								}else{
//									m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//
//								}
//
//							}else{
//								m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//							}
//
//
//						}else{
//							m_Handler.sendEmptyMessage(Integer.parseInt(result.get("code").toString()));
//						}
//					}else{
//						m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//					}
//				}else{
//					m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//				}
//
//			}else{
//				EmailDebugLog.getInstance(getApplicationContext()).writeLog("returned json is null");
//				m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//			}
//
//		}catch(Exception e){
//			EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside validateEmailAddress");
//			m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//		}
    }




    private JSONObject registerOnServer(Context appContext, JSONObject json, String url){

        try{
//			DebugLog.console("[HH_Screen_One]: inside registerOnServer");
//			url = MpcUtil.getBaseURL(appContext) +url ;
//			int retry = 0;
//			while(retry < MpcUtil.RETRY_COUNT_FOR_SIGIN_UP_IN_SERVER_REQUEST){
//				result = HttpsClient.sendNewHttpsPostRequest(appContext,url,json);
//				if(result != null){
//					break;
//				}else{
//					retry++;
//				}
//			}
            return result;
        }catch(Exception e){
            EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside registerOnServer");
            return null;
        }
    }

    private void parseResultReceivedFromServer(JSONObject json){

        try{

            FileManager.exportLogFile(appContext);
            if (!json.isNull("code")){
                String status = json.get("code").toString();
                //AnalyticsUtil.updateEventAction(getApplicationContext(),"Server Response " +status);
                m_Handler.sendEmptyMessage(Integer.parseInt(status));
            }else{
                //AnalyticsUtil.updateEventAction(getApplicationContext(),"Server Response code object not found");
                m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
                EmailDebugLog.getInstance(getApplicationContext()).writeLog("Inside parseResultReceivedFromServer code object not found ");
            }
        }catch(Exception e){
            EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside parseResultReceivedFromServer");
        }
    }

    private Handler m_Handler = new Handler() {

        boolean success = false;
        @Override
        public void handleMessage(Message msg) {

            try{
                DebugLog.console("code is :" + msg.what);

                DebugLog.console("\r\n[HH_Screen_One]: Class name "+this.getClass().getName().toString());
                success = true;//ResponceVerifier.verifyReceievedResponce(HH_Screen_two.this,msg.what);

                if(success) {


                    if (pd != null)
                        pd.dismiss();


                    DebugLog.console("[HH_Screen_two] inside run() Question_1 " + aghhid_h_1);
                    DebugLog.console("[HH_Screen_two] inside run() Question_2 " + reTypedEmailID);
                    DebugLog.console("[HH_Screen_two] inside run() Question_3 " + password);
                    DebugLog.console("[HH_Screen_two] inside run() Question_4 " + hh_edtfield_q_3_edt_answer);

                    DebugLog.console("[HH_Screen_two] inside run() Question_5_4 " + Question_5_4);
                    DebugLog.console("[HH_Screen_two] inside run() Question_5_3 " + Question_5_3);
                    DebugLog.console("[HH_Screen_two] inside run() Question_5_2 " + Question_5_2);
                    DebugLog.console("[HH_Screen_two] inside run() Question_5_1 " + Question_5_1);

                    if (appContext == null) {
                        appContext = AppController.getInstance();
                    }




                    if(NEW_HH_IN_SAME_STRUCTURE){
                        showPermanentExpiredAlert(AppController.getInstance().getResources().getString(R.string.app_name),top_bar.getText().toString()+getResources().getString(R.string.hh_in_structure_id_save_popup_text));

                    }else{
                        showPermanentExpiredAlert(AppController.getInstance().getResources().getString(R.string.app_name),top_bar.getText().toString()+getResources().getString(R.string.structure_id_save_popup_text));

                    }









                    if(success){
                        return;

                    }

                    boolean found = false;

                    if (pd!=null){
                        pd.dismiss();
                    }



                }

            }catch(Exception e){
                if (pd!=null)
                    pd.dismiss();
                EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside Handler");
            }
        }

    };

    private boolean saveDatainDataBase(  String user_name , String start_date_time) {
        boolean dataSaved = false;
        try {

            DebugLog.console("[HH_Screen_two] inside saveDatainDataBase() aghhid_h_1"+aghhid_h_1);
            dataSaved = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_insert_data_h1( school_code , student_id ,aghhid_h_1,   rcons_user , start_date_time);
            return dataSaved;
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside saveDatainDataBase() Exception is :"+e.toString());
            return dataSaved;
        }
    }


    private boolean isListOfDevicesNull() {
        // TODO Auto-generated method stub
        boolean isListOfDevicesNull =  true;
//		try{
//			JSONObject jsonObject = (JSONObject)MainUserInfo.licenseList.get(MainUserInfo.selectedLicensePosition);
//			if (!jsonObject.isNull("devices")){
//				JSONArray jsonArray = jsonObject.getJSONArray("devices");
//				if (jsonArray.length() != 0){
//					isListOfDevicesNull =  false;
//				}
//			}
//			DebugLog.console(" [HH_Screen_One] isListOfDevicesNull : "+isListOfDevicesNull);
        return isListOfDevicesNull;
//		}catch (Exception e) {
//			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HH_Screen_One]: Exception occured inside isListOfDevicesNull");
//			isListOfDevicesNull =  true;
//			return isListOfDevicesNull;
//		}
    }



    private void saveDataNow() {



        try {



            boolean dataSaved = saveDatainDataBase(RConsUtils.getUserName(), HH_Screen_one_Section_h.START_TIME);





            if (dataSaved){

                boolean screen_two = true;





                if (screen_two ) {




                    if(aghhid_h_1.equalsIgnoreCase("-777") || (aghhid_h_1.equalsIgnoreCase("-99"))){
                        Intent intent = new Intent(HH_Screen_one_Section_h.this, AddReportActivity_AD.class);
                        intent.putExtra("emp_id", emp_id);
                        intent.putExtra("order_id", order_id);
                        intent.putExtra("id", id);
                        intent.putExtra("farmer_cellphone", phone_number);
                        intent.putExtra("school_code", school_code);
                        intent.putExtra("student_id", student_id);
                        startActivityForResult(intent, 88);
                    } else {




                        Intent intent = MpcUtil.buildNewIntent(appContext, HH_Screen_two_Section_h.class);

                        intent.putExtra("m1b_parent_mobile", phone_number);
                        intent.putExtra("scode", school_code);
                        intent.putExtra("studentid", student_id);
                        intent.putExtra("m1b_student_name", student_name);
                        intent.putExtra("rcons_user", RConsUtils.getUserName());
                        startActivityForResult(intent, 88);
                        return;
                    }

//                    Intent backIntent = MpcUtil.buildNewIntent(appContext, MainMenuActivity.class);
//                    backIntent.putExtra("launchActivity", "signup");
////                    startActivity(backIntent);
//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("isDataUpdated", false);
//                    setResult(Activity.RESULT_OK, returnIntent);
//                    finish();


                }





            }else{
                showAlert("Erro" , "\nPlease Contact Admin");

            }





        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside saveDataNow() Exception is :"+e.toString());
        }











    }








    public  void sendForgotPasswordRequest(View v) {

        try{
            //AnalyticsUtil.updateEventAction(appContext,getResources().getString(R.string.event_sigin_forgot_pass));
            if(ConnectionVerifier.isInternetOn(appContext)){
                userNameToLogin = editTextUserNameToLogin.getText().toString();
                if (userNameToLogin.trim().length() == 0 ){
                    MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
                    showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_forgot_password_emailaddress_field_empty_message_body));
                    editTextUserNameToLogin.requestFocus();
                    MpcUtil.restartInput(appContext, editTextUserNameToLogin);
//				}else if (!MpcUtil.isEmailValid(userNameToLogin)) {
//					MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
//					showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_emailaddress_invalid_error_message));
//					editTextUserNameToLogin.requestFocus();
//					editTextUserNameToLogin.selectAll();
//					MpcUtil.restartInput(appContext, editTextUserNameToLogin);
                    //editTextUserNameToLogin.setTextColor(getResources().getColorStateList(R.color.selected_effect));
                }else{

                    showWarningAlert("Warning", "Instructions to change password will be emailed to address registered with this account. Are you sure you want to continue?");
                }
            }else{
                showAlert( getResources().getString(R.string.app_name),getResources().getString(R.string.connection_error_message));
            }
        }catch(Exception e){
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside sendForgotPasswordRequest");
        }
    }

    private JSONObject sendForgotPasswordRequestToServer(){

        try{
//			DebugLog.console("[HH_Screen_One]: inside sendForgotPasswordRequestToServer");
//			String url = MpcUtil.getBaseURL(appContext) +MpcUtil.PASSWORD_RESET_URL+userNameToLogin +"&resellerId=careteen";
//			EmailDebugLog.getInstance(getApplicationContext()).writeLog("[HH_Screen_One]: inside sendForgotPasswordRequestToServer"+url);
//			int retry = 0;
//			while(retry < MpcUtil.RETRY_COUNT_FOR_SIGIN_UP_IN_SERVER_REQUEST){
//				result = HttpsClient.sendHttpsGetRequest(appContext, url);
//				if(result != null){
//					break;
//				}else{
//					retry++;
//				}
//			}
            return result;
        }catch(Exception e){
            EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside sendForgotPasswordRequestToServer");
            return null;
        }
    }


    private Handler sign_up_screen_navigation_Handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {

            try{
                DebugLog.console("code is :" + msg.what);

                if (pd!=null){
                    pd.dismiss();
                }

//				setContentView(R.layout.sign_up_part_two);
                launchedActivityName = "signup_part_two";
                initializeReferenceOfViews();


            }catch(Exception e){
                if (pd!=null){
                    pd.dismiss();
                }
                EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside Handler");
            }
        }
    };


    private Handler forgot_password_Handler = new Handler() {

        boolean success = false;
        @Override
        public void handleMessage(Message msg) {

            try{
                DebugLog.console("code is :" + msg.what);
                success = ResponceVerifier.verifyReceievedResponce(HH_Screen_one_Section_h.this,msg.what);
                if(success){
                    if (pd!=null){
                        pd.dismiss();
                    }
                    showAlert(getResources().getString(R.string.signin_activity_forgot_password_email_sent_message_title),getResources().getString(R.string.signin_activity_forgot_password_email_sent_message_body));
                }else {
                    if (pd!=null){
                        pd.dismiss();
                    }
                    showAlert(ResponceVerifier.TitleToShow, ResponceVerifier.messageToShow);
                }

            }catch(Exception e){
                pd.dismiss();
                EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside Handler");
            }
        }
    };

    public void showAlert(String title, String message){
        new AlertDialog.Builder(HH_Screen_one_Section_h.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                    }
                })
                .show();
    }





    public void showPermanentExpiredAlert(String title, String message){
        new AlertDialog.Builder(HH_Screen_one_Section_h.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        saveDataNow();
                    }
                }).setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //	USER_CONFIRMATION = false;
            }
        })
                .show();
    }

    public void pressBackButton(View v){

        try {
            DebugLog.console("[HH_Screen_One] BackButton is pressed:" );


//            if(CALLED_FROM.equalsIgnoreCase("HH_Screen_three_Section_e")) {
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("isDataUpdated", false);
//                setResult(Activity.RESULT_OK, returnIntent);

//                Intent intent = MpcUtil.buildNewIntent(appContext, HH_Screen_three_Section_e.class);
//
//                intent.putExtra("m1b_parent_mobile", phone_number);
//                intent.putExtra("scode", school_code);
//                intent.putExtra("studentid", student_id);
//                intent.putExtra("m1b_student_name", student_name);
//                intent.putExtra("rcons_user", RConsUtils.getUserName());
//
//                startActivity(intent);
//                finish();

//            }else {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("isDataUpdated", false);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
//
//            }


        }catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside pressBackButton");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (pd!=null){
            pd.dismiss();
        }


        DebugLog.console("[HH_Screen_One] Inside onDestroy:" );
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();
        try {
//            DebugLog.console("[HH_Screen_One] Inside onBackPressed:" ); DebugLog.console("[HH_Screen_One] BackButton is pressed:" );   Intent returnIntent = new Intent();
//            returnIntent.putExtra("isDataUpdated", false);
//            setResult(Activity.RESULT_OK, returnIntent);
//
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);

            finish();
        }catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside onBackPressed");
            finish();
        }
    }

    public void showWarningAlert(String title, String message){
        new AlertDialog.Builder(HH_Screen_one_Section_h.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                        pd = ProgressDialog.show(HH_Screen_one_Section_h.this, getResources().getString(R.string.signin_activity_forgot_password_progress_bar_title), getResources().getString(R.string.signup_activity_forgot_password_text), true, false);
                        new Thread() {
                            public void run() {
                                try{
                                    Thread.sleep(200);
                                    JSONObject json = sendForgotPasswordRequestToServer();
                                    if (json!=null){

                                        if (!json.isNull("code")){
                                            String status = json.get("code").toString();
                                            if (status.equalsIgnoreCase("100")){
                                                forgot_password_Handler.sendEmptyMessage(Integer.parseInt(json.get("code").toString()));
                                                EmailDebugLog.getInstance(appContext).writeLog("[Forgot password]: Email Sent Successfully on id :"+editTextUserNameToLogin );
                                            }else{
                                                forgot_password_Handler.sendEmptyMessage(Integer.parseInt(json.get("code").toString()));
                                            }
                                        }else{
                                            EmailDebugLog.getInstance(getApplicationContext()).writeLog("Inside sendForgotPasswordRequest code object not found ");
                                            forgot_password_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
                                        }
                                    }else{
                                        EmailDebugLog.getInstance(getApplicationContext()).writeLog("Inside sendForgotPasswordRequest json object is null");
                                        forgot_password_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);

                                    }
                                } catch (Exception e) {  }
                                // Dismiss the Dialog
                            }
                        }.start();

                    }
                }).setNegativeButton(getResources().getString(R.string.registration_screen_alert_box_cancel_btn), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

            }
        }).show();
    }

    @Override
    protected void onStart() {
        super.onStart();


        try {

            JSONObject data = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).aghhid_getDataFromtable(appContext, DatabaseAdapter.aghhid_section_h_table,school_code,student_id);
            DebugLog.console("[HH_Screen_two] inside onStart() "+data.toString());

            if (data.length()>0){

                if(data.getString("h_1").equalsIgnoreCase("null"))
                    data.put("h_1","");

                hh_edtfield_q_2.setText(data.getString("h_1"));

                aghhid_c1_given_number="";//data.getString("e_4");

                if(data.getString("h_1").equalsIgnoreCase("null"))
                    data.put("h_1","");


                String h_1= data.getString("h_1");
                aghhid_h_1 =h_1;

                if(h_1.trim().equalsIgnoreCase("1")) {
                    hh_edtfield_q_2_rdg.check(hh_edtfield_q_2_rdg.getChildAt(0).getId());
                  //  hh_edtfield_q_2.setVisibility(View.VISIBLE);
                    //numbers_sp_q_2.setVisibility(View.VISIBLE);
                   // hh_edtfield_q_2.setText(data.getString("c1_given_number"));
                }

                if(h_1.trim().equalsIgnoreCase("2"))
                    hh_edtfield_q_2_rdg.check(hh_edtfield_q_2_rdg.getChildAt(1).getId());

                if(h_1.trim().equalsIgnoreCase("3"))
                    hh_edtfield_q_2_rdg.check(hh_edtfield_q_2_rdg.getChildAt(2).getId());

                if(h_1.trim().equalsIgnoreCase("4"))
                    hh_edtfield_q_2_rdg.check(hh_edtfield_q_2_rdg.getChildAt(3).getId());

                if(h_1.trim().equalsIgnoreCase("-99"))
                    hh_edtfield_q_2_rdg.check(hh_edtfield_q_2_rdg.getChildAt(4).getId());

                if(h_1.trim().equalsIgnoreCase("-777"))
                    hh_edtfield_q_2_rdg.check(hh_edtfield_q_2_rdg.getChildAt(5).getId());

            }













        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside onStart() Exception is :"+e.toString());
        }



    }

    public void onTermsPolicyClick(View arg0) {

        try {
            selfClose = true;
            //AnalyticsUtil.updateEventAction(appContext,"Terms of Use & Privacy Policy");
//			Intent intent = new Intent(Intent.ACTION_VIEW,
//					Uri.parse("http://www.secureteen.com/privacy-policy?ln="+MpcUtil.getLanguageCode(appContext)));
//			startActivity(intent);

        } catch (Exception ex){
            EmailDebugLog.getInstance(appContext).writeLog(ex.toString()+"\r\n[HH_Screen_One]: Exception occured inside onTermsPolicyClick");

        }

    }

    public void gotoSigninScreen(View arg0) {

        try {
            // = true;
            DebugLog.console("inside gotoSigninScreen");
            setContentView(R.layout.sign_in);

            launchedActivityName = "signin";
            initializeReferenceOfViews();
            //Intent intent = MpcUtil.buildNewIntent(appContext,  HH_Screen_One.class);
            //intent.putExtra("launchActivity", "signin");
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //startActivity(intent);
            //AnalyticsUtil.updateEventAction(appContext, getResources().getString(R.string.sign_up_eula_btn_label_below_btn_line_one));

            DebugLog.console("inside gotoSigninScreen done");
        } catch (Exception ex){
            EmailDebugLog.getInstance(appContext).writeLog(ex.toString()+"\r\n[HH_Screen_One]: Exception occured inside gotoSigninScreen");

        }

    }


    public void gotoSignupScreen(View arg0) {

        try {
            // = true;
            DebugLog.console("inside gotoSigninScreen");
            setContentView(R.layout.sign_up);
            launchedActivityName = "signup";
            initializeReferenceOfViews();
            //AnalyticsUtil.updateEventAction(appContext, getResources().getString(R.string.sign_in_eula_btn_label_below_btn_line_two_analytics));

            //Intent intent = MpcUtil.buildNewIntent(appContext,  HH_Screen_One.class);
            //intent.putExtra("launchActivity", "signin");
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //startActivity(intent);
            DebugLog.console("inside gotoSignupScreen done");
        } catch (Exception ex){
            EmailDebugLog.getInstance(appContext).writeLog(ex.toString()+"\r\n[HH_Screen_One]: Exception occured inside gotoSignupScreen");

        }

    }



    @Override
    public void onDetachedFromWindow() {
        // TODO Auto-generated method stub
        super.onDetachedFromWindow();
        try {
            finish();

        }catch (Exception ex) {

        }

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if(selfClose){

            //finish();

        }else {
//			Intent startMain = new Intent(Intent.ACTION_MAIN);
//			startMain.addCategory(Intent.CATEGORY_HOME);
//			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			startActivity(startMain);
        }
//		try{
//		Intent startMain = new Intent(Intent.ACTION_MAIN);
//		startMain.addCategory(Intent.CATEGORY_HOME);
//		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(startMain);

//}catch (Exception ex) {
//		
//	}
//		}else{
//			selfClose =  false;
//		}



    }

    public void gotohomeScreen(View view) {

        try {
            Intent intent = new Intent(HH_Screen_one_Section_h.this, MainMenuActivity.class);
//            startActivity(intent);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("isDataUpdated", false);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside gotohomeScreen() Exception is :"+e.toString());
        }
    }

    public void setMigration1(View view) {

        aghhid_h_1 = "1";
        //hh_edtfield_q_2.setVisibility(View.VISIBLE);
        hh_edtfield_q_2.setText("");
        //numbers_sp_q_2.setVisibility(View.VISIBLE);

    }

    public void setMigration2(View view) {
        aghhid_h_1 = "2";
        hh_edtfield_q_2.setVisibility(View.GONE);
        hh_edtfield_q_2.setText("");
        numbers_sp_q_2.setVisibility(View.GONE);
    }
    public void setMigration3(View view) {
        aghhid_h_1 = "3";
        hh_edtfield_q_2.setVisibility(View.GONE);
        hh_edtfield_q_2.setText("");
        numbers_sp_q_2.setVisibility(View.GONE);
    }


    public void setMigration4(View view) {
        aghhid_h_1 = "4";
        hh_edtfield_q_2.setVisibility(View.GONE);
        hh_edtfield_q_2.setText("");
        numbers_sp_q_2.setVisibility(View.GONE);
    }

    public void setMigration5(View view) {
        aghhid_h_1 = "-99";
        hh_edtfield_q_2.setVisibility(View.GONE);
        hh_edtfield_q_2.setText("");
        numbers_sp_q_2.setVisibility(View.GONE);
    }

    public void setMigration6(View view) {
        aghhid_h_1 = "-777";
        hh_edtfield_q_2.setVisibility(View.GONE);
        hh_edtfield_q_2.setText("");
        numbers_sp_q_2.setVisibility(View.GONE);
    }


    public void enableRadio(String value){


        try {



        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside enableRadio() Exception is :"+e.toString());
        }

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

    public void DialUserNumber(View v) {

        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        String phoneNumber =     txt_mobile_number.getText().toString().trim();


        String phoneNumber =   phone_number;// numbers_sp_q_2.getSelectedItem().toString().trim();

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
            ShowDialMessage(appContext, "Dial with", "", "880" + phoneNumber, "0" + phoneNumber);
        } else {
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }


    }

    public  String getSimNetwork() {
        TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return phoneMgr.getSimOperatorName();
    }




    void ShowDialMessage(final Context context, String title, String message, String str_btonok, String str_btnenum) {

        LayoutInflater li = LayoutInflater.from(context);
        View dialogView = li.inflate(R.layout.delete_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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
                // SaveInterviewStart_time();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + str_btonok));
                startActivity(callIntent);

            }
        });
        btnenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                // SaveInterviewStart_time();
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
