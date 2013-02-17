package com.redevs.forgetmenot;

import java.util.ArrayList;

import com.redevs.forgetmenot.classes.Contact;
import com.redevs.forgetmenot.classes.LazyAdapter;
import com.redevs.forgetmenot.sqlite.DatabaseHandler;

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
    	
    	adapter = new LazyAdapter(this, contactList, builder);
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
    
    private void makeToast(String str){
		Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
		toast.show();
    }  
}
