package com.parkinglot.Service;

import java.util.ArrayList;
import java.util.List;

import com.parkinglot.Exception.ExceptionHandler;
import com.parkinglot.Model.Car;
import com.parkinglot.Model.Ticketing;

public class ExecuteCommand {

	/* enum type to take the input of all types
	 * 
	 */
	private enum InputType {
		create_parking_lot, park, leave, status,registration_numbers_for_cars_with_colour,
		slot_numbers_for_cars_with_colour,slot_number_for_registration_number
	}
	
	/*
	 * To expose only the validate and command method
	 */
	public interface Command {
		public void validate();
		
		public String execute();
	}
	
	/*
	 * implementing the command for creating the parking lot*/
	 class CreateParkingLot implements Command {
		
		private String[] inputArray;
		public CreateParkingLot(String[] s) {
			inputArray = s;
		}
		
		@Override
		public void validate() {
			
			if(CarParkingService.countCreateCalled()>1)
				throw new ExceptionHandler("Parking Lot already Created");
			if(inputArray.length !=2)
				throw new ExceptionHandler(" Arguments donot meet requirments");
			
		}
		
		@Override
		public String execute() {
			int numberOfCars = Integer.parseInt(inputArray[1]);
			TicketGeneratingService.createInstance(numberOfCars);
			
			CarParkingService.countCreateCalled();
			return "Parking lot created with "+inputArray[1]+" car slot";
		}
	}
	/*
	 * Implementing the command to park the car after validating
	 */
	  class Park implements Command {
		private String[] inputArray;
		
		Park (String[] s){
			inputArray  =s;
		}
		public void validate() {
			if(inputArray.length !=3) {
				throw new IllegalArgumentException("Insufficieant Arguments");
				
			}
			
		}
		
		public String execute() {
			
			TicketGeneratingService ticketService = TicketGeneratingService.getInstance();
			int slotAllocated = ticketService.issueTicket(new 
					Car(inputArray[2], inputArray[1]));
			return "Allocated Slot number " + slotAllocated;
		}
	}
	  /*
		 * Implementing the command to exit the car after validating
		 */
	  class Leave implements Command {
		
		private String[] inputArray;
		
		Leave(String[] s) {
			inputArray = s;
		}
		public void validate() {
			if(inputArray.length!= 2) {
				throw new IllegalArgumentException("Leave  command should have exactly one  arguments");
			}
		}
		
		public String execute() {
			TicketGeneratingService ticketservice = TicketGeneratingService.getInstance();
			ticketservice.exitTheParking(Integer.parseInt(inputArray[1]));
			return "Slot number "+ inputArray[1] +"is EMpty now";
		}
	}
	/*
	 * To check the status of all parked vehicles in the parking lot
	 */
	  class Status implements Command {
		
		private String[] inputArray ;
		
		public Status(String [] s ) {
			inputArray = s;
		}
		
		public void validate() {
			if(inputArray.length != 1)
				throw new IllegalArgumentException("Status  command should have exactly one  arguments");
			
		}
		public String execute() {
			
			TicketGeneratingService ticket = TicketGeneratingService.getInstance();
			List<Ticketing> statusList = ticket.getStatus();
			
			StringBuilder output = new StringBuilder("Slot No.	Registration No		Color");
			for(Ticketing ticketStatus: statusList ) {
				output.append("\n").append(ticketStatus);
			}
			return output.toString();
		}
				
	}
	
	  class RegestrationNumberFromColor implements Command {

		private String[] inputArray ;
		
		public RegestrationNumberFromColor(String [] s ) {
			inputArray = s;
		}
		
		public void validate() {
			if(inputArray.length != 2)
				throw new IllegalArgumentException("RegestrationNumberFromColor  command should have exactly 2  arguments");
			
		}
		
		public String execute() {
			TicketGeneratingService ticketService = TicketGeneratingService.getInstance();
			List<String> outputresponse = ticketService.getRegNumberFromColor(inputArray[1]);
			StringBuilder outputBuilder = new StringBuilder();
			for(String regNo : outputresponse) {
				if(outputBuilder.length() >0) {
					outputBuilder.append(",");
				}
				outputBuilder.append(regNo);
			}
			return outputBuilder.toString();
		}
	}
	
	 class SlotNumFromColor implements Command {
		
		private String[] inputArray ;
		
		public SlotNumFromColor(String [] s ) {
			inputArray = s;
		}
		
		public void validate() {
			if(inputArray.length != 2)
				throw new IllegalArgumentException("SlotNumFromColor  command should have exactly 2  arguments");
			
		}
		
		public String execute() {
			
			TicketGeneratingService ticketService = TicketGeneratingService.getInstance();
			List<Integer> slotList = ticketService.getSlotNumberWithColor(inputArray[1]);
			StringBuilder outputBuilder = new StringBuilder();
			
			for(int slot : slotList) {
				if(outputBuilder.length() >0)
					outputBuilder.append("\n");
				
				outputBuilder.append(slot);
			}
			return outputBuilder.toString();
		}
		
	}
	
	 class SlotNumberFromRegNo implements Command {
		
		 private String[] inputArray ;
		
		public SlotNumberFromRegNo(String [] s ) {
			inputArray = s;
		}
		
		public void validate() {
			if(inputArray.length != 2)
				throw new IllegalArgumentException("SlotNumberFromRegNo  command should have exactly 2  arguments");
			
		}
		
		public String execute() {
			
			TicketGeneratingService ticketService = TicketGeneratingService.getInstance();
			int  slotNo = ticketService.getSlotNumberWithRegistrationNumber(inputArray[1]);
			return Integer.toString(slotNo);
		}
		
	}
 	
}
