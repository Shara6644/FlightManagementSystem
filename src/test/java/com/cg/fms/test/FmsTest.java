package com.cg.fms.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.cg.fms.bean.Airport;

import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.dao.ScheduledFlightDao;
import com.cg.fms.dao.ScheduledFlightDaoImpl;
import com.cg.fms.flightexception.FlightException;
import com.cg.fms.service.ScheduleFlightServices;
import com.cg.fms.service.ScheduleFlightServicesImpl;

class FmsTest {

	ScheduledFlightDao dao = null;
	ScheduleFlightServices service =null;
	Flight flight1 = new Flight(1001, "BUSSINESS", "INS", 100);

	Airport sourceAirport = new Airport("Rajiv Gandhi International Airport", "HYD", "Hyderabad");

	Airport destinationAirport = new Airport("chathrapathi Shivaji International Airport","MUM","Mumbai");
//	LocalDateTime x = new LocalDateTime("2018-07-14T17:45:55.9483536");
//	LocalDateTime y = new LocalDateTime("10-02-2019","21:00");
//	Schedule s = new Schedule(s1, d1, x, new LocalDateTime());
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
      String  arrivalDateAndTimeString ="01/11/2017 19:45";
	 LocalDateTime arrivalDateAndTime =LocalDateTime.parse(arrivalDateAndTimeString, formatter);
	 String  destinationlDateAndTimeString ="01/11/2017 20:45";
	 LocalDateTime destinationDateAndTime =LocalDateTime.parse(arrivalDateAndTimeString, formatter);
	Schedule schedule = new Schedule(sourceAirport,destinationAirport,arrivalDateAndTime,destinationDateAndTime);
	ScheduledFlight scheduledFlight = new ScheduledFlight(flight1, 0, schedule);

	@BeforeEach
	public void setUp() {
		
		dao = new ScheduledFlightDaoImpl();
		service=new ScheduleFlightServicesImpl();
    }

	

	
	@Test
	void testScheduleFlight() throws FlightException
	{

		int num = dao.scheduleFlight(scheduledFlight);

		assertEquals(scheduledFlight.getFlight().getFlightNumber(),num);

	}
	@Test
	void testScheduleFlight2() throws FlightException {

		int num = dao.scheduleFlight(scheduledFlight);

		assertNotNull(num);

	}
	@Test
	void testScheduleFlightService() throws FlightException
	{
		int num=service.scheduleFlight(scheduledFlight);
		//int num = dao.scheduleFlight(sf);
		assertEquals(scheduledFlight.getFlight().getFlightNumber(),num);
		
	}
	
	
	

	@Test
	void testDelete() throws Exception {

		dao.scheduleFlight(scheduledFlight);
		
		List<ScheduledFlight> list = dao.viewScheduledFlight();
		
		dao.deleteScheduledFlight(scheduledFlight.getFlight().getFlightNumber());
		int a = list.size();
		System.out.println( a);
		assertEquals(1, list.size());
	}

	@Test
	void testscheduleFlight() throws FlightException {

		dao.scheduleFlight(scheduledFlight);
		Flight a = dao.viewScheduledFlights(scheduledFlight.getFlight().getFlightNumber());
		assertNotNull(a);

	}
	
	
	@Test
	void testscheduleFlight2() throws FlightException {

		dao.scheduleFlight(scheduledFlight);
		Flight a = dao.viewScheduledFlights(scheduledFlight.getFlight().getFlightNumber());
		assertTrue(1001==a.getFlightNumber());

	}
	
	@Test
	void testscheduleFlightService() throws FlightException {

		service.scheduleFlight(scheduledFlight);
		Flight a =service.viewScheduledFlights(scheduledFlight.getFlight().getFlightNumber());
		assertEquals(1001,a.getFlightNumber());

	}
	
    @Test
	void testmodifyScheduledFlight() throws FlightException{
		
		dao.scheduleFlight(scheduledFlight);
		ScheduledFlight g=dao.modifyScheduledFlight(flight1, schedule, 10);
		assertEquals(flight1.getFlightNumber(),g.getFlight().getFlightNumber());
		
	}
   
    @Test
	void testmodifyScheduledFlight2() throws FlightException{
		
		dao.scheduleFlight(scheduledFlight);
		ScheduledFlight g=dao.modifyScheduledFlight(flight1, schedule, 10);
		assertNotNull(g);
		
	}
    @Test
	void testmodifyScheduledFlightService() throws FlightException{
		
    	service.scheduleFlight(scheduledFlight);
		ScheduledFlight g=service.modifyScheduledFlight(flight1, schedule, 10);
		assertEquals(flight1.getFlightNumber(),g.getFlight().getFlightNumber());
		
	}
    
    @Test
    void testviewSchedulesFlights() throws FlightException
    {
    	dao.scheduleFlight(scheduledFlight);
    	List<ScheduledFlight> list = dao.viewScheduledFlights(sourceAirport, destinationAirport, arrivalDateAndTime);
    	
    	assertEquals(1,list.size());
    	
    }
    
    @Test
    void testviewSchedulesFlights2() throws FlightException
    {
    	dao.scheduleFlight(scheduledFlight);
    	List<ScheduledFlight> list = dao.viewScheduledFlights(sourceAirport, destinationAirport,arrivalDateAndTime);
    	
    	assertNotNull(list);
    	
    }
    
    @Test
    void testviewSchedulesFlightsService() throws FlightException
    {
    	service.scheduleFlight(scheduledFlight);
    	List<ScheduledFlight> list = service.viewScheduledFlights(sourceAirport, destinationAirport,arrivalDateAndTime);
    	
    	assertNotNull(list);
    	
    }
    
    
    
   
}
