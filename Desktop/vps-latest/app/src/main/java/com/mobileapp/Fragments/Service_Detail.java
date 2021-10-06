package com.mobileapp.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Service_Detail extends Fragment {

    @BindView(R.id.tv_summary)
    TextView tv_summary;

    @BindView(R.id.tv_processor)
    TextView tv_processor;

    @BindView(R.id.tv_memory)
    TextView tv_memory;

    @BindView(R.id.tv_harddrive)
    TextView tv_harddrive;

    @BindView(R.id.tv_additional_ipv4)
    TextView tv_additional_ipv4;

    @BindView(R.id.tv_ipv4)
    TextView tv_ipv4;

    @BindView(R.id.tv_bandwisth)
    TextView tv_bandwisth;

    @BindView(R.id.tv_uplink)
    TextView tv_uplink;

    @BindView(R.id.tv_ddos_protection)
    TextView tv_ddos_protection;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_main_servicedetail)
    RelativeLayout rel_main_servicedetail;

    Bundle arguments;
    String processor, memory, harddrive,service_id,token,bandwidth,ipv4,additional_ipv4,uplink,PowerStatus,ddos;
    SharedPreference_VPS sharedPreference_vps;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_service_detail, container, false);
        ButterKnife.bind(this, view);

        sharedPreference_vps = SharedPreference_VPS.getInstance(getContext());
        arguments = getArguments();
        service_id = arguments.getString("service_id");
        PowerStatus = arguments.getString("PowerStatus");
        token = sharedPreference_vps.getApi_Key().toString();
        rel_main_servicedetail.setVisibility(View.GONE);

        ManageServiceApi(token, service_id);
        return view;
    }

    private void ManageServiceApi(String token, String service_id) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.getParticularService(token, Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("ServiceSpecificationApi", call.request().toString());
                Log.e("ServiceSpecificResponse", new Gson().toJson(response));

                try {
                    if (response.isSuccessful()) {

                        String data = "";
                        data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonResult = jsonObject.getJSONArray("result");

                        processor = jsonResult.getJSONObject(0).getJSONObject("cpu1").getJSONObject("result").getString("model");
                        memory = jsonResult.getJSONObject(0).getJSONObject("ram").getJSONObject("result").getString(    "total") + " " + "MB";
                        harddrive = jsonResult.getJSONObject(0).getJSONObject("storage").getJSONObject("drive1").getJSONObject("result").getString("total")
                                + " " + "GB" + " "
                                + jsonResult.getJSONObject(0).getJSONObject("storage").getJSONObject("drive1").getJSONObject("result").getString("type");
                        bandwidth = jsonResult.getJSONObject(0).getJSONObject("bandwidth").getJSONObject("result").getString("total") + " " + "GB";
                        uplink = jsonResult.getJSONObject(0).getJSONObject("uplink").getString("result") + " " + "Mbps";
//                        ddos = jsonResult.getJSONObject(0).getJSONObject("uplink").getString("result") + " " + "Mbps";
                        ipv4 = jsonResult.getJSONObject(0).getJSONObject("ipv4").getString("results");
                        additional_ipv4 = jsonResult.getJSONObject(0).getJSONObject("ipv4").getString("results");

                        tv_processor.setText(processor);
                        tv_memory.setText(memory);
                        tv_harddrive.setText(harddrive);
                        tv_bandwisth.setText(bandwidth);
                        tv_uplink.setText(uplink);
                        tv_additional_ipv4.setText(additional_ipv4);
                        tv_ipv4.setText(ipv4);

                        rel_main_servicedetail.setVisibility(View.VISIBLE);
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
