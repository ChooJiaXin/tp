package seedu.address.timetable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.lesson.Lesson;

public class TimetableRetriever {
    private static final String JSON_API = "https://api.nusmods.com/v2/2020-2021/modules/";
    private static final String DOT_JSON = ".json";

    private static final String DUMMY_DATE = "01-01-2020 ";

    /**
     * Retrieves timetable json information from NUSMods API and prints out the relevant information.
     */
    public static List<Lesson> retrieveLessons(TimetableData timetableData) throws IOException, ParseException {
        int semester = timetableData.getSemester();
        String[] moduleCodeArray = timetableData.getModuleCodeArray();
        String[] moduleLessonArray = timetableData.getModuleLessonArray();

        List<Lesson> lessons = new ArrayList<>();

        for (int moduleIter = 0; moduleIter < moduleCodeArray.length; moduleIter++) {
            String module = moduleCodeArray[moduleIter];
            URL url = getModuleUrl(module);
            String inline = httpGetModuleJsonData(url);

            JSONArray semesterSpecificTimetableData = getSemesterSpecificTimetableData(semester, inline);
            String[] specificModuleLessonArray = getSpecificModuleLessonArray(moduleLessonArray, moduleIter);

            findLessonAndAdd(lessons, module, semesterSpecificTimetableData, specificModuleLessonArray);
        }
        return lessons;
    }

    private static String getUrlString(String moduleCode) {
        return JSON_API + moduleCode + DOT_JSON;
    }

    private static URL getModuleUrl(String moduleCode) throws MalformedURLException {
        String urlString = getUrlString(moduleCode);
        return new URL(urlString);
    }

    private static String httpGetModuleJsonData(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        String inline = "";
        Scanner sc = new Scanner(url.openStream());
        while (sc.hasNext()) {
            inline += sc.nextLine();
        }
        sc.close();
        return inline;
    }

    private static JSONArray getSemesterSpecificTimetableData(int sem, String inline) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject moduleData = (JSONObject) jsonParser.parse(inline);
        JSONArray semesterData = (JSONArray) moduleData.get("semesterData");
        JSONObject bothSemesterTimetableData = (JSONObject) semesterData.get(sem - 1);
        JSONArray semesterSpecificTimetableData = (JSONArray) bothSemesterTimetableData.get("timetable");
        return semesterSpecificTimetableData;
    }

    private static String[] getSpecificModuleLessonArray(String[] moduleLessonArray, int index) {
        String specificModuleLesson = moduleLessonArray[index];
        String[] specificModuleLessonArray = specificModuleLesson.split(",");
        return specificModuleLessonArray; // ["T:1","L:2"]
    }

    private static void findLessonAndAdd(List<Lesson> lessons, String module, JSONArray timetableData,
                                         String[] moduleLessonArray) {
        for (int lessonIter = 0; lessonIter < moduleLessonArray.length; lessonIter++) {
            if (!moduleLessonArray[0].equals("")) {
                String lessonInfo = moduleLessonArray[lessonIter];
                addLesson(lessons, module, lessonInfo, timetableData);
            }
        }
    }

    private static void addLesson(List<Lesson> lessons, String module, String lessonInfo, JSONArray timetableData) {
        String lessonType = getLessonType(lessonInfo);
        String lessonNum = getLessonNum(lessonInfo);
        for (int dataIter = 0; dataIter < timetableData.size(); dataIter++) {
            JSONObject currentData = (JSONObject) timetableData.get(dataIter);
            String currentLessonType = (String) currentData.get("lessonType");
            String currentLessonNum = (String) currentData.get("classNo");
            if (currentLessonType.equals(lessonType) && currentLessonNum.equals(lessonNum)) {
                String day = (String) currentData.get("day");
                String startTime = DUMMY_DATE + (String) currentData.get("startTime");
                String endTime = DUMMY_DATE + (String) currentData.get("endTime");
                String name = module + currentLessonType;
                lessons.add(new Lesson(new Name(name), new Deadline(startTime), new Deadline(endTime),
                        new ModuleCode(module)));
            }
        }
    }

    private static String getLessonType(String lessonInfo) {
        String lessonType = lessonInfo.split(":")[0];
        if (lessonType.equals("LEC")) {
            lessonType = "Lecture";
        }
        if (lessonType.equals("TUT")) {
            lessonType = "Tutorial";
        }
        if (lessonType.equals("LAB")) {
            lessonType = "Laboratory";
        }
        if (lessonType.equals("REC")) {
            lessonType = "Recitation";
        }
        if (lessonType.equals("SEC")) {
            lessonType = "Sectional Teaching";
        }
        return lessonType;
    }

    private static String getLessonNum(String lessonInfo) {
        String lessonNum = lessonInfo.split(":")[1];
        return lessonNum;
    }
}
