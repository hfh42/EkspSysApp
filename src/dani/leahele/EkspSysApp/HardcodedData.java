package dani.leahele.EkspSysApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dani.leahele.EkspSysApp.Calender.Event;
import dani.leahele.EkspSysApp.Fun.Contact;

public class HardcodedData {

	private List<Contact> contacts = new ArrayList<Contact>();
	private List<Event> events = new ArrayList<Event>();

	public HardcodedData(File fileDir) {
		File[] files = fileDir.listFiles();
		if(files.length > 0){
			return;
		}
		
		createHardcodedContacts();
		createHardcodedEvents();

		File contactsDir = new File(fileDir, "contacts");
		File eventsDir = new File(fileDir, "events");
		contactsDir.mkdir();
		eventsDir.mkdir();

		for (Contact c : contacts) {
			c.save(contactsDir);
		}
		for(Event e : events){
			e.save(eventsDir);
		}
	}

	private void createHardcodedEvents() {
		Event e1 = new Event("Vin smagning", "3. juni", 154, "kl. 15:00",
				"25. maj", 20, "Vi m�des foran Resturant Substabs kl. 14.50");
		Event e2 = new Event("Fredagsbar", "20. juni", 171, "kl. 14:00", "", 0,
				"Fredags cafe ;)");

		e1.register("Louise Jensen");
		e1.register("Niels Hansen");
		e2.register("Martin Winter");
		e1.maybeRegister("Lone Larsen");
		e2.maybeRegister("George Gren");

		events.add(e1);
		events.add(e2);
		Collections.sort(events);
	}

	private void createHardcodedContacts() {
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
