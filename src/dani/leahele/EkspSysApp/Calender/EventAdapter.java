package dani.leahele.EkspSysApp.Calender;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import dani.leahele.EkspSysApp.Constants;
import dani.leahele.EkspSysApp.R;
import dani.leahele.EkspSysApp.Event.Event;

public class EventAdapter extends ArrayAdapter<Event> {
	private final LayoutInflater mInflater;

	public EventAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_2);

		// Context.LAYOUT_INFLATER_SERVICE is used with getSystemService(String)
		// to retrieve a LayoutInflater for inflating layout resources in this
		// context.
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<Event> data) {
		clear();
		if (data != null) {
			for (Event appEntry : data) {
				add(appEntry);
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

		if (convertView == null) {
			view = mInflater.inflate(R.layout.single_event, parent, false);
		} else {
			view = convertView;
		}

		Event item = getItem(position);
		((TextView) view.findViewById(R.id.single_event_date))
				.setText(item.date);
		TextView title = (TextView) view.findViewById(R.id.single_event_title);
		title.setText(item.title);

		for (String s : item.getRegistered()) {
		/*	System.out.println("!!!!!! title: " + item.title);
			System.out.println("!!!!!! owner: " + s.equals(Constants.OWNER));
			System.out.println("!!!!!! tilmeldte: " + item.getRegistered());*/
			if (s.equals(Constants.OWNER)) {
				System.out.println("!!!!!! tilmeldt");
				Typeface tf = Typeface.create("string", Typeface.BOLD);
				title.setTypeface(tf);
			}
		}

		return view;
	}

}
