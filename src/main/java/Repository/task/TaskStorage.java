package Repository.task;

import model.Task;

import java.util.List;

public interface TaskStorage {

    Long add(Task item);
    Task getById(Long id);
    void updateById(Long id, Task item);
    List<Task> fetchByAssignee(String assignee);
    List<Task> fetchBySprintId(Long sprintId);
}
