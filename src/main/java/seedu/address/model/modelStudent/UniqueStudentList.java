package seedu.address.model.modelStudent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.UniqueList;
import seedu.address.model.person.exceptions.DuplicateStudentException;
import seedu.address.model.person.exceptions.StudentNotFoundException;

/**
 * A list of students that enforces uniqueness between its elements and does not allow nulls. A
 * student is considered unique by comparing using {@code Student#isSameFinance(Student)}. As such,
 * adding and updating of students uses Student#isSameStudent(Student) for equality so as to ensure
 * that the student being added or updated is unique in terms of identity in the UniqueStudentList.
 * However, the removal of a student uses student#equals(Object) so as to ensure that the student
 * with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueStudentList extends UniqueList<Student> {
}
