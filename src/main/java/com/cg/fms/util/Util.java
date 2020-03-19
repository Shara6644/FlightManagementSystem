package com.cg.fms.util;

import java.util.ArrayList;
import java.util.List;

import com.cg.fms.bean.Airport;
import com.cg.fms.bean.Flight;
import com.cg.fms.flightexception.FlightException;

public class Util {
	static  List<Flight> listOfFlights=new ArrayList<Flight>();
	static List<Airport> listOfSourceAirports=new ArrayList<Airport>();
	static List<Airport> listOfDestinationAirports=new ArrayList<Airport>();
	
	
	
	static {
		
		Flight flight1= new Flight(1001,"BUSSINESS","INS",100);
		Flight flight2= new Flight(1002,"ECONOMY","INS",80);
		Flight flight3= new Flight(1003,"FIRSTCLASS","INS",50);
		
		Airport sourceAirport1= new Airport("Rajiv Gandhi International Airport","HYD","Hyderabad");
		Airport sourceAirport2= new Airport("chathrapathi Shivaji International Airport","MUM","Mumbai");
		Airport sourceAirport3= new Airport("Kempoguda Airport","BEN","Bengaluru");
		
		Airport destinationAirport1= new Airport("Rajiv Gandhi International Airport","HYD","Hyderabad");
		Airport destinationAirport2= new Airport("chathrapathi Shivaji International Airport","MUM","Mumbai");
		Airport destinationAirport3= new Airport("Kempoguda Airport","BEN","Bengaluru");
		
		listOfFlights.add(flight1);
		listOfFlights.add(flight2);
		listOfFlights.add(flight3);
		
		listOfSourceAirports.add(sourceAirport1);
		listOfSourceAirports.add(sourceAirport2);
		listOfSourceAirports.add(sourceAirport3);

		listOfDestinationAirports.add(destinationAirport1);
		listOfDestinationAirports.add(destinationAirport2);
		listOfDestinationAirports.add(destinationAirport3);

	}
	
	public static Flight  searchSourceFlight(int flightNumber)throws FlightException
	{
		Flight flight=null;
		if(listOfFlights.stream().anyMatch(p->p.getFlightNumber()==flightNumber))
		{
		  flight =listOfFlights.stream().filter(p->p.getFlightNumber()==flightNumber).findFirst().get();
		}	
			
		else
		{
		throw new FlightException(flightNumber+""+" Flight number does not exists");
		}	
			
		  return flight ;
	}
	public static Airport  searchSourceAirport(String sourceAirportCode) throws FlightException
	{

	Airport sourceAirport =null;
	if(listOfSourceAirports.stream().anyMatch(p->p.getAirportCode().equals(sourceAirportCode))) {
		 sourceAirport =listOfSourceAirports.stream().filter(p->p.getAirportCode().equals(sourceAirportCode)).findFirst().get();
		
	}
	else
		throw new FlightException(" Airport does not exists");
	  return sourceAirport;
	}
	public static Airport  searchDestAirport(String destinationAirportCode) throws FlightException
	{

	Airport destinationAirport =null;
	if(listOfSourceAirports.stream().anyMatch(p->p.getAirportCode().equals(destinationAirportCode))) {
		 destinationAirport =listOfSourceAirports.stream().filter(p->p.getAirportCode().equals(destinationAirportCode)).findFirst().get();
		
	}
	else
		throw new FlightException(" Airport does not exists");
		
		  return destinationAirport;
	}
}
