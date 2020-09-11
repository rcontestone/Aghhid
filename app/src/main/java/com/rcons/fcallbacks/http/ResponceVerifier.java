package com.rcons.fcallbacks.http;

import android.content.Context;


public class ResponceVerifier {

	//server responses
	public static final int STATUS_SUCCESS = 100;
	public static final int GENERAL_ERROR = 200;
	public static final int STATUS_INVALID_KEY = 201;
	public static final int STATUS_LICENSE_NOT_ACTIVATED = 202;
	public static final int STATUS_USER_ALREADY_EXSIST = 250;
	public static final int STATUS_INVALID_PASSWD = 301;
	public static final int STATUS_INVALID_USERNAME = 302;
	public static final int STATUS_REQUIRED_PARAMETERS_MISSING = 350;
	public static final int STATUS_REQUIRED_PARAMETERS_NULL = 351;
	public static final int STATUS_PARAMETERS_COULD_NOT_BE_PARSED= 352;
	public static final int DEVICE_ALREADY_REGISTERED = 400;
	public static final int LICENSE_LIMIT_EXCEEDED = 401;
	public static final int LICENSE_EXPIRED = 402;
	public static final int STATUS_SUB_USER_ALREADY_EXSIST = 403;
	public static final int STATUS_DEMO_LICENSE_ALREADY_CONSUMED = 404;   // no action to show 
	public static final int STATUS_INVALID_MAPPING_ID = 405; // no action to show 
	public static final int STATUS_SUB_USER_DOES_NOT_EXSIST = 410;
	public static final int STATUS_NO_MAPPING_EXSIST_AGAINST_DEVICE = 415;   //this message will not be shown in case of map existing device, device should be mapped seemlessly on server side 
	public static final int STATUS_NO_TRIAL_PRODUCT_AGAINST_THIS_PLATEFORM = 417;

	public static final int ALL_LICENSE_EXCEEDED = 425;
	public static final int ALL_LICENSE_CANCELLED = 426;
	public static final int NO_ACTIVE_LICENSE_MIX_OF_EXPIRE_AND_CANCEL = 427;
	public static final int ALL_LICENSE_LIMIT_EXCEEDED = 428;

	public static final int STATUS_INVALID_EMAIL_ADDRESS = 212;
	public static final int STATUS_UN_REGISTERED_EMAIL_ADDRESS= 211;
	public static final int STATUS_SOCKET_EXCEPION_ERROR  = 500;

	public static String messageToShow = "";
	public static String TitleToShow = "";
	public static boolean verifyReceievedResponce(Context appContext, int serverResponce){

		boolean success= false;
		try{
//			switch (serverResponce) {
//
//			case STATUS_SUCCESS:
//				success = true;
//				if(appContext.getClass().getName().toString().contains("test-SubUserSelectingActivity")){
//					//showAlert(appContext,"ParentalControl",appContext.getResources().getString(R.string.signup_success_message));
//				}
//				break;
//
//			case STATUS_USER_ALREADY_EXSIST:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.signup_user_already_exsist_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.signup_user_already_exsist_error_message_title);
//
//				break;
//
//			case STATUS_REQUIRED_PARAMETERS_NULL:
//
//				messageToShow = appContext.getResources().getString(R.string.required_parameters_null_error_message);
//				success = false;
//				break;
//
//			case STATUS_PARAMETERS_COULD_NOT_BE_PARSED:
//
//				messageToShow = appContext.getResources().getString(R.string.parameters_could_not_be_parsed_error_message);
//				success = false;
//				break;
//
//			case STATUS_REQUIRED_PARAMETERS_MISSING:
//
//				messageToShow = appContext.getResources().getString(R.string.required_parameters_missing_error_message);
//				success = false;
//				break;	
//
//			case STATUS_INVALID_PASSWD:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.invalid_password_error_message_via_server_code);
//				TitleToShow = appContext.getResources().getString(R.string.invalid_password_error_message_title_via_server_code);
//				break;
//
//			case STATUS_INVALID_USERNAME:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.invalid_user_name_error_message_via_errorcode);
//				TitleToShow = appContext.getResources().getString(R.string.invalid_user_name_error_message_title_via_errorcode);
//				break;	
//
//			case GENERAL_ERROR:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.general_error_message);
//				break;
//
//			case DEVICE_ALREADY_REGISTERED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.device_already_registered_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.device_already_registered_error_message_title);
//				
//				break;
//
//			case LICENSE_LIMIT_EXCEEDED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.license_limit_exceeds_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.license_limit_exceeds_error_message_title);
//				break;
//
//			case LICENSE_EXPIRED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.license_expired_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.license_expired_error_message_title);
//				break;
//
//			case STATUS_SUB_USER_ALREADY_EXSIST:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.sub_user_already_exsist_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.sub_user_already_exsist_error_message_title);
//				break;
//
//			case STATUS_DEMO_LICENSE_ALREADY_CONSUMED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.demo_license_already_consumed_error_message);
//				break;
//
//			case STATUS_LICENSE_NOT_ACTIVATED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.license_not_activated_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.license_not_activated_error_message_title);
//				break;
//
//			case STATUS_INVALID_KEY:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.invalid_key_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.invalid_key_error_message_title);
//				break;
//
//			case STATUS_INVALID_EMAIL_ADDRESS:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.signin_activity_forgot_password_invalid_email_id);
//				TitleToShow = appContext.getResources().getString(R.string.signin_activity_forgot_password_invalid_email_id_title);
//				break;
//
//			case STATUS_UN_REGISTERED_EMAIL_ADDRESS:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.signin_activity_forgot_password_un_registered_email_id);
//				TitleToShow = appContext.getResources().getString(R.string.signin_activity_forgot_password_un_registered_email_id_title);
//				break;
//
//			case STATUS_SUB_USER_DOES_NOT_EXSIST:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.sub_user_does_not_exsist_error_message);
//				break;
//
//			case STATUS_NO_MAPPING_EXSIST_AGAINST_DEVICE:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.no_mapping_exsist_against_device_error_message);
//				break;
//
//			case STATUS_NO_TRIAL_PRODUCT_AGAINST_THIS_PLATEFORM:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.no_trial_product_exsist_against_plateform_error_message);
//				break;
//
//			case ALL_LICENSE_EXCEEDED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.all_license_expired_error_message,HHIDConfigurations.getDashBoardUrl(appContext));
//				TitleToShow = appContext.getResources().getString(R.string.all_license_expired_error_message_title);
//				break;
//
//			case ALL_LICENSE_CANCELLED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.license_cancelled_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.license_cancelled_error_message_title);
//				break;
//
//			case NO_ACTIVE_LICENSE_MIX_OF_EXPIRE_AND_CANCEL:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.license_cancelled_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.license_cancelled_error_message_title);
//				break;
//
//			case ALL_LICENSE_LIMIT_EXCEEDED:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.no_trial_product_exsist_against_plateform_error_message);
//				break;
//
//			case STATUS_INVALID_MAPPING_ID:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.invalid_mapping_id_error_message);
//				break;	
//
//			case STATUS_SOCKET_EXCEPION_ERROR:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.socket_exception_error_message);
//				break;
//
//			default:
//
//				success = false;
//				messageToShow = appContext.getResources().getString(R.string.general_error_message);
//				TitleToShow = appContext.getResources().getString(R.string.license_cancelled_error_message_title);
//			}
			return success;
		} catch (Exception e) {
//			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[ResponceVerifier]: Exception occured inside verifyResponce");
			return success;
		}
	}

//	public static void showAlert(Context appContext, String title, String message){
//		new AlertDialog.Builder(appContext)
//		.setTitle(title)
//		.setMessage(message)
//		.setPositiveButton(appContext.getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
//			public void onClick(DialogInterface dialog, int which){
//
//			}
//		})
//		.show();
//	}

}
