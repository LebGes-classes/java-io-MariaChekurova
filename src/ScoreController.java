import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class ScoreController extends ChooseController {
    private static Scanner sc = new Scanner(System.in);
    static public void makeGrade() throws Exception {
        System.out.println("Введите номер группы студента, которому хотите поставить оценку:");
        GroupPrinter.printGroupNotClose();
        System.out.println("Введите id студента, которому хотите поставить оценку:");
        int id_stud = sc.nextInt();
        System.out.println("Введите оценку:");
        int score = correctScore();
        int id_subject = Menu.currentSubjectId;
        if(!(modifiedJSON.contains("scores.json"))){
            modifiedJSON.add("scores.json");
        }
        Menu.workingTeacher.rate(id_stud, score, id_subject);
        continueOrClose();
    }

    static public void deleteGrade() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Введите номер группы студента, у которого хотите удалить оценку:");
        GroupPrinter.printGroupNotClose();
        System.out.println("Введите id студента, у которого хотите удалить оценку:");
        int id_stud = sc.nextInt();
        if (ScoresPrinter.showStudentScoresBySubject(id_stud, Menu.currentSubjectId)){
            System.out.println("Введите id оценки, которую хотите удалить:");
            int id_score = correctScoreId(id_stud);
            JsonNode rootNode = mapper.readTree(new File("scores.json"));
            if (rootNode.isArray()) {
                Iterator<JsonNode> elements = rootNode.elements();
                while (elements.hasNext()) {
                    JsonNode node = elements.next();
                    if (node.get("id").asInt() == id_score) {
                        elements.remove();
                    }
                }
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("scores.json"), rootNode);
            if(!(modifiedJSON.contains("scores.json"))){
                modifiedJSON.add("scores.json");
            }
        }
        continueOrClose();
    }

    private static int correctScore() {
        int score = sc.nextInt();
        switch (score){
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.out.println("Введена некорректная оценка, введите оценку от 2 до 5:");
                return correctScore();
        }
        return score;
    }

    private static int correctScoreId(int id_student) throws IOException {
        int id_score = sc.nextInt();
        if (CorrectIdChecker.isCorrectScoreId(JSONReader.readScores(), id_score, id_student)){
            return id_score;
        }
        System.out.println("У введеного студента нет оценки с таким id, попробуйте снова");
        return correctScoreId(id_score);
    }
}
