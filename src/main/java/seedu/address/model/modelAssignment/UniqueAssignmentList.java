package seedu.address.model.modelAssignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.UniqueList;
import seedu.address.model.person.exceptions.AssignmentNotFoundException;
import seedu.address.model.person.exceptions.CourseNotFoundException;
import seedu.address.model.person.exceptions.DuplicateAssignmentException;
import seedu.address.model.person.exceptions.DuplicateCourseException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueAssignmentList extends UniqueList<Assignment> {
}
