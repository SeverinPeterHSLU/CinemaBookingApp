package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Show {

    private final int showID;
    private Movie movie;
    private Room room;
    private Date start;

    /**
     * No call outside the Class allowed!
     *
     * @param showID the show id
     * @param start the start date of the show
     * @param movie the movie object the show is showing
     * @param room the room object that the show takes place in
     */
    public Show(int showID, Date start, Movie movie, Room room) {
        this.showID = showID;
        this.movie = movie;
        this.room = room;
        this.start = start;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Show && ((Show) o).getShowID() == showID;
    }

    public int getShowID() { return showID; }

    public Movie getMovie() { return movie; }

    public Room getRoom() { return room; }

    public Date getStart() { return start; }

    public void setMovie(Movie movie) { this.movie = movie; }

    public void setRoom(Room room) { this.room = room; }

    public void setStart(Date start) { this.start = start; }
}
