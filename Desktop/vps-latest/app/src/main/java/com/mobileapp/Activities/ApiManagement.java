package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.budgetvm.mobileapp.R;
import com.mobileapp.Adapters.ApiManagement_Adapter;
import com.mobileapp.Interface.MyPostRecyclerViewListener;


import butterknife.BindView;
import butterknife.ButterKnife;

public class ApiManagement extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recycler_api)
    RecyclerView recycler_api;

    @BindView(R.id.rel_back_api)
    RelativeLayout rel_back_api;

    ApiManagement_Adapter dnsAdapter;
    Intent intent;
    GridLayoutManager mGridLayoutManager;
    private MyPostRecyclerViewListener Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_management);

        ButterKnife.bind(this);

        Listener = new MyPostRecyclerViewListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };
        recycler_api.setLayoutManager(new LinearLayoutManager(ApiManagement.this));

        dnsAdapter = new ApiManagement_Adapter(this,Listener);
        recycler_api.setAdapter(dnsAdapter);

        rel_back_api.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.rel_back_api:

                intent = new Intent(ApiManagement.this, MainHome.class);
                startActivity(intent);
                finish();

                break;
        }

    }
}
