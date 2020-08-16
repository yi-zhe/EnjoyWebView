package com.coohua.webview.command;

import com.coohua.webview.ICallbackFromMainProcessToWebViewProcessInterface;

import java.util.Map;

public interface Command {
    String name();

    void execute(Map params, ICallbackFromMainProcessToWebViewProcessInterface c);
}
