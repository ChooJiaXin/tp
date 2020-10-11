package seedu.address.testutil;

import seedu.address.timetable.TimetableData;

public class TimetableDataBuilder {

    private static final int DEFAULT_SEMESTER = 1;
    private static final String[] DEFAULT_MODULE_CODE_ARRAY = {"CS2100", "CS2105"};
    private static final String[] DEFAULT_MODULE_LESSON_ARRAY = {"TUT:01,LEC:2", "LEC:1,TUT:14"};

    private int semester;
    private String[] moduleCodeArray;
    private String[] moduleLessonArray;

    /**
     * Creates a {@code TimetableDataBuilder} with the default details.
     */
    public TimetableDataBuilder() {
        semester = DEFAULT_SEMESTER;
        moduleCodeArray = DEFAULT_MODULE_CODE_ARRAY;
        moduleLessonArray = DEFAULT_MODULE_LESSON_ARRAY;
    }

    /**
     * Initializes the TimetableDataBuilder with the data of {@code dataToCopy}.
     */
    public TimetableDataBuilder(TimetableData dataToCopy) {
        semester = dataToCopy.getSemester();
        moduleCodeArray = dataToCopy.getModuleCodeArray();
        moduleLessonArray = dataToCopy.getModuleLessonArray();
    }

    /**
     * Sets the {@code Name} of the {@code Lesson} that we are building.
     */
    public TimetableDataBuilder withSemester(int semester) {
        this.semester = semester;
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Lesson} that we are building.
     */
    public TimetableDataBuilder withModuleCodeArray(String[] moduleCodeArray) {
        this.moduleCodeArray = moduleCodeArray;
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Lesson} that we are building.
     */
    public TimetableDataBuilder withModuleLessonArray(String[] moduleLessonArray) {
        this.moduleLessonArray = moduleLessonArray;
        return this;
    }

    public TimetableData build() {
        return new TimetableData(semester, moduleCodeArray, moduleLessonArray);
    }
}
