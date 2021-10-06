package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.Adapters.ServiceAdapter;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.mobileapp.POJO.PowerPojo;
import com.mobileapp.POJO.ServerListResult;
import com.mobileapp.POJO.ServiceList_POJO;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Services extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.recycler_service)
    RecyclerView recycler_service;

    @BindView(R.id.no_record_found_tv)
    TextView no_record_found_tv;

    @BindView(R.id.rel_back_service)
    RelativeLayout rel_back_service;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.tv_power)
    TextView tv_power;

    @BindView(R.id.tv_servicee)
    TextView tv_servicee;

    @BindView(R.id.tv_hostname)
    TextView tv_hostname;

    @BindView(R.id.tv_mainIp)
    TextView tv_mainIp;

    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.claer_search)
    ImageView claer_search;


    GridLayoutManager mGridLayoutManager;
    private MyPostRecyclerViewListener Listener;

    ServiceAdapter serviceAdapter;

    SharedPreference_VPS sharedPreference_vps;

    String token;

    Intent service_intent;

    int service_id;

    List<ServerListResult> serverListResults = new ArrayList<>();

    ArrayList<String> IDD = new ArrayList<>();
    ArrayList<String> hostnamee = new ArrayList<>();
    ArrayList<String> MainIPP = new ArrayList<>();
    ArrayList<String> power_status = new ArrayList<>();

    List<ServiceList_POJO> service_list = new ArrayList<>();
    List<ServiceList_POJO> Search_service_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        ButterKnife.bind(this);
        claer_search.setOnClickListener(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();

        Listener = new MyPostRecyclerViewListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };
        recycler_service.setLayoutManager(new LinearLayoutManager(Services.this));

        rel_back_service.setOnClickListener(this);


        showServices(token);


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {

                Search_service_list.clear();

               for (int i =0;i<service_list.size();i++){

                   if (service_list.get(i).getID().contains(p0.toString())){
                      Search_service_list.add(service_list.get(i));
                       no_record_found_tv.setVisibility(View.GONE);
                   }

                   else if (Search_service_list.isEmpty() ) {
                       no_record_found_tv.setVisibility(View.VISIBLE);
                   }

                   serviceAdapter = new ServiceAdapter(Services.this, Listener, IDD, hostnamee, MainIPP, Search_service_list);
                   recycler_service.setAdapter(serviceAdapter);
               }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


    }


    private void showServices(String token) {

        simpleProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.DeviveList(token);
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("Services url", call.request().toString());
                try {

                    if (response.isSuccessful()) {

                        String data = "";
                        data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonResult = jsonObject.getJSONArray("result");

                        for (int i = 0; i < jsonResult.length(); i++) {
                            try {
                                if (jsonResult.getJSONObject(i).getString("id") != null) {

                                    ServiceList_POJO serviceList_pojo = new ServiceList_POJO();

                                    serviceList_pojo.setID(jsonResult.getJSONObject(i).getString("id"));
                                    serviceList_pojo.setHostname(jsonResult.getJSONObject(i).getString("domain"));
                                    serviceList_pojo.setMainIPP(jsonResult.getJSONObject(i).getJSONObject("mainip").getString("result"));

                                    service_list.add(serviceList_pojo);
                                }

                            } catch (Exception e) {

                            }
                        }

                        for (int i = 0; i < service_list.size(); i++) {

//                            service_id = ;
                            getPowerstatus(i, Integer.parseInt(service_list.get(i).getID()), token);
                        }

                    } else {
                       /* SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Services.this, SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Wait");
                        sweetAlertDialog.show();*/
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(Services.this,"fail",Toast.LENGTH_SHORT).show();

                simpleProgressBar.setVisibility(View.GONE);

                Log.e("errror", String.valueOf(t));
            }
        });
    }

    private void getPowerstatus(int i, int id, String token) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<PowerPojo> call = apiService.getPowerStatus(token, id);
        Log.e("getPowerstatus url....", call.request().toString());
        call.enqueue(new retrofit2.Callback<PowerPojo>() {
            @Override
            public void onResponse(Call<PowerPojo> call, Response<PowerPojo> response) {

                Log.e("PowerStatus url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("PowerStatus response", new Gson().toJson(response));
                    if (response.body().getSuccess() == true) {
                        Log.e("PowerStatus response", new Gson().toJson(response));

                        serviceAdapter = new ServiceAdapter(Services.this, Listener, IDD, hostnamee, MainIPP, service_list);
                        recycler_service.setAdapter(serviceAdapter);

                        simpleProgressBar.setVisibility(View.GONE);

                        service_list.get(i).setPower(response.body().getResult());
                        Log.e("PowerStatus Value....", response.body().getResult().toString());
                        if (i == service_list.size() - 1) {

                            /*simpleProgressBar.setVisibility(View.GONE);*/

                            serviceAdapter = new ServiceAdapter(Services.this, Listener, IDD, hostnamee, MainIPP, service_list);
                            recycler_service.setAdapter(serviceAdapter);

                            tv_power.setVisibility(View.VISIBLE);
                            tv_hostname.setVisibility(View.VISIBLE);
                            tv_mainIp.setVisibility(View.VISIBLE);
                            tv_servicee.setVisibility(View.VISIBLE);
                        }
                    }

                } else {
                  /*  SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Services.this, SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Wait");
                    sweetAlertDialog.show();*/
                }
            }

            @Override
            public void onFailure(Call<PowerPojo> call, Throwable t) {

                Log.e("errror", String.valueOf(t));

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rel_back_service:
                service_intent = new Intent(Services.this, MainHome.class);
                startActivity(service_intent);
                break;

            case R.id.claer_search:
                et_search.setText("");
        }
    }


}
