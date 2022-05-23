package ch.hslu.sweng.group3;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDAO {
    private final Connection db;

    public ReservationDAO(Connection connection) {
        db = connection;
    }

    /**
     *
     * @param numberOfSeats the number of seats to be booked, not 0
     * @param customer the customer object, not null
     * @param show the sow object, not null
     */
    public int addReservation(int numberOfSeats, Customer customer, Show show) {
        int retInt = -1;
        assert (numberOfSeats >= 0 && customer != null && show != null);
        String sql = "INSERT INTO Reservation(NumberOfSeats, IsCollected, CustomerID, ShowID) VALUES(?,?,?,?)";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, numberOfSeats);
            pstmnt.setBoolean(2, false);
            pstmnt.setInt(3, customer.getCustomerID());
            pstmnt.setInt(4, show.getShowID());

            pstmnt.executeUpdate();

            ResultSet res = pstmnt.getGeneratedKeys();
            if (res.next()) {
                retInt = res.getInt(1);
            } else {
                //Error handling...
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retInt;
    }

    /**
     *
     * @param reservation the reservation to be updated in the db, not null
     * @return true if update successful, false if not
     */
    public boolean editReservation(Reservation reservation) {
        assert (reservation != null);
        String sql = "UPDATE Reservation SET NumberOFSeats = ?, CustomerID = ?, ShowID = ?, IsCollected = ? WHERE ReservationID = ?";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)){
            pstmnt.setInt(1, reservation.getNumberOfSeats());
            pstmnt.setInt(2, reservation.getShow().getShowID());
            pstmnt.setInt(3, reservation.getCustomer().getCustomerID());
            pstmnt.setBoolean(4, reservation.isCollected());

            pstmnt.setInt(5, reservation.getReservationID());

            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param reservation the reservation to be deleted
     * @return true if deletion was successful, false if not
     */
    public boolean removeReservation(Reservation reservation) {
        assert (reservation != null);
        String sql = "DELETE FROM Reservation WHERE ReservationID = ?";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, reservation.getReservationID());

            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param reservationID the id of the reservation to be loaded from the db
     * @return the loaded reservation object loaded from the db, null if no such entry
     */
    public Reservation getReservation(int reservationID) {
        String sql = "SELECT * FROM Reservation\n" +
                "INNER JOIN Show ON Reservation.ShowID = Show.ShowID\n" +
                "INNER JOIN Customer ON Reservation.CustomerID = Customer.CustomerID\n" +
                "INNER JOIN Movie ON Show.MovieID=Movie.MovieID\n" +
                "INNER JOIN Room ON Show.RoomID=Room.RoomID\n" +
                "WHERE ReservationID = ?";
        Reservation retReservation = null;
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, reservationID);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retReservation = new Reservation(res.getInt("ReservationID"),
                        res.getInt("NumberOfSeats"), res.getBoolean("IsCollected"),
                        new Customer(res.getInt("CustomerID"), res.getString("Email")),
                        new Show(res.getInt("ShowID"), res.getDate("Start"),
                                new Movie(res.getInt("MovieID"), res.getString("Title"),
                                        res.getInt("Duration"), res.getBoolean("IsActive")),
                                new Room(res.getInt("RoomID"), res.getInt("AmountOfSeats"))));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retReservation;
    }

    /**
     *
     * @return an ArrayList of all reservations in the db, empty if no entries
     */
    public ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> returnList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Reservation\n" +
                "INNER JOIN Show ON Reservation.ShowID = Show.ShowID\n" +
                "INNER JOIN Customer ON Reservation.CustomerID = Customer.CustomerID\n" +
                "INNER JOIN Movie ON Show.MovieID=Movie.MovieID\n" +
                "INNER JOIN Room ON Show.RoomID=Room.RoomID";
        try {
            Statement stmnt = db.createStatement();
            ResultSet res = stmnt.executeQuery(sqlSelect);
            while (res.next()) {
                returnList.add(new Reservation(res.getInt("ReservationID"),
                        res.getInt("NumberOfSeats"), res.getBoolean("IsCollected"),
                        new Customer(res.getInt("CustomerID"), res.getString("Email")),
                        new Show(res.getInt("ShowID"), res.getDate("Start"),
                                new Movie(res.getInt("MovieID"), res.getString("Title"),
                                        res.getInt("Duration"), res.getBoolean("IsActive")),
                                new Room(res.getInt("RoomID"), res.getInt("AmountOfSeats")))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
