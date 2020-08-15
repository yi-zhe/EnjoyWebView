package com.coohua.base.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

public interface IWebViewService {
    /**
     * 打开WebView组件的WebView
     */
    void startWebViewActivity(Context cxt, String url, String title, boolean isShowActionBar);

    Fragment getWebViewFragment(String url, boolean canNativeRefresh);

    void startDemoHtml(Context context);
}
