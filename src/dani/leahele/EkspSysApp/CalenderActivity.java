package dani.leahele.EkspSysApp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import dani.leahele.EkspSysApp.Event.Event;

public class CalenderActivity extends Activity {

	private List<Event> events = new ArrayList<Event>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);
		
		createHardcodedEvents();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calender, menu);
		return true;
	}

	public void gotoHome(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void gotoFun(View view) {
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);
	}

	private void createHardcodedEvents() {
		Event e1 = new Event("Vin smagning", "3. juni 2013, kl. 15:00",
				"25. maj", 20, "Vi mødes foran Resturant Substabs kl. 14.50");
		Event e2 = new Event("Fredagsbar", "20. juni,  kl. 14:00", "", 0,
				"Fredags cafe ;)");

		events.add(e1);
		events.add(e2);
	}

}
