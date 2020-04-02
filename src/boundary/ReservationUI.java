package boundary;

import controller.ReservationController;
import entity.Reservation;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class ReservationUI {
    private static ReservationUI single_instance = null;
    private ReservationController reservationController = ReservationController.getInstance();
    private static Scanner in = new Scanner(System.in);
    
    private ReservationUI() {}

    public static ReservationUI getInstance()
    {
        if (single_instance == null)
            single_instance = new ReservationUI();
        return single_instance;
    }
    
    public void run() {
        String guestContact;
    	int choice = this.displayOptions();
        while (choice != -1) {
        	
            switch (choice) {
                case 1:
                	System.out.print("Please enter Guest Contact: ");
                	guestContact = in.next();
                    createReservationUI(guestContact);
                    break;
                case 2:
                	System.out.print("Please enter Guest Contact: ");
                	guestContact = in.next();
                    updateReservationUI(guestContact);
                    break;
                case 3:
                	System.out.print("Please enter Guest Contact: ");
                	guestContact = in.next();
                    removeReservationUI(guestContact);
                    break;
                case 4:
                    printReservationUI();
                    break;
                default:
                	break;
                /*case 0:
                	MainUI;
                	break;*/
            }choice = this.displayOptions();
        }
    }

    private void printReservationUI() {
		List<Reservation> reservations = reservationController.getAllReservations();
		if (reservations.isEmpty()) {
        	System.out.println("No records found!");
        }
		else {
			System.out.println("Printing all reservations in the system record...");
			for(Reservation reservation : reservations) {
				System.out.println(reservation.toString());
				System.out.print('\n');
			}
		}
		
	}

	private void removeReservationUI(String guestContact) {
		List<Reservation> reservations = reservationController.searchReservation(guestContact);//check if there is any existing reservations
        if (reservations.isEmpty()) {
        	System.out.println("No records found!");
        } 
        else {
        	System.out.println("Similar names are present in the system record: ");
        	for (Reservation reservation : reservations) {
            	System.out.println(reservation.toString());
            	System.out.println("Is this the reservation you are looking for?");
            	System.out.println("1. Yes");
            	System.out.println("2. No");
            	System.out.println("Your choice: ");
                int choice = in.nextInt();
                if(choice == 1) {
                	System.out.println("1. Remove this reservation");
                    System.out.println("2. Cancel operation");
                    System.out.println("Your choice: ");
                    choice = in.nextInt();
                    switch (choice) {
	                    case 1:
					        reservationController.removeReservation(reservation);
					        System.out.println("Reservation removed: ");
					        System.out.println(reservation.toString());
					        return;
	                    case 2:
	                        return;
                    }
                }
                else if(choice == 2) {}
            }
        }
		
	}

	private void updateReservationUI(String guestContact) {
		List<Reservation> reservations = reservationController.searchReservation(guestContact);//check if there is any existing reservations
        if (reservations.isEmpty()) {
        	System.out.println("No records found!");
        } 
        else {
        	System.out.println("Similar names are present in the system record: ");
        	for (Reservation reservation : reservations) {
            	System.out.println(reservation.toString());
            	System.out.println("Is this the reservation you are looking for?");
            	System.out.println("1. Yes");
            	System.out.println("2. No");
            	System.out.println("Your choice: ");
                int choice = in.nextInt();
                if(choice == 1) {
                	System.out.println("1. Update reservation details");
                    System.out.println("2. Cancel operation");
                    System.out.println("Your choice: ");
                    choice = in.nextInt();
                    switch (choice) {
	                    case 1:
	                    	String updatedPaymentmethod, updatedCheckIn, updatedcheckOut, updatedRoomNum;
	                		int  updatedAdult, updatedChild;
	                		System.out.println("Please enter the updated information: ");
					    	System.out.print("Payment Method: ");
					        updatedPaymentmethod = in.next();
					        System.out.print("Check In time (yyyy-MM-dd'T'HH:mm): ");
					        updatedCheckIn = in.next();
					        System.out.print("Check In time (yyyy-MM-dd'T'HH:mm): ");
					        updatedcheckOut = in.next();
					        System.out.print("Room Number: ");
					        updatedRoomNum = in.next();
					        System.out.print("Number Of Adults: ");
					        updatedAdult = in.nextInt();
					        System.out.print("Number Of Children: ");
					        updatedChild = in.nextInt();
					        Reservation updatedReservation = reservationController.updateReservation(reservation, updatedPaymentmethod, 
					        		updatedCheckIn, updatedcheckOut, updatedRoomNum, updatedAdult, updatedChild);
					        System.out.println("Reservation details updated: ");
					        System.out.println(updatedReservation.toString());
					        return;
	                    case 2:
	                        return;
                    }
                }
                else if(choice == 2) {}
            }
        }
		
	}

	private void createReservationUI(String guestContact) {
		List<Reservation> reservations = reservationController.searchReservation(guestContact);//check if there is any existing reservations
        if (reservations.isEmpty()) {
        	newReservationUI(guestContact);
        } 
        else {
        	System.out.println("Similar names are present in the system record: ");
        	for (Reservation reservation : reservations) {
            	System.out.println(reservation.toString());
            	System.out.println("Is this the reservation you are looking for?");
            	System.out.println("1. Yes");
            	System.out.println("2. No");
            	System.out.println("Your choice: ");
                int choice = in.nextInt();
                if(choice == 1) {
                	System.out.println("1. Update guest details");
                    System.out.println("2. Remove reservation");
                    System.out.println("3. Cancel operation");
                    System.out.println("Your choice: ");
                    choice = in.nextInt();
                    switch (choice) {
	                    case 1:
	                        updateReservationUI(guestContact);
	                    case 2:
	                    	removeReservationUI(guestContact);
	                    case 3:
	                        return;
                    }
                }
                else if(choice == 2) {
                	newReservationUI(guestContact);
                }
            }
        }
		
	}

	private void newReservationUI(String guestContact) {
		String paymentmethod, checkIn, checkOut, roomNum;
		int  adult, child;
    	System.out.print("Payment Method: ");
        paymentmethod = in.next();
        System.out.print("Check In time (yyyy-MM-dd'T'HH:mm): ");
        checkIn = in.next();
        System.out.print("Check In time (yyyy-MM-dd'T'HH:mm): ");
        checkOut = in.next();
        System.out.print("Room Number: ");//use room ui to check room availability
        roomNum = in.next();
        System.out.print("Number Of Adults: ");
        adult = in.nextInt();
        System.out.print("Number Of Children: ");
        child = in.nextInt();
        
		Reservation res = reservationController.createReservation(paymentmethod, guestContact, checkIn, 
				checkOut, adult, child, roomNum);
		System.out.println("New reservation added to the system: ");
        System.out.println(res.toString());
	}

	private int displayOptions() {
        System.out.println("-1. Exit");
        System.out.println("0. Go back to MainUI");
        System.out.println("1. Create a new reservation");
        System.out.println("2. Update reservation details");
        System.out.println("3. Remove a reservation");
        System.out.println("4. Print all reservations");
        System.out.println("Your choice: ");
        int choice = in.nextInt();
        return choice;
    }
    
}
