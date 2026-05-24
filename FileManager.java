import java.io.*;
import java.util.ArrayList;
import java.util.*;
public class FileManager {
    public void saveTasks(ArrayList <Task> tasks){
        try {
            File file = new File("tasks.txt");
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i<tasks.size();i++){
                writer.write(tasks.get(i).getTask()+" | "+tasks.get(i).isDone()+"\n");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error in file.");
        }
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File("tasks.txt");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    String taskText = parts[0].trim();
                    boolean done = Boolean.parseBoolean(parts[1].trim());
                    Task task = new Task(taskText);
                    task.setDone(done);
                    tasks.add(task);
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks.");
        }
        return tasks;
    }
}
