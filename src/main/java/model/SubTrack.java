package model;

import enums.Status;
import enums.TaskType;
import lombok.Data;

import java.util.Arrays;

@Data
public class SubTrack extends Task {

    private static final TaskType TYPE = TaskType.SUB_TRACK;

    private String title;

    public SubTrack() {
        super(TYPE, Arrays.asList(Status.OPEN, Status.IN_PROGRESS, Status.COMPLETED));
    }
}
