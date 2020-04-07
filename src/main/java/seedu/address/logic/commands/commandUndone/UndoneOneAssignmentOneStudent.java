package seedu.address.logic.commands.commandUndone;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandAssign.AssignDescriptor;
import seedu.address.logic.commands.commandDone.DoneOneAssignmentOneStudent;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.manager.ProgressManager;
import seedu.address.model.Model;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.person.ID;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

public class UndoneOneAssignmentOneStudent extends UndoneCommandBase {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an assignment as done for a student. "
            + "Parameters: "
            + PREFIX_ASSIGNMENTID + "ASSIGNMENTID "
            + PREFIX_STUDENTID + "STUDENTID"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ASSIGNMENTID + "100"
            + PREFIX_STUDENTID + "329";

    public static final String MESSAGE_INVALID_STUDENT_ID = "There is no such Student that with ID";
    public static final String MESSAGE_INVALID_ASSIGNMENT_ID = "There is no such Assignment that with ID";
    public static final String MESSAGE_INVALID_STUDENT_ASSIGNMENT = "Student has not been assigned this assignment currently!";
    public static final String MESSAGE_SUCCESS = "Successfully marked Assignment %s (%s) as UNDONE by Student %s (%s)";

    /*
        AssignDescriptor used as it takes in two prefixes just like assign commands. Essentially performs the same functionality
     **/
    private final AssignDescriptor assignDescriptor;

    public UndoneOneAssignmentOneStudent(AssignDescriptor assignDescriptor) {
        requireNonNull(assignDescriptor);

        this.assignDescriptor = assignDescriptor;
    }

    public static boolean isValidDescriptor(AssignDescriptor assignDescriptor) {
        Set<Prefix> allAssignEntities = assignDescriptor.getAllAssignKeys();
        int numOfKeys = allAssignEntities.size();
        return allAssignEntities.contains(PREFIX_STUDENTID) && allAssignEntities.contains(PREFIX_ASSIGNMENTID) && numOfKeys == 2;
    }


    @Override
    protected CommandResult executeUndoableCommand(Model model) throws CommandException {
        ID studentID = this.assignDescriptor.getAssignID(PREFIX_STUDENTID);
        ID assignmentID = this.assignDescriptor.getAssignID(PREFIX_ASSIGNMENTID);

        boolean studentExists = model.hasStudent(studentID);
        boolean assignmentExists = model.hasAssignment(assignmentID);
        boolean progressExists = model.hasProgress(assignmentID, studentID);

        if (!studentExists) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_ID);
        } else if (!assignmentExists) {
            throw new CommandException(MESSAGE_INVALID_ASSIGNMENT_ID);
        } else if (!progressExists) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_ASSIGNMENT);
        } else {
            Assignment assignment = model.getAssignment(assignmentID);
            Student student = model.getStudent(studentID);

            ProgressManager.markUndoneOneProgressOfOneStudent(assignmentID, studentID);

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    assignment.getName(), assignmentID.value,
                    student.getName(), studentID.value));
        }
    }

    @Override
    protected void generateOppositeCommand() throws CommandException {
        oppositeCommand = new DoneOneAssignmentOneStudent(this.assignDescriptor);
    }
}
