package com.rcons.fcallbacks.Utilties;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.rcons.fcallbacks.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class SyncMainActivity extends Activity {


	Button btn;
	private ProgressBar progressBar;
	TextView txt;
	Integer count =1;
	Context appContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.syncmain);

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
				adapter = new DatabaseAdapter(SyncMainActivity.this);
				adapter.Open();
				String userName =RConsUtils.getUserName();


				String whereQuery = " WHERE student_id IN ";


				String nowhereQuery = " WHERE id NOT IN ";
				//ArrayList<String> IDsToUpload = new ArrayList<>();
				ArrayList<String> schoolCodeIDsToUpload = new ArrayList<>();
				ArrayList<String> StudentCodeIDsToUpload = new ArrayList<>();

				ArrayList<String> excude_farmerIDs = new ArrayList<>();
				DebugLog.console("[MyTask1] inside doInBackground() "+whereQuery);
				Cursor farmersCoursor =    adapter.aghhid_selectCompletedCalls(userName);
				if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
					//	Toast.makeText(StartUpMainActivity.this, "No completed  exist", Toast.LENGTH_SHORT).show();

					return  SyncMainActivity.this.getString(R.string.Already_synced);

				} else {
					DebugLog.console("[MyTask1] inside doInBackground() "+farmersCoursor.getCount());
					Totalcount=farmersCoursor.getCount();
					publishProgress(0, Totalcount);
					if (farmersCoursor.moveToFirst()) {
						do {
							//String id = farmersCoursor.getString(farmersCoursor.getColumnIndex("id"));
							String scode = farmersCoursor.getString(farmersCoursor.getColumnIndex("village_id"));
							String studentid = farmersCoursor.getString(farmersCoursor.getColumnIndex("hhid"));
							if (!StringUtils.isEmpty(scode)) {


								String userName_against_farmer_id = RConsUtils.getUserName();//adapter.getUserNameagainstFarmerID(id);
//adding in farmerIDs.add(formerID); for upload all adding in this excude_farmerIDs.add(formerID); to exclude from deletion

								//Todo //check it later for userbase
								userName= userName_against_farmer_id;
								if (userName.equalsIgnoreCase(userName_against_farmer_id)) {
									//IDsToUpload.add(id);
									schoolCodeIDsToUpload.add(scode);
									StudentCodeIDsToUpload.add(studentid);
								} else {

//									IDsToUpload.add(id);
//									excude_farmerIDs.add(id);
									MubLog.cpnsoleLog("inside userName" + userName + "is not equal to " + userName_against_farmer_id);
								}

							}

						} while (farmersCoursor.moveToNext());

					}
				}
				String subQuery = "";
				String schoolCodeSubQuery = "";
				for (int i = 0; i < StudentCodeIDsToUpload.size(); i++) {
					if (i == 0) {
						subQuery = StudentCodeIDsToUpload.get(i);
						schoolCodeSubQuery = schoolCodeIDsToUpload.get(i);
					} else if (i < StudentCodeIDsToUpload.size()) {
						subQuery = subQuery + " , " + StudentCodeIDsToUpload.get(i);
						schoolCodeSubQuery = schoolCodeSubQuery + " , " + schoolCodeIDsToUpload.get(i);
					} else {
						subQuery = subQuery + " , " + StudentCodeIDsToUpload.get(i);
						schoolCodeSubQuery = schoolCodeSubQuery + " , " + schoolCodeIDsToUpload.get(i);
					}
				}


				subQuery = "( " + subQuery + " )";
				schoolCodeSubQuery = "( " + schoolCodeSubQuery + " )";
				MubLog.cpnsoleLog("subquery  hhid" + subQuery);
				MubLog.cpnsoleLog("subquery village_id " + schoolCodeSubQuery);


if(farmersCoursor!=null){
	if(!farmersCoursor.isClosed()){
		farmersCoursor.close();
	}

}
				//int remaning = Totalcount/2;

				JSONArray pq_section_a_table = adapter.baseline_readSection_pq(DatabaseAdapter.pq_section_a_table,whereQuery + subQuery + " AND village_id IN "+schoolCodeSubQuery);//adapter.getSectionBData_ALL();
				JSONArray pq_section_a2_table = adapter.baseline_readSection_pq(DatabaseAdapter.pq_section_a2_table,whereQuery + subQuery + " AND village_id IN "+schoolCodeSubQuery);//adapter.getSectionBData_ALL();
				JSONArray pq_section_a3_table = adapter.baseline_readSection_pq(DatabaseAdapter.pq_section_a3_table,whereQuery + subQuery + " AND village_id IN "+schoolCodeSubQuery);//adapter.getSectionBData_ALL();
				JSONArray pq_section_a4_table = adapter.baseline_readSection_pq(DatabaseAdapter.pq_section_a4_table,whereQuery + subQuery + " AND village_id IN "+schoolCodeSubQuery);//adapter.getSectionBData_ALL();



				JSONArray aghhid_section_c_table = adapter.baseline_readSection_pq(DatabaseAdapter.aghhid_section_c_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray aghhid_section_d_table = adapter.baseline_readSection_pq(DatabaseAdapter.aghhid_section_d_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray aghhid_section_e_table = adapter.baseline_readSection_pq(DatabaseAdapter.aghhid_section_e_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray aghhid_section_f_table = adapter.baseline_readSection_pq(DatabaseAdapter.aghhid_section_f_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();


				JSONArray aghhid_section_g_table = adapter.baseline_readSection_pq(DatabaseAdapter.aghhid_section_g_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();
				JSONArray aghhid_section_h_table = adapter.baseline_readSection_pq(DatabaseAdapter.aghhid_section_h_table,whereQuery + subQuery);//adapter.getSectionBData_ALL();


				JSONArray AGHHID_SampleTable =adapter.aghhid_readReport(userName);//adapter.getSectionBData_ALL();

				if (AGHHID_SampleTable.length() > 0) {

				} else {


					return "No completed  exist" ;

				}

				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))//check if sd card is mounted
				{
					//  exportDataonSdcard(dataToUploadB, dataToUploadC, dataToUploadD, dataToUploadE, readSectionFTable1ForDataSync, readSectionFTable2ForDataSync, readSectionFTable3ForDataSync, readSectionFTable4ForDataSync, readSectionFTable5ForDataSync, readSectionFTable6ForDataSync, readSectionGForDataSync, readSectionGBForDataSync, readSectionHForDataSync, readSectionOneForDataSync);
					exportDataonSdcard(pq_section_a_table,pq_section_a2_table,pq_section_a3_table,pq_section_a4_table,aghhid_section_c_table,aghhid_section_d_table,aghhid_section_e_table,aghhid_section_f_table,aghhid_section_g_table,aghhid_section_h_table,AGHHID_SampleTable);

					//   adapter.deleteAllSectionData(userName,notinquerry);
					publishProgress(Totalcount, Totalcount);

					//txt.setText(StartUpMainActivity.this.getString(R.string.sdcard_exported_text));
					//return  "";
				} else {
					Toast.makeText(SyncMainActivity.this, "Sdcard Export Issue", Toast.LENGTH_LONG).show();

				}

				if (RConsUtils.isNetworkAvailable(SyncMainActivity.this)) {

					JSONObject allSectiondata = new JSONObject();
					allSectiondata.put(DatabaseAdapter.pq_section_a_table, pq_section_a_table);
					allSectiondata.put(DatabaseAdapter.pq_section_a2_table, pq_section_a2_table);
					allSectiondata.put(DatabaseAdapter.pq_section_a3_table, pq_section_a3_table);
					allSectiondata.put(DatabaseAdapter.pq_section_a4_table, pq_section_a4_table);

					allSectiondata.put(DatabaseAdapter.aghhid_section_c_table, aghhid_section_c_table);
					allSectiondata.put(DatabaseAdapter.aghhid_section_d_table, aghhid_section_d_table);
					allSectiondata.put(DatabaseAdapter.aghhid_section_e_table, aghhid_section_e_table);


					allSectiondata.put(DatabaseAdapter.aghhid_section_f_table, aghhid_section_f_table);
					allSectiondata.put(DatabaseAdapter.aghhid_section_g_table, aghhid_section_g_table);
					allSectiondata.put(DatabaseAdapter.aghhid_section_h_table, aghhid_section_h_table);
					allSectiondata.put(DatabaseAdapter.AGHHID_SampleTable, AGHHID_SampleTable);

					String url = "http://rconsdb.org/devteam/general/services/ffbcsr/bbaseline_secdata.php";

					JSONObject dataUploaded = HttpsClient.sendNewHttpsPostRequest(SyncMainActivity.this,url,allSectiondata);
					DebugLog.console("[MyTask1] inside doInBackground() "+dataUploaded.toString());

					if(dataUploaded.getString("code").equalsIgnoreCase("100")){



//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_a_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_b_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_c1_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_c2_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_c3_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_d_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//						adapter.baseline_UpdateSyncedData(DatabaseAdapter.pq_section_e_table, subQuery+ " AND school_code IN "+schoolCodeSubQuery);
//
//
//
//						adapter.baseline_UpdateReportData(DatabaseAdapter.BaseLineSampleTable, subQuery+ " AND scode IN "+schoolCodeSubQuery);


						return "Uploaded Successfully ";
					}else{
						return "Error in uploading. Please contact Admin ";
					}


				}else{

					return SyncMainActivity.this.getString(R.string.conenctivity_issue);

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
//			if(RConsUtils.isNetworkAvailable(SyncMainActivity.this)){
				//DeviceRegisterActivity.callingObje =  Page2.this;

				progressBar.setVisibility(View.VISIBLE);
				//txt.setVisibility(View.VISIBLE);
				progressBar.setProgress(0);
				new MyTask1().execute(1);



//			}else{
//				showAlert(getResources().getString(R.string.connection_error_message_title),getResources().getString(R.string.connection_error_message));
//
//			}
		}catch (Exception e) {
			EmailDebugLog.getInstance(appContext).writeLog( e.toString()+"\r\n[DeviceAlreadyRegisterActivity]: Exception occured inside importdatabase");
		}
	}
	public void showAlert(String title, String message){
		new AlertDialog.Builder(SyncMainActivity.this)
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

	private void exportDataonSdcard(JSONArray pq_section_a_table,JSONArray pq_section_a2_table,JSONArray pq_section_a3_table,JSONArray pq_section_a4_table ,JSONArray aghhid_section_c_table, JSONArray aghhid_section_d_table, JSONArray aghhid_section_e_table, JSONArray aghhid_section_f_table, JSONArray aghhid_section_g_table, JSONArray aghhid_section_h_table, JSONArray baseLineSampleTable) {
		try {

			JSONObject allSectiondata = new JSONObject();
			try {


				allSectiondata.put(DatabaseAdapter.pq_section_a_table, pq_section_a_table);
				allSectiondata.put(DatabaseAdapter.pq_section_a2_table, pq_section_a2_table);
				allSectiondata.put(DatabaseAdapter.pq_section_a3_table, pq_section_a3_table);
				allSectiondata.put(DatabaseAdapter.pq_section_a4_table, pq_section_a4_table);

				allSectiondata.put(DatabaseAdapter.aghhid_section_c_table, aghhid_section_c_table);
				allSectiondata.put(DatabaseAdapter.aghhid_section_d_table, aghhid_section_d_table);
				allSectiondata.put(DatabaseAdapter.aghhid_section_e_table, aghhid_section_e_table);
				allSectiondata.put(DatabaseAdapter.aghhid_section_f_table, aghhid_section_f_table);
				allSectiondata.put(DatabaseAdapter.aghhid_section_g_table, aghhid_section_g_table);
				allSectiondata.put(DatabaseAdapter.aghhid_section_h_table, aghhid_section_h_table);
				allSectiondata.put(DatabaseAdapter.BaseLineSampleTable, baseLineSampleTable);



				String fileName = com.mubashar.dateandtime.filemanager.FileManager.createFileName(SyncMainActivity.this.getApplicationContext(), "baseline_Section_and_Report", "Rcons");


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
//				Toast.makeText(SyncMainActivity.this, "Data Exported at " + newFile.getPath(), Toast.LENGTH_SHORT).show();


				// boolean created =  com.mubashar.dateandtime.filemanager.FileManager.createNewFileOnSdCarddownloads(MainMenuActivity.this.getApplicationContext(),backupDB.toString(),allSectiondata.toString());
				//   MubLog.cpnsoleLog("created "+created);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			EmailDebugLog.getInstance(SyncMainActivity.this).writeLog("[MainMenuActivity] inside exportDataonSdcard() Exception is :" + e.toString());
		}
	}



}
