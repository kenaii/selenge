package com.example.selenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class InfoDetailActivity extends Activity implements OnClickListener {
	
	private ProgressDialog progDailog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_info);
		this.init();
	}
	
	private void init() {
		String url = getIntent().getStringExtra("url");
		
		ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		TextView titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/AGFuturaMon.ttf"));
		titleTv.setText(R.string.detailed);

		progDailog = ProgressDialog.show(InfoDetailActivity.this, "Уншиж байна","Түр хүлээнэ үү...", true);
		progDailog.setCancelable(false);

		WebView introWv = (WebView) findViewById(R.id.infoWv);

		introWv.getSettings().setJavaScriptEnabled(true);     
		introWv.getSettings().setLoadWithOverviewMode(true);
		introWv.getSettings().setUseWideViewPort(true);
		introWv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (Uri.parse(url).getHost().equals(url)) {
					progDailog.show();
					return false;
		        }
				return true;                
			}
			@Override
			public void onPageFinished(WebView view, final String url) {
				progDailog.dismiss();
			}
		});
		introWv.loadUrl(url);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		default:
			break;
		}
	}
	
}
