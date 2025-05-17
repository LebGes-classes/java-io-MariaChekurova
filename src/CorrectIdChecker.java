import java.util.List;

public class CorrectIdChecker {

    public static boolean isCorrectScoreId (List<Score> arr, int id, int id_student) {
        for (Score score: arr){
            if (score.getId() == id && score.getId_student() == id_student){
                return true;
            }
        }
        return false;
    }

    public static boolean isCorrectStudentId (List<Student> arr, int id, int id_group) {
        for (Student stud: arr){
            if (stud.getId() == id && stud.getId_group() == id_group){
                return true;
            }
        }
        return false;
    }
}
