package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Rank.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseGradeTest {
    private CourseGrade courseGrade1;
    private CourseGrade courseGrade2;
    private CourseGrade courseGrade3;
    private CourseGrade courseGrade4;
    private CourseGrade courseGrade5;

    @BeforeEach
    public void beforeRun() {
        Course course = new Course("cpsc 110", 4, new Instructor("gregor"));
        courseGrade1 = new CourseGrade(course, 1, EXCELLENT, 8);
        courseGrade2 = new CourseGrade(course, 1, GOOD, 8);
        courseGrade3 = new CourseGrade(course, 1, ADEQUATE, 8);
        courseGrade4 = new CourseGrade(course, 1, INSUFFICIENT, 8);
        courseGrade5 = new CourseGrade(course, 1, UNACCEPTABLE, 8);
    }

    @Test
    public void testConstructorAndNestedClasses() {
        assertEquals("cpsc 110", courseGrade1.getCourse().getCourseID());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 90 + 0.5 * 80), courseGrade1.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 80 + 0.5 * 80), courseGrade2.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 70 + 0.5 * 80), courseGrade3.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 60 + 0.5 * 80), courseGrade4.getGrade());
        assertEquals((int) Math.round(0.2 * 95 + 0.3 * 50 + 0.5 * 80), courseGrade5.getGrade());
    }

    @Test
    public void testToString() {
        assertEquals("Course: cpsc 110, Grade: " + (int) Math.round(0.2 * 95 + 0.3 * 90 + 0.5 * 80),
                courseGrade1.toString());
    }
}
