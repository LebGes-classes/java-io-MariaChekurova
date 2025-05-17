import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ScoresPrinter extends ChooseController{
    private static Scanner sc = new Scanner(System.in);
    public static void printStudentScores() throws Exception {
        int id_group = GroupPrinter.printGroupReturnId();
        System.out.println("Введите id студента, у котороко хотите посмотреть оценки:");
        int id_stud = sc.nextInt();
        if (CorrectIdChecker.isCorrectStudentId(JSONReader.readStudents(), id_stud, id_group)){
            showStudentScoresBySubject(id_stud, Menu.currentSubjectId);
            continueOrClose();
        } else {
            System.out.println("Такого студента нет в группе, попробуйте снова:");
            printStudentScores();
        }
    }

    public static void printGroupScores() throws Exception {
        System.out.println("Выберете номер группы:");
        System.out.println("11-300\n" +
                "11-301\n" +
                "11-302\n");
        Integer group_id = Integer.parseInt(sc.nextLine().substring(3));

        showGroupScoresBySubject(group_id, Menu.currentSubjectId);
        continueOrClose();
    }

    public static boolean showStudentScoresBySubject(int id_student, int id_subject) throws IOException {
        boolean isScoresExist = false;
        List<Score> scoreList = JSONReader.readScores();
        for (Score score: scoreList){
            if (score.getId_student() == id_student && score.getId_subject() == id_subject){
                if(!isScoresExist){
                    System.out.println("Оценка | id оценки");
                    System.out.println("------------------");
                }
                System.out.printf("%6s | %6d%n", score.getScore(), score.getId());
                isScoresExist = true;
            }
        }
        if (!isScoresExist){
            System.out.println("У этого студента ещё нет оценок");
            System.out.println("------------------");
            return false;
        }
        System.out.println("------------------");
        return true;
    }

    public static void showGroupScoresBySubject(int id_group, int id_subject) throws IOException {
        boolean isScoresExist = false;
        List<Score> scoreList = JSONReader.readScores();
        System.out.println("          ФИО студента           |            оценка");
        System.out.println("----------------------------------------------------");
        for (Score score: scoreList){
            Student stud = Student.getStudentById(score.getId_student());
            if (stud.getId_group() == id_group && score.getId_subject() == id_subject){
                System.out.printf("%-32s | %16d%n", stud.getFullname(), score.getScore());
                isScoresExist = true;
            }
        }
        if (!isScoresExist){
            System.out.println("У студентов этой группы ещё нет оценок");
        }
        System.out.println("----------------------------------------------------");
    }
}
