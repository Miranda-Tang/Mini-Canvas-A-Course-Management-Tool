package model;

import org.json.JSONObject;

// Represents an instructor with a name and a list of courses he/she is teaching
public class Instructor extends Personnel {

    // REQUIRES: name != null
    // EFFECTS: creates a new instructor with the given name and an empty list of courses
    public Instructor(String name) {
        super(name);
    }

    // REQUIRES: s != null && c != null &&
    //           c is one of the courses the instructor is teaching
    // MODIFIES: s, c
    // EFFECTS: adds student s to course c
    public void addStudentToCourse(Student s, Course c) {
        c.addStudent(s);
    }

    // REQUIRES: s != null && courseGrade != null
    // MODIFIES: s
    // EFFECTS: adds courseGrade to student s's course grades list
    public void addStudentGrade(Student s, CourseGrade courseGrade) {
        s.getCourseGrades().add(courseGrade);
    }

    // EFFECTS: returns a string representation of instructor
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Instructor)) {
            return false;
        }
        return name.equals(((Instructor) obj).name);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }
}
