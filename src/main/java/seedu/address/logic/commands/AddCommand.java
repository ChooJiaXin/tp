package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

/**
 * Adds an assignment to the academic schedule.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the academic schedule. "
            + "Parameters: "
            + PREFIX_NAME + "ASSIGNMENT NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_MODULE_CODE + "MODULE "
            + "[remind] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Lab report 3 "
            + PREFIX_DEADLINE + "23-04-2020 1230 "
            + PREFIX_MODULE_CODE + "CS2100 "
            + "remind";
    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the academic schedule";

    private final Assignment toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Assignment}
     */
    public AddCommand(Assignment assignment) {
        requireNonNull(assignment);
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAssignment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
