package com.coohua.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.webkit.WebView;

import com.coohua.base.BaseApplication;
import com.coohua.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.coohua.webview.IWebViewProcessToMainProcessInterface;
import com.coohua.webview.mainprocess.MainProcessCommandService;

public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private static WebViewProcessCommandDispatcher sInstance;
    private IWebViewProcessToMainProcessInterface iWebViewProcessToMainProcessInterface;

    public static WebViewProcessCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewProcessCommandDispatcher.class) {
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebViewProcessToMainProcessInterface = IWebViewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params, final BaseWebView w) {
        if (iWebViewProcessToMainProcessInterface != null) {
            try {
                iWebViewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainProcessToWebViewProcessInterface() {
                    @Override
                    public void onResult(String callbackName, String response) throws RemoteException {
                        w.handleCallback(callbackName, response);
                    }

                    @Override
                    public IBinder asBinder() {
                        return null;
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
