package com.yogiw.sleepcalculator.Helper;

import android.content.Context;

import com.yogiw.sleepcalculator.Model.SettingClass;
import com.yogiw.sleepcalculator.Model.SettingList;

import java.util.ArrayList;

/**
 * Created by Yogi Wisesa on 10/31/2017.
 */

public class SettingPref {
    public static final String PREFS_NAME = "bedtimecalculator_prefs";
    public static final String PREFS_VAL = "setting_value";

    public static void save(SettingList data, Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.putObject(PREFS_VAL, data);
        complexPreferences.commit();
    }

    public static SettingList load(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getObject(PREFS_VAL, SettingList.class);
    }

    public static String getJSON(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getJSON(PREFS_VAL);
    }

    public static void clearAll(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }
}
