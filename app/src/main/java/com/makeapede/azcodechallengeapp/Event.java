package com.makeapede.azcodechallengeapp;

public class Event {
	public String title;
	public String dateTime;
	public String location;
	public String details;
	public String link;

	public Event() {}

	public Event(String title, String dateTime, String location, String details, String link) {
		this.title = title;
		this.dateTime = dateTime;
		this.location = location;
		this.details = details;
		this.link = link;
	}
}
