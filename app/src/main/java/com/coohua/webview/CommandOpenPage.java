package com.coohua.webview;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.coohua.base.BaseApplication;
import com.coohua.webview.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;

@AutoService({Command.class})
public class CommandOpenPage implements Command {

    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map params, ICallbackFromMainProcessToWebViewProcessInterface c) {
        String targetClass = String.valueOf(params.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
