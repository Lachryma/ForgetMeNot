package com.redevs.forgetmenot.classes;

import java.util.ArrayList;
import java.util.Hashtable;

import com.redevs.forgetmenot.MainActivity;
import com.redevs.forgetmenot.R;
import com.redevs.forgetmenot.globals.Globals;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private MainActivity activity;
	private ArrayList<Contact> data;
	private static LayoutInflater inflater = null;
	private static final String TAG = "ADAPTER";
	
	public LazyAdapter(MainActivity a, ArrayList<Contact> d, AlertDialog.Builder b) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void add(Contact c){
		data.add(c);
		notifyDataSetChanged();
	}
	
	public void remove(int position){
		data.remove(position);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView == null)
			vi = inflater.inflate(R.layout.listitem, null);
		
		TextView name = (TextView) vi.findViewById(R.id.nameText);
		ImageView expander = (ImageView) vi.findViewById(R.id.expandImage);
		ImageView caller = (ImageView) vi.findViewById(R.id.callImage);
		ImageView messager = (ImageView) vi.findViewById(R.id.messageImage);
		
		expander.setClickable(true);
		caller.setClickable(true);
		messager.setClickable(true);
		
		Contact c = new Contact();
		c = data.get(position);
		
		//Set Values and Tags
		name.setText(c.getName());
		Hashtable<Object, Object> table = new Hashtable<Object, Object>();
		table.put(0, c);
		table.put(1, position);
		vi.setTag(table);
		caller.setTag(c);
		expander.setTag(c);
		messager.setTag(c);
		
		vi.setOnLongClickListener(new OnLongClickListener(){

			@Override
			public boolean onLongClick(View v) {
				Hashtable<Object, Object> table = (Hashtable<Object, Object>) v.getTag();
				Contact contactTag = (Contact) table.get(0);
				int pos = (Integer) table.get(1);
				activity.makeListDialog(pos, contactTag);
				return false;
			}
			
		});
		
		
		
		expander.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				 Contact contactTag = (Contact) v.getTag();
				Log.d(TAG, "Expand for " + contactTag.getName());
			}
			
		});
		
		caller.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Contact contactTag = (Contact) v.getTag();
				activity.makeDialog(Globals.CALL_DIALOG, contactTag);
			}
			
		});
		
		messager.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Contact contactTag = (Contact) v.getTag();
				activity.makeDialog(Globals.SMS_DIALOG, contactTag);
			}
			
		});
		
		return vi;
	}
}
