package ch.hslu.sweng.group3;

import java.util.*;
import java.sql.*;
import java.util.Date;


public class RoomDAO {
    private final Connection db;

    public RoomDAO(Connection connection) {
        db = connection;
    }

    /**
     *
     * @param room the Room object to be updated in the db
     * @return true if update successful, false if not
     */
    public boolean editRoom(Room room) {
        assert (room != null);
        String sql = "UPDATE Room SET AmountofSeats = ? WHERE RoomID = ?";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)){
            pstmnt.setInt(1, room.getSeatsOfRoom());
            pstmnt.setInt(2, room.getRoomID());

            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Show isOccupiedBy(Date startDate, Date endDate, Room room) {
        assert (startDate != null && endDate != null && endDate.compareTo(startDate) > 0);
        String sql = "SELECT MAX(Duration) FROM Movie WHERE IsActive = True";
        int maxDuration = 0;
        try {
            Statement stmnt = App.db.createStatement();

            ResultSet res = stmnt.executeQuery(sql);
            if (res.next()) {
                maxDuration = res.getInt("MAX(Duration)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Set<Integer> showIDs = new HashSet<>();

        sql = "SELECT ShowID FROM Show WHERE Show.Start <= ? AND Show.Start + ? > ? OR Show.Start > ? AND Show.Start < ? AND RoomID = ?;";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setDate(1, new java.sql.Date(startDate.getTime()));
            pstmnt.setInt(2, maxDuration * 60000);
            pstmnt.setDate(3, new java.sql.Date(startDate.getTime()));
            pstmnt.setDate(4, new java.sql.Date(startDate.getTime()));
            pstmnt.setDate(5, new java.sql.Date(endDate.getTime()));
            pstmnt.setInt(6, room.getRoomID());

            ResultSet res = pstmnt.executeQuery();
            while (res.next()) {
                showIDs.add(res.getInt(1));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        ArrayList<Show> shows = new ArrayList<>();
        for (int id : showIDs) {
            shows.add(App.showDAO.getShow(id));
        }
        for (Show show : shows) {
            Calendar c = Calendar.getInstance();
            c.setTime(show.getStart());
            c.add(Calendar.MINUTE, show.getMovie().getMovieDuration());
            java.util.Date end = c.getTime();

            if (startDate.after(show.getStart()) && startDate.before(end) || endDate.after(show.getStart()) && endDate.before(end)) {
                return show;
            }
        }
        return null;
    }
    /**
     *
     * @param roomID the id of the room to be loaded form the db
     * @return the room object loaded from the db or null if no such entry
     */
    public Room getRoom(int roomID) {
        String sql = "SELECT * FROM Room WHERE RoomID = ?";
        Room retRoom = null;
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, roomID);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retRoom = new Room(res.getInt("RoomID"), res.getInt("AmountOfSeats"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retRoom;
    }

    /**
     *
     * @return an ArrayList of all room objects in the db, empty if no entries
     */
    public ArrayList<Room> getRooms() {
        ArrayList<Room> returnList = new ArrayList<>();
        String sql = "SELECT * FROM Room;";
        try {
            Statement stmnt = db.createStatement();
            ResultSet res = stmnt.executeQuery(sql);
            while (res.next()) {
                returnList.add(new Room(res.getInt("RoomID"), res.getInt("AmountOfSeats")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
