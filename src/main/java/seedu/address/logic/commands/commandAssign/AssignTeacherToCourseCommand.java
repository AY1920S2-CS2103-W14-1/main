package seedu.address.logic.commands.commandAssign;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHERID;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import java.util.Set;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.person.ID;

/** This class will be in charge of assigning stuff (e.g students, staff, etc) to a course. */
public class AssignTeacherToCourseCommand extends AssignCommandBase {

    public static final String MESSAGE_INVALID_COURSE_ID = "There is no such course that with ID";
    public static final String MESSAGE_INVALID_STAFF_ID = "There is no such staff that with ID";
    public static final String MESSAGE_COURSE_ALREADY_CONTAINS_TEACHER = "Course already has that teacher assigned to it!";
    public static final String MESSAGE_TEACHER_ALREADY_TEACHES_COURSE = "Teacher is assigned to the course already!";

    public static final String MESSAGE_SUCCESS = "Successfully assigned staff %s(%s) to course %s(%s)";

    private final AssignDescriptor assignDescriptor;

    public AssignTeacherToCourseCommand(AssignDescriptor assignDescriptor) {
        requireNonNull(assignDescriptor);

        this.assignDescriptor = assignDescriptor;
    }

    public static boolean isValidDescriptor(AssignDescriptor assignDescriptor) {
        Set<Prefix> allAssignEntities = assignDescriptor.getAllAssignKeys();
        return allAssignEntities.contains(PREFIX_COURSEID) && allAssignEntities.contains(PREFIX_TEACHERID);
    }

    @Override
    protected CommandResult executeUndoableCommand(Model model) throws CommandException {
        ID courseID = this.assignDescriptor.getAssignID(PREFIX_COURSEID);
        ID teacherID = this.assignDescriptor.getAssignID(PREFIX_TEACHERID);

        boolean courseExists = model.hasCourse(courseID);
        boolean staffExists = model.hasStaff(teacherID);

        if (!courseExists) {
            throw new CommandException(MESSAGE_INVALID_COURSE_ID);
        } else if (!staffExists) {
            throw new CommandException(MESSAGE_INVALID_STAFF_ID);
        } else {
            model.assignTeacherToCourse(teacherID, courseID);

            Course foundCourse = model.getCourse(courseID);
            Staff foundStaff = model.getStaff(teacherID);

            boolean assignedCourseContainsTeacher = foundCourse.containsStaff(teacherID);
            boolean assigningStudentContainsCourse = foundStaff.containsCourse(courseID);

            if(assignedCourseContainsTeacher) {
                throw new CommandException(MESSAGE_COURSE_ALREADY_CONTAINS_TEACHER);
            } else if (assigningStudentContainsCourse) {
                throw new CommandException(MESSAGE_TEACHER_ALREADY_TEACHES_COURSE);
            } else {
                model.assignTeacherToCourse(teacherID, courseID);
                return new CommandResult(String.format(MESSAGE_SUCCESS,
                        foundStaff.getName().toString(), teacherID.value,
                        foundCourse.getName().toString(), courseID.value));
            }
        }
    }

    @Override
    protected void generateOppositeCommand() throws CommandException {

    }
}
