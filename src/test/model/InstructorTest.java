package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Rank.EXCELLENT;
import static model.Rank.GOOD;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {
    private Instructor instructor;
    private Instructor obj1;

    @BeforeEach
    public void runBefore() {
        instructor = new Instructor("harold");
    }

    @Test
    public void testConstructor() {
        assertEquals("harold", instructor.getName());
        assertEquals(new ArrayList<>(), instructor.getCourses());
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
        CourseGrade courseGrade = new CourseGrade("cpsc 110", 1, GOOD, 8);

        instructor.addStudentGrade(s, courseGrade);

        assertEquals(1, s.getCourseGrades().size());
        assertTrue(s.getCourseGrades().contains(courseGrade));
    }

    @Test
    public void testAddStudentGradeMultipleTimes() {
        Student s = new Student("john");
        CourseGrade courseGrade1 = new CourseGrade("cpsc 110", 1, GOOD, 8);
        CourseGrade courseGrade2 = new CourseGrade("math 180", 2, EXCELLENT, 9);

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

    @Test
    public void testEquals() {
        Instructor obj1 = instructor;
        assertEquals(instructor, obj1);

        Student obj2 = new Student("miranda");
        assertNotEquals(instructor, obj2);

        Instructor obj3 = new Instructor("john");
        assertNotEquals(instructor, obj3);

        Instructor obj4 = new Instructor("harold");
        assertEquals(instructor, obj4);
    }
}
