package com.mobileapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.budgetvm.mobileapp.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.mobileapp.Adapters.ViewTicket_Adapter;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfReaderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rel_back_authorized_user4)
    ConstraintLayout rel_back_authorized_user4;

    PDFView pdfView;
    File file;
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_reader);
        ButterKnife.bind(this);
        rel_back_authorized_user4.setOnClickListener(this);
        pdfView = findViewById(R.id.pdfView);
      // file = new File("/storage/emulated/0/vps/1631021891734.pdf");
        String value = getIntent().getStringExtra("sstt");
        File bytes = new File(value);
        pdfView.fromFile(bytes).load();

     /*   WebView webview = (WebView) findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
        webview.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);*/
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_back_authorized_user4:
             onBackPressed();
                break;
        }
    }


}