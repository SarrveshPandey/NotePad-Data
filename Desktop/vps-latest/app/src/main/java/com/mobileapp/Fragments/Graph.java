package com.mobileapp.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.budgetvm.mobileapp.R;
import com.mobileapp.Utils.SharedPreference_VPS;

import org.json.JSONObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class Graph extends Fragment implements View.OnClickListener {

    @BindView(R.id.spinnerSelectDay)
    Spinner spinnerSelectDay;

    @BindView(R.id.iv_graph)
    ImageView iv_graph;

    @BindView(R.id.tv_fromDate)
    TextView tv_fromDate;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    Bundle arguments;
    String token, service_id, graphImg;
    SharedPreference_VPS sharedPreference_vps;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.graph_fragment, container, false);
        ButterKnife.bind(this, view);
        sharedPreference_vps = SharedPreference_VPS.getInstance(getContext());
        token = sharedPreference_vps.getApi_Key().toString();
        arguments = getArguments();
        service_id = arguments.getString("service_id");

        String[] data = {"Select Day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        ArrayAdapter dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, data);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectDay.setAdapter(dataAdapter);

        spinnerSelectDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(),parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//Graph Api...................................
        graphApi(token, service_id);

 //............................

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        return view;
    }

    private void graphApi(String token, String service_id) {


        simpleProgressBar.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<ResponseBody> call = apiService.graphimage(token, Integer.parseInt(service_id));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.e("graphApi", call.request().toString());
                Log.e("graphApiResponse", new Gson().toJson(response));

                simpleProgressBar.setVisibility(View.GONE);

                try {
                    if (response.isSuccessful()) {

                        String data = "";
                        data = response.body().string();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONObject jsonResult = jsonObject.getJSONObject("result");

                        graphImg = jsonResult.getString("graph");


                        byte[] decodedString = Base64.decode(graphImg.substring(0,graphImg.length()-1), Base64.DEFAULT );

try {
    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    iv_graph.setImageBitmap(decodedByte);
}
catch (Exception e)
{
    String exception=e.toString();
}

                        simpleProgressBar.setVisibility(View.GONE);

                    } else {
                        simpleProgressBar.setVisibility(View.GONE);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                simpleProgressBar.setVisibility(View.GONE);
                Log.e("errror", String.valueOf(t));

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_fromDate:
                break;
        }
    }


}
