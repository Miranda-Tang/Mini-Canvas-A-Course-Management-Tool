package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CanvasTest {
    private Canvas canvas;

    @BeforeEach
    public void runBefore() {
        canvas = new Canvas();
        Iterator<Event> itr = EventLog.getInstance().iterator();
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }

    @Test
    public void testConstructor() {
        assertEquals(new ArrayList<>(), canvas.getCourseList());
        assertEquals(new ArrayList<>(), canvas.getInstructorList());
        assertEquals(new ArrayList<>(), canvas.getStudentList());
    }

    @Test
    public void testAddCourse() {
        Course c1 = new Course("cpsc 110", 4, new Instructor("gregor"));
        Course c2 = new Course("wrds 150", 3, new Instructor("tara"));
        canvas.addCourse(c1);
        canvas.addCourse(c2);

        List<Course> courses = new ArrayList<>();
        courses.add(c1);
        courses.add(c2);

        assertEquals(courses, canvas.getCourseList());
    }

    @Test
    public void testAddCourseFail() {
        Course c1 = new Course("cpsc 110", 4, new Instructor("gregor"));
        Course c2 = new Course("cpsc 110", 4, new Instructor("gregor"));
        canvas.addCourse(c1);
        canvas.addCourse(c2);

        List<Course> courses = new ArrayList<>();
        courses.add(c1);

        assertEquals(courses, canvas.getCourseList());
    }

    @Test
    public void testAddCourseEventLog() {
        canvas.addCourse(new Course("cpsc 110", 4, new Instructor("gregor")));
        assertEquals("New course \"cpsc 110\" is created.",
                EventLog.getInstance().iterator().next().getDescription());
    }

    @Test
    public void testAddInstructor() {
        Instructor i1 = new Instructor("gregor");
        Instructor i2 = new Instructor("tara");
        canvas.addInstructor(i1);
        canvas.addInstructor(i2);

        List<Instructor> instructors = new ArrayList<>();
        instructors.add(i1);
        instructors.add(i2);

        assertEquals(instructors, canvas.getInstructorList());
    }

    @Test
    public void testAddInstructorFail() {
        Instructor i1 = new Instructor("gregor");
        Instructor i2 = new Instructor("gregor");
        canvas.addInstructor(i1);
        canvas.addInstructor(i2);

        List<Instructor> instructors = new ArrayList<>();
        instructors.add(i1);

        assertEquals(instructors, canvas.getInstructorList());
    }

    @Test
    public void testAddInstructorEventLog() {
        canvas.addInstructor(new Instructor("tara"));
        assertEquals("New instructor \"tara\" is created.",
                EventLog.getInstance().iterator().next().getDescription());
    }

    @Test
    public void testAddStudent() {
        Student s1 = new Student("harold");
        Student s2 = new Student("john");
        canvas.addStudent(s1);
        canvas.addStudent(s2);

        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);

        assertEquals(students, canvas.getStudentList());
    }

    @Test
    public void testAddStudentFail() {
        Student s1 = new Student("harold");
        Student s2 = new Student("harold");
        canvas.addStudent(s1);
        canvas.addStudent(s2);

        List<Student> students = new ArrayList<>();
        students.add(s1);

        assertEquals(students, canvas.getStudentList());
    }

    @Test
    public void testAddStudentEventLog() {
        canvas.addStudent(new Student("john"));
        assertEquals("New student \"john\" is created.",
                EventLog.getInstance().iterator().next().getDescription());
    }
}