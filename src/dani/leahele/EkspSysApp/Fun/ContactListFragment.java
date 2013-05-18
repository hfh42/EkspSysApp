package dani.leahele.EkspSysApp.Fun;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupMenu.OnMenuItemClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ContactListFragment extends ListFragment {
	private OnContactSelectedListener mListener;
	private ContactAdapter adapter;
	
	public void setContactList(List<Contact> contacts){		
		setListAdapter(adapter);
		adapter.setData(contacts);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new ContactAdapter(getActivity());
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedState){
		super.onActivityCreated(savedState);
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnContactSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnContactSelectedListener");
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		getListView().setItemChecked(position, true);
		mListener.onContactSelected(position, v);
	}
	
	public interface OnContactSelectedListener{
		public void onContactSelected(int position, View view);
	}

}
