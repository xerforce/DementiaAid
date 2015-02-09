package com.example.models;

public class Monitoring_Weekly {

	private int idx;
	private int patient_id;
	private int location_id;
	private int date_weekday;
	private int place_frequency;
	private int duration;
	
	public Monitoring_Weekly(){
		
	}
	
	public Monitoring_Weekly(int patient_id, int location_id,
			int date_weekday, int place_frequency, int duration) {
		super();
//		this.idx = idx;
		this.patient_id = patient_id;
		this.location_id = location_id;
		this.date_weekday = date_weekday;
		this.place_frequency = place_frequency;
		this.duration = duration;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	public int getDate_weekday() {
		return date_weekday;
	}
	public void setDate_weekday(int date_weekday) {
		this.date_weekday = date_weekday;
	}
	public int getPlace_frequency() {
		return place_frequency;
	}
	public void setPlace_frequency(int place_frequency) {
		this.place_frequency = place_frequency;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	

}
