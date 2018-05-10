package com.example.selenge;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {
	
	private Animation move2left; 
	private Animation move2right;
	
	private RelativeLayout menuLayout;
	private RelativeLayout overlayLayout;
	private TextView titleTv;
	private Typeface tf1;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.init();
		if (savedInstanceState == null) {
			displayView(getIntent().getIntExtra("MENU", 0));
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	protected void init() {
		this.tf1 = Typeface.createFromAsset(getAssets(), "fonts/AGFuturaMon.ttf");
		
		move2left = AnimationUtils.loadAnimation(this, R.anim.move2left);
		move2right = AnimationUtils.loadAnimation(this, R.anim.move2right);
		
		menuLayout = (RelativeLayout)findViewById(R.id.menuLayout);
		menuLayout.setVisibility(8);
		
		overlayLayout = (RelativeLayout)findViewById(R.id.overlayLayout);
		overlayLayout.setOnClickListener(this);
		overlayLayout.setVisibility(8);	
		
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
		
		RelativeLayout headerRl = (RelativeLayout) findViewById(R.id.headerRl);
		headerRl.setOnClickListener(this);
		ImageButton navBtn = (ImageButton) findViewById(R.id.navBtn);
		navBtn.setOnClickListener(this);		
	
		this.titleTv = (TextView) findViewById(R.id.titleTv);
		this.titleTv.setTypeface(tf1);
		TextView footerTv = (TextView) findViewById(R.id.footerTv);
		footerTv.setTypeface(tf1);
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.navBtn:
			this.slideMenu(move2right, 0);
			break;
		case R.id.headerRl:
			this.slideMenu(move2right, 0);
			break;
		case R.id.overlayLayout:
			this.slideMenu(move2left, 8);
			break;
		case R.id.menu1Btn:
			this.displayView(0);
			this.slideMenu(move2left, 8);
			break;
		case R.id.menu2Btn:
			this.displayView(1);
			this.slideMenu(move2left, 8);
			break;
		case R.id.menu3Btn:
			this.displayView(2);
			this.slideMenu(move2left, 8);
			break;
		case R.id.menu4Btn:
			this.displayView(3);
			this.slideMenu(move2left, 8);
			break;			
		case R.id.menu5Btn:
			this.displayView(4);
			this.slideMenu(move2left, 8);
			break;
		case R.id.menu6Btn:
			this.displayView(5);
			this.slideMenu(move2left, 8);
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
	
	private void slideMenu(Animation animation, int visibility) {
		menuLayout.startAnimation(animation);
		menuLayout.setVisibility(visibility);
		overlayLayout.setVisibility(visibility);
	}

	private void displayView(int position) {
		Fragment fragment = null;
		int titleId = 0;
		switch (position) {
		case 0:
			fragment = new IntroduceFragment();
			titleId = R.string.menu1;
			break;
		case 1:
			fragment = new ServiceFragment();
			titleId = R.string.menu2;
			break;
		case 2:
			fragment = new InfoFragment();
			titleId = R.string.menu3;
			break;
		case 3:
			fragment = new GalleryFragment();
			titleId = R.string.menu4;
			break;
		case 4:
			fragment = new SuggestFragment();
			titleId = R.string.menu5;
			break;
		case 5:
			fragment = new ContactFragment();
			titleId = R.string.menu6;
			break;
		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.containerFl, fragment).commit();
			this.titleTv.setText(getResources().getString(titleId));
		}
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

	public static boolean isNetworkAvailable(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.init();
	}

}
