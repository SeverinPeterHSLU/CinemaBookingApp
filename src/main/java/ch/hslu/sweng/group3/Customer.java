package ch.hslu.sweng.group3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customer {

    private final int customerID;
    private String email;

    public Customer(int customerID, String email) {
        this.customerID = customerID;
        this.email = email;
    }

    private static void addCustomer(String email) {
        String sql = "INSERT INTO Customer(Email) VALUES(?)";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)){
            pstmnt.setString(1, email);

            pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer getCustomer(int customerID) {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement pstmnt = App.db.prepareStatement(sql)) {
            pstmnt.setInt(1, customerID);

            ResultSet res = pstmnt.executeQuery();
            return new Customer(res.getInt("CustomerID"), res.getString("Email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
