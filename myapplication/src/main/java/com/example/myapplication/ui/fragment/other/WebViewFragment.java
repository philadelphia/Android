package com.example.myapplication.ui.fragment.other;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*从Android 4.4（KitKat）开始，原本基于WebKit的WebView开始基于Chromium内核，
 * 这一改动大大提升了WebView组件的性能以及对HTML5,CSS3,JavaScript的支持。
 *    WebView继承自AbsoluteLayout，可以在其中放入一些子View
 *  websetting webview 的一些属性设置
 *  webclient 从名字上不难理解，这个类就像WebView的委托人一样，
 *  是帮助WebView处理各种通知和请求事件的，我们可以称他为WebView的“内政大臣”.若果不设置，webview还是用系统浏览器进行加载网页
 *
 */

public class WebViewFragment extends Fragment implements View.OnKeyListener {
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
        ButterKnife.bind(this, view);
        initWebView();
        return view;
    }

    private void initWebView() {
        WebSettings webSettings = webView.getSettings();

//        webSettings.setJavaScriptEnabled(true);   // 设置WebView是否可以运行JavaScript
//        webSettings.setSupportMultipleWindows(true);  // 设置WebView是否支持多窗口
//        webSettings.setSupportZoom(false);            //设置是否支持缩放。
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //设置使用默认的缓存模式
//        webView.setWebViewClient(new WebViewClient());
//        webView.setOnKeyListener(this);
//        webView.loadUrl("http://www.google.com");
//        if (webView.canGoBack())
//            webView.goBack();
//        if (webView.canGoForward())
//            webView.goForward();
    }

    @OnClick({R.id.url, R.id.btn_load, R.id.webView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load:
                webView.loadUrl(url.getText().toString().trim());
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        Log.i(TAG, "onPageStarted: --started url ==" + url);
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        Log.i(TAG, "onPageStarted: --page finished url ==" + url);
                        super.onPageFinished(view, url);
                    }

                    //让多个web界面在一个窗口中打开
//                    @Override
//                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                        webView.loadUrl(url);
//                        return true;
//                    }

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        Uri url = request.getUrl();
                        if (url.getAuthority().contains("tencent")) {
                            return true;
                        }
                        return  false;
//                        return super.shouldOverrideUrlLoading(view, request);
                    }
                });

                // 果说WebViewClient是帮助WebView处理各种通知、请求事件的“内政大臣”的话，那么WebChromeClient就是辅
                // 助WebView处理Javascript的对话框，网站图标，网站title，加载进度等偏外部事件的“外交大臣”。
//                webView.setWebChromeClient(new WebChromeClient() {
//                    @Override
//                    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//                        Log.i(TAG, "onCreateWindow: ");
//                        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
//                    }
//
//                    @Override
//                    public void onProgressChanged(WebView view, int newProgress) {
//                        Log.i(TAG, "onProgressChanged: " + newProgress);
//                        super.onProgressChanged(view, newProgress);
//                    }
//
//                    @Override
//                    public void onReceivedIcon(WebView view, Bitmap icon) {
//                        Log.i(TAG, "onReceivedIcon: ");
//                        super.onReceivedIcon(view, icon);
//                    }
//
//                    @Override
//                    public void onReceivedTitle(WebView view, String title) {
//                        Log.i(TAG, "onReceivedTitle: ");
//                        super.onReceivedTitle(view, title);
//                    }
//                });

                if (webView.canGoBack())
                    webView.goBack();
                if (webView.canGoForward())
                    webView.goForward();
                Log.i(TAG, "url == : " + url.getText().toString().trim());
                break;

            case R.id.webView:
//                webView.loadUrl(url.getText().toString().trim());
                break;
        }
    }


    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        switch (keyEvent.getAction()) {
            case KeyEvent.ACTION_DOWN:
                Log.i(TAG, "onKey: KeyDown");
                switch (keyCode) {
                    case KeyEvent.KEYCODE_BACK:
                        Log.i(TAG, "onKey: KEYCODE_BACK");
                        if (webView.canGoBack()) {
                            webView.goBack();
                            return true;
                        }
                        break;
                    case KeyEvent.KEYCODE_HOME:
                        Log.i(TAG, "onKey: KEYCODE_HOME");
                        break;
                    case KeyEvent.KEYCODE_MENU:
                        Log.i(TAG, "onKey:KEYCODE_MENU ");
                        break;
                    default:
                        break;
                }

                break;
            case KeyEvent.ACTION_UP:
                Log.i(TAG, "onKey: ACTION_UP");
                break;

            default:
                break;
        }

        return false;
    }

}
