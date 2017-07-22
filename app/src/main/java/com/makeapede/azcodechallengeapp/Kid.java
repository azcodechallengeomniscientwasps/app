package com.makeapede.azcodechallengeapp;

import android.support.annotation.NonNull;

public class Kid implements Comparable<Kid> {
	public int age;
	public String name;

	public Kid() {}

	public Kid(int age, String name) {
		this.age = age;
		this.name = name;
	}


	@Override
	public int compareTo(@NonNull Kid kid) {
		return name.compareTo(kid.name);
	}
}
