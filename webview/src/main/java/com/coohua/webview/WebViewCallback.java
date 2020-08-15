package com.coohua.webview;

public interface WebViewCallback {
    void pageStarted(String url);

    void pageFinished(String url);

    void onError();
}
