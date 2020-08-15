package com.coohua.webview;


import android.content.Context;
import android.content.Intent;

import com.coohua.base.autoservice.IWebViewService;
import com.coohua.webview.utils.Constants;
import com.google.auto.service.AutoService;

@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {

    @Override
    public void startWebViewActivity(Context cxt, String url, String title, boolean isShowActionBar) {
        if (cxt != null) {
            Intent intent = new Intent(cxt, WebViewActivity.class);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.URL, url);
            intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
            cxt.startActivity(intent);
        }
    }

}
