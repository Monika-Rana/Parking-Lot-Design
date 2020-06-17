package InputController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;


import com.parkinglot.Service.InputFormatService;

public class MainController {

	public static void main(String[] args) throws Exception{
		
		InputFormatService inputService = InputFormatService.getInstance();
		
		 BufferedReader bufferedReader;

	        if (args.length == 0) {
	           
	            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	        } else {
	            
	            String filePath = args[0];
	            File inputFile = new File(filePath);
	            bufferedReader = new BufferedReader(new FileReader(inputFile));
	        }
		while(true) {
			 String text = bufferedReader.readLine();
			if("exit".equalsIgnoreCase(text))
				break;
			else {
				boolean success = inputService.executeCommands(text);
				
				if(!success)
					break;
			}
		}
	}

}
