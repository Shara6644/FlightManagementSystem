package com.cg.fms.dao;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cg.fms.bean.Airport;

import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.flightexception.FlightException;

public class ScheduledFlightDaoImpl implements ScheduledFlightDao{


	private Map<Integer,ScheduledFlight> scheduledFlightsMap;

	public  ScheduledFlightDaoImpl() {
		scheduledFlightsMap = new HashMap<Integer,ScheduledFlight>();

		
	}
	@Override
	public int scheduleFlight(ScheduledFlight scheduledFlight) throws FlightException {
		
		
		if(scheduledFlightsMap.containsKey(scheduledFlight.getFlight().getFlightNumber()))
				{
					throw new FlightException(" Flight is already scheduled");
				}
		
		scheduledFlightsMap.put(scheduledFlight.getFlight().getFlightNumber(), scheduledFlight);
	
		return scheduledFlight.getFlight().getFlightNumber();

	}

	

	@Override
	public Flight viewScheduledFlights(int flightNumber) throws FlightException {
		if(!scheduledFlightsMap.containsKey(flightNumber))
		{
			throw new FlightException(" No Flight is Present ");
		}
		Flight f =scheduledFlightsMap.values().stream().map(p->p.getFlight()).filter(p->p.getFlightNumber()==flightNumber).findFirst().get();
		
		return f;
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlight() throws FlightException{
		if(scheduledFlightsMap.isEmpty())
		{
			throw new FlightException(" No Flight is Scheduled ");
		}
		Collection<ScheduledFlight> col = scheduledFlightsMap.values();
		List<ScheduledFlight> list = new ArrayList<ScheduledFlight>(col);
		


		return list;
	}

	@Override
	public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, int seatnumbers) throws FlightException{

		if(!scheduledFlightsMap.containsKey(flight.getFlightNumber())) {
			throw new FlightException(" Flight number does not exist");
			
		}
		ScheduledFlight obj = new ScheduledFlight(flight,seatnumbers,schedule);
		scheduledFlightsMap.put(obj.getFlight().getFlightNumber(), obj);
		return obj;
		
	}

	@Override
	public void deleteScheduledFlight(int flightNumber) throws FlightException{
		
		
		if(!scheduledFlightsMap.containsKey(flightNumber)) {
			throw new FlightException(" Flight number does not exist");
			
		}
		scheduledFlightsMap.remove(flightNumber);
		
		
	}
	@Override
	public List<ScheduledFlight> viewScheduledFlights(Airport souce, Airport destination,
			LocalDateTime dateTime) throws FlightException {
		List<ScheduledFlight> listOfScheduledFlights= new ArrayList<ScheduledFlight>();

		String sourceAirportCode=souce.getAirportCode();
		String destinationAirportCode =destination.getAirportCode();
	    
		Collection<ScheduledFlight> col = scheduledFlightsMap.values();
		List<ScheduledFlight> list = new ArrayList<ScheduledFlight>(col);
		for(ScheduledFlight scheduledFlight : list) {

			Schedule schedule =scheduledFlight.getSchedule();
			LocalDateTime ArrivaldateAndTime =schedule.getArrivalDateAndTime();
			// arrivalDate =ArrivaldateAndTime.getDate();
			Airport source1 =schedule.getSourceAirport();
			Airport dest1=schedule.getDestinationAirport();
			String sourceCode =source1.getAirportCode();
			String destCode = dest1.getAirportCode();
			if(sourceCode.equals(sourceAirportCode)&&destCode.equals(destinationAirportCode)&& dateTime.equals(ArrivaldateAndTime)) {
				
				listOfScheduledFlights.add(scheduledFlight);
			}
			else if(sourceCode==destCode)
			{
				throw new FlightException("Source airport code and destination code should not be same");
			}
			else
			{
				throw new FlightException("No Flight is available ");
			}
		}

	
		return listOfScheduledFlights;
		
	}

}
