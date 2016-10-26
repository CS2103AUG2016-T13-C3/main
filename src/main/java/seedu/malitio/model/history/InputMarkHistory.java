package seedu.malitio.model.history;

import seedu.malitio.model.task.ReadOnlyDeadline;
import seedu.malitio.model.task.ReadOnlyEvent;
import seedu.malitio.model.task.ReadOnlyFloatingTask;

public class InputMarkHistory extends InputHistory {
    
    ReadOnlyFloatingTask taskToMark;
    ReadOnlyDeadline deadlineToMark;
    ReadOnlyEvent eventToMark;
    boolean markWhat;
    String type;

    public InputMarkHistory(ReadOnlyFloatingTask taskToMark, boolean marked) {
        this.commandForUndo = "unmark";
        this.type = "floating task";
        this.taskToMark = taskToMark;
        if (marked) {
            this.markWhat = false;
        }
        else {
            this.markWhat = true;
        }
    }

    public InputMarkHistory(ReadOnlyDeadline deadlineToMark, boolean marked) {
        this.commandForUndo = "unmark";
        this.type = "deadline";
        this.deadlineToMark = deadlineToMark;
        if (marked) {
            this.markWhat = false;
        }
        else {
            this.markWhat = true;
        }
    }
    
    public InputMarkHistory(ReadOnlyEvent eventToMark, boolean marked) {
        this.commandForUndo = "unmark";
        this.type = "event";
        this.eventToMark = eventToMark;
        if (marked) {
            this.markWhat = false;
        }
        else {
            this.markWhat = true;
        }
    }
    
    public String getType() {
        return type;
    }
    
    public ReadOnlyFloatingTask getTaskToMark() {
        return taskToMark;
    }
    
    public ReadOnlyDeadline getDeadlineToMark() {
        return deadlineToMark;
    }
    
    public boolean getMarkWhat() {
        return markWhat;
    }
}