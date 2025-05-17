import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class OptionChoice {
    private static Scanner sc = new Scanner(System.in);

    public static Teacher chooseWorkingTeacher(String name) {
        try {
            List<Teacher> teacherList = JSONReader.readTeachers();
            for (Teacher teacher : teacherList){
                if (teacher.getFullname().equals(name)){
                    return teacher;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        throw new IllegalArgumentException("Нет такого учителя: " + name);
    }

    public static void chooseOption() throws Exception {
        String choice = sc.next();
        switch (choice){
            case "1":
                GroupPrinter.printGroup();
                break;
            case "2":
                ScheldulePrinter.printScheldule(Menu.workingTeacher.getId_scheldule());
                break;
            case "3":
                ScoreController.makeGrade();
                break;
            case "4":
                 ScoreController.deleteGrade();
                break;
            case "5":
                StudentManager.markStudying();
                break;
            case "6":
                StudentManager.markAcadem();
                break;
            case "7":
                ScoresPrinter.printStudentScores();
                break;
            case "8":
                ScoresPrinter.printGroupScores();
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова");
                chooseOption();
        }
    }
}
