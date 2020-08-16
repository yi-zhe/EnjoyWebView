// ICallbackFromMainProcessToWebViewProcessInterface.aidl
package com.coohua.webview;

// Declare any non-default types here with import statements

interface ICallbackFromMainProcessToWebViewProcessInterface {
    void onResult(String callbackName, String response);
}
