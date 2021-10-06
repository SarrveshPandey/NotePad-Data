 package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.budgetvm.mobileapp.R;
import com.mobileapp.Adapters.ServiceAdapter;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.mobileapp.Adapters.BillingAdapter;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.POJO.BillingModal;
import com.mobileapp.POJO.BillingResult;

import com.mobileapp.Utils.SharedPreference_VPS;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class Billing extends AppCompatActivity implements View.OnClickListener  {

    @BindView(R.id.rel_back_billing)
    ConstraintLayout rel_back_billing;

    @BindView(R.id.recycler_billing)
    RecyclerView recycler_billing;

    @BindView(R.id.no_record_found_tv_billing)
    TextView no_record_found_tv_billing;

    @BindView(R.id.clickBtn)
    Button clickBtn;

    @BindView(R.id.et_search_billing)
    EditText et_search_billing;

    @BindView(R.id.claer_search_billig)
    ImageView claer_search_billig;

    BillingAdapter billingAdapter;

    SharedPreference_VPS sharedPreference_vps;

    String token;

    Intent billing_intent;

    ArrayList<BillingResult> list = new ArrayList<>();

    ArrayList<BillingResult> list_paid_2 = new ArrayList<>();
    ArrayList<BillingResult> list_pa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        ButterKnife.bind(this);

        rel_back_billing.setOnClickListener(this);
        clickBtn.setOnClickListener(this);
        claer_search_billig.setOnClickListener(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycler_billing.setLayoutManager(layoutManager);

        showServices(token);

//for search bar code--
        et_search_billing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {

                list_paid_2.clear();
                list_pa = (ArrayList<BillingResult>) billingModal.getResult();

                for (int i =0;i<list_pa.size();i++){

                    if (list_pa.get(i).getId().contains(p0.toString())){
                        list_paid_2.add(list_pa.get(i));
                        no_record_found_tv_billing.setVisibility(View.GONE);
                    }

                    else if (list_paid_2.isEmpty() ) {
                        no_record_found_tv_billing.setVisibility(View.VISIBLE);
                    }

                    billingAdapter = new BillingAdapter(Billing.this,list_paid_2);
                    recycler_billing.setAdapter(billingAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }



    BillingModal billingModal = new BillingModal();

    private void showServices(String token) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<BillingModal> call = apiService.billinglist(token);

        call.enqueue(new retrofit2.Callback<BillingModal>() {
            @Override
            public void onResponse(Call<BillingModal> call, Response<BillingModal> response) {

                    billingModal = response.body();
                    if (response.isSuccessful()) {

                        ArrayList<BillingResult> list_paid_1 = new ArrayList<>();

                        String data = "";

                        list = (ArrayList<BillingResult>) billingModal.getResult();

                        for(int i =0;i<list.size();i++) {

                            String unpaid = list.get(i).getStatus();

                            if (unpaid.equals("Unpaid")) {

                                list_paid_1.add(list.get(i));
                            }
                            billingAdapter = new BillingAdapter(Billing.this, list_paid_1);
                            recycler_billing.setAdapter(billingAdapter);
                        }


                    } else {
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Billing.this, SweetAlertDialog.ERROR_TYPE);
                        Toast.makeText(Billing.this,"error",Toast.LENGTH_SHORT).show();
                        sweetAlertDialog.setTitleText("Wait");
                        sweetAlertDialog.show();
                    }
            }

            @Override
            public void onFailure(Call<BillingModal> call, Throwable t) {
                Toast.makeText(Billing.this,"onFailure",Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getMessage().toString());
            }

        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_back_billing:

                billing_intent = new Intent(Billing.this, MainHome.class);
                startActivity(billing_intent);
                break;

            case R.id.claer_search_billig:
                et_search_billing.setText(null);
                break;

            case R.id.clickBtn:

                PopupMenu popupMenu = new PopupMenu(Billing.this, clickBtn);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        ArrayList<BillingResult> list_paid = new ArrayList<>();

                        String status = menuItem.toString();

                        for(int i =0;i<list.size();i++){

                            if (status.equals(list.get(i).getStatus())) {

                                list_paid.add(list.get(i));
                                popupMenu.dismiss();
                            }

                            else if ((status.equals(list.get(i).getStatus()))){

                                list_paid.add(list.get(i));
                                popupMenu.dismiss();
                            }

                            else if (status.equals(list.get(i).getStatus())){

                                list_paid.add(list.get(i));

                                popupMenu.dismiss();
                            }

                            else if ((status.equals(list.get(i).getStatus()))){

                                list_paid.add(list.get(i));

                                popupMenu.dismiss();
                            }

                            else if (status.equals("All")){

                                list_paid.add(list.get(i));

                                popupMenu.dismiss();
                            }
                            billingAdapter = new BillingAdapter(Billing.this,list_paid);
                            recycler_billing.setAdapter(billingAdapter);
                        }

                        return true;
                    }
                });
                popupMenu.show();


        }
    }


}