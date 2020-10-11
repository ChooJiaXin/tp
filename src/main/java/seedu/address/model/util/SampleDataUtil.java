package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Remind;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Remind NOT_REMINDED = new Remind();
    public static Assignment[] getSampleAssignments() {
        return new Assignment[] {
            new Assignment(new Name("CS1231S Homework"), new Deadline("12-12-2020 1200"),
                    new ModuleCode("CS1231S"), NOT_REMINDED),
            new Assignment(new Name("CS2103T Quiz"), new Deadline("09-10-2020 2359"),
                    new ModuleCode("CS2103T"), NOT_REMINDED),
            new Assignment(new Name("CS2106 Lab"), new Deadline("08-08-2020 1900"),
                    new ModuleCode("CS2106"), NOT_REMINDED),
            new Assignment(new Name("Peer review"), new Deadline("25-12-2020 1200"),
                    new ModuleCode("CS2101"), NOT_REMINDED),
            new Assignment(new Name("IS1103 Mission"), new Deadline("13-10-2020 1300"),
                    new ModuleCode("IS1103"), NOT_REMINDED),
            new Assignment(new Name("Oral Presentation"), new Deadline("30-04-2020 1700"),
                    new ModuleCode("CS2101"), NOT_REMINDED)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Assignment sampleAssignment : getSampleAssignments()) {
            sampleAb.addAssignment(sampleAssignment);
        }
        return sampleAb;
    }

}
