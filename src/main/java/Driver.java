import Repository.sprint.InMemorySprintStorageImpl;
import Repository.sprint.SprintStorage;
import Repository.task.InMemoryTaskStorageImpl;
import Repository.task.TaskStorage;
import dao.SprintDao;
import dao.TaskDao;
import enums.BugSeverity;
import enums.Status;
import exceptions.InvalidTaskIdException;
import exceptions.StoryAlreadyCompletedException;
import model.*;
import services.SprintService;
import services.TaskService;

public class Driver {

    public static void main(String[] args) throws InvalidTaskIdException, StoryAlreadyCompletedException {
        // Init storage
        TaskStorage taskStorage = new InMemoryTaskStorageImpl();
        SprintStorage sprintStorage = new InMemorySprintStorageImpl();

        // Init dao
        TaskDao taskDao = new TaskDao(taskStorage);
        SprintDao sprintDao = new SprintDao(sprintStorage);

        // Init services
        TaskService taskService = new TaskService(taskDao);
        SprintService sprintService = new SprintService(sprintDao, taskDao);

        // Create a sprint
        Sprint sprint = new Sprint();
        sprint.setName("Sprint-1");
        Long sprintId = sprintService.createSprint(sprint);

        // Create a bug
        Bug bug = new Bug();
        bug.setTitle("Fix mysql issue");
        bug.setDueDate(16186435739764L);
        bug.setSeverity(BugSeverity.P0);
        Long bugId = taskService.createTask(bug);

        try {
            taskService.addTaskToSprint(sprintId, bugId);
        } catch (InvalidTaskIdException e) {
            e.printStackTrace();
        }

        // Create a feature
        Feature feature = new Feature();
        feature.setTitle("Setup console sprint");
        feature.setSummary("This is a short summary");
        feature.setDueDate(1618640668L);
        Long f1 = taskService.createTask(feature);

        Feature feature2 = new Feature();
        feature2.setTitle("Console api sprint");
        feature2.setSummary("This is a short summary");
        feature2.setDueDate(1818640668L);
        Long f2 = taskService.createTask(feature2);

        // Create a story
        Story story = new Story();
        story.setTitle("Create a microservice sprint");
        story.setSummary("This is a summary");
        story.setDueDate(118640668L);
        //story.setCurrentStatus(Status.COMPLETED);
        Long storyId = taskService.createTask(story);

        // Create a subtrack
        SubTrack subTrack = new SubTrack();
        subTrack.setTitle("Development");
        subTrack.setDueDate(1418640668L);
        Long subId = taskService.createTask(subTrack);

        taskService.addSubTrackToStory(subId, storyId);

        try {
            taskService.changeAssignee(bugId, "Rahul");
            taskService.changeAssignee(f1, "Rahul");
            taskService.changeAssignee(f2, "Rahul");
        } catch (InvalidTaskIdException e) {
            e.printStackTrace();
        }

        System.out.println("Tasks assigned to Rahul");
        System.out.println(taskService.fetchAllTasksForUser("Rahul"));

        try {
            taskService.addTaskToSprint(sprintId, f1);
            taskService.addTaskToSprint(sprintId, f2);
            taskService.addTaskToSprint(sprintId, bugId);
            taskService.addTaskToSprint(sprintId, storyId);
        } catch (InvalidTaskIdException e) {
            e.printStackTrace();
        }

        System.out.println("Tasks in Sprint 1");
        System.out.println(sprintService.fetchSnapshot(sprintId));
    }
}
