package com.cg.fms.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cg.fms.bean.Airport;
import com.cg.fms.bean.DateTime;
import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.flightexception.FlightException;

public class ScheduledFlightDaoImpl implements ScheduledFlightDao{


	private Map<Integer,ScheduledFlight> scheduledFlights;

	public  ScheduledFlightDaoImpl() {
		scheduledFlights = new HashMap<Integer,ScheduledFlight>();

		
	}
	@Override
	public int scheduleFlight(ScheduledFlight scheduledFlight) throws FlightException {
		
		
		if(scheduledFlights.containsKey(scheduledFlight.getFlight().getFlightNumber()))
				{
					throw new FlightException(" Flight is already scheduled");
				}
		
		scheduledFlights.put(scheduledFlight.getFlight().getFlightNumber(), scheduledFlight);
	
		return scheduledFlight.getFlight().getFlightNumber();

	}

	@Override
	public List<ScheduledFlight> viewScheduledFlights(Airport source, Airport destination, DateTime dateTime) throws FlightException {
	List<ScheduledFlight> listOfScheduledFlights= new ArrayList<ScheduledFlight>();

		String sourceAirportCode=source.getAirportCode();
		String destinationAirportCode =destination.getAirportCode();
		String date = dateTime.getDate();
		Collection<ScheduledFlight> col = scheduledFlights.values();
		List<ScheduledFlight> list = new ArrayList<ScheduledFlight>(col);
		for(ScheduledFlight x : list) {

			Schedule s =x.getSchedule();
			DateTime dateAndTime =s.getArrivalTime();
			 date =dateAndTime.getDate();
			Airport source1 =s.getSourceAirport();
			Airport dest1=s.getDestinationAirport();
			String sourceCode =source1.getAirportCode();
			String destCode = dest1.getAirportCode();
			if(sourceCode.equals(sourceAirportCode)&&destCode.equals(destinationAirportCode)&& dateTime.equals(dateAndTime)) {
				
				listOfScheduledFlights.add(x);
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

	@Override
	public Flight viewScheduledFlights(int flightNumber) throws FlightException {
		if(!scheduledFlights.containsKey(flightNumber))
		{
			throw new FlightException(" No Flight is Present ");
		}
		Flight f =scheduledFlights.values().stream().map(p->p.getFlight()).filter(p->p.getFlightNumber()==flightNumber).findFirst().get();
		
		return f;
	}

	@Override
	public List<ScheduledFlight> viewScheduledFlight() throws FlightException{
		if(scheduledFlights.isEmpty())
		{
			throw new FlightException(" No Flight is Scheduled ");
		}
		Collection<ScheduledFlight> col = scheduledFlights.values();
		List<ScheduledFlight> list = new ArrayList<ScheduledFlight>(col);
		


		return list;
	}

	@Override
	public ScheduledFlight modifyScheduledFlight(Flight flight, Schedule schedule, int seatnumbers) throws FlightException{

		if(!scheduledFlights.containsKey(flight.getFlightNumber())) {
			throw new FlightException(" Flight number does not exist");
			
		}
		ScheduledFlight obj = new ScheduledFlight(flight,seatnumbers,schedule);
		scheduledFlights.put(obj.getFlight().getFlightNumber(), obj);
		return obj;
		
	}

	@Override
	public void deleteScheduledFlight(int flightNumber) throws FlightException{
		
		
		if(!scheduledFlights.containsKey(flightNumber)) {
			throw new FlightException(" Flight number does not exist");
			
		}
		scheduledFlights.remove(flightNumber);
		
		
	}

}
