package com.gamenative.fruitymatch.fruit_ad;

import android.content.Context;

import com.gamenative.fruitymatch.Fruit_App_Controller;
import com.github.rtoshiro.secure.SecureSharedPreferences;

public class Fruit_SharePrefs {
    private static Fruit_SharePrefs instance = null;
    public static final String HomeData = "HomeData";
    public static final String isAppAdShow = "isAppAdShow";
    public static final String WhichOne = "WhichOne";
    public static final String status = "status";
    private SecureSharedPreferences sharedPreferences;

    public static final String isFromNotification = "isFromNotification";


    public Fruit_SharePrefs(Context context) {
        this.sharedPreferences = new SecureSharedPreferences(Fruit_App_Controller.mContext);
    }

    public static Fruit_SharePrefs getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return new Fruit_SharePrefs(Fruit_App_Controller.mContext);
        }
    }



    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }


    public void putString(String key, String val) {
        sharedPreferences.edit().putString(key, val).apply();
    }
    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}
