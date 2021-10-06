package com.mobileapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference_VPS {

    String PREF_NAME = "VPS";
    String Email = "Email";
    String Password = "Password";
    String Api_Key = "API_KEY";
    String Date = "Date";



    private SharedPreferences pref = null;
    private static SharedPreference_VPS preferences = null;

    static Context mContext = null;

    public static SharedPreference_VPS getInstance(Context context) {

        if (preferences == null) {

            preferences = new SharedPreference_VPS(context);
        }
        mContext = context;
        return preferences;
    }

    public void clearPreference() {
        pref.edit().clear().apply();
    }

    public SharedPreference_VPS(Context context) {
        mContext = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getPREF_NAME() {
        return PREF_NAME;
    }

    public void setPREF_NAME(String PREF_NAME) {
        this.PREF_NAME = PREF_NAME;
    }



    public String getEmail() {
        return pref.getString(Email,"");
    }

    public void setEmail(String email) {
        pref.edit().putString(Email,email).commit();

    }


    public String getPassword() {
        return pref.getString(Password,"");
    }

    public void setPassword(String password) {
        pref.edit().putString(Password,password).commit();
    }

    public String getApi_Key() {
        return pref.getString(Api_Key,"");
    }

    public void setApi_Key(String Api_Keyy) {
        pref.edit().putString(Api_Key,Api_Keyy).commit();
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
