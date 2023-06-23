package com.lld.movieticket;

import java.util.Date;
import java.util.List;

import com.lld.vendingMachine.Payment;

public class Booking {



	private String bookingNumber;
	private int numberOfSeats;
	private Date createdOn;
	private BookingStatus status;
	private Show show;
	private List<ShowSeat> seats;
	private Payment payment;


	//	public boolean makePayment(Payment payment);
	//	public boolean cancel();
	//	public boolean assignSeats(List<ShowSeat> seats);
}
