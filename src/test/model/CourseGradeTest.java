package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Rank.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CourseGradeTest {
    private CourseGrade courseGrade1;
    private CourseGrade courseGrade2;
    private CourseGrade courseGrade3;
    private CourseGrade courseGrade4;
    private CourseGrade courseGrade5;
    private CourseGrade courseGrade6;

    @BeforeEach
    public void beforeRun() {
        String courseID = "cpsc 110";
        courseGrade1 = new CourseGrade(courseID, 1, EXCELLENT, 8);
        courseGrade2 = new CourseGrade(courseID, 1, GOOD, 8);
        courseGrade3 = new CourseGrade(courseID, 1, ADEQUATE, 8);
        courseGrade4 = new CourseGrade(courseID, 1, INSUFFICIENT, 8);
        courseGrade5 = new CourseGrade(courseID, 1, UNACCEPTABLE, 8);
        courseGrade6 = new CourseGrade(courseID, 92);

        CourseGrade invalidCourseGrade = new CourseGrade(courseID, 1, INVALID, 8);
    }

    @Test
    public void testFirstConstructorAndNestedClasses() {
        assertEquals("cpsc 110", courseGrade1.getCourseID());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 90 + 0.5 * 80), courseGrade1.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 80 + 0.5 * 80), courseGrade2.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 70 + 0.5 * 80), courseGrade3.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 60 + 0.5 * 80), courseGrade4.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 50 + 0.5 * 80), courseGrade5.getGrade());
    }

    @Test
    public void testSecondConstructor() {
        assertEquals("cpsc 110", courseGrade6.getCourseID());
        assertEquals(92, courseGrade6.getGrade());
    }

    @Test
    public void testToString() {
        assertEquals("Course: cpsc 110, Grade: " + (int) Math.round(0.2 * 95 + 0.3 * 90 + 0.5 * 80),
                courseGrade1.toString());
    }

    @Test
    public void testEquals() {
        CourseGrade obj1 = courseGrade6;
        assertEquals(courseGrade6, obj1);

        CourseGrade nullCourseGrade = null;
        assertNotEquals(courseGrade6, nullCourseGrade);

        Course obj2 = new Course("cpsc 110", 4, new Instructor("gregor"));
        assertNotEquals(courseGrade6, obj2);

        CourseGrade obj3 = new CourseGrade("cpsc 110", 90);
        assertNotEquals(courseGrade6, obj3);

        CourseGrade obj4 = new CourseGrade("cpsc 110", 92);
        assertEquals(courseGrade6, obj4);
        assertEquals(courseGrade6.hashCode(), obj4.hashCode());
    }
}