package com.redevs.forgetmenot;

import java.util.ArrayList;

import com.redevs.forgetmenot.classes.*;
import com.redevs.forgetmenot.sqlite.DatabaseHandler;
import com.redevs.forgetmenot.globals.Globals;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ListView listLayout_;
	LazyAdapter adapter;
	AlertDialog.Builder builder;
	DialogOnClickListener listener;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DatabaseHandler db = new DatabaseHandler(this);
        // Insert Dummy Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact(0, "Kyle Dausin", "9100000000", "12/12/2012", "12/12/2012"));
        db.addContact(new Contact(1, "Alex Crees", "9199999999", "12/12/2012", "12/12/2012"));
        db.addContact(new Contact(2, "Brian Voorhees", "9522222222", "12/12/2012", "12/12/2012"));
        db.addContact(new Contact(3, "Dan Rolph", "9533333333", "12/12/2012", "12/12/2012"));
        
        listLayout_ = (ListView) findViewById(R.id.contactList);
        
    	ArrayList<Contact> contactList = db.getAllContacts();

    	builder = new AlertDialog.Builder(this);
    	
    	adapter = new LazyAdapter(MainActivity.this, contactList, builder);
    	listLayout_.setAdapter(adapter);
    	
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.menu_add:
    		menuAdd();
    		return true;
    	case R.id.menu_remove:
    		menuRemove();
    		return true;
    	case R.id.menu_settings:
    		menuSettings();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    private void menuAdd(){
    	makeToast("Menu Add Pressed");
    	
    }
    
    private void menuRemove(){
    	makeToast("Menu Remove Pressed");
    }
    
    private void menuSettings(){
    	makeToast("Menu Settings Pressed");
    } 
    
    public void makeToast(String str){
		Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
		toast.show();
    }  
    
    public void makeDialog(int type, Contact c){
		String message = null, positiveButton = "Okay", title = null, negativeButton = "Cancel";
    	switch(type){
    	case Globals.CALL_DIALOG:
    		message = "Do you wish to call " + c.getName() + "?";
    		title = "Call";
        	builder.setPositiveButton(positiveButton, new DialogOnClickListener(Globals.CALL_DIALOG, Globals.DIALOG_POSITIVE, c, this));
        	builder.setNegativeButton(negativeButton, new DialogOnClickListener(Globals.CALL_DIALOG, Globals.DIALOG_NEGATIVE, c, this));
        	break;
    	case Globals.SMS_DIALOG:
    		message = "Do you wish to SMS " + c.getName() + "?";
    		title = "SMS";
        	builder.setPositiveButton(positiveButton, new DialogOnClickListener(Globals.SMS_DIALOG, Globals.DIALOG_POSITIVE, c, this));
        	builder.setNegativeButton(negativeButton, new DialogOnClickListener(Globals.SMS_DIALOG, Globals.DIALOG_NEGATIVE, c, this));
        	break;
    	}
    	
    	builder.setTitle(title);
    	builder.setMessage(message);
    	
    	builder.show();
    	
    }
}
