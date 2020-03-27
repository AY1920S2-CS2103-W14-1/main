package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalTeacher.TEACHER_ALICE;
import static seedu.address.testutil.TypicalTeacher.TEACHER_BENSON;
import static seedu.address.testutil.TypicalStaff.STAFF_ALICE;
import static seedu.address.testutil.TypicalStaff.STAFF_BENSON;
import static seedu.address.testutil.TypicalStudent.STUDENT_ALICE;
import static seedu.address.testutil.TypicalStudent.STUDENT_BENSON;
import static seedu.address.testutil.TypicalFinance.FINANCE_ALICE;
import static seedu.address.testutil.TypicalFinance.FINANCE_BENSON;
import static seedu.address.testutil.TypicalCourse.COURSE_ALICE;
import static seedu.address.testutil.TypicalCourse.COURSE_BENSON;
import static seedu.address.testutil.TypicalAssignment.ASSIGNMENT_EASY;
import static seedu.address.testutil.TypicalAssignment.ASSIGNMENT_HARD;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.model.modelStudent.StudentAddressBook;
import seedu.address.model.modelTeacher.TeacherAddressBook;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.*;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        TeacherAddressBook teacherAddressBook = new TeacherAddressBookBuilder().withTeacher(TEACHER_ALICE).withTeacher(TEACHER_BENSON).build();
        StaffAddressBook staffAddressBook = new StaffAddressBookBuilder().withStaff(STAFF_ALICE).withStaff(STAFF_BENSON).build();
        StudentAddressBook studentAddressBook = new StudentAddressBookBuilder().withStudent(STUDENT_ALICE).withStudent(STUDENT_BENSON).build();
        FinanceAddressBook financeAddressBook = new FinanceAddressBookBuilder().withFinance(FINANCE_ALICE).withFinance(FINANCE_BENSON).build();
        CourseAddressBook courseAddressBook = new CourseAddressBookBuilder().withCourse(COURSE_ALICE).withCourse(COURSE_BENSON).build();
        AssignmentAddressBook assignmentAddressBook = new AssignmentBookBuilder().withAssignment(ASSIGNMENT_EASY).withAssignment(ASSIGNMENT_HARD).build();


        AddressBook differentAddressBook = new AddressBook();
        TeacherAddressBook differentTeacherAddressBook = new TeacherAddressBook();
        StaffAddressBook differentStaffAddressBook = new StaffAddressBook();
        StudentAddressBook differentStudentAddressBook = new StudentAddressBook();
        FinanceAddressBook differentFinanceAddressBook = new FinanceAddressBook();
        CourseAddressBook differentCourseAddressBook = new CourseAddressBook();
        AssignmentAddressBook differentAssignmentAddressBook = new AssignmentAddressBook();


        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, teacherAddressBook, staffAddressBook, studentAddressBook, financeAddressBook, courseAddressBook, assignmentAddressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, teacherAddressBook, staffAddressBook, studentAddressBook, financeAddressBook, courseAddressBook, assignmentAddressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, differentTeacherAddressBook, differentStaffAddressBook, differentStudentAddressBook,
            differentFinanceAddressBook, differentCourseAddressBook, differentAssignmentAddressBook,userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, differentTeacherAddressBook, differentStaffAddressBook, differentStudentAddressBook,
            differentFinanceAddressBook, differentCourseAddressBook, differentAssignmentAddressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, teacherAddressBook, staffAddressBook, studentAddressBook, financeAddressBook, courseAddressBook, assignmentAddressBook,differentUserPrefs)));
    }
}
