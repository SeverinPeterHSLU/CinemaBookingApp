package ch.hslu.sweng.group3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testEqualsWithSameObj() {
        Room r1 = new Room(1, 22);
        assertTrue(r1.equals(r1));
    }

    @Test
    void testEqualsWithEqualObj() {
        Room r1 = new Room(1, 22);
        Room r2 = new Room(1, 25);
        assertEquals(r1, r2);
    }

    @Test
    void testEqualsWithNonEqualObj() {
        Room r1 = new Room(1, 22);
        Room r2 = new Room(2, 25);
        assertFalse(r1.equals(r2));
    }

    @Test
    void testEqualsWithOtherClassObj() {
        Room r1 = new Room(1, 22);
        Customer c1 = new Customer(1, "hello@world.com");
        assertFalse(r1.equals(c1));
    }
}