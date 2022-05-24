package ch.hslu.sweng.group3;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    @Test
    void testEqualsWithSameObj() {
        Show s1 = new Show(1, new Date(System.currentTimeMillis()),
                new Movie(2, "Rio Mare", 133, false),
                new Room(17, 100));
        assertTrue(s1.equals(s1));
    }

    @Test
    void testEqualsWithEqualObj() {
        Show s1 = new Show(1, new Date(System.currentTimeMillis()),
                new Movie(2, "Rio Mare", 133, false),
                new Room(17, 100));
        Show s2 = new Show(1, new Date(System.currentTimeMillis() - 1000000),
                new Movie(5, "Rio Mare", 133, false),
                new Room(12, 100));
        assertEquals(s1, s2);
    }

    @Test
    void testEqualsWithNonEqualObj() {
        Show s1 = new Show(1, new Date(System.currentTimeMillis()),
                new Movie(2, "Rio Mare", 133, false),
                new Room(17, 100));
        Show s2 = new Show(7, new Date(System.currentTimeMillis() - 1000000),
                new Movie(5, "Rio Mare", 133, false),
                new Room(12, 100));
        assertFalse(s1.equals(s2));
    }

    @Test
    void testEqualsWithOtherClassObj() {
        Show s1 = new Show(1, new Date(System.currentTimeMillis()),
                new Movie(2, "Rio Mare", 133, false),
                new Room(17, 100));
        Customer c1 = new Customer(1, "hello@world.com");
        assertFalse(s1.equals(c1));
    }

}