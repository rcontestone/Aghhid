package com.rcons.fcallbacks.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;

import com.mubashar.dateandtime.DebugLog;
import com.mubashar.dateandtime.EmailDebugLog;
import com.mubashar.dateandtime.MubDateAndTime;
import com.rcons.fcallbacks.BuildConfig;
import com.rcons.fcallbacks.Main.CallMenuActivity;
import com.rcons.fcallbacks.Model.Crop;
import com.rcons.fcallbacks.Model.CropModel;
import com.rcons.fcallbacks.Model.SectionF12;
import com.rcons.fcallbacks.Model.SectionF2;
import com.rcons.fcallbacks.Model.SectionF2C;
import com.rcons.fcallbacks.Utilties.MubLog;
import com.rcons.fcallbacks.Utilties.RConsUtils;
import com.rcons.fcallbacks.Utilties.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class DatabaseAdapter {

    public static final String AGHHID_SampleTable = "aghhid_sample";
    public static final String BaseLineSampleTable = "baseline_sample";
    public static final String FarmerCallBackTable = "FarmerCallBackTable";
    public static final String KnowledgeTest = "Questions_Table";
    public static final String CropIDTable = "Crop_Table";
    public static final String ZaatNameTable = "Zaat_Codes";
    public static final String Enum_NameTable = "Enum_Name";
    public static final String SectionBTable = "Section_B";
    public static final String SectionCTable = "Section_C";
    public static final String SectionDTable = "Section_D";
    public static final String SectionETable = "Section_E";
    public static final String SectionFTable1 = "Section_F_table1";
    public static final String SectionFTable2 = "Section_F_table2";
    public static final String SectionFTable3 = "Section_F_table3";
    public static final String SectionFTable4 = "Section_F_table4";
    public static final String SectionFTable5 = "Section_F_table5";
    public static final String SectionFTable6 = "Section_F_table6";
    public static final String SectionGTable = "Section_G";
    public static final String SectionG_bTable = "Section_G_b";
    public static final String SectionHTable = "Section_H";
    public static final String Section1Table = "Section_1";
    public static final String SectionATable = "Section_A";


    public static final String trt_section_0_table = "trt_section_0";
    public static final String trt_section_1_table = "trt_section_1";
    public static final String trt_section_2_table = "trt_section_2";
    public static final String trt_section_3_table = "trt_section_3";
    public static final String trt_section_4_table = "trt_section_4";


    public static final String pq_section_a_table = "aghhid_section_a";
    public static final String pq_section_a2_table = "aghhid_section_a2";
    public static final String pq_section_a3_table = "aghhid_section_a3";
    public static final String pq_section_a4_table = "aghhid_section_a4";
    public static final String pq_section_b_table = "pq_section_b";
    public static final String pq_section_c1_table = "pq_section_c1";
    public static final String pq_section_c2_table = "pq_section_c2";
    public static final String pq_section_c3_table = "pq_section_c3";
    public static final String pq_section_d_table = "pq_section_d";
    public static final String pq_section_e_table = "pq_section_e";


    public static final String aghhid_section_c_table = "aghhid_section_c";
    public static final String aghhid_section_d_table = "aghhid_section_d";
    public static final String aghhid_section_e_table = "aghhid_section_e";
    public static final String aghhid_section_g_table = "aghhid_section_g";

    private Context context;
    public DataHelper database;
    private Cursor cursor;
    private SQLiteDatabase db;


    private DurationPopup durationPopup;


    public DatabaseAdapter(Context ctx) {
        this.context = ctx;
    }

    public DatabaseAdapter Open() {
        database = new DataHelper(context);
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

    public Cursor getCropIDInformation() {
        String query = "select * from " + CropIDTable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getEnumNameCodeInformation() {
        try {
            String query = "select * from " + Enum_NameTable;
            Cursor cursor = db.rawQuery(query, new String[]{});
            if (cursor != null && cursor.getCount() > 0) {
                return cursor;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("TAG", "" + e.toString());
            return null;
        }
    }

    public Cursor getZaatCodeInformation() {
        try {
            String query = "select * from " + ZaatNameTable;
            Cursor cursor = db.rawQuery(query, new String[]{});
            if (cursor != null && cursor.getCount() > 0) {
                return cursor;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("TAG", "" + e.toString());
            return null;
        }
    }

    public Cursor getEnterFormInformation() {
        try {
            String query = "select * from " + FarmerCallBackTable;
            Cursor cursor = db.rawQuery(query, new String[]{});
            if (cursor != null && cursor.getCount() > 0) {
                return cursor;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("TAG", "" + e.toString());
            return null;
        }
    }


    public CropModel readf2cCrop(String farmerID) {
        // String query   = "SELECT * from Crop_Table WHERE Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f1_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = "+farmerID+") AND Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f2b_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = "+farmerID+")";
        String query = "SELECT * from Crop_Table WHERE \n" +
                "Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f1_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = " + farmerID + ") AND \n" +
                "Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f2b_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = " + farmerID + ") AND\n" +
                "Crop_Table.crop_id NOT IN (SELECT Section_F_table3.f2c_crop  from Section_F_table3 WHERE Section_F_table3.farmer_id = " + farmerID + ") ";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            CropModel cropArrayList = new CropModel();
            if (cursor.moveToFirst()) {
                do {

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));

                    cropArrayList.getCropID().add(crop_id);
                    cropArrayList.getCropUrduName().add(crop_name_urdu);
                    cropArrayList.getCropEnglishName().add(crop_name_english);
                    cropArrayList.getCropCategory().add(crop_catagory);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return cropArrayList;

        } else {
            return null;
        }


    }

    public CropModel Readf2cCrop(String farmerID, CropModel cropArrayList) {
        // String query   = "SELECT * from Crop_Table WHERE Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f1_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = "+farmerID+") AND Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f2b_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = "+farmerID+")";
        String query = "SELECT * from Crop_Table WHERE \n" +
                "Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f1_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = " + farmerID + ") AND \n" +
                "Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f2b_crop  from Section_F_table2 WHERE Section_F_table2.farmer_id = " + farmerID + ") AND\n" +
                "Crop_Table.crop_id NOT IN (SELECT Section_F_table3.f2c_crop  from Section_F_table3 WHERE Section_F_table3.farmer_id = " + farmerID + ") ";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));

                    cropArrayList.getCropID().add(crop_id);
                    cropArrayList.getCropUrduName().add(crop_name_urdu);
                    cropArrayList.getCropEnglishName().add(crop_name_english);
                    cropArrayList.getCropCategory().add(crop_catagory);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return cropArrayList;

        } else {
            return null;
        }


    }


    public CropModel ReadF12(String farmerID, CropModel cropArrayList) {
        String query = "SELECT * from Crop_Table WHERE Crop_Table.crop_id NOT IN (SELECT Section_F_table5.f12_crop  from Section_F_table5 WHERE Section_F_table5.farmer_id = " + farmerID + ") ";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));

                    cropArrayList.getCropID().add(crop_id);
                    cropArrayList.getCropUrduName().add(crop_name_urdu);
                    cropArrayList.getCropEnglishName().add(crop_name_english);
                    cropArrayList.getCropCategory().add(crop_catagory);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return cropArrayList;

        } else {
            return null;
        }


    }


    public CropModel Read2FBBYParameter(String cropID, CropModel externalModel) {
        String query = "SELECT * from Crop_Table ";//WHERE Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f1_crop  from Section_F_table2) AND Crop_Table.crop_id NOT IN (SELECT Section_F_table2.f2b_crop  from Section_F_table2)";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            //  CropModel    cropArrayList  = new CropModel();
            if (cursor.moveToFirst()) {
                do {

                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));
                    if (!StringUtils.isEmpty(cropID) && !cropID.equalsIgnoreCase(crop_id)) {
                        externalModel.getCropID().add(crop_id);
                        externalModel.getCropUrduName().add(crop_name_urdu);
                        externalModel.getCropEnglishName().add(crop_name_english);
                        externalModel.getCropCategory().add(crop_catagory);
                    }


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return externalModel;

        } else {
            return null;
        }


    }

    public ArrayList<Crop> readF4Crops() {
        String query = "select * from " + CropIDTable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        ArrayList<Crop> crops = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            CropModel cropArrayList = new CropModel();
            Crop crop;
            if (cursor.moveToFirst()) {
                do {
                    crop = new Crop();
                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));
                    crop.setCropID(crop_id);
                    crop.setCropEnglishName(crop_name_english);
                    crop.setCropUrduName(crop_name_urdu);
                    crop.setCropCategory(crop_catagory);
                    crop.setIsSelected(false);
                    crops.add(crop);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return crops;

        } else {
            return null;
        }
    }

    public ArrayList<Crop> readF4CropsByCheckBox(String f4a, String f4b, String f4c) {
        String query = "select * from " + CropIDTable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        ArrayList<Crop> crops = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            CropModel cropArrayList = new CropModel();
            Crop crop;
            if (cursor.moveToFirst()) {
                do {
                    crop = new Crop();
                    String crop_id = cursor.getString(cursor.getColumnIndex("crop_id"));
                    String crop_name_urdu = cursor.getString(cursor.getColumnIndex("crop_name_urdu"));
                    String crop_name_english = cursor.getString(cursor.getColumnIndex("crop_name_english"));
                    String crop_catagory = cursor.getString(cursor.getColumnIndex("crop_catagory"));
                    crop.setCropID(crop_id);
                    crop.setCropEnglishName(crop_name_english);
                    crop.setCropUrduName(crop_name_urdu);
                    crop.setCropCategory(crop_catagory);

                    if (!StringUtils.isEmpty(f4a) && f4a.equalsIgnoreCase(crop_id)) {
                        crop.setIsSelected(true);
                    } else if (!StringUtils.isEmpty(f4b) && f4b.equalsIgnoreCase(crop_id)) {
                        crop.setIsSelected(true);
                    } else if (!StringUtils.isEmpty(f4c) && f4c.equalsIgnoreCase(crop_id)) {
                        crop.setIsSelected(true);
                    } else {
                        crop.setIsSelected(false);
                    }

                    crops.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return crops;

        } else {
            return null;
        }
    }

    public void DeleteSectionF2Data(String id) {
        try {
            String deleteQuery = "Delete from " + SectionFTable2 + " WHERE id = " + id;
            db = database.getWritableDatabase();
            db.execSQL(deleteQuery);
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    public void DeleteSectionF2CData(String id) {
        try {
            String deleteQuery = "Delete from " + SectionFTable3 + " WHERE id = " + id;
            db = database.getWritableDatabase();
            db.execSQL(deleteQuery);
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    public ArrayList<SectionF2> readSectionF2(String farmerID) {
        String query = "SELECT sft.*, ct.crop_name_english as f1cropenglishname, \n" +
                "ct.crop_name_urdu as f1cropurduname   ,\n" +
                " ct1.crop_name_english as f2cropenglishaname, \n" +
                "ct1.crop_name_urdu as f2cropurduname   \n" +
                "from Section_F_table2 sft \n" +
                "RIGHT JOIN Crop_Table ct on sft.f1_crop = ct.crop_id\n" +
                "LEFT JOIN Crop_Table ct1 on sft.f2b_crop = ct1.crop_id\n" +
                "WHERE sft.farmer_id = " + farmerID + "\n" +
                "\n";

        Cursor cursor = db.rawQuery(query, new String[]{});
        ArrayList<SectionF2> sectionF2ArrayList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            SectionF2 sectionF2;
            if (cursor.moveToFirst()) {
                do {


                    sectionF2 = new SectionF2();
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String emp_id = cursor.getString(cursor.getColumnIndex("emp_id"));
                    String order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                    String farmer_id = cursor.getString(cursor.getColumnIndex("farmer_id"));
                    String f1_crop = cursor.getString(cursor.getColumnIndex("f1_crop"));
                    String f1_other = cursor.getString(cursor.getColumnIndex("f1_other"));

                    String f2a_kanal = cursor.getString(cursor.getColumnIndex("f2a_kanal"));
                    String f2a_acre = cursor.getString(cursor.getColumnIndex("f2a_acre"));

                    String f2b_crop = cursor.getString(cursor.getColumnIndex("f2b_crop"));
                    String f2b_other = cursor.getString(cursor.getColumnIndex("f2b_other"));

                    String f2b_kanal = cursor.getString(cursor.getColumnIndex("f2b_kanal"));
                    String f2b_acre = cursor.getString(cursor.getColumnIndex("f2b_acre"));

                    String f1cropurduname = cursor.getString(cursor.getColumnIndex("f1cropurduname"));
                    String f1cropenglishname = cursor.getString(cursor.getColumnIndex("f1cropenglishname"));
                    String f2cropurduname = cursor.getString(cursor.getColumnIndex("f2cropurduname"));
                    String f2cropenglishname = cursor.getString(cursor.getColumnIndex("f2cropenglishname"));

                    sectionF2.setId(id);
                    sectionF2.setEmp_id(emp_id);
                    sectionF2.setOrder_id(order_id);
                    sectionF2.setFarmer_id(farmer_id);

                    sectionF2.setF1_crop(f1_crop);
                    sectionF2.setF1_other(f1_other);

                    sectionF2.setF2a_kanal(f2a_kanal);
                    sectionF2.setF2a_acre(f2a_acre);

                    sectionF2.setF2b_crop(f2b_crop);
                    sectionF2.setF2b_other(f2b_other);

                    sectionF2.setF2bKanal(f2b_kanal);
                    sectionF2.setF2bAcre(f2b_acre);

                    sectionF2.setF1crop_english_name(f1cropenglishname);
                    sectionF2.setF1crop_urdu_name(f1cropurduname);

                    sectionF2.setF2bcrop_english_name(f2cropenglishname);
                    sectionF2.setF2bcrop_urdu_name(f2cropurduname);

                    sectionF2ArrayList.add(sectionF2);

                } while (cursor.moveToNext());
            }
            cursor.close();
            return sectionF2ArrayList;

        } else {
            return null;
        }
    }

    public ArrayList<SectionF2C> readSectionF2C(String farmerID) {
        String query = "SELECT sft.*, ct.crop_name_english as f2ccropenglishname, \n" +
                "ct.crop_name_urdu as f2ccropurduname   \n" +
                "from Section_F_table3 sft \n" +
                "INNER JOIN Crop_Table ct on sft.f2c_crop = ct.crop_id\n" +
                "WHERE sft.farmer_id = " + farmerID;
        Cursor cursor = db.rawQuery(query, new String[]{});
        ArrayList<SectionF2C> sectionF2ArrayList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            SectionF2C sectionF2;
            if (cursor.moveToFirst()) {
                do {


                    sectionF2 = new SectionF2C();
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String emp_id = cursor.getString(cursor.getColumnIndex("emp_id"));
                    String order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                    String farmer_id = cursor.getString(cursor.getColumnIndex("farmer_id"));
                    String f2c_crop = cursor.getString(cursor.getColumnIndex("f2c_crop"));
                    String f2c_other = cursor.getString(cursor.getColumnIndex("f2c_other"));

                    String f2c_kanal = cursor.getString(cursor.getColumnIndex("f2c_kanal"));
                    String f2c_acre = cursor.getString(cursor.getColumnIndex("f2c_acre"));


                    String f2ccropenglishname = cursor.getString(cursor.getColumnIndex("f2ccropenglishname"));
                    String f2ccropurduname = cursor.getString(cursor.getColumnIndex("f2ccropurduname"));

                    sectionF2.setId(id);
                    sectionF2.setEmp_id(emp_id);
                    sectionF2.setOrder_id(order_id);
                    sectionF2.setFarmer_id(farmer_id);

                    sectionF2.setF2c_crop(f2c_crop);
                    sectionF2.setF2c_other(f2c_other);

                    sectionF2.setF2c_kanal(f2c_kanal);
                    sectionF2.setF2c_acre(f2c_acre);
                    sectionF2.setF2c_crop_english_name(f2ccropenglishname);
                    sectionF2.setF2c_crop_urdu_name(f2ccropurduname);

                    sectionF2ArrayList.add(sectionF2);


                } while (cursor.moveToNext());
            }
            cursor.close();
            return sectionF2ArrayList;

        } else {
            return null;
        }
    }

    public ArrayList<SectionF12> readSectionF12(String farmerID) {
        String query = "SELECT sft.*, ct.crop_name_english as f12cropenglishname, \n" +
                "ct.crop_name_urdu as f12cropurduname   \n" +
                "from " + SectionFTable5 + " sft \n" +
                "INNER JOIN Crop_Table ct on sft.f12_crop = ct.crop_id\n" +
                "WHERE sft.farmer_id = " + farmerID;
        Cursor cursor = db.rawQuery(query, new String[]{});
        ArrayList<SectionF12> sectionF12s = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            SectionF12 sectionF2;
            if (cursor.moveToFirst()) {
                do {


                    sectionF2 = new SectionF12();
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String emp_id = cursor.getString(cursor.getColumnIndex("emp_id"));
                    String order_id = cursor.getString(cursor.getColumnIndex("order_id"));
                    String farmer_id = cursor.getString(cursor.getColumnIndex("farmer_id"));
                    String f12_crop = cursor.getString(cursor.getColumnIndex("f12_crop"));
                    String f12_other = cursor.getString(cursor.getColumnIndex("f12_other"));

                    String f13 = cursor.getString(cursor.getColumnIndex("f13"));
                    String f13_other = cursor.getString(cursor.getColumnIndex("f13_other"));


                    String f14 = cursor.getString(cursor.getColumnIndex("f14"));
                    String f14_other = cursor.getString(cursor.getColumnIndex("f14_other"));

                    String f15 = cursor.getString(cursor.getColumnIndex("f15"));
                    String f15_other = cursor.getString(cursor.getColumnIndex("f15_other"));


                    String f2ccropenglishname = cursor.getString(cursor.getColumnIndex("f12cropenglishname"));
                    String f2ccropurduname = cursor.getString(cursor.getColumnIndex("f12cropurduname"));

                    sectionF2.setId(id);
                    sectionF2.setEmp_id(emp_id);
                    sectionF2.setOrder_id(order_id);
                    sectionF2.setFarmer_id(farmer_id);

                    sectionF2.setF12_crop(f12_crop);
                    sectionF2.setF12_other(f12_other);

                    sectionF2.setF13(f13);
                    sectionF2.setF13_other(f13_other);

                    sectionF2.setF14(f14);
                    sectionF2.setF14_other(f14_other);

                    sectionF2.setF15(f15);
                    sectionF2.setF15_other(f15_other);

                    sectionF2.setF2c_crop_english_name(f2ccropenglishname);
                    sectionF2.setF2c_crop_urdu_name(f2ccropurduname);

                    sectionF12s.add(sectionF2);


                    //    cropArrayList.add(crop);
                } while (cursor.moveToNext());
            }
            cursor.close();
            return sectionF12s;

        } else {
            return null;
        }
    }

    public Cursor getEditFormFarmerData() {
        String query = "SELECT FarmerCallBackTable.farmer_id , FarmerCallBackTable.tehsil_basedata   FROM FarmerCallBackTable INNER JOIN Section_A WHERE FarmerCallBackTable.farmer_id = Section_A.farmer_id AND isSynced IS NULL";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionHMajorCrops(String farmerID) {

        String query = "SELECT sft.*, ct.crop_name_english as f1cropenglishname, ct.crop_catagory  , (coalesce(sft.f2a_kanal ,0) + coalesce(sft.f2a_acre ,0)*8) as totalKanals ,\n" +
                "ct.crop_name_urdu as f1cropurduname   \n" +
                "from Section_F_table2 sft \n" +
                "INNER JOIN Crop_Table ct on sft.f1_crop = ct.crop_id\n" +
                "where sft.farmer_id = " + farmerID + "  AND ct.crop_catagory IN ('Major Crops' ) ORDER BY totalKanals DESC";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionHMinorCrops(String farmerID) {
        String query = "SELECT sft.*, ct.crop_name_english as f1cropenglishname, ct.crop_catagory  , (coalesce(sft.f2a_kanal ,0) + coalesce(sft.f2a_acre ,0)*8) as totalKanals ,\n" +
                "ct.crop_name_urdu as f1cropurduname   \n" +
                "from Section_F_table2 sft \n" +
                "INNER JOIN Crop_Table ct on sft.f1_crop = ct.crop_id\n" +
                "where sft.farmer_id = " + farmerID + "  AND ct.crop_catagory IN ('Minor Crops' ) ORDER BY totalKanals DESC";

        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }


    public Cursor getDataBaseOnRegionAndCrop(String cropID, String region) {
        String query = "";
        if (!StringUtils.isEmpty(region)) {
            query = "SELECT question_type  ,question_no_new, question_name  FROM Questions_Table WHERE crop_id ='" + cropID + "' AND region ='" + region + "' AND random_quetions = '1' GROUP By question_name ORDER BY question_no_new ASC";

        } else {
            query = "SELECT question_type  ,question_no_new, question_name  FROM Questions_Table WHERE crop_id ='" + cropID + "'  AND random_quetions = '1' GROUP By question_name ORDER BY question_no_new ASC";

        }
        //String query = "SELECT question_type  ,question_no_new, question_name FROM data_Table INNER JOIN FarmerCallBackTable on data_Table.region = FarmerCallBackTable.region WHERE crop_id ='" + cropID + "' AND region ='" +region+"' AND random_month1 = 'Yes' GROUP By question_name ORDER BY question_no_new ASC";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }


    public Cursor getAnswers(String questionNumber) {

        String query = "SELECT options_name , options_no , answers from Questions_Table WHERE question_no_new ='" + questionNumber + "' ORDER by options_no ASC";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }
/*
    public int getPendingCall(String userName) {

        String query = "SELECT * \n" +
                "FROM   farmercallbacktable \n" +
                "WHERE  rcons_user LIKE '%" + userName + "%' \n" +
                "AND sync != '2' \n" +
                "AND survey_status NOT IN ( '', 1, 6, 3,  5, 7 ) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  \n" +
                "\t\t\t\t\t\t\t\t\tWHERE  Ifnull(Length(sc1), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc2), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc3), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc1_alt), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc2_alt), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc3_alt), 0) != 0) \n" +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (2)))\n" +
                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )   AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL))  \n" +
                "AND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE temp1 IN (1)))" +
                " AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))";


        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;

        }

    }

    public Cursor getFarmerPendingCalls(String userName) {

        String query = "SELECT * \n" +
                "FROM   farmercallbacktable \n" +
                "WHERE  rcons_user LIKE '%" + userName + "%' \n" +
                "AND sync != '2' \n" +
                "AND survey_status NOT IN ( '', 1, 6, 3,  5, 7 ) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  \n" +
                "\t\t\t\t\t\t\t\t\tWHERE  Ifnull(Length(sc1), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc2), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc3), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc1_alt), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc2_alt), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc3_alt), 0) != 0) \n" +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (2)))\n" +
                "AND ( farmer_id  IN (SELECT farmer_id FROM   farmercallbacktable WHERE survey_status IN (2, 6 , 4, 7 ,8, 9 ,10 )))\n" +

                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )  AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND farmer_cellphone=alt_farmer_cellphone) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )   AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL))  \n" +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))";
        // "AND (farmer_id IN ( SELECT farmer_id FROM farmercallbacktable WHERE temp1 IN (2)))";



        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }
*/


    //when change in this also make change in delete section data querry and getFarmerPendingCalls
    public int getPendingCall_old(String userName) {


        //when change in this also make change in delete section data querry and getFarmerPendingCalls
        Cursor cursor2 = this.db.rawQuery("SELECT * \nFROM   farmercallbacktable \nWHERE  rcons_user LIKE '%" + userName + "%' \nAND sync != '2' \nAND survey_status NOT IN ( '', 1, 6, 3,  5, 7 ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  \n\t\t\t\t\t\t\t\t\tWHERE  Ifnull(Length(sc1), 0) != 0 \n                                    AND Ifnull(Length(sc2), 0) != 0 \n                                    AND Ifnull(Length(sc3), 0) != 0 \n                                    AND Ifnull(Length(sc1_alt), 0) != 0 \n                                    AND Ifnull(Length(sc2_alt), 0) != 0 \n                                    AND Ifnull(Length(sc3_alt), 0) != 0) \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc1_alt), 0) = 0 AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc2_alt), 0) = 0  AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc3), 0) != 0 AND Ifnull(Length(sc3_alt), 0) = 0 AND temp1 IN ('',1)))\nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (2)))\nAND ( farmer_id  IN (SELECT farmer_id FROM   farmercallbacktable WHERE survey_status IN (2, 6 , 4, 7 ,8, 9,10 )))\nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )  AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )   AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE temp1 IN (1 )))AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 , 2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();
    }

    //when change in this also make change in delete section data querry  and getPendingCall
    public Cursor getFarmerPendingCalls_old(String userName) {

        //when change in this also make change in delete section data querry  and getPendingCall
        Cursor cursor2 = this.db.rawQuery("SELECT * \nFROM   farmercallbacktable \nWHERE  rcons_user LIKE '%" + userName + "%' \nAND sync != '2' \nAND survey_status NOT IN ( '', 1, 6, 3,  5, 7 ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  \n\t\t\t\t\t\t\t\t\tWHERE  Ifnull(Length(sc1), 0) != 0 \n                                    AND Ifnull(Length(sc2), 0) != 0 \n                                    AND Ifnull(Length(sc3), 0) != 0 \n                                    AND Ifnull(Length(sc1_alt), 0) != 0 \n                                    AND Ifnull(Length(sc2_alt), 0) != 0 \n                                    AND Ifnull(Length(sc3_alt), 0) != 0) \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc1_alt), 0) = 0 AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc2_alt), 0) = 0  AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc3), 0) != 0 AND Ifnull(Length(sc3_alt), 0) = 0 AND temp1 IN ('',1)))\nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (2)))\nAND ( farmer_id  IN (SELECT farmer_id FROM   farmercallbacktable WHERE survey_status IN (2, 6 , 4, 7 ,8, 9 ,10 )))\nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )  AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )   AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE temp1 IN (1 )))AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 , 2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }


    public int getPendingCall(String userName) {
        //removed 8 when 4447 issue

        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        // String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1) ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
        // String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1) ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
        String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);

        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1) ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (3,5,10) OR  farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,4,6,7,8,9) AND  farmer_id IN " + getFarmerId_sc1_alt_Done + " ) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + "  AND farmer_id NOT IN " + getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid + "  )";

        MubLog.cpnsoleLog("inside getPendingCall " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();
    }

    //when change in this also make change in delete section data querry  and getPendingCall


    public Cursor getFarmerPendingCalls(String userName) {
        //removed 8 when 4447 issue

        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        //   String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status_org IN (1)  OR survey_status_alt IN (1)    ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
        //  String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
        String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);

        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1) ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (3,5,10) OR  farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,4,6,7,8,9) AND  farmer_id IN " + getFarmerId_sc1_alt_Done + " ) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + "  AND farmer_id NOT IN " + getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid + "  )";


        MubLog.cpnsoleLog("inside getPendingCall " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    public Cursor getFarmerPendingCalls_farmerid(String userName) {
        Cursor cursor2 = this.db.rawQuery("SELECT farmer_id \nFROM   farmercallbacktable \nWHERE  rcons_user LIKE '%" + userName + "%' \nAND sync != '2' \nAND survey_status NOT IN ( '', 1, 6, 3,  5, 7 ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  \n\t\t\t\t\t\t\t\t\tWHERE  Ifnull(Length(sc1), 0) != 0 \n                                    AND Ifnull(Length(sc2), 0) != 0 \n                                    AND Ifnull(Length(sc3), 0) != 0 \n                                    AND Ifnull(Length(sc1_alt), 0) != 0 \n                                    AND Ifnull(Length(sc2_alt), 0) != 0 \n                                    AND Ifnull(Length(sc3_alt), 0) != 0) \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc1_alt), 0) = 0 AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc2_alt), 0) = 0  AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc3), 0) != 0 AND Ifnull(Length(sc3_alt), 0) = 0 AND temp1 IN ('',1)))\nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (2)))\nAND ( farmer_id  IN (SELECT farmer_id FROM   farmercallbacktable WHERE survey_status IN (2, 6 , 4, 7 ,8, 9 ,10 )))\nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )  AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )   AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE temp1 IN (1 )))AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 , 2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        MubLog.cpnsoleLog("inside getFarmerPendingCalls_farmerid" + cursor2.getCount());
        return cursor2;
    }

    public Cursor getFarmerDistinctCallRecord_old(String userName) {

        //removed 8 when 4447 issue
        Cursor cursor2 = this.db.rawQuery("SELECT * \nFROM   farmercallbacktable \nWHERE  ( ( rcons_user LIKE '%" + userName + "%' \nAND sync != '2' ) \nAND ( survey_status IN ('' ,2, 6 , 4 ,7 ,8, 9)) )\nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN ( 1 ) ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  sc1_alt IN ( 2, 6 , 4, 7 ,8, 9 ) ) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp1 IN ( 2)))AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 , 2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1))) ORDER BY district_basedata,tehsil_basedata,emp_id, order_id ASC", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    public int getNewCallsCount_old(String userName) {
        //removed 8 when 4447 issue
        Cursor cursor2 = this.db.rawQuery("SELECT * \nFROM   farmercallbacktable \nWHERE  ( ( rcons_user LIKE '%" + userName + "%' \nAND sync != '2' ) \nAND ( survey_status IN ('' ,2, 6 , 4 ,7 ,8, 9)) )\nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN ( 1 ) ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  sc1_alt IN ( 2,  6 , 4, 7 ,8, 9 ) ) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp1 IN (2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 ,2)))  AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();
    }


    public Cursor getFarmerDistinctCallRecord(String userName) {

        //removed 8 when 4447 issue
        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        // String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status_org IN (1)  OR survey_status_alt IN (1)    ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status_org IN (2,3,4,5,6,7,8,9,10)  OR survey_status_alt IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
//        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (2,3,4,5,6,7,8,9,10) ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+" AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";
        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (3,5,10) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + " AND farmer_id NOT IN " + getFarmerId_sc1_alt_Done + "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";

        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        MubLog.cpnsoleLog("inside getFarmerDistinctCallRecord " + querry);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    public int getNewCallsCount(String userName) {
        //removed 8 when 4447 issue

        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        // String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status_org IN (1)  OR survey_status_alt IN (1)    ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status_org IN (2,3,4,5,6,7,8,9,10)  OR survey_status_alt IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
//        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (2,3,4,5,6,7,8,9,10) ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+" AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";
        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (3,5,10) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + " AND farmer_id NOT IN " + getFarmerId_sc1_alt_Done + "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";

        MubLog.cpnsoleLog("inside getNewCallsCount " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();
    }


    //CallMenuActivity
    /*
    public Cursor getFarmerDistinctCallRecord(String userName) {
        MubLog.cpnsoleLog("inside getFarmerDistinctCallRecord " + userName);
        String query = "SELECT * \n" +
                "FROM   farmercallbacktable \n" +
                "WHERE  ( ( rcons_user LIKE '%" + userName + "%' \n" +
                "AND sync != '2' ) \n" +
                "AND ( survey_status IN ('' ,2, 6 , 4 ,7 ,8, 9)) )\n" +
                "AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN ( 1 ) ) \n" +
                "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable  WHERE  survey_status IN ( 1 ) ) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  sc1_alt IN ( 2, 6 , 4, 7 ,8, 9 ) ) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND farmer_cellphone=alt_farmer_cellphone) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL))  \n" +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp1 IN ( 2)))" +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1)))" +
                //   "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE (Ifnull(Length(survey_status), 0) != 0) AND survey_status IN ( 2, 6 , 4, 7 ,8, 9 )))"+
                " ORDER BY district_basedata,tehsil_basedata,emp_id, order_id ASC";


        MubLog.cpnsoleLog("querry "+query);
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }


    public int getNewCallsCount(String userName) {

        String query = "SELECT * \n" +
                "FROM   farmercallbacktable \n" +
                "WHERE  ( ( rcons_user LIKE '%" + userName + "%' \n" +
                "AND sync != '2' ) \n" +
                "AND ( survey_status IN ('' ,2, 6 , 4 ,7 ,8, 9)) )\n" +
                "AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN ( 1 ) ) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND farmer_cellphone=alt_farmer_cellphone) \n" +
                "AND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL))  \n" +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp1 IN (2))) " +
                "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1))) ";
        //  "AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE (Ifnull(Length(survey_status), 0) != 0) AND survey_status IN ( 2, 6 , 4, 7 ,8, 9 )))";

        Cursor cursor = db.rawQuery(query, new String[]{});


        if (cursor != null && cursor.getCount() > 0) {

            return cursor.getCount();
        } else {
            return 0;

        }


    }*/


    public Cursor getAssignStaffInformation(String farmerID) {
        Cursor cursor = db.rawQuery("select farmer_id , ao_assigned , fa_assigned ,ai_assigned , emp_id , empost_designation ,emp_fullname from FarmerCallBackTable where farmer_id='" + farmerID + "'", new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }


    public Cursor searchPendingCall_(String userName, String number) {


        MubLog.cpnsoleLog("inside searchPendingCall" + number);

        String query = "SELECT * \n" +
                "FROM   farmercallbacktable \n" +
                "WHERE  rcons_user LIKE '%" + userName + "%' \n" +
                "       AND sync != '2' \n" +
                "       AND survey_status NOT IN ( '', 1, 6, 3, \n" +
                "                                  5, 7 ) \n" +
                "       AND farmer_cellphone LIKE '%" + number + "%'" +
                "       AND farmer_id NOT IN (SELECT farmer_id \n" +
                "                             FROM   farmercallbacktable \n" +
                "                             WHERE  Ifnull(Length(sc1), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc2), 0) != 0 \n" +
                "                                    AND Ifnull(Length(sc3), 0) != 0 \n" +
                "\t\t\t\t\t\t\t\t\tAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE temp1 IN (1)))";

        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor searchPendingCall(String userName, String farmer_id) {
        //removed 8 when 4447 issue

        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        //   String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status_org IN (1)  OR survey_status_alt IN (1)    ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN "+getFarmerId_sixTries_Done+"  )";
        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' And farmer_id LIKE '%" + farmer_id + "%' AND sync != '2' ) AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN (2,3,4,5,6,7,8,9,10)    ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + "  )";

        MubLog.cpnsoleLog("inside getPendingCall " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    public int getFarmerCount() {
        int count = 0;
        String query = "select id from " + FarmerCallBackTable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            count = cursor.getCount();
        } else {
            count = 0;
        }
        return count;

    }


//    public Cursor SelectCompletedSectionFarmersTAbleA() {
//        String query = "select * from " + SectionATable;
//        Cursor cursor = db.rawQuery(query, new String[]{});
//        return cursor;
//
//    }

    public Cursor SelectCompletedSectionFarmers() {
        String query = "select * from " + SectionATable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        return cursor;

    }

    public void UpdateNumber(String phoneNumber, String farmerID, boolean isAlternateNumber) {
        String updateQuery = "";
        if (isAlternateNumber) {
            updateQuery = "UPDATE " + FarmerCallBackTable + " SET add_farmer_no_alt = '" + phoneNumber + "'  WHERE farmer_id = " + farmerID + "";

            MubLog.cpnsoleLog(updateQuery);
        } else {
            updateQuery = "UPDATE " + FarmerCallBackTable + " SET add_farmer_no = '" + phoneNumber + "'  WHERE farmer_id = " + farmerID + "";
            MubLog.cpnsoleLog(updateQuery);
        }
        try {
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
        }

    }


    public void updateCallSyncStatus(String id, String synStatus) {

        try {
            this.db = this.database.getWritableDatabase();
            this.db.execSQL("UPDATE FarmerCallBackTable SET sync = '" + synStatus + "'  WHERE id = " + id);
            MubLog.cpnsoleLog("inside updateCallSyncStatus executed done " + id + "status " + synStatus);
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateCallSyncStatus() Exception is :"+e.toString());
            MubLog.cpnsoleLog("Exception inside updateCallSyncStatus " + e.toString());
        }
    }


    public boolean CheckandEnableCalldurationReason(Context context, String survey_status, String id, String farmer_id, String phoneNumber, String reason, boolean isAlterNameFarmer, String callAgain, String empID, String calldurationReason, DurationPopup durationPopup) {


        boolean CheckandEnableCalldurationReason = false;
        try {


            this.durationPopup = durationPopup;

            if (id != null) {

            } else {
                id = farmer_id;
            }


            String currentDate = "";


            if (survey_status.equalsIgnoreCase("1") && !calldurationReason.equalsIgnoreCase("")) {

                MubLog.cpnsoleLog("inside CheckandEnableCalldurationReason reason already provided " + calldurationReason);
                CheckandEnableCalldurationReason = false;

            } else {

                if (!isAlterNameFarmer) {
                    MubLog.cpnsoleLog("CheckandEnableCalldurationReason manually handling b14 invalid number received when ");

                    phoneNumber = re_initializePhoneNumber(farmer_id, survey_status, isAlterNameFarmer);

                }


                long lastCallDurationInMinutes = RConsUtils.LastCallDurationInMinutes(context, phoneNumber);
                if (survey_status.equalsIgnoreCase("1") && lastCallDurationInMinutes < 8) {

                    CheckandEnableCalldurationReason = true;
                } else {
                    MubLog.cpnsoleLog("CheckandEnableCalldurationReason survey_status not 1 so returning false");
                    CheckandEnableCalldurationReason = false;
                }


                if (durationPopup.showDurationPopUp()) {

                } else {
                    MubLog.cpnsoleLog("CheckandEnableCalldurationReason false returned so moving back without storing values");
                    //  return false;
                }
            }


            return CheckandEnableCalldurationReason;
        } catch (Exception e) {

            MubLog.cpnsoleLog("inside CheckandEnableCalldurationReason " + e.toString());
            return CheckandEnableCalldurationReason;
        }
    }


    public boolean updateCallStatus(Context context, String survey_status, String id, String farmer_id, String phoneNumber, String reason, boolean isAlterNameFarmer, String callAgain, String empID, String calldurationReason, DurationPopup durationPopup) {


        boolean callagain_flag_manual_set = false;
        try {


            this.durationPopup = durationPopup;
            CheckMissingColumnsList();


            if (reason != null) {
                if (!reason.equalsIgnoreCase("")) {
                    reason = reason.toUpperCase();
                }

            }


            if (isAlterNameFarmer) {
                CallMenuActivity.LAST_SAVED_STATUS_ID = "alt";
            } else {
                CallMenuActivity.LAST_SAVED_STATUS_ID = "sc";
            }
            CallMenuActivity.LAST_SAVED_FARMER_ID = farmer_id;

            if (id != null) {

            } else {
                id = farmer_id;
            }

            String checkedQuery = "";
            if (isAlterNameFarmer) {
                checkedQuery = "Select sc1_alt as statusOne , sc2_alt as statusTwo, sc3_alt as statusThree , temp2 from FarmerCallBackTable WHERE farmer_id = '" + farmer_id + "'";
            } else {
                checkedQuery = "Select sc1 as statusOne , sc2 as statusTwo, sc3 as statusThree  , temp2 from FarmerCallBackTable WHERE farmer_id = '" + farmer_id + "'";
            }

            String deviceID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            String currentDate = "";


//                     if(phoneNumber.equalsIgnoreCase("")){
//
//                         phoneNumber = re_initializePhoneNumber(farmer_id,survey_status,isAlterNameFarmer);
//
//                     }

            if (!isAlterNameFarmer) {
                MubLog.cpnsoleLog("manually handling b14 invalid number received when ");

                phoneNumber = re_initializePhoneNumber(farmer_id, survey_status, isAlterNameFarmer);

            }


            String duration = RConsUtils.LastCallDuration(context, phoneNumber);
            currentDate = RConsUtils.currentDateTime();


            if (durationPopup.showDurationPopUp()) {

            } else {
                MubLog.cpnsoleLog("false returned so moving back without storing values");
                //  return false;
            }


//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            try {
//                currentDate = RConsUtils.getLastCallTime(context, phoneNumber);
//                if(!StringUtils.isEmpty(currentDate)){
//                    currentDate =   formatter.format(Long.valueOf(currentDate));
//                }else{
//                    currentDate = RConsUtils.currentDateTime();
//                }
//            }catch (Exception e){
//                currentDate = RConsUtils.currentDateTime();
//
//            }

            String statusOne = "";
            String statusTwo = "";
            String statusThree = "";

            String updateQuery = "";


            String survey_status_for_org_and_alt = survey_status;


            String survey_status_org_reason = "survey_status_org_reason = '" + reason + "'";
            String survey_status_alt_reason = "survey_status_alt_reason = '" + reason + "'";


//phone number assigning last value if empty
            String defaultstatusforcallnum = getcalldone_number(farmer_id, survey_status, isAlterNameFarmer, phoneNumber);
            ;


            if (survey_status.equalsIgnoreCase("1")) {

                survey_status_for_org_and_alt = "";
                MubLog.cpnsoleLog("inside survey_status_for_org_and_alt status 1");


            }


            String survey_status_org_call_on = "survey_status_org_call_on = '" + defaultstatusforcallnum + "'";
            String survey_status_alt_call_on = "survey_status_alt_call_on = '" + defaultstatusforcallnum + "'";


            Cursor cursor = db.rawQuery(checkedQuery, new String[]{});
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                statusOne = cursor.getString(cursor.getColumnIndex("statusOne"));
                statusTwo = cursor.getString(cursor.getColumnIndex("statusTwo"));
                statusThree = cursor.getString(cursor.getColumnIndex("statusThree"));
                String temp2 = cursor.getString(cursor.getColumnIndex("temp2"));


                MubLog.cpnsoleLog("survey_status_org_reason " + survey_status_org_reason);
                MubLog.cpnsoleLog("survey_status_alt_reason " + survey_status_alt_reason);

                if (StringUtils.isEmpty(statusOne)) {

                    if (isAlterNameFarmer) {
                        // updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' , sc1_alt = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc1_end_date_time_alt = '" + currentDate + "' , sc1_alt_reason = '" + reason + "' , reason = '" + reason + "', duration_alt = '" + duration + "', temp1 = '" + callAgain + "' WHERE farmer_id = " + id + "";
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_alt_call_on + ", survey_status_alt = '" + survey_status_for_org_and_alt + "' ," + survey_status_alt_reason + ", sc1_alt = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc1_end_date_time_alt = '" + currentDate + "' , sc1_alt_reason = '" + reason + "' , reason = '" + reason + "', duration_alt = '" + duration + "', temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";

                    } else {
                        //  updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' , sc1 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc1_end_date_time = '" + currentDate + "' , sc1_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + id + "";
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_org_call_on + ", survey_status_org = '" + survey_status_for_org_and_alt + "' ," + survey_status_org_reason + ", sc1 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc1_end_date_time = '" + currentDate + "' , sc1_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";

                    }

                } else if (StringUtils.isEmpty(statusTwo)) {
                    if (isAlterNameFarmer) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_alt_call_on + ", survey_status_alt = '" + survey_status_for_org_and_alt + "'," + survey_status_alt_reason + ", sc2_alt = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc2_end_date_time_alt = '" + currentDate + "'  , sc2_alt_reason = '" + reason + "' , reason = '" + reason + "', duration_alt = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";

                    } else {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_org_call_on + ", survey_status_org = '" + survey_status_for_org_and_alt + "'," + survey_status_org_reason + ", sc2 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc2_end_date_time = '" + currentDate + "' , sc2_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";

                    }

                } else if (StringUtils.isEmpty(statusThree)) {   //disabled when 4447 issue occuered
                    if (isAlterNameFarmer) {

                        if (callAgain.equalsIgnoreCase("2")) {
                            //making call again false manually
                            updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_alt_call_on + ", survey_status_alt = '" + survey_status_for_org_and_alt + "'," + survey_status_alt_reason + ", sc3_alt = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_date_time_alt = '" + currentDate + "'  , sc3_alt_reason = '" + reason + "' , reason = '" + reason + "', duration_alt = '" + duration + "' , temp1 = '" + "1" + "' WHERE farmer_id = " + farmer_id + "";


                            callagain_flag_manual_set = true;
                        } else {
                            updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_alt_call_on + ", survey_status_alt = '" + survey_status_for_org_and_alt + "'," + survey_status_alt_reason + ", sc3_alt = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_date_time_alt = '" + currentDate + "'  , sc3_alt_reason = '" + reason + "' , reason = '" + reason + "', duration_alt = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";
                        }


                    } else {


                        //making call again false manually
                        if (callAgain.equalsIgnoreCase("2")) {
                            updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_org_call_on + ", survey_status_org = '" + survey_status_for_org_and_alt + "'," + survey_status_org_reason + ", sc3 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_date_time = '" + currentDate + "' , sc3_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + "1" + "' WHERE farmer_id = " + farmer_id + "";
                            callagain_flag_manual_set = true;
                        } else {
                            updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_org_call_on + ", survey_status_org = '" + survey_status_for_org_and_alt + "'," + survey_status_org_reason + ", sc3 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_date_time = '" + currentDate + "' , sc3_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";

                        }

                    }


                }
            } else {
                updateQuery = "UPDATE " + FarmerCallBackTable + " SET survey_status = '" + survey_status + "' ," + survey_status_org_call_on + ", survey_status_org = '" + survey_status_for_org_and_alt + "'," + survey_status_org_reason + ", sc1 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc1_end_date_time = '" + currentDate + "' , sc1_reason = '" + reason + "'  , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' WHERE farmer_id = " + farmer_id + "";

            }
            if (survey_status.equalsIgnoreCase("1") || survey_status.equalsIgnoreCase("3") || survey_status.equalsIgnoreCase("5") || survey_status.equalsIgnoreCase("7")) {

            }

            MubLog.cpnsoleLog("updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);


            Cursor basic = getBasicInfo(farmer_id);
            if (basic != null) {

                basic.moveToFirst();
                String farmerCellphone = basic.getString(basic.getColumnIndex("farmer_cellphone"));

                String altFarmerCellphone = basic.getString(basic.getColumnIndex("alt_farmer_cellphone"));

                MubLog.cpnsoleLog("farmerCellphone" + farmerCellphone);
                MubLog.cpnsoleLog("altFarmerCellphone" + altFarmerCellphone);
                if (farmerCellphone.equalsIgnoreCase(altFarmerCellphone) || altFarmerCellphone.equalsIgnoreCase("")) {


                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET   sc1_alt = '" + "N/A" + "' , deviceID = '" + deviceID + "' , sc1_end_date_time_alt = '" + "N/A" + "' , sc1_alt_reason = '" + "N/A" + "'  WHERE farmer_id = " + farmer_id + "";
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
                    MubLog.cpnsoleLog("executed 1");
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET   sc2_alt = '" + "N/A" + "' , deviceID = '" + deviceID + "' , sc2_end_date_time_alt = '" + "N/A" + "'  , sc2_alt_reason = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
                    MubLog.cpnsoleLog("executed 2");
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3_alt = '" + "N/A" + "' , deviceID = '" + deviceID + "' , sc3_end_date_time_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
                    MubLog.cpnsoleLog("executed 3");
                }

            }


            //if 3,5,6,7,10,
            updateFurtherStatusManually(farmer_id, survey_status, isAlterNameFarmer, statusOne, statusTwo, statusThree, callAgain);


            updateAlt_And_make_empty_if_surveystatusorg_complete(farmer_id, survey_status, isAlterNameFarmer, statusOne, statusTwo, statusThree, callAgain);

            update_insert_or_updated_in_phone_at(farmer_id, DatabaseAdapter.FarmerCallBackTable);

            //updateAllStatusManually_if_refusal(farmer_id,survey_status,isAlterNameFarmer,statusOne,statusTwo,statusThree,callAgain);

            updateAllStatusManually_if_original_and_10_and_1(farmer_id, survey_status, isAlterNameFarmer, statusOne, statusTwo, statusThree, callAgain);

            updateAllStatusManually_if_partial_cover(farmer_id, survey_status, isAlterNameFarmer, statusOne, statusTwo, statusThree, callAgain);


            update_enumName_enumcode(farmer_id);


            if (survey_status.equalsIgnoreCase("1") && !calldurationReason.equalsIgnoreCase(""))
                update_call_duration_reason(farmer_id, survey_status, isAlterNameFarmer, statusOne, statusTwo, statusThree, callAgain, calldurationReason);

            return callagain_flag_manual_set;
        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
            return callagain_flag_manual_set;
        }
    }


    private String re_initializePhoneNumber(String farmer_id, String survey_status, boolean isAlterNameFarmer) {

        String phoneNumber = "";
        try {

            MubLog.cpnsoleLog("inside re_initializePhoneNumber" + isAlterNameFarmer);


            Cursor basic = getBasicInfo(farmer_id);
            if (basic != null) {

                basic.moveToFirst();
                String farmerCellphone = basic.getString(basic.getColumnIndex("farmer_cellphone"));
                String addedPhoneNumber = basic.getString(basic.getColumnIndex("add_farmer_no"));

                String altFarmerCellphone = basic.getString(basic.getColumnIndex("alt_farmer_cellphone"));
                String addedAlternatePhoneNumber = basic.getString(basic.getColumnIndex("add_farmer_no_alt"));


                MubLog.cpnsoleLog("getcalldone_number farmerCellphone" + farmerCellphone);
                MubLog.cpnsoleLog("getcalldone_number add_farmer_no" + addedPhoneNumber);
                MubLog.cpnsoleLog("getcalldone_number altFarmerCellphone" + altFarmerCellphone);
                MubLog.cpnsoleLog("getcalldone_number add_farmer_no_alt" + addedAlternatePhoneNumber);


                MubLog.cpnsoleLog("getcalldone_number *********************");

                MubLog.cpnsoleLog("getcalldone_number add_farmer_no" + addedPhoneNumber);
                MubLog.cpnsoleLog("getcalldone_number add_farmer_no_alt" + addedAlternatePhoneNumber);
                MubLog.cpnsoleLog("getcalldone_number *********************");

                if (addedPhoneNumber == null)
                    addedPhoneNumber = "";

                if (addedAlternatePhoneNumber == null)
                    addedAlternatePhoneNumber = "";


                if (isAlterNameFarmer) {

                    if (!addedAlternatePhoneNumber.equalsIgnoreCase("")) {
                        MubLog.cpnsoleLog("addedAlternatePhoneNumber found matched so returning addedAlternatePhoneNumber" + addedAlternatePhoneNumber);
                        phoneNumber = addedAlternatePhoneNumber;
                    } else {
                        MubLog.cpnsoleLog("addedAlternatePhoneNumber not found matched so returning farmerCellphone" + altFarmerCellphone);
                        phoneNumber = altFarmerCellphone;
                    }


                } else if (!addedPhoneNumber.equalsIgnoreCase("")) {
                    MubLog.cpnsoleLog("addedPhoneNumber found matched so returning " + addedPhoneNumber);
                    phoneNumber = addedPhoneNumber;
                } else {
                    MubLog.cpnsoleLog("addedPhoneNumber not found matched so returning farmerCellphone" + farmerCellphone);
                    phoneNumber = farmerCellphone;
                }


            }


            return phoneNumber;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());

            MubLog.cpnsoleLog("inside getcalldone_number" + e.toString());
            return phoneNumber;

        }
    }


    private String getcalldone_number(String farmer_id, String survey_status, boolean isAlterNameFarmer, String phoneNumber) {

        String call_done_on_Number = "";
        try {

            MubLog.cpnsoleLog("inside getcalldone_number" + phoneNumber);


            if (phoneNumber.equalsIgnoreCase("")) {
                call_done_on_Number = "3";
            } else if (survey_status.equalsIgnoreCase("1")) {

                Cursor basic = getBasicInfo(farmer_id);
                if (basic != null) {

                    basic.moveToFirst();
                    String farmerCellphone = basic.getString(basic.getColumnIndex("farmer_cellphone"));
                    String addedPhoneNumber = basic.getString(basic.getColumnIndex("add_farmer_no"));

                    String altFarmerCellphone = basic.getString(basic.getColumnIndex("alt_farmer_cellphone"));
                    String addedAlternatePhoneNumber = basic.getString(basic.getColumnIndex("add_farmer_no_alt"));


                    MubLog.cpnsoleLog("getcalldone_number farmerCellphone" + farmerCellphone);
                    MubLog.cpnsoleLog("getcalldone_number add_farmer_no" + addedPhoneNumber);
                    MubLog.cpnsoleLog("getcalldone_number altFarmerCellphone" + altFarmerCellphone);
                    MubLog.cpnsoleLog("getcalldone_number add_farmer_no_alt" + addedAlternatePhoneNumber);


                    if (phoneNumber.length() > 9) {

                        phoneNumber = phoneNumber.substring(2);
                    }


                    if (addedPhoneNumber != null && addedPhoneNumber.length() > 9) {
                        addedPhoneNumber = addedPhoneNumber.substring(2);
                    }

                    if (addedAlternatePhoneNumber != null && addedAlternatePhoneNumber.length() > 9) {
                        addedAlternatePhoneNumber = addedAlternatePhoneNumber.substring(2);
                    }


                    MubLog.cpnsoleLog("getcalldone_number *********************");
                    MubLog.cpnsoleLog("getcalldone_number phoneNumber" + phoneNumber);
                    MubLog.cpnsoleLog("getcalldone_number add_farmer_no" + addedPhoneNumber);
                    MubLog.cpnsoleLog("getcalldone_number add_farmer_no_alt" + addedAlternatePhoneNumber);
                    MubLog.cpnsoleLog("getcalldone_number *********************");

                    if (addedPhoneNumber == null)
                        addedPhoneNumber = "";

                    if (addedAlternatePhoneNumber == null)
                        addedAlternatePhoneNumber = "";


                    if (isAlterNameFarmer) {

                        if (!addedAlternatePhoneNumber.equalsIgnoreCase("")) {
                            MubLog.cpnsoleLog("addedAlternatePhoneNumber found matched so returning 2");
                            call_done_on_Number = "2";
                        } else {
                            MubLog.cpnsoleLog("addedAlternatePhoneNumber not found matched so returning 1");
                            call_done_on_Number = "1";
                        }


                    } else if (!addedPhoneNumber.equalsIgnoreCase("")) {
                        MubLog.cpnsoleLog("addedPhoneNumber found matched so returning 2");
                        call_done_on_Number = "2";
                    } else {
                        MubLog.cpnsoleLog("addedPhoneNumber not found matched so returning 1");
                        call_done_on_Number = "1";
                    }


//                if (phoneNumber.equalsIgnoreCase(addedPhoneNumber) || phoneNumber.equalsIgnoreCase(addedAlternatePhoneNumber)) {
//
//                    MubLog.cpnsoleLog("number found matched so returning 2");
//                    call_done_on_Number= "2";
//                }else{
//                    MubLog.cpnsoleLog("number not  matched so returning 1");
//                    call_done_on_Number= "1";
//                }

                } else {
                    call_done_on_Number = "1";
                }

            } else {

                MubLog.cpnsoleLog("inside getcalldone_number survey status not 1 so returning hardcoded 3 ");
                call_done_on_Number = "3";
            }


            return call_done_on_Number;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());

            MubLog.cpnsoleLog("inside getcalldone_number" + e.toString());
            return call_done_on_Number = "";

        }
    }

    private void update_call_duration_reason(String farmer_id, String survey_status, boolean isAlterNameFarmer, String statusOne, String statusTwo, String statusThree, String callAgain, String calldurationReason) {

        try {
            String updateQuery = "";


            MubLog.cpnsoleLog("inside call_duration_reason calldurationReason " + calldurationReason);

            String call_duration_reason = "call_duration_reason = '" + calldurationReason + "'";


            updateQuery = "UPDATE " + FarmerCallBackTable + " SET " + call_duration_reason + "WHERE farmer_id = " + farmer_id + "";

            MubLog.cpnsoleLog("update call_duration_reason querry " + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception call_duration_reason" + e.toString());
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void update_insert_or_updated_in_phone_section_table(String farmer_id, String tableName) {

        try {
            String updateQuery = "";

            String time = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);

            MubLog.cpnsoleLog("inside update_insert_or_updated_in_phone_at survey_status time " + time);

            String insert_or_updated_in_phone_at = "insert_or_updated_in_phone_at = '" + time + "'";


            updateQuery = "UPDATE " + FarmerCallBackTable + " SET " + insert_or_updated_in_phone_at + "WHERE farmer_id = " + farmer_id + "";

            MubLog.cpnsoleLog("update update_insert_or_updated_in_phone_at querry " + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception update_insert_or_updated_in_phone_at" + e.toString());
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    public void update_insert_or_updated_in_phone_at(String farmer_id, String table_name) {

        try {


            CheckMissingColumnsList();


            String updateQuery = "";

            String time = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);

            MubLog.cpnsoleLog("inside update_insert_or_updated_in_phone_at survey_status time " + time);

            String insert_or_updated_in_phone_at = "insert_or_updated_in_phone_at = '" + time + "'";


            updateQuery = "UPDATE " + table_name + " SET " + insert_or_updated_in_phone_at + "WHERE farmer_id = " + farmer_id + "";

            MubLog.cpnsoleLog("update update_insert_or_updated_in_phone_at querry " + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception update_insert_or_updated_in_phone_at" + e.toString());
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void updateAlt_And_make_empty_if_surveystatusorg_complete(String farmer_id, String survey_status, boolean isAlterNameFarmer, String statusOne, String statusTwo, String statusThree, String callAgain) {

        try {
            String updateQuery = "";


            MubLog.cpnsoleLog("inside updateAlt_And_make_empty_if_surveystatusorg_complete survey_status" + survey_status);
            MubLog.cpnsoleLog("inside updateAlt_And_make_empty_if_surveystatusorg_complete isAlterNameFarmer" + isAlterNameFarmer);

//means survey complete on original or given by original
            if (survey_status.equalsIgnoreCase("1")) {


                if (!isAlterNameFarmer) {

                    String survey_status_for_org_and_alt = "";

                    String reason = "";
                    String defaultstatusforcallnum = "";
                    String survey_status_alt_reason = "survey_status_alt_reason = '" + reason + "'";
                    String survey_status_alt_call_on = "survey_status_alt_call_on = '" + defaultstatusforcallnum + "'";

                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET " + survey_status_alt_call_on + ", survey_status_alt = '" + survey_status_for_org_and_alt + "' ," + survey_status_alt_reason + "WHERE farmer_id = " + farmer_id + "";

                    MubLog.cpnsoleLog("update updateAlt_And_make_empty_if_surveystatusorg_complete querry " + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);


                }


            }


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception updateAlt_And_make_empty_if_surveystatusorg_complete" + e.toString());
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void update_enumName_enumcode(String farmer_id) {

        try {
            String updateQuery = "";
            MubLog.cpnsoleLog("inside update_enumName_enumcode _enumcode survey_status " + farmer_id + "   farmer_id");


            //for questionnaire app
//            if (survey_status.equalsIgnoreCase("5")) {


            MubLog.cpnsoleLog("inside update_enumName_enumcode ode survey_status doing to execute querries");

            String enum_name = "N/A";
            String enum_code = "N/A";
            if (RConsUtils.GetEnumState() == 1) {
                enum_name = RConsUtils.getEnumName();
                enum_code = RConsUtils.getEnumCode();
            }

            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  enum_name = '" + enum_name + "', enum_code = '" + enum_code + "', build_no = '" + BuildConfig.VERSION_NAME + "'  WHERE farmer_id = " + farmer_id;
            MubLog.cpnsoleLog("inside update_enumName_enumcode updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);


        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void updateAllStatusManually_if_partial_cover(String farmer_id, String survey_status, boolean isAlterNameFarmer, String statusOne, String statusTwo, String statusThree, String callAgain) {

        try {
            String updateQuery = "";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover survey_status " + isAlterNameFarmer + "   isAlterNameFarmer");
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover survey_status " + survey_status + "   setting status");
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover survey_status " + callAgain + "    callAgain");


            //for questionnaire app
//            if (survey_status.equalsIgnoreCase("5")) {


            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover survey_status doing to execute querries");


            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc1_alt = ''";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);


            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc2_alt = '' ";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc3_alt = ''";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc1 = '' ";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc2 = '' ";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

            updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc3 = '' ";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_partial_cover updateQuery" + updateQuery);
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

//            }


        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void updateAllStatusManually_if_original_and_10_and_1(String farmer_id, String survey_status, boolean isAlterNameFarmer, String statusOne, String statusTwo, String statusThree, String callAgain) {

        try {
            String updateQuery = "";
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_original_and_10_and_1 survey_status " + isAlterNameFarmer + "   isAlterNameFarmer");
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_original_and_10_and_1 survey_status " + survey_status + "   setting status");
            MubLog.cpnsoleLog("inside updateAllStatusManually_if_original_and_10_and_1 survey_status " + callAgain + "    callAgain");

            if ((survey_status.equalsIgnoreCase("10") && callAgain.equalsIgnoreCase("1")) || survey_status.equalsIgnoreCase("3")) {
                if (!isAlterNameFarmer) {

                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original_and_10_and_1 survey_status doing to execute querries");


//                   if(StringUtils.isEmpty(statusOne)){
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc1_alt = ''";
                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original updateQuery" + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
//                    }

//                    if(StringUtils.isEmpty(statusTwo)){
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc2_alt = '' ";
                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original updateQuery" + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
//                    }

//                    if(StringUtils.isEmpty(statusThree)){
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc3_alt = ''";
                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original updateQuery" + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
//                    }


//                    if (StringUtils.isEmpty(statusOne)) {
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc1 = '' ";
                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original updateQuery" + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
//                    }

//                    if (StringUtils.isEmpty(statusTwo)) {
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc2 = '' ";
                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original updateQuery" + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
//                    }

//                    if (StringUtils.isEmpty(statusThree)) {
                    updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc3 = '' ";
                    MubLog.cpnsoleLog("inside updateAllStatusManually_if_original updateQuery" + updateQuery);
                    db = database.getWritableDatabase();
                    db.execSQL(updateQuery);
//                    }
                }


            } else {


                MubLog.cpnsoleLog("inside updateAllStatusManually_if_original_and_10_and_1 survey_status " + survey_status + "   so not doing anything");
            }


        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void updateAllStatusManually_if_refusal(String farmer_id, String survey_status, boolean isAlterNameFarmer, String statusOne, String statusTwo, String statusThree, String callAgain) {

        try {
            String updateQuery = "";

            if (survey_status.equalsIgnoreCase("3")) {
                MubLog.cpnsoleLog("inside updateFurtherStatusManually survey_status " + survey_status + "   setting status");
                if (!isAlterNameFarmer) {

                    if (StringUtils.isEmpty(statusOne)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusTwo)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusThree)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }


                    if (StringUtils.isEmpty(statusOne)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusTwo)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusThree)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + "";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }
                }


            } else {


                MubLog.cpnsoleLog("inside updateFurtherStatusManually survey_status " + survey_status + "   so not doing anything");
            }


        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }


    private void updateFurtherStatusManually(String farmer_id, String survey_status, boolean isAlterNameFarmer, String statusOne, String statusTwo, String statusThree, String callAgain) {

        try {
            String updateQuery = "";

            if (survey_status.equalsIgnoreCase("3") || survey_status.equalsIgnoreCase("5") || survey_status.equalsIgnoreCase("6") || survey_status.equalsIgnoreCase("7") || survey_status.equalsIgnoreCase("10")) {


                if (survey_status.equalsIgnoreCase("10") && callAgain.equalsIgnoreCase("1")) {

                } else {

                    if (survey_status.equalsIgnoreCase("10") && callAgain.equalsIgnoreCase("2")) {
                        MubLog.cpnsoleLog("updateFurtherStatusManually not required becuase 10 and  call again  ");
                        return;
                    }
                }


                if (isAlterNameFarmer) {

                    if (StringUtils.isEmpty(statusOne)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc1_alt = '' ";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusTwo)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc2_alt = ''";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusThree)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3_alt = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc3_alt = '' ";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                } else {

                    if (StringUtils.isEmpty(statusOne)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc1 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc1 = ''";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusTwo)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc2 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc2 = ''";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                    if (StringUtils.isEmpty(statusThree)) {
                        updateQuery = "UPDATE " + FarmerCallBackTable + " SET  sc3 = '" + "N/A" + "' WHERE farmer_id = " + farmer_id + " AND sc3 = '' ";
                        db = database.getWritableDatabase();
                        db.execSQL(updateQuery);
                    }

                }


            }


        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside updateFurtherStatusManually() Exception is :"+e.toString());
        }
    }

//    void SaveTemp2Status(String alreadySavedValue, String farmerID, String empID) {
//        try {
//            String updateQuery = "";
//            if (StringUtils.isEmpty(alreadySavedValue)) {
//                updateQuery = "UPDATE FarmerCallBackTable set temp2 = 1 WHERE emp_id = " + empID;
//            } else {
//                updateQuery = "UPDATE FarmerCallBackTable set temp2 = 2 WHERE emp_id = " + empID;
//
//            }
//
//
//            db = database.getWritableDatabase();
//            db.execSQL(updateQuery);
//        } catch (Exception e) {
//            Log.e("TAG", e.toString());
//
//        }
//
//    }

    public void deleteAllData() {
        MubLog.cpnsoleLog("inside delete All data");
        String deleteQuery = "Delete from " + FarmerCallBackTable;
        db = database.getWritableDatabase();
        db.execSQL(deleteQuery);
        MubLog.cpnsoleLog("inside delete All data done");

    }

    public Cursor getCompletedCalls_old(String userName) {

        return db.rawQuery("SELECT * from FarmerCallBackTable WHERE rcons_user LIKE '%" + userName + "%' AND sync!='2' AND ifnull(length(survey_status), 0) != 0", new String[0]);
    }

    public Cursor getCompletedCalls(String userName) {

        return db.rawQuery("SELECT * from FarmerCallBackTable WHERE rcons_user LIKE '%" + userName + "%' AND sync!='2' AND ifnull(length(survey_status), 0) != 0", new String[0]);
    }

    public int getCompletedCallCounter(String userName) {


        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        String querry = "SELECT * FROM   farmercallbacktable WHERE  ( ( rcons_user LIKE '%" + userName + "%' AND sync != '2' )  AND farmer_id  IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)   OR  farmer_id  IN " + getFarmerId_sixTries_Done + "  ))";

        MubLog.cpnsoleLog("inside getNewCallsCount " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();

    }

    public int getCompletedCallCounter_old(String userName) {


        //original Cursor cursor = db.rawQuery("SELECT rcons_user from FarmerCallBackTable WHERE rcons_user LIKE '%" + userName + "%' AND sync!='2' AND ((survey_status IN (1,3,5) OR temp1 = 1) OR ((ifnull(length(sc1), 0) != 0 AND ifnull(length(sc2), 0) != 0 AND ifnull(length(sc3), 0) != 0) OR (ifnull(length(sc1_alt), 0) != 0 AND ifnull(length(sc2_alt), 0) != 0 AND ifnull(length(sc3_alt), 0) != 0) ))", new String[0]);
        Cursor cursor = db.rawQuery("SELECT rcons_user from FarmerCallBackTable WHERE rcons_user LIKE '%" + userName + "%' AND sync!='2' AND ((survey_status IN (1,3,5,6,7) OR temp1 = 1)) OR (((ifnull(length(sc1), 0) != 0 AND ifnull(length(sc2), 0) != 0 AND ifnull(length(sc3), 0) != 0) AND (ifnull(length(sc1_alt), 0) != 0 AND ifnull(length(sc2_alt), 0) != 0 AND ifnull(length(sc3_alt), 0) != 0) ))", new String[0]);
        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;

        }

    }

    public int getSuccessFullCallCounter(String userName) {

        Cursor cursor = db.rawQuery("SELECT * from " + FarmerCallBackTable + " WHERE rcons_user LIKE '%" + userName + "%' AND sync!='2' AND survey_status IN (1)", new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;

        }

    }

    public Cursor AddFarmerData(ContentValues cv) {

        Cursor cursor;
        db = database.getReadableDatabase();
        int counter = 0;
        String str = "";
        str = "select Count(*) as count from " + FarmerCallBackTable + " where  id =" + cv.get("id");

        cursor = db.rawQuery(str, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));

            if (ifExits == 0) {
                db = database.getWritableDatabase();
                try {
                    db.insert(FarmerCallBackTable, null, cv);
                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }
                Log.d("Inserted", "AddFarmerData: " + counter);
                counter++;
            } else {
                db = database.getReadableDatabase();
                String updatelaunch = "update " + FarmerCallBackTable + " set " +

                        "farmer_id='" + cv.get("farmer_id") + "', " +
                        "region='" + cv.get("region") + "', " +
                        "district_basedata='" + cv.get("district_basedate") + "', " +
                        "tehsil_basedata='" + cv.get("tehsil_basedate") + "', " +
                        "markaz_name='" + cv.get("markaz_name") + "', " +
                        "uc_name='" + cv.get("uc_name") + "', " +
                        "village_muza_basedata='" + cv.get("village_muza_basedate") + "', " +
                        "farmer_name='" + cv.get("farmer_name") + "', " +
                        "farmer_fatherhusband_name='" + cv.get("farmer_fatherhusband_name") + "', " +
                        "gender='" + cv.get("gender") + "', " +
                        "farmer_cellphone='" + cv.get("farmer_cellphone") + "', " +
                        "alt_farmer_name='" + cv.get("alt_farmer_name") + "', " +
                        "alt_relaction_with_farmer='" + cv.get("alt_relaction_with_farmer") + "', " +
                        "alt_farmer_cellphone='" + cv.get("alt_farmer_cellphone") + "', " +
                        "emp_no='" + cv.get("emp_no") + "', " +
                        "empost_designation='" + cv.get("empost_designation") + "', " +
                        "emp_fullname='" + cv.get("emp_fullname") + "', " +
                        "emp_id='" + cv.get("emp_id") + "', " +
                        "emp_cnic='" + cv.get("emp_cnic") + "', " +
                        "empost_imei='" + cv.get("empost_imei") + "', " +
                        "ao_assigned='" + cv.get("ao_assigned") + "', " +
                        "fa_assigned='" + cv.get("fa_assigned") + "', " +
                        "ai_assigned='" + cv.get("ai_assigned") + "', " +
                        "unique_id='" + cv.get("unique_id") + "', " +

                        "sc1='" + cv.get("sc1") + "', " +
                        "sc1_end_date_time='" + cv.get("sc1_end_date_time") + "', " +
                        "sc2='" + cv.get("sc2") + "', " +
                        "sc2_end_date_time='" + cv.get("sc2_end_date_time") + "', " +
                        "sc3='" + cv.get("sc3") + "', " +
                        "sc3_end_date_time='" + cv.get("sc3_end_date_time") + "', " +
                        "duration='" + cv.get("duration") + "', " +

                        "sc1_alt='" + cv.get("sc1_alt") + "', " +
                        "sc1_end_date_time_alt='" + cv.get("sc1_end_date_time_alt") + "', " +
                        "sc2_alt='" + cv.get("sc2_alt") + "', " +
                        "sc2_end_date_time_alt='" + cv.get("sc2_end_date_time_alt") + "', " +
                        "sc3_alt='" + cv.get("sc3_alt") + "', " +
                        "sc3_end_date_time_alt='" + cv.get("sc3_end_date_time_alt") + "', " +
                        "duration_alt='" + cv.get("duration_alt") + "', " +

                        "survey_status='" + cv.get("survey_status") + "', " +
                        "reason='" + cv.get("reason") + "', " +
                        "sync='" + cv.get("sync") + "', " +
                        "rcons_user='" + cv.get("rcons_user") + "', " +
                        "month='" + cv.get("month") + "', " +
                        "deviceid='" + cv.get("deviceid") + "', " +
                        "add_farmer_no='" + cv.get("add_farmer_no") + "', " +
                        "temp1='" + cv.get("temp1") + "', " +
                        "temp2='" + cv.get("temp2") + "', " +
                        "temp3='" + cv.get("temp3") + "', " +
                        "temp4='" + cv.get("temp4") + "', " +
                        "temp5='" + cv.get("temp5") + "' where id =" + cv.get("id");


                db.execSQL(updatelaunch);
                Log.d("Updated", "AddFarmerData: " + counter);
                counter++;
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor saveSection_B_Data(String emp_id, String order_id, String farmer_id, String enum_code, String enum_name, String b1, String status, String b2, String b3, String b3_other, String b4
            , String b5, String b6, String b7, String b7_hh, String b7_mm, String b7_day, String b7_month, String b7_alternative_number, String b8, String b9
            , String b10, String b10_other, String b11a, String b11b, String b12, String b13, String b13_hh, String b13_mm, String b13_day, String b13__month, String b14
            , String b15, String b16, String b16_other, String b17, String b18, String b19, String b19_hh, String b19_mm, String b19_day, String b19_month
            , String b19_alternative_number, String b20, String b21, String b22, String b22_other, String b23, String b24, String b25
            , String b25_hh, String b25_mm, String b25_day, String b25_month, String b26, String comments) {


        MubLog.cpnsoleLog("Inside saveSection_B_Data");
        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionBTable + " where  farmer_id =" + farmer_id;
        MubLog.cpnsoleLog("Inside saveSection_B_Data" + str);
        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("b1", b1);
                contentValues.put("status", status);
                contentValues.put("b2", b2);
                contentValues.put("b3", b3);
                contentValues.put("b3_other", b3_other);
                contentValues.put("b4", b4);
                contentValues.put("b5", b5);
                contentValues.put("b6", b6);
                contentValues.put("b7", b7);
                contentValues.put("b7_hh", b7_hh);
                contentValues.put("b7_mm", b7_mm);
                contentValues.put("b7_day", b7_day);
                contentValues.put("b7_month", b7_month);
                contentValues.put("b7_alternative_number", b7_alternative_number);
                contentValues.put("b8", b8);
                contentValues.put("b9", b9);
                contentValues.put("b10", b10);
                contentValues.put("b10_other", b10_other);
                contentValues.put("b11a", b11a);
                contentValues.put("b11b", b11b);
                contentValues.put("b12", b12);
                contentValues.put("b13", b13);
                contentValues.put("b13_hh", b13_hh);
                contentValues.put("b13_mm", b13_mm);
                contentValues.put("b13_day", b13_day);
                contentValues.put("b13__month", b13__month);
                contentValues.put("b14", b14);
                contentValues.put("b15", b15);
                contentValues.put("b16", b16);
                contentValues.put("b16_other", b16_other);
                contentValues.put("b17", b17);
                contentValues.put("b18", b18);
                contentValues.put("b19", b19);
                contentValues.put("b19_hh", b19_hh);
                contentValues.put("b19_mm", b19_mm);
                contentValues.put("b19_day", b19_day);
                contentValues.put("b19_month", b19_month);
                contentValues.put("b19_alternative_number", b19_alternative_number);
                contentValues.put("b20", b20);
                contentValues.put("b21", b21);
                contentValues.put("b22", b22);
                contentValues.put("b22_other", b22_other);
                contentValues.put("b23", b23);
                contentValues.put("b24", b24);
                contentValues.put("b25", b25);
                contentValues.put("b25_hh", b25_hh);
                contentValues.put("b25_mm", b25_mm);
                contentValues.put("b25_day", b25_day);
                contentValues.put("b25_month", b25_month);
                contentValues.put("b26", b26);
                contentValues.put("comments", comments);


                db.insertOrThrow(SectionBTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("b1", b1);
                contentValues.put("status", status);
                contentValues.put("b2", b2);
                contentValues.put("b3", b3);
                contentValues.put("b3_other", b3_other);
                contentValues.put("b4", b4);
                contentValues.put("b5", b5);
                contentValues.put("b6", b6);
                contentValues.put("b7", b7);
                contentValues.put("b7_hh", b7_hh);
                contentValues.put("b7_mm", b7_mm);
                contentValues.put("b7_day", b7_day);
                contentValues.put("b7_month", b7_month);
                contentValues.put("b7_alternative_number", b7_alternative_number);
                contentValues.put("b8", b8);
                contentValues.put("b9", b9);
                contentValues.put("b10", b10);
                contentValues.put("b10_other", b10_other);
                contentValues.put("b11a", b11a);
                contentValues.put("b11b", b11b);
                contentValues.put("b12", b12);
                contentValues.put("b13", b13);
                contentValues.put("b13_hh", b13_hh);
                contentValues.put("b13_mm", b13_mm);
                contentValues.put("b13_day", b13_day);
                contentValues.put("b13__month", b13__month);
                contentValues.put("b14", b14);
                contentValues.put("b15", b15);
                contentValues.put("b16", b16);
                contentValues.put("b16_other", b16_other);
                contentValues.put("b17", b17);
                contentValues.put("b18", b18);
                contentValues.put("b19", b19);
                contentValues.put("b19_hh", b19_hh);
                contentValues.put("b19_mm", b19_mm);
                contentValues.put("b19_day", b19_day);
                contentValues.put("b19_month", b19_month);
                contentValues.put("b19_alternative_number", b19_alternative_number);
                contentValues.put("b20", b20);
                contentValues.put("b21", b21);
                contentValues.put("b22", b22);
                contentValues.put("b22_other", b22_other);
                contentValues.put("b23", b23);
                contentValues.put("b24", b24);
                contentValues.put("b25", b25);
                contentValues.put("b25_hh", b25_hh);
                contentValues.put("b25_mm", b25_mm);
                contentValues.put("b25_day", b25_day);
                contentValues.put("b25_month", b25_month);
                contentValues.put("b26", b26);
                contentValues.put("comments", comments);


                db.update(SectionBTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSection_C_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String c1, String c2_day, String c2_month, String c2_year, String c2_hh, String c2_mm, String c3_day
            , String c3_month, String c3_year, String c4_hh, String c4_mm, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionCTable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);

                contentValues.put("c1", c1);
                contentValues.put("c2_day", c2_day);
                contentValues.put("c2_month", c2_month);
                contentValues.put("c2_year", c2_year);
                contentValues.put("c2_hh", c2_hh);
                contentValues.put("c2_mm", c2_mm);
                contentValues.put("c3_day", c3_day);
                contentValues.put("c3_month", c3_month);
                contentValues.put("c3_year", c3_year);
                contentValues.put("c4_hh", c4_hh);
                contentValues.put("c4_mm", c4_mm);
                contentValues.put("comments", comments);


                db.insertOrThrow(SectionCTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);

                contentValues.put("c1", c1);
                contentValues.put("c2_day", c2_day);
                contentValues.put("c2_month", c2_month);
                contentValues.put("c2_year", c2_year);
                contentValues.put("c2_hh", c2_hh);
                contentValues.put("c2_mm", c2_mm);
                contentValues.put("c3_day", c3_day);
                contentValues.put("c3_month", c3_month);
                contentValues.put("c3_year", c3_year);
                contentValues.put("c4_hh", c4_hh);
                contentValues.put("c4_mm", c4_mm);
                contentValues.put("comments", comments);

                db.update(SectionCTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSection_D_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String d1, String d2_uc_name, String d2_uc_code, String d2_village_name, String d2_village_code
            , String d3, String d3_tehsil_name, String d3_tehsil_code, String d4, String d5, String d6, String d7, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionDTable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);

                contentValues.put("d1", d1);
                contentValues.put("d2_uc_name", d2_uc_name);
                contentValues.put("d2_uc_code", d2_uc_code);
                contentValues.put("d2_village_name", d2_village_name);
                contentValues.put("d2_village_code", d2_village_code);
                contentValues.put("d3", d3);

                contentValues.put("d3_tehsil_name", d3_tehsil_name);
                contentValues.put("d3_tehsil_code", d3_tehsil_code);
                contentValues.put("d4", d4);
                contentValues.put("d5", d5);

                contentValues.put("d6", d6);
                contentValues.put("d7", d7);
                contentValues.put("comments", comments);


                db.insertOrThrow(SectionDTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);

                contentValues.put("d1", d1);
                contentValues.put("d2_uc_name", d2_uc_name);
                contentValues.put("d2_uc_code", d2_uc_code);
                contentValues.put("d2_village_name", d2_village_name);
                contentValues.put("d2_village_code", d2_village_code);
                contentValues.put("d3", d3);

                contentValues.put("d3_tehsil_name", d3_tehsil_name);
                contentValues.put("d3_tehsil_code", d3_tehsil_code);
                contentValues.put("d4", d4);
                contentValues.put("d5", d5);

                contentValues.put("d6", d6);
                contentValues.put("d7", d7);
                contentValues.put("comments", comments);

                db.update(SectionDTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSection_E_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String e1, String e1_other, String e2, String e3a, String e3b, String e4
            , String e4_other, String e5, String e6a, String e6b, String e7, String e8, String e9, String e10, String e11
            , String e11a, String e11b, String e11c, String e11d
            , String e12, String e13, String e14, String e15a, String e15b, String e15c, String e15_other, String e15_2_other, String e15_3_other, String e16, String e17
            , String e17a, String e17b, String e17c, String e17d
            , String e18, String e19, String e20, String e21, String e22, String e23, String e24, String e25, String e26, String e27, String e28
            , String e29, String e30, String e31, String e32, String e32a, String e33a, String e33b, String e33c, String e33_other, String e34, String e34_other
            , String e35a, String e35b, String e35c, String e35_other, String e36a, String e36b, String e36c, String e36_other, String e37, String e38a
            , String e38b, String e38c, String e38_other, String e39, String e40, String e41, String e42, String e42_other, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionETable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);

                contentValues.put("e1", e1);
                contentValues.put("e1_other", e1_other);
                contentValues.put("e2", e2);
                contentValues.put("e3a", e3a);
                contentValues.put("e3b", e3b);

                contentValues.put("e4", e4);
                contentValues.put("e4_other", e4_other);
                contentValues.put("e5", e5);
                contentValues.put("e6a", e6a);
                contentValues.put("e6b", e6b);
                contentValues.put("e7", e7);
                contentValues.put("e8", e8);
                contentValues.put("e9", e9);
                contentValues.put("e10", e10);
                contentValues.put("e11", e11);
                contentValues.put("e11a", e11a);
                contentValues.put("e11b", e11b);
                contentValues.put("e11c", e11c);
                contentValues.put("e11d", e11d);
                contentValues.put("e12", e12);
                contentValues.put("e13", e13);
                contentValues.put("e14", e14);
                contentValues.put("e15a", e15a);
                contentValues.put("e15b", e15b);
                contentValues.put("e15c", e15c);
                contentValues.put("e15_other", e15_other);
                contentValues.put("e15_2_other", e15_2_other);
                contentValues.put("e15_3_other", e15_3_other);
                contentValues.put("e16", e16);
                contentValues.put("e17", e17);
                contentValues.put("e17a", e17a);
                contentValues.put("e17b", e17b);
                contentValues.put("e17c", e17c);
                contentValues.put("e17d", e17d);
                contentValues.put("e18", e18);

                contentValues.put("e19", e19);
                contentValues.put("e20", e20);
                contentValues.put("e21", e21);
                contentValues.put("e22", e22);
                contentValues.put("e23", e23);
                contentValues.put("e24", e24);
                contentValues.put("e25", e25);
                contentValues.put("e26", e26);
                contentValues.put("e27", e27);

                contentValues.put("e28", e28);
                contentValues.put("e29", e29);
                contentValues.put("e30", e30);
                contentValues.put("e31", e31);
                contentValues.put("e32", e32);

                contentValues.put("e32a", e32a);
                contentValues.put("e33a", e33a);
                contentValues.put("e33b", e33b);
                contentValues.put("e33c", e33c);
                contentValues.put("e33_other", e33_other);
                contentValues.put("e34", e34);
                contentValues.put("e34_other", e34_other);
                contentValues.put("e35a", e35a);
                contentValues.put("e35b", e35b);
                contentValues.put("e35c", e35c);

                contentValues.put("e35_other", e35_other);
                contentValues.put("e36a", e36a);
                contentValues.put("e36b", e36b);
                contentValues.put("e36c", e36c);
                contentValues.put("e36_other", e36_other);
                contentValues.put("e37", e37);
                contentValues.put("e38a", e38a);
                contentValues.put("e38b", e38b);

                contentValues.put("e38c", e38c);
                contentValues.put("e38_other", e38_other);
                contentValues.put("e39", e39);
                contentValues.put("e40", e40);

                contentValues.put("e41", e41);
                contentValues.put("e42", e42);
                contentValues.put("e42_other", e42_other);

                contentValues.put("comments", comments);


                db.insertOrThrow(SectionETable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);

                contentValues.put("e1", e1);
                contentValues.put("e1_other", e1_other);
                contentValues.put("e2", e2);
                contentValues.put("e3a", e3a);
                contentValues.put("e3b", e3b);

                contentValues.put("e4", e4);
                contentValues.put("e4_other", e4_other);
                contentValues.put("e5", e5);
                contentValues.put("e6a", e6a);
                contentValues.put("e6b", e6b);
                contentValues.put("e7", e7);
                contentValues.put("e8", e8);
                contentValues.put("e9", e9);
                contentValues.put("e10", e10);
                contentValues.put("e11", e11);
                contentValues.put("e11a", e11a);
                contentValues.put("e11b", e11b);
                contentValues.put("e11c", e11c);
                contentValues.put("e11d", e11d);
                contentValues.put("e12", e12);
                contentValues.put("e13", e13);
                contentValues.put("e14", e14);
                contentValues.put("e15a", e15a);
                contentValues.put("e15b", e15b);
                contentValues.put("e15c", e15c);
                contentValues.put("e15_other", e15_other);
                contentValues.put("e15_2_other", e15_2_other);
                contentValues.put("e15_3_other", e15_3_other);
                contentValues.put("e16", e16);
                contentValues.put("e17", e17);
                contentValues.put("e17a", e17a);
                contentValues.put("e17b", e17b);
                contentValues.put("e17c", e17c);
                contentValues.put("e17d", e17d);
                contentValues.put("e18", e18);

                contentValues.put("e19", e19);
                contentValues.put("e20", e20);
                contentValues.put("e21", e21);
                contentValues.put("e22", e22);
                contentValues.put("e23", e23);
                contentValues.put("e24", e24);
                contentValues.put("e25", e25);
                contentValues.put("e26", e26);
                contentValues.put("e27", e27);

                contentValues.put("e28", e28);
                contentValues.put("e29", e29);
                contentValues.put("e30", e30);
                contentValues.put("e31", e31);
                contentValues.put("e32", e32);

                contentValues.put("e32a", e32a);
                contentValues.put("e33a", e33a);
                contentValues.put("e33b", e33b);
                contentValues.put("e33c", e33c);
                contentValues.put("e33_other", e33_other);
                contentValues.put("e34", e34);
                contentValues.put("e34_other", e34_other);
                contentValues.put("e35a", e35a);
                contentValues.put("e35b", e35b);
                contentValues.put("e35c", e35c);

                contentValues.put("e35_other", e35_other);
                contentValues.put("e36a", e36a);
                contentValues.put("e36b", e36b);
                contentValues.put("e36c", e36c);
                contentValues.put("e36_other", e36_other);
                contentValues.put("e37", e37);
                contentValues.put("e38a", e38a);
                contentValues.put("e38b", e38b);

                contentValues.put("e38c", e38c);
                contentValues.put("e38_other", e38_other);
                contentValues.put("e39", e39);
                contentValues.put("e40", e40);

                contentValues.put("e41", e41);
                contentValues.put("e42", e42);
                contentValues.put("e42_other", e42_other);

                contentValues.put("comments", comments);

                db.update(SectionETable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSectionF_table1_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String d8a, String d8b, String d8b_other, String d8c_kanal, String d8c_acre, String d8d_kanal
            , String d8d_acre, String d9_kanal, String d9_acre, String d10, String d11_kanal, String d11_acre, String d12, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionFTable1 + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("d8a", d8a);
                contentValues.put("d8b", d8b);
                contentValues.put("d8b_other", d8b_other);
                contentValues.put("d8c_kanal", d8c_kanal);
                contentValues.put("d8c_acre", d8c_acre);
                contentValues.put("d8d_kanal", d8d_kanal);
                contentValues.put("d8d_acre", d8d_acre);
                contentValues.put("d9_kanal", d9_kanal);
                contentValues.put("d9_acre", d9_acre);
                contentValues.put("d10", d10);
                contentValues.put("d11_kanal", d11_kanal);
                contentValues.put("d11_acre", d11_acre);
                contentValues.put("d12", d12);


                db.insertOrThrow(SectionFTable1, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("d8a", d8a);
                contentValues.put("d8b", d8b);
                contentValues.put("d8b_other", d8b_other);
                contentValues.put("d8c_kanal", d8c_kanal);
                contentValues.put("d8c_acre", d8c_acre);
                contentValues.put("d8d_kanal", d8d_kanal);
                contentValues.put("d8d_acre", d8d_acre);
                contentValues.put("d9_kanal", d9_kanal);
                contentValues.put("d9_acre", d9_acre);
                contentValues.put("d10", d10);
                contentValues.put("d11_kanal", d11_kanal);
                contentValues.put("d11_acre", d11_acre);
                contentValues.put("d12", d12);

                db.update(SectionFTable1, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor saveSectionF_table2_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String f1_crop, String f1_other, String f2a_kanal, String f2a_acre, String f2b_crop, String f2b_other
            , String f2b_kanal, String f2b_acre, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionFTable2 + " where  farmer_id= " + farmer_id + " AND f1_crop= " + f1_crop + "";
        //   str = "select Count(*) as count from " + SectionFTable2 + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f1_crop", f1_crop);
                contentValues.put("f1_other", f1_other);
                contentValues.put("f2a_kanal", f2a_kanal);
                contentValues.put("f2a_acre", f2a_acre);
                contentValues.put("f2b_crop", f2b_crop);
                contentValues.put("f2b_other", f2b_other);
                contentValues.put("f2b_kanal", f2b_kanal);
                contentValues.put("f2b_acre", f2b_acre);
                contentValues.put("comments", comments);

                db.insertOrThrow(SectionFTable2, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f1_crop", f1_crop);
                contentValues.put("f1_other", f1_other);
                contentValues.put("f2a_kanal", f2a_kanal);
                contentValues.put("f2a_acre", f2a_acre);
                contentValues.put("f2b_crop", f2b_crop);
                contentValues.put("f2b_other", f2b_other);
                contentValues.put("f2b_kanal", f2b_kanal);
                contentValues.put("f2b_acre", f2b_acre);
                contentValues.put("comments", comments);

                db.update(SectionFTable2, contentValues, "farmer_id=" + farmer_id + " and f1_crop=" + f1_crop, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor saveSectionF_table3_Data(String emp_id, String order_id, String farmer_id, String f2c_crop, String f2c_other, String f2c_kanal, String f2c_acre) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionFTable3 + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f2c_crop", f2c_crop);
                contentValues.put("f2c_other", f2c_other);
                contentValues.put("f2c_kanal", f2c_kanal);
                contentValues.put("f2c_acre", f2c_acre);


                db.insertOrThrow(SectionFTable3, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f2c_crop", f2c_crop);
                contentValues.put("f2c_other", f2c_other);
                contentValues.put("f2c_kanal", f2c_kanal);
                contentValues.put("f2c_acre", f2c_acre);


                db.update(SectionFTable3, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSectionF_table4_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String f3, String f4_a, String f4_b, String f4_c, String f4_other, String f5, String f6_a
            , String f6_b, String f6_c, String f6_other, String f7, String f8_a, String f8_b, String f8_c, String f8_other, String f9
            , String f10_a, String f10_b, String f10_c, String f10_other, String f11, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionFTable4 + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f3", f3);
                contentValues.put("f4_a", f4_a);
                contentValues.put("f4_b", f4_b);
                contentValues.put("f4_c", f4_c);
                contentValues.put("f4_other", f4_other);
                contentValues.put("f5", f5);
                contentValues.put("f6_a", f6_a);
                contentValues.put("f6_b", f6_b);
                contentValues.put("f6_c", f6_c);
                contentValues.put("f6_other", f6_other);
                contentValues.put("f7", f7);
                contentValues.put("f8_a", f8_a);
                contentValues.put("f8_b", f8_b);
                contentValues.put("f8_c", f8_c);
                contentValues.put("f8_other", f8_other);
                contentValues.put("f9", f9);
                contentValues.put("f10_a", f10_a);
                contentValues.put("f10_b", f10_b);
                contentValues.put("f10_c", f10_c);
                contentValues.put("f10_other", f10_other);
                contentValues.put("f11", f11);

                db.insertOrThrow(SectionFTable4, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f3", f3);
                contentValues.put("f4_a", f4_a);
                contentValues.put("f4_b", f4_b);
                contentValues.put("f4_c", f4_c);
                contentValues.put("f4_other", f4_other);
                contentValues.put("f5", f5);
                contentValues.put("f6_a", f6_a);
                contentValues.put("f6_b", f6_b);
                contentValues.put("f6_c", f6_c);
                contentValues.put("f6_other", f6_other);
                contentValues.put("f7", f7);
                contentValues.put("f8_a", f8_a);
                contentValues.put("f8_b", f8_b);
                contentValues.put("f8_c", f8_c);
                contentValues.put("f8_other", f8_other);
                contentValues.put("f9", f9);
                contentValues.put("f10_a", f10_a);
                contentValues.put("f10_b", f10_b);
                contentValues.put("f10_c", f10_c);
                contentValues.put("f10_other", f10_other);
                contentValues.put("f11", f11);

                db.update(SectionFTable4, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor saveSectionF_table5_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String f12_crop, String f12_other, String f13, String f13_other, String f14, String f14_other
            , String f15, String f15_other, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionFTable5 + " where  farmer_id= " + farmer_id + " AND f12_crop= " + f12_crop + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f12_crop", f12_crop);
                contentValues.put("f12_other", f12_other);
                contentValues.put("f13", f13);
                contentValues.put("f13_other", f13_other);
                contentValues.put("f14", f14);
                contentValues.put("f14_other", f14_other);
                contentValues.put("f15", f15);
                contentValues.put("f15_other", f15_other);


                db.insertOrThrow(SectionFTable5, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f12_crop", f12_crop);
                contentValues.put("f12_other", f12_other);
                contentValues.put("f13", f13);
                contentValues.put("f13_other", f13_other);
                contentValues.put("f14", f14);
                contentValues.put("f14_other", f14_other);
                contentValues.put("f15", f15);
                contentValues.put("f15_other", f15_other);


                db.update(SectionFTable5, contentValues, "farmer_id=" + farmer_id + " and f12_crop=" + f12_crop, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSectionF_table6_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String f16, String f17, String f18, String f19, String f20, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionFTable6 + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f16", f16);
                contentValues.put("f17", f17);
                contentValues.put("f18", f18);
                contentValues.put("f19", f19);
                contentValues.put("f20", f20);


                db.insertOrThrow(SectionFTable6, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("f16", f16);
                contentValues.put("f17", f17);
                contentValues.put("f18", f18);
                contentValues.put("f19", f19);
                contentValues.put("f20", f20);


                db.update(SectionFTable6, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor saveSectionG_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String g1, String g2, String g3_kanal, String g3_acre, String g3b, String g4
            , String g4b, String g4c, String g5, String g6_1, String g7_1, String g6_2, String g7_2, String g6_3, String g7_3, String g6_4, String g7_4, String g6_5, String g6_6, String g6_7_other
            , String g6_7, String g7_7, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionGTable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("g1", g1);
                contentValues.put("g2", g2);
                contentValues.put("g3_kanal", g3_kanal);
                contentValues.put("g3_acre", g3_acre);
                contentValues.put("g3b", g3b);
                contentValues.put("g4", g4);
                contentValues.put("g4b", g4b);
                contentValues.put("g4c", g4c);
                contentValues.put("g5", g5);
                contentValues.put("g6_1", g6_1);
                contentValues.put("g7_1", g7_1);
                contentValues.put("g6_2", g6_2);
                contentValues.put("g7_2", g7_2);
                contentValues.put("g6_3", g6_3);
                contentValues.put("g7_3", g7_3);
                contentValues.put("g6_4", g6_4);
                contentValues.put("g7_4", g7_4);
                contentValues.put("g6_5", g6_5);
                contentValues.put("g6_6", g6_6);
                contentValues.put("g6_7_other", g6_7_other);
                contentValues.put("g6_7", g6_7);
                contentValues.put("g7_7", g7_7);


                db.insertOrThrow(SectionGTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("g1", g1);
                contentValues.put("g2", g2);
                contentValues.put("g3_kanal", g3_kanal);
                contentValues.put("g3_acre", g3_acre);
                contentValues.put("g3b", g3b);
                contentValues.put("g4", g4);
                contentValues.put("g4b", g4b);
                contentValues.put("g4c", g4c);
                contentValues.put("g5", g5);
                contentValues.put("g6_1", g6_1);
                contentValues.put("g7_1", g7_1);
                contentValues.put("g6_2", g6_2);
                contentValues.put("g7_2", g7_2);
                contentValues.put("g6_3", g6_3);
                contentValues.put("g7_3", g7_3);
                contentValues.put("g6_4", g6_4);
                contentValues.put("g7_4", g7_4);
                contentValues.put("g6_5", g6_5);
                contentValues.put("g6_6", g6_6);
                contentValues.put("g6_7_other", g6_7_other);
                contentValues.put("g6_7", g6_7);
                contentValues.put("g7_7", g7_7);

                db.update(SectionGTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor saveSectionG_b_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String g8, String g9_kanal, String g9_acre, String g10, String g11
            , String g12_1, String g13_1, String g12_2, String g13_2, String g12_3, String g13_3, String g12_4, String g13_4, String g12_5, String g12_6, String g12_7_other
            , String g12_7, String g13_7, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionG_bTable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("g8", g8);
                contentValues.put("g9_kanal", g9_kanal);
                contentValues.put("g9_acre", g9_acre);
                contentValues.put("g10", g10);
                contentValues.put("g11", g11);
                contentValues.put("g12_1", g12_1);
                contentValues.put("g13_1", g13_1);
                contentValues.put("g12_2", g12_2);
                contentValues.put("g13_2", g13_2);
                contentValues.put("g12_3", g12_3);
                contentValues.put("g13_3", g13_3);
                contentValues.put("g12_4", g12_4);
                contentValues.put("g13_4", g13_4);
                contentValues.put("g12_5", g12_5);
                contentValues.put("g12_6", g12_6);
                contentValues.put("g12_7_other", g12_7_other);
                contentValues.put("g12_7", g12_7);
                contentValues.put("g13_7", g13_7);


                db.insertOrThrow(SectionG_bTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("g8", g8);
                contentValues.put("g9_kanal", g9_kanal);
                contentValues.put("g9_acre", g9_acre);
                contentValues.put("g10", g10);
                contentValues.put("g11", g11);
                contentValues.put("g12_1", g12_1);
                contentValues.put("g13_1", g13_1);
                contentValues.put("g12_2", g12_2);
                contentValues.put("g13_2", g13_2);
                contentValues.put("g12_3", g12_3);
                contentValues.put("g13_3", g13_3);
                contentValues.put("g12_4", g12_4);
                contentValues.put("g13_4", g13_4);
                contentValues.put("g12_5", g12_5);
                contentValues.put("g12_6", g12_6);
                contentValues.put("g12_7_other", g12_7_other);
                contentValues.put("g12_7", g12_7);
                contentValues.put("g13_7", g13_7);

                db.update(SectionG_bTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSectionH_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String h1, String h2, String h3, String h4, String h5, String h6
            , String h7, String h8, String h9, String h10, String comments) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionHTable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("h1", h1);
                contentValues.put("h2", h2);
                contentValues.put("h3", h3);
                contentValues.put("h4", h4);
                contentValues.put("h5", h5);
                contentValues.put("h6", h6);
                contentValues.put("h7", h7);
                contentValues.put("h8", h8);
                contentValues.put("h9", h9);
                contentValues.put("h10", h10);

                db.insertOrThrow(SectionHTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("h1", h1);
                contentValues.put("h2", h2);
                contentValues.put("h3", h3);
                contentValues.put("h4", h4);
                contentValues.put("h5", h5);
                contentValues.put("h6", h6);
                contentValues.put("h7", h7);
                contentValues.put("h8", h8);
                contentValues.put("h9", h9);
                contentValues.put("h10", h10);

                db.update(SectionHTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSectionI_Data(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String q1_1, String q1_2_hh, String q1_2_mm, String q1_3, String q1_4, String j1, String j2
            , String j3, String end_day, String end_month, String end_year) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + Section1Table + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("q1_1", q1_1);
                contentValues.put("q1_2_hh", q1_2_hh);
                contentValues.put("q1_2_mm", q1_2_mm);
                contentValues.put("q1_3", q1_3);
                contentValues.put("q1_4", q1_4);
                contentValues.put("j1", j1);
                contentValues.put("j2", j2);
                contentValues.put("j3", j3);

                contentValues.put("end_day", end_day);
                contentValues.put("end_month", end_month);
                contentValues.put("end_year", end_year);

                db.insertOrThrow(Section1Table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("q1_1", q1_1);
                contentValues.put("q1_2_hh", q1_2_hh);
                contentValues.put("q1_2_mm", q1_2_mm);
                contentValues.put("q1_3", q1_3);
                contentValues.put("q1_4", q1_4);
                contentValues.put("j1", j1);
                contentValues.put("j2", j2);
                contentValues.put("j3", j3);

                contentValues.put("end_day", end_day);
                contentValues.put("end_month", end_month);
                contentValues.put("end_year", end_year);


                db.update(Section1Table, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor saveSectionA_Data(String emp_id, String order_id, String farmer_id, String person_id, String rcons_user, String enum_name, String enum_code, String phone_number, String q0,
                                    String q0_other, String q1a, String q1b, String q2, String q3a, String q3b,
                                    String q4a, String q4b, String q5, String q6, String q7,
                                    String q8, String q9, String q10a, String q10b, String q10c, String q10d, String q10_other,
                                    String insert_or_updated_in_phone_at, String deviceid, String build_no
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionATable + " where  person_id =" + person_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("person_id", person_id);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("phone_number", phone_number);

                contentValues.put("q0", q0);
                contentValues.put("q0_other", q0_other);
                contentValues.put("q1a", q1a);
                contentValues.put("q1b", q1b);
                contentValues.put("q2", q2);
                contentValues.put("q3a", q3a);
                contentValues.put("q3b", q3b);
                contentValues.put("q4a", q4a);
                contentValues.put("q4b", q4b);
                contentValues.put("q5", q5);
                contentValues.put("q6", q6);
                contentValues.put("q7", q7);
                contentValues.put("q8", q8);
                contentValues.put("q9", q9);
                contentValues.put("q10a", q10a);
                contentValues.put("q10b", q10b);
                contentValues.put("q10c", q10c);
                contentValues.put("q10d", q10d);
                contentValues.put("q10_other", q10_other);


                db.insertOrThrow(SectionATable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("person_id", person_id);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);

                contentValues.put("q0", q0);
                contentValues.put("q0_other", q0_other);
                contentValues.put("q1a", q1a);
                contentValues.put("q1b", q1b);
                contentValues.put("q2", q2);
                contentValues.put("q3a", q3a);
                contentValues.put("q3b", q3b);
                contentValues.put("q4a", q4a);
                contentValues.put("q4b", q4b);
                contentValues.put("q5", q5);
                contentValues.put("q6", q6);
                contentValues.put("q7", q7);
                contentValues.put("q8", q8);
                contentValues.put("q9", q9);
                contentValues.put("q10a", q10a);
                contentValues.put("q10b", q10b);
                contentValues.put("q10c", q10c);
                contentValues.put("q10d", q10d);
                contentValues.put("q10_other", q10_other);

                db.update(SectionATable, contentValues, "person_id=" + person_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor getSpecificFromCallBackTable(String farmerID) {
        // String query = "SELECT FarmerCallBackTable.farmer_id , FarmerCallBackTable.region , FarmerCallBackTable.emp_id , FarmerCallBackTable.order_id, FarmerCallBackTable.alt_farmer_cellphone , FarmerCallBackTable.farmer_cellphone , FarmerCallBackTable.district_basedata , FarmerCallBackTable.village_muza_basedata, FarmerCallBackTable.tehsil_basedata FROM FarmerCallBackTable where farmer_id='" + farmerID + "'";
        String query = "SELECT * FROM FarmerCallBackTable where farmer_id='" + farmerID + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionBData(String farmer_id) {
        String query = "SELECT * from " + SectionBTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionCData(String farmer_id) {
        String query = "SELECT * from " + SectionCTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionDData(String farmer_id) {
        String query = "SELECT * from " + SectionDTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionAData(String person_id) {
        String query = "SELECT * from " + SectionATable + " where  person_id= " + person_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionEData(String farmer_id) {
        String query = "SELECT * from " + SectionETable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_1Data(String farmer_id) {
        String query = "SELECT * from " + SectionFTable1 + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_2Recycler(String farmer_id) {
        String query = "SELECT * from " + SectionFTable2 + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_2Data(String farmer_id, String f1_crop) {
        String query = "SELECT * from " + SectionFTable2 + " where  farmer_id= '" + farmer_id + "' AND f1_crop= '" + f1_crop + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_5Data_(String farmer_id, String f12_crop) {
        String query = "SELECT * from " + SectionFTable5 + " where  farmer_id= '" + farmer_id + "' AND f12_crop= '" + f12_crop + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_3Data(String farmer_id) {
        String query = "SELECT * from " + SectionFTable3 + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }


    public Cursor getSectionF_table_4Data(String farmer_id) {
        String query = "SELECT * from " + SectionFTable4 + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_5Data(String farmer_id) {
        String query = "SELECT * from " + SectionFTable5 + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionF_table_6Data(String farmer_id) {
        String query = "SELECT * from " + SectionFTable6 + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionGData(String farmer_id) {
        String query = "SELECT * from " + SectionGTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionG_bData(String farmer_id) {
        String query = "SELECT * from " + SectionG_bTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSectionHData(String farmer_id) {
        String query = "SELECT * from " + SectionHTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getSection1Data(String farmer_id) {
        String query = "SELECT * from " + Section1Table + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getBasicInfo(String farmer_id) {
        String query = "SELECT * from " + FarmerCallBackTable + " where  farmer_id= " + farmer_id;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }


    public String getUserNameagainstFarmerID(String farmer_id) {

        String rcons_user = "";
        String query = "SELECT rcons_user from " + FarmerCallBackTable + " where  farmer_id= " + farmer_id;
        MubLog.cpnsoleLog("getUserNameagainstFarmerID " + query);
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            rcons_user = cursor.getString(cursor.getColumnIndex("rcons_user"));
            if (rcons_user == null)
                return rcons_user = "";
        } else {

        }

        if (cursor != null)
            cursor.close();

        return rcons_user;
    }


    public void DeleteData() {
        try {
            String deleteQuery = "Delete from " + SectionFTable2;
            db = database.getWritableDatabase();
            db.execSQL(deleteQuery);
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    public void DeleteSection_F2Data(String farmer_id, String f1_crop) {
        try {
            String deleteQuery = "Delete from " + SectionFTable2 + " where  farmer_id= " + farmer_id + " AND f1_crop= " + f1_crop + "";
            db = database.getWritableDatabase();
            db.execSQL(deleteQuery);
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    public void DeleteSection_F5Data(String farmer_id, String f12_crop) {
        try {
            String deleteQuery = "Delete from " + SectionFTable5 + " where  farmer_id= " + farmer_id + " AND f12_crop= " + f12_crop + "";
            db = database.getWritableDatabase();
            db.execSQL(deleteQuery);
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }

    }

    public Cursor getSection_H_Crops(String region, String crop_id, String question_type) {
        String query = "SELECT * from " + KnowledgeTest + " where  region= '" + region + "' AND crop_id= '" + crop_id + "'AND question_type= '" + question_type + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public int GetSyncCompleteQuestionnaireCounter() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + SectionATable, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;

        }

    }


    public synchronized JSONArray getSingleRow(String query, Cursor cursor) {
        long rowId = 1;

        int dataCount = 0;
        int rowswritteninCSV = 0;
        ArrayList<String[]> data = new ArrayList<String[]>();
        JSONArray dataArray = new JSONArray();
        try {
            Cursor curCSV = null;
            if (query.equalsIgnoreCase("")) {
                curCSV = cursor;
            } else {
                curCSV = db.rawQuery(query, null);
            }

            dataCount = curCSV.getCount();
            if (dataCount != 0) {

                MubLog.cpnsoleLog("[StartUpMainActivity] inside getSingleRow() id  " + curCSV.getColumnNames()[0].toString());

                String deviceID = RConsUtils.getDeviceID(context);


                String uploaded_time = MubDateAndTime.INSTANCE.getcurrentTime(0) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(1) + "-" + MubDateAndTime.INSTANCE.getcurrentTime(2) + " " + MubDateAndTime.INSTANCE.getcurrentTime(3) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(4) + ":" + MubDateAndTime.INSTANCE.getcurrentTime(5);
                String build_no = BuildConfig.VERSION_NAME;

                MubLog.cpnsoleLog("device id " + deviceID);

                String columnNames[] = curCSV.getColumnNames();
                MubLog.cpnsoleLog("query " + query + "columnNames length " + columnNames.length);
                int count = curCSV.getColumnCount();
                String arrStr[] = new String[count];
                //curCSV.moveToFirst();
                data.add(columnNames);
                while (curCSV.moveToNext()) {
                    //Which column you want to exprort
                    int index = 0;
                    JSONObject object = new JSONObject();
                    while (index < count) {


                        arrStr[index] = curCSV.getString(index);
                        MubLog.cpnsoleLog("[MyTaskUpload] inside getSingleRow() " + arrStr[index]);
                        if (arrStr[index] == null)
                            arrStr[index] = "";

                        object.put(columnNames[index], arrStr[index]);


                        index++;
                    }
                    //  arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};


                    //object.put("isSynced", "2");
                    object.put("uploaded_time", uploaded_time);
                    //  object.put("build_no", build_no);
                    dataArray.put(rowswritteninCSV, object);
                    rowswritteninCSV++;
                    Thread.sleep(100);
                    MubLog.cpnsoleLog("[StartUpMainActivity] inside getSingleRow() rows written in csv " + rowswritteninCSV);

                    if (columnNames.length == arrStr.length) {
                        MubLog.cpnsoleLog("[MyTaskUpload] inside getSingleRow() row size matched ");

                        data.add(arrStr);

                    }
                }


//					if(rowswritteninCSV==dataCount){
//						DebugLog.console("[StartUpMainActivity] inside getSingleRow() write code to delete table");
//						curCSV = db.rawQuery("Delete FROM "+table_name_to_export+"",null);
//						curCSV.moveToFirst();
//
//					}else{
//						DebugLog.console("[StartUpMainActivity] inside getSingleRow() count not matched ");
//					}

                if (curCSV != null)
                    curCSV.close();

                MubLog.cpnsoleLog("Record  fetched successfully" + dataArray.toString());


            } else {
                //	txt.setText("No data available");
                MubLog.cpnsoleLog("[MyTaskUpload] inside getSingleRow() No data available");

            }

            return dataArray;
        } catch (Exception e) {
            MubLog.cpnsoleLog(e.toString() + "[DatabaseProcessor]: exception inside storeInBlockedList");

            return dataArray;
        }
    }


    public JSONArray readSectionAForDataSync(String whereClause) {
        MubLog.cpnsoleLog("inside readSectionAForDataSync");

        try {


            Cursor cursor2 = db.rawQuery("Select * from Section_A " + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionAForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionAForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionAForDataSync\" " + e.toString());
            return new JSONArray();
        }
    }


    public JSONArray readSectionBForDataSync(String whereClause) {
        MubLog.cpnsoleLog("inside readSectionBForDataSync");

        try {


            Cursor cursor2 = db.rawQuery("Select * from Section_B " + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionBForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionBForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionBForDataSync\" " + e.toString());
            return new JSONArray();
        }
    }

    public JSONArray readSectionCForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_C" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionCForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionCForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionCForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionDForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_D" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionDForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionDForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionDForDataSync\" " + e.toString());
            return new JSONArray();
        }
    }

    public JSONArray readSectionEForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_E" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionEForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionEForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionEForDataSync\" " + e.toString());
            return new JSONArray();
        }
    }

    public JSONArray readSectionFTable1ForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_F_table1" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionFTable1ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionFTable1ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionFTable1ForDataSync\" " + e.toString());
            return new JSONArray();
        }
    }

    public JSONArray readSectionFTable2ForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_F_table2" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionFTable2ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionFTable2ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionFTable2ForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionFTable3ForDataSync(String whereClause) {
        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_F_table3" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionFTable3ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionFTable3ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionFTable3ForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionFTable4ForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_F_table4" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionFTable4ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionFTable4ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionFTable4ForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionFTable5ForDataSync(String whereClause) {
        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_F_table5" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionFTable5ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionFTable5ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionFTable5ForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionFTable6ForDataSync(String whereClause) {
        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_F_table6" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionFTable6ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionFTable6ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionFTable6ForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionGForDataSync(String whereClause) {
        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_G" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionGForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionGForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionGForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }


    public JSONArray readSectionGBForDataSync(String whereClause) {
        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_G_b" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionGBForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionGBForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionGBForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }


    public JSONArray readSectionHForDataSync(String whereClause) {
        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_H" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionHForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionHForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionHForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }

    public JSONArray readSectionOneForDataSync(String whereClause) {

        try {


            Cursor cursor2 = this.db.rawQuery("Select * from Section_1" + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSectionOneForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSectionOneForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSectionOneForDataSync\" " + e.toString());
            return new JSONArray();
        }

    }


    public void deleteAllSectionData(String username, String where) {

        try {

            MubLog.cpnsoleLog(" inside deleteAllSectionData ");

            String tableName = "";
            String deleteQuery = "";


            tableName = "Section_A";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);


            tableName = "Section_B";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);


            tableName = "Section_C";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_D";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_E";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_F_table1";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_F_table2";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_F_table3";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_F_table4";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_F_table5";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_F_table6";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_G";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_G_b";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);


            tableName = "Section_H";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);

            tableName = "Section_1";
            MubLog.cpnsoleLog("inside deleteAllData " + tableName);
            deleteQuery = "Delete from " + tableName;
            executeQuerry(tableName, username, where);


            MubLog.cpnsoleLog("inside deleteAllData done");
        } catch (Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside deleteAllData() Exception is :"+e.toString());
        }
    }


    public void executeQuerry(String tableName, String userName, String where) {

        try {

            String querry = "Delete from " + tableName + where;
            MubLog.cpnsoleLog("inside executeQuerry" + querry);
            Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
            MubLog.cpnsoleLog("querryexecuted no issue ");
            MubLog.cpnsoleLog("querryexecuted no issue " + cursor2.getCount());
            // db = database.getWritableDatabase();
            //db.execSQL(querry);
        } catch (Exception e) {
//            EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside executeQuerry() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside executeQuerry" + e.toString());
        }


    }

    public int getFarmerAllCallCount_summary() {
        //removed 8 when 4447 issue
        Cursor cursor2 = this.db.rawQuery("SELECT * \nFROM   farmercallbacktable \nWHERE  ( (sync != '2' ) \nAND ( survey_status IN ('' ,2, 6 , 4 ,7 ,8, 9)) )\nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  survey_status IN ( 1 ) ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  WHERE  sc1_alt IN ( 2,  6 , 4, 7 ,8, 9 ) ) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 )  AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp1 IN (2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 ,2)))  AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();
    }

    //when change in this also make change in delete section data querry and getFarmerPendingCalls
    public int getPendingCall_summary() {


        //when change in this also make change in delete section data querry and getFarmerPendingCalls
        Cursor cursor2 = this.db.rawQuery("SELECT * \nFROM   farmercallbacktable \nWHERE  rcons_user LIKE '%" + "%' \nAND sync != '2' \nAND survey_status NOT IN ( '', 1, 6, 3,  5, 7 ) \nAND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable  \n\t\t\t\t\t\t\t\t\tWHERE  Ifnull(Length(sc1), 0) != 0 \n                                    AND Ifnull(Length(sc2), 0) != 0 \n                                    AND Ifnull(Length(sc3), 0) != 0 \n                                    AND Ifnull(Length(sc1_alt), 0) != 0 \n                                    AND Ifnull(Length(sc2_alt), 0) != 0 \n                                    AND Ifnull(Length(sc3_alt), 0) != 0) \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc1_alt), 0) = 0 AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc2_alt), 0) = 0  AND temp1 IN ('',1)))\nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE Ifnull(Length(sc3), 0) != 0 AND Ifnull(Length(sc3_alt), 0) = 0 AND temp1 IN ('',1)))\nAND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (2)))\nAND ( farmer_id  IN (SELECT farmer_id FROM   farmercallbacktable WHERE survey_status IN (2, 6 , 4, 7 ,8, 9,10 )))\nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )  AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND farmer_cellphone=alt_farmer_cellphone) \nAND farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable  WHERE  survey_status IN ( 2, 6 , 4, 7 ,8, 9 ,10 )   AND Ifnull(Length(sc1), 0) != 0 AND Ifnull(Length(sc2), 0) != 0 AND Ifnull(Length(sc3), 0) != 0 AND (alt_farmer_cellphone='' OR alt_farmer_cellphone = NULL OR alt_farmer_cellphone = 'null' OR alt_farmer_cellphone = 'NULL'))  \nAND ( farmer_id NOT IN (SELECT farmer_id FROM   farmercallbacktable WHERE temp1 IN (1 )))AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE temp2 IN (1 , 2))) AND (emp_id  NOT IN ( SELECT emp_id FROM farmercallbacktable WHERE survey_status IN (1)))", new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }
        return cursor2.getCount();
    }


    public int getCompletedCallCounter_summary() {


        //original Cursor cursor = db.rawQuery("SELECT rcons_user from FarmerCallBackTable WHERE rcons_user LIKE '%" + userName + "%' AND sync!='2' AND ((survey_status IN (1,3,5) OR temp1 = 1) OR ((ifnull(length(sc1), 0) != 0 AND ifnull(length(sc2), 0) != 0 AND ifnull(length(sc3), 0) != 0) OR (ifnull(length(sc1_alt), 0) != 0 AND ifnull(length(sc2_alt), 0) != 0 AND ifnull(length(sc3_alt), 0) != 0) ))", new String[0]);
        Cursor cursor = db.rawQuery("SELECT rcons_user from FarmerCallBackTable WHERE rcons_user LIKE '%" + "%' AND sync!='2' AND ((survey_status IN (1,3,5,6,7) OR temp1 = 1)) OR (((ifnull(length(sc1), 0) != 0 AND ifnull(length(sc2), 0) != 0 AND ifnull(length(sc3), 0) != 0) AND (ifnull(length(sc1_alt), 0) != 0 AND ifnull(length(sc2_alt), 0) != 0 AND ifnull(length(sc3_alt), 0) != 0) ))", new String[0]);
        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;

        }

    }

    public int getSuccessFullCallCounter_summary() {

        Cursor cursor = db.rawQuery("SELECT * from " + FarmerCallBackTable + " WHERE rcons_user LIKE '%" + "%' AND sync!='2' AND survey_status IN (1)", new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor.getCount();
        } else {
            return 0;

        }

    }


    public Cursor getUsers() {
        SQLiteDatabase db = database.getReadableDatabase();
        return db.rawQuery("select * from " + FarmerCallBackTable, null);
    }


    public String getFarmerId_sixTries_Done(String userName) {

        String subQuery = "(0)";
        ArrayList<String> farmerIDs = new ArrayList<>();
        try {
            String querry = "SELECT * FROM   farmercallbacktable WHERE  rcons_user LIKE '%" + userName + "%' AND sync != '2'  AND `sc1` != '' AND `sc2` != '' AND `sc3` !='' AND `sc1_alt` != '' AND `sc2_alt` != '' AND `sc3_alt` != ''; ";

            Cursor farmersCoursor = this.db.rawQuery(querry, new String[0]);

            MubLog.cpnsoleLog("inside getFarmerId_sixTries_Done" + "\r\n" + querry);


            if (farmersCoursor == null || farmersCoursor.getCount() == 0) {

                return subQuery;

            } else {

                MubLog.cpnsoleLog("inside getFarmerId_sixTries_Done count  " + farmersCoursor.getCount());
                if (farmersCoursor.moveToFirst()) {
                    do {
                        String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
                        if (!StringUtils.isEmpty(formerID)) {
                            farmerIDs.add(formerID);
                        }

                    } while (farmersCoursor.moveToNext());

                }
            }

            subQuery = "";
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

            MubLog.cpnsoleLog("inside getFarmerId_sixTries_Done " + subQuery);
            return subQuery;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside getFarmerId_sixTries_not_Done() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside getFarmerId_sixTries_Done " + e.toString());
            return subQuery;
        }

    }


    public String getFarmerId_sc1_alt_Done(String userName) {

        String subQuery = "(0)";
        ArrayList<String> farmerIDs = new ArrayList<>();
        try {
            String querry = "SELECT * FROM   farmercallbacktable WHERE  rcons_user LIKE '%" + userName + "%' AND sync != '2' AND `sc1_alt` != '' ; ";

            Cursor farmersCoursor = this.db.rawQuery(querry, new String[0]);

            MubLog.cpnsoleLog("inside getFarmerId_sc1_alt_Done" + "\r\n" + querry);


            if (farmersCoursor == null || farmersCoursor.getCount() == 0) {

                return subQuery;

            } else {

                MubLog.cpnsoleLog("inside getFarmerId_sc1_alt_Done count  " + farmersCoursor.getCount());
                if (farmersCoursor.moveToFirst()) {
                    do {
                        String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
                        if (!StringUtils.isEmpty(formerID)) {
                            farmerIDs.add(formerID);
                        }

                    } while (farmersCoursor.moveToNext());

                }
            }

            subQuery = "";
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

            MubLog.cpnsoleLog("inside getFarmerId_sc1_alt_Done " + subQuery);
            return subQuery;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside getFarmerId_sixTries_not_Done() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside getFarmerId_sc1_alt_Done " + e.toString());
            return subQuery;
        }

    }


    public String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(String userName) {

        String subQuery = "(0)";
        ArrayList<String> farmerIDs = new ArrayList<>();
        try {
            // String querry = "SELECT farmer_id FROM   farmercallbacktable WHERE  rcons_user LIKE '%" + userName + "%' AND sync != '2' AND farmer_id IN ( SELECT farmer_id FROM   farmercallbacktable WHERE  rcons_user LIKE '%" + userName + "%' AND sync != '2'  )`temp1` = '2' ; ";


            String querry = "SELECT `farmer_id` FROM  farmercallbacktable WHERE  rcons_user LIKE '%" + userName + "%' AND sync != '2'"

                    + "\n"

                    + "AND emp_id IN (SELECT emp_id FROM   farmercallbacktable WHERE  rcons_user LIKE '%" + userName + "%' AND sync != '2' AND `temp1` = '2')\n"

                    + "\n"

                    + "AND temp1 != '2'\n"

                    + "\n"

                    + "ORDER BY emp_id,order_id";


            Cursor farmersCoursor = this.db.rawQuery(querry, new String[0]);
            MubLog.cpnsoleLog("inside getFarmerId_having_temp_2" + "\r\n" + querry);

            if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
                return subQuery;
            } else {
                MubLog.cpnsoleLog("inside getFarmerId_having_temp_2 count  " + farmersCoursor.getCount());
                if (farmersCoursor.moveToFirst()) {
                    do {
                        String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("farmer_id"));
                        if (!StringUtils.isEmpty(formerID)) {
                            farmerIDs.add(formerID);
                        }

                    } while (farmersCoursor.moveToNext());
                }
            }

            subQuery = "";
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
            MubLog.cpnsoleLog("inside getFarmerId_having_temp_2 " + subQuery);
            return subQuery;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside getFarmerId_sixTries_not_Done() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside getFarmerId_having_temp_2 " + e.toString());
            return subQuery;
        }

    }


    public void CheckMissingColumnsList() {

        try {


            String insert_or_updated_in_phone_at = "ALTER TABLE `FarmerCallBackTable` ADD `insert_or_updated_in_phone_at` TEXT DEFAULT '' ";
            createMissingColumn(insert_or_updated_in_phone_at);

            String call_duration_reason = "ALTER TABLE `FarmerCallBackTable` ADD `call_duration_reason` TEXT DEFAULT '' ";
            createMissingColumn(call_duration_reason);

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception CheckMissingColumnsList database" + e.toString());
        }

    }

    public void createMissingColumn(String query) {
        try {


            MubLog.cpnsoleLog("inside createMissingColumn query" + query);
            db = database.getWritableDatabase();
            db.execSQL(query);
            MubLog.cpnsoleLog("inside createMissingColumn querry done");
        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception createMissingColumn database" + e.toString());

        } finally {

        }
    }


    public void execute_patch_query(String query) {
        try {


            MubLog.cpnsoleLog("inside execute_patch_query query" + query);
            db = database.getWritableDatabase();
            db.execSQL(query);
            MubLog.cpnsoleLog("inside execute_patch_query querry done");
        } catch (Exception e) {
            MubLog.cpnsoleLog("inside exception execute_patch_query database" + e.toString());

        } finally {

        }
    }

    public interface DurationPopup {

        boolean showDurationPopUp();
    }


    public Cursor savestartTime_of_survey(String enum_name, String enum_code, String emp_id, String order_id, String farmer_id, String c3_day, String c3_month, String c3_year, String c4_hh, String c4_mm) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionCTable + " where  farmer_id =" + farmer_id;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);


                contentValues.put("c3_day", c3_day);
                contentValues.put("c3_month", c3_month);
                contentValues.put("c3_year", c3_year);
                contentValues.put("c4_hh", c4_hh);
                contentValues.put("c4_mm", c4_mm);

                MubLog.cpnsoleLog("start_date_and_time insertOrThrow contentValues " + contentValues.size());


                db.insertOrThrow(SectionCTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);

                contentValues.put("c3_day", c3_day);
                contentValues.put("c3_month", c3_month);
                contentValues.put("c3_year", c3_year);
                contentValues.put("c4_hh", c4_hh);
                contentValues.put("c4_mm", c4_mm);

                MubLog.cpnsoleLog("start_date_and_time insertOrThrow update " + contentValues.size());
                db.update(SectionCTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor update_Section_b_14_number(String emp_id, String order_id, String farmer_id, String enum_code, String enum_name, String b14
    ) {


        MubLog.cpnsoleLog("Inside saveSection_B_Data update_Section_b_14_number");
        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + SectionBTable + " where  farmer_id =" + farmer_id;
        MubLog.cpnsoleLog("Inside saveSection_B_Data update_Section_b_14_number" + str);
        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);

                contentValues.put("b14", b14);

                MubLog.cpnsoleLog("inside saveSection_B_Data update_Section_b_14_number insertOrThrow");
                db.insertOrThrow(SectionBTable, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);

                contentValues.put("farmer_id", farmer_id);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("b14", b14);
                MubLog.cpnsoleLog("inside saveSection_B_Data update_Section_b_14_number update");
                db.update(SectionBTable, contentValues, "farmer_id=" + farmer_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public void update_Question_table_q_no_37() {

        try {


            MubLog.cpnsoleLog("Inside update_Question_table_q_no_37 ");
            db = database.getReadableDatabase();
            String new_question = "\"ڈسٹرکٹ راولپنڈی کے پہاڑی علاقوں میں \n" +
                    "early-2002, Sahiwal-2002, Pearl, MMRI-Yellow کے بیج کی ورائٹی کی کاشت کا بہترین وقت کونسا ہے؟\"\n";

            MubLog.cpnsoleLog("Inside update_Question_table_q_no_37 new_question" + new_question);


            db = database.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("question_name", new_question);

            MubLog.cpnsoleLog("inside saveSection_B_Data update_Section_b_14_number update");
            db.update(KnowledgeTest, contentValues, "question_no_new=" + 37, null);

        } catch (Exception e) {
            //EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside update_Question_table_q_no_37() Exception is :"+e.toString());
        }


    }


    public Cursor savetrt_Section_0_Data(String emp_id, String order_id,
                                         String farmer_id, String sr_no, String rcons_user,
                                         String enum_code, String enum_name, String isComplete,
                                         String isSynced, String insert_or_updated_in_phone_at, String deviceid,
                                         String build_no, String phone_number, String city_name, String status_code, String q0) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + trt_section_0_table + " where  phone_number =" + phone_number;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("city_name", city_name);
                contentValues.put("status_code", status_code);
                contentValues.put("q0", q0);


                db.insertOrThrow(trt_section_0_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("city_name", city_name);
                contentValues.put("status_code", status_code);
                contentValues.put("q0", q0);

                db.update(trt_section_0_table, contentValues, "phone_number=" + phone_number, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savetrt_Section_1_Data(String emp_id, String order_id,
                                         String farmer_id, String sr_no, String rcons_user,
                                         String enum_code, String enum_name, String isComplete,
                                         String isSynced, String insert_or_updated_in_phone_at,
                                         String deviceid, String build_no, String phone_number,
                                         String q1_a, String q1_b, String q1_c,
                                         String q2, String q3, String q4) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + trt_section_1_table + " where  phone_number =" + phone_number;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q1_a", q1_a);
                contentValues.put("q1_b", q1_b);
                contentValues.put("q1_c", q1_c);
                contentValues.put("q2", q2);
                contentValues.put("q3", q3);
                contentValues.put("q4", q4);


                db.insertOrThrow(trt_section_1_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q1_a", q1_a);
                contentValues.put("q1_b", q1_b);
                contentValues.put("q1_c", q1_c);
                contentValues.put("q2", q2);
                contentValues.put("q3", q3);
                contentValues.put("q4", q4);


                db.update(trt_section_1_table, contentValues, "phone_number=" + phone_number, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savetrt_Section_2_Data(String emp_id, String order_id, String farmer_id, String sr_no,
                                         String rcons_user, String enum_code, String enum_name,
                                         String isComplete, String isSynced, String insert_or_updated_in_phone_at,
                                         String deviceid, String build_no, String phone_number,
                                         String q5, String q5_other, String q6_1, String q6_2, String q6_3,
                                         String q6_4, String q6_5, String q6_6, String q6_7, String q6_other,
                                         String q7_1, String q7_2, String q7_3, String q7_4, String q7_5,
                                         String q7_6, String q7_7, String q7_other, String q8, String q9,
                                         String q10_1, String q10_2, String q10_3, String q10_4, String q10_5,
                                         String q10_6, String q10_7, String q10_8, String q10_other,
                                         String q11, String q11_other, String q12, String q13_1,
                                         String q13_2, String q13_3, String q13_4, String q13_5,
                                         String q13_6, String q13_7, String q13_other) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + trt_section_2_table + " where  phone_number =" + phone_number;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q5", q5);
                contentValues.put("q5_other", q5_other);
                contentValues.put("q6_1", q6_1);
                contentValues.put("q6_2", q6_2);
                contentValues.put("q6_3", q6_3);
                contentValues.put("q6_4", q6_4);
                contentValues.put("q6_5", q6_5);
                contentValues.put("q6_6", q6_6);
                contentValues.put("q6_7", q6_7);
                contentValues.put("q6_other", q6_other);
                contentValues.put("q7_1", q7_1);
                contentValues.put("q7_2", q7_2);
                contentValues.put("q7_3", q7_3);
                contentValues.put("q7_4", q7_4);
                contentValues.put("q7_5", q7_5);
                contentValues.put("q7_6", q7_6);
                contentValues.put("q7_7", q7_7);
                contentValues.put("q7_other", q7_other);
                contentValues.put("q8", q8);
                contentValues.put("q9", q9);
                contentValues.put("q10_1", q10_1);
                contentValues.put("q10_2", q10_2);
                contentValues.put("q10_3", q10_3);
                contentValues.put("q10_4", q10_4);
                contentValues.put("q10_5", q10_5);
                contentValues.put("q10_6", q10_6);
                contentValues.put("q10_7", q10_7);
                contentValues.put("q10_8", q10_8);
                contentValues.put("q10_other", q10_other);
                contentValues.put("q11", q11);
                contentValues.put("q11_other", q11_other);
                contentValues.put("q12", q12);
                contentValues.put("q13_1", q13_1);
                contentValues.put("q13_2", q13_2);
                contentValues.put("q13_3", q13_3);
                contentValues.put("q13_4", q13_4);
                contentValues.put("q13_5", q13_5);
                contentValues.put("q13_6", q13_6);
                contentValues.put("q13_7", q13_7);
                contentValues.put("q13_other", q13_other);


                db.insertOrThrow(trt_section_2_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q5", q5);
                contentValues.put("q5_other", q5_other);
                contentValues.put("q6_1", q6_1);
                contentValues.put("q6_2", q6_2);
                contentValues.put("q6_3", q6_3);
                contentValues.put("q6_4", q6_4);
                contentValues.put("q6_5", q6_5);
                contentValues.put("q6_6", q6_6);
                contentValues.put("q6_7", q6_7);
                contentValues.put("q6_other", q6_other);
                contentValues.put("q7_1", q7_1);
                contentValues.put("q7_2", q7_2);
                contentValues.put("q7_3", q7_3);
                contentValues.put("q7_4", q7_4);
                contentValues.put("q7_5", q7_5);
                contentValues.put("q7_6", q7_6);
                contentValues.put("q7_7", q7_7);
                contentValues.put("q7_other", q7_other);
                contentValues.put("q8", q8);
                contentValues.put("q9", q9);
                contentValues.put("q10_1", q10_1);
                contentValues.put("q10_2", q10_2);
                contentValues.put("q10_3", q10_3);
                contentValues.put("q10_4", q10_4);
                contentValues.put("q10_5", q10_5);
                contentValues.put("q10_6", q10_6);
                contentValues.put("q10_7", q10_7);
                contentValues.put("q10_8", q10_8);
                contentValues.put("q10_other", q10_other);
                contentValues.put("q11", q11);
                contentValues.put("q11_other", q11_other);
                contentValues.put("q12", q12);
                contentValues.put("q13_1", q13_1);
                contentValues.put("q13_2", q13_2);
                contentValues.put("q13_3", q13_3);
                contentValues.put("q13_4", q13_4);
                contentValues.put("q13_5", q13_5);
                contentValues.put("q13_6", q13_6);
                contentValues.put("q13_7", q13_7);
                contentValues.put("q13_other", q13_other);

                db.update(trt_section_2_table, contentValues, "phone_number=" + phone_number, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savetrt_Section_3_Data(String emp_id, String order_id, String farmer_id,
                                         String sr_no, String rcons_user,
                                         String enum_code, String enum_name,
                                         String isComplete, String isSynced,
                                         String insert_or_updated_in_phone_at, String deviceid,
                                         String build_no, String phone_number,
                                         String q14, String q15, String q16, String q17,
                                         String q18, String q19, String q20) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + trt_section_3_table + " where  phone_number =" + phone_number;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q14", q14);
                contentValues.put("q15", q15);
                contentValues.put("q16", q16);
                contentValues.put("q17", q17);
                contentValues.put("q18", q18);
                contentValues.put("q19", q19);
                contentValues.put("q20", q20);


                db.insertOrThrow(trt_section_3_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q14", q14);
                contentValues.put("q15", q15);
                contentValues.put("q16", q16);
                contentValues.put("q17", q17);
                contentValues.put("q18", q18);
                contentValues.put("q19", q19);
                contentValues.put("q20", q20);


                db.update(trt_section_3_table, contentValues, "phone_number=" + phone_number, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savetrt_Section_4_Data(String emp_id, String order_id,
                                         String farmer_id, String sr_no, String rcons_user,
                                         String enum_code, String enum_name, String isComplete, String isSynced,
                                         String insert_or_updated_in_phone_at, String deviceid, String build_no,
                                         String phone_number, String q21, String q22,
                                         String q23, String q24, String q25) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + trt_section_4_table + " where  phone_number =" + phone_number;

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q21", q21);
                contentValues.put("q22", q22);
                contentValues.put("q23", q23);
                contentValues.put("q24", q24);
                contentValues.put("q25", q25);


                db.insertOrThrow(trt_section_4_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("sr_no", sr_no);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("phone_number", phone_number);
                contentValues.put("q21", q21);
                contentValues.put("q22", q22);
                contentValues.put("q23", q23);
                contentValues.put("q24", q24);
                contentValues.put("q25", q25);


                db.update(trt_section_4_table, contentValues, "phone_number=" + phone_number, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor gettrt_section_0_Data(String phone_number) {
        String query = "SELECT * from " + trt_section_0_table + " where  phone_number= " + phone_number;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor gettrt_section_1_Data(String phone_number) {
        String query = "SELECT * from " + trt_section_1_table + " where  phone_number= " + phone_number;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }


    public Cursor gettrt_section_2_Data(String phone_number) {
        String query = "SELECT * from " + trt_section_2_table + " where  phone_number= " + phone_number;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor gettrt_section_3_Data(String phone_number) {
        String query = "SELECT * from " + trt_section_3_table + " where  phone_number= " + phone_number;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor gettrt_section_4_Data(String phone_number) {
        String query = "SELECT * from " + trt_section_4_table + " where  phone_number= " + phone_number;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor gettrt_NewCalls() {
        String querry = "SELECT * FROM trt_section_0 WHERE status_code IS NOT NULL AND status_code < 1 ";
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        MubLog.cpnsoleLog("inside getFarmerDistinctCallRecord " + querry);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }


    public Cursor selectCompletedCalls() {
        String query = "select * from " + trt_section_0_table + " WHERE (status_code is NOT NULL or status_code != '' ) AND  isSynced != 'yes'";
        DebugLog.console("[DatabaseAdapter] inside selectCompletedCalls() " + query);
        Cursor cursor = db.rawQuery(query, new String[]{});
        return cursor;

    }


    public JSONArray readSection_TRT_ForDataSync(String tableName, String whereClause) {
        MubLog.cpnsoleLog("inside readSection_TRT_ForDataSync");

        try {


            Cursor cursor2 = db.rawQuery("Select * from " + tableName + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data readSection_TRT_ForDataSync");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("readSection_TRT_ForDataSync data found ");
                return getSingleRow("", cursor2);
            }

        } catch (Exception e) {
            MubLog.cpnsoleLog("inside readSection_TRT_ForDataSync\" " + e.toString());
            return new JSONArray();
        }
    }


    public void UpdateSyncedData(String tableName, String farmerID) {
        String updateQuery = "";

        updateQuery = "UPDATE " + tableName + " SET isSynced = '" + "yes" + "'  WHERE farmer_id IN " + farmerID + "";

        MubLog.cpnsoleLog(updateQuery);

        try {
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
        }

    }


    public Cursor savepq_Section_A_Data(String emp_id,
                                        String order_id,
                                        String farmer_id,
                                        String rcons_user,
                                        String enum_code,
                                        String enum_name,
                                        String isComplete,
                                        String isSynced,
                                        String insert_or_updated_in_phone_at,
                                        String deviceid,
                                        String build_no,
                                        String school_code,
                                        String student_id,
                                        String a1,
                                        String a2,
                                        String a3,
                                        String a4,
                                        String a4_a,
                                        String a4_b,
                                        String a4_c,
                                        String a4_c_other,
                                        String a4_d,
                                        String a4_d_other,
                                        String a4_day,
                                        String a4_month,
                                        String a4_year,
                                        String a4_hh,
                                        String a4_mm,
                                        String a4_number,
                                        String a5,
                                        String a5_a,
                                        String a6,
                                        String a6_other,
                                        String a6_day,
                                        String a6_month,
                                        String a6_year,
                                        String a6_hh,
                                        String a6_mm,
                                        String a7) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_a_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.insertOrThrow(pq_section_a_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.update(pq_section_a_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savepq_Section_A2_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String a1,
                                         String a2,
                                         String a3,
                                         String a4,
                                         String a4_a,
                                         String a4_b,
                                         String a4_c,
                                         String a4_c_other,
                                         String a4_d,
                                         String a4_d_other,
                                         String a4_day,
                                         String a4_month,
                                         String a4_year,
                                         String a4_hh,
                                         String a4_mm,
                                         String a4_number,
                                         String a5,
                                         String a5_a,
                                         String a6,
                                         String a6_other,
                                         String a6_day,
                                         String a6_month,
                                         String a6_year,
                                         String a6_hh,
                                         String a6_mm,
                                         String a7) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_a2_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.insertOrThrow(pq_section_a2_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.update(pq_section_a2_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savepq_Section_A3_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String a1,
                                         String a2,
                                         String a3,
                                         String a4,
                                         String a4_a,
                                         String a4_b,
                                         String a4_c,
                                         String a4_c_other,
                                         String a4_d,
                                         String a4_d_other,
                                         String a4_day,
                                         String a4_month,
                                         String a4_year,
                                         String a4_hh,
                                         String a4_mm,
                                         String a4_number,
                                         String a5,
                                         String a5_a,
                                         String a6,
                                         String a6_other,
                                         String a6_day,
                                         String a6_month,
                                         String a6_year,
                                         String a6_hh,
                                         String a6_mm,
                                         String a7) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_a3_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.insertOrThrow(pq_section_a3_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.update(pq_section_a3_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_A4_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String a1,
                                         String a2,
                                         String a3,
                                         String a4,
                                         String a4_a,
                                         String a4_b,
                                         String a4_c,
                                         String a4_c_other,
                                         String a4_d,
                                         String a4_d_other,
                                         String a4_day,
                                         String a4_month,
                                         String a4_year,
                                         String a4_hh,
                                         String a4_mm,
                                         String a4_number,
                                         String a5,
                                         String a5_a,
                                         String a6,
                                         String a6_other,
                                         String a6_day,
                                         String a6_month,
                                         String a6_year,
                                         String a6_hh,
                                         String a6_mm,
                                         String a7) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_a4_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.insertOrThrow(pq_section_a4_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("a1", a1);
                contentValues.put("a2", a2);
                contentValues.put("a3", a3);
                contentValues.put("a4", a4);
                contentValues.put("a4_a", a4_a);
                contentValues.put("a4_b", a4_b);
                contentValues.put("a4_c", a4_c);
                contentValues.put("a4_c_other", a4_c_other);
                contentValues.put("a4_d", a4_d);
                contentValues.put("a4_d_other", a4_d_other);
                contentValues.put("a4_day", a4_day);
                contentValues.put("a4_month", a4_month);
                contentValues.put("a4_year", a4_year);
                contentValues.put("a4_hh", a4_hh);
                contentValues.put("a4_mm", a4_mm);
                contentValues.put("a4_number", a4_number);
                contentValues.put("a5", a5);
                contentValues.put("a5_a", a5_a);
                contentValues.put("a6", a6);
                contentValues.put("a6_other", a6_other);
                contentValues.put("a6_day", a6_day);
                contentValues.put("a6_month", a6_month);
                contentValues.put("a6_year", a6_year);
                contentValues.put("a6_hh", a6_hh);
                contentValues.put("a6_mm", a6_mm);
                contentValues.put("a7", a7);


                db.update(pq_section_a4_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_B_Data(String emp_id,
                                        String order_id,
                                        String farmer_id,
                                        String rcons_user,
                                        String enum_code,
                                        String enum_name,
                                        String isComplete,
                                        String isSynced,
                                        String insert_or_updated_in_phone_at,
                                        String deviceid,
                                        String build_no,
                                        String school_code,
                                        String student_id,
                                        String b0,
                                        String b1,
                                        String b1_other,
                                        String b2,
                                        String b3,
                                        String b4_a,
                                        String b4_b,
                                        String b5,
                                        String b6,
                                        String b7_0,
                                        String b7_1,
                                        String b7_2,
                                        String b7_3,
                                        String b7_4,
                                        String b7_5,
                                        String b7_6,
                                        String b7_7,
                                        String b8,
                                        String b9_1,
                                        String b9_2,
                                        String b9_3,
                                        String b9_4,
                                        String b9_5,
                                        String b10_0,
                                        String b10_1,
                                        String b10_2,
                                        String b10_3,
                                        String b10_4,
                                        String b10_5,
                                        String b10_99,
                                        String b10_other,
                                        String b11) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_b_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("b0", b0);
                contentValues.put("b1", b1);
                contentValues.put("b1_other", b1_other);
                contentValues.put("b2", b2);
                contentValues.put("b3", b3);
                contentValues.put("b4_a", b4_a);
                contentValues.put("b4_b", b4_b);
                contentValues.put("b5", b5);
                contentValues.put("b6", b6);
                contentValues.put("b7_0", b7_0);
                contentValues.put("b7_1", b7_1);
                contentValues.put("b7_2", b7_2);
                contentValues.put("b7_3", b7_3);
                contentValues.put("b7_4", b7_4);
                contentValues.put("b7_5", b7_5);
                contentValues.put("b7_6", b7_6);
                contentValues.put("b7_7", b7_7);
                contentValues.put("b8", b8);
                contentValues.put("b9_1", b9_1);
                contentValues.put("b9_2", b9_2);
                contentValues.put("b9_3", b9_3);
                contentValues.put("b9_4", b9_4);
                contentValues.put("b9_5", b9_5);
                contentValues.put("b10_0", b10_0);
                contentValues.put("b10_1", b10_1);
                contentValues.put("b10_2", b10_2);
                contentValues.put("b10_3", b10_3);
                contentValues.put("b10_4", b10_4);
                contentValues.put("b10_5", b10_5);
                contentValues.put("b10_99", b10_99);
                contentValues.put("b10_other", b10_other);
                contentValues.put("b11", b11);


                db.insertOrThrow(pq_section_b_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("b0", b0);
                contentValues.put("b1", b1);
                contentValues.put("b1_other", b1_other);
                contentValues.put("b2", b2);
                contentValues.put("b3", b3);
                contentValues.put("b4_a", b4_a);
                contentValues.put("b4_b", b4_b);
                contentValues.put("b5", b5);
                contentValues.put("b6", b6);
                contentValues.put("b7_0", b7_0);
                contentValues.put("b7_1", b7_1);
                contentValues.put("b7_2", b7_2);
                contentValues.put("b7_3", b7_3);
                contentValues.put("b7_4", b7_4);
                contentValues.put("b7_5", b7_5);
                contentValues.put("b7_6", b7_6);
                contentValues.put("b7_7", b7_7);
                contentValues.put("b8", b8);
                contentValues.put("b9_1", b9_1);
                contentValues.put("b9_2", b9_2);
                contentValues.put("b9_3", b9_3);
                contentValues.put("b9_4", b9_4);
                contentValues.put("b9_5", b9_5);
                contentValues.put("b10_0", b10_0);
                contentValues.put("b10_1", b10_1);
                contentValues.put("b10_2", b10_2);
                contentValues.put("b10_3", b10_3);
                contentValues.put("b10_4", b10_4);
                contentValues.put("b10_5", b10_5);
                contentValues.put("b10_99", b10_99);
                contentValues.put("b10_other", b10_other);
                contentValues.put("b11", b11);


                db.update(pq_section_b_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savehh_Section_G_Data(String isSynced,
                                        String isComplete,
                                        String rcons_user,
                                        String enum_code,
                                        String enum_name,
                                        String deviceid,
                                        String insert_or_updated_in_phone_at,
                                        String uploaded_time,
                                        String build_no,
                                        String village_id,
                                        String hhid,
                                        String g_1,
                                        String g_2,
                                        String g_3,
                                        String g_4,
                                        String g_5,
                                        String g_6,
                                        String g_7,
                                        String g_8,
                                        String g_urdu_1,
                                        String g_urdu_2_a,
                                        String g_urdu_2_b,
                                        String g_urdu_3,
                                        String g_urdu_4
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + aghhid_section_g_table + " where  village_id= " + village_id + " AND hhid= " + hhid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("isSynced", isSynced);
                contentValues.put("isComplete", isComplete);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("deviceid", deviceid);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("uploaded_time", uploaded_time);
                contentValues.put("build_no", build_no);
                contentValues.put("village_id", village_id);
                contentValues.put("hhid", hhid);
                contentValues.put("g_1", g_1);
                contentValues.put("g_2", g_2);
                contentValues.put("g_3", g_3);
                contentValues.put("g_4", g_4);
                contentValues.put("g_5", g_5);
                contentValues.put("g_6", g_6);
                contentValues.put("g_7", g_7);
                contentValues.put("g_8", g_8);
                contentValues.put("g_urdu_1", g_urdu_1);
                contentValues.put("g_urdu_2_a", g_urdu_2_a);
                contentValues.put("g_urdu_2_b", g_urdu_2_b);
                contentValues.put("g_urdu_3", g_urdu_3);
                contentValues.put("g_urdu_4", g_urdu_4);


                db.insertOrThrow(aghhid_section_g_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("isSynced", isSynced);
                contentValues.put("isComplete", isComplete);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("deviceid", deviceid);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("uploaded_time", uploaded_time);
                contentValues.put("build_no", build_no);
                contentValues.put("village_id", village_id);
                contentValues.put("hhid", hhid);
                contentValues.put("g_1", g_1);
                contentValues.put("g_2", g_2);
                contentValues.put("g_3", g_3);
                contentValues.put("g_4", g_4);
                contentValues.put("g_5", g_5);
                contentValues.put("g_6", g_6);
                contentValues.put("g_7", g_7);
                contentValues.put("g_8", g_8);
                contentValues.put("g_urdu_1", g_urdu_1);
                contentValues.put("g_urdu_2_a", g_urdu_2_a);
                contentValues.put("g_urdu_2_b", g_urdu_2_b);
                contentValues.put("g_urdu_3", g_urdu_3);
                contentValues.put("g_urdu_4", g_urdu_4);


                db.update(aghhid_section_g_table, contentValues, "village_id=" + village_id + " and hhid=" + hhid, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_C1_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String c1_a, String c1_b) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_c1_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("c1_a", c1_a);
                contentValues.put("c1_b", c1_b);

                db.insertOrThrow(pq_section_c1_table, null, contentValues);
            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("c1_a", c1_a);
                contentValues.put("c1_b", c1_b);

                db.update(pq_section_c1_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_C2_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String c2_id,
                                         String c2_a,
                                         String c2_b,
                                         String c2_c,
                                         String c2_d,
                                         String c2_e) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_c2_table + " where  school_code= " + school_code + " AND student_id= " + student_id + " AND c2_id= " + c2_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("c2_id", c2_id);
                contentValues.put("c2_a", c2_a);
                contentValues.put("c2_b", c2_b);
                contentValues.put("c2_c", c2_c);
                contentValues.put("c2_d", c2_d);
                contentValues.put("c2_e", c2_e);


                db.insertOrThrow(pq_section_c2_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("c2_id", c2_id);
                contentValues.put("c2_a", c2_a);
                contentValues.put("c2_b", c2_b);
                contentValues.put("c2_c", c2_c);
                contentValues.put("c2_d", c2_d);
                contentValues.put("c2_e", c2_e);

                db.update(pq_section_c2_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id + " and c2_id=" + c2_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_C3_v2_Data(String emp_id,
                                            String order_id,
                                            String farmer_id,
                                            String rcons_user,
                                            String enum_code,
                                            String enum_name,
                                            String isComplete,
                                            String isSynced,
                                            String insert_or_updated_in_phone_at,
                                            String deviceid,
                                            String build_no,
                                            String school_code,
                                            String student_id,
                                            String s1_st,
                                            String s1_et,
                                            String s1_activity,
                                            String s2_st,
                                            String s2_et,
                                            String s2_activity,
                                            String s3_st,
                                            String s3_et,
                                            String s3_activity,
                                            String s4_st,
                                            String s4_et,
                                            String s4_activity,
                                            String s5_st,
                                            String s5_et,
                                            String s5_activity,
                                            String s6_st,
                                            String s6_et,
                                            String s6_activity,
                                            String s7_st,
                                            String s7_et,
                                            String s7_activity,
                                            String s8_st,
                                            String s8_et,
                                            String s8_activity,
                                            String s9_st,
                                            String s9_et,
                                            String s9_activity,
                                            String s10_st,
                                            String s10_et,
                                            String s10_activity,
                                            String s11_st,
                                            String s11_et,
                                            String s11_activity,
                                            String s12_st,
                                            String s12_et,
                                            String s12_activity,
                                            String s13_st,
                                            String s13_et,
                                            String s13_activity,
                                            String s14_st,
                                            String s14_et,
                                            String s14_activity,
                                            String s15_st,
                                            String s15_et,
                                            String s15_activity,
                                            String s16_st,
                                            String s16_et,
                                            String s16_activity) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_c3_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("s1_st", s1_st);
                contentValues.put("s1_et", s1_et);
                contentValues.put("s1_activity", s1_activity);
                contentValues.put("s2_st", s2_st);
                contentValues.put("s2_et", s2_et);
                contentValues.put("s2_activity", s2_activity);
                contentValues.put("s3_st", s3_st);
                contentValues.put("s3_et", s3_et);
                contentValues.put("s3_activity", s3_activity);
                contentValues.put("s4_st", s4_st);
                contentValues.put("s4_et", s4_et);
                contentValues.put("s4_activity", s4_activity);
                contentValues.put("s5_st", s5_st);
                contentValues.put("s5_et", s5_et);
                contentValues.put("s5_activity", s5_activity);
                contentValues.put("s6_st", s6_st);
                contentValues.put("s6_et", s6_et);
                contentValues.put("s6_activity", s6_activity);
                contentValues.put("s7_st", s7_st);
                contentValues.put("s7_et", s7_et);
                contentValues.put("s7_activity", s7_activity);
                contentValues.put("s8_st", s8_st);
                contentValues.put("s8_et", s8_et);
                contentValues.put("s8_activity", s8_activity);
                contentValues.put("s9_st", s9_st);
                contentValues.put("s9_et", s9_et);
                contentValues.put("s9_activity", s9_activity);
                contentValues.put("s10_st", s10_st);
                contentValues.put("s10_et", s10_et);
                contentValues.put("s10_activity", s10_activity);
                contentValues.put("s11_st", s11_st);
                contentValues.put("s11_et", s11_et);
                contentValues.put("s11_activity", s11_activity);
                contentValues.put("s12_st", s12_st);
                contentValues.put("s12_et", s12_et);
                contentValues.put("s12_activity", s12_activity);
                contentValues.put("s13_st", s13_st);
                contentValues.put("s13_et", s13_et);
                contentValues.put("s13_activity", s13_activity);
                contentValues.put("s14_st", s14_st);
                contentValues.put("s14_et", s14_et);
                contentValues.put("s14_activity", s14_activity);
                contentValues.put("s15_st", s15_st);
                contentValues.put("s15_et", s15_et);
                contentValues.put("s15_activity", s15_activity);
                contentValues.put("s16_st", s16_st);
                contentValues.put("s16_et", s16_et);
                contentValues.put("s16_activity", s16_activity);


                db.insertOrThrow(pq_section_c3_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("s1_st", s1_st);
                contentValues.put("s1_et", s1_et);
                contentValues.put("s1_activity", s1_activity);
                contentValues.put("s2_st", s2_st);
                contentValues.put("s2_et", s2_et);
                contentValues.put("s2_activity", s2_activity);
                contentValues.put("s3_st", s3_st);
                contentValues.put("s3_et", s3_et);
                contentValues.put("s3_activity", s3_activity);
                contentValues.put("s4_st", s4_st);
                contentValues.put("s4_et", s4_et);
                contentValues.put("s4_activity", s4_activity);
                contentValues.put("s5_st", s5_st);
                contentValues.put("s5_et", s5_et);
                contentValues.put("s5_activity", s5_activity);
                contentValues.put("s6_st", s6_st);
                contentValues.put("s6_et", s6_et);
                contentValues.put("s6_activity", s6_activity);
                contentValues.put("s7_st", s7_st);
                contentValues.put("s7_et", s7_et);
                contentValues.put("s7_activity", s7_activity);
                contentValues.put("s8_st", s8_st);
                contentValues.put("s8_et", s8_et);
                contentValues.put("s8_activity", s8_activity);
                contentValues.put("s9_st", s9_st);
                contentValues.put("s9_et", s9_et);
                contentValues.put("s9_activity", s9_activity);
                contentValues.put("s10_st", s10_st);
                contentValues.put("s10_et", s10_et);
                contentValues.put("s10_activity", s10_activity);
                contentValues.put("s11_st", s11_st);
                contentValues.put("s11_et", s11_et);
                contentValues.put("s11_activity", s11_activity);
                contentValues.put("s12_st", s12_st);
                contentValues.put("s12_et", s12_et);
                contentValues.put("s12_activity", s12_activity);
                contentValues.put("s13_st", s13_st);
                contentValues.put("s13_et", s13_et);
                contentValues.put("s13_activity", s13_activity);
                contentValues.put("s14_st", s14_st);
                contentValues.put("s14_et", s14_et);
                contentValues.put("s14_activity", s14_activity);
                contentValues.put("s15_st", s15_st);
                contentValues.put("s15_et", s15_et);
                contentValues.put("s15_activity", s15_activity);
                contentValues.put("s16_st", s16_st);
                contentValues.put("s16_et", s16_et);
                contentValues.put("s16_activity", s16_activity);


                db.update(pq_section_c3_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_C3_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String refused,
                                         String c3a_hh,
                                         String c3a_mm,
                                         String c3b_hh,
                                         String c3b_mm,
                                         String c3c_hh,
                                         String c3c_mm,
                                         String c3d_hh,
                                         String c3d_mm,
                                         String c3e_hh,
                                         String c3e_mm,
                                         String c3f_hh,
                                         String c3f_mm,
                                         String c3g_hh,
                                         String c3g_mm,
                                         String c3h_hh,
                                         String c3h_mm,
                                         String c3i_hh,
                                         String c3i_mm,
                                         String c3j_hh,
                                         String c3j_mm,
                                         String c3k_hh,
                                         String c3k_mm,
                                         String c3l_hh,
                                         String c3l_mm,
                                         String c3m_hh,
                                         String c3m_mm,
                                         String c3n_hh,
                                         String c3n_mm,
                                         String c3o_hh,
                                         String c3o_mm,
                                         String c4,
                                         String c4_other

    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_c3_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("c3a_hh", c3a_hh);
                contentValues.put("c3a_mm", c3a_mm);
                contentValues.put("c3b_hh", c3b_hh);
                contentValues.put("c3b_mm", c3b_mm);
                contentValues.put("c3c_hh", c3c_hh);
                contentValues.put("c3c_mm", c3c_mm);
                contentValues.put("c3d_hh", c3d_hh);
                contentValues.put("c3d_mm", c3d_mm);
                contentValues.put("c3e_hh", c3e_hh);
                contentValues.put("c3e_mm", c3e_mm);
                contentValues.put("c3f_hh", c3f_hh);
                contentValues.put("c3f_mm", c3f_mm);
                contentValues.put("c3g_hh", c3g_hh);
                contentValues.put("c3g_mm", c3g_mm);
                contentValues.put("c3h_hh", c3h_hh);
                contentValues.put("c3h_mm", c3h_mm);
                contentValues.put("c3i_hh", c3i_hh);
                contentValues.put("c3i_mm", c3i_mm);
                contentValues.put("c3j_hh", c3j_hh);
                contentValues.put("c3j_mm", c3j_mm);
                contentValues.put("c3k_hh", c3k_hh);
                contentValues.put("c3k_mm", c3k_mm);
                contentValues.put("c3l_hh", c3l_hh);
                contentValues.put("c3l_mm", c3l_mm);
                contentValues.put("c3m_hh", c3m_hh);
                contentValues.put("c3m_mm", c3m_mm);
                contentValues.put("c3n_hh", c3n_hh);
                contentValues.put("c3n_mm", c3n_mm);
                contentValues.put("c3o_hh", c3o_hh);
                contentValues.put("c3o_mm", c3o_mm);
                contentValues.put("c4", c4);
                contentValues.put("c4_other", c4_other);
                contentValues.put("refused", refused);


                db.insertOrThrow(pq_section_c3_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("c3a_hh", c3a_hh);
                contentValues.put("c3a_mm", c3a_mm);
                contentValues.put("c3b_hh", c3b_hh);
                contentValues.put("c3b_mm", c3b_mm);
                contentValues.put("c3c_hh", c3c_hh);
                contentValues.put("c3c_mm", c3c_mm);
                contentValues.put("c3d_hh", c3d_hh);
                contentValues.put("c3d_mm", c3d_mm);
                contentValues.put("c3e_hh", c3e_hh);
                contentValues.put("c3e_mm", c3e_mm);
                contentValues.put("c3f_hh", c3f_hh);
                contentValues.put("c3f_mm", c3f_mm);
                contentValues.put("c3g_hh", c3g_hh);
                contentValues.put("c3g_mm", c3g_mm);
                contentValues.put("c3h_hh", c3h_hh);
                contentValues.put("c3h_mm", c3h_mm);
                contentValues.put("c3i_hh", c3i_hh);
                contentValues.put("c3i_mm", c3i_mm);
                contentValues.put("c3j_hh", c3j_hh);
                contentValues.put("c3j_mm", c3j_mm);
                contentValues.put("c3k_hh", c3k_hh);
                contentValues.put("c3k_mm", c3k_mm);
                contentValues.put("c3l_hh", c3l_hh);
                contentValues.put("c3l_mm", c3l_mm);
                contentValues.put("c3m_hh", c3m_hh);
                contentValues.put("c3m_mm", c3m_mm);
                contentValues.put("c3n_hh", c3n_hh);
                contentValues.put("c3n_mm", c3n_mm);
                contentValues.put("c3o_hh", c3o_hh);
                contentValues.put("c3o_mm", c3o_mm);
                contentValues.put("c4", c4);
                contentValues.put("c4_other", c4_other);
                contentValues.put("refused", refused);


                db.update(pq_section_c3_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_D_Data(String emp_id,
                                        String order_id,
                                        String farmer_id,
                                        String rcons_user,
                                        String enum_code,
                                        String enum_name,
                                        String isComplete,
                                        String isSynced,
                                        String insert_or_updated_in_phone_at,
                                        String deviceid,
                                        String build_no,
                                        String school_code,
                                        String student_id,
                                        String d1_0,
                                        String d1_1,
                                        String d1_2,
                                        String d1_3,
                                        String d2,
                                        String d3,
                                        String d4,
                                        String d5_0,
                                        String d5_1,
                                        String d5_2,
                                        String d5_3,
                                        String d5_4,
                                        String d5_99,
                                        String d5_other,
                                        String d6,
                                        String d7,
                                        String d8_1,
                                        String d8_2,
                                        String d8_3,
                                        String d8_4,
                                        String d8_5,
                                        String d8_99,
                                        String d8_other,
                                        String d9_0,
                                        String d9_1,
                                        String d9_2,
                                        String d9_3,
                                        String d9_98,
                                        String d9_97) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_d_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("d1_0", d1_0);
                contentValues.put("d1_1", d1_1);
                contentValues.put("d1_2", d1_2);
                contentValues.put("d1_3", d1_3);
                contentValues.put("d2", d2);
                contentValues.put("d3", d3);
                contentValues.put("d4", d4);
                contentValues.put("d5_0", d5_0);
                contentValues.put("d5_1", d5_1);
                contentValues.put("d5_2", d5_2);
                contentValues.put("d5_3", d5_3);
                contentValues.put("d5_4", d5_4);
                contentValues.put("d5_99", d5_99);
                contentValues.put("d5_other", d5_other);
                contentValues.put("d6", d6);
                contentValues.put("d7", d7);
                contentValues.put("d8_1", d8_1);
                contentValues.put("d8_2", d8_2);
                contentValues.put("d8_3", d8_3);
                contentValues.put("d8_4", d8_4);
                contentValues.put("d8_5", d8_5);
                contentValues.put("d8_99", d8_99);
                contentValues.put("d8_other", d8_other);
                contentValues.put("d9_0", d9_0);
                contentValues.put("d9_1", d9_1);
                contentValues.put("d9_2", d9_2);
                contentValues.put("d9_3", d9_3);
                contentValues.put("d9_98", d9_98);
                contentValues.put("d9_97", d9_97);


                db.insertOrThrow(pq_section_d_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("d1_0", d1_0);
                contentValues.put("d1_1", d1_1);
                contentValues.put("d1_2", d1_2);
                contentValues.put("d1_3", d1_3);
                contentValues.put("d2", d2);
                contentValues.put("d3", d3);
                contentValues.put("d4", d4);
                contentValues.put("d5_0", d5_0);
                contentValues.put("d5_1", d5_1);
                contentValues.put("d5_2", d5_2);
                contentValues.put("d5_3", d5_3);
                contentValues.put("d5_4", d5_4);
                contentValues.put("d5_99", d5_99);
                contentValues.put("d5_other", d5_other);
                contentValues.put("d6", d6);
                contentValues.put("d7", d7);
                contentValues.put("d8_1", d8_1);
                contentValues.put("d8_2", d8_2);
                contentValues.put("d8_3", d8_3);
                contentValues.put("d8_4", d8_4);
                contentValues.put("d8_5", d8_5);
                contentValues.put("d8_99", d8_99);
                contentValues.put("d8_other", d8_other);
                contentValues.put("d9_0", d9_0);
                contentValues.put("d9_1", d9_1);
                contentValues.put("d9_2", d9_2);
                contentValues.put("d9_3", d9_3);
                contentValues.put("d9_98", d9_98);
                contentValues.put("d9_97", d9_97);


                db.update(pq_section_d_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_Section_E1_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String refused,
                                         String e1_1,
                                         String e1_2,
                                         String e1_3,
                                         String e1_4,
                                         String e1_5,
                                         String e1_6,
                                         String e1_7,
                                         String e1_8,
                                         String e1_9,
                                         String e1_10,
                                         String e1_11,
                                         String e1_12,
                                         String e1_13,
                                         String e1_14,
                                         String e1_15,
                                         String e1_16) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_e_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("e1_1", e1_1);
                contentValues.put("e1_2", e1_2);
                contentValues.put("e1_3", e1_3);
                contentValues.put("e1_4", e1_4);
                contentValues.put("e1_5", e1_5);
                contentValues.put("e1_6", e1_6);
                contentValues.put("e1_7", e1_7);
                contentValues.put("e1_8", e1_8);
                contentValues.put("e1_9", e1_9);
                contentValues.put("e1_10", e1_10);
                contentValues.put("e1_11", e1_11);
                contentValues.put("e1_12", e1_12);
                contentValues.put("e1_13", e1_13);
                contentValues.put("e1_14", e1_14);
                contentValues.put("e1_15", e1_15);
                contentValues.put("e1_16", e1_16);
                contentValues.put("refused", refused);


                db.insertOrThrow(pq_section_e_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("e1_1", e1_1);
                contentValues.put("e1_2", e1_2);
                contentValues.put("e1_3", e1_3);
                contentValues.put("e1_4", e1_4);
                contentValues.put("e1_5", e1_5);
                contentValues.put("e1_6", e1_6);
                contentValues.put("e1_7", e1_7);
                contentValues.put("e1_8", e1_8);
                contentValues.put("e1_9", e1_9);
                contentValues.put("e1_10", e1_10);
                contentValues.put("e1_11", e1_11);
                contentValues.put("e1_12", e1_12);
                contentValues.put("e1_13", e1_13);
                contentValues.put("e1_14", e1_14);
                contentValues.put("e1_15", e1_15);
                contentValues.put("e1_16", e1_16);
                contentValues.put("refused", refused);

                db.update(pq_section_e_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savepq_Section_E2_Data(String emp_id,
                                         String order_id,
                                         String farmer_id,
                                         String rcons_user,
                                         String enum_code,
                                         String enum_name,
                                         String isComplete,
                                         String isSynced,
                                         String insert_or_updated_in_phone_at,
                                         String deviceid,
                                         String build_no,
                                         String school_code,
                                         String student_id,
                                         String e2,
                                         String e3,
                                         String e4,
                                         String e4_other,
                                         String e5,
                                         String e6,
                                         String e6_other,
                                         String e7,
                                         String e8,
                                         String e8_other,
                                         String e9,
                                         String e9_other,
                                         String e10,
                                         String end_day,
                                         String end_month,
                                         String end_year
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + pq_section_e_table + " where  school_code= " + school_code + " AND student_id= " + student_id + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("e2", e2);
                contentValues.put("e3", e3);
                contentValues.put("e4", e4);
                contentValues.put("e4_other", e4_other);
                contentValues.put("e5", e5);
                contentValues.put("e6", e6);
                contentValues.put("e6_other", e6_other);
                contentValues.put("e7", e7);
                contentValues.put("e8", e8);
                contentValues.put("e8_other", e8_other);
                contentValues.put("e9", e9);
                contentValues.put("e9_other", e9_other);
                contentValues.put("e10", e10);
                contentValues.put("end_day", end_day);
                contentValues.put("end_month", end_month);
                contentValues.put("end_year", end_year);


                db.insertOrThrow(pq_section_e_table, null, contentValues);

            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put("emp_id", emp_id);
                contentValues.put("order_id", order_id);
                contentValues.put("farmer_id", farmer_id);
                contentValues.put("rcons_user", rcons_user);
                contentValues.put("enum_code", enum_code);
                contentValues.put("enum_name", enum_name);
                contentValues.put("isComplete", isComplete);
                contentValues.put("isSynced", isSynced);
                contentValues.put("insert_or_updated_in_phone_at", insert_or_updated_in_phone_at);
                contentValues.put("deviceid", deviceid);
                contentValues.put("build_no", build_no);
                contentValues.put("school_code", school_code);
                contentValues.put("student_id", student_id);
                contentValues.put("e2", e2);
                contentValues.put("e3", e3);
                contentValues.put("e4", e4);
                contentValues.put("e4_other", e4_other);
                contentValues.put("e5", e5);
                contentValues.put("e6", e6);
                contentValues.put("e6_other", e6_other);
                contentValues.put("e7", e7);
                contentValues.put("e8", e8);
                contentValues.put("e8_other", e8_other);
                contentValues.put("e9", e9);
                contentValues.put("e9_other", e9_other);
                contentValues.put("e10", e10);
                contentValues.put("end_day", end_day);
                contentValues.put("end_month", end_month);
                contentValues.put("end_year", end_year);


                db.update(pq_section_e_table, contentValues, "school_code=" + school_code + " and student_id=" + student_id, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor getpq_basic_Data(String scode, String studentid) {
        String query = "SELECT * from " + BaseLineSampleTable + " where  scode= '" + scode + "' AND studentid= '" + studentid + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_a_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_a_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_a2_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_a2_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_a3_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_a3_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_a4_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_a4_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_b_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_b_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor gethh_section_g_Data(String village_id, String hhid) {
        String query = "SELECT * from " + aghhid_section_g_table + " where  village_id= '" + village_id + "' AND hhid= '" + hhid + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }


    public Cursor savepq_interview_start_sc1_(String scode,
                                              String studentid,
                                              String sc1_st_day,
                                              String sc1_st_month,
                                              String sc1_st_year,
                                              String sc1_st_hh,
                                              String sc1_st_mm) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + BaseLineSampleTable + " where  scode= " + scode + " AND studentid= " + studentid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc1_st_day", sc1_st_day);
                contentValues.put("sc1_st_month", sc1_st_month);
                contentValues.put("sc1_st_year", sc1_st_year);
                contentValues.put("sc1_st_hh", sc1_st_hh);
                contentValues.put("sc1_st_mm", sc1_st_mm);


                db.insertOrThrow(BaseLineSampleTable, null, contentValues);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_interview_start_sc1(
            String scode,
            String studentid,
            String sc1_start_year,
            String sc1_start_month,
            String sc1_start_day,
            String sc1_start_hh,
            String sc1_start_mm
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + BaseLineSampleTable + " where  scode= " + scode + " AND studentid= " + studentid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc1_start_year", sc1_start_year);
                contentValues.put("sc1_start_month", sc1_start_month);
                contentValues.put("sc1_start_day", sc1_start_day);
                contentValues.put("sc1_start_hh", sc1_start_hh);
                contentValues.put("sc1_start_mm", sc1_start_mm);

                db.insertOrThrow(BaseLineSampleTable, null, contentValues);
            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc1_start_year", sc1_start_year);
                contentValues.put("sc1_start_month", sc1_start_month);
                contentValues.put("sc1_start_day", sc1_start_day);
                contentValues.put("sc1_start_hh", sc1_start_hh);
                contentValues.put("sc1_start_mm", sc1_start_mm);

                db.update(BaseLineSampleTable, contentValues, "scode=" + scode + " and studentid=" + studentid, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savepq_interview_start_sc2(
            String scode,
            String studentid,
            String sc2_start_year,
            String sc2_start_month,
            String sc2_start_day,
            String sc2_start_hh,
            String sc2_start_mm
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + BaseLineSampleTable + " where  scode= " + scode + " AND studentid= " + studentid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc2_start_year", sc2_start_year);
                contentValues.put("sc2_start_month", sc2_start_month);
                contentValues.put("sc2_start_day", sc2_start_day);
                contentValues.put("sc2_start_hh", sc2_start_hh);
                contentValues.put("sc2_start_mm", sc2_start_mm);

                db.insertOrThrow(BaseLineSampleTable, null, contentValues);
            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc2_start_year", sc2_start_year);
                contentValues.put("sc2_start_month", sc2_start_month);
                contentValues.put("sc2_start_day", sc2_start_day);
                contentValues.put("sc2_start_hh", sc2_start_hh);
                contentValues.put("sc2_start_mm", sc2_start_mm);

                db.update(BaseLineSampleTable, contentValues, "scode=" + scode + " and studentid=" + studentid, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public Cursor savepq_interview_start_sc3(
            String scode,
            String studentid,
            String sc3_start_year,
            String sc3_start_month,
            String sc3_start_day,
            String sc3_start_hh,
            String sc3_start_mm
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + BaseLineSampleTable + " where  scode= " + scode + " AND studentid= " + studentid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc3_start_year", sc3_start_year);
                contentValues.put("sc3_start_month", sc3_start_month);
                contentValues.put("sc3_start_day", sc3_start_day);
                contentValues.put("sc3_start_hh", sc3_start_hh);
                contentValues.put("sc3_start_mm", sc3_start_mm);

                db.insertOrThrow(BaseLineSampleTable, null, contentValues);
            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc3_start_year", sc3_start_year);
                contentValues.put("sc3_start_month", sc3_start_month);
                contentValues.put("sc3_start_day", sc3_start_day);
                contentValues.put("sc3_start_hh", sc3_start_hh);
                contentValues.put("sc3_start_mm", sc3_start_mm);

                db.update(BaseLineSampleTable, contentValues, "scode=" + scode + " and studentid=" + studentid, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor savepq_interview_start_sc4(
            String scode,
            String studentid,
            String sc4_start_year,
            String sc4_start_month,
            String sc4_start_day,
            String sc4_start_hh,
            String sc4_start_mm
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + BaseLineSampleTable + " where  scode= " + scode + " AND studentid= " + studentid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc4_start_year", sc4_start_year);
                contentValues.put("sc4_start_month", sc4_start_month);
                contentValues.put("sc4_start_day", sc4_start_day);
                contentValues.put("sc4_start_hh", sc4_start_hh);
                contentValues.put("sc4_start_mm", sc4_start_mm);

                db.insertOrThrow(BaseLineSampleTable, null, contentValues);
            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("sc4_start_year", sc4_start_year);
                contentValues.put("sc4_start_month", sc4_start_month);
                contentValues.put("sc4_start_day", sc4_start_day);
                contentValues.put("sc4_start_hh", sc4_start_hh);
                contentValues.put("sc4_start_mm", sc4_start_mm);

                db.update(BaseLineSampleTable, contentValues, "scode=" + scode + " and studentid=" + studentid, null);
            }
        }
        cursor.close();
        return cursor;
    }

    public Cursor getpq_section_c1_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_c1_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_c2_Data(String school_code, String student_id, String c2_id) {
        String query = "SELECT * from " + pq_section_c2_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "' AND c2_id= '" + c2_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getpq_section_c3_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_c3_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getpq_section_c2_recyler(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_c2_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getpq_section_d_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_d_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public Cursor getpq_section_e_Data(String school_code, String student_id) {
        String query = "SELECT * from " + pq_section_e_table + " where  school_code= '" + school_code + "' AND student_id= '" + student_id + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }

    }

    public void UpdateGivenNumber_(String given_number, String scode, String studentid) {
        String updateQuery = "";
        updateQuery = "UPDATE " + BaseLineSampleTable + " SET given_number = '" + given_number + "'  WHERE scode = " + scode + "' AND studentid= '" + studentid + "'";

        MubLog.cpnsoleLog(updateQuery);

        try {
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);

        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
        }

    }

    public Cursor UpdateGivenNumber(
            String given_number,
            String scode,
            String studentid
    ) {

        db = database.getReadableDatabase();
        String str = "";
        str = "select Count(*) as count from " + BaseLineSampleTable + " where  scode= " + scode + " AND studentid= " + studentid + "";

        cursor = db.rawQuery(str, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int ifExits = cursor.getInt(cursor.getColumnIndex("count"));
            if (ifExits == 0) {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("given_number", given_number);

                db.insertOrThrow(BaseLineSampleTable, null, contentValues);
            } else {
                db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();


                contentValues.put("given_number", given_number);

                db.update(BaseLineSampleTable, contentValues, "scode=" + scode + " and studentid=" + studentid, null);
            }
        }
        cursor.close();
        return cursor;
    }


    public int baseline_getBaseLineDataCount() {
        int count = 0;
        String query = "select id from " + BaseLineSampleTable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            count = cursor.getCount();
        } else {
            count = 0;
        }
        return count;

    }


    public void baseline_deleteAllData() {
        MubLog.cpnsoleLog("inside delete All data");
        String deleteQuery = "Delete from " + BaseLineSampleTable;
        db = database.getWritableDatabase();
        db.execSQL(deleteQuery);
        MubLog.cpnsoleLog("inside baseline_deleteAllData  All data done");

    }

    public boolean baseline_insertImporteddata(Context appcontext, JSONArray data) {

        boolean error = false;
        try {

            DebugLog.console("[DatabaseAdapter] inside baseline_insertImporteddata() " + data.length());

            if (!isOpen())
                db = database.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            int k = 0;
            while (k < data.length()) {
                JSONObject json = data.getJSONObject(k);
                contentValues = new ContentValues();
                Iterator<String> iter = json.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    try {
                        Object value = json.get(key);

                        DebugLog.console("[DatabaseAdapter] inside baseline_insertImporteddata() key" + key);
                        DebugLog.console("[DatabaseAdapter] inside baseline_insertImporteddata() value" + value);

                        contentValues.put(key, value.toString());

                    } catch (JSONException e) {
                        // Something went wrong!
                        DebugLog.console("[DatabaseAdapter] inside baseline_insertImporteddata() " + e.toString());
                        error = true;
                    }
                }

                long isInserted = db.insert(DatabaseAdapter.BaseLineSampleTable, null, contentValues);
                DebugLog.console("[DatabaseAdapter] inside baseline_insertImporteddata() " + isInserted);
                if (isInserted == -1) {
                    error = true;
                }
                k++;

            }


            db.close();
            return error;

        } catch (Exception e) {
            EmailDebugLog.getInstance(appcontext).writeLog("[DatabaseAdapter] inside insertImporteddata() Exception is :" + e.toString());

            return error;
        }
    }

    public int baseline_getNewCallsCount(String userName) {
        //removed 8 when 4447 issue
        try {


            String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
            // String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
            // String querry = "SELECT * FROM   "+BaseLineSampleTable+" WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (3,5,10) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + " AND farmer_id NOT IN " + getFarmerId_sc1_alt_Done + "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";
            String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2'  AND survey_status = '' )   )";

            MubLog.cpnsoleLog("inside baseline_getNewCallsCount " + querry);
            Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                return 0;
            }
            int count = cursor2.getCount();
            if (cursor2 != null) {

                if (!cursor2.isClosed()) {
                    cursor2.close();
                }
            }
            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(context).writeLog("[DatabaseAdapter] inside baseline_getNewCallsCount() Exception is :" + e.toString());

            return 0;
        }


    }


    public Cursor baseline_getNewCallsCursor(String userName) {

        //removed 8 when 4447 issue
        String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
        //  String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        // String querry = "SELECT * FROM   "+BaseLineSampleTable+" WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (3,5,10) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + " AND farmer_id NOT IN " + getFarmerId_sc1_alt_Done + "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";
        String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2'  AND survey_status = '' )   ) ORDER BY  scode,order_to_contact ASC";

        MubLog.cpnsoleLog("inside baseline_getNewCallsCount " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        MubLog.cpnsoleLog("inside getFarmerDistinctCallRecord " + querry);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }


    public int baseline_getPendingCall(String userName) {
        //removed 8 when 4447 issue
        try {


            String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
            String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);
            // String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
            // String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);
            String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND  (survey_status  IN (2,4,8,9,10) AND (id  NOT IN " + baseline_ThreeTries_Done + " AND id NOT IN " + baseline_no_call_again_ids + "))   ";
            MubLog.cpnsoleLog("inside getPendingCall " + querry);
            Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                return 0;
            }

            int count = cursor2.getCount();
            if (cursor2 != null) {

                if (!cursor2.isClosed()) {
                    cursor2.close();
                }
            }
            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(context).writeLog("[DatabaseAdapter] inside baseline_getNewCallsCount() Exception is :" + e.toString());
            return 0;
        }

    }


    public Cursor baseline_getPendingCallCursor(String userName) {


        String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
        String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);

        //String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        //  String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);
        String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND  (survey_status  IN (2,4,8,9,10) AND (id  NOT IN " + baseline_ThreeTries_Done + " AND id NOT IN " + baseline_no_call_again_ids + ")) ORDER BY  scode,order_to_contact ASC  ";

        MubLog.cpnsoleLog("inside getPendingCall " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    public Cursor baseline_getPendingCallCursor_search(String userName, String scode) {


        String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
        String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);

        //String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        //  String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);
        String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE   ( rcons_user = '" + userName + "' AND scode = " + scode + " AND isSynced != '2' ) AND  (survey_status  IN (2,4,8,9,10) AND (id  NOT IN " + baseline_ThreeTries_Done + " AND id NOT IN " + baseline_no_call_again_ids + ")) ORDER BY  scode,order_to_contact ASC  ";

        MubLog.cpnsoleLog("inside getPendingCall " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    //when change in this also make change in delete section data querry  and getPendingCall

    public int baseline_getSuccessFullCallCounter(String userName) {


        String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND ( survey_status  IN (1) ) )";

        Cursor cursor = db.rawQuery(querry, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            int count = cursor.getCount();

            if (cursor != null) {

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            return count;

        } else {
            if (cursor != null) {

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            return 0;

        }

    }

    public int baseline_getCompletedCallCounter(String userName) {

        String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);
        String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
        String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND  (survey_status  IN (1,3,5,6,7) OR ( id  IN " + baseline_ThreeTries_Done + " OR id  IN " + baseline_no_call_again_ids + "))   ";


        MubLog.cpnsoleLog("inside baseline_getCompletedCallCounter " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }

        int count = cursor2.getCount();

        if (cursor2 != null) {

            if (!cursor2.isClosed()) {
                cursor2.close();
            }
        }
        return count;
    }

    public boolean baseline_updateCallStatus(Context context, String survey_status, String scode, String studentid, String id, String farmer_id, String phoneNumber, String reason, boolean isAlterNameFarmer, String callAgain, String empID, String calldurationReason, DurationPopup durationPopup) {


        boolean callagain_flag_manual_set = false;
        try {
            DebugLog.console("[DatabaseAdapter] inside baseline_updateCallStatus() ");

            this.durationPopup = durationPopup;
            //CheckMissingColumnsList();

            String checkedQuery = "Select sc1 as statusOne , sc2 as statusTwo, sc3 as statusThree, sc4 as statusFour , temp2 from " + BaseLineSampleTable + " WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";

            String deviceID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            String current_year = RConsUtils.getcurrentTime(0);
            String current_month = RConsUtils.getcurrentTime(1);
            String current_day = RConsUtils.getcurrentTime(2);
            String current_hh = RConsUtils.getcurrentTime(3);
            String current_mm = RConsUtils.getcurrentTime(4);


            String duration = RConsUtils.LastCallDuration(context, phoneNumber);
            String currentDate = RConsUtils.getcurrentTime(14);

            String statusOne = "";
            String statusTwo = "";
            String statusThree = "";
            String statusFour = "";

            String updateQuery = "";
            DebugLog.console("[DatabaseAdapter] inside baseline_updateCallStatus() checkedQuery " + checkedQuery);
            Cursor cursor = db.rawQuery(checkedQuery, new String[]{});
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                statusOne = cursor.getString(cursor.getColumnIndex("statusOne"));
                statusTwo = cursor.getString(cursor.getColumnIndex("statusTwo"));
                statusThree = cursor.getString(cursor.getColumnIndex("statusThree"));
                statusFour = cursor.getString(cursor.getColumnIndex("statusFour"));
                String temp2 = cursor.getString(cursor.getColumnIndex("temp2"));

                if (StringUtils.isEmpty(statusOne)) {

                    updateQuery = "UPDATE " + BaseLineSampleTable + " SET insert_or_updated_in_phone_at = '" + currentDate + "', survey_status = '" + survey_status + "', sc1 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc1_end_year = '" + current_year + "', sc1_end_month = '" + current_month + "' , sc1_end_day = '" + current_day + "', sc1_end_hh = '" + current_hh + "' , sc1_end_mm = '" + current_mm + "',sc1_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' ,  enum_code = '" + RConsUtils.getEnumCode() + "' ,  enum_name = '" + RConsUtils.getEnumName() + "' ,  build_no = '" + BuildConfig.VERSION_NAME + "' WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";
                } else if (StringUtils.isEmpty(statusTwo)) {

                    updateQuery = "UPDATE " + BaseLineSampleTable + " SET insert_or_updated_in_phone_at = '" + currentDate + "', survey_status = '" + survey_status + "', sc2 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc2_end_year = '" + current_year + "', sc2_end_month = '" + current_month + "' , sc2_end_day = '" + current_day + "', sc2_end_hh = '" + current_hh + "' , sc2_end_mm = '" + current_mm + "',sc2_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' ,  enum_code = '" + RConsUtils.getEnumCode() + "' ,  enum_name = '" + RConsUtils.getEnumName() + "' ,  build_no = '" + BuildConfig.VERSION_NAME + "' WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";
                } else if (StringUtils.isEmpty(statusThree)) {  //disabled when 4447 issue occuered
                    //making call again false manually
                    if (callAgain.equalsIgnoreCase("2")) {
                        updateQuery = "UPDATE " + BaseLineSampleTable + " SET insert_or_updated_in_phone_at = '" + currentDate + "', survey_status = '" + survey_status + "', sc3 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_year = '" + current_year + "', sc3_end_month = '" + current_month + "' , sc3_end_day = '" + current_day + "', sc3_end_hh = '" + current_hh + "' , sc3_end_mm = '" + current_mm + "',sc3_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' ,  enum_code = '" + RConsUtils.getEnumCode() + "' ,  enum_name = '" + RConsUtils.getEnumName() + "' ,  build_no = '" + BuildConfig.VERSION_NAME + "' WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";
                    } else {
                        updateQuery = "UPDATE " + BaseLineSampleTable + " SET insert_or_updated_in_phone_at = '" + currentDate + "', survey_status = '" + survey_status + "', sc3 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_year = '" + current_year + "', sc3_end_month = '" + current_month + "' , sc3_end_day = '" + current_day + "', sc3_end_hh = '" + current_hh + "' , sc3_end_mm = '" + current_mm + "',sc3_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' ,  enum_code = '" + RConsUtils.getEnumCode() + "' ,  enum_name = '" + RConsUtils.getEnumName() + "' ,  build_no = '" + BuildConfig.VERSION_NAME + "' WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";
                    }

                } else if (StringUtils.isEmpty(statusFour)) {  //disabled when 4447 issue occuered
                    //making call again false manually

                    updateQuery = "UPDATE " + BaseLineSampleTable + " SET insert_or_updated_in_phone_at = '" + currentDate + "', survey_status = '" + survey_status + "', sc4 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc4_end_year = '" + current_year + "', sc4_end_month = '" + current_month + "' , sc4_end_day = '" + current_day + "', sc4_end_hh = '" + current_hh + "' , sc4_end_mm = '" + current_mm + "',sc4_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' ,  enum_code = '" + RConsUtils.getEnumCode() + "' ,  enum_name = '" + RConsUtils.getEnumName() + "' ,  build_no = '" + BuildConfig.VERSION_NAME + "' WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";


                } else {
                    updateQuery = "UPDATE " + BaseLineSampleTable + " SET insert_or_updated_in_phone_at = '" + currentDate + "', survey_status = '" + survey_status + "', sc3 = '" + survey_status + "' , deviceID = '" + deviceID + "' , sc3_end_year = '" + current_year + "', sc3_end_month = '" + current_month + "' , sc3_end_day = '" + current_day + "', sc3_end_hh = '" + current_hh + "' , sc3_end_mm = '" + current_mm + "',sc3_reason = '" + reason + "' , reason = '" + reason + "', duration = '" + duration + "' , temp1 = '" + callAgain + "' ,  enum_code = '" + RConsUtils.getEnumCode() + "' ,  enum_name = '" + RConsUtils.getEnumName() + "' ,  build_no = '" + BuildConfig.VERSION_NAME + "' WHERE scode = '" + scode + "' AND studentid = '" + studentid + "'";
                }


                MubLog.cpnsoleLog("updateQuery" + updateQuery);
                db = database.getWritableDatabase();
                db.execSQL(updateQuery);
                db.close();

            }
            return callagain_flag_manual_set;
        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
            return callagain_flag_manual_set;
        }
    }


    public String getbaseline_ThreeTries_Done(String userName) {

        String subQuery = "(0)";
        ArrayList<String> farmerIDs = new ArrayList<>();
        try {
            String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE  rcons_user = '" + userName + "' AND isSynced != '2'  AND `sc1` != '' AND `sc2` != '' AND `sc3` !='' AND `sc4` !='' ; ";

            Cursor farmersCoursor = this.db.rawQuery(querry, new String[0]);

            MubLog.cpnsoleLog("inside baseline_ThreeTries_Done" + "\r\n" + querry);


            if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
                if (farmersCoursor != null) {

                    if (!farmersCoursor.isClosed()) {
                        farmersCoursor.close();
                    }
                }
                return subQuery;

            } else {

                MubLog.cpnsoleLog("inside baseline_ThreeTries_Done count  " + farmersCoursor.getCount());
                if (farmersCoursor.moveToFirst()) {
                    do {
                        String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("id"));
                        if (!StringUtils.isEmpty(formerID)) {
                            farmerIDs.add(formerID);
                        }

                    } while (farmersCoursor.moveToNext());

                }
            }

            subQuery = "";
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

            MubLog.cpnsoleLog("inside baseline_ThreeTries_Done " + subQuery);
            if (farmersCoursor != null) {

                if (!farmersCoursor.isClosed()) {
                    farmersCoursor.close();
                }
            }
            return subQuery;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside getFarmerId_sixTries_not_Done() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside baseline_ThreeTries_Done " + e.toString());
            return subQuery;
        }

    }

    public String getbaseline_no_call_again_ids(String userName) {

        String subQuery = "(0)";
        ArrayList<String> farmerIDs = new ArrayList<>();
        try {
            String querry = "SELECT * FROM   " + BaseLineSampleTable + " WHERE  rcons_user = '" + userName + "' AND isSynced != '2'  AND  survey_status = '10' AND temp1 = '1'; ";

            Cursor farmersCoursor = this.db.rawQuery(querry, new String[0]);

            MubLog.cpnsoleLog("inside baseline_no_call_again_ids" + "\r\n" + querry);


            if (farmersCoursor == null || farmersCoursor.getCount() == 0) {
                if (farmersCoursor != null) {

                    if (!farmersCoursor.isClosed()) {
                        farmersCoursor.close();
                    }
                }
                return subQuery;

            } else {

                MubLog.cpnsoleLog("inside baseline_no_call_again_ids count  " + farmersCoursor.getCount());
                if (farmersCoursor.moveToFirst()) {
                    do {
                        String formerID = farmersCoursor.getString(farmersCoursor.getColumnIndex("id"));
                        if (!StringUtils.isEmpty(formerID)) {
                            farmerIDs.add(formerID);
                        }

                    } while (farmersCoursor.moveToNext());

                }
            }

            subQuery = "";
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

            MubLog.cpnsoleLog("inside baseline_no_call_again_ids " + subQuery);
            if (farmersCoursor != null) {

                if (!farmersCoursor.isClosed()) {
                    farmersCoursor.close();
                }
            }
            return subQuery;
        } catch (Exception e) {
            // EmailDebugLog.getInstance(appContext).writeLog("[DatabaseAdapter] inside getFarmerId_sixTries_not_Done() Exception is :"+e.toString());
            MubLog.cpnsoleLog("inside baseline_no_call_again_ids " + e.toString());
            return subQuery;
        }

    }


    public Cursor baseline_selectCompletedCalls(String userName) {
        String query = "select * from " + BaseLineSampleTable + " WHERE rcons_user = '" + userName + "' AND survey_status !=''  AND  isSynced != '2'";
        DebugLog.console("[DatabaseAdapter] inside selectCompletedCalls() " + query);
        Cursor cursor = db.rawQuery(query, new String[]{});
        return cursor;

    }


    public JSONArray baseline_readReport(String userName) {
        MubLog.cpnsoleLog("inside baseline_readReport");

        try {

            String query = "select * from " + BaseLineSampleTable + " WHERE rcons_user = '" + userName + "' AND survey_status !=''  AND  isSynced != '2'";

            Cursor cursor2 = db.rawQuery(query, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data baseline_readReport");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("baseline_readReport data found ");
                return getSingleRow("", cursor2);
            }


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside baseline_readReport" + e.toString());
            return new JSONArray();
        }
    }


    public JSONArray baseline_readSection_pq(String tableName, String whereClause) {
        MubLog.cpnsoleLog("inside baseline_readSection_pq" + whereClause);

        try {


            Cursor cursor2 = db.rawQuery("Select * from " + tableName + whereClause, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                MubLog.cpnsoleLog("no data baseline_readSection_pq");
                return new JSONArray();
            } else {
                MubLog.cpnsoleLog("baseline_readSection_pq data found ");
                return getSingleRow("", cursor2);
            }


        } catch (Exception e) {
            MubLog.cpnsoleLog("inside baseline_readSection_pq\" " + e.toString());
            return new JSONArray();
        }
    }

    public void baseline_UpdateSyncedData(String tableName, String id) {
        String updateQuery = "";

        updateQuery = "UPDATE " + tableName + " SET isSynced = '" + "2" + "'  WHERE student_id IN " + id + "";

        MubLog.cpnsoleLog(updateQuery);

        try {
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);
            db.close();
        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
        }

    }


    public void baseline_UpdateReportData(String tableName, String id) {
        String updateQuery = "";

        updateQuery = "UPDATE " + tableName + " SET isSynced = '" + "2" + "'  WHERE studentid IN " + id + "";

        MubLog.cpnsoleLog(updateQuery);

        try {
            db = database.getWritableDatabase();
            db.execSQL(updateQuery);
            db.close();
        } catch (Exception e) {
            Log.e("updateCallStatus", e.toString());
        }

    }


    public int baseline_GetSyncCompleteQuestionnaireCounter(String userName) {
        SQLiteDatabase db = database.getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("select * from " + pq_section_a_table + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' )", new String[]{});

        if (cursor != null && cursor.getCount() > 0) {

            count = cursor.getCount();

            if (cursor != null) {

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            return count;
        } else {

            if (cursor != null) {

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }

            return count;

        }

    }


    public boolean aghhid_insertImporteddata(Context appcontext, JSONArray data) {

        boolean error = false;
        try {

            DebugLog.console("[DatabaseAdapter] inside aghhid_insertImporteddata() " + data.length());

            if (!isOpen())
                db = database.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            int k = 0;
            while (k < data.length()) {
                JSONObject json = data.getJSONObject(k);
                contentValues = new ContentValues();
                Iterator<String> iter = json.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    try {
                        Object value = json.get(key);

                        DebugLog.console("[DatabaseAdapter] inside aghhid_insertImporteddata() key" + key);
                        DebugLog.console("[DatabaseAdapter] inside aghhid_insertImporteddata() value" + value);

                        contentValues.put(key, value.toString());

                    } catch (JSONException e) {
                        // Something went wrong!
                        DebugLog.console("[DatabaseAdapter] inside aghhid_insertImporteddata() " + e.toString());
                        error = true;
                    }
                }

                long isInserted = db.insert(DatabaseAdapter.AGHHID_SampleTable, null, contentValues);
                DebugLog.console("[DatabaseAdapter] inside aghhid_insertImporteddata() " + isInserted);
                if (isInserted == -1) {
                    error = true;
                }
                k++;

            }


            db.close();
            return error;

        } catch (Exception e) {
            EmailDebugLog.getInstance(appcontext).writeLog("[DatabaseAdapter] inside insertImporteddata() Exception is :" + e.toString());

            return error;
        }
    }


    public int aghhid_getSampleDataCount() {
        int count = 0;
        String query = "select id from " + AGHHID_SampleTable;
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            count = cursor.getCount();
        } else {
            count = 0;
        }
        return count;

    }


    public void aghhid_deleteAllData() {
        MubLog.cpnsoleLog("inside delete All data");
        String deleteQuery = "Delete from " + AGHHID_SampleTable;
        db = database.getWritableDatabase();
        db.execSQL(deleteQuery);
        MubLog.cpnsoleLog("inside aghhid_deleteAllData  All data done");

    }


    public int aghhid_getNewCallsCount(String userName) {
        //removed 8 when 4447 issue
        try {


            String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
            // String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
            // String querry = "SELECT * FROM   "+BaseLineSampleTable+" WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (3,5,10) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + " AND farmer_id NOT IN " + getFarmerId_sc1_alt_Done + "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";
            String querry = "SELECT * FROM   " + AGHHID_SampleTable + " WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2'  AND survey_status = '' )   )";

            MubLog.cpnsoleLog("inside aghhid_getNewCallsCount " + querry);
            Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                return 0;
            }
            int count = cursor2.getCount();
            if (cursor2 != null) {

                if (!cursor2.isClosed()) {
                    cursor2.close();
                }
            }
            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(context).writeLog("[DatabaseAdapter] inside aghhid_getNewCallsCount() Exception is :" + e.toString());

            return 0;
        }


    }


    public Cursor aghhid_getNewCallsCursor(String userName) {

        //removed 8 when 4447 issue

        try {


            String getFarmerId_sixTries_Done = getFarmerId_sixTries_Done(userName);
            //  String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
            // String querry = "SELECT * FROM   "+BaseLineSampleTable+" WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2' )  AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  survey_status IN (1)  ) AND farmer_id NOT IN (SELECT farmer_id  FROM   farmercallbacktable    WHERE  survey_status IN (3,5,10) ) AND farmer_id NOT IN " + getFarmerId_sixTries_Done + " AND farmer_id NOT IN " + getFarmerId_sc1_alt_Done + "AND emp_id NOT IN (SELECT emp_id  FROM   farmercallbacktable    WHERE  temp1 IN (2)  ) )";
            String querry = "SELECT * FROM   " + AGHHID_SampleTable + " WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2'  AND survey_status = '' )   ) ORDER BY  village_id,hhid,phone_order ASC";

            MubLog.cpnsoleLog("inside aghhid_getNewCallsCursor " + querry);
            Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
            MubLog.cpnsoleLog("inside aghhid_getNewCallsCursor " + querry);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                return null;
            }
            return cursor2;
        } catch (Exception e) {
            MubLog.cpnsoleLog("[DatabaseAdapter] inside aghhid_getNewCallsCursor() Exception is :" + e.toString());
            return null;
        }
    }


    public int aghhid_getPendingCall(String userName) {
        //removed 8 when 4447 issue
        try {


            String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
            String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);
            // String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
            // String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);
            String querry = "SELECT * FROM   " + AGHHID_SampleTable + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND  (survey_status  IN (2,4,8,9,10) AND (id  NOT IN " + baseline_ThreeTries_Done + " AND id NOT IN " + baseline_no_call_again_ids + "))   ";
            MubLog.cpnsoleLog("inside aghhid_getPendingCall " + querry);
            Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
            if (cursor2 == null || cursor2.getCount() <= 0) {
                return 0;
            }

            int count = cursor2.getCount();
            if (cursor2 != null) {

                if (!cursor2.isClosed()) {
                    cursor2.close();
                }
            }
            return count;
        } catch (Exception e) {
            EmailDebugLog.getInstance(context).writeLog("[DatabaseAdapter] inside aghhid_getPendingCall() Exception is :" + e.toString());
            return 0;
        }

    }


    public Cursor aghhid_getPendingCallCursor(String userName) {


        String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
        String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);

        //String getFarmerId_sc1_alt_Done = getFarmerId_sc1_alt_Done(userName);
        //  String getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid = getFarmerId_having_temp_2_in_emp_ID_exceptthat_farmerid(userName);
        String querry = "SELECT * FROM   " + AGHHID_SampleTable + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND  (survey_status  IN (2,4,8,9,10) AND (id  NOT IN " + baseline_ThreeTries_Done + " AND id NOT IN " + baseline_no_call_again_ids + ")) ORDER BY  village_id,hhid,phone_order ASC  ";

        MubLog.cpnsoleLog("inside aghhid_getPendingCallCursor " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return null;
        }
        return cursor2;
    }

    public int aghhid_getSuccessFullCallCounter(String userName) {


        String querry = "SELECT * FROM   " + AGHHID_SampleTable + " WHERE  ( ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND ( survey_status  IN (1) ) )";

        Cursor cursor = db.rawQuery(querry, new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            int count = cursor.getCount();

            if (cursor != null) {

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            return count;

        } else {
            if (cursor != null) {

                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            return 0;

        }

    }

    public int aghhid_getCompletedCallCounter(String userName) {

        String baseline_no_call_again_ids = getbaseline_no_call_again_ids(userName);
        String baseline_ThreeTries_Done = getbaseline_ThreeTries_Done(userName);
        String querry = "SELECT * FROM   " + AGHHID_SampleTable + " WHERE   ( rcons_user = '" + userName + "' AND isSynced != '2' ) AND  (survey_status  IN (1,3,5,6,7) OR ( id  IN " + baseline_ThreeTries_Done + " OR id  IN " + baseline_no_call_again_ids + "))   ";


        MubLog.cpnsoleLog("inside aghhid_getCompletedCallCounter " + querry);
        Cursor cursor2 = this.db.rawQuery(querry, new String[0]);
        if (cursor2 == null || cursor2.getCount() <= 0) {
            return 0;
        }

        int count = cursor2.getCount();

        if (cursor2 != null) {

            if (!cursor2.isClosed()) {
                cursor2.close();
            }
        }
        return count;
    }


}