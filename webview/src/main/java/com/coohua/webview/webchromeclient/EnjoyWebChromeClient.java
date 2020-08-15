package com.coohua.webview.webchromeclient;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.coohua.webview.WebViewCallback;

public class EnjoyWebChromeClient extends WebChromeClient {

    private WebViewCallback mWebViewCallback;

    public EnjoyWebChromeClient(WebViewCallback mWebViewCallback) {
        this.mWebViewCallback = mWebViewCallback;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mWebViewCallback != null) {
            mWebViewCallback.updateTitle(title);
        }
    }
}
