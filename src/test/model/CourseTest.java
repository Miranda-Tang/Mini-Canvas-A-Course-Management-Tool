package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course course;

    @BeforeEach
    public void runBefore() {
        course = new Course("cpsc 110", 4, new Instructor("gregor"));
        Iterator<Event> itr = EventLog.getInstance().iterator();
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("cpsc 110", course.getCourseID());
        assertEquals(4, course.getCredits());
        assertEquals("gregor", course.getInstructor().getName());
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        assertEquals(courses, course.getInstructor().getCourses());
        assertEquals(new ArrayList<>(), course.getStudents());
    }

    @Test
    public void testAddStudentOneTime() {
        Student s = new Student("john");
        course.addStudent(s);
        List<Student> students = course.getStudents();
        assertEquals(1, students.size());
        assertTrue(students.contains(s));
    }

    @Test
    public void testAddStudentMultipleTimes() {
        Student s1 = new Student("john");
        course.addStudent(s1);
        Student s2 = new Student("harold");
        course.addStudent(s2);
        List<Student> students = course.getStudents();

        assertEquals(2, students.size());
        assertTrue(students.contains(s1));
        assertTrue(students.contains(s2));
    }

    @Test
    public void testAddStudentFail() {
        Student s1 = new Student("john");
        course.addStudent(s1);
        Student s2 = new Student("john");
        course.addStudent(s2);

        List<Student> students = course.getStudents();
        assertEquals(1, students.size());
        assertTrue(students.contains(s1));
    }

    @Test
    public void testAddStudentEventLog() {
        course.addStudent(new Student("john"));
        assertEquals("Student \"john\" is registered to course \"cpsc 110\".",
                EventLog.getInstance().iterator().next().getDescription());
    }

    @Test
    public void testToString() {
        assertEquals("cpsc 110", course.toString());
    }

    @Test
    public void testEquals() {
        course.addStudent(new Student("john"));
        course.addStudent(new Student("harold"));

        Course obj1 = course;
        assertEquals(course, obj1);

        Course nullCourse = null;
        assertNotEquals(course, nullCourse);

        Student obj2 = new Student("miranda");
        assertNotEquals(course, obj2);

        Course obj3 = new Course("cpsc 120", 4, new Instructor("gregor"));
        assertNotEquals(course, obj3);

        Course obj4 = new Course("cpsc 110", 3, new Instructor("gregor"));
        assertNotEquals(course, obj4);

        Course obj5 = new Course("cpsc 110", 4, new Instructor("felix"));
        assertNotEquals(course, obj5);

        Course obj6 = new Course("cpsc 110", 4, new Instructor("gregor"));
        obj6.addStudent(new Student("john"));
        assertNotEquals(course, obj6);

        Course obj7 = new Course("cpsc 110", 4, new Instructor("gregor"));
        obj7.addStudent(new Student("john"));
        obj7.addStudent(new Student("steve"));
        assertNotEquals(course, obj7);

        Course obj8 = new Course("cpsc 110", 4, new Instructor("gregor"));
        obj8.addStudent(new Student("john"));
        obj8.addStudent(new Student("harold"));
        assertEquals(course, obj8);
        assertEquals(course.hashCode(), obj8.hashCode());
    }
}