package model;

// Represents an instructor with a name and a set of courses he/she is teaching
public class Instructor extends Personnel {

    // REQUIRES: name != null
    // EFFECTS: creates a new instructor with the given name and an empty set of courses
    public Instructor(String name) {
        super(name);
    }

    // REQUIRES: s != null && c != null
    // MODIFIES: s, c
    // EFFECTS: add student s to course c
    public void addStudentToCourse(Student s, Course c) {
        s.addCourse(c);
    }

    // REQUIRES: s != null && c != null &&
    //           0 <= skippedClass <= 10 && 0 <= correctQuestion <= 10
    // MODIFIES: s
    // EFFECTS: add the grades of a certain course c to student s's course grades list
    public void addStudentGrade(Student s, Course c, int skippedClass, Rank projectRank, int correctQuestion) {
        CourseGrade courseGrade = new CourseGrade(c, skippedClass, projectRank, correctQuestion);
        s.getCourseGrades().add(courseGrade);
    }

    @Override
    public String toString() {
        return name;
    }
}
