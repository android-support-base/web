package com.amlzq.android.web;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by amlzq on 2018/4/8.
 * <p>
 * 处理常用的业务
 */

public class SystemWebViewClient extends WebViewClient {
    public static final String TAG = "SystemWebViewClient";

    private String mCurrentUrl;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // 返回true表示你已经处理此次请求
        // 返回false表示有webview自行处理
        // post请求并不会回调这个函数

        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (mCurrentUrl != null && url.equals(mCurrentUrl)) {
            view.goBack();
            return true;
        }
        mCurrentUrl = url;

        if (url.startsWith("http:") || url.startsWith("https:")) {
            return false;
        }

        // Otherwise allow the OS to handle things like tel, mailto, weixin,
        // etc.
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            if (url.startsWith("alipays:")) {
                Toast.makeText(view.getContext(), "请安装支付宝客户端", Toast.LENGTH_SHORT).show();
            } else if (url.startsWith("weixin:")) {
                Toast.makeText(view.getContext(), "请安装微信客户端", Toast.LENGTH_SHORT).show();
            } else if (url.startsWith("qq:")) {
                Toast.makeText(view.getContext(), "请安装QQ客户端", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String s) {
        super.onPageFinished(view, s);

        // 解决H5的音视频不能自动播放的问题
//            view.loadUrl("javascript:(function() { " +
//                    "var videos = document.getElementsByTagName('audio');" +
//                    " for(var i=0;i<videos.length;i++){videos[i].play();}})()");
//
//            view.loadUrl("javascript:(function() { " +
//                    "var videos = document.getElementsByTagName('video');" +
//                    " for(var i=0;i<videos.length;i++){videos[i].play();}})()");
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        // 重新加载错误页面
    }

    @Override
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        //
    }

    public String getCurrentUrl() {
        return mCurrentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.mCurrentUrl = currentUrl;
    }

}
