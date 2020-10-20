package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.*;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static boolean isDateFormat(String keyword) {
        return keyword.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    private static boolean isTimeFormat(String keyword) {
        return keyword.matches("\\d{4}");
    }

    private static boolean moreThanOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        long countPrefixesPresent = Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count();
        return countPrefixesPresent > 1;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] keywords;
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_MODULE_CODE, PREFIX_PRIORITY);

        if (moreThanOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_DEADLINE, PREFIX_PRIORITY)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        try {
            if (argMultimap.getValue(PREFIX_NAME).isPresent() && argMultimap.getPreamble().isEmpty()) {
                keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
                for (String keyword : keywords) {
                    ParserUtil.parseName(keyword);
                }
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
            }
            if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent() && argMultimap.getPreamble().isEmpty()) {
                keywords = argMultimap.getValue(PREFIX_MODULE_CODE).get().split("\\s+");
                for (String keyword : keywords) {
                    ParserUtil.parseModuleCode(keyword);
                }
                return new FindCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList(keywords)));
            }
            if (argMultimap.getValue(PREFIX_DEADLINE).isPresent() && argMultimap.getPreamble().isEmpty()) {
                keywords = argMultimap.getValue(PREFIX_DEADLINE).get()
                        .split("\\s+");
                for (String keyword : keywords) {
                    if (!(isTimeFormat(keyword) || isDateFormat(keyword))) {
                        throw new ParseException(
                                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
                    }
                }
                return new FindCommand(new DeadlineContainsKeywordsPredicate(Arrays.asList(keywords)));
            }
            if (argMultimap.getValue(PREFIX_PRIORITY).isPresent() && argMultimap.getPreamble().isEmpty()) {
                keywords = argMultimap.getValue(PREFIX_PRIORITY).get().split("\\s+");
                for (String keyword : keywords) {
                    ParserUtil.parsePriority(keyword);
                }
                return new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList(keywords)));
            }
        }
        catch (ParseException pe) {
            throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
