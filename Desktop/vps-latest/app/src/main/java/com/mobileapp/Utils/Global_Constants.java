package com.mobileapp.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

public class Global_Constants {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public  static String regularExpresionOfEmailId =
            "^(?!.{51})([A-Za-z0-9])+([A-Za-z0-9._-])+@([A-Za-z0-9._-])+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static ProgressDialog mProgressDialog;

    public boolean checkInternetPermission(Context ctx) {
        String permission = "android.permission.INTERNET";
        int res = ctx.checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

//    public Typeface Regular(Activity act){
//        Typeface custom_font = Typeface.createFromAsset(act.getAssets(),  "font/sfdisplay_regular.ttf");
//        return custom_font;
//    }

    public Typeface montserrat_bold(Activity act)
    {
        Typeface custom_font = Typeface.createFromAsset(act.getAssets(), "montserrat_bold.ttf");
        return custom_font;
    }

    public Typeface montserrat_light(Activity act)
    {
        Typeface custom_font = Typeface.createFromAsset(act.getAssets(), "montserrat_light.ttf");
        return custom_font;
    }

    public Typeface montserrat_regular(Activity act)
    {
        Typeface custom_font = Typeface.createFromAsset(act.getAssets(), "montserrat_regular.ttf");
        return custom_font;
    }

    public Typeface montserrat_semiBold(Activity act)
    {
        Typeface custom_font = Typeface.createFromAsset(act.getAssets(), "montserrat_semiBold.ttf");
        return custom_font;
    }




}
