package dani.leahele.EkspSysApp.Calender;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import dani.leahele.EkspSysApp.FunActivity;
import dani.leahele.EkspSysApp.MainActivity;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Event.Event;

public class EventActivity extends Activity {
	private Event event;

	private boolean isRegistered = false;
	private boolean isMaybe = false;
	private String owner = "Vivi Pedersen";

	private LinearLayout registered;
	private LinearLayout maybes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);

		Intent intent = getIntent();
		event = (Event) intent
				.getSerializableExtra("dani.leahele.EkspSysApp.event");

		TextView title = (TextView) findViewById(R.id.event_title);
		TextView date = (TextView) findViewById(R.id.event_date);
		TextView time = (TextView) findViewById(R.id.event_time);
		TextView signup = (TextView) findViewById(R.id.event_frist);
		TextView info = (TextView) findViewById(R.id.event_info);

		title.setText(event.title);
		date.setText(event.date);
		time.setText(event.time);
		signup.setText(event.singupDate);
		info.setText(event.info);

		registered = (LinearLayout) findViewById(R.id.event_registered);
		maybes = (LinearLayout) findViewById(R.id.event_registered_maybe);

		addTextViews(registered, event.getRegistered());
		addTextViews(maybes, event.getMaybeRegistered());
	}

	private void addTextViews(LinearLayout ll, List<String> items) {
		ll.removeAllViews();
		for (String s : items) {
			TextView tv = new TextView(this);
			tv.setText(s);
			ll.addView(tv);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

	public void gotoHome(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	public void gotoCalender(View view) {
		Intent intent = new Intent(this, CalenderActivity.class);
		startActivity(intent);
	}

	public void gotoFun(View view) {
		Intent intent = new Intent(this, FunActivity.class);
		startActivity(intent);
	}

	public void signup(View view) {
		Button b = (Button) findViewById(R.id.event_signup);
		if (isRegistered) {
			if (event.price == 0) {
				event.unRegister(owner);
				addTextViews(registered, event.getRegistered());
				b.setText("Tilmeld");
			}
		} else {
			if (isMaybe) {
				event.unMaybeRegister(owner);
				addTextViews(maybes, event.getMaybeRegistered());
				isMaybe = !isMaybe;
			}
			event.register(owner);
			addTextViews(registered, event.getRegistered());
			b.setText("Afmeld");
		}
		isRegistered = !isRegistered;
	}

	public void signupMaybe(View view) {
		Button m = (Button) findViewById(R.id.event_signup_maybe);
		Button r = (Button) findViewById(R.id.event_signup);
		if (isMaybe) {
			event.unMaybeRegister(owner);
			addTextViews(maybes, event.getMaybeRegistered());
			
		} else {
			if (!isRegistered) {
				event.maybeRegister(owner);
				addTextViews(maybes, event.getMaybeRegistered());
			} else if (event.price == 0) {
				event.unRegister(owner);
				addTextViews(registered, event.getRegistered());
				r.setText("Tilmeld");
				isRegistered = !isRegistered;

				event.maybeRegister(owner);
				addTextViews(maybes, event.getMaybeRegistered());
			}
		}
		isMaybe = !isMaybe;

	}

}
