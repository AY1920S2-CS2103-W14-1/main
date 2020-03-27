package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignment.getTypicalAssignmentAddressBook;
import static seedu.address.testutil.TypicalCourse.getTypicalCourseAddressBook;
import static seedu.address.testutil.TypicalFinance.getTypicalFinanceAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudent.getTypicalStudentAddressBook;
import static seedu.address.testutil.TypicalTeacher.getTypicalTeacherAddressBook;
import static seedu.address.testutil.TypicalStaff.getTypicalStaffAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.commandClear.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTeacherAddressBook(), getTypicalStaffAddressBook(), getTypicalStudentAddressBook(),
            getTypicalFinanceAddressBook(), getTypicalCourseAddressBook(), getTypicalAssignmentAddressBook(),new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTeacherAddressBook(), getTypicalStaffAddressBook(), getTypicalStudentAddressBook(),
            getTypicalFinanceAddressBook(), getTypicalCourseAddressBook(), getTypicalAssignmentAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
