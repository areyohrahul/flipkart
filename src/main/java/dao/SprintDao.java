package dao;

import Repository.sprint.SprintStorage;
import model.Sprint;

public class SprintDao {

    private SprintStorage sprintStorage;

    public SprintDao(SprintStorage sprintStorage) {
        this.sprintStorage = sprintStorage;
    }

    public Long createSprint(Sprint sprint) {
        return sprintStorage.add(sprint);
    }

    public void deleteSprint(Long id) {
        sprintStorage.remove(id);
    }
}
