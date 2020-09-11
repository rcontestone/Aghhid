package com.rcons.fcallbacks.http;

import android.content.Context;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.filemanager.FileManager;
import com.rcons.fcallbacks.EmailDebugLog;
import com.rcons.fcallbacks.Utilties.Util;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;


public class HttpsClient {

	private final static int MAX_FILE_SIZE = 19 * 1024 * 1024 ;
	private final static int CONNECTION_TIME_OUT =6*60 * 1000 ;
	private final static int CONNECTION_READ_TIME_OUT = 5*60 * 1000 ;
	private static Context apppContext = null;
	public  static String titile = "";
	public static String message = "";






	



	public static JSONObject sendHttpsPostRequestForPushAndPasswordVerification(Context appContext, String url , String param1, String value1, String param2, String value2)  {

		String responce = "";
		JSONObject json = null;

		HttpURLConnection con = null;

		StringBuilder debugString = new StringBuilder();
		apppContext = appContext;

		try {
			debugString.append("[HttpsClient] : inside sendNewHttpsPostRequest url : "+url+"\r\n");

			//	url = "https://192.168.7.147:443/1/1.php?";
			URL url1 = new URL(url);
			con = (HttpURLConnection) url1.openConnection();
						con.setRequestProperty("Accept-Encoding", "identity");

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			//con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");


			StringBuilder result = new StringBuilder();
			result.append(URLEncoder.encode(param1, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(value1, "UTF-8"));
			result.append("&");
			result.append(URLEncoder.encode(param2, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(value2, "UTF-8"));



			//con.setRequestProperty("Content-Length", String.valueOf(result.toString().length()));
			con.setChunkedStreamingMode(0);

			con.setReadTimeout(CONNECTION_READ_TIME_OUT);
			con.setConnectTimeout(CONNECTION_TIME_OUT); // timeout is in milliseconds
			con.connect();



			OutputStream out = new BufferedOutputStream(con.getOutputStream());


			BufferedOutputStream buf = new BufferedOutputStream(out);
			//	String b="Sachin is my favourite player";  
			buf.write(result.toString().getBytes());  

			buf.flush();  
			buf.close();  


			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {


				//			con.setRequestProperty("Content-Type",
				//					"multipart/form-data;boundary=" + boundary);








				InputStream in = new BufferedInputStream(con.getInputStream());
				BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					responce += s;
				}

				buffer.close();  
				in.close(); 
				DebugLog.console(responce);





				debugString.append("\r\n[HttpsClient] : server response: "+responce+"\r\n");
				json = new JSONObject(responce);

				titile = "server response:\r\n";
				message = responce;
				//				showAlert("server response:\r\n",responce);


			}else{

				titile ="Http Status \r\n";
				message = responce;
				//				showAlert("Http Status \r\n",httpStatus+"");
				json = new JSONObject();
				json.put("code", ResponceVerifier.GENERAL_ERROR);


			}


			//closeExpiredConnections(se, client);
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString());
			debugString = null;
			if(con!=null)
				con.disconnect();

			return json;




		}catch (SocketException sex) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+sex.toString()+"\r\n[HttpsClient] : Exception occuerred inside sendHttpsPostRequest");
			debugString = null;
			json = new JSONObject();
			try{
				titile ="SocketException\r\n";
				message = sex.toString()+"";

				if (con!=null)
					con.disconnect();



				//				showAlert("SocketException\r\n",sex.toString()+"");
				//closeExpiredConnections(se, client);
				json.put("code", ResponceVerifier.STATUS_SOCKET_EXCEPION_ERROR);
				return json;
			}catch (Exception e){
				return json;
			}

		}catch (Exception ex) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+ex.toString()+"\r\n[HttpsClient] : Exception occuerred inside sendHttpsPostRequest");
			json = new JSONObject();
			try{
				titile ="Exception\r\n";
				message = ex.toString()+"";
				if (con!=null)
					con.disconnect();
				//				showAlert("Exception\r\n",ex.toString()+"");
				//closeExpiredConnections(se, client);
				json.put("code", ResponceVerifier.GENERAL_ERROR);
				return json;
			}catch (Exception e){
				return json;
			}
		}
	}





	public static boolean binnaryFileUpload(Context appContext, String fileName, byte[] bmpBytes) {

		JSONObject json = null;
		boolean reported = false;
		BufferedReader buffReader = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;
		StringBuilder debugString = new StringBuilder();
		String userName="";
		String password = "";
		HttpURLConnection con = null;

		try {
			String baseUrl = "";//MpcUtil.getBaseURL(appContext);
			//baseUrl = baseUrl.replace("https", "http");

			String sUrl = baseUrl;//+ MpcUtil.HTTP_UPLOAD_URL ;
			debugString.append("[HttpsClient]: binnaryFileUpload uploading : sUrl: " + sUrl+"\r\n" );
			String boundary = "*****************************************";
			String newLine = "\r\n";

			URL url = new URL(sUrl);
			con = (HttpURLConnection) url.openConnection();


			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Accept-Encoding", "identity");
			con.setRequestMethod("POST");
			//con.setRequestProperty("Connection", "Keep-Alive");


			con.setRequestProperty("Content-Type",
					"multipart/form-data;boundary=" + boundary);
			dos = new DataOutputStream(con.getOutputStream());

			dos.writeBytes("--" + boundary + newLine);
			dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
					+ fileName + "\"" + newLine + newLine);

			//dos.write(bmpBytes.toByteArray());
			dos.write(bmpBytes);
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);


			dos.writeBytes("--" + boundary + newLine);
			dos.writeBytes("Content-Disposition: form-data;name=\"" + "u"
					+ "\"" + newLine + newLine + userName);
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);

			dos.writeBytes("--" + boundary + newLine);
			dos.writeBytes("Content-Disposition: form-data;name=\"" + "p"
					+ "\"" + newLine + newLine + password);
			dos.writeBytes(newLine);
			dos.writeBytes("--" + boundary + "--" + newLine);

			dos.flush();
			int respCode = con.getResponseCode();
			debugString.append("[HttpsClient]: respCode: " + respCode + " fileName: "+fileName+"\r\n");
			if (respCode == HttpURLConnection.HTTP_OK) {
				buffReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				line = buffReader.readLine();
				json = new JSONObject(line);
				debugString.append("[HttpsClient]: server response: "+json.toString(0)+"\r\n");

				if (!json.isNull("code")){
					if (json.get("code").toString().equalsIgnoreCase("100")){
						reported =  true;
					}else{
						debugString.append("[HttpsClient]: file not saved on server"+"\r\n");
					}
				}else{
					debugString.append("[HttpsClient]: server response does not contain code key"+"\r\n");
					reported = false;
				}
			} else {
				reported = false;
			}
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString());
			if(con!=null)
				con.disconnect();
			return reported;

		} catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+e.toString()+"\r\n[HttpClient]: Exception occured inside binnaryFileUpload"+"\r\n");			
			if(con!=null)
				con.disconnect();
			return reported;
		} finally {
			try {
				if (buffReader != null) {
					buffReader.close();
				}
				if(con!=null)
					con.disconnect();
			} catch (Exception e) {
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (Exception e) {
			}
			try {
				if (dos != null) {
					dos.close();
				}
			} catch (Exception e) {
			}
		}
	}







	public static JSONObject sendNewHttpsPostRequest(Context appContext, String url , JSONObject signUPJson)  {

		String responce = "";
		JSONObject json = null;
		InputStream content = null;
		HttpURLConnection con = null;

		StringBuilder debugString = new StringBuilder();
		apppContext = appContext;

		try {
			debugString.append("[HttpsClient] : inside sendNewHttpsPostRequest url : "+url+"\r\n");

			//	url = "https://192.168.7.147:443/1/1.php?";
			URL url1 = new URL(url);
			con = (HttpURLConnection) url1.openConnection();
						con.setRequestProperty("Accept-Encoding", "identity");

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			//con.setRequestProperty("Connection", "Keep-Alive");


			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setChunkedStreamingMode(0);


			con.setConnectTimeout(CONNECTION_TIME_OUT); // timeout is in milliseconds
			con.setReadTimeout(CONNECTION_READ_TIME_OUT);
			StringBuilder result = new StringBuilder();
			result.append(URLEncoder.encode("formData", "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(signUPJson.toString(), "UTF-8"));

			con.connect();



			OutputStream out = new BufferedOutputStream(con.getOutputStream());


			BufferedOutputStream buf = new BufferedOutputStream(out);
			//	String b="Sachin is my favourite player";  
			buf.write(result.toString().getBytes());  

			buf.flush();  
			buf.close();  


			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {


				//			con.setRequestProperty("Content-Type",
				//					"multipart/form-data;boundary=" + boundary);








				InputStream in = new BufferedInputStream(con.getInputStream());
				BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					responce += s;
				}

				buffer.close();  
				in.close(); 
				DebugLog.console(responce);





				debugString.append("\r\n[HttpsClient] : server response: "+responce+"\r\n");
				json = new JSONObject(responce);

				titile = "server response:\r\n";
				message = responce;
				//				showAlert("server response:\r\n",responce);


			}else{

				titile ="Http Status \r\n";
				message = responce;
				//				showAlert("Http Status \r\n",httpStatus+"");
				json = new JSONObject();
				json.put("code", ResponceVerifier.GENERAL_ERROR);


			}


			//closeExpiredConnections(se, client);
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString());
			debugString = null;
			if(con!=null)
				con.disconnect();

			return json;




		}catch (SocketException sex) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+sex.toString()+"\r\n[HttpsClient] : Exception occuerred inside sendHttpsPostRequest");
			debugString = null;
			json = new JSONObject();
			try{
				titile ="SocketException\r\n";
				message = sex.toString()+"";

				if (con!=null)
					con.disconnect();



				//				showAlert("SocketException\r\n",sex.toString()+"");
				//closeExpiredConnections(se, client);
				json.put("code", ResponceVerifier.STATUS_SOCKET_EXCEPION_ERROR);
				return json;
			}catch (Exception e){
				return json;
			}

		}catch (Exception ex) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+ex.toString()+"\r\n[HttpsClient] : Exception occuerred inside sendHttpsPostRequest");
			json = new JSONObject();
			try{
				titile ="Exception\r\n";
				message = ex.toString()+"";

				//				showAlert("Exception\r\n",ex.toString()+"");
				//closeExpiredConnections(se, client);
				json.put("code", ResponceVerifier.GENERAL_ERROR);
				if(con!=null)
					con.disconnect();
				return json;
			}catch (Exception e){
				return json;
			}
		}
	}



	public static JSONObject sendHttpsGetRequest(Context appContext, String url) {
		String responce = "";
		JSONObject json = null;
		InputStream content = null;
		HttpURLConnection con = null;

		StringBuilder debugString = new StringBuilder();
		apppContext = appContext;




		try{
			debugString.append("[HttpsClient] : inside sendNewHttpsGetRequest url : "+url+"\r\n");



			URL url1 = new URL(url);
			con = (HttpURLConnection) url1.openConnection();
						con.setRequestProperty("Accept-Encoding", "identity");

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			//con.setRequestMethod("POST");
			//con.setRequestProperty("Connection", "Keep-Alive");


			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setChunkedStreamingMode(0);

			con.setReadTimeout(CONNECTION_READ_TIME_OUT);
			con.setConnectTimeout(CONNECTION_TIME_OUT); // timeout is in milliseconds
			con.connect();



			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

				InputStream in = new BufferedInputStream(con.getInputStream());
				BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					responce += s;
				}

				buffer.close();  
				in.close(); 
				DebugLog.console(responce);





				debugString.append("\r\n[HttpsClient] : server response: "+responce+"\r\n");
				json = new JSONObject(responce);

				titile = "server response:\r\n";
				message = responce;
				//				showAlert("server response:\r\n",responce);
				//showPop();

			}else{

				titile ="Http Status \r\n";
				message = responce;
				//				showAlert("Http Status \r\n",httpStatus+"");
				json = new JSONObject();
				json.put("code", ResponceVerifier.GENERAL_ERROR);


			}


			//closeExpiredConnections(se, client);
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString());
			debugString = null;
			if(con!=null)
				con.disconnect();

			return json;




		}catch (SocketException sex) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+sex.toString()+"\r\n[HttpsClient] : Exception occuerred inside sendHttpsPostRequest");
			debugString = null;
			json = new JSONObject();
			try{
				titile ="SocketException\r\n";
				message = sex.toString()+"";

				if (con!=null)
					con.disconnect();


				//showPop();
				//				showAlert("SocketException\r\n",sex.toString()+"");
				//closeExpiredConnections(se, client);
				json.put("code", ResponceVerifier.STATUS_SOCKET_EXCEPION_ERROR);
				return json;
			}catch (Exception e){
				return json;
			}

		}catch (Exception ex) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+ex.toString()+"\r\n[HttpsClient] : Exception occuerred inside sendHttpsPostRequest");
			json = new JSONObject();
			try{
				titile ="Exception\r\n";
				message = ex.toString()+"";
				//showPop();
				//				showAlert("Exception\r\n",ex.toString()+"");
				//closeExpiredConnections(se, client);
				json.put("code", ResponceVerifier.GENERAL_ERROR);
				if(con!=null)
					con.disconnect();
				return json;
			}catch (Exception e){
				return json;
			}
		}
	}


	public static boolean dataFileUpload(Context appContext, String fileName) {
		File file = null;
		String mimeType = "";
		String serverFile = fileName;;
		StringBuilder debugString = new StringBuilder();
		String responce = "";
		JSONObject json = null;
		boolean reported = false;
		InputStream content = null;

		BufferedReader buffReader = null;
		DataOutputStream dos = null;
		FileInputStream fis = null;

		String userName="";
		String password = "";

		HttpURLConnection con = null;

		try {
			debugString.append("[HttpsClient]: inside dataFileUpload: file name: " + fileName + "\r\n");

			String lowerCase = fileName.toLowerCase();
			if (lowerCase.startsWith("null")) {
				debugString.append("File name starts with null so returning back without uploading");
				return true;
			}


//			String sUrl = "http://192.168.1.9:5050/jall/generic/bbUploadFile.php?";//MpcUtil.getBaseURL(appContext) + MpcUtil.HTTP_UPLOAD_URL ;

			//String	sUrl = "http://rconsdb.org/devteam/bbUploadFile.php?";
			String sUrl = "http://rconsdb.org/devteam/uploadandres.php?";
//			String	sUrl = "http://rconsdb.org/devteam/uploadmub.php?";
//

			if (Util.LOGGED_IN_USER.startsWith("Test")||Util.LOGGED_IN_USER.startsWith("TEST")){
				sUrl = "http://rconsdb.org/devteam/leap/test.php?";
			}else{
				sUrl = "http://rconsdb.org/devteam/dataentry/fielddata.php?";
			}



			DebugLog.console("[HttpsClient]:url : "+sUrl);
			if(fileName.endsWith(".json") ||fileName.endsWith(".txt")||fileName.endsWith("xml") ||fileName.endsWith("db") ||fileName.endsWith(".csv")){
				file = appContext.getFileStreamPath(fileName);//openFileInput(fileName);
			}else {
				debugString.append("[HttpsClient] dataFileUpload. Not a recognized format \r\n");
				return true;
			}

			if(fileName.endsWith(".txt")){

			}
			int bytesAvailable = (int)file.length();
			debugString.append("[HttpsClient]: file size in Bytes: " + bytesAvailable + " in KB:  " + bytesAvailable/1024 + "\r\n");
			if(bytesAvailable > MAX_FILE_SIZE || bytesAvailable == 0){
				debugString.append("[HttpsClient]: Not uploading as file size is not proper \r\n");
				return true;
			}else{


				String boundary = "*****************************************";
				String newLine = "\r\n";

				URL url = new URL(sUrl);
				con = (HttpURLConnection) url.openConnection();


				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);
				con.setRequestMethod("POST");
				//con.setRequestProperty("Connection", "Keep-Alive");

				//con.setChunkedStreamingMode(0);
				con.setReadTimeout(CONNECTION_READ_TIME_OUT);
				con.setConnectTimeout(CONNECTION_TIME_OUT);
				con.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
				dos = new DataOutputStream(con.getOutputStream());

				dos.writeBytes("--" + boundary + newLine);
				dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
						+ fileName + "\"" + newLine + newLine);




				//dos.write(bmpBytes.toByteArray());
				dos.write(FileManager.readFileDataInByteArray(appContext,fileName));
				dos.writeBytes(newLine);
				dos.writeBytes("--" + boundary + "--" + newLine);


				dos.writeBytes("--" + boundary + newLine);
				dos.writeBytes("Content-Disposition: form-data;name=\"" + "u"
						+ "\"" + newLine + newLine + userName);
				dos.writeBytes(newLine);
				dos.writeBytes("--" + boundary + "--" + newLine);

				dos.writeBytes("--" + boundary + newLine);
				dos.writeBytes("Content-Disposition: form-data;name=\"" + "p"
						+ "\"" + newLine + newLine + password);
				dos.writeBytes(newLine);
				dos.writeBytes("--" + boundary + "--" + newLine);

				dos.flush();
				int respCode = con.getResponseCode();
				debugString.append("[HttpsClient]: respCode: " + respCode + " fileName: "+fileName+"\r\n");

				if (respCode == HttpURLConnection.HTTP_OK) {
					InputStream in = new BufferedInputStream(con.getInputStream());
					BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
					String s = "";
					while ((s = buffer.readLine()) != null) {
						responce += s;
					}

					buffer.close();  
					in.close(); 
					debugString.append("[HttpsClient]: Server responce: " + responce+"\r\n");
					json = new JSONObject(responce);
					DebugLog.console("[HttpsClient] : inside sendHttpsPostRequest server response: "+json.toString(0));
					if (!json.isNull("code")){
						if (json.get("code").toString().equalsIgnoreCase("100")){
							reported =  true;
						}
					}else{
						reported = false;
					}
				}else{
					reported = false;
				}
			}
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString());

			if(con!=null)
				con.disconnect();
			return reported;
		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog(debugString.toString()+e.toString()+"\r\n[HttpClient]: Exception occured inside dataFileUpload");
			reported =  false;
			if(con!=null)
				con.disconnect();
			return reported;	
		}
	}






}
