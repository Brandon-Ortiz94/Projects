package edu.ncsu.csc216.ticket_manager.model.ticket;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Category;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.Priority;
import edu.ncsu.csc216.ticket_manager.model.ticket.Ticket.TicketType;

/**
 * Testing suite for the Ticket class.
 * @author brandonortiz
 */
class TicketTest {

    /**
     * Tests the constructor intended to receive instantiation parameters from the GUI.
     * This test only considers valid parameters.
     * This case tests instantiation with all enumerators (TicketType, Category, and Priority)
     */
    @Test
    void testTicketConstructorWithGUI() {
        Ticket a = assertDoesNotThrow(() -> new Ticket(TicketType.INCIDENT, "subject", "brandon", Category.HARDWARE,
                Priority.HIGH, "note"));

        assertEquals("Incident", 	a.getTicketTypeString());
        assertEquals(TicketType.INCIDENT, a.getTicketType());
        assertEquals("subject", 	a.getSubject());
        assertEquals("brandon", 	a.getCaller());
        assertEquals("Hardware", 	a.getCategory());
        assertEquals("High", 		a.getPriority());
        assertEquals("note", 		a.getNotes());

        a = assertDoesNotThrow(() -> new Ticket(TicketType.REQUEST, "subject", "brandon", Category.INQUIRY,
                Priority.URGENT, "note"));
        assertEquals("Request", a.getTicketTypeString());
        assertEquals(TicketType.REQUEST, a.getTicketType());
        assertEquals("subject", a.getSubject());
        assertEquals("brandon", a.getCaller());
        assertEquals("Inquiry", a.getCategory());
        assertEquals("Urgent", 	a.getPriority());
        assertEquals("note", 	a.getNotes());

        a = assertDoesNotThrow(() -> new Ticket(TicketType.INCIDENT, "subject", "brandon", Category.DATABASE,
                Priority.MEDIUM, "note"));
        assertEquals("Incident", 	a.getTicketTypeString());
        assertEquals(TicketType.INCIDENT, a.getTicketType());
        assertEquals("subject", 	a.getSubject());
        assertEquals("brandon", 	a.getCaller());
        assertEquals("Database", 	a.getCategory());
        assertEquals("Medium", 		a.getPriority());
        assertEquals("note", 		a.getNotes());

        a = assertDoesNotThrow(() ->  new Ticket(TicketType.REQUEST, "subject", "brandon", Category.NETWORK,
                Priority.LOW, "note"));
        assertEquals("Request", a.getTicketTypeString());
        assertEquals(TicketType.REQUEST, a.getTicketType());
        assertEquals("subject", a.getSubject());
        assertEquals("brandon", a.getCaller());
        assertEquals("Network", a.getCategory());
        assertEquals("Low", 	a.getPriority());
        assertEquals("note", 	a.getNotes());

        a = assertDoesNotThrow(() ->  new Ticket(TicketType.REQUEST, "subject", "brandon", Category.SOFTWARE,
                Priority.LOW, "note"));
        assertEquals("Request", 	a.getTicketTypeString());
        assertEquals(TicketType.REQUEST, a.getTicketType());
        assertEquals("subject", 	a.getSubject());
        assertEquals("brandon", 	a.getCaller());
        assertEquals("Software", 	a.getCategory());
        assertEquals("Low", 		a.getPriority());
        assertEquals("note", 		a.getNotes());
    }

    /**
     * Tests the constructor parameterized to receive instantiation parameters
     * from the TicketReader class. This test only considers valid records
     */
    @Test
    void testTicketConstructorWithIOReader() {
        // *id#state#ticket-type#subject#caller#category#priority#owner#code-appropriate-for-state
        ArrayList<String> list = new ArrayList<String>();
        list.add("-note 1");
        list.add("-note 2");
        list.add("-note 3");

        // Case: Request Ticket in New State, all codes should be null

        // Case: Incident Ticket in New State, all codes should be null


        // Case: Request Ticket in the Working State, codes?

        // Case: Incident Ticket in the Working State, codes?




        // Case: Request Ticket in Feedback State, feedback status code: Awaiting caller
        Ticket a = new Ticket(34, "Feedback", "Request", "subject", "Brandon", "Software",
                "Urgent", "jason", "Awaiting Caller", list);
        assertEquals(34, a.getTicketId());
        assertEquals("Feedback", a.getState());
        assertEquals("Request", a.getTicketTypeString());
        assertEquals(TicketType.REQUEST, a.getTicketType());
        assertEquals("subject", a.getSubject());
        assertEquals("Brandon", a.getCaller());
        assertEquals("Software", a.getCategory());
        assertEquals("Medium", a.getPriority());
        assertEquals("jason", a.getOwner());
        assertEquals("Awaiting Caller", a.getFeedbackCode());
        assertEquals("-note 1\n" + "-note 2\n" + "-note 3\n", a.getNotes());

        // Case: Request Ticket in Feedback State, feedback status code: Awaiting Change
        Ticket b = new Ticket(16, "Feedback", "Request", "subject", "Brandon", "Hardware",
                "High", "jason", "Awaiting Change", list);
        assertEquals(34, b.getTicketId());
        assertEquals("Working", b.getState());
        assertEquals("Request", b.getTicketType());
        assertEquals("subject", b.getSubject());
        assertEquals("Brandon", b.getCaller());
        assertEquals("Hardware", b.getCategory());
        assertEquals("High", b.getPriority());
        assertEquals("jason", b.getOwner());
        assertEquals("Awaiting Change", b.getFeedbackCode());
        assertEquals("-note 1\n" + "-note 2\n" + "-note 3\n", b.getNotes());

        // Case: Request Ticket in Feedback State, feedback status code: Awaiting Caller
        Ticket c = new Ticket(16, "Feedback", "Request", "subject", "Brandon", "Inquiry",
                "Medium", "jason", "Awaiting Provider", list);
        assertEquals(34, c.getTicketId());
        assertEquals("Feedback", c.getState());
        assertEquals("Request", c.getTicketType());
        assertEquals("subject", c.getSubject());
        assertEquals("Brandon", c.getCaller());
        assertEquals("Software", c.getCategory());
        assertEquals("Medium", c.getPriority());
        assertEquals("jason", c.getOwner());
        assertEquals("Awaiting Provider", c.getFeedbackCode());
        assertEquals("-note 1\n" + "-note 2\n" + "-note 3\n", c.getNotes());


        // Case: Request Ticket in Resolution State, resolution code: Not Completed

        // Case: Request Ticket in Resolution State, resolution code: Completed

        // Case: Request Ticket in Resolution State, resolution code: Caller Closed


        // Case: Incident Ticket in Resolution State, resolution code: Solved

        // Case: Incident Ticket in Resolution State, resolution code: Not Solved

        // Case: Incident Ticket in Resolution State, resolution code: Workaround

        // Case: Incident Ticket in Resolution State, resolution code: Caller Closed


        // Case: Incident Ticket in Cancelled State, cancellation code: Duplicate

        // Case: Incident Ticket in Cancelled State, cancellation code: Inappropriate


        // Case: Request Ticket in Cancelled State, cancellation code: Inappropriate

        // Case: Request Ticket in Cancelled State, cancellation code: Duplicate


        // Case: Incident Ticket in Closed State, code?

        // Case: Request Ticket in Closed State, code?

    }

    /**
     * Tests the setCounter() method correctly sets the counter at under various nces
     * and before/during/after a range of operations.
     */
    @Test
    void testSetCounter() {
        // Case: Create first ticket from GUI constructor, counter = 2

        // Case: Create first Ticket from IOReader constructor, counter = 2
        fail();
    }

    @Test
    void testSetCode() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdateState() {
        fail("Not yet implemented");
    }

    @Test
    void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    void testToString() {
        Ticket a = new Ticket(TicketType.REQUEST, "subject", "brandon", Category.NETWORK,
                Priority.LOW, "note");
        String b = "";
        String c = a.toString();
        assertEquals(b, c);
    }


    @Test
    void testEqualsObject() {
        Ticket a = new Ticket(TicketType.REQUEST, "subject", "brandon", Category.NETWORK,
                Priority.LOW, "note");
        Ticket b = new Ticket(TicketType.REQUEST, "subject", "brandon", Category.NETWORK,
                Priority.LOW, "note");
        Ticket c = new Ticket(TicketType.REQUEST, "different", "brandon", Category.NETWORK,
                Priority.LOW, "note");

        // Test direct comparison, should return true
        assertTrue(a.equals(a));
        assertTrue(a.equals(b));
        assertTrue(a.equals(b));

        // Test commutability
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // Test direct comparison, should return false
        assertFalse(a.equals(c));
        assertFalse(c.equals(a));
    }

    /**
     * Tests the overriden hashCode() for functionality.
     */
    @Test void testHashCode() {
        Ticket a = new Ticket(TicketType.REQUEST, "subject", "brandon", Category.NETWORK,
                Priority.LOW, "note");
        Ticket b = new Ticket(TicketType.REQUEST, "subject", "brandon", Category.NETWORK,
                Priority.LOW, "note");
        assertEquals(a, b);
    }

}
