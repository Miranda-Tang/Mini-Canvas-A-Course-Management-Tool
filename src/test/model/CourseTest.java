package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseTest {
    private Course course;

    @BeforeEach
    public void runBefore() {
        course = new Course("cpsc 110", 4, new Instructor("gregor"));
    }

    @Test
    public void testConstructor() {
        assertEquals("cpsc 110", course.getCourseID());
        assertEquals(4, course.getCredits());
        assertEquals("gregor", course.getInstructor().getName());
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        assertEquals(courses, course.getInstructor().getCourses());
        assertEquals(new HashSet<>(), course.getStudents());
    }

    @Test
    public void testAddStudentOneTime() {
        Student s = new Student("john");
        course.addStudent(s);
        Set<Student> students = course.getStudents();
        assertEquals(1, students.size());
        assertTrue(students.contains(s));
    }

    @Test
    public void testAddStudentMultipleTimes() {
        Student s1 = new Student("john");
        course.addStudent(s1);
        Student s2 = new Student("harold");
        course.addStudent(s2);
        Set<Student> students = course.getStudents();

        assertEquals(2, students.size());
        assertTrue(students.contains(s1));
        assertTrue(students.contains(s2));
    }

    @Test
    public void testToString() {
        assertEquals("cpsc 110", course.toString());
    }

}