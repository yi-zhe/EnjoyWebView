package com.coohua.webview;

import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

import com.coohua.base.BaseApplication;
import com.coohua.base.autoservice.CoohuaServiceLoader;
import com.coohua.base.autoservice.IUserCenterService;
import com.coohua.common.eventbus.LoginEvent;
import com.coohua.webview.command.Command;
import com.google.auto.service.AutoService;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command {

    IUserCenterService iUserCenterService = CoohuaServiceLoader.load(IUserCenterService.class);
    ICallbackFromMainProcessToWebViewProcessInterface callback;
    String callbacknameFromNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map params, ICallbackFromMainProcessToWebViewProcessInterface c) {
        Log.d("CommandLogin", new Gson().toJson(params));
        iUserCenterService.login();
        this.callback = c;
        callbacknameFromNativeJs = (String) params.get("callbackname");
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.d("CommandLogin", event.userName);
        HashMap map = new HashMap<>();
        map.put("accountName", event.userName);
        if (callback != null) {
            try {
                callback.onResult(callbacknameFromNativeJs, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
