package dani.leahele.EkspSysApp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	Boolean commentShown = false;
	private static final int WriteComment = 3;
	String newComment;
	ArrayList<String> comments = new ArrayList<String>(); 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hardCodedCommentsSvamp();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void gotoCalender(View view) {
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	public void gotoFun(View view) {
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);
	}

	public void showCommentSvamp(View view) {
		LinearLayout ll = (LinearLayout) findViewById(R.id.frag_home_comments);
		TextView commentsStatus = (TextView) findViewById(R.id.frag_home_show_comment_svamp);
		LinearLayout.LayoutParams params;
		
		int color = Color.parseColor("#EBDDE2");
		
		if (commentShown == false) {
			for (String s : comments){
				TextView c = new TextView(this);
				c.setText(s);
				c.setBackgroundColor(color);
				ll.addView(c);
			}
			
			commentsStatus.setText("Skjul kommentar");
			params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(15, 0, 15, 10);
			ll.setLayoutParams(params);
			commentShown = true;
		} else {
			ll.removeAllViewsInLayout();
			commentsStatus.setText("Vis kommentar");
			params = new LinearLayout.LayoutParams(0, 0);
			ll.setLayoutParams(params);
			commentShown = false;
		}

	}
	
	public void hardCodedCommentsSvamp(){
		String c1Svamp = new String("LUISE JENSEN : Rigtig dejlig tur :)");
		String c2Svamp = new String("MADS PEDERSEN: Ja, super arrangement");
		comments.add(c1Svamp);
		comments.add(c2Svamp);
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
				comments.add(newComment);
				LinearLayout ll = (LinearLayout) findViewById(R.id.frag_home_comments);	
				showCommentSvamp(new View(this));	
			}
		}
	}
}
