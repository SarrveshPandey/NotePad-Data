package com.mobileapp.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mobileapp.Adapters.MainHome_Adapter;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.budgetvm.mobileapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHome extends AppCompatActivity {

//    private static final int PERMISSION_REQUEST_CODE = 101;


    @BindView(R.id.recyclerview_home)
    RecyclerView recyclerview_home;

    GridLayoutManager mGridLayoutManager;
    MainHome_Adapter mainHome_adapter;

    private MyPostRecyclerViewListener Listener;

    Intent intent;
    String Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        ButterKnife.bind(this);
        Listener = new MyPostRecyclerViewListener() {
            @Override
            public void onClick(View view, int position) {

                Position = String.valueOf(position);
                if (Position.equals("") || Position.equals(null)) {

                } else if (position == 0) {
                    intent = new Intent(MainHome.this, Services.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent = new Intent(MainHome.this, Billing.class);
                    startActivity(intent);
                } else if (position == 2) {
                    intent = new Intent(MainHome.this, Home.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent = new Intent(MainHome.this, DNS.class);
                    startActivity(intent);
                }

                /*else if (position== 4){
                    intent = new Intent(MainHome.this,ApiManagement.class);
                    startActivity(intent);
                }*/
                else if (position == 4) {
                    intent = new Intent(MainHome.this, Profile.class);
                    startActivity(intent);
                } else {
//                    intent = new Intent(MainHome.this,Services.class);
//                    startActivity(intent);
                }
            }
        };
        mGridLayoutManager = new GridLayoutManager(this, 2);
        recyclerview_home.setLayoutManager(mGridLayoutManager);
        mainHome_adapter = new MainHome_Adapter(this, Listener);
        recyclerview_home.setAdapter(mainHome_adapter);





       /* if (checkPermission()) {
            //main logic or main code

            // . write your main code to execute, It will execute if the permission is already given.

        } else {
            requestPermission();
        }*/
    }

/*    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }*/
}





