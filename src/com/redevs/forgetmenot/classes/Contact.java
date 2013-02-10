package com.redevs.forgetmenot.classes;

public class Contact {
	
	//private variables
	int id;
	String name;
	String phoneNumber;
	String lastContact;
	String reminder;
	
	//Empty Constructor
	public Contact(){
		
	}
	
	//Constructor
	public Contact(int id, String name, String phoneNumber,
			String lastContact, String reminder){
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.lastContact = lastContact;
		this.reminder = reminder;
	}
	
	//Non date constructor
	public Contact(int id, String name, String phoneNumber){
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.id = id;
	}

	//Getters and Setters
	public int getID(){
		return this.id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getPhoneNumber(){
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getLastContact(){
		return this.lastContact;
	}
	
	public void setLastContact(String lastContact){
		this.lastContact = lastContact;
	}
	
	public String getReminder(){
		return this.reminder;
	}
	
	public void setReminder(String reminder){
		this.reminder = reminder;
	}
}
