package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonnelTest {
    private Personnel personnel;

    @BeforeEach
    public void runBefore() {
        personnel = new Instructor("harold");
    }

    @Test
    public void testConstructor() {
        assertEquals("harold", personnel.getName());
        assertEquals(new ArrayList<>(), personnel.getCourses());
    }
}
