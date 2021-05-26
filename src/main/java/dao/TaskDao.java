package dao;

import enums.Status;
import exceptions.InvalidStatusException;
import exceptions.InvalidTaskIdException;
import model.Task;
import Repository.task.TaskStorage;

import java.util.List;

public class TaskDao {

    private TaskStorage taskStorage;

    public TaskDao(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    public Long createTask(Task task) {
        return taskStorage.add(task);
    }

    public Task fetchById(Long id) throws InvalidTaskIdException {
        return taskStorage.getById(id);
    }

    public void updateStatus(Long id, Status status) throws InvalidStatusException {
        Task task = taskStorage.getById(id);
        if (task != null) {
            if (task.getAllowedStatus().contains(status)) {
                task.setCurrentStatus(status);
            } else {
                throw new InvalidStatusException();
            }
        }
    }

    public void updateAssignee(Long id, String assignee) throws InvalidTaskIdException {
        Task task = taskStorage.getById(id);
        if (task != null) {
            task.setAssignee(assignee);
            taskStorage.updateById(id, task);
        } else {
            throw new InvalidTaskIdException();
        }
    }

    public void updateSprintId(Long sprintId, Long taskId) throws InvalidTaskIdException {
        Task task = taskStorage.getById(taskId);
        if (task != null) {
            task.setSprintId(sprintId);
            taskStorage.updateById(taskId, task);
        } else {
            throw new InvalidTaskIdException();
        }
    }

    public List<Task> fetchAllByAssignee(String assignee) {
        return taskStorage.fetchByAssignee(assignee);
    }

    public List<Task> fetchAllBySprintId(Long sprintId) {
        return taskStorage.fetchBySprintId(sprintId);
    }
}
