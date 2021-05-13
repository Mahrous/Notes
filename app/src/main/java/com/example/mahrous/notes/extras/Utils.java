package com.example.mahrous.notes.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import java.util.List;

public class Utils {

    public static final int ALL_NOTE_TYPE = 0;
    public static final int ALL_DESCENDING = 1;
    public static final int ALL_ASCENDING = 2;
    public static final int ALL_COMPLETED = 3;
    public static final int ALL_IN_COMPLETED = 4;


    public static void showViews(List<View> views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
            Log.d("call", "Visible");
        }

    }

    public static void hideViews(List<View> views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
            Log.d("call", "Gone");

        }

    }

    public static void savePref(Context context, int type) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("t", type);
        editor.apply();

    }

    public static int loadPref(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Log.d("t", "Shared " + preferences.getInt("t", Utils.ALL_NOTE_TYPE));
        return preferences.getInt("t", Utils.ALL_NOTE_TYPE);
    }
}
