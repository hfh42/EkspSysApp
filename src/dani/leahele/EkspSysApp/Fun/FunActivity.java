package dani.leahele.EkspSysApp.Fun;

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
import android.view.View;
import android.widget.PopupMenu;
import dani.leahele.EkspSysApp.MainActivity;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Calender.CalenderActivity;
import dani.leahele.EkspSysApp.Fun.ContactListFragment.OnContactSelectedListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FunActivity extends Activity implements OnContactSelectedListener {

	private List<Contact> contacts = new ArrayList<Contact>();
	
	private ContactListFragment contactfrag;
	
	private static final int NORMAL_CLICK = 1;
	private static final int LONG_CLICK = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fun);

		createHardCodedContacts();
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
		contactfrag.setContactList(contacts);
	}

	public void gotoHome(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void gotoCalender(View view) {
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	@Override
	public void onContactSelected(int position, int type, View view) {
		if (type == NORMAL_CLICK) {
			PopupMenu popup = new PopupMenu(this, view);
			MenuInflater inflater = popup.getMenuInflater();
			inflater.inflate(R.menu.contact_chosen, popup.getMenu());
			popup.show();
		} else if (type == LONG_CLICK) {
			Contact c = contacts.get(position);
			c.setFavorite();
			Collections.sort(contacts);
			contactfrag.setContactList(contacts);
		}
	}

	private void createHardCodedContacts() {
		Contact c1 = new Contact("Louise Jensen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c1.setFavorite();

		Contact c2 = new Contact("Niels Hansen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c2.setFavorite();
		c2.setOnline(true);

		Contact c3 = new Contact("Martin Winter", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c3.setFavorite();

		Contact c4 = new Contact("Lone Larsen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c4.setOnline(true);

		Contact c5 = new Contact("George Gren", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c5.setOnline(true);

		Contact c6 = new Contact("Lykke Berg", R.drawable.smiley_online,
				R.drawable.smiley_offline);

		contacts.add(c1);
		contacts.add(c2);
		contacts.add(c3);
		contacts.add(c4);
		contacts.add(c5);
		contacts.add(c6);

		Collections.sort(contacts);
	}

}
