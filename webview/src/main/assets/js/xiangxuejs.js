var xiangxuejs = {};
xiangxuejs.os = {};
xiangxuejs.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
xiangxuejs.os.isAndroid = !xiangxuejs.os.isIOS;

xiangxuejs.callbacks = {}

xiangxuejs.callback = function(callbackname, response) {
    var callbackobject = xiangxuejs.callbacks[callbackname];
    if(callbackobject!==undefined) {
        if(callbackobject.callback !== undefined) {
            var ret = callbackobject.callback(response)
            if(ret === false)
                return

            delete xiangxuejs.callbacks[callbackname]
        }
    }
}

xiangxuejs.takeNativeAction = function(cmd, parameters){
    console.log("xiangxuejs takenativeaction")
    var request = {};
    request.name = cmd;
    request.param = parameters;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
    }
}

xiangxuejs.takeNativeActionWithCallback = function(cmd, parameters, callback){
    console.log("xiangxuejs takeNativeActionWithCallback")
    var callbackname = "nativetojs_callback_" + (new Date()).getTime()+"_"+Math.floor(Math.random()*10000);
    xiangxuejs.callbacks[callbackname] = {callback: callback};

    var request = {};
    request.name = cmd;
    request.param = parameters;
    request.param.callbackname = callbackname;
    if(window.xiangxuejs.os.isAndroid){
        console.log("android take native action" + JSON.stringify(request));
        window.xiangxuewebview.takeNativeAction(JSON.stringify(request));
    } else {
        window.webkit.messageHandlers.xiangxuewebview.postMessage(JSON.stringify(request))
    }
}

window.xiangxuejs = xiangxuejs;
