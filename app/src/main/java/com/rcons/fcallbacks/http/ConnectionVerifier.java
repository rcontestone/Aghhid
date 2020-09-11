package com.rcons.fcallbacks.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.mubashar.dateandtime.DebugLog;
import com.rcons.fcallbacks.EmailDebugLog;


public class ConnectionVerifier {

	public Context ctx = null;


	public static boolean isInternetOnNew(Context ctx) {
		boolean isInternetAvailable =false;

		try{
			ConnectivityManager connec =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			if ( connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ){
				isInternetAvailable=false;
			}else{

				//Wifi available
				EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: Wifi available connected");

				return isInternetAvailable=true;
			}


			String imsi = getIMSINumber(ctx);
			if (imsi!=null){
				if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED  ){
					isInternetAvailable=false;
				}else{
					isInternetAvailable = true;
					EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: Data plan available connected");
				}

			}else{

				//Sim not available
			}


			return isInternetAvailable ;
		}catch(Exception e){
			EmailDebugLog.getInstance(ctx).writeLog(e.toString()+ "\r\n[ConnectionVrifier]: Exception occured in isInternetOn"+e.toString());
			return isInternetAvailable = true;
		}
	}




	public static boolean isInternetOn(Context ctx) {
		boolean isInternetAvailable =false;

		try{
			ConnectivityManager connec =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			if ( connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ){
				isInternetAvailable=false;
			}else{

				//Wifi available
				EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: Wifi available connected");

				return isInternetAvailable=true;
			}


			String imsi = getIMSINumber(ctx);
			if (imsi!=null){
				if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED  ){
					isInternetAvailable=false;
				}else{
					isInternetAvailable = true;
					EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: Data plan available connected");
				}

			}else{

				//Sim not available
			}


			return isInternetAvailable ;
		}catch(Exception e){
			EmailDebugLog.getInstance(ctx).writeLog(e.toString()+ "\r\n[ConnectionVrifier]: Exception occured in isInternetOn"+e.toString());
			return isInternetAvailable = false;
		}
	}




	public static String whichNetworkEnabled(Context ctx) {
		String result = "NotConnected";

		try{
			ConnectivityManager connec =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			if ( connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ){

				String imsi = getIMSINumber(ctx);
				if (imsi!=null) {
					if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED) {

					} else {
						result = "GPRS";
						EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: Data plan available connected");
					}
				}



			}else{


				EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: WIFI available connected");
				result = "WIFI";
			}


			return  result;
		}catch(Exception e){
			EmailDebugLog.getInstance(ctx).writeLog(e.toString()+ "\r\n[ConnectionVrifier]: Exception occured in whichNetworkEnabled"+e.toString());
			return  result;
		}
	}

	public static String isGPRSEnabled(Context ctx) {
		String result = "Disabled";

		try{
			ConnectivityManager connec =  (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
			String imsi = getIMSINumber(ctx);
			if (imsi!=null){
				if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED  ){

				}else{
					result = "GPRS";
					EmailDebugLog.getInstance(ctx).writeLog("\r\n[ConnectionVrifier]: Data plan available connected");
				}

			}else{

				//Sim not available
			}

			return  result;
		}catch(Exception e){
			EmailDebugLog.getInstance(ctx).writeLog(e.toString()+ "\r\n[ConnectionVrifier]: Exception occured in isGPRSEnabled"+e.toString());
			return  result;
		}
	}





	public static boolean isConnectingToInternet(Context _context){
		ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}
		}
		return false;
	}
	
	
	
	private static String getIMSINumber(Context ctx){
		String imsi = null;
		
		try {
			
	
		TelephonyManager tm = (TelephonyManager)ctx.getSystemService(
				Context.TELEPHONY_SERVICE);
		imsi = tm.getSubscriberId();
		return imsi;
		}catch (Exception e) {
			DebugLog.console(e, "\r\n[ConnectionVrifier]: Exception occured in getIMSINumber"+e.toString());
			return imsi;
		}
	}
}
