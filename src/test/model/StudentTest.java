package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Rank.GOOD;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void runBefore() {
        student = new Student("john");
    }

    @Test
    public void testConstructor() {
        assertEquals("john", student.getName());
        assertEquals(new HashSet<>(), student.getCourses());
        assertEquals(new HashSet<>(), student.getCourseGrades());
    }

    @Test
    public void testGetCoursesWithGrade() {
        assertEquals(new HashSet<>(), student.getCoursesWithGrade());

        Instructor i = new Instructor("harold");
        Course c1 = new Course("cpsc 110", 4, i);
        Course c2 = new Course("math 180", 6, i);
        i.addStudentToCourse(student, c1);
        i.addStudentToCourse(student, c2);

        CourseGrade courseGrade = new CourseGrade(c1, 2, GOOD, 9);
        i.addStudentGrade(student, courseGrade);

        assertEquals(1, student.getCoursesWithGrade().size());
        assertTrue(student.getCoursesWithGrade().contains(c1));
    }

    @Test
    public void testAddCourseOneTime() {
        Instructor i = new Instructor("harold");
        Course c = new Course("cpsc 110", 4, i);
        student.addCourse(c);

        assertEquals(1, student.getCourses().size());
        assertTrue(student.getCourses().contains(c));
        assertTrue(c.getStudents().contains(student));
    }

    @Test
    public void testAddCourseMultipleTimes() {
        Instructor i = new Instructor("harold");
        Course c1 = new Course("cpsc 110", 4, i);
        student.addCourse(c1);
        Course c2 = new Course("math 180", 6, i);
        student.addCourse(c2);

        assertEquals(2, student.getCourses().size());
        assertTrue(student.getCourses().contains(c1));
        assertTrue(student.getCourses().contains(c2));
        assertTrue(c1.getStudents().contains(student));
        assertTrue(c2.getStudents().contains(student));
    }

    @Test
    public void testToString() {
        assertEquals("john", student.toString());
    }

}
