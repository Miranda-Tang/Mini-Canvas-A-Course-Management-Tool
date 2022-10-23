package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a canvas having a list of courses, a list of instructors and a list of students
public class Canvas implements Writable {
    private final List<Course> courseList = new ArrayList<>();
    private final List<Instructor> instructorList = new ArrayList<>();
    private final List<Student> studentList = new ArrayList<>();

    // MODIFIES: this
    // EFFECTS: adds course c to the existing list of courses
    public void addCourse(Course c) {
        if (!courseList.contains(c)) {
            courseList.add(c);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds instructor i to the existing list of instructors
    public void addInstructor(Instructor i) {
        if (!instructorList.contains(i)) {
            instructorList.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds student s to the existing list of students
    public void addStudent(Student s) {
        if (!studentList.contains(s)) {
            studentList.add(s);
        }
    }

    // getters
    public List<Course> getCourseList() {
        return courseList;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseList", courseListToJson());
        json.put("instructorList", instructorListToJson());
        json.put("studentList", studentListToJson());
        return json;
    }

    // EFFECTS: returns courses in the existing list of courses as a JSON array
    private JSONArray courseListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courseList) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns instructors in the existing list of instructors as a JSON array
    private JSONArray instructorListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Instructor i : instructorList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns students in the existing list of students as a JSON array
    private JSONArray studentListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : studentList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
