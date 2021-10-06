package com.mobileapp.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobileapp.Fragments.AccessFragment;
import com.mobileapp.Fragments.Graph;
import com.mobileapp.Fragments.Networking;
import com.mobileapp.Fragments.PowerFragment;
import com.mobileapp.Fragments.Recovery;
import com.mobileapp.Fragments.ReinstallOS;
import com.mobileapp.Fragments.Service_Detail;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManageService extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;

    @BindView(R.id.frameAccess)
    FrameLayout frameAccess;

    @BindView(R.id.framePower)
    FrameLayout framePower;


    @BindView(R.id.framegraph)
    FrameLayout framegraph;

    @BindView(R.id.framegraphImg)
    FrameLayout framegraphImg;

    @BindView(R.id.tv_service_detail)
    TextView tv_service_detail;

    @BindView(R.id.tv_graph)
    TextView tv_graph;

    @BindView(R.id.tv_access)
    TextView tv_access;

    @BindView(R.id.tv_power)
    TextView tv_power;

    @BindView(R.id.tv_networking)
    TextView tv_networking;

    @BindView(R.id.tv_reinstall)
    TextView tv_reinstall;

    @BindView(R.id.tv_recovery)
    TextView tv_recovery;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_back_service)
    RelativeLayout rel_back_service;

    Service_Detail service_detail;
    AccessFragment accessFragment;
    PowerFragment powerFragment;
    Networking networking;
    Recovery recovery;
    ReinstallOS reinstallOS;
    Graph graph;

    String token, processor, memory, harddrive, uplink, bandwidth, ipv4, PowerStatus, service_id;
    Intent manageIntent;
    Bundle arguments = new Bundle();

    SharedPreference_VPS sharedPreference_vps;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service);
        ButterKnife.bind(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        manageIntent = getIntent();
        service_id = manageIntent.getStringExtra("Service_Id");
        PowerStatus = arguments.getString("PowerStatus");
        token = sharedPreference_vps.getApi_Key().toString();


        frame.setVisibility(View.VISIBLE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.GONE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(Color.WHITE);
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        service_detail = new Service_Detail();
        arguments.putString("service_id", service_id);
        arguments.putString("PowerStatus", PowerStatus);
        service_detail.setArguments(arguments);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, service_detail).commit();

    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_service_detail)
    public void ServiceDetail(View view) {
        frame.setVisibility(View.VISIBLE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.GONE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(Color.WHITE);
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        service_detail = new Service_Detail();
        arguments.putString("service_id", service_id);
        arguments.putString("PowerStatus", PowerStatus);
        service_detail.setArguments(arguments);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, service_detail).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_access)
    public void Access(View view) {
        frame.setVisibility(View.GONE);
        frameAccess.setVisibility(View.VISIBLE);
        framePower.setVisibility(View.GONE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen_white);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(getColor(R.color.viewcolorText));
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(Color.WHITE);
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        accessFragment = new AccessFragment();
        arguments.putString("Service_Id", service_id);
        accessFragment.setArguments(arguments);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameAccess, accessFragment).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_power)
    public void Power(View view) {
        frame.setVisibility(View.GONE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.VISIBLE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen_white);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(getColor(R.color.viewcolorText));
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(Color.WHITE);
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        powerFragment = new PowerFragment();
        arguments.putString("Service_Id", service_id);
        powerFragment.setArguments(arguments);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framePower, powerFragment).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_networking)
    public void Networking(View view) {
        frame.setVisibility(View.GONE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.VISIBLE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen_white);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(getColor(R.color.viewcolorText));
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(Color.WHITE);
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        networking = new Networking();
        arguments.putString("Service_Id", service_id);
        networking.setArguments(arguments);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framePower, networking).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_recovery)
    public void Recovery(View view) {
        frame.setVisibility(View.GONE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.VISIBLE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen_white);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen);
        tv_service_detail.setTextColor(getColor(R.color.viewcolorText));
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(Color.WHITE);

        recovery = new Recovery();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framePower, recovery).commit();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_reinstall)
    public void Reinstall(View view) {
        frame.setVisibility(View.GONE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.VISIBLE);
        framegraph.setVisibility(View.GONE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen_white);
        tv_graph.setBackgroundResource(R.drawable.login_screen_white);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(getColor(R.color.viewcolorText));
        tv_graph.setTextColor(getColor(R.color.viewcolorText));
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(Color.WHITE);
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        reinstallOS = new ReinstallOS();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framePower, reinstallOS).commit();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.tv_graph)
    public void Graph(View view) {
        frame.setVisibility(View.GONE);
        frameAccess.setVisibility(View.GONE);
        framePower.setVisibility(View.GONE);
        framegraph.setVisibility(View.VISIBLE);
        framegraphImg.setVisibility(View.GONE);
        tv_service_detail.setBackgroundResource(R.drawable.login_screen_white);
        tv_graph.setBackgroundResource(R.drawable.login_screen);
        tv_access.setBackgroundResource(R.drawable.login_screen_white);
        tv_power.setBackgroundResource(R.drawable.login_screen_white);
        tv_networking.setBackgroundResource(R.drawable.login_screen_white);
        tv_reinstall.setBackgroundResource(R.drawable.login_screen_white);
        tv_recovery.setBackgroundResource(R.drawable.login_screen_white);
        tv_service_detail.setTextColor(getColor(R.color.viewcolorText));
        tv_graph.setTextColor(Color.WHITE);
        tv_access.setTextColor(getColor(R.color.viewcolorText));
        tv_power.setTextColor(getColor(R.color.viewcolorText));
        tv_networking.setTextColor(getColor(R.color.viewcolorText));
        tv_reinstall.setTextColor(getColor(R.color.viewcolorText));
        tv_recovery.setTextColor(getColor(R.color.viewcolorText));

        graph = new Graph();
        arguments.putString("service_id", service_id);
        graph.setArguments(arguments);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framegraph, graph).commit();
    }

    @OnClick(R.id.rel_back_service)
    public void Back(View view){
        onBackPressed();
    }

}
