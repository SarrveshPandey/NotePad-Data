package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileapp.Adapters.DNSAdapter;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.mobileapp.POJO.Domain;
import com.mobileapp.POJO.DomainResult;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class DNS extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recycler_dns)
    RecyclerView recycler_dns;

    @BindView(R.id.rel_back_dns)
    RelativeLayout rel_back_dns;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_recy)
    RelativeLayout rel_recy;

    @BindView(R.id.rel_domain_main)
    RelativeLayout rel_domain_main;

    @BindView(R.id.tv_nodata)
    TextView tv_nodata;

    DNSAdapter dnsAdapter;

    Intent intent;

    SharedPreference_VPS sharedPreference_vps;

    List<DomainResult> domainResults = new ArrayList<>();

    String token;

    GridLayoutManager mGridLayoutManager;
    private MyPostRecyclerViewListener Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_n_s);

        ButterKnife.bind(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();

        Listener = new MyPostRecyclerViewListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };
        recycler_dns.setLayoutManager(new LinearLayoutManager(DNS.this));

        dnsAdapter = new DNSAdapter(this, Listener, domainResults);
        recycler_dns.setAdapter(dnsAdapter);

        rel_back_dns.setOnClickListener(this);

        DoaminApi(token);

    }

    private void DoaminApi(String token) {

        simpleProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<Domain> call = apiService.Domain_list(token);
        call.enqueue(new retrofit2.Callback<Domain>() {
            @Override
            public void onResponse(Call<Domain> call, Response<Domain> response) {

                Log.e("DoaminApi url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("DoaminApi response", new Gson().toJson(response));
                    if (response.body().getSuccess() == true) {
                        Log.e("DoaminApi response", new Gson().toJson(response));
                        simpleProgressBar.setVisibility(View.GONE);
                        rel_domain_main.setVisibility(View.VISIBLE);
                        rel_recy.setVisibility(View.VISIBLE);
                        tv_nodata.setVisibility(View.GONE);
                        domainResults.addAll(response.body().getResult());
                        dnsAdapter.notifyDataSetChanged();

                    }
                } else {

                    simpleProgressBar.setVisibility(View.GONE);
                    rel_domain_main.setVisibility(View.GONE);
                    rel_recy.setVisibility(View.GONE);
                   tv_nodata.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Domain> call, Throwable t) {

                Log.e("errror", String.valueOf(t));

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_back_dns:

                intent = new Intent(DNS.this, MainHome.class);

                startActivity(intent);
                finish();

                break;
        }
    }
}
