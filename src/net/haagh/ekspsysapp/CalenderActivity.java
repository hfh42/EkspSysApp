package net.haagh.ekspsysapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class CalenderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calender, menu);
		return true;
	}

	public void gotoHome(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);		
	}
	
	public void gotoFun(View view){
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);		
	}

}