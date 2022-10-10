package model;

import java.util.HashSet;
import java.util.Set;

// Represents a course having an ID, credits, instructor and a set of students
public class Course {
    private final String courseID;
    private final int credits;
    private final Instructor instructor;
    private final Set<Student> students;

    // REQUIRES: courseID != null && credits > 0 && instructor != null
    // EFFECTS: creates a new course with the given ID, credits, instructor and an empty set of students
    public Course(String courseID, int credits, Instructor instructor) {
        this.courseID = courseID;
        this.credits = credits;
        this.instructor = instructor;
        instructor.getCourses().add(this);
        students = new HashSet<>();
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

    public Set<Student> getStudents() {
        return students;
    }

    // REQUIRES: s != null
    // MODIFIES: this
    // EFFECTS: adds student s to the students of this course
    public void addStudent(Student s) {
        students.add(s);
    }

    @Override
    public String toString() {
        return courseID;
    }

}
