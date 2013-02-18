package com.redevs.forgetmenot.classes;

import android.content.DialogInterface;

import com.redevs.forgetmenot.MainActivity;

public class ListDialogOnClickListener implements DialogInterface.OnClickListener {
    private Contact c;
    private MainActivity activity;
    private LazyAdapter adapter;
    private int position;

    public ListDialogOnClickListener(LazyAdapter adapter, Contact c, int p, MainActivity a) {
       this.c = c;
       this.activity = a;
       this.position = p;
       this.adapter = adapter;
    }

    public void onClick(DialogInterface dialog, int which) {
            if(which == 0)
            	adapter.remove(position);
            else if(which == 1)
            	activity.makeToast("edit " + c.getName());
            	
        }

}
