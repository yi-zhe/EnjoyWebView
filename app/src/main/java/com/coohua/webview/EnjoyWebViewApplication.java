package com.coohua.webview;

import com.coohua.base.BaseApplication;
import com.coohua.base.loadsir.CustomCallback;
import com.coohua.base.loadsir.EmptyCallback;
import com.coohua.base.loadsir.ErrorCallback;
import com.coohua.base.loadsir.LoadingCallback;
import com.coohua.base.loadsir.TimeoutCallback;
import com.kingja.loadsir.core.LoadSir;

public class EnjoyWebViewApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
