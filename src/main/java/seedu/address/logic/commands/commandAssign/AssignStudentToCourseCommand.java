package seedu.address.logic.commands.commandAssign;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHERID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandAdd.AddAssignmentCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.person.Courseid;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Studentid;
import seedu.address.model.tag.Tag;

/** This class will be in charge of assigning stuff (e.g students, teacher, etc) to a course. */
public class AssignStudentToCourseCommand extends AssignCommandBase {

    public static final String MESSAGE_INVALID_COURSE_ID = "There is no such course that with ID";
    public static final String MESSAGE_INVALID_STUDENT_ID = "There is no such student that with ID";
    public static final String MESSAGE_SUCCESS = "Successfully added student %s(%s) to course %s(%s)";

    private final AssignDescriptor assignDescriptor;
    private Set<Tag> ArrayList;

    public AssignStudentToCourseCommand(AssignDescriptor assignDescriptor) {
        requireNonNull(assignDescriptor);

        this.assignDescriptor = assignDescriptor;
    }

    public static boolean isValidDescriptor(AssignDescriptor assignDescriptor) {
        Set<Prefix> allAssignEntities = assignDescriptor.getAllAssignKeys();
        return allAssignEntities.contains(PREFIX_COURSEID) && allAssignEntities.contains(PREFIX_STUDENTID);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, ParseException {
        String courseidString = this.assignDescriptor.getAssignID(PREFIX_COURSEID).value;
        String studentidString = this.assignDescriptor.getAssignID(PREFIX_STUDENTID).value;
        String courseName = "";
        String studentName = "";

        boolean courseExists = false;
        boolean studentExists = false;
        Course foundCourse = null;
        Student foundStudent = null;

        for (Course course : model.getFilteredCourseList()) {
            if (course.getId().value.equals(courseidString)) {
                courseName = course.getName().toString();
                courseExists = true;
                foundCourse = course;
                break;
            }
        }

        for (Student student : model.getFilteredStudentList()) {
            if (student.getID().value.equals(studentidString)) {
                studentName = student.getName().toString();
                studentExists = true;
                foundStudent = student;
                break;
            }
        }

        if (!courseExists) {
            throw new CommandException(MESSAGE_INVALID_COURSE_ID);
        } else if (!studentExists) {
            throw new CommandException(MESSAGE_INVALID_STUDENT_ID);
        } else {
            Courseid courseid = ParserUtil.parseCourseid(courseidString);
            Studentid studentid = ParserUtil.parseStudentid(studentidString);
            foundCourse.addStudent(studentid);
            foundStudent.addCourse(courseid);
            foundCourse.processAssignedStudents(
                (FilteredList<Student>) model.getFilteredStudentList());
            foundStudent.processAssignedCourses(
                (FilteredList<Course>) model.getFilteredCourseList());
            model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

            return new CommandResult(String.format(MESSAGE_SUCCESS, studentName, studentidString, courseName, courseidString));
        }

    }
}
