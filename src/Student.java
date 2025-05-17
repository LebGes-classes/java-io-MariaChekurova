import java.io.IOException;
import java.util.List;

public class Student {
    private int id;
    private String fullname;
    private int id_group;

    public String getFullname() {
        return fullname;
    }

    public int getId() { return id; }

    public int getId_group() {
        return id_group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public Student() {
        this.id_group = 0;
        this.fullname = null;
        this.id = 0;
    }

    public Student(int id_group, String fullname, int id) {
        this.id_group = id_group;
        this.fullname = fullname;
        this.id = id;
    }

    public static Student getStudentById(int id) throws IOException {
        List<Student> list = JSONReader.readStudents();
        for (Student stud: list){
            if (stud.getId() == id){
                return stud;
            }
        }
        throw new IllegalArgumentException("Нет студента c таким id: " + id);
    }
}
