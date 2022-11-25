package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static model.Rank.EXCELLENT;
import static model.Rank.GOOD;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {
    private Instructor instructor;

    @BeforeEach
    public void runBefore() {
        instructor = new Instructor("harold");
        Iterator<Event> itr = EventLog.getInstance().iterator();
        while (itr.hasNext()) {
            itr.next();
            itr.remove();
        }
    }

    @Test
    public void testConstructor() {
        assertEquals("harold", instructor.getName());
        assertEquals(new ArrayList<>(), instructor.getCourses());
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
    public void testAddStudentGradeEventLog() {
        Student s = new Student("john");
        CourseGrade courseGrade = new CourseGrade("cpsc 110", 1, GOOD, 8);
        instructor.addStudentGrade(s, courseGrade);
        assertEquals("john's grade for cpsc 110 is added.",
                EventLog.getInstance().iterator().next().getDescription());
    }

    @Test
    public void testToString() {
        assertEquals("harold", instructor.toString());
    }

    @Test
    public void testEquals() {
        Instructor obj1 = instructor;
        assertEquals(instructor, obj1);

        Instructor nullInstructor = null;
        assertNotEquals(instructor, nullInstructor);

        Student obj2 = new Student("miranda");
        assertNotEquals(instructor, obj2);

        Instructor obj3 = new Instructor("john");
        assertNotEquals(instructor, obj3);

        Instructor obj4 = new Instructor("harold");
        assertEquals(instructor, obj4);
        assertEquals(instructor.hashCode(), obj4.hashCode());
    }
}