package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents a student with a name, a list of courses he/she is learning and a list of grades of the courses taken
public class Student extends Personnel {
    private final List<CourseGrade> courseGrades;

    // REQUIRES: name != null
    // EFFECTS: creates a new student with the given name, an empty list of courses and an empty list of course grades
    public Student(String name) {
        super(name);
        courseGrades = new ArrayList<>();
    }

    // getters
    public List<CourseGrade> getCourseGrades() {
        return courseGrades;
    }

    // EFFECTS: returns a list of courses the grades of which have been included in the course grades list
    public List<String> getCoursesWithGrade() {
        List<String> res = new ArrayList<>();
        for (CourseGrade cg : courseGrades) {
            res.add(cg.getCourseID());
        }
        return res;
    }

    // EFFECTS: returns a string representation of student
    @Override
    public String toString() {
        return name;
    }

    // EFFECTS: returns true if the two students are considered equivalent, false otherwise
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student s = (Student) obj;
        if (!name.equals(s.getName())) {
            return false;
        }
        if (courseGrades.size() != s.getCourseGrades().size()) {
            return false;
        }
        for (CourseGrade cg : courseGrades) {
            if (!s.getCourseGrades().contains(cg)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("courseGrades", courseGradesToJson());
        return json;
    }

    // EFFECTS: returns course grades in the course grades list as a JSON array
    private JSONArray courseGradesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CourseGrade cg : courseGrades) {
            jsonArray.put(cg.toJson());
        }

        return jsonArray;
    }
}
