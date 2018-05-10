package com.example.selenge;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
public class IntroduceFragment extends Fragment {

	private ProgressDialog progDailog;
	
	public IntroduceFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_introduce, container, false);

		if (!MainActivity.isNetworkAvailable(getActivity())) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(R.string.no_network_connection_title);
			builder.setMessage(R.string.no_network_connection_message)
			       .setCancelable(false)
			       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
			return rootView;
		}
		
		progDailog = ProgressDialog.show(getActivity(), "Уншиж байна","Түр хүлээнэ үү...", true);
		progDailog.setCancelable(false);

		WebView introWv = (WebView) rootView.findViewById(R.id.introWv);

		introWv.getSettings().setJavaScriptEnabled(true);     
		introWv.getSettings().setLoadWithOverviewMode(true);
		introWv.getSettings().setUseWideViewPort(true);
		introWv.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if (Uri.parse(url).getHost().equals("http://sel.gov.mn/beta/2/item/8")) {
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
		introWv.loadUrl("http://sel.gov.mn/beta/2/item/8");

		return rootView;
	}
	
}
