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

    @Override
    public boolean equals(Object o) {
        return o instanceof Customer && ((Customer) o).getCustomerID() == customerID;
    }

    public int getCustomerID() { return customerID; }

    public String getEmail() { return email;}

    public void setEmail(String email) {
        this.email = email; }
}
