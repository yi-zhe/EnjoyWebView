package com.coohua.webview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.coohua.base.loadsir.ErrorCallback;
import com.coohua.base.loadsir.LoadingCallback;
import com.coohua.webview.databinding.FragmentWebviewBinding;
import com.coohua.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.coohua.webview.utils.Constants;
import com.coohua.webview.webviewprocess.webchromeclient.EnjoyWebChromeClient;
import com.coohua.webview.webviewprocess.webviewclient.EnjoyWebViewClient;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class WebViewFragment extends Fragment implements WebViewCallback, OnRefreshListener {

    private String mUrl;
    private boolean canNativeRefresh;
    private FragmentWebviewBinding mBinding;
    private LoadService mLoadService;
    private boolean mIsError = false;

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
            canNativeRefresh = bundle.getBoolean(Constants.CAN_NATIVE_REFRESH);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        mBinding.webview.registerWebViewCallback(this);
        mBinding.webview.loadUrl(mUrl);
        mLoadService = LoadSir.getDefault().register(mBinding.smartrefreshlayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                mLoadService.showCallback(LoadingCallback.class);
                mBinding.webview.reload();
            }
        });
        WebViewDefaultSettings.getInstance().setSettings(mBinding.webview);
        mBinding.smartrefreshlayout.setOnRefreshListener(this);
        mBinding.smartrefreshlayout.setEnableAutoLoadMore(false);
        mBinding.smartrefreshlayout.setEnableRefresh(canNativeRefresh);
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
        if (mIsError) {
            mBinding.smartrefreshlayout.setEnableRefresh(true);
        } else {
            mBinding.smartrefreshlayout.setEnableRefresh(canNativeRefresh);
        }
        mBinding.smartrefreshlayout.finishRefresh();
        if (mLoadService != null) {
            if (mIsError) {
                mLoadService.showCallback(ErrorCallback.class);
            } else {
                mLoadService.showSuccess();
            }
        }
        mIsError = false;
    }

    @Override
    public void onError() {
        mIsError = true;
        mBinding.smartrefreshlayout.finishRefresh();
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mBinding.webview.reload();
    }
}
