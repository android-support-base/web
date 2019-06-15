package com.amlzq.android.web;

/**
 * Created by amlzq on 2018/11/6.
 */

public interface WebViewJavaScriptFunction {

    String EXPOSE_OBJECT = "";

    void onJsFunctionCalled(String tag);
}
