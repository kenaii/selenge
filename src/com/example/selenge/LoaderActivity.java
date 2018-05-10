package com.example.selenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loader);
	}
     
    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                	Intent intent = new Intent(getApplicationContext(), MenuActivity.class); 
                	startActivity(intent); 	
                }
            }
        };
        timer.start();
	}

}
