package com.coohua.webview;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.coohua.base.autoservice.CoohuaServiceLoader;
import com.coohua.base.autoservice.IWebViewService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = findViewById(R.id.text);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWebViewService webViewService = CoohuaServiceLoader.load(IWebViewService.class);
                if (webViewService != null) {
                    webViewService.startDemoHtml(MainActivity.this);
                }
            }
        });
    }
}
