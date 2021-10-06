package com.mobileapp.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.internal.ByteStreams;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.mobileapp.Adapters.ViewTicket_Adapter;
import com.mobileapp.ApiClasses.ApiClient;
import com.mobileapp.ApiClasses.ApiInterface;
import com.mobileapp.Interface.MyPostRecyclerViewListener;
import com.mobileapp.POJO.ReplyPOJO;
import com.mobileapp.POJO.Ticket_Specification;
import com.mobileapp.POJO.Ticket_SpecificationResult;
import com.budgetvm.mobileapp.R;
import com.mobileapp.POJO.AttachmentOnly;
import com.mobileapp.Utils.SharedPreference_VPS;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;


public class View_Ticket extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS= 7;


    @BindView(R.id.rel_back_viewticket)
    RelativeLayout rel_back_viewticket;

    @BindView(R.id.iv_attach)
    ImageView iv_attach;

    @BindView(R.id.uploadimage1)
    ImageView uploadimage1;

    @BindView(R.id.editimage1)
    ImageView editimage1;

    @BindView(R.id.del1)
    ImageView del1;

    @BindView(R.id.tv_department)
    TextView tv_department;

    @BindView(R.id.txtattach)
    TextView txtattach;

    @BindView(R.id.tv_lastreplier_type)
    TextView tv_lastreplier_type;

    @BindView(R.id.tv_days)
    TextView tv_days;

    @BindView(R.id.tv_status)
    TextView tv_status;

    @BindView(R.id.tv_text)
    TextView tv_text;

    @BindView(R.id.tv_createTicket)
    TextView tv_createTicket;

    @BindView(R.id.et_reply)
    TextInputEditText et_reply;

    @BindView(R.id.simpleProgressBarr)
    RelativeLayout simpleProgressBar;

    @BindView(R.id.rel_view_ticketBottom)
    RelativeLayout rel_view_ticketBottom;

    @BindView(R.id.recycler_view_ticket)
    RecyclerView recycler_view_ticket;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    Intent intent;

    int last_updated;

    String ticketId, ticket_id ,token,department, last_replier,ticket_status,replier_name,reply,TAG;

    String attachment2 ="";

    SharedPreference_VPS sharedPreference_vps;

    ViewTicket_Adapter viewTicket_adapter;

    private String actualPictureImagePath = "";

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

        if (requestCode == 101 && resultCode == RESULT_OK) {
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            Log.e("ViewTicket", "onActivityResult: photo" + photo);
            Bitmap thumbnail = null;
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageCaptureUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadimage1.setImageBitmap(thumbnail);
            attachment2 = getRealPathFromURI(mImageCaptureUri);
//            mImageCaptureUri = getImage(View_Ticket.this,photo);
            uploadimage1.setImageURI(mImageCaptureUri);
            uploadimage1.setVisibility(View.VISIBLE);
            editimage1.setVisibility(View.VISIBLE);
            del1.setVisibility(View.VISIBLE);
            iv_attach.setVisibility(View.GONE);
            txtattach.setVisibility(View.GONE);
            Toast.makeText(View_Ticket.this, "Image Attached", Toast.LENGTH_SHORT).show();
        }

        else if(requestCode == 1234 && resultCode == RESULT_OK){
            mImageCaptureUri = data.getData();
            /*String Path=mImageCaptureUri.getPath();*/
            uploadimage1.setImageURI(mImageCaptureUri);
            uploadimage1.setVisibility(View.VISIBLE);
            editimage1.setVisibility(View.VISIBLE);
            del1.setVisibility(View.VISIBLE);
            iv_attach.setVisibility(View.GONE);
            txtattach.setVisibility(View.GONE);
            attachment2 =  getRealPathFromURI(mImageCaptureUri);
            Toast.makeText(View_Ticket.this, "Image Attached", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == 12345 && resultCode == RESULT_OK){
            mImageCaptureUri = data.getData();
            Log.e("image uri" , "" + mImageCaptureUri.toString());
            uploadimage1.setImageResource(R.drawable.file);
            uploadimage1.setVisibility(View.VISIBLE);
            editimage1.setVisibility(View.VISIBLE);
            del1.setVisibility(View.VISIBLE);
            iv_attach.setVisibility(View.GONE);
            txtattach.setVisibility(View.GONE);
            attachment2 = mImageCaptureUri.getPath();

            Toast.makeText(View_Ticket.this, "File Attached", Toast.LENGTH_SHORT).show();
        }
    }

    private MyPostRecyclerViewListener Listener;
    List<Ticket_SpecificationResult> ticket_specifications = new ArrayList<>();
    List<AttachmentOnly.Result> ticket_specifications2 = new ArrayList<>();
    Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__ticket);
        ButterKnife.bind(this);
        iv_attach.setOnClickListener(this);
        editimage1.setOnClickListener(this);
        del1.setOnClickListener(this);

//        uploadimage();
        sharedPreference_vps = SharedPreference_VPS.getInstance(this);
        token = sharedPreference_vps.getApi_Key().toString();
        intent = getIntent();
        ticketId = intent.getStringExtra("TicketId");
        ticket_id = intent.getStringExtra("TicketId2");
        department = intent.getStringExtra("department");
        last_replier = intent.getStringExtra("last_replier");
        ticket_status = intent.getStringExtra("ticket_status");
        replier_name = intent.getStringExtra("customer_name");
        TAG = intent.getStringExtra("Tag");

        tv_text.setText(ticketId);
        tv_department.setText(department);
        tv_lastreplier_type.setText(last_replier);
        tv_status.setText(ticket_status);


        if (TAG.equals("1")){
            TicketSpecificationApi(token,ticketId);
        }
        else {

        }

        Listener = new MyPostRecyclerViewListener() {
            @Override
            public void onClick(View view, int position) {

            }
        };

        recycler_view_ticket.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_ticket.setNestedScrollingEnabled(false);

        viewTicket_adapter = new ViewTicket_Adapter(this,ticket_specifications,Listener , ticket_specifications2);
        recycler_view_ticket.setAdapter(viewTicket_adapter);

        et_reply.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_createTicket.setClickable(false);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_createTicket.setClickable(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tv_createTicket.setClickable(true);
            }
        });

        rel_back_viewticket.setOnClickListener(this);

        TicketSpecificationApi(token,ticketId);



        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swiperefresh.setRefreshing(false);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.tv_createTicket)
    public void CreateTicket(View view){

        File file;

        MultipartBody.Part attachment = null;


        if (et_reply.getText().toString().isEmpty()){
            et_reply.setError("Please enter Reply");
        }
        else {
            reply = et_reply.getText().toString();

            if(attachment2.contains("document")){
                file = null;
                try {
                    ParcelFileDescriptor parcelFileDescriptor = getContentResolver()
                            .openFileDescriptor(mImageCaptureUri, "r", null);
                    InputStream inputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
                    file = new File(getCacheDir(), getFileName(mImageCaptureUri));
                    FileOutputStream outputStream = new FileOutputStream(file);
                    ByteStreams.copy(inputStream , outputStream);

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
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/pdf"), file);
                attachment = MultipartBody.Part.createFormData("attachment[]",  file.getName(),requestFile);
            }
            else {
                file = new File("");
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/pdf"), "");
                attachment = MultipartBody.Part.createFormData("attachment[]", file.getName(), requestFile);
            }
            RequestBody message = RequestBody.create(MediaType.parse("text/plain"), reply);
            RequestBody ticket = RequestBody.create(MediaType.parse("text/plain"), ticketId);
            CreateReplyApi(token, ticket, message, attachment);

        }
    }

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

    private void CreateReplyApi(String token, RequestBody ticket, RequestBody message, MultipartBody.Part attachment) {

        simpleProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        retrofit2.Call<ReplyPOJO> call = apiService.replyticket(token,ticket,message,attachment);
        call.enqueue(new retrofit2.Callback<ReplyPOJO>() {

            @Override
            public void onResponse(Call<ReplyPOJO> call, Response<ReplyPOJO> response) {

                simpleProgressBar.setVisibility(View.GONE);
                Log.e("CreateTicketApi url", call.request().toString());
                if (response.isSuccessful()) {
                    Log.w("CreateTicketApi resp", new Gson().toJson(response));

                    if (response.body().getSuccess()==true) {
                        Log.e("CreateTicketApi resp", new Gson().toJson(response));
                        Toast.makeText(View_Ticket.this,"success",Toast.LENGTH_SHORT).show();

                        et_reply.setText("");
                        et_reply.setHint("Type Reply");
                        TicketSpecificationApi(token,ticketId);
                        finish();
                        startActivity(getIntent());
                        tv_createTicket.setClickable(false);
//                        ticketResult.addAll(response.body().getResult());
//                        cardview.notifyDataSetChanged();
                        simpleProgressBar.setVisibility(View.GONE);
                    }
                    else {
                        Toast.makeText(View_Ticket.this,response.message(),Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReplyPOJO> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);

                Log.e("errror", String.valueOf(t));
                Toast.makeText(View_Ticket.this,t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }




    private void TicketSpecificationApi(String token, String ticketId) {
        simpleProgressBar.setVisibility(View.VISIBLE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<Ticket_Specification> call = apiService.getParticularTicket(token,ticketId);
        call.enqueue(new retrofit2.Callback<Ticket_Specification>() {
            @Override
            public void onResponse(Call<Ticket_Specification> call, Response<Ticket_Specification> response) {
                Log.e("TicketSpecificationApi", call.request().toString());
                if (response.isSuccessful()) {
                    TicketSpecificationApi2(token,ticket_id);

                    Log.w("TicketSpecificationApi", new Gson().toJson(response));
                    if (response.body().getSuccess()==true) {

                        Log.e("TicketSpecificationApi", new Gson().toJson(response));
                        ticket_specifications.add(response.body().getResult());
                        viewTicket_adapter.notifyDataSetChanged();
                        last_updated = Integer.parseInt(response.body().getResult().getDateline());
                        Date date = new Date(last_updated*1000L); // *1000 is to convert seconds to milliseconds
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); // the format of your date
                        sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));

                        String currentDateandTime = sdf.format(new Date());

                        Log.e("Dates before:",sdf.format(date));
                        Log.e("Dates current:",currentDateandTime);
                        int day = 0;
                        try {
                            Date dateBefore = sdf.parse(sdf.format(date));
                            Date dateAfter = sdf.parse(currentDateandTime);
                            long difference = dateAfter.getTime() - dateBefore.getTime();
                            float daysBetween = (difference / (1000*60*60*24));
                            day = (int) daysBetween;

                           Log.e("Days between dates: ",daysBetween+"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tv_days.setText(sdf.format(date));
                        rel_view_ticketBottom.setVisibility(View.VISIBLE);
                    }
                } else {
                    simpleProgressBar.setVisibility(View.GONE);
                    Toast.makeText(View_Ticket.this,response.message(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Ticket_Specification> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
                Log.e("errror", String.valueOf(t));
            }
        });
    }

    private void TicketSpecificationApi2(String token, String ticket_id) {
        simpleProgressBar.setVisibility(View.VISIBLE);
        recycler_view_ticket.setVisibility(View.GONE);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        retrofit2.Call<AttachmentOnly> call = apiService.attachm(token,ticket_id);
        call.enqueue(new retrofit2.Callback<AttachmentOnly>() {
            @Override
            public void onResponse(Call<AttachmentOnly> call, Response<AttachmentOnly> response) {

                if (response.isSuccessful()) {

                    if (response.body().getSuccess() == true) {
                        ticket_specifications2.add(response.body().getResult());
                        viewTicket_adapter.notifyDataSetChanged();
//                        Toast.makeText(View_Ticket.this,"successful2",Toast.LENGTH_LONG).show();
                        simpleProgressBar.setVisibility(View.GONE);
                        recycler_view_ticket.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    Toast.makeText(View_Ticket.this,response.message(),Toast.LENGTH_LONG).show();
                    simpleProgressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<AttachmentOnly> call, Throwable t) {
                simpleProgressBar.setVisibility(View.GONE);
//                Toast.makeText(View_Ticket.this,"Api Fail2222",Toast.LENGTH_LONG).show();
            }
        });
    }



    ContentValues values = new ContentValues();  //for camera good quality

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.rel_back_viewticket:
               onBackPressed();
                break;

            case R.id.del1:
                uploadimage1.setImageDrawable(null);
                iv_attach.setVisibility(View.VISIBLE);
                txtattach.setVisibility(View.VISIBLE);
                editimage1.setVisibility(View.GONE);
                del1.setVisibility(View.GONE);
                break;

            case R.id.editimage1:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);

            case R.id.iv_attach:
                checkAndroidVersion();
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);/////img

                builder.setMessage(R.string.file);
                builder.setPositiveButton(R.string.camera, new DialogInterface.OnClickListener() {
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
                        Intent i = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        final int ACTIVITY_SELECT_IMAGE = 1234;
                        startActivityForResult(i, 1234);
                    }
                });

                builder.setNeutralButton(R.string.filemanager, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 12345);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

//FFFFor camera and storage permission
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
