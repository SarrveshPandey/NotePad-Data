package com.mobileapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mobileapp.Adapters.BillingAdapter;
import com.mobileapp.Adapters.Cardview;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.mobileapp.POJO.BillingModal;
import com.mobileapp.POJO.BillingResult;
import com.mobileapp.POJO.TicketListPojo;
import com.mobileapp.POJO.Ticket_Result;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.Global_Constants;
import com.mobileapp.Utils.SharedPreference_VPS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class Home extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_home_top)
    LinearLayout ll_home_top;

    @BindView(R.id.rel_tickets)
    RelativeLayout rel_tickets;

    @BindView(R.id.rel_mydevices)
    RelativeLayout rel_mydevices;

    @BindView(R.id.rel_create_ticket)
    RelativeLayout rel_create_ticket;

    @BindView(R.id.rel_home)
    RelativeLayout rel_home;

    @BindView(R.id.rel_maindns)
    RelativeLayout rel_maindns;

    @BindView(R.id.tv_tickets)
    TextView tv_tickets;

    @BindView(R.id.tv_mydevices)
    TextView tv_mydevices;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    @BindView(R.id.et_search_ticket)
    EditText et_search_ticket;

    @BindView(R.id.claer_search_ticket2)
    ImageView claer_search_ticket2;

    @BindView(R.id.no_data)
    TextView no_data;

    @BindView(R.id.idRecyclerView)
    RecyclerView idRecyclerView;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_back_tickets)
    RelativeLayout rel_back_tickets;


    List<Ticket_Result> ticketResult = new ArrayList<>();

    ArrayList<Ticket_Result> list_paid = new ArrayList<>();

    private MyPostRecyclerViewListener Listener;

    Cardview cardview;

    Intent intent;

    SharedPreference_VPS sharedPreference_vps;

    String token,department,last_replier,last_updated,ticket_status,customer_name,ticket_id;

    Global_Constants global_constants;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        global_constants = new Global_Constants();

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();

        Listener = new MyPostRecyclerViewListener() {
            @Override
            public void onClick(View view, int position) {

                ticket_id = ticketResult.get(position).getTicketmaskid().toString();
                String id = ticketResult.get(position).getTicketid().toString();

                department = ticketResult.get(position).getDepartmenttitle().toString();
                last_replier = ticketResult.get(position).getLastReplyType().toString();
//                last_updated = ticketResult.get(position).get;
                ticket_status = ticketResult.get(position).getTicketstatustitle();
                customer_name = ticketResult.get(position).getLastreplier().toString();


                intent = new Intent(Home.this,View_Ticket.class);
                intent.putExtra("TicketId",ticket_id);
                intent.putExtra("TicketId2",id);
                intent.putExtra("department",department);
                intent.putExtra("last_replier",last_replier);
                intent.putExtra("ticket_status",ticket_status);
                intent.putExtra("customer_name",customer_name);
                intent.putExtra("Tag","");
                startActivity(intent);
//                finish();
            }
        };
        idRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setfont();
        rel_home.setVisibility(View.VISIBLE);
        rel_maindns.setVisibility(View.GONE);
        rel_create_ticket.setVisibility(View.VISIBLE);
        ll_home_top.setBackgroundResource(R.drawable.round_buttonshape);
        rel_tickets.setBackgroundResource(R.drawable.filled_round_button);
        tv_tickets.setTextColor(getColor(R.color.colorPrimary));
        rel_mydevices.setBackgroundResource(R.drawable.round_buttonshape);
        tv_mydevices.setTextColor(getColor(R.color.white));

        cardview = new Cardview(this,ticketResult,Listener);
        idRecyclerView.setAdapter(cardview);

        rel_back_tickets.setOnClickListener(this);

//        final Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 100);

        showTickets(token);
        rel_tickets.setOnClickListener(this);
        rel_mydevices.setOnClickListener(this);
        rel_create_ticket.setOnClickListener(this);
        claer_search_ticket2.setOnClickListener(this);


        et_search_ticket.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {

                ticketResult.clear();

                for (int i =0;i<list_paid.size();i++){

                    if (list_paid.get(i).getTicketmaskid().contains(p0.toString())){
                        ticketResult.add(list_paid.get(i));
                        no_data.setVisibility(View.GONE);

                    } else if (ticketResult.isEmpty() ) {
                        no_data.setVisibility(View.VISIBLE);
                    }
                    cardview = new Cardview(Home.this, ticketResult,Listener);
                    idRecyclerView.setAdapter(cardview);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        showTickets(token);
//    }

    private void setfont() {

        toolbar_title.setTypeface(global_constants.montserrat_light(this));
        tv_tickets.setTypeface(global_constants.montserrat_light(this));
        tv_mydevices.setTypeface(global_constants.montserrat_light(this));
    }

    private void showTickets(String token) {
        simpleProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<TicketListPojo> call = apiService.ticketList(token);
        call.enqueue(new retrofit2.Callback<TicketListPojo>() {
            @Override
            public void onResponse(Call<TicketListPojo> call, Response<TicketListPojo> response) {

                Log.e("Login url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("Login response", new Gson().toJson(response));
                    if (response.body().getSuccess()==true) {
                        Log.e("Login response", new Gson().toJson(response));

                      ticketResult.addAll(response.body().getResult());
                        list_paid.addAll(response.body().getResult());
                        cardview.notifyDataSetChanged();
                        simpleProgressBar.setVisibility(View.GONE);
                    }
                }

                else {
                    simpleProgressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TicketListPojo> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);

                Log.e("errror", String.valueOf(t));
            }
        });


    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rel_tickets:
                rel_home.setVisibility(View.VISIBLE);
                rel_maindns.setVisibility(View.GONE);
                rel_create_ticket.setVisibility(View.VISIBLE);
                ll_home_top.setBackgroundResource(R.drawable.round_buttonshape);
                rel_tickets.setBackgroundResource(R.drawable.filled_round_button);
                tv_tickets.setTextColor(getColor(R.color.colorPrimary));
                rel_mydevices.setBackgroundResource(R.drawable.round_buttonshape);
                tv_mydevices.setTextColor(getColor(R.color.white));

//                showTickets(token);
                break;

            case R.id.rel_mydevices:

                rel_home.setVisibility(View.GONE);
                rel_maindns.setVisibility(View.VISIBLE);
                rel_create_ticket.setVisibility(View.GONE);
                ll_home_top.setBackgroundResource(R.drawable.round_buttonshape);
                rel_tickets.setBackgroundResource(R.drawable.round_buttonshape);
                tv_tickets.setTextColor(getColor(R.color.white));
                rel_mydevices.setBackgroundColor(getColor(R.color.white));
                tv_mydevices.setTextColor(getColor(R.color.colorPrimary));
                break;

            case R.id.rel_create_ticket:
                intent = new Intent(Home.this, CreateTicket.class);
                startActivity(intent);
                break;


            case R.id.rel_back_tickets:
                intent = new Intent(Home.this, MainHome.class);
                startActivity(intent);
                finish();
                break;

            case R.id.claer_search_ticket2:
                et_search_ticket.setText(null);
                break;
        }
    }
}
