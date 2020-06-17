package com.parkinglot.Service;

import java.util.*;

import com.parkinglot.Exception.ExceptionHandler;
import com.parkinglot.Model.Ticketing;
import com.parkinglot.Model.Vehicle;

public class TicketGeneratingService {
	
	private class Ticket {
		Vehicle vehicle;
		int slotId;
		
		public Ticket(Vehicle vehicle, int slotId) {
			this.vehicle = vehicle;
			this.slotId = slotId;
		}
		
	}
	private HashMap<Integer, Ticket > ticketMap ;
	private CarParkingService carParkingService;
	private static TicketGeneratingService ticketIssue;
	
	
	private TicketGeneratingService(CarParkingService carParkingService) {
		this.carParkingService = carParkingService;
		ticketMap = new HashMap<Integer, Ticket>();
		
	}
	/*Creating the instance for generating the ticket,
	 * 
	 *  when a slot is associated slotno is returned,
	 *  
	 *   using that ticket is created for the car*/
	public static TicketGeneratingService createInstance(int slotNumbers) {
		if(slotNumbers <1)
			throw new ExceptionHandler("invalid slot number");
		if(ticketIssue == null)
		{
			CarParkingService carParking = CarParkingService.getInstance(slotNumbers);
			ticketIssue = new TicketGeneratingService(carParking);
		}
		return ticketIssue;
	}
	
	/*Method to get the Instance of the SingelTon*/
	public static TicketGeneratingService getInstance() {
		if(ticketIssue == null)
		{
			 throw new IllegalStateException("Parking Lot is not initialized");
		}
		return ticketIssue;
	}
	/*
	 * park a vehicle and it will issue a ticket with slot number associated
	 */
	public int issueTicket(Vehicle vehicle) {
		if(vehicle == null) {
			throw new IllegalArgumentException("Vehicle is not created");
		}
		
		boolean flag =true;
		for(Ticket ticket : ticketMap.values()) {
			if(ticket.vehicle.getRegistrationNo().equals(vehicle.getRegistrationNo()))
				flag = false;
		}
		
		if(flag) {
			int slotNumberAssigned = carParkingService.insertAvailSlot();
			Ticket newTicket = new Ticket(vehicle, slotNumberAssigned);
			
			ticketMap.put(slotNumberAssigned, newTicket);
			return  slotNumberAssigned;
		} else {
			throw new ExceptionHandler("Cannot issue a tick to the car with already existing reg no");
			
		}
	} 
	/*
	 * Exit the vehicel at the given slot number
	 */
	public void exitTheParking(int vehicleSlotNumber) {
		
		if(ticketMap.containsKey(vehicleSlotNumber)) {
			carParkingService.emptyTheSlot(vehicleSlotNumber);
			ticketMap.remove(vehicleSlotNumber);
		}else {
			throw new ExceptionHandler("Vehicle is not found with given ticket");
		}
	}
	
	public List<Ticketing> getStatus() {
		
		List<Ticketing> ticketList = new ArrayList<Ticketing>();
		
		for(Ticket ticket : ticketMap.values()) {
			ticketList.add(new Ticketing(ticket.slotId, ticket.vehicle.getRegistrationNo(),ticket.vehicle.getColor()));
		}
		//System.out.println(ticketList);
		return ticketList;
		
	}
	
	/*
	 * Generates all the regestration number of the car with a given color
	 */
	
	public List<String> getRegNumberFromColor(String color){
		List<String> registrationNumbers = new ArrayList<String>();
		
		if(color==null)
			throw new IllegalArgumentException("color cannot be null");
	    for(Ticket ticket : ticketMap.values()) {
	    	if(color.equals(ticket.vehicle.getColor())) {
	    		registrationNumbers.add(ticket.vehicle.getRegistrationNo());
	    		
	    	}
	    }
	    return registrationNumbers;
	}
	

	/*
	 * Generates all the slot number of the car with a given color
	 */
	
	public List<Integer> getSlotNumberWithColor(String color){
		if(color==null)
			throw new IllegalArgumentException("color cannot be null");
	   List<Integer> registrationNumList = new ArrayList<Integer>();
	   for(Ticket ticket : ticketMap.values()) {
		   if(color.equals(ticket.vehicle.getColor())) {
			   registrationNumList.add(ticket.slotId);
		   }
	   }
	   
	   return registrationNumList;
	}
	

	/*
	 * Generates  the slot number of a car with a given regestration number
	 */
	public int getSlotNumberWithRegistrationNumber(String regNum ){
		if(regNum == null) {
			throw new IllegalArgumentException("color cannot be null");
			  
		}
		
		 for(Ticket ticket: ticketMap.values()) {
			 if(regNum.equals(ticket.vehicle.getRegistrationNo()))
				 return ticket.slotId;
		 }
		  throw new ExceptionHandler("Not Found");
	}
	
	
	
}
