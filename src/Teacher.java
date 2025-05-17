import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Teacher {
    private int id;
    private int id_subject;
    private int id_scheldule;
    private String fullname;
    private static ObjectMapper objectMapper = new ObjectMapper();

    public Teacher() {
        this.id = 0;
        this.id_scheldule = 0;
        this.id_subject = 0;
        this.fullname = null;
    }

    public Teacher(int id, int id_subject, int id_scheldule, String fullname) {
        this.id = id;
        this.id_subject = id_subject;
        this.id_scheldule = id_scheldule;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public int getId_subject() {
        return id_subject;
    }

    public int getId_scheldule() {
        return id_scheldule;
    }

    public String getFullname() {
        return fullname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public void setId_scheldule(int id_scheldule) {
        this.id_scheldule = id_scheldule;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void rate(int id_student, int score, int id_subject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("scores.json");
        Score newScore = new Score(id_student, id_subject, score);
        ArrayNode jsonArray;

        if (jsonFile.exists() && jsonFile.length() > 0) {
            jsonArray = (ArrayNode) mapper.readTree(jsonFile);
        } else {
            jsonArray = mapper.createArrayNode();
        }

        // 2. Добавляем новый объект
        jsonArray.add(mapper.valueToTree(newScore));

        // 3. Записываем обратно в файл
        mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonArray);
    }

    public void getGroup(int group_id) throws Exception {
        boolean isThisGroupExist = false;
        List<Student> studList = JSONReader.readStudents();
        System.out.println("          ФИО студента           |            id студента");
        System.out.println("-----------------------------------------------------");
        for (Student stud: studList){
            if (stud.getId_group() == group_id){
                System.out.printf("%-35s | %16d%n", stud.getFullname(), stud.getId());
                isThisGroupExist = true;
            }
        }
        if (!isThisGroupExist){
            System.out.println("Такой группы нет, введите корректный номер группы");
            GroupPrinter.printGroup();
        }
        System.out.println("-----------------------------------------------------");
    }
}
