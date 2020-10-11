package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadlines
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("00:00")); // time only
        assertFalse(Deadline.isValidDeadline("deadline")); // non-numeric
        assertFalse(Deadline.isValidDeadline("01-01-2020")); // date only
        assertFalse(Deadline.isValidDeadline("2a-02-2O2O aa00")); // alphabets within digits
        assertFalse(Deadline.isValidDeadline("2*-02-2O2O #a00")); // special characters within digits
        assertFalse(Deadline.isValidDeadline("01- 01-2020 1800")); // spaces within date
        assertFalse(Deadline.isValidDeadline("01-01-2020 18 00")); // spaces within time
        assertFalse(Deadline.isValidDeadline("00-00-0000 0000")); // all zeros
        assertFalse(Deadline.isValidDeadline("31-02-2020 1800")); // invalid day of month
        assertFalse(Deadline.isValidDeadline("01-99-2020 1800")); // invalid month
        assertFalse(Deadline.isValidDeadline("01-01-99999 1800")); // invalid year
        assertFalse(Deadline.isValidDeadline("01-01-2020 2400")); // invalid hour
        assertFalse(Deadline.isValidDeadline("01-01-2020 1860")); // invalid minute
        assertFalse(Deadline.isValidDeadline("2020-01-01 1800")); // invalid format ("uuuu-MM-dd HHmm")
        assertFalse(Deadline.isValidDeadline("04-30-2020 1800")); // invalid format ("MM-dd-uuuu HHmm")
        assertFalse(Deadline.isValidDeadline("1-01-2020 1800")); // invalid format ("d-MM-uuuu HHmm")
        assertFalse(Deadline.isValidDeadline("01-1-2020 1800")); // invalid format ("dd-M-uuuu HHmm")
        assertFalse(Deadline.isValidDeadline("01-01-20 1800")); // invalid format ("dd-MM-y HHmm")
        assertFalse(Deadline.isValidDeadline("01-01-20 18:00")); // invalid format ("dd-MM-y HH:mm")
        assertFalse(Deadline.isValidDeadline("01-01-2020 06:00 PM")); // invalid format ("dd-MM-uuuu hh:mm a")

        // valid deadline dates and time
        assertTrue(Deadline.isValidDeadline("30-04-2020 1800")); // exactly of format "dd-MM-uuuu HHmm"
        assertTrue(Deadline.isValidDeadline("31-12-2020 2359"));
    }

    @Test
    public void getLessonDateFromDay() {
        String expectedLessonDay = "Tuesday";
        Deadline dd = new Deadline("01-01-2000 1800");
        String actualLessonDay = dd.getLessonDateFromDay(expectedLessonDay);
        System.out.println(actualLessonDay);
        assertTrue(expectedLessonDay == actualLessonDay);
    }
}
