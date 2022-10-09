package model;

public class CourseGrade {
    private final Course course;
    private final int grade;

    private static class Attendance implements Gradable {
        private static final int PUNISHMENT = 5;
        private final int skippedClass;

        public Attendance(int skippedClass) {
            this.skippedClass = skippedClass;
        }

        @Override
        public int mark() {
            return 100 - skippedClass * PUNISHMENT;
        }
    }

    private static class Project implements Gradable {
        private final Rank projectRank;

        public Project(Rank projectRank) {
            this.projectRank = projectRank;
        }

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
                    return 0;
            }
        }
    }

    private static class Exam implements Gradable {
        private static final int POINTS_PER_QUESTION = 10;
        private final int correctQuestion;

        public Exam(int correctQuestion) {
            this.correctQuestion = correctQuestion;
        }

        @Override
        public int mark() {
            return correctQuestion * POINTS_PER_QUESTION;
        }
    }

    public CourseGrade(Course course, int skippedClass, Rank projectRank, int correctQuestion) {
        this.course = course;
        Attendance attendance = new Attendance(skippedClass);
        Project project = new Project(projectRank);
        Exam exam = new Exam(correctQuestion);
        this.grade = (int) Math.round(0.2 * attendance.mark() + 0.3 * project.mark() + 0.5 * exam.mark());
    }

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
