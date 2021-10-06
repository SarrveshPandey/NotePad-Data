package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.budgetvm.mobileapp.R;
import com.google.gson.Gson;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.POJO.Auth_Result;
import com.mobileapp.POJO.UpdateProfile_Model;
import com.mobileapp.POJO.Update_account;
import com.mobileapp.Utils.SharedPreference_VPS;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class EditAuth extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.rel_back_authorized_user2)
    ConstraintLayout rel_back_authorized_user2;

    @BindView(R.id.progress_barr2)
    ConstraintLayout progress_barr2;

    @BindView(R.id.full_view5)
    ScrollView full_view5;

    @BindView(R.id.button_cancel2)
    Button button_cancel2;

    @BindView(R.id.button_updateAccount)
    Button button_updateAccount;

    @BindView(R.id.et_fn2)
    EditText et_fn2;

    @BindView(R.id.et_ln2)
    EditText et_ln2;

    @BindView(R.id.et_company2)
    EditText et_company2;

    @BindView(R.id.et_mail2)
    EditText et_mail2;

    @BindView(R.id.et_phn2)
    EditText et_phn2;

    @BindView(R.id.et_pin2)
    EditText et_pin2;

    @BindView(R.id.et_address12)
    EditText et_address12;

    @BindView(R.id.et_address22)
    EditText et_address22;

    @BindView(R.id.et_state2)
    EditText et_state2;

    @BindView(R.id.et_country2)
    EditText et_country2;

    @BindView(R.id.et_city2)
    EditText et_city2;

    Intent auth_intent;
    SharedPreference_VPS sharedPreference_vps2;
    String token, idd,id, firstname, lastname, email ,phonenumber;

    Auth_Result result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_auth);
        ButterKnife.bind(this);
        button_cancel2.setOnClickListener(this);
       rel_back_authorized_user2.setOnClickListener(this);
        button_updateAccount.setOnClickListener(this);

        sharedPreference_vps2 = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps2.getApi_Key().toString();

       String selectedResult = getIntent().getStringExtra("Selected_Result");
       if (!selectedResult.isEmpty() && selectedResult != null){
           try {
               result = new Gson().fromJson(selectedResult , Auth_Result.class);
           } catch (Exception e){

           }
       }
       setData();
    }

    private void setData() {
        if (result != null){
            idd = result.getId();
            et_fn2.setText(result.getFirstname());
            et_ln2.setText(result.getLastname());
            et_company2.setText(result.getCompanyname());
            et_mail2.setText(result.getEmail());
            et_phn2.setText(result.getPhonenumber());
            et_pin2.setText(result.getPostcode());
            et_address12.setText(result.getAddress1());
            et_address22.setText(result.getAddress2());
            et_state2.setText(result.getState());
            et_country2.setText(result.getCountry());
            et_city2.setText(result.getCity());
        }
    }


    private void updateAccount(String token, String id, String firstname, String lastname, String email) {
          Log.e("update" ,  "" + id+" " + firstname + " " + lastname + " " + email);
        progress_barr2.setVisibility(View.VISIBLE);
        full_view5.setVisibility(View.GONE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<Update_account> call = apiService.update_info(token,id,firstname,lastname,email);
        call.enqueue(new retrofit2.Callback<Update_account>() {
            @Override
            public void onResponse(Call<Update_account> call, Response<Update_account> response) {

                progress_barr2.setVisibility(View.GONE);
                full_view5.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {
                    Toast.makeText(EditAuth.this,"Successfully Update",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditAuth.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Update_account> call, Throwable t) {

                Log.e("errror", String.valueOf(t));
                Toast.makeText(EditAuth.this,"onFailure",Toast.LENGTH_SHORT).show();
                /* simpleProgressBarr.setVisibility(View.GONE);*/
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_back_authorized_user2:
                auth_intent = new Intent(EditAuth.this, Profile.class);
                startActivity(auth_intent);
                break;

            case R.id.button_cancel2:
                auth_intent = new Intent(EditAuth.this, Profile.class);
                startActivity(auth_intent);
                break;

            case R.id.button_updateAccount:

                id = idd;
                firstname = et_fn2.getText().toString();
                lastname = et_ln2.getText().toString();
                email =  et_mail2.getText().toString();
                updateAccount(token,id,firstname,lastname,email);

                break;
        }
    }


}