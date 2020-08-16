package com.coohua.webview;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.coohua.base.BaseApplication;
import com.coohua.webview.command.Command;
import com.google.auto.service.AutoService;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map params, ICallbackFromMainProcessToWebViewProcessInterface c) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(BaseApplication.sApplication, (String) params.get("message"), Toast.LENGTH_LONG).show());
    }
}
