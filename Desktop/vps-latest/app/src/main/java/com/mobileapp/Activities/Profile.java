package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobileapp.Adapters.Authorized_Adapter;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.OnItemClickListener;
import com.mobileapp.POJO.Auth_Model;
import com.mobileapp.POJO.Auth_Result;
import com.mobileapp.POJO.Auth_del;
import com.mobileapp.POJO.Profile_Model;
import com.mobileapp.POJO.Profile_Result;
import com.mobileapp.POJO.UpdateProfile_Model;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class Profile extends AppCompatActivity implements View.OnClickListener , OnItemClickListener {

    @BindView(R.id.rel_back_profile)
    ConstraintLayout rel_back_profile;

    @BindView(R.id.progress_bar)
    ConstraintLayout progress_bar;

    @BindView(R.id.nested_profile_)
    NestedScrollView  nested_profile_;

    @BindView(R.id.profile_recyclerView)
    RecyclerView profile_recyclerView;

    @BindView(R.id.iv_EditProfile)
    ImageView iv_EditProfile;

    @BindView(R.id.iv_authorized)
    ImageView iv_authorized;

    @BindView(R.id.tv_firstname)
    EditText tv_firstname;

    @BindView(R.id.tv_lastname)
    EditText tv_lastname;

    @BindView(R.id.tv_emailProfile)
    EditText tv_emailProfile;

    @BindView(R.id.tv_phonenumber)
    EditText tv_phonenumber;

    @BindView(R.id.tv_pinNumber)
    EditText tv_pinNumber;

    @BindView(R.id.tv_signout)
    ImageView tv_signout;

    private int clickedPosition = 0;

 /*   @BindView(R.id.tv_company)
    EditText tv_company;

    @BindView(R.id.tv_address1)
    EditText tv_address1;

    @BindView(R.id.tv_address2)
    EditText tv_address2;

    @BindView(R.id.tv_country)
    EditText tv_country;

    @BindView(R.id.tv_state)
    EditText tv_state;

    @BindView(R.id.tv_city)
    EditText tv_city;

    @BindView(R.id.tv_zip)
    EditText tv_zip;*/

    @BindView(R.id. btn_updateinfo)
    Button btn_updateinfo;


    SharedPreference_VPS sharedPreference_vps;
    String token, id, firstname, lastname, email, phonenumber, postcode,companyname, address1, address2, country, state, city;

    Intent profile_intent;

    Authorized_Adapter authorized_adapter;

    //Get From API
    Profile_Result list;

    String ID ;

    //for Auth User
    ArrayList<Auth_Result> list2;


    @Override
    protected void onStart() {
        super.onStart();

        AuthServices(token);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
        rel_back_profile.setOnClickListener(this);
        iv_authorized.setOnClickListener(this);
        iv_EditProfile.setOnClickListener(this);
        btn_updateinfo.setOnClickListener(this);
        tv_signout.setOnClickListener(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        profile_recyclerView.setLayoutManager(layoutManager);

        progress_bar.setVisibility(View.VISIBLE);

        showServices(token);

        ProfileApi();
    }

    private void ProfileApi() {

        btn_updateinfo.setVisibility(View.GONE);

        tv_firstname.setEnabled(false);
        tv_lastname.setEnabled(false);
        tv_emailProfile.setEnabled(false);
        tv_phonenumber.setEnabled(false);
        tv_pinNumber.setEnabled(false);

       /* tv_company.setEnabled(false);
        tv_address1.setEnabled(false);
        tv_address2.setEnabled(false);
        tv_country.setEnabled(false);
        tv_state.setEnabled(false);
        tv_city.setEnabled(false);
        tv_zip.setEnabled(false);*/
    }


    Profile_Model profile_model = new Profile_Model();

    private void showServices(String token) {


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<Profile_Model> call = apiService.profilelist(token);

        call.enqueue(new retrofit2.Callback<Profile_Model>() {

            @Override
            public void onResponse(Call<Profile_Model> call, Response<Profile_Model> response) {

                progress_bar.setVisibility(View.GONE);

                nested_profile_.setVisibility(View.VISIBLE);

                profile_model = response.body();

                if (response.isSuccessful()) {
                    String data = "";

                    list =  profile_model.getResult();

                    tv_firstname.setText(list.getFirstname());
                    tv_lastname.setText(list.getLastname());
                    tv_emailProfile.setText(list.getEmail());
                    tv_phonenumber.setText(list.getPhonenumber());
                    tv_pinNumber.setText(list.getPostcode());

                    ID = list.getId(); //for id this use in Update Account

                    //second View
                   /* tv_company.setText(list.getCompanyname());
                    tv_address1.setText(list.getAddress1());
                    tv_address2.setText(list.getAddress2());
                    tv_country.setText(list.getCountry());
                    tv_state.setText(list.getState());
                    tv_city.setText(list.getCity());
                    tv_zip.setText(list.getPostcode());*/


                } else {
//                    Toast.makeText(Profile.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Profile_Model> call, Throwable t) {

                progress_bar.setVisibility(View.GONE);

                Toast.makeText(Profile.this,"onFailure",Toast.LENGTH_SHORT).show();

                Log.e("Error",t.getMessage().toString());
            }
        });
    }

    Auth_Model auth_model = new Auth_Model();

    public void AuthServices(String token) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<Auth_Model> call = apiService.Authlist(token);

        call.enqueue(new retrofit2.Callback<Auth_Model>() {
            @Override
            public void onResponse(Call<Auth_Model> call, Response<Auth_Model> response) {

                auth_model = response.body();

                if (response.isSuccessful()) {

                    String data = "";

                    list2 = (ArrayList<Auth_Result>) auth_model.getResult();

                    authorized_adapter = new Authorized_Adapter(Profile.this,list2 ,Profile.this::onItemClick);
                    profile_recyclerView.setAdapter(authorized_adapter);

                } else {
//                    Toast.makeText(Profile.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Auth_Model> call, Throwable t) {
                Toast.makeText(Profile.this,"onFailure",Toast.LENGTH_SHORT).show();
                Log.e("Error",t.getMessage().toString());
            }
        });
    }


    private void UpdateProfile(String token, String id, String firstname, String lastname, String email, String phonenumber, String postcode) {

        progress_bar.setVisibility(View.VISIBLE);
        nested_profile_.setVisibility(View.GONE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<UpdateProfile_Model> call = apiService.UpdateProfile(token,id,firstname,lastname,email,phonenumber,postcode);
        call.enqueue(new retrofit2.Callback<UpdateProfile_Model>() {
            @Override
            public void onResponse(Call<UpdateProfile_Model> call, Response<UpdateProfile_Model> response) {

                progress_bar.setVisibility(View.GONE);
                nested_profile_.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {
                    Toast.makeText(Profile.this,"Successfully Update",Toast.LENGTH_SHORT).show();
                    ProfileApi();
                }
                else {
                    Toast.makeText(Profile.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UpdateProfile_Model> call, Throwable t) {

                Log.e("errror", String.valueOf(t));
                Toast.makeText(Profile.this,"onFailure",Toast.LENGTH_SHORT).show();
                /* simpleProgressBarr.setVisibility(View.GONE);*/
            }
        });
    }



    private void delete(String token, String id_user) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<Auth_del> call = apiService.delete(token,id_user);

        call.enqueue(new retrofit2.Callback<Auth_del>() {

            @Override
            public void onResponse(Call<Auth_del> call, Response<Auth_del> response) {

//                progress_bar.setVisibility(View.GONE);
//                nested_profile_.setVisibility(View.VISIBLE);
//                profile_model = response.body();

                if (response.isSuccessful()) {
                    String data = "";
                    Toast.makeText(Profile.this,"successfully Deleted",Toast.LENGTH_SHORT).show();

                    authorized_adapter.deleteItemList(clickedPosition);

                } else {
//                    Toast.makeText(Profile.this,"error",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Auth_del> call, Throwable t) {

//                progress_bar.setVisibility(View.GONE);

                Toast.makeText(Profile.this,"onFailure",Toast.LENGTH_SHORT).show();

                Log.e("Error",t.getMessage().toString());
            }
        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rel_back_profile:
                profile_intent = new Intent(Profile.this, MainHome.class);
                startActivity(profile_intent);
                break;

            case R.id.tv_signout:
                profile_intent = new Intent(Profile.this, Login.class);
                profile_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                this.startActivity(profile_intent);
                sharedPreference_vps.setEmail("");
                sharedPreference_vps.setPassword("");
                finish();
                break;

            case R.id.iv_authorized:
                profile_intent = new Intent(Profile.this, Authorized_User.class);
                startActivity(profile_intent);
                break;


            case R.id.btn_updateinfo:
                id = ID;

                firstname = tv_firstname.getText().toString();
                lastname = tv_lastname.getText().toString();
                email = tv_emailProfile.getText().toString();
                phonenumber = tv_phonenumber.getText().toString();
                postcode = tv_pinNumber.getText().toString();

                UpdateProfile(token,id,firstname,lastname,email,phonenumber,postcode);


            case R.id.iv_EditProfile: {
                btn_updateinfo.setVisibility(View.VISIBLE);

                tv_firstname.setEnabled(true);
                tv_lastname.setEnabled(true);
                tv_emailProfile.setEnabled(true);
                tv_phonenumber.setEnabled(true);
                tv_pinNumber.setEnabled(true);

               /* tv_company.setEnabled(true);
                tv_address1.setEnabled(true);
                tv_address2.setEnabled(true);
                tv_country.setEnabled(true);
                tv_state.setEnabled(true);
                tv_city.setEnabled(true);
                tv_zip.setEnabled(true);*/
            }

        }
    }


    @Override
    public void onItemClick(View view, int position, Object... objects) {
        Auth_Result result = null;
         if (view.getId() == R.id.dell){
             if (objects[0] instanceof Auth_Result){
                  result = (Auth_Result) objects[0];
               String  id_user = result.getId();

                 clickedPosition = position;
                 delete(token , id_user);
             }
         } else if (view.getId() == R.id.iv_edit){
             if (objects[0] instanceof Auth_Result){
                 String selectedResult = null;
                  result = (Auth_Result) objects[0];
                 try {
                     selectedResult = new Gson().toJson(result);
                 } catch (Exception e){

                 }
                  Intent intent = new Intent(this , EditAuth.class);
                 intent.putExtra("Selected_Result" , selectedResult);
                 startActivity(intent);
             }
         }
    }
}