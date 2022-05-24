package ch.hslu.sweng.group3;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionCheckTest {

    @Test
    void isValuePositiveNumber() {
        assertTrue(ExceptionCheck.isValuePositiveNumber("12"));
        assertFalse(ExceptionCheck.isValuePositiveNumber("0"));
        assertFalse(ExceptionCheck.isValuePositiveNumber("-22"));
    }

    @Test
    void isValidDateFormat() {
        assertTrue(ExceptionCheck.isValidDateFormat("2022.12.20", "18.30"));
        assertFalse(ExceptionCheck.isValidDateFormat("12.02.2022", "19.00"));
        assertFalse(ExceptionCheck.isValidDateFormat("2022.10.10", "19:00"));
    }

    @Test
    void isDateInFuture() {
        assertTrue(ExceptionCheck.isDateInFuture(new Date(System.currentTimeMillis() + 10000), new SimpleDateFormat("")));
        assertFalse(ExceptionCheck.isDateInFuture(new Date(System.currentTimeMillis() - 1), new SimpleDateFormat()));
    }

    @Test
    void isDateInCurrentWeek() {
        assertTrue(ExceptionCheck.isDateInCurrentWeek(new Date()));
        assertFalse(ExceptionCheck.isDateInCurrentWeek(new Date(System.currentTimeMillis() - 10)));
        assertFalse(ExceptionCheck.isDateInCurrentWeek(new Date(System.currentTimeMillis() + 604800000)));
    }

    @Test
    void isValueAnEmail() {
        assertTrue(ExceptionCheck.isValueAnEmail("email@mail.com"));
        assertFalse(ExceptionCheck.isValueAnEmail("mail.mail.com"));
        assertFalse(ExceptionCheck.isValueAnEmail("@mail.com"));
        assertFalse(ExceptionCheck.isValueAnEmail("mail@mail"));
    }
}