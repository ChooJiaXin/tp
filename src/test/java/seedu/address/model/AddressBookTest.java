package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_HW;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS1231S_HW;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.model.lesson.Lesson;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.timetable.TimetableData;


public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getAssignmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateAssignments_throwsDuplicateAssignmentException() {
        // Two assignments with the same identity fields
        Assignment editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withModuleCode(VALID_MODULE_CODE_HW).build();
        List<Assignment> newAssignments = Arrays.asList(CS1231S_HW, editedCs1231sHw);
        List<Lesson> lessonList = Arrays.asList();
        AddressBookStub newData = new AddressBookStub(newAssignments, lessonList);

        assertThrows(DuplicateAssignmentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasAssignment(null));
    }

    @Test
    public void hasAssignment_assignmentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasAssignment(CS1231S_HW));
    }

    @Test
    public void hasAssignment_assignmentInAddressBook_returnsTrue() {
        addressBook.addAssignment(CS1231S_HW);
        assertTrue(addressBook.hasAssignment(CS1231S_HW));
    }

    @Test
    public void hasAssignment_assignmentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addAssignment(CS1231S_HW);
        Assignment editedCs1231sHw = new AssignmentBuilder(CS1231S_HW)
                .withModuleCode(VALID_MODULE_CODE_HW).build();
        assertTrue(addressBook.hasAssignment(editedCs1231sHw));
    }

    @Test
    public void getAssignmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getAssignmentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose assignments list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Assignment> assignments = FXCollections.observableArrayList();
        private final ObservableList<Lesson> lessons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Assignment> assignments, Collection<Lesson> lessons) {
            this.assignments.setAll(assignments);
            this.lessons.setAll(lessons);
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            return assignments;
        }

        @Override
        public ObservableList<Lesson> getLessonList() {
            return lessons;
        }

        @Override
        public void importTimetable(TimetableData data) {
        }
    }

}
