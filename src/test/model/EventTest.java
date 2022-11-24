package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("New course \"math180\" is created.");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("New course \"math180\" is created.", e.getDescription());
        assertTrue(Math.abs(d.getTime() - e.getDate().getTime()) < 1000);
    }

    @Test
    public void testEquals() {
        assertNotEquals(e, null);
        assertNotEquals(e, new Instructor("gregor"));

        Event e1 = new Event("event");
        Event e2 = new Event("event");
        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        Event e3 = new Event("new event");
        assertNotEquals(e1, e3);
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "New course \"math180\" is created.", e.toString());
    }
}