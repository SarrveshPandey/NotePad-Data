package com.mobileapp.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import org.json.JSONObject;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AccessFragment extends Fragment {

    @BindView(R.id.tv_access_button1)
    TextView tv_access_button1;

    @BindView(R.id.tv_access_button2)
    TextView tv_access_button2;


    @BindView(R.id.tv_usernamee)
    TextView tv_usernamee;


    @BindView(R.id.tv_password)
    TextView tv_password;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_toast)
    RelativeLayout rel_toast;

    @BindView(R.id.rel_cross)
    RelativeLayout rel_cross;

    SharedPreference_VPS sharedPreference_vps;
    String token, service_id,username,password,link,reset;
    Intent accessintent;
    JSONObject jsonResult;
    public  Dialog dialog;
    TextView tv_okay,tv_cancell;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.access_fragment, container, false);
        ButterKnife.bind(this, view);
        sharedPreference_vps = SharedPreference_VPS.getInstance(getContext());
        token = sharedPreference_vps.getApi_Key().toString();
        accessintent = getActivity().getIntent();
        service_id = accessintent.getStringExtra("Service_Id");

        dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.delete_or_not);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tv_okay = (TextView)dialog.findViewById(R.id.tv_okay);
        tv_cancell = (TextView)dialog.findViewById(R.id.tv_cancell);

        return view;

    }

    @OnClick(R.id.tv_access_button1)
    public void Launch_Console(View view) {

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Launch_ConsoleApi(service_id, token);

            }
        }, 1000);


    }


    @OnClick(R.id.tv_access_button2)
    public void Reset_Console(View view) {

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

               Reset_ConsoleApi(service_id, token);

            }
        }, 2000);


    }

    @OnClick(R.id.rel_cross)
    public void CloseToast(View view) {

        rel_toast.setVisibility(View.GONE);

    }

    private void Reset_ConsoleApi(String service_id, String token) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.resetConsole(token, Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("Reset_ConsoleApi", call.request().toString());

                try {
                    if (response.isSuccessful()) {

                        String data = "";

                        data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        Iterator<String> iterator = jsonObject.keys();
                        while(iterator.hasNext()){

                            String key = (String)iterator.next();
                            Object issue = jsonObject.get(key);
                            Log.e("JSON key:-----", key);
                            Log.e("JSON issue:-----", String.valueOf(issue));

                            if (key.contains("result")){
                                reset = String.valueOf(issue);
                            }

                            rel_toast.setVisibility(View.VISIBLE);
                            tv_usernamee.setText(reset);
                           tv_password.setVisibility(View.GONE);
                            final Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    rel_toast.setVisibility(View.GONE);
                                }
                            }, 3000);

                        }


                        simpleProgressBar.setVisibility(View.GONE);

                    } else {
                        simpleProgressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
                Log.e("errror", String.valueOf(t));

            }
        });

    }


    private void Launch_ConsoleApi(String service_id, String token) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.launchConsole(token, Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("Launch_ConsoleApi", call.request().toString());

                try {
                    if (response.isSuccessful()) {

                        String data = "";

                        data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        jsonResult = jsonObject.getJSONObject("result");
                        Log.e("JSON OBJECT:-----", String.valueOf(jsonResult.length()));
                        JSONObject j  = new JSONObject(String.valueOf(jsonResult));
                        Iterator<String> iterator = j.keys();
                        while(iterator.hasNext()){

                            String key = (String)iterator.next();
                            Object issue = j.get(key);
                            Log.e("JSON key:-----", key);
                            Log.e("JSON issue:-----", String.valueOf(issue));

                            if (key.contains("user")){
                                username = String.valueOf(issue);

                            }
                            if (key.contains("pass")){
                                password = String.valueOf(issue);
                            }

                            if (key.contains("link")){
                                link = String.valueOf(issue);
                            }

                            tv_okay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialog.dismiss();

                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                                    startActivity(browserIntent);
                                    Runnable progressRunnable = new Runnable() {

                                        @Override
                                        public void run() {

                                            try {


                                            }
                                            catch (IndexOutOfBoundsException e){

                                            }
                                        }
                                    };

                                    Handler pdCanceller = new Handler();
                                    pdCanceller.postDelayed(progressRunnable, 3000);


                                }
                            });

                            tv_cancell.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();

                            rel_toast.setVisibility(View.VISIBLE);
                            tv_usernamee.setText("Userame:" + " " + username);
                            tv_password.setText("Password:" + " " + password);


                        }


                        simpleProgressBar.setVisibility(View.GONE);

                    } else {
                        simpleProgressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
                Log.e("errror", String.valueOf(t));

            }
        });

    }


}
