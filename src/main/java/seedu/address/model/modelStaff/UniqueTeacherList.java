package seedu.address.model.modelStaff;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.modelGeneric.UniqueList;

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
