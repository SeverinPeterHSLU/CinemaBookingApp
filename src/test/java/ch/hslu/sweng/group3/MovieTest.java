package ch.hslu.sweng.group3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void testEqualsWithSameObj() {
        Movie m1 = new Movie(1, "Die Hard", 133, true);
        assertTrue(m1.equals(m1));
    }

    @Test
    void testEqualsWithEqualObj() {
        Movie m1 = new Movie(1, "Hanibal", 122, true);
        Movie m2 = new Movie(1, "Huhnerball", 133, false);
        assertEquals(m1, m2);
    }

    @Test
    void testEqualsWithNonEqualObj() {
        Movie m1 = new Movie(1, "Hanibal", 122, true);
        Movie m2 = new Movie(2, "Huhnerball", 133, false);
        assertFalse(m1.equals(m2));
    }

    @Test
    void testEqualsWithOtherClassObj() {
        Movie m1 = new Movie(1, "Top Gun", 112, true);
        Room r1 = new Room(1, 33);
        assertFalse(m1.equals(r1));
    }

}