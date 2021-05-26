package Repository.task;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskStorageImpl implements TaskStorage {

    private final HashMap<Long, Task> data;
    private Long autoId;

    public InMemoryTaskStorageImpl() {
        data = new HashMap<>();
        autoId = 0L;
    }

    @Override
    public Long add(Task item) {
        synchronized (this) {
            autoId = autoId + 1;
            data.put(autoId, item);
            return autoId;
        }
    }

    @Override
    public Task getById(Long id) {
        return data.get(id);
    }

    @Override
    public void updateById(Long id, Task item) {
        synchronized (this) {
            data.put(id, item);
        }
    }

    @Override
    public List<Task> fetchByAssignee(String assignee) {
        List<Task> response = new ArrayList<>();

        for (Map.Entry<Long, Task> entry: data.entrySet()) {
            Task value = entry.getValue();
            if (value.getAssignee() != null && value.getAssignee().equals(assignee)) {
                response.add(value);
            }
        }

        return response;
    }

    @Override
    public List<Task> fetchBySprintId(Long sprintId) {
        List<Task> response = new ArrayList<>();

        for (Map.Entry<Long, Task> entry: data.entrySet()) {
            Task value = entry.getValue();
            if (value.getSprintId() != null && value.getSprintId().equals(sprintId)) {
                response.add(value);
            }
        }

        return response;
    }
}
