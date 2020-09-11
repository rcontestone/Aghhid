package com.rcons.fcallbacks;

import java.util.ArrayList;

/**
 * Created by MubasharChoudhry on 12/3/2016.
 * MubasharChoudhry=""
 */



public class StructureUtil {

    public static ArrayList<String> withinvillage_json = new ArrayList<String>();
    public static int LAST_STRUCTUREID_withinsame_village = 0;
    public static ArrayList<String> withinvillage_json_updated = new ArrayList<String>();

    public static ArrayList<String> outsidevillage_samedistrict_json = new ArrayList<String>();
    public static int LAST_STRUCTUREID_outsidevillage_samedistrict = 0;

    public static ArrayList<String> outsidedistrict_withincountry_json = new ArrayList<String>();
    public static int LAST_STRUCTUREID_outsidedistrict_withincountry = 0;

    public static ArrayList<String> outsidecountry_json = new ArrayList<String>();
    public static int LAST_STRUCTUREID_outsidecountry = 0;


    public static void re_initialize_variables() {

        withinvillage_json = new ArrayList<String>();
        LAST_STRUCTUREID_withinsame_village = 0;

        outsidevillage_samedistrict_json = new ArrayList<String>();
        LAST_STRUCTUREID_outsidevillage_samedistrict = 0;

        outsidedistrict_withincountry_json = new ArrayList<String>();
        LAST_STRUCTUREID_outsidedistrict_withincountry = 0;

        outsidecountry_json = new ArrayList<String>();
        LAST_STRUCTUREID_outsidecountry = 0;
    }
}
