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

    public Reservation(int reservationID, int numberOfSeats, boolean isCollected, Customer customer, Show show) {
        this.reservationID = reservationID;
        this.show = show;
        this.customer = customer;
        this.numberOfSeats = numberOfSeats;
        this.isCollected = isCollected;
    }

    public static void addReservation(int numberOfSeats, Customer customer, Show show) {
        String sql = "INSERT INTO Reservation(NumberOfSeats, IsCollected, CustomerID, ShowID) VALUES(?,?,?,?)";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setInt(1, numberOfSeats);
            pstmnt.setBoolean(2, false);
            pstmnt.setInt(3, customer.getCustomerID());
            pstmnt.setInt(4, show.getShowID());

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean editReservation(Reservation reservation) {
        String sql = "UPDATE Reservation SET NumberOFSeats = ?, CustomerID = ?, ShowID = ? WHERE ReservationID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setInt(1, reservation.getNumberOfSeats());
            pstmnt.setInt(2, reservation.getShow().getShowID());
            pstmnt.setInt(3, reservation.getCustomer().getCustomerID());

            pstmnt.setInt(4, reservation.getReservationID());

            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeReservation(Reservation reservation) {
        String sql = "DELETE FROM Reservation WHERE ReservationID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, reservation.getReservationID());

            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Reservation getReservation(int reservationID) {
        String sql = "SELECT * FROM Reservation WHERE ReservationID = ?";
        Reservation retReservation = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, reservationID);

            ResultSet res = pstmnt.executeQuery();
            retReservation = new Reservation(res.getInt("ReservationID"),
                    res.getInt("NumberOfSeats"), res.getBoolean("IsCollected"),
                    Customer.getCustomer(res.getInt("CustomerID")),
                    Show.getShow(res.getInt("ShowID")));
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retReservation;
    }

    public static ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> returnList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Reservation;";
        try {
            Statement stmnt = App.db.createStatement();
            ResultSet res = stmnt.executeQuery(sqlSelect);
            while (res.next()) {
                returnList.add(new Reservation(res.getInt("ReservationID"),
                        res.getInt("NumberOfSeats"), res.getBoolean("IsCollected"),
                        Customer.getCustomer(res.getInt("CustomerID")),
                        Show.getShow(res.getInt("ShowID"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public int getReservationID() { return reservationID; }

    public Show getShow() { return show; }

    public Customer getCustomer() { return customer; }

    public int getNumberOfSeats() { return numberOfSeats; }

    public boolean isCollected() { return isCollected; }

    public void setShow(Show show) { this.show = show; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public void setNumberOfSeats(int numberOfSeats) { this.numberOfSeats = numberOfSeats; }

    public void setCollected(boolean collected) { isCollected = collected; }
}
