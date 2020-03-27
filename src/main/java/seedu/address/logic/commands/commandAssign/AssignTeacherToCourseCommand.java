package seedu.address.logic.commands.commandAssign;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Courseid;
import seedu.address.model.person.ID;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHERID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TEACHERS;

import java.util.List;
import java.util.Set;
import seedu.address.model.person.Studentid;
import seedu.address.model.person.Teacherid;

/** This class will be in charge of assigning stuff (e.g students, teacher, etc) to a course. */
public class AssignTeacherToCourseCommand extends AssignCommandBase {

    public static final String MESSAGE_INVALID_COURSE_ID = "There is no such course that with ID";
    public static final String MESSAGE_INVALID_TEACHER_ID = "There is no such teacher that with ID";
    public static final String MESSAGE_SUCCESS = "Successfully assigned teacher %s(%s) to course %s(%s)";

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
    public CommandResult execute(Model model) throws CommandException, ParseException {
        String courseidString = this.assignDescriptor.getAssignID(PREFIX_COURSEID).value;
        String teacheridString = this.assignDescriptor.getAssignID(PREFIX_TEACHERID).value;
        String courseName = "";
        String teacherName = "";

        boolean courseExists = false;
        boolean teacherExists = false;
        Course foundCourse = null;
        Teacher foundTeacher = null;

        for (Course course : model.getFilteredCourseList()) {
            if (course.getId().value.equals(courseidString)) {
                courseName = course.getName().toString();
                courseExists = true;
                foundCourse = course;
                break;
            }
        }

        for (Teacher teacher : model.getFilteredTeacherList()) {
            if (teacher.getID().value.equals(teacheridString)) {
                teacherName = teacher.getName().toString();
                teacherExists = true;
                foundTeacher = teacher;
                break;
            }
        }

        if (!courseExists) {
            throw new CommandException(MESSAGE_INVALID_COURSE_ID);
        } else if (!teacherExists) {
            throw new CommandException(MESSAGE_INVALID_TEACHER_ID);
        } else {
            Courseid courseid = ParserUtil.parseCourseid(courseidString);
            Teacherid teacherid = ParserUtil.parseTeacherid(teacheridString);
            foundCourse.assignTeacher(teacherid);
            foundTeacher.addCourse(courseid);

            foundCourse.processAssignedTeacher(
                (FilteredList<Teacher>) model.getFilteredTeacherList());
            foundTeacher.processAssignedCourses(
                (FilteredList<Course>) model.getFilteredCourseList());

            model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
            model.updateFilteredTeacherList(PREDICATE_SHOW_ALL_TEACHERS);

            return new CommandResult(String.format(MESSAGE_SUCCESS, teacherName, teacheridString, courseName, courseidString));
        }
    }
}
