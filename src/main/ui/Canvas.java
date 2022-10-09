package ui;

import model.*;

import java.util.*;

// Course Database application
public class Canvas {
    public final List<Course> courseList = new ArrayList<>();
    private final List<Instructor> instructorList = new ArrayList<>();
    private final List<Student> studentList = new ArrayList<>();

    private static final int DEFAULT_COURSE_SIZE = 5;

    private static final String QUIT_PROMPT = "To quit the program, enter 'q'.";
    private static final String RETURN_PROMPT = "To return to the previous menu, enter 'r'.";
    private static final String NOT_VALID_PROMPT = "Selection not valid. Please try again.";

    private static final String COURSE_NOTE = "NOTE: The courseID has to be in the following list:\n";
    private static final String INSTRUCTOR_NOTE = "NOTE: The instructor name has to be in the following list:\n";
    private static final String STUDENT_NOTE = "NOTE: The student name has to be in the following list:\n";
    private static final String GRADE_NOTE = "NOTE: Please enter an integer between 0 and 10.";

    private Scanner input;

    // EFFECTS: runs the course database application
    public Canvas() {
        runCanvas();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCanvas() {
        boolean keepGoing = true;

        initProgram();

        while (keepGoing) {
            displayMenu();
            String command = nextCommand();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        endProgram();
    }

    // MODIFIES: this
    // EFFECTS: initializes course list, instructor list and student list in the database
    private void initProgram() {
        setInstructorAndCourseList();
        setStudentList();
        setCourseRegistration();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: initializes course list and instructor list
    private void setInstructorAndCourseList() {
        Instructor gregor = new Instructor("gregor");
        Instructor tara = new Instructor("tara");
        Instructor eugenia = new Instructor("eugenia");
        Instructor jamie = new Instructor("jamie");
        instructorList.add(gregor);
        instructorList.add(tara);
        instructorList.add(eugenia);
        instructorList.add(jamie);

        Course cpsc110 = new Course("cpsc 110", 4, gregor);
        Course wrds150 = new Course("wrds 150", 3, tara);
        Course engl301 = new Course("engl 301", 4, tara);
        Course engl304 = new Course("engl 304", 4, tara);
        Course stat203 = new Course("stat 203", 3, eugenia);
        Course stat200 = new Course("stat 200", 3, eugenia);
        Course math180 = new Course("math 180", 6, jamie);
        courseList.add(cpsc110);
        courseList.add(wrds150);
        courseList.add(engl301);
        courseList.add(engl304);
        courseList.add(stat203);
        courseList.add(stat200);
        courseList.add(math180);
    }

    // MODIFIES: this
    // EFFECTS: initializes student list
    private void setStudentList() {
        Student miranda = new Student("miranda");
        Student francis = new Student("francis");
        Student kate = new Student("kate");
        Student violet = new Student("violet");
        Student harold = new Student("harold");
        Student john = new Student("john");
        Student steve = new Student("steve");
        studentList.add(miranda);
        studentList.add(francis);
        studentList.add(kate);
        studentList.add(violet);
        studentList.add(harold);
        studentList.add(john);
        studentList.add(steve);
    }

    // MODIFIES: this
    // EFFECTS: initializes students' course registration
    private void setCourseRegistration() {
        Random random = new Random();
        for (Course c : courseList) {
            List<Integer> chosen = new ArrayList<>();
            for (int i = 0; i < DEFAULT_COURSE_SIZE; i++) {
                int next = random.nextInt(studentList.size());
                if (!chosen.contains(next)) {
                    chosen.add(next);
                    studentList.get(next).addCourse(c);
                }
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nPlease select your identity:");
        System.out.println("\ti -> instructor");
        System.out.println("\ts -> student");
        System.out.println(QUIT_PROMPT);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("i")) {
            goInstructorInterface();
        } else if (command.equals("s")) {
            goStudentInterface();
        } else {
            System.out.println(NOT_VALID_PROMPT);
        }
    }

    // EFFECTS: displays welcome interface to instructor and asks about the next move
    private void goInstructorInterface() {
        Instructor instructor = spotInstructor(instructorList);

        System.out.println("\nHello " + instructor.getName()
                + ", please find below a list of courses you are currently teaching:\n"
                + instructor.getCourses());

        instructorAction(instructor);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void instructorAction(Instructor instructor) {
        label:
        while (true) {
            displayInstructorAction();
            String command = nextCommand();
            switch (command) {
                case "detail":
                    printCourseDetail(spotCourse(instructor.getCourses()));
                    break;
                case "grade":
                    toAddStudentGrade(instructor);
                    break;
                case "register":
                    toAddStudentToCourse(instructor);
                    break;
                case "r":
                    break label;
                default:
                    System.out.println(NOT_VALID_PROMPT);
                    break;
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayInstructorAction() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tdetail -> look into the details of a certain course");
        System.out.println("\tgrade -> enter student's grades of a certain course");
        System.out.println("\tregister -> add a student to a certain course");
        System.out.println(RETURN_PROMPT);
    }

    // REQUIRES: course != null
    // EFFECTS: prints the details of the given course
    private void printCourseDetail(Course course) {
        System.out.println("\nHere are the details of " + course.getCourseID() + ":");
        System.out.println("Course: " + course.getCourseID()
                + "\nCredits: " + course.getCredits()
                + "\nInstructor: " + course.getInstructor()
                + "\nStudents: " + course.getStudents());
    }

    // REQUIRES: instructor != null
    // MODIFIES: this
    // EFFECTS: adds student's grades of a certain course
    private void toAddStudentGrade(Instructor instructor) {
        Course c = spotCourse(instructor.getCourses());
        Student s = spotStudent(c.getStudents());
        String name = s.getName();

        System.out.println("\nHow many classes did " + name + " skip?");
        System.out.println(GRADE_NOTE);
        int skippedClass = input.nextInt();
        System.out.println("\nPlease rank " + name + "'s project:");
        for (Rank r : Rank.values()) {
            System.out.println("\t" + r.toString());
        }
        Rank projectRank = Rank.valueOf(nextCommand().toUpperCase());
        System.out.println("\nHow many questions did " + name + " answer correctly in the exam?");
        System.out.println(GRADE_NOTE);
        int correctQuestion = input.nextInt();

        instructor.addStudentGrade(s, c, skippedClass, projectRank, correctQuestion);
        System.out.println("\n" + name + "'s grades are added successfully!");
    }

    // REQUIRES: instructor != null
    // MODIFIES: this
    // EFFECTS: adds a student to a certain course
    private void toAddStudentToCourse(Instructor instructor) {
        Course c = spotCourse(instructor.getCourses());

        System.out.println("\nIs the student one of the existing students? Please enter yes/no.");
        String command = nextCommand();
        while (!command.equals("yes") && !command.equals("no")) {
            System.out.println(NOT_VALID_PROMPT);
            command = nextCommand();
        }
        Student s;
        if (command.equals("yes")) {
            s = spotStudent(studentList);
            if (c.getStudents().contains(s)) {
                System.out.println("\n" + s.getName() + " is already in the course!");
                return;
            }
        } else {
            System.out.println("\nPlease enter the name of the student:");
            s = new Student(nextCommand());
            studentList.add(s);
        }
        instructor.addStudentToCourse(s, c);
        System.out.println("\n" + s.getName() + " is now registered in " + c.getCourseID() + ".");
    }


    // EFFECTS: displays welcome interface to student and asks about the next move
    private void goStudentInterface() {
        Student student = spotStudent(studentList);

        System.out.println("\nHello " + student.getName()
                + ", please find below a list of courses you are currently learning:\n"
                + student.getCourses());

        studentAction(student);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void studentAction(Student student) {
        label:
        while (true) {
            displayStudentAction();
            String command = nextCommand();
            switch (command) {
                case "detail":
                    printCourseDetail(spotCourse(student.getCourses()));
                    break;
                case "grade":
                    toPrintGrades(student);
                    break;
                case "register":
                    toRegisterNewCourse(student);
                    break;
                case "r":
                    break label;
                default:
                    System.out.println(NOT_VALID_PROMPT);
                    break;
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayStudentAction() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tdetail -> look into the details of a certain course");
        System.out.println("\tgrade -> see your grades of all the courses taken");
        System.out.println("\tregister -> register a new course");
        System.out.println(RETURN_PROMPT);
    }

    // REQUIRES: student != null
    // EFFECTS: prints student's grades of all the courses taken
    private void toPrintGrades(Student student) {
        System.out.println("\nHere are your grades of all the courses taken:");
        for (Course c : student.getCourses()) {
            if (!student.getCoursesWithGrade().contains(c)) {
                System.out.println("Course: " + c.getCourseID() + ", Grade: Not published");
            }
        }
        for (CourseGrade courseGrade : student.getCourseGrades()) {
            System.out.println(courseGrade);
        }
    }

    // REQUIRES: student != null
    // MODIFIES: this
    // EFFECTS: adds student to a certain course
    private void toRegisterNewCourse(Student student) {
        Course c = spotCourse(courseList);
        if (student.getCourses().contains(c)) {
            System.out.println("\nYou're already in the course!");
        } else {
            student.addCourse(c);
            System.out.println("\nYou're now registered in " + c.getCourseID() + ".");
        }
    }

    // REQUIRES: courseCollection != null
    // EFFECTS: spots the target course in a collection of courses based on user input
    private Course spotCourse(Collection<Course> courseCollection) {
        System.out.println("\nPlease enter the courseID of the course:");
        System.out.println(COURSE_NOTE + courseCollection);
        while (true) {
            String courseID = nextCommand();
            for (Course c : courseCollection) {
                if (courseID.equals(c.getCourseID())) {
                    return c;
                }
            }
            System.out.println("No course with the given ID is found. Please try again.");
        }
    }

    // REQUIRES: instructorCollection != null
    // EFFECTS: spots the target instructor in a collection of instructors based on user input
    private Instructor spotInstructor(Collection<Instructor> instructorCollection) {
        System.out.println("\nPlease enter the name of the instructor:");
        System.out.println(INSTRUCTOR_NOTE + instructorCollection);
        while (true) {
            String name = nextCommand();
            for (Instructor i : instructorCollection) {
                if (name.equals(i.getName())) {
                    return i;
                }
            }
            System.out.println("No instructor with the given name is found. Please try again.");
        }
    }

    // REQUIRES: studentCollection != null
    // EFFECTS: spots the target student in a collection of students based on user input
    private Student spotStudent(Collection<Student> studentCollection) {
        System.out.println("\nPlease enter the name of the student:");
        System.out.println(STUDENT_NOTE + studentCollection);
        while (true) {
            String name = nextCommand();
            for (Student s : studentCollection) {
                if (name.equals(s.getName())) {
                    return s;
                }
            }
            System.out.println("No student with the given name is found. Please try again.");
        }
    }

    //EFFECTS: handles user input, converts the input into lower case and
    //         removes white space and quotation marks around the input
    private String nextCommand() {
        String command = input.next().toLowerCase();
        command = command.trim();
        command = command.replaceAll("\"|'", "");
        return command;
    }

    //EFFECTS: stops receiving user input
    public void endProgram() {
        System.out.println("\nThank you! Quitting...");
        input.close();
    }
}
