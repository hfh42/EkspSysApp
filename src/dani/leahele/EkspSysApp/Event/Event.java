package dani.leahele.EkspSysApp.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Event implements Serializable{
	
	private static final long serialVersionUID = -5613769324766155412L;
	
	public final String title;
	public final String date;
	public final String time;
	public final String singupDate;
	public final int price;
	public final String info;
	
	public boolean isRegistered;
	public boolean isMaybe;
		
	private List<String> registered = new ArrayList<String>();
	private List<String> maybes = new ArrayList<String>();
	
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
	
	public void unRegister(String name){
		registered.remove(name);
	}
	
	public List<String> getRegistered(){
		return registered;
	}
	
	public int numOfRegistered(){
		return registered.size();
	}
	
	public void maybeRegister(String name){
		maybes.add(name);
	}
	
	public void unMaybeRegister(String name){
		maybes.remove(name);
	}
	
	public List<String> getMaybeRegistered(){
		return maybes;
	}
	
}
