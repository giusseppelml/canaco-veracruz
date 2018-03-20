package com.beta.giusseppe.canacoveracruz.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.beta.giusseppe.canacoveracruz.R;
import com.beta.giusseppe.canacoveracruz.api.Api;

public class PdfActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        if(getIntent().getExtras() != null)
        {
            url = getIntent().getExtras().getString("url");
        }

        WebView webView = (WebView) findViewById(R.id.my_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });

        //Carga url de .PDF en WebView  mediante Google Drive Viewer.
        webView.loadUrl(Api.URL_DRIVER + Api.URL_GALERIA + url);
    }
}
