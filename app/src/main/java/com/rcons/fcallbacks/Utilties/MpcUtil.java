package com.rcons.fcallbacks.Utilties;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.EmailDebugLog;
import com.rcons.fcallbacks.Main.CallMenuActivity;
import com.rcons.fcallbacks.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


/**
 * Created by MubasharChoudhry on 1/29/2019.
 */



public class MpcUtil {

    public static final String SIGNUP_URL = "";
    public static String DEVICE_UNIQUE_ID = "";
    public static int DEVICE_WIDTH = 0;
    public static String DEFAULT_DASHBOARD_URL;
    public static String DEFAULT_LOCATION_INTERVAL;
    public static String HOME_URL;
    public static String DEFAULT_APPHISTORY_REPORTING_DAYS;
    public static int DEFAULT_MINNIMUM_USERNAME_LENGTH = 4;
    public static int DEFAULT_MINNIMUM_PASSWORD_LENGTH = 4;
    public static int DEFAULT_DEVICE_NAME_LENGTH = 1;
    public static int DEFAULT_MAX_PASSWORD_LENGTH = 50;
    public static int RETRY_COUNT_FOR_SIGIN_UP_IN_SERVER_REQUEST = 3;
    public static int RETRY_COUNT_TO_CHECK_DEVICE_AVAILIBILTY_SERVER_REQUEST = 3;
    public static int RETRY_COUNT_TO_MAPP_DEVICE_SERVER_REQUEST = 3;
    public static int RETRY_COUNT_TO_CREATE_NEW_SUB_USER_SERVER_REQUEST = 3;
    public static int RETRY_COUNT_FOR_SETTING_GETTER = 2;
    public static int RETRY_COUNT_FOR_UPLOADING_FILE = 3;
    public static int RETRY_COUNT_FOR_DOWNLOADING_FILE = 2;
    public final static String HHID_Survey = "hhid_survey";
    public final static String HHID_Survey_PHONE_LIST = "hhid_survey_phone_list";
    public final static String HHID_survey_details = "hhid_survey_details";

    public static Intent buildNewIntent(Context appContext, Class<?> componentName){

        Intent intent = null;
        try{
            intent = new Intent(appContext,componentName);
            return intent;
        }catch (Exception ex){
//           EmailDebugLog.writeLog( "[MpcUtil]: Exception occuered inside buildNewIntent");
            return intent;
        }
    }
    public static String getMAC(Context appContext){
        String deviceUniqueId = "";
        try {
//            WifiManager manager = (WifiManager)appContext. getSystemService(Context.WIFI_SERVICE);
//            WifiInfo info = manager.getConnectionInfo();
//            String address = info.getMacAddress();
            return deviceUniqueId= Settings.Secure.getString(appContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[MpcUtil]:Exception occured inside getDeviceUniqueId");
            return deviceUniqueId;
        }
    }

    public static String checkIfExists(String[] myStringArray, String stringToLocate) {
        List<String> stooges = Arrays.asList( myStringArray );
        String value = "";
        if( stooges.contains( stringToLocate )){
            //	value = stooges.indexOf(stringToLocate)+"";
            value = Arrays.asList(AppController.getInstance().getResources().getStringArray(R.array.array_psu_code_names)).get(stooges.indexOf(stringToLocate));

        }
        return value;
    }

    public static String getcurrentTime(int option){

        String curTime = "";

        try{

            Date date = new Date();
            long time = date.getTime();
            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.ENGLISH);
            if (option == 0){
                dateFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            }else if (option == 1){
                dateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
            }else if(option == 2){
                dateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            }else if (option == 3){
                dateFormat = new SimpleDateFormat("HH", Locale.ENGLISH);
            }else if (option == 4){
                dateFormat = new SimpleDateFormat("mm", Locale.ENGLISH);
            }else if (option == 5){
                dateFormat = new SimpleDateFormat("ss", Locale.ENGLISH);
            }else if (option == 6){
                dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            }else if (option == 7){
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSSSS", Locale.ENGLISH);
            }else if (option == 11){
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
            }else if (option == 13){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            }else if (option == 14){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            }else if (option == 1111){
                dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.ENGLISH);
            }



                curTime = dateFormat.format(new Date(time));
                DebugLog.console("[MpcUtil] inside getcurrentTime() DateAndTime synced false "+curTime);
                //EmailDebugLog.getInstance(MyApplication.getAppContext()).writeLog("[MpcUtil] inside getcurrentTime() DateAndTime synced false "+curTime);




            //DebugLog.console("[MpcUtil]: fromated current time is :"+curTime);

            return curTime;
        }catch(Exception e){

            return curTime;
        }

    }


    public static String getFormatedTime(String formate, long time ){

        String curTime = "";

        try{

            Date date = new Date();

            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat(formate, Locale.ENGLISH);



            curTime = dateFormat.format(new Date(time));
            DebugLog.console("[MpcUtil] inside getFormatedTime() DateAndTime local time "+curTime);
            DebugLog.console("[MpcUtil]: fromated  time is :"+curTime);

            return curTime;
        }catch(Exception e){

            return curTime;
        }

    }


    public static boolean  hideSoftKeyBoard(Context ctx, EditText myEditText){

        boolean blocked = false;
        try {

            InputMethodManager imm = (InputMethodManager)ctx.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);

            return blocked;
        }catch (Exception ex) {
            DebugLog.console( ex.toString()+"\r\n[MpcUtil]: Exception occurred in hideSoftKeyBoard");
            return blocked;
        }

    }

    public static boolean  restartInput(Context ctx, EditText myEditText){

        boolean blocked = false;
        try {

            InputMethodManager imm = (InputMethodManager)ctx.getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(myEditText, 0);

            return blocked;
        }catch (Exception ex) {
            DebugLog.console( ex.toString()+"\r\n[MpcUtil]: Exception occurred in hideSoftKeyBoard");
            return blocked;
        }
    }
    public static String getPassword(String[] myStringArray, String stringToLocate) {
        List<String> stooges = Arrays.asList( myStringArray );
        String value = "";
        if( stooges.contains( stringToLocate )){
            //	value = stooges.indexOf(stringToLocate)+"";
            value = Arrays.asList(AppController.getInstance().getResources().getStringArray(R.array.array_name_enumerator_passwords)).get(stooges.indexOf(stringToLocate));

        }
        DebugLog.console("[MpcUtil] inside getPassword() value "+value);
        return value;
    }











}
