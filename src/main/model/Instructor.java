package model;

import org.json.JSONObject;

// Represents an instructor with a name and a list of courses he/she is teaching
public class Instructor extends Personnel {

    // REQUIRES: name != null
    // EFFECTS: creates a new instructor with the given name and an empty list of courses
    public Instructor(String name) {
        super(name);
    }

    // REQUIRES: s != null && courseGrade != null
    // MODIFIES: s
    // EFFECTS: adds courseGrade to student s's course grades list
    public void addStudentGrade(Student s, CourseGrade courseGrade) {
        s.getCourseGrades().add(courseGrade);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}