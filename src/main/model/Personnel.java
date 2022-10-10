package model;

import java.util.HashSet;
import java.util.Set;

// Represents a person with a name and a set of courses involved
public class Personnel {
    protected final String name;
    protected Set<Course> courses;

    // REQUIRES: name != null
    // EFFECTS: creates a new person with the given name and an empty set of courses
    public Personnel(String name) {
        this.name = name;
        courses = new HashSet<>();
    }

    // getters
    public String getName() {
        return name;
    }

    public Set<Course> getCourses() {
        return courses;
    }
}
