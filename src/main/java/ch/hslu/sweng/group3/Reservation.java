package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {

    private final int reservationID;
    private Show show;
    private Customer customer;
    private int numberOfSeats;
    private boolean isCollected;

    /**
     * No call outside the Class allowed!
     *
     * @param reservationID id of the reservation
     * @param numberOfSeats number of booked seats
     * @param isCollected the status of the reservation
     * @param customer the customer object that made the reservation
     * @param show the show object for which the reservation has been made
     */
    public Reservation(int reservationID, int numberOfSeats, boolean isCollected, Customer customer, Show show) {
        this.reservationID = reservationID;
        this.show = show;
        this.customer = customer;
        this.numberOfSeats = numberOfSeats;
        this.isCollected = isCollected;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Reservation && ((Reservation) o).getReservationID() == reservationID;
    }

    public int getReservationID() { return reservationID; }

    public Show getShow() { return show; }

    public Customer getCustomer() { return customer; }

    public int getNumberOfSeats() { return numberOfSeats; }

    public boolean isCollected() { return isCollected; }

    public void setShow(Show show) { this.show = show; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public void setNumberOfSeats(int numberOfSeats) {
        assert (numberOfSeats >= 0);
        this.numberOfSeats = numberOfSeats; }

    public void setCollected(boolean collected) { isCollected = collected; }
}
