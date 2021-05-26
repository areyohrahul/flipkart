package services;

import dao.SprintDao;
import dao.TaskDao;
import model.Sprint;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SprintService {

    private final SprintDao sprintDao;
    private final TaskDao taskDao;

    public SprintService(final SprintDao sprintDao, final TaskDao taskDao) {
        this.sprintDao = sprintDao;
        this.taskDao = taskDao;
    }

    public Long createSprint(Sprint sprint) {
        return sprintDao.createSprint(sprint);
    }

    public void deleteSprint(Long id) {
        sprintDao.deleteSprint(id);
    }

    public Map<String, List<Task>> fetchSnapshot(Long id) {
        Long currentTime = System.currentTimeMillis();
        Map<String, List<Task>> map = new HashMap<>();
        List<Task> tasks = taskDao.fetchAllBySprintId(id);

        for (Task task: tasks) {
            if (task.getDueDate() < currentTime) {
                List<Task> existingTasks = map.getOrDefault("DELAYED", new ArrayList<>());
                existingTasks.add(task);
                map.put("DELAYED", existingTasks);
            } else {
                List<Task> existingTasks = map.getOrDefault("ON_TRACK", new ArrayList<>());
                existingTasks.add(task);
                map.put("ON_TRACK", existingTasks);
            }
        }

        return map;
    }
}
