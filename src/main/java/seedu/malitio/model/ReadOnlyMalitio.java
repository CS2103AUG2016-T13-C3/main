package seedu.malitio.model;


import seedu.malitio.model.tag.Tag;
import seedu.malitio.model.tag.UniqueTagList;
import seedu.malitio.model.task.ReadOnlySchedule;
import seedu.malitio.model.task.ReadOnlyTask;
import seedu.malitio.model.task.UniqueScheduleList;
import seedu.malitio.model.task.UniqueTaskList;

import java.util.List;

/**
 * Unmodifiable view of an malitio
 */
public interface ReadOnlyMalitio {

    UniqueTagList getUniqueTagList();

    UniqueTaskList getUniqueTaskList();
    
    UniqueScheduleList getUniqueScheduleList();

    /**
     * Returns an unmodifiable view of tasks list
     */
    List<ReadOnlyTask> getTaskList();
    
    /**
     * Returns an unmodifiable view of tasks list
     */
    List<ReadOnlySchedule> getScheduleList();

    /**
     * Returns an unmodifiable view of tags list
     */
    List<Tag> getTagList();

}
