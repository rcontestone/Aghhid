package com.rcons.fcallbacks;


import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

import com.mubashar.dateandtime.DebugLog;
import com.rcons.fcallbacks.Utilties.MpcUtil;
import com.rcons.fcallbacks.Utilties.RConsUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class HHIDConfigurations {

	// Enabled Feature List Constant for saving configuration in preferences
	public final static String FEATURE_WEBHISTORY_LOG = "WEBHISTORY_LOG";
	public final static String FEATURE_LOCATION_LOG = "LOCATION_LOG";
	public final static String FEATURE_SMS_LOG = "SMS_LOG";
	public final static String FEATURE_CALL_LOG = "CDR_LOG";
	public final static String FEATURE_MMS_LOG = "MMS_LOG";

	public final static String FEATURE_INTERNET_FILTER = "INTERNET_FILTER";
	public final static String FEATURE_SAFE_SEARCH = "SAFE_SEARCH";
	public final static String FEATURE_CATEGORY_SETTINGS = "CATEGORY_FILTERING";
	public final static String FEATURE_URL_SETTINGS = "URL_FILTERING";
	public final static String FEATURE_KEYWORD_SETTINGS = "KEYWORD_FILTERING";
	public final static String FEATURE_TIME_MANAGEMENT = "TIME_MANAGEMENT";
	public final static String FEATURE_EMERGENCY_NUMBERS = "EMERGENCY_NUMBERS";
	public final static String FEATURE_APP_BLOCK = "APP_BLOCKING";
	public final static String FEATURE_APP_LOG = "APP_LOG";
	public final static String FEATURE_APP_USAGE_LOG = "APP_USAGE";
//	public final static String	DEBUG_FILE_UPLOADING_STATUS = "debug_file_uploading_status";


	// Constant for saving configuration in preferences
	public final static String APP_CONFIGURATION = "configurations";
	public final static String PERMISSION_SHOWN_STATUS = "isPermissionScreenShown";
	public final static String FEATURES_CONFIGURATION = "featuresconfigurations";
	public final static String BUILD_NO = "build_no";
	public final static String APP_FIRST_LAUNCH = "app_first_launch";
	public final static String APP_ADMIN_RIGHTS = "app_admin_rights";
	public final static String APP_CONFIGURATION_CREATED = "app_configuration_created";
	//public final static String	APP_IMEI = "app_imei";
	public final static String APP_MAPPIND_ID = "app_mapping_id";
	public final static String HOME_PAGE_URL = "home_page_url";
	public final static String ATION_INTERVAL = "ation_interval";
	public final static String DASHBOARD_URL = "dashboard_url";
	public final static String SECOND_SERVICE_TIME = "second_service_time";
	public final static String APP_FTP_SERVER_IP = "ftp_server_ip";
	public final static String APP_AL_PHONE_NO  = "al_phone_number";
	public final static String APP_LICENSE_VERSION = "license_version";
	public final static String PUSH_NOTIFICATION_key = "push_notification_key_text";

	public final static String APP_PHONE_TYPE = "phone_typ";
	public final static String APP_PHONE_MODEL = "phone_model";
	public final static String APP_LICENSE_STATUS = "license_status";
	public final static String APP_LICENSE_Type_BASIC = "license_tpe_basic";
	public final static String APP_LOGUPLOAD_DATE = "logupload_date";
	public final static String GOOGLE_SAFE_SEARCH = "google_safe_search";
	public final static String DATA_UPLOADING_STATUS = "uploading_status";
	public final static String LAST_MAPPED_DEVICE_NAME = "last_mapped_device_name";
	public final static String DEVICE_LOCKING_PASSWORD = "device_lock_password";
	public final static String REGISTERED_EMAIL_ID_TEMP = "registered_code_id";
	public final static String REGISTERED_EMAIL_ID = "registered_email_id_temp";
	public final static String APP_BLOCKING_STATUS = "app_blocking_status";
	public final static String RE_ENABLE_TIME = "re_enable_time";
	public final static String PUSH_NOTIFICATION_STATUS = "push_notification_key";
	public final static String CALL_BACKUP_STATUS = "call_backupdone_key";
	public final static String SMS_BACKUP_STATUS = "sms_backupdone_key";
	public final static String MMS_BACKUP_STATUS = "mms_backupdone_key";
	public final static String APPLIST_UPLOADING_STATUS = "app_uploading_status";
	public final static String PARENTAL_PROCESSES_DOWNLOADING_STATUS = "parental_processes_downloading_status";
	public final static String DEVIC_GPS_STATUS = "gpsStatus";
	public final static int MAX_DEBUG_FILE_SIZE_TO_UPLOAD = 3 * 1024 * 1024 ;

	private final static String SET_SIGNINUP_SHOWNONETIME = "signInUpShownOneTime";
	private final static String LAST_APPUSAGE_REPORTING_STATUS = "lastAppUsageReportingState";
	private final static String LAST_ADMINRIGHTS_REPORTING_STATUS = "lastAdminRightsReportingState";
	private final static String LAST_ACCESSIBILITY_REPORTING_STATUS = "lastAccessibilityRightsReportingState";
	private final static String LAST_LOCATION_REPORTING_STATUS = "lastLocationReportingState";
	private final static String APP_HISTORY_REPORTING_STATUS = "appHistoryReportingState";
	private final static String APP_HISTORY_REPORTING_DAYS = "appHistoryReportingDays";
	private final static String Urls_To_Report = "urlsToReport";
	private final static String DEBUG_FILE_UPLOADING_STATUS = "debugfileuploadingstatus";

	public final static String Build_VERSION_SENT = "build_version_sent_to_analytics";
	public final static String BUTTON_PRESSED_STATUS = "BTN_PRESSED_STATUS";
	//quota_configurations
	public final static String DAILY_QUOTA_APP_CONFIGURATION = "daily_quota_configuration";
	public final  static String daily_quota_map_key = "map_daily_quota";
	public final String daily_quota_current_day_key = "daily_quota_current_day";
	public final String daily_quota_last_day_key = "daily_quota_last_day";






	public final static String LOCK_ALL_OPTION = "lock_all_option";
	public final static String VPN_ENABLE_STATE = "enable_vpn";
	public final static String ADMIN_RIGHT_STATUS = "admin_righ_status";
	public final static String ADMIN_RIGHT_STATUS_DISABLED_BY_APP = "admin_righ_status_disabled_by_app";

	public final static String PREVIOUS_DATA_DAYS = "previousDataDays";
	//Emergency preference 
	public final static String EMERGENCY_CONFIGURATION = "emergencyNumbers";
	public final static String EMERGENCY_NUMBERS_KEY = "emergencynumbers";


	//Appusage
	public final static String AppUsagePrefFileName = "app_usage_pref";
	public final static String APP_USAGE_END_TIME_PREF = "end_time-pref";
	public final static String REGISTERATION_TIME_PREF = "registeration_time-pref";



	public final static String lAST_STRCT_ID_AGAINST_PSU = "last_strcut_id_against_psu";

	public HHIDConfigurations() {

	}

	/**
	 * Get the phone number of mobile
	 *
	 * @return phone number of mobile
	 */
	public static String getPhoneNumber(Context appContext) {

		String phoneNumber = null;
		try {
			TelephonyManager tm = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);
			phoneNumber = tm.getLine1Number();
			return phoneNumber;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occured inside getPhoneNumber");
			return null;
		}
	}

	public static String getServerIP(Context appContext) {
		String str = getConfiguration(appContext, APP_FTP_SERVER_IP);
		return str;
	}
	public static String getLogUploadDate(Context appContext) {
		String str = getConfiguration(appContext, APP_LOGUPLOAD_DATE);
		if(str == null){
			str = "0";
		}
		return str;
	}

	public static boolean setServerIP(Context ctx, String ip) {
		boolean result = setConfiguration(ctx, APP_FTP_SERVER_IP, ip);
		return result;
	}
	public void setFtpServerIP(Context ctx, String ip) {
		setConfiguration(ctx, APP_FTP_SERVER_IP, ip);
	}

	private void setLicenseVersion(Context appContext, String version) {
		setConfiguration(appContext, APP_LICENSE_VERSION, version);
	}
	public static boolean setAppFunctionalityStatus(Context appContext, String status) {
		return setConfiguration(appContext, DATA_UPLOADING_STATUS, status);
	}
	public void saveLicenseVersion(Context appContext, String version) {
		setLicenseVersion(appContext, version);
	}

	public static boolean setDashBoardUrl(Context appContext, String dashBoardUrl) {
		boolean status =  false;
		status = setConfiguration(appContext, DASHBOARD_URL, dashBoardUrl);
		return status;
	}

	public static String getDashBoardUrl(Context appContext) {
		String url = getConfiguration(appContext, DASHBOARD_URL);
		if(url == null){
			return MpcUtil.DEFAULT_DASHBOARD_URL;
		}else{
			return url;
		}
	}

	public static boolean setLocationInterval(Context appContext, String interval) {
		boolean status =  false;
		status = setConfiguration(appContext, ATION_INTERVAL, interval);
		return status;
	}

	public static String getLocationInterval(Context appContext) {
		String interval = getConfiguration(appContext, ATION_INTERVAL);
		if(interval == null){
			return MpcUtil.DEFAULT_LOCATION_INTERVAL+"";
		}else{
			return interval;
		}
	}

	public static void setSecondServiceTime(Context appContext, String time) {
		setConfiguration(appContext, SECOND_SERVICE_TIME, time);
	}

	public static String getSecondServiceTime(Context appContext) {
		String time = getConfiguration(appContext, SECOND_SERVICE_TIME);
		if(time == null){
			return "0";
		}else{
			return time;
		}
	}


	public static String getLicenseStatus(Context appContext) {
		String status = getConfiguration(appContext, APP_LICENSE_STATUS);
		if(status == null){
			status = "ACTIVE";
		}
		return status;
	}
	public static String getAPPFunctionalityStatus(Context appContext) {
		String status = getConfiguration(appContext, DATA_UPLOADING_STATUS);
		if(status == null){
			status = "START_FUNCTIONING";
		}
		return status;
	}


	public static String getalPhoneNumber(Context appContext) {
		return getConfiguration(appContext, APP_AL_PHONE_NO);
	}

	public static String getLicenseVersion(Context appContext) {
		return getConfiguration(appContext, APP_LICENSE_VERSION);
	}

	public static boolean getInfoAndUploadSysFile(Context appContext) {

		boolean sysFileUploaded = false;

		try {
			String phoneType = readNetworkType(appContext);
			setConfiguration(appContext, APP_PHONE_TYPE, phoneType);

			String alPhoneNumber = getPhoneNumber(appContext);
			if(alPhoneNumber == null){
				alPhoneNumber = "TargetPhone";
			}
			setConfiguration(appContext, APP_AL_PHONE_NO, alPhoneNumber);
//			String phoneModel = getPhoneModelForPref(appContext);
//			setConfiguration(appContext, APP_PHONE_MODEL, phoneModel);
//			DebugLog.console("[HHIDConfigurations] : Going to create  sys-file info  ");
//			DeviceInfoProcessor sysProcessor = new DeviceInfoProcessor();
//			sysFileUploaded = sysProcessor.sendDeviceInfo(appContext);

			return sysFileUploaded;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+ "[HHIDConfigurations]Exception occuered in getInfoAndUploadSysFile");
			return sysFileUploaded;
		}
	}

	public static String readNetworkType(Context appContext) {

		try {
			TelephonyManager tm = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);
			int type = tm.getPhoneType();
			String typeStr = null;
			if (type == TelephonyManager.PHONE_TYPE_GSM) {
				typeStr = "GSM";
			} else {
				typeStr = "CDMA";
			}

			return typeStr;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occured inside readNetworkType");
			return "CDMA";
		}
	}

	public String getPhoneType(Context appContext){
		return getConfiguration(appContext, APP_PHONE_TYPE);
	}

	public static String getPhoneModel(Context appContext) {
		return getConfiguration(appContext, APP_PHONE_MODEL);
	}

	public static String getPhoneModelForPref(Context appContext){
		// within my emulator it returns: 310995000000000
		String model = null;
		try {
			model = android.os.Build.MODEL;
			if (model != null) {
				return model;

			} else return "error";
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occured inside getPhoneModelForPref");
			return "error";
		}
	}





	private static boolean updateMessageList(Context appContext, JSONObject job) {

		String line = null;
		boolean status = false;

		try {

			if(!job.isNull("code")){
				if(job.getString("code").equalsIgnoreCase("100")){
					if(!job.isNull("serverName")){
						line =  job.getString("serverName");
						DebugLog.console("[HHIDConfigurations]:Server name returned from server is : "+line);
						if(!line.equalsIgnoreCase("")){

//							if(line.startsWith("http://")){
//								line = line.substring(line.indexOf("//")+2);
//							}else if(line.startsWith("https://")){
//								line = line.substring(line.indexOf("//")+2);
//							}							
							status  = setServerIP(appContext,line);
						}else{
							DebugLog.console("[HHIDConfigurations]:Server name returned from server is empty String ");
							status = false;
						}
					}else{
						DebugLog.console("[HHIDConfigurations]:Server name Not exsist in JSONOBJECT : ");
						status = false;
					}

				}
			}


			return status;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"[HHIDConfigurations]: exception occured inside updateMessageList");

			return status;
		}
	}

	public static boolean setConfiguration(Context appContext, String key, String value) {

		try {
			SharedPreferences.Editor editor = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.putString(key, value);
			editor.commit();
			return true;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occuered inside setConfiguration");
			return false;
		}
	}



	public static String getConfiguration(Context appContext, String key) {
		String conf = "";
		DebugLog.console("KEY: " + key);
		try {
			SharedPreferences pref = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			conf = pref.getString(key, null);

		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[HHIDConfigurations]: Exception occuered inside getConfiguration");
			conf = "";
		}
		return conf;
	}

	public static String getGoogeSafeSearchStatus(Context ctx, String key) {
		String conf = "";
		DebugLog.console("KEY: " + key);
		try {
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			conf = pref.getString(key, "ON");

		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog(ex.toString()+"\r\n[HHIDConfigurations]: Exception occured inside getGoogeSafeSearchStatus");
			conf = null;
		}
		return conf;
	}

	public static boolean setGoogeSafeSearchStatus(Context ctx, String key, String value) {
		boolean conf = false;
		DebugLog.console("KEY: " + key);
		try {
			SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.putString(key, value);
			editor.commit();
			DebugLog.console("KEY: Stored successfully " + key);
			return conf=true;
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog(ex.toString()+"\r\n[HHIDConfigurations]: Exception occured inside setGoogeSafeSearchStatus");
			return conf;
		}
	}



	public static String getBuildInfo(Context ctx, String key) {
		String conf = "";
		DebugLog.console("KEY: " + key);
		try {

			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			conf = pref.getString(key, "");
			return conf;
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog(ex.toString()+"\r\n[HHIDConfigurations]: Exception occured inside getStoredBuildInfo");
			return conf = "";
		}

	}

	public static boolean setBuildInfo(Context ctx, String key, String value) {
		boolean conf = false;
		DebugLog.console("KEY: " + key);
		try {
			SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.putString(key, value);
			editor.commit();
			DebugLog.console("KEY: Stored successfully " + key);
			return conf=true;
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog(ex.toString()+"\r\n[HHIDConfigurations]: Exception occured inside setStoredBuildInfo");
			return conf;
		}
	}




	public static boolean isFirstLaunch(Context appContext) {
		Boolean status = true;

		try{
			SharedPreferences pref = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(APP_FIRST_LAUNCH, true);
			DebugLog.console("isFirstLaunch="+status);
			return status;
		}catch(Exception e){
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: exception occured while checking isFirstLaunch ");
			return status;
		}

	}

	public static boolean areConfPrefInitialized(Context ctx) {
		Boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(APP_CONFIGURATION_CREATED, false);
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog( ex.toString()+"[HHIDConfigurations]: Exception occured while checking areConfPrefInitialized");
		}
		return status;
	}

	public static void setFirstLaunchCompleted(Context ctx, boolean value) {
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(APP_FIRST_LAUNCH, value);
			pref.commit();
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog( ex.toString()+"[HHIDConfigurations]: Exception occured while checking setFirstLaunchCompleted");
		}
	}


	public static void setAdminRightsRequestStatus(Context appContext, boolean status) {

		try{
			DebugLog.console("[SplashScreenActivity]: Going to store setAdminRightsRequestStatus"+status);
			SharedPreferences.Editor pref = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(APP_ADMIN_RIGHTS, status);
			pref.commit();
			DebugLog.console("[SplashScreenActivity]: AdminRightsRequestStatus stored successfully");
		}catch(Exception ex){

			EmailDebugLog.getInstance(appContext).writeLog("[HHIDConfigurations]:exception occured in setAdminRightsRequestStatus");
		}
	}

	public static boolean isAdminRightsRequestInitiated(Context appContext) {
		boolean status = false;
		try{
			DebugLog.console("[SplashScreenActivity]: inside isAdminRightsRequestInitiated");
			SharedPreferences pref = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(APP_ADMIN_RIGHTS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(appContext).writeLog("[HHIDConfigurations]:exception occured in setAdminRightsRequestStatus");
			return status;
		}
	}


	public static void setLogUploadDate(Context ctx, String date) {
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(APP_LOGUPLOAD_DATE, date);
			pref.commit();
		}catch(Exception ex){
			DebugLog.console("[HHIDConfigurations]:exception occured in setLogUploadDate");
		}
	}

	public static boolean setLicenseStatus(Context ctx, String status) {
		boolean  stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(APP_LICENSE_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			DebugLog.console("[HHIDConfigurations]:exception occured in setLicenseStatus");
			return stored;
		}
	}
	public static boolean setLicenseTypeBasic(Context ctx, String status) {
		boolean  stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(APP_LICENSE_Type_BASIC, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			DebugLog.console("[HHIDConfigurations]:exception occured in setLicenseTypeBasic");
			return stored;
		}
	}


	public static String getLicenseTypeBsic(Context ctx) {
		String storedMappedId = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			storedMappedId = pref.getString(APP_LICENSE_Type_BASIC, "No");
			return storedMappedId;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getLicenseTypeBsic");
			return storedMappedId = null;
		}
	}









	public static boolean setMappingId(Context ctx, String id) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(APP_MAPPIND_ID, id);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in setMappingId");
			return stored;
		}
	}

	public static String getMappingId(Context ctx) {
		String storedMappedId = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			storedMappedId = pref.getString(APP_MAPPIND_ID, null);
			return storedMappedId;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getMappingId");
			return storedMappedId = null;
		}
	}

	public static boolean setSignInUpShownOneTime(Context ctx, boolean signInUpShownOneTime) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(SET_SIGNINUP_SHOWNONETIME, signInUpShownOneTime);
			pref.commit();
			EmailDebugLog.getInstance(ctx).writeLog("setSignInUpShownOneTime true");
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occuered in setSignInUpShownOneTime");
			return stored;
		}
	}

	public static boolean getSignInUpShownOneTime(Context ctx) {
		boolean signInUpShownOneTime = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			signInUpShownOneTime = pref.getBoolean(SET_SIGNINUP_SHOWNONETIME, false);
			EmailDebugLog.getInstance(ctx).writeLog("getSignInUpShownOneTime "+signInUpShownOneTime);
			return signInUpShownOneTime;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getSignInUpShownOneTime");
			return signInUpShownOneTime;
		}
	}


	public static boolean setHomePageUrl(Context ctx, String url) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(HOME_PAGE_URL, url);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in setHomePageUrl");
			return stored;
		}
	}

	public static String getHomePageUrl(Context ctx) {
		String homePageUrl = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			homePageUrl = pref.getString(HOME_PAGE_URL, MpcUtil.HOME_URL);
			return homePageUrl;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getHomePageUrl");
			return MpcUtil.HOME_URL;
		}
	}



	public static boolean setDeviceLockPassword(Context ctx, String password) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(DEVICE_LOCKING_PASSWORD, password);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setDeviceLockPassword");
			return stored;
		}
	}

	public static String getDeviceLockPassword(Context ctx) {
		String lockingPassword = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			lockingPassword = pref.getString(DEVICE_LOCKING_PASSWORD, "");
			return lockingPassword;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in DEVICE_LOCKING_PASSWORD");
			return lockingPassword;
		}
	}



	public static boolean setRegisteredEmailAddressTemp(Context ctx, String emailID) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(REGISTERED_EMAIL_ID_TEMP, emailID);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setRegisteredEmailAddress");
			return stored;
		}
	}
	public static String getRegisteredEmailAddressTemp(Context ctx) {
		String registeredEmailAddress = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			registeredEmailAddress = pref.getString(REGISTERED_EMAIL_ID_TEMP, "");
			return registeredEmailAddress;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getRegisteredEmailAddress");
			return registeredEmailAddress;
		}
	}







	public static boolean setRegisteredEmailAddress(Context ctx, String emailID) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(REGISTERED_EMAIL_ID, emailID);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setRegisteredEmailAddress");
			return stored;
		}
	}
	public static String getRegisteredEmailAddress(Context ctx) {
		String registeredEmailAddress = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			registeredEmailAddress = pref.getString(REGISTERED_EMAIL_ID, "");
			return registeredEmailAddress;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getRegisteredEmailAddress");
			return registeredEmailAddress;
		}
	}

	public static boolean setAppBlockingStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(APP_BLOCKING_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAppBlockingStatus");
			return stored;
		}
	}

	public static Boolean getAppBlockingStatus(Context ctx) {
		boolean status = true;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(APP_BLOCKING_STATUS, true);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getAppBlockingStatus");
			return status;
		}
	}


	public static boolean setReenableSchedule(Context ctx, String time) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(RE_ENABLE_TIME, time);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setReenableSchedule");
			return stored;
		}
	}

	public static String getReenableSchedule(Context ctx) {
		String time = "-1";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			time = pref.getString(RE_ENABLE_TIME, "-1");
			return time;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getReenableSchedule");
			return time;
		}
	}







	public static boolean setLockAllOption(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(LOCK_ALL_OPTION, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setLockAllOption");
			return stored;
		}
	}

	public static boolean getLockAllOption(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(LOCK_ALL_OPTION, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getLockAllOption");
			return status;
		}
	}



	public static boolean setAdminRightStatusDisabledByApp(Context ctx, String status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(ADMIN_RIGHT_STATUS_DISABLED_BY_APP, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAdminRightStatusDisabledByApp");
			return stored;
		}
	}

	public static String getAdminRightStatusDisabledByApp(Context ctx) {
		String status = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getString(ADMIN_RIGHT_STATUS_DISABLED_BY_APP, "");
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getAdminRightStatusDisabledByApp");
			return status;
		}
	}


	public static boolean setAdminRightStatus(Context ctx, String status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(ADMIN_RIGHT_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAdminRightStatus");
			return stored;
		}
	}

	public static String getAdminRightStatus(Context ctx) {
		String status = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getString(ADMIN_RIGHT_STATUS, "");
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getAdminRightStatus");
			return status;
		}
	}







	public static boolean setPushNotificationRegisteredStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(PUSH_NOTIFICATION_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setPushNotificationRegisteredStatus");
			return stored;
		}
	}

	public static boolean getPushNotificationRegisteredStatus(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(PUSH_NOTIFICATION_STATUS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getPushNotificationRegisteredStatus");
			return status;
		}
	}



	public static boolean setCALLBackupStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(CALL_BACKUP_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setCALLBackupStatus");
			return stored;
		}
	}

	public static boolean getCALLBackupStatus(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(CALL_BACKUP_STATUS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getCALLBackupStatus");
			return status;
		}
	}





	public static boolean setSMSBackupStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(SMS_BACKUP_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setSMSBackupStatus");
			return stored;
		}
	}

	public static boolean getSMSBackupStatus(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(SMS_BACKUP_STATUS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getSMSBackupStatus");
			return status;
		}
	}



	public static boolean setMMSBackupStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(MMS_BACKUP_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setMMSBackupStatus");
			return stored;
		}
	}

	public static boolean getMMSBackupStatus(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(MMS_BACKUP_STATUS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getMMSBackupStatus");
			return status;
		}
	}










	public static void initAllPreferencesSettings(Context appContext){
		if (isFirstLaunch(appContext) && !HHIDConfigurations.areConfPrefInitialized(appContext)) {
			initializConfPref(appContext);
			//			initializCallRecordingPref(appContext);
			//			initializSurroundRecording(appContext);
			//			initializSMSLogging(appContext);
		}
	}

	private static void initializConfPref(Context ctx) {
		try {
			SharedPreferences.Editor editor = ctx.getSharedPreferences(HHIDConfigurations.APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.putBoolean(HHIDConfigurations.APP_CONFIGURATION_CREATED, true);
			//editor.putString(HHIDConfigurations.APP_IMEI, "");
			editor.putString(HHIDConfigurations.APP_MAPPIND_ID, "");
			editor.putString(HHIDConfigurations.ATION_INTERVAL, "");
			editor.putString(HHIDConfigurations.SECOND_SERVICE_TIME, "");
			//editor.putString(HHIDConfigurations.APP_FTP_SERVER_IP, MpcUtil.DEFAULT_DOMAIN);
			//editor.putString(HHIDConfigurations.APP_DIR_NAME, "");
			editor.putString(HHIDConfigurations.APP_AL_PHONE_NO, "");
			editor.putString(HHIDConfigurations.APP_LICENSE_VERSION, "");
			editor.putString(HHIDConfigurations.APP_PHONE_TYPE, "");
			editor.putBoolean(HHIDConfigurations.APP_FIRST_LAUNCH, true);
			//editor.putString(HHIDConfigurations.APP_PHONE_MODEL, getPhoneModelForPref(ctx));
			editor.putString(HHIDConfigurations.APP_LICENSE_STATUS, "ACTIVE");

			//google safe search

			editor.putString(HHIDConfigurations.GOOGLE_SAFE_SEARCH, "ON");
			Date date = new Date();
			String dayOfMonth = date.getDate()+ "";
			editor.putString(HHIDConfigurations.APP_LOGUPLOAD_DATE, dayOfMonth);
			DebugLog.console("[HHIDConfigurations]:Day of Month is " + dayOfMonth);
			editor.commit();
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in initializConfPref");	}
	}

	public static void initializFeatureList(Context ctx, String[] featureListCodes) {
		try {


			SharedPreferences.Editor editor = ctx.getSharedPreferences(HHIDConfigurations.FEATURES_CONFIGURATION, Context.MODE_PRIVATE).edit();


			for (int i=0; i<featureListCodes.length; i++) {
				editor.putBoolean(featureListCodes[i], true);
			}
			editor.commit();

			DebugLog.console("[HHIDConfigurations]:featureListCodes saved successfully ");

		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in initializFeatureList");
		}
	}

	public static void clearFeatureList(Context ctx) {
		try {
			SharedPreferences.Editor editor = ctx.getSharedPreferences(HHIDConfigurations.FEATURES_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.clear();
			editor.commit();
			DebugLog.console("[HHIDConfigurations]:featureListCodes cleared successfully ");
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in clearFeatureList");
		}
	}

	public static boolean isFeatureEnabled(Context appContext, String key) {
		boolean enabled = false;

		try {
			SharedPreferences pref = appContext.getSharedPreferences(FEATURES_CONFIGURATION, Context.MODE_PRIVATE);
			enabled = pref.getBoolean(key, false);
			DebugLog.console("[HHIDConfigurations]: feature enabled status against key :"+key+ "is "+enabled);

			if(HHIDConfigurations.getLicenseTypeBsic(appContext).equalsIgnoreCase("yes")){
				enabled=true;
			}



			return enabled;
//			return true;
		} catch (Exception ex) {
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(appContext).writeLog(ex.toString()+"[HHIDConfigurations]:exception occured in isFeatureEnabled");
			return enabled;
		}
	}


	public static boolean setEmergencyNumbers(Context appContext, JSONArray value) {

		try {
			SharedPreferences sharedPreferences = appContext.getSharedPreferences(EMERGENCY_CONFIGURATION, Context.MODE_PRIVATE);
			int  size = sharedPreferences.getAll().size();

			if (size!=0){

				EmailDebugLog.getInstance(appContext).writeLog( "[HHIDConfigurations]:clearing previously stored emergency numbers ");
				sharedPreferences.edit().clear().commit();
			}

			if (value == null){
				EmailDebugLog.getInstance(appContext).writeLog( "[HHIDConfigurations]:new values null so clearing ");

				sharedPreferences.edit().clear().commit();
			}


			SharedPreferences.Editor editor = appContext.getSharedPreferences(EMERGENCY_CONFIGURATION, Context.MODE_PRIVATE).edit();



			for (int i=0; i<value.length(); i++){
				String phoneNumber = "";
				String contactName = "";
				JSONObject j = value.getJSONObject(i);
				if (j.has("phoneNumber"))
					phoneNumber =	j.getString("phoneNumber");
				if (j.has("contactName"))
					contactName =	j.getString("contactName");
				editor.putString(phoneNumber, contactName);
			}

			editor.commit();
			EmailDebugLog.getInstance(appContext).writeLog( "[HHIDConfigurations]:EmergencyNumbers stored successfully ");

			return true;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occured inside setEmergencyNumbers");
			return false;
		}
	}




	public static ArrayList<String[]> getEmergencyNumbers(Context appContext) {

		ArrayList<String[]> lst = new ArrayList<String[]>();
		String[] emergencyNumbers =  null;
		String[] emergencyNames =  null;
		try {

			SharedPreferences sharedPreferences = appContext.getSharedPreferences(EMERGENCY_CONFIGURATION, Context.MODE_PRIVATE);
			int  size = sharedPreferences.getAll().size();
			if (size!=0){
				emergencyNumbers = new String[size];
				emergencyNames = new String[size];
				int counter = 0;
				Map<String,?> keys = sharedPreferences.getAll();

				for(Map.Entry<String,?> entry : keys.entrySet()){
					//				            Log.d("map values",entry.getKey() + ": " + 
					//				                                   entry.getValue().toString());

					emergencyNumbers[counter] = entry.getKey().toString();
					emergencyNames[counter] = entry.getValue().toString();

					counter++;

				}
				lst.add(emergencyNames);
				lst.add(emergencyNumbers);
				EmailDebugLog.getInstance(appContext).writeLog( "[HHIDConfigurations]: stored  EmergencyNumbers length"+emergencyNumbers.length);

			}
			return lst;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occured inside setEmergencyNumbers");
			return null;
		}
	}



	public static boolean setAppListUploaingStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(APPLIST_UPLOADING_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAppListUploaingStatus");
			return stored;
		}
	}

	public static boolean getAppListUploaingStatus(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(APPLIST_UPLOADING_STATUS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getAppListUploaingStatus");
			return status;
		}
	}


	public static boolean setParentalProcessSettingsDownloadStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(PARENTAL_PROCESSES_DOWNLOADING_STATUS, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setParentalProcessSettingsDownloadStatus");
			return stored;
		}
	}

	public static boolean getParentalProcessSettingsDownloadStatus(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(PARENTAL_PROCESSES_DOWNLOADING_STATUS, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getParentalProcessSettingsDownloadStatus");
			return status;
		}
	}



	public static boolean setpreviousDataDays(Context ctx, String key, int value) {
		boolean conf = false;
		DebugLog.console("KEY: " + key);
		try {
			SharedPreferences.Editor editor = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.putInt(key, value);
			editor.commit();
			DebugLog.console("KEY: Stored successfully " + key);
			return conf=true;
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog(ex.toString()+"\r\n[Configurations]: Exception occured inside setpreviousDataDays");
			return conf;
		}
	}






//	public static boolean setDebugFileUploadingStatus(Context ctx, boolean debugFileUploadingStatus) {
//		boolean stored = false;
//		try{
//			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
//			pref.putBoolean(DEBUG_FILE_UPLOADING_STATUS, debugFileUploadingStatus);
//			pref.commit();
//			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]: debugFileUploadingStatus is saved"+debugFileUploadingStatus);
//
//			return stored = true;
//
//		}catch(Exception ex){
//			DebugLog.console(ex.toString());
//			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in setDebugFileUploadingStatus");
//			return stored;
//		}
//	}

//	public static boolean getDebugFileUploadingStatus(Context ctx) {
//		boolean debugFileUploadingStatus = true;
//		try{
//			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
//			debugFileUploadingStatus = pref.getBoolean(DEBUG_FILE_UPLOADING_STATUS, true);
//			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Saved debugFileUploadingStatus is "+debugFileUploadingStatus);
//
//			return debugFileUploadingStatus;
//		}catch(Exception ex){
//			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getDebugFileUploadingStatus");
//			return debugFileUploadingStatus;
//		}
//	}





	//Appusage
	public static long getAppUsageEndTime(Context ctx) {
		long status = getRegisterationTime(ctx);
		try {
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getLong(APP_USAGE_END_TIME_PREF, status);
			return status;
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getAppUsageStrtTime()");
			return status;
		}
	}

	public static boolean setAppUsageEndTime(Context ctx, long time) {
		boolean stored = false;
		try {
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putLong(APP_USAGE_END_TIME_PREF, time);
			pref.commit();
			return stored = true;
		} catch (Exception ex) {
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAppUsageStrtTime()");
			return stored;
		}
	}
	public static long getRegisterationTime(Context ctx) {
		long status = System.currentTimeMillis();
		try {
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getLong(REGISTERATION_TIME_PREF, status);
			return status;
		} catch (Exception ex) {
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getAppUsageStrtTime()");
			return status;
		}
	}

	public static boolean setRegisterationTime(Context ctx, long time) {
		boolean stored = false;
		try {
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putLong(REGISTERATION_TIME_PREF, time);
			pref.commit();
			return stored = true;
		} catch (Exception ex) {
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAppUsageStrtTime()");
			return stored;
		}
	}


	public static boolean setLastAppUsageReportingState(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, LAST_APPUSAGE_REPORTING_STATUS, state);
		return status;
	}

	public static String getLastAppUsageReportingState(Context appContext) {
		String state = getConfiguration(appContext, LAST_APPUSAGE_REPORTING_STATUS);
		if(state == null){
			return "";
		}else{
			return state;
		}
	}
	public static boolean setLastAdminRightsReportingState(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, LAST_ADMINRIGHTS_REPORTING_STATUS, state);
		return status;
	}

	public static String getLastAdminRightsReportingState(Context appContext) {
		String state = getConfiguration(appContext, LAST_ADMINRIGHTS_REPORTING_STATUS);
		if(state == null){
			return "";
		}else{
			return state;
		}
	}


	public static boolean setLastAccessibilityRightsReportingState(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, LAST_ACCESSIBILITY_REPORTING_STATUS, state);
		return status;
	}

	public static String getLastAccessibilityRightsReportingState(Context appContext) {
		String state = getConfiguration(appContext, LAST_ACCESSIBILITY_REPORTING_STATUS);
		if(state == null){
			return "";
		}else{
			return state;
		}
	}

	public static boolean setLastLocationReportingState(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, LAST_LOCATION_REPORTING_STATUS, state);
		return status;
	}

	public static String getLastLocationReportingState(Context appContext) {
		String state = getConfiguration(appContext, LAST_LOCATION_REPORTING_STATUS);
		if(state == null){
			return "";
		}else{
			return state;
		}
	}



	public static boolean setAPPHistoryReportingState(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, APP_HISTORY_REPORTING_STATUS, state);
		return status;
	}

	public static String getAPPHistoryReportingState(Context appContext) {
		String state = getConfiguration(appContext, APP_HISTORY_REPORTING_STATUS);
		if(state == null){
			return MpcUtil.DEFAULT_APPHISTORY_REPORTING_DAYS+"";
		}else{
			return state;
		}
	}

	public static boolean setDEBUGFILEUploadingReportingState(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, DEBUG_FILE_UPLOADING_STATUS, state);
		return status;
	}

	public static String getDEBUGFILEUploadingReportingState(Context appContext) {
		String state = getConfiguration(appContext, DEBUG_FILE_UPLOADING_STATUS);
		if(state == null){
			return "START";
		}else{
			return state;
		}
	}





	public static boolean setAPPHistoryReportingDaysDate(Context ctx, long time) {
		boolean stored = false;
		try {
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putLong(APP_HISTORY_REPORTING_DAYS, time);
			pref.commit();
			return stored = true;
		} catch (Exception ex) {
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setAPPHistoryReportingDaysDate()");
			return stored;
		}
	}


	public static String getLastAccessibilityRightsShownStatus(Context appContext) {
		String state = getConfiguration(appContext, LAST_ADMINRIGHTS_REPORTING_STATUS);
		if(state == null){
			return "";
		}else{
			return state;
		}
	}


	public static boolean setLastAccessibilityRightsShownStatus(Context appContext, String state) {
		boolean status =  false;
		status = setConfiguration(appContext, LAST_ACCESSIBILITY_REPORTING_STATUS, state);
		return status;
	}



	public static boolean setDailyQuota(Context appContext, String value) {

		try {



			SharedPreferences.Editor editor = appContext.getSharedPreferences(DAILY_QUOTA_APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			editor.putString(daily_quota_map_key, value);
			editor.commit();



			return true;
		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: Exception occuered inside setDailyQuota");
			return false;
		}
	}



	public static String getDailyQuota(Context appContext) {
		String conf = "";
		DebugLog.console("KEY: " + daily_quota_map_key);
		try {
			SharedPreferences pref = appContext.getSharedPreferences(DAILY_QUOTA_APP_CONFIGURATION, Context.MODE_PRIVATE);
			conf = pref.getString(daily_quota_map_key, null);

		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"\r\n[HHIDConfigurations]: Exception occuered inside getDailyQuota");
			conf = "";
		}
		return conf;
	}





	public static boolean setVPNEnableState(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(VPN_ENABLE_STATE, status);
			pref.commit();
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations] inside setVPNEnableState() storinf status "+status);
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setVPNEnableState");
			return stored;
		}
	}

	public static boolean getVPNEnableState(Context ctx) {
		boolean status = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(VPN_ENABLE_STATE, false);
			return status;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getVPNEnableState");
			return status;
		}
	}



	public static boolean setBuildInfoAnalyticsStatus(Context ctx, boolean status) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(Build_VERSION_SENT, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setBuildInfoAnalyticsStatus");
			return stored;
		}
	}
	public static boolean isBuildInfoAnalyticSent(Context ctx) {
		boolean isLoggedIn = false;
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			isLoggedIn = pref.getBoolean(Build_VERSION_SENT, false);
			return isLoggedIn;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in isBuildInfoAnalyticSent");
			return isLoggedIn;
		}
	}




	public static boolean setButtonPressedStatus(Context ctx, boolean status, String key) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putBoolean(key, status);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setButtonPressedStatus");
			return stored;
		}
	}




    public static boolean getButtonPressedStatus(Context appContext, String key) {
		boolean isButtonPressed = false;

		try {
			SharedPreferences pref = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			isButtonPressed = pref.getBoolean(key, false);
			return isButtonPressed;

		} catch (Exception e) {
		    EmailDebugLog.getInstance(appContext).writeLog("[HHIDConfigurations] inside getButtonPressedStatus() Exception is :"+e.toString());
			return isButtonPressed;
		}
    }



	public static void setPermissionScreenShownStatus(Context ctx, boolean value) {
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
//			pref.putBoolean(IS_PARENT_APP, false);
			pref.putBoolean(PERMISSION_SHOWN_STATUS, value);
			pref.commit();
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog( ex.toString()+"[HHIDConfigurations]: Exception occured while checking setPermissionScreenShownStatus");
		}
	}



	public static boolean isPermissionScreenShown(Context appContext) {
		Boolean status = true;

		try{
			SharedPreferences pref = appContext.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			status = pref.getBoolean(PERMISSION_SHOWN_STATUS, false);
			DebugLog.console("isPermissionScreenShown="+status);
			return status;
		}catch(Exception e){
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"[HHIDConfigurations]: exception occured while checking isPermissionScreenShown ");
			return status;
		}

	}



	public static boolean setPeshawarLastStructerIDagainstPSU(Context ctx, String key, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( key,value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setLastStructerIDagainstPSU");
			return stored;
		}
	}

	public static String getPeshawarLastStructerIDagainstPSU(Context ctx, String key) {

		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString(key, "0");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getLastStructerIDagainstPSU");
			return key;
		}
	}

	public static String getPeshawarLastHHIDQ2PSUAndHHID(Context ctx, String key) {

		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString(key, "0");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getLastStructerIDagainstPSU");
			return key;
		}
	}

	public static boolean setPeshawarLastHHIDQ2PSUAndHHID(Context ctx, String key, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( key,value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setLastHHIDagainstPSUAndStructID");
			return stored;
		}
	}


	public static boolean setPeshawarLastHHIDagainstPSUAndStructID(Context ctx, String key, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( key,value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setLastHHIDagainstPSUAndStructID");
			return stored;
		}
	}

	public static String getPeshawarLastHHIDagainstPSUAndStructID(Context ctx, String key) {

		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString(key, "0");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getLastHHIDagainstPSUAndStructID");
			return key;
		}
	}

	public static boolean setPeshawarCurrentPSU(Context ctx, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( "CurrentPSU",value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setCurrentPSU");
			return stored;
		}
	}

	public static String getPeshawarCurrentPSU(Context ctx ) {
		String key = "0";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString("CurrentPSU", "");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in CurrentPSU");
			return key;
		}
	}

	public static boolean setPeshawarCurrentPSUName(Context ctx, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( "CurrentPSUName",value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setCurrentPSUName");
			return stored;
		}
	}

	public static String getPeshawarCurrentPSUName(Context ctx ) {
		String key = "0";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString("CurrentPSUName", "0");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in CurrentPSUName");
			return key;
		}
	}


	public static boolean setPeshawarCurrentLoggedInUser(Context ctx, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( "CurrentLoggedInUser",value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setCurrentLoggedInUser");
			return stored;
		}
	}

	public static String getPeshawarCurrentLoggedInUser(Context ctx) {
		String key = "0";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString("CurrentLoggedInUser", "0");
			if(key.equalsIgnoreCase("0")){
				key = RConsUtils.getUserName();
			}

			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in CurrentLoggedInUser");
			return key;
		}
	}


	public static boolean setPeshawarCurrentDistrict(Context ctx, String value) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString( "CurrentDistrict",value);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setCurrentDistrict");
			return stored;
		}
	}

	public static String getPeshawarCurrentDistrict(Context ctx) {
		String key = "0";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString("CurrentDistrict", "0");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in CurrentDistrict");
			return key;
		}
	}




	public static boolean setPushNotificationRegisteredKey(Context ctx, String key) {
		boolean stored = false;
		try{
			SharedPreferences.Editor pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE).edit();
			pref.putString(PUSH_NOTIFICATION_key, key);
			pref.commit();
			return stored = true;
		}catch(Exception ex){
			DebugLog.console(ex.toString());
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:Exception occured in setPushNotificationRegisteredKey");
			return stored;
		}
	}

	public static String getPushNotificationRegisteredKey(Context ctx) {
		String key = "";
		try{
			SharedPreferences pref = ctx.getSharedPreferences(APP_CONFIGURATION, Context.MODE_PRIVATE);
			key = pref.getString(PUSH_NOTIFICATION_key, "");
			return key;
		}catch(Exception ex){
			EmailDebugLog.getInstance(ctx).writeLog("[HHIDConfigurations]:exception occured in getPushNotificationRegisteredKey");
			return key;
		}
	}




}
