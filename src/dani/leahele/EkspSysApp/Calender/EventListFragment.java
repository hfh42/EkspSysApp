package dani.leahele.EkspSysApp.Calender;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EventListFragment extends ListFragment {
	private OnEventSelectedListener mListener;
	private EventAdapter eAdapter;
	
	public void setEventList(List<Event> events){		
		setListAdapter(eAdapter);
		eAdapter.setData(events);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		eAdapter = new EventAdapter(getActivity());
		setListAdapter(eAdapter);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnEventSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnEventSelectedListener");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		getListView().setItemChecked(position, true);
		mListener.onEventSelected(position);
	}
	
	public interface OnEventSelectedListener{
		public void onEventSelected(int position);
	}
	
}
