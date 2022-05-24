package ch.hslu.sweng.group3;

import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CustomerDAOTest {
    @Mock
    Connection connectionMock;
    @Mock
    Statement mockStatement;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    ResultSet mockResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCustomer() throws Exception {
        CustomerDAO subject = new CustomerDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        String email = "email@email.com";
        subject.addCustomer(email);
        Mockito.verify(mockPreparedStatement).setString(1, email);
        Mockito.verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void getCustomer() throws Exception {
        CustomerDAO subject = new CustomerDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        String email = "email@email.com";
        int id = 12;
        Mockito.when(mockResult.getInt(any())).thenReturn(id);
        Mockito.when(mockResult.getString(any())).thenReturn(email);
        Mockito.when(mockResult.next()).thenReturn(true);

        Customer customer = subject.getCustomer(id);
        Mockito.verify(mockPreparedStatement).setInt(1, id);
        Mockito.verify(mockPreparedStatement).executeQuery();
        assertEquals(customer.getCustomerID(), id);
        assertEquals(customer.getEmail(), email);
    }

    @Test
    void getCustomerByEmail() throws Exception{
        CustomerDAO subject = new CustomerDAO(connectionMock);
        Mockito.when(connectionMock.prepareStatement(any())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResult);
        String email = "email@email.com";
        int id = 12;
        Mockito.when(mockResult.getInt(any())).thenReturn(id);
        Mockito.when(mockResult.getString(any())).thenReturn(email);
        Mockito.when(mockResult.next()).thenReturn(true);

        Customer customer = subject.getCustomerByEmail(email);
        Mockito.verify(mockPreparedStatement).setString(1, email);
        Mockito.verify(mockPreparedStatement).executeQuery();
        assertEquals(customer.getCustomerID(), id);
        assertEquals(customer.getEmail(), email);
    }

    @Test
    void getCustomers() throws Exception{
        CustomerDAO subject = new CustomerDAO(connectionMock);
        Mockito.when(connectionMock.createStatement()).thenReturn(mockStatement);
        Mockito.when(mockStatement.executeQuery(any())).thenReturn(mockResult);
        String email1 = "email@email.com";
        String email2 = "new@mail.com";
        int id1 = 1;
        int id2 = 2;
        Mockito.when(mockResult.getInt(any())).thenReturn(id1).thenReturn(id2);
        Mockito.when(mockResult.getString(any())).thenReturn(email1).thenReturn(email2);
        Mockito.when(mockResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        ArrayList<Customer> customers = subject.getCustomers();
        Mockito.verify(mockStatement).executeQuery(any());
        assertEquals(customers.get(0).getCustomerID(), id1);
        assertEquals(customers.get(0).getEmail(), email1);
        assertEquals(customers.get(1).getCustomerID(), id2);
        assertEquals(customers.get(1).getEmail(), email2);
    }
}