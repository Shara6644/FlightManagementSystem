package com.cg.fms.pl;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DTDemo {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter year");
		int year =scanner.nextInt();
		System.out.println("enter month");
		int month =scanner.nextInt();
		System.out.println("enter date");
		int date =scanner.nextInt();
		LocalDateTime specificDate = LocalDateTime.of(year, month , date, 10, 10, 30);
		System.out.println("Specific Date="+specificDate);
		
		//date in String
		String dateString = "2018-07-14T17";
		 
		//Build formatter
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		         
		//Parse String to LocalDateTime
		LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
		 
		//Verify
		System.out.println("Parsed LocalDateTime : " + dateTime);    
	}
}
