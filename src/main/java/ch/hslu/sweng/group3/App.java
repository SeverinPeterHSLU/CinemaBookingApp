package ch.hslu.sweng.group3;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.Properties;

public class App {

    protected static Connection db;
    private static final String configLocation = "src/config.properties";
    private static Scanner kb;

    /**
     * @return null, -non-empty string
     * @post number of iterations displayed (logging)
     */
    private static String readNonEmpty() {
        // read non-null, non-empty string;
        String s = kb.nextLine().trim();
        while (s == null || s.equals("")) {
            System.out.print("must not be blank -- try again: ");
            s = kb.nextLine().trim();
        }
        assert s != null && !s.trim().equals("");
        return s;
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        kb = new Scanner(System.in);
        try (FileInputStream propsInputStream = new FileInputStream(configLocation)) {
            properties.load(propsInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            db = DriverManager.getConnection(properties.getProperty("DB_URL"));
            System.out.println("got connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String option, startName,endName;
        String[] mainMenuOptions = {"Movie Program", "New Resrevation", "Reservation Administration",
            "Movie Administration", "Show Administration", "Room Administration"};
        option = readNonEmpty();
        while(option.charAt(0)!= 'Q' && option.charAt(0)!= 'q') {
            int i = 1;
            System.out.println("Cinema Booking System");
            while (i <= mainMenuOptions.length) {
                System.out.println("[" + i + "] " + mainMenuOptions[i - 1]);
            }
            switch (option.charAt(0)) {
                case '1' :
                    System.out.println("[1] " + mainMenuOptions[1]);
                    ArrayList<Show> shows = Show.getShows();
                    Collections.sort(shows, new Comparator<Show>(){
                        public int compare(Show show1, Show show2) {
                            return show1.getStart().compareTo(show2.getStart());
                        }
                    });
                    for (Show show : shows) {
                        System.out.println(show.getStart() + " - " + show.getMovie().getMovieTitle()
                        + " (" + show.getRoom().getRoomID() + ")");
                    }
                    System.out.println("[R] Rteurn to Main Menu");
                    while (!(option.compareTo("r") == 0 || option.compareTo("R") == 0)) {
                        option = readNonEmpty();
                    }
                    break;
                case '2' :
                    System.out.println("[2] " + mainMenuOptions[2] + "\n");
                    System.out.println("Please fill in the reservation details to create a new one\n");
                    System.out.println("Select a Show!\n");

                    ArrayList<Show> shows2 = Show.getShows();
                    Collections.sort(shows2, new Comparator<Show>() {
                        public int compare(Show show1, Show show2) {
                            return show1.getStart().compareTo(show2.getStart());
                        }
                    });

                    int j = 0;
                    while (j < shows2.size()) {
                        Show show = shows2.get(j);
                        System.out.println("[" + j + "] " + show.getStart() + " - " + show.getMovie().getMovieTitle()
                                + " (" + show.getRoom().getRoomID() + ")");
                        j++;
                    }

                    option = readNonEmpty();
                    while (!(Integer.parseInt(option) < 0 || Integer.parseInt(option) >= shows2.size())) {
                        option = readNonEmpty();
                    }
                    Show reservationShow = shows2.get(Integer.parseInt(option));

                    System.out.println("Customer email: ");
                    String email = readNonEmpty();
                    System.out.println("Number of Seats: ");
                    int numberOfSeats = Integer.parseInt(readNonEmpty());

                    Customer customer = Customer.getCustomerByEmail(email);
                    if (customer == null) {
                        Customer.addCustomer(email);
                    }


                    Reservation.addReservation(numberOfSeats, customer, reservationShow);
                    break;
                case '3' :
                    System.out.println("Enter Reservation ID: ");
                    int reservationID = Integer.parseInt(readNonEmpty());
                    Reservation reservation = Reservation.getReservation(reservationID);

                    System.out.println("What do you want to change?\n");
                    System.out.println("[1] Number of seats");
                    System.out.println("[2] status");

                    option = readNonEmpty();
                    switch (option.charAt(0)) {
                        case '1' :
                            System.out.println("Type in the new number: ");
                            int newNumberOfSeats = Integer.parseInt(readNonEmpty());
                            reservation.setNumberOfSeats(newNumberOfSeats);
                            Reservation.editReservation(reservation);
                        case '2' :
                            System.out.println("Type 'C' if the customer has collected the reservation: ");
                            option = readNonEmpty();
                            if (option.charAt(0) == 'C' || option.charAt(0) == 'c') {
                                reservation.setCollected(true);
                                Reservation.editReservation(reservation);
                            }
                    }
                    break;
                case '4' :
                    System.out.println("[4] " + mainMenuOptions[4] + "\n");
                    ArrayList<Movie> movies = Movie.getMovies();
                    for (Movie movie : movies) {
                        System.out.println(movie.getMovieID() + " " + movie.getMovieTitle() + "\n");
                    }
                    System.out.println("[A] Add Movie\n[E] Edit Movie\n[D] Delete Movie");
                    option =readNonEmpty();
                    switch (option.charAt(0)) {
                        case 'A' :
                            System.out.println("Enter the fields to create a new movie entry");
                            System.out.println("Movie Title: ");
                            String title = readNonEmpty();
                            System.out.println("Movie Durration");
                            int duration = Integer.parseInt(readNonEmpty());

                            Movie.addMovie(title, duration, true);
                        case 'E' :
                            System.out.println("Enter Movie ID of movie to be edited: ");
                            int id = Integer.parseInt(readNonEmpty());
                            Movie movie = Movie.getMovie(id);
                            System.out.println("\n" + movie.getMovieTitle() +"\n" + movie.getMovieDuration()
                                    + "\n" + movie.isActive() + "\n");
                            System.out.println();

                        case 'D' :

                    }
                case '5' :

                case '6' :

                default :
                    System.out.println("Please enter a valid Option");

            }
            option = readNonEmpty();
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
