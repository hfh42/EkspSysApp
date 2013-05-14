package dani.leahele.EkspSysApp;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		TextView comment = new TextView(this);
		TextView comment2 = new TextView(this);
		TextView commentsStatus = (TextView) findViewById(R.id.frag_home_show_comment_svamp);
		LinearLayout.LayoutParams params;
		if (commentShown == false) {
			comment.setText("LUISE JENSEN : Rigtig dejlig tur :)");
			int c = Color.parseColor("#EBDDE2");
			comment.setBackgroundColor(c);
			comment2.setText("MADS PEDERSEN: Ja, super arrangement");
			comment2.setBackgroundColor(c);
			ll.addView(comment);
			ll.addView(comment2);
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

	public void writeCommentSvamp(View view) {
		Intent intent = new Intent(this, WriteActivity.class);
		startActivityForResult(intent, WriteComment);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println("onActivityResult");
		if (requestCode == WriteComment) {
			System.out.println("request code");
			if (resultCode == RESULT_OK) {
				System.out.println("result code");
				final String newComment = data
						.getStringExtra("dani.leahele.EkspSysApp.comment");
				this.newComment = newComment;

				LinearLayout ll = (LinearLayout) findViewById(R.id.frag_home_comments);
				TextView comment = new TextView(this);
				if (commentShown == false) {
					showCommentSvamp(comment);
					comment.setText(newComment);
					int c = Color.parseColor("#EBDDE2");
					comment.setBackgroundColor(c);
					ll.addView(comment);
				} else {
					comment.setText(newComment);
					int c = Color.parseColor("#EBDDE2");
					comment.setBackgroundColor(c);
					ll.addView(comment);

				}
			}
		}
	}

}
