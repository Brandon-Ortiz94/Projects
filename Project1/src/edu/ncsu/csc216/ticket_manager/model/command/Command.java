package edu.ncsu.csc216.ticket_manager.model.command;

/**
 * Encapsulates the information about a user command that would lead to a transition.
 *
 * The information that constructs this concrete class is provided by the GUI.
 *
 * A Command is constructed that contains a CommandValue, an
 * @author brandonortiz
 */
public class Command {

    /**
     * String value representation of the owner assigned to a ticket.
     */
    private String ownerId;

    /**
     * String value representation of option information attached to a ticket.
     */
    private String note;

    /**
     * <pre>
     * String value constant that provides text based description for the StatusCode enumeration
     *
     * This ticket is waiting on information from the caller.
     * </pre>
     */
    private static final String F_CALLER = "Awaiting Caller";

    /**
     * <pre>
     * String value constant that provides text based description for the StatusCode enumeration.
     *
     * This ticket is waiting on an information update.
     * </pre>
     */
    private static final String F_CHANGE = "Awaiting Change";

    /**
     * <pre>
     * String value constant that provides text based description for the StatusCode
     * enumeration.
     *
     * This ticket is waiting on information from a 3rd party provider.
     * </pre>
     */
    private static final String F_PROVIDER = "Awaiting Provider";

    /**
     * <pre>
     * String value constant that provides text based description for the ResolutionCode
     * enumeration.
     *
     * This ticket has been marked as resolved and should transition to the resolved state.
     * </pre>
     */
    private static final String RC_COMPLETED = "Completed";

    /**
     * <pre>
     * String value constant that provides text based description for the ResolutionCode
     * enumeration.
     *
     * This ticket shold return to the working state.
     * </pre>
     */
    private static final String RC_NOT_COMPLETED = "Not Completed";

    /**
     * <pre>
     * String value constant that provides text based description for the ResolutionCode
     * enumeration for a ticket that has been solved.
     *
     * This ticket should transition to closed state.
     * </pre>
     */
    private static final String RC_SOLVED = "Solved";

    /**
     * <pre>
     * String value constant that provides text based description for the ResolutionCode
     * enumeration.
     * </pre>
     */
    private static final String RC_WORKAROUND = "Workaround";

    /**
     * <pre>
     * String value constant that provides text based description for the ResolutionCode
     * enumeration.
     *
     * This ticket should return to the working state.
     * </pre>
     */
    private static final String RC_NOT_SOLVED = "Not Solved";

    /**
     * <pre>
     * String value constant that provides text based description for the ResolutionCode
     * enumeration for a ticket in the resolved state.
     *
     * This ticket has been closed by the caller and should be moved to the closed state.
     * </pre>
     */
    private static final String RC_CALLER_CLOSED = "Caller Closed";

    /**
     * <pre>
     * String value constant that provides text based description for the CancellationCode
     * enumeration for a ticket that has been reported as a duplicate.
     *
     * This code is required for a ticket to transition to the cancellation state.
     * </pre>
     */
    private static final String CC_DUPLICATE = "Duplicate";

    /**
     * <pre>
     * String value constant that provides text based description for the CancellationCode
     * enumeration for a ticket that has been reported as inappropriate.
     *
     * This code is required for a ticket to transition to the cancelled state.
     * </pre>
     */
    private static final String CC_INAPPROPRIATE = "Inapropriate";





    /**
     * <pre>
     * Represents one of the six possible commands that a user can make for
     * the Ticket Manager FSM.
     *
     * A CommandValue represents the general state transition that is requested.
     * A CommandValue is provided from the GUI once an update to a ticket is provided.
     *
     * CommandValues represent actions on a ticket that can be:
     * 	1. {@link #PROCESS}
     * 		{@literal Process} a new ticket and assign an owner.
     * 	2. {@link #RESOLVE}
     * 		Mark a ticket as resolved and ready for feedback or provide feedback to a
     * 		recently resolved ticket.
     * 	3. {@link #CONFIRM}
     * 		Provide final resolution on a ticket that has received feedback
     * 		so that it can be closed.
     * 	4. {@link #REOPEN}
     * 		Move a closed ticket back to working status.
     * 	5. {@link #CANCEL}
     * 		Cancel a duplicate or inappropriate ticket.
     * </pre>
     */
    public enum CommandValue {
        /** Process a new ticket and assign an owner. */
        PROCESS,

        /**
         * A ticket that was recently worked on is ready for feedback or provide feedback
         * to a ticket waiting on it.
         */
        FEEDBACK,

        /** A ticket that was worked on was resolved by the IT team or ready for final resolution. */
        RESOLVE,

        /**
         * A ticket's problem set has been been fixed, feedback is complete, and is ready for
         * final confirmation.
         */
        CONFIRM,

        /** A closed tickets requires additional work. */
        REOPEN,

        /** A ticket needs to be cancelled because it's a duplicate or inappropriate. */
        CANCEL }

    /**
     * Private field that references the set command.
     * @see CommandValue
     */
    private CommandValue commandValue;





    /**
     * <pre>
     * Enumerator that represents the three possible feedback codes.
     *
     * Feedback codes are required to mark a ticket as resolved. Resolved tickets:
     *
     * 	1. {@link #AWAITING_CALLER}
     * 	2. {@link #AWAITING_CHANGE}
     * 	3. {@link #AWAITING_PROVIDER}
     * </pre>
     */
    public enum FeedbackCode {
        /** This ticket is waiting on information from the caller. */
        AWAITING_CALLER,
        /** This ticket is waiting on system change. */
        AWAITING_CHANGE,
        /** This ticket is waiting on information from a 3rd party provider. */
        AWAITING_PROVIDER }

    /**
     * Private field of enum type FeedbackCode that references one of three possible feedback codes.
     * @see FeedbackCode
     */

    private FeedbackCode feedbackCode;

    /**
     * Standard getter method to retrieve the feedback status update.
     * @return returns a {@link FeedbackCode} enumeraion value that can be: <br>
     * 	{@link FeedbackCode#AWAITING_CALLER}, {@link FeedbackCode#AWAITING_CHANGE}, {@link FeedbackCode#AWAITING_PROVIDER}
     * @see FeedbackCode for the types of feedback codes and their context
     */
    public FeedbackCode getFeedbackCode() {
        return feedbackCode;
    }



    /**
     * <pre>
     * Enumerator that represents the six possible resolution codes.
     *
     * Resolution codes are required to:
     *
     * 	1. Confirm the resolution of a ticket and mark it as closed.
     *
     * 	2. Return a ticket to the working group because it requires additional work.
     *
     * 	3. Mark a ticket that was being worked on as resolved.
     * </pre>
     */
    public enum ResolutionCode {
        /**
         * This ticket has been completed, feedback has been provided and it is ready for
         * final confirmation. This ticket should transition to closed.
         */
        COMPLETED,

        /**
         * This ticket was marked as resolved but is not complete. This ticket should return
         * to the working status.
         */
        NOT_COMPLETED,

        /**
         * This ticket was recently worked on and it's problem set has been solved by
         * an IT operator.
         */
        SOLVED,

        /**
         * This ticket was recently worked on and it's problem set was resolved by an IT
         * operator. The specific solution was to implement a workaround.
         */
        WORKAROUND,

        /**
         * This ticket was recently worked on and marked as resolved but the problem remains
         * and is not actually solved.
         */
        NOT_SOLVED,

        /**
         * The ticket's caller has closed the ticket.
         */
        CALLER_CLOSED }

    /**
     * Private field of enum type ResolutionCode that references one of six possible resolution codes.
     * @see ResolutionCode
     */
    private ResolutionCode resolutionCode;

    /**
     * Standard getter method to retrieve the enum value of type {@link ResolutionCode} required
     * for {@link CommandValue#RESOLVE}
     * <br>
     * @return returns the {@link ResolutionCode} enumeration value that can be: <br>
     * 	{@link ResolutionCode#SOLVED}, {@link ResolutionCode#COMPLETED}, {@link ResolutionCode#WORKAROUND},
     * 	{@link ResolutionCode#CALLER_CLOSED}, {@link ResolutionCode#NOT_SOLVED}, {@link ResolutionCode#NOT_SOLVED}
     * @see ResolutionCode for the types of resolution codes and their context
     */
    public ResolutionCode getResolutionCode() {
        return resolutionCode;
    }



    /**
     * <pre>
     * Enumerator that represents the two possible cancellation codes.
     *
     * Cancellation codes are required to change any ticket to Cancelled.
     * </pre>
     */
    public enum CancellationCode { DUPLICATE, INAPPROPRIATE }

    /**
     * Private field that references one of two possible cancellation codes.
     */
    private CancellationCode cancellationCode;

    /**
     * Standard getter method to retrieve a cancellation request.
     * @return returns the enum{@code CancellationCode>} that can be:<br>
     * 	{@link CancellationCode#INAPPROPRIATE} or {@link CancellationCode#DUPLICATE}
     * @see CancellationCode for the types of cancellation codes and their context
     */
    public CancellationCode getCancellationCode() {
        return cancellationCode;
    }

    /**
     * Instance variable to hold the String value definition of the assigned code.
     */
    private String codeString;

    /**
     * Standard getter method to retrieve the assigned code value.
     * @return returns the String value definition of the assigned code.
     */
    public String getCodeString() {
        return codeString;
    }



    /**
     * <pre>
     * Fully parameterized constructor for a Command object. Not all values require a non-null
     * value for instantiation depending on the CommandValue. Any required fields that are required
     * are instantiated first before attempting to set a respective field.
     *
     * Any non-applicable fields must be passed as a null or empty string.
     *
     * Any unneeded command values are passed as null and ignored by the system.
     * </pre>
     * @param command
     * 		the Command that will be passed to the {@link Ticket} FSM state manager.
     * 		See also {@link CommandValue} for command types and their context.
     *
     * @param ownerId
     * 		the owner assigned to a ticket. Not required for instantiation but
     * 		is required for any {@link Ticket} that is being moved to
     * 		the working category.
     * 		A PROCESS command must have an ownerId.
     * 		See also {@link CommandValue#PROCESS} for more information.
     *
     * @param feedbackCode
     * 		the feedback code for any ticket that requires feedback. Feedback
     * 		may be in the form of additional information required from
     * 		a caller, system updates controlled by IT, or updates from a
     * 		3rd party system provider.
     * 		A FEEDBACK command must have a feedbackCode to update the ticket.
     * 		See also {@link CommandValue#FEEDBACK} for more information.
     *
     * @param resolutionCode
     * 		the information provided from a stakeholder that indicates
     * 		a ticket's problem set has been recorded.
     * 		A RESOLVE command must have a resolutionCode to update the system.
     * 		See also {@link CommandValue#RESOLVE} for more information.
     *
     * @param cancellationCode
     * 		code for either a duplicate or inappropriate ticket. This code
     * 		is required for any ticket that needs to be cancelled.
     * 		A CANCEL command must have a cancellationCode to update the system.
     * 		See also {@link CommandValue#CANCEL} for more information.
     *
     * @param note
     * 		optional information that can be included with any Tickets.
     * 		A note value may
     *
     * @throws IllegalArgumentException
     * 	for any of the following inputs:
     * 	1. A null {@code CommandValue}, Command must have a value
     * 	2. If {@code CommandValue} is {@code PROCESS}, {@link ownerId} cannot be null
     * 	3. If {@code CommandValue} is {@code FEEDBACK}, {@link FeedbackCode} cannot be null
     * 	4. If {@code CommandValue} is {@code RESOLVE}, {@link ResolutionCode} cannot be null
     * 	5. If {@code CommandValue} is {@code CANCEL}, {@link CancellationCode} cannot be null
     * 	6. If a command is passed with an empty or null String.
     */
    public Command(CommandValue command, String ownerId, FeedbackCode feedbackCode,
                   ResolutionCode resolutionCode, CancellationCode cancellationCode, String note) {

        setNote(note);
        setCommand(command);
        setOwnerId(ownerId);

        setFeedbackCode(feedbackCode);
        setResolutionCode(resolutionCode);
        setCancellationCode(cancellationCode);

    }

    /**
     * For use by constructor. If passed null, ignores the value.
     * Otherwise, sets the instance variable.
     * @param cancellationCode the enum CancellationCode to set the instance variable to.
     */
    private void setCancellationCode(CancellationCode cancellationCode) {
        if (cancellationCode == null) {
            return;
        } else { this.cancellationCode = cancellationCode; }

        if (cancellationCode == CancellationCode.DUPLICATE) { this.codeString = CC_DUPLICATE; }
        if (cancellationCode == CancellationCode.INAPPROPRIATE) { this.codeString = CC_INAPPROPRIATE; }
    }

    /**
     * For use by constructor. If passed null, ignores the value.
     * Otherwise, sets the instance variable.
     * @param resolutionCode the enum ResolutionCode to set the instance variable to.
     */
    private void setResolutionCode(ResolutionCode resolutionCode) {
        if (resolutionCode == null) {
            return;
        } else { this.resolutionCode = resolutionCode; }

        if (resolutionCode == ResolutionCode.COMPLETED) 	{ this.codeString = RC_COMPLETED; }
        if (resolutionCode == ResolutionCode.NOT_COMPLETED) { this.codeString = RC_NOT_COMPLETED; }
        if (resolutionCode == ResolutionCode.SOLVED) 		{ this.codeString = RC_SOLVED; }
        if (resolutionCode == ResolutionCode.NOT_SOLVED) 	{ this.codeString = RC_NOT_SOLVED; }
        if (resolutionCode == ResolutionCode.WORKAROUND) 	{ this.codeString = RC_WORKAROUND; }
        if (resolutionCode == ResolutionCode.CALLER_CLOSED) { this.codeString = RC_CALLER_CLOSED; }
    }

    /**
     * For use by constructor. If passed null, ignores the value.
     * Otherwise, sets the instance variable.
     * @param feedbackCode the enum FeedbackCode to set the instance variable to.
     */
    private void setFeedbackCode(FeedbackCode feedbackCode) {
        if (feedbackCode == null) {
            return;
        } else { this.feedbackCode = feedbackCode; }

        if (feedbackCode == FeedbackCode.AWAITING_CALLER) { this.codeString = F_CALLER; }
        if (feedbackCode == FeedbackCode.AWAITING_CHANGE) { this.codeString = F_CHANGE; }
        if (feedbackCode == FeedbackCode.AWAITING_PROVIDER) {this.codeString = F_PROVIDER; }
    }

    /**
     * For use by constructor. If the passed command is PROCESS, sets the ownerId to the
     * specified String value representation of the user's name or id. Otherwise, ignores
     * the parameter.
     * @param ownerId the string value representation of the owner name assigned to a ticket
     */
    private void setOwnerId(String ownerId) {
        if (ownerId == null) { throw new IllegalArgumentException(); }
        this.ownerId = ownerId;
    }

    /**
     * Sets the {@link command} to the specified CommandValue.
     * @param command the specified command to set
     * @see CommandValue
     */
    private void setCommand(CommandValue command) { this.commandValue = command; }

    /**
     * Sets the {@link note} to a specified String value.
     * @param note the String value to set the note to.
     * @throws IllegalArgumentException
     * 	if the note is null or empty string
     */
    private void setNote(String note) {
        if ("".equals(note) || null == note) {
            throw new IllegalArgumentException("Invalid note");
        }

        this.note = note;

    }

    /**
     * Standard getter method to retrieve the command provided by the GUI.
     * @return returns the enum{@code <CommandValue>} that can be: <br>
     * 	{@link CommandValue#PROCESS}, {@link CommandValue#FEEDBACK}, {@link CommandValue#RESOLVE},
     * 	{@link CommandValue#CONFIRM}, {@link CommandValue#REOPEN}, {@link CommandValue#CANCEL}
     * @see CommandValue for the types of command's that can be given and their context
     */
    public CommandValue getCommand() {
        return commandValue;
    }

    /**
     * Standard getter method to retrieve the owner assigned to a {@link Ticket}
     * @return returns a string value representation of the owner's name or id.
     * @see ownerId
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Standard getter method to retrieve the {@link note} or note updates for a {@link Ticket}
     * @return returns a string value representation of the ticket's note details.
     * @see note
     */
    public String getNote() {
        return note;
    }

}