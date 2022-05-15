package ch.hslu.sweng.group3;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class App {

    protected static Connection db;
    private static final String configLocation = "src/config.properties";


    public static void main(String[] args) {
        MainFrame mainframe = new MainFrame();

        Properties properties = new Properties();
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


        try {
            if (db != null) {
                db.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
