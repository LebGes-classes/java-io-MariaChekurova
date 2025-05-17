import java.io.IOException;
import java.util.List;

public class Score {
    private int id;
    private int id_student;
    private int id_subject;
    private int score;
    private static int globalId = 1;

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getId_subject() {
        return id_subject;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Score() {
        this.id_student = 0;
        this.id_subject = 0;
        this.score = 0;
        this.id = 0;
        globalId++;
    }

    public Score(int id_student, int id_subject, int score, int id) {
        this.id_student = id_student;
        this.id_subject = id_subject;
        this.score = score;
        this.id = id;
        globalId++;
    }

    public Score(int id_student, int id_subject, int score) {
        this.id_student = id_student;
        this.id_subject = id_subject;
        this.score = score;
        this.id = globalId++;
    }

}
