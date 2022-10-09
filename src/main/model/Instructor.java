package model;

public class Instructor extends Personnel {

    public Instructor(String name) {
        super(name);
    }

    public void addStudentToCourse(Student s, Course c) {
        s.addCourse(c);
    }

    public void addStudentGrade(Student s, Course c, int skippedClass, Rank projectRank, int correctQuestion) {
        CourseGrade courseGrade = new CourseGrade(c, skippedClass, projectRank, correctQuestion);
        s.getCourseGrades().add(courseGrade);
    }

    @Override
    public String toString() {
        return name;
    }
}
