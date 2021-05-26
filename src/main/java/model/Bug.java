package model;

import enums.BugSeverity;
import enums.Status;
import enums.TaskType;
import lombok.Data;

import java.util.Arrays;

@Data
public class Bug extends Task {

    private static final TaskType TYPE = TaskType.BUG;

    private BugSeverity severity;

    public Bug() {
        super(TYPE, Arrays.asList(Status.OPEN, Status.IN_PROGRESS, Status.FIXED));
    }
}
