package com.mobileapp.Fragments;

import android.content.Intent;
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

import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.POJO.PowerPojo;
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

public class PowerFragment extends Fragment {


    @BindView(R.id. rel_graceful_button)
    RelativeLayout  rel_graceful_button;

    @BindView(R.id. rel_graceful_button_power)
    RelativeLayout  rel_graceful_button_power;

    @BindView(R.id. rel_graceful_button1)
    RelativeLayout  rel_graceful_button1;

    @BindView(R.id. rel_graceful_button2)
    RelativeLayout  rel_graceful_button2;

    @BindView(R.id. rel_button4)
    RelativeLayout  rel_button4;

    @BindView(R.id. rel_button3)
    RelativeLayout  rel_button3;

    @BindView(R.id. rel_button2)
    RelativeLayout  rel_button2;

    @BindView(R.id. rel_button1)
    RelativeLayout  rel_button1;

    @BindView(R.id. view1)
    View  view1;

    @BindView(R.id. view2)
    View  view2;

    @BindView(R.id. view3)
    View  view3;

    @BindView(R.id.view4)
    View  view4;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_toast)
    RelativeLayout rel_toast;

    @BindView(R.id.rel_cross)
    RelativeLayout rel_cross;

    @BindView(R.id.tv_usernamee)
    TextView tv_usernamee;


    @BindView(R.id.tv_password)
    TextView tv_password;

    Intent powerintent;
    String service_id,token,power_status,changed_power_status,force_poweroff,power_cycle;
    SharedPreference_VPS sharedPreference_vps;
    JSONObject jsonResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.power_fragment, container, false);
        ButterKnife.bind(this, view);
        sharedPreference_vps = SharedPreference_VPS.getInstance(getContext());
        powerintent = getActivity().getIntent();
        service_id =  powerintent.getStringExtra("Service_Id");
        token = sharedPreference_vps.getApi_Key().toString();

        rel_button1.setVisibility(View.GONE);
        rel_button2.setVisibility(View.GONE);
        rel_button3.setVisibility(View.GONE);
        rel_button4.setVisibility(View.GONE);

        view1.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);
        getPowerstatus(service_id, token);



        return view;

    }

    private void getPowerstatus(String service_id, String token) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<PowerPojo> call = apiService.getPowerStatus(token, Integer.parseInt(service_id));
        Log.e("getPowerstatus url....", call.request().toString());
        call.enqueue(new retrofit2.Callback<PowerPojo>() {
            @Override
            public void onResponse(Call<PowerPojo> call, Response<PowerPojo> response) {

                Log.e("PowerStatus url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("PowerStatus response", new Gson().toJson(response));
                    if (response.body().getSuccess() == true) {
                        Log.e("PowerStatus response", new Gson().toJson(response));

                        power_status  = response.body().getResult().toString();

                        if (power_status.equals("on")){
                            rel_button1.setVisibility(View.VISIBLE);
                            rel_button2.setVisibility(View.VISIBLE);
                            rel_button3.setVisibility(View.VISIBLE);
                            rel_button4.setVisibility(View.GONE);

                            view1.setVisibility(View.VISIBLE);
                            view2.setVisibility(View.VISIBLE);
                            view3.setVisibility(View.VISIBLE);
                            view4.setVisibility(View.GONE);
                        }else {

                            rel_button1.setVisibility(View.GONE);
                            rel_button2.setVisibility(View.GONE);
                            rel_button3.setVisibility(View.GONE);
                            rel_button4.setVisibility(View.VISIBLE);

                            view1.setVisibility(View.GONE);
                            view2.setVisibility(View.GONE);
                            view3.setVisibility(View.GONE);
                            view4.setVisibility(View.VISIBLE);
                        }
                        simpleProgressBar.setVisibility(View.GONE);

                        }

                } else {
                    simpleProgressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<PowerPojo> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
                Log.e("errror", String.valueOf(t));

            }
        });

    }

    @OnClick(R.id. rel_graceful_button)
    public void GracefulPower(View view){

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (power_status.equals("on")){
                    changePowerStatus(token,"off",service_id);
                }
                else {
                    changePowerStatus(token,"on",service_id);
                }
            }
        }, 2000);

    }

    @OnClick(R.id. rel_graceful_button1)
    public void ForcePowerOff(View view){

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                force_PowerOffApi(token,"reboot",service_id);

            }
        }, 1000);

    }

    private void force_PowerOffApi(String token, String reboot, String service_id) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.forcePowerOff(token,reboot ,Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("force_PowerOffApi", call.request().toString());

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
                                force_poweroff = String.valueOf(issue);
                            }

                            rel_toast.setVisibility(View.VISIBLE);
                            tv_usernamee.setText(force_poweroff);
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

    @OnClick(R.id. rel_graceful_button2)
    public void PowerCycler(View view){

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                powerCycleApi(token,"cycle",service_id);

            }
        }, 1000);

    }

    private void powerCycleApi(String token, String cycle, String service_id) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.powerCycle(token,cycle ,Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("powerCycleApi", call.request().toString());

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
                                power_cycle = String.valueOf(issue);
                            }

                            rel_toast.setVisibility(View.VISIBLE);
                            tv_usernamee.setText(power_cycle);
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


    @OnClick(R.id. rel_graceful_button_power)
    public void PowerButton(View view){

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
        if (power_status.equals("on")){
            changePowerStatus(token,"off",service_id);
        }
        else {
            changePowerStatus(token,"on",service_id);
        }
            }
        }, 1000);

    }

    private void changePowerStatus(String token, String off, String service_id) {


        simpleProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<PowerPojo> call = apiService.powerStatusUpdate(token,off ,Integer.parseInt(service_id));
        Log.e("getPowerstatus url....", call.request().toString());
        call.enqueue(new retrofit2.Callback<PowerPojo>() {
            @Override
            public void onResponse(Call<PowerPojo> call, Response<PowerPojo> response) {

                Log.e("PowerStatus url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("PowerStatus response", new Gson().toJson(response));
                    if (response.body().getSuccess() == true) {
                        Log.e("PowerStatus response", new Gson().toJson(response));


                        changed_power_status = response.body().getResult();
                        rel_toast.setVisibility(View.VISIBLE);
                        tv_usernamee.setText(changed_power_status);
                        tv_password.setVisibility(View.GONE);
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rel_toast.setVisibility(View.GONE);
                            }
                        }, 3000);


                        getPowerstatus(service_id, token);

                        simpleProgressBar.setVisibility(View.GONE);

                    }

                }
                else {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Wait");
                    sweetAlertDialog.show();
                }
            }

            @Override
            public void onFailure(Call<PowerPojo> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
                Log.e("errror", String.valueOf(t));

            }
        });

    }

}
