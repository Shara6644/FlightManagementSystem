package com.cg.fms.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cg.fms.bean.Airport;

import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.dao.ScheduledFlightDaoImpl;
import com.cg.fms.flightexception.FlightException;

public class ScheduleFlightServicesImpl implements  ScheduleFlightServices{
	
	private ScheduledFlightDaoImpl daoimpl ;
			
	public ScheduleFlightServicesImpl() {
		daoimpl=new ScheduledFlightDaoImpl();
		
	}
	Map<Integer,ScheduledFlight> schedflight = new HashMap<Integer,ScheduledFlight>();
	@Override
	public int scheduleFlight(ScheduledFlight scheduleFlight) throws FlightException{
		String str= String.valueOf(scheduleFlight.getFlight().getFlightNumber());
		
		 
		if(!str.matches("[0-9]{4}"))
				{
			throw new FlightException("Flight number should be 4 digits");
				}
		
		
		return daoimpl.scheduleFlight(scheduleFlight);
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(Airport source, Airport destination, LocalDateTime date)  throws FlightException{
		String str1=source.getAirportCode();
		String str2=destination.getAirportCode();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		if(!((str1.matches("[A-Z]{3}"))&&(str2.matches("[A-Z]{3}"))&&(date.format(formatter).matches("^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$" +"([01]?[0-9]|2[0-3]):[0-5][0-9]"))))
		{
	     throw new FlightException("Airport Code should be of 3 Characters in Upper Case( MUM, HYD, BEN )");
		}
//		else if(!(date.format(formatter).matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$")));
//		{
//			throw new FlightException("date and time should be in this format dd-MM-yyyy HH:mm");
//		}
		
	//	&&(date.format(formatter).matches("^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$" +"([01]?[0-9]|2[0-3]):[0-5][0-9]")
		return daoimpl.viewScheduledFlights(source, destination, date);
	}

	@Override
	public Flight viewScheduledFlights(int flightNumber) throws FlightException {
		String str= String.valueOf(flightNumber);
		if(!str.matches("[0-9]{4}"))
				{
			throw new FlightException("Flight number should be 4 digits");
				}
		
		return daoimpl.viewScheduledFlights(flightNumber);
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlight() throws FlightException {
		
		return daoimpl.viewScheduledFlight();
	}

	@Override
	public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, int a)  throws FlightException{
		String str= String.valueOf(flight.getFlightNumber());
		String str1= flight.getCarrierName();
		
		if(!str.matches("[0-9]{4}")&&(str1.matches("[A-Z}{3}")))
				{
			throw new FlightException("Flight number should be 4 digits and Airport Code should contain only 3 characters");
				}
		return daoimpl.modifyScheduledFlight(flight, schedule,a);
	}

	@Override
	public void deleteScheduledFlight(int flightNumber) throws FlightException {
		String str= String.valueOf(flightNumber);
		if(!str.matches("[0-9]{4}"))
				{
			throw new FlightException("Flight number should be 4 digits");
				}
		daoimpl.deleteScheduledFlight(flightNumber);
	}

	
}
