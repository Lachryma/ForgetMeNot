package com.redevs.forgetmenot.sqlite;

import java.util.ArrayList;

import com.redevs.forgetmenot.classes.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	//All Static Variables
	//Database Version
	private static final int DATABASE_VERSION = 1;
	
	//Database Name
	private static final String DATABASE_NAME = "contactsManager";
	
	//Contacts table name
	private static final String TABLE_CONTACTS = "contacts";
	
	//Contacts Table Columns names
	private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_LAST_CONTACT = "last_contact";
    private static final String KEY_REMINDER = "reminder";
    
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_LAST_CONTACT + " TEXT,"
                + KEY_REMINDER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
    }
    
 // Adding new contact
    public void addContact(Contact contact) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, contact.getName()); //Contact Name
    	values.put(KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number
    	values.put(KEY_LAST_CONTACT, contact.getLastContact()); //Contact last contacted
    	values.put(KEY_ID, contact.getID()); //Contact ID
    	values.put(KEY_REMINDER, contact.getReminder());
    	
    	//Inserting row
    	db.insert(TABLE_CONTACTS, null, values);
    	db.close(); //Closing db connection
    }
     
    // Getting single contact
    public Contact getContact(int id) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
    			KEY_NAME, KEY_PH_NO, KEY_LAST_CONTACT, KEY_REMINDER}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
    	
    	if(cursor != null)
    		cursor.moveToFirst();
    	
    	Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
    			cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
    	
    	//return contact
    	return contact;
    }
     
    // Getting All Contacts
    public ArrayList<Contact> getAllContacts() {
    	ArrayList<Contact> contactList = new ArrayList<Contact>();
    	//Select All Query
    	String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
    	
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor cursor = db.rawQuery(selectQuery, null);
    	
    	//looping through all rows and adding to list
    	if(cursor.moveToFirst()) {
    		do {
    			Contact contact = new Contact();
    			contact.setID(Integer.parseInt(cursor.getString(0)));
    			contact.setName(cursor.getString(1));
    	        contact.setPhoneNumber(cursor.getString(2));
    	        contact.setLastContact(cursor.getString(3));
    	        contact.setReminder(cursor.getString(4));
    	        // Adding contact to list
    	        contactList.add(contact);
    	        } while (cursor.moveToNext());
    	}
    	//return contact list
    	return contactList;
    }
     
    // Getting contacts Count
    public int getContactsCount() {
    	String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
    	SQLiteDatabase db = this.getReadableDatabase();
    	Cursor cursor = db.rawQuery(countQuery, null);
    	cursor.close();
    	
    	//return count
    	return cursor.getCount();
    }
    // Updating single contact
    public int updateContact(Contact contact) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_NAME, contact.getName()); //Contact Name
    	values.put(KEY_PH_NO, contact.getPhoneNumber()); //Contact Phone Number
    	values.put(KEY_LAST_CONTACT, contact.getLastContact()); //Contact last contacted
    	values.put(KEY_REMINDER, contact.getReminder());
    	
    	//updating row
    	return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
    			new String[] { String.valueOf(contact.getID()) });
    }
     
    // Deleting single contact
    public void deleteContact(Contact contact) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
    			new String[] { String.valueOf(contact.getID()) });
    	db.close();
    }
    
}
