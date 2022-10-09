package model;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private final String courseID;
    private int credits;
    private Instructor instructor;
    private Set<Student> students;

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

    // setters
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        instructor.getCourses().add(this);
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    @Override
    public String toString() {
        return courseID;
    }
}
