package ch.hslu.sweng.group3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class App {

    protected static Connection db;

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
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainFrame mainframe = new MainFrame();

        try {
            db = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
            System.out.println("got connected");
            if (hasNoTables()) {
                System.out.println("DB has no Tables!");
                setUpDB();
                System.out.println(hasNoTables());
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
