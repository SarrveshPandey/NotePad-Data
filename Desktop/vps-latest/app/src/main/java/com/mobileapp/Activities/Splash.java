package com.mobileapp.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;

import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Splash extends AppCompatActivity {

    SharedPreference_VPS sharedPreference_vps;
    String email,password,date,currentdate;
    Intent intent;
    private static int SPLASH_TIME_OUT = 3000;
    int day = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);

        email = sharedPreference_vps.getEmail().toString();
        password = sharedPreference_vps.getPassword().toString();
        date = sharedPreference_vps.getDate().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDate = sdf.format(new Date());

        try {
            Date dateBefore = sdf.parse(sdf.format(date));
            Date dateAfter = sdf.parse(currentDate);
            long difference = dateAfter.getTime() - dateBefore .getTime();
            float daysBetween = (difference / (1000*60*60*24));
            /* You can also convert the milliseconds to days using this method
             * float daysBetween =
             *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */

            day = (int) daysBetween;

            Log.e("Days between dates: ",daysBetween+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (email.equals("") || email.equals(null) || password.equals("") || password.equals(null)) {

                    intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();

                } else {

                    if (day <30){
                        intent = new Intent(Splash.this, MainHome.class);
                        startActivity(intent);
                        finish();
                    }else {
                        sharedPreference_vps.clearPreference();
                        intent = new Intent(Splash.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }, SPLASH_TIME_OUT);

    }

}
