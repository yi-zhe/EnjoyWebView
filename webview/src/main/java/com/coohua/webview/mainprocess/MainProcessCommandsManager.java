package com.coohua.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;

import com.coohua.base.BaseApplication;
import com.coohua.webview.IWebViewProcessToMainProcessInterface;
import com.coohua.webview.command.Command;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandsManager extends IWebViewProcessToMainProcessInterface.Stub {

    private static MainProcessCommandsManager sInstance;
    private static HashMap<String, Command> mCommands = new HashMap<>();

    public static MainProcessCommandsManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandsManager.class) {
                sInstance = new MainProcessCommandsManager();
            }
        }
        return sInstance;
    }

    private MainProcessCommandsManager() {
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    public void executeCommand(String commandName, Map params) {
        mCommands.get(commandName).execute(params);
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams) throws RemoteException {
        executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class));
    }
}
