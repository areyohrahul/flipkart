package model;

import enums.Status;
import enums.TaskType;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Story extends Task {

    private static final TaskType TYPE = TaskType.STORY;

    private String summary;
    private List<SubTrack> subTrackList;

    public Story() {
        super(TYPE, Arrays.asList(Status.OPEN, Status.IN_PROGRESS, Status.COMPLETED));
        subTrackList = new ArrayList<>();
    }

    public void addSubTrack(SubTrack subTrack) {
        subTrackList.add(subTrack);
    }
}
