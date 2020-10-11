package seedu.address.timetable;

public class TimetableUrlParser {

    /**
     * Parses timetable url and returns timetable data.
     */
    public static TimetableData parseTimetableUrl(String url) {
        int semester = parseTimetableUrlForSem(url);
        String[] moduleDataArray = parseTimetableUrlForData(url);
        String[] moduleCodeArray = getModuleCodeArray(moduleDataArray);
        String[] moduleLessonArray = getModuleLessonArray(moduleDataArray);
        return new TimetableData(semester, moduleCodeArray, moduleLessonArray);
    }

    private static int parseTimetableUrlForSem(String url) {
        String semester = url.split("/sem-", 2)[1];
        semester = semester.substring(0, 1);
        return Integer.parseInt(semester);
    }

    private static String[] parseTimetableUrlForData(String url) {
        String moduleData = url.split("\\?", 2)[1];
        String[] moduleDataArray = moduleData.split("&");
        return moduleDataArray; // ["CS=T:1,L=2&MA=L:9"]
    }

    private static String[] getModuleCodeArray(String[] moduleDataArray) {
        String[] moduleCodeArray = moduleDataArray.clone();
        for (int i = 0; i < moduleCodeArray.length; i++) {
            moduleCodeArray[i] = moduleCodeArray[i].split("=", 2)[0];
        }
        return moduleCodeArray; // ["CS","MA"]
    }

    private static String[] getModuleLessonArray(String[] moduleDataArray) {
        String[] moduleLessonArray = moduleDataArray.clone();
        for (int i = 0; i < moduleLessonArray.length; i++) {
            moduleLessonArray[i] = moduleLessonArray[i].split("=", 2)[1];
        }
        return moduleLessonArray; // ["T:1,L:2","L:9"]
    }
}
