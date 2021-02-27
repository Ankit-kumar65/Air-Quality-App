package com.aqm.airquality;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;

import java.security.AlgorithmParameters;
import android.app.ProgressDialog;
import android.graphics.Bitmap;

public class activity1 extends AppCompatActivity {


    private WebView webView;
    private ProgressDialog dialog;



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);
        webView = findViewById(R.id.b34);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);



        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, 0);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }



        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, android.webkit.GeolocationPermissions.Callback callback) {
                Log.d("Location", "callback");
                callback.invoke(origin, true, false);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                webView.loadUrl("javascript:(function() { " +
                        " document.getElementsByTagName('header')[0].style.display='none';" +

                        " document.getElementsByTagName('footer')[0].style.display='none';" +
                        " document.getElementsByTagName('catapult-cookie-bar')[0].style.display='none';" +
                        " document.getElementsByTagName('nav-bar')[0].style.display='none';})()");

                dialog.dismiss();
                super.onPageFinished(view, url);


            }
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return true;
        }



            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(activity1.this, null,
                        "Please Wait....");
                dialog.setCancelable(true);

                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

                view.loadUrl("about:blank");
                Toast.makeText(activity1.this, "Error occured, please check your internet connectivity", Toast.LENGTH_SHORT).show();
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
            // This method will be triggered when error page appear



    });











        webView.loadUrl("https://breezometer.com/air-quality-map/");




    }}


















