package seedu.address.timetable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTimetableData.DATA;
import static seedu.address.testutil.TypicalTimetableData.VALID_MODULE_CODE_ARRAY;
import static seedu.address.testutil.TypicalTimetableData.VALID_MODULE_LESSON_ARRAY;
import static seedu.address.testutil.TypicalTimetableData.VALID_SEMESTER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TimetableDataBuilder;

public class TimetableDataTest {
    @Test
    public void isSameTimetableData() {
        // same object -> returns true
        assertTrue(DATA.isSameTimetableData(DATA));

        // null -> returns false
        assertFalse(DATA.isSameTimetableData(null));

        // different semester and arrays -> returns false
        TimetableData editedData = new TimetableDataBuilder(DATA)
                .withSemester(VALID_SEMESTER)
                .withModuleCodeArray(VALID_MODULE_CODE_ARRAY)
                .withModuleLessonArray(VALID_MODULE_LESSON_ARRAY)
                .build();
        assertFalse(DATA.isSameTimetableData(editedData));
    }
}
