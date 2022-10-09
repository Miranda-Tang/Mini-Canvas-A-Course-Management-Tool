package model;

import java.util.HashSet;
import java.util.Set;

// Represents a student with a name, a set of courses he/she is learning and a set of grades of the courses taken
public class Student extends Personnel {
    private final Set<CourseGrade> courseGrades;

    // REQUIRES: name != null
    // EFFECTS: creates a new student with the given name, an empty set of courses and an empty set of course grades
    public Student(String name) {
        super(name);
        courseGrades = new HashSet<>();
    }

    // getters
    public Set<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    // EFFECTS: return a set of courses the grades of which have been included in course grades list
    public Set<Course> getCoursesWithGrade() {
        Set<Course> res = new HashSet<>();
        for (CourseGrade cg : courseGrades) {
            res.add(cg.getCourse());
        }
        return res;
    }

    // REQUIRES: c != null
    // MODIFIES: this, c
    // EFFECTS: add student s to course c
    public void addCourse(Course c) {
        courses.add(c);
        c.addStudent(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
