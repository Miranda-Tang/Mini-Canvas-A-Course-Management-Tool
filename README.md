# Mini Canvas: A Learning Analytics Tool

*Given a course id, extracts details of course.*

## 📚 Description

The goal of this project is to build a tool that is useful to instructors and students in course management. Each course is identified by a unique `course_id` and contains information about the number of `credits` the course is worth, the `instructor` of the course as well as `students` enrolled in this course.

Possible features include:
- Specifying a course to get detailed information;
- Adding a student to a certain course;
- Entering students' grades of each course;
- Visualizing student's grades in a dashboard.

The target is to be able to combine course information and student data for a **full picture** of the course and its activity. Through this project I expect to discover valuable connections between CS and my previous studies in the field of education.

## 📝 User Stories

- As an instructor, I want to be able to view the list of courses I'm teaching.
- As an instructor, I want to be able to select a course in my database and view the course in detail.
- As an instructor, I want to be able to enter a student's grade of a certain course.
- As an instructor, I want to be able to select a course and add a new student to that course.
***
- As a student, I want to be able to view the list of courses I'm learning.
- As a student, I want to be able to view my grades of all the courses taken.
- As a student, I want to be able to register in a new course.
***
- As an instructor or a student, I want to be able to save the Canvas records to file.
- As an instructor or a student, I want to be able to load the Canvas records from file.

## 🔭 Technical Points
- Achieve data persistence using `JSON`
- Develop GUI using the Java `Swing` library