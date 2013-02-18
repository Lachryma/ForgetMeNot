package com.redevs.forgetmenot.classes;

import com.redevs.forgetmenot.MainActivity;
import com.redevs.forgetmenot.globals.Globals;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

public class DialogOnClickListener implements DialogInterface.OnClickListener {
	    private int id, type;
	    private Contact c;
	    private MainActivity activity;

	    public DialogOnClickListener(int id, int type, Contact c, MainActivity a) {
	       this.id = id;
	       this.type = type;
	       this.c = c;
	       this.activity = a;
	    }

	    public void onClick(DialogInterface dialog, int which) {
	            switch(id){
	            case Globals.CALL_DIALOG:
	            	if(type == Globals.DIALOG_POSITIVE)
						activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
		                        + c.getPhoneNumber())));
	            	dialog.dismiss();
	            	break;
	            case Globals.SMS_DIALOG:	
	            	if(type == Globals.DIALOG_POSITIVE)
						activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
		                        + c.getPhoneNumber())));
	            	dialog.dismiss();
	            	break;
	            }
	        }

	}
