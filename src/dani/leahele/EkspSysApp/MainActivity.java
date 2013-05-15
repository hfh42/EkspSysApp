package dani.leahele.EkspSysApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Boolean commentShown = false;
	private static final int WriteComment = 3;
	private ArrayList<String> commentsSvamp = new ArrayList<String>(); 
	private TextView showCommentButton;
	private LinearLayout commentsll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hardCodedCommentsSvamp();

		commentsll = (LinearLayout) findViewById(R.id.frag_home_comments);
		showCommentButton = (TextView) findViewById(R.id.frag_home_show_comment_svamp);
	}
	

	@Override
	public void onStart() {
		super.onStart();
		commentsSvamp = loadComments("svamp");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void gotoCalender(View view) {
		saveComments(commentsSvamp, "svamp");
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	public void gotoFun(View view) {
		saveComments(commentsSvamp, "svamp");
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);
	}

	public void showCommentSvamp(View view) {
		LinearLayout.LayoutParams params;
		
		if (commentShown == false) {
			showComment();
		} else {
			commentsll.removeAllViewsInLayout();
			showCommentButton.setText("Vis kommentar");
			params = new LinearLayout.LayoutParams(0, 0);
			commentsll.setLayoutParams(params);
			commentShown = false;
		}

	}

	private void showComment() {
		commentsll.removeAllViewsInLayout();
		int color = Color.parseColor("#EBDDE2");
		LinearLayout.LayoutParams params;
		for (String s : commentsSvamp){
			TextView c = new TextView(this);
			c.setText(s);
			c.setBackgroundColor(color);
			commentsll.addView(c);
		}
		
		showCommentButton.setText("Skjul kommentar");
		params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(15, 0, 15, 10);
		commentsll.setLayoutParams(params);
		commentShown = true;
	}
	
	
	public void hardCodedCommentsSvamp(){
		File fileDir = getFilesDir();
		if(fileDir.listFiles().length > 0){
			return;
		}
		
		String c1Svamp = new String("LUISE JENSEN : Rigtig dejlig tur :)");
		String c2Svamp = new String("MADS PEDERSEN: Ja, super arrangement");
		commentsSvamp.add(c1Svamp);
		commentsSvamp.add(c2Svamp);
		saveComments(commentsSvamp, "svamp");
	}

	public void writeCommentSvamp(View view) {
		Intent intent = new Intent(this, WriteActivity.class);
		startActivityForResult(intent, WriteComment);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == WriteComment) {
			if (resultCode == RESULT_OK) {
				final String newComment = data
						.getStringExtra("dani.leahele.EkspSysApp.comment");
				commentsSvamp.add(newComment);
				saveComments(commentsSvamp, "svamp");
				showComment();
			}
		}
	}
	
	private void saveComments(ArrayList<String> comments, String name) {
		System.out.println("save");
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
	
	private ArrayList<String> loadComments(String name) {
		System.out.println("Load");
		// Clear data
		ArrayList<String> comments = new ArrayList<String>();

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
			System.out.println("Files found");
			FileInputStream fin;
			try {
				for (File f : files) {
					fin = new FileInputStream(f);
					ObjectInputStream in = new ObjectInputStream(fin);
					comments = (ArrayList<String>) in.readObject();
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return comments;
	}
}
