package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static model.Rank.GOOD;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void runBefore() {
        student = new Student("john");
        Iterator<Event> itr = EventLog.getInstance().iterator();
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
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
        c1.addStudent(student);
        c2.addStudent(student);

        CourseGrade courseGrade = new CourseGrade(c1.getCourseID(), 2, GOOD, 9);
        i.addStudentGrade(student, courseGrade);

        assertEquals(1, student.getCoursesWithGrade().size());
        assertTrue(student.getCoursesWithGrade().contains(c1.getCourseID()));
    }

    @Test
    public void testAddCourse() {
        Instructor i = new Instructor("gregor");
        Course c1 = new Course("cpsc 110", 4, i);
        student.addCourse(c1);
        assertEquals(1, student.getCourses().size());
        assertTrue(student.getCourses().contains(c1));
        assertTrue(c1.getStudents().contains(student));

        Course c2 = new Course("math 180", 6, i);
        student.addCourse(c2);
        assertEquals(2, student.getCourses().size());
        assertTrue(student.getCourses().contains(c1));
        assertTrue(student.getCourses().contains(c2));
        assertTrue(c2.getStudents().contains(student));
    }

    @Test
    public void testAddCourseEventLog() {
        student.addCourse(new Course("cpsc 110", 4, new Instructor("gregor")));
        assertEquals("Student \"john\" is registered to course \"cpsc 110\".",
                EventLog.getInstance().iterator().next().getDescription());
    }

    @Test
    public void testToString() {
        assertEquals("john", student.toString());
    }

    @Test
    public void testEquals() {
        Instructor i = new Instructor("gregor");
        Course c = new Course("cpsc 110", 4, i);
        c.addStudent(student);
        i.addStudentGrade(student, new CourseGrade(c.getCourseID(), 90));

        Student obj1 = student;
        assertEquals(student, obj1);

        Student nullStudent = null;
        assertNotEquals(student, nullStudent);

        Instructor obj2 = new Instructor("gregor");
        assertNotEquals(student, obj2);

        Student obj3 = new Student("harold");
        assertNotEquals(student, obj3);

        Student obj4 = new Student("john");
        assertNotEquals(student, obj4);

        Student obj5 = new Student("john");
        new Course("wrds 150", 3, i).addStudent(obj5);
        i.addStudentGrade(obj5, new CourseGrade("wrds 150", 90));
        assertNotEquals(student, obj5);

        Student obj6 = new Student("john");
        c.addStudent(obj6);
        i.addStudentGrade(obj6, new CourseGrade(c.getCourseID(), 90));
        assertEquals(student, obj6);
        assertEquals(student.hashCode(), obj6.hashCode());
    }
}