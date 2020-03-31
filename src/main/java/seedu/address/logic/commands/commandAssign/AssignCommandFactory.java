package seedu.address.logic.commands.commandAssign;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.parser.AssignCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.logging.Logger;

import static seedu.address.logic.parser.CliSyntax.*;

public class AssignCommandFactory {
    public static AssignCommandBase getCommand(AssignDescriptor assignDescriptor) throws ParseException {

        final String ASSIGNMENT_FAILURE_MESSAGE =
                "assign command must have valid pair of IDs such as for: " +
                "\n1. Assigning a student to a course " +
                PREFIX_COURSEID + " " + PREFIX_STUDENTID +
                "\n2. Assigning a teacher to a course " +
                PREFIX_COURSEID + " " + PREFIX_TEACHERID +
                "\n3. Assigning an assignment to a course " +
                PREFIX_COURSEID + " " + PREFIX_ASSIGNMENTID;


        Prefix[] prefixes = assignDescriptor.getType();


        AssignCommandBase outputCommand = null;

        if ( (prefixes[0].equals(PREFIX_COURSEID) && prefixes[1].equals(PREFIX_TEACHERID)) || (prefixes[1].equals(PREFIX_COURSEID) && prefixes[0].equals(PREFIX_TEACHERID)) ){
            if (AssignTeacherToCourseCommand.isValidDescriptor(assignDescriptor)) {
                outputCommand = new AssignTeacherToCourseCommand(assignDescriptor);
            }
        }
        else if ( (prefixes[0].equals(PREFIX_COURSEID) && prefixes[1].equals(PREFIX_STUDENTID)) || (prefixes[1].equals(PREFIX_COURSEID) && prefixes[0].equals(PREFIX_STUDENTID)) ){
            if (AssignStudentToCourseCommand.isValidDescriptor(assignDescriptor)) {
                outputCommand = new AssignStudentToCourseCommand(assignDescriptor);
            }
        }
        else if ( (prefixes[0].equals(PREFIX_COURSEID) && prefixes[1].equals(PREFIX_ASSIGNMENTID)) || (prefixes[1].equals(PREFIX_COURSEID) && prefixes[0].equals(PREFIX_ASSIGNMENTID)) ){
            if (AssignAssignmentToCourseCommand.isValidDescriptor(assignDescriptor)) {
                outputCommand = new AssignAssignmentToCourseCommand(assignDescriptor);
            }
        }
        else {
            // Thrown when there is no valid assignment such as Teachers -> Students
            throw new ParseException(ASSIGNMENT_FAILURE_MESSAGE);
        }

        return outputCommand;
    }
}
