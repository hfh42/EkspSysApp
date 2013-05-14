package dani.leahele.EkspSysApp.Event;

import java.util.ArrayList;

public class Event {
	
	public final String title;
	public final String date;
	public final String singupDate;
	public final int price;
	public final String info;
	
	private ArrayList<String> registered = new ArrayList<String>();
	
	public Event(String title, String date, String signupDate, int price, String info){
		this.title = title;
		this.date = date;
		this.singupDate = signupDate;
		this.price = price;
		this.info = info;
	}
	
	public void register(String name){
		registered.add(name);
	}
	
	public ArrayList<String> getRegistered(){
		return registered;
	}
	
	public int numOfRegistered(){
		return registered.size();
	}

}
