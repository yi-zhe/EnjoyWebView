package com.coohua.common.autoservice;

import android.content.Context;

public interface IWebViewService {
    /**
     * 打开WebView组件的WebView
     */
    void startWebViewActivity(Context cxt, String url, String title, boolean isShowActionBar);
}
