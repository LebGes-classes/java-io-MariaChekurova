import java.util.List;

public class ScheldulePrinter extends ChooseController{
    public static void printScheldule(int id_scheldule) throws Exception {
        List<Scheldule> schedules = JSONReader.readScheldules();
        for (Scheldule schedule : schedules) {
            if (schedule.getId() == id_scheldule) {
                for (SchelduleDay day : schedule.getDays()) {
                    System.out.println("\nДень недели: " + day.getDayOfWeek());
                    System.out.println("----------------------------------");
                    System.out.printf("%-6s | %-11s | %-8s | %s%n",
                            "Пара", "Время", "Группа", "Аудитория");

                    for (Lesson lesson : day.getLessons()) {
                        System.out.printf("%-6d | %-11s | %-8s | %d%n",
                                lesson.getNumber(),
                                lesson.getTime(),
                                lesson.getGroup(),
                                lesson.getClassNumber());
                    }
                }
            }
        }
        System.out.println("----------------------------------");
        continueOrClose();
    }
}
