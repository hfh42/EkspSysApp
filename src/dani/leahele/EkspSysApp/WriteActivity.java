package dani.leahele.EkspSysApp;

import dani.leahele.EkspSysApp.Calender.CalenderActivity;
import dani.leahele.EkspSysApp.Fun.FunActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class WriteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.write, menu);
		return true;
	}
	
	public void gotoHome(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void gotoCalender(View view) {
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	public void gotoFun(View view) {
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);
	}
	
	public void sendComment(View view){
		TextView comment = (TextView) findViewById(R.id.write_comment);
		String s = comment.getText().toString();
		Intent i = new Intent();
		i.putExtra("dani.leahele.EkspSysApp.comment", s );
		setResult(RESULT_OK, i);
		finish();
	}

}
