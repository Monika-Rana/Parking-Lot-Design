package com.parkinglot.Model;

import com.parkinglot.Model.Vehicle;

public class Car implements Vehicle{
	
	private String color;
	private String regNo;
	
	public Car(String color, String regNo) {
		
		if(color==null || regNo == null)
			throw new IllegalArgumentException("Car Reg. No and Color is required");
		this.color = color;
		this.regNo = regNo;
	}
	
	


	@Override
	public String getColor() {
		
		return this.color;
	}

	@Override
	public String getRegistrationNo() {
		
		return this.regNo;
	}

}
