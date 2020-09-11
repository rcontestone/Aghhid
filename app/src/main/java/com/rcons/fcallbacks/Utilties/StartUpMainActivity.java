package com.rcons.fcallbacks.Utilties;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.EmailDebugLog;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Main.MainMenuActivity;
import com.rcons.fcallbacks.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class StartUpMainActivity extends Activity {


	Button btn;
	private ProgressBar progressBar;
	TextView txt;
	Integer count =1;
	Context appContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startupmain);

		appContext = getApplicationContext();

		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		txt = (TextView) findViewById(R.id.output);
//    	OnClickListener listener = new OnClickListener() {

		//progressBar.setVisibility(View.INVISIBLE);
		//txt.setVisibility(View.INVISIBLE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
		}

	}



	class MyTask1 extends AsyncTask<Integer, Integer, String> {


		int count = 0;
		int Totalcount = 0;
		DatabaseAdapter adapter;

		@Override
		protected String doInBackground(Integer... params) {

			try {
				adapter = new DatabaseAdapter(StartUpMainActivity.this);
				adapter.Open();
				String userName =RConsUtils.getUserName();


				String usernameParam = URLEncoder.encode(userName, "UTF-8");

				String url = "http://rconsdb.org/devteam/general/services/ffbcsr/aghhid_sample.php?" + "&pageSize=" + "100" + "&username=" + usernameParam + "&password=" + "fcb123!1"; // New Url


				if (RConsUtils.isNetworkAvailable(StartUpMainActivity.this)) {

					JSONArray data = HttpsClient.sendHttpsGetRequestForJSONARRAY(appContext,url);
					DebugLog.console("[MyTask1] inside doInBackground() "+data.length());

					if(data.length()>0){

                        adapter.aghhid_insertImporteddata(appContext,data);
						return "Imported Successfully against username "+RConsUtils.getUserName();
					}else{
						return "Error in Importing. Please contact Admin ";
					}


				}else{

					return StartUpMainActivity.this.getString(R.string.conenctivity_issue);
//					return  "local database imported you can start work";

				}







			} catch (Exception ex) {
				return "Error: Please connect to your manager";

			}

		}


		private void readCountFromAsset() {
			// TODO Auto-generated method stub
			InputStream fIn =  null;
			try {

				//File file = new File("Item Codes.csv");
				//BufferedReader bufRdr  = new BufferedReader(new FileReader(file));



				fIn= appContext.getResources().getAssets().open("2.csv", Context.MODE_WORLD_READABLE);

				BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(fIn));






				String line = null;


				//read each line of text file
				while((line = bufRdr.readLine()) != null )
				{

					// publishProgress(count);
					DebugLog.tagConsole("DataFilter","MainActivity"+line+"\r\nTotalcount"+Totalcount);
					Totalcount++;



				}




			}catch (Exception ex) {
				DebugLog.tagConsole("DataFilter",ex.toString());
			}finally {
				try {
					fIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		public synchronized boolean uploadFile(String uploadFileName){
			boolean uploaded = false;
			try {
				int retry = 0;
				while(retry < 2){



					uploaded = true;//HttpsClient.dataFileUpload(appContext,uploadFileName);










					if(uploaded){
						appContext.deleteFile(uploadFileName);
						//HttpsClient.sendHttpsGetRequest(appContext,"http://rconsdb.org/devteam/dataentry/process_single.php?&filename="+uploadFileName+"&dt="+MpcUtil.getcurrentTime(1111)+"&userName="+SecureTeenConfigurations.getPeshawarCurrentLoggedInUser(appContext).replaceAll(" ","_"));

						break;
					}else{
						retry++;
					}
				}
				return uploaded;
			}catch (Exception e) {
				EmailDebugLog.getInstance(appContext).writeLog(e.toString()+ "\r\n[SecureSecondService]: Exception occuered in uploadFile");
				return false;
			}
		}



		private void readFileFromAsset() {
			// TODO Auto-generated method stub
			InputStream fIn =  null;
			try {

				//File file = new File("Item Codes.csv");
				//BufferedReader bufRdr  = new BufferedReader(new FileReader(file));



				fIn= appContext.getResources().getAssets().open("2.csv", Context.MODE_WORLD_READABLE);

				BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(fIn));






				String line = null;
				int row = 0;
				int col = 0;
				count = 0;
				//read each line of text file
				while((line = bufRdr.readLine()) != null )
				{

					publishProgress(count);
					DebugLog.tagConsole("DataFilter","MainActivity"+line);

					StringTokenizer st = new StringTokenizer(line,",");
					int colr = 0;
					while (st.hasMoreTokens())
					{
						//get next token and store it in the array
						//numbers[row][col] = st.nextToken();
						if (colr==0){
							st.nextToken();
						}

						if (colr==1){
							//	DeviceAlreadyRegisterActivity.itemNamesArray[count] = 	st.nextToken();
						}

						if (colr==2){
							//	DeviceAlreadyRegisterActivity.itemCodesArray[count] = 	st.nextToken();

						}

						if (colr==3){
							//		DeviceAlreadyRegisterActivity.newItemNamesArray[count] = 	st.nextToken();

						}










						colr++;
					}
					col = 0;
					count++;
				}

			}catch (Exception ex) {
				DebugLog.tagConsole("DataFilter",ex.toString());
			}finally {
				try {
					fIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}





		private void readUnitFileFromAsset() {
			// TODO Auto-generated method stub
			InputStream fIn =  null;
			try {

				//File file = new File("Item Codes.csv");
				//BufferedReader bufRdr  = new BufferedReader(new FileReader(file));



				fIn= appContext.getResources().getAssets().open("unit.csv", Context.MODE_WORLD_READABLE);

				BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(fIn));

//		       	DeviceAlreadyRegisterActivity.Unit_NewItemIDArray = new String[Totalcount];
//	        	DeviceAlreadyRegisterActivity.Unit_UnitIDArray = new String[Totalcount];
//	        	DeviceAlreadyRegisterActivity.Unit_UnitArray = new String[Totalcount];
//	        	DeviceAlreadyRegisterActivity.Unit_UnitDescriptionArray = new String[Totalcount];




				String line = null;
				int row = 0;
				int col = 0;
				count = 0;
				//read each line of text file
				while((line = bufRdr.readLine()) != null )
				{

					publishProgress(count);
					DebugLog.tagConsole("DataFilter","MainActivity"+line);

					StringTokenizer st = new StringTokenizer(line,",");
					int colr = 0;
					while (st.hasMoreTokens())
					{
						//get next token and store it in the array
						//numbers[row][col] = st.nextToken();
						if (colr==0){
//        				DeviceAlreadyRegisterActivity.Unit_NewItemIDArray[count] = 	st.nextToken();

						}

						if (colr==1){
//        					DeviceAlreadyRegisterActivity.Unit_UnitIDArray[count] = 	st.nextToken();
						}

						if (colr==2){
//        						DeviceAlreadyRegisterActivity.Unit_UnitArray[count] = 	st.nextToken();

						}

						if (colr==3){
//        							DeviceAlreadyRegisterActivity.Unit_UnitDescriptionArray[count] = 	st.nextToken();

						}

						if (colr==4){
//        							DeviceAlreadyRegisterActivity.AttributeFieldIDArray[count] = 	st.nextToken();

						}


						colr++;
					}
					col = 0;
					count++;
				}

			}catch (Exception ex) {
				DebugLog.tagConsole("DataFilter",ex.toString());
			}finally {
				try {
					fIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
















		@Override
		protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			txt.setText(result);
//                btn.setText("Restart");


//                DataBaseProcessor.getDataBaseProcessor(appContext).UpdateItemDictionary(null, -1);
			//DataBaseProcessor.getDataBaseProcessor(appContext).UpdateUnitDictionary(null, -1);
//                DataBaseProcessor.getDataBaseProcessor(appContext).UpdateAttributesFieldDictionary(null, -1);

//                DataBaseProcessor.getDataBaseProcessor(appContext).UpdateAttributesValueDictionary(null, -1);




//                DataBaseProcessor.getDataBaseProcessor(appContext).exportDB(appContext);
//                Intent intent = MpcUtil.buildNewIntent(appContext, DeviceAlreadyRegisterActivity.class);
//				startActivity(intent);

		}
		@Override
		protected void onPreExecute() {
			txt.setText("Task Starting...");

			long result = -1;//HouseHoldDataBaseHelper.getDataBaseProcessor(StartUpMainActivity.this.getApplicationContext()).exportCSV(StartUpMainActivity.this.getApplicationContext());
//			if (result==-1){
//				String k = null;
//				k.charAt(1);
//
//			}





			String[] fileList = appContext.fileList();
			Totalcount=fileList.length;
			progressBar.setMax(Totalcount);
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			txt.setText("Running..."+ values[0]+"/"+values[1]);
			progressBar.setProgress(values[0]);

		}
	}






	class MyTask2 extends AsyncTask<Integer, Integer, String> {


		int count = 0;
		int Totalcount = 0;
		DatabaseAdapter adapter;

		@Override
		protected String doInBackground(Integer... params) {


			try {
				adapter = new DatabaseAdapter(StartUpMainActivity.this);
				adapter.Open();
				String userName ="";
				String whereQuery = " WHERE farmer_id IN ";
				String nowhereQuery = " WHERE farmer_id NOT IN ";
				ArrayList<String> farmerIDs = new ArrayList<>();

				ArrayList<String> excude_farmerIDs = new ArrayList<>();
				DebugLog.console("[MyTask1] inside doInBackground() "+whereQuery);
				Cursor farmersCoursor = adapter.selectCompletedCalls();
				if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
					//	Toast.makeText(StartUpMainActivity.this, "No completed  exist", Toast.LENGTH_SHORT).show();

					return  StartUpMainActivity.this.getString(R.string.Already_synced);

				} else {
					DebugLog.console("[MyTask1] inside doInBackground() "+farmersCoursor.getCount());
					Totalcount=farmersCoursor.getCount();
					publishProgress(0, Totalcount);
					if (farmersCoursor.moveToFirst()) {
						do {
							String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
							if (!StringUtils.isEmpty(formerID)) {


								String userName_against_farmer_id = adapter.getUserNameagainstFarmerID(formerID);
//adding in farmerIDs.add(formerID); for upload all adding in this excude_farmerIDs.add(formerID); to exclude from deletion

								//Todo //check it later for userbase
								userName= userName_against_farmer_id;
								if (userName.equalsIgnoreCase(userName_against_farmer_id)) {
									farmerIDs.add(formerID);
								} else {

									farmerIDs.add(formerID);
									excude_farmerIDs.add(formerID);
									MubLog.cpnsoleLog("inside userName" + userName + "is not equal to " + userName_against_farmer_id);
								}

							}

						} while (farmersCoursor.moveToNext());

					}
				}
				String subQuery = "";
				for (int i = 0; i < farmerIDs.size(); i++) {
					if (i == 0) {
						subQuery = farmerIDs.get(i);
					} else if (i < farmerIDs.size()) {
						subQuery = subQuery + " , " + farmerIDs.get(i);
					} else {
						subQuery = subQuery + " , " + farmerIDs.get(i);
					}
				}


				subQuery = "( " + subQuery + " )";
				MubLog.cpnsoleLog("subquery " + subQuery);



				//int remaning = Totalcount/2;

				JSONArray trt_section_0_table = adapter.readSection_TRT_ForDataSync(DatabaseAdapter.trt_section_0_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();

				//publishProgress(0, remaning);
				JSONArray trt_section_1_table = adapter.readSection_TRT_ForDataSync(DatabaseAdapter.trt_section_1_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray trt_section_2_table = adapter.readSection_TRT_ForDataSync(DatabaseAdapter.trt_section_2_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray trt_section_3_table = adapter.readSection_TRT_ForDataSync(DatabaseAdapter.trt_section_3_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray trt_section_4_table = adapter.readSection_TRT_ForDataSync(DatabaseAdapter.trt_section_4_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();

				if (trt_section_0_table.length() > 0) {

				} else {
					Toast.makeText(StartUpMainActivity.this, "No data to sync.", Toast.LENGTH_SHORT).show();

					return "No completed  exist" ;

				}

				if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))//check if sd card is mounted
				{
					//  exportDataonSdcard(dataToUploadB, dataToUploadC, dataToUploadD, dataToUploadE, readSectionFTable1ForDataSync, readSectionFTable2ForDataSync, readSectionFTable3ForDataSync, readSectionFTable4ForDataSync, readSectionFTable5ForDataSync, readSectionFTable6ForDataSync, readSectionGForDataSync, readSectionGBForDataSync, readSectionHForDataSync, readSectionOneForDataSync);
					exportDataonSdcard(trt_section_0_table,trt_section_1_table,trt_section_2_table,trt_section_3_table,trt_section_4_table);

					//   adapter.deleteAllSectionData(userName,notinquerry);
					publishProgress(Totalcount, Totalcount);

					//txt.setText(StartUpMainActivity.this.getString(R.string.sdcard_exported_text));
					//return  "";
				} else {
					Toast.makeText(StartUpMainActivity.this, "Sdcard Export Issue", Toast.LENGTH_LONG).show();

				}

				if (RConsUtils.isNetworkAvailable(StartUpMainActivity.this)) {

					JSONObject allSectiondata = new JSONObject();
					allSectiondata.put(DatabaseAdapter.trt_section_0_table, trt_section_0_table);
					allSectiondata.put(DatabaseAdapter.trt_section_1_table, trt_section_1_table);
					allSectiondata.put(DatabaseAdapter.trt_section_2_table, trt_section_2_table);
					allSectiondata.put(DatabaseAdapter.trt_section_3_table, trt_section_3_table);
					allSectiondata.put(DatabaseAdapter.trt_section_4_table, trt_section_4_table);


					String url = "http://rconsdb.org/devteam/general/services/ffbcsr/trt_secdata.php";

					JSONObject dataUploaded = HttpsClient.sendNewHttpsPostRequest(StartUpMainActivity.this,url,allSectiondata);
					DebugLog.console("[MyTask1] inside doInBackground() "+dataUploaded.toString());

					if(dataUploaded.getString("code").equalsIgnoreCase("100")){

						adapter.UpdateSyncedData(DatabaseAdapter.trt_section_0_table,subQuery);
						adapter.UpdateSyncedData(DatabaseAdapter.trt_section_1_table,subQuery);
						adapter.UpdateSyncedData(DatabaseAdapter.trt_section_2_table,subQuery);
						adapter.UpdateSyncedData(DatabaseAdapter.trt_section_3_table,subQuery);
						adapter.UpdateSyncedData(DatabaseAdapter.trt_section_4_table,subQuery);

						return "Uploaded Successfully ";
					}else{
						return "Error in uploading. Please contact Admin ";
					}


				}else{

					return StartUpMainActivity.this.getString(R.string.conenctivity_issue);

				}


//                JSONArray dataToUploadB = adapter.readSectionBForDataSync(whereQuery + subQuery);//adapter.getSectionBData_ALL();
//                JSONArray dataToUploadC = adapter.readSectionCForDataSync(whereQuery + subQuery);//adapter.getSectionCData_ALL();
//                JSONArray dataToUploadD = adapter.readSectionDForDataSync(whereQuery + subQuery);//adapter.getSectionDData_ALL();
//                JSONArray dataToUploadE = adapter.readSectionEForDataSync(whereQuery + subQuery);//adapter.getSectionEData_ALL();
//








			} catch (Exception ex) {
				return "Error: Please connect to your manager";

			}

		}


		private void readCountFromAsset() {
			// TODO Auto-generated method stub
			InputStream fIn =  null;
			try {

				//File file = new File("Item Codes.csv");
				//BufferedReader bufRdr  = new BufferedReader(new FileReader(file));



				fIn= appContext.getResources().getAssets().open("2.csv", Context.MODE_WORLD_READABLE);

				BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(fIn));






				String line = null;


				//read each line of text file
				while((line = bufRdr.readLine()) != null )
				{

					// publishProgress(count);
					DebugLog.tagConsole("DataFilter","MainActivity"+line+"\r\nTotalcount"+Totalcount);
					Totalcount++;



				}




			}catch (Exception ex) {
				DebugLog.tagConsole("DataFilter",ex.toString());
			}finally {
				try {
					fIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		public synchronized boolean uploadFile(String uploadFileName){
			boolean uploaded = false;
			try {
				int retry = 0;
				while(retry < 2){



					uploaded = true;//HttpsClient.dataFileUpload(appContext,uploadFileName);










					if(uploaded){
						appContext.deleteFile(uploadFileName);
						//HttpsClient.sendHttpsGetRequest(appContext,"http://rconsdb.org/devteam/dataentry/process_single.php?&filename="+uploadFileName+"&dt="+MpcUtil.getcurrentTime(1111)+"&userName="+SecureTeenConfigurations.getPeshawarCurrentLoggedInUser(appContext).replaceAll(" ","_"));

						break;
					}else{
						retry++;
					}
				}
				return uploaded;
			}catch (Exception e) {
				EmailDebugLog.getInstance(appContext).writeLog(e.toString()+ "\r\n[SecureSecondService]: Exception occuered in uploadFile");
				return false;
			}
		}








		private void readFileFromAsset() {
			// TODO Auto-generated method stub
			InputStream fIn =  null;
			try {

				//File file = new File("Item Codes.csv");
				//BufferedReader bufRdr  = new BufferedReader(new FileReader(file));



				fIn= appContext.getResources().getAssets().open("2.csv", Context.MODE_WORLD_READABLE);

				BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(fIn));






				String line = null;
				int row = 0;
				int col = 0;
				count = 0;
				//read each line of text file
				while((line = bufRdr.readLine()) != null )
				{

					publishProgress(count);
					DebugLog.tagConsole("DataFilter","MainActivity"+line);

					StringTokenizer st = new StringTokenizer(line,",");
					int colr = 0;
					while (st.hasMoreTokens())
					{
						//get next token and store it in the array
						//numbers[row][col] = st.nextToken();
						if (colr==0){
							st.nextToken();
						}

						if (colr==1){
							//	DeviceAlreadyRegisterActivity.itemNamesArray[count] = 	st.nextToken();
						}

						if (colr==2){
							//	DeviceAlreadyRegisterActivity.itemCodesArray[count] = 	st.nextToken();

						}

						if (colr==3){
							//		DeviceAlreadyRegisterActivity.newItemNamesArray[count] = 	st.nextToken();

						}










						colr++;
					}
					col = 0;
					count++;
				}

			}catch (Exception ex) {
				DebugLog.tagConsole("DataFilter",ex.toString());
			}finally {
				try {
					fIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}





		private void readUnitFileFromAsset() {
			// TODO Auto-generated method stub
			InputStream fIn =  null;
			try {

				//File file = new File("Item Codes.csv");
				//BufferedReader bufRdr  = new BufferedReader(new FileReader(file));



				fIn= appContext.getResources().getAssets().open("unit.csv", Context.MODE_WORLD_READABLE);

				BufferedReader bufRdr  = new BufferedReader(new InputStreamReader(fIn));

//		       	DeviceAlreadyRegisterActivity.Unit_NewItemIDArray = new String[Totalcount];
//	        	DeviceAlreadyRegisterActivity.Unit_UnitIDArray = new String[Totalcount];
//	        	DeviceAlreadyRegisterActivity.Unit_UnitArray = new String[Totalcount];
//	        	DeviceAlreadyRegisterActivity.Unit_UnitDescriptionArray = new String[Totalcount];




				String line = null;
				int row = 0;
				int col = 0;
				count = 0;
				//read each line of text file
				while((line = bufRdr.readLine()) != null )
				{

					publishProgress(count);
					DebugLog.tagConsole("DataFilter","MainActivity"+line);

					StringTokenizer st = new StringTokenizer(line,",");
					int colr = 0;
					while (st.hasMoreTokens())
					{
						//get next token and store it in the array
						//numbers[row][col] = st.nextToken();
						if (colr==0){
//        				DeviceAlreadyRegisterActivity.Unit_NewItemIDArray[count] = 	st.nextToken();

						}

						if (colr==1){
//        					DeviceAlreadyRegisterActivity.Unit_UnitIDArray[count] = 	st.nextToken();
						}

						if (colr==2){
//        						DeviceAlreadyRegisterActivity.Unit_UnitArray[count] = 	st.nextToken();

						}

						if (colr==3){
//        							DeviceAlreadyRegisterActivity.Unit_UnitDescriptionArray[count] = 	st.nextToken();

						}

						if (colr==4){
//        							DeviceAlreadyRegisterActivity.AttributeFieldIDArray[count] = 	st.nextToken();

						}


						colr++;
					}
					col = 0;
					count++;
				}

			}catch (Exception ex) {
				DebugLog.tagConsole("DataFilter",ex.toString());
			}finally {
				try {
					fIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
















		@Override
		protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);
			txt.setText(result);
//                btn.setText("Restart");


//                DataBaseProcessor.getDataBaseProcessor(appContext).UpdateItemDictionary(null, -1);
			//DataBaseProcessor.getDataBaseProcessor(appContext).UpdateUnitDictionary(null, -1);
//                DataBaseProcessor.getDataBaseProcessor(appContext).UpdateAttributesFieldDictionary(null, -1);

//                DataBaseProcessor.getDataBaseProcessor(appContext).UpdateAttributesValueDictionary(null, -1);




//                DataBaseProcessor.getDataBaseProcessor(appContext).exportDB(appContext);                
//                Intent intent = MpcUtil.buildNewIntent(appContext, DeviceAlreadyRegisterActivity.class);
//				startActivity(intent);

		}
		@Override
		protected void onPreExecute() {
			txt.setText("Task Starting...");

			long result = -1;//HouseHoldDataBaseHelper.getDataBaseProcessor(StartUpMainActivity.this.getApplicationContext()).exportCSV(StartUpMainActivity.this.getApplicationContext());
//			if (result==-1){
//				String k = null;
//				k.charAt(1);
//
//			}





			String[] fileList = appContext.fileList();
			Totalcount=fileList.length;
			progressBar.setMax(Totalcount);
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
			txt.setText("Running..."+ values[0]+"/"+values[1]);
			progressBar.setProgress(values[0]);

		}
	}



	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}



	public void gotoPage1(View v){

		try{
			//	if(ConnectionVerifier.isInternetOn(Page2.this)){
			//DeviceRegisterActivity.callingObje =  Page2.this;
//				Intent intent = new Intent(StartUpMainActivity.this,DeviceAlreadyRegisterActivity.class);
//				startActivity(intent);
			finish();
			//	}else{
			//	showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
			//}
		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[DeviceAlreadyRegisterActivity]: Exception occured inside goToCreateNewSubUser");
		}
	}



	public void gotoPageattribute(View v){

		try{
			//	if(ConnectionVerifier.isInternetOn(Page2.this)){
			//DeviceRegisterActivity.callingObje =  Page2.this;
//				Intent intent = new Intent(StartUpMainActivity.this,AttributeTakerActivity.class);
//				startActivity(intent);
			finish();
			//	}else{
			//	showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
			//}
		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[DeviceAlreadyRegisterActivity]: Exception occured inside goToCreateNewSubUser");
		}
	}

	public void onPressingtakepic(View v){

		try{
			//	if(ConnectionVerifier.isInternetOn(Page2.this)){
			//DeviceRegisterActivity.callingObje =  Page2.this;
//				Intent intent = new Intent(StartUpMainActivity.this,PictureTakerActivity.class);
//				startActivity(intent);
			finish();
			//	}else{
			//	showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
			//}
		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[DeviceAlreadyRegisterActivity]: Exception occured inside goToCreateNewSubUser");
		}
	}


	public void importdatabase(View v){

		try{
			if(RConsUtils.isNetworkAvailable(StartUpMainActivity.this)){
				//DeviceRegisterActivity.callingObje =  Page2.this;

				progressBar.setVisibility(View.VISIBLE);
				//txt.setVisibility(View.VISIBLE);
				progressBar.setProgress(0);
				new MyTask1().execute(1);



			}else{
				showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));

			}
		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[DeviceAlreadyRegisterActivity]: Exception occured inside importdatabase");
		}
	}
	public void showAlert(String title, String message){
		new AlertDialog.Builder(StartUpMainActivity.this)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(getResources().getString(R.string.registration_screen_alert_box_btn), new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
					}
				})
				.show();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
//		Intent intent = new Intent(StartUpMainActivity.this,ParentRemoteMonitoringActivity.class);
//		startActivity(intent);
		finish();

	}

	public void onClose(View v) {

		try {
//							Intent intent = new Intent(StartUpMainActivity.this,ParentRemoteMonitoringActivity.class);
//				startActivity(intent);
			finish();

			//			if (Configurations.isExitAppWithConfirmationEnabled(getApplicationContext())){
			//	showWarningAlert(getResources().getString(R.string.main_activity_close_dialog_title_text),getResources().getString(R.string.main_activity_close_dialog_body_text));
			//}else{
			//finish();
			//}

		} catch (Exception ex) {
		}
	}

	private void exportDataonSdcard(JSONArray trt_section_0_table,JSONArray trt_section_1_table,JSONArray trt_section_2_table,JSONArray trt_section_3_table,JSONArray trt_section_4_table) {
		try {

			JSONObject allSectiondata = new JSONObject();
			try {


				allSectiondata.put(DatabaseAdapter.trt_section_0_table, trt_section_0_table);
				allSectiondata.put(DatabaseAdapter.trt_section_1_table, trt_section_1_table);
				allSectiondata.put(DatabaseAdapter.trt_section_2_table, trt_section_2_table);
				allSectiondata.put(DatabaseAdapter.trt_section_3_table, trt_section_3_table);
				allSectiondata.put(DatabaseAdapter.trt_section_4_table, trt_section_4_table);



				String fileName = com.mubashar.dateandtime.filemanager.FileManager.createFileName(StartUpMainActivity.this.getApplicationContext(), "trt_SectionData", "Rcons");


				//  File directory = new File(MainMenuActivity.this.getApplicationContext().getExternalFilesDir(null),MainMenuActivity.this.getApplicationContext().getString(R.string.app_name));
				File directory = new File(Environment.getExternalStorageDirectory() + "/Rcons/" + MubDateAndTime.INSTANCE.getcurrentTime(6));


				if (directory.isDirectory()) {

				} else {
					DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + directory.getName());
					DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + directory.getAbsolutePath());

					DebugLog.console("[MyTask1] inside exportLogFile() created " + directory.mkdir());
					DebugLog.console("[MyTask1] inside exportLogFile() created " + directory.mkdirs());
					DebugLog.console("[MyTask1] inside exportLogFile() getPath " + directory.getPath());
				}
				File newFile = new File(directory.getPath() + "/" + fileName);
				MubLog.cpnsoleLog("newFile " + newFile.toString());
				MubLog.cpnsoleLog("fileName " + fileName);
				FileOutputStream fos = new FileOutputStream(newFile, true);
				fos.write(allSectiondata.toString().getBytes("UTF-8"));
				fos.close();
				Toast.makeText(StartUpMainActivity.this, "Data Exported at " + newFile.getPath(), Toast.LENGTH_SHORT).show();


				// boolean created =  com.mubashar.dateandtime.filemanager.FileManager.createNewFileOnSdCarddownloads(MainMenuActivity.this.getApplicationContext(),backupDB.toString(),allSectiondata.toString());
				//   MubLog.cpnsoleLog("created "+created);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			EmailDebugLog.getInstance(StartUpMainActivity.this).writeLog("[MainMenuActivity] inside exportDataonSdcard() Exception is :" + e.toString());
		}
	}



}
