package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;

public class TypicalLessons {
    public static final Lesson LECTURE = new LessonBuilder().withName("Generic Lecture")
            .withTime("01-01-2020 1200").withEndTime("01-01-2020 1400")
            .withModuleCode("CS2103T").build();
    public static final Lesson CS2100_TUT = new LessonBuilder().withName("CS2100 Tutorial")
            .withTime("01-01-2020 0800").withEndTime("01-01-2020 0900")
            .withModuleCode("CS2100").build();
    public static final Lesson CS2100_LEC_1 = new LessonBuilder().withName("CS2100 Lecture")
            .withTime("01-01-2020 1600").withEndTime("01-01-2020 1800")
            .withModuleCode("CS2100").build();
    public static final Lesson CS2100_LEC_2 = new LessonBuilder().withName("CS2100 Lecture")
            .withTime("01-01-2020 0900").withEndTime("01-01-2020 1000")
            .withModuleCode("CS2100").build();

    public static final String VALID_MODULE_CODE = "CS2101";
    public static final String VALID_NAME = "Generic Tutorial";
    public static final String VALID_TIME = "30-12-2019 1200";
    public static final String VALID_END_TIME = "30-12-2019 1400";
}
