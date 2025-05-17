import java.util.ArrayList;
import java.util.List;

public class SchelduleDay {
    private String dayOfWeek;
    private List<Lesson> lessons;



    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public SchelduleDay(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.lessons = new ArrayList<>();
    }

    public SchelduleDay() {
        this.dayOfWeek = null;
        this.lessons = null;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
}
