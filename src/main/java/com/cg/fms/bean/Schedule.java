package com.cg.fms.bean;

import java.time.LocalDateTime;

public class Schedule {
	
	private Airport sourceAirport;
	private Airport destinationAirport;
	private  LocalDateTime arrivalDateAndTime;
	private  LocalDateTime departureDateAndTime;
	
	
	
//	public Schedule( Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalDateAndTime, LocalDateTime departureDateAndTime) {
//		super();
//		
//		this.sourceAirport = sourceAirport;
//		this.destinationAirport = destinationAirport;
//        this.arrivalDateAndTime = arrivalDateAndTime;
//	this.departureDateAndTime = departureDateAndTime;
//	}
	public Schedule() {
		super();
	}
	
	public Schedule(Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalDateAndTime,
		LocalDateTime departureDateAndTime) {
	super();
	this.sourceAirport = sourceAirport;
	this.destinationAirport = destinationAirport;
	this.arrivalDateAndTime = arrivalDateAndTime;
	this.departureDateAndTime = departureDateAndTime;
}

	public Airport getSourceAirport() {
		return sourceAirport;
	}
	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}
	public Airport getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(Airport destinationAirport) 
	{
		this.destinationAirport = destinationAirport;
	}
	public LocalDateTime getArrivalDateAndTime() {
		return arrivalDateAndTime;
	}
	public void setArrivalDateAndTime(LocalDateTime arrivalDateAndTime) {
		this.arrivalDateAndTime = arrivalDateAndTime;
	}
	public LocalDateTime getDepartureDateAndTime() {
		return departureDateAndTime;
	}
	public void setDepartureDateAndTime(LocalDateTime departureDateAndTime) {
		this.departureDateAndTime = departureDateAndTime;
	}

}
