package seedu.malitio.logic.commands;

import seedu.malitio.commons.exceptions.IllegalValueException;
import seedu.malitio.model.tag.Tag;
import seedu.malitio.model.tag.UniqueTagList;
import seedu.malitio.model.task.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a task to Malitio.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to Malitio.\n"
            + "Parameters: NAME [by DEADLINE] [start STARTTIME end ENDTIME] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " Pay John $100 by 10112016 2359 t/oweMoney";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in Malitio";

    private final Task toAdd;

    /**
     * Convenience constructor for floating tasks using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new FloatingTask(
                new Name(name),
                new UniqueTagList(tagSet)
        );
    }
    
    /**
     * Convenience constructor for deadlines using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, String date, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Deadlines(
                new Name(name),
                new DateTime(date),
                new UniqueTagList(tagSet)
        );
    }
    
    /**
     * Convenience constructor for events using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, String start, String end, Set<String> tags)
            throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        // check if start < end
        this.toAdd = new Events(
                new Name(name),
                new DateTime(start),
                new DateTime(end),
                new UniqueTagList(tagSet)
        );
    }
    @Override
    public CommandResult execute() {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }

    }

}