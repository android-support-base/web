package com.amlzq.android.web;

import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by amlzq on 2018/4/8.
 * <p>
 * 处理常用的业务
 */

public class SystemWebChromeClient extends WebChromeClient {
    public static final String TAG = "SystemWebChromeClient";

    /**
     * 展示提示信息
     * js code "window.prompt(message, value)"
     */
    @Override
    public boolean onJsPrompt(WebView webView, String s, String s1, String s2, JsPromptResult jsPromptResult) {
        return super.onJsPrompt(webView, s, s1, s2, jsPromptResult);
    }

    @Override
    public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
        return super.onJsAlert(webView, s, s1, jsResult);
    }

    @Override
    public boolean onJsConfirm(WebView webView, String s, String s1, JsResult jsResult) {
        return super.onJsConfirm(webView, s, s1, jsResult);
    }

    /**
     * Android提供给Js调试在Native代码里面打印日志信息的API
     * js code "console.log("xxx")"
     */
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        return super.onConsoleMessage(consoleMessage);
    }

}
