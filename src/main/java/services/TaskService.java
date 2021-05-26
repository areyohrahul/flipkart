package services;

import dao.TaskDao;
import enums.Status;
import enums.TaskType;
import exceptions.InvalidStatusException;
import exceptions.InvalidTaskIdException;
import exceptions.StoryAlreadyCompletedException;
import model.Story;
import model.SubTrack;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskService {

    private final TaskDao taskDao;

    public TaskService(final TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public Long createTask(Task task) {
        // validations
        return taskDao.createTask(task);
    }

    public void addSubTrackToStory(Long subTrackId, Long storyId) throws InvalidTaskIdException, StoryAlreadyCompletedException {
        SubTrack subTrack = (SubTrack) taskDao.fetchById(subTrackId);
        Story story = (Story) taskDao.fetchById(storyId);

        if (story.getCurrentStatus() == Status.COMPLETED) {
            throw new StoryAlreadyCompletedException();
        }
        story.addSubTrack(subTrack);
    }

    public void addTaskToSprint(Long sprintId, Long taskId) throws InvalidTaskIdException {
        taskDao.updateSprintId(sprintId, taskId);
    }

    public void changeStatus(Long id, Status status) throws InvalidStatusException {
        taskDao.updateStatus(id, status);
    }

    public void changeAssignee(Long id, String assignee) throws InvalidTaskIdException {
        taskDao.updateAssignee(id, assignee);
    }

    public HashMap<TaskType, List<Task>> fetchAllTasksForUser(String assignee) {
        HashMap<TaskType, List<Task>> map = new HashMap<>();
        List<Task> tasks = taskDao.fetchAllByAssignee(assignee);
        for (Task task: tasks) {
            List<Task> existingTasks = map.getOrDefault(task.getTaskType(), new ArrayList<>());
            existingTasks.add(task);
            map.put(task.getTaskType(), existingTasks);
        }
        return map;
    }
}
