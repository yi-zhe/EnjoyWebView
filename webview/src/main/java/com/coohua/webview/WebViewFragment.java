package com.coohua.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.coohua.base.BaseApplication;
import com.coohua.base.loadsir.LoadingCallback;
import com.coohua.webview.databinding.FragmentWebviewBinding;
import com.coohua.webview.utils.Constants;
import com.coohua.webview.webviewclient.EnjoyWebViewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public class WebViewFragment extends Fragment implements WebViewCallback {

    private String mUrl;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        mBinding.webview.getSettings().setJavaScriptEnabled(true);
        mBinding.webview.loadUrl(mUrl);
        mLoadService = LoadSir.getDefault().register(mBinding.webview, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
        mBinding.webview.setWebViewClient(new EnjoyWebViewClient(this));
        return mLoadService.getLoadLayout();
    }

    @Override
    public void pageStarted(String url) {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }
}
