import java.util.ArrayList;

public class TaskManager {

    private static ArrayList <Task> tasks;

    public TaskManager (){
        tasks=new ArrayList<>();
    }

    public void addTask (Task task){
        tasks.add(task);
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public static void removeTask(Task task){
        tasks.remove(task);
    }


}
