package com.makeapede.azcodechallengeapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

public class Kid implements Comparable<Kid> {
	public String age;
	public String name;

	@Exclude
	public String uid;

	public Kid() {}

	public Kid(String age, String name) {
		this.age = age;
		this.name = name;
	}


	@Override
	public int compareTo(@NonNull Kid kid) {
		return name.compareTo(kid.name);
	}
}
