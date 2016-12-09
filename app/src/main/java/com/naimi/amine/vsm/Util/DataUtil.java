package com.naimi.amine.vsm.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by macbookpro on 06/12/16.
 */
public class DataUtil {
    public static final String MY_PREFS = "myprefs";

    public static final String PRODUCT_ID = "ProductID";

    public static final String PROCESS_ID = "ProcessID";

    public static String getCurrentProductID(Context c) {

        SharedPreferences sharedPref = c.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);

        return sharedPref.getString(PRODUCT_ID, "");



    }

    public static void setCurrentProductID(Context c, String prductID) {

        SharedPreferences sharedPref = c.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PRODUCT_ID, prductID);

        editor.commit();

    }

    public static String getCurrentProcessID(Context c) {

        SharedPreferences sharedPref = c.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);

        return sharedPref.getString(PROCESS_ID, "");

    }

    public static void setCurrentProcessID(Context c, String processID) {

        SharedPreferences sharedPref = c.getSharedPreferences(MY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PROCESS_ID, processID);

        editor.commit();

    }
}
