package net.elshaarawy.appsinnovate.Managers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by elshaarawy on 27-Dec-16.
 */

public class PreferenceManager {
    private Context context;
    private String SharedPreferenceName;
    private SharedPreferences sharedPreference;


    public PreferenceManager(Context context, String sharedPreferenceName) {
        this.context = context;
        this.SharedPreferenceName = sharedPreferenceName;
        this.sharedPreference = context.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE);
    }

    public boolean editValue(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putBoolean(key, value);
        boolean back = editor.commit();
        return back;
    }

    public boolean editValue(String key, String value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key, value);
        boolean back = editor.commit();
        return back;
    }

    public boolean editValue(String key, float value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putFloat(key, value);
        boolean back = editor.commit();
        return back;
    }

    public boolean editValue(String key, int value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putInt(key, value);
        boolean back = editor.commit();
        return back;
    }

    public boolean editValue(String key, long value) {
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putLong(key, value);
        boolean back = editor.commit();
        return back;
    }


    public boolean getBoolean(String key) {
        return sharedPreference.getBoolean(key, false);
    }

    public String getString(String key) {
        return sharedPreference.getString(key, null);
    }

    public float getFloat(String key) {
        return sharedPreference.getFloat(key, 0.0f);
    }

    public int getInt(String key) {
        return sharedPreference.getInt(key, 0);
    }

    public long getLong(String key) {
        return sharedPreference.getLong(key, 0);
    }

    public Set<String> getStringSet(String key) {
        return sharedPreference.getStringSet(key, null);
    }


    public static final class Keys {
        public static final String Default_SHARED_PREFERENCE = "DefaultPreference";
        public static final String PREF_IS_FIRST_TIME = "isFirstTime";
    }
}
