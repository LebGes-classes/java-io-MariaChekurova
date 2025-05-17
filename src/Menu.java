import java.util.Scanner;

public class Menu {
    public static Teacher workingTeacher; //поместить Menu и все прилегающие в один пакет и сделать другую облать видимости Scanner
    public static int currentSubjectId;
    public static void showMenu() throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("==========MENU==========");
        System.out.println("Введите ФИО");
        workingTeacher = OptionChoice.chooseWorkingTeacher(sc.nextLine());
        currentSubjectId = workingTeacher.getId_subject();
        printOptions(workingTeacher);
    }

    public static void printOptions(Teacher workingTeacher) throws Exception {
        System.out.println("Выберите опцию");
        System.out.println("1 - Показать список группы\n" +
                "2 - Показать расписание\n" +
                "3 - Поставить оценку\n" +
                "4 - Удалить оценку\n" +
                "5 - Добавить студента (Вышел из академа)\n" +
                "6 - Удалить студента (академический отпуск)\n" +
                "7 - Показать оценки студента\n" +
                "8 - Показать оценки группы\n");
        OptionChoice.chooseOption();
    }
}
