package com.redevs.forgetmenot;

import java.util.List;

import com.redevs.forgetmenot.classes.Contact;
import com.redevs.forgetmenot.sqlite.DatabaseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//Variables
	LinearLayout listLayout_;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        DatabaseHandler db = new DatabaseHandler(this);
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact(0, "Ravi", "9100000000", "12/12/2012", "12/12/2012"));
        db.addContact(new Contact(1, "Srinivas", "9199999999", "12/12/2012", "12/12/2012"));
        db.addContact(new Contact(2, "Tommy", "9522222222", "12/12/2012", "12/12/2012"));
        db.addContact(new Contact(3, "Karthik", "9533333333", "12/12/2012", "12/12/2012"));
        
        listLayout_ = (LinearLayout) findViewById(R.id.listLayout);
        populateList();
        
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
    
    private void populateList(){
    	DatabaseHandler db = new DatabaseHandler(this);
    	List<Contact> contactList = db.getAllContacts();
    	
    	listLayout_.removeAllViews();
    	for(Contact cn : contactList){
    		
    	}

    }  
}
