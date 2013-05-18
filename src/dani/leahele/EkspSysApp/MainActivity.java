package dani.leahele.EkspSysApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import dani.leahele.EkspSysApp.Calender.CalenderActivity;
import dani.leahele.EkspSysApp.Calender.Event;
import dani.leahele.EkspSysApp.Calender.EventActivity;
import dani.leahele.EkspSysApp.Fun.FunActivity;

public class MainActivity extends Activity {
	private Boolean isSvampComShown = false;
	private Boolean isBioComShown = false;
	
	private List<String> commentsSvamp = new ArrayList<String>();
	private List<String> commentsBio = new ArrayList<String>();
	
	private TextView showSvampCommentButton;
	private LinearLayout svampll;
	private TextView showBioCommentButton;
	private LinearLayout bioll;

	private static final int WRITE_SVAMP_COMMENT = 3;
	private static final int WRITE_BIO_COMMENT = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		svampll = (LinearLayout) findViewById(R.id.frag_home_comments_svamp);
		showSvampCommentButton = (TextView) findViewById(R.id.frag_home_show_comment_svamp);
		
		bioll = (LinearLayout) findViewById(R.id.frag_home_comments_bio);
		showBioCommentButton = (TextView) findViewById(R.id.frag_home_show_comment_svamp);
	}

	@Override
	public void onStart() {
		super.onStart();
		commentsSvamp = loadComments(Constants.SVAMP_COM);
		commentsBio = loadComments(Constants.BIO_COM);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.main_menu_reset:
			resetUserData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public void resetUserData() {
		new HardcodedData(getFilesDir());
		commentsSvamp = loadComments(Constants.SVAMP_COM);
		commentsBio = loadComments(Constants.BIO_COM);
	}

	public void gotoCalender(View view) {
		saveComments(commentsSvamp, Constants.SVAMP_COM);
		saveComments(commentsBio, Constants.BIO_COM);
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	public void gotoFun(View view) {
		saveComments(commentsSvamp, Constants.SVAMP_COM);
		saveComments(commentsBio, Constants.BIO_COM);
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);
	}
	
	public void goToEvent(View view){
		Event vinEvent = loadVinEvent();
		
		if(vinEvent == null){
			System.out.println("Couldn't find vin event");
			return;
		}
		
		Intent intent = new Intent(this, EventActivity.class);		
		intent.putExtra("dani.leahele.EkspSysApp.event", vinEvent);
		startActivity(intent);
	}
	
	private Event loadVinEvent() {
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
			return null;
		}

		File contactsDir = files1[0];
		File[] files2 = contactsDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.equals("Vin smagning");
			}
		});

		if (files2.length == 0) {
			System.out.println("ERROR: no events");
			return null;
		}

		Event vinEvent = null;
		
		// Get the saved recipes and add them to the recipe list
		FileInputStream fin;
		try {
			for (File f : files2) {
				fin = new FileInputStream(f);
				ObjectInputStream in = new ObjectInputStream(fin);
				vinEvent = (Event) in.readObject();
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vinEvent;
	}

	public void showCommentSvamp(View view) {
		if (!isSvampComShown) {
			isSvampComShown = showComment(svampll, showSvampCommentButton, commentsSvamp);
		} else {
			isSvampComShown = hideComment(svampll, showSvampCommentButton);
		}

	}
	
	public void showCommentBio(View view){
		if(!isBioComShown){
			isBioComShown = showComment(bioll, showBioCommentButton, commentsBio);
		} else {
			isBioComShown = hideComment(bioll, showBioCommentButton);
		}
	}
	
	private boolean hideComment(LinearLayout ll, TextView hideCom){
		ll.removeAllViewsInLayout();
		hideCom.setText("Vis kommentar");
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
		ll.setLayoutParams(params);
		return false;
	}

	private boolean showComment(LinearLayout ll, TextView showCom, List<String> comments) {
		ll.removeAllViewsInLayout();
		int color = Color.parseColor("#EBDDE2");
		LinearLayout.LayoutParams params;
		for (String s : comments) {
			TextView c = new TextView(this);
			c.setText(s);
			c.setBackgroundColor(color);
			ll.addView(c);
		}

		showCom.setText("Skjul kommentar");
		params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(15, 0, 15, 10);
		ll.setLayoutParams(params);
		return true;
	}

	public void writeCommentSvamp(View view) {
		Intent intent = new Intent(this, WriteActivity.class);
		startActivityForResult(intent, WRITE_SVAMP_COMMENT);
	}
	
	public void writeCommentBio(View view){
		Intent intent = new Intent(this, WriteActivity.class);
		startActivityForResult(intent, WRITE_BIO_COMMENT);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == WRITE_SVAMP_COMMENT) {
			if (resultCode == RESULT_OK) {
				final String newComment = data
						.getStringExtra("dani.leahele.EkspSysApp.comment");
				
				commentsSvamp.add(Constants.OWNER_CAPS + ": " + newComment);
				saveComments(commentsSvamp, Constants.SVAMP_COM);
				
				isSvampComShown = showComment(svampll, showSvampCommentButton, commentsSvamp);
			}
		}
		if(requestCode == WRITE_BIO_COMMENT){
			if(resultCode == RESULT_OK){
				final String newComment = data
						.getStringExtra("dani.leahele.EkspSysApp.comment");

				commentsBio.add(Constants.OWNER_CAPS + ": " + newComment);		
				saveComments(commentsBio, Constants.BIO_COM);
				
				isBioComShown = showComment(bioll, showBioCommentButton, commentsBio);
			}
		}
	}

	private void saveComments(List<String> comments, String name) {

		// Create file
		File fileDir = getFilesDir();
		File recipefile = new File(fileDir, name);

		// Save comments in file
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(recipefile);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(comments);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> loadComments(String name) {
		
		// Clear data
		List<String> comments = new ArrayList<String>();

		// Get saved files containing recipes
		File fileDir = getFilesDir();

		final String fname = name;

		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return fname.equals(filename);
			}
		};

		File[] files = fileDir.listFiles(filter);

		// Check whether there we have any files
		if (files.length != 0) {
			FileInputStream fin;
			try {
				for (File f : files) {
					fin = new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(fin);
					comments = (List<String>) in.readObject();
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return comments;
	}
}
