package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents the deadline of an assignment in the assignment list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should only be in the format 'dd-MM-uuuu HHmm', and contain a valid date and time";
    public static final String DEADLINE_DATE_TIME_FORMAT = "dd-MM-uuuu HHmm";
    public final String value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = deadline;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime taskDate = LocalDateTime.parse(test, inputFormat);
            taskDate.format(inputFormat);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    public String getLessonDateFromDay(String lessonDay) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        LocalDateTime currentDate = LocalDateTime.now();
        DayOfWeek dayOfTheWeek = currentDate.getDayOfWeek();

        int dayIndex = 0;
        for (int i = 0; i < 7; i++) {
            String day = days[i];
            if (day.equals(lessonDay)) {
                dayIndex = i + 1;
                break;
            }
        }

        // assuming we are always going to count future lessons (omit lessons that have already passed)
        int numOfDaysLessonIsAheadOfCurrentDate = Math.abs(dayIndex - dayOfTheWeek.getValue());
        LocalDateTime lessonDate = currentDate.plusDays(numOfDaysLessonIsAheadOfCurrentDate);

        return lessonDate.format(DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
