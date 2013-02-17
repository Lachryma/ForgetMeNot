package com.redevs.forgetmenot.classes;

import java.util.ArrayList;

import com.redevs.forgetmenot.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<Contact> data;
	private static LayoutInflater inflater = null;

	private static final String TAG = "ADAPTER";
	
	public LazyAdapter(Activity a, ArrayList<Contact> d, AlertDialog.Builder b) {
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
		caller.setTag(c);
		expander.setTag(c);
		messager.setTag(c);
		
		
		
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
				Log.d(TAG, "Call for " + contactTag.getName());
				activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
						+ contactTag.getPhoneNumber())));
			}
			
		});
		
		messager.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Contact contactTag = (Contact) v.getTag();
				Log.d(TAG, "Message for " + contactTag.getName());
				activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + contactTag.getPhoneNumber())));
			}
			
		});
		
		return vi;
	}
}