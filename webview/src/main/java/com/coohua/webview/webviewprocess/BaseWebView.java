package com.coohua.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.coohua.webview.WebViewCallback;
import com.coohua.webview.bean.JsParam;
import com.coohua.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.coohua.webview.webviewprocess.webchromeclient.EnjoyWebChromeClient;
import com.coohua.webview.webviewprocess.webviewclient.EnjoyWebViewClient;
import com.google.gson.Gson;

import java.util.Map;

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        this(context, null);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        WebViewDefaultSettings.getInstance().setSettings(this);
    }

    public void registerWebViewCallback(WebViewCallback webViewCallback) {
        setWebViewClient(new EnjoyWebViewClient(webViewCallback));
        setWebChromeClient(new EnjoyWebChromeClient(webViewCallback));
        addJavascriptInterface(this, "xiangxuewebview");
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i("BaseWebView", jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                if ("showToast".equalsIgnoreCase(jsParamObject.name)) {
                    Toast.makeText(getContext(), String.valueOf(new Gson().fromJson(jsParamObject.param, Map.class).get("message")), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}