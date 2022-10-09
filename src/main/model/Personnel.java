package model;

import java.util.HashSet;
import java.util.Set;

public class Personnel {
    protected final String name;
    protected Set<Course> courses;

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
