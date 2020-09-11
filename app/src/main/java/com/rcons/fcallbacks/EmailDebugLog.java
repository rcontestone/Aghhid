package com.rcons.fcallbacks;

import android.content.Context;

import com.mubashar.dateandtime.DebugLog;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class EmailDebugLog {

	public static final String DEBUG_FILE_NAME = "DEBUGLOG";
	private static EmailDebugLog instanceDebugLog = null;
	private Context appContext = null;
	public synchronized static EmailDebugLog getInstance(Context ctx) {
		if (instanceDebugLog == null) {
			instanceDebugLog = new EmailDebugLog(ctx);
		}
		return instanceDebugLog;
	}

	private EmailDebugLog(Context appContext) {
		this.appContext = appContext;
	}

	public synchronized String getStackTrace(Exception e, String methodName){
		try{
			Writer result = new StringWriter();
			PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			String stacktrace = result.toString();
			return (stacktrace + methodName);
		}catch(Exception ex){
			DebugLog.console(ex.toString() + "\r\n[EmailDebugLog]: exception inside getStackTrace");
			return "\r\n[EmailDebugLog]: exception inside getStackTrace" ;

		}
	}

	public synchronized void writeStackTrace(Exception e, String methodName){
		try{
			Writer result = new StringWriter();
			PrintWriter printWriter = new PrintWriter(result);
			e.printStackTrace(printWriter);
			String stacktrace = result.toString();
			writeLog(stacktrace + methodName);
		}catch(Exception ex){
			DebugLog.console(ex.toString() + "\r\n[EmailDebugLog]: exception inside writeStackTrace ");

		}
	}

	public synchronized void writeLog(String str){
		try{


			str = "";//MpcUtil.getcurrentTime(9)+str;
//			str = str;
			DebugLog.console(str);

			if(appContext != null){
//				FileOutputStream fos ;
//				fos= appContext.openFileOutput(DEBUG_FILE_NAME, Context.MODE_APPEND);
//				str = str +"\r\n";
//				fos.write(str.getBytes("UTF-8"));
//				fos.close();
			}else{
				DebugLog.console(str);
			}


		}catch(Exception e){
			DebugLog.console(e.toString());
			DebugLog.console("[EmailDebugLog]: exception inside writeLog");

		}
	}

	public synchronized void deleteLogFile(){
		try{
			appContext.deleteFile(DEBUG_FILE_NAME);
		}catch(Exception e){
			DebugLog.console(e.toString());
			DebugLog.console("[EmailDebugLog]: exception inside deletLogFileg");

		}
	}

	public static String format(String format, Object... objs) {
		if (objs == null || objs.length == 0) {
			return format;
		} else {
			return String.format(format, objs);
		}
	}

	public static final String URL_FILE_NAME = "URLS";
	public synchronized void writeURLInLog(String str){
		try{


			str = "";//MpcUtil.getcurrentTime(9)+str;
			DebugLog.console(str);
//			if(MpcUtil.isMemorySizeAvailableAndroid(appContext,DISK_SPACE_LIMIT_TO_WITE_IN_LOGFILE, false,false,EmailDebugLog.class.getName())){

			if(appContext != null){
				FileOutputStream fos ;
				fos= appContext.openFileOutput(URL_FILE_NAME, Context.MODE_APPEND);
				str = str +"\r\n";
				fos.write(str.getBytes("UTF-8"));
				fos.close();
			}else{
				DebugLog.console(str);
			}

//			}else{
//
//			}

		}catch(Exception e){
			DebugLog.console(e.toString());
			DebugLog.console("[EmailDebugLog]: exception inside writeLog");

		}
	}

}
