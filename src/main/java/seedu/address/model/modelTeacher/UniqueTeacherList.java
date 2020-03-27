package seedu.address.model.modelTeacher;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.UniqueList;
import seedu.address.model.person.exceptions.DuplicateTeacherException;
import seedu.address.model.person.exceptions.TeacherNotFoundException;

/**
 * A list of teachers that enforces uniqueness between its elements and does not allow nulls. A
 * teacher is considered unique by comparing using {@code Teacher#isSameTeacher(Teacher)}. As such,
 * adding and updating of teachers uses Teacher#isSameTeacher(Teacher) for equality so as to ensure
 * that the teacher being added or updated is unique in terms of identity in the UniqueTeacherList.
 * However, the removal of a teacher uses Teacher#equals(Object) so as to ensure that the teacher
 * with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueTeacherList extends UniqueList<Teacher> {
}
