package dani.leahele.EkspSysApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dani.leahele.EkspSysApp.Calender.Event;
import dani.leahele.EkspSysApp.Fun.Contact;

public class HardcodedData {

	private File fileDir;

	private List<Contact> contacts = new ArrayList<Contact>();
	private List<Event> events = new ArrayList<Event>();
	private List<String> commentsSvamp = new ArrayList<String>();
	private List<String> commentsBio = new ArrayList<String>();

	public HardcodedData(File fileDir) {
		this.fileDir = fileDir;

		createHardcodedCommentsSvamp();
		createHardcodedCommentsBio();
		createHardcodedContacts();
		createHardcodedEvents();

		File contactsDir = new File(fileDir, "contacts");
		File eventsDir = new File(fileDir, "events");
		contactsDir.mkdir();
		eventsDir.mkdir();

		for (Contact c : contacts) {
			c.save(contactsDir);
		}
		for (Event e : events) {
			e.save(eventsDir);
		}

		saveComments(commentsSvamp, Constants.SVAMP_COM);
		saveComments(commentsBio, Constants.BIO_COM);
	}

	private void createHardcodedCommentsSvamp() {
		String c1Svamp = new String("LUISE JENSEN: Rigtig dejlig tur :)");
		String c2Svamp = new String("MADS PEDERSEN: Ja, super arrangement");
		commentsSvamp.add(c1Svamp);
		commentsSvamp.add(c2Svamp);
	}

	private void createHardcodedCommentsBio() {
		String c1 = new String("NIELS HANSEN: Fed film, glæder mig til 2'eren");
		String c2 = new String(
				"MADS PEDERSEN: Ja, og fantastisk med uventet plot");
		String c3 = new String("LONE LARSEN: Dejligt med så mange deltager");
		commentsBio.add(c1);
		commentsBio.add(c2);
		commentsBio.add(c3);
	}

	private void createHardcodedEvents() {
		Event e1 = new Event("Vin smagning", "3. jun.", 154, "kl. 15:00",
				"25. maj", 20, "Vi mødes foran Resturant Substabs kl. 14.50");
		Event e2 = new Event("Fredagsbar", "20. jun.", 171, "kl. 14:00", "", 0,
				"Fredags cafe ;)");
		Event e3 = new Event(
				"Foredrag: Eksistens og arbejdsliv",
				"16. sep.",
				259,
				"kl. 19:00",
				"",
				0,
				"Foredrag med Tommy Kjær Lassen om eksisten og arbejdsliv. "
						+ "Der vil til arrangementet blive serveret kaffe, te og kage!");
		Event e4 = new Event("Sommerfest", "6. aug.", 218, "kl. 17:30",
				"20. jul.", 50, "Den årlige sommerfest med spisning");
		Event e5 = new Event("Biograftur: En dans på roser", "19. sep.", 262,
				"kl. 18:30", "", 0,
				"Vi mødes foran CinemaxX i Bruuns galleri kl. 18:30, filmen starter kl. 19:15");

		e1.register("Louise Jensen");
		e1.register("Niels Hansen");
				
		e2.register("Martin Winter");
		
		e3.register("Loise Jensen");
		e3.register("George Gren");
		e3.register("Lone Larsen");
		e3.register("Martin Winter");
		e3.register("Harry Storm");
		
		e4.register("Marten Winter");
		e4.register("Harry Storm");

		e5.register("Harry Storm");
		e5.register("Loise Jensen");
		e5.register("George Gren");
		e5.register("Martin Winter");
		e5.register("Lone Larsen");
		
		e1.maybeRegister("Lone Larsen");
		
		e2.maybeRegister("George Gren");
		
		e3.maybeRegister("Solvej Sommer");
		e3.maybeRegister("Lykke Larsen");
		e3.maybeRegister("Frank Gren");
		e3.maybeRegister("Ravn Holm");
		
		e4.maybeRegister("Louise Jensen");
		
		e5.maybeRegister("Frank Thomsen");
		e5.maybeRegister("Ravn Holm");

		events.add(e1);
		events.add(e2);
		events.add(e3);
		events.add(e4);
		events.add(e5);
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

		Contact c7 = new Contact("Holger Gren", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c7.setOnline(true);
		
		Contact c8 = new Contact("Ivan Sommer", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c8.setOnline(true);
		c8.setFavorite();
		
		Contact c9 = new Contact("Liv Klausen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c9.setFavorite();
		
		Contact c10 = new Contact("Palle Nielsen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c10.setFavorite();
		c10.setOnline(true); 
		
		Contact c11 = new Contact("Hans Hansen", R.drawable.smiley_online,
				R.drawable.smiley_offline);

		Contact c12 = new Contact("Ole Holm", R.drawable.smiley_online,
				R.drawable.smiley_offline);

		Contact c13 = new Contact("Lykke Hansen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c13.setOnline(true);
		
		Contact c14 = new Contact("Klaus Olesen", R.drawable.smiley_online,
				R.drawable.smiley_offline);
		c14.setFavorite();
		
		contacts.add(c1);
		contacts.add(c2);
		contacts.add(c3);
		contacts.add(c4);
		contacts.add(c5);
		contacts.add(c6);
		contacts.add(c7);
		contacts.add(c8);
		contacts.add(c9);
		contacts.add(c10);
		contacts.add(c11);
		contacts.add(c12);
		contacts.add(c13);
		contacts.add(c14);

		Collections.sort(contacts);
	}

	private void saveComments(List<String> comments, String name) {
		// Create file
		File recipefile = new File(fileDir, name);

		// Save comments in file
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(recipefile);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(comments);
			out.close();
			System.out.println("Hardcode, comments saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
