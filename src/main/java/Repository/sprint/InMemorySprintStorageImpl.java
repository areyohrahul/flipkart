package Repository.sprint;

import model.Sprint;

import java.util.HashMap;

public class InMemorySprintStorageImpl implements SprintStorage {

    private final HashMap<Long, Sprint> data;
    private Long autoId;

    public InMemorySprintStorageImpl() {
        data = new HashMap<>();
        autoId = 0L;
    }

    @Override
    public Long add(Sprint item) {
        synchronized (this) {
            autoId = autoId + 1;
            data.put(autoId, item);
            return autoId;
        }
    }

    @Override
    public void remove(Long id) {
        data.remove(id);
    }
}
