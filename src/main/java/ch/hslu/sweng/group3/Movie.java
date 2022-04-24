package ch.hslu.sweng.group3;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Movie {

    private int movieID, movieDuration;
    private String movieTitle;
    private boolean isActive;

    public Movie(int movieID, String movieTitle, int movieDuration, boolean isActive) {
        this.movieID = movieID;
        this.movieDuration = movieDuration;
        this.movieTitle = movieTitle;
        this.isActive = isActive;
    }

    public static void addMovie(String movieTitle, int movieDuration, boolean isActive) {
        try {
            Statement stmnt = App.db.createStatement();
            String sqlInsert = "INSERT INTO Movie (Title, Duration, isActive)" +
                    "Values ('" + movieTitle + "', '" + movieDuration + "', " + isActive + ");";
            stmnt.execute(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editMovie(int movieID, String movieTitle, int movieDuration, boolean isActive) {
        try {
            Statement stmnt = App.db.createStatement();
            String sqlUpdate = "INSERT INTO Movie (Title, Duration, isActive)" +
                    "Values ('" + movieTitle + "', '" + movieDuration + "', '" + isActive + "');";
            stmnt.execute(sqlUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeMovie(int movieID) {
        try {
            Statement stmnt = App.db.createStatement();
            String sqlDelete = "DELETE FROM Movie WHERE MovieID ='" + movieID + "';";
            stmnt.execute(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Movie> getMovies() {
        ArrayList<Movie> returnList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Movie;";
        try {
            Statement stmnt = App.db.createStatement();
            ResultSet res = stmnt.executeQuery(sqlSelect);
            while (res.next()) {
                returnList.add(new Movie(res.getInt("MovieID"), res.getString("Title"),
                        res.getInt("Duration"), res.getBoolean("IsActive")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    @Override
    public String toString() {
        return this.movieID + ", " + this.movieTitle + ", " + this.movieDuration  + ", " + this.isActive;
    }

    public int getMovieID() { return movieID; }

    public int getMovieDuration() { return movieDuration; }

    public String getMovieTitle() { return movieTitle; }

    public boolean isActive() { return isActive; }
}
