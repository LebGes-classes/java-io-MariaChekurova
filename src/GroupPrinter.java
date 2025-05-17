import java.util.Scanner;

public class GroupPrinter extends ChooseController {
    private static Scanner sc = new Scanner(System.in);
    public static void printGroup() throws Exception {
        System.out.println("Выберете номер группы:");
        System.out.println("11-300\n" +
                "11-301\n" +
                "11-302\n");
        Integer group_id = Integer.parseInt(sc.nextLine().substring(3));

        Menu.workingTeacher.getGroup(group_id);
        continueOrClose();
    }

    public static void printGroupNotClose() throws Exception {
        System.out.println("Выберете номер группы:");
        System.out.println("11-300\n" +
                "11-301\n" +
                "11-302\n");
        Integer group_id = Integer.parseInt(sc.nextLine().substring(3));

        Menu.workingTeacher.getGroup(group_id);
    }

    public static int printGroupReturnId() throws Exception {
        System.out.println("Выберете номер группы:");
        System.out.println("11-300\n" +
                "11-301\n" +
                "11-302\n");
        Integer group_id = Integer.parseInt(sc.nextLine().substring(3));

        Menu.workingTeacher.getGroup(group_id);
        return group_id;
    }
}
