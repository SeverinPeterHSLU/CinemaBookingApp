package ch.hslu.sweng.group3;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {

    protected static Connection db;
    private static final String url = "C:\\Users\\sevip\\CinemaDB.db";
    private static final String preAmble ="jdbc:sqlite:";
    public static void main( String[] args )
    {
        try {
            db = DriverManager.getConnection(preAmble.concat(url));
            System.out.println("got connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Movie.addMovie("Die Hard", 122, true);
        for (Movie movie : Movie.getMovies()) {
            System.out.println(movie.getMovieID());
            System.out.println(movie.getMovieTitle());
            System.out.println(movie.getMovieDuration());
            System.out.println(movie.isActive());
            Movie.removeMovie(movie.getMovieID());
        }

        try {
            if (db != null) {
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
