package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_HW = "CS1231S Homework";
    public static final String VALID_NAME_LAB = "CS2106 Lab";
    public static final String VALID_DEADLINE_HW = "01-01-2020 1800";
    public static final String VALID_DEADLINE_LAB = "02-02-2020 2000";
    public static final String VALID_MODULE_CODE_HW = "CS2103T";
    public static final String VALID_MODULE_CODE_LAB = "CS2100";
    public static final String VALID_REMIND = "remind";

    public static final String NAME_DESC_HW = " " + PREFIX_NAME + VALID_NAME_HW;
    public static final String NAME_DESC_LAB = " " + PREFIX_NAME + VALID_NAME_LAB;
    public static final String DEADLINE_DESC_HW = " " + PREFIX_DEADLINE + VALID_DEADLINE_HW;
    public static final String DEADLINE_DESC_LAB = " " + PREFIX_DEADLINE + VALID_DEADLINE_LAB;
    public static final String MODULE_CODE_DESC_HW = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_HW;
    public static final String MODULE_CODE_DESC_LAB = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_LAB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "CS1231&"; // '&' not allowed in names
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE
            + "31-02-2020 0000"; // 31st Feb does not exist
    public static final String INVALID_MODULE_CODE_DESC = " "
            + PREFIX_MODULE_CODE; // empty string not allowed for module code
    public static final String REMIND_DESC = " " + VALID_REMIND;
    public static final String INVALID_REMIND = " " + "remin"; // wrong spelling
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditAssignmentDescriptor DESC_HW;
    public static final EditCommand.EditAssignmentDescriptor DESC_LAB;

    static {
        DESC_HW = new EditAssignmentDescriptorBuilder().withName(VALID_NAME_HW)
                .withDeadline(VALID_DEADLINE_HW).withModuleCode(VALID_MODULE_CODE_HW).build();
        DESC_LAB = new EditAssignmentDescriptorBuilder().withName(VALID_NAME_LAB)
                .withDeadline(VALID_DEADLINE_LAB).withModuleCode(VALID_MODULE_CODE_LAB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered assignment list and selected assignment in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Assignment> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAssignmentList());
        List<Assignment> expectedRemindedList = new ArrayList<>(actualModel.getRemindedAssignmentsList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredAssignmentList());
        assertEquals(expectedRemindedList, actualModel.getRemindedAssignmentsList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the assignment at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showAssignmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAssignmentList().size());

        Assignment assignment = model.getFilteredAssignmentList().get(targetIndex.getZeroBased());
        final String[] splitName = assignment.getName().fullName.split("\\s+");
        model.updateFilteredAssignmentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredAssignmentList().size());
    }

}
