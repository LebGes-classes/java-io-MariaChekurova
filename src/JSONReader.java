import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONReader {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static List readStudents() throws IOException {
        File file = new File("students.json");
        List<Student> studentsList = objectMapper.readValue(file, new TypeReference<>(){});
        return studentsList;
    }

    static List readAcadem() throws IOException {
        File file = new File("academ.json");
        List<Student> studentsList = objectMapper.readValue(file, new TypeReference<>(){});
        return studentsList;
    }

    static List readTeachers() throws IOException {
        File file = new File("teachers.json");
        //ObjectMapper objectMapper1 = new ObjectMapper();
        List<Teacher> teachersList = objectMapper.readValue(file, new TypeReference<>(){});
        return teachersList;
    }

    static List readSubjects() throws IOException {
        File file = new File("subjects.json");
        List<Subject> subjectsList = objectMapper.readValue(file, new TypeReference<>(){});
        return subjectsList;
    }

    static List readGroups() throws IOException {
        File file = new File("groups.json");
        List<Group> groupsList = objectMapper.readValue(file, new TypeReference<>(){});
        return groupsList;
    }

    static List readScores() throws IOException {
        File file = new File("scores.json");
        List<Score> scoresList = objectMapper.readValue(file, new TypeReference<>(){});
        return scoresList;
    }

    static List readScheldules() throws IOException {
        File file = new File("scheldules.json");
        List<Scheldule> scheldulesList = objectMapper.readValue(file, new TypeReference<>(){});
        return scheldulesList;
    }
}
