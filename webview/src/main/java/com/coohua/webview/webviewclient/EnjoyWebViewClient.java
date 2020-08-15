package com.coohua.webview.webviewclient;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coohua.webview.WebViewCallback;

public class EnjoyWebViewClient extends WebViewClient {

    private WebViewCallback mWebViewCallback;

    public EnjoyWebViewClient(WebViewCallback mWebViewCallback) {
        this.mWebViewCallback = mWebViewCallback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mWebViewCallback != null) {
            mWebViewCallback.pageStarted(url);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mWebViewCallback != null) {
            mWebViewCallback.pageFinished(url);
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mWebViewCallback != null) {
            mWebViewCallback.onError();
        }
    }
}
