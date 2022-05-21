package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {

    private final int roomID;
    private int seatsOfRoom;

    /**
     *No call outside the Class allowed!
     *
     * @param roomID        ID of Room object to be created
     * @param seatsOfRoom   number of seats in the Room
     */
    public Room(int roomID, int seatsOfRoom) {
        this.roomID = roomID;
        this.seatsOfRoom = seatsOfRoom;
    }

    /**
     *
     * @param room the Room object to be updated in the db
     * @return true if update successful, false if not
     */
    public static boolean editRoom(Room room) {
        assert (room != null);
        String sql = "UPDATE Room SET AmountofSeats = ? WHERE RoomID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setInt(1, room.getSeatsOfRoom());
            pstmnt.setInt(2, room.getRoomID());

            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param roomID the id of the room to be loaded form the db
     * @return the room object loaded from the db or null if no such entry
     */
    public static Room getRoom(int roomID) {
        String sql = "SELECT * FROM Room WHERE RoomID = ?";
        Room retRoom = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, roomID);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retRoom = new Room(res.getInt("RoomID"), res.getInt("AmountofSeats"));
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
    public static ArrayList<Room> getRooms() {
        ArrayList<Room> returnList = new ArrayList<>();
        String sql = "SELECT * FROM Room;";
        try {
            Statement stmnt = App.db.createStatement();
            ResultSet res = stmnt.executeQuery(sql);
            while (res.next()) {
                returnList.add(new Room(res.getInt("RoomID"), res.getInt("AmountofSeats")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public int getRoomID() { return roomID; }

    public int getSeatsOfRoom() { return seatsOfRoom; }

    public void setSeatsOfRoom(int seatsOfRoom) { this.seatsOfRoom = seatsOfRoom; }
}
