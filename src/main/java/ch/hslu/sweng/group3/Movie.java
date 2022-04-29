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

    private boolean hasShow() {
        String sql = "SELECT COUNT(MovieID) FROM Show WHERE MovieID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, this.movieID);

            ResultSet res = pstmnt.executeQuery();
            if (res.getInt("COUNT(MovieID)") == 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public static boolean editMovie(Movie movie) {
        if (!movie.hasShow()) {
            String sql = "UPDATE Movie SET Title = ? , Duration = ? , IsActive = ? WHERE MovieID = ?";
            try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
                pstmnt.setString(1, movie.getMovieTitle());
                pstmnt.setInt(2, movie.getMovieDuration());
                pstmnt.setBoolean(3, movie.isActive());

                pstmnt.setInt(4, movie.getMovieID());

                pstmnt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void removeMovie(Movie movie) {
        String sql = "DELETE FROM Movie WHERE MovieID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setInt(1, movie.getMovieID());

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Movie getMovie(int movieID) {
        String sql = "SELECT * FROM Movie WHERE MovieID = ?";
        Movie retMovie = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, movieID);

            ResultSet res = pstmnt.executeQuery();
            retMovie = new Movie(res.getInt("MovieID"), res.getString("Title"),
                    res.getInt("Duration"), res.getBoolean("IsActive"));

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retMovie;
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

    public int getMovieID() { return movieID; }

    public int getMovieDuration() { return movieDuration; }

    public String getMovieTitle() { return movieTitle; }

    public boolean isActive() { return isActive; }

    public void setMovieDuration(int movieDuration) { this.movieDuration = movieDuration; }

    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public void setActive(boolean active) { isActive = active; }
}
