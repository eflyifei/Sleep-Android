package com.arefly.sleep.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.arefly.sleep.R;

/**
 * Created by eflyjason on 5/8/2016.
 */
public class PreferencesHelper {

    public static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static boolean getIsWakenUpBool(Context context) {
        return getPreferences(context).getBoolean(context.getResources().getString(R.string.bool_is_waken_up), false);
    }

    public static String getSleepTimeString(Context context) {
        return getPreferences(context).getString(context.getResources().getString(R.string.string_earliest_sleep_time), "20:00");
    }

    public static String getWakeTimeString(Context context) {
        return getPreferences(context).getString(context.getResources().getString(R.string.string_earliest_wake_time), "06:00");
    }


    public static void setIsWakenUpBool(boolean isWakenUp, Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(context.getResources().getString(R.string.bool_is_waken_up), isWakenUp);
        editor.apply();
    }

    public static void setSleepTimeString(String time, Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(context.getResources().getString(R.string.string_earliest_sleep_time), time);
        editor.apply();
    }

    public static void setWakeTimeString(String time, Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(context.getResources().getString(R.string.string_earliest_wake_time), time);
        editor.apply();
    }

}