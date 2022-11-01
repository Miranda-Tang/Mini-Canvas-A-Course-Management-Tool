package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Rank.GOOD;
import static org.junit.jupiter.api.Assertions.*;

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

        Instructor i = new Instructor("gregor");
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

    @Test
    public void testEquals() {
        Instructor i = new Instructor("gregor");
        Course c = new Course("cpsc 110", 4, i);
        i.addStudentToCourse(student, c);
        i.addStudentGrade(student, new CourseGrade(c.getCourseID(), 90));

        Student obj1 = student;
        assertEquals(student, obj1);

        Instructor obj2 = new Instructor("gregor");
        assertNotEquals(student, obj2);

        Student obj3 = new Student("harold");
        assertNotEquals(student, obj3);

        Student obj4 = new Student("john");
        assertNotEquals(student, obj4);

        Student obj5 = new Student("john");
        i.addStudentToCourse(obj5, new Course("wrds 150", 3, i));
        i.addStudentGrade(obj5, new CourseGrade("wrds 150", 90));
        assertNotEquals(student, obj5);

        Student obj6 = new Student("john");
        i.addStudentToCourse(obj6, c);
        i.addStudentGrade(obj6, new CourseGrade(c.getCourseID(), 90));
        assertEquals(student, obj6);
        assertEquals(student.hashCode(), obj6.hashCode());
    }
}
