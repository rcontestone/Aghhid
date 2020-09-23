package com.rcons.fcallbacks.Athreehhid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.filemanager.FileManager;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.HHIDConfigurations;

import com.rcons.fcallbacks.Main.AddReportActivity;
import com.rcons.fcallbacks.Main.MainMenuActivity;
import com.rcons.fcallbacks.R;
import com.rcons.fcallbacks.Utilties.AppController;
import com.rcons.fcallbacks.Utilties.MpcUtil;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.http.ConnectionVerifier;
import com.rcons.fcallbacks.http.ResponceVerifier;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class HH_SECTION_E_ONE extends Activity {

	FloatingActionButton btn_AddReportQuestionnaire;
	//Use For sign-up
	EditText editTextfirstName;
	//	EditText editTextLastName;
	EditText editTextUserName;
	EditText editTextEmailID;
	EditText editTextReTypeEmailID;
	EditText editTextPhone;
	EditText editTextPassword;
	EditText editTextConfirmPassword;
	ImageButton btnCreateAccount;
	ImageButton nextbtn;
	String phone = "";
	String userName = "";
	String firstName = "";
	String lastName = "";
	String emailID = "";
	String reTypedEmailID = "";
	String password = "";
	String confirmPassword = "";
	String launchedActivityName = "";

	// Use For sign-in
	EditText editTextUserNameToLogin;
	EditText editTextPasswordToLogin;
	String userNameToLogin = "";
	String passwordToLogin = "";
	ImageButton btnSignIn;

	TextView sign_up_eula_textview_label_line_two,sign_up_eula_btn_label_below_btn_line_one,sign_in_eula_btn_label_below_btn_line_one = null;

	JSONObject result = null;
	static String[] licenseLabels = null;
	public static JSONArray licenseList = null;
	private ProgressDialog pd = null;
	Context appContext =  null;
	boolean selfClose = false;
	public static String calledFromView = ""; //Use by m_handler to show responce to user in case of request from forgot-password view or from signup-in view
	public Spinner spiiner_messge,spiiner_user;
	public static String SELECTED_SPINNER_VALUE = "";
	public static  int SELECTED_SPINNER_VALUE_INDEX = 0;

	public static String SELECTED_USER_SPINNER_VALUE = "";
	public static  int SELECTED_USER_SPINNER_VALUE_INDEX = 0;


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
			//HouseHoldDataBaseHelper.getDataBaseProcessor(MyApplication.getAppContext());

//			Bundle bundle = getIntent().getExtras();
//			launchedActivityName = bundle.getString("launchActivity");

			launchedActivityName = "signin";

					setContentView(R.layout.sign_in);
					//AnalyticsUtil.updateScreen(this,getResources().getString(R.string.sign_in_screen_top_bar_text_view));



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
	}public HH_SECTION_E_ONE() {
		// TODO Auto-generated constructor stub
	}


	private void initializeReferenceOfViews() {
		// TODO Auto-generated method stub
		try {

			if (launchedActivityName.equalsIgnoreCase("signup")) {

				String stringToShow =getResources().getString(R.string.sign_up_eula_textview_label_line_two);


				String stringToShowforSigninnavigation = getResources().getString(R.string.sign_up_eula_btn_label_below_btn_line_two);

				sign_up_eula_btn_label_below_btn_line_one = (TextView)findViewById(R.id.textViewbelowbtn);
				sign_up_eula_btn_label_below_btn_line_one.setText(Html.fromHtml(stringToShowforSigninnavigation));

				btn_AddReportQuestionnaire = (FloatingActionButton) findViewById(R.id.btn_AddReportQuestionnaire);
				sign_up_eula_textview_label_line_two = (TextView)findViewById(R.id.sign_up_eula_textview_label_line_two);
				sign_up_eula_textview_label_line_two.setText(Html.fromHtml(stringToShow));


				//sign_up_eula_btn_label_below_btn_line_one.setVisibility(View.GONE);
				//sign_up_eula_textview_label_line_two.setVisibility(View.GONE);

				editTextfirstName = (EditText)findViewById(R.id.sign_up_first_name_textfield);
				editTextfirstName.setLines(1);
				editTextfirstName.setInputType(InputType.TYPE_CLASS_TEXT);
				editTextfirstName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_icon, 0, 0, 0);
				editTextfirstName.setCompoundDrawablePadding(8);

				editTextfirstName.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_first_name_textfield_label) + "</font>" + "<small>"));


				editTextEmailID =  (EditText)findViewById(R.id.hh_edtfield_q_2);
//				hh_edtfield_q_2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//				hh_edtfield_q_2.setCompoundDrawablePadding(8);
				editTextReTypeEmailID = (EditText)findViewById(R.id.sign_up_confirm_email_textfield);


				//hh_edtfield_q_2.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_email_name_textfield_label) + "</font>" + "<small>"));

				spiiner_messge = (Spinner) findViewById(R.id.spiiner_messge);
				spiiner_user = (Spinner) findViewById(R.id.spiiner_user);


				spiiner_messge.setVisibility(View.GONE);
				editTextEmailID.setVisibility(View.GONE);
				editTextReTypeEmailID.setVisibility(View.GONE);


				spiiner_messge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
				{
					@Override
					public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

						String selecteditem =  adapter.getItemAtPosition(i).toString();
						DebugLog.console("[spiiner_messge] inside onItemSelected() "+selecteditem);
						//or this can be also right: selecteditem = level[i];
						SELECTED_SPINNER_VALUE = selecteditem;
						SELECTED_SPINNER_VALUE_INDEX = i;
						DebugLog.console("[spiiner_messge] inside onItemSelected() SELECTED_SPINNER_VALUE"+SELECTED_SPINNER_VALUE);
						DebugLog.console("[spiiner_messge] inside onItemSelected() SELECTED_SPINNER_VALUE_INDEX"+SELECTED_SPINNER_VALUE_INDEX);

					}
					@Override
					public void onNothingSelected(AdapterView<?> parentView)
					{

					}
				});



				spiiner_user.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
				{
					@Override
					public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

						String selecteditem =  adapter.getItemAtPosition(i).toString();
						DebugLog.console("[spiiner_user] inside onItemSelected() "+selecteditem);
						//or this can be also right: selecteditem = level[i];
						SELECTED_USER_SPINNER_VALUE = selecteditem;
						SELECTED_USER_SPINNER_VALUE_INDEX = i;
						DebugLog.console("[spiiner_user] inside onItemSelected() SELECTED_USER_SPINNER_VALUE"+SELECTED_USER_SPINNER_VALUE);
						DebugLog.console("[spiiner_user] inside onItemSelected() SELECTED_USER_SPINNER_VALUE_INDEX"+SELECTED_USER_SPINNER_VALUE_INDEX);

					}
					@Override
					public void onNothingSelected(AdapterView<?> parentView)
					{

					}
				});



//				editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//				editTextReTypeEmailID.setCompoundDrawablePadding(8);
//
//				editTextReTypeEmailID.setHint(Html.fromHtml("<small><font>" + getResources().getString(R.string.sign_up_confirm_email_name_textfield_label) + "</font>" + "<small>"));





				//Handling back-navigation from signup_part_two screen
				if(!firstName.equalsIgnoreCase("")){
					editTextfirstName.setText(firstName);
					editTextEmailID.setText(emailID);
					editTextReTypeEmailID.setText(reTypedEmailID);
				}

				//manually filling firstName
				editTextfirstName.setText("Parent");



				/**
				 * Enabling TextChangedListener for  Email-ID field signup
				 * */
				editTextEmailID.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
						// When user changed the Text
						DebugLog.console("inside onTextChanged:" );

						emailID = editTextEmailID.getText().toString().trim();
						//reTypedEmailID = editTextReTypeEmailID.getText().toString().trim();

//						if (reTypedEmailID.length()==0){
						//	hh_edtfield_q_2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
						//	editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
						//	hh_edtfield_q_2.setCompoundDrawablePadding(8);
						//	editTextReTypeEmailID.setCompoundDrawablePadding(8);

//						}else{

						String found = 	MpcUtil.checkIfExists(AppController.getInstance().getResources().getStringArray(R.array.array_psu_codes),emailID);

						editTextReTypeEmailID.setText(found);




//						}
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

						emailID = editTextEmailID.getText().toString().trim();
						reTypedEmailID = editTextReTypeEmailID.getText().toString().trim();
						if (reTypedEmailID.length()==0){
//							hh_edtfield_q_2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//							editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//							hh_edtfield_q_2.setCompoundDrawablePadding(8);
//							editTextReTypeEmailID.setCompoundDrawablePadding(8);
						}else {
							if (emailID.length() < reTypedEmailID.length() || emailID.length() > reTypedEmailID.length()){
//								hh_edtfield_q_2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								hh_edtfield_q_2.setCompoundDrawablePadding(8);
//								editTextReTypeEmailID.setCompoundDrawablePadding(8);
							}else if (emailID.equalsIgnoreCase(reTypedEmailID)) {
//								hh_edtfield_q_2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								editTextReTypeEmailID.setCompoundDrawablesWithIntrinsicBounds(R.drawable.email_icon, 0, 0, 0);
//								hh_edtfield_q_2.setCompoundDrawablePadding(8);
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

				editTextPhone = (EditText)findViewById(R.id.sign_up_phone_textfield);
				editTextPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.signup_phone_icon, 0, 0, 0);
				editTextPhone.setCompoundDrawablePadding(8);

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

				btnCreateAccount.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub

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
						confirmPassword = editTextConfirmPassword.getText().toString().trim();

						if (confirmPassword.length()==0){
							//	editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
							//	editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
							//	editTextPassword.setCompoundDrawablePadding(8);
							//	editTextConfirmPassword.setCompoundDrawablePadding(8);
						}else{
							if (password.length() < confirmPassword.length() || password.length() > confirmPassword.length()){
//								editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextPassword.setCompoundDrawablePadding(8);
//								editTextConfirmPassword.setCompoundDrawablePadding(8);
							}else if (password.equalsIgnoreCase(confirmPassword)) {
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
						confirmPassword = editTextConfirmPassword.getText().toString().trim();
						if (confirmPassword.length()==0){
//							editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//							editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//							editTextPassword.setCompoundDrawablePadding(8);
//							editTextConfirmPassword.setCompoundDrawablePadding(8);

						}else {
							if (password.length() < confirmPassword.length() || password.length() > confirmPassword.length()){
//								editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.key, 0, 0, 0);
//								editTextPassword.setCompoundDrawablePadding(8);
//								editTextConfirmPassword.setCompoundDrawablePadding(8);
							}else if (password.equalsIgnoreCase(confirmPassword)) {
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
//				hh_edtfield_q_2.setText(time+"@setech.com");
//				editTextReTypeEmailID.setText(time+"@setech.com");
//				editTextPassword.setText("test");
//				editTextConfirmPassword.setText("test");





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

				editTextPasswordToLogin.setOnEditorActionListener(new OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
						if(actionId== EditorInfo.IME_ACTION_DONE){
							//do something
							grabEnteredTextForSignIN();


							return true;
						}
						return false;
					}
				});



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
			FileManager.exportLogFile(appContext);
			return isConnected=true;
		}
	}



	public void grabEnteredTextForSignUp(View v) {

		boolean error = false;

		try {

//			Intent intent = MpcUtil.buildNewIntent(appContext,  HH_Screen_two.class);
//			intent.putExtra("launchActivity","signup");
//			startActivity(intent);
//			selfClose =  true;
//			finish();


			//editTextfirstName.setText("Parent");
			//userName = editTextUserName.getText().toString();
			firstName = "Parent";// editTextfirstName.getText().toString();
			//lastName = editTextLastName.getText().toString();
			emailID = editTextEmailID.getText().toString();
			reTypedEmailID = editTextReTypeEmailID.getText().toString();
			////AnalyticsUtil.updateEventAction(appContext, "Email Address : "+emailID);
			//	//AnalyticsUtil.updateEventAction(appContext, "reTypedEmail Address : "+reTypedEmailID);

			if(SELECTED_SPINNER_VALUE_INDEX==0) {
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_d_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_d_body));
//				error = true;
			}else


			if(SELECTED_USER_SPINNER_VALUE_INDEX==0) {
				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
				error = true;
			}else


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
//				error = true;

			}else if (emailID.trim().length() == 0 ){
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_2.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_2);
//				error = true;
//			}
//			else if (!MpcUtil.isEmailValid(emailID.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_2.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_2);
//				error = true;
			}else if (reTypedEmailID.trim().length()==0){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_psu_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
//			}else if (!reTypedEmailID.trim().equalsIgnoreCase(emailID.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
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

	public void grabEnteredTextForSignUpPartTwo() {





		boolean error = false;
		try {




			password = editTextPassword.getText().toString();
			confirmPassword = MpcUtil.getPassword(AppController.getInstance().getResources().getStringArray(R.array.array_name_enumerator),SELECTED_USER_SPINNER_VALUE);
			//phone = editTextPhone.getText().toString();
			//userName = editTextUserName.getText().toString();
			firstName = editTextfirstName.getText().toString();
			//lastName = editTextLastName.getText().toString();
			emailID = editTextEmailID.getText().toString();
			reTypedEmailID = editTextReTypeEmailID.getText().toString();
//			//AnalyticsUtil.updateEventAction(appContext, "Email Address : "+emailID);
//			//AnalyticsUtil.updateEventAction(appContext, "reTypedEmail Address : "+reTypedEmailID);
//			//AnalyticsUtil.updateEventAction(appContext, "Phone : "+phone);


//			if(SELECTED_SPINNER_VALUE_INDEX==0) {
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_d_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_d_body));
//				error = true;
//			}else


			if(SELECTED_USER_SPINNER_VALUE_INDEX==0) {
				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title_user),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body_user));
				error = true;
			}else



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
//				error = true;

			}

			else

			if(password.trim().length() == 0)
			{
//				//AnalyticsUtil.updateEventAction(appContext, " Password field empty");
				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
				showAlert(getResources().getString(R.string.signup_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_empty_message_body));
				editTextPassword.requestFocus();
				MpcUtil.restartInput(appContext, editTextPassword);
				error = true;
			}
			else if(password.trim().length() < MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH )
			{
				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
				showAlert(getResources().getString(R.string.signup_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_minimum_length, MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH));
				editTextPassword.requestFocus();
				MpcUtil.restartInput(appContext, editTextPassword);
				//showAlert("Invalid Password","Password Length should be greater than "+MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH+" characters");
				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " Password length less than 4 characters");
			}
			else if(password.trim().length() > MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH )
			{
//				//AnalyticsUtil.updateEventAction(appContext, " Password length greater than 50 characters");
				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
				showAlert(getResources().getString(R.string.signup_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_maximum_length, MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH));
				editTextPassword.requestFocus();
				MpcUtil.restartInput(appContext, editTextPassword);
				error = true;
			}
			else if(confirmPassword.trim().length()==0)
			{
				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
				showAlert(getResources().getString(R.string.signup_activity_confirm_password_field_empty_message_title),getResources().getString(R.string.signup_activity_confirm_password_field_empty_message_body));
				editTextPassword.requestFocus();
				MpcUtil.restartInput(appContext, editTextPassword);
				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " ReType Password field empty");
			}

			else if(!confirmPassword.trim().equalsIgnoreCase(password))
			{
				MpcUtil.hideSoftKeyBoard(appContext, editTextPassword);
				showAlert(getResources().getString(R.string.signup_activity_confirm_password_field_empty_message_title),getResources().getString(R.string.signup_activity_confirm_password_field_empty_message_body));
				editTextPassword.requestFocus();
				MpcUtil.restartInput(appContext, editTextPassword);
				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " ReType Password field empty");
			}

			else if (emailID.trim().length() == 0 ){
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_2.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_2);
//				error = true;
//			}
//			else if (!MpcUtil.isEmailValid(emailID.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, hh_edtfield_q_2);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_body));
//				hh_edtfield_q_2.requestFocus();
//				MpcUtil.restartInput(appContext, hh_edtfield_q_2);
//				error = true;
//			}else if (reTypedEmailID.trim().length()==0){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_psu_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
//			}else if (!reTypedEmailID.trim().equalsIgnoreCase(emailID.trim())){
//				MpcUtil.hideSoftKeyBoard(appContext, editTextReTypeEmailID);
//				showAlert(getResources().getString(R.string.signup_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signup_activity_retyped_email_id_error_message_body));
//				editTextReTypeEmailID.requestFocus();
//				MpcUtil.restartInput(appContext, editTextReTypeEmailID);
//				error = true;
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
			}

//			else if(!password.equals(confirmPassword))
//			{
//				MpcUtil.hideSoftKeyBoard(appContext, editTextConfirmPassword);
//
//				showAlert(getResources().getString(R.string.signup_activity_confirm_password_field_empty_message_title),getResources().getString(R.string.signup_activity_confirm_password_field_empty_message_body));
//				editTextConfirmPassword.requestFocus();
//				MpcUtil.restartInput(appContext, editTextConfirmPassword);
//				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, " Password and ReType Password mismatch");
//			}
//			else
//			{
//			}

			if(!error){

				pd = ProgressDialog.show(this, getResources().getString(R.string.signup_activity__progress_bar_title), getResources().getString(R.string.signup_activity__progress_bar_body_text), true, false);
				new Thread() {
					public void run() {
						try{
							// complete registration from server
							completeProcess("signup", MpcUtil.SIGNUP_URL);

//							if(pd!=null){
//								pd.dismiss();
//							}


//							Intent intent = MpcUtil.buildNewIntent(appContext,  HH_Screen_two.class);
//							intent.putExtra("launchActivity","signup");
//							startActivity(intent);
							//selfClose =  true;
							//	finish();




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




//			Intent intent = MpcUtil.buildNewIntent(appContext,  HH_Screen_two.class);
//			intent.putExtra("launchActivity","signup");
//			startActivity(intent);
//			selfClose =  true;
//			finish();


//			userNameToLogin = editTextUserNameToLogin.getText().toString();
//			passwordToLogin = editTextPasswordToLogin.getText().toString();
//			//AnalyticsUtil.updateEventAction(appContext, "Email Address : "+userNameToLogin);
//
//			if (userNameToLogin.trim().length() == 0 )
//			{
//				MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
//				showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_body));
//				editTextUserNameToLogin.requestFocus();
//				MpcUtil.restartInput(appContext, editTextUserNameToLogin);
//				error = true;
//				//AnalyticsUtil.updateEventAction(appContext, "Login Button: Email field empty");
//			}
//			else if (!MpcUtil.isEmailValid(userNameToLogin)) {
//				//AnalyticsUtil.updateEventAction(appContext, "Login Button: Invalid Email Address : "+userNameToLogin);
//				MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
//				showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_body));
//				editTextUserNameToLogin.requestFocus();
//				MpcUtil.restartInput(appContext, editTextUserNameToLogin);
//				error = true;
//			}
//			else  if (passwordToLogin.trim().length() == 0 )
//			{
//				//AnalyticsUtil.updateEventAction(appContext, "Login Button: Password field empty");
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPasswordToLogin);
//				showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.signin_activity_password_field_empty_message_body));
//				editTextPasswordToLogin.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPasswordToLogin);
//				error = true;
//			}
//			else  if (passwordToLogin.trim().length() < MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH )
//			{
//				//AnalyticsUtil.updateEventAction(appContext, "Login Button: Password length less than 4 characters");
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPasswordToLogin);
//				//showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_minimum_length,MpcUtil.DEFAULT_MINNIMUM_PASSWORD_LENGTH));
//				showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.invalid_password_error_message_via_server_code));
//				editTextPasswordToLogin.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPasswordToLogin);
//				error = true;
//			}
//			else  if (passwordToLogin.trim().length() > MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH )
//			{
//				//AnalyticsUtil.updateEventAction(appContext, "Login Button: Password length greater than 50 characters");
//				MpcUtil.hideSoftKeyBoard(appContext, editTextPasswordToLogin);
//				//showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.signup_activity_password_field_error_message_for_maximum_length,MpcUtil.DEFAULT_MAX_PASSWORD_LENGTH));
//				showAlert(getResources().getString(R.string.signin_activity_password_field_empty_message_title),getResources().getString(R.string.invalid_password_error_message_via_server_code));
//				editTextPasswordToLogin.requestFocus();
//				MpcUtil.restartInput(appContext, editTextPasswordToLogin);
//				error = true;
//			}
//
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
//				DebugLog.console("userName: "+userName+"\r\nfirstName"+ firstName+ "\r\nlastName"+lastName+"\r\nemailID"+ emailID+"\r\npassword"+ password);
//				json = JsonHandler.createSIGNUPJsonObject(appContext, firstName, emailID, phone,password, "careteen", "android", MpcUtil.DEVICE_UNIQUE_ID,MpcUtil.BUILD_INFO);
//				//EmailDebugLog.getInstance(appContext).writeLog(json.toString());
//				MainUserInfo.devicesTempLockPassword = password;
//				MainUserInfo.REGISTERED_EMAIL_ADDRESS = emailID;
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
//					m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
//				}
//			}
			m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
		}catch(Exception e){

			if (pd!=null)
				pd.dismiss();
			EmailDebugLog.getInstance(getApplicationContext()).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside completeProcess");
			m_Handler.sendEmptyMessage(ResponceVerifier.GENERAL_ERROR);
		}
	}



	private void validateEmailAddress(){

//		try{
//
//			DebugLog.console("userName: "+userName+"\r\nfirstName"+ firstName+ "\r\nlastName"+lastName+"\r\nemailID"+ emailID+"\r\npassword"+ password);
//			MainUserInfo.REGISTERED_EMAIL_ADDRESS = emailID;
//			String url = MpcUtil.getBaseURL(appContext)+ MpcUtil.EMAIL_ID_VERIFICATION_URL + Uri.encode(emailID);
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
				success = true;//ResponceVerifier.verifyReceievedResponce(HH_Screen_One.this,msg.what);

				if(success){


					if (pd!=null)
						pd.dismiss();


				//	HHIDConfigurations.setPeshawarCurrentPSU(appContext,emailID);
				//	HHIDConfigurations.setPeshawarCurrentPSUName(appContext,reTypedEmailID);
				//	HHIDConfigurations.setPeshawarCurrentDistrict(appContext,SELECTED_USER_SPINNER_VALUE);
					HHIDConfigurations.setPeshawarCurrentLoggedInUser(appContext,SELECTED_USER_SPINNER_VALUE);



					//HH_Screen_two.START_TIME = MpcUtil.getcurrentTime(14);
					Intent intent = MpcUtil.buildNewIntent(appContext, MainMenuActivity.class);
					intent.putExtra("launchActivity","signup");
					startActivity(intent);
					selfClose = true;
					finish();
					if(success){
						return;
					}










				}else {
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













	public  void sendForgotPasswordRequest(View v) {

//		try{
//			//AnalyticsUtil.updateEventAction(appContext,getResources().getString(R.string.event_sigin_forgot_pass));
//			if(ConnectionVerifier.isInternetOn(appContext)){
//				userNameToLogin = editTextUserNameToLogin.getText().toString();
//				if (userNameToLogin.trim().length() == 0 ){
//					MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
//					showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_forgot_password_emailaddress_field_empty_message_body));
//					editTextUserNameToLogin.requestFocus();
//					MpcUtil.restartInput(appContext, editTextUserNameToLogin);
//				}else if (!MpcUtil.isEmailValid(userNameToLogin)) {
//					MpcUtil.hideSoftKeyBoard(appContext, editTextUserNameToLogin);
//					showAlert(getResources().getString(R.string.signin_activity_emailaddress_field_empty_message_title),getResources().getString(R.string.signin_activity_emailaddress_invalid_error_message));
//					editTextUserNameToLogin.requestFocus();
//					editTextUserNameToLogin.selectAll();
//					MpcUtil.restartInput(appContext, editTextUserNameToLogin);
//					//editTextUserNameToLogin.setTextColor(getResources().getColorStateList(R.color.selected_effect));
//				}else{
//
//					showWarningAlert("Warning", "Instructions to change password will be emailed to address registered with this account. Are you sure you want to continue?");
//				}
//			}else{
//				showAlert( getResources().getString(R.string.app_name),getResources().getString(R.string.connection_error_message));
//			}
//		}catch(Exception e){
//			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[HH_Screen_One]:Exception occured inside sendForgotPasswordRequest");
//		}
	}

	private JSONObject sendForgotPasswordRequestToServer(){

		try{
			DebugLog.console("[HH_Screen_One]: inside sendForgotPasswordRequestToServer");
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
				success = ResponceVerifier.verifyReceievedResponce(HH_SECTION_E_ONE.this,msg.what);
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
		new AlertDialog.Builder(HH_SECTION_E_ONE.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
					}
				})
				.show();
	}
	public void showPermanentExpiredAlert(String title, String message){
		new AlertDialog.Builder(HH_SECTION_E_ONE.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
					}
				}).setNegativeButton("Uninstall",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
//				Intent i = new Intent(appContext,UninstallOptionsActivity.class);
//				i.putExtra("callFromActivity", "HH_Screen_One");
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NO_HISTORY);
//				appContext.startActivity(i);
				selfClose =  true;
				finish();
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

		//	Intent backIntent = MpcUtil.buildNewIntent(appContext, HH_MainActivity.class);
		//	startActivity(backIntent);
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

			Intent backIntent = MpcUtil.buildNewIntent(appContext, MainMenuActivity.class);
			startActivity(backIntent);
			finish();

		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[HH_Screen_One]: Exception occured inside onBackPressed");
			finish();
		}
	}

	public void showWarningAlert(String title, String message){
		new AlertDialog.Builder(HH_SECTION_E_ONE.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){

						pd = ProgressDialog.show(HH_SECTION_E_ONE.this, getResources().getString(R.string.signin_activity_forgot_password_progress_bar_title), getResources().getString(R.string.signup_activity_forgot_password_text), true, false);
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
			String[] myStringArray = appContext.getResources().getStringArray(R.array.array_name_enumerator);
			List<String> stooges = Arrays.asList( myStringArray );
//			String lastDistrict = HHIDConfigurations.getPeshawarCurrentDistrict(appContext);
//			if(lastDistrict.equalsIgnoreCase("0")){
//				spiiner_messge.setSelection(0);
//			}else{
//				spiiner_messge.setSelection(stooges.indexOf(lastDistrict));
//
//			}

         //  String PSU_code = HHIDConfigurations.getPeshawarCurrentPSU(appContext);
//           if (PSU_code.equalsIgnoreCase("0")){
			//   spiiner_messge.setSelection(1);
			//spiiner_messge.setEnabled(false);

//		   }

//			if(PSU_code.equalsIgnoreCase("1001")||PSU_code.equalsIgnoreCase("1002")||PSU_code.equalsIgnoreCase("1003")){
//				spiiner_messge.setSelection(1);
//			}else  if(PSU_code.equalsIgnoreCase("1004")||PSU_code.equalsIgnoreCase("1005")) {
//				spiiner_messge.setSelection(1);
//			}else{
//				spiiner_messge.setSelection(2);
//			}



            String user = HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext);
            if(user.equalsIgnoreCase("0")){

			}else {
//				spiiner_user.setSelection(stooges.indexOf(user));
				Intent intent = new Intent(HH_SECTION_E_ONE.this, MainMenuActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
			}







			} catch (Exception e) {
		    EmailDebugLog.getInstance(appContext).writeLog("[HH_Screen_One] inside onStart() Exception is :"+e.toString());
		}




	}

	public void onTermsPolicyClick(View arg0) {

		try {
//			selfClose = true;
//			//AnalyticsUtil.updateEventAction(appContext,"Terms of Use & Privacy Policy");
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
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain);
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

	public void onClose(View v) {

		try{

			//			if (Configurations.isExitAppWithConfirmationEnabled(getApplicationContext())){
			showWarningAlert(getResources().getString(R.string.main_activity_close_dialog_title_text),getResources().getString(R.string.main_activity_close_dialog_body_text));
			//}else{
			//finish();
			//}

		}catch (Exception ex) {
		}
	}

	public void showWarningAlert1(String title, String message){
		new AlertDialog.Builder(HH_SECTION_E_ONE.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){


						finish();
					}
				}).setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which){
			}
		}).show();
	}


	public void launchReport(View v) {

		try {


			Intent intent = new Intent(HH_SECTION_E_ONE.this, AddReportActivity.class);

			intent.putExtra("emp_id", emp_id);
			intent.putExtra("order_id", order_id);
			intent.putExtra("id", id);
			intent.putExtra("farmer_cellphone", phone_number);
			intent.putExtra("school_code", school_code);
			intent.putExtra("student_id", student_id);
			startActivityForResult(intent, 88);


		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog("[HH_SECTION_E_ONE] inside launchReport() Exception is :" + e.toString());
		}

	}




}
