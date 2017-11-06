package com.yogiw.sleepcalculator.Helper;

import android.content.Context;

import com.yogiw.sleepcalculator.Model.FirsttimeClass;
import com.yogiw.sleepcalculator.Model.SettingList;

/**
 * Created by Yogi Wisesa on 11/6/2017.
 */

public class FirsttimePref {

    public static final String PREFS_NAME = "bedtimecalculator_prefs";
    public static final String PREFS_VAL = "firstime_value";

    public static void save(FirsttimeClass data, Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.putObject(PREFS_VAL, data);
        complexPreferences.commit();
    }

    public static FirsttimeClass load(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getObject(PREFS_VAL, FirsttimeClass.class);
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

