package model;

import java.text.CharacterIterator;
import java.util.HashSet;
import java.util.Set;

public class Student extends Personnel {
    private final Set<CourseGrade> courseGrades;

    public Student(String name) {
        super(name);
        courseGrades = new HashSet<>();
    }

    public Set<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    public Set<Course> getCoursesWithGrade() {
        Set<Course> res = new HashSet<>();
        for (CourseGrade cg : courseGrades) {
            res.add(cg.getCourse());
        }
        return res;
    }

    public void addCourse(Course c) {
        courses.add(c);
        c.addStudent(this);
    }

    @Override
    public String toString() {
        return name;
    }
}
