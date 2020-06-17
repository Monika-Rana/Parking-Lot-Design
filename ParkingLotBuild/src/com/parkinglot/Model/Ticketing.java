package com.parkinglot.Model;


public class Ticketing {
	
	private int slotNo;
	private String regNo;
	private String color;
	
	public Ticketing () {
		
	}
	public Ticketing(int slotNo, String regNo, String color) {
		super();
		this.slotNo = slotNo;
		this.regNo = regNo;
		this.color = color;
	}
	public int getSlotNo() {
		return slotNo;
	}
	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return slotNo + "		" + regNo + "		" + color;
	}
	
	
}
