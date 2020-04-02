package controller;

import entity.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestController {
    private List<Guest> guestList;
    private static GuestController guestController = null;

    private GuestController() {
        this.guestList = new ArrayList<>();
    }

    public static GuestController getInstance() {
        if (guestController == null) {
            guestController = new GuestController();
        }
        return guestController;
    }

    public Guest createGuest(String guestName, String creditCardDetails, String address, String country, String gender, String passport, String drivingLicense, String nationality, String contact) {
        Guest guest = new Guest(guestName, creditCardDetails, address, country, gender, passport, drivingLicense, nationality, contact);
        this.guestList.add(guest);
        return guest;
    }
    
    public Guest updateGuest(Guest guest, String guestName, String creditCardDetails, String address, String country, String gender, String passport, String drivingLicense, String nationality, String contact) {
        guest.setGuestName(guestName);
        guest.setCreditCardDetails(creditCardDetails);
        guest.setAddress(address);
        guest.setCountry(country);
        guest.setGender(gender);
        guest.setPassport(passport);
        guest.setDrivingLicense(drivingLicense);
        guest.setNationality(nationality);
        guest.setContact(contact);
        return guest;
    }

    public List<Guest> searchGuest(String guestName) {
        return guestList.stream().filter(o -> o.getGuestName().equals(guestName)).collect(Collectors.toList());//return a list of guest with the specified name
    }
}
