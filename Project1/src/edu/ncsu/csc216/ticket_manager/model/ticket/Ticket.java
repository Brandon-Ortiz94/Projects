package edu.ncsu.csc216.ticket_manager.model.ticket;

import java.util.ArrayList;

import edu.ncsu.csc216.ticket_manager.model.command.Command;

/**
 * This concrete class contaisn the State Pattern implementation of the Ticket Manager FSM.
 * It is responsible for providing the context for all FSM specific interactions.
 *
 * Ticket objects keeps track of all ticket information including the current state.
 * The state is updated when a Command encapsulating a transition is given to the Ticket.
 *
 * This class encapsulates six concrete *State classes and two enumerations.
 * @author brandonortiz
 */
public class Ticket implements TicketState {

    /**
     * <pre>
     * Enumeration representing the five types of possible ticket categories.
     * 	1. {@link #INQUIRY}
     * 	2. {@link #SOFTWARE}
     * 	3. {@link #HARDWARE}
     * 	4. {@link #NETWORK}
     * 	5. {@link #DATABASE}
     * </pre>
     */
    public enum Category { INQUIRY, SOFTWARE, HARDWARE, NETWORK, DATABASE }

    /**
     * <pre>
     * Enumeration representing the four priories assignable to a Ticket.
     * 	1. {@link #URGENT}
     * 	2. {@link #HIGH}
     * 	3. {@link #MEDIUM}
     * 	4. {@link #LOW}
     * </pre>
     */
    public enum Priority {
        /** A ticket with the urgent priority. */
        URGENT,
        /** A high priority ticket. */
        HIGH,
        /** A medium priority ticket. */
        MEDIUM,
        /** A medium priority ticket. */
        LOW }

    /**
     * <pre>
     * Enumeration represeting the two possible types of Ticket.
     * 	1. REQUEST
     * 	2. INCIDENT
     * </pre>
     */
    public enum TicketType {
        /** A request for services, implementations, or features. */
        REQUEST,

        /** Incident ticket for an event that has occurrred. */
        INCIDENT
    }


    /**	String value constant that defines one of two types of Tickets. */
    private static final String TT_REQUEST = "Request";

    /**	String value constant that defines one of two types of Tickets. */
    private static final String  TT_INCIDENT = "Incident";

    /**	String value constant that defines one of five categories of tickets. */
    private static final String C_INQUIRY = "Inquiry";

    /**	String value constant that defines one of five categories of tickets. */
    private static final String C_SOFTWARE = "Software";

    /**	String value constant that defines one of five categories of tickets. */
    private static final String C_HARDWARE = "Hardware";

    /**	String value constant that defines one of five categories of tickets. */
    private static final String C_NETWORK = "Network";

    /**	String value constant that defines one of five categories of tickets. */
    private static final String C_DATABASE = "Database";

    /**	String value constant that defines one of four possible priorities for a Ticket. */
    private static final String P_URGENT = "Urgent";

    /**	String value constant that defines one of four possible priorities for a Ticket. */
    private static final String P_HIGH = "High";

    /**	String value constant that defines one of four possible priorities for a Ticket. */
    private static final String P_MEDIUM = "Medium";

    /**	String value constant that defines one of four possible priorities for a Ticket. */
    private static final String P_LOW = "Low";

    /**	String value constant that defines one of four possible priorities for a Ticket. */
    public static final String NEW_NAME = "New";

    /**	String value constant that provides a text based descirption
     * for a ticket in the working state.
     */
    public static final String WORKING_NAME = "Working";

    /**	String value constant that provides a text based descirption
     * for a ticket in the feedback state.
     */
    public static final String FEEDBACK_NAME = "Feedback";

    /**	String value constant that provides a text based descirption
     * for a ticket in the resolved state.
     */
    public static final String RESOLVED_NAME = "Resolved";

    /**	String value constant that provides a text based descirption
     * for a ticket in the closed state.
     */
    public static final String CLOSED_NAME = "Closed";

    /**	String value constant that provides a text based descirption
     * for a ticket in the cancelled state.
     */
    public static final String CANCELED_NAME = "Canceled";


    /**
     * Static class variable that is used to maintain a running count
     * of the number of instance objects. This variable is used
     * in conjunction with ticketCounter() to generate unique ticketIds.
     */
    private static int currCount = 1;

    /**
     * A unique value assigned to each ticket to distinguish each ticket.
     * A ticket ID is always one more than the most recently created ticket.
     * Ticket IDs are based solely on the number on pre-existing tickets.
     */
    private int ticketId;

    /**
     * Standard getter method to return the String value representation of the ticket owner.
     * @return returns a string value representation of the owner's name or id
     */
    public int getTicketId() { return this.ticketId; }

    /**
     * Instance variable that holds the string value definition for the current FSM state
     * of the Ticket.
     */
    private String state;

    /**
     * Standard getter to return the String value definition of the Ticket's state.
     * @return returns a String value representing the state.
     */
    public String getState() { return state; }

    /**
     * Instance variable that holds the String value definition for the assigned
     * priority of a ticket.
     */
    private String priority;

    /**
     * Standard getter to return the String value definition of the Ticket's priority.
     * @return returns the string value of the ticket's priority
     */
    public String getPriority() { return priority; }

    /**
     * Instance variable that holds the String value definition for the assigned
     * category of the ticket.
     */
    private String category;

    /** Returns a string value definition of the category based on the assigned Category enum
     * @return returns a string value representation of the category
     */
    public String getCategory() { return category; }

    /**
     * Instance variable that holds the String Value definition for the ticket's type.
     */
    private String ticketTypeString;

    /** Returns a string value definition of the ticket type based on the enum value assigned.
     * @return returns string value representation of the ticket type.
     * @see TicketType
     */
    public String getTicketTypeString() { return ticketTypeString; }

    /**
     * Instance variable to hold the enumeration TicketType.
     */
    private TicketType ticketType;

    /**
     * Returns the ticket's type of type TicketType.
     * @return returns the enumeration TicketType of the instance's type
     */
    public TicketType getTicketType() { return ticketType; }

    /**
     * Instance variable that holds the String value definition for the subject
     * information assigned to the Ticket when it was made.
     */
    private String subject;

    /**
     * Standard getter method to return the String value representation of the subject.
     * @return the the String value representation of the ticket's subject
     */
    public String getSubject() { return subject; }

    /**
     * Instance variable that holds the String value definition for the caller's name.
     */
    private String caller;

    /**
     * Standard getter method to return the String value of the caller.
     * @return returns the String value of the caller's name or user id.
     */
    public String getCaller() { return caller; }

    /**
     * Instance variable that holds the String value definition of the assigned owner of the ticker.
     */
    private String owner;

    /**
     * Standard getter to return the owner assigned to a ticket.
     * @return the owner
     */
    public String getOwner() { return owner; }

    /**
     * Instance class variables that holds the reference to the ArrayList of notes.
     * Each object in the array represents a single line of note information
     */
    private String notes;
    /**
     * Standard getter to set the ticket notes to.
     * @return the notes to set the field to.
     */
    public String getNotes() { return notes; }

    /**
     * Instance class variable that references the FeedbackCode if the ticket is in
     * the Feedback State. Null otherwise.
     */
    private String feedbackCode;

    /**
     * Standard getter to return the String value definition of the Ticket's feedback code
     * if it has one.
     * @return returns a String value definition of the ticket's feedback code if defined,
     * returns null otherwise.
     */
    public String getFeedbackCode() { return feedbackCode; }

    /**
     * Instance class variable that references the ResolutionCode if the ticket is in the
     * Resolved State. Null otherwise.
     */
    private String resolutionCode;

    /**
     * Standard getter to return the String value definition of the Ticket's resolution code if
     * defined.
     * @return returns the string value definition of the Ticket's cancellation code if defined,
     * returns null otherwise.
     */
    public String getResolutionCode() { return resolutionCode; }

    /**
     * Instance class variable that references the CancellationCode is the ticket is
     * in the Cancelled State. Null otherwise.
     */
    private String cancellationCode;

    /**
     * Gets the String value cancellation code.
     * @return returns the String value definition of the Ticket's cancellation code if defined,
     * returns null otherwise.
     */
    public String getCancellationCode() { return cancellationCode; }






    /**
     * <pre>
     * Fully parameterized constructor that is intended to create a Ticket object
     * from information provided by the GUI.
     *
     * This constructor automatically sets the ticketId to the value current in {@link #currCount}
     * and then increments the value. This constructor is ONLY used to instantiate new tickets.
     *
     * All other values are set to their passed parameter values.
     *
     * An IllegalArgumentException is thrown if any of the parameters are null or empty Strings.
     * </pre>
     * @param ticketType
     * 		the type of ticket
     * @param subject
     * 		the subejct of the ticket
     * @param caller
     * 		the caller who created the ticket
     * @param category
     * 		the category of the ticket
     * @param priority
     * 		the ticket's priority
     * @param note
     * 		the note data provided for the ticket
     * @throws IllegalArgumentException
     * 		for any null or empty String values.
     */
    public Ticket(TicketType ticketType, String subject, String caller,
                  Category category, Priority priority, String note) {
        setTicketId();
        setTicketType(ticketType);
        setSubject(subject);
        setCaller(caller);
        setCategory(category);
        setPriority(priority);
        setNote(note);
    }

    /**
     * Responsible for setting the ticketID and maintaining the counter on each instantiation
     * of an object. This reference variable holds the current count of the number of instance objects.
     * It is used in conjunction with ticketCounter to assign id's.
     */
    private void setTicketId() {
        this.ticketId = currCount;
        incrementCounter();
    }

    /**
     * Utilized by constructor to maintain {@link #currCount} by incremementing it by +1
     * each time a new ticket is instantiated. The new counter value represents the current
     * number of instance objects + 1.
     */
    private static void incrementCounter() {
        currCount++;
    }

    /**
     * Utilized by constructor to assign the ticket type to the instance variable.
     * @param ticketType the enum TicketType of the object
     */
    private void setTicketType(TicketType ticketType) {
        if (ticketType == null) 				{ throw new IllegalArgumentException(); }
        if (ticketType == TicketType.INCIDENT) 	{
            this.ticketTypeString = TT_INCIDENT;
            this.ticketType = TicketType.INCIDENT;
        }

        if (ticketType == TicketType.REQUEST) 	{
            this.ticketTypeString = TT_REQUEST;
            this.ticketType = TicketType.REQUEST;
        }
    }

    /** Setter methods that sets the subject of the ticket.
     * @param subject the subject to set
     */
    private void setSubject(String subject) {
        if (subject == null) 	{ throw new IllegalArgumentException(); }
        else 					{ this.subject = subject; }
    }

    /**
     * Getter method that sets the caller's name from parameters passed to the
     * constructor.
     * @param caller the String value of the caller's name
     * @throws IllegalArgumentException if the value is null or empty String
     */
    private void setCaller(String caller) {
        if ("".equals(caller) || caller == null) {
            throw new IllegalArgumentException();
        }
        this.caller = caller;
    }

    /**
     * Setter method that assigns a string value definition to the instance {@link #category}
     * based on the Category enumeration received by constructor.
     * @param category the enum Category to set the category to.
     */
    private void setCategory(Category category) {
        if (category == Category.DATABASE) 	{ this.category = C_DATABASE; }

        if (category == Category.HARDWARE) 	{ this.category = C_HARDWARE; }

        if (category == Category.INQUIRY) 	{ this.category = C_INQUIRY; }

        if (category == Category.NETWORK) 	{ this.category = C_NETWORK; }

        if (category == Category.SOFTWARE) 	{ this.category = C_SOFTWARE; }
    }

    /**
     * Sets the priority based on the value received by constructor that receives input
     * from the GUI.
     * @param priority the priority of the ticket
     */
    private void setPriority(Priority priority) {
        if (priority == Priority.URGENT) 	{ this.priority = P_URGENT; }
        if (priority == Priority.HIGH) 		{ this.priority = P_HIGH; }
        if (priority == Priority.MEDIUM) 	{ this.priority = P_MEDIUM; }
        if (priority == Priority.LOW) 		{ this.priority = P_LOW; }
    }

    /**
     * The Ticket note information to set field to.
     * @param notes the notes to set
     */
    private void setNote(String notes) {
        if (notes == null) { throw new IllegalArgumentException(); }
        this.notes = notes;
    }

    /**
     * Constructor for use when instantiating from the TicketReader class. When instantiating
     * from a class, the first ID represents the state of the counter. Because saved lists can be
     * edited, the ID value should incremement with ticket objects, but may do so unpredictably
     * as some tickets in the value line may have been removed.
     *
     * An IllegalArgumentException is thrown if any of the parameters are null or empty Strings.
     *
     * Values are set directly from this constructor utilizing checks on string literal values.
     *
     * Tickets read into this constructor must be formatted accordingly:
     * *id#state#ticket-type#subject#caller#category#priority#owner#code-appropriate-for-state
     * -note
     * -note
     * -note
     *
     *
     * @param id
     * 		the int value ticketId of a ticket. Also represents the counter value. Must be an int
     * 		value greater than or equal to 0.
     * @param state
     * 		the string value representation of the FSM state of a ticket. Must have a state of
     * 		"New", "Working",“Feedback”, “Resolved”, “Closed”, or “Canceled”.
     * @param ticketType
     * 		the string value ticketType of a ticket. Must be "Request" or "Incident"
     * @param subject
     * 		the string value subject information for a ticket. Must be a String of at least
     * 		length 1.
     * @param caller
     * 		the string value representation of the callers name. Must be a String of at least
     * 		length 1.
     * @param category
     * 		the string value definition of the ticket's category. “Inquiry”, “Software”,
     * 		“Hardware”, “Network”, or “Database”.
     * @param priority
     * 		the string value representation of the ticket's priority. Must have a priority of
     * 		“Urgent”, “High”, “Medium”, or “Low”.
     * @param owner
     * 		the string value representation of the IT operator assigned. must have an owner
     * 		if the state is “Working”, “Feedback”, “Resolved”, or “Closed”. The ticket may have
     * 		an owner if the in the “Canceled” state.
     * @param code
     * 		the string value representation of code for the last transition.
     * 		<br> If the ticket is a “Request”, the code can only be “Completed”, “Not Completed”, or “Caller Closed”.
     * 		<br> If the ticket is an “Incident”, the code can only be “Solved”, “Workaround”, “Not Solved”, or “Caller Closed”.
     * 		<br> If the ticket is in the Canceled state, must have a code fo "Duplicate" or "Inappropriate".
     * @param notes
     * 		the ArrayList of type String containing a ticket's notes. The notes must:
     * 		<br> Have at least one note line for each ticket.
     * 		<br> The ‘-‘ and ‘*’ characters can appear in a note, but not as the first character in a new line - that would be considered a new note.
     * 		<br> The note may include new line characters
     */
    public Ticket(int id, String state, String ticketType, String subject, String caller,
                  String category, String priority, String owner, String code, ArrayList<String> notes) {
        setCounter(id);
        setTicketId(id);
        setState(state);
        setTicketType(ticketType);
        setSubject(subject);
        setCaller(caller);
        setCategory(category);
        setPriority(priority);
        setOwner(owner);
        setCode(code);
        setNotes(notes);
    }

    /**
     * Responsible for setting the counter to a specified value. If the current counter
     * is less than or equal to the new value, it will 'reset' the value to the passed value
     * (the counter will now start counting at that new value) and increment the value by 1.
     * <br>
     * Otherwise will set the counter to the new count (for example, counter is 0, and a new
     * list starts with a counter at 60).
     * @param count the number of existing instances
     */
    public static void setCounter(int count) {
        if (count >= currCount) {
            currCount = count;
            incrementCounter();
        }
        currCount = count;
    }

    /**
     * Responsible for setting the ticket id when passed an explicit ticket id by the
     * constructor intended to receive parameters from the IO reader class.
     * @param id the ticket id to set.
     */
    private void setTicketId(int id) {
        if (id == 0) { throw new IllegalArgumentException(); }
        this.ticketId = id;
    }

    /**
     * This method is responsible for setting the state to the value passed to the
     * constructor intended to receive parameters from the IO class.
     * @param state the state of the ticket
     * @throws IllegalArgumentException
     * 		for null or empty strings or if the state is not one of the allowable states:
     * 		"New", "Working", "Feedback", "Resolved", "Closed", "Canceled"
     */
    private void setState(String state) {
        if (state == null) 						{ throw new IllegalArgumentException(); }
        if (state.matches("New|Working|Feedback"
                + "|Resolved|Closed|Canceled")) { this.state = state; }
    }

    /**
     * Sets the ticketType String field to the value received by the constructor designed
     * to receive parameters from the IO class.
     * @param ticketType the type of ticket
     */
    private void setTicketType(String ticketType) {
        if (ticketType == null) 				{ throw new IllegalArgumentException(); }
        if (ticketType.equals(TT_INCIDENT)) 	{ this.ticketTypeString = TT_INCIDENT;

        }
        if (ticketType.equals(TT_REQUEST)) 		{ this.ticketTypeString = TT_REQUEST; }
    }

    /**
     * Sets the category instance variable according to the String value passed to constructor
     * for the IOReader class.
     * @param category the category of the ticket
     * @throws IllegalArgumentException
     * 		if category does not contain "Inquiry", "Software", "Hardware", "Network", or "Database"
     */
    private void setCategory(String category) {
        if (category == null) { throw new IllegalArgumentException(); }
        if (category.equals(C_NETWORK)
                || category.equals(C_INQUIRY)
                || category.equals(C_HARDWARE)
                || category.equals(C_DATABASE)
                || category.equals(C_SOFTWARE)) { this.category = category; }

        else throw new IllegalArgumentException();
    }

    /**
     * Setter method that sets the parameters received from the constructor that is
     * intended to read in parameters from the IOReader class.
     * @param notes the ArrayList of String notes to set the field to
     */
    private void setNotes(ArrayList<String> notes) {
        if (notes == null) 			{ throw new IllegalArgumentException(); }
        for (int i = 0; i < 0; i++) { this.notes += notes.get(i); 			}
    }

    /**
     * Used by constructor when restoring ticket objects from a saved state. Sets either
     * feedbackCode, cancellationCode, or resolutionCode to the appropriate value
     * depending on the information passed to the constructor.
     * @param code the code to set the field to
     */
    private void setCode(String code) {
        if (this.state.equals(FEEDBACK_NAME)) { this.feedbackCode = code; 	}
        if (this.state.equals(CANCELED_NAME)) { this.cancellationCode = code; }
        if (this.state.equals(RESOLVED_NAME) && ticketTypeString.equals(TT_REQUEST))
        { this.resolutionCode = code; 	}
        if (this.state.equals(RESOLVED_NAME) && ticketTypeString.equals(TT_INCIDENT))
        { this.resolutionCode = code; 	}
    }

    /**
     * Setter method that assigns a string value definition to the instance {@code #priority}
     * based on the Priority enumeration received by the constructor.
     * @param priority the enum Priority to set the priority to.
     * @throws IllegalArgumentException
     * 		if priority is null or empty string
     */
    private void setPriority(String priority) {
        if ("".equals(priority) || priority == null) { throw new IllegalArgumentException(); }
        if (priority.equals(P_HIGH)) 	{ this.priority = P_URGENT;	}
        if (priority.equals(P_HIGH)) 	{ this.priority = P_HIGH; 	}
        if (priority.equals(P_MEDIUM)) 	{ this.priority = P_MEDIUM; }
        if (priority.equals(P_LOW)) 	{ this.priority = P_LOW; 	}
    }

    /**
     * The IT officer assigned to work on a ticket.
     * @param owner the owner to set
     */
    private void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Executes a state change based on Command
     * @param command the command instructions to execute
     */
    @Override
    public void updateState(Command command) {
        //TODO Implement updateState() functionality
    }

    /**
     * Executes a command function update triggering the FSM implementation.
     * @param cmd the command to action
     */
    public void update(Command cmd) {
        //TODO Implement update() functionality
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((caller == null) ? 0 : caller.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ticketId;
        result = prime * result + ((ticketTypeString == null) ? 0 : ticketTypeString.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ticket other = (Ticket) obj;
        if (caller == null) {
            if (other.caller != null)
                return false;
        } else if (!caller.equals(other.caller))
            return false;
        if (category == null) {
            if (other.category != null)
                return false;
        } else if (!category.equals(other.category))
            return false;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (ticketId != other.ticketId)
            return false;
        if (ticketTypeString == null) {
            if (other.ticketTypeString != null)
                return false;
        } else if (!ticketTypeString.equals(other.ticketTypeString))
            return false;
        return true;
    }


    /**
     * Override implementation of toString() that provides object string formatting required
     * to translate objects into format compliant text data.
     * #TODO Need to implement proper getCode implementation
     */
    @Override
    public String toString() {
        String value =  "*" + getTicketId() + "#" + getState() + "#" + getTicketTypeString() + "#" + getSubject() +
                getCaller()  + "#" + getCategory() + "#" + getPriority() + "#" + getOwner() + "#" + getNotes().toString();

        return value;

    }


}
