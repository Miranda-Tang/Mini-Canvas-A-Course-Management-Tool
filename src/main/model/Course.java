package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a course having an ID, credits, an instructor and a list of students
public class Course implements Writable {
    private final String courseID;
    private final int credits;
    private final Instructor instructor;
    private final List<Student> students;

    // REQUIRES: courseID != null && credits > 0 && instructor != null
    // EFFECTS: creates a new course with the given ID, credits, instructor and an empty list of students
    public Course(String courseID, int credits, Instructor instructor) {
        this.courseID = courseID;
        this.credits = credits;
        this.instructor = instructor;
        instructor.getCourses().add(this);
        students = new ArrayList<>();
    }

    // getters
    public String getCourseID() {
        return courseID;
    }

    public int getCredits() {
        return credits;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Student> getStudents() {
        return students;
    }

    // REQUIRES: s != null
    // MODIFIES: this, s
    // EFFECTS: adds student s to this course if s is not registered in the course;
    //          otherwise, do nothing
    public void addStudent(Student s) {
        if (!students.contains(s)) {
            students.add(s);
            s.getCourses().add(this);
        }
    }

    // EFFECTS: returns a string representation of course
    @Override
    public String toString() {
        return courseID;
    }

    // EFFECTS: returns true if the two courses are considered equivalent, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Course)) {
            return false;
        }
        Course c = (Course) obj;
        if (!(courseID.equals(c.getCourseID()) && credits == c.getCredits() && instructor.equals(c.getInstructor()))) {
            return false;
        }
        if (students.size() != c.getStudents().size()) {
            return false;
        }
        for (Student s : students) {
            if (!c.getStudents().contains(s)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseID", courseID);
        json.put("credits", credits);
        json.put("instructor", instructor.toJson());
        json.put("students", studentsToJson());
        return json;
    }

    // EFFECTS: returns students in the students list as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student s : students) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}
