package seedu.address.logic.commands;

import org.junit.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

    @Test
    public void execute_noPreviousCommand_fail() {
        assertCommandFailure(new ClearCommand("undo"), model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_UNDO_FAIL));
    }

    @Test
    public void execute_haveSuccessfulPreviousCommand_success() throws CommandException {
        List<Index> indexesToMarkDone = new ArrayList<>();
        indexesToMarkDone.add(INDEX_FIRST_ASSIGNMENT);

        DoneCommand doneCommand = new DoneCommand(indexesToMarkDone);
        doneCommand.execute(model);

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

        UndoCommand undoCommand = new UndoCommand("undo");

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_haveUnsuccessfulPreviousCommand_fail() throws CommandException {
        UndoneCommand undoneCommand = new UndoneCommand(INDEX_FIRST_ASSIGNMENT);
        undoneCommand.execute(model);

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        Model expectedModel = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);

        UndoCommand undoCommand = new UndoCommand("undo");

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongCommandFormat_fail() {
        assertCommandFailure(new ClearCommand("undo"), model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UndoCommand.MESSAGE_USAGE));
    }
}
