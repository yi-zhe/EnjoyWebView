package com.coohua.webview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.coohua.webview.databinding.ActivityWebviewBinding;
import com.coohua.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {

    private ActivityWebviewBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(getIntent().getStringExtra(Constants.URL));
    }
}
