package model;

import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // EFFECTS: returns a string representation of person
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Personnel personnel = (Personnel) o;
        return Objects.equals(getName(), personnel.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
