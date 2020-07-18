package com.bhuman.vt.challenge.model;

/*
 * Adding the states that we know so far
 * More to be added as per client request
 */
public enum WineState {
	READ_FOR_BOTTELING ("Ready for bottling"),
	FILTERED ("Filtered");
	
	private String name;
	
	WineState(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
