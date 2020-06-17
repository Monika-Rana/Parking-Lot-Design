package com.parkinglot.Service;

import com.parkinglot.Exception.ExceptionHandler;
import com.parkinglot.Service.ExecuteCommand.*;

public class InputFormatService {

	private static InputFormatService inputFormatService;
	
	private InputFormatService() {
		
	}
	
	public static InputFormatService getInstance() {
		if(inputFormatService== null) {
			inputFormatService = new InputFormatService();
			
		}
		return inputFormatService;
	}
	
	
	private enum InputType {
		create_parking_lot, park, leave, status,registration_numbers_for_cars_with_colour,
		slot_numbers_for_cars_with_colour,slot_number_for_registration_number, 
	}
	
	private InputType getInput(String read) {
		InputType input = null;
		if(read==null) {
			System.out.println("Invalid INput");
		} else {
			String[] inputArray = read.split(" ");
			input = InputType.valueOf(inputArray[0]);
			
//			if(inputArray[0].equals("")) {
//				System.out.println("Command Empty");
//			}
//			else {
//				input = InputType.valueOf(inputArray[0]);
//			}
			
			
		}
		return input;
	}
	
	
	public boolean executeCommands(String inputCommand) {
		InputType input = getInput(inputCommand);
		
		if(input==null)
			return false;
		
		String[] inputStringArray = inputCommand.split(" ");
		Command cmd;
		
		ExecuteCommand c = new ExecuteCommand();
		ExecuteCommand.CreateParkingLot cd = new ExecuteCommand().new CreateParkingLot(inputStringArray);
		
		switch(input) {
		
		case create_parking_lot: 
			cmd = new ExecuteCommand().new CreateParkingLot(inputStringArray);
			break;
		case park:
			cmd = new ExecuteCommand().new Park(inputStringArray);
			break;
		case leave: 
			cmd = new ExecuteCommand().new Leave(inputStringArray);
			break;
		case status:
			cmd = new ExecuteCommand().new Status(inputStringArray);
			break;
		case registration_numbers_for_cars_with_colour:
			cmd = new ExecuteCommand().new RegestrationNumberFromColor(inputStringArray);
			break;
		case slot_numbers_for_cars_with_colour :
			cmd =new ExecuteCommand().new  SlotNumFromColor(inputStringArray);
			break;
		case slot_number_for_registration_number : 
			cmd = new ExecuteCommand().new SlotNumberFromRegNo(inputStringArray);
			break;
		
			
		default : 
			System.out.println("Unknown Command");
			return false;
			
		}
		
		try {
			cmd.validate();
		} catch(IllegalArgumentException e){
            System.out.println("Please provide a valid argument");
            return false;
        }
		
		String output = "";
		try {
			output = cmd.execute();
		} catch(ExceptionHandler e ) {
			System.out.println(e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println(output);
		return true;
	}
	
	
	

}
