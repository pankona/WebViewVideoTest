package io.pankona.webviewvideotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private WebChromeClient webChromeClient;

    private ViewGroup videoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoContainer = (ViewGroup)findViewById(R.id.video_container);
        webview = (WebView)findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);

        webChromeClient = new WebChromeClient() {
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                Log.d("WebChromeClient", "IN onShowCustomView");
                super.onShowCustomView(view, callback);
                view.setVisibility(View.VISIBLE);
                videoContainer.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                videoContainer.setVisibility(View.VISIBLE);
                Log.d("WebChromeClient", "OUT onShowCustomView");
            }

            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
            }

        };

        webview.setWebChromeClient(webChromeClient);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("WebView", "IN shouldOverrideUrlLoading");
                view.loadUrl(url);
                Log.d("WebView", "OUT shouldOverrideUrlLoading");
                return true;
            }
        });
        webview.loadUrl("http://m.youtube.com");
    }
}
