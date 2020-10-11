package seedu.address.testutil;

import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.lesson.Lesson;

/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {

    public static final String DEFAULT_NAME = "CS2103T Lecture";
    public static final String DEFAULT_TIME = "01-01-2020 1200";
    public static final String DEFAULT_END_TIME = "01-01-2020 1400";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";

    private Name name;
    private Deadline time;
    private Deadline endTime;
    private ModuleCode moduleCode;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Deadline(DEFAULT_TIME);
        endTime = new Deadline(DEFAULT_END_TIME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */
    public LessonBuilder(Lesson lessonToCopy) {
        name = lessonToCopy.getName();
        time = lessonToCopy.getTime();
        endTime = lessonToCopy.getEndTime();
        moduleCode = lessonToCopy.getModuleCode();
    }

    /**
     * Sets the {@code Name} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Time} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withTime(String time) {
        this.time = new Deadline(time);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withEndTime(String endTime) {
        this.endTime = new Deadline(endTime);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    public Lesson build() {
        return new Lesson(name, time, endTime, moduleCode);
    }
}
