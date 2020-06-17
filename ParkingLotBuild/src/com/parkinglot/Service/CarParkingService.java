package com.parkinglot.Service;

import java.util.HashMap;

import com.parkinglot.Exception.ExceptionHandler;

public class CarParkingService {

	/*
	 * slot Entity associated with Parking lot*/
	private class Slots {
		
		private int slotNo;
		private boolean status;
		public Slots(int slotNo, boolean status) {
			
			this.slotNo = slotNo;
			this.status = status;
		}
	}
	
	private static CarParkingService carParkingService;
	private static HashMap<Integer, Slots> slotMap ;
	private static int count =0;
	
	/*Consttuct is Private as its is singleTOn*/
	
	private CarParkingService(int noOfSlots) {
		slotMap = new HashMap<Integer, Slots>();
		for(int i=1; i<=noOfSlots ; i++) {
			slotMap.put(i, new Slots(i, false));
		}
	}
	
	/*
	 * SingleTon Class to Ensure only one Instance is Created/ 
	 */
	public  static CarParkingService getInstance(int slotNumber) {
		if(carParkingService == null)
		{
			carParkingService = new CarParkingService(slotNumber);
		}
		
		return carParkingService;
	}
	
	/*
	 * Method to cound the number of times the command is called to enusure one instance of parking lot is created*/
	public static int countCreateCalled() {
		count ++;
		return count;
	}
	
	/*
	 * Insert a car in the nearest available slot and return the slot number associate with it*/
	public static int  insertAvailSlot() {
		 int slotAvailableNumber = -1;
		
		for(int i=1; i<= slotMap.size(); i++) {
			Slots sl = slotMap.get(i);
			if(sl.status== false) {
				slotAvailableNumber = sl.slotNo;
				sl.status= true;
				break;
			}
		}
		if(slotAvailableNumber !=-1) {
			return slotAvailableNumber;
		}
		else throw new ExceptionHandler("sorry, the Slot is full");
	}
	/*
	 * While exiting it empty the slot and mark it as available */
	public static void emptyTheSlot(int slotId) {
		if(slotMap.containsKey(slotId)) {
			if(slotMap.get(slotId).status==false) {
				throw new ExceptionHandler("The slot is already empty");
			} else {
				slotMap.get(slotId).status = false;
			}
			
		} else throw new IllegalArgumentException("THe slot number is invalid");
	}
}
