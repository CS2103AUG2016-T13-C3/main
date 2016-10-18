package seedu.malitio.model.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.malitio.commons.exceptions.DuplicateDataException;
import seedu.malitio.commons.util.CollectionUtil;
import seedu.malitio.model.task.UniqueFloatingTaskList.DuplicateFloatingTaskException;
import seedu.malitio.model.task.UniqueFloatingTaskList.FloatingTaskNotFoundException;

import java.util.*;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueDeadlineList implements Iterable<Deadline> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateDeadlineException extends DuplicateDataException {
        protected DuplicateDeadlineException() {
            super("Operation would result in duplicate deadlines");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class DeadlineNotFoundException extends Exception {}

    private final ObservableList<Deadline> internalList = FXCollections.observableArrayList();

    /**
     * Constructs empty TaskList.
     */
    public UniqueDeadlineList() {}

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyDeadline toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateFloatingTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Deadline toAdd) throws DuplicateDeadlineException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateDeadlineException();
        }
        internalList.add(toAdd);
    }
    
    public void edit(Deadline edited, ReadOnlyDeadline beforeEdit) throws DuplicateDeadlineException, DeadlineNotFoundException {
        assert edited!=null;
        assert beforeEdit!=null;
        if (contains(edited)) {
            throw new DuplicateDeadlineException();
        }
        
        if (!contains(beforeEdit)) {
            throw new DeadlineNotFoundException();
        }
        
        int indexToReplace = internalList.indexOf(beforeEdit);
        internalList.add(indexToReplace, edited);
        internalList.remove(beforeEdit);
    }

    /**
     * Removes the equivalent schedule from the list.
     *
     * @throws DeadlineNotFoundException if no such deadline could be found in the list.
     */
    public boolean remove(ReadOnlyDeadline toRemove) throws DeadlineNotFoundException {
        assert toRemove != null;
        final boolean deadlineFoundAndDeleted = internalList.remove(toRemove);
        if (!deadlineFoundAndDeleted) {
            throw new DeadlineNotFoundException();
        }
        return deadlineFoundAndDeleted;
    }

    public ObservableList<Deadline> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Deadline> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDeadlineList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueDeadlineList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}