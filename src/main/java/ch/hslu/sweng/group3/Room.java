package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {

    private final int roomID;
    private int seatsOfRoom;

    public Room(int roomID, int seatsOfRoom) {
        this.roomID = roomID;
        this.seatsOfRoom = seatsOfRoom;
    }

    public void editRoom(int roomID, int seatsOfRoom) {
        String sql = "UPDATE Room SET SeatsOfRoom = ? WHERE RoomID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setInt(1, seatsOfRoom);
            pstmnt.setInt(2, roomID);

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Room getRoom(int roomID) {
        String sql = "SELECT * FROM Room WHERE RoomID = ?";
        Room retRoom = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, roomID);

            ResultSet res = pstmnt.executeQuery();
            retRoom = new Room(res.getInt("RoomID"), res.getInt("SeatsOfRoom"));

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retRoom;
    }

    public static ArrayList<Room> getRooms() {
        ArrayList<Room> returnList = new ArrayList<>();
        String sql = "SELECT * FROM Room;";
        try {
            Statement stmnt = App.db.createStatement();
            ResultSet res = stmnt.executeQuery(sql);
            while (res.next()) {
                returnList.add(new Room(res.getInt("RoomID"), res.getInt("SeatsOfRoom")));
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
