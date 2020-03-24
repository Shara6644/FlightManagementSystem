package com.cg.fms.pl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import com.cg.fms.bean.Airport;

import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.flightexception.FlightException;
import com.cg.fms.service.ScheduleFlightServicesImpl;
import com.cg.fms.util.Util;

public class FMSClient {
	
	public static void main(String[] args) {
		Scanner  scanner =new Scanner(System.in);
		ScheduleFlightServicesImpl service= new ScheduleFlightServicesImpl();
		
		
		int choice =0;
		Boolean flag;
		
		while(choice!=7) 
			{
			
			System.out.println(" 1. Schedule A Flight ");
			System.out.println(" 2. View list of ScheduledFlights ");
			System.out.println(" 3. Modify a  ScheduledFlight ");
			System.out.println(" 4. Delete a ScheduledFlight ");
			System.out.println(" 5. View a Schedule Flight by flight number ");
			System.out.println(" 6. View list of Scheduled Flights between a source and destination ");
			System.out.println(" 7. Exit ");
			try {
			System.out.println(" Enter your choice ");
			choice =scanner.nextInt();
			
			if(choice>7||choice<1) {
			System.err.println("Choice must be 1 to 7");
			}
			switch(choice) {
			
			case 1:
				
			    ScheduledFlight scheduledFlight=new ScheduledFlight();
				System.out.println(" Enter the Flight number from the given list ");
				System.out.println("1.1001 2.1002 3.1003");
				try {
				int flightNumber = scanner .nextInt();
  			    scanner.nextLine();
  			    
				Flight flight= Util.searchSourceFlight(flightNumber);
				System.out.println(" Enter the source airport code from the given list");
				System.out.println("1.HYD 2.MUM 3.BEN");
				String sourceAirportCode = scanner .nextLine();
				Airport sourceAirport= Util.searchSourceAirport(sourceAirportCode);
				System.out.println("  Enter the destination airport code from the given list");
				System.out.println("1.HYD 2.MUM 3.BEN");
				String destinationAirportCode = scanner .nextLine();
				Airport destinationAirport= Util.searchDestAirport( destinationAirportCode );
				//System.out.println(" enter the Arrival Date and Time in the following format dd/MM/yyyy HH:mm ");
				String arrivalDateAndTimeString;
				
					System.out.println(" enter the Arrival Date and Time in the following format dd/MM/yyyy HH:mm ");
				 arrivalDateAndTimeString = scanner.nextLine();
				//flag = Util.validateDate(arrivalDateAndTimeString);
				
//				if(!Util.validateDate(arrivalDateAndTimeString))
//				{
//					
//					System.out.println("invalid date");
//				}
				
			//	while(Util.validateDate(arrivalDateAndTimeString)==false);
//				String arrivalTime=scanner.next();
				System.out.println(" enter the Destination Date and Time  in the following format dd/MM/yyyy HH:mm ");
				String destinationDateAndTimeString = scanner.nextLine();
//				flag = Util.validateDate(destinationDateAndTimeString);
//				if(!flag)
//				{
//					System.out.println("invalid date");
//				}
//				String destinationTime=scanner.next();
			
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				 LocalDateTime arrivalDateAndTime =LocalDateTime.parse(arrivalDateAndTimeString, formatter);
				 //System.out.println(arrivalDateAndTime);
               	LocalDateTime destinationDateAndTime =LocalDateTime.parse(destinationDateAndTimeString , formatter);
				 
				Schedule schedule = new Schedule(sourceAirport,destinationAirport,arrivalDateAndTime,destinationDateAndTime);
				//System.out.println("dummy");
				System.out.println(" Enter the available seats ");
				//scanner.nextLine();
				int numberOfSeats = scanner.nextInt();
				scheduledFlight.setFlight(flight);
				scheduledFlight.setSchedule(schedule);
				scheduledFlight.setAvailableSeats(numberOfSeats);
				service.scheduleFlight(scheduledFlight);
				System.out.println("Flight is scheduled successfully");
				}
				
				catch(FlightException e) {
					System.out.println(e.getMessage());
					
					
				}
				catch(Exception e)
				{
					System.out.println("enter a valid details");
					 scanner.nextLine();
				}
                  break;
               
			case 2:
				try {
				List<ScheduledFlight> listOfScheduledFlights =service.viewScheduledFlight();
				//System.out.println(listOfScheduledFlights.size());
				for(ScheduledFlight scheduledFlights : listOfScheduledFlights) {
					
					Flight flight=scheduledFlights.getFlight();
					Schedule schedule=scheduledFlights.getSchedule();
					
					int flightNumber =flight.getFlightNumber();
					
				Airport sourceAirport =schedule.getSourceAirport();
				Airport destinationAirport =schedule.getDestinationAirport();
				String sourceAiportCode =sourceAirport.getAirportCode();
				String destinationAirportCode =destinationAirport.getAirportCode();
				LocalDateTime localDateTime = schedule.getArrivalDateAndTime();
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				String str = localDateTime.format(formatter);
				LocalDateTime localDateTime1 = schedule.getDepartureDateAndTime();
				String str1 = localDateTime.format(formatter);
//				LocalDateTime arrivalDateAndTime=schedule.getArrivalTime();
//				LocalDateTime destinationDateAndTime=schedule.getDepartureTime();
//				String arrivalDate =arrivalDateAndTime.getDate();
//				String arrivalTime = arrivalDateAndTime.getHour();
//				String departureDate =destinationDateAndTime.getDate();
//				String departureTime =destinationDateAndTime.getHour();
				System.out.println(flightNumber+" "+sourceAiportCode+" "+" "+destinationAirportCode+" "+str+" " +str1);
					
				}
				}
				catch(FlightException e) {
					System.out.println(e.getMessage());
				}
				catch(Exception e)
				{
					System.out.println("enter a valid details");
					scanner.nextLine();
				}
				
                 break;
			case 3 :
				try {

				scanner.nextLine();
				System.out.println(" Enter the Flight number from the given list ");
				System.out.println("1.1001 2.1002 3.1003");
				int flightNumber = scanner .nextInt();
				Flight flight= Util.searchSourceFlight(flightNumber);
				scanner.nextLine();
				System.out.println(" Enter the source airport code from the given list");
				System.out.println("1.HYD 2.MUM 3.BEN");
				String sourceAirportCode = scanner .nextLine();
				Airport sourceAirport= Util.searchSourceAirport(sourceAirportCode);
				System.out.println("  Enter the destination airport codefrom the given list");
				System.out.println("1.HYD 2.MUM 3.BEN");
				String destinationAirportCode = scanner .nextLine();
				Airport destinationAirport= Util.searchDestAirport( destinationAirportCode );
				System.out.println(" enter the Arrival Date and Time in the following format dd/MM/yyyy HH:mm ");
				String arrivalDateAndTimeString = scanner.next();
//				 flag = Util.validateDate(arrivalDateAndTimeString);
//				if(!flag)
//				{
//					System.out.println("invalid date");
//				}
//				String arrivalTime=scanner.next();
				System.out.println(" enter the Destination Date and Time  in the following format dd/MM/yyyy HH:mm");
				String destinationDateAndTimeString = scanner.next();
//				 flag = Util.validateDate(arrivalDateAndTimeString);
//				if(!flag)
//				{
//					System.out.println("invalid date");
//				}
//				String destinationTime=scanner.next();
//				
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				 LocalDateTime arrivalDateAndTime =LocalDateTime.parse(arrivalDateAndTimeString, formatter);
               	LocalDateTime destinationDateAndTime =LocalDateTime.parse(destinationDateAndTimeString , formatter);
				 
				Schedule schedule = new Schedule(sourceAirport,destinationAirport,arrivalDateAndTime,destinationDateAndTime);
				System.out.println(" Enter the available seats ");
				int numberOfSeats = scanner.nextInt();
//				service.modifyScheduledFlight(flight, schedule, numberOfSeats);
				System.out.println("Flight is scheduled successfully");
				}catch(FlightException e) {
					System.err.println(e.getMessage());
				}
				catch(Exception e)
				{
					System.out.println("enter a details");
					scanner.nextLine();
				}
				break;
			case 4 :
				try {
				scanner.nextLine();
				System.out.println(" Enter the Flight number from the given list ");
				System.out.println("1.1001 2.1002 3.1003");
			    int flightNumber = scanner .nextInt();
			    Flight flight= Util.searchSourceFlight(flightNumber);
         		service.deleteScheduledFlight(flightNumber);  
				}
				catch(FlightException e) {
					System.err.println(e.getMessage());
				}
				catch(Exception e)
				{
					System.out.println("enter a flight number from the given list");
					scanner.nextLine();
				}
			    break;
			case 5:
				try
			{
				System.out.println(" Enter the Flight number ");
				int flightNumber=scanner.nextInt();
		        Flight flight= service.viewScheduledFlights(flightNumber);
				System.out.println(flight.getFlightNumber()+" "+flight.getFlightModel()+" "+flight.getCarrierName()+" "+flight.getSeatCapacity());
				
				
			}
			catch(FlightException e)
			{
				System.out.println(e.getMessage());
			}
			catch(Exception e)
			{
				System.out.println("enter a valid flight number from the given list");
				scanner.nextLine();
			}
			    break;
			case 6: 
			try {
				scanner.nextLine();
				System.out.println(" Enter the source airport code from the given list");
				System.out.println("1.HYD 2.MUM 3.BEN");
				String s=scanner.nextLine();
				Airport sourceAirport=Util.searchSourceAirport(s);
				System.out.println("  Enter the destination airport code from the given list");
				System.out.println("1.HYD 2.MUM 3.BEN");
				String sourceAirportCode=scanner.nextLine();
				Airport destinationAirport=Util.searchDestAirport(sourceAirportCode);
				System.out.println(" enter the Arrival Date and Time in the following format dd/MM/yyyy HH:mm ");
				String arrivalDateAndTimeString = scanner.nextLine();
//				flag = Util.validateDate(arrivalDateAndTimeString);
//				if(!flag)
//				{
//					System.out.println("invalid date");
//				}
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				 LocalDateTime arrivalDateAndTime =LocalDateTime.parse(arrivalDateAndTimeString, formatter);
//				System.out.println("Enter Date");
//				String date=scanner.nextLine();
//				System.out.println("Entertime");
//			//	String time=scanner.nextLine();
			//	LocalDateTime dt1=new LocalDateTime(date,time);
				List<ScheduledFlight> listOfScheduledFlights=service.viewScheduledFlights(sourceAirport, destinationAirport,arrivalDateAndTime);
			//	System.out.println(listOfScheduledFlights.size());
			for(ScheduledFlight scheduledFlights: listOfScheduledFlights)
				{
					int flightNumber=scheduledFlights.getFlight().getFlightNumber();
					String flightModel =scheduledFlights.getFlight().getFlightModel();
					String carrierName=scheduledFlights.getFlight().getCarrierName();
				int seatCapacity=scheduledFlights.getFlight().getSeatCapacity();
					System.out.println(flightNumber+" "+flightModel+" "+carrierName+" "+seatCapacity);
			}
				
			
			}
			catch(FlightException e)
				{
				System.out.println(e.getMessage());
				}
				catch(Exception e)
				{
					System.out.println("enter a valid details");
					scanner.nextLine();
				}
				break;
			case 7: System.out.println("THANK YOU");
			 return;
			
		}
			}
			catch(InputMismatchException e)
        	{
        		System.err.println(" Choice should be integer ");
        		scanner.nextLine();
        	
        	}
			
			
			
			
			}
		
	
		}
		
		
		
		
}

