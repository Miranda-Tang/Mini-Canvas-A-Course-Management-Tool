package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a course and its grade comprised of three parts: attendance, project and exam
public class CourseGrade implements Writable {
    private final String courseID;
    private final int grade;

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseID", courseID);
        json.put("grade", grade);
        return json;
    }

    // Represents student's attendance
    private static class Attendance implements Gradable {
        private static final int PUNISHMENT = 5;
        private final int skippedClass;

        // REQUIRES: 0 <= skippedClass <= 10
        // EFFECTS: creates a new attendance record with the given number of skipped classes
        public Attendance(int skippedClass) {
            this.skippedClass = skippedClass;
        }

        // EFFECTS: calculates student's attendance grade, then returns the grade
        @Override
        public int mark() {
            return 100 - skippedClass * PUNISHMENT;
        }
    }

    // Represents student's project
    private static class Project implements Gradable {
        private final Rank projectRank;

        // EFFECTS: creates a new project record with the given rank
        public Project(Rank projectRank) {
            this.projectRank = projectRank;
        }

        // EFFECTS: calculates student's project grade, then returns the grade
        @Override
        public int mark() {
            switch (projectRank) {
                case EXCELLENT:
                    return 90;
                case GOOD:
                    return 80;
                case ADEQUATE:
                    return 70;
                case INSUFFICIENT:
                    return 60;
                case UNACCEPTABLE:
                    return 50;
                default:
                    return -1;
            }
        }
    }

    // Represents student's exam
    private static class Exam implements Gradable {
        private static final int POINTS_PER_QUESTION = 10;
        private final int correctQuestion;

        // REQUIRES: 0 <= correctQuestion <= 10
        // EFFECTS: creates a new exam record with the given number of correct questions
        public Exam(int correctQuestion) {
            this.correctQuestion = correctQuestion;
        }

        // EFFECTS: calculates student's exam grade, then returns the grade
        @Override
        public int mark() {
            return correctQuestion * POINTS_PER_QUESTION;
        }
    }

    // REQUIRES: 0 <= skippedClass <= 10 && 0 <= correctQuestion <= 10
    // EFFECTS: creates a new course grade with the given number of skipped classes, project rank,
    //                                          and number of correct questions
    public CourseGrade(String courseID, int skippedClass, Rank projectRank, int correctQuestion) {
        this.courseID = courseID;
        Attendance attendance = new Attendance(skippedClass);
        Project project = new Project(projectRank);
        Exam exam = new Exam(correctQuestion);
        this.grade = (int) Math.round(0.2 * attendance.mark() + 0.3 * project.mark() + 0.5 * exam.mark());
    }

    public CourseGrade(String courseID, int grade) {
        this.courseID = courseID;
        this.grade = grade;
    }

    // getters
    public String getCourseID() {
        return courseID;
    }

    public int getGrade() {
        return grade;
    }

    // EFFECTS: returns a string representation of course grade
    @Override
    public String toString() {
        return "Course: " + courseID + ", Grade: " + grade;
    }
}
