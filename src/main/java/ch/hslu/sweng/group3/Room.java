package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

    public int getRoomID() { return roomID; }

    public int getSeatsOfRoom() { return seatsOfRoom; }

    public void setSeatsOfRoom(int seatsOfRoom) { this.seatsOfRoom = seatsOfRoom; }
}
