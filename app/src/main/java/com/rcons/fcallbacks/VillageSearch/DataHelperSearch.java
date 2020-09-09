package com.rcons.fcallbacks.VillageSearch;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DataHelperSearch extends SQLiteOpenHelper
{
    private Context context;

    private static String DB_NAME = "UcVillageBasedata.db";
    public SQLiteDatabase myDataBase;
    private String DB_PATH = "";

    public DataHelperSearch(Context context)
    {
        super(context,DB_NAME,null,1);
        this.context =context;
        DB_PATH ="data/data/"+ this.context.getPackageName()+"/databases/";
        boolean dbExist = checkDatabase();
        if (dbExist)
        {
            openDatabase();
        }
        else
        {
            Log.d("DataHelperSeach","Database doesn't exist");
            try {
                createDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDatabase() throws IOException
    {
        boolean dbExist = checkDatabase();
        if(dbExist)
        {
        }
        else
        {
            this.getReadableDatabase();
            try
            {
                copyDatabase();
            } catch(IOException e)
            {

            }
        }
    }

    public boolean checkDatabase() {
        boolean checkDb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbFile = new File(myPath);
            checkDb = dbFile.exists();
        } catch(SQLiteException e) {
            Log.d("DataHelperSearch","Database doesn't exist");
        }
        return checkDb;
    }

    private void copyDatabase() throws IOException {
        InputStream inputStream = context.getAssets().open(DB_NAME);
        Log.d("DataHelperSearch","========myInput==========="+inputStream);
        OutputStream outputStream = new FileOutputStream(DB_PATH+DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer))>0)
        {
            outputStream.write(buffer,0,length);
            Log.d("DataHelperSearch","========outputStream==========="+outputStream);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public synchronized void openDatabase() throws SQLException
    {
        String databasePath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE );
    }

    public synchronized void close()
    {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
