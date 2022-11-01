package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected final Canvas expectedCanvas = new Canvas();

    @BeforeEach
    public void runBefore() {
        Instructor gregor = new Instructor("gregor");
        Instructor tara = new Instructor("tara");
        Instructor eugenia = new Instructor("eugenia");
        Instructor jamie = new Instructor("jamie");
        expectedCanvas.addInstructor(gregor);
        expectedCanvas.addInstructor(tara);
        expectedCanvas.addInstructor(eugenia);
        expectedCanvas.addInstructor(jamie);

        Student miranda = new Student("miranda");
        Student francis = new Student("francis");
        Student kate = new Student("kate");
        Student violet = new Student("violet");
        Student harold = new Student("harold");
        Student john = new Student("john");
        Student steve = new Student("steve");
        expectedCanvas.addStudent(miranda);
        expectedCanvas.addStudent(francis);
        expectedCanvas.addStudent(kate);
        expectedCanvas.addStudent(violet);
        expectedCanvas.addStudent(harold);
        expectedCanvas.addStudent(john);
        expectedCanvas.addStudent(steve);

        Course cpsc110 = new Course("cpsc 110", 4, gregor);
        cpsc110.addStudent(harold);
        cpsc110.addStudent(miranda);
        cpsc110.addStudent(violet);
        cpsc110.addStudent(john);

        Course wrds150 = new Course("wrds 150", 3, tara);
        wrds150.addStudent(francis);
        wrds150.addStudent(steve);
        wrds150.addStudent(harold);
        wrds150.addStudent(miranda);

        Course engl301 = new Course("engl 301", 4, tara);
        engl301.addStudent(steve);
        engl301.addStudent(john);
        engl301.addStudent(kate);

        Course engl304 = new Course("engl 304", 4, tara);
        engl304.addStudent(violet);
        engl304.addStudent(steve);
        engl304.addStudent(harold);
        engl304.addStudent(francis);
        engl304.addStudent(kate);

        Course stat203 = new Course("stat 203", 3, eugenia);
        stat203.addStudent(steve);
        stat203.addStudent(harold);
        stat203.addStudent(john);

        Course stat200 = new Course("stat 200", 3, eugenia);
        stat200.addStudent(francis);
        stat200.addStudent(miranda);
        stat200.addStudent(harold);
        stat200.addStudent(kate);

        Course math180 = new Course("math 180", 6, jamie);
        math180.addStudent(violet);
        math180.addStudent(kate);
        math180.addStudent(francis);
        math180.addStudent(harold);

        expectedCanvas.addCourse(cpsc110);
        expectedCanvas.addCourse(wrds150);
        expectedCanvas.addCourse(engl301);
        expectedCanvas.addCourse(engl304);
        expectedCanvas.addCourse(stat203);
        expectedCanvas.addCourse(stat200);
        expectedCanvas.addCourse(math180);

        CourseGrade courseGrade = new CourseGrade("cpsc 110", 87);
        gregor.addStudentGrade(john, courseGrade);
    }

    protected void checkCanvasInstructors(Canvas expectedCanvas, Canvas testCanvas) {
        List<Instructor> expectedInstructorList = expectedCanvas.getInstructorList();
        List<Instructor> testInstructorList = testCanvas.getInstructorList();
        assertEquals(expectedInstructorList.size(), testInstructorList.size());
        for (int i = 0; i < expectedInstructorList.size(); i++) {
            assertEquals(expectedInstructorList.get(i), testInstructorList.get(i));
        }
    }

    protected void checkCanvasStudents(Canvas expectedCanvas, Canvas testCanvas) {
        List<Student> expectedStudentList = expectedCanvas.getStudentList();
        List<Student> testStudentList = testCanvas.getStudentList();
        assertEquals(expectedStudentList.size(), testStudentList.size());
        for (int i = 0; i < expectedStudentList.size(); i++) {
            assertEquals(expectedStudentList.get(i), testStudentList.get(i));
        }
    }

    protected void checkCanvasCourses(Canvas expectedCanvas, Canvas testCanvas) {
        List<Course> expectedCourseList = expectedCanvas.getCourseList();
        List<Course> testCourseList = testCanvas.getCourseList();
        assertEquals(expectedCourseList.size(), testCourseList.size());
        for (int i = 0; i < expectedCourseList.size(); i++) {
            assertEquals(expectedCourseList.get(i), testCourseList.get(i));
        }
    }
}
