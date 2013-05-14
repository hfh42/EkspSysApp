package dani.leahele.EkspSysApp.Event;

import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable{
	
	private static final long serialVersionUID = -5613769324766155412L;
	
	public final String title;
	public final String date;
	public final String time;
	public final String singupDate;
	public final int price;
	public final String info;
	
	private ArrayList<String> registered = new ArrayList<String>();
	
	public Event(String title, String date, String time, String signupDate, int price, String info){
		this.title = title;
		this.date = date;
		this.time = time;
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
