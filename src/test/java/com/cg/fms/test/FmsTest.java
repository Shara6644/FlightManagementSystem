package com.cg.fms.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.cg.fms.bean.Airport;
import com.cg.fms.bean.DateTime;
import com.cg.fms.bean.Flight;
import com.cg.fms.bean.Schedule;
import com.cg.fms.bean.ScheduledFlight;
import com.cg.fms.dao.ScheduledFlightDao;
import com.cg.fms.dao.ScheduledFlightDaoImpl;
import com.cg.fms.flightexception.FlightException;

class FmsTest {

	ScheduledFlightDao dao = null;
	Flight flight1 = new Flight(1001, "BUSSINESS", "INS", 100);

	Airport s1 = new Airport("Rajiv Gandhi International Airport", "HYD", "Hyderabad");

	Airport d1 = new Airport("chathrapathi Shivaji International Airport","MUM","Mumbai");
	DateTime x = new DateTime("10-02-2019","20:00");

	Schedule s = new Schedule(s1, d1, x, new DateTime());
	ScheduledFlight sf = new ScheduledFlight(flight1, 0, s);

	@BeforeEach
	public void setUp() {
		
		dao = new ScheduledFlightDaoImpl();
    }

	@Test
	void testScheduleFlight() throws FlightException {

		int num = dao.scheduleFlight(sf);

		assertTrue(num > 0);

	}
	@Test
	void testScheduleFlight1() throws FlightException {

		int num = dao.scheduleFlight(sf);

		assertEquals(1001,num);

	}
	@Test
	void testScheduleFlight2() throws FlightException {

		int num = dao.scheduleFlight(sf);

		assertNotNull(num);

	}
	
	

	@Test
	void testDelete() throws Exception {

		dao.scheduleFlight(sf);
		
		List<ScheduledFlight> list = dao.viewScheduledFlight();
		
		dao.deleteScheduledFlight(sf.getFlight().getFlightNumber());
		int a = list.size();
		System.out.println( a);
		assertEquals(1, list.size());
	}

	@Test
	void testscheduleFlight() throws FlightException {

		dao.scheduleFlight(sf);
		Flight a = dao.viewScheduledFlights(sf.getFlight().getFlightNumber());
		assertNotNull(a);

	}
	
	@Test
	void testscheduleFlight1() throws FlightException {

		dao.scheduleFlight(sf);
		Flight a = dao.viewScheduledFlights(sf.getFlight().getFlightNumber());
		assertEquals(1001,a.getFlightNumber());

	}
	@Test
	void testscheduleFlight2() throws FlightException {

		dao.scheduleFlight(sf);
		Flight a = dao.viewScheduledFlights(sf.getFlight().getFlightNumber());
		assertTrue(1001==a.getFlightNumber());

	}
	
    @Test
	void testmodifyScheduledFlight() throws FlightException{
		
		dao.scheduleFlight(sf);
		ScheduledFlight g=dao.modifyScheduledFlight(flight1, s, 10);
		assertEquals(flight1.getFlightNumber(),g.getFlight().getFlightNumber());
		
	}
    @Test
	void testmodifyScheduledFlight1() throws FlightException{
		
		dao.scheduleFlight(sf);
		ScheduledFlight g=dao.modifyScheduledFlight(flight1, s, 10);
		assertTrue(g.getFlight().getFlightNumber()>0);
		
	}
    @Test
	void testmodifyScheduledFlight2() throws FlightException{
		
		dao.scheduleFlight(sf);
		ScheduledFlight g=dao.modifyScheduledFlight(flight1, s, 10);
		assertNotNull(g);
		
	}
    @Test
    void testviewSchedulesFlights() throws FlightException
    {
    	dao.scheduleFlight(sf);
    	List<ScheduledFlight> list = dao.viewScheduledFlights(s1, d1, x);
    	
    	assertEquals(1,list.size());
    	
    }
    @Test
    void testviewSchedulesFlights1() throws FlightException
    {
    	dao.scheduleFlight(sf);
    	List<ScheduledFlight> list = dao.viewScheduledFlights(s1, d1, x);
    	
    	assertTrue(list.size()>0);
    	
    }
    @Test
    void testviewSchedulesFlights2() throws FlightException
    {
    	dao.scheduleFlight(sf);
    	List<ScheduledFlight> list = dao.viewScheduledFlights(s1, d1, x);
    	
    	assertNotNull(list);
    	
    }
    
   
}
