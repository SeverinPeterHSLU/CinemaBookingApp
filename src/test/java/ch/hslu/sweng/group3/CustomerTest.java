package ch.hslu.sweng.group3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testEqualsWithSameObj() {
        Customer customer = new Customer(1, "Hello@mail.com");
        assertTrue(customer.equals(customer));
    }

    @Test
    void testEqualsWithEqualObj() {
        Customer c1 = new Customer(1, "mail");
        Customer c2 = new Customer(1, "MAil");
        assertEquals(c1, c2);
    }

    @Test
    void testEqualsWithNonEqualObj() {
        Customer c1 = new Customer(1, "morgen");
        Customer c2 = new Customer(2, "morgen");
        assertFalse(c1.equals(c2));
    }

    @Test
    void testEqualsWithOtherClassObj() {
        Customer c1 = new Customer(1, "hello");
        Room r1 = new Room(1, 33);
        assertFalse(c1.equals(r1));
    }
}