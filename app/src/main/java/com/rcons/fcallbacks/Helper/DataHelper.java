package com.rcons.fcallbacks.Helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.EmailDebugLog;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.Utilties.MubLog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DataHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "PhoneQuestionnaire.db";
    private SQLiteDatabase myDataBase;
    private Context context;
    private String DB_PATH = "";

    public DataHelper(Context context) {
        super(context, DB_NAME, null, BuildConfig.VERSION_CODE);
        this.context = context;
        DB_PATH = "data/data/" + this.context.getPackageName() + "/databases/";
        boolean dbExist = checkDatabase();
        if (dbExist) {
            openDatabase();
        } else {
            Log.d("DataHelper", "Database doesn't exist");
            try {
                createDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDatabase() throws IOException {
        boolean dbExist = checkDatabase();
        if (dbExist) {
        } else {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabase();
            } catch (IOException e) {

            }
        }
    }

    public boolean checkDatabase() {
        boolean checkDb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbFile = new File(myPath);
            checkDb = dbFile.exists();
        } catch (SQLiteException e) {
            Log.d("DataHelper", "Database doesn't exist");
        }
        return checkDb;
    }

    private void copyDatabase() throws IOException {
        InputStream inputStream = context.getAssets().open(DB_NAME);
        Log.d("DataHelper", "========myInput===========" + inputStream);
        OutputStream outputStream = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            Log.d("DataHelper", "========outputStream===========" + outputStream);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public synchronized void openDatabase() throws SQLException {
        String databasePath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        try {


            String querry = "ALTER TABLE "+DatabaseAdapter.pq_section_e_table+" ADD COLUMN e6_other TEXT DEFAULT ''";
            checkAndCreatecolumn(db,querry);
            querry = "ALTER TABLE "+DatabaseAdapter.pq_section_c3_table+" ADD COLUMN refused TEXT DEFAULT ''";
            checkAndCreatecolumn(db,querry);

            querry = "ALTER TABLE "+DatabaseAdapter.pq_section_e_table+" ADD COLUMN refused TEXT DEFAULT ''";
            checkAndCreatecolumn(db,querry);

        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[DataHelper] inside onUpgrade() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside exception onCreate database"+e.toString());
        }
    }

    private void checkAndCreatecolumn(SQLiteDatabase db,String querry) {
        try {


            DebugLog.console("[DataHelper] inside checkAndCreatecolumn() querry "+querry);
            db.execSQL(querry);


        } catch (Exception e) {

            EmailDebugLog.getInstance(context).writeLog("[DataHelper] inside checkAndCreatecolumn() Exception is :"+e.toString());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        try {
            MubLog.cpnsoleLog("inside onUpgrade");
            if (newVersion > oldVersion) {


                String end_day = "ALTER TABLE `"+DatabaseAdapter.aghhid_title_table+"` ADD `end_day` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,end_day);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() end_day"+end_day);

                String end_month = "ALTER TABLE `"+DatabaseAdapter.aghhid_title_table+"` ADD `end_month` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,end_month);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() end_month"+end_month);

                String end_year = "ALTER TABLE `"+DatabaseAdapter.aghhid_title_table+"` ADD `end_year` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,end_year);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() end_month"+end_year);

                String end_hh = "ALTER TABLE `"+DatabaseAdapter.aghhid_title_table+"` ADD `end_hh` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,end_hh);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() end_month"+end_hh);

                String end_mm = "ALTER TABLE `"+DatabaseAdapter.aghhid_title_table+"` ADD `end_mm` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,end_mm);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() end_month"+end_mm);


                String comment_add = "ALTER TABLE `"+DatabaseAdapter.aghhid_section_m_table+"` ADD `comments` TEXT DEFAULT '' ";
                db.execSQL(comment_add);
                checkAndCreatecolumn(db,comment_add);

                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() comment_add"+comment_add);

                comment_add = "ALTER TABLE `"+DatabaseAdapter.aghhid_section_ad_m_table+"` ADD `comments` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,comment_add);


            }
        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[DataHelper] inside onUpgrade() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside exception onUpgrade database"+e.toString());
        }

    }





}
