package dani.leahele.EkspSysApp.Fun;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import dani.leahele.EkspSysApp.R;

public class ContactAdapter extends ArrayAdapter<Contact> {
	private final LayoutInflater mInflater;

	public ContactAdapter(Context context) {
		super(context, android.R.layout.simple_list_item_2);

		// Context.LAYOUT_INFLATER_SERVICE is used with getSystemService(String)
		// to retrieve a LayoutInflater for inflating layout resources in this
		// context.
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(List<Contact> data) {
		clear();
		if (data != null) {
			for (Contact appEntry : data) {
				add(appEntry);
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;

		if (convertView == null) {
			view = mInflater.inflate(R.layout.single_contact, parent, false);
		} else {
			view = convertView;
		}

		Contact item = getItem(position);
		TextView name = (TextView) view.findViewById(R.id.single_contact_name);
		name.setText(item.name);
		
		ImageView image = (ImageView) view.findViewById(R.id.single_contact_image);
		if(item.isOnline()){
			image.setImageResource(R.drawable.smiley_online);
		}
		else{
			image.setImageResource(R.drawable.smiley_offline);
		}

		ImageView favorite = (ImageView) view.findViewById(R.id.single_contact_favorite);
		if(item.isFavorite()){
			favorite.setImageResource(R.drawable.yellow_star);
		}
		else{
			favorite.setImageResource(R.drawable.white_star_with_edge);
		}

		return view;
	}
}
