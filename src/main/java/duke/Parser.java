package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.ExtraDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidNumberException;

/**
 * Parser class.
 */
public class Parser {
    public static String[] oneLine;

    /**
     * isNumeric.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * findFeature().
     */
    public static FindCommand findFeature() throws DukeException {
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length == 2 && !oneLine[1].isBlank()) {
            return new FindCommand(oneLine);
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
    }

    /**
     * doneFeature().
     */
    public static DoneCommand doneFeature() throws DukeException {
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length == 2 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())) {
            return new DoneCommand(oneLine);
        } else if (oneLine.length == 2 && oneLine[1].trim().split(" ").length != 1) {
            throw new ExtraDescriptionException("There is extra description for done");
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
    }

    /**
     * deleteFeature().
     */
    public static DeleteCommand deleteFeature() throws DukeException {
        if (oneLine.length == 1 || (oneLine.length == 2 && oneLine[1].isBlank())) {
            throw new DukeException("The description of a " + oneLine[0].trim() + " cannot be empty.");
        } else if (oneLine.length != 1 && !oneLine[1].isBlank()
                && oneLine[1].trim().split(" ").length == 1 && isNumeric(oneLine[1].trim())) {
            return new DeleteCommand(oneLine);
        } else if (oneLine.length == 2 && oneLine[1].trim().split(" ").length != 1) {
            throw new ExtraDescriptionException("There is extra description for delete");
        } else {
            throw new InvalidNumberException("the description should be a number");
        }
    }

    /**
     * childFeature().
     */
    public static AddCommand childFeature() throws DukeException {
        String firstWord = oneLine[0];
        if (oneLine.length == 2 && !oneLine[1].isBlank()) {
            return new AddCommand(oneLine);
        } else {
            throw new DukeException("The description of a " + firstWord + " cannot be empty.");
        }
    }

    /**
     * Parser.
     */
    public static Command parse(String input) throws DukeException {
        Command outCommand = null;
        String cmd = input;
        oneLine = cmd.trim().split(" ", 2);
        String firstWord = oneLine[0];
        if (firstWord.equals("exit")) {
            if (oneLine.length == 2 && oneLine[1].trim().equals("duke")) {
                outCommand = new ExitCommand("exit duke");
            } else {
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } else if (firstWord.equals("bye")) {
            if (oneLine.length != 1) {
                throw new ExtraDescriptionException("There is extra description for bye");
            }
            outCommand = new ExitCommand();
        } else if (firstWord.equals("list")) {
            if (oneLine.length != 1) {
                throw new ExtraDescriptionException("There is extra description for list");
            }
            outCommand = new ListCommand();
        } else if (firstWord.equals("find")) {
            outCommand = findFeature();
        } else if (firstWord.equals("done")) {
            outCommand = doneFeature();
        } else if (firstWord.equals("delete")) {
            outCommand = deleteFeature();
        } else if (firstWord.equals("todo") || firstWord.equals("deadline")
                || firstWord.equals("event")) {
            outCommand = childFeature();
        } else {
            throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }
        return outCommand;
    }
}