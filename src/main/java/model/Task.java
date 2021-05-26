package model;

import enums.Status;
import enums.TaskType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public abstract class Task {

    private Long id;
    private String title;
    private String creator;
    private String assignee;
    private List<Status> allowedStatus;
    private Status currentStatus;
    private TaskType taskType;
    private Long dueDate;
    private Long sprintId;

    public Task(TaskType taskType, List<Status> allowedStatus) {
        this.taskType = taskType;
        this.allowedStatus = allowedStatus;
    }
}
