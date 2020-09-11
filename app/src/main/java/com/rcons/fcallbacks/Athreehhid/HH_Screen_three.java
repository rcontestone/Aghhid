package com.rcons.fcallbacks.Athreehhid;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.filemanager.FileManager;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.HHIDConfigurations;
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
import java.util.List;

import androidx.core.app.ActivityCompat;


public class HH_Screen_three extends Activity {

	//Use For sign-up
	EditText editTextfirstName;
	//	EditText editTextLastName;   String stringTopBar = getResources().getString(R.string.screen_two_top_bar,currentPSUCode,count+"",CURRENT_HHID+"", current_STID+"");
	EditText editTextUserName;
	EditText hh_edtfield_q_5;
	EditText hh_edtfield_q_4;
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
	String hh_edtfield_q_5_edt_answer = "";
	String reTypedEmailID = "";
	String password = "";
	String hh_edtfield_q_3_edt_answer = "";
	String hh_edtfield_q_4_edt_answer = "";
	String launchedActivityName = "";
	ViewGroup main;
	TextView hh_textview_q_2 = null;
	TextView hh_textview_q_3 = null;
	TextView head_count = null;
	TextView migration = null;
	RadioButton migration1 = null;
	RadioButton migration2 = null;
	RadioGroup sign_up_confirm_password_textfield_rb = null;
	RadioGroup hh_edtfield_q_2_rdg = null;



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

	public static String START_TIME  = "";

	public List<JSONObject > numberList = null;
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



					setContentView(R.layout.screen_three);
					//AnalyticsUtil.updateScreen(this, getResources().getString(R.string.sign_up_screen_top_bar_text_view));

			phone_number = getIntent().getStringExtra("m1b_parent_mobile");
			school_code = getIntent().getStringExtra("scode");
			student_id = getIntent().getStringExtra("studentid");
			student_name = getIntent().getStringExtra("m1b_student_name");
			school_name = "";//getIntent().getStringExtra("m2_school_name");
			rcons_user = RConsUtils.getUserName();


				// Get Refferences of Views
				initializeReferenceOfViews();




		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]: Exception occured inside onCreate");
		}
	}public HH_Screen_three() {
		// TODO Auto-generated constructor stub
	}


	private void initializeReferenceOfViews() {
		// TODO Auto-generated method stub
		try {



				hh_textview_q_2 = (TextView)findViewById(R.id.hh_textview_q_2);
				hh_textview_q_3 = (TextView)findViewById(R.id.hh_textview_q_3);
				head_count = (TextView)findViewById(R.id.head_count);
				migration = (TextView)findViewById(R.id.migration);
				migration1 = (RadioButton)findViewById(R.id.migration1);
				migration2 = (RadioButton)findViewById(R.id.migration2);
				sign_up_confirm_password_textfield_rb = (RadioGroup) findViewById(R.id.sign_up_confirm_password_textfield_rb);
				hh_edtfield_q_2_rdg = (RadioGroup) findViewById(R.id.hh_edtfield_q_2_rdg);
				main = (ViewGroup) findViewById(R.id.phone_number_ll);
				house_address = (TextView)findViewById(R.id.house_address);
				house_no= (TextView)findViewById(R.id.house_no);
				block_no= (TextView)findViewById(R.id.block_no);
				area_no= (TextView)findViewById(R.id.area_no);
				landmark= (TextView)findViewById(R.id.landmark);





				sign_in_back_btn = (RelativeLayout) findViewById(R.id.sign_in_back_btn);
				screenthreeBackbtnonerl = (RelativeLayout) findViewById(R.id.screenthreeBackbtnonerl);
				screenthreenextbtnonerl = (RelativeLayout) findViewById(R.id.screenthreenextbtnonerl);
				relative_layout_sign_up_btn = (RelativeLayout) findViewById(R.id.relative_layout_sign_up_btn);
				relative_layout_new_hh_in_same_stid_btn = (RelativeLayout) findViewById(R.id.relative_layout_new_hh_in_same_stid_btn);


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


				int count =  HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_getCountOfhhid_having_phoneNumber(HHIDConfigurations.getPeshawarCurrentPSU(appContext),"");


				String stringTopBar = getResources().getString(R.string.screen_two_top_bar,currentPSUCode,count+"",CURRENT_HHID+"", current_STID+"");

				top_bar.setText(stringTopBar);

				//sign_up_eula_btn_label_below_btn_line_one.setVisibility(View.GONE);
				//sign_up_eula_textview_label_line_two.setVisibility(View.GONE);

				editTextfirstName = (EditText)findViewById(R.id.sign_up_first_name_textfield);
				editTextfirstName.setLines(1);
				editTextfirstName.setInputType(InputType.TYPE_CLASS_TEXT);
//				editTextfirstName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon, 0, 0, 0);
//				editTextfirstName.setCompoundDrawablePadding(8);

//				editTextfirstName.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_first_name_textfield_label) + "</font>" + "<small>"));


				hh_edtfield_q_5 =  (EditText)findViewById(R.id.hh_edtfield_q_5);
				hh_edtfield_q_4 =  (EditText)findViewById(R.id.hh_edtfield_q_4);
//				hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//				hh_edtfield_q_5.setCompoundDrawablePadding(8);
				hh_edtfield_q_5.requestFocus();
				MpcUtil.restartInput(appContext,hh_edtfield_q_5);

				//hh_edtfield_q_5.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_email_name_textfield_label) + "</font>" + "<small>"));

				editTextReTypeEmailID = (EditText)findViewById(R.id.sign_up_confirm_email_textfield);
//				editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//				editTextReTypeEmailID.setCompoundDrawablePadding(8);
//
//				editTextReTypeEmailID.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_confirm_email_name_textfield_label) + "</font>" + "<small>"));


				//edt_Question_5 = (EditText)findViewById(R.id.sign_up_first_name_textfield);
				edt_Question_5_1 = (EditText)findViewById(R.id.house_no_textfield);
				edt_Question_5_2 = (EditText)findViewById(R.id.block_no_textfield);
				edt_Question_5_3 = (EditText)findViewById(R.id.area_no_textfield);
				edt_Question_5_4 = (EditText)findViewById(R.id.landmark_textfield);
//				edt_Question_5_5 = (EditText)findViewById(R.id.sign_up_first_name_textfield);


				//Handling back-navigation from signup_part_two screen
				if(!firstName.equalsIgnoreCase("")){
					editTextfirstName.setText(firstName);
					hh_edtfield_q_5.setText(hh_edtfield_q_5_edt_answer);
					editTextReTypeEmailID.setText(reTypedEmailID);
				}

				//manually filling firstName
				editTextfirstName.setText("Parent");



				/**
				 * Enabling TextChangedListener for  Email-ID field signup
				 * */
				hh_edtfield_q_5.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
						// When user changed the Text
						DebugLog.console("inside onTextChanged:" );

						hh_edtfield_q_5_edt_answer = hh_edtfield_q_5.getText().toString().trim();
						reTypedEmailID = editTextReTypeEmailID.getText().toString().trim();

						if (reTypedEmailID.length()==0){
//							hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//							editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//							hh_edtfield_q_5.setCompoundDrawablePadding(8);
//							editTextReTypeEmailID.setCompoundDrawablePadding(8);

						}else{
							if (hh_edtfield_q_5_edt_answer.length() < reTypedEmailID.length() || hh_edtfield_q_5_edt_answer.length() > reTypedEmailID.length()){
//								hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//								hh_edtfield_q_5.setCompoundDrawablePadding(8);
//								editTextReTypeEmailID.setCompoundDrawablePadding(8);
							}else if (hh_edtfield_q_5_edt_answer.equalsIgnoreCase(reTypedEmailID)) {
//								hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								hh_edtfield_q_5.setCompoundDrawablePadding(8);
//								editTextReTypeEmailID.setCompoundDrawablePadding(8);
							}
						}
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

				/**
				 * Enabling TextChangedListener for re-typed email address field signup
				 * */
				editTextReTypeEmailID.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
						// When user changed the Text
						DebugLog.console("inside onTextChanged:" );

						hh_edtfield_q_5_edt_answer = hh_edtfield_q_5.getText().toString().trim();
						reTypedEmailID = editTextReTypeEmailID.getText().toString().trim();
						if (reTypedEmailID.length()==0){
//							hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//							editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//							hh_edtfield_q_5.setCompoundDrawablePadding(8);
//							editTextReTypeEmailID.setCompoundDrawablePadding(8);
						}else {
							if (hh_edtfield_q_5_edt_answer.length() < reTypedEmailID.length() || hh_edtfield_q_5_edt_answer.length() > reTypedEmailID.length()){
//								hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								hh_edtfield_q_5.setCompoundDrawablePadding(8);
//								editTextReTypeEmailID.setCompoundDrawablePadding(8);
							}else if (hh_edtfield_q_5_edt_answer.equalsIgnoreCase(reTypedEmailID)) {
//								hh_edtfield_q_5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								hh_edtfield_q_5.setCompoundDrawablePadding(8);
//								editTextReTypeEmailID.setCompoundDrawablePadding(8);
							}
						}
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



				editTextReTypeEmailID.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						if(actionId== EditorInfo.IME_ACTION_DONE){
							//do something
							if(ConnectionVerifier.isInternetOn(appContext)){
								grabEnteredTextForSignUp(editTextReTypeEmailID);
							}else{

								showAlert( getResources().getString(R.string.signup_activity_alert_box_title),getResources().getString(R.string.connection_error_message));
							}

							return true;
						}
						return false;
					}
				});

//				editTextPhone = (EditText)findViewById(R.id.sign_up_phone_textfield);
//				editTextPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.signup_phone_icon, 0, 0, 0);
//				editTextPhone.setCompoundDrawablePadding(8);

				String number = "";//MpcUtil.getPhoneNumber(appContext);
				if (number.equalsIgnoreCase("")) {
					//	editTextPhone.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_in_phone_textfield_label) + "</font>" + "<small>"));
				}else{
					//	editTextPhone.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_in_phone_textfield_label) + "</font>" + "<small>"));

//					editTextPhone.setText(number);
				}


				editTextPassword = (EditText)findViewById(R.id.sign_up_password_textfield);

//				editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//				editTextPassword.setCompoundDrawablePadding(8);


//				editTextPassword.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_password_textfield_label) + "</font>" + "<small>"));

				editTextConfirmPassword = (EditText)findViewById(R.id.sign_up_confirm_password_textfield);

//				editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//				editTextConfirmPassword.setCompoundDrawablePadding(8);
//
//				editTextConfirmPassword.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_confirm_password_textfield_label) + "</font>" + "<small>"));


				btnCreateAccount=(ImageButton)findViewById(R.id.sign_up_btn);
				btnHHNew=(ImageButton)findViewById(R.id.hh_btn);



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




				/**
				 * Enabling TextChangedListener for  password field signup
				 * */
				editTextPassword.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
						// When user changed the Text
						DebugLog.console("inside onTextChanged:" );

						password = editTextPassword.getText().toString().trim();
						hh_edtfield_q_3_edt_answer = "";//editTextConfirmPassword.getText().toString().trim();

						if (hh_edtfield_q_3_edt_answer.length()==0){
//							editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//							editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//							editTextPassword.setCompoundDrawablePadding(8);
//							editTextConfirmPassword.setCompoundDrawablePadding(8);
						}else{
							if (password.length() < hh_edtfield_q_3_edt_answer.length() || password.length() > hh_edtfield_q_3_edt_answer.length()){
//								editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextPassword.setCompoundDrawablePadding(8);
//								editTextConfirmPassword.setCompoundDrawablePadding(8);
							}else if (password.equalsIgnoreCase(hh_edtfield_q_3_edt_answer)) {
//								editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextPassword.setCompoundDrawablePadding(8);
//								editTextConfirmPassword.setCompoundDrawablePadding(8);
							}
						}
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

				/**
				 * Enabling TextChangedListener for confirm password field signup
				 * */
				editTextConfirmPassword.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
						// When user changed the Text
						DebugLog.console("inside onTextChanged:" );

						password = editTextPassword.getText().toString().trim();
						//	hh_edtfield_q_3_edt_answer = editTextConfirmPassword.getText().toString().trim();
						if (hh_edtfield_q_3_edt_answer.length()==0){
//							editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//							editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//							editTextPassword.setCompoundDrawablePadding(8);
//							editTextConfirmPassword.setCompoundDrawablePadding(8);

						}else {
							if (password.length() < hh_edtfield_q_3_edt_answer.length() || password.length() > hh_edtfield_q_3_edt_answer.length()){
//								editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextPassword.setCompoundDrawablePadding(8);
//								editTextConfirmPassword.setCompoundDrawablePadding(8);
							}else if (password.equalsIgnoreCase(hh_edtfield_q_3_edt_answer)) {
//								editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextPassword.setCompoundDrawablePadding(8);
//								editTextConfirmPassword.setCompoundDrawablePadding(8);
							}
						}
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


				editTextConfirmPassword.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						if(actionId== EditorInfo.IME_ACTION_DONE){
							//do something
							if(ConnectionVerifier.isInternetOn(appContext)){
								grabEnteredTextForSignUpPartTwo();
							}else{

								showAlert( getResources().getString(R.string.signup_activity_alert_box_title),getResources().getString(R.string.connection_error_message));
							}

							return true;
						}
						return false;
					}
				});



				String time = MpcUtil.getcurrentTime(17);
//				hh_edtfield_q_5.setText(time+"@setech.com");
//				editTextReTypeEmailID.setText(time+"@setech.com");
//				editTextPassword.setText("test");
//				editTextConfirmPassword.setText("test");











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
			//lastName = editTextLastName.getText().toString();
			hh_edtfield_q_5_edt_answer = hh_edtfield_q_5.getText().toString();
			reTypedEmailID = editTextReTypeEmailID.getText().toString();
			//AnalyticsUtil.updateEventAction(appContext, "Email Address : "+hh_edtfield_q_2_edt_answer);
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

			}else if (hh_edtfield_q_5_edt_answer.trim().length() == 0 ){
				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_5);
				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
				hh_edtfield_q_5.requestFocus();
				MpcUtil.restartInput(appContext, hh_edtfield_q_5);
				error = true;
//			}else if (!MpcUtil.isEmailValid(hh_edtfield_q_2_edt_answer.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_5);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_5.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_5);
//				error = true;
			}else if (reTypedEmailID.trim().length()==0){
				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
				editTextReTypeEmailID.requestFocus();
				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
				error = true;
			}else if (!reTypedEmailID.trim().equalsIgnoreCase(hh_edtfield_q_5_edt_answer.trim())){
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
			hh_edtfield_q_5.setVisibility(View.VISIBLE);

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




	public boolean  c05Next(View v){


		try {

			if (main.getChildCount() == 0) {
				//TODO store
				Toast.makeText(this, "Please add at least one phone number", Toast.LENGTH_SHORT).show();
				return false;
			} else {

				numberList = new ArrayList<JSONObject>();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < main.getChildCount(); i++) {
					JSONObject obj = new JSONObject();
					View view = main.getChildAt(i);
					EditText phoneNumber = view.findViewById(R.id.editTextPhone);
					EditText phoneStatus = view.findViewById(R.id.phone1_status);


					if (phoneNumber.getText().toString().trim().equalsIgnoreCase("")) {
						Toast.makeText(this, "Phone number invald " + phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();

						return false;
					} else if (phoneNumber.getText().toString().trim().length() != 10) {
						Toast.makeText(this, "Phone number invalid " + phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();

						return false;

					} else {


						RadioGroup vstatus = view.findViewById(R.id.hh_edtfield_q_5_rdg);

						int checkedID = vstatus.getCheckedRadioButtonId();
						DebugLog.console("[HH_Screen_three] inside checkedID() " + checkedID);
						if (checkedID == -1) {
							Toast.makeText(this, "Please add status", Toast.LENGTH_SHORT).show();

							return false;
						}



						obj.put("number", phoneNumber.getText().toString());


						if (vstatus.getChildAt(0).getId()==checkedID){
							obj.put("status", "1");
						}if (vstatus.getChildAt(1).getId()==checkedID){
							obj.put("status", "2");
						}if (vstatus.getChildAt(2).getId()==checkedID){
							obj.put("status", "3");
						}if (vstatus.getChildAt(3).getId()==checkedID){
							obj.put("status", "4");
						}

						DebugLog.console("[HH_Screen_three] inside c05Next() "+obj.toString());
						numberList.add(obj);

						DebugLog.console("[HH_Screen_three] inside c05Next: phoneNumber : " + phoneNumber.getText().toString() + "phoneStatus : " + phoneStatus.getText().toString());
//						sb.append(phoneNumber.getText().toString());
//						sb.append("|");
//						sb.append(phoneStatus.getText().toString());
//						sb.append(",");


					}
				}
//				sb.deleteCharAt(sb.length() - 1);


				return true;
			}
		}catch(Exception ex) {
			return false;
		}
	}






	public void grabEnteredTextForscreenthreenextbtn(View v) {

		boolean error = false;
		try {

			sign_in_back_btn.setVisibility(View.GONE);


			Question_5_1 = edt_Question_5_1 .getText().toString();
			Question_5_2 = edt_Question_5_2 .getText().toString();
			Question_5_3 = edt_Question_5_3 .getText().toString();
			Question_5_4 = edt_Question_5_4 .getText().toString();


			hh_edtfield_q_5_edt_answer = hh_edtfield_q_5.getText().toString();
			hh_edtfield_q_4_edt_answer = hh_edtfield_q_4.getText().toString().trim();

			DebugLog.console("[HH_Screen_two] inside grabEnteredTextForscreenthreenextbtn() hh_edtfield_q_5_edt_answer "+hh_edtfield_q_5_edt_answer);
			reTypedEmailID = editTextReTypeEmailID.getText().toString();
			password = editTextPassword.getText().toString();
			//hh_edtfield_q_3_edt_answer = //editTextConfirmPassword.getText().toString();
			//phone = editTextPhone.getText().toString();
			//userName = editTextUserName.getText().toString();
			firstName = editTextfirstName.getText().toString();
			//lastName = editTextLastName.getText().toString();



			if(!hh_edtfield_q_5_edt_answer.equalsIgnoreCase("")){
				if(hh_edtfield_q_5_edt_answer.equalsIgnoreCase("999") || hh_edtfield_q_5_edt_answer.equalsIgnoreCase("888")){

				}else{
					error = true;
					showAlert(appContext.getResources().getString(R.string.app_name),"Only 999 & 888 allowed");
				}


			}else


			if (!c05Next(null)){
				error = true;

			}

			if(!error){



				boolean screen_three = true;
				if (screen_three){


					saveDataNow();
					return ;
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


			hh_edtfield_q_5_edt_answer = hh_edtfield_q_5.getText().toString();
			reTypedEmailID = editTextReTypeEmailID.getText().toString();
			password = editTextPassword.getText().toString();


			String selectedRadio = ""+ sign_up_confirm_password_textfield_rb.getCheckedRadioButtonId();//editTextConfirmPassword.getText().toString();
			if(selectedRadio.equalsIgnoreCase(R.id.migration1+"")){
				selectedRadio = 1+"";
			}if(selectedRadio.equalsIgnoreCase(R.id.migration2+"")){
				selectedRadio = 2+"";
			}
			if(selectedRadio.equalsIgnoreCase("-1")){
				selectedRadio = "";
			}
			hh_edtfield_q_3_edt_answer = selectedRadio;

			firstName = editTextfirstName.getText().toString();






			if (hh_edtfield_q_5_edt_answer.trim().length() == 0 ){
				//AnalyticsUtil.updateEventAction(appContext, " Email field empty");
				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_5);
				showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 2");
				hh_edtfield_q_5.requestFocus();
				MpcUtil.restartInput(appContext, hh_edtfield_q_5);
				error = true;
//			}else if (!MpcUtil.isEmailValid(hh_edtfield_q_2_edt_answer.trim())){
//				//AnalyticsUtil.updateEventAction(appContext, "Invalid Email Address : "+hh_edtfield_q_2_edt_answer.trim());
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_5);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_5.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_5);
//				error = true;
//			}else if (reTypedEmailID.trim().length()==0){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(appContext.getResources().getString(R.string.app_name),"Please fill Question 2");
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
				//AnalyticsUtil.updateEventAction(appContext, " Re-type Email field empty");
//			}else if (!reTypedEmailID.trim().equalsIgnoreCase(hh_edtfield_q_2_edt_answer.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " Re-type Email and email mismatch :"+reTypedEmailID.trim()+" email :"+hh_edtfield_q_2_edt_answer.trim());
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
//				DebugLog.console("userName: "+userName+"\r\nfirstName"+ firstName+ "\r\nlastName"+lastName+"\r\nhh_edtfield_q_2_edt_answer"+ hh_edtfield_q_2_edt_answer+"\r\npassword"+ password);
//				json = JsonHandler.createSIGNUPJsonObject(appContext, firstName, hh_edtfield_q_2_edt_answer, phone,password, "careteen", "android", MpcUtil.DEVICE_UNIQUE_ID,MpcUtil.BUILD_INFO);
//				//EmailDebugLog.getInstance(appContext).writeLog(json.toString());
//				MainUserInfo.devicesTempLockPassword = password;
//				MainUserInfo.REGISTERED_EMAIL_ADDRESS = hh_edtfield_q_2_edt_answer;
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
//			DebugLog.console("userName: "+userName+"\r\nfirstName"+ firstName+ "\r\nlastName"+lastName+"\r\nhh_edtfield_q_2_edt_answer"+ hh_edtfield_q_2_edt_answer+"\r\npassword"+ password);
//			MainUserInfo.REGISTERED_EMAIL_ADDRESS = hh_edtfield_q_2_edt_answer;
//			String url = MpcUtil.getBaseURL(appContext)+ MpcUtil.EMAIL_ID_VERIFICATION_URL + Uri.encode(hh_edtfield_q_2_edt_answer);
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


					DebugLog.console("[HH_Screen_two] inside run() Question_1 " + hh_edtfield_q_5_edt_answer);
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

	private boolean saveDatainDataBase(String District_Code , String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id , String q1_headname) {
		boolean dataSaved = false;
		try {


			if(PSU_code.equalsIgnoreCase("1001")||PSU_code.equalsIgnoreCase("1002")||PSU_code.equalsIgnoreCase("1003")){

				Tehsil_code = "10";
				Tehsil_Name = "Peshawar";
				District_Name = "Peshawar";
				District_Code = "1";
			}else if(PSU_code.equalsIgnoreCase("1004")||PSU_code.equalsIgnoreCase("1005")) {
				Tehsil_code = "11";
				Tehsil_Name = "Peshawar cantt";
				District_Name = "Peshawar";
				District_Code = "1";
			}else if(PSU_code.equalsIgnoreCase("2021")||PSU_code.equalsIgnoreCase("2022")||PSU_code.equalsIgnoreCase("2023")){
				Tehsil_code = "19";
				Tehsil_Name = "Rawalpindi";
				District_Name = "Rawalpindi";
				District_Code = "2";
			}else if(PSU_code.equalsIgnoreCase("2024")){
				Tehsil_code = "20";
				Tehsil_Name = "Gujjar khan";
				District_Name = "Rawalpindi";
				District_Code = "2";
			}


			Tehsil_code = "1";
			Tehsil_Name = "Peshawar";
			District_Name = "Peshawar";
			District_Code = "1";


			String hhid_q1 = HH_id;
			String hhid_q5 = hh_edtfield_q_5_edt_answer;

			dataSaved = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_insert_data_screen_three(District_Code ,  District_Name , Tehsil_code , Tehsil_Name , PSU_code , PSU_name , Structure_id , HH_id ,hhid_q5);


			if (dataSaved){

				if(hhid_q5.equalsIgnoreCase("999") || hhid_q5.equalsIgnoreCase("888")){
					//Todo delete code from phone tab;e



					dataSaved = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_delete_number_list(District_Code ,  District_Name , Tehsil_code , Tehsil_Name , PSU_code , PSU_name , Structure_id , HH_id ,"","");
					DebugLog.console("[HH_Screen_three] inside saveDatainDataBase() datadeleted "+dataSaved);
				}else{


					for (int k=0; k<numberList.size(); k++){
						DebugLog.console("[HH_Screen_three] inside saveDatainDataBase() number"+numberList.get(k).getString("number"));
						DebugLog.console("[HH_Screen_three] inside saveDatainDataBase() status "+numberList.get(k).getString("status"));

						dataSaved = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_insert_number_list(District_Code ,  District_Name , Tehsil_code , Tehsil_Name , PSU_code , PSU_name , Structure_id , HH_id ,numberList.get(k).getString("number"),numberList.get(k).getString("status"));


					}

				}

			}else{



			}



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



			boolean dataSaved = saveDatainDataBase("", "", "", "", HHIDConfigurations.getPeshawarCurrentPSU(appContext), HHIDConfigurations.getPeshawarCurrentPSUName(appContext), current_STID + "", CURRENT_HHID + "", hh_edtfield_q_5_edt_answer);





			if (dataSaved){

				boolean screen_three = true;




				//	HH_Screen_three.START_TIME = MpcUtil.getcurrentTime(14);


				if (screen_three ) {

					Intent intent = MpcUtil.buildNewIntent(appContext, MainMenuActivity.class);
					intent.putExtra("launchActivity", "signup");
					intent.putExtra("backon", "HH_Screen_two");
//                        intent.putExtra("q10", aghhid_c_2);
//                        startActivity(intent);
					Intent returnIntent = new Intent();
					returnIntent.putExtra("isDataUpdated", false);
					setResult(Activity.RESULT_OK, returnIntent);
					finish();
					return;
				}


				if (NEW_HH_IN_SAME_STRUCTURE) {


					//HHIDConfigurations.setPeshawarLastHHIDagainstPSUAndStructID(appContext,HHIDConfigurations.getPeshawarCurrentPSU(appContext))

					//	HHIDConfigurations.setPeshawarLastHHIDagainstPSUAndStructID(appContext, HHIDConfigurations.getPeshawarCurrentPSU(appContext) + "_" + current_STID, CURRENT_HHID + "");
					HHIDConfigurations.setPeshawarLastHHIDagainstPSUAndStructID(appContext, HHIDConfigurations.getPeshawarCurrentPSU(appContext)+"_hhid" , CURRENT_HHID + "");
					hh_edtfield_q_5.setText("");
					editTextReTypeEmailID.setText("");
					editTextReTypeEmailID.setText("");
					editTextPassword.setText("");
//					editTextConfirmPassword.setText("");
					sign_up_confirm_password_textfield_rb.clearCheck();
					hh_edtfield_q_3_edt_answer = "-1";
					migration1.setChecked(false);
					migration2.setChecked(false);
//							edt_Question_5_1.setText("");
//							edt_Question_5_2.setText("");
//							edt_Question_5_3.setText("");
//							edt_Question_5_4.setText("");

					String currentPSUCode = HHIDConfigurations.getPeshawarCurrentPSU(appContext);
					DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() currentPSUCode " + currentPSUCode);

					String last_strict_id_against_psu = HHIDConfigurations.getPeshawarLastStructerIDagainstPSU(appContext, currentPSUCode);

					current_STID = Integer.parseInt(last_strict_id_against_psu) + 1;

					DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() last_strict_id_against_psu " + last_strict_id_against_psu);

					String lastHHIID = HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(appContext, currentPSUCode+"_hhid" );
					CURRENT_HHID = Integer.parseInt(lastHHIID) + 1;


					int count =  HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_getCountOfhhid_having_phoneNumber(HHIDConfigurations.getPeshawarCurrentPSU(appContext),"");


					String stringTopBar = getResources().getString(R.string.screen_two_top_bar,currentPSUCode,count+"",CURRENT_HHID+"", current_STID+"");

					top_bar.setText(stringTopBar);

					//showAlert("Struct ID : " + last_strict_id_against_psu, "\nSuccessfully saved");


				} else {

					HHIDConfigurations.setPeshawarLastStructerIDagainstPSU(appContext, HHIDConfigurations.getPeshawarCurrentPSU(appContext), current_STID + "");
					HHIDConfigurations.setPeshawarLastHHIDagainstPSUAndStructID(appContext, HHIDConfigurations.getPeshawarCurrentPSU(appContext) +"_hhid", CURRENT_HHID + "");


					hh_edtfield_q_5.setText("");
					editTextReTypeEmailID.setText("");
					editTextReTypeEmailID.setText("");
					editTextPassword.setText("");
					//editTextConfirmPassword.setText("");
					hh_edtfield_q_3_edt_answer = "-1";
					migration1.setChecked(false);
					migration2.setChecked(false);
					sign_up_confirm_password_textfield_rb.clearCheck();
					edt_Question_5_1.setText("");
					edt_Question_5_2.setText("");
					edt_Question_5_3.setText("");
					edt_Question_5_4.setText("");

					String currentPSUCode = HHIDConfigurations.getPeshawarCurrentPSU(appContext);
					DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() currentPSUCode " + currentPSUCode);

					String last_strict_id_against_psu = HHIDConfigurations.getPeshawarLastStructerIDagainstPSU(appContext, currentPSUCode);

					current_STID = Integer.parseInt(last_strict_id_against_psu) + 1;

					DebugLog.console("[HH_Screen_two] inside initializeReferenceOfViews() last_strict_id_against_psu " + last_strict_id_against_psu);

					//String lastHHIID = HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(appContext, currentPSUCode + "_" + current_STID);
					String lastHHIID = HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(appContext, currentPSUCode+"_hhid" );

					CURRENT_HHID = Integer.parseInt(lastHHIID) + 1;


					int count =  HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).hhid_getCountOfhhid_having_phoneNumber(HHIDConfigurations.getPeshawarCurrentPSU(appContext),"");


					String stringTopBar = getResources().getString(R.string.screen_two_top_bar,currentPSUCode,count+"",CURRENT_HHID+"", current_STID+"");
					top_bar.setText(stringTopBar);

					//	showAlert("Struct ID : " + last_strict_id_against_psu, "\nSuccessfully saved");

				}


				grabEnteredTextForscreenthreeBackbtn(screenthreeBackbtnonerl);


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
				success = ResponceVerifier.verifyReceievedResponce(HH_Screen_three.this,msg.what);
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
		new AlertDialog.Builder(HH_Screen_three.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
					}
				})
				.show();
	}





	public void showPermanentExpiredAlert(String title, String message){
		new AlertDialog.Builder(HH_Screen_three.this)
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
//			if (HHIDConfigurations.getMappingId(appContext)==null &&  !HHIDConfigurations.getSignInUpShownOneTime(appContext)){
//
//				if(launchedActivityName.equalsIgnoreCase("signup_part_two")){
//
//					setContentView(R.layout.sign_up);
//
//					launchedActivityName = "signup";
//					initializeReferenceOfViews();
//				}else{
//
//					if (!HHIDConfigurations.getSignInUpShownOneTime(appContext)) {
//						if ("HH_MainActivity".equalsIgnoreCase(calledFromView)) {
//							Intent backIntent = MpcUtil.buildNewIntent(appContext, HH_MainActivity.class);
//							startActivity(backIntent);
//						} else {
//							Intent backIntent = MpcUtil.buildNewIntent(appContext, ChildRemoteMonitoringActivity.class);
//							startActivity(backIntent);
//						}
//					}
//					calledFromView="";
//					selfClose =  true;
//					finish();
//				}
//			}else{
//				finish();
//
//			}




			calledFromView="";
			selfClose =  true;

			Intent backIntent = MpcUtil.buildNewIntent(appContext, HH_Screen_two.class);
			backIntent.putExtra("launchActivity", "signup");
			startActivity(backIntent);
			finish();
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
			DebugLog.console("[HH_Screen_One] Inside onBackPressed:" );

//			if (HHIDConfigurations.getMappingId(appContext)==null){
//
//				if(launchedActivityName.equalsIgnoreCase("signup_part_two")){
//
//					setContentView(R.layout.sign_up);
//					launchedActivityName = "signup";
//					initializeReferenceOfViews();
//				}else{
//
//					if(!HHIDConfigurations.getSignInUpShownOneTime(appContext)) {
//						if ("HH_MainActivity".equalsIgnoreCase(calledFromView)) {
//							Intent backIntent = MpcUtil.buildNewIntent(appContext, HH_MainActivity.class);
//							startActivity(backIntent);
//						} else {
//							Intent backIntent = MpcUtil.buildNewIntent(appContext, ParentSelectDeviceActivity.class);
//							startActivity(backIntent);
//						}
//					}
//					calledFromView="";
//					selfClose =  true;
//					finish();
//
//
//				}
//
//			}
			calledFromView="";
			selfClose =  true;

			Intent backIntent = MpcUtil.buildNewIntent(appContext, HH_Screen_two.class);
			backIntent.putExtra("launchActivity", "signup");
			startActivity(backIntent);
			finish();

		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside onBackPressed");
			finish();
		}
	}

	public void showWarningAlert(String title, String message){
		new AlertDialog.Builder(HH_Screen_three.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){

						pd = ProgressDialog.show(HH_Screen_three.this, getResources().getString(R.string.signin_activity_forgot_password_progress_bar_title), getResources().getString(R.string.signup_activity_forgot_password_text), true, false);
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

			JSONObject data = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).getDataFromtable(appContext, HHIDConfigurations.getPeshawarCurrentPSU(appContext),CURRENT_HHID+"");
			DebugLog.console("[HH_Screen_three] inside onStart() "+data.toString());

			if (data.length()>0){

				if(data.getString("hhid_q5").equalsIgnoreCase("null"))
					data.put("hhid_q5","");

				hh_edtfield_q_5.setText(data.getString("hhid_q5"));

				DebugLog.console("[HH_Screen_three] inside onStart() update views "+"hhid_q5");

			}

			JSONArray phonedataarray = HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).getDataFromPhoneTable(appContext, HHIDConfigurations.getPeshawarCurrentPSU(appContext),CURRENT_HHID+"");
			DebugLog.console("[HH_Screen_three] inside phonedataarray onStart() "+phonedataarray.toString());



			if(phonedataarray.length()>0){

				int k =0;
				for (k=0;k<phonedataarray.length();k++){
					DebugLog.console("[HH_Screen_three] inside onStart() inside loop");

					LayoutInflater inflater = getLayoutInflater();
					View view = inflater.inflate(R.layout.phone_number_layout, null);
					main.addView(view, main.getChildCount(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
					EditText phoneNumber = view.findViewById(R.id.editTextPhone);
					JSONObject obj;
					obj = phonedataarray.getJSONObject(k);
					DebugLog.console("[HH_Screen_three] inside obj onStart() "+obj.toString());
					phoneNumber.setText(obj.getString("hhid_phone_number"));

					RadioGroup vstatus = view.findViewById(R.id.hh_edtfield_q_5_rdg);

					vstatus.check(vstatus.getChildAt(Integer.parseInt(obj.getString("status"))-1).getId());

					Button callbutton =view.findViewById(R.id.callbutton);

					callbutton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {

							if(phoneNumber.getText().toString().equalsIgnoreCase("")){
								Toast.makeText(HH_Screen_three.this, "Please add phone number", Toast.LENGTH_SHORT).show();
							}
							else if (phoneNumber.getText().toString().trim().length() != 10) {
								Toast.makeText(HH_Screen_three.this, "Phone number invalid " + phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();

							}else {

								if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
									requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
								}else {
									DialUserNumber(phoneNumber.getText().toString());
								}


							}
						}
					});


					Button removebutton =view.findViewById(R.id.removebutton);
					removebutton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							main.removeView(view);
						}
					});




				}




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

			finish();

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
			Intent intent = new Intent(HH_Screen_three.this, MainMenuActivity.class);
//            startActivity(intent);
			Intent returnIntent = new Intent();
			returnIntent.putExtra("isDataUpdated", false);
			setResult(Activity.RESULT_OK, returnIntent);
			finish();

		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_two] inside gotohomeScreen() Exception is :"+e.toString());
		}
	}

	public void c05AddPhone(View v){


		boolean error = false;

		hh_edtfield_q_5_edt_answer = hh_edtfield_q_5.getText().toString().trim();

		DebugLog.console("[HH_Screen_three] inside c05AddPhone() hh_edtfield_q_5_edt_answer"+hh_edtfield_q_5_edt_answer);


		if(!hh_edtfield_q_5_edt_answer.equalsIgnoreCase("")){
			if(hh_edtfield_q_5_edt_answer.equalsIgnoreCase("999") || hh_edtfield_q_5_edt_answer.equalsIgnoreCase("888")){
				error = true;
				DebugLog.console("[HH_Screen_three] inside c05AddPhone() 999 detected");

				showAlert(appContext.getResources().getString(R.string.app_name),"Please remove value from text field s");
				return;
			}






		}



		if(main.getChildCount()==0){

		}else{
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < main.getChildCount(); i++) {
				View view = main.getChildAt(i);
				EditText phoneNumber = view.findViewById(R.id.editTextPhone);
				EditText phoneStatus = view.findViewById(R.id.phone1_status);

				RadioGroup vstatus = view.findViewById(R.id.hh_edtfield_q_5_rdg);

				int checkedID = vstatus.getCheckedRadioButtonId();



				if (phoneNumber.getText().toString().trim().equalsIgnoreCase("")){
					Toast.makeText(this, "Phone number invald "+phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();

					error=true;
				}else 		if (phoneNumber.getText().toString().trim().length()!=10){
					Toast.makeText(this, "Phone number invalid "+phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();

					error=true;
				}else

					DebugLog.console("[HH_Screen_three] inside checkedID() "+checkedID);
				if (checkedID==-1){
					Toast.makeText(this, "Please add status against phone number "+phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();
					error = true;
				}


			}


		}





		if(!error) {

			LayoutInflater inflater = getLayoutInflater();
			View view = inflater.inflate(R.layout.phone_number_layout, null);
			main.addView(view, main.getChildCount(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

			Button callbutton =view.findViewById(R.id.callbutton);
			EditText phoneNumber = view.findViewById(R.id.editTextPhone);
			callbutton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if(phoneNumber.getText().toString().equalsIgnoreCase("")){
						Toast.makeText(HH_Screen_three.this, "Please add phone number", Toast.LENGTH_SHORT).show();
					}
					else if (phoneNumber.getText().toString().trim().length() != 10) {
						Toast.makeText(HH_Screen_three.this, "Phone number invalid " + phoneNumber.getText().toString(), Toast.LENGTH_SHORT).show();

					}else {

						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
							requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
						}else {
							DialUserNumber(phoneNumber.getText().toString());
						}


					}
				}
			});

			Button removebutton =view.findViewById(R.id.removebutton);
			removebutton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					main.removeView(view);
				}
			});

		}
	}

	void DialUserNumber(String number) {
		if (ActivityCompat.checkSelfPermission(HH_Screen_three.this,
				Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
			return;

		}
		Intent callIntent = new Intent(Intent.ACTION_CALL);

		String phoneNumber = number.toString().trim();

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
		//callIntent.setData(Uri.parse("tel:" + "03006982661"));
		callIntent.setData(Uri.parse("tel:" + phoneNumber));
		startActivity(callIntent);
//		startActivityForResult(callIntent, 99);

	}

	private String getSimNetwork() {
		TelephonyManager phoneMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		return phoneMgr.getSimOperatorName();
	}
	public void setMigration1(View view) {

		hh_edtfield_q_3_edt_answer = "1";
	}

	public void setMigration2(View view) {
		hh_edtfield_q_3_edt_answer = "2";
	}
	public void setMigration3(View view) {
		hh_edtfield_q_3_edt_answer = "3";
	}

	public void setMigration4(View view) {
		hh_edtfield_q_3_edt_answer = "4";
	}

}
