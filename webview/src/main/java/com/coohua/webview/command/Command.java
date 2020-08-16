package com.coohua.webview.command;

import java.util.Map;

public interface Command {
    String name();

    void execute(Map params);
}
