package com.coohua.webview;

interface IWebViewProcessToMainProcessInterface {

    void handleWebCommand(String commandName, String jsonParams);
}
