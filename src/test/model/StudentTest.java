package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Rank.GOOD;
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
        assertEquals(new ArrayList<>(), student.getCourses());
        assertEquals(new ArrayList<>(), student.getCourseGrades());
    }

    @Test
    public void testGetCoursesWithGrade() {
        assertEquals(new ArrayList<>(), student.getCoursesWithGrade());

        Instructor i = new Instructor("harold");
        Course c1 = new Course("cpsc 110", 4, i);
        Course c2 = new Course("math 180", 6, i);
        i.addStudentToCourse(student, c1);
        i.addStudentToCourse(student, c2);

        CourseGrade courseGrade = new CourseGrade(c1.getCourseID(), 2, GOOD, 9);
        i.addStudentGrade(student, courseGrade);

        assertEquals(1, student.getCoursesWithGrade().size());
        assertTrue(student.getCoursesWithGrade().contains(c1.getCourseID()));
    }

    @Test
    public void testToString() {
        assertEquals("john", student.toString());
    }
}
