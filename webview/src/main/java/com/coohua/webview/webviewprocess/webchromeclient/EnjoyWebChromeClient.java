package com.coohua.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
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

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d("Console", consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
