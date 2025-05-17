import java.util.HashMap;
import java.util.List;

public class Scheldule {
    private int id;
    private List<SchelduleDay> days;

    public Scheldule() {
        this.id = 0;
        this.days = null;
    }

    public Scheldule(int id, List<SchelduleDay> days) {
        this.id = id;
        this.days = days;
    }

    public void setDays(List<SchelduleDay> days) {
        this.days = days;
    }

    public List<SchelduleDay> getDays() {
        return days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SchelduleDay getOrCreateDay(String dayName) {
        for (SchelduleDay day : days) {
            if (day.getDayOfWeek().equals(dayName)) {
                return day;
            }
        }
        SchelduleDay newDay = new SchelduleDay(dayName);
        days.add(newDay);
        return newDay;
    }

}
