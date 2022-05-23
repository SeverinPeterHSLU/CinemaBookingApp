package ch.hslu.sweng.group3;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.sql.*;
import java.util.Properties;

public class App {

    protected static Connection db;
    protected static MovieDAO movieDAO;
    protected static CustomerDAO customerDAO;
    protected static RoomDAO roomDAO;
    protected static ShowDAO showDAO;
    protected static ReservationDAO reservationDAO;

    private static boolean hasNoTables() {
        String sql = "SELECT name FROM sqlite_schema\n" +
                "WHERE type='table'\n" +
                "ORDER BY name;";

        try {
            Statement stmnt = db.createStatement();
            ResultSet res = stmnt.executeQuery(sql);
            if (!res.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void setUpDB() {
        try {
            Statement stmnt = db.createStatement();
            stmnt.execute(SQL.movieTable);
            stmnt.execute(SQL.roomTable);
            stmnt.execute(SQL.customerTable);
            stmnt.execute(SQL.showTable);
            stmnt.execute(SQL.reservationTable);
            stmnt.execute(SQL.insertRoom1);
            stmnt.execute(SQL.insertRoom2);
            stmnt.execute(SQL.insertRoom3);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() {
        if (db == null) {
            try {
                db = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
                System.out.println("got connected");
                if (hasNoTables()) {
                    setUpDB();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        connect();
        movieDAO = new MovieDAO(db);
        customerDAO = new CustomerDAO(db);
        roomDAO = new RoomDAO(db);
        showDAO = new ShowDAO(db);
        reservationDAO = new ReservationDAO(db);

        new MainFrame().setVisible(true);
    }

}
