package model;

// Represents a course and its grade comprised of three parts: attendance, project and exam
public class CourseGrade {
    private final Course course;
    private final int grade;

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
            int projectGrade = 0;
            switch (projectRank) {
                case EXCELLENT:
                    projectGrade = 90;
                    break;
                case GOOD:
                    projectGrade = 80;
                    break;
                case ADEQUATE:
                    projectGrade = 70;
                    break;
                case INSUFFICIENT:
                    projectGrade = 60;
                    break;
                case UNACCEPTABLE:
                    projectGrade = 50;
                    break;
            }
            return projectGrade;
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
    public CourseGrade(Course course, int skippedClass, Rank projectRank, int correctQuestion) {
        this.course = course;
        Attendance attendance = new Attendance(skippedClass);
        Project project = new Project(projectRank);
        Exam exam = new Exam(correctQuestion);
        this.grade = (int) Math.round(0.2 * attendance.mark() + 0.3 * project.mark() + 0.5 * exam.mark());
    }

    // getters
    public Course getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Course: " + course + ", Grade: " + grade;
    }
}
