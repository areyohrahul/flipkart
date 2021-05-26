package Repository.sprint;

import model.Sprint;

public interface SprintStorage {

    Long add(Sprint sprint);
    void remove(Long id);
}
