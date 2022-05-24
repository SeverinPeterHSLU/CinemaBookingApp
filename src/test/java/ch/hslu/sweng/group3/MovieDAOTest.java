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

class MovieDAOTest {

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
    void addMovie() throws Exception{
        MovieDAO subject = new MovieDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        String title = "Working Title";
        int duration = 122;
        boolean active = true;
        subject.addMovie(title,duration,active);
        Mockito.verify(mockPreparedStatement).setString(1, title);
        Mockito.verify(mockPreparedStatement).setInt(2, duration);
        Mockito.verify(mockPreparedStatement).setBoolean(3, active);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void editMovie() throws Exception{
        int id = 1;
        String title = "Die Hard";
        int duration = 20;
        boolean active = true;
        Movie movie = new Movie(id, title, duration, active);
        MovieDAO subject = new MovieDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement2).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement2.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("COUNT(ShowID)")).thenReturn(0);
        assertTrue(subject.editMovie(movie));
        Mockito.verify(mockPreparedStatement2).setInt(1, id);
        Mockito.verify(mockPreparedStatement).setString(1, title);
        Mockito.verify(mockPreparedStatement).setInt(2, duration);
        Mockito.verify(mockPreparedStatement).setBoolean(3, active);
        Mockito.verify(mockPreparedStatement).setInt(4, id);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void removeMovie() throws Exception{
        int id = 1;
        String title = "Die Hard";
        int duration = 20;
        boolean active = true;
        Movie movie = new Movie(id, title, duration, active);
        MovieDAO subject = new MovieDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement2).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement2.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("COUNT(ShowID)")).thenReturn(0);
        assertTrue(subject.removeMovie(movie));
        Mockito.verify(mockPreparedStatement2).setInt(1, id);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getMovie() throws Exception{
        int id = 1;
        String title = "Die Hard";
        int duration = 20;
        boolean active = true;
        MovieDAO subject = new MovieDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("MovieID")).thenReturn(id);
        Mockito.when(mockResult.getString("Title")).thenReturn(title);
        Mockito.when(mockResult.getInt("Duration")).thenReturn(duration);
        Mockito.when(mockResult.getBoolean("IsActive")).thenReturn(active);
        Mockito.when(mockResult.next()).thenReturn(true);

        Movie movie = subject.getMovie(id);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
        Mockito.verify(mockPreparedStatement).executeQuery();
        assertEquals(movie.getMovieID(), id);
        assertEquals(movie.getMovieTitle(), title);
        assertEquals(movie.getMovieDuration(), duration);
        assertEquals(movie.isActive(), active);
    }

    @Test
    void getMovies() throws Exception{
        int id1 = 1;
        int id2 = 2;
        String title1 = "Die Hard";
        String title2 = "Die Hard 2";
        int duration1 = 50;
        int duration2 = 20;
        boolean active1 = true;
        boolean active2 = false;
        MovieDAO subject = new MovieDAO(connectionMock);
        Mockito.when(connectionMock.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(any())).thenReturn(mockResult);
        Mockito.when(mockResult.getInt("MovieID")).thenReturn(id1).thenReturn(id2);
        Mockito.when(mockResult.getString("Title")).thenReturn(title1).thenReturn(title2);
        Mockito.when(mockResult.getInt("Duration")).thenReturn(duration1).thenReturn(duration2);
        Mockito.when(mockResult.getBoolean("IsActive")).thenReturn(active1).thenReturn(active2);
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        ArrayList<Movie> movies = subject.getMovies();
        Mockito.verify(mockStatement).executeQuery(any());
        assertEquals(movies.get(0).getMovieID(), id1);
        assertEquals(movies.get(0).getMovieTitle(), title1);
        assertEquals(movies.get(0).getMovieDuration(), duration1);
        assertEquals(movies.get(0).isActive(), active1);
        assertEquals(movies.get(1).getMovieID(), id2);
        assertEquals(movies.get(1).getMovieTitle(), title2);
        assertEquals(movies.get(1).getMovieDuration(), duration2);
        assertEquals(movies.get(1).isActive(), active2);
    }
}