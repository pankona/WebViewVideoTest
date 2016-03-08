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

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private WebChromeClient webChromeClient;
    private ViewGroup videoContainer;

    private final String URL = "http://m.youtube.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoContainer = (ViewGroup)findViewById(R.id.video_container);
        webview = (WebView)findViewById(R.id.webview);

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        webChromeClient = new WebChromeClient() {
            private View video;
            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                Log.d("WebChromeClient", "IN onShowCustomView");
                video = view;
                videoContainer.addView(video);
                Log.d("WebChromeClient", "OUT onShowCustomView");
            }

            @Override
            public void onHideCustomView() {
                Log.d("WebChromeClient", "IN onHideCustomView");
                videoContainer.removeView(video);
                video = null;
                Log.d("WebChromeClient", "IN onHideCustomView");
            }
        };

        webview.setWebChromeClient(webChromeClient);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(URL);
    }
}
