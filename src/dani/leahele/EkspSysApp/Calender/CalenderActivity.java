package dani.leahele.EkspSysApp.Calender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
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
import dani.leahele.EkspSysApp.MainActivity;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Calender.EventListFragment.OnEventSelectedListener;
import dani.leahele.EkspSysApp.Fun.FunActivity;

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

		File fileDir = getFilesDir();
		File[] files1 = fileDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.equals("events");
			}
		});

		// Check whether there we have any files
		if (files1.length == 0) {
			System.out.println("ERROR: eventsDir does not exists");
			return;
		}

		File contactsDir = files1[0];
		File[] files2 = contactsDir.listFiles();

		if (files2.length == 0) {
			System.out.println("ERROR: no events");
			return;
		}

		// Get the saved recipes and add them to the recipe list
		FileInputStream fin;
		try {
			for (File f : files2) {
				fin = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fin);
				Event e = (Event) in.readObject();
				in.close();
				events.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Collections.sort(events);
		eventfrag.setEventList(events);
	}

}
