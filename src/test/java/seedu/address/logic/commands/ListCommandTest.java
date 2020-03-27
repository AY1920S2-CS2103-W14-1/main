package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalAssignment.getTypicalAssignmentAddressBook;
import static seedu.address.testutil.TypicalCourse.getTypicalCourseAddressBook;
import static seedu.address.testutil.TypicalFinance.getTypicalFinanceAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudent.getTypicalStudentAddressBook;
import static seedu.address.testutil.TypicalTeacher.getTypicalTeacherAddressBook;
import static seedu.address.testutil.TypicalStaff.getTypicalStaffAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandList.ListCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTeacherAddressBook(), getTypicalStaffAddressBook(), getTypicalStudentAddressBook(),
            getTypicalFinanceAddressBook(), getTypicalCourseAddressBook(), getTypicalAssignmentAddressBook(),new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getTeacherAddressBook(), model.getStaffAddressBook(),
            model.getStudentAddressBook(), model.getFinanceAddressBook(), model.getCourseAddressBook(),
                model.getAssignmentAddressBook() ,new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
