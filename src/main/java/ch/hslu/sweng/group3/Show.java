package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Show {

    private final int showID;
    private Movie movie;
    private Room room;
    private Date start;

    public Show(int showID, Date start, Movie movie, Room room) {
        this.showID = showID;
        this.movie = movie;
        this.room = room;
        this.start = start;
    }

    public static void addShow(Date start, Movie movie, Room room) {
        String sql = "INSERT INTO Show(Start, MovieID, RoomID) VALUES(?,?,?)";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setDate(1, new java.sql.Date(start.getTime()));
            pstmnt.setInt(2, movie.getMovieID());
            pstmnt.setInt(3, room.getRoomID());

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean hasReservation() {
        return true;
    }

    public static boolean editShow(Show show) {
        if (!show.hasReservation()) {
            String sql = "UPDATE Show SET Start = ?, MovieID = ?, RoomID = ? WHERE ShowID = ?";
            try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
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

    public static boolean removeShow(Show show) {
        if (!show.hasReservation()) {
            String sql = "DELETE FROM Show WHERE ShowID = ?";
            try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
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

    public static Show getShow(int showID) {
        String sql = "SELECT * FROM Show WHERE ShowID = ?";
        Show retShow = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, showID);

            ResultSet res = pstmnt.executeQuery();
            retShow = new Show(res.getInt("ShowID"), res.getDate("Start"),
                    Movie.getMovie(res.getInt("MovieID")), Room.getRoom(res.getInt("RoomID")));
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retShow;
    }

    public static ArrayList<Show> getShows() {
        ArrayList<Show> returnList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Show;";
        try {
            Statement stmnt = App.db.createStatement();
            ResultSet res = stmnt.executeQuery(sqlSelect);
            while (res.next()) {
                returnList.add(new Show(res.getInt("ShowID"), res.getDate("Start"),
                        Movie.getMovie(res.getInt("MovieID")), Room.getRoom(res.getInt("RoomID"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public int getShowID() { return showID; }

    public Movie getMovie() { return movie; }

    public Room getRoom() { return room; }

    public Date getStart() { return start; }

    public void setMovie(Movie movie) { this.movie = movie; }

    public void setRoom(Room room) { this.room = room; }

    public void setStart(Date start) { this.start = start; }
}
