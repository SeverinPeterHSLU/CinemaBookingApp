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

class ReservationDAOTest {

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
    void addReservation() throws Exception{
        int resID = 1;
        Show show = new Show(5, new Date(System.currentTimeMillis()),
                new Movie(2, "Sadness", 1000, true),
                new Room(7, 222));
        Customer customer = new Customer(12, "email");
        int seats = 12;
        ReservationDAO subject = new ReservationDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt(1)).thenReturn(resID);
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(false);
        assertEquals(resID, subject.addReservation(seats, customer, show));
        Mockito.verify(mockPreparedStatement).setInt(1, seats);
        Mockito.verify(mockPreparedStatement).setInt(3, customer.getCustomerID());
        Mockito.verify(mockPreparedStatement).setInt(4, show.getShowID());
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void editReservation() throws Exception {
        int id = 1;
        int seats = 20;
        Show show = new Show(5, new Date(System.currentTimeMillis()),
                new Movie(2, "Sadness", 1000, true),
                new Room(7, 222));
        Customer customer = new Customer(27, "mymail@helloworld.com");
        boolean collectd = true;
        Reservation reservation = new Reservation(id, seats, collectd, customer, show);
        ReservationDAO subject = new ReservationDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        assertTrue(subject.editReservation(reservation));
        Mockito.verify(mockPreparedStatement).setInt(1, seats);
        Mockito.verify(mockPreparedStatement).setInt(2, show.getShowID());
        Mockito.verify(mockPreparedStatement).setInt(3, customer.getCustomerID());
        Mockito.verify(mockPreparedStatement).setBoolean(4, collectd);
        Mockito.verify(mockPreparedStatement).setInt(5, id);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getReservation() throws Exception{
        int id1 = 1;
        int seats = 12;
        boolean collected = false;
        Customer customer = new Customer(1, "abc@mail.com");
        Movie movie = new Movie(1, "Balde", 150, true);
        Room room = new Room(1, 75);
        Show show = new Show(1, new Date(System.currentTimeMillis()), movie, room);

        ReservationDAO subject = new ReservationDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("ReservationID")).thenReturn(id1);
        Mockito.when(mockResult.getInt("NumberOfSeats")).thenReturn(seats);
        Mockito.when(mockResult.getBoolean("IsCollected")).thenReturn(collected);
        Mockito.when(mockResult.getInt("CustomerID")).thenReturn(customer.getCustomerID());
        Mockito.when(mockResult.getString("Email")).thenReturn(customer.getEmail());
        Mockito.when(mockResult.getInt("ShowID")).thenReturn(show.getShowID());
        Mockito.when(mockResult.getDate("Start")).thenReturn(new java.sql.Date(show.getStart().getTime()));
        Mockito.when(mockResult.getInt("MovieID")).thenReturn(movie.getMovieID());
        Mockito.when(mockResult.getString("Title")).thenReturn(movie.getMovieTitle());
        Mockito.when(mockResult.getInt("Duration")).thenReturn(movie.getMovieDuration());
        Mockito.when(mockResult.getBoolean("IsActive")).thenReturn(movie.isActive());
        Mockito.when(mockResult.getInt("RoomID")).thenReturn(room.getRoomID());
        Mockito.when(mockResult.getInt("AmountOfSeats")).thenReturn(room.getSeatsOfRoom());
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(false);

        Reservation reservation = subject.getReservation(id1);
        Mockito.verify(mockPreparedStatement).setInt(1, id1);
        Mockito.verify(mockPreparedStatement).executeQuery();
        assertEquals(reservation.getReservationID(), id1);
        assertEquals(reservation.getNumberOfSeats(), seats);
        assertEquals(reservation.isCollected(), collected);
        assertEquals(reservation.getShow(), show);
        assertEquals(reservation.getCustomer(), customer);
    }

    @Test
    void getReservations() throws Exception{
        int id1 = 1;
        int id2 = 2;
        int seats1 = 12;
        int seats2 = 22;
        boolean collected1 = false;
        boolean collected2 = true;
        Customer customer1 = new Customer(1, "abc@mail.com");
        Customer customer2 = new Customer(2, "hello@mail.to");
        Movie movie1 = new Movie(1, "Blade", 150, true);
        Movie movie2 = new Movie(2, "Troy", 160, true);
        Room room1 = new Room(1, 75);
        Room room2 = new Room(2, 50);
        Show show1 = new Show(1, new Date(System.currentTimeMillis()), movie1, room1);
        Show show2 = new Show(1, new Date(System.currentTimeMillis() + 20000000), movie2, room2);

        ReservationDAO subject = new ReservationDAO(connectionMock);
        Mockito.when(connectionMock.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(any())).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("ReservationID")).thenReturn(id1).thenReturn(id2);
        Mockito.when(mockResult.getInt("NumberOfSeats")).thenReturn(seats1).thenReturn(seats2);
        Mockito.when(mockResult.getBoolean("IsCollected")).thenReturn(collected1).thenReturn(collected2);
        Mockito.when(mockResult.getInt("CustomerID")).thenReturn(customer1.getCustomerID()).thenReturn(customer2.getCustomerID());
        Mockito.when(mockResult.getString("Email")).thenReturn(customer1.getEmail()).thenReturn(customer2.getEmail());
        Mockito.when(mockResult.getInt("ShowID")).thenReturn(show1.getShowID()).thenReturn(show2.getShowID());
        Mockito.when(mockResult.getDate("Start")).thenReturn(new java.sql.Date(show1.getStart().getTime())).thenReturn(new java.sql.Date(show2.getStart().getTime()));
        Mockito.when(mockResult.getInt("MovieID")).thenReturn(movie1.getMovieID()).thenReturn(movie2.getMovieID());
        Mockito.when(mockResult.getString("Title")).thenReturn(movie1.getMovieTitle()).thenReturn(movie2.getMovieTitle());
        Mockito.when(mockResult.getInt("Duration")).thenReturn(movie1.getMovieDuration()).thenReturn(movie2.getMovieDuration());
        Mockito.when(mockResult.getBoolean("IsActive")).thenReturn(movie1.isActive()).thenReturn(movie2.isActive());
        Mockito.when(mockResult.getInt("RoomID")).thenReturn(room1.getRoomID()).thenReturn(room2.getRoomID());
        Mockito.when(mockResult.getInt("AmountOfSeats")).thenReturn(room1.getSeatsOfRoom()).thenReturn(room1.getSeatsOfRoom());
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        ArrayList<Reservation> reservations = subject.getReservations();
        Mockito.verify(mockStatement).executeQuery(any());
        assertEquals(reservations.get(0).getReservationID(), id1);
        assertEquals(reservations.get(0).getNumberOfSeats(), seats1);
        assertEquals(reservations.get(0).isCollected(), collected1);
        assertEquals(reservations.get(0).getShow(), show1);
        assertEquals(reservations.get(0).getCustomer(), customer1);
        assertEquals(reservations.get(1).getReservationID(), id2);
        assertEquals(reservations.get(1).getNumberOfSeats(), seats2);
        assertEquals(reservations.get(1).isCollected(), collected2);
        assertEquals(reservations.get(1).getShow(), show2);
        assertEquals(reservations.get(1).getCustomer(), customer2);
    }
}