package com.mobileapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.Adapters.NetworkAdapter;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.mobileapp.POJO.NetworkingPojo;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Networking extends Fragment {


    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_recycler_network)
    RelativeLayout rel_recycler_network;

    @BindView(R.id.rel_network_main)
    LinearLayout rel_network_main;

    @BindView(R.id.tv_nodata)
    TextView tv_nodata;

    @BindView(R.id.recycler_network)
    RecyclerView recycler_network;

    private MyPostRecyclerViewListener Listener;
    NetworkAdapter networkAdapter;
    String service_id,token;
    Intent networ_intent;
    SharedPreference_VPS sharedPreference_vps;
    JSONObject jsonResult;
    List<NetworkingPojo> networkingPojos=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.networking_fragment, container, false);
        ButterKnife.bind(this, view);
        sharedPreference_vps = SharedPreference_VPS.getInstance(getContext());
        networ_intent = getActivity().getIntent();
        service_id = networ_intent.getStringExtra("Service_Id");
        token = sharedPreference_vps.getApi_Key().toString();
        recycler_network.setLayoutManager(new LinearLayoutManager(getActivity()));


        NetworkingApi(token,service_id);
        return view;

    }
    private void NetworkingApi(String token, String service_id) {

        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.getNetworking(token, Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("NetworkingApi", call.request().toString());

                try {
                    if (response.isSuccessful()) {

                        String data = "";
                        networkingPojos.clear();
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
                            NetworkingPojo networkingPojo=new NetworkingPojo();
                            networkingPojo.setKey(key);
                            networkingPojo.setValue(issue.toString());
                            networkingPojos.add(networkingPojo);
                        }

                        Log.e("JSON networkingPojos size:-----", String.valueOf(networkingPojos.size()));
                        networkAdapter = new NetworkAdapter(getActivity(), Listener,networkingPojos);
        recycler_network.setAdapter(networkAdapter);

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
