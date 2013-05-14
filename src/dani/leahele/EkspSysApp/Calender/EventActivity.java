package dani.leahele.EkspSysApp.Calender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import dani.leahele.EkspSysApp.FunActivity;
import dani.leahele.EkspSysApp.MainActivity;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Event.Event;

public class EventActivity extends Activity {
	private Event event;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		
		Intent intent = getIntent();
		event = (Event) intent.getSerializableExtra("dani.leahele.EkspSysApp.event");
		
		TextView title = (TextView) findViewById(R.id.event_title);
		TextView date = (TextView) findViewById(R.id.event_date);
		TextView time = (TextView) findViewById(R.id.event_time);
		TextView signup = (TextView) findViewById(R.id.event_signup);
		TextView info = (TextView) findViewById(R.id.event_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
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

}
