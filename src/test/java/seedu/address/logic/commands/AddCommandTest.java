/*
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.commandAdd.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
<<<<<<< HEAD
import seedu.address.model.modelCourse.ReadOnlyCourseAddressBook;
=======
import seedu.address.model.modelCourseStudent.CourseStudent;
>>>>>>> 74d1a0f1335c84b8e209886663d9f3bbd4cd0691
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }
 */
    /*
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTeacherAddressBookFilePath() {
            return null;
        }

        @Override
        public void setTeacherAddressBookFilePath(Path teacherAddressBookFilePath) {

        }

        @Override
        public ReadOnlyAddressBookGeneric<Teacher> getTeacherAddressBook() {
            return null;
        }

        @Override
        public void setTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook) {

        }

        @Override
        public boolean hasTeacher(Teacher teacher) {
            return false;
        }

        @Override
        public void deleteTeacher(Teacher target) {

        }

        @Override
        public void addTeacher(Teacher teacher) {

        }

        @Override
        public void setTeacher(Teacher target, Teacher editedTeacher) {

        }

        @Override
        public ObservableList<Teacher> getFilteredTeacherList() {
            return null;
        }

        @Override
        public void updateFilteredTeacherList(Predicate<Teacher> predicate) {

        }

        @Override
        public Path getStudentAddressBookFilePath() {
            return null;
        }

        @Override
        public void setStudentAddressBookFilePath(Path studentAddressBookFilePath) {

        }

        @Override
        public ReadOnlyAddressBookGeneric<Student> getStudentAddressBook() {
            return null;
        }

        @Override
        public void setStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook) {

        }

        @Override
        public boolean hasStudent(Student student) {
            return false;
        }

        @Override
        public void deleteStudent(Student target) {

        }

        @Override
        public void addStudent(Student student) {

        }

        @Override
        public void setStudent(Student target, Student editedStudent) {

        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            return null;
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {

        }

        @Override
        public Path getCourseAddressBookFilePath() {
            return null;
        }

        @Override
        public void setCourseAddressBookFilePath(Path courseAddressBookFilePath) {

        }

        @Override
        publicReadOnlyAddressBookGeneric<Course> getCourseAddressBook() {
            return null;
        }

        @Override
        public void setCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook) {

        }

        @Override
        public boolean hasCourse(Course course) {
            return false;
        }

        @Override
        public void deleteCourse(Course target) {

        }

        @Override
        public void addCourse(Course course) {

        }

        @Override
        public void setCourse(Course target, Course editedCourse) {

        }

        @Override
        public ObservableList<Course> getFilteredCourseList() {
            return null;
        }

        @Override
        public void updateFilteredCourseList(Predicate<Course> predicate) {

        }

        @Override
        public Path getFinanceAddressBookFilePath() {
            return null;
        }

        @Override
        public void setFinanceAddressBookFilePath(Path financeAddressBookFilePath) {

        }

        @Override
        public ReadOnlyAddressBookGeneric<Finance> getFinanceAddressBook() {
            return null;
        }

        @Override
        public void setFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook) {

        }

        @Override
        public boolean hasFinance(Finance finance) {
            return false;
        }

        @Override
        public void deleteFinance(Finance target) {

        }

        @Override
        public void addFinance(Finance finance) {

        }

        @Override
        public void setFinance(Finance target, Finance editedFinance) {

        }

        @Override
        public ObservableList<Finance> getFilteredFinanceList() {
            return null;
        }

        @Override
        public void updateFilteredFinanceList(Predicate<Finance> predicate) {

        }

        @Override
        public Path getAssignmentAddressBookFilePath() {
            return null;
        }

        @Override
        public void setAssignmentAddressBookFilePath(Path assignmentAddressBookFilePath) {

        }

        @Override
        public ReadOnlyAddressBookGeneric<Assignment> getAssignmentAddressBook() {
            return null;
        }

        @Override
        public void setAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook) {

        }

        @Override
        public boolean hasAssignment(Assignment assignment) {
            return false;
        }

        @Override
        public void deleteAssignment(Assignment assignment) {

        }

        @Override
        public void addAssignment(Assignment assignment) {

        }

        @Override
        public void setAssignment(Assignment target, Assignment editedAssignment) {

        }

        @Override
        public ObservableList<Assignment> getFilteredAssignmentList() {
            return null;
        }

        @Override
        public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {

        }
<<<<<<< HEAD
=======

        @Override
        public Path getCourseStudentAddressBookFilePath() {
            return null;
        }

        @Override
        public void setCourseStudentAddressBookFilePath(Path courseStudentAddressBookFilePath) {

        }

        @Override
        public ReadOnlyAddressBookGeneric<CourseStudent> getCourseStudentAddressBook() {
            return null;
        }

        @Override
        public void setCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentAddressBook) {

        }

        @Override
        public boolean hasCourseStudent(CourseStudent courseStudent) {
            return false;
        }

        @Override
        public void deleteCourseStudent(CourseStudent courseStudent) {

        }

        @Override
        public void addCourseStudent(CourseStudent courseStudent) {

        }

        @Override
        public void setCourseStudent(CourseStudent target, CourseStudent editedCourseStudent) {

        }

        @Override
        public ObservableList<CourseStudent> getFilteredCourseStudentList() {
            return null;
        }

        @Override
        public void updateFilteredCourseStudentList(Predicate<CourseStudent> predicate) {

        }

        @Override
        public void updateCourseStudents() {

        }
>>>>>>> 74d1a0f1335c84b8e209886663d9f3bbd4cd0691
    }
    */

    /**
     * A Model stub that contains a single person.
     */
    /*
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }
    */
    /**
     * A Model stub that always accept the person being added.
     */
    /*
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
    */
/*
}*/
