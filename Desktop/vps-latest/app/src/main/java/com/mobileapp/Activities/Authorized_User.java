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
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.POJO.CreateAuthorized_Model;

import com.mobileapp.Utils.SharedPreference_VPS;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class  Authorized_User extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.rel_back_authorized_user)
    ConstraintLayout rel_back_authorized_user;

    @BindView(R.id.progress_barr)
    ConstraintLayout progress_barr;

    @BindView(R.id.full_view)
    ScrollView full_view;

    @BindView(R.id.button_cancel)
    Button button_cancel;

    @BindView(R.id.button_createAccount)
    Button button_createAccount;

    @BindView(R.id.et_fn)
    EditText et_fn;

    @BindView(R.id.et_ln)
    EditText et_ln;

    @BindView(R.id.et_company)
    EditText et_company;

    @BindView(R.id.et_mail)
    EditText et_mail;

    @BindView(R.id.et_phn)
    EditText et_phn;

    @BindView(R.id.et_pin)
    EditText et_pin;

    @BindView(R.id.et_pswd)
    EditText et_pswd;

    @BindView(R.id.et_confirmpswd)
    EditText et_confirmpswd;

    @BindView(R.id.et_address1)
    EditText et_address1;

    @BindView(R.id.et_address2)
    EditText et_address2;

    @BindView(R.id.et_state)
    EditText et_state;

    @BindView(R.id.et_country)
    EditText et_country;

    @BindView(R.id.et_city)
    EditText et_city;

/*    @BindView(R.id.checkbox_masterProfile)
    CheckBox checkbox_masterProfile;

    @BindView(R.id.checkbox_ManageContact)
    CheckBox checkbox_ManageContact;

    @BindView(R.id.checkBox_ProductServices)
    CheckBox checkBox_ProductServices;

    @BindView(R.id.checkBox_ModifyProduct)
    CheckBox checkBox_ModifyProduct;

    @BindView(R.id.checkBox_SingleSignOn)
    CheckBox checkBox_SingleSignOn;

    @BindView(R.id.checkBox_ViewDomains)
    CheckBox checkBox_ViewDomains;

    @BindView(R.id.checkBox_ManageDomains)
    CheckBox checkBox_ManageDomains;

    @BindView(R.id.checkBox_ViewPay)
    CheckBox checkBox_ViewPay;

    @BindView(R.id.checkBox_ViewAccept)
    CheckBox checkBox_ViewAccept;

    @BindView(R.id.checkBox_ViewSupport)
    CheckBox checkBox_ViewSupport;

    @BindView(R.id.checkBox_ViewManage)
    CheckBox checkBox_ViewManage;

    @BindView(R.id.checkBox_ViewEmail)
    CheckBox checkBox_ViewEmail;

    @BindView(R.id.checkBox_Upgrades)
    CheckBox checkBox_Upgrades;

    //Second  Check Screen

    @BindView(R.id.checkBox_General)
    CheckBox checkBox_General;

    @BindView(R.id.checkBox_ProductEmail)
    CheckBox checkBox_ProductEmail;

    @BindView(R.id.checkBox_InvoiceEmail)
    CheckBox checkBox_InvoiceEmail;

    @BindView(R.id.checkBox_SupportEmails)
    CheckBox checkBox_SupportEmails;*/

    SharedPreference_VPS sharedPreference_vps;
    String token;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Intent auth_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorized__user);

        ButterKnife.bind(this);
        button_cancel.setOnClickListener(this);
        button_createAccount.setOnClickListener(this);
        rel_back_authorized_user.setOnClickListener(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();
    }

    private void callCreateapi(String token, String firstname, String lastname, String company, String email, String phone, String zipcode, String password, String address1, String address2, String state, String country, String city) {

        progress_barr.setVisibility(View.VISIBLE);
        full_view.setVisibility(View.GONE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<CreateAuthorized_Model> call = apiService.createNew(token, firstname, lastname, company, email, phone, zipcode, password,  address1, address2, state, country, city);
        call.enqueue(new retrofit2.Callback<CreateAuthorized_Model>() {
            @Override
            public void onResponse(Call<CreateAuthorized_Model> call, Response<CreateAuthorized_Model> response) {

                progress_barr.setVisibility(View.GONE);
                full_view.setVisibility(View.VISIBLE);

                if (response.isSuccessful()) {

                    Toast.makeText(Authorized_User.this,"Successfully Account Create",Toast.LENGTH_SHORT).show();

                    }
                else {
                    Toast.makeText(Authorized_User.this,"Please check all the enter field again",Toast.LENGTH_SHORT).show();
                    /*SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(Authorized_User.this, SweetAlertDialog.ERROR_TYPE);
                    sweetAlertDialog.setTitleText("Please Enter Valid Details");
                    sweetAlertDialog.show();*/
                }

                }
            @Override
            public void onFailure(Call<CreateAuthorized_Model> call, Throwable t) {

                Log.e("errror", String.valueOf(t));
                Toast.makeText(Authorized_User.this,"onFailure",Toast.LENGTH_SHORT).show();
               /* simpleProgressBarr.setVisibility(View.GONE);*/
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_cancel:
                auth_intent = new Intent(Authorized_User.this, Profile.class);
                startActivity(auth_intent);
                finish();
                break;

            case R.id.button_createAccount:
                Validations();
                break;


            case R.id.rel_back_authorized_user:
                auth_intent = new Intent(Authorized_User.this, Profile.class);
                startActivity(auth_intent);
                break;
        }
    }


    private void Validations() {

        if (et_fn.getText().toString().isEmpty()){
           et_fn.setError("Please enter first name");
        }

        else if (et_ln.getText().toString().isEmpty()){
            et_ln.setError("Please enter last name");
        }
        else if (et_company.getText().toString().isEmpty()){
            et_company.setError("Please enter company name");
        }

        else if (et_mail.getText().toString().isEmpty()) {
            et_mail.setError("Please enter mail id");
        }
        else if (!et_mail.getText().toString().trim().matches(emailPattern)) {
            Toast.makeText(getApplicationContext(),"Enter valid email address",Toast.LENGTH_SHORT).show();
        }


        else if (et_phn.getText().toString().isEmpty()){
            et_phn.setError("Please enter phone number");
        }
        else if (et_pin.getText().toString().isEmpty()){
            et_pin.setError("Please enter pin");
        }
        else if (et_pswd.getText().toString().isEmpty()){
            et_pswd.setError("Please enter password");
        }
        else if (et_confirmpswd.getText().toString().isEmpty()){
            et_confirmpswd.setError("Please enter confirm password");
        }
        else if(!et_pswd.getText().toString().equals(et_confirmpswd.getText().toString())){
            et_confirmpswd.setError("Password does not matched");
        }
        else if (et_address1.getText().toString().isEmpty()){
            et_address1.setError("Please enter adress1");
        }
        else if (et_address2.getText().toString().isEmpty()){
            et_address2.setError("Please enter address2");
        }
        else if (et_state.getText().toString().isEmpty()){
            et_state.setError("Please enter state name");
        }
        else if (et_country.getText().toString().isEmpty()){
            et_country.setError("Please enter country name");
        }
        else if (et_city.getText().toString().isEmpty()){
            et_city.setError("Please enter city name");
        }
        else {
            String firstname = et_fn.getText().toString();
            String lastname = et_ln.getText().toString();
            String company = et_company.getText().toString();
            String email =  et_mail.getText().toString();
            String phone = et_phn.getText().toString();
            String zipcode = et_pin.getText().toString();
            String password = et_pswd.getText().toString();
            String confirmpassword = et_confirmpswd.getText().toString();//main
            String address1 = et_address1.getText().toString();
            String address2 = et_address2.getText().toString();
            String state = et_state.getText().toString();
            String country = et_country.getText().toString();
            String city = et_city.getText().toString();

            callCreateapi(token,firstname,lastname,company,email,phone,zipcode,password,address1,address2,state,country,city);
        }
    }


}