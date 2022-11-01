package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads canvas from JSON data stored in file
// CITATION: the structure and contents of the following program are borrowed from JsonSerializationDemo
public class JsonReader {
    private final String source;

    private final Canvas canvas = new Canvas();

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // MODIFIES: this
    // EFFECTS: reads canvas from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Canvas read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        parseCanvas(jsonObject);
        return canvas;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // MODIFIES: this
    // EFFECTS: parses canvas from JSON object
    private void parseCanvas(JSONObject jsonObject) {
        addInstructorList(jsonObject);
        addStudentList(jsonObject);
        addCourseList(jsonObject);
    }

    // MODIFIES: this
    // EFFECTS: parses list of instructors from JSON object and adds them to canvas
    private void addInstructorList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("instructorList");
        for (Object json : jsonArray) {
            JSONObject nextInstructor = (JSONObject) json;
            canvas.addInstructor(parseInstructor(nextInstructor));
        }
    }

    // MODIFIES: this
    // EFFECTS: parses list of students from JSON object and adds them to canvas
    private void addStudentList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("studentList");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            canvas.addStudent(parseStudent(nextStudent));
        }
    }

    // MODIFIES: this
    // EFFECTS: parses list of courses from JSON object and adds them to canvas
    private void addCourseList(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courseList");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            canvas.addCourse(parseCourse(nextCourse));
        }
    }

    // EFFECTS: parses instructor from JSON object and returns it
    private Instructor parseInstructor(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        for (Instructor i : canvas.getInstructorList()) {
            if (name.equals(i.getName())) {
                return i;
            }
        }
        return new Instructor(name);
    }

    // EFFECTS: parses student from JSON object and returns it
    private Student parseStudent(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        for (Student s : canvas.getStudentList()) {
            if (name.equals(s.getName())) {
                return s;
            }
        }
        Student student = new Student(name);
        addCourseGrades(student, jsonObject);
        return student;
    }

    // EFFECTS: parses course from JSON object and returns it
    private Course parseCourse(JSONObject jsonObject) {
        String courseID = jsonObject.getString("courseID");
        int credits = jsonObject.getInt("credits");
        Instructor instructor = parseInstructor(jsonObject.getJSONObject("instructor"));
        Course course = new Course(courseID, credits, instructor);
        addStudents(course, jsonObject);
        return course;
    }

    // MODIFIES: course
    // EFFECTS: parses students from JSON object and adds them to course
    private void addStudents(Course course, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            course.addStudent(parseStudent(nextStudent));
        }
    }

    // MODIFIES: student
    // EFFECTS: parses course grades from JSON object and adds them to student
    private void addCourseGrades(Student student, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courseGrades");
        for (Object json : jsonArray) {
            JSONObject nextCourseGrade = (JSONObject) json;
            addCourseGrade(student, nextCourseGrade);
        }
    }

    // MODIFIES: student
    // EFFECTS: parses course grade from JSON object and adds it to student
    private void addCourseGrade(Student student, JSONObject jsonObject) {
        String courseID = jsonObject.getString("courseID");
        int grade = jsonObject.getInt("grade");
        CourseGrade courseGrade = new CourseGrade(courseID, grade);
        student.getCourseGrades().add(courseGrade);
    }
}
