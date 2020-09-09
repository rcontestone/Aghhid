package com.rcons.fcallbacks.Utilties;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.CallLog;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mubashar.dateandtime.DebugLog;
import com.rcons.fcallbacks.sms.SmsDeliveredReceiver;
import com.rcons.fcallbacks.sms.SmsSentReceiver;

import androidx.core.app.ActivityCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RConsUtils {


    public static boolean OLD_BUILD = false;

    public static String prefRegistrationState = "user_registration_state";
    public static int UserRegistered = 1;
    public static int UserUnRegistered = 0;


    public static String Enum_prefRegistrationState = "Enum_registration_state";
    public static int Enum_Registered = 1;
    public static int Enum_UserUnRegistered = 0;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String currentDateTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(System.currentTimeMillis());


    }

    public static String LastCallDuration(Context context, String phoneNumber) {
        try {


            StringBuffer sb = new StringBuffer();
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                return "";

            }
            Cursor cur = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

            int number = cur.getColumnIndex(CallLog.Calls.NUMBER);
            int duration = cur.getColumnIndex(CallLog.Calls.DURATION);

            while (cur.moveToNext()) {
                String phNumber = cur.getString(number);

                MubLog.cpnsoleLog("phNumber in phone db  " + phNumber);
                MubLog.cpnsoleLog("phNumber in app db  " + phoneNumber);
                String callDuration = cur.getString(duration);

                String timeDuration = "";
                if (!StringUtils.isEmpty(callDuration)) {
                    try {
                        int seconds = Integer.parseInt(callDuration);
                        int hours = seconds / 3600;
                        int minutes = (seconds % 3600) / 60;
                        seconds = seconds % 60;

                        timeDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    } catch (Exception e) {
                        timeDuration = "00:00:00";
                    }

                } else {
                    timeDuration = "00:00:00";
                }


                if (phNumber.equalsIgnoreCase(phoneNumber) || phNumber.contains(phoneNumber)) {
                    sb.append(timeDuration);
                }
                break;
            }
            cur.close();
            String str = sb.toString();
            return str;
        } catch (Exception e) {
            Log.e("LastCallDuration", "LastCallDuration: " + e.toString());
            return "";

        }
    }


    public static long LastCallDurationInMinutes(Context context, String phoneNumber) {

        long lastCallDurationInMinutes = 0;
        try {


            StringBuffer sb = new StringBuffer();
            if (ActivityCompat.checkSelfPermission(context,
                    Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                return lastCallDurationInMinutes;

            }
            Cursor cur = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

            int number = cur.getColumnIndex(CallLog.Calls.NUMBER);
            int duration = cur.getColumnIndex(CallLog.Calls.DURATION);

            while (cur.moveToNext()) {
                String phNumber = cur.getString(number);

                MubLog.cpnsoleLog("LastCallDurationInMinutes phNumber in phone db  " + phNumber);
                MubLog.cpnsoleLog("LastCallDurationInMinutes phNumber in app db  " + phoneNumber);

                String callDuration = cur.getString(duration);
                MubLog.cpnsoleLog("LastCallDurationInMinutes callDuration in phone db  " + callDuration);
                String timeDuration = "";
                int minutes = 0;
                if (!StringUtils.isEmpty(callDuration)) {

                    try {
                        int seconds = Integer.parseInt(callDuration);
                        int hours = seconds / 3600;
                        minutes = (seconds % 3600) / 60;
                        seconds = seconds % 60;

                        timeDuration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    } catch (Exception e) {
                        timeDuration = "00:00:00";
                        lastCallDurationInMinutes = 0;
                    }

                } else {
                    timeDuration = "00:00:00";
                }


                if (phNumber.equalsIgnoreCase(phoneNumber) || phNumber.contains(phoneNumber)) {
                    MubLog.cpnsoleLog("inside LastCallDurationInMinutes number matched " + phoneNumber);
                    sb.append(timeDuration);
                    if (minutes > 0) {
                        lastCallDurationInMinutes = lastCallDurationInMinutes + minutes;
                    }
                }

            }
            cur.close();
            String str = sb.toString();
            MubLog.cpnsoleLog("LastCallDurationInMinutes returning time in minutes is " + lastCallDurationInMinutes);
            return lastCallDurationInMinutes;
        } catch (Exception e) {
            MubLog.cpnsoleLog("inside LastCallDurationInMinutes" + e.toString());
            return lastCallDurationInMinutes;

        }
    }


    public static String getLastCallTime(Context context, String phoneNumber) {
        StringBuffer sb = new StringBuffer();
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return "";

        }
        Cursor cur = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, android.provider.CallLog.Calls.DATE + " DESC");

        int number = cur.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cur.getColumnIndex(CallLog.Calls.DURATION);
        int date = cur.getColumnIndex(CallLog.Calls.DATE);

        while (cur.moveToNext()) {
            String phNumber = cur.getString(number);
            //    String callDuration = cur.getString( duration );
            String callDate = cur.getString(date);
            if (phNumber.equalsIgnoreCase(phoneNumber) || phNumber.contains(phoneNumber)) {
                sb.append(callDate);

            }
            break;
        }
        cur.close();
        String str = sb.toString();
        return str;
    }


    public static int GetRegistrationState() {

        int registrationState = AppController.appSharedPrefs.getInt(prefRegistrationState, 0);
        return registrationState;
    }

    public static void SaveUserInfo(String name, String password) {

        SharedPreferences.Editor prefsEditor = AppController.appSharedPrefs.edit();
        prefsEditor.putString("rcons_callback_username", name);
        prefsEditor.putString("rcons_callback_password", password);

        prefsEditor.putInt(RConsUtils.prefRegistrationState, RConsUtils.UserRegistered);
        prefsEditor.commit();

    }

    public static void LogoutUser() {

        SharedPreferences.Editor prefsEditor = AppController.appSharedPrefs.edit();
        prefsEditor.putString("rcons_callback_username", "");
        prefsEditor.putString("rcons_callback_password", "");

        prefsEditor.putInt(RConsUtils.prefRegistrationState, RConsUtils.UserUnRegistered);
        prefsEditor.commit();

    }

    public static String getUserName() {

        String customerID = AppController.appSharedPrefs.getString("rcons_callback_username", "");
        return customerID.toUpperCase();
    }

    public static String getUserPassword() {

        String customerID = AppController.appSharedPrefs.getString("rcons_callback_password", "");
        return customerID;
    }

    public static void HideKeyBoard(Context context) {
        try {


            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {

        }

    }


    public static String getDeviceID(Context context) {


        String deviceID = "NA";
        try {

            deviceID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            return deviceID;
        } catch (Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog("[RConsUtils] inside getDeviceID() Exception is :"+e.toString());

            return deviceID;
        }

    }

    public static void hideKeyboard(Activity activity) {
        try {
            View view = activity.findViewById(android.R.id.content);
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }

    }

    public static void SaveEnumInfo(String name, String code) {

        SharedPreferences.Editor prefsEditor = AppController.Enum_appSharedPrefs.edit();
        prefsEditor.putString("enum_name", name);
        prefsEditor.putString("enum_code", code);

        prefsEditor.putInt(RConsUtils.Enum_prefRegistrationState, RConsUtils.Enum_Registered);
        prefsEditor.commit();

    }

    public static void LogoutEnum() {

        SharedPreferences.Editor prefsEditor = AppController.Enum_appSharedPrefs.edit();
        prefsEditor.putString("enum_name", "");
        prefsEditor.putString("enum_code", "");

        prefsEditor.putInt(RConsUtils.Enum_prefRegistrationState, RConsUtils.Enum_UserUnRegistered);
        prefsEditor.commit();

    }

    public static String getEnumName() {

        String customerID = AppController.Enum_appSharedPrefs.getString("enum_name", "");

        if(customerID.equalsIgnoreCase(""))
            customerID = RConsUtils.getUserName();
        return customerID;
    }

    public static String getEnumCode() {

        String customerID = AppController.Enum_appSharedPrefs.getString("enum_code", "");
        return customerID;
    }

    public static int GetEnumState() {

        int registrationState = AppController.Enum_appSharedPrefs.getInt(Enum_prefRegistrationState, 1);
        return registrationState;
    }

    public static void hideView(LinearLayout layout1, LinearLayout layout2) {

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);

    }

    public static void setEditText(EditText editText, String string) {
        if (!StringUtils.isEmpty(string)) {
            editText.setText(string);
        }
    }

    public static void setotherEditText(EditText editText, String string) {
        if (!StringUtils.isEmpty(string)) {
            editText.setText(string);
            editText.setVisibility(View.VISIBLE);
        } else {
            editText.setVisibility(View.GONE);
        }
    }

    public static void setradiogroup(String string, RadioGroup radioGroup) {
        if (!StringUtils.isEmpty(string)) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if (radioButton != null) {
                    if (radioButton.getTag().toString().equalsIgnoreCase(string)) {
                        radioButton.setChecked(true);
                    }
                }

            }
        }
    }

    public static void setCheckbox_load(CheckBox chekbox, String qname) {
        if (qname.equalsIgnoreCase("1")) {
            chekbox.setChecked(true);
        } else {
            chekbox.setChecked(false);
        }
    }


    public static String getcurrentTime(int option) {

        String curTime = "";

        try {

            Date date = new Date();
            long time = date.getTime();
            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.ENGLISH);
            if (option == 0) {
                dateFormat = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            } else if (option == 1) {
                dateFormat = new SimpleDateFormat("MM", Locale.ENGLISH);
            } else if (option == 2) {
                dateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            } else if (option == 3) {
                dateFormat = new SimpleDateFormat("HH", Locale.ENGLISH);
            } else if (option == 4) {
                dateFormat = new SimpleDateFormat("mm", Locale.ENGLISH);
            } else if (option == 5) {
                dateFormat = new SimpleDateFormat("ss", Locale.ENGLISH);
            } else if (option == 6) {
                dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            } else if (option == 7) {
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSSSS", Locale.ENGLISH);
            } else if (option == 11) {
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
            } else if (option == 13) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            } else if (option == 14) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            } else if (option == 1111) {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.ENGLISH);
            }


            curTime = dateFormat.format(new Date(time));
            DebugLog.console("[MpcUtil] inside getcurrentTime() DateAndTime synced false " + curTime);
            //EmailDebugLog.getInstance(MyApplication.getAppContext()).writeLog("[MpcUtil] inside getcurrentTime() DateAndTime synced false "+curTime);


            //DebugLog.console("[MpcUtil]: fromated current time is :"+curTime);

            return curTime;
        } catch (Exception e) {

            return curTime;
        }

    }


    public static void sendSMS(Context appContext, String phoneNumber, String message) {

        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
        PendingIntent sentPI = PendingIntent.getBroadcast(appContext, 0,
                new Intent(appContext, SmsSentReceiver.class), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(appContext, 0,
                new Intent(appContext, SmsDeliveredReceiver.class), 0);
        try {
            SmsManager sms = SmsManager.getDefault();
            ArrayList<String> mSMSMessage = sms.divideMessage(message);
            for (int i = 0; i < mSMSMessage.size(); i++) {
                sentPendingIntents.add(i, sentPI);
                deliveredPendingIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
                    sentPendingIntents, deliveredPendingIntents);

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(appContext, "SMS sending failed..." + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public static <T> T iif(boolean test, T ifTrue, T ifFalse) {
        return test ? ifTrue : ifFalse;
    }
}
