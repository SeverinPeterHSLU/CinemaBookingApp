package ch.hslu.sweng.group3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ShowDAO {
    private final Connection db;

    public ShowDAO(Connection connection) {
        db = connection;
    }

    /**
     *
     * @param start the start date of the show, not null
     * @param movie the movie object the show is showing, not null
     * @param room the room object where the show is shown, not null
     */
    public void addShow(Date start, Movie movie, Room room) {
        assert (start != null && movie != null && room != null);
        String sql = "INSERT INTO Show(Start, MovieID, RoomID) VALUES(?,?,?)";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)){
            pstmnt.setDate(1, new java.sql.Date(start.getTime()));
            pstmnt.setInt(2, movie.getMovieID());
            pstmnt.setInt(3, room.getRoomID());

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return true if there is a reservation for the show, false if not
     */
    private boolean hasReservation(int showID) {
        String sql = "SELECT COUNT(ShowID) FROM Reservation WHERE ShowID = ?";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, showID);

            ResultSet res = pstmnt.executeQuery();
            if (res.getInt("COUNT(ShowID)") != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int seatsAvailable(Show show) {
        int bookedSeats = 0;
        String sql = "SELECT NumberOfSeats FROM Reservation WHERE ShowID = ?";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, show.getShowID());

            ResultSet res = pstmnt.executeQuery();
            while (res.next()) {
                bookedSeats += res.getInt("NumberOfSeats");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return show.getRoom().getSeatsOfRoom() - bookedSeats;
    }

    /**
     *
     * @param show the show to be updated in the db, not null
     * @return true if update successful, false if not
     */
    public boolean editShow(Show show) {
        assert (show != null);
        if (!hasReservation(show.getShowID())) {
            String sql = "UPDATE Show SET Start = ?, MovieID = ?, RoomID = ? WHERE ShowID = ?";
            try (PreparedStatement pstmnt = db.prepareStatement(sql)){
                pstmnt.setDate(1, new java.sql.Date(show.getStart().getTime()));
                pstmnt.setInt(2, show.getMovie().getMovieID());
                pstmnt.setInt(3, show.getRoom().getRoomID());

                pstmnt.setInt(4, show.getShowID());

                pstmnt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *
     * @param show the show object to be deleted in the db, not null
     * @return true if deletion was successful, false if not
     */
    public boolean removeShow(Show show) {
        assert (show != null);
        if (!hasReservation(show.getShowID())) {
            String sql = "DELETE FROM Show WHERE ShowID = ?";
            try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
                pstmnt.setInt(1, show.getShowID());

                pstmnt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param showID the id of the show objet to be loaded from the db
     * @return the show objet loaded from the db, null if no such entry
     */
    public Show getShow(int showID) {
        assert (showID >= 0);
        String sql = "SELECT * FROM Show " +
                "INNER JOIN Movie ON Show.MovieID=Movie.MovieID " +
                "INNER JOIN Room ON Show.RoomID=Room.RoomID " +
                "WHERE ShowID = ?";
        Show retShow = null;
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, showID);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retShow = new Show(res.getInt("ShowID"), res.getDate("Start"),
                        new Movie(res.getInt("MovieID"), res.getString("Title"),
                                res.getInt("Duration"), res.getBoolean("IsActive")),
                        new Room(res.getInt("RoomID"), res.getInt("AmountOfSeats")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retShow;
    }

    /**
     *
     * @return an ArrayList of all show objects in the db, empty if no entries
     */
    public ArrayList<Show> getShows() {
        ArrayList<Show> returnList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Show " +
                "INNER JOIN Movie ON Show.MovieID=Movie.MovieID " +
                "INNER JOIN Room ON Show.RoomID=Room.RoomID";
        try {
            Statement stmnt = db.createStatement();
            ResultSet res = stmnt.executeQuery(sqlSelect);
            while (res.next()) {
                returnList.add(new Show(res.getInt("ShowID"), (Date)res.getDate("Start"),
                        new Movie(res.getInt("MovieID"), res.getString("Title"),
                                res.getInt("Duration"), res.getBoolean("IsActive")),
                        new Room(res.getInt("RoomID"), res.getInt("AmountOfSeats"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
