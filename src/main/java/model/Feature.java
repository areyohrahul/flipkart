package model;

import enums.FeatureImpact;
import enums.Status;
import enums.TaskType;
import lombok.Data;

import java.util.Arrays;

@Data
public class Feature extends Task {

    private static final TaskType TYPE = TaskType.FEATURE;

    private String summary;
    private FeatureImpact impact;

    public Feature() {
        super(TYPE, Arrays.asList(Status.OPEN, Status.IN_PROGRESS, Status.TESTING, Status.DEPLOYED));
    }
}
