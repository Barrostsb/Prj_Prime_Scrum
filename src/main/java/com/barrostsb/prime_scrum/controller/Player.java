package com.barrostsb.prime_scrum.controller;

public class Player {
	
	private String name;
	private int number;
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	private String position;
	public Player(String name, int number, String position) {
		this.name = name;
		this.number = number;
		this.position = position;
	}
	
	


}
