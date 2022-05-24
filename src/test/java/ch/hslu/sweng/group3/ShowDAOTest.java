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
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ShowDAOTest {

    @Mock
    Connection connectionMock;
    @Mock
    Statement mockStatement;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    PreparedStatement mockPreparedStatement2;
    @Mock
    ResultSet mockResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addShow() throws Exception{
        Date start = new Date(System.currentTimeMillis());
        Movie movie = new Movie(1, "Pirates of the Caribbean", 99, true);
        Room room = new Room(1, 300);
        ShowDAO subject = new ShowDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        subject.addShow(start, movie, room);
        Mockito.verify(mockPreparedStatement).setDate(1, new java.sql.Date(start.getTime()));
        Mockito.verify(mockPreparedStatement).setInt(2, movie.getMovieID());
        Mockito.verify(mockPreparedStatement).setInt(3, room.getRoomID());
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void seatsAvailable() throws Exception{
        int id = 1;
        Date start = new Date(System.currentTimeMillis());
        Movie movie = new Movie(1, "Pirates of the Caribbean", 99, true);
        Room room = new Room(1, 300);
        Show show = new Show(id, start, movie, room);
        ShowDAO subject = new ShowDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(mockResult.getInt("NumberOfSeats")).thenReturn(100).thenReturn(50);
        assertEquals(subject.seatsAvailable(show), 150);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
    }

    @Test
    void editShow() throws Exception{
        int id = 1;
        Date start = new Date(System.currentTimeMillis());
        Movie movie = new Movie(1, "Pirates of the Caribbean", 99, true);
        Room room = new Room(1, 300);
        Show show = new Show(id, start, movie, room);
        ShowDAO subject = new ShowDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement2).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement2.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("COUNT(ReservationID)")).thenReturn(0);
        assertTrue(subject.editShow(show));
        Mockito.verify(mockPreparedStatement2).setInt(1, id);
        Mockito.verify(mockPreparedStatement).setDate(1, new java.sql.Date(start.getTime()));
        Mockito.verify(mockPreparedStatement).setInt(2, movie.getMovieID());
        Mockito.verify(mockPreparedStatement).setInt(3, room.getRoomID());
        Mockito.verify(mockPreparedStatement).setInt(4, id);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void removeShow() throws Exception{
        int id = 1;
        Date start = new Date(System.currentTimeMillis());
        Movie movie = new Movie(1, "Pirates of the Caribbean", 99, true);
        Room room = new Room(1, 300);
        Show show = new Show(id, start, movie, room);
        ShowDAO subject = new ShowDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement2).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement2.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("COUNT(ReservationID)")).thenReturn(0);
        assertTrue(subject.removeShow(show));
        Mockito.verify(mockPreparedStatement2).setInt(1, id);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getShow() throws Exception{
        int id1 = 1;
        Date start1 = new Date(System.currentTimeMillis());
        Movie movie1 = new Movie(1, "Die Hard", 122, true);
        Room room1 = new Room(1, 50);

        ShowDAO subject = new ShowDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("ShowID")).thenReturn(id1);
        Mockito.when(mockResult.getDate("Start")).thenReturn(new java.sql.Date(start1.getTime()));
        Mockito.when(mockResult.getInt("MovieID")).thenReturn(movie1.getMovieID());
        Mockito.when(mockResult.getString("Title")).thenReturn(movie1.getMovieTitle());
        Mockito.when(mockResult.getInt("Duration")).thenReturn(movie1.getMovieDuration());
        Mockito.when(mockResult.getBoolean("IsActive")).thenReturn(movie1.isActive());
        Mockito.when(mockResult.getInt("RoomID")).thenReturn(room1.getRoomID());
        Mockito.when(mockResult.getInt("AmountOfSeats")).thenReturn(room1.getSeatsOfRoom());
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(false);

        Show show = subject.getShow(id1);
        Mockito.verify(mockPreparedStatement).setInt(1, id1);
        Mockito.verify(mockPreparedStatement).executeQuery();
        assertEquals(show.getShowID(), id1);
        assertEquals(show.getStart(), start1);
        assertEquals(show.getMovie(), movie1);
        assertEquals(show.getRoom(), room1);
    }

    @Test
    void getShows() throws Exception {
        int id1 = 1;
        int id2 = 2;
        Date start1 = new Date(System.currentTimeMillis());
        Date start2 = new Date(System.currentTimeMillis() + 28000000);
        Movie movie1 = new Movie(1, "Die Hard", 122, true);
        Movie movie2 = new Movie(2, "Die Hard 2", 140, false);
        Room room1 = new Room(1, 50);
        Room room2 = new Room(2, 25);

        ShowDAO subject = new ShowDAO(connectionMock);
        Mockito.when(connectionMock.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(any())).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("ShowID")).thenReturn(id1).thenReturn(id2);
        Mockito.when(mockResult.getDate("Start")).thenReturn(new java.sql.Date(start1.getTime())).thenReturn(new java.sql.Date(start2.getTime()));
        Mockito.when(mockResult.getInt("MovieID")).thenReturn(movie1.getMovieID()).thenReturn(movie2.getMovieID());
        Mockito.when(mockResult.getString("Title")).thenReturn(movie1.getMovieTitle()).thenReturn(movie2.getMovieTitle());
        Mockito.when(mockResult.getInt("Duration")).thenReturn(movie1.getMovieDuration()).thenReturn(movie2.getMovieDuration());
        Mockito.when(mockResult.getBoolean("IsActive")).thenReturn(movie1.isActive()).thenReturn(movie2.isActive());
        Mockito.when(mockResult.getInt("RoomID")).thenReturn(room1.getRoomID()).thenReturn(room2.getRoomID());
        Mockito.when(mockResult.getInt("AmountOfSeats")).thenReturn(room1.getSeatsOfRoom()).thenReturn(room2.getSeatsOfRoom());
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        ArrayList<Show> shows = subject.getShows();
        Mockito.verify(mockStatement).executeQuery(any());
        assertEquals(shows.get(0).getShowID(), id1);
        assertEquals(shows.get(0).getStart(), start1);
        assertEquals(shows.get(0).getMovie(), movie1);
        assertEquals(shows.get(0).getRoom(), room1);
        assertEquals(shows.get(1).getShowID(), id2);
        assertEquals(shows.get(1).getStart(), start2);
        assertEquals(shows.get(1).getMovie(), movie2);
        assertEquals(shows.get(1).getRoom(), room2);
    }
}