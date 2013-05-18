package dani.leahele.EkspSysApp.Fun;

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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import dani.leahele.EkspSysApp.MainActivity;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Calender.CalenderActivity;
import dani.leahele.EkspSysApp.Fun.ContactListFragment.OnContactSelectedListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FunActivity extends Activity implements OnContactSelectedListener,
		OnMenuItemClickListener {

	private List<Contact> contacts = new ArrayList<Contact>();

	private ContactListFragment contactfrag;

	private File contactsDir;

	private int position = -1;

	private static final int NORMAL_CLICK = 1;
	private static final int LONG_CLICK = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fun);

		contactsDir = new File(getFilesDir(), "contacts");

		FragmentManager fragmentManager = getFragmentManager();

		contactfrag = (ContactListFragment) fragmentManager
				.findFragmentById(R.id.fun_list_fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fun, menu);
		return true;
	}

	@Override
	public void onStart() {
		super.onStart();
		loadContacts();
	}

	public void gotoHome(View view) {
		for (Contact c : contacts) {
			c.save(contactsDir);
		}
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void gotoCalender(View view) {
		for (Contact c : contacts) {
			c.save(contactsDir);
		}
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onContactSelected(int position, View view) {
			this.position = position;
			PopupMenu popup = new PopupMenu(this, view);
			popup.setOnMenuItemClickListener(this);
			popup.inflate(R.menu.contact_chosen);
			popup.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.contact_menu_favorit:
			Contact c = contacts.get(position);
			c.setFavorite();
			Collections.sort(contacts);
			contactfrag.setContactList(contacts);

			for (Contact con : contacts) {
				con.save(contactsDir);
			}
			
			return true;
		default:
			return false;
		}
	}

	private void loadContacts() {
		// Clear data
		contacts = new ArrayList<Contact>();

		File fileDir = getFilesDir();
		File[] files1 = fileDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.equals("contacts");
			}
		});

		// Check whether there we have any files
		if (files1.length == 0) {
			System.out.println("ERROR: contactsDir does not exists");
			return;
		}

		File contactsDir = files1[0];
		File[] files2 = contactsDir.listFiles();

		if (files2.length == 0) {
			System.out.println("ERROR: no contacts");
			return;
		}

		// Get the saved recipes and add them to the recipe list
		FileInputStream fin;
		try {
			for (File f : files2) {
				fin = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fin);
				Contact c = (Contact) in.readObject();
				in.close();
				contacts.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Collections.sort(contacts);
		contactfrag.setContactList(contacts);
		System.out.println("Fun, load, contacts: " + contacts);
	}

}
