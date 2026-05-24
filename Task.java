public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public Task() {}

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void toggleIsDone() {
        isDone = !isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "✔ " + task;
        } else {
            return "✖ " + task;
        }
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }
}