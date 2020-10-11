package seedu.address.timetable;

import java.util.Arrays;

public class TimetableData {
    private int semester;
    private String[] moduleCodeArray;
    private String[] moduleLessonArray;

    /**
     * Constructor for TimetableData.
     */
    public TimetableData(int semester, String[] moduleCodeArray, String[] moduleLessonArray) {
        this.semester = semester;
        this.moduleCodeArray = moduleCodeArray;
        this.moduleLessonArray = moduleLessonArray;
    }

    public int getSemester() {
        return this.semester;
    }

    public String[] getModuleCodeArray() {
        return this.moduleCodeArray;
    }

    public String[] getModuleLessonArray() {
        return this.moduleLessonArray;
    }

    /**
     * Returns true if both data is of the same semester and same arrays.
     */
    public boolean isSameTimetableData(TimetableData otherData) {
        if (otherData == this) {
            return true;
        }

        return otherData != null
                && otherData.getSemester() == getSemester()
                && Arrays.equals(otherData.getModuleCodeArray(), getModuleCodeArray())
                && Arrays.equals(otherData.getModuleLessonArray(), getModuleLessonArray());
    }
}
