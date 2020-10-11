package seedu.address.testutil;

import seedu.address.timetable.TimetableData;

public class TypicalTimetableData {
    public static final int VALID_SEMESTER = 2;
    public static final String[] VALID_MODULE_CODE_ARRAY = {"EC1301", "CS2103T"};
    public static final String[] VALID_MODULE_LESSON_ARRAY = {"TUT:S28,LEC:1", "LEC:G16"};

    private static final String[] typicalModuleCodeArray = {"CS2100"};
    private static final String[] typicalModuleLessonArray = {"TUT:01,LEC:1"};
    public static final TimetableData DATA = new TimetableDataBuilder()
            .withSemester(1)
            .withModuleCodeArray(typicalModuleCodeArray)
            .withModuleLessonArray(typicalModuleLessonArray)
            .build();
}
