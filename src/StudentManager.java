import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class StudentManager extends ChooseController{
    private static ObjectMapper mapper = new ObjectMapper();
    private static Scanner sc = new Scanner(System.in);
    public static void markAcadem() throws Exception {
        System.out.println("Введите номер группы студента, который ушёл в академический отпуск:");
        GroupPrinter.printGroupNotClose();
        System.out.println("Введите id студента, который ушёл в академический отпуск:");
        int id_student = sc.nextInt();
        // Чтение JSON файла
        JsonNode rootNode = mapper.readTree(new File("students.json"));
        ArrayNode academArray = prepareArchiveArray("academ.json");
        if (rootNode.isArray()) {
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                if (node.get("id").asInt() == id_student) {
                    academArray.add(node);
                    elements.remove();
                }
            }
        }
        // Запись изменений обратно в файл
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("students.json"), rootNode);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("academ.json"), academArray);
        if(!(modifiedJSON.contains("academ.json")) && !(modifiedJSON.contains("students.json"))){
            modifiedJSON.add("academ.json");
            modifiedJSON.add("students.json");
        }
        continueOrClose();
    }

    private static ArrayNode prepareArchiveArray(String archivePath) throws Exception {
        File archiveFile = new File(archivePath);

        // Если файл существует и не пустой - читаем его
        if (archiveFile.exists() && archiveFile.length() > 0) {
            JsonNode existing = mapper.readTree(archiveFile);
            if (existing.isArray()) {
                return (ArrayNode) existing;
            }
        }
        // Создаем новый массив
        return mapper.createArrayNode();
    }

    public static void markStudying() throws Exception {
        List<Student> studList = JSONReader.readAcadem();
        System.out.println("===============Список студентов в академе================");
        System.out.println("          ФИО студента           |            id студента");
        for (Student stud: studList){
            System.out.printf("%-32s | %16d%n", stud.getFullname(), stud.getId());
        }
        System.out.println("Введите id студента, который вышел из академического отпуска:");
        int id_student = sc.nextInt();
        // Чтение JSON файла
        JsonNode rootNode = mapper.readTree(new File("academ.json"));
        ArrayNode studentsArray = (ArrayNode) mapper.readTree(new File("students.json"));
        if (rootNode.isArray()) {
            Iterator<JsonNode> elements = rootNode.elements();
            while (elements.hasNext()) {
                JsonNode node = elements.next();
                if (node.get("id").asInt() == id_student) {
                    studentsArray.add(node);
                    elements.remove();
                }
            }
        }
        // Запись изменений обратно в файл
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("academ.json"), rootNode);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("students.json"), studentsArray);
        if(!(modifiedJSON.contains("academ.json")) && !(modifiedJSON.contains("students.json"))){
            modifiedJSON.add("academ.json");
            modifiedJSON.add("students.json");
        }
        continueOrClose();
    }
}
