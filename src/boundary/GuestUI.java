package boundary;

import controller.GuestController;
import entity.Guest;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class GuestUI {
    // static variable single_instance of type GuestUI
    private static GuestUI single_instance = null;
    private GuestController guestController = GuestController.getInstance();
    private static Scanner in = new Scanner(System.in);
    
    // private constructor restricted to this class itself
    private GuestUI() {}

    // static method to create instance of GuestUI class
    public static GuestUI getInstance()
    {
        if (single_instance == null)
            single_instance = new GuestUI();
        return single_instance;
    }

    public void run() {
        int choice = this.displayOptions();
        String guestName;
        while (choice != -1) {
        	
            switch (choice) {
                case 1:
                	System.out.print("Please enter Guest Name: ");
                	guestName = in.next();
                    createGuestUI(guestName);
                    guestName = null;
                    break;
                case 2:
                	System.out.print("Please enter Guest Name: ");
                	guestName = in.next();
                    updateGuestUI(guestName);
                    guestName = null;
                    break;
                case 3:
                	System.out.print("Please enter Guest Name: ");
                	guestName = in.next();
                    findGuestUI(guestName);
                    guestName = null;
                    break;
                default:
                	break;
                /*case 0:
                	MainUI;
                	break;*/
            }choice = this.displayOptions();
        }
    }

    private int displayOptions() {
        System.out.println("-1. Exit");
        System.out.println("0. Go back to MainUI");
        System.out.println("1. Create a new guest");
        System.out.println("2. Update guest details");
        System.out.println("3. Find a guest");
        System.out.println("Your choice: ");
        int choice = in.nextInt();
        return choice;
    }

    private void createGuestUI(String guestName) {
        List<Guest> guests = guestController.searchGuest(guestName);//check if there is any guest with the same name
        if (guests.isEmpty()) {
            newGuestUI(guestName);
        } 
        else {
        	System.out.println("Similar names are present in the system record: ");
        	for (Guest guest : guests) {
            	System.out.println(guest.toString());
            	System.out.println("Is this who you are looking for?");
            	System.out.println("1. Yes");
            	System.out.println("2. No");
            	System.out.println("Your choice: ");
                int choice = in.nextInt();
                if(choice == 1) {
                	System.out.println("1. Update guest details");
                    System.out.println("2. Cancel operation");
                    System.out.println("Your choice: ");
                    choice = in.nextInt();
                    switch (choice) {
	                    case 1:
	                        updateGuestUI(guestName);
	                    case 2:
	                        return;
                    }
                }
                else if(choice == 2) {
                	newGuestUI(guestName);
                }
            }
        }
        
    }
    
    private void updateGuestUI(String guestName) {
    	List<Guest> guests = guestController.searchGuest(guestName);//check if there is any guest with the same name
        if (guests.isEmpty()) {
        	System.out.println("No records found!");
        } 
        else {
        	System.out.println("Shown below are guests with this name in the system record: ");
        	for (Guest guest : guests) {
            	System.out.println(guest.toString());
            	System.out.println("Is this who you are looking for?");
            	System.out.println("1. Yes");
            	System.out.println("2. No");
            	System.out.println("Your choice: ");
                int choice = in.nextInt();
                if(choice == 1) {
                	System.out.println("1. Update guest details");
                    System.out.println("2. Cancel operation");
                    System.out.println("Your choice: ");
                    choice = in.nextInt();
                    switch (choice) {
	                    case 1:
	                        String updatedGuestName, creditCardDetails, address, country, gender, passport, drivingLicense, nationality, contact;
					    	System.out.println("Please enter the updated information: ");
					    	System.out.print("Guest Name: ");
					    	updatedGuestName = in.next();
					    	System.out.print("Credit Card: ");
					        creditCardDetails = in.next();
					        System.out.print("Address: ");
					        address = in.next();
					        System.out.print("Country: ");
					        country = in.next();
					        System.out.print("Gender: ");
					        gender = in.next();
					        System.out.print("Passport: ");
					        passport= in.next();
					        System.out.print("Driving License: ");
					        drivingLicense = in.next();
					        System.out.print("Nationality: ");
					        nationality = in.next();
					        System.out.print("Contact: ");
					        contact = in.next();
					        Guest updatedGuest = guestController.updateGuest(guest, updatedGuestName, creditCardDetails, address, country, gender, passport, drivingLicense, nationality, contact);
					        System.out.println("Guest details updated: ");
					        System.out.println(updatedGuest.toString());
					        return;
	                    case 2:
	                        return;
                    }
                }
                else if(choice == 2) {}
            }
        }
    	
    }
    
    private void newGuestUI(String guestName) {
    	String creditCardDetails, address, country, gender, passport, drivingLicense, nationality, contact;
    	System.out.print("Credit Card: ");
        creditCardDetails = in.next();
        System.out.print("Address: ");
        address = in.next();
        System.out.print("Country: ");
        country = in.next();
        System.out.print("Gender: ");
        gender = in.next();
        System.out.print("Passport: ");
        passport= in.next();
        System.out.print("Driving License: ");
        drivingLicense = in.next();
        System.out.print("Nationality: ");
        nationality = in.next();
        System.out.print("Contact: ");
        contact = in.next();
        Guest guest = guestController.createGuest(guestName, creditCardDetails, address, country, gender, passport, drivingLicense, nationality, contact);
        System.out.println("New guest added to the system: ");
        System.out.println(guest.toString());
    }
    



    private void findGuestUI(String guestName) {
        List<Guest> guests = guestController.searchGuest(guestName);
        if(guests.isEmpty()) {
    		System.out.println("No records found!");
    	}
    	else{
    		for (Guest guest : guests) {
            System.out.println(guest.toString());
    		}
    	}
    }
}
