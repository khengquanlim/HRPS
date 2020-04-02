package controller;

import entity.Reservation;
import entity.Reservation.PaymentMethod;
import entity.Reservation.ReservationStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservationController {
	private List<Reservation> reservationList;
    private static ReservationController reservationController = null;

    private ReservationController() {
        this.reservationList = new ArrayList<>();
    }

    public static ReservationController getInstance() {
        if (reservationController == null) {
            reservationController = new ReservationController();
        }
        return reservationController;
    }
	
    public Reservation createReservation(String paymentmethod, String guestContact, String checkIn, 
    		String checkOut, int adult, int child, String roomNum) {
    	
    	UUID reservationCode = UUID.randomUUID();
    	PaymentMethod payment = PaymentMethod.valueOf(paymentmethod.toUpperCase());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");//input format:yyyy-mm-ddThh:mm	
    	LocalDateTime checkInTime = LocalDateTime.parse(checkIn, formatter);
		LocalDateTime checkOutTime = LocalDateTime.parse(checkOut, formatter);
		Integer numAdult = Integer.valueOf(adult);
		Integer numChild = Integer.valueOf(child);
		ReservationStatus status = ReservationStatus.CONFIRMED;
        Reservation reservation = new Reservation(reservationCode, payment, guestContact, checkInTime, 
    			checkOutTime, numAdult, numChild, roomNum, status);        
        this.reservationList.add(reservation);
        return reservation;
    }
    
    public Reservation updateReservation(Reservation reservation, String updatedPaymentmethod, 
    		String updatedCheckIn, String updatedCheckOut, String updatedRoomNum, int updatedAdult, int updatedChild) {
    	
    	PaymentMethod payment = PaymentMethod.valueOf(updatedPaymentmethod.toUpperCase());
    	reservation.setPayment(payment);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    	LocalDateTime checkInTime = LocalDateTime.parse(updatedCheckIn, formatter);
		LocalDateTime checkOutTime = LocalDateTime.parse(updatedCheckOut, formatter);
        reservation.setCheckInTime(checkInTime);
        reservation.setCheckOutTime(checkOutTime);
        reservation.setNumAdult(updatedAdult);
        reservation.setNumChild(updatedChild);
        return reservation;
    }
    
    public List<Reservation> searchReservation(String guestContact) {
        return reservationList.stream().filter(o -> o.getGuestContact().equals(guestContact)).collect(Collectors.toList());//return a list of reservations with the specified contact number
    }
    
    public List<Reservation> getAllReservations(){
    	return reservationList;
    }
    
    public void removeReservation(Reservation reservation) {
    		reservationList.remove(reservation);
    }
	
}
