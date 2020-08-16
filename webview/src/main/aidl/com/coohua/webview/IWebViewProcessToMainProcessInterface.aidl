package com.coohua.webview;

import com.coohua.webview.ICallbackFromMainProcessToWebViewProcessInterface;

interface IWebViewProcessToMainProcessInterface {

    void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainProcessToWebViewProcessInterface ic);
}
