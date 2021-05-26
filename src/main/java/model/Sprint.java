package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Sprint {

    private List<Task> tasks;
    private String name;

    public Sprint() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
