import java.io.*;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExelReader {
    private static String exelTableWay = "C:\\Users\\serge\\OneDrive\\Рабочий стол\\КФУ 1 Курс\\Gradebook.xlsx";

    static void readTable() throws IOException {
        FileInputStream groups_is = new FileInputStream(new File(exelTableWay));
        XSSFWorkbook workbook = new XSSFWorkbook(groups_is);

        readStudents(workbook);
        readTeachers(workbook);
        readScores(workbook);
        readSubjects(workbook);
        readGroups(workbook);
        readAcadem(workbook);
        readScheldule(workbook);
    }

    static private void readStudents(XSSFWorkbook workbook) {
        XSSFSheet students = workbook.getSheet("Students");
        int lastNotEmptyRow = students.getLastRowNum();
        List<Student> studentsList = new ArrayList<>();
        for (int i = 1; i <= lastNotEmptyRow; i++) {
            Row row = students.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            int id = (int) row.getCell(1).getNumericCellValue();
            int id_group = (int) row.getCell(2).getNumericCellValue();
            studentsList.add(new Student(id_group, name, id));
        }
        try {
            Writer writer = new FileWriter("students.json");
            new Gson().toJson(studentsList, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void readAcadem(XSSFWorkbook workbook) {
        XSSFSheet academ = workbook.getSheet("Academ");
        int lastNotEmptyRow = academ.getLastRowNum();
        List<Student> academList = new ArrayList<>();
        for (int i = 1; i <= lastNotEmptyRow; i++) {
            Row row = academ.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            int id = (int) row.getCell(1).getNumericCellValue();
            int id_group = (int) row.getCell(2).getNumericCellValue();
            academList.add(new Student(id_group, name, id));
        }
        try {
            Writer writer = new FileWriter("academ.json");
            new Gson().toJson(academList, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void readTeachers(XSSFWorkbook workbook) {
        XSSFSheet teachers = workbook.getSheet("Teachers");
        int lastNotEmptyRow = teachers.getLastRowNum();
        List<Teacher> teachersList = new ArrayList<>();
        for (int i = 1; i <= lastNotEmptyRow; i++) {
            Row row = teachers.getRow(i);
            String name = row.getCell(0).getStringCellValue();
            int id = (int) row.getCell(1).getNumericCellValue();
            int id_subject = (int) row.getCell(2).getNumericCellValue();
            int id_scheldule = (int) row.getCell(3).getNumericCellValue();
            teachersList.add(new Teacher(id, id_subject, id_scheldule, name));
        }
        try {
            Writer writer = new FileWriter("teachers.json");
            new Gson().toJson(teachersList, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void readScores(XSSFWorkbook workbook) {
        XSSFSheet scores = workbook.getSheet("Scores");
        int lastNotEmptyRow = scores.getLastRowNum();
        List<Score> scoresList = new ArrayList<>();
        for (int i = 1; i <= lastNotEmptyRow; i++) {
            Row row = scores.getRow(i);
            int score = (int) row.getCell(0).getNumericCellValue();
            int id = (int) row.getCell(1).getNumericCellValue();
            int id_student = (int) row.getCell(2).getNumericCellValue();
            int id_subject = (int) row.getCell(3).getNumericCellValue();
            scoresList.add(new Score(id_student, id_subject, score, id));
        }
        try {
            Writer writer = new FileWriter("scores.json");
            new Gson().toJson(scoresList, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void readGroups(XSSFWorkbook workbook) {
        XSSFSheet groups = workbook.getSheet("Groups");
        int lastNotEmptyRow = groups.getLastRowNum();
        List<Group> groupsList = new ArrayList<>();
        for (int i = 1; i <= lastNotEmptyRow; i++) {
            Row row = groups.getRow(i);
            String name = (String) row.getCell(0).getStringCellValue();
            int id = (int) row.getCell(1).getNumericCellValue();
            groupsList.add(new Group(id, name));
        }
        try {
            Writer writer = new FileWriter("groups.json");
            new Gson().toJson(groupsList, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void readSubjects(XSSFWorkbook workbook) {
        XSSFSheet subjects = workbook.getSheet("Subjects");
        int lastNotEmptyRow = subjects.getLastRowNum();
        List<Subject> subjectsList = new ArrayList<>();
        for (int i = 1; i <= lastNotEmptyRow; i++) {
            Row row = subjects.getRow(i);
            String name = (String) row.getCell(0).getStringCellValue();
            int id = (int) row.getCell(1).getNumericCellValue();
            subjectsList.add(new Subject(id, name));
        }
        try {
            Writer writer = new FileWriter("subjects.json");
            new Gson().toJson(subjectsList, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void readScheldule(XSSFWorkbook workbook) {
        XSSFSheet scheduleSheet = workbook.getSheet("Scheldules");
        Map<Integer, Scheldule> schedulesMap = new HashMap<>();

        for (int i = 1; i <= scheduleSheet.getLastRowNum(); i++) {
            Row row = scheduleSheet.getRow(i);
            if (row == null) continue;

            // Парсинг данных из строки
            int scheduleId = (int) row.getCell(0).getNumericCellValue();
            String dayOfWeek = row.getCell(1).getStringCellValue();
            int lessonNumber = (int) row.getCell(2).getNumericCellValue();
            String lessonTime = row.getCell(3).getStringCellValue();
            String group = row.getCell(4).getStringCellValue();
            int classNumber = (int) row.getCell(5).getNumericCellValue();

            // Создание объектов структуры
            Lesson lesson = new Lesson(lessonNumber, lessonTime, group, classNumber);

            schedulesMap
                    .computeIfAbsent(scheduleId, k -> new Scheldule(scheduleId, new ArrayList<>()))
                    .getOrCreateDay(dayOfWeek)
                    .addLesson(lesson);
        }

        // Запись в JSON
        try (Writer writer = new FileWriter("scheldules.json")) {
            new Gson().toJson(schedulesMap.values(), writer);
        } catch (IOException e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }
}