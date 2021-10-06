package com.mobileapp.Activities;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.budgetvm.mobileapp.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.internal.ByteStreams;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.mobileapp.POJO.CreateTicketPOJO;
import com.mobileapp.POJO.ServerListResult;
import com.mobileapp.POJO.ServiceList_POJO;
import com.mobileapp.Utils.SharedPreference_VPS;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class CreateTicket extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS= 7;

    @BindView(R.id.rel_back_createticket)
    RelativeLayout rel_back_createticket;

    @BindView(R.id.rel_spinner)
    RelativeLayout rel_spinner;

    @BindView(R.id.aa)
    RelativeLayout aa;

    @BindView(R.id.rel_radioSuppport)
    RelativeLayout rel_radioSuppport;

    @BindView(R.id.rel_radioBilling)
    RelativeLayout rel_radioBilling;

    @BindView(R.id.rel_radioSales)
    RelativeLayout rel_radioSales;

    @BindView(R.id.rel_createTicket)
    RelativeLayout rel_createTicket;

    @BindView(R.id.rel_attachment)
    RelativeLayout rel_attachment;

    @BindView(R.id.uploadimage)
    ImageView uploadimage;

    @BindView(R.id.editimage)
    ImageView editimage;

    @BindView(R.id.del)
    ImageView del;

    @BindView(R.id.spinner1)
    Spinner spinner1;

    @BindView(R.id.tv_please_select)
    TextView tv_please_select;

    @BindView(R.id.et_type_subject)
    EditText et_type_subject;

    @BindView(R.id.et_type_desc)
    TextInputEditText et_type_desc;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBarr;

    Intent intent;
    String token;
    ArrayAdapter<String> dataAdapter;

    GridLayoutManager mGridLayoutManager;
    private MyPostRecyclerViewListener Listener;

    ArrayList<String> array = new ArrayList<>();
    String spinner, message2, subject2, service = "", department = "";
    String attachment2="";

    List<ServerListResult> serverListResults = new ArrayList<>();

    ArrayList<String> IDD = new ArrayList<>();
    ArrayList<String> hostnamee = new ArrayList<>();
    ArrayList<String> MainIPP = new ArrayList<>();
    ArrayList<String> power_status = new ArrayList<>();

    List<ServiceList_POJO> service_list = new ArrayList<>();

    SharedPreference_VPS sharedPreference_vps;

    Uri mImageCaptureUri;


    public Uri getImage(Context inContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), photo, "image"+ Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }
    public String getRealPathFromURI(Uri uri) {
        String path = "";
        if (getContentResolver() != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 101 && resultCode == RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            uploadimage.setImageBitmap(photo);

            if (requestCode == 101 && resultCode == RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            Log.e("ViewTicket", "onActivityResult: photo" + photo);
                Bitmap thumbnail = null;
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageCaptureUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadimage.setImageBitmap(thumbnail);

            aa.setVisibility(View.GONE);
//            mImageCaptureUri = getImage(CreateTicket.this, photo);
//            uploadimage.setImageURI(mImageCaptureUri);
            attachment2 =  getRealPathFromURI(mImageCaptureUri);
            Toast.makeText(CreateTicket.this, "Image Attached", Toast.LENGTH_SHORT).show();

        } else if (requestCode == 1234 && resultCode == RESULT_OK) {
            mImageCaptureUri = data.getData();
            uploadimage.setImageURI(mImageCaptureUri);
            aa.setVisibility(View.GONE);
            attachment2 =  getRealPathFromURI(mImageCaptureUri);
            Toast.makeText(CreateTicket.this, "Image Attached", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == 12345 && resultCode == RESULT_OK) {
            mImageCaptureUri = data.getData();
            uploadimage.setImageResource(R.drawable.file);
            aa.setVisibility(View.GONE);
            attachment2 =  mImageCaptureUri.getPath();
            Toast.makeText(CreateTicket.this, "File Attached", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();

        department = "Sales";
        showServices(token);
        rel_back_createticket.setOnClickListener(this);
        spinner1.setOnItemSelectedListener(this);
        rel_attachment.setOnClickListener(this);
        editimage.setOnClickListener(this);
        del.setOnClickListener(this);
        rel_createTicket.setOnClickListener(this);

    }

   /* @OnClick(R.id.rel_createTicket)
    public void SubmitButton(View view){


        if (et_type_subject.getText().toString().isEmpty()){
            et_type_subject.setError("Please enter first name");
        }

        else if (et_type_desc.getText().toString().isEmpty()){
            et_type_desc.setError("Please enter last name");
        }

        subject = et_type_subject.getText().toString();
        message = et_type_desc.getText().toString();

        attachment = mImageCaptureUri.toString();

        CreateticketApi(token,subject,message,department,attachment);
    }*/

    @OnClick(R.id.rel_radioSales)
    public void RadioSales(View view) {

        department = "Sales";

        rel_radioSales.setBackgroundResource(R.drawable.radio_active);
        rel_radioBilling.setBackgroundResource(R.drawable.radio_unactive);
        rel_radioSuppport.setBackgroundResource(R.drawable.radio_unactive);
    }


    @OnClick(R.id.rel_radioBilling)
    public void RadioBilling(View view) {

        department = "Billing";
        rel_radioSales.setBackgroundResource(R.drawable.radio_unactive);
        rel_radioBilling.setBackgroundResource(R.drawable.radio_active);
        rel_radioSuppport.setBackgroundResource(R.drawable.radio_unactive);
    }


    @OnClick(R.id.rel_radioSuppport)
    public void RadioSupport(View view) {

        department = "Support";
        rel_radioSales.setBackgroundResource(R.drawable.radio_unactive);
        rel_radioBilling.setBackgroundResource(R.drawable.radio_unactive);
        rel_radioSuppport.setBackgroundResource(R.drawable.radio_active);
    }


    private void showServices(String token) {

        simpleProgressBarr.setVisibility(View.VISIBLE);

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

                                    if (i == 0) {
                                        serviceList_pojo.setID("");
                                        serviceList_pojo.setHostname("");
                                        serviceList_pojo.setMainIPP("");
                                    } else {
                                        serviceList_pojo.setID(jsonResult.getJSONObject(i).getString("id"));
                                        serviceList_pojo.setHostname(jsonResult.getJSONObject(i).getString("domain"));
                                        serviceList_pojo.setMainIPP(jsonResult.getJSONObject(i).getJSONObject("mainip").getString("result"));
                                    }
                                    service_list.add(serviceList_pojo);
                                }
                            } catch (Exception e) {

                            }
                        }

                        for (int i = 0; i < service_list.size(); i++) {

                            if (i == 0) {
                                spinner = "Please Select";
                            } else {
                                spinner = "Service - " + service_list.get(i).getID() + " " + "-" + " " + service_list.get(i).getMainIPP().toString() + " " + "-" + " " + service_list.get(i).getHostname().toString();
                            }

                            array.add(spinner);
                        }

                        dataAdapter = new ArrayAdapter<String>(CreateTicket.this, android.R.layout.simple_spinner_item, array);

                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        tv_please_select.setVisibility(View.GONE);
                        // attaching data adapter to spinner
                        spinner1.setAdapter(dataAdapter);

                        simpleProgressBarr.setVisibility(View.GONE);

                    } else {
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(CreateTicket.this, SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText("Wait");
                        sweetAlertDialog.show();
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("errror", String.valueOf(t));
            }
        });
    }

    ContentValues values = new ContentValues();  ////for camera
//for pdf
    private String getFileName(Uri mImageCaptureUri) {
        String fileName = "";
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(mImageCaptureUri, null, null, null, null);
        if (cursor != null){
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            fileName = cursor.getString(nameIndex);
            cursor.close();
        }
        return fileName;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rel_back_createticket:
                onBackPressed();
                break;

            case R.id.rel_createTicket:

                File file;
                MultipartBody.Part attachment = null;

                if (et_type_subject.getText().toString().isEmpty()) {
                    et_type_subject.setError("Please enter Subject");
                } else if (et_type_desc.getText().toString().isEmpty()) {
                    et_type_desc.setError("Please enter Message");
                } else {
                    subject2 = et_type_subject.getText().toString();
                    message2 = et_type_desc.getText().toString();

                    if(attachment2.contains("document")) {
                        file = null;
                        try {
                            ParcelFileDescriptor parcelFileDescriptor = getContentResolver()
                                    .openFileDescriptor(mImageCaptureUri, "r", null);
                            InputStream inputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
                            file = new File(getCacheDir(), getFileName(mImageCaptureUri));
                            FileOutputStream outputStream = new FileOutputStream(file);
                            ByteStreams.copy(inputStream, outputStream);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        RequestBody requestFile = RequestBody.create(MediaType.parse("application/pdf"), file);
                        attachment = MultipartBody.Part.createFormData("attachment[]", file.getName(), requestFile);
                    }

                   else if (!attachment2.isEmpty()){
                        file = new File(attachment2);

                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

                        attachment = MultipartBody.Part.createFormData("attachment[]", file.getName(), requestFile);
                    }
                    else {
                        file = new File("");
                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), "");

                        attachment = MultipartBody.Part.createFormData("attachment[]", file.getName(), requestFile);
                    }

                    RequestBody subject = RequestBody.create(MediaType.parse("text/plain"), subject2);

                    RequestBody message = RequestBody.create(MediaType.parse("text/plain"), message2);

                    RequestBody dept = RequestBody.create(MediaType.parse("text/plain"), department);

                    CreateticketApi(token, subject, message, dept, attachment);
                }
                break;



            case R.id.del:
                uploadimage.setImageDrawable(null);
                aa.setVisibility(View.VISIBLE);
                break;

            case R.id.editimage:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);


            case R.id.rel_attachment:

                checkAndroidVersion();

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);/////img

                builder.setMessage(R.string.file);
                builder.setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
                  /*  @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 101);
                    }*/
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      values.put(MediaStore.Images.Media.TITLE, "New Picture");
                      values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                      mImageCaptureUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                      intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                      startActivityForResult(intent, 101);
                  }
                });

                builder.setNegativeButton(R.string.gallery, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        final int ACTIVITY_SELECT_IMAGE = 1234;
                        startActivityForResult(i, 1234);
                    }
                });
                builder.setNeutralButton(R.string.filemanager, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        Intent i = Intent.createChooser(intent, "View Default File Manager");
                        startActivityForResult(i, 12345);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
        }

    }

    private void CreateticketApi(String token, RequestBody subject, RequestBody message,RequestBody dept, MultipartBody.Part attachment) {

        simpleProgressBarr.setVisibility(View.VISIBLE);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        retrofit2.Call<CreateTicketPOJO> call = apiService.create_ticket(token, subject, message,dept, attachment);
        call.enqueue(new retrofit2.Callback<CreateTicketPOJO>() {
            @Override
            public void onResponse(Call<CreateTicketPOJO> call, Response<CreateTicketPOJO> response) {

                Log.e("CreateticketApi url", call.request().toString());

                if (response.isSuccessful()) {

                    Log.w("CreateticketApi resp", new Gson().toJson(response));
                    if (response.body().getSuccess() == true) {
                        Log.e("Login response", new Gson().toJson(response));
                        simpleProgressBarr.setVisibility(View.GONE);

                        Toast.makeText(CreateTicket.this, "Created Successfully", Toast.LENGTH_SHORT).show();

                        intent = new Intent(CreateTicket.this, Home.class);
                        intent.putExtra("Tag", "1");
                        startActivity(intent);
                        finish();
                    }
                } else {
                    simpleProgressBarr.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<CreateTicketPOJO> call, Throwable t) {

                Log.e("errror", String.valueOf(t));

                simpleProgressBarr.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    boolean flag = false;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (flag) {
            String item = parent.getItemAtPosition(position).toString();
        } else
            flag = true;
        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
//        String item = "Please Select";
    }



///    ///////    //FFFFor camera and storage permission
    private void checkAndroidVersion () {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAndRequestPermissions();

        } else {

        }
    }

    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        int wtite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (wtite != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d("in fragment on request", "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();

                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);

                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("in fragment on request", "CAMERA & WRITE_EXTERNAL_STORAGE READ_EXTERNAL_STORAGE permission granted");

                    } else {
                        Log.d("in fragment on request", "Some permissions are not granted ask again ");

                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            showDialogOK("Camera and Storage Permission required for this app",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case DialogInterface.BUTTON_POSITIVE:
                                                    checkAndRequestPermissions();
                                                    break;
                                                case DialogInterface.BUTTON_NEGATIVE:

                                                    break;
                                            }
                                        }
                                    });
                        }

                        else {
                            Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                }
            }
        }

    }
    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show();
    }

}
