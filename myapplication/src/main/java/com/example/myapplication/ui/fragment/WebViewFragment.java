package com.example.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewFragment extends Fragment {
    @BindView(R.id.url)
    EditText url;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.btn_load)
    Button btnLoad;

    private static final String TAG = "WebViewFragment";
    public WebViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        initView(view);
//        ButterKnife.bind(this, view);
        return view;
    }

    private void initView(View view) {
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
    }

    @OnClick({R.id.url, R.id.btn_load, R.id.webView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.url:
                break;
            case R.id.btn_load:
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://www.baidu.com");
                Log.i(TAG, "onClick: btn_load");
            case R.id.webView:
//                webView.loadUrl(url.getText().toString().trim());
                break;
        }
    }


}
