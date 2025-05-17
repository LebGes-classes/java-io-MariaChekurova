public class Lesson {
    private int number;
    private String time;
    private String group;
    private int classNumber;

    public Lesson(int number, String time, String group, int classNumber) {
        this.number = number;
        this.time = time;
        this.group = group;
        this.classNumber = classNumber;
    }

    public Lesson() {
        this.number = 0;
        this.time = null;
        this.group = null;
        this.classNumber = 0;
    }

    public int getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public String getGroup() {
        return group;
    }

    public int getClassNumber() {
        return classNumber;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }
}
