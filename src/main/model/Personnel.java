package model;

import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a person with a name and a list of courses involved
public abstract class Personnel implements Writable {
    protected final String name;
    protected List<Course> courses;

    // REQUIRES: name != null
    // EFFECTS: creates a new person with the given name and an empty list of courses
    public Personnel(String name) {
        this.name = name;
        courses = new ArrayList<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
