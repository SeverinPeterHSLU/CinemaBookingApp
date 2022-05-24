package ch.hslu.sweng.group3;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void testEqualsWithSameObj() {
        Reservation r1 = new Reservation(1, 12, false,
                new Customer(12, "noIdea@gmail.com"),
                new Show(1, new Date(System.currentTimeMillis()),
                    new Movie(2, "Rio Mare", 133, false),
                    new Room(17, 100)));
        assertTrue(r1.equals(r1));
    }

    @Test
    void testEqualsWithEqualObj() {
        Reservation r1 = new Reservation(1, 12, false,
                new Customer(12, "noIdea@gmail.com"),
                new Show(1, new Date(System.currentTimeMillis()),
                        new Movie(2, "Rio Mare", 133, false),
                        new Room(17, 100)));
        Reservation r2 = new Reservation(1, 13, true,
                new Customer(12, "noIdea@gmail.com"),
                new Show(15, new Date(System.currentTimeMillis()),
                        new Movie(2, "Rio Mare", 133, false),
                        new Room(17, 100)));
        assertEquals(r1, r2);
    }

    @Test
    void testEqualsWithNonEqualObj() {
        Reservation r1 = new Reservation(1, 12, false,
                new Customer(12, "noIdea@gmail.com"),
                new Show(1, new Date(System.currentTimeMillis()),
                        new Movie(2, "Rio Mare", 133, false),
                        new Room(17, 100)));
        Reservation r2 = new Reservation(2, 12, false,
                new Customer(12, "noIdea@gmail.com"),
                new Show(1, new Date(System.currentTimeMillis()),
                        new Movie(2, "Rio Mare", 133, false),
                        new Room(17, 100)));
        assertFalse(r1.equals(r2));
    }

    @Test
    void testEqualsWithOtherClassObj() {
        Reservation r1 = new Reservation(1, 12, false,
                new Customer(12, "noIdea@gmail.com"),
                new Show(1, new Date(System.currentTimeMillis()),
                        new Movie(2, "Rio Mare", 133, false),
                        new Room(17, 100)));
        Customer c1 = new Customer(1, "hello@world.com");
        assertFalse(r1.equals(c1));
    }

}