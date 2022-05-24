package ch.hslu.sweng.group3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RoomDAOTest {

    @Mock
    Connection connectionMock;
    @Mock
    Statement mockStatement;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    ResultSet mockResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void editRoom() throws Exception{
        int id = 1;
        int seats = 20;
        Room room = new Room(id, seats);
        RoomDAO subject = new RoomDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        assertTrue(subject.editRoom(room));
        Mockito.verify(mockPreparedStatement).setInt(1, seats);
        Mockito.verify(mockPreparedStatement).setInt(2, id);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void isOccupiedBy() throws Exception {

    }

    @Test
    void getRoom() throws Exception{
        RoomDAO subject = new RoomDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        int seats = 50;
        int id = 12;
        Mockito.when(mockResult.getInt("RoomID")).thenReturn(id);
        Mockito.when(mockResult.getInt("AmountOfSeats")).thenReturn(seats);
        Mockito.when(mockResult.next()).thenReturn(true);

        Room room = subject.getRoom(id);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
        Mockito.verify(mockPreparedStatement).executeQuery();
        assertEquals(room.getRoomID(), id);
        assertEquals(room.getSeatsOfRoom(), seats);
    }

    @Test
    void getRooms() throws Exception{
        RoomDAO subject = new RoomDAO(connectionMock);
        Mockito.when(connectionMock.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(any())).thenReturn(mockResult);
        int seats1 = 50;
        int seats2 = 25;
        int id1 = 1;
        int id2 = 2;
        Mockito.when(mockResult.getInt("RoomID")).thenReturn(id1).thenReturn(id2);
        Mockito.when(mockResult.getInt("AmountOfSeats")).thenReturn(seats1).thenReturn(seats2);
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        ArrayList<Room> rooms = subject.getRooms();
        Mockito.verify(mockStatement).executeQuery(any());
        assertEquals(rooms.get(0).getRoomID(), id1);
        assertEquals(rooms.get(0).getSeatsOfRoom(), seats1);
        assertEquals(rooms.get(1).getRoomID(), id2);
        assertEquals(rooms.get(1).getSeatsOfRoom(), seats2);
    }
}