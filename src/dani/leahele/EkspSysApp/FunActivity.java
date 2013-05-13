package dani.leahele.EkspSysApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class FunActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fun);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fun, menu);
		return true;
	}

	public void gotoHome(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);		
	}

	public void gotoCalender(View view){
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);		
	}

}
