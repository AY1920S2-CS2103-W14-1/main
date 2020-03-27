package seedu.address.model.modelCourse;

import seedu.address.model.modelGeneric.UniqueList;

/**
 * A list of courses that enforces uniqueness between its elements and does not allow nulls. A
 * course is considered unique by comparing using {@code Assignment#isSameCourse(Assignment)}. As such,
 * adding and updating of courses uses Assignment#isSameCourse(Assignment) for equality so as to ensure that
 * the course being added or updated is unique in terms of identity in the UniqueAssignmentList.
 * However, the removal of a course uses course#equals(Object) so as to ensure that the course with
 * exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Course#isSameCourse(Course)
 */
public class UniqueCourseList extends UniqueList<Course> {
}
