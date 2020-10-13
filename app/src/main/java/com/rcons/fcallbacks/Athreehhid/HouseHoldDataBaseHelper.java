package com.rcons.fcallbacks.Athreehhid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.filemanager.FileManager;

import com.opencsv.CSVWriter;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.EmailDebugLog;

import com.rcons.fcallbacks.HHIDConfigurations;
import com.rcons.fcallbacks.Helper.DatabaseAdapter;
import com.rcons.fcallbacks.Model.HouseMemberModel;
import com.rcons.fcallbacks.StructureUtil;
import com.rcons.fcallbacks.Utilties.AppController;
import com.rcons.fcallbacks.Utilties.MpcUtil;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by  on 11/16/2016.
 */
public class HouseHoldDataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "KOKAKOLA";
    private Context mContext;
    private static HouseHoldDataBaseHelper databaseProcessor;
    private SQLiteDatabase db;
    private int dbOpenCounter = 0;

    private static String DB_NAME = "PhoneQuestionnaire.db";
    private static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";

    public HouseHoldDataBaseHelper(Context context) {
        super(context, DB_NAME, null, BuildConfig.VERSION_CODE);
        mContext = context;

//        boolean dbexist = checkdatabase();
//        if (dbexist) {
//
//            DebugLog.tagConsole(TAG, "Database exists");
//            //opendatabase();
//        } else {
//
//            DebugLog.tagConsole(TAG, "Database doesn't exist");
//            createdatabase();
//        }
    }

    public void createdatabase() {

        try {

            boolean dbexist = checkdatabase();
            if (dbexist) {

                DebugLog.tagConsole(TAG, "Database exists.");
            } else {
                this.getReadableDatabase();

                SQLiteDatabase db = this.getReadableDatabase();
                if (db.isOpen()){
                    db.close();
                }


                try {
                    copydatabase();
                } catch (Exception e) {
                    throw new Error("Error copying database");
                }
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside createdatabase() Exception is : " + e.toString());
        }

    }

    private void copydatabase() {
        //Open your local db as the input stream
        InputStream myinput = null;
        OutputStream myoutput = null;

        try {
            myinput = mContext.getAssets().open(DB_NAME);

            // Path to the just created empty db
            String outfilename = DB_PATH + DB_NAME;

            //Open the empty db as the output stream
            myoutput = new FileOutputStream(outfilename);

            // transfer byte to inputfile to outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myinput.read(buffer)) > 0) {
                myoutput.write(buffer, 0, length);
            }

            //Close the streams
            if (myinput != null) {
                myinput.close();
            }
            if (myoutput != null) {
                myoutput.flush();
                myoutput.close();
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside copydatabase() Exception is : " + e.toString());

        } finally {
            try {
                if (myinput != null) {

                    myinput.close();
                }
                if (myoutput != null) {

                    myoutput.flush();
                    myoutput.close();
                }
            } catch (Exception e) {

            }
        }

    }


    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch (SQLiteException e) {
            DebugLog.tagConsole(TAG, "Database doesn't exist.");
        }
        return checkdb;
    }

    public static HouseHoldDataBaseHelper getDataBaseProcessor(Context appContext) {
        if (databaseProcessor == null) {
            databaseProcessor = new HouseHoldDataBaseHelper(appContext);
        }
        return databaseProcessor;
    }

    private synchronized void openDB() {
        try {
            dbOpenCounter++;
            DebugLog.console("[DatabaseProcessor] inside openDB: " + dbOpenCounter);
            if (db == null || !db.isOpen()) {
                String mypath = DB_PATH + DB_NAME;
                db = this.getWritableDatabase();
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog(e.getMessage() + "[DatabaseProcessor]: exception inside openDB() Exception is : " + e.toString());
        }
    }

    private synchronized void closeDB() {
        try {
            DebugLog.console("[DatabaseProcessor] dbOpenCounter: " + dbOpenCounter);
            dbOpenCounter--;
            if (dbOpenCounter <= 0 && db != null && db.isOpen()) {
                DebugLog.console("[DatabaseProcessor] Inside check to close: ");
                dbOpenCounter = 0;
                db.close();
                this.close();
            }
        } catch (Exception ex) {
            EmailDebugLog.getInstance(mContext).writeLog("FALCO" + ex.toString() + "[DatabaseProcessor]: exception inside closeDB");
        }
    }

    private synchronized void closeDB(Cursor cursor) {
        try {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
                cursor = null;
            } else if (cursor != null) {
                cursor = null;
            }
            if (--dbOpenCounter <= 0 && db != null && db.isOpen()) {
                db.close();
                this.close();
                dbOpenCounter = 0;
            }
        } catch (Exception ex) {
            EmailDebugLog.getInstance(mContext).writeLog("FALCO" + ex.toString() + "[DataBaseProcessor]: Eception occurred in closeDB");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            String CREATE_TABLE_HHID_SURVEY = "CREATE TABLE IF NOT EXISTS " + MpcUtil.HHID_Survey + "(" + "id integer primary key autoincrement,  PSU_code text,PSU_name text,Structure_id text,HH_id text,hhid_q1 text,hhid_q2 text,hhid_q3 text,hhid_q4 text,hhid_q4_a text,hhid_q5 text,hhid_q7 text,hhid_q8 text,hhid_q9 text,hhid_q10 text,hhid_q11 text,hhid_q12 text,hhid_q13_h text,hhid_q13_m text, isSynced text, Comments text, user_name text, start_date_time text , end_date_time text, uploaded_time text, insert_or_updated_in_phone_at text ,build_no text, device_id text);";
            db.execSQL(CREATE_TABLE_HHID_SURVEY);
            DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() CREATE_TABLE_HHID_SURVEY"+CREATE_TABLE_HHID_SURVEY);

            String CREATE_TABLE_HHID_PHONE_LIST = "CREATE TABLE IF NOT EXISTS " + MpcUtil.HHID_Survey_PHONE_LIST + "(" + "id integer primary key autoincrement, PSU_code text,HH_id text,hhid_phone_number text,status text, user_name text, insert_or_updated_in_phone_at text ,build_no text,  device_id text);";
            db.execSQL(CREATE_TABLE_HHID_PHONE_LIST);
            DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() CREATE_TABLE_HHID_SURVEY"+CREATE_TABLE_HHID_PHONE_LIST);

            String CREATE_TABLE_HHID_SURVEY_DETAILS = "CREATE TABLE IF NOT EXISTS " + MpcUtil.HHID_survey_details + "(" + "id integer primary key autoincrement, PSU_code text,total_hhid_covered text,eligible_hhid text,user_name text, insert_or_updated_in_phone_at text ,build_no text,  device_id text);";
            db.execSQL(CREATE_TABLE_HHID_SURVEY_DETAILS);
            DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() HHID_survey_details"+CREATE_TABLE_HHID_SURVEY_DETAILS);



        } catch (Exception e) {
            DebugLog.console("[DataBaseProcessor] inside onCreate() Exception is : " + e.toString());
            EmailDebugLog.getInstance(mContext).writeLog("[DataBaseProcessor] inside onCreate() Exception is : " + e.toString());
        } finally {
            closeDB();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {


            if (newVersion > oldVersion){
                DebugLog.console("[HouseHoldDataBaseHelper] inside onUpgrade() oldVersion"+oldVersion);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onUpgrade() newVersion"+newVersion);

//                String enum_code = "ALTER TABLE `" + MpcUtil.HHID_Survey + "` ADD `enum_code` TEXT DEFAULT '' ";
//
//                createMissingTable();

              //    String querry = create_pending_adolescent();



                String comment_add = "ALTER TABLE `"+DatabaseAdapter.aghhid_section_m_table+"` ADD `comments` TEXT DEFAULT '' ";
                db.execSQL(comment_add);
                checkAndCreatecolumn(db,comment_add);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() comment_add"+comment_add);

                comment_add = "ALTER TABLE `"+DatabaseAdapter.aghhid_section_ad_m_table+"` ADD `comments` TEXT DEFAULT '' ";
                checkAndCreatecolumn(db,comment_add);
                DebugLog.console("[HouseHoldDataBaseHelper] inside onCreate() comment_add"+comment_add);


                DebugLog.console("[HouseHoldDataBaseHelper] inside onUpgrade() going to export database");
                exportDB(AppController.getInstance());
                DebugLog.console("[HouseHoldDataBaseHelper] inside onUpgrade() going to export database done");

            }

            // dropTable(MpcUtil.HHID_Survey);
        } catch (Exception e) {
            EmailDebugLog.getInstance(AppController.getInstance()).writeLog("[HouseHoldDataBaseHelper] inside onUpgrade() Exception is :"+e.toString());
        }
    }

    private void create_pending_adolescent() {

        try {


            String querry = "INSERT INTO pending_adolescent(village_id, hhid, hh_status, adolescent_id, adolescent_name)\n" +
                    "VALUES\n" +
                    "(\n" +
                    "797, 111, 1, 3, 'REHANA'\n" +
                    "),\n" +
                    "(\n" +
                    "798, 30, 1, 7, 'MARYAM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "798, 103, 1, 4, 'NISHAT SHABER'\n" +
                    "),\n" +
                    "(\n" +
                    "798, 123, 1, 5, 'SHEZA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "798, 153, 1, 6, 'ANILA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "798, 162, 1, 8, 'NAGEEN BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "800, 86, 1, 4, 'ALEENA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "800, 185, 1, 4, 'MARIYA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "803, 141, 1, 3, 'AREEBA BATOOL'\n" +
                    "),\n" +
                    "(\n" +
                    "803, 203, 1, 3, 'RIDA BTOOL'\n" +
                    "),\n" +
                    "(\n" +
                    "803, 224, 1, 9, 'SHAHNAZ'\n" +
                    "),\n" +
                    "(\n" +
                    "803, 304, 1, 3, 'HUMAIRA'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 1, 1, 3, 'TEHMINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 5, 1, 2, 'NAGEENA BATOOL'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 20, 1, 3, 'SHAZMO MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 23, 1, 3, 'UMMY SALMA'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 26, 1, 3, 'RIMSHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 41, 1, 3, 'SAMIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 76, 1, 3, 'AFEERA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 77, 1, 3, 'NAYAB BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 102, 1, 3, 'SADAF BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 108, 1, 6, 'NAFEESA SAJJAD'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 127, 1, 3, 'ASMA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 130, 1, 6, 'SHAKEELA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 135, 1, 5, 'ANEEQA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 139, 1, 4, 'TAHIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 142, 1, 5, 'GURIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 183, 1, 4, 'MAHNOOR BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "804, 185, 1, 4, 'AREESHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "807, 143, 1, 3, 'RASHDA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "807, 157, 1, 5, 'HASEENA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "818, 17, 1, 6, 'SANIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "818, 46, 1, 3, 'SAMRENA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "818, 85, 1, 9, 'RIFFAT BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 20, 1, 4, 'RABIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 29, 1, 8, 'AFSANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 40, 1, 7, 'MISBAH MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 52, 1, 4, 'RUKHSANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 53, 1, 7, 'SHABANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 60, 1, 4, 'ANISHA AJMAL'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 148, 1, 4, 'KIRAN BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 151, 1, 8, 'SUMAIRA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 206, 1, 6, 'AQILA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 219, 1, 5, 'KOUSAR'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 222, 1, 6, 'RABIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 247, 1, 8, 'KALSOOM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 260, 1, 3, 'FIZA BATOOL'\n" +
                    "),\n" +
                    "(\n" +
                    "827, 262, 1, 4, 'SUMAIRA'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 19, 1, 5, 'SONIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 42, 1, 6, 'ALIZA'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 46, 1, 3, 'UZMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 85, 1, 5, 'SHADO MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 130, 1, 7, 'ZUBADA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 170, 1, 4, 'TAHREEM'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 174, 1, 11, 'NADIA'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 191, 1, 3, 'MUMNA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 229, 1, 5, 'LAL MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 237, 1, 3, 'ABIDA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 238, 1, 3, 'SHUMILA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 243, 1, 4, 'NASREEM MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 245, 1, 4, 'SANIA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "830, 272, 1, 3, 'KOSAR MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "846, 92, 1, 4, 'ASIMA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "847, 107, 1, 4, 'KOMAL BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "847, 163, 1, 5, 'SIDRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 39, 1, 5, 'ABIDA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 52, 1, 3, 'ZAHRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 56, 1, 5, 'DAUGTER'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 57, 1, 8, 'RUKHSANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 74, 1, 4, 'SIDRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 84, 1, 4, 'TANZEELA'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 89, 1, 6, 'ISHRET FATIMA'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 129, 1, 5, 'JAWERIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 141, 1, 3, 'MISBA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 191, 1, 2, 'SHABANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 235, 1, 3, 'KOUSER BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 256, 1, 7, 'NASREEN BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "850, 316, 1, 3, 'ZAHIDA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "856, 6, 1, 6, 'MAWISH MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "856, 69, 1, 3, 'SARA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "856, 83, 1, 8, 'NADIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "856, 124, 1, 8, 'FATIMA'\n" +
                    "),\n" +
                    "(\n" +
                    "857, 38, 1, 3, 'ESHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "858, 36, 1, 4, 'ASLA KHALIL'\n" +
                    "),\n" +
                    "(\n" +
                    "858, 61, 1, 3, 'MISBHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "858, 265, 1, 3, 'ALIZA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 4, 1, 3, 'SALEENA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 12, 1, 3, 'KIRAN SHABEER'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 13, 1, 3, 'AYSHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 37, 1, 9, 'ASMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 100, 1, 9, 'FARHANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 116, 1, 5, 'ASMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 174, 1, 4, 'SUMAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "866, 186, 1, 3, 'NADIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "871, 71, 1, 6, 'PARWEEN MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "871, 73, 1, 3, 'REHANA'\n" +
                    "),\n" +
                    "(\n" +
                    "871, 104, 1, 5, 'NAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "871, 120, 1, 4, 'SUMAIRA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "876, 34, 1, 3, 'HUMAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "876, 51, 1, 4, 'MISBAH'\n" +
                    "),\n" +
                    "(\n" +
                    "876, 59, 1, 3, 'UZWA SAJJAD'\n" +
                    "),\n" +
                    "(\n" +
                    "878, 24, 1, 3, 'MOMAL MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "878, 117, 1, 4, 'SUMERA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "878, 129, 1, 3, 'NOREEN MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "878, 130, 1, 3, 'ASMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "878, 155, 1, 3, 'SHAZIA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 2, 1, 4, 'FOUZIA'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 11, 1, 3, 'AMINA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 25, 1, 3, 'SAFIA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 64, 1, 5, 'BEBUL'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 119, 1, 6, 'SADIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 124, 1, 7, 'UZMA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 154, 1, 6, 'SALMA'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 157, 1, 6, 'SHEHREEN'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 170, 1, 3, 'TAHIRA'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 177, 1, 4, 'RABIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 185, 1, 14, 'SHAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 211, 1, 5, 'ZAHRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 217, 1, 3, 'SHAHNAZ'\n" +
                    "),\n" +
                    "(\n" +
                    "879, 220, 1, 4, 'IQRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "882, 19, 1, 9, 'FAKHRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "882, 81, 1, 8, 'RUBINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "882, 117, 1, 4, 'ASIYA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 40, 1, 10, 'ASFA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 73, 1, 8, 'NOREEN BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 77, 1, 4, 'FOZIA'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 95, 1, 9, 'SAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 106, 1, 5, 'ZOYA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 115, 1, 6, 'SHUMAILA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 118, 1, 4, 'SHABAN A BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 120, 1, 6, 'TANIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 122, 1, 3, 'RABIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 125, 1, 3, 'SHAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 133, 1, 4, 'TAHIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 136, 1, 12, 'LAILA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 137, 1, 8, 'MEHWISH BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 148, 1, 6, 'RIMSHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "891, 151, 1, 11, 'SADIA FIDA'\n" +
                    "),\n" +
                    "(\n" +
                    "912, 19, 1, 3, 'HIFZA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "912, 26, 1, 3, 'RABIA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "934, 14, 1, 3, 'SHAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "934, 30, 1, 3, 'SHABANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "934, 104, 1, 7, 'SHAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "934, 132, 1, 3, 'GULL JUMA'\n" +
                    "),\n" +
                    "(\n" +
                    "943, 95, 1, 3, 'FARZANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "955, 114, 1, 3, 'IRUM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1124, 24, 1, 6, 'SADIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1124, 48, 1, 3, 'NADIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1124, 68, 1, 4, 'SUMAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1124, 129, 1, 3, 'SURAIYA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1124, 140, 1, 4, 'SADIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1124, 145, 1, 5, 'MAQADAS BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1131, 11, 1, 4, 'SHAHANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1131, 16, 1, 4, 'KIRAN BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1131, 21, 1, 4, 'EYMAN'\n" +
                    "),\n" +
                    "(\n" +
                    "1131, 35, 1, 3, 'ROBINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1131, 84, 1, 4, 'SIDRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1131, 155, 1, 4, 'BUSHRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 20, 1, 4, 'ALISHBA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 23, 1, 4, 'KANWAL BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 72, 1, 5, 'SAIMA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 88, 1, 3, 'MEHREEN'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 96, 1, 5, 'FAZEELA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 107, 1, 6, 'SABA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 139, 1, 5, 'THAMINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 155, 1, 3, 'AFSANA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1277, 160, 1, 4, 'SHAHIDA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1279, 5, 1, 4, 'BEBO MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1279, 47, 1, 3, 'HUSNA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1279, 66, 1, 3, 'TEHMINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1279, 70, 1, 3, 'SHAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 7, 1, 3, 'MISBAH BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 8, 1, 4, 'IQRA SHAFI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 39, 1, 7, 'GURYA RANI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 50, 1, 3, 'SHAMEELA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 75, 1, 4, 'ZUBIDA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 147, 1, 3, 'MISBAH NAZIR'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 236, 1, 4, 'HUSSNA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1317, 264, 1, 3, 'SADIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1397, 5, 1, 3, 'SOFIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1410, 93, 1, 5, 'BILKEES BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1410, 130, 1, 4, 'SAMINA'\n" +
                    "),\n" +
                    "(\n" +
                    "1410, 174, 1, 3, 'IQRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1410, 182, 1, 3, 'HASEENA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 2, 1, 3, 'ASMS BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 6, 1, 5, 'MEHNAZ MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 38, 1, 5, 'ABIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 81, 1, 4, 'NAHEED MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 83, 1, 3, 'MUMTAZ MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 84, 1, 5, 'SOBIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 85, 1, 6, 'ARSHAD MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 115, 1, 7, 'MISBAH'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 130, 1, 5, 'TAHIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 131, 1, 4, 'SOBIA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 134, 1, 3, 'KALSOOM MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1436, 147, 1, 2, 'SAIMA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1441, 87, 1, 4, 'SHAHEENA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 14, 1, 5, 'BUSHRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 27, 1, 5, 'SOBIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 30, 1, 4, 'BUSHRA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 52, 1, 5, 'SHAISTA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 67, 1, 3, 'MARIAM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 98, 1, 4, 'SUMAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 106, 1, 6, 'IRAM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 120, 1, 3, 'SALMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 132, 1, 5, 'ASMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 157, 1, 3, 'NAJMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 181, 1, 6, 'ZAKIA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1475, 197, 1, 3, 'SUMAIRA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 3, 1, 4, 'SAMINA MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 32, 1, 5, 'NAZIA'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 47, 1, 7, 'NUSRAT'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 56, 1, 5, 'AFSHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 68, 1, 3, 'SAMREEN MAY'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 82, 1, 21, 'FOZIA'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 113, 1, 4, 'KHALDA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 115, 1, 5, 'NAGINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 122, 1, 6, 'WAISA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 126, 1, 3, 'KALSOOM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 131, 1, 3, 'ZULAKHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 140, 1, 6, 'ZATOON BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 146, 1, 8, 'SAHNAZ BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 154, 1, 4, 'SHAZIA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 166, 1, 3, 'TAHREEM BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1484, 197, 1, 5, 'SAMIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1497, 47, 1, 7, 'SAWERA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1514, 25, 1, 3, 'SHAMIM MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 1, 1, 7, 'MISBAH MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 26, 1, 4, 'NASEEM MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 54, 1, 3, 'HUMAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 67, 1, 3, 'BALQEES BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 71, 1, 3, 'FOZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 72, 1, 3, 'RABIA NAWAZ'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 98, 1, 4, 'SHAHEEN MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 140, 1, 3, 'SHIZA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 145, 1, 4, 'ALISHBA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 149, 1, 4, 'SHAHEENA'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 159, 1, 5, 'SHAZIA MAI'\n" +
                    "),\n" +
                    "(\n" +
                    "1523, 238, 1, 3, 'NAMREEN'\n" +
                    "),\n" +
                    "(\n" +
                    "1553, 39, 1, 6, 'FIZA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1553, 43, 1, 3, 'SIDRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1553, 83, 1, 3, 'MEHMONA'\n" +
                    "),\n" +
                    "(\n" +
                    "1553, 169, 1, 3, 'MUQADAS BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1553, 189, 1, 3, 'MEHMONA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 39, 1, 3, 'ROBINA'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 44, 1, 8, 'RAFIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 67, 1, 4, 'ZAHRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 84, 1, 4, 'NASREEN'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 86, 1, 4, 'SAMINA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 135, 1, 3, 'YASMEEN'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 213, 1, 3, 'SAIMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 216, 1, 3, 'SHADO BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 245, 1, 5, 'SHEHLA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 259, 1, 3, 'NAJMA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1569, 260, 1, 3, 'SAIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 114, 1, 4, 'ASIA BABI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 170, 1, 4, 'RAZIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 241, 1, 3, 'SAWERA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 247, 1, 3, 'SONIA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 266, 1, 3, 'RISHBA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 306, 1, 3, 'TAHIRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1584, 365, 1, 3, 'AYESHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1615, 11, 1, 8, 'SONIA'\n" +
                    "),\n" +
                    "(\n" +
                    "1615, 15, 1, 4, 'RAMSHA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1615, 25, 1, 5, 'KUBRA BIBI'\n" +
                    "),\n" +
                    "(\n" +
                    "1615, 32, 1, 3, 'NADIA'\n" +
                    "),\n" +
                    "(\n" +
                    "1615, 37, 1, 3, 'RAMSHA BIBI'\n" +
                    ");";



        } catch (Exception e) {
           // EmailDebugLog.getInstance(appContext).writeLog("[HouseHoldDataBaseHelper] inside create_pending_adolescent() Exception is :"+e.toString());
        }

    }


    private void checkAndCreatecolumn(SQLiteDatabase db,String querry) {
        try {


            DebugLog.console("[DataHelper] inside checkAndCreatecolumn() querry "+querry);
            db.execSQL(querry);


        } catch (Exception e) {

            //com.mubashar.dateandtime.EmailDebugLog.getInstance().writeLog("[DataHelper] inside checkAndCreatecolumn() Exception is :"+e.toString());
        }
    }


    private void dropTable(String tableName) {

        try {
            db.execSQL("DROP TABLE IF EXISTS '" + MpcUtil.HHID_Survey + "'");
            DebugLog.console("[HouseHoldDataBaseHelper] inside dropTable() "+ MpcUtil.HHID_Survey);
            String CREATE_TABLE_P_URBAN_SURVEY = "CREATE TABLE IF NOT EXISTS " + MpcUtil.HHID_Survey + "(" + "_id integer primary key autoincrement, District_Code text,District_Name text, Tehsil_code text, Tehsil_Name text, PSU_code text,PSU_name text,Structure_id text,HH_id text,q1_headname text, q2_headfathername text, q3_Hhmember text, q4_migration text, q5_Add_house text, q5_Add_street text,q5_Add_Neighborhood text,q5_Add_Landmark text, Comments text, user_name text, start_date_time text, end_date_time text, device_id text);";
            db.execSQL(CREATE_TABLE_P_URBAN_SURVEY);
            DebugLog.console("[HouseHoldDataBaseHelper] inside dropTable() "+CREATE_TABLE_P_URBAN_SURVEY);

        } catch (Exception e) {
            EmailDebugLog.getInstance(AppController.getInstance()).writeLog("[HouseHoldDataBaseHelper] inside dropTable() Exception is :"+e.toString());
        }
    }

    public ArrayList<HouseMemberModel> getAllMembersList(String hhid) {
        ArrayList<HouseMemberModel> allMembersData = new ArrayList<HouseMemberModel>();
        try {
            //    exportDB(mContext);
            openDB();

            //using varaiable prioirty but reading value from study sample

            String query = "select hhid,mid,mem_name,district,mauzaid,mauza_name,study_sample from hh_old_survey where hhid="+hhid+";";

//            String query = "select hhid,mid,mem_name,district,mauza_name from hh_old_survey where hhid="+hhid+";";





            Cursor cursor = db.rawQuery(query, null);
            while (cursor.moveToNext()) {
                HouseMemberModel hhMembersModel = new HouseMemberModel();
                int mid = cursor.getInt(cursor.getColumnIndex("mid"));
                String mem_name = cursor.getString(cursor.getColumnIndex("mem_name"));
                int memHHID = cursor.getInt(cursor.getColumnIndex("hhid"));
                String district = cursor.getString(cursor.getColumnIndex("district"));
                int mauza_id = cursor.getInt(cursor.getColumnIndex("mauzaid"));
                String mauza_name = cursor.getString(cursor.getColumnIndex("mauza_name"));

                int priority = cursor.getInt(cursor.getColumnIndex("study_sample"));
                DebugLog.console(priority+"---study_sample");


                hhMembersModel.setMid(mid);
                hhMembersModel.setMem_name(mem_name);
                hhMembersModel.setHhid(memHHID);
                hhMembersModel.setDistrict(district);
                hhMembersModel.setMauza_id(mauza_id);
                hhMembersModel.setMauza_name(mauza_name);
                hhMembersModel.setPriority(priority);
                allMembersData.add(hhMembersModel);

                DebugLog.tagConsole(TAG,"[HouseHoldDataBaseHelper] inside getAllMembersList() HHID : "+hhid );
            }
            closeDB(cursor);
            closeDB();
            return allMembersData;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[HouseHoldDataBaseHelper] inside getAllMembersList() Exception is : " + e.toString());
            closeDB();
            return allMembersData;
        }
    }

    public boolean insertIntoHHSummary(String district, int mauza_id, String mauza_name,
                                       int hhid, int same_hh, int same_village, int out_of_village,
                                       int same_district, int out_of_district, int same_country,
                                       int out_of_country, int dead, int initial_members, int
                                               new_members, int total_members){
        try{
            ContentValues cv = new ContentValues();
            openDB();
            cv.put(Util.HH_SUMMARY_COLUMNS[0],district);
            cv.put(Util.HH_SUMMARY_COLUMNS[1],mauza_id);
            cv.put(Util.HH_SUMMARY_COLUMNS[2],mauza_name);
            cv.put(Util.HH_SUMMARY_COLUMNS[3],hhid);
            cv.put(Util.HH_SUMMARY_COLUMNS[4],same_hh);
            cv.put(Util.HH_SUMMARY_COLUMNS[5],same_village);
            cv.put(Util.HH_SUMMARY_COLUMNS[6],out_of_village);
            cv.put(Util.HH_SUMMARY_COLUMNS[7],same_district);
            cv.put(Util.HH_SUMMARY_COLUMNS[8],out_of_district);
            cv.put(Util.HH_SUMMARY_COLUMNS[9],same_country);
            cv.put(Util.HH_SUMMARY_COLUMNS[10],out_of_country);
            cv.put(Util.HH_SUMMARY_COLUMNS[11],dead);
            cv.put(Util.HH_SUMMARY_COLUMNS[12],initial_members);
            cv.put(Util.HH_SUMMARY_COLUMNS[13],new_members);
            cv.put(Util.HH_SUMMARY_COLUMNS[14],total_members);
            long isInserted = db.insert(Util.HHSUMMARY, null, cv);
            DebugLog.console("");
            closeDB();
            if (isInserted != -1) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside insertIntoHHSummary() Data inserted successfully");
                return true;
            } else {
                DebugLog.console("[HouseHoldDataBaseHelper] inside insertIntoHHSummary() Data inserted successfully");
                return false;
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("["+this.getClass().getSimpleName()+"] inside insertIntoHHSummary() Exception is : "+e.toString());
            closeDB();
            return false;
        }
    }


    public synchronized int isUserExist(String userName, String password) {
        Cursor cursor = null;
        try {
            openDB();
            String query = "Select " + Util.USER_COLUMNS[1] + " from " + Util.USERS_TABLE + " where " + Util.USER_COLUMNS[0] + " = \'" + userName + "\';";
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() !=0) {
                cursor.moveToFirst();
                String mPassword = cursor.getString(cursor.getColumnIndex(Util.USER_COLUMNS[1]));
                if (mPassword != null) {
                    if (mPassword.equals(password)) {
                        DebugLog.console("[DataBaseProcessor] inside isUserExist() password match successfully");
                        closeDB(cursor);
                        return 100;
                    } else {
                        DebugLog.console("[DataBaseProcessor] inside isUserExist() password match unsuccessful Invalid password");
                        closeDB(cursor);
                        return 301;
                    }
                } else {
                    DebugLog.console("[DataBaseProcessor] inside isUserExist() password == null");
                    closeDB(cursor);
                    return 200;
                }
            } else {
                DebugLog.console("[DataBaseProcessor] inside isUserExist() Invalid user");
                closeDB(cursor);
                return 302;
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside isUserExist() Exception is : " + e.toString());
            closeDB(cursor);
            return 200;
        }
    }






    public synchronized int isHHCovered(String hhid) {
        Cursor cursor = null;
        int result = 0;
        try {
            openDB();
            String query = "Select * from hh_new_survey where hhid = '"+hhid+ "'";
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() !=0) {

                result = 0;

            } else {
                DebugLog.console("[DataBaseProcessor] inside isUserExist() Invalid user");
                closeDB(cursor);
                result =  1;
            }
            return result;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside isUserExist() Exception is : " + e.toString());
            closeDB(cursor);
            return result;
        }
    }



    public synchronized void exportDB(Context appContext) {


        try {

            if (Util.isSdPresent()){
                openDB();


                String[]  dbList =  appContext.databaseList();

                if (true) {
                    File newFile = new File(Environment.getExternalStorageDirectory().getPath() + "/Rcons/databases/AgHHid/"+MpcUtil.getcurrentTime(6)+"/");

                    if (newFile.isDirectory()) {

                    } else {
                        DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + newFile.getName());
                        DebugLog.console("[MyTask1] inside exportLogFile() creating directory" + newFile.getAbsolutePath());

                        DebugLog.console("[MyTask1] inside exportLogFile() created " + newFile.mkdir());
                        DebugLog.console("[MyTask1] inside exportLogFile() created " + newFile.mkdirs());
                        DebugLog.console("[MyTask1] inside exportLogFile() getPath " + newFile.getPath());
                    }

                    newFile = new File(Environment.getExternalStorageDirectory().getPath() + "/Rcons/databases/AgHHid/"+MpcUtil.getcurrentTime(6)+"/"+MpcUtil.getcurrentTime(11)+"_PhoneQuestionnaire.db");
                    if (newFile.exists()) ;
                    newFile.delete();

                    // File dbFile = appContext.getDatabasePath(dbList[0]);
                    File dbFile = appContext.getDatabasePath("PhoneQuestionnaire.db");

                    InputStream input = new FileInputStream(
                            dbFile.getAbsoluteFile());

                    OutputStream output = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = input.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                    output.flush();
                    output.close();
                    input.close();

                }
                closeDB();
            }else{
                DebugLog.console("[DatabaseProcessor]: SD card is not available ");
            }
        } catch (FileNotFoundException e1) {
            //  TODO Auto-generated catch block
            e1.printStackTrace();
            closeDB();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            closeDB();
        }


    }



    public synchronized int getCounthousehold_new(String categoryName) {
        String ids = "";
        int count = 0;
        Cursor cur = null;
        try{
            openDB();

            String queryy = "SELECT * FROM  hh_new_survey  WHERE `hhid` = '" +categoryName+ "' AND  `mid` > '700' ORDER BY `mid` ASC";
            DebugLog.console( "[DataBaseProcessor]: inside getCounthousehold_new_members :Going to run queryy \r\n"+queryy);
            cur = db.rawQuery(queryy, null);
            if(cur.getCount()!=0){
                cur.moveToFirst();
                DebugLog.console( "[DatabaseProcessor]: returned count = "+cur.getCount());
                count=701 + cur.getCount();
            }else{
                count=701;
            }
            closeDB(cur);
            closeDB();

            return count;

        }catch (Exception e) {
            DebugLog.console(e.toString()+ "[DataBaseProcessor]: exception inside getCounthousehold_new_members  \r\n");
            closeDB(cur);
            closeDB();

            // String household_new_members = "CREATE TABLE IF NOT EXISTS " + "household_new_members" + "(" + "[id] Integer PRIMARY KEY  AUTOINCREMENT  NOT NULL UNIQUE, [hhid] Integer, [mid] Integer , [mem_name] Text, [mem_age] Integer,[mem_gender] Integer,[mem_contact_1] Text, [mem_contact_2] Text, [updated_date] DATETIME));";
            //createMissingTable(household_new_members);


            return 701;
        }

    }



    public synchronized int getCounthousehold_new_members(String categoryName) {
        String ids = "";
        int count = 0;
        Cursor cur = null;
        try{
            openDB();

            String queryy = "SELECT * FROM hh_new_survey WHERE `hhid` = '" +categoryName+ "' ORDER BY `mid` ASC";
            DebugLog.console( "[DataBaseProcessor]: inside getCounthousehold_new_members :Going to run queryy \r\n"+queryy);
            cur = db.rawQuery(queryy, null);
            if(cur.getCount()!=0){
                cur.moveToFirst();
                DebugLog.console( "[DatabaseProcessor]: returned count = "+cur.getCount());
                count=701 + cur.getCount();
            }else{
                count=701;
            }
            closeDB(cur);
            closeDB();

            return count;

        }catch (Exception e) {
            DebugLog.console(e.toString()+ "[DataBaseProcessor]: exception inside getCounthousehold_new_members  \r\n");
            closeDB(cur);
            closeDB();

            String household_new_members = "CREATE TABLE IF NOT EXISTS " + "household_new_members" + "(" + "[id] Integer PRIMARY KEY  AUTOINCREMENT  NOT NULL UNIQUE, [hhid] Integer, [mid] Integer , [mem_name] Text, [mem_age] Integer,[mem_gender] Integer,[mem_contact_1] Text, [mem_contact_2] Text, [updated_date] DATETIME));";
            createMissingTable(household_new_members);


            return 701;
        }

    }
    public  void createMissingTable(String query) {
        try {
            openDB();

            //db.setLocale(Locale.getDefault());

            db.execSQL(query);
            DebugLog.console( "[DatabaseProcessor]:Table created "+query);
            //   debugString.append( "\r\n[DatabaseProcessor]:Table created "+query);


        } catch (Exception e) {
            DebugLog.console(""+ e.toString()+"[DatabaseProcessor]: exception inside createMissingTable");
            //  debugString.append(""+ e.toString()+"[DatabaseProcessor]: exception inside createMissingTable");
        } finally {
            closeDB();
        }
    }

    public synchronized long set_household_new_members(int hhid, int mid, String mem_name, int mem_age, int gender, String mem_contact_1, String mem_contact_2, String date) {
        long rowId = -1;
        try {
            openDB();
            ContentValues values = new ContentValues();
            values.put("hhid",hhid);
            values.put("mid", mid);
            values.put("mem_name",mem_name);
            values.put("mem_age",  mem_age);
            values.put("mem_gender",  gender);
            values.put("mem_contact_1", mem_contact_1);
            values.put("mem_contact_2",mem_contact_2);
            values.put("updated_date",date);

            rowId = db.insert("household_new_members", null, values);
            DebugLog.console( "[DatabaseProcessor]inside storeInBlockedList row inserted : "+rowId);
            closeDB();
            return rowId;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return rowId;
        }
    }








    public synchronized long set_hh_cover_uncover_detail(String id,
                                                         String hhid,
                                                         String s0Q1,
                                                         String s0Q2,
                                                         String s0Q2_other,
                                                         String s0Q3_a,
                                                         String s0Q3_d,
                                                         String s0Q3_c,
                                                         String s0Q3_p,
                                                         String survey_by,
                                                         String tablet_id,
                                                         String start_date,
                                                         String end_date ) {
        long rowId = -1;
        try {
            openDB();
            ContentValues values = new ContentValues();
            values.put("hhid",hhid);
            values.put("s0Q1", s0Q1);
            values.put("s0Q2", s0Q2);
            values.put("s0Q2_other",s0Q2_other);
            values.put("s0Q3_a",  s0Q3_a);
            values.put("s0Q3_d",  s0Q3_d);
            values.put("s0Q3_c", s0Q3_c);
            values.put("s0Q3_p",s0Q3_p);
            values.put("survey_by",survey_by);
            values.put("tablet_id",tablet_id);
            values.put("start_time",start_date);
            values.put("end_time",end_date);
            // values.put("updated_date",updated_date);

            rowId = db.insert("hh_cover_uncover_detail", null, values);
            DebugLog.console( "[DatabaseProcessor]inside set_hh_cover_uncover_detail row inserted : "+rowId);
            closeDB();
            //exportDB(mContext);
            return rowId;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside set_hh_cover_uncover_detail() Exception is : " + e.toString());
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside set_hh_cover_uncover_detail");
            closeDB();
            return rowId;
        }
    }



    public synchronized long set_hh_new_survey(JSONObject ob) {
        long rowId = -1;
        int index = 0;
        try {
            openDB();


            int len = ob.length();
            if (len==0){


            }else {
                ContentValues values = new ContentValues();


                Iterator<?> keys = ob.keys();

                while( keys.hasNext() ) {
                    String key = (String)keys.next();

                    if ( ob.get(key) instanceof Integer) {

                        values.put(key,ob.getInt(key));
                    }if ( ob.get(key) instanceof String) {
                        values.put(key,ob.getString(key));
                    }



                }


                rowId = db.insert("hh_new_survey", null, values);
                DebugLog.console("[DatabaseProcessor]inside set_hh_cover_uncover_detail row inserted : " + rowId);
            }
            closeDB();
            return rowId;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside set_hh_cover_uncover_detail() Exception is : " + e.toString());
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside set_hh_cover_uncover_detail");
            closeDB();
            return rowId;
        }
    }


    public synchronized long exportCSVNewHH(Context appContext){
        long rowId = 1;
        try {
            openDB();

            Cursor curCSV = db.rawQuery("SELECT * FROM hh_new_survey",null);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
                String fileName = FileManager.createFileName(appContext, "csv" + "", "hh_new_survey");
                FileManager.createNewFile(appContext, fileName, "");
                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


                csvWrite.writeNext(curCSV.getColumnNames());
                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    while (index < count) {
                        arrStr[index] = curCSV.getString(index);
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                    csvWrite.writeNext(arrStr);


                }
                csvWrite.close();

            }
            //curCSV.close();
            DebugLog.console("exportCSVNewHH Csv created successfully");
            closeDB();
            return rowId;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return -1;
        }
    }





    public synchronized long exportCSV(Context appContext){
        long rowId = 1;
        try {
            openDB();

            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ MpcUtil.HHID_Survey,null);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
                FileManager.createNewFile(appContext, fileName, "");


                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


                csvWrite.writeNext(curCSV.getColumnNames());
                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    while (index < count) {
                        arrStr[index] = curCSV.getString(index);
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                    csvWrite.writeNext(arrStr);


                }
                csvWrite.close();

            }
            //curCSV.close();
            DebugLog.console("Csv created successfully");
            closeDB();
            return rowId;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return -1;
        }
    }



    public  synchronized boolean importDataBase(Context appContext) {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {

            openDB();

            // Open local db from assets as the input stream
            myInput = appContext.getAssets().open("houses.db");

            File dbFile = appContext.getDatabasePath("houses.db");
            // Open the empty db as the output stream
            myOutput = new FileOutputStream(dbFile);

            // transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myInput.close();
            myOutput.close();
            // Close the db connection
            closeDB();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }




    public synchronized boolean addUser(String userName, String password) {
        try {
            openDB();
            ContentValues cv = new ContentValues();
            cv.put(Util.USER_COLUMNS[0], userName);
            cv.put(Util.USER_COLUMNS[1], password);
            long isInserted = db.insert(Util.USERS_TABLE, null, cv);
            closeDB();
            if (isInserted != -1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside addUser() Exception is : " + e.toString());
            closeDB();
            return false;
        }

    }






    public synchronized long InsertOrUpdateQ1screen(String hhid, String mid, String mem_name, String s1Q1, String s1Q2, String s1Q3, String s1Q3_private, String s1Q4, String s1Q5, String s1Q5_a, String s1Q5_d, String s1Q5_c, String s1Q5_p, String s2Q1, String s2Q2, String s3, String updated_date) {


        long rowId = -1;
        // String sql = "INSERT OR REPLACE INTO "+"hh_new_survey"+" (hhid, mid, mem_name, s1Q1, s1Q2, s1Q3,s1Q3_private, s1Q4, s1Q5, s1Q5_a, s1Q5_d, s1Q5_c, s1Q5_p,  s2Q1, s2Q2, s3,updated_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String sql ="INSERT OR REPLACE INTO "+"hh_new_survey"+" (hhid, mid, mem_name, still_in_HH, still_studying, grade,Is_in_private_school, when_interview, where_shifted, shifted_address, shifted_District, shifted_Country, shifted_PH_no,  age, gender, st_id,updated_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            openDB();

            db.beginTransaction();
            SQLiteStatement st = db.compileStatement(sql);
            DebugLog.console("[DatabaseProcessor]:  compileStatement done");


            st.clearBindings();
            st.bindString(1, hhid);
            st.bindString(2, mid);
            st.bindString(3, mem_name);
            st.bindString(4, s1Q1);
            st.bindString(5,s1Q2);
            st.bindString(6,s1Q3);
            st.bindString(7,s1Q3_private);
            st.bindString(8,s1Q4);
            st.bindString(9,s1Q5);
            st.bindString(10,s1Q5_a);
            st.bindString(11,s1Q5_d);
            st.bindString(12,s1Q5_c);
            st.bindString(13,s1Q5_p);
            st.bindString(14,s2Q1);
            st.bindString(15,s2Q2);
            st.bindString(16,s3);
            st.bindString(17,updated_date);

            rowId = st.executeInsert();
            DebugLog.console("1[DatabaseProcessor]:Inside InsertOrUpdateSingleUrIInWhiteList Insertion result "+rowId);


            db.setTransactionSuccessful();
            db.endTransaction();
            closeDB();
            return rowId;

        }
        catch(Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"[DatabaseProcessor]: exception inside InsertOrUpdateSingleUrIInWhiteList");
            db.endTransaction();
            closeDB();
            return rowId;
        }

    }


    public synchronized long InsertOrUpdateQ2screen(String hhid, String mid, String mem_name, String s1Q1, String s1Q2, String s1Q3, String s1Q4, String s1Q5, String s1Q5_a, String s1Q5_d, String s1Q5_c, String s1Q5_p, String s2Q1, String s2Q2, String s3, String updated_date) {


        long rowId = -1;
        String sql = "";//"INSERT OR REPLACE INTO "+"hh_new_survey"+" (hhid, mid, mem_name, s1Q1, s1Q2, s1Q3, s1Q4, s1Q5, s1Q5_a, s1Q5_d, s1Q5_c, s1Q5_p,  s2Q1, s2Q2, s3,updated_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE ";

        try {
            openDB();

            db.beginTransaction();
            SQLiteStatement st = db.compileStatement(sql);
            DebugLog.console("[DatabaseProcessor]:  compileStatement done");


            st.clearBindings();
//            st.bindString(1, hhid);
//            st.bindString(2, mid);
//            st.bindString(3, mem_name);
//            st.bindString(4, s1Q1);
            st.bindString(5,s1Q2);
            rowId = st.executeInsert();
            DebugLog.console("1[DatabaseProcessor]:Inside InsertOrUpdateSingleUrIInWhiteList Insertion result "+rowId);


            db.setTransactionSuccessful();
            db.endTransaction();
            closeDB();
            return rowId;

        }
        catch(Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog(e.toString()+"[DatabaseProcessor]: exception inside InsertOrUpdateSingleUrIInWhiteList");
            db.endTransaction();
            closeDB();
            return rowId;
        }

    }




    public synchronized long InsertOrUpdateEndTime( String hhid) {


        int count = 0;
        Cursor cur = null;
        try{
            openDB();

            String queryy = "UPDATE hh_cover_uncover_detail SET end_time = '"+Util.getcurrentTime(9)+"'  WHERE `hhid` = '" +hhid+ "' ";
//            String queryy = "UPDATE hh_new_survey SET s3 = '"+strucID+"'  WHERE `hhid` = '" +categoryName+ "' AND  `mem_name` = '"+mem_name+"';";
            EmailDebugLog.getInstance(mContext).writeLog( "[DataBaseProcessor]: inside InsertOrUpdateEndTime :Going to run queryy \r\n"+queryy);
            cur = db.rawQuery(queryy, null);
            if (cur!=null){
                cur.moveToFirst();
            }
//            if(cur.getCount()!=0){
//
//                int struc_id = cur.getInt(cur.getColumnIndex("s3"));
//                DebugLog.console( "[DatabaseProcessor]: returned count = "+struc_id);
//                count=struc_id;
//            }else{
//                count=0;
//            }
            closeDB(cur);
            closeDB();

            return count;

        }catch (Exception e) {
            DebugLog.console(e.toString()+ "[DataBaseProcessor]: exception inside getCounthousehold_new_members  \r\n");
            closeDB(cur);
            closeDB();

            // String household_new_members = "CREATE TABLE IF NOT EXISTS " + "household_new_members" + "(" + "[id] Integer PRIMARY KEY  AUTOINCREMENT  NOT NULL UNIQUE, [hhid] Integer, [mid] Integer , [mem_name] Text, [mem_age] Integer,[mem_gender] Integer,[mem_contact_1] Text, [mem_contact_2] Text, [updated_date] DATETIME));";
            //createMissingTable(household_new_members);


            return 0;
        }


    }




    public synchronized int getStructureIDLivingWithInSameVillage(String categoryName, String date, String s1Q5) {
        String ids = "";
        int count = 0;
        Cursor cur = null;
        try{


            if (s1Q5.equalsIgnoreCase("1")) {
                count =  StructureUtil.LAST_STRUCTUREID_withinsame_village;
                StructureUtil.LAST_STRUCTUREID_withinsame_village= StructureUtil.LAST_STRUCTUREID_withinsame_village+1;
            }
            if (s1Q5.equalsIgnoreCase("2")) {
                count =  StructureUtil.LAST_STRUCTUREID_outsidevillage_samedistrict;
                StructureUtil.LAST_STRUCTUREID_outsidevillage_samedistrict= StructureUtil.LAST_STRUCTUREID_outsidevillage_samedistrict+1;
            }
            if (s1Q5.equalsIgnoreCase("3")) {
                count =  StructureUtil.LAST_STRUCTUREID_outsidedistrict_withincountry;
                StructureUtil.LAST_STRUCTUREID_outsidedistrict_withincountry= StructureUtil.LAST_STRUCTUREID_outsidedistrict_withincountry+1;
            }
            if (s1Q5.equalsIgnoreCase("4")) {
                count =  StructureUtil.LAST_STRUCTUREID_outsidecountry;
                StructureUtil.LAST_STRUCTUREID_outsidecountry= StructureUtil.LAST_STRUCTUREID_outsidecountry+1;
            }
//            if (s1Q5.equalsIgnoreCase("5")) {
//                count =  StructureUtil.LAST_STRUCTUREID_withinsame_village;
//            }
//            openDB();
//
//            String queryy = "SELECT * FROM  hh_new_survey  WHERE `hhid` = '" +categoryName+ "' AND  `updated_date` = '"+date+"'AND  `s1Q5` = '"+s1Q5+"' ORDER BY `s3` DESC";
//            DebugLog.console( "[DataBaseProcessor]: inside getCounthousehold_new_members :Going to run queryy \r\n"+queryy);
//            cur = db.rawQuery(queryy, null);
//            if(cur.getCount()!=0){
//                cur.moveToFirst();
//                int struc_id = cur.getInt(cur.getColumnIndex("s3"));
//                DebugLog.console( "[DatabaseProcessor]: returned count = "+struc_id);
//                count=struc_id;
//            }else{
//                count=0;
//            }
//            closeDB(cur);
//            closeDB();

            return count;

        }catch (Exception e) {
            DebugLog.console(e.toString()+ "[DataBaseProcessor]: exception inside getCounthousehold_new_members  \r\n");
            closeDB(cur);
            closeDB();

            // String household_new_members = "CREATE TABLE IF NOT EXISTS " + "household_new_members" + "(" + "[id] Integer PRIMARY KEY  AUTOINCREMENT  NOT NULL UNIQUE, [hhid] Integer, [mid] Integer , [mem_name] Text, [mem_age] Integer,[mem_gender] Integer,[mem_contact_1] Text, [mem_contact_2] Text, [updated_date] DATETIME));";
            //createMissingTable(household_new_members);


            return 0;}
    }






    public synchronized int updateStructureIDLivingWithInSameVillage(String categoryName, String strucID, String date, String mem_name) {
        String ids = "";
        int count = 0;
        Cursor cur = null;
        try{
            openDB();

            String queryy = "UPDATE hh_new_survey SET st_id = '"+strucID+"'  WHERE `hhid` = '" +categoryName+ "' AND  `updated_date` = '"+date+ "' AND  `mem_name` = '"+mem_name+"'";
//            String queryy = "UPDATE hh_new_survey SET s3 = '"+strucID+"'  WHERE `hhid` = '" +categoryName+ "' AND  `mem_name` = '"+mem_name+"';";
            EmailDebugLog.getInstance(mContext).writeLog( "[DataBaseProcessor]: inside getCounthousehold_new_members :Going to run queryy \r\n"+queryy);
            cur = db.rawQuery(queryy, null);
            if (cur!=null){
                cur.moveToFirst();
            }
//            if(cur.getCount()!=0){
//
//                int struc_id = cur.getInt(cur.getColumnIndex("s3"));
//                DebugLog.console( "[DatabaseProcessor]: returned count = "+struc_id);
//                count=struc_id;
//            }else{
//                count=0;
//            }
            closeDB(cur);
            closeDB();

            return count;

        }catch (Exception e) {
            DebugLog.console(e.toString()+ "[DataBaseProcessor]: exception inside getCounthousehold_new_members  \r\n");
            closeDB(cur);
            closeDB();

            // String household_new_members = "CREATE TABLE IF NOT EXISTS " + "household_new_members" + "(" + "[id] Integer PRIMARY KEY  AUTOINCREMENT  NOT NULL UNIQUE, [hhid] Integer, [mid] Integer , [mem_name] Text, [mem_age] Integer,[mem_gender] Integer,[mem_contact_1] Text, [mem_contact_2] Text, [updated_date] DATETIME));";
            //createMissingTable(household_new_members);


            return 0;
        }


    }



    public synchronized void reinitializeTable(String tableName) {
        Cursor cursor = null;
        String queery = "";
        try{
            openDB();
            queery = "DELETE * FROM `"+tableName+"`";

            DebugLog.console("[DatabaseProcessor]:  inside reinitializeTable :Going to run queery \r\n"+queery);
            int affectedRows = db.delete(tableName, "1", null);


            closeDB(cursor);
            EmailDebugLog.getInstance(mContext).writeLog( "[DatabaseProcessor]:Row affected reinitializeTable"+tableName+""+affectedRows);
        }catch(Exception e) {
            DebugLog.console(e.toString()+"[DatabaseProcessor]: exception inside removeWlBLEntriesFromDB");

            closeDB(cursor);
        }

    }


    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^HelPer Function For Android DataBaseManager^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

//mubashar
//                if (c!=null)
//                    c.close();
                return alc ;
            }

            if (c!=null)
                c.close();

            return alc;
        } catch(Exception sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
//        catch(Exception ex){
//
//            Log.d("printing exception", ex.getMessage());
//
//            //if any exceptions are triggered save the error message to cursor an return the arraylist
//            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
//            alc.set(1,Cursor2);
//            return alc;
//        }


    }







    public synchronized int updateTestQuery (String queryy) {
        String ids = "";
        int count = 0;
        Cursor cur = null;
        try{
            openDB();

            //String queryy = "UPDATE hh_new_survey SET s3 = '"+strucID+"'  WHERE `hhid` = '" +categoryName+ "' AND  `updated_date` = '"+date+ "' AND  `mem_name` = '"+mem_name+"'";
//            String queryy = "UPDATE hh_new_survey SET s3 = '"+strucID+"'  WHERE `hhid` = '" +categoryName+ "' AND  `mem_name` = '"+mem_name+"';";
            EmailDebugLog.getInstance(mContext).writeLog( "[DataBaseProcessor]: inside getCounthousehold_new_members :Going to run queryy \r\n"+queryy);
            cur = db.rawQuery(queryy, null);
            if (cur!=null){
                cur.moveToFirst();
            }
//            if(cur.getCount()!=0){
//
//                int struc_id = cur.getInt(cur.getColumnIndex("s3"));
//                DebugLog.console( "[DatabaseProcessor]: returned count = "+struc_id);
//                count=struc_id;
//            }else{
//                count=0;
//            }
            closeDB(cur);
            closeDB();

            return count;

        }catch (Exception e) {
            DebugLog.console(e.toString()+ "[DataBaseProcessor]: exception inside getCounthousehold_new_members  \r\n");
            closeDB(cur);
            closeDB();

            // String household_new_members = "CREATE TABLE IF NOT EXISTS " + "household_new_members" + "(" + "[id] Integer PRIMARY KEY  AUTOINCREMENT  NOT NULL UNIQUE, [hhid] Integer, [mid] Integer , [mem_name] Text, [mem_age] Integer,[mem_gender] Integer,[mem_contact_1] Text, [mem_contact_2] Text, [updated_date] DATETIME));";
            //createMissingTable(household_new_members);


            return 0;
        }


    }


    public synchronized boolean hhid_isPSU_INCoveredLIST(String PSU_code) {
        Cursor cursor = null;
        boolean  result = false;
        try {
            openDB();
            String query = "Select * from "+ MpcUtil.HHID_survey_details+" where  PSU_code = '"+PSU_code.toUpperCase()+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside survey_details () "+query);
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside survey_details hhid_isPSU_INCoveredLIST() yes");
                result = true;

            } else {
                DebugLog.console("[DataBaseProcessor] inside  survey_details hhid_isPSU_INCoveredLIST()no");

                result =  false;
            }
            closeDB(cursor);
            return result;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_isHHCovered() Exception is : " + e.toString());
            closeDB(cursor);
            return result;
        }
    }


    public synchronized boolean hhid_isHHCovered(String village_id,String hhid) {
        Cursor cursor = null;
        boolean  result = false;
        try {
            openDB();
            String query = "Select * from "+ DatabaseAdapter.aghhid_section_c_table+" where hhid = '"+hhid.toUpperCase()+ "' AND village_id = '"+village_id.toUpperCase()+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_isHHCovered() "+query);
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_isHHCovered() yes");
                result = true;

            } else {
                DebugLog.console("[DataBaseProcessor] inside hhid_isHHCovered() new hhid");

                result =  false;
            }
            closeDB(cursor);
            return result;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_isHHCovered() Exception is : " + e.toString());
            closeDB(cursor);
            return result;
        }
    }

    public synchronized boolean hhid_isHHCovered_for_e(String village_id,String hhid) {
        Cursor cursor = null;
        boolean  result = false;
        try {
            openDB();
            String query = "Select * from "+ DatabaseAdapter.aghhid_section_e_table+" where hhid = '"+hhid.toUpperCase()+ "' AND village_id = '"+village_id.toUpperCase()+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_isHHCovered() "+query);
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_isHHCovered() yes");
                result = true;

            } else {
                DebugLog.console("[DataBaseProcessor] inside hhid_isHHCovered() new hhid");

                result =  false;
            }
            closeDB(cursor);
            return result;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_isHHCovered() Exception is : " + e.toString());
            closeDB(cursor);
            return result;
        }
    }

    public synchronized boolean hhid_isHHCovered_for_h(String village_id,String hhid) {
        Cursor cursor = null;
        boolean  result = false;
        try {
            openDB();
            String query = "Select * from "+ DatabaseAdapter.aghhid_section_h_table+" where hhid = '"+hhid.toUpperCase()+ "' AND village_id = '"+village_id.toUpperCase()+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_isHHCovered_for_h() "+query);
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_isHHCovered_for_h() yes");
                result = true;

            } else {
                DebugLog.console("[DataBaseProcessor] inside hhid_isHHCovered_for_h() new hhid");

                result =  false;
            }
            closeDB(cursor);
            return result;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_isHHCovered_for_h() Exception is : " + e.toString());
            closeDB(cursor);
            return result;
        }
    }



    public synchronized boolean hhid_delete_number_list(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String number,String status) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_number_list() runUpdate_Querry"+runUpdate_Querry);


            openDB();

//            ContentValues cv = new ContentValues();
//            cv.put("PSU_code", PSU_code.toUpperCase());
//            cv.put("HH_id", HH_id.toUpperCase());
//            cv.put("hhid_phone_number", number.toUpperCase());
//            cv.put("status", status.toUpperCase());
//            cv.put("build_no", BuildConfig.VERSION_NAME);
//            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14));
//            cv.put("user_name", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());
//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            int id =  db.delete(MpcUtil.HHID_Survey_PHONE_LIST,  "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] HHID_Survey_PHONE_LIST inside hhid_delete_number_list  delete() "+id);




            closeDB();
            return  true;

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_screen_six() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }




    public synchronized boolean hhid_insert_number_list(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String number,String status) {
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_number_list() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();
            cv.put("PSU_code", PSU_code.toUpperCase());
            cv.put("HH_id", HH_id.toUpperCase());
            cv.put("hhid_phone_number", number.toUpperCase());
            cv.put("status", status.toUpperCase());
            cv.put("build_no", BuildConfig.VERSION_NAME);
            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14));
            cv.put("user_name", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());
            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            long  isInserted = db.insert(MpcUtil.HHID_Survey_PHONE_LIST, null, cv);
            DebugLog.console("[HouseHoldDataBaseHelper] inside isInserted() "+isInserted);



            closeDB();
            if (isInserted != -1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_screen_six() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }




    public synchronized boolean hhid_insert_data_screen_three(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String hhid_q5) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_six() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

//            cv.put("District_Code", District_Code.toUpperCase());
//            cv.put("District_Name", District_Name.toUpperCase());
//            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
//            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
//            cv.put("PSU_code", PSU_code.toUpperCase());
//            cv.put("PSU_name", PSU_name.toUpperCase());
//            cv.put("Structure_id", Structure_id.toUpperCase());
//            cv.put("HH_id", HH_id.toUpperCase());

            cv.put("hhid_q5", hhid_q5.toUpperCase());


            if(hhid_q5.equalsIgnoreCase("999")){

                //ToDo code to delete numbers from other table
            }

//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            db.update(MpcUtil.HHID_Survey, cv, "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_six() "+hhid_q5.toString());




            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_screen_six() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }






    public synchronized boolean hhid_insert_data_screen_six(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String hhid_q10) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_six() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

//            cv.put("District_Code", District_Code.toUpperCase());
//            cv.put("District_Name", District_Name.toUpperCase());
//            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
//            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
//            cv.put("PSU_code", PSU_code.toUpperCase());
//            cv.put("PSU_name", PSU_name.toUpperCase());
//            cv.put("Structure_id", Structure_id.toUpperCase());
//            cv.put("HH_id", HH_id.toUpperCase());

            cv.put("hhid_q10", hhid_q10.toUpperCase());


            if(!hhid_q10.equalsIgnoreCase("")){

                if(Integer.parseInt(hhid_q10)==0){
                    cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
                }
            }

//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            db.update(MpcUtil.HHID_Survey, cv, "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_six() "+hhid_q10.toString());




            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_screen_six() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }

    public synchronized boolean hhid_insert_data_screen_eleven_twelve(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String hhid_q11,String hhid_q12) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();
//            cv.put("District_Code", District_Code.toUpperCase());
//            cv.put("District_Name", District_Name.toUpperCase());
//            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
//            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
            cv.put("PSU_code", PSU_code.toUpperCase());
            cv.put("PSU_name", PSU_name.toUpperCase());
            cv.put("Structure_id", Structure_id.toUpperCase());
            cv.put("HH_id", HH_id.toUpperCase());

            cv.put("hhid_q11", hhid_q11.toUpperCase());
            cv.put("hhid_q12", hhid_q12.toUpperCase());

//            cv.put("q1_headname", q1_headname.toUpperCase());
//            cv.put("q2_headfathername", q2_headfathername.toUpperCase());
//            cv.put("q3_Hhmember", q3_Hhmember.toUpperCase());
//            cv.put("q4_migration", q4_migration.toUpperCase());
//            cv.put("q5_Add_house", q5_Add_house.toUpperCase());
//            cv.put("q5_Add_street", q5_Add_street.toUpperCase());
//            cv.put("q5_Add_Neighborhood", q5_Add_Neighborhood.toUpperCase());
//            cv.put("q5_Add_Landmark", q5_Add_Landmark.toUpperCase());
//            cv.put("user_name", user_name.toUpperCase());
//            cv.put("start_date_time", start_date_time.toUpperCase());
//            cv.put("end_date_time", end_date_time.toUpperCase());
//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());


            db.update(MpcUtil.HHID_Survey, cv, "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'", null);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q11"+hhid_q11.toString());
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q12"+hhid_q12.toString());




            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }

    public synchronized boolean hhid_insert_data_q_thirteen(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String hhid_q13_h,String hhid_q13_m) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();
//            cv.put("District_Code", District_Code.toUpperCase());
//            cv.put("District_Name", District_Name.toUpperCase());
//            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
//            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
//            cv.put("PSU_code", PSU_code.toUpperCase());
//            cv.put("PSU_name", PSU_name.toUpperCase());
//            cv.put("Structure_id", Structure_id.toUpperCase());
//            cv.put("HH_id", HH_id.toUpperCase());

            cv.put("hhid_q13_h", hhid_q13_h.toUpperCase());
            cv.put("hhid_q13_m", hhid_q13_m.toUpperCase());
            cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());

            db.update(MpcUtil.HHID_Survey, cv, "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'",null);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q13_h"+hhid_q13_h.toString());
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q13_m"+hhid_q13_m.toString());

            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }


    public synchronized boolean hhid_insert_data_screen_eight_nine(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String hhid_q8,String hhid_q9) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();
//            cv.put("District_Code", District_Code.toUpperCase());
//            cv.put("District_Name", District_Name.toUpperCase());
//            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
//            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
            cv.put("PSU_code", PSU_code.toUpperCase());
            cv.put("PSU_name", PSU_name.toUpperCase());
            cv.put("Structure_id", Structure_id.toUpperCase());
            cv.put("HH_id", HH_id.toUpperCase());

            cv.put("hhid_q8", hhid_q8.toUpperCase());
            cv.put("hhid_q9", hhid_q9.toUpperCase());

//            cv.put("q1_headname", q1_headname.toUpperCase());
//            cv.put("q2_headfathername", q2_headfathername.toUpperCase());
//            cv.put("q3_Hhmember", q3_Hhmember.toUpperCase());
//            cv.put("q4_migration", q4_migration.toUpperCase());
//            cv.put("q5_Add_house", q5_Add_house.toUpperCase());
//            cv.put("q5_Add_street", q5_Add_street.toUpperCase());
//            cv.put("q5_Add_Neighborhood", q5_Add_Neighborhood.toUpperCase());
//            cv.put("q5_Add_Landmark", q5_Add_Landmark.toUpperCase());
//            cv.put("user_name", user_name.toUpperCase());
//            cv.put("start_date_time", start_date_time.toUpperCase());
//            cv.put("end_date_time", end_date_time.toUpperCase());
//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());


            db.update(MpcUtil.HHID_Survey, cv, "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'", null);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q8"+hhid_q8.toString());
            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q9"+hhid_q9.toString());




            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }



    public synchronized boolean hhid_insert_data_screen_four(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id ,String hhid_q7) {
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();
//            cv.put("District_Code", District_Code.toUpperCase());
//            cv.put("District_Name", District_Name.toUpperCase());
//            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
//            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
            cv.put("PSU_code", PSU_code.toUpperCase());
            cv.put("PSU_name", PSU_name.toUpperCase());
            cv.put("Structure_id", Structure_id.toUpperCase());
            cv.put("HH_id", HH_id.toUpperCase());

            cv.put("hhid_q7", hhid_q7.toUpperCase());

//            cv.put("q1_headname", q1_headname.toUpperCase());
//            cv.put("q2_headfathername", q2_headfathername.toUpperCase());
//            cv.put("q3_Hhmember", q3_Hhmember.toUpperCase());
//            cv.put("q4_migration", q4_migration.toUpperCase());
//            cv.put("q5_Add_house", q5_Add_house.toUpperCase());
//            cv.put("q5_Add_street", q5_Add_street.toUpperCase());
//            cv.put("q5_Add_Neighborhood", q5_Add_Neighborhood.toUpperCase());
//            cv.put("q5_Add_Landmark", q5_Add_Landmark.toUpperCase());
//            cv.put("user_name", user_name.toUpperCase());
//            cv.put("start_date_time", start_date_time.toUpperCase());
//            cv.put("end_date_time", end_date_time.toUpperCase());
//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());


            db.update(MpcUtil.HHID_Survey, cv, "HH_id=" + HH_id.toUpperCase()+ " AND PSU_code = '"+PSU_code.toUpperCase()+"'", null);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() "+hhid_q7.toString());




            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }




    public synchronized boolean aghhid_update_given_nummber(String village_id ,String hhid ,String given_number) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_update_given_nummber() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();
            cv.put("given_number", given_number.toUpperCase());
            db.update(DatabaseAdapter.AGHHID_SampleTable, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+"", null);

            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_update_given_nummber() update");
            updated = true;


            closeDB();


            updated =  true;


            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }




    public synchronized boolean hhid_insert_data(String village_id ,String hhid , String c1, String c1_given_number, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("c1", c1.toUpperCase());
            cv.put("c1_given_number", c1_given_number.toUpperCase());

            if(c1.equalsIgnoreCase("-777")|| c1.equalsIgnoreCase("-99") ){
                cv.put("c2_1", "");
                cv.put("c2_2", "");
                cv.put("c2_3", "");
                cv.put("c2_4", "");
                cv.put("c2_5", "");
                cv.put("c2_6", "");
                cv.put("c2_7", "");
                cv.put("c2_8", "");
                cv.put("c2_9", "");
            }

//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
                cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_c_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_c_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+"", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }


    public synchronized boolean hhid_insert_data_c_2(String village_id ,String hhid ,  String aghhid_c_2_1,String aghhid_c_2_2,String aghhid_c_2_3,String aghhid_c_2_4,String aghhid_c_2_5,String aghhid_c_2_6,String aghhid_c_2_7,String aghhid_c_2_8,String aghhid_c_2_9,String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_c_2() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("c2_1", aghhid_c_2_1.toUpperCase());
            cv.put("c2_2", aghhid_c_2_2.toUpperCase());
            cv.put("c2_3", aghhid_c_2_3.toUpperCase());
            cv.put("c2_4", aghhid_c_2_4.toUpperCase());
            cv.put("c2_5", aghhid_c_2_5.toUpperCase());
            cv.put("c2_6", aghhid_c_2_6.toUpperCase());
            cv.put("c2_7", aghhid_c_2_7.toUpperCase());
            cv.put("c2_8", aghhid_c_2_8.toUpperCase());
            cv.put("c2_9", aghhid_c_2_9.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
                cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_c_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_c_2() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_c_table, cv, "hhid=" + hhid.toUpperCase()+ " AND village_id = '"+village_id.toUpperCase()+"'", null);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_c_2() update");
                updated = true;
            }

            closeDB();



            if (isInserted != -1) {
                updated =  true;
            }
            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_c_2() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized JSONArray getDataFromPhoneTable(Context appContext,String village_Code,String hhid){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ MpcUtil.HHID_Survey_PHONE_LIST +" where PSU_code = '"+village_Code+"' AND HH_id = '"+hhid+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromPhoneTable() village_Code "+village_Code);
            DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromPhoneTable() hhid"+hhid);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromPhoneTable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");
//DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+data.toString());
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);
                    dataArray.put(data);

                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console("Csv created successfully");
            closeDB();
            return dataArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return new JSONArray();
        }
    }

    public synchronized JSONArray getAllPhoneDataFromtable(Context appContext){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();
//            Cursor curCSV = db.rawQuery("SELECT * FROM  "+MpcUtil.HHID_Survey +" where PSU_code = '"+village_Code+"' AND HH_id = '"+hhid+"'",null);
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ MpcUtil.HHID_Survey_PHONE_LIST +" where user_name = '"+ HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext).toUpperCase()+"'",null);

            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside getAllPhoneDataFromtable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    data =  new JSONObject();
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside getAllPhoneDataFromtable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");


                        if(data.getString(columnNames[index]).equalsIgnoreCase("null"))
                            data.put(columnNames[index],"");


                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);

                    data.put("uploaded_time", MpcUtil.getcurrentTime(14));
                    dataArray.put(data);

                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console("Csv created successfully");
            closeDB();
            return dataArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return new JSONArray();
        }
    }


    public synchronized JSONArray getSurveyDetailsArray(Context appContext){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();

            String querry = "SELECT * FROM  "+ MpcUtil.HHID_survey_details +" where  user_name = '"+ HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext).toUpperCase()+"'";
//            String querry = "SELECT * FROM  "+MpcUtil.HHID_Survey +" where user_name = '"+ HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext).toUpperCase()+"'";

            DebugLog.console("[HouseHoldDataBaseHelper] inside getSurveyDetailsArray() querry"+querry);
            Cursor curCSV = db.rawQuery(querry,null);

            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside getSurveyDetailsArray() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    data =  new JSONObject();
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside getSurveyDetailsArray() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");

                        if(data.getString(columnNames[index]).equalsIgnoreCase("null"))
                            data.put(columnNames[index],"");

                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);
                    data.put("uploaded_time", MpcUtil.getcurrentTime(14));
                    dataArray.put(data);

                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console(" created successfully");
            closeDB();
            return dataArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside getSurveyDetailsArray");
            closeDB();
            return new JSONArray();
        }
    }



    public synchronized JSONArray getAllDataFromtable(Context appContext){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();

            String querry = "SELECT * FROM  "+ MpcUtil.HHID_Survey +" where ( user_name = '"+ HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext).toUpperCase()+"' AND isSynced IS NULL ) OR isSynced = '"+""+"'";
//            String querry = "SELECT * FROM  "+MpcUtil.HHID_Survey +" where user_name = '"+ HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext).toUpperCase()+"'";

            DebugLog.console("[HouseHoldDataBaseHelper] inside getAllDataFromtable() querry"+querry);
            Cursor curCSV = db.rawQuery(querry,null);

            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    data =  new JSONObject();
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");

                        if(data.getString(columnNames[index]).equalsIgnoreCase("null"))
                            data.put(columnNames[index],"");

                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);
                    data.put("uploaded_time", MpcUtil.getcurrentTime(14));
                    dataArray.put(data);

                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console("Csv created successfully");
            closeDB();
            return dataArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return new JSONArray();
        }
    }



    public synchronized JSONObject getDataFromtable(Context appContext,String village_Code,String hhid){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ MpcUtil.HHID_Survey +" where PSU_code = '"+village_Code+"' AND HH_id = '"+hhid+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() village_Code "+village_Code);
            DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() hhid"+hhid);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");
//DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+data.toString());
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);


                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console("Csv created successfully");
            closeDB();
            return data;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside storeInBlockedList");
            closeDB();
            return new JSONObject();
        }
    }


    public synchronized boolean update_isSynced_against_this_user(Context appContext) {
        try {



            //  boolean runUpdate_Querry = hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside update_isSynced_against_this_user() runUpdate_Querry");


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("isSynced", "yes".toUpperCase());
            cv.put("uploaded_time", MpcUtil.getcurrentTime(14));

            db.update(MpcUtil.HHID_Survey, cv, "user_name = '"+ HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext).toUpperCase()+"'", null);

//            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q8"+hhid_q8.toString());
//            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_screen_four() hhid_q9"+hhid_q9.toString());




            closeDB();
            return  true;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }



    public synchronized int hhid_getCountOfhhid_having_girls(String PSU_code,String hhid) {
        Cursor cursor = null;
        boolean  result = false;
        int count = 0;
        ArrayList<String> hhid_list = new ArrayList<>();
        try {
            openDB();
            //    String query = "Select HH_id from "+MpcUtil.HHID_Survey+" where hhid_q10 is not NULL AND  hhid_q10 != '' AND  PSU_code = '"+PSU_code.toUpperCase()+"' AND HH_id IN ( "+hhid+" )  AND ( hhid_q11 < hhid_q10  OR  hhid_q12 < hhid_q10  )";
            String query = "Select HH_id from "+ MpcUtil.HHID_Survey+" where hhid_q10 is not NULL AND  hhid_q10 != '0' AND hhid_q10 != '00' AND hhid_q10 != '000' AND PSU_code = '"+PSU_code.toUpperCase()+"' AND HH_id IN ( "+hhid+" )  AND (  hhid_q10 > hhid_q11 OR  hhid_q10 > hhid_q12  )";
            DebugLog.console("[HouseHoldDataBaseHelper] inside stat hhid_getCountOfhhid_having_girls() "+query);
            cursor = db.rawQuery(query, null);
            count = cursor.getCount();
            DebugLog.console("[HouseHoldDataBaseHelper] inside stat hhid_getCountOfhhid_having_girls() "+ count);
//            if (cursor.getCount() !=0) {
//                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_getCountOfhhid_having_girls() count "+cursor.getCount());
//                result = true;
//                while (cursor.moveToNext()) {
//
//                    String id = cursor.getString(cursor.getColumnIndex("HH_id"));
//                    hhid_list.add( id );
//                    DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_getCountOfhhid_having_girls() "+id);
//                }
//
//
//
//
//            } else {
//                DebugLog.console("[DataBaseProcessor] inside hhid_getCountOfhhid_having_girls() new hhid");
//
//                result =  false;
//            }
            closeDB(cursor);
            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside stat hhid_isHHCovered() Exception is : " + e.toString());
            closeDB(cursor);
            return count;
        }
    }


    public synchronized int hhid_getCountOfhhid_having_phoneNumber(String PSU_code,String hhid) {
        Cursor cursor = null;
        boolean  result = false;
        int count = 0;
        ArrayList<String> hhid_list = new ArrayList<>();
        try {
            openDB();
            String query = "Select HH_id from "+ MpcUtil.HHID_Survey_PHONE_LIST+" where hhid_phone_number != '"+""+ "' AND PSU_code = '"+PSU_code.toUpperCase()+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside stat hhid_getCountOfhhid_having_phoneNumber() "+query);
            cursor = db.rawQuery(query, null);
            count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat hhid_getCountOfhhid_having_phoneNumber() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    String id = cursor.getString(cursor.getColumnIndex("HH_id"));
                    hhid_list.add( id );
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat hhid_getCountOfhhid_having_phoneNumber() hhid "+id);
                }

                closeDB(cursor);
                if (count !=0) {
                    String hhidlisthaving_numbers = android.text.TextUtils.join(",", hhid_list);
                    count = hhid_getCountOfhhid_having_girls(PSU_code,hhidlisthaving_numbers);

                }


            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat hhid_getCountOfhhid_having_girls() new hhid");
                closeDB(cursor);
                result =  false;
            }


            update_survey_details(PSU_code,HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(mContext,PSU_code+"_hhid"),count);


            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside stat hhid_isHHCovered() Exception is : " + e.toString());
            closeDB(cursor);
            return count;
        }
    }

    private void update_survey_details(String PSU_code, String peshawarLastHHIDagainstPSUAndStructID, int count) {

        long isInserted =0;
        try {
            DebugLog.console("[HouseHoldDataBaseHelper] inside survey_details update_survey_details() update_survey_details"+peshawarLastHHIDagainstPSUAndStructID);

            ContentValues cv = new ContentValues();

            cv.put("PSU_code", PSU_code.toUpperCase());
            cv.put("total_hhid_covered", peshawarLastHHIDagainstPSUAndStructID.toUpperCase());
            cv.put("eligible_hhid", count+"".toUpperCase());
            cv.put("build_no", BuildConfig.VERSION_NAME);
            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14).toUpperCase());
            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
            cv.put("user_name", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());



            boolean runUpdate_Querry = hhid_isPSU_INCoveredLIST(PSU_code);
            openDB();
            if(!runUpdate_Querry){
                DebugLog.console("[HouseHoldDataBaseHelper] inside survey_details insert queryy ");

                isInserted = db.insert(MpcUtil.HHID_survey_details, null, cv);

            }else{

                DebugLog.console("[HouseHoldDataBaseHelper] inside survey_details update querry ");
                isInserted = db.update(MpcUtil.HHID_survey_details, cv, "PSU_code = '"+PSU_code.toUpperCase()+"'", null);

            }
            closeDB();
            DebugLog.console("[HouseHoldDataBaseHelper] inside survey_details update_survey_details isInserted "+isInserted);

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[HouseHoldDataBaseHelper] inside survey_details Exception is :"+e.toString());
        }
    }


    public synchronized boolean addUser(String District_Code, String District_Name , String Tehsil_code , String Tehsil_Name , String PSU_code , String PSU_name , String Structure_id , String HH_id , String q1_headname , String q2_headfathername , String q3_Hhmember, String q4_migration , String q5_Add_house , String q5_Add_street , String q5_Add_Neighborhood , String q5_Add_Landmark, String user_name , String start_date_time, String end_date_time) {
        try {
            openDB();

            ContentValues cv = new ContentValues();
            cv.put("District_Code", District_Code.toUpperCase());
            cv.put("District_Name", District_Name.toUpperCase());
            cv.put("Tehsil_code", Tehsil_code.toUpperCase());
            cv.put("Tehsil_Name", Tehsil_Name.toUpperCase());
            cv.put("PSU_code", PSU_code.toUpperCase());
            cv.put("PSU_name", PSU_name.toUpperCase());
            cv.put("Structure_id", Structure_id.toUpperCase());
            cv.put("HH_id", HH_id.toUpperCase());
            cv.put("q1_headname", q1_headname.toUpperCase());
            cv.put("q2_headfathername", q2_headfathername.toUpperCase());
            cv.put("q3_Hhmember", q3_Hhmember.toUpperCase());
            cv.put("q4_migration", q4_migration.toUpperCase());
            cv.put("q5_Add_house", q5_Add_house.toUpperCase());
            cv.put("q5_Add_street", q5_Add_street.toUpperCase());
            cv.put("q5_Add_Neighborhood", q5_Add_Neighborhood.toUpperCase());
            cv.put("q5_Add_Landmark", q5_Add_Landmark.toUpperCase());
            cv.put("user_name", user_name.toUpperCase());
            cv.put("start_date_time", start_date_time.toUpperCase());
            cv.put("end_date_time", end_date_time.toUpperCase());
            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            long isInserted = db.insert(MpcUtil.HHID_Survey, null, cv);
            closeDB();
            if (isInserted != -1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside addUser() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }

    public synchronized  ArrayList<String>  aghhid_getNumbersDataagainstvillageAndhhid(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Select Number");
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();
            Cursor cursor = db.rawQuery("SELECT hhid_phone_number FROM  "+DatabaseAdapter.AGHHID_SampleTable +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'",null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    String id = cursor.getString(cursor.getColumnIndex("hhid_phone_number"));
                    spinnerArray.add( id );
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() hhid "+id);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+spinnerArray.size());
            return spinnerArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return spinnerArray;
        }
    }


    public synchronized JSONObject aghhid_getDataFromtable_memwith_id(Context appContext,String tableName , String village_id,String hhid,String memID){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ tableName +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'  AND  d_1 =  '"+memID+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable_memwith_id() village_id "+village_id);
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable_memwith_id() hhid"+hhid);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable_memwith_id() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable_memwith_id() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");
//DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+data.toString());
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);


                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console(" executed successfully");
            closeDB();
            return data;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getDataFromtable_memwith_id");
            closeDB();
            return new JSONObject();
        }
    }



    public synchronized JSONObject aghhid_getDataFromtable(Context appContext,String tableName , String village_id,String hhid){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  new JSONObject();
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ tableName +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable() village_id "+village_id);
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable() hhid"+hhid);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getDataFromtable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");
//DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+data.toString());
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);


                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console(" executed successfully");
            closeDB();
            return data;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getDataFromtable");
            closeDB();
            return new JSONObject();
        }
    }



    public synchronized boolean aghhid_delete_hhids_withempty_member(String village_id,String hhid) {
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_delete_hhid() runUpdate_Querry"+runUpdate_Querry);


            openDB();

//            ContentValues cv = new ContentValues();
//            cv.put("PSU_code", PSU_code.toUpperCase());
//            cv.put("HH_id", HH_id.toUpperCase());
//            cv.put("hhid_phone_number", number.toUpperCase());
//            cv.put("status", status.toUpperCase());
//            cv.put("build_no", BuildConfig.VERSION_NAME);
//            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14));
//            cv.put("user_name", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());
//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            int id =  db.delete(DatabaseAdapter.aghhid_section_d_table,  "hhid=" + hhid.toUpperCase()+ " AND village_id = '"+village_id.toUpperCase()+"' AND d_1 = ''  OR  d_1 IS NULL",null);
            DebugLog.console("[HouseHoldDataBaseHelper] HHID_Survey_PHONE_LIST aghhid_delete_hhid aghhid_delete_hhid  delete() "+id);




            closeDB();
            return  true;

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside aghhid_delete_hhid() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }



    public synchronized boolean aghhid_delete_hhid(String village_id,String hhid,JSONObject member) {
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_delete_hhid() runUpdate_Querry"+runUpdate_Querry);


            openDB();

//            ContentValues cv = new ContentValues();
//            cv.put("PSU_code", PSU_code.toUpperCase());
//            cv.put("HH_id", HH_id.toUpperCase());
//            cv.put("hhid_phone_number", number.toUpperCase());
//            cv.put("status", status.toUpperCase());
//            cv.put("build_no", BuildConfig.VERSION_NAME);
//            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14));
//            cv.put("user_name", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());
//            cv.put("device_id", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            int id =  db.delete(DatabaseAdapter.aghhid_section_d_table,  "hhid=" + hhid.toUpperCase()+ " AND village_id = '"+village_id.toUpperCase()+"' AND d_1 = '"+member.getInt("d_1")+"".toUpperCase()+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] HHID_Survey_PHONE_LIST aghhid_delete_hhid aghhid_delete_hhid  delete() "+id);




            closeDB();
            return  true;

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside aghhid_delete_hhid() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }

  public synchronized boolean aghhid_update_member_list(String village_id,String hhid, JSONObject member) {
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_update_member_list() runUpdate_Querry"+runUpdate_Querry);


            openDB();


            ContentValues cv = new ContentValues();
            cv.put("village_id", village_id.toUpperCase());
            cv.put("hhid", hhid.toUpperCase());
            cv.put("d_1", member.getInt("d_1")+"".toUpperCase());
            cv.put("d_2", member.getString("d_2").toUpperCase());
            cv.put("d_3", member.getString("d_3").toUpperCase());
            cv.put("d_4", member.getInt("d_4")+"".toUpperCase());
            cv.put("d_5", member.getString("d_5").toUpperCase());
            cv.put("d_5_other",member.getString("d_5_other").toUpperCase());
            cv.put("d_6",member.getString("d_6").toUpperCase());
            cv.put("d_7", member.getString("d_7").toUpperCase());
            cv.put("d_8", member.getString("d_8").toUpperCase());
            cv.put("build_no", BuildConfig.VERSION_NAME);
            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14));
            cv.put("rcons_user", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());
            cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());


            long  isInserted =  db.update(DatabaseAdapter.aghhid_section_d_table,  cv,"hhid=" + hhid.toUpperCase()+ " AND village_id = '"+village_id.toUpperCase()+"' AND d_1 = '"+member.getInt("d_1")+"".toUpperCase()+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] HHID_Survey_PHONE_LIST aghhid_update_member_list aghhid_update_member_list  aghhid_update_member_list() "+isInserted);


            closeDB();
            if (isInserted != -1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_screen_six() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }


    public synchronized boolean aghhid_insert_member_list(String village_id,String hhid, JSONObject member) {
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered(PSU_code,HH_id);

            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_insert_member_list() runUpdate_Querry"+runUpdate_Querry);


            openDB();


            ContentValues cv = new ContentValues();
            cv.put("village_id", village_id.toUpperCase());
            cv.put("hhid", hhid.toUpperCase());
            cv.put("d_1", member.getInt("d_1")+"".toUpperCase());
            cv.put("d_2", member.getString("d_2").toUpperCase());
            cv.put("d_3", member.getString("d_3").toUpperCase());
            cv.put("d_4", member.getInt("d_4")+"".toUpperCase());
            cv.put("d_5", member.getString("d_5").toUpperCase());
            cv.put("d_5_other",member.getString("d_5_other").toUpperCase());
            cv.put("d_6",member.getString("d_6").toUpperCase());
            cv.put("d_7", member.getString("d_7").toUpperCase());
            cv.put("d_8", member.getString("d_8").toUpperCase());
            cv.put("build_no", BuildConfig.VERSION_NAME);
            cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14));
            cv.put("rcons_user", HHIDConfigurations.getPeshawarCurrentLoggedInUser(mContext).toUpperCase());
            cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());

            long  isInserted = db.insert(DatabaseAdapter.aghhid_section_d_table, null, cv);
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_insert_member_list isInserted() "+isInserted);



            closeDB();
            if (isInserted != -1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_screen_six() Exception is : " + e.toString());
            closeDB();
            return false;
        }
    }

    public synchronized JSONArray agghhid_getDataFromMemberTableAgeGreatethen17(Context appContext,String village_id,String hhid){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  null;
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"' AND  d_4 > 17 ",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() village_id "+village_id);
            DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() hhid"+hhid);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    data =  new JSONObject();
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");
//DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+data.toString());
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);
                    dataArray.put(data);

                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console(" created successfully");
            closeDB();
            return dataArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside agghhid_getDataFromMemberTable");
            closeDB();
            return new JSONArray();
        }
    }



    public synchronized JSONArray agghhid_getDataFromMemberTable(Context appContext,String village_id,String hhid){
        long rowId = 1;
        try {
            openDB();
            JSONArray dataArray = new JSONArray();
            JSONObject data =  null;
            Cursor curCSV = db.rawQuery("SELECT * FROM  "+ DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'",null);
            DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() village_id "+village_id);
            DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() hhid"+hhid);
            if(curCSV.getCount()!=0) {

                //file.createNewFile();
//                String fileName = FileManager.createFileName(appContext, "csv" + "", HHIDConfigurations.getPeshawarCurrentLoggedInUser(appContext)+"_PSU_DATA");
//                FileManager.createNewFile(appContext, fileName, "");


//                CSVWriter csvWrite = new CSVWriter(new FileWriter(appContext.getFileStreamPath(fileName)));


//                csvWrite.writeNext(curCSV.getColumnNames());
                String columnNames[] = curCSV.getColumnNames();
                DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() columnNames"+columnNames.length);

                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    data =  new JSONObject();
                    int index = 0;
                    while (index < count) {
                        //  arrStr[index] = curCSV.getString(index);
                        DebugLog.console("[HouseHoldDataBaseHelper] inside agghhid_getDataFromMemberTable() "+columnNames[index]);

                        data.put(columnNames[index],curCSV.getString(index)+"");
//DebugLog.console("[HouseHoldDataBaseHelper] inside getDataFromtable() "+data.toString());
                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
//                    csvWrite.writeNext(arrStr);
                    dataArray.put(data);

                }
//                csvWrite.close();

            }
            curCSV.close();
            DebugLog.console(" created successfully");
            closeDB();
            return dataArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside agghhid_getDataFromMemberTable");
            closeDB();
            return new JSONArray();
        }
    }





    public synchronized  boolean  aghhid_checkIfheadalreadydefined(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Show Previous Numbers");
        int id =0;
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();
            Cursor cursor = db.rawQuery("SELECT * FROM  "+DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"' AND d_5 = '1'",null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getmaxmemberindhhid() count "+cursor.getCount());
                result = true;
//                while (cursor.moveToNext()) {
//
//                    id = cursor.getInt(cursor.getColumnIndex("max"));
//                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getmaxmemberindhhid() hhid "+id);
//                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+spinnerArray.size());
            return result;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return result;
        }
    }






    public synchronized  int  aghhid_getmaxmemberindhhid(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Show Previous Numbers");
        int id =0;
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();
            Cursor cursor = db.rawQuery("SELECT MAX(d_1) as max FROM  "+DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'",null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getmaxmemberindhhid() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    id = cursor.getInt(cursor.getColumnIndex("max"));
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getmaxmemberindhhid() hhid "+id);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+spinnerArray.size());
            return id;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return 0;
        }
    }

    public synchronized  ArrayList<String>  aghhid_getgirlsgainstvillageAndhhid(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Select child not went to school");
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String query = "SELECT d_2,d_4,d_7,d_8 FROM  "+DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'   AND d_3 = '2' AND d_4 IN (9,10,11,12,13,14,15,16,17,18,19) AND ( d_7 IN (3,4) OR d_8 IN (3,4))  ";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getgirlsgainstvillageAndhhid() query "+query);
            Cursor cursor = db.rawQuery(query,null);



            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    String name = cursor.getString(cursor.getColumnIndex("d_2"));
                    String age = cursor.getString(cursor.getColumnIndex("d_4"));
                    String q7 = cursor.getString(cursor.getColumnIndex("d_7"));
                    String q8 = cursor.getString(cursor.getColumnIndex("d_8"));
                    spinnerArray.add( name +"  |  Age:  " +age+" |  Q7:  " +q7+" |  Q8:  " +q8);
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() hhid "+name);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+spinnerArray.size());
            return spinnerArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return spinnerArray;
        }
    }



    public synchronized  ArrayList<String>  aghhid_getgirlsgainstvillageAndhhid_second_op(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Select any child between 9 to 19");
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String query = "SELECT d_2,d_4,d_7,d_8 FROM  "+DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'   AND d_3 = '2' AND d_4 IN (9,10,11,12,13,14,15,16,17,18,19)";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getgirlsgainstvillageAndhhid() query "+query);
            Cursor cursor = db.rawQuery(query,null);



            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("d_2"));
                    String age = cursor.getString(cursor.getColumnIndex("d_4"));
                    String q7 = cursor.getString(cursor.getColumnIndex("d_7"));
                    String q8 = cursor.getString(cursor.getColumnIndex("d_8"));
                    spinnerArray.add( name +"  |  Age:  " +age+" |  Q7:  " +q7+" |  Q8:  " +q8);
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() hhid "+name);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+spinnerArray.size());
            return spinnerArray;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return spinnerArray;
        }
    }


    public synchronized  String  aghhid_get_headname_selected_in_sample(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        //  ArrayList<String> spinnerArray = new ArrayList<String>();
        // spinnerArray.add("Show Previous Numbers");
        String name = "";
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String querry = "SELECT hhid_father_name  FROM  "+DatabaseAdapter.AGHHID_SampleTable +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_get_headname_selected_in_sample() querry "+querry);
            Cursor cursor = db.rawQuery(querry,null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_get_headname_selected_in_sample() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    name = cursor.getString(cursor.getColumnIndex("hhid_father_name"));
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_get_headname_selected_in_sample() hhid "+name);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+count);
            return name;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return "";
        }
    }

    public synchronized  String  aghhid_get_member_selected_in_e13(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        //  ArrayList<String> spinnerArray = new ArrayList<String>();
        // spinnerArray.add("Show Previous Numbers");
        String name = "";
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String querry = "SELECT e13_name  FROM  "+DatabaseAdapter.aghhid_section_e_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_get_member_selected_in_e1() querry "+querry);
            Cursor cursor = db.rawQuery(querry,null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_get_member_selected_in_e1() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    name = cursor.getString(cursor.getColumnIndex("e13_name"));
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_get_member_selected_in_e1() hhid "+name);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+count);

            if(name==null)
                name="";


            return name;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return "";
        }
    }


    public synchronized  String  aghhid_get_member_selected_in_e1(Context appContext, String village_id,String hhid){
        long rowId = 1;
        boolean result =false;
        //  ArrayList<String> spinnerArray = new ArrayList<String>();
        // spinnerArray.add("Show Previous Numbers");
        String name = "";
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String querry = "SELECT e_1  FROM  "+DatabaseAdapter.aghhid_section_e_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_get_member_selected_in_e1() querry "+querry);
            Cursor cursor = db.rawQuery(querry,null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_get_member_selected_in_e1() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    name = cursor.getString(cursor.getColumnIndex("e_1"));
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_get_member_selected_in_e1() hhid "+name);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+count);
            if(name==null)
                name="";


            return name;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return "";
        }
    }

    public synchronized  int  aghhid_memberid_against_name(Context appContext, String village_id,String hhid, String name){
        long rowId = 1;
        boolean result =false;
        //  ArrayList<String> spinnerArray = new ArrayList<String>();
        // spinnerArray.add("Show Previous Numbers");
        int id =0;
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String querry = "SELECT d_1  FROM  "+DatabaseAdapter.aghhid_section_d_table +" where village_id = '"+village_id+"' AND hhid = '"+hhid+"' AND d_2 =  '"+name+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_memberid_against_name() querry "+querry);
            Cursor cursor = db.rawQuery(querry,null);
            int  count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getmaxmemberindhhid() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    id = cursor.getInt(cursor.getColumnIndex("d_1"));
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getmaxmemberindhhid() hhid "+id);
                }

                closeDB(cursor);



            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");
                closeDB(cursor);
                result =  false;
            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+count);
            return id;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return 0;
        }
    }

    public synchronized boolean hhid_insert_data_d9(String village_id ,String hhid , String d_9_resp_name, String d_9_resp_id,String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = true;//hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("d_9_resp_name", d_9_resp_name.toUpperCase());
            cv.put("d_9_resp_id", d_9_resp_id.toUpperCase());
            cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
            cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_d_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_d_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_e1(String village_id ,String hhid , String e_1,String e_1_other, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_1", e_1.toUpperCase());
            cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
                cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_e13(String village_id ,String hhid , String e_1,String e_1_other, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e13_name", e_1.toUpperCase());
            cv.put("e13_id", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_e4(String village_id ,String hhid , String e_4, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_4", e_4.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }




    public synchronized boolean hhid_insert_data_e5(String village_id ,String hhid , String e_5, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_5", e_5.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_e6(String village_id ,String hhid , String e_6, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_6", e_6.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_e7(String village_id ,String hhid , String e_7, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_7", e_7.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_e9(String village_id ,String hhid , String e_9, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_9", e_9.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_e15(String village_id ,String hhid , String aghhid_e_15,String e15_day, String e15_month, String e15_hh, String e15_mm, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_15", aghhid_e_15.toUpperCase());
            cv.put("e15_day", e15_day.toUpperCase());
            cv.put("e15_month", e15_month.toUpperCase());
            cv.put("e15_hh", e15_hh.toUpperCase());
            cv.put("e15_mm", e15_mm.toUpperCase());





            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_e11(String village_id ,String hhid , String aghhid_e_11,String e11_day, String e11_month, String e11_hh, String e11_mm, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_11", aghhid_e_11.toUpperCase());
            cv.put("e11_day", e11_day.toUpperCase());
            cv.put("e11_month", e11_month.toUpperCase());
            cv.put("e11_hh", e11_hh.toUpperCase());
            cv.put("e11_mm", e11_mm.toUpperCase());


            if(aghhid_e_11.equalsIgnoreCase("2")){
                cv.put("e11_day", "");
                cv.put("e11_month", "");
                cv.put("e11_hh", "");
                cv.put("e11_mm", "");
                cv.put("e12", "");
                cv.put("e13_id", "");
                cv.put("e13_name", "");
                cv.put("e14", "");
                cv.put("e_15", "");
                cv.put("e15_day", "");
                cv.put("e15_month", "");
                cv.put("e15_hh", "");
                cv.put("e15_mm", "");
            }


            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_e12(String village_id ,String hhid , String aghhid_e_12, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e12", aghhid_e_12.toUpperCase());

            if(aghhid_e_12.equalsIgnoreCase("2")){
                cv.put("e13_id", "");
                cv.put("e13_name", "");
                cv.put("e14", "");
                cv.put("e_15", "");
                cv.put("e15_day", "");
                cv.put("e15_month", "");
                cv.put("e15_hh", "");
                cv.put("e15_mm", "");
            }



            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_e14(String village_id ,String hhid , String e14, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            if(e14.equalsIgnoreCase("1")){
                cv.put("e_15", "");
                cv.put("e15_day", "");
                cv.put("e15_month", "");
                cv.put("e15_hh", "");
                cv.put("e15_mm", "");
            }

            cv.put("e14", e14.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_e10(String village_id ,String hhid , String e_10, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            if(e_10.equalsIgnoreCase("1")||e_10.equalsIgnoreCase("-777")|| e_10.equalsIgnoreCase("-99")){
                cv.put("e_11", "");
                cv.put("e11_day", "");
                cv.put("e11_month", "");
                cv.put("e11_hh", "");
                cv.put("e11_mm", "");
                cv.put("e12", "");
                cv.put("e13_id", "");
                cv.put("e13_name", "");
                cv.put("e14", "");
                cv.put("e_15", "");
                cv.put("e15_day", "");
                cv.put("e15_month", "");
                cv.put("e15_hh", "");
                cv.put("e15_mm", "");
            }


            cv.put("e_10", e_10.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }


    public synchronized boolean hhid_insert_data_e2(String village_id ,String hhid , String e_2, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            if(e_2.equalsIgnoreCase("1") || e_2.equalsIgnoreCase("-777") || e_2.equalsIgnoreCase("-99")){
                cv.put("e_3", "");
                cv.put("e_3_2", "");
                cv.put("e_3_3", "");
                cv.put("e_3_4", "");
                cv.put("e_3_5", "");
                cv.put("e_3_6", "");
                cv.put("e_3_7", "");
                cv.put("e_3_8", "");
                cv.put("e_3_9", "");
                cv.put("e_3_10", "");
                cv.put("e_3_11", "");
                cv.put("e_3_12", "");
                cv.put("e_3_13", "");
                cv.put("e_3_14", "");
                cv.put("e_3_14_other", "");
                cv.put("e_3_15", "");
                cv.put("e_3_16", "");
                cv.put("e_3_17", "");
                cv.put("e_3_18", "");

            }


            cv.put("e_2", e_2.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_e3(String village_id ,String hhid , String aghhid_e_3,  String aghhid_e_3_2,  String aghhid_e_3_3,  String aghhid_e_3_4,  String aghhid_e_3_5,  String aghhid_e_3_6,  String aghhid_e_3_7,  String aghhid_e_3_8,  String aghhid_e_3_9,  String aghhid_e_3_10,  String aghhid_e_3_11,  String aghhid_e_3_12, String aghhid_e_3_13, String aghhid_e_3_14, String aghhid_e_3_14_other,  String aghhid_e_3_15, String aghhid_e_3_16,  String aghhid_e_3_17, String  aghhid_e_3_18, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_3",aghhid_e_3);
            cv.put("e_3_2",aghhid_e_3_2);
            cv.put("e_3_3",aghhid_e_3_3);
            cv.put("e_3_4",aghhid_e_3_4);
            cv.put("e_3_5",aghhid_e_3_5);
            cv.put("e_3_6",aghhid_e_3_6);
            cv.put("e_3_7",aghhid_e_3_7);
            cv.put("e_3_8",aghhid_e_3_8);
            cv.put("e_3_9",aghhid_e_3_9);
            cv.put("e_3_10",aghhid_e_3_10);
            cv.put("e_3_11",aghhid_e_3_11);
            cv.put("e_3_12",aghhid_e_3_12);
            cv.put("e_3_13",aghhid_e_3_13);
            cv.put("e_3_14",aghhid_e_3_14);
            cv.put("e_3_14_other",aghhid_e_3_14_other);
            cv.put("e_3_15",aghhid_e_3_15);
            cv.put("e_3_16",aghhid_e_3_16);
            cv.put("e_3_17",aghhid_e_3_17);
            cv.put("e_3_18",aghhid_e_3_18);
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }


    public synchronized boolean hhid_insert_data_e8(String village_id ,String hhid ,  String aghhid_e_8,String aghhid_e_8_1,  String aghhid_e_8_2,  String aghhid_e_8_3,  String aghhid_e_8_4,  String aghhid_e_8_4_other,  String aghhid_e_8_15, String aghhid_e_8_16,  String aghhid_e_8_17, String  aghhid_e_8_18, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("e_8",aghhid_e_8);
            cv.put("e_8_1",aghhid_e_8_1);
            cv.put("e_8_2",aghhid_e_8_2);
            cv.put("e_8_3",aghhid_e_8_3);
            cv.put("e_8_4",aghhid_e_8_4);

            cv.put("e_8_other",aghhid_e_8_4_other);
            cv.put("e_8_88",aghhid_e_8_15);
            cv.put("e_8_98",aghhid_e_8_16);
            cv.put("e_8_99",aghhid_e_8_17);
            cv.put("e_8_777",aghhid_e_8_18);
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_e_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_e_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_h1(String village_id ,String hhid , String h_1, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_h(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("h_1", h_1.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
                cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_h_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_h_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }


    public synchronized boolean hhid_insert_data_h2(String village_id ,String hhid , String h_2, String user_name , String start_date_time) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = hhid_isHHCovered_for_h(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_h2() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            cv.put("h_2", h_2.toUpperCase());
            // cv.put("e_1_other", e_1_other.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", start_date_time.toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", user_name.toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_h_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_h_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_h2() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_h2() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }

    public synchronized boolean hhid_insert_data_section_m(String village_id, String hhid, String phoneNumber, String survey_status,String m3_answered, String m4_answered, String m4_answered_other,String e11_day,String e11_month,String e11_hh,String e11_mm,String tryNumber,String reportComments) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = false;//hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_section_m() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            //   cv.put("d_9_resp_name", d_9_resp_name.toUpperCase());
            //    cv.put("d_9_resp_id", d_9_resp_id.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("phone_number", phoneNumber.toUpperCase());
                cv.put("try_no", tryNumber.toUpperCase());
                cv.put("m1", survey_status.toUpperCase());
                cv.put("m2_day", e11_day);
                cv.put("m2_month", e11_month.toUpperCase());
                cv.put("m2_hh", e11_hh.toUpperCase());
                cv.put("m2_mm", e11_mm.toUpperCase());
                cv.put("m3", m3_answered.toUpperCase());
                cv.put("m4", m4_answered.toUpperCase());
                cv.put("m4_other", m4_answered_other.toUpperCase());
                cv.put("comments", reportComments.toUpperCase());
                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14).toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", RConsUtils.getUserName());
                cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
                cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_m_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_section_m() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_m_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_section_m() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }



    public synchronized boolean hhid_insert_data_section_ad_m(String village_id, String hhid, String phoneNumber, String survey_status,String m3_answered, String m4_answered, String m4_answered_other,String e11_day,String e11_month,String e11_hh,String e11_mm,String tryNumber,String reportComments) {
        boolean updated =false;
        try {



            boolean runUpdate_Querry = false;//hhid_isHHCovered_for_e(village_id,hhid);

            DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_section_ad_m() runUpdate_Querry"+runUpdate_Querry);


            openDB();

            ContentValues cv = new ContentValues();

            //   cv.put("d_9_resp_name", d_9_resp_name.toUpperCase());
            //    cv.put("d_9_resp_id", d_9_resp_id.toUpperCase());
//            cv.put("c1_given_number", c1_given_number.toUpperCase());
//            cv.put("hhid_q3", hhid_q3.toUpperCase());
//            cv.put("hhid_q4", hhid_q4.toUpperCase());
//            cv.put("hhid_q4_a", hhid_q4_a.toUpperCase());

//            if (!hhid_q3.equalsIgnoreCase("1")){
//
//                cv.put("end_date_time", MpcUtil.getcurrentTime(14).toUpperCase());
//
//            }



            long isInserted =0;
            if(!runUpdate_Querry){

                cv.put("village_id", village_id.toUpperCase());
                cv.put("hhid", hhid.toUpperCase());
                cv.put("phone_number", phoneNumber.toUpperCase());
                cv.put("try_no", tryNumber.toUpperCase());
                cv.put("m1", survey_status.toUpperCase());
                cv.put("m2_day", e11_day);
                cv.put("m2_month", e11_month.toUpperCase());
                cv.put("m2_hh", e11_hh.toUpperCase());
                cv.put("m2_mm", e11_mm.toUpperCase());
                //  cv.put("m3", m3_answered.toUpperCase());
                cv.put("m3", m4_answered.toUpperCase());
                cv.put("m3_other", m4_answered_other.toUpperCase());
                cv.put("comments", reportComments.toUpperCase());

                cv.put("build_no", BuildConfig.VERSION_NAME);
                cv.put("insert_or_updated_in_phone_at", MpcUtil.getcurrentTime(14).toUpperCase());
                cv.put("deviceid", MpcUtil.getMAC(AppController.getInstance()).toUpperCase());
                cv.put("rcons_user", RConsUtils.getUserName());
                cv.put("enum_name", RConsUtils.getEnumName().toUpperCase());
                cv.put("enum_code", RConsUtils.getEnumCode().toUpperCase());
                isInserted = db.insert(DatabaseAdapter.aghhid_section_ad_m_table, null, cv);
                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_section_ad_m() isInserted"+isInserted);

            }else{
                db.update(DatabaseAdapter.aghhid_section_ad_m_table, cv, "hhid =" + hhid.toUpperCase()+ " AND village_id = "+village_id.toUpperCase()+" ", null);

                DebugLog.console("[HouseHoldDataBaseHelper] inside hhid_insert_data_section_ad_m() update");
                updated = true;
            }

            closeDB();


            if (isInserted != -1) {
                updated =  true;
            }

            return  updated;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside hhid_insert_data_section_ad_m() Exception is : " + e.toString());
            closeDB();
            return  updated;
        }
    }




    public synchronized int aghhid_gethhid_having_successful_status(Context appContext,String rcons_user) {
        Cursor cursor = null;
        boolean  result = false;
        int count = 0;
        ArrayList<String> hhid_list = new ArrayList<>();
        try {
            openDB();
            String query = "Select hhid from "+ DatabaseAdapter.aghhid_section_ad_m_table+" where m1 = '"+"1"+ "' AND rcons_user = '"+rcons_user.toUpperCase()+"'";
            DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_gethhid_having_successful_status() "+query);
            cursor = db.rawQuery(query, null);
            count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_gethhid_having_successful_status() count "+cursor.getCount());
                result = true;
                while (cursor.moveToNext()) {

                    String id = cursor.getString(cursor.getColumnIndex("hhid"));
                    hhid_list.add( id );
                    DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_gethhid_having_successful_status() hhid "+id);
                }

                closeDB(cursor);
                if (count !=0) {
                    String hhidlisthaving_success = android.text.TextUtils.join(",", hhid_list);
                    count = aghhid_getgirlscountgainst(rcons_user,hhidlisthaving_success);

                }


            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_gethhid_having_successful_status() new hhid");
                closeDB(cursor);
                result =  false;
            }


           // update_survey_details(PSU_code,HHIDConfigurations.getPeshawarLastHHIDagainstPSUAndStructID(mContext,PSU_code+"_hhid"),count);


            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(mContext).writeLog("[" + this.getClass().getSimpleName() + "] inside aghhid_gethhid_having_successful_status hhid_isHHCovered() Exception is : " + e.toString());
            closeDB(cursor);
            return count;
        }
    }






    public synchronized int aghhid_getgirlscountgainst( String rcons_user,String hhidlisthaving_success){
        long rowId = 1;
        boolean result =false;
        int  count =0;
                ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Select child not went to school");
        try {
            openDB();
            // JSONArray dataArray = new JSONArray();
            //JSONObject data =  new JSONObject();

            String query = "SELECT d_2,d_4,d_7,d_8 FROM  "+DatabaseAdapter.aghhid_section_d_table +" where rcons_user = '"+rcons_user+"'  AND d_3 = '2' AND d_4 IN (9,10,11,12,13,14,15,16,17,18,19) AND hhid IN ("+hhidlisthaving_success+") AND ( d_7 IN (3,4) OR d_8 IN (3,4)) group by hhid ";
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getgirlsgainstvillageAndhhid() query "+query);
            Cursor cursor = db.rawQuery(query,null);



              count = cursor.getCount();
            if (count !=0) {
                DebugLog.console("[HouseHoldDataBaseHelper] inside stat aghhid_getNumbersDataagainstvillageAndhhid() count "+cursor.getCount());





            } else {
                count = 0;
                DebugLog.console("[DataBaseProcessor] inside stat aghhid_getNumbersDataagainstvillageAndhhid() new hhid");

            }
            DebugLog.console("[HouseHoldDataBaseHelper] inside aghhid_getNumbersDataagainstvillageAndhhid() size "+count);
            closeDB(cursor);
            return count;
        } catch (Exception e) {
            DebugLog.console( e.toString()+"[DatabaseProcessor]: exception inside aghhid_getNumbersDataagainstvillageAndhhid");
            closeDB();
            return count;
        }
    }


}
