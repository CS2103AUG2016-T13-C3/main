package seedu.malitio.model.task;

import seedu.malitio.commons.util.CollectionUtil;
import seedu.malitio.model.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Task in Malitio.
 * Guarantees: field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private DateTime due = null;
    private DateTime start = null;
    private DateTime end = null;
    
    private UniqueTagList tags;

    /**
     * Constructor for floating tasks.
     */
    public Task(Name name, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;

        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }
    
    /**
     * Constructor for deadlines. 
     */
    public Task(Name name, DateTime due, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        this.name = name;
        this.due = due;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }
    
    /**
     * Constructor for events. 
     */
    public Task(Name name, DateTime start, DateTime end, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, tags);
        // end must be > start check
        this.name = name;
        this.start = start;
        this.end = end;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }


    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
