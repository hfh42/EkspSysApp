package dani.leahele.EkspSysApp.Calender;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import dani.leahele.EkspSysApp.FunActivity;
import dani.leahele.EkspSysApp.MainActivity;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Calender.EventListFragment.OnEventSelectedListener;
import dani.leahele.EkspSysApp.Event.Event;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CalenderActivity extends Activity implements
		OnEventSelectedListener {

	private FragmentManager fragmentManager;

	private EventListFragment eventfrag;

	private List<Event> events = new ArrayList<Event>();

	private Event chosen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calender);
		
		createHardcodedEvents();
		fragmentManager = getFragmentManager();

		eventfrag = (EventListFragment) fragmentManager
				.findFragmentById(R.id.calender_list_fragment);

	}

	@Override
	public void onStart() {
		super.onStart();
		loadEvents();
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
		File folder = getFilesDir();
		if(folder.list().length > 0){
			return;
		}
		
		Event e1 = new Event("Vin smagning", "3. juni",154, "kl. 15:00", "25. maj",
				20, "Vi mødes foran Resturant Substabs kl. 14.50");
		Event e2 = new Event("Fredagsbar", "20. juni",171, "kl. 14:00", "", 0,
				"Fredags cafe ;)");

		e1.register("Louise Jensen");
		e1.register("Niels Hansen");
		e2.register("Martin Winter");
		e1.maybeRegister("Lone Larsen");
		e2.maybeRegister("George Gren");

		events.add(e1);
		events.add(e2);
		Collections.sort(events);

		File fileDir = getFilesDir();
		e1.save(fileDir);
		e2.save(fileDir);
	}

	@Override
	public void onEventSelected(int position) {
		Intent intent = new Intent(this, EventActivity.class);

		chosen = events.get(position);

		intent.putExtra("dani.leahele.EkspSysApp.event", chosen);
		startActivity(intent);
	}

	private void loadEvents() {
		// Clear data
		events = new ArrayList<Event>();

		// Get saved files containing recipes
		File fileDir = getFilesDir();
		File[] files = fileDir.listFiles();

		// Check whether there we have any files
		if (files.length != 0) {
			// Get the saved recipes and add them to the recipe list
			FileInputStream fin;
			try {
				for (File f : files) {
					fin = new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(fin);
					Event e = (Event) in.readObject();
					in.close();
					events.add(e);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collections.sort(events);
		eventfrag.setEventList(events);
	}

}
