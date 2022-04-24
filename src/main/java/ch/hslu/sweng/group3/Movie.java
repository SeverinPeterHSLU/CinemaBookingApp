package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Movie {

    private final int movieID;
    private int movieDuration;
    private String movieTitle;
    private boolean isActive;

    public Movie(int movieID, String movieTitle, int movieDuration, boolean isActive) {
        this.movieID = movieID;
        this.movieDuration = movieDuration;
        this.movieTitle = movieTitle;
        this.isActive = isActive;
    }

    public static void addMovie(String movieTitle, int movieDuration, boolean isActive) {
        String sql = "INSERT INTO Movie(Title, Duration, IsActive) VALUES(?,?,?)";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setString(1, movieTitle);
            pstmnt.setInt(2, movieDuration);
            pstmnt.setBoolean(3, isActive);

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void editMovie(int movieID, String movieTitle, int movieDuration, boolean isActive) {
        String sql = "UPDATE Movie SET Title = ? , Duration = ? , IsActive = ? WHERE MovieID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setString(1, movieTitle);
            pstmnt.setInt(2, movieDuration);
            pstmnt.setBoolean(3, isActive);

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeMovie(int movieID) {
        String sql = "DELETE FROM Movie WHERE MovieID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setInt(1, movieID);

            pstmnt.executeUpdate();
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

    public void setMovieDuration(int movieDuration) { this.movieDuration = movieDuration; }

    public String getMovieTitle() { return movieTitle; }

    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }





}
