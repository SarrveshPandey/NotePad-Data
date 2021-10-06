package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.POJO.LoginPojo;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.Global_Constants;
import com.mobileapp.Utils.SharedPreference_VPS;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_forgot_pass)
    TextView tv_forgot_pass;

    @BindView(R.id.tv_loginhead)
    TextView tv_loginhead;

    @BindView(R.id.tv_email)
    TextView tv_email;

    @BindView(R.id.tv_password)
    TextView tv_password;

    @BindView(R.id.tv_signin)
    TextView tv_signin;

    @BindView(R.id.tv_emailerror)
    TextView tv_emailerror;

    @BindView(R.id.tv_passerror)
    TextView tv_passerror;

    @BindView(R.id.tv_error)
    TextView tv_error;

    @BindView(R.id.rel_loginbutton)
    RelativeLayout rel_loginbutton;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBarr;


    Intent intent;

    String email, password;

    SharedPreference_VPS sharedPreference_vps;

    Global_Constants global_constants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        global_constants  = new Global_Constants();
        sharedPreference_vps = SharedPreference_VPS.getInstance(this);

        setfont();
        rel_loginbutton.setOnClickListener(this);

        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://portal.budgetvm.com/auth/login"));
                startActivity(intent);
            }
        });//on click close
    }

    private void setfont() {

        tv_loginhead.setTypeface(global_constants.montserrat_regular(this));
        tv_email.setTypeface(global_constants.montserrat_light(this));
        tv_password.setTypeface(global_constants.montserrat_light(this));
        tv_signin.setTypeface(global_constants.montserrat_light(this));
//        tv_forgot_pass.setTypeface(global_constants.montserrat_light(this));

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rel_loginbutton:
                validation();
                break;
        }
    }

    private void validation() {

        if (et_email.getText().toString().isEmpty()) {

            tv_emailerror.setVisibility(View.VISIBLE);
            tv_passerror.setVisibility(View.GONE);
            tv_error.setVisibility(View.GONE);

        } else if (et_password.getText().toString().trim().isEmpty()) {
            tv_emailerror.setVisibility(View.GONE);
            tv_passerror.setVisibility(View.VISIBLE);
            tv_error.setVisibility(View.GONE);

        } else {

            email = et_email.getText().toString();
            password = et_password.getText().toString();
            callLoginapi(email, password);
        }

    }

    private void callLoginapi(String email, String password) {

        simpleProgressBarr.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<LoginPojo> call = apiService.loginData(email, password);
        call.enqueue(new retrofit2.Callback<LoginPojo>() {
            @Override
            public void onResponse(Call<LoginPojo> call, Response<LoginPojo> response) {

                Log.e("Login url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("Login response", new Gson().toJson(response));
                    if (response.body().getSuccess() == true) {

                        Log.e("Login response", new Gson().toJson(response));

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        String currentDate = sdf.format(new Date());

                        simpleProgressBarr.setVisibility(View.GONE);
                        sharedPreference_vps.setEmail(email);
                        sharedPreference_vps.setPassword(password);
                        sharedPreference_vps.setDate(currentDate);
                        sharedPreference_vps.setApi_Key(response.body().getResult().toString());
                        intent = new Intent(Login.this, MainHome.class);
                        startActivity(intent);
                        finish();

                    }

                } else {

                    simpleProgressBarr.setVisibility(View.GONE);

                    tv_emailerror.setVisibility(View.GONE);
                    tv_passerror.setVisibility(View.GONE);
                    tv_error.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<LoginPojo> call, Throwable t) {

                Log.e("errror", String.valueOf(t));

                simpleProgressBarr.setVisibility(View.GONE);
            }
        });

    }
}
