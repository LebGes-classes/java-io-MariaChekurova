import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseController {

    private static Scanner sc = new Scanner(System.in);
    protected static ArrayList<String> modifiedJSON = new ArrayList<>();
    private static String exelTableWay = "C:\\Users\\serge\\OneDrive\\Рабочий стол\\КФУ 1 Курс\\Gradebook.xlsx";

    public static void continueOrClose() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("Хотите продолжить или закрыть программу?");
        System.out.println("1 - продолжить \n" +
                "2 - закрыть");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                backToMenu();
                break;
            case "2":
                closeAndSave();
                break;
            default:
                System.out.println("Неверный ввод, попробуйте снова");
                continueOrClose();
                break;
        }
    }

    private static void closeAndSave() throws IOException, URISyntaxException {
        if (modifiedJSON.contains("students.json")){
            File exel = new File(exelTableWay);
            FileInputStream fis = new FileInputStream(exel);
            Workbook workbook = new XSSFWorkbook(fis);
            saveStudents(workbook, exel);
            saveAcadem(workbook, exel);
        }

        if (modifiedJSON.contains("scores.json")){
            FileInputStream fis = new FileInputStream(new File(exelTableWay));
            Workbook workbook = new XSSFWorkbook(fis);
            saveScores(workbook);
        }
    }

    private static void backToMenu() throws Exception {
        Menu.printOptions(Menu.workingTeacher);
    }

    private static void saveStudents(Workbook workbook, File exel) throws IOException {
        List<Student> studentList = JSONReader.readStudents();
        int sheetIndex = workbook.getSheetIndex("Students");
        workbook.removeSheetAt(sheetIndex);
        Sheet sheet = workbook.createSheet("Students");
        int rowIndex = 0;
        Row row0 = sheet.createRow(rowIndex);
        row0.createCell(0).setCellValue("ФИО");
        row0.createCell(1).setCellValue("id");
        row0.createCell(2).setCellValue("id группы");
        rowIndex++;
        for (Student student: studentList) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(student.getFullname());
            row.createCell(1).setCellValue(student.getId());
            row.createCell(2).setCellValue(student.getId_group());
            rowIndex++;
        }
        FileOutputStream out = new FileOutputStream(exelTableWay);
        workbook.write(out);
        out.close();
    }

    private static void saveAcadem(Workbook workbook, File exel) throws IOException {
        List<Student> studentList = JSONReader.readAcadem();
        int sheetIndex = workbook.getSheetIndex("Academ");
        workbook.removeSheetAt(sheetIndex);
        Sheet sheet = workbook.createSheet("Academ");
        int rowIndex = 0;
        Row row0 = sheet.createRow(rowIndex);
        row0.createCell(0).setCellValue("ФИО");
        row0.createCell(1).setCellValue("id");
        row0.createCell(2).setCellValue("id группы");
        rowIndex++;
        for (Student student: studentList) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(student.getFullname());
            row.createCell(1).setCellValue(student.getId());
            row.createCell(2).setCellValue(student.getId_group());
            rowIndex++;
        }
        FileOutputStream out = new FileOutputStream(exelTableWay);
        workbook.write(out);
        out.close();
    }

    private static void saveScores(Workbook workbook) throws IOException, URISyntaxException {
        List<Score> scoreList = JSONReader.readScores();
        int sheetIndex = workbook.getSheetIndex("Scores");
        workbook.removeSheetAt(sheetIndex);
        Sheet sheet = workbook.createSheet("Scores");
        int rowIndex = 0;
        Row row0 = sheet.createRow(rowIndex);
        row0.createCell(0).setCellValue("Оценка");
        row0.createCell(1).setCellValue("id");
        row0.createCell(2).setCellValue("id студента");
        row0.createCell(3).setCellValue("id предмета");
        rowIndex++;
        for (Score score: scoreList) {
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(score.getScore());
            row.createCell(1).setCellValue(score.getId());
            row.createCell(2).setCellValue(score.getId_student());
            row.createCell(3).setCellValue(score.getId_subject());
            rowIndex++;
        }
        FileOutputStream out = new FileOutputStream(exelTableWay);
        workbook.write(out);
        out.close();
    }

}
