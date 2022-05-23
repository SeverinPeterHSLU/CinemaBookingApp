package ch.hslu.sweng.group3;

import java.sql.*;
import java.util.ArrayList;

public class MovieDAO {
    private final Connection db;

    public MovieDAO(Connection connection) {
        db = connection;
    }

    /**
     *
     * @return true if there is a show in the DB that shows the movie, false if not
     */
    public boolean hasShow(int movieID) {
        String sql = "SELECT COUNT(MovieID) FROM Show WHERE MovieID = ?";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, movieID);

            ResultSet res = pstmnt.executeQuery();
            if (res.getInt("COUNT(MovieID)") != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param    movieTitle      the Title of the movie to be written to the DB
     * @param    movieDuration   the Duration of the movie to be written to the DB
     * @param    isActive        the Active status of the movie to be written to the DB
     */
    public void addMovie(String movieTitle, int movieDuration, boolean isActive) {
        String sql = "INSERT INTO Movie(Title, Duration, IsActive) VALUES(?,?,?)";
        try (PreparedStatement pstmnt = db.prepareStatement(sql)){
            pstmnt.setString(1, movieTitle);
            pstmnt.setInt(2, movieDuration);
            pstmnt.setBoolean(3, isActive);

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param    movie   the Movie object that has been modified by the program and needs to be updated in the DB
     * @return           true if the update was successful, false if not
     */
    public boolean editMovie(Movie movie) {
        assert (movie != null);
        if (!hasShow(movie.getMovieID())) {
            String sql = "UPDATE Movie SET Title = ? , Duration = ? , IsActive = ? WHERE MovieID = ?";
            try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
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

    /**
     *
     * @param    movie   the Movie object to be deleted form the DB
     * @return           true if deletion was successful, false if not
     */
    public boolean removeMovie(Movie movie) {
        assert (movie != null);
        String sql = "DELETE FROM Movie WHERE MovieID = ?";
        if (!hasShow(movie.getMovieID())) {
            try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
                pstmnt.setInt(1, movie.getMovieID());

                pstmnt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *
     * @param    movieID the ID of the movie to be loaded from the DB
     * @return           the loaded Movie object from the DB, null if no such entry exist
     */
    public Movie getMovie(int movieID) {
        String sql = "SELECT * FROM Movie WHERE MovieID = ?";
        Movie retMovie = null;
        try (PreparedStatement pstmnt = db.prepareStatement(sql)) {
            pstmnt.setInt(1, movieID);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retMovie = new Movie(res.getInt("MovieID"), res.getString("Title"),
                        res.getInt("Duration"), res.getBoolean("IsActive"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return retMovie;
    }

    /**
     *
     * @return   an ArrayList with all the movie entries in the DB, empty if table is empty
     */
    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> returnList = new ArrayList<>();
        String sqlSelect = "SELECT * FROM Movie;";
        try {
            Statement stmnt = db.createStatement();
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
}
