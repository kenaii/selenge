package com.example.selenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.ImageButton;
import android.widget.TextView;

public class ServiceDetailActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_service);
		this.init();
	}
	
	@SuppressWarnings("deprecation")
	@SuppressLint("WrongCall")
	private void init() {
		String url = "file:///android_asset/www/" + getIntent().getStringExtra("ID") + ".html";
		
		ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		TextView titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/AGFuturaMon.ttf"));
		titleTv.setText(R.string.detailed);

		WebView serviceWv = (WebView)findViewById(R.id.serviceWv);
		serviceWv.setInitialScale(50);
		serviceWv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		serviceWv.getSettings().setUseWideViewPort(true);
		serviceWv.getSettings().setBuiltInZoomControls(true);
		serviceWv.loadUrl(url);
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.gc();
		this.init();
	}
	
}
