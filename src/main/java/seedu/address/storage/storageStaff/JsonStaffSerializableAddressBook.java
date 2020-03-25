package seedu.address.storage.storageStaff;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelStaff.ReadOnlyStaffAddressBook;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.storage.storageStaff.JsonAdaptedStaff;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "staffaddressbook")
class JsonStaffSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STAFF = "Staffs list contains duplicate staff(s).";

    private final List<JsonAdaptedStaff> staffs = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given staffs.
     */
    @JsonCreator
    public JsonStaffSerializableAddressBook(
            @JsonProperty("staffs") List<JsonAdaptedStaff> staffs) {
        this.staffs.addAll(staffs);
    }

    /**
     * Converts a given {@code ReadOnlyStaffAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code
     *               JsonStaffSerializableAddressBook}.
     */
    public JsonStaffSerializableAddressBook(ReadOnlyStaffAddressBook source) {
        staffs.addAll(
                source.getStaffList().stream().map(JsonAdaptedStaff::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StaffAddressBook toModelType() throws IllegalValueException {
        StaffAddressBook staffAddressBook = new StaffAddressBook();
        for (JsonAdaptedStaff jsonAdaptedStaff : staffs) {
            Staff staff = jsonAdaptedStaff.toModelType();
            if (staffAddressBook.hasStaffs(staff)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STAFF);
            }
            staffAddressBook.addStaff(staff);
        }
        return staffAddressBook;
    }

}