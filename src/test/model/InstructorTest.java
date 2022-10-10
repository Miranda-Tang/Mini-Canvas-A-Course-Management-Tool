package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static model.Rank.EXCELLENT;
import static model.Rank.GOOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructorTest {
    private Instructor instructor;

    @BeforeEach
    public void runBefore() {
        instructor = new Instructor("harold");
    }

    @Test
    public void testConstructor() {
        assertEquals("harold", instructor.getName());
        assertEquals(new HashSet<>(), instructor.getCourses());
    }

    @Test
    public void testAddStudentToCourseOneTime() {
        Student s = new Student("john");
        Course c = new Course("cpsc 110", 4, instructor);
        instructor.addStudentToCourse(s, c);

        assertEquals(1, s.getCourses().size());
        assertEquals(1, c.getStudents().size());

        assertTrue(s.getCourses().contains(c));
        assertTrue(c.getStudents().contains(s));
    }

    @Test
    public void testAddStudentToCourseMultipleTimes() {
        Student s1 = new Student("john");
        Student s2 = new Student("carter");
        Course c = new Course("cpsc 110", 4, instructor);
        instructor.addStudentToCourse(s1, c);
        instructor.addStudentToCourse(s2, c);

        assertEquals(1, s1.getCourses().size());
        assertEquals(1, s2.getCourses().size());
        assertEquals(2, c.getStudents().size());

        assertTrue(s1.getCourses().contains(c));
        assertTrue(s2.getCourses().contains(c));
        assertTrue(c.getStudents().contains(s1));
        assertTrue(c.getStudents().contains(s2));
    }

    @Test
    public void testAddStudentGradeOneTime() {
        Student s = new Student("john");
        Course c = new Course("cpsc 110", 4, instructor);
        CourseGrade courseGrade = new CourseGrade(c, 1, GOOD, 8);

        instructor.addStudentGrade(s, courseGrade);

        assertEquals(1, s.getCourseGrades().size());
        assertTrue(s.getCourseGrades().contains(courseGrade));
    }

    @Test
    public void testAddStudentGradeMultipleTimes() {
        Student s = new Student("john");
        Course c1 = new Course("cpsc 110", 4, instructor);
        Course c2 = new Course("math 180", 3, instructor);
        CourseGrade courseGrade1 = new CourseGrade(c1, 1, GOOD, 8);
        CourseGrade courseGrade2 = new CourseGrade(c2, 2, EXCELLENT, 9);

        instructor.addStudentGrade(s, courseGrade1);
        instructor.addStudentGrade(s, courseGrade2);

        assertEquals(2, s.getCourseGrades().size());
        assertTrue(s.getCourseGrades().contains(courseGrade1));
        assertTrue(s.getCourseGrades().contains(courseGrade2));
    }

    @Test
    public void testToString() {
        assertEquals("harold", instructor.toString());
    }

}
