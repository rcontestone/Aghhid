package com.rcons.fcallbacks.VillageSearch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseAdapterSearch {

    private final String BaseData = "basedata_table";

    private Context context;
    private DataHelperSearch database;
    private Cursor cursor;
    private SQLiteDatabase db;

    public DatabaseAdapterSearch(Context ctx) {
        this.context = ctx;
    }

    public DatabaseAdapterSearch Open() {
        database = new DataHelperSearch(context);
        db = database.getWritableDatabase();

        return this;
    }

    public boolean isOpen() {
        if (db == null) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor GetDistrictData() {
        String query  = "select district_id ,  district_name from basedata_table  GROUP BY district_name";
        Cursor cursor =db.rawQuery(query ,new String[]{});
        if (cursor!=null && cursor.getCount()>0){
            return cursor;
        }else {
            return null;
        }

    }
    public Cursor GetTehsilData(String id) {
        String query  = "select tehsil_id ,  tehsil_name from basedata_table   WHERE district_id = '"+id+"' GROUP BY tehsil_name";
        Cursor cursor =db.rawQuery(query ,new String[]{});
        if (cursor!=null && cursor.getCount()>0){
            return cursor;
        }else {
            return null;
        }

    }





    public Cursor GetSelectTehsilDataDistrictBased() {
        String query  = "select tehsil_id ,  tehsil_name from basedata_table GROUP BY tehsil_name";
        Cursor cursor =db.rawQuery(query ,new String[]{});
        if (cursor!=null && cursor.getCount()>0){
            return cursor;
        }else {
            return null;
        }

    }


    public Cursor GetVillageData(String id) {
        String query  = "select mauza_village_id ,  mauza_village_name , union_council_id ,union_council_name,tehsil_name,tehsil_id from basedata_table   WHERE tehsil_id = '"+id+"' GROUP BY mauza_village_name ORDER BY mauza_village_id , union_council_id";
        Cursor cursor =db.rawQuery(query ,new String[]{});
        if (cursor!=null && cursor.getCount()>0){
            return cursor;
        }else {
            return null;
        }

    }

    public Cursor GetVillageDataDistrictBased(String districtName) {
        String query  = "select mauza_village_id ,  mauza_village_name , union_council_id ,union_council_name,tehsil_id , tehsil_name from basedata_table WHERE district_name LIKE '%"+districtName+"%' GROUP BY mauza_village_name ORDER BY mauza_village_id , union_council_id\n" ;
       // String query  = "select mauza_village_id ,  mauza_village_name , union_council_id ,union_council_name,tehsil_name,tehsil_id from basedata_table   WHERE district_id = '"+id+"' GROUP BY mauza_village_name ORDER BY mauza_village_id , union_council_id";
        Cursor cursor =db.rawQuery(query ,new String[]{});
        if (cursor!=null && cursor.getCount()>0){
            return cursor;
        }else {
            return null;
        }

    }


}




