package com.joseadilson.news_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView mWebView;
    /*@BindView(R.id.myEditText)
    TextView browserHead;*/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.linearLayout2)
    RelativeLayout relLay;
    @BindView(R.id.mError)
    LinearLayout linError;
    @BindView(R.id.progressBar5)
    ProgressBar progressBar;

    String s = "https://news.google.com/news/explore/section/q/Goa/Goa?ned=in&hl=en-IN";
    int sa = 0, id = 0, isFromNotification = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web);
        ButterKnife.bind(this);

        s = getIntent().getStringExtra("URL_Article");
        /* sa = getIntent().getIntExtra("URL_ArticleType", 0);
        id = getIntent().getIntExtra("URL_ArticleID", 0);*/
        //isFromNotification = getIntent().getIntExtra(getPackageName(), 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("News Goa");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // this.setContentView(mWebView);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        //final ProgressDialog pd = ProgressDialog.show(WebActivity.this, "", "Please wait...", true);

        mWebView.getSettings().setJavaScriptEnabled(true); // enable javascript
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
       // pd.show();
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebActivity.this, description, Toast.LENGTH_SHORT).show();
                mWebView.setVisibility(View.GONE);
                linError.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
              //  pd.dismiss();
                String webUrl = mWebView.getUrl();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //progressBar.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                progressBar.setProgress(100);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
            }
        });

        mWebView.loadUrl(s);
       // browserHead.setText(s);
        /* mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });*/

    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.share:
                /*Intent intent = new Intent(this,NotificationActivity.class);
                startActivity(intent);*/
                //String text = "Look at my awesome picture";
                //Uri pictureUri = Uri.parse("file://my_picture");
                /* Resources resources = this.getResources();
                Uri pictureUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(R.mipmap.ic_launcher) + '/'
                        + resources.getResourceTypeName(R.mipmap.ic_launcher) + '/' + resources.getResourceEntryName(R.mipmap.ic_launcher) );*/
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                //shareIntent.putExtra(Intent.EXTRA_STREAM, pictureUri);
                shareIntent.setType("text/plain");
                //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share link..."));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
