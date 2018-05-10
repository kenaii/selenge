package com.example.selenge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class MenuActivity extends Activity implements OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		this.init();
	}
	
	private void init() {
		Typeface tf1 = Typeface.createFromAsset(getAssets(), "fonts/AGFuturaMon.ttf");
		
		Button menu1Btn = (Button) findViewById(R.id.menu1Btn);
		Button menu2Btn = (Button) findViewById(R.id.menu2Btn);
		Button menu3Btn = (Button) findViewById(R.id.menu3Btn);
		Button menu4Btn = (Button) findViewById(R.id.menu4Btn);
		Button menu5Btn = (Button) findViewById(R.id.menu5Btn);
		Button menu6Btn = (Button) findViewById(R.id.menu6Btn);
		
		menu1Btn.setOnClickListener(this);
		menu2Btn.setOnClickListener(this);
		menu3Btn.setOnClickListener(this);
		menu4Btn.setOnClickListener(this);
		menu5Btn.setOnClickListener(this);
		menu6Btn.setOnClickListener(this);
		
		menu1Btn.setTypeface(tf1);
		menu2Btn.setTypeface(tf1);
		menu3Btn.setTypeface(tf1);
		menu4Btn.setTypeface(tf1);
		menu5Btn.setTypeface(tf1);
		menu6Btn.setTypeface(tf1);
		
		TextView footerTv = (TextView) findViewById(R.id.footerTv);
		footerTv.setTypeface(tf1);
		
		RelativeLayout menuRl = (RelativeLayout) findViewById(R.id.menuRl);
		menuRl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.menu1Btn:
			this.toTransfer(0);
			break;
		case R.id.menu2Btn:
			this.toTransfer(1);
			break;
		case R.id.menu3Btn:
			this.toTransfer(2);
			break;
		case R.id.menu4Btn:
			this.toTransfer(3);
			break;			
		case R.id.menu5Btn:
			this.toTransfer(4);
			break;
		case R.id.menu6Btn:
			this.toTransfer(5);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.doExit();
	}
	
	private void toTransfer(int i ) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("MENU", i);
		startActivity(intent);
	}
	
	private void doExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_question).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
        	   android.os.Process.killProcess(android.os.Process.myPid());
           }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int id) {
       		}
        });
        builder.show();
	}
	
}
