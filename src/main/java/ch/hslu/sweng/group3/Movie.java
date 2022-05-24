package ch.hslu.sweng.group3;

public class Movie {

    private final int movieID;
    private int movieDuration;
    private String movieTitle;
    private boolean isActive;

    /**
     * No call outside the Class allowed!
     *
     * @param    movieID         the ID of the movie object to be created
     * @param    movieTitle      the Title of the movie object to be created
     * @param    movieDuration   the duration of the movie object to be created
     * @param    isActive        the Active status of the movie object to be created
     * */
    public Movie(int movieID, String movieTitle, int movieDuration, boolean isActive) {
        this.movieID = movieID;
        this.movieDuration = movieDuration;
        this.movieTitle = movieTitle;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Movie && ((Movie) o).getMovieID() == movieID;
    }

    public int getMovieID() { return movieID; }

    public int getMovieDuration() { return movieDuration; }

    public String getMovieTitle() { return movieTitle; }

    public boolean isActive() { return isActive; }

    public void setMovieDuration(int movieDuration) { this.movieDuration = movieDuration; }

    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public void setActive(boolean active) { isActive = active; }
}
