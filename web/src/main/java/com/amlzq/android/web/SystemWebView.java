package com.amlzq.android.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.amlzq.android.web.WebViewJavaScriptFunction;

public class SystemWebView extends WebView {

    // 使用system webview 来验证 X5webview的支持情况

    public SystemWebView(Context context) {
        super(context);
    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    public SystemWebView(Context context, AttributeSet attr) {
        super(context, attr);
        this.addJavascriptInterface(new WebViewJavaScriptFunction() {

            @JavascriptInterface
            @Override
            public void onJsFunctionCalled(String tag) {

            }
        }, WebViewJavaScriptFunction.EXPOSE_OBJECT);
        this.setClickable(true);
        initSettings();

        this.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    @SuppressWarnings("deprecation")
    private void initSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
    }

}