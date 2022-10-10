package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonnelTest {
    private Personnel personnel;

    @BeforeEach
    public void runBefore() {
        personnel = new Personnel("harold");
    }

    @Test
    public void testConstructor() {
        assertEquals("harold", personnel.getName());
        assertEquals(new HashSet<>(), personnel.getCourses());
    }

}
