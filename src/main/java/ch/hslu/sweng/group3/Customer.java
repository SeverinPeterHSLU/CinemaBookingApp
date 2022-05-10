package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer {

    private final int customerID;
    private String email;

    /**
     * No call outside the Class allowed!
     *
     * @param customerID the id of the customer
     * @param email the email of the customer
     */
    public Customer(int customerID, String email) {
        this.customerID = customerID;
        this.email = email;
    }

    /**
     *
     * @param email the email of the Customer to be added to the DB
     */
    public static void addCustomer(String email) {
        String sql = "INSERT INTO Customer(Email) VALUES(?)";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setString(1, email);

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param customerID    the ID of the Customer to be loaded form the DB
     * @return              the Customer object loaded form the db, null if no such entry
     */
    public static Customer getCustomer(int customerID) {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        Customer retCustomer = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, customerID);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retCustomer = new Customer(res.getInt("CustomerID"), res.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retCustomer;
    }

    /**
     *
     * @param email the email of the Customer to be loaded from the DB
     * @return      the Customer object loaded form the DB, null if no such entry
     */
    public static Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE Email = ?";
        Customer retCustomer = null;
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setString(1, email);

            ResultSet res = pstmnt.executeQuery();
            if (res.next()) {
                retCustomer = new Customer(res.getInt("CustomerID"), res.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retCustomer;
    }

    /**
     *
     * @return an ArrayList with all Customers in the BD, empty if table is empty
     */
    public static ArrayList<Customer> getCustomers() {
        ArrayList<Customer> returnList = new ArrayList<>();
        String sql = "SELECT * FROM Customer;";
        try {
            Statement stmnt = App.db.createStatement();
            ResultSet res = stmnt.executeQuery(sql);
            while (res.next()) {
                returnList.add(new Customer(res.getInt("CustomerID"), res.getString("Email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    public int getCustomerID() { return customerID; }

    public String getEmail() { return email;}
}
