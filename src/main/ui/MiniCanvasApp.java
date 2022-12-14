package ui;

import model.Canvas;
import model.Event;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Canvas application
public class MiniCanvasApp extends JFrame {
    private static final String JSON_STORE = "./data/canvas.json";
    private static final int DEFAULT_COURSE_SIZE = 5;
    private static final String ICONS_PATH = "src/main/ui/icons/";
    private static final ImageIcon BOOKS = new ImageIcon(ICONS_PATH + "books.png");
    private static final int CANVAS_WIDTH = 420;
    private static final int CANVAS_HEIGHT = 420;
    private static final Color RED = new Color(0xDF7861);
    private static final Color IVORY = new Color(0xFCF8E8);
    private static final Color GREEN = new Color(0x94B49F);
    private static final Color PURPLE = new Color(0x76549A);
    private final JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private final JsonReader jsonReader = new JsonReader(JSON_STORE);
    private Canvas canvas = new Canvas();

    // EFFECTS: runs the canvas application
    public MiniCanvasApp() throws FileNotFoundException {
        super("Canvas");
        initUI();
        startProgram();
    }

    // EFFECTS: initializes GUI
    private void initUI() {
        setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(IVORY);

        JLabel welcome = new JLabel("Welcome to Canvas", BOOKS, JLabel.CENTER);
        welcome.setForeground(IVORY);
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 30));
        welcome.setIconTextGap(10);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(RED);
        topPanel.setPreferredSize(new Dimension(WIDTH, 50));
        topPanel.add(welcome);

        add(topPanel, BorderLayout.NORTH);

        displayMenu();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: if there exists canvas.json and the user wants to load data, loads the data from canvas.json;
    //          otherwise initializes course list, student list and student list in canvas
    private void startProgram() {
        if (new File(JSON_STORE).exists()) {
            int command = JOptionPane.showConfirmDialog(null,
                    "Would you like to load existing Canvas records?",
                    "Load or Create", JOptionPane.YES_NO_OPTION);
            if (command == JOptionPane.YES_OPTION) {
                loadCanvas();
                return;
            }
        }
        initCanvas();
    }

    // MODIFIES: this
    // EFFECTS: initializes course list, student list and student list in canvas
    public void initCanvas() {
        JOptionPane.showMessageDialog(null,
                "New Canvas has been created.",
                "Create", JOptionPane.INFORMATION_MESSAGE);
        setInstructorAndCourseList();
        setStudentList();
        setCourseRegistration();
    }

    // MODIFIES: this
    // EFFECTS: initializes course list and student list
    private void setInstructorAndCourseList() {
        Instructor gregor = new Instructor("gregor");
        Instructor tara = new Instructor("tara");
        Instructor eugenia = new Instructor("eugenia");
        Instructor jamie = new Instructor("jamie");
        canvas.addInstructor(gregor);
        canvas.addInstructor(tara);
        canvas.addInstructor(eugenia);
        canvas.addInstructor(jamie);

        Course cpsc110 = new Course("cpsc 110", 4, gregor);
        Course wrds150 = new Course("wrds 150", 3, tara);
        Course engl301 = new Course("engl 301", 4, tara);
        Course engl304 = new Course("engl 304", 4, tara);
        Course stat203 = new Course("stat 203", 3, eugenia);
        Course stat200 = new Course("stat 200", 3, eugenia);
        Course math180 = new Course("math 180", 6, jamie);
        canvas.addCourse(cpsc110);
        canvas.addCourse(wrds150);
        canvas.addCourse(engl301);
        canvas.addCourse(engl304);
        canvas.addCourse(stat203);
        canvas.addCourse(stat200);
        canvas.addCourse(math180);
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
        canvas.addStudent(miranda);
        canvas.addStudent(francis);
        canvas.addStudent(kate);
        canvas.addStudent(violet);
        canvas.addStudent(harold);
        canvas.addStudent(john);
        canvas.addStudent(steve);
    }

    // MODIFIES: this
    // EFFECTS: initializes students' course registration
    private void setCourseRegistration() {
        Random random = new Random();
        for (Course c : canvas.getCourseList()) {
            List<Integer> chosen = new ArrayList<>();
            for (int i = 0; i < DEFAULT_COURSE_SIZE; i++) {
                int next = random.nextInt(canvas.getStudentList().size());
                if (!chosen.contains(next)) {
                    chosen.add(next);
                    c.addStudent(canvas.getStudentList().get(next));
                }
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        JLabel identity = new JLabel("Please select your identity:", JLabel.CENTER);
        identity.setForeground(GREEN);
        identity.setFont(new Font("Times New Roman", Font.BOLD, 25));

        ImageIcon instructorIcon = new ImageIcon(ICONS_PATH + "training.png");
        ImageIcon studentIcon = new ImageIcon(ICONS_PATH + "reading-book.png");

        JButton instructorButton = createButton("Instructor", instructorIcon, 140);
        instructorButton.addActionListener(e -> new InstructorInterface());
        JButton studentButton = createButton("Student", studentIcon, 140);
        studentButton.addActionListener(e -> new StudentInterface());

        JPanel menuPanel = new JPanel(new BorderLayout(20, 20));
        menuPanel.setBackground(IVORY);
        menuPanel.setBorder(new EmptyBorder(80, 60, 120, 60));
        menuPanel.add(identity, BorderLayout.NORTH);
        menuPanel.add(instructorButton, BorderLayout.WEST);
        menuPanel.add(studentButton, BorderLayout.EAST);

        ImageIcon exitIcon = new ImageIcon(ICONS_PATH + "exit.png");
        JButton exitButton = customizeButton(new JButton(exitIcon), GREEN);
        exitButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        exitButton.addActionListener(e -> endProgram());

        add(menuPanel);
        add(exitButton, BorderLayout.SOUTH);
    }

    // EFFECTS: provides a background template which is broadly used across different interfaces
    private void setBasePanel(JFrame frame, String s, Color color) {
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(20, 0));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(IVORY);

        JLabel welcome = new JLabel(s, BOOKS, JLabel.CENTER);
        welcome.setForeground(IVORY);
        welcome.setFont(new Font("Times New Roman", Font.BOLD, 30));
        welcome.setIconTextGap(10);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(color);
        topPanel.setPreferredSize(new Dimension(WIDTH, 50));
        topPanel.add(welcome);
        frame.add(topPanel, BorderLayout.NORTH);
    }

    // EFFECTS: turns a list of courses into a JComboBox from which the target course can be selected
    private JComboBox<Course> spotCourse(List<Course> courseList) {
        int size = courseList.size();
        Course[] courses = new Course[size];
        for (int i = 0; i < size; i++) {
            courses[i] = courseList.get(i);
        }
        return new JComboBox<>(courses);
    }

    // EFFECTS: turns a list of instructors into a JComboBox from which the target instructor can be selected
    private JComboBox<Instructor> spotInstructor(List<Instructor> instructorList) {
        int size = instructorList.size();
        Instructor[] instructors = new Instructor[size];
        for (int i = 0; i < size; i++) {
            instructors[i] = instructorList.get(i);
        }
        return new JComboBox<>(instructors);
    }

    // EFFECTS: turns a list of students into a JComboBox from which the target student can be selected
    private JComboBox<Student> spotStudent(List<Student> studentList) {
        int size = studentList.size();
        Student[] students = new Student[size];
        for (int i = 0; i < size; i++) {
            students[i] = studentList.get(i);
        }
        return new JComboBox<>(students);
    }

    // EFFECTS: provides a particular JButton meeting the specified criteria
    private JButton createButton(String s, ImageIcon imageIcon, int width) {
        JButton button = new JButton(s, imageIcon);
        button.setForeground(GREEN);
        button.setFont(new Font("Times New Roman", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(width, 50));
        return button;
    }

    // EFFECTS: customizes an existing JButton to fit into its background
    private JButton customizeButton(JButton button, Color c) {
        button.setBackground(c);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    //EFFECTS: stops receiving user input and saves the data to testReaderGeneralCanvas.json if permitted
    public void endProgram() {
        int command = JOptionPane.showConfirmDialog(null,
                "Would you like to save the changes to Canvas records?",
                "", JOptionPane.YES_NO_OPTION);
        if (command == JOptionPane.YES_OPTION) {
            saveCanvas();
        }
        dispose();
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    // EFFECTS: saves canvas to file
    private void saveCanvas() {
        try {
            jsonWriter.open();
            jsonWriter.write(canvas);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved the data to " + JSON_STORE,
                    "Save", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE,
                    "Fail to write", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads canvas from file
    private void loadCanvas() {
        try {
            canvas = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded the data from " + JSON_STORE,
                    "Load", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read from file: " + JSON_STORE,
                    "Fail to Load", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    // Instructor's User Interface
    private class InstructorInterface extends JFrame {
        private final JPanel coursePanel = new JPanel(new GridLayout(0, 1));
        private JComboBox<Instructor> instructors;
        private Instructor instructor;

        InstructorInterface() {
            initUI();
        }

        // MODIFIES: this
        // EFFECTS: initializes GUI for instructors, identifies which instructor the user is
        private void initUI() {
            setBasePanel(this, "Welcome, Instructor!", GREEN);

            instructors = spotInstructor(canvas.getInstructorList());
            instructors.setBorder(new EmptyBorder(0, 20, 0, 20));
            instructors.setPreferredSize(new Dimension(200, 50));
            instructors.addActionListener(e -> {
                instructor = (Instructor) instructors.getSelectedItem();
                displayCourses();
            });
            add(instructors, BorderLayout.WEST);

            ImageIcon homeIcon = new ImageIcon(ICONS_PATH + "home.png");

            JButton homeButton = customizeButton(new JButton(homeIcon), IVORY);
            homeButton.setBorder(new EmptyBorder(0, 0, 8, 0));
            homeButton.addActionListener(e -> dispose());

            add(homeButton, BorderLayout.SOUTH);

            setVisible(true);
        }

        // EFFECTS: displays all the courses the selected instructor is teaching
        private void displayCourses() {
            coursePanel.removeAll();
            coursePanel.setBorder(new EmptyBorder(45, 20, 45, 20));
            coursePanel.setBackground(IVORY);
            for (Course c : instructor.getCourses()) {
                JButton courseButton = new JButton(c.getCourseID());
                courseButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                courseButton.setForeground(PURPLE);
                courseButton.addActionListener(e -> new InstructorCourseInterface(c));
                coursePanel.add(courseButton);
            }

            revalidate();
            repaint();

            add(coursePanel);
            setVisible(true);
        }
    }

    // Instructor's Course Interface
    private class InstructorCourseInterface extends JFrame {
        private final Course course;
        private final JPanel detailPanel = new JPanel(new BorderLayout());

        InstructorCourseInterface(Course course) {
            this.course = course;
            initUI();
        }

        // MODIFIES: this
        // EFFECTS: initializes GUI for instructors where course details and more options
        //          related to the selected course are shown
        private void initUI() {
            setBasePanel(this, "Course Information", PURPLE);
            printCourseDetail();
            addOptionsPanel();
            setVisible(true);
        }

        // MODIFIES: this
        // EFFECTS: prints course details of the selected course
        public void printCourseDetail() {
            JLabel courseDetail = new JLabel("<html>Course: " + course.getCourseID()
                    + "<br>Credits: " + course.getCredits()
                    + "<br>Instructor: " + course.getInstructor()
                    + "<br>Students: " + course.getStudents() + "</html>",
                    JLabel.CENTER);
            courseDetail.setForeground(IVORY);
            courseDetail.setFont(new Font("Times New Roman", Font.BOLD, 20));
            courseDetail.setBorder(new EmptyBorder(50, 25, 0, 25));

            detailPanel.removeAll();
            detailPanel.setBackground(GREEN);
            detailPanel.add(courseDetail);

            revalidate();
            repaint();

            add(detailPanel);
        }

        // EFFECTS: displays 2 options the instructor can select between
        private void addOptionsPanel() {
            JPanel optionsPanel = new JPanel(new BorderLayout(20, 30));
            optionsPanel.setBackground(GREEN);
            optionsPanel.setBorder(new EmptyBorder(40, 30, 8, 30));

            ImageIcon gradeIcon = new ImageIcon(ICONS_PATH + "exam.png");
            ImageIcon studentIcon = new ImageIcon(ICONS_PATH + "reading-book.png");

            JButton addGradeButton = createButton("Add Grade", gradeIcon, 160);
            addGradeButton.addActionListener(e -> new GradeInterface(course));

            JButton addStudentButton = createButton("Add Student", studentIcon, 160);
            addStudentButton.addActionListener(e -> new RegisterStudentInterface(course, this));

            ImageIcon backIcon = new ImageIcon(ICONS_PATH + "back.png");
            JButton backButton = customizeButton(new JButton(backIcon), GREEN);
            backButton.addActionListener(e -> dispose());

            optionsPanel.add(addGradeButton, BorderLayout.WEST);
            optionsPanel.add(addStudentButton, BorderLayout.EAST);
            optionsPanel.add(backButton, BorderLayout.SOUTH);
            add(optionsPanel, BorderLayout.SOUTH);
        }
    }

    // Instructor's Grade Interface
    private class GradeInterface extends JFrame implements ActionListener, ItemListener {
        private final Course course;
        private final JButton completeButton = new JButton("Complete Grading");
        private JComboBox<Student> students;
        private Student student;
        private int skippedClass;
        private Rank projectRank;
        private int correctQuestion;

        GradeInterface(Course course) {
            this.course = course;
            initUI();
        }

        // EFFECTS: initializes GUI for instructors
        private void initUI() {
            setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
            setResizable(false);
            setLayout(null);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setBackground(IVORY);

            JLabel welcome = new JLabel("Grading: " + course, BOOKS, JLabel.CENTER);
            welcome.setForeground(IVORY);
            welcome.setFont(new Font("Times New Roman", Font.BOLD, 30));
            welcome.setIconTextGap(10);

            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(RED);
            topPanel.setBounds(0, 0, CANVAS_WIDTH, 50);
            topPanel.add(welcome);
            add(topPanel);

            students = spotStudent(course.getStudents());
            students.setBounds(110, 80, 200, 50);
            students.addActionListener(this);
            add(students);

            setCompleteButton();

            setVisible(true);
        }

        // MODIFIES: this
        // EFFECTS: adds student's grades when clicking on the "Complete" button
        private void setCompleteButton() {
            completeButton.setVisible(false);
            completeButton.setBackground(IVORY);
            completeButton.setForeground(GREEN);
            completeButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
            completeButton.addActionListener(e -> {
                CourseGrade courseGrade = new CourseGrade(course.getCourseID(),
                        skippedClass, projectRank, correctQuestion);
                course.getInstructor().addStudentGrade(student, courseGrade);
                JOptionPane.showMessageDialog(null,
                        student + "'s grades are added successfully!",
                        "Grade", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            });
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where instructors mark students' attendance, project, exam
        private void displayMarkPanel() {
            CardLayout cardLayout = new CardLayout();
            JPanel markPanel = new JPanel(cardLayout);
            markPanel.setBackground(GREEN);
            markPanel.setBounds(0, 150, CANVAS_WIDTH, CANVAS_HEIGHT - 250);

            markPanel.add(createAttendancePanel());
            markPanel.add(createRankPanel());
            markPanel.add(createExamPanel());

            ImageIcon prevIcon = new ImageIcon(ICONS_PATH + "previous.png");
            ImageIcon nextIcon = new ImageIcon(ICONS_PATH + "next.png");

            JButton prevButton = customizeButton(new JButton(prevIcon), IVORY);
            prevButton.addActionListener(e -> cardLayout.previous(markPanel));
            JButton nextButton = customizeButton(new JButton(nextIcon), IVORY);
            nextButton.addActionListener(e -> cardLayout.next(markPanel));

            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            btnPanel.setBackground(IVORY);
            btnPanel.setBounds(0, CANVAS_HEIGHT - 100, CANVAS_WIDTH, 50);
            btnPanel.add(prevButton);
            btnPanel.add(nextButton);
            btnPanel.add(completeButton);

            add(markPanel);
            add(btnPanel);
            setVisible(true);
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where instructors mark students' attendance
        private JPanel createAttendancePanel() {
            JPanel attendancePanel = new JPanel();
            attendancePanel.setLayout(null);
            attendancePanel.setBackground(IVORY);

            JLabel label = new JLabel("<html>How many classes did " + student + " skip?</html>", JLabel.CENTER);
            label.setForeground(GREEN);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setBounds(10, 0, 400, 50);

            JSlider slider = new JSlider(0, 10, 5);
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(5);
            slider.setPaintLabels(true);
            slider.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            slider.addChangeListener((ChangeEvent event) -> {
                skippedClass = slider.getValue();
                label.setText("Skipped Class: " + skippedClass);
            });
            slider.setBounds(10, 80, 400, 50);

            attendancePanel.add(label);
            attendancePanel.add(slider);

            return attendancePanel;
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where instructors mark students' project
        private JPanel createRankPanel() {
            JPanel rankPanel = new JPanel();
            rankPanel.setLayout(null);
            rankPanel.setBackground(IVORY);

            JLabel label = new JLabel("<html>Please rank " + student + "'s project:</html>", JLabel.CENTER);
            label.setForeground(GREEN);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setBounds(60, 0, 300, 50);

            JPanel rankOptions = new JPanel(new GridLayout(2, 3));
            rankOptions.setBackground(IVORY);
            rankOptions.setBounds(10, 80, 400, 50);

            List<JRadioButton> rankButtons = new ArrayList<>();

            ButtonGroup group = new ButtonGroup();

            for (int i = 0; i < Rank.values().length - 1; i++) {
                rankButtons.add(new JRadioButton(Rank.values()[i].toString()));
                rankButtons.get(i).addItemListener(this);
                group.add(rankButtons.get(i));
                rankOptions.add(rankButtons.get(i));
            }

            rankPanel.add(rankOptions);
            rankPanel.add(label);
            return rankPanel;
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where instructors mark students' exam
        private JPanel createExamPanel() {
            JPanel examPanel = new JPanel();
            examPanel.setLayout(null);
            examPanel.setBackground(IVORY);

            JLabel label = new JLabel("<html>How many questions did " + student
                    + " answer correctly in the exam?</html>", JLabel.CENTER);
            label.setForeground(GREEN);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setBounds(60, 0, 300, 50);

            JSlider slider = new JSlider(0, 10, 5);
            slider.setPaintTicks(true);
            slider.setMinorTickSpacing(1);
            slider.setMajorTickSpacing(5);
            slider.setPaintLabels(true);
            slider.setFont(new Font("Times New Roman", Font.PLAIN, 15));
            slider.addChangeListener((ChangeEvent event) -> {
                correctQuestion = slider.getValue();
                label.setText("Correct Question: " + correctQuestion);
                completeButton.setVisible(true);
            });
            slider.setBounds(10, 80, 400, 50);

            examPanel.add(label);
            examPanel.add(slider);

            return examPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == students) {
                students.setEnabled(false);
                student = (Student) students.getSelectedItem();
                displayMarkPanel();
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JRadioButton button = (JRadioButton) e.getSource();
                projectRank = Rank.valueOf(button.getText().toUpperCase());
            }
        }
    }

    // Instructor's "Register Student to Course" Interface
    private class RegisterStudentInterface extends JFrame {
        private final Course course;
        private final InstructorCourseInterface instructorCourseInterface;
        private Student student;

        public RegisterStudentInterface(Course course, InstructorCourseInterface instructorCourseInterface) {
            this.course = course;
            this.instructorCourseInterface = instructorCourseInterface;
            initUI();
        }

        // EFFECTS: initializes GUI for instructors
        private void initUI() {
            setBasePanel(this, "Register: " + course, GREEN);

            int command = JOptionPane.showConfirmDialog(null,
                    "Is the student one of the existing students?",
                    "Register", JOptionPane.YES_NO_OPTION);

            if (command == JOptionPane.YES_OPTION) {
                add(createSelectStudentPanel());
            } else {
                add(createNewStudentPanel());
            }

            addConfirmButton();

            setVisible(true);
        }

        // MODIFIES: this
        // EFFECTS: adds student to course when clicking on the "Confirm" button if student isn't registered in course
        //          otherwise do nothing
        private void addConfirmButton() {
            ImageIcon confirmIcon = new ImageIcon(ICONS_PATH + "check.png");

            JButton confirmButton = customizeButton(new JButton(confirmIcon), IVORY);
            confirmButton.setBorder(new EmptyBorder(40, 0, 40, 0));
            confirmButton.addActionListener(e -> {
                if (course.getStudents().contains(student)) {
                    JOptionPane.showMessageDialog(null,
                            student + " is already in the course!",
                            "Register", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    course.addStudent(student);
                    instructorCourseInterface.printCourseDetail();
                    JOptionPane.showMessageDialog(null,
                            student + " is now registered to " + course + ".",
                            "Register", JOptionPane.INFORMATION_MESSAGE);
                }
                dispose();
            });

            add(confirmButton, BorderLayout.SOUTH);
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where instructors add an existing student to the course
        private JPanel createSelectStudentPanel() {
            JPanel selectStudentPanel = new JPanel(new BorderLayout());
            selectStudentPanel.setBackground(IVORY);

            JLabel label = new JLabel("Register: ", JLabel.CENTER);
            label.setForeground(PURPLE);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setBorder(new EmptyBorder(60, 0, 0, 0));

            JComboBox<Student> students = spotStudent(canvas.getStudentList());
            students.setBorder(new EmptyBorder(0, 100, 30, 100));
            students.addActionListener(e -> {
                students.setEnabled(false);
                student = (Student) students.getSelectedItem();
                label.setText("Register: " + student);
            });

            selectStudentPanel.add(label, BorderLayout.NORTH);
            selectStudentPanel.add(students);

            return selectStudentPanel;
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where instructors add a new student to the course
        private JPanel createNewStudentPanel() {
            JPanel newStudentPanel = new JPanel(new BorderLayout(20, 20));
            newStudentPanel.setBackground(IVORY);
            newStudentPanel.setBorder(new EmptyBorder(0, 50, 60, 50));

            JLabel label = new JLabel("Register New Student: ", JLabel.CENTER);
            label.setForeground(PURPLE);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setBorder(new EmptyBorder(60, 0, 25, 0));

            JButton btn = new JButton("Confirm");
            btn.setForeground(PURPLE);
            btn.setFont(new Font("Times New Roman", Font.BOLD, 15));

            JTextField textField = new JTextField("Student Name");
            textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            textField.setForeground(PURPLE);

            btn.addActionListener(e -> {
                btn.setEnabled(false);
                textField.setEditable(false);
                canvas.addStudent(student = new Student(textField.getText()));
                label.setText("Register New Student: " + student);
            });

            newStudentPanel.add(label, BorderLayout.NORTH);
            newStudentPanel.add(textField);
            newStudentPanel.add(btn, BorderLayout.EAST);

            return newStudentPanel;
        }
    }

    // Student's User Interface
    private class StudentInterface extends JFrame {
        private final JPanel coursePanel = new JPanel(new GridLayout(0, 1));
        private JComboBox<Student> students;
        private Student student;
        private JButton gradeButton;
        private JButton registerButton;

        StudentInterface() {
            initUI();
        }

        // MODIFIES: this
        // EFFECTS: initializes GUI for students, identifies which student the user is
        private void initUI() {
            setBasePanel(this, "Welcome, Student!", GREEN);

            students = spotStudent(canvas.getStudentList());
            students.setBorder(new EmptyBorder(0, 20, 0, 20));
            students.setPreferredSize(new Dimension(200, 50));
            students.addActionListener(e -> {
                student = (Student) students.getSelectedItem();
                gradeButton.setVisible(true);
                registerButton.setVisible(true);
                displayCourses();
            });
            add(students, BorderLayout.WEST);

            addBottomPanel();

            setVisible(true);
        }

        // EFFECTS: displays options students can choose between (i.e. look up grades & register new course)
        private void addBottomPanel() {
            JPanel bottomPanel = new JPanel(new GridLayout(1, 3));
            bottomPanel.setBackground(IVORY);

            ImageIcon homeIcon = new ImageIcon(ICONS_PATH + "home.png");
            JButton homeButton = customizeButton(new JButton(homeIcon), IVORY);
            homeButton.setBorder(new EmptyBorder(0, 0, 8, 0));
            homeButton.addActionListener(e -> dispose());

            gradeButton = new JButton("Grade", new ImageIcon(ICONS_PATH + "grade.png"));
            gradeButton.setVisible(false);
            gradeButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            gradeButton.setForeground(PURPLE);
            gradeButton.setBorder(new EmptyBorder(0, 0, 8, 0));
            gradeButton.addActionListener(e -> new LookUpGradeInterface(student));

            registerButton = new JButton("Register", new ImageIcon(ICONS_PATH + "edit.png"));
            registerButton.setVisible(false);
            registerButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
            registerButton.setForeground(PURPLE);
            registerButton.setBorder(new EmptyBorder(0, 0, 8, 0));
            registerButton.addActionListener(e -> new RegisterCourseInterface(student, this));

            bottomPanel.add(gradeButton);
            bottomPanel.add(homeButton);
            bottomPanel.add(registerButton);

            add(bottomPanel, BorderLayout.SOUTH);
        }

        // EFFECTS: displays all the courses the selected student is taking
        public void displayCourses() {
            coursePanel.removeAll();
            coursePanel.setBorder(new EmptyBorder(30, 20, 30, 20));
            coursePanel.setBackground(IVORY);
            for (Course c : student.getCourses()) {
                JButton courseButton = new JButton(c.getCourseID());
                courseButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
                courseButton.setForeground(PURPLE);
                courseButton.addActionListener(e -> new StudentCourseInterface(c));
                coursePanel.add(courseButton);
            }

            revalidate();
            repaint();

            add(coursePanel);
            setVisible(true);
        }
    }

    // Student's Course Interface
    private class StudentCourseInterface extends JFrame {
        private final Course course;

        StudentCourseInterface(Course course) {
            this.course = course;
            initUI();
        }

        // EFFECTS: initializes GUI for students where course details are shown
        private void initUI() {
            setBasePanel(this, "Course Information", PURPLE);

            printCourseDetail();

            ImageIcon backIcon = new ImageIcon(ICONS_PATH + "back.png");
            JButton backButton = customizeButton(new JButton(backIcon), GREEN);
            backButton.setBorder(new EmptyBorder(88, 0, 12, 0));
            backButton.addActionListener(e -> dispose());
            add(backButton, BorderLayout.SOUTH);

            setVisible(true);
        }

        // EFFECTS: prints course details of the selected course
        private void printCourseDetail() {
            JLabel courseDetail = new JLabel("<html>Course: " + course.getCourseID()
                    + "<br>Credits: " + course.getCredits()
                    + "<br>Instructor: " + course.getInstructor()
                    + "<br>Students: " + course.getStudents() + "</html>",
                    JLabel.CENTER);
            courseDetail.setForeground(IVORY);
            courseDetail.setFont(new Font("Times New Roman", Font.BOLD, 20));
            courseDetail.setBorder(new EmptyBorder(50, 25, 0, 25));

            JPanel detailPanel = new JPanel(new BorderLayout());
            detailPanel.setBackground(GREEN);
            detailPanel.add(courseDetail);

            add(detailPanel);
        }
    }

    // Interface where students look up their grades
    private class LookUpGradeInterface extends JFrame {
        private final Student student;

        LookUpGradeInterface(Student student) {
            this.student = student;
            initUI();
        }

        // EFFECTS: initializes GUI for students where their course grades are shown
        private void initUI() {
            setBasePanel(this, "Grade", RED);

            printGrades();

            ImageIcon backIcon = new ImageIcon(ICONS_PATH + "back.png");
            JButton backButton = customizeButton(new JButton(backIcon), GREEN);
            backButton.setBorder(new EmptyBorder(88, 0, 12, 0));
            backButton.addActionListener(e -> dispose());
            add(backButton, BorderLayout.SOUTH);

            setVisible(true);
        }

        // EFFECTS: displays students' course grades
        private void printGrades() {
            StringBuilder grades = new StringBuilder();
            for (CourseGrade courseGrade : student.getCourseGrades()) {
                grades.append(courseGrade).append("<br>");
            }
            for (Course c : student.getCourses()) {
                if (!student.getCoursesWithGrade().contains(c.getCourseID())) {
                    grades.append("Course: ").append(c).append(", Grade: Not published<br>");
                }
            }

            JLabel courseDetail = new JLabel("<html>" + grades + "</html>", JLabel.CENTER);
            courseDetail.setForeground(IVORY);
            courseDetail.setFont(new Font("Times New Roman", Font.BOLD, 20));
            courseDetail.setBorder(new EmptyBorder(50, 25, 0, 25));

            JPanel detailPanel = new JPanel(new BorderLayout());
            detailPanel.setBackground(GREEN);
            detailPanel.add(courseDetail);

            add(detailPanel);
        }
    }

    // Student's "Register New Course" Interface
    private class RegisterCourseInterface extends JFrame {
        private final Student student;
        private final StudentInterface studentInterface;
        private Course course;

        RegisterCourseInterface(Student student, StudentInterface studentInterface) {
            this.student = student;
            this.studentInterface = studentInterface;
            initUI();
        }

        // EFFECTS: initializes interface for students where they can register to a new course
        private void initUI() {
            setBasePanel(this, "Register", RED);

            add(createSelectCoursePanel());

            addConfirmButton();

            setVisible(true);
        }

        // MODIFIES: this
        // EFFECTS: displays the panel where students select a course to register
        private JPanel createSelectCoursePanel() {
            JPanel selectCoursePanel = new JPanel(new BorderLayout());
            selectCoursePanel.setBackground(IVORY);

            JLabel label = new JLabel("Register: ", JLabel.CENTER);
            label.setForeground(GREEN);
            label.setFont(new Font("Times New Roman", Font.BOLD, 20));
            label.setBorder(new EmptyBorder(60, 0, 0, 0));

            JComboBox<Course> courses = spotCourse(canvas.getCourseList());
            courses.setBorder(new EmptyBorder(0, 100, 30, 100));
            courses.addActionListener(e -> {
                courses.setEnabled(false);
                course = (Course) courses.getSelectedItem();
                label.setText("Register: " + course);
            });

            selectCoursePanel.add(label, BorderLayout.NORTH);
            selectCoursePanel.add(courses);

            return selectCoursePanel;
        }

        // MODIFIES: this
        // EFFECTS: adds student to course when clicking on the "Confirm" button if student isn't registered in course
        //          otherwise do nothing
        private void addConfirmButton() {
            ImageIcon confirmIcon = new ImageIcon(ICONS_PATH + "check2.png");

            JButton confirmButton = customizeButton(new JButton(confirmIcon), IVORY);
            confirmButton.setBorder(new EmptyBorder(40, 0, 40, 0));
            confirmButton.addActionListener(e -> {
                if (student.getCourses().contains(course)) {
                    JOptionPane.showMessageDialog(null,
                            "You're already in the course!",
                            "Register", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    student.addCourse(course);
                    studentInterface.displayCourses();
                    JOptionPane.showMessageDialog(null,
                            "You're now registered to " + course + ".",
                            "Register", JOptionPane.INFORMATION_MESSAGE);
                }
                dispose();
            });

            add(confirmButton, BorderLayout.SOUTH);
        }
    }
}