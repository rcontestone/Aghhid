package com.rcons.fcallbacks.Utilties;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.mubashar.dateandtime.DebugLog;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by Administrator on 11/9/2016.
 */
public class Util {


    public static String LOGGED_IN_USER = "";

    public static int CURRENT_PROCESSING_MEMBER_INDEX = 1;
    public static int NEW_CHILD_MEM_ID = 700;
    public static int HOUSE_MEMEBERS_LIST_SIZE = 0;
    public static final int STATUS_SUCCESS = 100;
    public static final int GENERAL_ERROR = 200;
    public static final int STATUS_INVALID_PASSWD = 301;
    public static final int STATUS_INVALID_USERNAME = 302;
    public static final String MEMEBER_ID_INTENT = "intent_member_id";

    public static final String FALCOVPN_DB_NAME = "houses.sqlite";
    public static final String USERS_TABLE = "users";
    public static final String HHSUMMARY = "hhsummary";
    public static final String[] USER_COLUMNS = {"_username","_password"};
    public static final String[] HH_SUMMARY_COLUMNS = {"district","mauza_id","mauza_name","hhid","same_hh","same_village","out_of_village",
            "same_district","out_of_district","same_country","out_of_country","dead","initial_members","new_members","total_members"};


//    public static ArrayList<HouseMemberModel> allMembersMap = new ArrayList<HouseMemberModel>();
//    public static HouseMemberModel houseMemberModel = new HouseMemberModel();
//    public static MemberInfoModel memberInfoModel = new MemberInfoModel();
//    public static NewChildInfoModel newChildInfoModel = new NewChildInfoModel();
//    public static ListHouseHoldModel listHouseHoldModel = new ListHouseHoldModel();

    public static boolean isTraverseEnabled = false;























    public static boolean isInternetWorking(Context context) {


        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("https://www.google.com").openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1500);
            urlc.connect();
            return (urlc.getResponseCode() == 200);
        }   catch  (Exception e) {
            EmailDebugLog.getInstance(context).writeLog("[MpcUtil]:Exception occuered in  isInternetWorking returning false");
            return false;
        }


    }
    public static String getcurrentTime(int option) {
        String curTime = "";
        try {
            Date date = new Date();
            long time = date.getTime();
            DateFormat dateFormat = null;
            dateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
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
            } else if (option == 11) {
                dateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSSSS", Locale.ENGLISH);
            }
            curTime = dateFormat.format(new Date(time));

            DebugLog.console("[MpcUtil]: fromated current time is :" + curTime);

            return curTime;
        } catch (Exception e) {

            return curTime;
        }

    }


    public static String messageToShow = "";
    public static String TitleToShow = "";
    public static boolean verifyReceievedResponce(Context appContext, int serverResponce){

        boolean success= false;
        try{
            switch (serverResponce) {

                case STATUS_SUCCESS:
                    success = true;
                    showAlert(appContext,"SignIn","Successfully Signin!");
                    break;

                case STATUS_INVALID_PASSWD:
                    success = false;
                    messageToShow = appContext.getResources().getString(R.string.invalid_password_error_message_via_server_code);
                    TitleToShow = appContext.getResources().getString(R.string.invalid_password_error_message_title_via_server_code);
                    showAlert(appContext,TitleToShow,messageToShow);
                    break;

                case STATUS_INVALID_USERNAME:

                    success = false;
                    messageToShow = appContext.getResources().getString(R.string.invalid_user_name_error_message_via_errorcode);
                    TitleToShow = appContext.getResources().getString(R.string.invalid_user_name_error_message_title_via_errorcode);
                    showAlert(appContext,TitleToShow,messageToShow);
                    break;

                case GENERAL_ERROR:

                    success = false;
                    messageToShow = appContext.getResources().getString(R.string.general_error_message);
                    showAlert(appContext,TitleToShow,messageToShow);
                    break;

            }
            return success;
        } catch (Exception e) {
            DebugLog.console(e.toString()+"\r\n[ResponceVerifier]: Exception occured inside verifyResponce");
            return success;
        }
    }

    public static void showAlert(Context appContext, String title, String message){
        new AlertDialog.Builder(appContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(appContext.getResources().getString(R.string.OK), new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){

                    }
                })
                .show();
    }


    public static boolean isSdPresent() {
        try {

            return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        }catch (Exception ex) {

            return false;

        }
    }
    public static void showKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

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
    public static <T> List<List<T>> powerSet(List<T> originalSet) {
        List<List<T>> sets = new ArrayList<List<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new ArrayList<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        List<T> rest = new ArrayList<T>(list.subList(1, list.size()));
        for (List<T> set : powerSet(rest)) {
            List<T> newSet = new ArrayList<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public static int[] splitTagsInParts(String s, int partLength)
    {
        int len = s.length();
        // Number of parts
        int nparts = (len + partLength - 1) / partLength;
        int parts[] = new int[nparts];

        // Break into parts
        int offset= 0;
        int i = 0;
        while (i < nparts)
        {
            parts[i] = Integer.parseInt(s.substring(offset, Math.min(offset + partLength, len)));
            offset += partLength;
            i++;
        }

        return parts;
    }

    public static void reinitializeAll(Context appContext){
        try {
        //HouseHoldDataBaseHelper.getDataBaseProcessor(appContext).exportDB(appContext);
        Util.isTraverseEnabled = false;
        Util.CURRENT_PROCESSING_MEMBER_INDEX = 1;
        Util.NEW_CHILD_MEM_ID = 700;
//        Util.listHouseHoldModel = new ListHouseHoldModel();
//        Util.allMembersMap = new ArrayList<HouseMemberModel>();
//        Util.memberInfoModel = new MemberInfoModel();
//        Util.newChildInfoModel = new NewChildInfoModel();
        }catch (Exception ex) {

        }
    }

    public static String getDeviceUniqueId(Context appContext){
        String deviceUniqueId = "";
        try {
            WifiManager manager = (WifiManager)appContext. getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            String address = info.getMacAddress();
            return deviceUniqueId=address;
        } catch (Exception e) {
            EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[MpcUtil]:Exception occured inside getDeviceUniqueId");
            return deviceUniqueId;
        }
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }


//    public static String getMacAddr() {
//        try {
//            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface nif : all) {
//                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
//
//                byte[] macBytes = nif.getHardwareAddress();
//                if (macBytes == null) {
//                    return "";
//                }
//
//                StringBuilder res1 = new StringBuilder();
//                for (byte b : macBytes) {
//                    res1.append(Integer.toHexString(b & 0xFF) + ":");
//                }
//
//                if (res1.length() > 0) {
//                    res1.deleteCharAt(res1.length() - 1);
//                }
//                return res1.toString();
//            }
//        } catch (Exception ex) {
//        }
//        return "02:00:00:00:00:00";
//    }


    public static Intent buildNewIntent(Context appContext, Class<?> componentName){

        Intent intent = null;
        try{
            intent = new Intent(appContext,componentName);
            return intent;
        }catch (Exception ex){
            EmailDebugLog.getInstance(appContext).writeLog( "[MpcUtil]: Exception occuered inside buildNewIntent");
            return intent;
        }
    }
}
